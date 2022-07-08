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
@Table(name = "departamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamentos.findAll", query = "SELECT d FROM Departamentos d"),
    @NamedQuery(name = "Departamentos.findByCodDepart", query = "SELECT d FROM Departamentos d WHERE d.codDepart = :codDepart"),
    @NamedQuery(name = "Departamentos.findByDescripcion", query = "SELECT d FROM Departamentos d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Departamentos.findByCdgTerritorio", query = "SELECT d FROM Departamentos d WHERE d.cdgTerritorio = :cdgTerritorio"),
    @NamedQuery(name = "Departamentos.findByIso31662", query = "SELECT d FROM Departamentos d WHERE d.iso31662 = :iso31662")})
public class Departamentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "cod_depart")
    private String codDepart;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 2)
    @Column(name = "cdg_territorio")
    private String cdgTerritorio;
    @Size(max = 3)
    @Column(name = "iso31662")
    private String iso31662;

    public Departamentos() {
    }

    public Departamentos(String codDepart) {
        this.codDepart = codDepart;
    }

    public Departamentos(String codDepart, String descripcion) {
        this.codDepart = codDepart;
        this.descripcion = descripcion;
    }

    public String getCodDepart() {
        return codDepart;
    }

    public void setCodDepart(String codDepart) {
        this.codDepart = codDepart;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCdgTerritorio() {
        return cdgTerritorio;
    }

    public void setCdgTerritorio(String cdgTerritorio) {
        this.cdgTerritorio = cdgTerritorio;
    }

    public String getIso31662() {
        return iso31662;
    }

    public void setIso31662(String iso31662) {
        this.iso31662 = iso31662;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDepart != null ? codDepart.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamentos)) {
            return false;
        }
        Departamentos other = (Departamentos) object;
        if ((this.codDepart == null && other.codDepart != null) || (this.codDepart != null && !this.codDepart.equals(other.codDepart))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.Departamentos[ codDepart=" + codDepart + " ]";
    }
    
}
