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
public class EventosVO implements Serializable {
    private String reporte;
    
    private Date fechEvento;
    
    private String efectoIndeseado;
    
    private Date fechReporte;
    
    private Integer cdgProblema;
    
    private String descripEfecto;
   
    private String clasificacion = "Evento Adverso";
    
    private Integer cdgDesenlace;
    
    private String otroDesenlace;
    
    private Character desempeno;
    
    private Character internet;
    
    private Date fechaIngreso;
    
    private Character reportado;

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public Date getFechEvento() {
        return fechEvento;
    }

    public void setFechEvento(Date fechEvento) {
        this.fechEvento = fechEvento;
    }

    public String getEfectoIndeseado() {
        return efectoIndeseado;
    }

    public void setEfectoIndeseado(String efectoIndeseado) {
        this.efectoIndeseado = efectoIndeseado;
    }

    public Date getFechReporte() {
        return fechReporte;
    }

    public void setFechReporte(Date fechReporte) {
        this.fechReporte = fechReporte;
    }

    public Integer getCdgProblema() {
        return cdgProblema;
    }

    public void setCdgProblema(Integer cdgProblema) {
        this.cdgProblema = cdgProblema;
    }

    public String getDescripEfecto() {
        return descripEfecto;
    }

    public void setDescripEfecto(String descripEfecto) {
        this.descripEfecto = descripEfecto;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Integer getCdgDesenlace() {
        return cdgDesenlace;
    }

    public void setCdgDesenlace(Integer cdgDesenlace) {
        this.cdgDesenlace = cdgDesenlace;
    }

    public String getOtroDesenlace() {
        return otroDesenlace;
    }

    public void setOtroDesenlace(String otroDesenlace) {
        this.otroDesenlace = otroDesenlace;
    }

    public Character getDesempeno() {
        return desempeno;
    }

    public void setDesempeno(Character desempeno) {
        this.desempeno = desempeno;
    }

    public Character getInternet() {
        return internet;
    }

    public void setInternet(Character internet) {
        this.internet = internet;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Character getReportado() {
        return reportado;
    }

    public void setReportado(Character reportado) {
        this.reportado = reportado;
    }
    
    
}
