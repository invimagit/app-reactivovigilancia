/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.inject.Typed;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mgualdrond
 */
@Entity
@Table(name = "reactivo_friarh")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoFriarh.findAll", query = "SELECT r.reactivoFriarhPK FROM ReactivoFriarh r"),
    @NamedQuery(name = "ReactivoFriarh.findByFriarh", query = "SELECT r FROM ReactivoFriarh r WHERE r.reactivoFriarhPK.friarh = :friarh"),
    @NamedQuery(name = "ReactivoFriarh.findByCdgTipoalerta", query = "SELECT r FROM ReactivoFriarh r WHERE r.reactivoFriarhPK.cdgTipoalerta = :cdgTipoalerta"),
    @NamedQuery(name = "ReactivoFriarh.findByFechaIngreso", query = "SELECT r FROM ReactivoFriarh r WHERE r.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "ReactivoFriarh.findByNombreReac", query = "SELECT r FROM ReactivoFriarh r WHERE r.nombreReac = :nombreReac"),
    @NamedQuery(name = "ReactivoFriarh.findByNroregsan", query = "SELECT r FROM ReactivoFriarh r WHERE r.nroregsan = :nroregsan"),
    @NamedQuery(name = "ReactivoFriarh.findByMarca", query = "SELECT r FROM ReactivoFriarh r WHERE r.marca = :marca"),
    @NamedQuery(name = "ReactivoFriarh.findByModelo", query = "SELECT r FROM ReactivoFriarh r WHERE r.modelo = :modelo"),
    @NamedQuery(name = "ReactivoFriarh.findByLote", query = "SELECT r FROM ReactivoFriarh r WHERE r.lote = :lote"),
    @NamedQuery(name = "ReactivoFriarh.findByCdgRiesgo", query = "SELECT r FROM ReactivoFriarh r WHERE r.cdgRiesgo = :cdgRiesgo"),
    @NamedQuery(name = "ReactivoFriarh.findByFuente", query = "SELECT r FROM ReactivoFriarh r WHERE r.fuente = :fuente"),
    @NamedQuery(name = "ReactivoFriarh.findByCausas", query = "SELECT r FROM ReactivoFriarh r WHERE r.causas = :causas"),
    @NamedQuery(name = "ReactivoFriarh.findByMedidasCorrectivas", query = "SELECT r FROM ReactivoFriarh r WHERE r.medidasCorrectivas = :medidasCorrectivas"),
    @NamedQuery(name = "ReactivoFriarh.findByCdgNotificante", query = "SELECT r FROM ReactivoFriarh r WHERE r.cdgNotificante = :cdgNotificante"),
    @NamedQuery(name = "ReactivoFriarh.findByRazonSocialNotificante", query = "SELECT r FROM ReactivoFriarh r WHERE r.razonSocialNotificante = :razonSocialNotificante"),
    @NamedQuery(name = "ReactivoFriarh.findByNit", query = "SELECT r FROM ReactivoFriarh r WHERE r.nit = :nit"),
    @NamedQuery(name = "ReactivoFriarh.findByDireccion", query = "SELECT r FROM ReactivoFriarh r WHERE r.direccion = :direccion"),
    @NamedQuery(name = "ReactivoFriarh.findByPais", query = "SELECT r FROM ReactivoFriarh r WHERE r.pais = :pais"),
    @NamedQuery(name = "ReactivoFriarh.findByCodMun", query = "SELECT r FROM ReactivoFriarh r WHERE r.codMun = :codMun"),
    @NamedQuery(name = "ReactivoFriarh.findByCodDepart", query = "SELECT r FROM ReactivoFriarh r WHERE r.codDepart = :codDepart"),
    @NamedQuery(name = "ReactivoFriarh.findByTelefono", query = "SELECT r FROM ReactivoFriarh r WHERE r.telefono = :telefono"),
    @NamedQuery(name = "ReactivoFriarh.findByCdgProfesion", query = "SELECT r FROM ReactivoFriarh r WHERE r.cdgProfesion = :cdgProfesion"),
    @NamedQuery(name = "ReactivoFriarh.findByEmail", query = "SELECT r FROM ReactivoFriarh r WHERE r.email = :email"),
    @NamedQuery(name = "ReactivoFriarh.findByNombreNotificante", query = "SELECT r FROM ReactivoFriarh r WHERE r.nombreNotificante = :nombreNotificante"),
    @NamedQuery(name = "ReactivoFriarh.findByOtroNotificante", query = "SELECT r FROM ReactivoFriarh r WHERE r.otroNotificante = :otroNotificante"),
    @NamedQuery(name = "ReactivoFriarh.findByEstadoReac", query = "SELECT r FROM ReactivoFriarh r WHERE r.estadoReac = :estadoReac"),
    @NamedQuery(name = "ReactivoFriarh.findByReporteEventos", query = "SELECT r FROM ReactivoFriarh r WHERE r.reporteEventos = :reporteEventos"),
    @NamedQuery(name = "ReactivoFriarh.findByCuantosEventos", query = "SELECT r FROM ReactivoFriarh r WHERE r.cuantosEventos = :cuantosEventos"),
    @NamedQuery(name = "ReactivoFriarh.findByCodDepart1", query = "SELECT r FROM ReactivoFriarh r WHERE r.codDepart1 = :codDepart1"),
    @NamedQuery(name = "ReactivoFriarh.findByCodMun1", query = "SELECT r FROM ReactivoFriarh r WHERE r.codMun1 = :codMun1"),
    @NamedQuery(name = "ReactivoFriarh.findByFechaHurto", query = "SELECT r FROM ReactivoFriarh r WHERE r.fechaHurto = :fechaHurto"),
    @NamedQuery(name = "ReactivoFriarh.findByFiscalia", query = "SELECT r FROM ReactivoFriarh r WHERE r.fiscalia = :fiscalia"),
    @NamedQuery(name = "ReactivoFriarh.findByAutorizacion", query = "SELECT r FROM ReactivoFriarh r WHERE r.autorizacion = :autorizacion"),
    @NamedQuery(name = "ReactivoFriarh.findByEstadoReporte", query = "SELECT r FROM ReactivoFriarh r WHERE r.estadoReporte = :estadoReporte"),
    @NamedQuery(name = "ReactivoFriarh.findByInternet", query = "SELECT r FROM ReactivoFriarh r WHERE r.internet = :internet"),
    @NamedQuery(name = "ReactivoFriarh.findByExpedienteReac", query = "SELECT r FROM ReactivoFriarh r WHERE r.expedienteReac = :expedienteReac"),
    @NamedQuery(name = "ReactivoFriarh.findByNombreAgencia", query = "SELECT r FROM ReactivoFriarh r WHERE r.nombreAgencia = :nombreAgencia"),
    @NamedQuery(name = "ReactivoFriarh.findByFechaRadicado", query = "SELECT r FROM ReactivoFriarh r WHERE r.fechaRadicado = :fechaRadicado"),
    @NamedQuery(name = "ReactivoFriarh.findByRadicado", query = "SELECT r FROM ReactivoFriarh r WHERE r.radicado = :radicado")})
public class ReactivoFriarh implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReactivoFriarhPK reactivoFriarhPK;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Size(max = 255)
    @Column(name = "nombre_reac")
    private String nombreReac;
    @Size(max = 25)
    @Column(name = "nroregsan")
    private String nroregsan;
    @Size(max = 128)
    @Column(name = "marca")
    private String marca;
    @Size(max = 128)
    @Column(name = "modelo")
    private String modelo;
    @Size(max = 64)
    @Column(name = "lote")
    private String lote;
    @Size(max = 4)
    @Column(name = "cdg_riesgo")
    private String cdgRiesgo;
    @Column(name = "fuente")
    private Short fuente;
    @Size(max = 1024)
    @Column(name = "causas")
    private String causas;
    @Size(max = 255)
    @Column(name = "medidas_correctivas")
    private String medidasCorrectivas;
    @Column(name = "cdg_notificante")
    private Short cdgNotificante;
    @Size(max = 128)
    @Column(name = "razon_social_notificante")
    private String razonSocialNotificante;
    @Column(name = "nit")
    private Long nit;
    @Size(max = 128)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 2)
    @Column(name = "pais")
    private String pais;
    @Size(max = 5)
    @Column(name = "cod_mun")
    private String codMun;
    @Size(max = 2)
    @Column(name = "cod_depart")
    private String codDepart;
    @Size(max = 20)
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "cdg_profesion")
    private Short cdgProfesion;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 128)
    @Column(name = "email")
    private String email;
    @Size(max = 128)
    @Column(name = "nombre_notificante")
    private String nombreNotificante;
    @Size(max = 128)
    @Column(name = "otro_notificante")
    private String otroNotificante;
    @Column(name = "estado_reac")
    private Short estadoReac;
    @Size(max = 2)
    @Column(name = "reporte_eventos")
    private String reporteEventos;
    @Column(name = "cuantos_eventos")
    private Integer cuantosEventos;
    //
    @Column(name = "descripcion_evento")
    private String descripcionEvento;
    @Size(max = 2)
    @Column(name = "cod_depart1")
    private String codDepart1;
    @Size(max = 5)
    @Column(name = "cod_mun1")
    private String codMun1;
    @Column(name = "fecha_hurto")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHurto;
    //
    @Column(name = "descripcion_hurto")
    private String descripcionHurto;
    @Size(max = 2)
    @Column(name = "fiscalia")
    private String fiscalia;
    @Size(max = 2)
    @Column(name = "autorizacion")
    private String autorizacion;
    @Column(name = "estado_reporte")
    private Character estadoReporte;
    @Column(name = "internet")
    private Character internet;
    @Column(name = "expediente_reac")
    private Long expedienteReac;
    @Size(max = 128)
    @Column(name = "nombre_agencia")
    private String nombreAgencia;
    @Column(name = "fecha_radicado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRadicado;
    @Column(name = "radicado")
    private Long radicado;
    //
    @Column(name = "problema_reac")
    private String problemaReac;
    @Size(max = 128)
    @Column(name = "otro_estadoreac")
    private String otroEstadoreac;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reactivoFriarh")
    private List<ReactivoFriarh02> reactivoFriarh02List;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reactivoFriarh")
    private List<ReactivoFriarh01> reactivoFriarh01List;

    public ReactivoFriarh() {
    }

    public ReactivoFriarh(ReactivoFriarhPK reactivoFriarhPK) {
        this.reactivoFriarhPK = reactivoFriarhPK;
    }

    public ReactivoFriarh(String friarh, String cdgTipoalerta) {
        this.reactivoFriarhPK = new ReactivoFriarhPK(friarh, cdgTipoalerta);
    }

    public ReactivoFriarhPK getReactivoFriarhPK() {
        return reactivoFriarhPK;
    }

    public void setReactivoFriarhPK(ReactivoFriarhPK reactivoFriarhPK) {
        this.reactivoFriarhPK = reactivoFriarhPK;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getNombreReac() {
        return nombreReac;
    }

    public void setNombreReac(String nombreReac) {
        this.nombreReac = nombreReac;
    }

    public String getNroregsan() {
        return nroregsan;
    }

    public void setNroregsan(String nroregsan) {
        this.nroregsan = nroregsan;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getCdgRiesgo() {
        return cdgRiesgo;
    }

    public void setCdgRiesgo(String cdgRiesgo) {
        this.cdgRiesgo = cdgRiesgo;
    }

    public Short getFuente() {
        return fuente;
    }

    public void setFuente(Short fuente) {
        this.fuente = fuente;
    }

    public String getCausas() {
        return causas;
    }

    public void setCausas(String causas) {
        this.causas = causas;
    }

    public String getMedidasCorrectivas() {
        return medidasCorrectivas;
    }

    public void setMedidasCorrectivas(String medidasCorrectivas) {
        this.medidasCorrectivas = medidasCorrectivas;
    }

    public Short getCdgNotificante() {
        return cdgNotificante;
    }

    public void setCdgNotificante(Short cdgNotificante) {
        this.cdgNotificante = cdgNotificante;
    }

    public String getRazonSocialNotificante() {
        return razonSocialNotificante;
    }

    public void setRazonSocialNotificante(String razonSocialNotificante) {
        this.razonSocialNotificante = razonSocialNotificante;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
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

    public String getCodMun() {
        return codMun;
    }

    public void setCodMun(String codMun) {
        this.codMun = codMun;
    }

    public String getCodDepart() {
        return codDepart;
    }

    public void setCodDepart(String codDepart) {
        this.codDepart = codDepart;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Short getCdgProfesion() {
        return cdgProfesion;
    }

    public void setCdgProfesion(Short cdgProfesion) {
        this.cdgProfesion = cdgProfesion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreNotificante() {
        return nombreNotificante;
    }

    public void setNombreNotificante(String nombreNotificante) {
        this.nombreNotificante = nombreNotificante;
    }

    public String getOtroNotificante() {
        return otroNotificante;
    }

    public void setOtroNotificante(String otroNotificante) {
        this.otroNotificante = otroNotificante;
    }

    public Short getEstadoReac() {
        return estadoReac;
    }

    public void setEstadoReac(Short estadoReac) {
        this.estadoReac = estadoReac;
    }

    public String getReporteEventos() {
        return reporteEventos;
    }

    public void setReporteEventos(String reporteEventos) {
        this.reporteEventos = reporteEventos;
    }

    public Integer getCuantosEventos() {
        return cuantosEventos;
    }

    public void setCuantosEventos(Integer cuantosEventos) {
        this.cuantosEventos = cuantosEventos;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public String getCodDepart1() {
        return codDepart1;
    }

    public void setCodDepart1(String codDepart1) {
        this.codDepart1 = codDepart1;
    }

    public String getCodMun1() {
        return codMun1;
    }

    public void setCodMun1(String codMun1) {
        this.codMun1 = codMun1;
    }

    public Date getFechaHurto() {
        return fechaHurto;
    }

    public void setFechaHurto(Date fechaHurto) {
        this.fechaHurto = fechaHurto;
    }

    public String getDescripcionHurto() {
        return descripcionHurto;
    }

    public void setDescripcionHurto(String descripcionHurto) {
        this.descripcionHurto = descripcionHurto;
    }

    public String getFiscalia() {
        return fiscalia;
    }

    public void setFiscalia(String fiscalia) {
        this.fiscalia = fiscalia;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Character getEstadoReporte() {
        return estadoReporte;
    }

    public void setEstadoReporte(Character estadoReporte) {
        this.estadoReporte = estadoReporte;
    }

    public Character getInternet() {
        return internet;
    }

    public void setInternet(Character internet) {
        this.internet = internet;
    }

    public Long getExpedienteReac() {
        return expedienteReac;
    }

    public void setExpedienteReac(Long expedienteReac) {
        this.expedienteReac = expedienteReac;
    }

    public String getNombreAgencia() {
        return nombreAgencia;
    }

    public void setNombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
    }

    public Date getFechaRadicado() {
        return fechaRadicado;
    }

    public void setFechaRadicado(Date fechaRadicado) {
        this.fechaRadicado = fechaRadicado;
    }

    public Long getRadicado() {
        return radicado;
    }

    public void setRadicado(Long radicado) {
        this.radicado = radicado;
    }

    public String getProblemaReac() {
        return problemaReac;
    }

    public void setProblemaReac(String problemaReac) {
        this.problemaReac = problemaReac;
    }

    public String getOtroEstadoreac() {
        return otroEstadoreac;
    }

    public void setOtroEstadoreac(String otroEstadoreac) {
        this.otroEstadoreac = otroEstadoreac;
    }

    @XmlTransient
    public List<ReactivoFriarh02> getReactivoFriarh02List() {
        return reactivoFriarh02List;
    }

    public void setReactivoFriarh02List(List<ReactivoFriarh02> reactivoFriarh02List) {
        this.reactivoFriarh02List = reactivoFriarh02List;
    }

    @XmlTransient
    public List<ReactivoFriarh01> getReactivoFriarh01List() {
        return reactivoFriarh01List;
    }

    public void setReactivoFriarh01List(List<ReactivoFriarh01> reactivoFriarh01List) {
        this.reactivoFriarh01List = reactivoFriarh01List;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reactivoFriarhPK != null ? reactivoFriarhPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoFriarh)) {
            return false;
        }
        ReactivoFriarh other = (ReactivoFriarh) object;
        if ((this.reactivoFriarhPK == null && other.reactivoFriarhPK != null) || (this.reactivoFriarhPK != null && !this.reactivoFriarhPK.equals(other.reactivoFriarhPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoFriarh[ reactivoFriarhPK=" + reactivoFriarhPK + " ]";
    }
    
}
