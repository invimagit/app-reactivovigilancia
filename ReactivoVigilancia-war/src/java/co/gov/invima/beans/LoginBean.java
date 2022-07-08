/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.beans;

import co.gov.invima.VO.UsuariosVO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author mgualdrond
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private UsuariosVO usuariosVO;
    private String btnLogin = "btnLogin";
    private String mensaje;
    private HttpSession session;
    private MenuModel model;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
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
        return "";
    }

    public String registrar() {
//        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        UsuariosVO uvo = new UsuariosVO();
//        uvo.setUsuario("INVITADO");
//        session.setAttribute("usuario", uvo);
        return "faces/ReactivoUsuarioInternet.xhtml";
    }

    public String recuperarContrasena() {
//        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        UsuariosVO uvo = new UsuariosVO();
//        uvo.setUsuario("INVITADO");
//        session.setAttribute("usuario", uvo);
        return "restablecerClave";
    }

}
