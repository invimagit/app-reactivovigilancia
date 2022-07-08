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
public class EstadoVO implements Serializable {
    private Integer cdgEstado;
    private String descripcion;

    public Integer getCdgEstado() {
        return cdgEstado;
    }

    public void setCdgEstado(Integer cdgEstado) {
        this.cdgEstado = cdgEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
