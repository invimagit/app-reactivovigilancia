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
 * @author mgualdrond
 */
@Entity
@Table(name = "reactivo_eventos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoEventos.findAll", query = "SELECT r FROM ReactivoEventos r"),
    @NamedQuery(name = "ReactivoEventos.findByReporte", query = "SELECT r FROM ReactivoEventos r WHERE r.reporte = :reporte"),
    @NamedQuery(name = "ReactivoEventos.findByFechEvento", query = "SELECT r FROM ReactivoEventos r WHERE r.fechEvento = :fechEvento"),
    @NamedQuery(name = "ReactivoEventos.findByEfectoIndeseado", query = "SELECT r FROM ReactivoEventos r WHERE r.efectoIndeseado = :efectoIndeseado"),
    @NamedQuery(name = "ReactivoEventos.findByFechReporte", query = "SELECT r FROM ReactivoEventos r WHERE r.fechReporte = :fechReporte"),
    @NamedQuery(name = "ReactivoEventos.findByCdgProblema", query = "SELECT r FROM ReactivoEventos r WHERE r.cdgProblema = :cdgProblema"),
    @NamedQuery(name = "ReactivoEventos.findByClasificacion", query = "SELECT r FROM ReactivoEventos r WHERE r.clasificacion = :clasificacion"),
    @NamedQuery(name = "ReactivoEventos.findByCdgDesenlace", query = "SELECT r FROM ReactivoEventos r WHERE r.cdgDesenlace = :cdgDesenlace"),
    @NamedQuery(name = "ReactivoEventos.findByOtroDesenlace", query = "SELECT r FROM ReactivoEventos r WHERE r.otroDesenlace = :otroDesenlace"),
    @NamedQuery(name = "ReactivoEventos.findByDesempeno", query = "SELECT r FROM ReactivoEventos r WHERE r.desempeno = :desempeno"),
    @NamedQuery(name = "ReactivoEventos.findByInternet", query = "SELECT r FROM ReactivoEventos r WHERE r.internet = :internet"),
    @NamedQuery(name = "ReactivoEventos.findByFechaIngreso", query = "SELECT r FROM ReactivoEventos r WHERE r.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "ReactivoEventos.findByReportado", query = "SELECT r FROM ReactivoEventos r WHERE r.reportado = :reportado")})
public class ReactivoEventos implements Serializable {

    private final static Logger logEJBReactivo = Logger.getLogger(ReactivoEventos.class.getName());
    
    
    @Size(max = 1)
    @Column(length = 1)
    private String desempeno;
    @Size(max = 1)
    @Column(length = 1)
    private String internet;
    @Size(max = 1)
    @Column(length = 1)
    private String reportado;
    private Integer idrol;
    @Size(max = 20)
    @Column(length = 20)
    private String idips;
    @Size(max = 30)
    @Column(length = 30)
    private String reportepre;

    private static long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "reporte")
    private String reporte;
    @Column(name = "fech_evento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechEvento;
    @Size(max = 64)
    @Column(name = "efecto_indeseado")
    private String efectoIndeseado;
    @Column(name = "fech_reporte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechReporte;
    @Column(name = "cdg_problema")
    private Integer cdgProblema;
    //
    @Size(max = 2147483647)
    @Column(name = "descrip_efecto")
    private String descripEfecto;
    @Size(max = 20)
    @Column(name = "clasificacion")
    private String clasificacion;
    @Column(name = "cdg_desenlace")
    private Integer cdgDesenlace;
    @Size(max = 1024)
    @Column(name = "otro_desenlace")
    private String otroDesenlace;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    
    @Column(name = "cdg_seriedad")
    private Integer cdgSeriedad;
    

    public ReactivoEventos() {
    }

    public ReactivoEventos(String reporte) {
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


    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
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
        if (!(object instanceof ReactivoEventos)) {
            return false;
        }
        ReactivoEventos other = (ReactivoEventos) object;
        if ((this.getReporte() == null && other.getReporte() != null) || (this.getReporte() != null && !this.reporte.equals(other.reporte))) {
            return false;
        }
        return true;
    }

    /*
    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoEventos[ reporte=" + reporte + " ]";
    }
    */

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

    public String getReportepre() {
        return reportepre;
    }

    public void setReportepre(String reportepre) {
        this.reportepre = reportepre;
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


    
    
    
}
