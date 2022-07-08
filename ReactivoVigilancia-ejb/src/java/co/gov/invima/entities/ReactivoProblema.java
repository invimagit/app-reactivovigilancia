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
@Table(name = "reactivo_problema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoProblema.findAll", query = "SELECT r FROM ReactivoProblema r"),
    @NamedQuery(name = "ReactivoProblema.findByCdgProblema", query = "SELECT r FROM ReactivoProblema r WHERE r.cdgProblema = :cdgProblema"),
    @NamedQuery(name = "ReactivoProblema.findByDescripcion", query = "SELECT r FROM ReactivoProblema r WHERE r.descripcion = :descripcion")})
public class ReactivoProblema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cdg_problema")
    private Integer cdgProblema;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "descripcion")
    private String descripcion;

    public ReactivoProblema() {
    }

    public ReactivoProblema(Integer cdgProblema) {
        this.cdgProblema = cdgProblema;
    }

    public ReactivoProblema(Integer cdgProblema, String descripcion) {
        this.cdgProblema = cdgProblema;
        this.descripcion = descripcion;
    }

    public Integer getCdgProblema() {
        return cdgProblema;
    }

    public void setCdgProblema(Integer cdgProblema) {
        this.cdgProblema = cdgProblema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdgProblema != null ? cdgProblema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoProblema)) {
            return false;
        }
        ReactivoProblema other = (ReactivoProblema) object;
        if ((this.cdgProblema == null && other.cdgProblema != null) || (this.cdgProblema != null && !this.cdgProblema.equals(other.cdgProblema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoProblema[ cdgProblema=" + cdgProblema + " ]";
    }
    
}
