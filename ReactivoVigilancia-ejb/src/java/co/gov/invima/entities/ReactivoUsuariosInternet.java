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
 * @author mgualdrond
 */
@Entity
@Table(name = "reactivo_usuarios_internet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoUsuariosInternet.findAll", query = "SELECT r FROM ReactivoUsuariosInternet r"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByIdentificacionEmpresa", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.identificacionEmpresa = :identificacionEmpresa"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByTipidentificacionEmpresa", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.tipidentificacionEmpresa = :tipidentificacionEmpresa"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByNombreEmpresa", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.nombreEmpresa = :nombreEmpresa"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByDireccionEmpresa", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.direccionEmpresa = :direccionEmpresa"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByCdgPais", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.cdgPais = :cdgPais"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByCodDepart", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.codDepart = :codDepart"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByCodMun", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.codMun = :codMun"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByTelefonoEmpresa", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.telefonoEmpresa = :telefonoEmpresa"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByEmailEmpresa", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.emailEmpresa = :emailEmpresa"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByFax", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.fax = :fax"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByUrl", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.url = :url"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByIdentificacionPersona", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.identificacionPersona = :identificacionPersona"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByTipidentificacionPersona", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.tipidentificacionPersona = :tipidentificacionPersona"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByNombrePersona", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.nombrePersona = :nombrePersona"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByCargoPersona", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.cargoPersona = :cargoPersona"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByTelefonoPersona", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.telefonoPersona = :telefonoPersona"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByEmailPersona", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.emailPersona = :emailPersona"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByUsuario", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE UPPER(r.usuario) = UPPER(:usuario)"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByActivo", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.activo = :activo"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByIDRolUsuario", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.iDRolUsuario = :iDRolUsuario"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByPregunta", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.pregunta = :pregunta"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByRespuesta", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.respuesta = :respuesta"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByFechaIngreso", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByPassword", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.password = :password"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findBySession", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.session = :session"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByEstadoUsuario", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.estadoUsuario = :estadoUsuario"),
    @NamedQuery(name = "ReactivoUsuariosInternet.findByClasificacionUsuario", query = "SELECT r FROM ReactivoUsuariosInternet r WHERE r.clasificacionUsuario = :clasificacionUsuario")})
public class ReactivoUsuariosInternet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "identificacion_empresa")
    private long identificacionEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "tipidentificacion_empresa")
    private String tipidentificacionEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "direccion_empresa")
    private String direccionEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "cdg_pais")
    private String cdgPais;
    @Size(max = 2)
    @Column(name = "cod_depart")
    private String codDepart;
    @Size(max = 5)
    @Column(name = "cod_mun")
    private String codMun;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "telefono_empresa")
    private String telefonoEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email_empresa")
    private String emailEmpresa;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "fax")
    private String fax;
    @Size(max = 100)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "identificacion_persona")
    private long identificacionPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "tipidentificacion_persona")
    private String tipidentificacionPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre_persona")
    private String nombrePersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "cargo_persona")
    private String cargoPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "telefono_persona")
    private String telefonoPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email_persona")
    private String emailPersona;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private short activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_Rol_Usuario")
    private int iDRolUsuario;
    @Size(max = 100)
    @Column(name = "pregunta")
    private String pregunta;
    @Size(max = 100)
    @Column(name = "respuesta")
    private String respuesta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password")
    private String password;
    @Size(max = 64)
    @Column(name = "session")
    private String session;
    @Column(name = "estado_usuario")
    private Character estadoUsuario;
    @Size(max = 31)
    @Column(name = "clasificacion_usuario")
    private String clasificacionUsuario;

    public ReactivoUsuariosInternet() {
    }

    public ReactivoUsuariosInternet(String usuario) {
        this.usuario = usuario;
    }

    public ReactivoUsuariosInternet(String usuario, long identificacionEmpresa, String tipidentificacionEmpresa, String nombreEmpresa, String direccionEmpresa, String cdgPais, String telefonoEmpresa, String emailEmpresa, String fax, long identificacionPersona, String tipidentificacionPersona, String nombrePersona, String cargoPersona, String telefonoPersona, String emailPersona, short activo, int iDRolUsuario, Date fechaIngreso, String password) {
        this.usuario = usuario;
        this.identificacionEmpresa = identificacionEmpresa;
        this.tipidentificacionEmpresa = tipidentificacionEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.direccionEmpresa = direccionEmpresa;
        this.cdgPais = cdgPais;
        this.telefonoEmpresa = telefonoEmpresa;
        this.emailEmpresa = emailEmpresa;
        this.fax = fax;
        this.identificacionPersona = identificacionPersona;
        this.tipidentificacionPersona = tipidentificacionPersona;
        this.nombrePersona = nombrePersona;
        this.cargoPersona = cargoPersona;
        this.telefonoPersona = telefonoPersona;
        this.emailPersona = emailPersona;
        this.activo = activo;
        this.iDRolUsuario = iDRolUsuario;
        this.fechaIngreso = fechaIngreso;
        this.password = password;
    }

    public long getIdentificacionEmpresa() {
        return identificacionEmpresa;
    }

    public void setIdentificacionEmpresa(long identificacionEmpresa) {
        this.identificacionEmpresa = identificacionEmpresa;
    }

    public String getTipidentificacionEmpresa() {
        return tipidentificacionEmpresa;
    }

    public void setTipidentificacionEmpresa(String tipidentificacionEmpresa) {
        this.tipidentificacionEmpresa = tipidentificacionEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getCdgPais() {
        return cdgPais;
    }

    public void setCdgPais(String cdgPais) {
        this.cdgPais = cdgPais;
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

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getIdentificacionPersona() {
        return identificacionPersona;
    }

    public void setIdentificacionPersona(long identificacionPersona) {
        this.identificacionPersona = identificacionPersona;
    }

    public String getTipidentificacionPersona() {
        return tipidentificacionPersona;
    }

    public void setTipidentificacionPersona(String tipidentificacionPersona) {
        this.tipidentificacionPersona = tipidentificacionPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getCargoPersona() {
        return cargoPersona;
    }

    public void setCargoPersona(String cargoPersona) {
        this.cargoPersona = cargoPersona;
    }

    public String getTelefonoPersona() {
        return telefonoPersona;
    }

    public void setTelefonoPersona(String telefonoPersona) {
        this.telefonoPersona = telefonoPersona;
    }

    public String getEmailPersona() {
        return emailPersona;
    }

    public void setEmailPersona(String emailPersona) {
        this.emailPersona = emailPersona;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    public int getIDRolUsuario() {
        return getiDRolUsuario();
    }

    public void setIDRolUsuario(int iDRolUsuario) {
        this.setiDRolUsuario(iDRolUsuario);
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Character getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(Character estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getClasificacionUsuario() {
        return clasificacionUsuario;
    }

    public void setClasificacionUsuario(String clasificacionUsuario) {
        this.clasificacionUsuario = clasificacionUsuario;
    }
    
    /**
     * @return the iDRolUsuario
     */
    public int getiDRolUsuario() {
        return iDRolUsuario;
    }

    /**
     * @param iDRolUsuario the iDRolUsuario to set
     */
    public void setiDRolUsuario(int iDRolUsuario) {
        this.iDRolUsuario = iDRolUsuario;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuario != null ? usuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoUsuariosInternet)) {
            return false;
        }
        ReactivoUsuariosInternet other = (ReactivoUsuariosInternet) object;
        if ((this.usuario == null && other.usuario != null) || (this.usuario != null && !this.usuario.equals(other.usuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoUsuariosInternet[ usuario=" + usuario + " ]";
    }

    
}
