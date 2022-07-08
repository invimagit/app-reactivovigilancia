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
@Table(name = "reactivo_producto_temp", catalog = "SIVICOS", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoProductoTemp.findAll", query = "SELECT r FROM ReactivoProductoTemp r")
    , @NamedQuery(name = "ReactivoProductoTemp.findByReporte", query = "SELECT r FROM ReactivoProductoTemp r WHERE r.reporte = :reporte")
    , @NamedQuery(name = "ReactivoProductoTemp.findByNombreReactivo", query = "SELECT r FROM ReactivoProductoTemp r WHERE r.nombreReactivo = :nombreReactivo")
    , @NamedQuery(name = "ReactivoProductoTemp.findByNroregsan", query = "SELECT r FROM ReactivoProductoTemp r WHERE r.nroregsan = :nroregsan")
    , @NamedQuery(name = "ReactivoProductoTemp.findByFechaVenci", query = "SELECT r FROM ReactivoProductoTemp r WHERE r.fechaVenci = :fechaVenci")
    , @NamedQuery(name = "ReactivoProductoTemp.findByLote", query = "SELECT r FROM ReactivoProductoTemp r WHERE r.lote = :lote")
    , @NamedQuery(name = "ReactivoProductoTemp.findByProcedencia", query = "SELECT r FROM ReactivoProductoTemp r WHERE r.procedencia = :procedencia")
    , @NamedQuery(name = "ReactivoProductoTemp.findByCadenaFrio", query = "SELECT r FROM ReactivoProductoTemp r WHERE r.cadenaFrio = :cadenaFrio")
    , @NamedQuery(name = "ReactivoProductoTemp.findByTemperatura", query = "SELECT r FROM ReactivoProductoTemp r WHERE r.temperatura = :temperatura")
    , @NamedQuery(name = "ReactivoProductoTemp.findByCertiAnalisis", query = "SELECT r FROM ReactivoProductoTemp r WHERE r.certiAnalisis = :certiAnalisis")
    , @NamedQuery(name = "ReactivoProductoTemp.findByDistriUsuario", query = "SELECT r FROM ReactivoProductoTemp r WHERE r.distriUsuario = :distriUsuario")
    , @NamedQuery(name = "ReactivoProductoTemp.findByCondiciAlmacen", query = "SELECT r FROM ReactivoProductoTemp r WHERE r.condiciAlmacen = :condiciAlmacen")
    , @NamedQuery(name = "ReactivoProductoTemp.findByAreaFunciona", query = "SELECT r FROM ReactivoProductoTemp r WHERE r.areaFunciona = :areaFunciona")
    , @NamedQuery(name = "ReactivoProductoTemp.findByOtraArea", query = "SELECT r FROM ReactivoProductoTemp r WHERE r.otraArea = :otraArea")})
public class ReactivoProductoTemp implements Serializable {

    private final static Logger logEJBReactivo = Logger.getLogger(ReactivoProductoTemp.class.getName());
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(nullable = false, length = 15)
    private String reporte;
    @Size(max = 255)
    @Column(name = "nombre_reactivo", length = 255)
    private String nombreReactivo;
    @Size(max = 25)
    @Column(length = 25)
    private String nroregsan;
    @Column(name = "fecha_venci")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVenci;
    @Size(max = 64)
    @Column(length = 64)
    private String lote;
    @Size(max = 15)
    @Column(length = 15)
    private String procedencia;
    @Size(max = 2)
    @Column(name = "cadena_frio", length = 2)
    private String cadenaFrio;
    @Size(max = 10)
    @Column(length = 10)
    private String temperatura;
    @Size(max = 2)
    @Column(name = "certi_analisis", length = 2)
    private String certiAnalisis;
    @Size(max = 255)
    @Column(name = "distri_usuario", length = 255)
    private String distriUsuario;
    @Size(max = 2)
    @Column(name = "condici_almacen", length = 2)
    private String condiciAlmacen;
    @Column(name = "area_funciona")
    private Integer areaFunciona;
    @Size(max = 128)
    @Column(name = "otra_area", length = 128)
    private String otraArea;

    public ReactivoProductoTemp() {
    }

    public ReactivoProductoTemp(String reporte) {
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
        if (!(object instanceof ReactivoProductoTemp)) {
            return false;
        }
        ReactivoProductoTemp other = (ReactivoProductoTemp) object;
        if ((this.reporte == null && other.reporte != null) || (this.reporte != null && !this.reporte.equals(other.reporte))) {
            return false;
        }
        return true;
    }

    
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

    /*
    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoProductoTemp[ reporte=" + reporte + " ]";
    }
    */
    
}
