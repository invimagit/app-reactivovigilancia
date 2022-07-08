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
@Table(name = "reactivo_institucion_temp", catalog = "SIVICOS", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoInstitucionTemp.findAll", query = "SELECT r FROM ReactivoInstitucionTemp r")
    , @NamedQuery(name = "ReactivoInstitucionTemp.findByReporte", query = "SELECT r FROM ReactivoInstitucionTemp r WHERE r.reporte = :reporte")
    , @NamedQuery(name = "ReactivoInstitucionTemp.findByInstitucion", query = "SELECT r FROM ReactivoInstitucionTemp r WHERE r.institucion = :institucion")
    , @NamedQuery(name = "ReactivoInstitucionTemp.findByIdentificacion", query = "SELECT r FROM ReactivoInstitucionTemp r WHERE r.identificacion = :identificacion")
    , @NamedQuery(name = "ReactivoInstitucionTemp.findByDireccion", query = "SELECT r FROM ReactivoInstitucionTemp r WHERE r.direccion = :direccion")
    , @NamedQuery(name = "ReactivoInstitucionTemp.findByNaturaleza", query = "SELECT r FROM ReactivoInstitucionTemp r WHERE r.naturaleza = :naturaleza")
    , @NamedQuery(name = "ReactivoInstitucionTemp.findByComplejidad", query = "SELECT r FROM ReactivoInstitucionTemp r WHERE r.complejidad = :complejidad")
    , @NamedQuery(name = "ReactivoInstitucionTemp.findByCodDepart", query = "SELECT r FROM ReactivoInstitucionTemp r WHERE r.codDepart = :codDepart")
    , @NamedQuery(name = "ReactivoInstitucionTemp.findByCodMun", query = "SELECT r FROM ReactivoInstitucionTemp r WHERE r.codMun = :codMun")
    , @NamedQuery(name = "ReactivoInstitucionTemp.findByEmail", query = "SELECT r FROM ReactivoInstitucionTemp r WHERE r.email = :email")
    , @NamedQuery(name = "ReactivoInstitucionTemp.findByFechaReporte", query = "SELECT r FROM ReactivoInstitucionTemp r WHERE r.fechaReporte = :fechaReporte")
    , @NamedQuery(name = "ReactivoInstitucionTemp.findByTelefono", query = "SELECT r FROM ReactivoInstitucionTemp r WHERE r.telefono = :telefono")})
public class ReactivoInstitucionTemp implements Serializable {

    private final static Logger logEJBReactivo = Logger.getLogger(ReactivoInstitucionTemp.class.getName());
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(nullable = false, length = 15)
    private String reporte;
    @Size(max = 128)
    @Column(length = 128)
    private String institucion;
    @Size(max = 11)
    @Column(length = 11)
    private String identificacion;
    @Size(max = 128)
    @Column(length = 128)
    private String direccion;
    @Size(max = 3)
    @Column(length = 3)
    private String naturaleza;
    @Size(max = 3)
    @Column(length = 3)
    private String complejidad;
    @Size(max = 2)
    @Column(name = "cod_depart", length = 2)
    private String codDepart;
    @Size(max = 5)
    @Column(name = "cod_mun", length = 5)
    private String codMun;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 64)
    @Column(length = 64)
    private String email;
    @Column(name = "fecha_reporte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReporte;
    @Size(max = 30)
    @Column(length = 30)
    private String telefono;

    public ReactivoInstitucionTemp() {
    }

    public ReactivoInstitucionTemp(String reporte) {
        this.reporte = reporte;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public String getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(String complejidad) {
        this.complejidad = complejidad;
    }

    public String getCodDepart() {
        return codDepart;
    }

    public void setCodDepart(String codDepart) {
        this.codDepart = codDepart;
    }

    public String getCodMun() {
        return codMun;
    }

    public void setCodMun(String codMun) {
        this.codMun = codMun;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        if (!(object instanceof ReactivoInstitucionTemp)) {
            return false;
        }
        ReactivoInstitucionTemp other = (ReactivoInstitucionTemp) object;
        if ((this.reporte == null && other.reporte != null) || (this.reporte != null && !this.reporte.equals(other.reporte))) {
            return false;
        }
        return true;
    }

    /*
    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoInstitucionTemp[ reporte=" + reporte + " ]";
    }
    */
    
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
    
    
}
