/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.entities;

import java.io.Serializable;
import java.lang.reflect.Field;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;

/**
 *
 * @author jgutierrezme
 */
@Entity
@Table(name = "reactivo_eventos_temp", catalog = "SIVICOS", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoEventosTemp.findAll", query = "SELECT r FROM ReactivoEventosTemp r")
    , @NamedQuery(name = "ReactivoEventosTemp.findByReporte", query = "SELECT r FROM ReactivoEventosTemp r WHERE r.reporte = :reporte")
    , @NamedQuery(name = "ReactivoEventosTemp.findByFechEvento", query = "SELECT r FROM ReactivoEventosTemp r WHERE r.fechEvento = :fechEvento")
    , @NamedQuery(name = "ReactivoEventosTemp.findByEfectoIndeseado", query = "SELECT r FROM ReactivoEventosTemp r WHERE r.efectoIndeseado = :efectoIndeseado")
    , @NamedQuery(name = "ReactivoEventosTemp.findByFechReporte", query = "SELECT r FROM ReactivoEventosTemp r WHERE r.fechReporte = :fechReporte")
    , @NamedQuery(name = "ReactivoEventosTemp.findByCdgProblema", query = "SELECT r FROM ReactivoEventosTemp r WHERE r.cdgProblema = :cdgProblema")
    , @NamedQuery(name = "ReactivoEventosTemp.findByClasificacion", query = "SELECT r FROM ReactivoEventosTemp r WHERE r.clasificacion = :clasificacion")
    , @NamedQuery(name = "ReactivoEventosTemp.findByCdgDesenlace", query = "SELECT r FROM ReactivoEventosTemp r WHERE r.cdgDesenlace = :cdgDesenlace")
    , @NamedQuery(name = "ReactivoEventosTemp.findByOtroDesenlace", query = "SELECT r FROM ReactivoEventosTemp r WHERE r.otroDesenlace = :otroDesenlace")
    , @NamedQuery(name = "ReactivoEventosTemp.findByDesempeno", query = "SELECT r FROM ReactivoEventosTemp r WHERE r.desempeno = :desempeno")
    , @NamedQuery(name = "ReactivoEventosTemp.findByInternet", query = "SELECT r FROM ReactivoEventosTemp r WHERE r.internet = :internet")
    , @NamedQuery(name = "ReactivoEventosTemp.findByFechaIngreso", query = "SELECT r FROM ReactivoEventosTemp r WHERE r.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "ReactivoEventosTemp.findByReportado", query = "SELECT r FROM ReactivoEventosTemp r WHERE r.reportado = :reportado")
    , @NamedQuery(name = "ReactivoEventosTemp.findByIdrol", query = "SELECT r FROM ReactivoEventosTemp r WHERE r.idrol = :idrol")
    , @NamedQuery(name = "ReactivoEventosTemp.findByIdips", query = "SELECT r FROM ReactivoEventosTemp r WHERE r.idips = :idips")})
public class ReactivoEventosTemp implements Serializable 
{
    
    private final static Logger logEJBReactivo = Logger.getLogger(ReactivoEventosTemp.class.getName());
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(nullable = false, length = 15)
    private String reporte;
    @Column(name = "fech_evento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechEvento;
    @Size(max = 64)
    @Column(name = "efecto_indeseado", length = 64)
    private String efectoIndeseado;
    @Column(name = "fech_reporte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechReporte;
    @Column(name = "cdg_problema")
    private Integer cdgProblema;
    @Size(max = 2147483647)
    @Column(name = "descrip_efecto", length = 2147483647)
    private String descripEfecto;
    @Size(max = 20)
    @Column(length = 20)
    private String clasificacion;
    @Column(name = "cdg_desenlace")
    private Integer cdgDesenlace;
    @Size(max = 1024)
    @Column(name = "otro_desenlace", length = 1024)
    private String otroDesenlace;
    @Size(max = 1)
    @Column(length = 1)
    private String desempeno;
    @Size(max = 1)
    @Column(length = 1)
    private String internet;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Size(max = 1)
    @Column(length = 1)
    private String reportado;
    private Integer idrol;
    @Size(max = 20)
    @Column(length = 20)
    private String idips;
    //*********************************************************************
    @Column(name = "reportepre")
    private String reportepre;
    @Column(name = "cdg_seriedad")
    private Integer cdgSeriedad;
    

    public ReactivoEventosTemp() {
    }

    public ReactivoEventosTemp(String reporte) {
        this.reporte = reporte;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public Date getFechEvento() {
        return fechEvento;
    }

    public void setFechEvento(Date fechEvento) {
        this.fechEvento = fechEvento;
    }

    public String getEfectoIndeseado() {
        return efectoIndeseado;
    }

    public void setEfectoIndeseado(String efectoIndeseado) {
        this.efectoIndeseado = efectoIndeseado;
    }

    public Date getFechReporte() {
        return fechReporte;
    }

    public void setFechReporte(Date fechReporte) {
        this.fechReporte = fechReporte;
    }

    public Integer getCdgProblema() {
        return cdgProblema;
    }

    public void setCdgProblema(Integer cdgProblema) {
        this.cdgProblema = cdgProblema;
    }

    public String getDescripEfecto() {
        return descripEfecto;
    }

    public void setDescripEfecto(String descripEfecto) {
        this.descripEfecto = descripEfecto;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Integer getCdgDesenlace() {
        return cdgDesenlace;
    }

    public void setCdgDesenlace(Integer cdgDesenlace) {
        this.cdgDesenlace = cdgDesenlace;
    }

    public String getOtroDesenlace() {
        return otroDesenlace;
    }

    public void setOtroDesenlace(String otroDesenlace) {
        this.otroDesenlace = otroDesenlace;
    }

    public String getDesempeno() {
        return desempeno;
    }

    public void setDesempeno(String desempeno) {
        this.desempeno = desempeno;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getReportado() {
        return reportado;
    }

    public void setReportado(String reportado) {
        this.reportado = reportado;
    }

    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public String getIdips() {
        return idips;
    }

    public void setIdips(String idips) {
        this.idips = idips;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getReporte() != null ? getReporte().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoEventosTemp)) {
            return false;
        }
        ReactivoEventosTemp other = (ReactivoEventosTemp) object;
        if ((this.getReporte() == null && other.getReporte() != null) || (this.getReporte() != null && !this.reporte.equals(other.reporte))) {
            return false;
        }
        return true;
    }

    
    //*******************************************************************************
    //*******************************************************************************
    //*******************************************************************************
    //*******************************************************************************
    //*******************************************************************************
    //*******************************************************************************
    @Override
    public String toString() 
    {
            //logEJBReactivo.info ("*******************************************************");
            //logEJBReactivo.info ("*******************************************************");
            //logEJBReactivo.info ("*******************************************************");
            //logEJBReactivo.info ("Imprimiendo campos del objeto " + this.getClass().getCanonicalName());
            StringBuilder result = new StringBuilder();
            String newLine = System.getProperty("line.separator");
            String valor = "";
            result.append( this.getClass().getName() );
            result.append( " Object {" );
            result.append(newLine);

            //determine fields declared in this class only (no fields of superclass)
            Field[] fields = this.getClass().getDeclaredFields();

            //print field names paired with their values
            for ( Field field : fields  ) {
              result.append("  ");
              try {
                  valor = String.valueOf(field.get(this));
                result.append( field.getName() );
                result.append(": ");
                result.append( field.get(this) );
                result.append(" - Longitud del campo: ");
                result.append(valor.length());
                //requires access to private field:
              } catch ( IllegalAccessException ex ) {
                //logEJBReactivo.info(ex);
              }
              result.append(newLine);
            }
            result.append("}");
            //logEJBReactivo.info ("*******************************************************");
            //logEJBReactivo.info ("*******************************************************");
            //logEJBReactivo.info ("*******************************************************");

            return result.toString();
    }
    //*******************************************************************************
    //*******************************************************************************
    //*******************************************************************************
    //*******************************************************************************
    //*******************************************************************************
    //*******************************************************************************

    /**
     * @return the cdgSeriedad
     */
    public Integer getCdgSeriedad() {
        return cdgSeriedad;
    }

    /**
     * @param cdgSeriedad the cdgSeriedad to set
     */
    public void setCdgSeriedad(Integer cdgSeriedad) {
        this.cdgSeriedad = cdgSeriedad;
    }
    
    /**
     * @return the reportepre
     */
    public String getReportepre() {
        return reportepre;
    }

    /**
     * @param reportepre the reportepre to set
     */
    public void setReportepre(String reportepre) {
        this.reportepre = reportepre;
    }
    //*******************************************************************************
    //*******************************************************************************
    //*******************************************************************************
    //*******************************************************************************
    //*******************************************************************************
    //*******************************************************************************
}
