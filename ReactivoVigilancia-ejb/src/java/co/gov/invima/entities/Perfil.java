/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mgualdrond
 */
@Entity
@Table(name = "perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p"),
    @NamedQuery(name = "Perfil.findByCdgPerfil", query = "SELECT p FROM Perfil p WHERE p.cdgPerfil = :cdgPerfil"),
    @NamedQuery(name = "Perfil.findByNmbPerfil", query = "SELECT p FROM Perfil p WHERE p.nmbPerfil = :nmbPerfil")})
public class Perfil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cdg_perfil")
    private Short cdgPerfil;
    @Size(max = 30)
    @Column(name = "nmb_perfil")
    private String nmbPerfil;
    @ManyToMany(mappedBy = "perfilList")
    private List<Funcionarios> funcionariosList;

    public Perfil() {
    }

    public Perfil(Short cdgPerfil) {
        this.cdgPerfil = cdgPerfil;
    }

    public Short getCdgPerfil() {
        return cdgPerfil;
    }

    public void setCdgPerfil(Short cdgPerfil) {
        this.cdgPerfil = cdgPerfil;
    }

    public String getNmbPerfil() {
        return nmbPerfil;
    }

    public void setNmbPerfil(String nmbPerfil) {
        this.nmbPerfil = nmbPerfil;
    }

    @XmlTransient
    public List<Funcionarios> getFuncionariosList() {
        return funcionariosList;
    }

    public void setFuncionariosList(List<Funcionarios> funcionariosList) {
        this.funcionariosList = funcionariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdgPerfil != null ? cdgPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.cdgPerfil == null && other.cdgPerfil != null) || (this.cdgPerfil != null && !this.cdgPerfil.equals(other.cdgPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.Perfil[ cdgPerfil=" + cdgPerfil + " ]";
    }
    
}
