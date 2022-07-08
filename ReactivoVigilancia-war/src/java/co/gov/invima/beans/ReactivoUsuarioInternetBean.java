/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.beans;

import co.gov.invima.BO.ReactivoBO;
import co.gov.invima.VO.CiudadesVO;
import co.gov.invima.VO.DepartamentoVO;
import co.gov.invima.VO.PaisesVO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author mgualdrond
 */
@Named(value = "reactivoUsuarioInternetBean")
@ViewScoped
public class ReactivoUsuarioInternetBean implements Serializable{

    private final ReactivoBO bO;
    private final Logger log = Logger.getLogger(ReactivoUsuarioInternetBean.class.getName());
    private String codigoPais;
    private String codigoDpto;
    private String codigoMpio;
    private String direccionOrganizacion;
    private List<DepartamentoVO> departamentoVOs;
    private List<SelectItem> lstPais;
    private List<SelectItem> lstDpto;
    private List<SelectItem> lstCiudad;
    private boolean activaDptoMpioOrga;
    private String telefonoOrg;
    private String cedula;
    private String nombreApellidos;
    private String telefonoPersonal;
    private String correoPersonal;
    private String nit;
    private String razonSocial;
    private String correoCorp;
    private String cargoDesemp;
    private String usuario;
    private String clave;
    private String pregunta;
    private String respuesta;
    
    /**
     * Creates a new instance of ReactivoUsuarioInternet
     */
    public ReactivoUsuarioInternetBean() {
        bO=new ReactivoBO();
        lstPais = this.obtenerPais();
        activaDptoMpioOrga=false;
        departamentoVOs = bO.obtenerDptos();
        lstDpto=this.obtenerDepartamento();
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public List<SelectItem> getLstPais() {
        return lstPais;
    }

    public void setLstPais(List<SelectItem> lstPais) {
        this.lstPais = lstPais;
    }

    public boolean isActivaDptoMpioOrga() {
        return activaDptoMpioOrga;
    }

    public void setActivaDptoMpioOrga(boolean activaDptoMpioOrga) {
        this.activaDptoMpioOrga = activaDptoMpioOrga;
    }

    public String getCodigoDpto() {
        return codigoDpto;
    }

    public void setCodigoDpto(String codigoDpto) {
        this.codigoDpto = codigoDpto;
    }

    public String getCodigoMpio() {
        return codigoMpio;
    }

    public void setCodigoMpio(String codigoMpio) {
        this.codigoMpio = codigoMpio;
    }

    public List<SelectItem> getLstDpto() {
        return lstDpto;
    }

    public void setLstDpto(List<SelectItem> lstDpto) {
        this.lstDpto = lstDpto;
    }

    public List<SelectItem> getLstCiudad() {
        return lstCiudad;
    }

    public void setLstCiudad(List<SelectItem> lstCiudad) {
        this.lstCiudad = lstCiudad;
    }

    public String getDireccionOrganizacion() {
        return direccionOrganizacion;
    }

    public void setDireccionOrganizacion(String direccionOrganizacion) {
        this.direccionOrganizacion = direccionOrganizacion.toUpperCase();
    }

    public String getTelefonoOrg() {
        return telefonoOrg;
    }

    public void setTelefonoOrg(String telefonoOrg) {
        this.telefonoOrg = telefonoOrg;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos.toUpperCase();
    }

    public String getTelefonoPersonal() {
        return telefonoPersonal;
    }

    public void setTelefonoPersonal(String telefonoPersonal) {
        this.telefonoPersonal = telefonoPersonal;
    }

    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial.toUpperCase();
    }

    public String getCorreoCorp() {
        return correoCorp;
    }

    public void setCorreoCorp(String correoCorp) {
        this.correoCorp = correoCorp;
    }

    public String getCargoDesemp() {
        return cargoDesemp;
    }

    public void setCargoDesemp(String cargoDesemp) {
        this.cargoDesemp = cargoDesemp.toUpperCase();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta.toUpperCase();
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta.toUpperCase(Locale.FRENCH);
    }
    
    
    /**
     * Obtiene el listado de paises
     * @return 
     */
    private List<SelectItem> obtenerPais(){
        List<SelectItem> items = null;
        try {
            List<PaisesVO> pvos = bO.obtenerPaises();
            if(pvos!=null && !pvos.isEmpty()){
                items = new ArrayList<>();
                for (PaisesVO paisesVO : pvos) {
                    items.add(new SelectItem(paisesVO.getCdgPais(), paisesVO.getPais()));
                }
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error al obtener Pais: ", e);
        }finally{
            return items;
        }
    }
    
    private List<SelectItem> obtenerDepartamento(){
        List<SelectItem> items = null;
        try {
            departamentoVOs = bO.obtenerDptos();
            if(departamentoVOs!=null && !departamentoVOs.isEmpty()){
                items = new ArrayList<>();
                for (DepartamentoVO departamentoVO : departamentoVOs) {
                    items.add(new SelectItem(departamentoVO.getCodDepart(),departamentoVO.getDescripcion()));
                }
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error al obtener lista de Dpto", e);
        }finally{
            return items;
        }
    }
    
    /**
     * Activa o inactiva los combos de acuerdo a la seleccion del pais
     * @param event 
     */
    public void changePais(ValueChangeEvent event){
        String valor = (String) event.getNewValue();
        if(valor.equals("CO")){
            this.activaDptoMpioOrga=true;
        }else{
            this.activaDptoMpioOrga=false;
        }
    }
    
    /**
     * Obtiene la lista de ciudades de un departamento
     * @param event 
     */
    public void changeDpto(ValueChangeEvent event){
        String valor = (String) event.getNewValue();
        if(!valor.isEmpty()){
            lstCiudad = new ArrayList<>();
            for(DepartamentoVO dvo : departamentoVOs){
                if(dvo.getCodDepart().equals(valor)){
                    for(CiudadesVO cvo : dvo.getCiudadesVOs()){
                        lstCiudad.add(new SelectItem(cvo.getCodMun(), cvo.getCiudad()));
                    }
                    break;
                }
            }
        }
    }
    
    public void btnAceptar(){
        
    }
    
    public void btnCancelar(){
        
    }
}
