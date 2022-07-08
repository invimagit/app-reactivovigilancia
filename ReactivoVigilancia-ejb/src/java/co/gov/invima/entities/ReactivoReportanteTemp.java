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
@Table(name = "reactivo_reportante_temp", catalog = "SIVICOS", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoReportanteTemp.findAll", query = "SELECT r FROM ReactivoReportanteTemp r")
    , @NamedQuery(name = "ReactivoReportanteTemp.findByReporte", query = "SELECT r FROM ReactivoReportanteTemp r WHERE r.reporte = :reporte")
    , @NamedQuery(name = "ReactivoReportanteTemp.findByNombresApellidos", query = "SELECT r FROM ReactivoReportanteTemp r WHERE r.nombresApellidos = :nombresApellidos")
    , @NamedQuery(name = "ReactivoReportanteTemp.findByIdentificacion", query = "SELECT r FROM ReactivoReportanteTemp r WHERE r.identificacion = :identificacion")
    , @NamedQuery(name = "ReactivoReportanteTemp.findByCargo", query = "SELECT r FROM ReactivoReportanteTemp r WHERE r.cargo = :cargo")
    , @NamedQuery(name = "ReactivoReportanteTemp.findByProfesion", query = "SELECT r FROM ReactivoReportanteTemp r WHERE r.profesion = :profesion")
    , @NamedQuery(name = "ReactivoReportanteTemp.findByDireccion", query = "SELECT r FROM ReactivoReportanteTemp r WHERE r.direccion = :direccion")
    , @NamedQuery(name = "ReactivoReportanteTemp.findByPais", query = "SELECT r FROM ReactivoReportanteTemp r WHERE r.pais = :pais")
    , @NamedQuery(name = "ReactivoReportanteTemp.findByCodDepart", query = "SELECT r FROM ReactivoReportanteTemp r WHERE r.codDepart = :codDepart")
    , @NamedQuery(name = "ReactivoReportanteTemp.findByCodMun", query = "SELECT r FROM ReactivoReportanteTemp r WHERE r.codMun = :codMun")
    , @NamedQuery(name = "ReactivoReportanteTemp.findByTelefono", query = "SELECT r FROM ReactivoReportanteTemp r WHERE r.telefono = :telefono")
    , @NamedQuery(name = "ReactivoReportanteTemp.findByMovil", query = "SELECT r FROM ReactivoReportanteTemp r WHERE r.movil = :movil")
    , @NamedQuery(name = "ReactivoReportanteTemp.findByEmail", query = "SELECT r FROM ReactivoReportanteTemp r WHERE r.email = :email")
    , @NamedQuery(name = "ReactivoReportanteTemp.findByDivulgacion", query = "SELECT r FROM ReactivoReportanteTemp r WHERE r.divulgacion = :divulgacion")
    , @NamedQuery(name = "ReactivoReportanteTemp.findByFechaNotificacion", query = "SELECT r FROM ReactivoReportanteTemp r WHERE r.fechaNotificacion = :fechaNotificacion")
    , @NamedQuery(name = "ReactivoReportanteTemp.findByAreaPertenece", query = "SELECT r FROM ReactivoReportanteTemp r WHERE r.areaPertenece = :areaPertenece")})
public class ReactivoReportanteTemp implements Serializable {

    private final static Logger logEJBReactivo = Logger.getLogger(ReactivoReportanteTemp.class.getName());
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(nullable = false, length = 15)
    private String reporte;
    @Size(max = 128)
    @Column(name = "nombres_apellidos", length = 128)
    private String nombresApellidos;
    @Size(max = 11)
    @Column(length = 11)
    private String identificacion;
    @Size(max = 128)
    @Column(length = 128)
    private String cargo;
    @Size(max = 128)
    @Column(length = 128)
    private String profesion;
    @Size(max = 128)
    @Column(length = 128)
    private String direccion;
    @Size(max = 2)
    @Column(length = 2)
    private String pais;
    @Size(max = 2)
    @Column(name = "cod_depart", length = 2)
    private String codDepart;
    @Size(max = 5)
    @Column(name = "cod_mun", length = 5)
    private String codMun;
    @Size(max = 15)
    @Column(length = 15)
    private String telefono;
    @Size(max = 15)
    @Column(length = 15)
    private String movil;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 128)
    @Column(length = 128)
    private String email;
    @Size(max = 2)
    @Column(length = 2)
    private String divulgacion;
    @Column(name = "fecha_notificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNotificacion;
    @Size(max = 128)
    @Column(name = "area_pertenece", length = 128)
    private String areaPertenece;

    public ReactivoReportanteTemp() {
    }

    public ReactivoReportanteTemp(String reporte) {
        this.reporte = reporte;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public String getNombresApellidos() {
        return nombresApellidos;
    }

    public void setNombresApellidos(String nombresApellidos) {
        this.nombresApellidos = nombresApellidos;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDivulgacion() {
        return divulgacion;
    }

    public void setDivulgacion(String divulgacion) {
        this.divulgacion = divulgacion;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getAreaPertenece() {
        return areaPertenece;
    }

    public void setAreaPertenece(String areaPertenece) {
        this.areaPertenece = areaPertenece;
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
        if (!(object instanceof ReactivoReportanteTemp)) {
            return false;
        }
        ReactivoReportanteTemp other = (ReactivoReportanteTemp) object;
        if ((this.reporte == null && other.reporte != null) || (this.reporte != null && !this.reporte.equals(other.reporte))) {
            return false;
        }
        return true;
    }

    /*
    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoReportanteTemp[ reporte=" + reporte + " ]";
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
}
