/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mgualdrond
 */
@Entity
@Table(name = "reactivo_friarh_02")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoFriarh02.findAll", query = "SELECT r FROM ReactivoFriarh02 r"),
    @NamedQuery(name = "ReactivoFriarh02.findById", query = "SELECT r FROM ReactivoFriarh02 r WHERE r.id = :id"),
    @NamedQuery(name = "ReactivoFriarh02.findByFRIARH", query = "SELECT r FROM ReactivoFriarh02 r WHERE r.reactivoFriarh.reactivoFriarhPK.friarh = :friarh"),
    @NamedQuery(name = "ReactivoFriarh02.findByNombreReactivo", query = "SELECT r FROM ReactivoFriarh02 r WHERE r.nombreReactivo = :nombreReactivo"),
    @NamedQuery(name = "ReactivoFriarh02.findByRegistroSanitario", query = "SELECT r FROM ReactivoFriarh02 r WHERE r.registroSanitario = :registroSanitario"),
    @NamedQuery(name = "ReactivoFriarh02.findByReferencia", query = "SELECT r FROM ReactivoFriarh02 r WHERE r.referencia = :referencia"),
    @NamedQuery(name = "ReactivoFriarh02.findByLote", query = "SELECT r FROM ReactivoFriarh02 r WHERE r.lote = :lote"),
    @NamedQuery(name = "ReactivoFriarh02.findByMarca", query = "SELECT r FROM ReactivoFriarh02 r WHERE r.marca = :marca"),
    @NamedQuery(name = "ReactivoFriarh02.findByCantidad", query = "SELECT r FROM ReactivoFriarh02 r WHERE r.cantidad = :cantidad")})
public class ReactivoFriarh02 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_reactivo")
    private String nombreReactivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "registro_sanitario")
    private String registroSanitario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "referencia")
    private String referencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "lote")
    private String lote;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private short cantidad;
    @JoinColumns({
        @JoinColumn(name = "friarh", referencedColumnName = "friarh"),
        @JoinColumn(name = "cdg_tipoalerta", referencedColumnName = "cdg_tipoalerta")})
    @ManyToOne(optional = false)
    private ReactivoFriarh reactivoFriarh;

    public ReactivoFriarh02() {
    }

    public ReactivoFriarh02(Integer id) {
        this.id = id;
    }

    public ReactivoFriarh02(Integer id, String nombreReactivo, String registroSanitario, String referencia, String lote, String marca, short cantidad) {
        this.id = id;
        this.nombreReactivo = nombreReactivo;
        this.registroSanitario = registroSanitario;
        this.referencia = referencia;
        this.lote = lote;
        this.marca = marca;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreReactivo() {
        return nombreReactivo;
    }

    public void setNombreReactivo(String nombreReactivo) {
        this.nombreReactivo = nombreReactivo;
    }

    public String getRegistroSanitario() {
        return registroSanitario;
    }

    public void setRegistroSanitario(String registroSanitario) {
        this.registroSanitario = registroSanitario;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }

    public ReactivoFriarh getReactivoFriarh() {
        return reactivoFriarh;
    }

    public void setReactivoFriarh(ReactivoFriarh reactivoFriarh) {
        this.reactivoFriarh = reactivoFriarh;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoFriarh02)) {
            return false;
        }
        ReactivoFriarh02 other = (ReactivoFriarh02) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoFriarh02[ id=" + id + " ]";
    }
    
}
