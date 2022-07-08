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
@Table(name = "reactivo_camposcomp_temp", catalog = "SIVICOS", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoCamposcompTemp.findAll", query = "SELECT r FROM ReactivoCamposcompTemp r")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByReporte", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.reporte = :reporte")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByOrganizacion", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.organizacion = :organizacion")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByCodigoCausa", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.codigoCausa = :codigoCausa")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByFechaEnvioReactivo", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.fechaEnvioReactivo = :fechaEnvioReactivo")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByExpediente", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.expediente = :expediente")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByCodigoUnicoReactivo", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.codigoUnicoReactivo = :codigoUnicoReactivo")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByCodigoTipoDiagnostico", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.codigoTipoDiagnostico = :codigoTipoDiagnostico")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByReferencia", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.referencia = :referencia")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByCategoriaReactivo", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.categoriaReactivo = :categoriaReactivo")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByIndRealizaRecepcion", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.indRealizaRecepcion = :indRealizaRecepcion")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByCdgTiporeportante", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.cdgTiporeportante = :cdgTiporeportante")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByEstadoReporte", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.estadoReporte = :estadoReporte")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByFechaGestionEnte", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.fechaGestionEnte = :fechaGestionEnte")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByGestionEnte", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.gestionEnte = :gestionEnte")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByEstadoGestionEnte", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.estadoGestionEnte = :estadoGestionEnte")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByFechaGestionInvima", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.fechaGestionInvima = :fechaGestionInvima")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByObservacionInvima", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.observacionInvima = :observacionInvima")
    , @NamedQuery(name = "ReactivoCamposcompTemp.findByCodigoFuncionario", query = "SELECT r FROM ReactivoCamposcompTemp r WHERE r.codigoFuncionario = :codigoFuncionario")})
public class ReactivoCamposcompTemp implements Serializable {

    private final static Logger logEJBReactivo = Logger.getLogger(ReactivoCamposcompTemp.class.getName());

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String reporte;
    @Size(max = 500)
    @Column(length = 500)
    private String organizacion;
    private Integer codigoCausa;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvioReactivo;
    @Size(max = 100)
    @Column(length = 100)
    private String expediente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200)
    private String codigoUnicoReactivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200)
    private String codigoTipoDiagnostico;
    @Size(max = 200)
    @Column(length = 200)
    private String referencia;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int categoriaReactivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(nullable = false, length = 2)
    private String indRealizaRecepcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cdg_tiporeportante", nullable = false)
    private int cdgTiporeportante;
    private Integer estadoReporte;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGestionEnte;
    @Size(max = 200)
    @Column(length = 200)
    private String gestionEnte;
    private Integer estadoGestionEnte;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGestionInvima;
    @Size(max = 200)
    @Column(length = 200)
    private String observacionInvima;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private long codigoFuncionario;

    public ReactivoCamposcompTemp() {
    }

    public ReactivoCamposcompTemp(String reporte) {
        this.reporte = reporte;
    }

    public ReactivoCamposcompTemp(String reporte, String codigoUnicoReactivo, String codigoTipoDiagnostico, int categoriaReactivo, String indRealizaRecepcion, int cdgTiporeportante, long codigoFuncionario) {
        this.reporte = reporte;
        this.codigoUnicoReactivo = codigoUnicoReactivo;
        this.codigoTipoDiagnostico = codigoTipoDiagnostico;
        this.categoriaReactivo = categoriaReactivo;
        this.indRealizaRecepcion = indRealizaRecepcion;
        this.cdgTiporeportante = cdgTiporeportante;
        this.codigoFuncionario = codigoFuncionario;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public Integer getCodigoCausa() {
        return codigoCausa;
    }

    public void setCodigoCausa(Integer codigoCausa) {
        this.codigoCausa = codigoCausa;
    }

    public Date getFechaEnvioReactivo() {
        return fechaEnvioReactivo;
    }

    public void setFechaEnvioReactivo(Date fechaEnvioReactivo) {
        this.fechaEnvioReactivo = fechaEnvioReactivo;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getCodigoUnicoReactivo() {
        return codigoUnicoReactivo;
    }

    public void setCodigoUnicoReactivo(String codigoUnicoReactivo) {
        this.codigoUnicoReactivo = codigoUnicoReactivo;
    }

    public String getCodigoTipoDiagnostico() {
        return codigoTipoDiagnostico;
    }

    public void setCodigoTipoDiagnostico(String codigoTipoDiagnostico) {
        this.codigoTipoDiagnostico = codigoTipoDiagnostico;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getCategoriaReactivo() {
        return categoriaReactivo;
    }

    public void setCategoriaReactivo(int categoriaReactivo) {
        this.categoriaReactivo = categoriaReactivo;
    }

    public String getIndRealizaRecepcion() {
        return indRealizaRecepcion;
    }

    public void setIndRealizaRecepcion(String indRealizaRecepcion) {
        this.indRealizaRecepcion = indRealizaRecepcion;
    }

    public int getCdgTiporeportante() {
        return cdgTiporeportante;
    }

    public void setCdgTiporeportante(int cdgTiporeportante) {
        this.cdgTiporeportante = cdgTiporeportante;
    }

    public Integer getEstadoReporte() {
        return estadoReporte;
    }

    public void setEstadoReporte(Integer estadoReporte) {
        this.estadoReporte = estadoReporte;
    }

    public Date getFechaGestionEnte() {
        return fechaGestionEnte;
    }

    public void setFechaGestionEnte(Date fechaGestionEnte) {
        this.fechaGestionEnte = fechaGestionEnte;
    }

    public String getGestionEnte() {
        return gestionEnte;
    }

    public void setGestionEnte(String gestionEnte) {
        this.gestionEnte = gestionEnte;
    }

    public Integer getEstadoGestionEnte() {
        return estadoGestionEnte;
    }

    public void setEstadoGestionEnte(Integer estadoGestionEnte) {
        this.estadoGestionEnte = estadoGestionEnte;
    }

    public Date getFechaGestionInvima() {
        return fechaGestionInvima;
    }

    public void setFechaGestionInvima(Date fechaGestionInvima) {
        this.fechaGestionInvima = fechaGestionInvima;
    }

    public String getObservacionInvima() {
        return observacionInvima;
    }

    public void setObservacionInvima(String observacionInvima) {
        this.observacionInvima = observacionInvima;
    }

    public long getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(long codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
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
        if (!(object instanceof ReactivoCamposcompTemp)) {
            return false;
        }
        ReactivoCamposcompTemp other = (ReactivoCamposcompTemp) object;
        if ((this.reporte == null && other.reporte != null) || (this.reporte != null && !this.reporte.equals(other.reporte))) {
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
    /*
    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoCamposcompTemp[ reporte=" + reporte + " ]";
    }
    */
}
