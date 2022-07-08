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
@Table(name = "reactivo_reportante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoReportante.findAll", query = "SELECT r FROM ReactivoReportante r"),
    @NamedQuery(name = "ReactivoReportante.findByReporte", query = "SELECT r FROM ReactivoReportante r WHERE r.reporte = :reporte"),
    @NamedQuery(name = "ReactivoReportante.findByNombresApellidos", query = "SELECT r FROM ReactivoReportante r WHERE r.nombresApellidos = :nombresApellidos"),
    @NamedQuery(name = "ReactivoReportante.findByIdentificacion", query = "SELECT r FROM ReactivoReportante r WHERE r.identificacion = :identificacion"),
    @NamedQuery(name = "ReactivoReportante.findByCargo", query = "SELECT r FROM ReactivoReportante r WHERE r.cargo = :cargo"),
    @NamedQuery(name = "ReactivoReportante.findByProfesion", query = "SELECT r FROM ReactivoReportante r WHERE r.profesion = :profesion"),
    @NamedQuery(name = "ReactivoReportante.findByDireccion", query = "SELECT r FROM ReactivoReportante r WHERE r.direccion = :direccion"),
    @NamedQuery(name = "ReactivoReportante.findByPais", query = "SELECT r FROM ReactivoReportante r WHERE r.pais = :pais"),
    @NamedQuery(name = "ReactivoReportante.findByCodDepart", query = "SELECT r FROM ReactivoReportante r WHERE r.codDepart = :codDepart"),
    @NamedQuery(name = "ReactivoReportante.findByCodMun", query = "SELECT r FROM ReactivoReportante r WHERE r.codMun = :codMun"),
    @NamedQuery(name = "ReactivoReportante.findByTelefono", query = "SELECT r FROM ReactivoReportante r WHERE r.telefono = :telefono"),
    @NamedQuery(name = "ReactivoReportante.findByMovil", query = "SELECT r FROM ReactivoReportante r WHERE r.movil = :movil"),
    @NamedQuery(name = "ReactivoReportante.findByEmail", query = "SELECT r FROM ReactivoReportante r WHERE r.email = :email"),
    @NamedQuery(name = "ReactivoReportante.findByDivulgacion", query = "SELECT r FROM ReactivoReportante r WHERE r.divulgacion = :divulgacion"),
    @NamedQuery(name = "ReactivoReportante.findByFechaNotificacion", query = "SELECT r FROM ReactivoReportante r WHERE r.fechaNotificacion = :fechaNotificacion"),
    @NamedQuery(name = "ReactivoReportante.findByAreaPertenece", query = "SELECT r FROM ReactivoReportante r WHERE r.areaPertenece = :areaPertenece")})
public class ReactivoReportante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "reporte")
    private String reporte;
    @Size(max = 128)
    @Column(name = "nombres_apellidos")
    private String nombresApellidos;
    @Size(max = 11)
    @Column(name = "identificacion")
    private String identificacion;
    @Size(max = 128)
    @Column(name = "cargo")
    private String cargo;
    @Size(max = 128)
    @Column(name = "profesion")
    private String profesion;
    @Size(max = 128)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 2)
    @Column(name = "pais")
    private String pais;
    @Size(max = 2)
    @Column(name = "cod_depart")
    private String codDepart;
    @Size(max = 5)
    @Column(name = "cod_mun")
    private String codMun;
    @Size(max = 15)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 15)
    @Column(name = "movil")
    private String movil;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 128)
    @Column(name = "email")
    private String email;
    @Size(max = 2)
    @Column(name = "divulgacion")
    private String divulgacion;
    @Column(name = "fecha_notificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNotificacion;
    @Size(max = 128)
    @Column(name = "area_pertenece")
    private String areaPertenece;

    public ReactivoReportante() {
    }

    public ReactivoReportante(String reporte) {
        this.reporte = reporte;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public String getNombresApellidos() {
        return nombresApellidos;
    }

    public void setNombresApellidos(String nombresApellidos) {
        this.nombresApellidos = nombresApellidos;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDivulgacion() {
        return divulgacion;
    }

    public void setDivulgacion(String divulgacion) {
        this.divulgacion = divulgacion;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getAreaPertenece() {
        return areaPertenece;
    }

    public void setAreaPertenece(String areaPertenece) {
        this.areaPertenece = areaPertenece;
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
        if (!(object instanceof ReactivoReportante)) {
            return false;
        }
        ReactivoReportante other = (ReactivoReportante) object;
        if ((this.reporte == null && other.reporte != null) || (this.reporte != null && !this.reporte.equals(other.reporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoReportante[ reporte=" + reporte + " ]";
    }
    
}
