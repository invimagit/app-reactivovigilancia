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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jonat
 */
@Entity
@Table(name = "reactivo_monitoreo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoMonitoreo.findAll", query = "SELECT r FROM ReactivoMonitoreo r")
    , @NamedQuery(name = "ReactivoMonitoreo.findByFriarh", query = "SELECT r FROM ReactivoMonitoreo r WHERE r.friarh = :friarh")
    , @NamedQuery(name = "ReactivoMonitoreo.findByFechaIngreso", query = "SELECT r FROM ReactivoMonitoreo r WHERE r.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "ReactivoMonitoreo.findByAgenciaSanitaria", query = "SELECT r FROM ReactivoMonitoreo r WHERE r.agenciaSanitaria = :agenciaSanitaria")
    , @NamedQuery(name = "ReactivoMonitoreo.findByAplica", query = "SELECT r FROM ReactivoMonitoreo r WHERE r.aplica = :aplica")
    , @NamedQuery(name = "ReactivoMonitoreo.findByTipo", query = "SELECT r FROM ReactivoMonitoreo r WHERE r.tipo = :tipo")
    , @NamedQuery(name = "ReactivoMonitoreo.findByFuncionarioMonitoreo", query = "SELECT r FROM ReactivoMonitoreo r WHERE r.funcionarioMonitoreo = :funcionarioMonitoreo")
    , @NamedQuery(name = "ReactivoMonitoreo.findByPaginaWeb", query = "SELECT r FROM ReactivoMonitoreo r WHERE r.paginaWeb = :paginaWeb")
    , @NamedQuery(name = "ReactivoMonitoreo.findByInternet", query = "SELECT r FROM ReactivoMonitoreo r WHERE r.internet = :internet")
    , @NamedQuery(name = "ReactivoMonitoreo.findByFuente", query = "SELECT r FROM ReactivoMonitoreo r WHERE r.fuente = :fuente")})
public class ReactivoMonitoreo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "friarh")
    private String friarh;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Size(max = 128)
    @Column(name = "agencia_sanitaria")
    private String agenciaSanitaria;
    @Basic(optional = false)
    @NotNull
    
    @Size(min = 1, max = 2147483647)
    @Column(name = "detalles")
    private String detalles;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "aplica")
    private String aplica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "funcionario_monitoreo")
    private short funcionarioMonitoreo;
    @Size(max = 1024)
    @Column(name = "pagina_web")
    private String paginaWeb;
    @Size(max = 1)
    @Column(name = "internet")
    private String internet;
    @Column(name = "fuente")
    private Integer fuente;
    
    @Transient
    public boolean registro_nuevo = true;

    public ReactivoMonitoreo() {
    }

    public ReactivoMonitoreo(String friarh) {
        this.friarh = friarh;
    }

    public ReactivoMonitoreo(String friarh, Date fechaIngreso, String detalles, String aplica, String tipo, short funcionarioMonitoreo) {
        this.friarh = friarh;
        this.fechaIngreso = fechaIngreso;
        this.detalles = detalles;
        this.aplica = aplica;
        this.tipo = tipo;
        this.funcionarioMonitoreo = funcionarioMonitoreo;
    }

    public String getFriarh() {
        return friarh;
    }

    public void setFriarh(String friarh) {
        this.friarh = friarh;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getAgenciaSanitaria() {
        return agenciaSanitaria;
    }

    public void setAgenciaSanitaria(String agenciaSanitaria) {
        this.agenciaSanitaria = agenciaSanitaria;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getAplica() {
        return aplica;
    }

    public void setAplica(String aplica) {
        this.aplica = aplica;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public short getFuncionarioMonitoreo() {
        return funcionarioMonitoreo;
    }

    public void setFuncionarioMonitoreo(short funcionarioMonitoreo) {
        this.funcionarioMonitoreo = funcionarioMonitoreo;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public Integer getFuente() {
        return fuente;
    }

    public void setFuente(Integer fuente) {
        this.fuente = fuente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (friarh != null ? friarh.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoMonitoreo)) {
            return false;
        }
        ReactivoMonitoreo other = (ReactivoMonitoreo) object;
        if ((this.friarh == null && other.friarh != null) || (this.friarh != null && !this.friarh.equals(other.friarh))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoMonitoreo[ friarh=" + friarh + " ]";
    }
    
}
