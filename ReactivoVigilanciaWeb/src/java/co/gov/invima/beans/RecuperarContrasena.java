/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.beans;

import co.gov.invima.BO.ReactivoBO;
import co.gov.invima.VO.UsuariosVO;
import co.gov.invima.beans.masivo.BeanBusquedaPrerdivs;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author mgualdrond
 */
@Named(value = "recuperarContrasena")
@SessionScoped
public class RecuperarContrasena implements Serializable {

    private static Logger logBeanWebReactivo = Logger.getLogger(RecuperarContrasena.class.getName());
    
    private HttpSession session;
    private final ReactivoBO reactivoBO;
    private String mensajes;
    private UsuariosVO usuariosVO;
    private UsuariosVO usuarioEncontrado;

    /**
     * Creates a new instance of RecuperarContrasena
     */
    public RecuperarContrasena() {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        reactivoBO = new ReactivoBO();
        usuariosVO = new UsuariosVO();
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public UsuariosVO getUsuariosVO() {
        return usuariosVO;
    }

    public void setUsuariosVO(UsuariosVO usuariosVO) {
        this.usuariosVO = usuariosVO;
    }


    public void btnAceptar() 
    {
        if (usuariosVO != null) {
            mensajes = reactivoBO.recuperarContrasena(usuariosVO);
            if (mensajes.startsWith("Correo")) {
                FacesContext.getCurrentInstance().addMessage("panelContenedorRecuperar", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        mensajes, null));
            } else {
                FacesContext.getCurrentInstance().addMessage("panelContenedorRecuperar", new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        mensajes, null));
            }
        }
    }

    public String btnCancelar() 
    {
        //logBeanWebReactivo.info ("Regresando al home");
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        //session.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "faces/index.xhtml?faces-redirect=true";
    }
    
    public String retornoResultadoOK() {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        //session.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "faces/index.xhtml?faces-redirect=true";
    }
    

    public void changeUsuario(ValueChangeEvent event) {
        String valor = (String) event.getNewValue();
        usuarioEncontrado = reactivoBO.busrcarUsuario(valor);
        if (usuarioEncontrado == null) {
            usuariosVO = new UsuariosVO();
            mensajes = "Usuario No encontrado";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, mensajes, mensajes);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            //FacesContext.getCurrentInstance().addMessage("panelContenedorRecuperar", new FacesMessage(FacesMessage.SEVERITY_FATAL,
            //        mensajes, null));
        } else {
            usuariosVO = usuarioEncontrado;
        }
    }

    public void listenerUsuario(AjaxBehaviorEvent event) {
        String valor = (String) ((UIOutput) event.getSource()).getValue();
        usuarioEncontrado = reactivoBO.busrcarUsuario(valor);
        if (usuarioEncontrado == null) {
            usuariosVO = new UsuariosVO();
            mensajes = "Usuario No encontrado";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, mensajes, mensajes);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
            //        mensajes, null));
        } else {
            usuariosVO = usuarioEncontrado;
        }
    }
}
