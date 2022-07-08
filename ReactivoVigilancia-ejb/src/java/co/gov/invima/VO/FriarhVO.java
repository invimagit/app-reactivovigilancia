/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.VO;

import co.gov.invima.entities.ReactivoFriarhPK;
import co.gov.invima.entities.ReactivoSeguimiento;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mgualdrond
 */
public class FriarhVO implements Serializable {
    
    private String friarh;
    
    private String cdgTipoalerta;
    
    private Date fechaIngreso= new Date();
    private String nombreReac;
    private String nroregsan;
    private String marca;
    private String modelo;
    private String lote;
    private String cdgRiesgo;
    private Short fuente;
    private String causas;
    private String medidasCorrectivas;
    private Short cdgNotificante;
    private String razonSocialNotificante;
    private Long nit;
    private String direccion;
    private String pais;
    private String codMun;
    private String codDepart;
    private String telefono;
    private Short cdgProfesion;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    private String email;
    private String nombreNotificante;
    private String otroNotificante;
    private Short estadoReac;
    private String reporteEventos;
    private Integer cuantosEventos;
    private String descripcionEvento;
    private String codDepart1;
    private String codMun1;
    private Date fechaHurto;
    private String descripcionHurto;
    private String fiscalia;
    private String autorizacion;
    private Character estadoReporte;
    private Character internet;
    private Long expedienteReac;
    private String nombreAgencia;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private Date fechaRadicado = new Date();   
    private Long radicado;
    private String problemaReac;
    private String otroEstadoreac;
    private List<Friarh02VO> hurtados;
    private List<Friarh01VO> trazabilidad;
    private String usuario;
    private ReactivoFriarhPK ReactivoFriarhPK;
    private boolean reporte_con_friah_pero_sin_crear = false;
    private ReactivoSeguimiento seguimiento = new ReactivoSeguimiento();

    public ReactivoSeguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(ReactivoSeguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }

    
    public boolean isReporte_con_friah_pero_sin_crear() {
        return reporte_con_friah_pero_sin_crear;
    }

    public void setReporte_con_friah_pero_sin_crear(boolean reporte_con_friah_pero_sin_crear) {
        this.reporte_con_friah_pero_sin_crear = reporte_con_friah_pero_sin_crear;
    }

    public ReactivoFriarhPK getReactivoFriarhPK() {
        return ReactivoFriarhPK;
    }

    public void setReactivoFriarhPK(ReactivoFriarhPK ReactivoFriarhPK) {
        this.ReactivoFriarhPK = ReactivoFriarhPK;
    }
    

    public String getFriarh() {
        return friarh;
    }

    public void setFriarh(String friarh) {
        this.friarh = friarh;
    }

    public String getCdgTipoalerta() {
        return cdgTipoalerta;
    }

    public void setCdgTipoalerta(String cdgTipoalerta) {
        this.cdgTipoalerta = cdgTipoalerta;
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
    
    public String getFechaRadicadoWithFormat() {
        return  this.fechaRadicado != null ? sdf.format(this.fechaRadicado):"";
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

    public List<Friarh02VO> getHurtados() {
        return hurtados;
    }

    public void setHurtados(List<Friarh02VO> hurtados) {
        this.hurtados = hurtados;
    }

    public List<Friarh01VO> getTrazabilidad() {
        return trazabilidad;
    }

    public void setTrazabilidad(List<Friarh01VO> trazabilidad) {
        this.trazabilidad = trazabilidad;
    }

    public String getOtroEstadoreac() {
        return otroEstadoreac;
    }

    public void setOtroEstadoreac(String otroEstadoreac) {
        this.otroEstadoreac = otroEstadoreac;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
}
