
package co.gov.invima.VO;

import java.io.Serializable;

/**
 * Clase que mapea la tabla tecno_riesgo
 * @author Ingeniero Jaime Alberto Gutiérrez Mejía
 */
public class ReactivoReporteCeroVO implements Serializable
{
    //*********************************************************************
    //*********************************************************************
    //*********************************************************************
    private String idregistro;
    private String usuario_id;
    private String observacion;
    private java.util.Date fecha_reporte;
    private String trimestre;
    private String year_trimestre;
    private java.util.Date  fecha_actualiza;
    private String radicadocero;    
    private String notificacion;
    private String depto;
    private String municipio;
    //*********************************************************************
    //*********************************************************************
    //*********************************************************************

    /**
     * @return the idregistro
     */
    public String getIdregistro() {
        return idregistro;
    }

    /**
     * @param idregistro the idregistro to set
     */
    public void setIdregistro(String idregistro) {
        this.idregistro = idregistro;
    }

    /**
     * @return the usuario_id
     */
    public String getUsuario_id() {
        return usuario_id;
    }

    /**
     * @param usuario_id the usuario_id to set
     */
    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the fecha_reporte
     */
    public java.util.Date  getFecha_reporte() {
        return fecha_reporte;
    }

    /**
     * @param fecha_reporte the fecha_reporte to set
     */
    public void setFecha_reporte(java.util.Date fecha_reporte) {
        this.fecha_reporte = fecha_reporte;
    }

    /**
     * @return the trimestre
     */
    public String getTrimestre() {
        return trimestre;
    }

    /**
     * @param trimestre the trimestre to set
     */
    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    /**
     * @return the year_trimestre
     */
    public String getYear_trimestre() {
        return year_trimestre;
    }

    /**
     * @param year_trimestre the year_trimestre to set
     */
    public void setYear_trimestre(String year_trimestre) {
        this.year_trimestre = year_trimestre;
    }

    /**
     * @return the fecha_actualiza
     */
    public java.util.Date getFecha_actualiza() {
        return fecha_actualiza;
    }

    /**
     * @param fecha_actualiza the fecha_actualiza to set
     */
    public void setFecha_actualiza(java.util.Date fecha_actualiza) {
        this.fecha_actualiza = fecha_actualiza;
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
     * @return the notificacion
     */
    public String getNotificacion() {
        return notificacion;
    }

    /**
     * @param notificacion the notificacion to set
     */
    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
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
