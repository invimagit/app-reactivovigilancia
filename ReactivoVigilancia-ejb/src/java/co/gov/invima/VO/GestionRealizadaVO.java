/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mgualdrond
 */
public class GestionRealizadaVO implements Serializable{
    
    private String reporte;
    
    private String causaProbable;
    
    private String causaEfecto;
    
    private String importador;
    
    private String fabricante;
    
    private String comercializador;
    
    private String distribuidor;
    
    private Date fechaNotificacion;
    
    private String enviadoDistriImport;
    
    private String gestionRiesgo;
    
    private String analisisEfecto;
    
    private String herramientaAnalisis;
    
    private String otraHerramienta;
    
    private String descripcionCausa;
    
    private String acciones;
    
    private String descripcionAcciones;
    
    //******************************************************************************
    //******************************************************************************
    //******************************************************************************
    //******************************************************************************

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public String getCausaProbable() {
        return causaProbable;
    }

    public void setCausaProbable(String causaProbable) {
        this.causaProbable = causaProbable;
    }

    public String getCausaEfecto() {
        return causaEfecto;
    }

    public void setCausaEfecto(String causaEfecto) {
        this.causaEfecto = causaEfecto;
    }

    public String getImportador() {
        return importador;
    }

    public void setImportador(String importador) {
        this.importador = importador;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getComercializador() {
        return comercializador;
    }

    public void setComercializador(String comercializador) {
        this.comercializador = comercializador;
    }

    public String getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getEnviadoDistriImport() {
        return enviadoDistriImport;
    }

    public void setEnviadoDistriImport(String enviadoDistriImport) {
        this.enviadoDistriImport = enviadoDistriImport;
    }

    public String getGestionRiesgo() {
        return gestionRiesgo;
    }

    public void setGestionRiesgo(String gestionRiesgo) {
        this.gestionRiesgo = gestionRiesgo;
    }

    public String getAnalisisEfecto() {
        return analisisEfecto;
    }

    public void setAnalisisEfecto(String analisisEfecto) {
        this.analisisEfecto = analisisEfecto;
    }

    public String getHerramientaAnalisis() {
        return herramientaAnalisis;
    }

    public void setHerramientaAnalisis(String herramientaAnalisis) {
        this.herramientaAnalisis = herramientaAnalisis;
    }

    public String getOtraHerramienta() {
        return otraHerramienta;
    }

    public void setOtraHerramienta(String otraHerramienta) {
        this.otraHerramienta = otraHerramienta;
    }

    public String getDescripcionCausa() {
        return descripcionCausa;
    }

    public void setDescripcionCausa(String descripcionCausa) {
        this.descripcionCausa = descripcionCausa;
    }

    public String getAcciones() {
        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    public String getDescripcionAcciones() {
        return descripcionAcciones;
    }

    public void setDescripcionAcciones(String descripcionAcciones) {
        this.descripcionAcciones = descripcionAcciones;
    }

    
    
    
    
}
