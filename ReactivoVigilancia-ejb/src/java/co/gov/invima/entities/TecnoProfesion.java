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
@Table(name = "tecno_profesion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TecnoProfesion.findAll", query = "SELECT t FROM TecnoProfesion t"),
    @NamedQuery(name = "TecnoProfesion.findByCdgProfesion", query = "SELECT t FROM TecnoProfesion t WHERE t.cdgProfesion = :cdgProfesion"),
    @NamedQuery(name = "TecnoProfesion.findByDescripcion", query = "SELECT t FROM TecnoProfesion t WHERE t.descripcion = :descripcion")})
public class TecnoProfesion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cdg_profesion")
    private Integer cdgProfesion;
    @Size(max = 128)
    @Column(name = "descripcion")
    private String descripcion;

    public TecnoProfesion() {
    }

    public TecnoProfesion(Integer cdgProfesion) {
        this.cdgProfesion = cdgProfesion;
    }

    public Integer getCdgProfesion() {
        return cdgProfesion;
    }

    public void setCdgProfesion(Integer cdgProfesion) {
        this.cdgProfesion = cdgProfesion;
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
        hash += (cdgProfesion != null ? cdgProfesion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TecnoProfesion)) {
            return false;
        }
        TecnoProfesion other = (TecnoProfesion) object;
        if ((this.cdgProfesion == null && other.cdgProfesion != null) || (this.cdgProfesion != null && !this.cdgProfesion.equals(other.cdgProfesion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.TecnoProfesion[ cdgProfesion=" + cdgProfesion + " ]";
    }
    
}
