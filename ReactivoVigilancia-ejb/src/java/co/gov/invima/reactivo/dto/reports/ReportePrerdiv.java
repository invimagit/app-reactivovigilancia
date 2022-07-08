/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.reactivo.dto.reports;

import java.io.Serializable;

/**
 *
 * @author jgutierrezme
 */
public class ReportePrerdiv implements Serializable
{
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    private String precol;
    private String departamento;
    private String municipio;
    private String nombre_reportante;
    private String id_rol;
    private java.util.Date fecha_ingreso;
    private String descripcionEvento;
    private String nombre_dispositivo;
    private String tipo_reporte;
    private String codigo_causa;
    private String medidaEjecutada;
    private String seguimiento;
    private String estadoCaso;
    private boolean escogerPrecol;
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    private String entidadIncidente;
    private String nitEntidadIncidente;
    private String registroSanitarioDisp;
    private String estadoReporte;
    
    /**
     * @return the precol
     */
    public String getPrecol() {
        return precol;
    }

    /**
     * @param precol the precol to set
     */
    public void setPrecol(String precol) {
        this.precol = precol;
    }

    /**
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the nombre_reportante
     */
    public String getNombre_reportante() {
        return nombre_reportante;
    }

    /**
     * @param nombre_reportante the nombre_reportante to set
     */
    public void setNombre_reportante(String nombre_reportante) {
        this.nombre_reportante = nombre_reportante;
    }

    /**
     * @return the id_rol
     */
    public String getId_rol() {
        return id_rol;
    }

    /**
     * @param id_rol the id_rol to set
     */
    public void setId_rol(String id_rol) {
        this.id_rol = id_rol;
    }

    /**
     * @return the fecha_ingreso
     */
    public java.util.Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    /**
     * @param fecha_ingreso the fecha_ingreso to set
     */
    public void setFecha_ingreso(java.util.Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    /**
     * @return the descripcionEvento
     */
    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    /**
     * @param descripcionEvento the descripcionEvento to set
     */
    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    /**
     * @return the nombre_dispositivo
     */
    public String getNombre_dispositivo() {
        return nombre_dispositivo;
    }

    /**
     * @param nombre_dispositivo the nombre_dispositivo to set
     */
    public void setNombre_dispositivo(String nombre_dispositivo) {
        this.nombre_dispositivo = nombre_dispositivo;
    }

    /**
     * @return the tipo_reporte
     */
    public String getTipo_reporte() {
        return tipo_reporte;
    }

    /**
     * @param tipo_reporte the tipo_reporte to set
     */
    public void setTipo_reporte(String tipo_reporte) {
        this.tipo_reporte = tipo_reporte;
    }

    /**
     * @return the codigo_causa
     */
    public String getCodigo_causa() {
        return codigo_causa;
    }

    /**
     * @param codigo_causa the codigo_causa to set
     */
    public void setCodigo_causa(String codigo_causa) {
        this.codigo_causa = codigo_causa;
    }
    
    public boolean isEscogerPrecol() {
        return escogerPrecol;
    }

    public void setEscogerPrecol(boolean escogerPrecol) {
        this.escogerPrecol = escogerPrecol;
    }
    
    /**
     * @return the medidaEjecutada
     */
    public String getMedidaEjecutada() {
        return medidaEjecutada;
    }

    /**
     * @param medidaEjecutada the medidaEjecutada to set
     */
    public void setMedidaEjecutada(String medidaEjecutada) {
        this.medidaEjecutada = medidaEjecutada;
    }

    /**
     * @return the seguimiento
     */
    public String getSeguimiento() {
        return seguimiento;
    }

    /**
     * @param seguimiento the seguimiento to set
     */
    public void setSeguimiento(String seguimiento) {
        this.seguimiento = seguimiento;
    }

    /**
     * @return the estadoCaso
     */
    public String getEstadoCaso() {
        return estadoCaso;
    }

    /**
     * @param estadoCaso the estadoCaso to set
     */
    public void setEstadoCaso(String estadoCaso) {
        this.estadoCaso = estadoCaso;
    }
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    /*
    private String precol;
    private String departamento;
    private String municipio;
    private java.util.Date fechaEvento;
    private String descripcionEvento;
    private String deteccion_evento;
    private java.util.Date fechaIngreso;
    private String nombreComercial;
    private String identificacion;
    private String diagnostico;
    private boolean escogerPrecol;
    
    public String getPrecol() {
        return precol;
    }

    public void setPrecol(String precol) {
        this.precol = precol;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public java.util.Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(java.util.Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public String getDeteccion_evento() {
        return deteccion_evento;
    }

    public void setDeteccion_evento(String deteccion_evento) {
        this.deteccion_evento = deteccion_evento;
    }

    public java.util.Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(java.util.Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    public boolean isEscogerPrecol() {
        return escogerPrecol;
    }

    public void setEscogerPrecol(boolean escogerPrecol) {
        this.escogerPrecol = escogerPrecol;
    }
    */
    
    /**
     * @return the entidadIncidente
     */
    public String getEntidadIncidente() {
        return entidadIncidente;
    }

    /**
     * @param entidadIncidente the entidadIncidente to set
     */
    public void setEntidadIncidente(String entidadIncidente) {
        this.entidadIncidente = entidadIncidente;
    }

    /**
     * @return the nitEntidadIncidente
     */
    public String getNitEntidadIncidente() {
        return nitEntidadIncidente;
    }

    /**
     * @param nitEntidadIncidente the nitEntidadIncidente to set
     */
    public void setNitEntidadIncidente(String nitEntidadIncidente) {
        this.nitEntidadIncidente = nitEntidadIncidente;
    }

    /**
     * @return the registroSanitarioDisp
     */
    public String getRegistroSanitarioDisp() {
        return registroSanitarioDisp;
    }

    /**
     * @param registroSanitarioDisp the registroSanitarioDisp to set
     */
    public void setRegistroSanitarioDisp(String registroSanitarioDisp) {
        this.registroSanitarioDisp = registroSanitarioDisp;
    }
    
    /**
     * @return the estadoReporte
     */
    public String getEstadoReporte() {
        return estadoReporte;
    }

    /**
     * @param estadoReporte the estadoReporte to set
     */
    public void setEstadoReporte(String estadoReporte) {
        this.estadoReporte = estadoReporte;
    }
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************
    //*************************************************************************

}

