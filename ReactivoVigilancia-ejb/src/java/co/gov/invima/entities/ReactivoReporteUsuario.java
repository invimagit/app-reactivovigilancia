/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "reactivo_reporte_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoReporteUsuario.findAll", query = "SELECT r FROM ReactivoReporteUsuario r")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByNombre", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "ReactivoReporteUsuario.findBySexo", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.sexo = :sexo")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByEdad", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.edad = :edad")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByEdadEn", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.edadEn = :edadEn")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByDireccion", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.direccion = :direccion")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByTelefono", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.telefono = :telefono")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByPais", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.pais = :pais")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByDepartamento", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.departamento = :departamento")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByCiudad", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.ciudad = :ciudad")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByCorreo", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.correo = :correo")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByNombreReactivo", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.nombreReactivo = :nombreReactivo")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByNombreComercialReactivo", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.nombreComercialReactivo = :nombreComercialReactivo")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByRegistroReactivo", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.registroReactivo = :registroReactivo")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByLoteReactivo", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.loteReactivo = :loteReactivo")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByReferenciaReactivo", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.referenciaReactivo = :referenciaReactivo")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByFabricanteReactivo", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.fabricanteReactivo = :fabricanteReactivo")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByImportadorReactivo", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.importadorReactivo = :importadorReactivo")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByFechaEvento", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.fechaEvento = :fechaEvento")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByFechaElaboracion", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.fechaElaboracion = :fechaElaboracion")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByFechaElaboracionBetween", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.fechaElaboracion BETWEEN :fechaElaboracion1 AND :fechaElaboracion2")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByDeteccion", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.deteccion = :deteccion")
    , @NamedQuery(name = "ReactivoReporteUsuario.findByDescripcionEvento", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.descripcionEvento = :descripcionEvento")
    , @NamedQuery(name = "ReactivoReporteUsuario.findById", query = "SELECT r FROM ReactivoReporteUsuario r WHERE r.id = :id")})
public class ReactivoReporteUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 1)
    @Column(name = "sexo")
    private String sexo;
    @Size(max = 3)
    @Column(name = "edad")
    private String edad;
    @Size(max = 1)
    @Column(name = "edad_en")
    private String edadEn;
    @Size(max = 255)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 15)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 15)
    @Column(name = "pais")
    private String pais;
    @Size(max = 15)
    @Column(name = "departamento")
    private String departamento;
    @Size(max = 15)
    @Column(name = "ciudad")
    private String ciudad;
    @Size(max = 60)
    @Column(name = "correo")
    private String correo;
    @Size(max = 255)
    @Column(name = "nombre_reactivo")
    private String nombreReactivo;
    @Size(max = 255)
    @Column(name = "nombre_comercial_reactivo")
    private String nombreComercialReactivo;
    @Size(max = 100)
    @Column(name = "registro_reactivo")
    private String registroReactivo;
    @Size(max = 100)
    @Column(name = "lote_reactivo")
    private String loteReactivo;
    @Size(max = 100)
    @Column(name = "referencia_reactivo")
    private String referenciaReactivo;
    @Size(max = 255)
    @Column(name = "fabricante_reactivo")
    private String fabricanteReactivo;
    @Size(max = 255)
    @Column(name = "importador_reactivo")
    private String importadorReactivo;
    @Column(name = "fecha_evento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEvento;
    @Column(name = "fecha_elaboracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaElaboracion;
    @Size(max = 1)
    @Column(name = "deteccion")
    private String deteccion;
    @Size(max = 255)
    @Column(name = "descripcion_evento")
    private String descripcionEvento;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Transient
    private List <ReactivoSeguimiento> Seguimiento;
    @Transient
    private String nuevo_Seguimiento;

    public ReactivoReporteUsuario() {
    }

    public ReactivoReporteUsuario(Long id) {
        this.id = id;
    }

    public String getNuevo_Seguimiento() {
        return nuevo_Seguimiento;
    }

    public void setNuevo_Seguimiento(String nuevo_Seguimiento) {
        this.nuevo_Seguimiento = nuevo_Seguimiento;
    }

    public List <ReactivoSeguimiento> getSeguimiento() {
        return Seguimiento;
    }

    public void setSeguimiento(List <ReactivoSeguimiento> Seguimiento) {
        this.Seguimiento = Seguimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEdadEn() {
        return edadEn;
    }

    public void setEdadEn(String edadEn) {
        this.edadEn = edadEn;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreReactivo() {
        return nombreReactivo;
    }

    public void setNombreReactivo(String nombreReactivo) {
        this.nombreReactivo = nombreReactivo;
    }

    public String getNombreComercialReactivo() {
        return nombreComercialReactivo;
    }

    public void setNombreComercialReactivo(String nombreComercialReactivo) {
        this.nombreComercialReactivo = nombreComercialReactivo;
    }

    public String getRegistroReactivo() {
        return registroReactivo;
    }

    public void setRegistroReactivo(String registroReactivo) {
        this.registroReactivo = registroReactivo;
    }

    public String getLoteReactivo() {
        return loteReactivo;
    }

    public void setLoteReactivo(String loteReactivo) {
        this.loteReactivo = loteReactivo;
    }

    public String getReferenciaReactivo() {
        return referenciaReactivo;
    }

    public void setReferenciaReactivo(String referenciaReactivo) {
        this.referenciaReactivo = referenciaReactivo;
    }

    public String getFabricanteReactivo() {
        return fabricanteReactivo;
    }

    public void setFabricanteReactivo(String fabricanteReactivo) {
        this.fabricanteReactivo = fabricanteReactivo;
    }

    public String getImportadorReactivo() {
        return importadorReactivo;
    }

    public void setImportadorReactivo(String importadorReactivo) {
        this.importadorReactivo = importadorReactivo;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public String getDeteccion() {
        return deteccion;
    }

    public void setDeteccion(String deteccion) {
        this.deteccion = deteccion;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
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
        if (!(object instanceof ReactivoReporteUsuario)) {
            return false;
        }
        ReactivoReporteUsuario other = (ReactivoReporteUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoReporteUsuario[ id=" + id + " ]";
    }
    
}
