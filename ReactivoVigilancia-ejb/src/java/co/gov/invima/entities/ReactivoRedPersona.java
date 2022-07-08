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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "reactivo_red_persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReactivoRedPersona.findAll", query = "SELECT r FROM ReactivoRedPersona r"),
    @NamedQuery(name = "ReactivoRedPersona.findById", query = "SELECT r FROM ReactivoRedPersona r WHERE r.id = :id"),
    @NamedQuery(name = "ReactivoRedPersona.findByFechaSolicitud", query = "SELECT r FROM ReactivoRedPersona r WHERE r.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "ReactivoRedPersona.findByNombreSolic", query = "SELECT r FROM ReactivoRedPersona r WHERE r.nombreSolic = :nombreSolic"),
    @NamedQuery(name = "ReactivoRedPersona.findByAreaEmpresa", query = "SELECT r FROM ReactivoRedPersona r WHERE r.areaEmpresa = :areaEmpresa"),
    @NamedQuery(name = "ReactivoRedPersona.findByCargos", query = "SELECT r FROM ReactivoRedPersona r WHERE r.cargos = :cargos"),
    @NamedQuery(name = "ReactivoRedPersona.findByProfesion", query = "SELECT r FROM ReactivoRedPersona r WHERE r.profesion = :profesion"),
    @NamedQuery(name = "ReactivoRedPersona.findByDireccionSolicitante", query = "SELECT r FROM ReactivoRedPersona r WHERE r.direccionSolicitante = :direccionSolicitante"),
    @NamedQuery(name = "ReactivoRedPersona.findByTelefonoSolic", query = "SELECT r FROM ReactivoRedPersona r WHERE r.telefonoSolic = :telefonoSolic"),
    @NamedQuery(name = "ReactivoRedPersona.findByEmailPersonal", query = "SELECT r FROM ReactivoRedPersona r WHERE r.emailPersonal = :emailPersonal"),
    @NamedQuery(name = "ReactivoRedPersona.findByPaisSolic", query = "SELECT r FROM ReactivoRedPersona r WHERE r.paisSolic = :paisSolic"),
    @NamedQuery(name = "ReactivoRedPersona.findByCelularSolic", query = "SELECT r FROM ReactivoRedPersona r WHERE r.celularSolic = :celularSolic"),
    @NamedQuery(name = "ReactivoRedPersona.findByCodMun", query = "SELECT r FROM ReactivoRedPersona r WHERE r.codMun = :codMun"),
    @NamedQuery(name = "ReactivoRedPersona.findByCodDepart", query = "SELECT r FROM ReactivoRedPersona r WHERE r.codDepart = :codDepart"),
    @NamedQuery(name = "ReactivoRedPersona.findByCdgFuncionario", query = "SELECT r FROM ReactivoRedPersona r WHERE r.cdgFuncionario = :cdgFuncionario"),
    @NamedQuery(name = "ReactivoRedPersona.findByCedulaSolicitante", query = "SELECT r FROM ReactivoRedPersona r WHERE r.cedulaSolicitante = :cedulaSolicitante")})
public class ReactivoRedPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @Size(max = 64)
    @Column(name = "nombre_solic")
    private String nombreSolic;
    @Size(max = 128)
    @Column(name = "area_empresa")
    private String areaEmpresa;
    @Size(max = 64)
    @Column(name = "cargos")
    private String cargos;
    @Size(max = 128)
    @Column(name = "profesion")
    private String profesion;
    @Size(max = 128)
    @Column(name = "direccion_solicitante")
    private String direccionSolicitante;
    @Size(max = 30)
    @Column(name = "telefono_solic")
    private String telefonoSolic;
    @Size(max = 128)
    @Column(name = "email_personal")
    private String emailPersonal;
    @Size(max = 2)
    @Column(name = "pais_solic")
    private String paisSolic;
    @Size(max = 25)
    @Column(name = "celular_solic")
    private String celularSolic;
    @Size(max = 5)
    @Column(name = "cod_mun")
    private String codMun;
    @Size(max = 2)
    @Column(name = "cod_depart")
    private String codDepart;
    @Basic(optional = false)
    @Column(name = "cdg_funcionario")
    private int cdgFuncionario;
    @Size(max = 11)
    @Column(name = "cedula_solicitante")
    private String cedulaSolicitante;
    @JoinColumn(name = "id_red", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ReactivoRed idRed;

    public ReactivoRedPersona() {
    }

    public ReactivoRedPersona(Integer id) {
        this.id = id;
    }

    public ReactivoRedPersona(Integer id, int cdgFuncionario) {
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

    public String getNombreSolic() {
        return nombreSolic;
    }

    public void setNombreSolic(String nombreSolic) {
        this.nombreSolic = nombreSolic;
    }

    public String getAreaEmpresa() {
        return areaEmpresa;
    }

    public void setAreaEmpresa(String areaEmpresa) {
        this.areaEmpresa = areaEmpresa;
    }

    public String getCargos() {
        return cargos;
    }

    public void setCargos(String cargos) {
        this.cargos = cargos;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getDireccionSolicitante() {
        return direccionSolicitante;
    }

    public void setDireccionSolicitante(String direccionSolicitante) {
        this.direccionSolicitante = direccionSolicitante;
    }

    public String getTelefonoSolic() {
        return telefonoSolic;
    }

    public void setTelefonoSolic(String telefonoSolic) {
        this.telefonoSolic = telefonoSolic;
    }

    public String getEmailPersonal() {
        return emailPersonal;
    }

    public void setEmailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal;
    }

    public String getPaisSolic() {
        return paisSolic;
    }

    public void setPaisSolic(String paisSolic) {
        this.paisSolic = paisSolic;
    }

    public String getCelularSolic() {
        return celularSolic;
    }

    public void setCelularSolic(String celularSolic) {
        this.celularSolic = celularSolic;
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

    public int getCdgFuncionario() {
        return cdgFuncionario;
    }

    public void setCdgFuncionario(int cdgFuncionario) {
        this.cdgFuncionario = cdgFuncionario;
    }

    public String getCedulaSolicitante() {
        return cedulaSolicitante;
    }

    public void setCedulaSolicitante(String cedulaSolicitante) {
        this.cedulaSolicitante = cedulaSolicitante;
    }

    public ReactivoRed getIdRed() {
        return idRed;
    }

    public void setIdRed(ReactivoRed idRed) {
        this.idRed = idRed;
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
        if (!(object instanceof ReactivoRedPersona)) {
            return false;
        }
        ReactivoRedPersona other = (ReactivoRedPersona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoRedPersona[ id=" + id + " ]";
    }
    
}
