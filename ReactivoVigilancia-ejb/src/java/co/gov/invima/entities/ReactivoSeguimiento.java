/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jonat
 */
@Entity
@Table(name = "reactivo_seguimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoSeguimiento.findAll", query = "SELECT r FROM ReactivoSeguimiento r")
    , @NamedQuery(name = "ReactivoSeguimiento.findByFechaIngreso", query = "SELECT r FROM ReactivoSeguimiento r WHERE r.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "ReactivoSeguimiento.findByUsuario", query = "SELECT r FROM ReactivoSeguimiento r WHERE r.usuario = :usuario")
    , @NamedQuery(name = "ReactivoSeguimiento.findByIdReporte", query = "SELECT r FROM ReactivoSeguimiento r WHERE r.idReporte = :idReporte")
    , @NamedQuery(name = "ReactivoSeguimiento.findByCategoria", query = "SELECT r FROM ReactivoSeguimiento r WHERE r.categoria = :categoria")
    , @NamedQuery(name = "ReactivoSeguimiento.findByCategoriaReporte", query = "SELECT r FROM ReactivoSeguimiento r WHERE r.categoria = :categoria AND r.idReporte = :reporte")
    , @NamedQuery(name = "ReactivoSeguimiento.findById", query = "SELECT r FROM ReactivoSeguimiento r WHERE r.id = :id")})
public class ReactivoSeguimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "usuario")
    private String usuario;
    
    @Size(max = 2147483647)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 255)
    @Column(name = "id_reporte")
    private String idReporte;
    @Size(max = 255)
    @Column(name = "categoria")
    private String categoria;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Transient
    private String date_string;
    
    

    public ReactivoSeguimiento() {
    }

    public ReactivoSeguimiento(Long id) {
        this.id = id;
    }

    public ReactivoSeguimiento(Long id, Date fechaIngreso, String usuario) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.usuario = usuario;
    }

    public String getDate_string() {
        return date_string;
    }

    public void setDate_string(String date_string) {
        this.date_string = date_string;
    }
    
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoSeguimiento)) {
            return false;
        }
        ReactivoSeguimiento other = (ReactivoSeguimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoSeguimiento[ id=" + id + " ]";
    }
    
}
