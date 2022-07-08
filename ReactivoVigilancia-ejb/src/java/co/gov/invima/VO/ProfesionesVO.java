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
public class ProfesionesVO implements Serializable {
    
    private Integer cdgProfesion;

    private String descripcion;

    public Integer getCdgProfesion() {
        return cdgProfesion;
    }

    public void setCdgProfesion(Integer cdgProfesion) {
        this.cdgProfesion = cdgProfesion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
