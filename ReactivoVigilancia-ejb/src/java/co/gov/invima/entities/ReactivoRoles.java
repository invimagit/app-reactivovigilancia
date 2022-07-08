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
@Table(name = "reactivo_roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoRoles.findAll", query = "SELECT r FROM ReactivoRoles r"),
    @NamedQuery(name = "ReactivoRoles.findByIdrol", query = "SELECT r FROM ReactivoRoles r WHERE r.idrol = :idrol"),
    @NamedQuery(name = "ReactivoRoles.findByNombreRol", query = "SELECT r FROM ReactivoRoles r WHERE r.nombreRol = :nombreRol")})
public class ReactivoRoles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDROL")
    private Integer idrol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_rol")
    private String nombreRol;
    @ManyToMany(mappedBy = "reactivoRolesList")
    private List<ReactivoMenus> reactivoMenusList;

    public ReactivoRoles() {
    }

    public ReactivoRoles(Integer idrol) {
        this.idrol = idrol;
    }

    public ReactivoRoles(Integer idrol, String nombreRol) {
        this.idrol = idrol;
        this.nombreRol = nombreRol;
    }

    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @XmlTransient
    public List<ReactivoMenus> getReactivoMenusList() {
        return reactivoMenusList;
    }

    public void setReactivoMenusList(List<ReactivoMenus> reactivoMenusList) {
        this.reactivoMenusList = reactivoMenusList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrol != null ? idrol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoRoles)) {
            return false;
        }
        ReactivoRoles other = (ReactivoRoles) object;
        if ((this.idrol == null && other.idrol != null) || (this.idrol != null && !this.idrol.equals(other.idrol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoRoles[ idrol=" + idrol + " ]";
    }
    
}
