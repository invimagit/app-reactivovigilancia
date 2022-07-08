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
import co.gov.invima.VO.ProfesionesVO;
import co.gov.invima.VO.ReactivoModalidadesVO;
import co.gov.invima.VO.RedVO;
import co.gov.invima.entities.ReactivoRedPersona;
import co.gov.invima.sesion.ReactivoRedFacadeLocal;
import co.gov.invima.sesion.ReactivoRedPersonaFacadeLocal;
import co.gov.invima.sesion.ReactivoUsuariosInternetFacadeLocal;
import co.gov.invima.util.PropertiesReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.log4j.Priority;
import org.primefaces.context.RequestContext;

/**
 *
 * @author mgualdrond
 */
@Named("inscripcionBean")
@SessionScoped
public class InscripcionBean implements Serializable {

    private static final Logger logBeanWebReactivo = Logger.getLogger(InscripcionBean.class.getName());
    private String codigoModalidad;
    private List<SelectItem> lstModalidad;
    private List<ReactivoModalidadesVO> modalidadesVOs;
    private ReactivoBO bO;
    private String nombreRazonSocial;
    private String nit;
    private String codigoNaturaleza;
    private String codigoNivelComplejidad;
    private String direccionOrganizacion;
    private String codigoPais;
    private String codigoDpto;
    private String codigoMpio;
    private String telefono;
    private String extension;
    private String fax;
    private String correoCorporativo;
    private String nombreSolicitante;
    private String documentoSolicitante;
    private String codigoProfesion;
    private String codigoCargo;
    private String codigoAreaEmpresa;
    private String direccionCorresp;
    private String codigoPaisSol;
    private String codigoDptoSol;
    private String codigoMpioSol;
    private String telefonoDomicilio;
    private String celularSol;
    private String correoSolicitante;
    private List<SelectItem> lstPais;
    private List<SelectItem> lstDpto;
    private List<SelectItem> lstCiudad;
    private List<SelectItem> lstPaisSol;
    private List<SelectItem> lstDptoSol;
    private List<SelectItem> lstCiudadSol;
    private List<SelectItem> lstProfesion;
    private List<SelectItem> lstAreaEmpresa;
    private List<SelectItem> lstCargo;
    private List<DepartamentoVO> lstDptos;
    private boolean activaDptoMpioOrga;
    private boolean activaDptoMpioSol;
    private RedVO redVO;
    private boolean mostrarInscripcion;
    private String mensajes;
    private String otraModalidad;
    private boolean requierOtraModalidad;
    //private HttpSession session;
    
    //******************************************************************************
    private String cedula_usuario_a_buscar;
    @EJB
    private ReactivoRedPersonaFacadeLocal reactivoRedPersonaFacadeLocal;
    @EJB
    private ReactivoRedFacadeLocal reactivoRedFacadeLocal;
    private boolean busqueda_por_cedula_exitosa = false;
    private ReactivoRedPersona persona_red_buscada;
//******************************************************************************
    /**
     * Creates a new instance of InscripcionBean
     */
    public InscripcionBean() {
        //session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        bO = new ReactivoBO();
        modalidadesVOs = bO.obtnerModalidades();
        
    }

    @PostConstruct
    public void init() {
        mostrarInscripcion = true;
        nombreSolicitante = "";
        nombreRazonSocial = "";
        requierOtraModalidad = false;
        lstModalidad = this.obtenerModalidad();
        activaDptoMpioOrga = false;
        activaDptoMpioSol = false;
        lstPais = this.obtenerPais();
        lstPaisSol = this.obtenerPais();
        lstDpto = this.obtenerDepartamento();
        lstDptoSol = this.obtenerDepartamento();
        lstProfesion = this.obtenerProfesion();
    }

    public void onload(){
        requierOtraModalidad = false;
        if(!FacesContext.getCurrentInstance().isPostback()) {
            RequestContext.getCurrentInstance().execute("alert('This onload script is added from backing bean.')");
        }
    }

    public ReactivoRedPersona getPersona_red_buscada() {
        return persona_red_buscada;
    }

    public void setPersona_red_buscada(ReactivoRedPersona persona_red_buscada) {
        this.persona_red_buscada = persona_red_buscada;
    }

    public boolean isBusqueda_por_cedula_exitosa() {
        return busqueda_por_cedula_exitosa;
    }

    public void setBusqueda_por_cedula_exitosa(boolean busqueda_por_cedula_exitosa) {
        this.busqueda_por_cedula_exitosa = busqueda_por_cedula_exitosa;
    }

    public String getCedula_usuario_a_buscar() {
        return cedula_usuario_a_buscar;
    }

    public void setCedula_usuario_a_buscar(String cedula_usuario_a_buscar) {
        this.cedula_usuario_a_buscar = cedula_usuario_a_buscar;
    }
    
    public String getCodigoModalidad() {
        return codigoModalidad;
    }

    public void setCodigoModalidad(String codigoModalidad) {
        this.codigoModalidad = codigoModalidad;
    }

    public List<SelectItem> getLstModalidad() {
        return lstModalidad;
    }

    public void setLstModalidad(List<SelectItem> lstModalidad) {
        this.lstModalidad = lstModalidad;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCodigoNaturaleza() {
        return codigoNaturaleza;
    }

    public void setCodigoNaturaleza(String codigoNaturaleza) {
        this.codigoNaturaleza = codigoNaturaleza;
    }

    public String getCodigoNivelComplejidad() {
        return codigoNivelComplejidad;
    }

    public void setCodigoNivelComplejidad(String codigoNivelComplejidad) {
        this.codigoNivelComplejidad = codigoNivelComplejidad;
    }

    public String getDireccionOrganizacion() {
        return direccionOrganizacion;
    }

    public void setDireccionOrganizacion(String direccionOrganizacion) {
        this.direccionOrganizacion = direccionOrganizacion;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCorreoCorporativo() {
        return correoCorporativo;
    }

    public void setCorreoCorporativo(String correoCorporativo) {
        this.correoCorporativo = correoCorporativo;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante.toUpperCase();
    }

    public String getDocumentoSolicitante() {
        return documentoSolicitante;
    }

    public void setDocumentoSolicitante(String documentoSolicitante) {
        this.documentoSolicitante = documentoSolicitante;
    }

    public String getCodigoProfesion() {
        return codigoProfesion;
    }

    public void setCodigoProfesion(String codigoProfesion) {
        this.codigoProfesion = codigoProfesion;
    }

    public String getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(String codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public String getCodigoAreaEmpresa() {
        return codigoAreaEmpresa;
    }

    public void setCodigoAreaEmpresa(String codigoAreaEmpresa) {
        this.codigoAreaEmpresa = codigoAreaEmpresa.toUpperCase();
    }

    public String getDireccionCorresp() {
        return direccionCorresp;
    }

    public void setDireccionCorresp(String direccionCorresp) {
        this.direccionCorresp = direccionCorresp;
    }

    public String getCodigoPaisSol() {
        return codigoPaisSol;
    }

    public void setCodigoPaisSol(String codigoPaisSol) {
        this.codigoPaisSol = codigoPaisSol;
    }

    public String getCodigoDptoSol() {
        return codigoDptoSol;
    }

    public void setCodigoDptoSol(String codigoDptoSol) {
        this.codigoDptoSol = codigoDptoSol;
    }

    public String getCodigoMpioSol() {
        return codigoMpioSol;
    }

    public void setCodigoMpioSol(String codigoMpioSol) {
        this.codigoMpioSol = codigoMpioSol;
    }

    public String getTelefonoDomicilio() {
        return telefonoDomicilio;
    }

    public void setTelefonoDomicilio(String telefonoDomicilio) {
        this.telefonoDomicilio = telefonoDomicilio;
    }

    public String getCelularSol() {
        return celularSol;
    }

    public void setCelularSol(String celularSol) {
        this.celularSol = celularSol;
    }

    public String getCorreoSolicitante() {
        return correoSolicitante;
    }

    public void setCorreoSolicitante(String correoSolicitante) {
        this.correoSolicitante = correoSolicitante;
    }

    public List<SelectItem> getLstPais() {
        return lstPais;
    }

    public void setLstPais(List<SelectItem> lstPais) {
        this.lstPais = lstPais;
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

    public List<SelectItem> getLstPaisSol() {
        return lstPaisSol;
    }

    public void setLstPaisSol(List<SelectItem> lstPaisSol) {
        this.lstPaisSol = lstPaisSol;
    }

    public List<SelectItem> getLstDptoSol() {
        return lstDptoSol;
    }

    public void setLstDptoSol(List<SelectItem> lstDptoSol) {
        this.lstDptoSol = lstDptoSol;
    }

    public List<SelectItem> getLstCiudadSol() {
        return lstCiudadSol;
    }

    public void setLstCiudadSol(List<SelectItem> lstCiudadSol) {
        this.lstCiudadSol = lstCiudadSol;
    }

    public List<SelectItem> getLstProfesion() {
        return lstProfesion;
    }

    public void setLstProfesion(List<SelectItem> lstProfesion) {
        this.lstProfesion = lstProfesion;
    }

    public boolean isActivaDptoMpioOrga() {
        return activaDptoMpioOrga;
    }

    public void setActivaDptoMpioOrga(boolean activaDptoMpioOrga) {
        this.activaDptoMpioOrga = activaDptoMpioOrga;
    }

    public boolean isActivaDptoMpioSol() {
        return activaDptoMpioSol;
    }

    public void setActivaDptoMpioSol(boolean activaDptoMpioSol) {
        this.activaDptoMpioSol = activaDptoMpioSol;
    }

    public boolean isMostrarInscripcion() {
        return mostrarInscripcion;
    }

    public void setMostrarInscripcion(boolean mostrarInscripcion) {
        this.mostrarInscripcion = mostrarInscripcion;
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public String getOtraModalidad() {
        return otraModalidad;
    }

    public void setOtraModalidad(String otraModalidad) {
        this.otraModalidad = otraModalidad;
    }

    public boolean isRequierOtraModalidad() {
        return requierOtraModalidad;
    }

    public void setRequierOtraModalidad(boolean requierOtraModalidad) {
        this.requierOtraModalidad = requierOtraModalidad;
    }

    /**
     * Obtiene el lsuitado de la modalidad para el combo
     *
     * @return Listado de SelecItem de modalidad
     */
    private List<SelectItem> obtenerModalidad() {
        List<SelectItem> items = null;
        try {
            if (modalidadesVOs != null && !modalidadesVOs.isEmpty()) {
                items = new ArrayList<>();
                for (ReactivoModalidadesVO rmvo : modalidadesVOs) {
                    SelectItem item = new SelectItem();
                    item.setValue(rmvo.getCdgModalidad().toString());
                    item.setLabel(rmvo.getDescripcion());
                    items.add(item);
                }
            }
        } catch (Exception e) {
            logBeanWebReactivo.log(Priority.ERROR, "Error al obtener Lista de modalidades: ", e);
        } finally {
            return items;
        }
    }

    private List<SelectItem> obtenerPais() {
        List<SelectItem> items = null;
        try {
            List<PaisesVO> pvos = bO.obtenerPaises();
            if (pvos != null && !pvos.isEmpty()) {
                items = new ArrayList<>();
                for (PaisesVO paisesVO : pvos) {
                    items.add(new SelectItem(paisesVO.getCdgPais(), paisesVO.getPais()));
                }
            }
        } catch (Exception e) {
            logBeanWebReactivo.log(Priority.ERROR, "Error al obtener Pais: ", e);
        } finally {
            return items;
        }
    }

    private List<SelectItem> obtenerDepartamento() {
        List<SelectItem> items = null;
        try {
            lstDptos = bO.obtenerDptos();
            if (lstDptos != null && !lstDptos.isEmpty()) {
                items = new ArrayList<>();
                for (DepartamentoVO departamentoVO : lstDptos) {
                    items.add(new SelectItem(departamentoVO.getCodDepart(), departamentoVO.getDescripcion()));
                }
            }
        } catch (Exception e) {
            logBeanWebReactivo.log(Priority.ERROR, "Error al obtener lista de Dpto", e);
        } finally {
            return items;
        }
    }

    /**
     * Activa o inactiva los combos de acuerdo a la seleccion del pais
     *
     * @param event
     */
    public void changePais(ValueChangeEvent event) {
        String valor = (String) event.getNewValue();
        this.activaDptoMpioOrga = valor.trim().equals("CO");
    }

    public void changeModalidad(ValueChangeEvent event) {
        String valor = (String) event.getNewValue();
        if (valor.equals("9") || valor.equals("12")) {
            mostrarInscripcion = false;
        } else {
            mostrarInscripcion = true;
        }
        if (valor.equals("16")) {
            requierOtraModalidad = true;
        } else {
            otraModalidad = "";
            requierOtraModalidad = false;
        }
    }

    public void changeModalidadLstnr() {
        String valor = this.codigoModalidad;
        if (valor.equals("9") || valor.equals("12")) {
            mostrarInscripcion = false;
        } else {
            mostrarInscripcion = true;
        }
        if (valor.equals("16")) {
            requierOtraModalidad = true;
        } else {
            otraModalidad = "";
            requierOtraModalidad = false;
        }
    }

    public void changePaisSol(ValueChangeEvent event) {
        String valor = (String) event.getNewValue();
        this.activaDptoMpioSol = valor.trim().equals("CO");
    }

    /**
     * Obtiene la lista de ciudades de un departamento
     *
     * @param event
     */
    public void changeDpto(ValueChangeEvent event) {
        String valor = (String) event.getNewValue();
        if (!valor.isEmpty()) {
            lstCiudad = new ArrayList<>();
            for (DepartamentoVO dvo : lstDptos) {
                if (dvo.getCodDepart().equals(valor)) {
                    for (CiudadesVO cvo : dvo.getCiudadesVOs()) {
                        lstCiudad.add(new SelectItem(cvo.getCodMun(), cvo.getCiudad()));
                    }
                    break;
                }
            }
        }
    }
    
    public void changeDptoLtn() {
        String valor = codigoDpto;
        if (!valor.isEmpty()) {
            lstCiudad = new ArrayList<>();
            for (DepartamentoVO dvo : lstDptos) {
                if (dvo.getCodDepart().equals(valor)) {
                    for (CiudadesVO cvo : dvo.getCiudadesVOs()) {
                        lstCiudad.add(new SelectItem(cvo.getCodMun(), cvo.getCiudad()));
                    }
                    break;
                }
            }
        }
    }

    public void changeDptoSol(ValueChangeEvent event) {
        String valor = (String) event.getNewValue();
        if (!valor.isEmpty()) {
            lstCiudadSol = new ArrayList<>();
            for (DepartamentoVO dvo : lstDptos) {
                if (dvo.getCodDepart().equals(valor)) {
                    for (CiudadesVO cvo : dvo.getCiudadesVOs()) {
                        lstCiudadSol.add(new SelectItem(cvo.getCodMun(), cvo.getCiudad()));
                    }
                    break;
                }
            }
        }
    }

    private List<SelectItem> obtenerProfesion() {
        List<SelectItem> items = null;
        try {
            List<ProfesionesVO> pvos = bO.obtenerProfesiones();
            if (pvos != null && !pvos.isEmpty()) {
                items = new ArrayList<>();
                for (ProfesionesVO profesionesVO : pvos) {
                    items.add(new SelectItem(profesionesVO.getCdgProfesion(), profesionesVO.getDescripcion()));
                }
            }
        } catch (Exception e) {
            logBeanWebReactivo.log(Priority.ERROR, "Error al obtener profesion", e);
        } finally {
            return items;
        }
    }

    
    public void btnBuscarUsuarioRed(){
        try{
            persona_red_buscada = reactivoRedPersonaFacadeLocal.obtenerPersonaRedPorDocumento(cedula_usuario_a_buscar).get(0);
            if(persona_red_buscada!=null){
                busqueda_por_cedula_exitosa = true;
            }
            else
            {
                busqueda_por_cedula_exitosa = false;
            }
        }
        catch(Exception e){
            busqueda_por_cedula_exitosa = false;
        }
        
        if(busqueda_por_cedula_exitosa)
        {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/ReactivoVigilanciaWeb/faces/pages/EditarUsuarioRed.xhtml");
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(InscripcionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            persona_red_buscada = new ReactivoRedPersona();
            mensajes = "Persona no encontrada";
                FacesContext.getCurrentInstance().addMessage("panelContenedorIns", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        mensajes, null));
        }
    }
    
    public void btnCancelar(){
        try {
            cedula_usuario_a_buscar="";
            busqueda_por_cedula_exitosa = false;
            persona_red_buscada = new ReactivoRedPersona();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ReactivoVigilanciaWeb/faces/pages/EditarUsuarioRed.xhtml");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(InscripcionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void btnActualizarUsuarioRed(){
        try{
            reactivoRedPersonaFacadeLocal.edit(persona_red_buscada);
            reactivoRedFacadeLocal.edit(persona_red_buscada.getIdRed());
            busqueda_por_cedula_exitosa = false;
            mensajes = "Usuario Actualizado";
                FacesContext.getCurrentInstance().addMessage("panelContenedorIns", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        mensajes, null));
        }
        catch(Exception e)
        {
            mensajes = "Error al actualizar el usuario";
                FacesContext.getCurrentInstance().addMessage("panelContenedorIns", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        mensajes, null));
        }
        
    }
            
    public void btnAceptar() {
        try {
            if (redVO == null) {
                redVO = new RedVO();
            }
            if (mostrarInscripcion) {
                redVO.setAreaEmpresa(codigoAreaEmpresa);
                redVO.setCargos(codigoCargo);
                redVO.setCdgModalidad(Integer.valueOf(codigoModalidad));
                redVO.setOtraModalidad(otraModalidad.toUpperCase());
                redVO.setCedulaSolicitante(documentoSolicitante);
                redVO.setCelularSolic(celularSol);
                redVO.setCodDepart(codigoDpto);
                redVO.setCodDepart1(codigoDptoSol);
                redVO.setCodMun(codigoMpio);
                redVO.setCodMun1(codigoMpioSol);
                redVO.setComplejidad(codigoNivelComplejidad);
                redVO.setDireccionOrganizacion(direccionOrganizacion);
                redVO.setDireccionSolicitante(direccionCorresp);
                redVO.setEmailCorporativo(correoCorporativo);
                redVO.setEmailPersonal(correoSolicitante);
                redVO.setFaxOrganiz(fax);
                Date fecha = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String fechaTemp = sdf.format(fecha);
                redVO.setFechaSolicitud(sdf.parse(fechaTemp));
                redVO.setNaturaleza(codigoNaturaleza);
                redVO.setNit(nit);
                redVO.setNombreInstitucion(nombreRazonSocial);
                redVO.setNombreSolic(nombreSolicitante);
                redVO.setPaisOrganiz(codigoPais);
                redVO.setPaisSolic(codigoPaisSol);
                redVO.setProfesion(codigoProfesion);
                redVO.setTelefonoOrganiz(telefono);
                redVO.setExtOrganiz(extension);
                redVO.setTelefonoSolic(telefonoDomicilio);
            } else {
                redVO.setAreaEmpresa(codigoAreaEmpresa);
                redVO.setCargos(codigoCargo);
                redVO.setCdgModalidad(Integer.valueOf(codigoModalidad));
                redVO.setOtraModalidad(otraModalidad.toUpperCase());
                redVO.setCedulaSolicitante(documentoSolicitante);
                redVO.setCelularSolic(celularSol);
                redVO.setCodDepart(codigoDptoSol);
                redVO.setCodDepart1(codigoDptoSol);
                redVO.setCodMun(codigoMpioSol);
                redVO.setCodMun1(codigoMpioSol);
                redVO.setComplejidad("NA");
                redVO.setDireccionOrganizacion(direccionCorresp);
                redVO.setDireccionSolicitante(direccionCorresp);
                redVO.setEmailCorporativo(correoSolicitante);
                redVO.setEmailPersonal(correoSolicitante);
                redVO.setFaxOrganiz("");
                Date fecha = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String fechaTemp = sdf.format(fecha);
                redVO.setFechaSolicitud(sdf.parse(fechaTemp));
                redVO.setNaturaleza("PRI");
                redVO.setNit(documentoSolicitante);
                redVO.setNombreInstitucion(nombreSolicitante);
                redVO.setNombreSolic(nombreSolicitante);
                redVO.setPaisOrganiz(codigoPaisSol);
                redVO.setPaisSolic(codigoPaisSol);
                redVO.setProfesion(codigoProfesion);
                redVO.setTelefonoOrganiz(telefonoDomicilio);
                redVO.setExtOrganiz(extension);
                redVO.setTelefonoSolic(telefonoDomicilio);
            }
            String resultado = bO.guradrInscripcionRed(redVO);
            if (resultado.startsWith("OK")) {
                int pos = resultado.indexOf("-");

                mensajes = "Inscripción guardada con éxito, No. de Registro: " + resultado.substring(pos + 1, resultado.length());
                FacesContext.getCurrentInstance().addMessage("panelContenedorIns", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        mensajes, null));
            } else {
                //Error en la inscripcion
                mensajes = "No es posible guardar inscripción";
                FacesContext.getCurrentInstance().addMessage("panelContenedorIns", new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        mensajes, null));
            }

        } catch (ParseException ex) {
            Logger.getLogger(InscripcionBean.class.getName()).log(Priority.ERROR, null, ex);
        }

    }



    public String retornoResultadoOK() {
//        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        PropertiesReader pr = new PropertiesReader("co.gov.invima.util.retornos");
        return pr.getProperty("retornoInscripcion");
    }
}
