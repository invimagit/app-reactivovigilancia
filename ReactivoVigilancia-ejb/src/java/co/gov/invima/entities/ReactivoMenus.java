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
@Table(name = "reactivo_menus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoMenus.findAll", query = "SELECT r FROM ReactivoMenus r"),
    @NamedQuery(name = "ReactivoMenus.findByIdopcion", query = "SELECT r FROM ReactivoMenus r WHERE r.idopcion = :idopcion"),
    @NamedQuery(name = "ReactivoMenus.findByDescripcionMenu", query = "SELECT r FROM ReactivoMenus r WHERE r.descripcionMenu = :descripcionMenu"),
    @NamedQuery(name = "ReactivoMenus.findByPermitidoMenu", query = "SELECT r FROM ReactivoMenus r WHERE r.permitidoMenu = :permitidoMenu"),
    @NamedQuery(name = "ReactivoMenus.findByUrlMenu", query = "SELECT r FROM ReactivoMenus r WHERE r.urlMenu = :urlMenu"),
    @NamedQuery(name = "ReactivoMenus.findByAccionMenu", query = "SELECT r FROM ReactivoMenus r WHERE r.accionMenu = :accionMenu"),
    @NamedQuery(name = "ReactivoMenus.findByMostrarMenu", query = "SELECT r FROM ReactivoMenus r WHERE r.mostrarMenu = :mostrarMenu"),
    @NamedQuery(name = "ReactivoMenus.findBySolucionMenu", query = "SELECT r FROM ReactivoMenus r WHERE r.solucionMenu = :solucionMenu")})
public class ReactivoMenus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idopcion")
    private Integer idopcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "descripcion_menu")
    private String descripcionMenu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "permitido_menu")
    private int permitidoMenu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "url_menu")
    private String urlMenu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "accion_menu")
    private String accionMenu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mostrar_menu")
    private Character mostrarMenu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "solucion_menu")
    private Character solucionMenu;
    @JoinTable(name = "reactivo_roles_menu", joinColumns = {
        @JoinColumn(name = "ID_OPCION_MENU", referencedColumnName = "idopcion")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ROL", referencedColumnName = "IDROL")})
    @ManyToMany
    private List<ReactivoRoles> reactivoRolesList;

    public ReactivoMenus() {
    }

    public ReactivoMenus(Integer idopcion) {
        this.idopcion = idopcion;
    }

    public ReactivoMenus(Integer idopcion, String descripcionMenu, int permitidoMenu, String urlMenu, String accionMenu, Character mostrarMenu, Character solucionMenu) {
        this.idopcion = idopcion;
        this.descripcionMenu = descripcionMenu;
        this.permitidoMenu = permitidoMenu;
        this.urlMenu = urlMenu;
        this.accionMenu = accionMenu;
        this.mostrarMenu = mostrarMenu;
        this.solucionMenu = solucionMenu;
    }

    public Integer getIdopcion() {
        return idopcion;
    }

    public void setIdopcion(Integer idopcion) {
        this.idopcion = idopcion;
    }

    public String getDescripcionMenu() {
        return descripcionMenu;
    }

    public void setDescripcionMenu(String descripcionMenu) {
        this.descripcionMenu = descripcionMenu;
    }

    public int getPermitidoMenu() {
        return permitidoMenu;
    }

    public void setPermitidoMenu(int permitidoMenu) {
        this.permitidoMenu = permitidoMenu;
    }

    public String getUrlMenu() {
        return urlMenu;
    }

    public void setUrlMenu(String urlMenu) {
        this.urlMenu = urlMenu;
    }

    public String getAccionMenu() {
        return accionMenu;
    }

    public void setAccionMenu(String accionMenu) {
        this.accionMenu = accionMenu;
    }

    public Character getMostrarMenu() {
        return mostrarMenu;
    }

    public void setMostrarMenu(Character mostrarMenu) {
        this.mostrarMenu = mostrarMenu;
    }

    public Character getSolucionMenu() {
        return solucionMenu;
    }

    public void setSolucionMenu(Character solucionMenu) {
        this.solucionMenu = solucionMenu;
    }

    @XmlTransient
    public List<ReactivoRoles> getReactivoRolesList() {
        return reactivoRolesList;
    }

    public void setReactivoRolesList(List<ReactivoRoles> reactivoRolesList) {
        this.reactivoRolesList = reactivoRolesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idopcion != null ? idopcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoMenus)) {
            return false;
        }
        ReactivoMenus other = (ReactivoMenus) object;
        if ((this.idopcion == null && other.idopcion != null) || (this.idopcion != null && !this.idopcion.equals(other.idopcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoMenus[ idopcion=" + idopcion + " ]";
    }
    
}
