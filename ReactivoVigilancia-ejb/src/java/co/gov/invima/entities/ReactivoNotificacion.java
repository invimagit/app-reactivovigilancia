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
@Table(name = "reactivo_notificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoNotificacion.findAll", query = "SELECT r FROM ReactivoNotificacion r"),
    @NamedQuery(name = "ReactivoNotificacion.findByCdgNotificacion", query = "SELECT r FROM ReactivoNotificacion r WHERE r.cdgNotificacion = :cdgNotificacion"),
    @NamedQuery(name = "ReactivoNotificacion.findByDescripcion", query = "SELECT r FROM ReactivoNotificacion r WHERE r.descripcion = :descripcion")})
public class ReactivoNotificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cdg_notificacion")
    private Character cdgNotificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "descripcion")
    private String descripcion;

    public ReactivoNotificacion() {
    }

    public ReactivoNotificacion(Character cdgNotificacion) {
        this.cdgNotificacion = cdgNotificacion;
    }

    public ReactivoNotificacion(Character cdgNotificacion, String descripcion) {
        this.cdgNotificacion = cdgNotificacion;
        this.descripcion = descripcion;
    }

    public Character getCdgNotificacion() {
        return cdgNotificacion;
    }

    public void setCdgNotificacion(Character cdgNotificacion) {
        this.cdgNotificacion = cdgNotificacion;
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
        hash += (cdgNotificacion != null ? cdgNotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoNotificacion)) {
            return false;
        }
        ReactivoNotificacion other = (ReactivoNotificacion) object;
        if ((this.cdgNotificacion == null && other.cdgNotificacion != null) || (this.cdgNotificacion != null && !this.cdgNotificacion.equals(other.cdgNotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoNotificacion[ cdgNotificacion=" + cdgNotificacion + " ]";
    }
    
}
