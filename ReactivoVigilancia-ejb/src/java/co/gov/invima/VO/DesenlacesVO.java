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
public class DesenlacesVO implements Serializable {
    private Integer cdgDesenlace;

    private String descripcion;

    public Integer getCdgDesenlace() {
        return cdgDesenlace;
    }

    public void setCdgDesenlace(Integer cdgDesenlace) {
        this.cdgDesenlace = cdgDesenlace;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
