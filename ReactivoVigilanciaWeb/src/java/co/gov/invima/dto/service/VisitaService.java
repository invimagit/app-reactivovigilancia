/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.dto.service;

/**
 *
 * @author Kubit
 */
public class VisitaService {

    private int id;
    private String codigoTarea;
    private int idActividadIVC;
    private String fecha;
    private int idOrigenTarea;
    private int idEstadoTarea;
    private int idTipoProducto;
    private String activo;
    private String usuarioCrea;
    private String fechaCreacion;
    private String usuarioModifica;
    private String fechaModifica;

    public VisitaService() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoTarea() {
        return codigoTarea;
    }

    public void setCodigoTarea(String codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    public int getIdActividadIVC() {
        return idActividadIVC;
    }

    public void setIdActividadIVC(int idActividadIVC) {
        this.idActividadIVC = idActividadIVC;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public String getFechaModifica() {
        return fechaModifica;
    }

    public void setFechaModifica(String fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    
    

}
