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
@Table(name = "reactivo_estado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoEstado.findAll", query = "SELECT r FROM ReactivoEstado r"),
    @NamedQuery(name = "ReactivoEstado.findByCdgEstado", query = "SELECT r FROM ReactivoEstado r WHERE r.cdgEstado = :cdgEstado"),
    @NamedQuery(name = "ReactivoEstado.findByDescripcion", query = "SELECT r FROM ReactivoEstado r WHERE r.descripcion = :descripcion")})
public class ReactivoEstado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cdg_estado")
    private Integer cdgEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "descripcion")
    private String descripcion;

    public ReactivoEstado() {
    }

    public ReactivoEstado(Integer cdgEstado) {
        this.cdgEstado = cdgEstado;
    }

    public ReactivoEstado(Integer cdgEstado, String descripcion) {
        this.cdgEstado = cdgEstado;
        this.descripcion = descripcion;
    }

    public Integer getCdgEstado() {
        return cdgEstado;
    }

    public void setCdgEstado(Integer cdgEstado) {
        this.cdgEstado = cdgEstado;
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
        hash += (cdgEstado != null ? cdgEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoEstado)) {
            return false;
        }
        ReactivoEstado other = (ReactivoEstado) object;
        if ((this.cdgEstado == null && other.cdgEstado != null) || (this.cdgEstado != null && !this.cdgEstado.equals(other.cdgEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoEstado[ cdgEstado=" + cdgEstado + " ]";
    }
    
}
