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
@Table(name = "reactivo_desenlace")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoDesenlace.findAll", query = "SELECT r FROM ReactivoDesenlace r"),
    @NamedQuery(name = "ReactivoDesenlace.findByCdgDesenlace", query = "SELECT r FROM ReactivoDesenlace r WHERE r.cdgDesenlace = :cdgDesenlace"),
    @NamedQuery(name = "ReactivoDesenlace.findByDescripcion", query = "SELECT r FROM ReactivoDesenlace r WHERE r.descripcion = :descripcion")})
public class ReactivoDesenlace implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cdg_desenlace")
    private Integer cdgDesenlace;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "descripcion")
    private String descripcion;

    public ReactivoDesenlace() {
    }

    public ReactivoDesenlace(Integer cdgDesenlace) {
        this.cdgDesenlace = cdgDesenlace;
    }

    public ReactivoDesenlace(Integer cdgDesenlace, String descripcion) {
        this.cdgDesenlace = cdgDesenlace;
        this.descripcion = descripcion;
    }

    public Integer getCdgDesenlace() {
        return cdgDesenlace;
    }

    public void setCdgDesenlace(Integer cdgDesenlace) {
        this.cdgDesenlace = cdgDesenlace;
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
        hash += (cdgDesenlace != null ? cdgDesenlace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoDesenlace)) {
            return false;
        }
        ReactivoDesenlace other = (ReactivoDesenlace) object;
        if ((this.cdgDesenlace == null && other.cdgDesenlace != null) || (this.cdgDesenlace != null && !this.cdgDesenlace.equals(other.cdgDesenlace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoDesenlace[ cdgDesenlace=" + cdgDesenlace + " ]";
    }
    
}
