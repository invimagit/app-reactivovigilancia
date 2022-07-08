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
 * @author jonat
 */
@Entity
@Table(name = "tecno_tipoalertas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TecnoTipoalertas.findAll", query = "SELECT t FROM TecnoTipoalertas t")
    , @NamedQuery(name = "TecnoTipoalertas.findByCdgTipoalertas", query = "SELECT t FROM TecnoTipoalertas t WHERE t.cdgTipoalertas = :cdgTipoalertas")
    , @NamedQuery(name = "TecnoTipoalertas.findByDescripcion", query = "SELECT t FROM TecnoTipoalertas t WHERE t.descripcion = :descripcion")})
public class TecnoTipoalertas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "cdg_tipoalertas")
    private String cdgTipoalertas;
    @Size(max = 64)
    @Column(name = "descripcion")
    private String descripcion;

    public TecnoTipoalertas() {
    }

    public TecnoTipoalertas(String cdgTipoalertas) {
        this.cdgTipoalertas = cdgTipoalertas;
    }

    public String getCdgTipoalertas() {
        return cdgTipoalertas;
    }

    public void setCdgTipoalertas(String cdgTipoalertas) {
        this.cdgTipoalertas = cdgTipoalertas;
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
        hash += (cdgTipoalertas != null ? cdgTipoalertas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TecnoTipoalertas)) {
            return false;
        }
        TecnoTipoalertas other = (TecnoTipoalertas) object;
        if ((this.cdgTipoalertas == null && other.cdgTipoalertas != null) || (this.cdgTipoalertas != null && !this.cdgTipoalertas.equals(other.cdgTipoalertas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.TecnoTipoalertas[ cdgTipoalertas=" + cdgTipoalertas + " ]";
    }
    
}
