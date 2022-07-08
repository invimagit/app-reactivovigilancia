/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.beans;

import co.gov.invima.BO.ReactivoBO;
import co.gov.invima.VO.MenusVO;
import co.gov.invima.VO.UsuariosVO;
import co.gov.invima.delegate.ReactivoUsuarioInternetDelegate;
import co.gov.invima.util.PropertiesReader;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Priority;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author mgualdrond
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean extends BaseBean implements Serializable {

    private final static Logger logBeanWebReactivo = Logger.getLogger(LoginBean.class.getName());

    private ReactivoBO reactivoBO;
    private UsuariosVO usuariosVO;
    private String btnLogin = "btnLogin";
    private String mensaje;
    private HttpSession session;
    private MenuModel model;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        super();
        reactivoBO = new ReactivoBO();
        usuariosVO = new UsuariosVO();
    }

    public UsuariosVO getUsuariosVO() {
        return usuariosVO;
    }

    public void setUsuariosVO(UsuariosVO usuariosVO) {
        this.usuariosVO = usuariosVO;
    }

    public String getBtnLogin() {
        return btnLogin;
    }

    public void setBtnLogin(String btnLogin) {
        this.btnLogin = btnLogin;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String validarUsuario() {
        String resultado = "";
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }

             logBeanWebReactivo.info("LoginBean - validarUsuario - IP ADDRESS--> {0}"+ ipAddress);

            PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
            String prefijoRed = pr.getProperty("sufijoRedInvima");
            logBeanWebReactivo.info ("LoginBean - validarUsuario - SUFIJO DE RED PARA LA AUTENTICACIÓN = " + prefijoRed);

            UsuariosVO usuarioVerifica = null;
            //if (ipAddress.contains(prefijoRed)) 
            try  
            {
                logBeanWebReactivo.info ("LoginBean - validarUsuario - if (ipAddress.contains(prefijoRed))  ");
                logBeanWebReactivo.info ("LoginBean - validarUsuario -AUTENTICACION POR PREFIJO DE RED = " + prefijoRed);
                usuarioVerifica = ReactivoUsuarioInternetDelegate.validarUsuarioFuncionario(usuariosVO.getUsuario(), usuariosVO.getPassword());
                if(usuarioVerifica.getRespuesta().equals("Usuario o contraseña errados")){
                    usuarioVerifica = null;
                    logBeanWebReactivo.info ("LoginBean - validarUsuario -AUTENTICACION POR PREFIJO DE RED ****FALLIDA**** " );
                }
                try{logBeanWebReactivo.info ("LoginBean - validarUsuario -USUARIO ENCONTRADO = " + usuarioVerifica);}catch(Exception e){}
                if (usuarioVerifica != null) {
                    logBeanWebReactivo.info ("LoginBean - validarUsuario -RESPUESTA = " + usuarioVerifica.getRespuesta());                    
                    if (usuarioVerifica.getRolPalabra()!=null && usuarioVerifica.getRespuesta().equals("OK")) {
                        usuariosVO = usuarioVerifica;
                        resultado = "faces/pages/Principal.xhtml";
                        logBeanWebReactivo.info ("ROL DE USUARIO SESIONADO POR DIRECTORIO ACTIVO = " + usuarioVerifica.getIdRol());
                        this.cargarMenu(usuarioVerifica.getIdRol());
                        getSession().setAttribute("usuario", usuariosVO);
                        getSession().setAttribute("nombre", usuariosVO.getNombrePersona());
                        if (usuariosVO.getNombreEmpresa() != null)
                        {
                            getSession().setAttribute("nombreEmpresa", usuariosVO.getNombrePersona());
                        }
                        else
                        {
                            getSession().setAttribute("nombreEmpresa", "INSTITUTO NACIONAL DE VIGILANCIA DE MEDICAMENTOS Y ALIMENTOS (INVIMA)");
                        }
                    } 
                    else 
                    {
                        mensaje = usuarioVerifica.getRespuesta();
                        FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, null);
                        FacesContext.getCurrentInstance().addMessage("mensajeLogin", error);

                    }
                    logBeanWebReactivo.log(Priority.INFO, "RESPUESTA USUARIO INVIMA: "+usuarioVerifica.getRespuesta());
                }
            }
            catch(Exception e){
                logBeanWebReactivo.info ("LoginBean - validarUsuario -AUTENTICACION POR PREFIJO DE RED ****FALLIDA**** " );
            }
            if(usuarioVerifica == null)
            {
                logBeanWebReactivo.info ("LoginBean - validarUsuario - ELSE (ipAddress.contains(prefijoRed)) AUTENTICACION POR TABLA ");
                usuarioVerifica = reactivoBO.validarUsuarioInternet(usuariosVO.getUsuario(), usuariosVO.getPassword());
                if (usuarioVerifica != null) {
                    if (usuarioVerifica.getRolPalabra() != null && usuarioVerifica.getRolPalabra().equals("OK")) {
                        usuariosVO = usuarioVerifica;
                        resultado = "faces/pages/Principal.xhtml";
                        this.cargarMenu(usuarioVerifica.getIdRol());
                        //this.cargarMenu("1");*
                        logBeanWebReactivo.info ("LoginBean - validarUsuario -  usuario="+usuariosVO.getUsuario()+"- rol="+usuariosVO.getIdRol());
                        getSession().setAttribute("usuario", usuariosVO);
                        getSession().setAttribute("nombre", usuariosVO.getNombrePersona());
                        getSession().setAttribute("nombreEmpresa", usuariosVO.getNombreEmpresa());
                    } else {
                        mensaje = usuarioVerifica.getRespuesta();
                        FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, null);
                        FacesContext.getCurrentInstance().addMessage("mensajeLogin", error);
                    }
                } else {
                    mensaje = "Usuario no registrado";
                    FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, null);
                    FacesContext.getCurrentInstance().addMessage("mensajeLogin", error);
                }
                //*************************************************************************
                //*************************************************************************
            }
            //***************************************************************************
            //***************************************************************************
            //***************************************************************************
        } catch (Exception e) {
            logBeanWebReactivo.log(Priority.ERROR, "Error al validar usuario: ", e);
        } finally {
            return resultado;
        }
    }

    /*
    public String validarUsuario() {
        String resultado = "";
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            logBeanWebReactivo.logBeanWebReactivo(Level.INFO, "IP ADDRESS--> {0}", ipAddress);
            if (verificarIP(ipAddress)) {
                //loguea con usuario de base de daots que tenga permisos a reactivovigilancia
                UsuariosVO usuarioVerifica = reactivoBO.validarUsuarioFuncionario(usuariosVO.getUsuario(), usuariosVO.getPassword());
                if (usuarioVerifica != null) {
                    if (usuarioVerifica.getRolPalabra() != null && usuarioVerifica.getRespuesta().equals("OK")) {
                        usuariosVO = usuarioVerifica;
                        resultado = "faces/Principal.xhtml";
                        this.cargarMenu("2");
                        getSession().setAttribute("usuario", usuariosVO);
                        getSession().setAttribute("nombre", usuariosVO.getNombrePersona());
                    } else {
                        mensaje = usuarioVerifica.getRespuesta();
                        FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, null);
                        FacesContext.getCurrentInstance().addMessage("mensajeLogin", error);

                    }
                } else {
                    mensaje = "Usuario no registrado";
                    FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, null);
                    FacesContext.getCurrentInstance().addMessage("mensajeLogin", error);
                }
            } else {
                //loguea el usuario de acuerdo al registro de la tabla usuarios internet de reactivovigilancia
                UsuariosVO usuarioVerifica = reactivoBO.validarUsuarioInternet(usuariosVO.getUsuario(), usuariosVO.getPassword());
                if (usuarioVerifica != null) {
                    if (usuarioVerifica.getRolPalabra() != null && usuarioVerifica.getRolPalabra().equals("OK")) {
                        usuariosVO = usuarioVerifica;
                        resultado = "faces/Principal.xhtml";
                        this.cargarMenu("1");
                        getSession().setAttribute("usuario", usuariosVO);
                        getSession().setAttribute("nombre", usuariosVO.getNombrePersona());
                    } else {
                        mensaje = usuarioVerifica.getRespuesta();
                        FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, null);
                        FacesContext.getCurrentInstance().addMessage("mensajeLogin", error);
                    }
                } else {
                    mensaje = "Usuario no registrado";
                    FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, null);
                    FacesContext.getCurrentInstance().addMessage("mensajeLogin", error);
                }
            }
        } catch (Exception e) {
            logBeanWebReactivo.logBeanWebReactivo(Priority.ERROR, "Error al validar usuario: ", e);
        } finally {
            return resultado;
        }
    }
    */
    
    
    private Boolean verificarIP(String ipAddress){
        Boolean b  = false;
        try {
            PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
            String prefijoRed = pr.getProperty("REDLOCAL");
            String[] perfijos = prefijoRed.split(",");
            for (String perfijo : perfijos) {
                if(ipAddress.startsWith(perfijo)){
                    b=true;
                    break;
                }
            }
         } catch (Exception e) {
            logBeanWebReactivo.log(Priority.ERROR, "", e);
        } finally {
            return b;
        }
    }
    
    public HttpSession getSession() {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public String registrar() {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        UsuariosVO uvo = new UsuariosVO();
        uvo.setUsuario("INVITADO");
        session.setAttribute("usuario", uvo);
        return "faces/ReactivoUsuarioInternet.xhtml";
    }

    public String recuperarContrasena() {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        UsuariosVO uvo = new UsuariosVO();
        uvo.setUsuario("INVITADO");
        session.setAttribute("usuario", uvo);
        return "faces/recuperarContrasena.xhtml";
    }

    public void cargarMenu(String rol) {
        List<MenusVO> menus = null;
        //Se obtienen los menús para el usuario
        String urlAbsoluta = "";

        menus = reactivoBO.obtenerMenus(new Integer(rol));

        if (null != menus && !menus.isEmpty()) {
            //Si se obtienen menús, se arma el cuerpo de la URL absoluta
            HttpServletRequest extRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            //Se obtienen los atributos del request del contexto para armar la URL
            String esquema = extRequest.getScheme();
            String servidor = extRequest.getServerName();
            int puerto = extRequest.getServerPort();
            String rutaOrigen = extRequest.getContextPath();
            String rutaServlet = extRequest.getServletPath();
            
            if (puerto == 80)
            {
                //logBeanWebReactivo.info ("Redireccionando por puerto 80");
                urlAbsoluta = esquema + "://" + servidor + rutaOrigen + rutaServlet + "/";
            }
            else
            {
                //logBeanWebReactivo.info ("Redireccionando por puerto " + puerto);
                urlAbsoluta = esquema + "://" + servidor + ":" + puerto + rutaOrigen + rutaServlet + "/";
            }

            //El String armado se guarda en sesión
            getSession().setAttribute("urlAbsoluta", urlAbsoluta);

            model = new DefaultMenuModel();

            //A cada uno de los elementos obtenidos (opción de menú) se adhiere
            //el String armado, para que no se presenten problemas de navegación
            //entre opciones cuando no se hace desde la pantalla principal
            for (MenusVO menusVO : menus) {
                String urlCompleta = urlAbsoluta + menusVO.getUrlMenu();
                menusVO.setUrlMenu(urlCompleta);

                DefaultMenuItem botonMenu = new DefaultMenuItem();
                botonMenu.setValue(menusVO.getDescripcionMenu());
                botonMenu.setUrl(menusVO.getUrlMenu());
                botonMenu.setId("opcion_" + menusVO.getOpcionMenu());
                botonMenu.setOnclick("limpiarSesion()");

                model.addElement(botonMenu);
            }
            //Se establece el menú de opciones para el usuario en un atributo
            //de la sesión
            getSession().setAttribute("menus", menus);

        }
    }

}
