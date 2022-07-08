/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mgualdrond
 */
@Entity
@Table(name = "reactivo_paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoPaciente.findAll", query = "SELECT r FROM ReactivoPaciente r"),
    @NamedQuery(name = "ReactivoPaciente.findByReporte", query = "SELECT r FROM ReactivoPaciente r WHERE r.reporte = :reporte"),
    @NamedQuery(name = "ReactivoPaciente.findByNombrePaciente", query = "SELECT r FROM ReactivoPaciente r WHERE r.nombrePaciente = :nombrePaciente"),
    @NamedQuery(name = "ReactivoPaciente.findByTipidentifi", query = "SELECT r FROM ReactivoPaciente r WHERE r.tipidentifi = :tipidentifi"),
    @NamedQuery(name = "ReactivoPaciente.findByIdentifi", query = "SELECT r FROM ReactivoPaciente r WHERE r.identifi = :identifi"),
    @NamedQuery(name = "ReactivoPaciente.findByEdad", query = "SELECT r FROM ReactivoPaciente r WHERE r.edad = :edad"),
    @NamedQuery(name = "ReactivoPaciente.findByEdadEn", query = "SELECT r FROM ReactivoPaciente r WHERE r.edadEn = :edadEn"),
    @NamedQuery(name = "ReactivoPaciente.findByGenero", query = "SELECT r FROM ReactivoPaciente r WHERE r.genero = :genero"),
    @NamedQuery(name = "ReactivoPaciente.findByDireccPaciente", query = "SELECT r FROM ReactivoPaciente r WHERE r.direccPaciente = :direccPaciente"),
    @NamedQuery(name = "ReactivoPaciente.findByTelefoPaciente", query = "SELECT r FROM ReactivoPaciente r WHERE r.telefoPaciente = :telefoPaciente")})
public class ReactivoPaciente implements Serializable {

    @Size(max = 1)
    @Column(length = 1)
    private String genero;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "reporte")
    private String reporte;
    @Size(max = 128)
    @Column(name = "nombre_paciente")
    private String nombrePaciente;
    @Size(max = 2)
    @Column(name = "tipidentifi")
    private String tipidentifi;
    @Size(max = 11)
    @Column(name = "identifi")
    private String identifi;
    @Column(name = "edad")
    private Integer edad;
    @Size(max = 10)
    @Column(name = "edad_en")
    private String edadEn;
    @Size(max = 128)
    @Column(name = "direcc_paciente")
    private String direccPaciente;
    @Size(max = 15)
    @Column(name = "telefo_paciente")
    private String telefoPaciente;

    public ReactivoPaciente() {
    }

    public ReactivoPaciente(String reporte) {
        this.reporte = reporte;
    }

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
        System.out.println("ReactivoPaciente,setEdadEn,  edadEn = "+edadEn);
        this.edadEn = edadEn;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reporte != null ? reporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoPaciente)) {
            return false;
        }
        ReactivoPaciente other = (ReactivoPaciente) object;
        if ((this.reporte == null && other.reporte != null) || (this.reporte != null && !this.reporte.equals(other.reporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoPaciente[ reporte=" + reporte + " ]";
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
}
