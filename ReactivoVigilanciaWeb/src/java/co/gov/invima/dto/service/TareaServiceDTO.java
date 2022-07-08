/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.dto.service;

/**
 *
 * @author Chavarro
 */
public class TareaServiceDTO {
    
    private Integer idTarea;
    private Integer idActividadIVC;
    private Integer idOrigenTarea;
    private Integer idEstadoTarea;
    private Integer idTipoProducto;
    private String usuarioCrea;

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }
    
    public Integer getIdActividadIVC() {
        return idActividadIVC;
    }

    public void setIdActividadIVC(Integer idActividadIVC) {
        this.idActividadIVC = idActividadIVC;
    }

    public Integer getIdOrigenTarea() {
        return idOrigenTarea;
    }

    public void setIdOrigenTarea(Integer idOrigenTarea) {
        this.idOrigenTarea = idOrigenTarea;
    }

    public Integer getIdEstadoTarea() {
        return idEstadoTarea;
    }

    public void setIdEstadoTarea(Integer idEstadoTarea) {
        this.idEstadoTarea = idEstadoTarea;
    }

    public Integer getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(Integer idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }
    
    
    
}
