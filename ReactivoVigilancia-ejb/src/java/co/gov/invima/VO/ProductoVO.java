/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.VO;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author mgualdrond
 */
public class ProductoVO implements Serializable {
    
    private String reporte;
    
    private String nombreReactivo;
    
    private String nroregsan;
    
    private Date fechaVenci;
    
    private String lote;
    
    private String procedencia;
    
    private String cadenaFrio;
    
    private String temperatura;
    
    private String certiAnalisis;
    
    private String distriUsuario;
    
    private String condiciAlmacen;
    
    private Integer areaFunciona;
    
    private String otraArea;

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public String getNombreReactivo() {
        return nombreReactivo;
    }

    public void setNombreReactivo(String nombreReactivo) {
        this.nombreReactivo = nombreReactivo;
    }

    public String getNroregsan() {
        return nroregsan;
    }

    public void setNroregsan(String nroregsan) {
        this.nroregsan = nroregsan;
    }

    public Date getFechaVenci() {
        return fechaVenci;
    }

    public void setFechaVenci(Date fechaVenci) {
        this.fechaVenci = fechaVenci;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getCadenaFrio() {
        return cadenaFrio;
    }

    public void setCadenaFrio(String cadenaFrio) {
        this.cadenaFrio = cadenaFrio;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getCertiAnalisis() {
        return certiAnalisis;
    }

    public void setCertiAnalisis(String certiAnalisis) {
        this.certiAnalisis = certiAnalisis;
    }

    public String getDistriUsuario() {
        return distriUsuario;
    }

    public void setDistriUsuario(String distriUsuario) {
        this.distriUsuario = distriUsuario;
    }

    public String getCondiciAlmacen() {
        return condiciAlmacen;
    }

    public void setCondiciAlmacen(String condiciAlmacen) {
        this.condiciAlmacen = condiciAlmacen;
    }

    public Integer getAreaFunciona() {
        return areaFunciona;
    }

    public void setAreaFunciona(Integer areaFunciona) {
        this.areaFunciona = areaFunciona;
    }

    public String getOtraArea() {
        return otraArea;
    }

    public void setOtraArea(String otraArea) {
        this.otraArea = otraArea;
    }
    
    
}
