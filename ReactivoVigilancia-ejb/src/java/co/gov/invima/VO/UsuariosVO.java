/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.VO;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author mgualdrond
 */
public class UsuariosVO implements Serializable {
    public UsuariosVO(){
    
    }
    
    public UsuariosVO(UsuariosVO x){
      identificacionEmpresa = x.identificacionEmpresa;
      tipidentificacionEmpresa = x.tipidentificacionEmpresa;
      nombreEmpresa = x.nombreEmpresa;
      direccionEmpresa = x.direccionEmpresa;
      cdgPais = x.cdgPais;
      codDepart = x.codDepart;
      codMun = x.codMun;
      telefonoEmpresa = x.telefonoEmpresa;
      emailEmpresa = x.emailEmpresa;
      fax = x.fax;
      url = x.url;
      identificacionPersona = x.identificacionPersona;
      tipidentificacionPersona = x.tipidentificacionPersona;
      nombrePersona = x.nombrePersona;
      cargoPersona = x.cargoPersona;
      telefonoPersona = x.telefonoPersona;
      emailPersona = x.emailPersona;
      usuario = x.usuario;
      activo = x.activo;
      iDRolUsuario = x.iDRolUsuario;
      idRol = x.idRol;
      pregunta = x.pregunta;
      respuesta = x.respuesta;
      fechaIngreso = x.fechaIngreso;
      password = x.password;
      session = x.session;
      estadoUsuario = x.estadoUsuario;
      clasificacionUsuario = x.clasificacionUsuario;
      estadoPalabra = x.estadoPalabra;
      rolPalabra = x.rolPalabra;
      nombrePais = x.nombrePais;
      nombreDpto = x.nombreDpto;
      nombreMpio = x.nombreMpio;
        
    }
    private long identificacionEmpresa;
    
    private String tipidentificacionEmpresa;
    
    private String nombreEmpresa;
    
    private String direccionEmpresa;
    
    private String cdgPais;
    
    private String codDepart;
    
    private String codMun;
    
    private String telefonoEmpresa;
    
    private String emailEmpresa;
    
    private String fax;
    
    private String url;
    
    private long identificacionPersona;
    
    private String tipidentificacionPersona;
    
    private String nombrePersona;
    
    private String cargoPersona;
    
    private String telefonoPersona;
    
    private String emailPersona;
    
    private String usuario;
    
    private short activo;
    
    private int iDRolUsuario;
    
    private String idRol;
    
    private String pregunta;
    
    private String respuesta;
    
    private Date fechaIngreso;

    private String password;

    private String session;

    private Character estadoUsuario;

    private String clasificacionUsuario;
    
    private String estadoPalabra;
    
    private String rolPalabra;
    
    private String nombrePais;
    
    private String nombreDpto;
    
    private String nombreMpio;

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

    public int getiDRolUsuario() {
        return iDRolUsuario;
    }

    public void setiDRolUsuario(int iDRolUsuario) {
        this.iDRolUsuario = iDRolUsuario;
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

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public String getEstadoPalabra() {
        return estadoPalabra;
    }

    public void setEstadoPalabra(String estadoPalabra) {
        this.estadoPalabra = estadoPalabra;
    }

    public String getRolPalabra() {
        return rolPalabra;
    }

    public void setRolPalabra(String rolPalabra) {
        this.rolPalabra = rolPalabra;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getNombreDpto() {
        return nombreDpto;
    }

    public void setNombreDpto(String nombreDpto) {
        this.nombreDpto = nombreDpto;
    }

    public String getNombreMpio() {
        return nombreMpio;
    }

    public void setNombreMpio(String nombreMpio) {
        this.nombreMpio = nombreMpio;
    }
    
    public String getInfo() {
        String info = "";
        try {
            info += "USUARIO: " + usuario + "\n";
            info += "     identificacionEmpresa: " + identificacionEmpresa + "\n";
            info += "     nombreEmpresa: " + nombreEmpresa + "\n";
            info += "     emailEmpresa: " + emailEmpresa + "\n";
            info += "     identificacionPersona: " + identificacionPersona + "\n";
            info += "     nombrePersona: " + nombrePersona + "\n";
            info += "     emailPersona: " + emailPersona + "\n";
            info += "     iDRolUsuario: " + iDRolUsuario + "\n";
            info += "     idRol: " + idRol + "\n";
            info += "     clasificacionUsuario: " + clasificacionUsuario + "\n";
            info += "     rolPalabra: " + rolPalabra + "\n";

        } catch (Exception e) {
            info += "ERROR OBTENIENDO INFORMACION DEL USUARIO DE REACTIVOVIGILANCIA" + "\n";
            info += e.getMessage() + "\n";
        }
        return info;
    }
    
}
