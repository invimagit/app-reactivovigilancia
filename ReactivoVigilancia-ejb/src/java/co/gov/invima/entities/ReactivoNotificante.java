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
@Table(name = "reactivo_notificante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoNotificante.findAll", query = "SELECT r FROM ReactivoNotificante r"),
    @NamedQuery(name = "ReactivoNotificante.findByCdgNotificante", query = "SELECT r FROM ReactivoNotificante r WHERE r.cdgNotificante = :cdgNotificante"),
    @NamedQuery(name = "ReactivoNotificante.findByDescripcion", query = "SELECT r FROM ReactivoNotificante r WHERE r.descripcion = :descripcion")})
public class ReactivoNotificante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cdg_notificante")
    private Integer cdgNotificante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "descripcion")
    private String descripcion;

    public ReactivoNotificante() {
    }

    public ReactivoNotificante(Integer cdgNotificante) {
        this.cdgNotificante = cdgNotificante;
    }

    public ReactivoNotificante(Integer cdgNotificante, String descripcion) {
        this.cdgNotificante = cdgNotificante;
        this.descripcion = descripcion;
    }

    public Integer getCdgNotificante() {
        return cdgNotificante;
    }

    public void setCdgNotificante(Integer cdgNotificante) {
        this.cdgNotificante = cdgNotificante;
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
        hash += (cdgNotificante != null ? cdgNotificante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoNotificante)) {
            return false;
        }
        ReactivoNotificante other = (ReactivoNotificante) object;
        if ((this.cdgNotificante == null && other.cdgNotificante != null) || (this.cdgNotificante != null && !this.cdgNotificante.equals(other.cdgNotificante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoNotificante[ cdgNotificante=" + cdgNotificante + " ]";
    }
    
}
