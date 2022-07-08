/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.VO;

import java.io.Serializable;

/**
 *
 * @author mgualdrond
 */
public class NotificacionVO implements Serializable {
    private Character cdgNotificacion;
    private String descripcion;

    public Character getCdgNotificacion() {
        return cdgNotificacion;
    }

    public void setCdgNotificacion(Character cdgNotificacion) {
        this.cdgNotificacion = cdgNotificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
