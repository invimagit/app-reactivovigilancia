/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mgualdrond
 */
@Entity
@Table(name = "reactivo_datos_expedientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoDatosExpedientes.findAll", query = "SELECT r FROM ReactivoDatosExpedientes r"),
    @NamedQuery(name = "ReactivoDatosExpedientes.findByReporte", query = "SELECT r FROM ReactivoDatosExpedientes r WHERE r.reactivoDatosExpedientesPK.reporte = :reporte"),
    @NamedQuery(name = "ReactivoDatosExpedientes.findByNroexpediente", query = "SELECT r FROM ReactivoDatosExpedientes r WHERE r.reactivoDatosExpedientesPK.nroexpediente = :nroexpediente"),
    @NamedQuery(name = "ReactivoDatosExpedientes.findByNroregsan", query = "SELECT r FROM ReactivoDatosExpedientes r WHERE r.nroregsan = :nroregsan"),
    @NamedQuery(name = "ReactivoDatosExpedientes.findByFchvencimiento", query = "SELECT r FROM ReactivoDatosExpedientes r WHERE r.fchvencimiento = :fchvencimiento"),
    @NamedQuery(name = "ReactivoDatosExpedientes.findByNivelRiesgo", query = "SELECT r FROM ReactivoDatosExpedientes r WHERE r.nivelRiesgo = :nivelRiesgo"),
    @NamedQuery(name = "ReactivoDatosExpedientes.findByObservacion", query = "SELECT r FROM ReactivoDatosExpedientes r WHERE r.observacion = :observacion"),
    @NamedQuery(name = "ReactivoDatosExpedientes.findByReferenciaAutorizada", query = "SELECT r FROM ReactivoDatosExpedientes r WHERE r.referenciaAutorizada = :referenciaAutorizada"),
    @NamedQuery(name = "ReactivoDatosExpedientes.findByVidaUtil", query = "SELECT r FROM ReactivoDatosExpedientes r WHERE r.vidaUtil = :vidaUtil"),
    @NamedQuery(name = "ReactivoDatosExpedientes.findByArea", query = "SELECT r FROM ReactivoDatosExpedientes r WHERE r.area = :area")})
public class ReactivoDatosExpedientes implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReactivoDatosExpedientesPK reactivoDatosExpedientesPK;
    //
    @Size(max = 2147483647)
    @Column(name = "nmbproducto")
    private String nmbproducto;
    @Size(max = 30)
    @Column(name = "nroregsan")
    private String nroregsan;
    @Column(name = "fchvencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchvencimiento;
    @Size(max = 5)
    @Column(name = "nivel_riesgo")
    private String nivelRiesgo;
    @Size(max = 255)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 255)
    @Column(name = "referencia_autorizada")
    private String referenciaAutorizada;
    @Column(name = "vida_util")
    private Integer vidaUtil;
    @Size(max = 200)
    @Column(name = "area")
    private String area;

    public ReactivoDatosExpedientes() {
    }

    public ReactivoDatosExpedientes(ReactivoDatosExpedientesPK reactivoDatosExpedientesPK) {
        this.reactivoDatosExpedientesPK = reactivoDatosExpedientesPK;
    }

    public ReactivoDatosExpedientes(String reporte, long nroexpediente) {
        this.reactivoDatosExpedientesPK = new ReactivoDatosExpedientesPK(reporte, nroexpediente);
    }

    public ReactivoDatosExpedientesPK getReactivoDatosExpedientesPK() {
        return reactivoDatosExpedientesPK;
    }

    public void setReactivoDatosExpedientesPK(ReactivoDatosExpedientesPK reactivoDatosExpedientesPK) {
        this.reactivoDatosExpedientesPK = reactivoDatosExpedientesPK;
    }

    public String getNmbproducto() {
        return nmbproducto;
    }

    public void setNmbproducto(String nmbproducto) {
        this.nmbproducto = nmbproducto;
    }

    public String getNroregsan() {
        return nroregsan;
    }

    public void setNroregsan(String nroregsan) {
        this.nroregsan = nroregsan;
    }

    public Date getFchvencimiento() {
        return fchvencimiento;
    }

    public void setFchvencimiento(Date fchvencimiento) {
        this.fchvencimiento = fchvencimiento;
    }

    public String getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(String nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getReferenciaAutorizada() {
        return referenciaAutorizada;
    }

    public void setReferenciaAutorizada(String referenciaAutorizada) {
        this.referenciaAutorizada = referenciaAutorizada;
    }

    public Integer getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(Integer vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reactivoDatosExpedientesPK != null ? reactivoDatosExpedientesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoDatosExpedientes)) {
            return false;
        }
        ReactivoDatosExpedientes other = (ReactivoDatosExpedientes) object;
        if ((this.reactivoDatosExpedientesPK == null && other.reactivoDatosExpedientesPK != null) || (this.reactivoDatosExpedientesPK != null && !this.reactivoDatosExpedientesPK.equals(other.reactivoDatosExpedientesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoDatosExpedientes[ reactivoDatosExpedientesPK=" + reactivoDatosExpedientesPK + " ]";
    }
    
}
