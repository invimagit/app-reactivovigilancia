/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.beans;

import co.gov.invima.VO.MenusVO;
import co.gov.invima.VO.UsuariosVO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author mgualdrond
 */
@SessionScoped
@Named(value = "baseBean")
public class BaseBean extends AbstractBaseBean implements Serializable 
{
    //************************************************************************
    //************************************************************************
    //************************************************************************
    //************************************************************************
    //************************************************************************
    private final static Logger logBeanWebReactivo = Logger.getLogger(BaseBean.class.getName());
    //************************************************************************
    //************************************************************************
    //************************************************************************
    private boolean mostrarPoppup;
    private UsuariosVO usuarioVO;
    private String nombreUsuario;
    private MenuModel menu;
    private HttpSession sesion;
    private String nombreEmpresa;

    /**
     * Creates a new instance of BaseBean
     */
    public BaseBean() {
        usuarioVO = new UsuariosVO();
        menu = new DefaultMenuModel();
        this.armarMenu();

        sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    @PostConstruct
    public void obtenerNombreUsuario() {
        sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        String nombre = (String) sesion.getAttribute("nombre");
        String nombreEmpresa = (String)sesion.getAttribute("nombreEmpresa");
        //************************************************************
        //************************************************************
        //logBeanWebReactivo.info ("NOMBRE: " + nombre);
        //logBeanWebReactivo.info ("NOMBRE EMPRESA: " + nombreEmpresa);
        //************************************************************
        //************************************************************
        if (getUsuarioVO() != null) 
        {
            
            this.nombreUsuario = nombre;
            this.nombreEmpresa = nombreEmpresa;
        }
        else
        {
            this.nombreUsuario = "Usuario de ReactivoVigilancia";
            this.nombreEmpresa = "INVIMA";
        }
    }

    @Override
    public void limpiarFormulario() {
    }

    public String salir() {
        //logBeanWebReactivo.info("'''''''''Salieerr");
        FacesContext context = FacesContext.getCurrentInstance();

        ExternalContext externalContext = context.getExternalContext();
        Object session = externalContext.getSession(false);
        HttpSession httpSession = (HttpSession) session;
        httpSession.invalidate();
        return "/faces/index.xhtml";
    }

    public void armarMenu() {
        HttpSession session = (HttpSession) FacesContext
                .getCurrentInstance().getExternalContext().getSession(true);
        List<MenusVO> menus = (List<MenusVO>) session.getAttribute("menus");

        if (menus != null && !menus.isEmpty()) {

            MenuModel model = new DefaultMenuModel();

            DefaultSubMenu submenu = new DefaultSubMenu();
            //Submenu submenu2 = new Submenu();
            submenu.setLabel("Menus Usuario");
            //submenu2.setLabel("Submenu2");
            for (MenusVO menu : menus) {
                DefaultMenuItem item = new DefaultMenuItem() {
                };
                item.setValue(menu.getDescripcionMenu());
                item.setTitle(menu.getDescripcionMenu());
                item.setUrl(menu.getUrlMenu());
                submenu.addElement(item); //getChildren().add(item);
                //submenu2.getChildren().add(item1);
                //submenu2.getChildren().add(item2);
                model.addElement(submenu);
            }
            //model.addSubmenu(submenu2);
            this.setMenu(model);
        }

    }

    /**
     * Método toma los beans creados durante la navegación en la aplicación para
     * limpiar los formularios en los que son usados.
     */
    public void limpiarSesion() {
        //Se obtiene la lista de beans creados durante la navegación
        ArrayList<String> beansCreados = (ArrayList<String>) getSesion().getAttribute("beansCreados");

        //Se la lista de beans por los que el usuario ha navegado tiene elementos
        //se buscan dichos beans en el mapa de sesión
        if (null != beansCreados && !beansCreados.isEmpty()) {
            //Se obtiene el mapa de sesión
            Map<String, Object> mapaSesion = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            //Se recorre la lista de beans visitados por el usuario
            for (String beanEnLista : beansCreados) {
                //Se busca el nombre del bean en el mapa de sesión y se ejecuta 
                //el método limpiarFormulario de éste para asegurar que en
                //la próxima visita a la pantalla no aparezcan datos "pegados"
                BaseBean objetoBean = (BaseBean) mapaSesion.get(beanEnLista);
                objetoBean.limpiarFormulario();
            }
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(beanARemover);
        }
    }

    protected void registrarBean(String nombreBean) {
        //Se obtiene la lista de beans creados durante la navegación
        ArrayList<String> beansCreados = (ArrayList<String>) sesion.getAttribute("beansCreados");
        if (beansCreados == null) {
            //Si la lista está vacía se crea
            beansCreados = new ArrayList<String>();
        }

        //Si el nombre obtenido es diferente de "", entonces éste se adhiere
        //a la lista de beans por los que el usuario ha navegado
        if (null != nombreBean && !"".equals(nombreBean) && !beansCreados.contains(nombreBean)) {
            beansCreados.add(nombreBean);
            //Se actualiza la lista de beans en sesión
            sesion.setAttribute("beansCreados", beansCreados);
        }
        sesion.setAttribute("beansCreados", beansCreados);
    }

    public String inicio() {
        //logBeanWebReactivo.info("'''''''''A inicio");
        limpiarSesion();
        return "/faces/pages/Principal.xhtml";
    }

    public String regresar() {
        return "/faces/pages/Principal.xhtml";
    }

    public MenuModel getMenu() {
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public HttpSession getSesion() {
        return sesion;
    }

    public void setSesion(HttpSession sesion) {
        this.sesion = sesion;
    }

    /**
     * @return the mostrarPoppup
     */
    public boolean isMostrarPoppup() {
        return mostrarPoppup;
    }

    /**
     * @param mostrarPoppup the mostrarPoppup to set
     */
    public void setMostrarPoppup(boolean mostrarPoppup) {
        this.mostrarPoppup = mostrarPoppup;
    }

    /**
     * @return the usuarioVO
     */
    public UsuariosVO getUsuarioVO() {
        return usuarioVO;
    }

    /**
     * @param usuarioVO the usuarioVO to set
     */
    public void setUsuarioVO(UsuariosVO usuarioVO) {
        this.usuarioVO = usuarioVO;
    }

    /**
     * @return the nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * @param nombreEmpresa the nombreEmpresa to set
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    
    

}
