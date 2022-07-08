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
@Table(name = "reactivo_fuente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoFuente.findAll", query = "SELECT r FROM ReactivoFuente r"),
    @NamedQuery(name = "ReactivoFuente.findByCdgFuente", query = "SELECT r FROM ReactivoFuente r WHERE r.cdgFuente = :cdgFuente"),
    @NamedQuery(name = "ReactivoFuente.findByDescripcion", query = "SELECT r FROM ReactivoFuente r WHERE r.descripcion = :descripcion")})
public class ReactivoFuente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cdg_fuente")
    private Short cdgFuente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "descripcion")
    private String descripcion;

    public ReactivoFuente() {
    }

    public ReactivoFuente(Short cdgFuente) {
        this.cdgFuente = cdgFuente;
    }

    public ReactivoFuente(Short cdgFuente, String descripcion) {
        this.cdgFuente = cdgFuente;
        this.descripcion = descripcion;
    }

    public Short getCdgFuente() {
        return cdgFuente;
    }

    public void setCdgFuente(Short cdgFuente) {
        this.cdgFuente = cdgFuente;
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
        hash += (cdgFuente != null ? cdgFuente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoFuente)) {
            return false;
        }
        ReactivoFuente other = (ReactivoFuente) object;
        if ((this.cdgFuente == null && other.cdgFuente != null) || (this.cdgFuente != null && !this.cdgFuente.equals(other.cdgFuente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoFuente[ cdgFuente=" + cdgFuente + " ]";
    }
    
}
