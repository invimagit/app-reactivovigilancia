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
public class PacienteVO implements Serializable {
    private String reporte;

    private String nombrePaciente;

    private String tipidentifi;
    
    private String identifi;
    
    private Integer edad;
    
    private String edadEn;
    
    private Character genero;
    
    private String direccPaciente;
    
    private String telefoPaciente;

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getTipidentifi() {
        return tipidentifi;
    }

    public void setTipidentifi(String tipidentifi) {
        this.tipidentifi = tipidentifi;
    }

    public String getIdentifi() {
        return identifi;
    }

    public void setIdentifi(String identifi) {
        this.identifi = identifi;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getEdadEn() {
        return edadEn;
    }

    public void setEdadEn(String edadEn) {
        this.edadEn = edadEn;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public String getDireccPaciente() {
        return direccPaciente;
    }

    public void setDireccPaciente(String direccPaciente) {
        this.direccPaciente = direccPaciente;
    }

    public String getTelefoPaciente() {
        return telefoPaciente;
    }

    public void setTelefoPaciente(String telefoPaciente) {
        this.telefoPaciente = telefoPaciente;
    }
    
    
}
