/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.VO;

import java.io.Serializable;

/**
 *
 * @author jgutierrezme
 */
public class ReactivoRepCeroConsultaVO implements Serializable
{
    //******************************************************************************************
    //******************************************************************************************
    //******************************************************************************************
    //******************************************************************************************
    //******************************************************************************************
    //******************************************************************************************
    /*
    private String id_red;
    private String numero_radicado;
    private String razon_social;
    private String nit_empresa;
    private String departamento;
    private String municipio;
    private String trimestre_reportado;
    private String year_trimestre;
    private String tipo_actor;
    private String notificacion;
    private String fecha_radicado;
    private String observacion_reportante;
    */
    //******************************************************************************************
    //******************************************************************************************
    //******************************************************************************************
    private String radicado;
    private String empresa;
    private String nit;
    private String cod_dpto;
    private String departamento;
    private String cod_mun;
    private String municipio;
    private String trimestre;
    private String anio_reporte;
    private String tipo_actor;
    private String notificacion;
    private String fecha_actualiza;
    private String observacion;
    private String fecha_reporte;
    private String idregistro;
    private String usuario_id;
    //******************************************************************************************
    //******************************************************************************************
    //******************************************************************************************
    //******************************************************************************************
    //******************************************************************************************
    //******************************************************************************************

    /**
     * @return the radicado
     */
    public String getRadicado() {
        return radicado;
    }

    /**
     * @param radicado the radicado to set
     */
    public void setRadicado(String radicado) {
        this.radicado = radicado;
    }

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the nit
     */
    public String getNit() {
        return nit;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * @return the cod_dpto
     */
    public String getCod_dpto() {
        return cod_dpto;
    }

    /**
     * @param cod_dpto the cod_dpto to set
     */
    public void setCod_dpto(String cod_dpto) {
        this.cod_dpto = cod_dpto;
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
     * @return the cod_mun
     */
    public String getCod_mun() {
        return cod_mun;
    }

    /**
     * @param cod_mun the cod_mun to set
     */
    public void setCod_mun(String cod_mun) {
        this.cod_mun = cod_mun;
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
     * @return the trimestre
     */
    public String getTrimestre() {
        return trimestre;
    }

    /**
     * @param trimestre the trimestre to set
     */
    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    /**
     * @return the anio_reporte
     */
    public String getAnio_reporte() {
        return anio_reporte;
    }

    /**
     * @param anio_reporte the anio_reporte to set
     */
    public void setAnio_reporte(String anio_reporte) {
        this.anio_reporte = anio_reporte;
    }

    /**
     * @return the tipo_actor
     */
    public String getTipo_actor() {
        return tipo_actor;
    }

    /**
     * @param tipo_actor the tipo_actor to set
     */
    public void setTipo_actor(String tipo_actor) {
        this.tipo_actor = tipo_actor;
    }

    /**
     * @return the notificacion
     */
    public String getNotificacion() {
        return notificacion;
    }

    /**
     * @param notificacion the notificacion to set
     */
    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    /**
     * @return the fecha_actualiza
     */
    public String getFecha_actualiza() {
        return fecha_actualiza;
    }

    /**
     * @param fecha_actualiza the fecha_actualiza to set
     */
    public void setFecha_actualiza(String fecha_actualiza) {
        this.fecha_actualiza = fecha_actualiza;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the fecha_reporte
     */
    public String getFecha_reporte() {
        return fecha_reporte;
    }

    /**
     * @param fecha_reporte the fecha_reporte to set
     */
    public void setFecha_reporte(String fecha_reporte) {
        this.fecha_reporte = fecha_reporte;
    }

    /**
     * @return the idregistro
     */
    public String getIdregistro() {
        return idregistro;
    }

    /**
     * @param idregistro the idregistro to set
     */
    public void setIdregistro(String idregistro) {
        this.idregistro = idregistro;
    }

    /**
     * @return the usuario_id
     */
    public String getUsuario_id() {
        return usuario_id;
    }

    /**
     * @param usuario_id the usuario_id to set
     */
    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }
    
    
    
    
}
