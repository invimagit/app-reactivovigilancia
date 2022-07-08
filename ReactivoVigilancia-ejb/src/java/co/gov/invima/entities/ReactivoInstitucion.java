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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mgualdrond
 */
@Entity
@Table(name = "reactivo_institucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoInstitucion.findAll", query = "SELECT r FROM ReactivoInstitucion r"),
    @NamedQuery(name = "ReactivoInstitucion.findByReporte", query = "SELECT r FROM ReactivoInstitucion r WHERE r.reporte = :reporte"),
    @NamedQuery(name = "ReactivoInstitucion.findByInstitucion", query = "SELECT r FROM ReactivoInstitucion r WHERE r.institucion = :institucion"),
    @NamedQuery(name = "ReactivoInstitucion.findByIdentificacion", query = "SELECT r FROM ReactivoInstitucion r WHERE r.identificacion = :identificacion"),
    @NamedQuery(name = "ReactivoInstitucion.findByDireccion", query = "SELECT r FROM ReactivoInstitucion r WHERE r.direccion = :direccion"),
    @NamedQuery(name = "ReactivoInstitucion.findByNaturaleza", query = "SELECT r FROM ReactivoInstitucion r WHERE r.naturaleza = :naturaleza"),
    @NamedQuery(name = "ReactivoInstitucion.findByComplejidad", query = "SELECT r FROM ReactivoInstitucion r WHERE r.complejidad = :complejidad"),
    @NamedQuery(name = "ReactivoInstitucion.findByCodDepart", query = "SELECT r FROM ReactivoInstitucion r WHERE r.codDepart = :codDepart"),
    @NamedQuery(name = "ReactivoInstitucion.findByCodMun", query = "SELECT r FROM ReactivoInstitucion r WHERE r.codMun = :codMun"),
    @NamedQuery(name = "ReactivoInstitucion.findByEmail", query = "SELECT r FROM ReactivoInstitucion r WHERE r.email = :email"),
    @NamedQuery(name = "ReactivoInstitucion.findByFechaReporte", query = "SELECT r FROM ReactivoInstitucion r WHERE r.fechaReporte = :fechaReporte"),
    @NamedQuery(name = "ReactivoInstitucion.findByTelefono", query = "SELECT r FROM ReactivoInstitucion r WHERE r.telefono = :telefono")})
public class ReactivoInstitucion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "reporte")
    private String reporte;
    @Size(max = 128)
    @Column(name = "institucion")
    private String institucion;
    @Size(max = 11)
    @Column(name = "identificacion")
    private String identificacion;
    @Size(max = 128)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 3)
    @Column(name = "naturaleza")
    private String naturaleza;
    @Size(max = 3)
    @Column(name = "complejidad")
    private String complejidad;
    @Size(max = 2)
    @Column(name = "cod_depart")
    private String codDepart;
    @Size(max = 5)
    @Column(name = "cod_mun")
    private String codMun;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 64)
    @Column(name = "email")
    private String email;
    @Column(name = "fecha_reporte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReporte;
    @Size(max = 30)
    @Column(name = "telefono")
    private String telefono;

    public ReactivoInstitucion() {
    }

    public ReactivoInstitucion(String reporte) {
        this.reporte = reporte;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public String getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(String complejidad) {
        this.complejidad = complejidad;
    }

    public String getCodDepart() {
        return codDepart;
    }

    public void setCodDepart(String codDepart) {
        this.codDepart = codDepart;
    }

    public String getCodMun() {
        return codMun;
    }

    public void setCodMun(String codMun) {
        this.codMun = codMun;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reporte != null ? reporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoInstitucion)) {
            return false;
        }
        ReactivoInstitucion other = (ReactivoInstitucion) object;
        if ((this.reporte == null && other.reporte != null) || (this.reporte != null && !this.reporte.equals(other.reporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoInstitucion[ reporte=" + reporte + " ]";
    }
    
}
