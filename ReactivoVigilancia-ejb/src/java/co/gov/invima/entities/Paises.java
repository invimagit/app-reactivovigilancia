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
@Table(name = "paises")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paises.findAll", query = "SELECT p FROM Paises p"),
    @NamedQuery(name = "Paises.findByCdgPais", query = "SELECT p FROM Paises p WHERE p.cdgPais = :cdgPais"),
    @NamedQuery(name = "Paises.findByPais", query = "SELECT p FROM Paises p WHERE p.pais = :pais"),
    @NamedQuery(name = "Paises.findByReferencia", query = "SELECT p FROM Paises p WHERE p.referencia = :referencia"),
    @NamedQuery(name = "Paises.findByTipo", query = "SELECT p FROM Paises p WHERE p.tipo = :tipo")})
public class Paises implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "cdg_pais")
    private String cdgPais;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "pais")
    private String pais;
    @Column(name = "referencia")
    private Character referencia;
    @Size(max = 2)
    @Column(name = "tipo")
    private String tipo;

    public Paises() {
    }

    public Paises(String cdgPais) {
        this.cdgPais = cdgPais;
    }

    public Paises(String cdgPais, String pais) {
        this.cdgPais = cdgPais;
        this.pais = pais;
    }

    public String getCdgPais() {
        return cdgPais;
    }

    public void setCdgPais(String cdgPais) {
        this.cdgPais = cdgPais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Character getReferencia() {
        return referencia;
    }

    public void setReferencia(Character referencia) {
        this.referencia = referencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdgPais != null ? cdgPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paises)) {
            return false;
        }
        Paises other = (Paises) object;
        if ((this.cdgPais == null && other.cdgPais != null) || (this.cdgPais != null && !this.cdgPais.equals(other.cdgPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.Paises[ cdgPais=" + cdgPais + " ]";
    }
    
}
