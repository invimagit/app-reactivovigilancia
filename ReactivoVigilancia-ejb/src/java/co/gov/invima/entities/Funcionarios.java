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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "funcionarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionarios.findAll", query = "SELECT f FROM Funcionarios f"),
    @NamedQuery(name = "Funcionarios.findByCdgfuncionario", query = "SELECT f FROM Funcionarios f WHERE f.cdgfuncionario = :cdgfuncionario"),
    @NamedQuery(name = "Funcionarios.findByNmbfuncionario", query = "SELECT f FROM Funcionarios f WHERE f.nmbfuncionario = :nmbfuncionario"),
    @NamedQuery(name = "Funcionarios.findByActivo", query = "SELECT f FROM Funcionarios f WHERE f.activo = :activo"),
    @NamedQuery(name = "Funcionarios.findByLoginName", query = "SELECT f FROM Funcionarios f WHERE f.loginName = :loginName"),
    @NamedQuery(name = "Funcionarios.findByIdentificacion", query = "SELECT f FROM Funcionarios f WHERE f.identificacion = :identificacion"),
    @NamedQuery(name = "Funcionarios.findByCdgDoc", query = "SELECT f FROM Funcionarios f WHERE f.cdgDoc = :cdgDoc"),
    @NamedQuery(name = "Funcionarios.findByTelefono", query = "SELECT f FROM Funcionarios f WHERE f.telefono = :telefono")})
public class Funcionarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cdgfuncionario")
    private Short cdgfuncionario;
    @Size(max = 60)
    @Column(name = "nmbfuncionario")
    private String nmbfuncionario;
    
    @Size(max = 2147483647)
    @Column(name = "nombramiento")
    private String nombramiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Size(max = 30)
    @Column(name = "login_name")
    private String loginName;
    @Column(name = "identificacion")
    private Long identificacion;
    @Size(max = 2)
    @Column(name = "cdg_doc")
    private String cdgDoc;
    @Column(name = "telefono")
    private Long telefono;
    
    @XmlTransient
    @JoinTable(name = "funcionario_perfil", joinColumns = {
        @JoinColumn(name = "cdgfuncionario", referencedColumnName = "cdgfuncionario")}, inverseJoinColumns = {
        @JoinColumn(name = "cdg_perfil", referencedColumnName = "cdg_perfil")})
    @ManyToMany
    private List<Perfil> perfilList;
    
    @JoinColumn(name = "cdgcargo", referencedColumnName = "cdgcargo")
    @ManyToOne(optional = false)
    private Cargos cdgcargo;

    public Funcionarios() {
    }

    public Funcionarios(Short cdgfuncionario) {
        this.cdgfuncionario = cdgfuncionario;
    }

    public Funcionarios(Short cdgfuncionario, boolean activo) {
        this.cdgfuncionario = cdgfuncionario;
        this.activo = activo;
    }

    public Short getCdgfuncionario() {
        return cdgfuncionario;
    }

    public void setCdgfuncionario(Short cdgfuncionario) {
        this.cdgfuncionario = cdgfuncionario;
    }

    public String getNmbfuncionario() {
        return nmbfuncionario;
    }

    public void setNmbfuncionario(String nmbfuncionario) {
        this.nmbfuncionario = nmbfuncionario;
    }

    public String getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Long identificacion) {
        this.identificacion = identificacion;
    }

    public String getCdgDoc() {
        return cdgDoc;
    }

    public void setCdgDoc(String cdgDoc) {
        this.cdgDoc = cdgDoc;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<Perfil> getPerfilList() {
        return perfilList;
    }

    public void setPerfilList(List<Perfil> perfilList) {
        this.perfilList = perfilList;
    }

    public Cargos getCdgcargo() {
        return cdgcargo;
    }

    public void setCdgcargo(Cargos cdgcargo) {
        this.cdgcargo = cdgcargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdgfuncionario != null ? cdgfuncionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionarios)) {
            return false;
        }
        Funcionarios other = (Funcionarios) object;
        if ((this.cdgfuncionario == null && other.cdgfuncionario != null) || (this.cdgfuncionario != null && !this.cdgfuncionario.equals(other.cdgfuncionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.Funcionarios[ cdgfuncionario=" + cdgfuncionario + " ]";
    }
    
}
