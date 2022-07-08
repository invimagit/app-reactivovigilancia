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
@Table(name = "reactivo_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoProducto.findAll", query = "SELECT r FROM ReactivoProducto r"),
    @NamedQuery(name = "ReactivoProducto.findByReporte", query = "SELECT r FROM ReactivoProducto r WHERE r.reporte = :reporte"),
    @NamedQuery(name = "ReactivoProducto.findByNombreReactivo", query = "SELECT r FROM ReactivoProducto r WHERE r.nombreReactivo = :nombreReactivo"),
    @NamedQuery(name = "ReactivoProducto.findByNroregsan", query = "SELECT r FROM ReactivoProducto r WHERE r.nroregsan = :nroregsan"),
    @NamedQuery(name = "ReactivoProducto.findByFechaVenci", query = "SELECT r FROM ReactivoProducto r WHERE r.fechaVenci = :fechaVenci"),
    @NamedQuery(name = "ReactivoProducto.findByLote", query = "SELECT r FROM ReactivoProducto r WHERE r.lote = :lote"),
    @NamedQuery(name = "ReactivoProducto.findByProcedencia", query = "SELECT r FROM ReactivoProducto r WHERE r.procedencia = :procedencia"),
    @NamedQuery(name = "ReactivoProducto.findByCadenaFrio", query = "SELECT r FROM ReactivoProducto r WHERE r.cadenaFrio = :cadenaFrio"),
    @NamedQuery(name = "ReactivoProducto.findByTemperatura", query = "SELECT r FROM ReactivoProducto r WHERE r.temperatura = :temperatura"),
    @NamedQuery(name = "ReactivoProducto.findByCertiAnalisis", query = "SELECT r FROM ReactivoProducto r WHERE r.certiAnalisis = :certiAnalisis"),
    @NamedQuery(name = "ReactivoProducto.findByDistriUsuario", query = "SELECT r FROM ReactivoProducto r WHERE r.distriUsuario = :distriUsuario"),
    @NamedQuery(name = "ReactivoProducto.findByCondiciAlmacen", query = "SELECT r FROM ReactivoProducto r WHERE r.condiciAlmacen = :condiciAlmacen"),
    @NamedQuery(name = "ReactivoProducto.findByAreaFunciona", query = "SELECT r FROM ReactivoProducto r WHERE r.areaFunciona = :areaFunciona"),
    @NamedQuery(name = "ReactivoProducto.findByOtraArea", query = "SELECT r FROM ReactivoProducto r WHERE r.otraArea = :otraArea")})
public class ReactivoProducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "reporte")
    private String reporte;
    @Size(max = 255)
    @Column(name = "nombre_reactivo")
    private String nombreReactivo;
    @Size(max = 25)
    @Column(name = "nroregsan")
    private String nroregsan;
    @Column(name = "fecha_venci")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVenci;
    @Size(max = 64)
    @Column(name = "lote")
    private String lote;
    @Size(max = 15)
    @Column(name = "procedencia")
    private String procedencia;
    @Size(max = 2)
    @Column(name = "cadena_frio")
    private String cadenaFrio;
    @Size(max = 10)
    @Column(name = "temperatura")
    private String temperatura;
    @Size(max = 2)
    @Column(name = "certi_analisis")
    private String certiAnalisis;
    @Size(max = 255)
    @Column(name = "distri_usuario")
    private String distriUsuario;
    @Size(max = 2)
    @Column(name = "condici_almacen")
    private String condiciAlmacen;
    @Column(name = "area_funciona")
    private Integer areaFunciona;
    @Size(max = 128)
    @Column(name = "otra_area")
    private String otraArea;

    public ReactivoProducto() {
    }

    public ReactivoProducto(String reporte) {
        this.reporte = reporte;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public String getNombreReactivo() {
        return nombreReactivo;
    }

    public void setNombreReactivo(String nombreReactivo) {
        this.nombreReactivo = nombreReactivo;
    }

    public String getNroregsan() {
        return nroregsan;
    }

    public void setNroregsan(String nroregsan) {
        this.nroregsan = nroregsan;
    }

    public Date getFechaVenci() {
        return fechaVenci;
    }

    public void setFechaVenci(Date fechaVenci) {
        this.fechaVenci = fechaVenci;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getCadenaFrio() {
        return cadenaFrio;
    }

    public void setCadenaFrio(String cadenaFrio) {
        this.cadenaFrio = cadenaFrio;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getCertiAnalisis() {
        return certiAnalisis;
    }

    public void setCertiAnalisis(String certiAnalisis) {
        this.certiAnalisis = certiAnalisis;
    }

    public String getDistriUsuario() {
        return distriUsuario;
    }

    public void setDistriUsuario(String distriUsuario) {
        this.distriUsuario = distriUsuario;
    }

    public String getCondiciAlmacen() {
        return condiciAlmacen;
    }

    public void setCondiciAlmacen(String condiciAlmacen) {
        this.condiciAlmacen = condiciAlmacen;
    }

    public Integer getAreaFunciona() {
        return areaFunciona;
    }

    public void setAreaFunciona(Integer areaFunciona) {
        this.areaFunciona = areaFunciona;
    }

    public String getOtraArea() {
        return otraArea;
    }

    public void setOtraArea(String otraArea) {
        this.otraArea = otraArea;
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
        if (!(object instanceof ReactivoProducto)) {
            return false;
        }
        ReactivoProducto other = (ReactivoProducto) object;
        if ((this.reporte == null && other.reporte != null) || (this.reporte != null && !this.reporte.equals(other.reporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoProducto[ reporte=" + reporte + " ]";
    }
    
}
