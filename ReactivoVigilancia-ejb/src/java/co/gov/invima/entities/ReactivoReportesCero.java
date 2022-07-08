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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author jgutierrezme
 */
@Entity
@Table(name = "reactivo_reportes_cero", catalog = "sivicos", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoReportesCero.findAll", query = "SELECT r FROM ReactivoReportesCero r")
    , @NamedQuery(name = "ReactivoReportesCero.findByUsuarioId", query = "SELECT r FROM ReactivoReportesCero r WHERE r.usuarioId = :usuarioId")
    , @NamedQuery(name = "ReactivoReportesCero.findByObservacion", query = "SELECT r FROM ReactivoReportesCero r WHERE r.observacion = :observacion")
    , @NamedQuery(name = "ReactivoReportesCero.findByFechaReporte", query = "SELECT r FROM ReactivoReportesCero r WHERE r.fechaReporte = :fechaReporte")
    , @NamedQuery(name = "ReactivoReportesCero.findByTrimestre", query = "SELECT r FROM ReactivoReportesCero r WHERE r.trimestre = :trimestre")
    , @NamedQuery(name = "ReactivoReportesCero.findByYearTrimestre", query = "SELECT r FROM ReactivoReportesCero r WHERE r.yearTrimestre = :yearTrimestre")
    , @NamedQuery(name = "ReactivoReportesCero.findByFechaActualiza", query = "SELECT r FROM ReactivoReportesCero r WHERE r.fechaActualiza = :fechaActualiza")
    , @NamedQuery(name = "ReactivoReportesCero.findByIdregistro", query = "SELECT r FROM ReactivoReportesCero r WHERE r.idregistro = :idregistro")
    , @NamedQuery(name = "ReactivoReportesCero.findByRadicadocero", query = "SELECT r FROM ReactivoReportesCero r WHERE r.radicadocero = :radicadocero")
    , @NamedQuery(name = "ReactivoReportesCero.findByNotificacion", query = "SELECT r FROM ReactivoReportesCero r WHERE r.notificacion = :notificacion")})
public class ReactivoReportesCero implements Serializable 
{
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="idregistro", nullable = false, insertable=false)
    private Integer idregistro;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String radicadocero;
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "usuario_id", nullable = false, length = 30)
    private String usuarioId;
    //*********************************************************************************
    //*********************************************************************************
    @Size(max = 4000)
    @Column(length = 4000)
    private String observacion;
    //*********************************************************************************
    //*********************************************************************************
    @Column(name = "fecha_reporte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReporte;
    //*********************************************************************************
    //*********************************************************************************
    @Size(max = 2)
    @Column(length = 2)
    private String trimestre;
    //*********************************************************************************
    //*********************************************************************************
    @Column(name = "year_trimestre")
    private Short yearTrimestre;
    //*********************************************************************************
    //*********************************************************************************
    @Column(name = "fecha_actualiza")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualiza;
    //*********************************************************************************
    //*********************************************************************************
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(nullable = false, length = 20)
    private String notificacion;
    //*********************************************************************************
    //*********************************************************************************
    @Size(max = 2)
    @Column(length = 2)
    private String depto;
    //****************************************************************
    //****************************************************************
    @Size(max = 5)
    @Column(length = 5)
    private String municipio;
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
    
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
    //*********************************************************************************
    public ReactivoReportesCero() {
    }

    public ReactivoReportesCero(int idregistro, String radicadocero) {
        this.idregistro = idregistro;
        this.radicadocero = radicadocero;
    }
    


    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public Short getYearTrimestre() {
        return yearTrimestre;
    }

    public void setYearTrimestre(Short yearTrimestre) {
        this.yearTrimestre = yearTrimestre;
    }

    public Date getFechaActualiza() {
        return fechaActualiza;
    }

    public void setFechaActualiza(Date fechaActualiza) {
        this.fechaActualiza = fechaActualiza;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    /*
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reactivoReportesCeroPK != null ? reactivoReportesCeroPK.hashCode() : 0);
        return hash;
    }
    */

    /*
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoReportesCero)) {
            return false;
        }
        ReactivoReportesCero other = (ReactivoReportesCero) object;
        if ((this.reactivoReportesCeroPK == null && other.reactivoReportesCeroPK != null) || (this.reactivoReportesCeroPK != null && !this.reactivoReportesCeroPK.equals(other.reactivoReportesCeroPK))) {
            return false;
        }
        return true;
    }
    */

    /*
    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoReportesCero[ reactivoReportesCeroPK=" + idregistro + " ]";
    }
    */

    /**
     * @return the idregistro
     */
    public Integer getIdregistro() {
        return idregistro;
    }

    /**
     * @param idregistro the idregistro to set
     */
    public void setIdregistro(Integer idregistro) {
        this.idregistro = idregistro;
    }

    /**
     * @return the radicadocero
     */
    public String getRadicadocero() {
        return radicadocero;
    }

    /**
     * @param radicadocero the radicadocero to set
     */
    public void setRadicadocero(String radicadocero) {
        this.radicadocero = radicadocero;
    }

    /**
     * @return the depto
     */
    public String getDepto() {
        return depto;
    }

    /**
     * @param depto the depto to set
     */
    public void setDepto(String depto) {
        this.depto = depto;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    
    
    
}
