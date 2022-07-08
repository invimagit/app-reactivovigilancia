/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.VO;

import java.util.Date;

/**
 *
 * @author jonat
 */
public class SeguimientoVO {
    private String Usuario;
    private Date fechaIngreso = new Date();
    private String Observacion;
    
    //getSession().setAttribute("usuario", usuariosVO);

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }
    
    
}
