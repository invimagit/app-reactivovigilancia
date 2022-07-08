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
public class AreasVO implements Serializable {
    
    private Integer cdgArea;

    private String descripcion;

    public Integer getCdgArea() {
        return cdgArea;
    }

    public void setCdgArea(Integer cdgArea) {
        this.cdgArea = cdgArea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
