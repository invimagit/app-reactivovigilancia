/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.dto;

/**
 *
 * @author Kubit
 */
public class ParametrosSolicitarVisitaDTO {
    
    private int idActividadIVC;
    private int idOrigenTarea;
    private int idEstadoTarea;
    private int idTipoProducto;
    private String usuarioCrea;

    public int getIdActividadIVC() {
        return idActividadIVC;
    }

    public void setIdActividadIVC(int idActividadIVC) {
        this.idActividadIVC = idActividadIVC;
    }

    public int getIdOrigenTarea() {
        return idOrigenTarea;
    }

    public void setIdOrigenTarea(int idOrigenTarea) {
        this.idOrigenTarea = idOrigenTarea;
    }

    public int getIdEstadoTarea() {
        return idEstadoTarea;
    }

    public void setIdEstadoTarea(int idEstadoTarea) {
        this.idEstadoTarea = idEstadoTarea;
    }

    public int getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    
    
}
