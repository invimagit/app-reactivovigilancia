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
@Table(name = "reactivo_modalidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoModalidad.findAll", query = "SELECT r FROM ReactivoModalidad r"),
    @NamedQuery(name = "ReactivoModalidad.findByCdgModalidad", query = "SELECT r FROM ReactivoModalidad r WHERE r.cdgModalidad = :cdgModalidad"),
    @NamedQuery(name = "ReactivoModalidad.findByDescripcion", query = "SELECT r FROM ReactivoModalidad r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "ReactivoModalidad.findByEstado", query = "SELECT r FROM ReactivoModalidad r WHERE r.estado = :estado")})
public class ReactivoModalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cdg_modalidad")
    private Integer cdgModalidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private Character estado;

    public ReactivoModalidad() {
    }

    public ReactivoModalidad(Integer cdgModalidad) {
        this.cdgModalidad = cdgModalidad;
    }

    public ReactivoModalidad(Integer cdgModalidad, String descripcion) {
        this.cdgModalidad = cdgModalidad;
        this.descripcion = descripcion;
    }

    public Integer getCdgModalidad() {
        return cdgModalidad;
    }

    public void setCdgModalidad(Integer cdgModalidad) {
        this.cdgModalidad = cdgModalidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdgModalidad != null ? cdgModalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoModalidad)) {
            return false;
        }
        ReactivoModalidad other = (ReactivoModalidad) object;
        if ((this.cdgModalidad == null && other.cdgModalidad != null) || (this.cdgModalidad != null && !this.cdgModalidad.equals(other.cdgModalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoModalidad[ cdgModalidad=" + cdgModalidad + " ]";
    }
    
}
