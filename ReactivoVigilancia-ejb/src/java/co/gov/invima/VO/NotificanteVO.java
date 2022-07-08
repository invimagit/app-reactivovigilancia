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
public class NotificanteVO implements Serializable {
    
    private Integer cdgNotificante;

    private String descripcion;

    public Integer getCdgNotificante() {
        return cdgNotificante;
    }

    public void setCdgNotificante(Integer cdgNotificante) {
        this.cdgNotificante = cdgNotificante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
