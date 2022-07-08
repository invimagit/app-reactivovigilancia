/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jgutierrezme
 */
@Entity
@Table(name = "reactivo_tiporeportante", catalog = "SIVICOS", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoTiporeportante.findAll", query = "SELECT r FROM ReactivoTiporeportante r")
    , @NamedQuery(name = "ReactivoTiporeportante.findByCdgTiporeportante", query = "SELECT r FROM ReactivoTiporeportante r WHERE r.cdgTiporeportante = :cdgTiporeportante")
    , @NamedQuery(name = "ReactivoTiporeportante.findByNombreTiporeportante", query = "SELECT r FROM ReactivoTiporeportante r WHERE r.nombreTiporeportante = :nombreTiporeportante")})
public class ReactivoTiporeportante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cdg_tiporeportante", nullable = false)
    private Integer cdgTiporeportante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_tiporeportante", nullable = false, length = 50)
    private String nombreTiporeportante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdgTiporeportante")
    private List<ReactivoCamposcomp> reactivoCamposcompList;

    public ReactivoTiporeportante() {
    }

    public ReactivoTiporeportante(Integer cdgTiporeportante) {
        this.cdgTiporeportante = cdgTiporeportante;
    }

    public ReactivoTiporeportante(Integer cdgTiporeportante, String nombreTiporeportante) {
        this.cdgTiporeportante = cdgTiporeportante;
        this.nombreTiporeportante = nombreTiporeportante;
    }

    public Integer getCdgTiporeportante() {
        return cdgTiporeportante;
    }

    public void setCdgTiporeportante(Integer cdgTiporeportante) {
        this.cdgTiporeportante = cdgTiporeportante;
    }

    public String getNombreTiporeportante() {
        return nombreTiporeportante;
    }

    public void setNombreTiporeportante(String nombreTiporeportante) {
        this.nombreTiporeportante = nombreTiporeportante;
    }

    @XmlTransient
    public List<ReactivoCamposcomp> getReactivoCamposcompList() {
        return reactivoCamposcompList;
    }

    public void setReactivoCamposcompList(List<ReactivoCamposcomp> reactivoCamposcompList) {
        this.reactivoCamposcompList = reactivoCamposcompList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdgTiporeportante != null ? cdgTiporeportante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoTiporeportante)) {
            return false;
        }
        ReactivoTiporeportante other = (ReactivoTiporeportante) object;
        if ((this.cdgTiporeportante == null && other.cdgTiporeportante != null) || (this.cdgTiporeportante != null && !this.cdgTiporeportante.equals(other.cdgTiporeportante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoTiporeportante[ cdgTiporeportante=" + cdgTiporeportante + " ]";
    }
    
}
