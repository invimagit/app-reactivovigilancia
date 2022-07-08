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
@Table(name = "reactivo_area")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoArea.findAll", query = "SELECT r FROM ReactivoArea r"),
    @NamedQuery(name = "ReactivoArea.findByCdgArea", query = "SELECT r FROM ReactivoArea r WHERE r.cdgArea = :cdgArea"),
    @NamedQuery(name = "ReactivoArea.findByDescripcion", query = "SELECT r FROM ReactivoArea r WHERE r.descripcion = :descripcion")})
public class ReactivoArea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cdg_area")
    private Integer cdgArea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "descripcion")
    private String descripcion;

    public ReactivoArea() {
    }

    public ReactivoArea(Integer cdgArea) {
        this.cdgArea = cdgArea;
    }

    public ReactivoArea(Integer cdgArea, String descripcion) {
        this.cdgArea = cdgArea;
        this.descripcion = descripcion;
    }

    public Integer getCdgArea() {
        return cdgArea;
    }

    public void setCdgArea(Integer cdgArea) {
        this.cdgArea = cdgArea;
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
        hash += (cdgArea != null ? cdgArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoArea)) {
            return false;
        }
        ReactivoArea other = (ReactivoArea) object;
        if ((this.cdgArea == null && other.cdgArea != null) || (this.cdgArea != null && !this.cdgArea.equals(other.cdgArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoArea[ cdgArea=" + cdgArea + " ]";
    }
    
}
