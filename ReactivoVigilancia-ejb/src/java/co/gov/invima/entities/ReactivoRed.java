/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mgualdrond
 */
@Entity
@Table(name = "reactivo_red")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoRed.findAll", query = "SELECT r FROM ReactivoRed r"),
    @NamedQuery(name = "ReactivoRed.findById", query = "SELECT r FROM ReactivoRed r WHERE r.id = :id"),
    @NamedQuery(name = "ReactivoRed.findByFechaSolicitud", query = "SELECT r FROM ReactivoRed r WHERE r.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "ReactivoRed.findByNombreInstitucion", query = "SELECT r FROM ReactivoRed r WHERE r.nombreInstitucion = :nombreInstitucion"),
    @NamedQuery(name = "ReactivoRed.findByCdgModalidad", query = "SELECT r FROM ReactivoRed r WHERE r.cdgModalidad = :cdgModalidad"),
    @NamedQuery(name = "ReactivoRed.findByNaturaleza", query = "SELECT r FROM ReactivoRed r WHERE r.naturaleza = :naturaleza"),
    @NamedQuery(name = "ReactivoRed.findByComplejidad", query = "SELECT r FROM ReactivoRed r WHERE r.complejidad = :complejidad"),
    @NamedQuery(name = "ReactivoRed.findByDireccionOrganizacion", query = "SELECT r FROM ReactivoRed r WHERE r.direccionOrganizacion = :direccionOrganizacion"),
    @NamedQuery(name = "ReactivoRed.findByNit", query = "SELECT r FROM ReactivoRed r WHERE r.nit = :nit"),
    @NamedQuery(name = "ReactivoRed.findByCodMun", query = "SELECT r FROM ReactivoRed r WHERE r.codMun = :codMun"),
    @NamedQuery(name = "ReactivoRed.findByCodDepart", query = "SELECT r FROM ReactivoRed r WHERE r.codDepart = :codDepart"),
    @NamedQuery(name = "ReactivoRed.findByEmailCorporativo", query = "SELECT r FROM ReactivoRed r WHERE r.emailCorporativo = :emailCorporativo"),
    @NamedQuery(name = "ReactivoRed.findByCdgFuncionario", query = "SELECT r FROM ReactivoRed r WHERE r.cdgFuncionario = :cdgFuncionario"),
    @NamedQuery(name = "ReactivoRed.findByTelefonoOrganiz", query = "SELECT r FROM ReactivoRed r WHERE r.telefonoOrganiz = :telefonoOrganiz"),
    @NamedQuery(name = "ReactivoRed.findByFaxOrganiz", query = "SELECT r FROM ReactivoRed r WHERE r.faxOrganiz = :faxOrganiz"),
    @NamedQuery(name = "ReactivoRed.findByPaisOrganiz", query = "SELECT r FROM ReactivoRed r WHERE r.paisOrganiz = :paisOrganiz"),
    @NamedQuery(name = "ReactivoRed.findByExtOrganiz", query = "SELECT r FROM ReactivoRed r WHERE r.extOrganiz = :extOrganiz")})
public class ReactivoRed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @Size(max = 128)
    @Column(name = "nombre_institucion")
    private String nombreInstitucion;
    @Column(name = "cdg_modalidad")
    private Integer cdgModalidad;
    @Size(max = 3)
    @Column(name = "naturaleza")
    private String naturaleza;
    @Size(max = 64)
    @Column(name = "complejidad")
    private String complejidad;
    @Size(max = 64)
    @Column(name = "direccion_organizacion")
    private String direccionOrganizacion;
    @Size(max = 11)
    @Column(name = "nit")
    private String nit;
    @Size(max = 5)
    @Column(name = "cod_mun")
    private String codMun;
    @Size(max = 2)
    @Column(name = "cod_depart")
    private String codDepart;
    @Size(max = 128)
    @Column(name = "email_corporativo")
    private String emailCorporativo;
    @Basic(optional = false)
    @Column(name = "cdg_funcionario")
    private int cdgFuncionario;
    @Size(max = 30)
    @Column(name = "telefono_organiz")
    private String telefonoOrganiz;
    @Size(max = 6)
    @Column(name = "ext_organiz")
    private String extOrganiz;
    @Size(max = 30)
    @Column(name = "fax_organiz")
    private String faxOrganiz;
    @Size(max = 2)
    @Column(name = "pais_organiz")
    private String paisOrganiz;
    @Size(max = 30)
    @Column(name = "otra_modalidad")
    private String otraModalidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRed")
    private List<ReactivoRedPersona> reactivoRedPersonaList;

    public ReactivoRed() {
    }

    public ReactivoRed(Integer id) {
        this.id = id;
    }

    public ReactivoRed(Integer id, int cdgFuncionario) {
        this.id = id;
        this.cdgFuncionario = cdgFuncionario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public Integer getCdgModalidad() {
        return cdgModalidad;
    }

    public void setCdgModalidad(Integer cdgModalidad) {
        this.cdgModalidad = cdgModalidad;
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

    public String getDireccionOrganizacion() {
        return direccionOrganizacion;
    }

    public void setDireccionOrganizacion(String direccionOrganizacion) {
        this.direccionOrganizacion = direccionOrganizacion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
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

    public String getEmailCorporativo() {
        return emailCorporativo;
    }

    public void setEmailCorporativo(String emailCorporativo) {
        this.emailCorporativo = emailCorporativo;
    }

    public int getCdgFuncionario() {
        return cdgFuncionario;
    }

    public void setCdgFuncionario(int cdgFuncionario) {
        this.cdgFuncionario = cdgFuncionario;
    }

    public String getTelefonoOrganiz() {
        return telefonoOrganiz;
    }

    public void setTelefonoOrganiz(String telefonoOrganiz) {
        this.telefonoOrganiz = telefonoOrganiz;
    }

    public String getFaxOrganiz() {
        return faxOrganiz;
    }

    public void setFaxOrganiz(String faxOrganiz) {
        this.faxOrganiz = faxOrganiz;
    }

    public String getPaisOrganiz() {
        return paisOrganiz;
    }

    public void setPaisOrganiz(String paisOrganiz) {
        this.paisOrganiz = paisOrganiz;
    }

    public String getExtOrganiz() {
        return extOrganiz;
    }

    public void setExtOrganiz(String extOrganiz) {
        this.extOrganiz = extOrganiz;
    }

    public String getOtraModalidad() {
        return otraModalidad;
    }

    public void setOtraModalidad(String otraModalidad) {
        this.otraModalidad = otraModalidad;
    }

    @XmlTransient
    public List<ReactivoRedPersona> getReactivoRedPersonaList() {
        return reactivoRedPersonaList;
    }

    public void setReactivoRedPersonaList(List<ReactivoRedPersona> reactivoRedPersonaList) {
        this.reactivoRedPersonaList = reactivoRedPersonaList;
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
        if (!(object instanceof ReactivoRed)) {
            return false;
        }
        ReactivoRed other = (ReactivoRed) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoRed[ id=" + id + " ]";
    }

}
