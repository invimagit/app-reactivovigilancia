package co.gov.invima.beans;

import co.gov.invima.BO.ReactivoBO;
import co.gov.invima.VO.CiudadesVO;
import co.gov.invima.VO.DepartamentoVO;
import co.gov.invima.VO.PaisesVO;
import co.gov.invima.VO.UsuariosVO;
import co.gov.invima.entities.ReactivoUsuariosInternet;
import co.gov.invima.sesion.ReactivoUsuariosInternetFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import javax.ejb.EJB;
import org.apache.log4j.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Priority;

@Named(value = "reactivoUsuarioInternetBean")
@SessionScoped
@ManagedBean(name = "reactivoUsuarioInternetBean")
public class ReactivoUsuarioInternetBean implements Serializable {

    private final Logger logBeanWebReactivo = Logger.getLogger(ReactivoUsuarioInternetBean.class.getName());
    private HttpSession session;
    private final ReactivoBO bO;
    private String codigoPais;
    private String codigoDpto;
    private String codigoMpio;
    private String direccionOrganizacion;
    private List<DepartamentoVO> departamentoVOs;
    private List<SelectItem> lstPais;
    private List<SelectItem> lstDpto;
    private List<SelectItem> lstCiudad;
    private boolean activaDptoMpioOrga;
    private String telefonoOrg;
    private String cedula;
    private String nombreApellidos;
    private String telefonoPersonal;
    private String correoPersonal;
    private String nit;
    private String razonSocial;
    private String correoCorp;
    private String cargoDesemp;
    private String usuario;
    private String clave;
    private String pregunta;
    private String respuesta;
    private UsuariosVO usuariosVO;
    private String mensajes;
    private ReactivoUsuariosInternet usuario_buscado;
    private boolean mostrar_datos_usuario_buscado = false;

    @EJB
    private ReactivoUsuariosInternetFacadeLocal reactivoUsuariosInternetFacadeLocal;

    public ReactivoUsuarioInternetBean() {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        bO = new ReactivoBO();
        lstPais = this.obtenerPais();
        activaDptoMpioOrga = false;
        departamentoVOs = bO.obtenerDptos();
        lstDpto = this.obtenerDepartamento();
    }

    private List<SelectItem> obtenerPais() {
        List<SelectItem> items = null;
        try {
            List<PaisesVO> pvos = bO.obtenerPaises();
            if (pvos != null && !pvos.isEmpty()) {
                items = new ArrayList<>();
                for (PaisesVO paisesVO : pvos) {
                    items.add(new SelectItem(paisesVO.getCdgPais(), paisesVO.getPais()));
                }
            }
        } catch (Exception e) {
            logBeanWebReactivo.log(Priority.ERROR, "Error al obtener Pais: ", e);
        } finally {
            return items;
        }
    }

    private List<SelectItem> obtenerDepartamento() {
        List<SelectItem> items = null;
        try {
            departamentoVOs = bO.obtenerDptos();
            if (departamentoVOs != null && !departamentoVOs.isEmpty()) {
                items = new ArrayList<>();
                for (DepartamentoVO departamentoVO : departamentoVOs) {
                    items.add(new SelectItem(departamentoVO.getCodDepart(), departamentoVO.getDescripcion()));
                }
            }
        } catch (Exception e) {
            logBeanWebReactivo.log(Priority.ERROR, "Error al obtener lista de Dpto", e);
        } finally {
            return items;
        }
    }

    public void changePais(ValueChangeEvent event) {
        String valor = (String) event.getNewValue();
        changePais_(valor);
    }
    
    public void changePais_(String valor) {
        if (valor.equals("CO")) {
            this.activaDptoMpioOrga = true;
        } else {
            this.activaDptoMpioOrga = false;
        }
    }
    public void changeDpto(ValueChangeEvent event) {
        String valor = (String) event.getNewValue();
        changeDpto_(valor);
    }

    public void changeDpto_(String valor) {
        if (!valor.isEmpty()) {
            lstCiudad = new ArrayList<>();
            for (DepartamentoVO dvo : departamentoVOs) {
                if (dvo.getCodDepart().equals(valor)) {
                    for (CiudadesVO cvo : dvo.getCiudadesVOs()) {
                        lstCiudad.add(new SelectItem(cvo.getCodMun(), cvo.getCiudad()));
                    }
                    break;
                }
            }
        }
    }

    public void btnBuscarUsuarioInternet() {
        boolean busqueda_por_cedula_exitosa = false;
        //buscar por cedula
        try {
            long cedula_a_buscar = Long.parseLong(getCedula());
            usuario_buscado = reactivoUsuariosInternetFacadeLocal.validarCedulaExiste(cedula_a_buscar);
            if (usuario_buscado != null) {
                busqueda_por_cedula_exitosa = true;
            }
        } catch (Exception e) {
            busqueda_por_cedula_exitosa = false;
        }

        //busqueda por usuario
        if (!busqueda_por_cedula_exitosa) {
            try {
                usuario_buscado = reactivoUsuariosInternetFacadeLocal.validarUsuarioExiste(getCedula());
                if (usuario_buscado != null) {
                    busqueda_por_cedula_exitosa = true;
                }
            } catch (Exception e) {
                busqueda_por_cedula_exitosa = false;
            }
        }

        if (!busqueda_por_cedula_exitosa) {
            mostrar_datos_usuario_buscado = false;
            mensajes = "Usuario no encontrado";
            FacesContext.getCurrentInstance().addMessage("panelContenedorUsuarios", new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    mensajes, null));
            usuario_buscado = new ReactivoUsuariosInternet();
        } else {
            try {
                mostrar_datos_usuario_buscado = true;
                FacesContext.getCurrentInstance().getExternalContext().redirect("/ReactivoVigilanciaWeb/faces/pages/EditarUsuarioInternet.xhtml");
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(ReactivoUsuarioInternetBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try{
        changePais_(usuario_buscado.getCdgPais()); 
        changeDpto_(usuario_buscado.getCodDepart()); 
        }
        catch(Exception e){
            
        }
    }

    public void btnActualizarUsuario() {
        try {
            reactivoUsuariosInternetFacadeLocal.edit(usuario_buscado);
            mostrar_datos_usuario_buscado = false;
            mensajes = "Usuario Actualizado";
            FacesContext.getCurrentInstance().addMessage("panelContenedorUsuarios", new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    mensajes, null));
        } catch (Exception e) {
            mensajes = "Error al actualizar el usuario";
            FacesContext.getCurrentInstance().addMessage("panelContenedorUsuarios", new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    mensajes, null));
            java.util.logging.Logger.getLogger(ReactivoUsuarioInternetBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void btnCancelar() {
        try {
            cedula = "";
            mostrar_datos_usuario_buscado = false;
            usuario_buscado = new ReactivoUsuariosInternet();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ReactivoVigilanciaWeb/faces/pages/EditarUsuarioInternet.xhtml");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(InscripcionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnAceptar() {
        try {
            if (bO.existeUsuarioInternet(usuario)) {
                mensajes = "Nombre de usuario ya Registrado";
                FacesContext.getCurrentInstance().addMessage("panelContenedorUsuarios", new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        mensajes, null));
            } else {
                usuariosVO = new UsuariosVO();
                usuariosVO.setActivo(new Short("1"));
                usuariosVO.setCargoPersona(cargoDesemp);
                usuariosVO.setCdgPais(codigoPais);
                usuariosVO.setClasificacionUsuario("");
                usuariosVO.setCodDepart(codigoDpto);
                usuariosVO.setCodMun(codigoMpio);
                usuariosVO.setDireccionEmpresa(direccionOrganizacion.toUpperCase());
                usuariosVO.setEmailEmpresa(correoCorp);
                usuariosVO.setEmailPersona(correoPersonal);
                //usuariosVO.setEstadoUsuario('P');
                usuariosVO.setFax("NA");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss a");
                Date fecha = new Date();
                String fechaC = sdf.format(fecha);
                usuariosVO.setFechaIngreso(sdf.parse(fechaC));
                usuariosVO.setIdentificacionEmpresa(Long.parseLong(extraerNumeroDocumento(nit)));
                usuariosVO.setIdentificacionPersona(Long.parseLong(cedula));
                usuariosVO.setNombreEmpresa(razonSocial.toUpperCase());
                usuariosVO.setNombrePersona(nombreApellidos.toUpperCase());
                usuariosVO.setPassword(clave);
                usuariosVO.setPregunta(pregunta);
                usuariosVO.setRespuesta(respuesta.toUpperCase());
                usuariosVO.setTipidentificacionEmpresa("NI");
                usuariosVO.setTipidentificacionPersona("CC");
                usuariosVO.setTelefonoEmpresa(telefonoOrg);
                usuariosVO.setTelefonoPersona(telefonoPersonal);
                usuariosVO.setUrl("");
                usuariosVO.setUsuario(usuario);

                String guardarInscripcionPrograma = bO.guardarInscripcionPrograma(usuariosVO);
                if (guardarInscripcionPrograma.equals("OK")) {
                    mensajes = "Registrado en el programa nacional de Reactivovigilancia con el nombre de usuario: " + usuario;
                    FacesContext.getCurrentInstance().addMessage("panelContenedorUsuarios", new FacesMessage(FacesMessage.SEVERITY_INFO,
                            mensajes, null));
                } else {
                    mensajes = "No fue posible registrarse en el programa";
                    FacesContext.getCurrentInstance().addMessage("panelContenedorUsuarios", new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            mensajes, null));
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(ReactivoUsuarioInternetBean.class.getName()).log(Priority.ERROR, null, ex);
        }
    }

    public static String extraerNumeroDocumento(final String str) {

        if (str == null || str.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
                found = true;
            } else if (found) {
                break;
            }
        }

        return sb.toString();
    }

    public String retornoResultadoOK() {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "faces/index.xhtml";
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public List<SelectItem> getLstPais() {
        return lstPais;
    }

    public void setLstPais(List<SelectItem> lstPais) {
        this.lstPais = lstPais;
    }

    public boolean isActivaDptoMpioOrga() {
        return activaDptoMpioOrga;
    }

    public void setActivaDptoMpioOrga(boolean activaDptoMpioOrga) {
        this.activaDptoMpioOrga = activaDptoMpioOrga;
    }

    public String getCodigoDpto() {
        return codigoDpto;
    }

    public void setCodigoDpto(String codigoDpto) {
        this.codigoDpto = codigoDpto;
    }

    public String getCodigoMpio() {
        return codigoMpio;
    }

    public void setCodigoMpio(String codigoMpio) {
        this.codigoMpio = codigoMpio;
    }

    public List<SelectItem> getLstDpto() {
        return lstDpto;
    }

    public void setLstDpto(List<SelectItem> lstDpto) {
        this.lstDpto = lstDpto;
    }

    public List<SelectItem> getLstCiudad() {
        return lstCiudad;
    }

    public void setLstCiudad(List<SelectItem> lstCiudad) {
        this.lstCiudad = lstCiudad;
    }

    public String getDireccionOrganizacion() {
        return direccionOrganizacion;
    }

    public void setDireccionOrganizacion(String direccionOrganizacion) {
        this.direccionOrganizacion = direccionOrganizacion.toUpperCase();
    }

    public String getTelefonoOrg() {
        return telefonoOrg;
    }

    public void setTelefonoOrg(String telefonoOrg) {
        this.telefonoOrg = telefonoOrg;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos.toUpperCase();
    }

    public String getTelefonoPersonal() {
        return telefonoPersonal;
    }

    public void setTelefonoPersonal(String telefonoPersonal) {
        this.telefonoPersonal = telefonoPersonal;
    }

    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial.toUpperCase();
    }

    public String getCorreoCorp() {
        return correoCorp;
    }

    public void setCorreoCorp(String correoCorp) {
        this.correoCorp = correoCorp;
    }

    public String getCargoDesemp() {
        return cargoDesemp;
    }

    public void setCargoDesemp(String cargoDesemp) {
        this.cargoDesemp = cargoDesemp.toUpperCase();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta.toUpperCase();
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta.toUpperCase(Locale.FRENCH);
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public ReactivoUsuariosInternet getUsuario_buscado() {
        return usuario_buscado;
    }

    public void setUsuario_buscado(ReactivoUsuariosInternet usuario_buscado) {
        this.usuario_buscado = usuario_buscado;
    }

    public boolean isMostrar_datos_usuario_buscado() {
        return mostrar_datos_usuario_buscado;
    }

    public void setMostrar_datos_usuario_buscado(boolean mostrar_datos_usuario_buscado) {
        this.mostrar_datos_usuario_buscado = mostrar_datos_usuario_buscado;
    }

}
