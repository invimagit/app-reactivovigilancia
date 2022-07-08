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
@Table(name = "reactivo_friarh_01")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoFriarh01.findAll", query = "SELECT r FROM ReactivoFriarh01 r"),
    @NamedQuery(name = "ReactivoFriarh01.findById", query = "SELECT r FROM ReactivoFriarh01 r WHERE r.id = :id"),
    @NamedQuery(name = "ReactivoFriarh01.findByFRIARH", query = "SELECT r FROM ReactivoFriarh01 r WHERE r.reactivoFriarh.reactivoFriarhPK.friarh = :friarh"),
    @NamedQuery(name = "ReactivoFriarh01.findByNombreCliente", query = "SELECT r FROM ReactivoFriarh01 r WHERE r.nombreCliente = :nombreCliente"),
    @NamedQuery(name = "ReactivoFriarh01.findByDireccion", query = "SELECT r FROM ReactivoFriarh01 r WHERE r.direccion = :direccion"),
    @NamedQuery(name = "ReactivoFriarh01.findByCdgMunicipio", query = "SELECT r FROM ReactivoFriarh01 r WHERE r.cdgMunicipio = :cdgMunicipio"),
    @NamedQuery(name = "ReactivoFriarh01.findByTelefono", query = "SELECT r FROM ReactivoFriarh01 r WHERE r.telefono = :telefono"),
    @NamedQuery(name = "ReactivoFriarh01.findByNombreContacto", query = "SELECT r FROM ReactivoFriarh01 r WHERE r.nombreContacto = :nombreContacto"),
    @NamedQuery(name = "ReactivoFriarh01.findByNombreReactivo", query = "SELECT r FROM ReactivoFriarh01 r WHERE r.nombreReactivo = :nombreReactivo"),
    @NamedQuery(name = "ReactivoFriarh01.findByRegistroSanitario", query = "SELECT r FROM ReactivoFriarh01 r WHERE r.registroSanitario = :registroSanitario"),
    @NamedQuery(name = "ReactivoFriarh01.findByReferencia", query = "SELECT r FROM ReactivoFriarh01 r WHERE r.referencia = :referencia"),
    @NamedQuery(name = "ReactivoFriarh01.findByLote", query = "SELECT r FROM ReactivoFriarh01 r WHERE r.lote = :lote"),
    @NamedQuery(name = "ReactivoFriarh01.findByMarca", query = "SELECT r FROM ReactivoFriarh01 r WHERE r.marca = :marca"),
    @NamedQuery(name = "ReactivoFriarh01.findByCantidad", query = "SELECT r FROM ReactivoFriarh01 r WHERE r.cantidad = :cantidad")})
public class ReactivoFriarh01 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cdg_municipio")
    private int cdgMunicipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_contacto")
    private String nombreContacto;
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

    public ReactivoFriarh01() {
    }

    public ReactivoFriarh01(Integer id) {
        this.id = id;
    }

    public ReactivoFriarh01(Integer id, String nombreCliente, String direccion, int cdgMunicipio, String telefono, String nombreContacto, String nombreReactivo, String registroSanitario, String referencia, String lote, String marca, short cantidad) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.cdgMunicipio = cdgMunicipio;
        this.telefono = telefono;
        this.nombreContacto = nombreContacto;
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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCdgMunicipio() {
        return cdgMunicipio;
    }

    public void setCdgMunicipio(int cdgMunicipio) {
        this.cdgMunicipio = cdgMunicipio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
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
        if (!(object instanceof ReactivoFriarh01)) {
            return false;
        }
        ReactivoFriarh01 other = (ReactivoFriarh01) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoFriarh01[ id=" + id + " ]";
    }
    
}
