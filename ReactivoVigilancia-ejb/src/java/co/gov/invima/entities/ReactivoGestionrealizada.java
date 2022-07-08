/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.entities;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigInteger;
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
 * @author mgualdrond
 */
@Entity
@Table(name = "reactivo_gestionrealizada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoGestionrealizada.findAll", query = "SELECT r FROM ReactivoGestionrealizada r"),
    @NamedQuery(name = "ReactivoGestionrealizada.findByReporte", query = "SELECT r FROM ReactivoGestionrealizada r WHERE r.reporte = :reporte"),
    @NamedQuery(name = "ReactivoGestionrealizada.findByCausaProbable", query = "SELECT r FROM ReactivoGestionrealizada r WHERE r.causaProbable = :causaProbable"),
    @NamedQuery(name = "ReactivoGestionrealizada.findByCausaEfecto", query = "SELECT r FROM ReactivoGestionrealizada r WHERE r.causaEfecto = :causaEfecto"),
    @NamedQuery(name = "ReactivoGestionrealizada.findByImportador", query = "SELECT r FROM ReactivoGestionrealizada r WHERE r.importador = :importador"),
    @NamedQuery(name = "ReactivoGestionrealizada.findByFabricante", query = "SELECT r FROM ReactivoGestionrealizada r WHERE r.fabricante = :fabricante"),
    @NamedQuery(name = "ReactivoGestionrealizada.findByComercializador", query = "SELECT r FROM ReactivoGestionrealizada r WHERE r.comercializador = :comercializador"),
    @NamedQuery(name = "ReactivoGestionrealizada.findByDistribuidor", query = "SELECT r FROM ReactivoGestionrealizada r WHERE r.distribuidor = :distribuidor"),
    @NamedQuery(name = "ReactivoGestionrealizada.findByFechaNotificacion", query = "SELECT r FROM ReactivoGestionrealizada r WHERE r.fechaNotificacion = :fechaNotificacion"),
    @NamedQuery(name = "ReactivoGestionrealizada.findByEnviadoDistriImport", query = "SELECT r FROM ReactivoGestionrealizada r WHERE r.enviadoDistriImport = :enviadoDistriImport"),
    @NamedQuery(name = "ReactivoGestionrealizada.findByGestionRiesgo", query = "SELECT r FROM ReactivoGestionrealizada r WHERE r.gestionRiesgo = :gestionRiesgo"),
    @NamedQuery(name = "ReactivoGestionrealizada.findByAnalisisEfecto", query = "SELECT r FROM ReactivoGestionrealizada r WHERE r.analisisEfecto = :analisisEfecto"),
    @NamedQuery(name = "ReactivoGestionrealizada.findByHerramientaAnalisis", query = "SELECT r FROM ReactivoGestionrealizada r WHERE r.herramientaAnalisis = :herramientaAnalisis"),
    @NamedQuery(name = "ReactivoGestionrealizada.findByOtraHerramienta", query = "SELECT r FROM ReactivoGestionrealizada r WHERE r.otraHerramienta = :otraHerramienta"),
    @NamedQuery(name = "ReactivoGestionrealizada.findByDescripcionCausa", query = "SELECT r FROM ReactivoGestionrealizada r WHERE r.descripcionCausa = :descripcionCausa"),
    @NamedQuery(name = "ReactivoGestionrealizada.findByAcciones", query = "SELECT r FROM ReactivoGestionrealizada r WHERE r.acciones = :acciones"),
    @NamedQuery(name = "ReactivoGestionrealizada.findByDescripcionAcciones", query = "SELECT r FROM ReactivoGestionrealizada r WHERE r.descripcionAcciones = :descripcionAcciones")})
public class ReactivoGestionrealizada implements Serializable {
    
    private final static Logger logEJBReactivo = Logger.getLogger(ReactivoGestionrealizada.class.getName());    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "reporte")
    private String reporte;
    @Size(max = 8096)
    @Column(name = "causa_probable")
    private String causaProbable;
    @Size(max = 2)
    @Column(name = "causa_efecto")
    private String causaEfecto;
    @Size(max = 100)
    @Column(name = "importador")
    private String importador;
    @Size(max = 100)
    @Column(name = "fabricante")
    private String fabricante;
    @Size(max = 100)
    @Column(name = "comercializador")
    private String comercializador;
    @Size(max = 100)
    @Column(name = "distribuidor")
    private String distribuidor;
    @Column(name = "fecha_notificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNotificacion;
    @Size(max = 2)
    @Column(name = "enviado_distri_import")
    private String enviadoDistriImport;
    @Size(max = 2)
    @Column(name = "gestion_riesgo")
    private String gestionRiesgo;
    @Size(max = 2)
    @Column(name = "analisis_efecto")
    private String analisisEfecto;
    @Size(max = 30)
    @Column(name = "herramienta_analisis")
    private String herramientaAnalisis;
    @Size(max = 255)
    @Column(name = "otra_herramienta")
    private String otraHerramienta;
    @Size(max = 8096)
    @Column(name = "descripcion_causa")
    private String descripcionCausa;
    @Size(max = 2)
    @Column(name = "acciones")
    private String acciones;
    @Size(max = 2048)
    @Column(name = "descripcion_acciones")
    private String descripcionAcciones;
    
    
    @Column(name = "cdgfuncionario")
    private BigInteger cdgfuncionario;
    

    public ReactivoGestionrealizada() {
    }

    public ReactivoGestionrealizada(String reporte) {
        this.reporte = reporte;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public String getCausaProbable() {
        return causaProbable;
    }

    public void setCausaProbable(String causaProbable) {
        this.causaProbable = causaProbable;
    }

    public String getCausaEfecto() {
        return causaEfecto;
    }

    public void setCausaEfecto(String causaEfecto) {
        if(causaEfecto.toUpperCase().equals("SI"))
        {
            this.causaEfecto = "1";
        }
        else
        {
            this.causaEfecto = "0";
        }
    }

    public String getImportador() {
        return importador;
    }

    public void setImportador(String importador) {
        this.importador = importador;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getComercializador() {
        return comercializador;
    }

    public void setComercializador(String comercializador) {
        this.comercializador = comercializador;
    }

    public String getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getEnviadoDistriImport() {
        return enviadoDistriImport;
    }

    public void setEnviadoDistriImport(String enviadoDistriImport) {
        this.enviadoDistriImport = enviadoDistriImport;
    }

    public String getGestionRiesgo() {
        return gestionRiesgo;
    }

    public void setGestionRiesgo(String gestionRiesgo) {
        this.gestionRiesgo = gestionRiesgo;
    }

    public String getAnalisisEfecto() {
        return analisisEfecto;
    }

    public void setAnalisisEfecto(String analisisEfecto) {
        this.analisisEfecto = analisisEfecto;
    }

    public String getHerramientaAnalisis() {
        return herramientaAnalisis;
    }

    public void setHerramientaAnalisis(String herramientaAnalisis) {
        this.herramientaAnalisis = herramientaAnalisis;
    }

    public String getOtraHerramienta() {
        return otraHerramienta;
    }

    public void setOtraHerramienta(String otraHerramienta) {
        this.otraHerramienta = otraHerramienta;
    }

    public String getDescripcionCausa() {
        return descripcionCausa;
    }

    public void setDescripcionCausa(String descripcionCausa) {
        this.descripcionCausa = descripcionCausa;
    }

    public String getAcciones() {
        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    public String getDescripcionAcciones() {
        return descripcionAcciones;
    }

    public void setDescripcionAcciones(String descripcionAcciones) {
        this.descripcionAcciones = descripcionAcciones;
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
        if (!(object instanceof ReactivoGestionrealizada)) {
            return false;
        }
        ReactivoGestionrealizada other = (ReactivoGestionrealizada) object;
        if ((this.reporte == null && other.reporte != null) || (this.reporte != null && !this.reporte.equals(other.reporte))) {
            return false;
        }
        return true;
    }

    
    /*
    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoGestionrealizada[ reporte=" + reporte + " ]";
    }
    */
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
     * @return the cdgfuncionario
     */
    public BigInteger getCdgfuncionario() {
        return cdgfuncionario;
    }

    /**
     * @param cdgfuncionario the cdgfuncionario to set
     */
    public void setCdgfuncionario(BigInteger cdgfuncionario) {
        this.cdgfuncionario = cdgfuncionario;
    }
}
