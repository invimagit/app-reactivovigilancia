/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.beans;

import co.gov.invima.dto.DepartamentoDTO;
import co.gov.invima.dto.MunicipioDTO;
import co.gov.invima.dto.ParametrosDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import co.gov.invima.dto.ResponseDTO;
import co.gov.invima.dto.ResponseVisitaDTO;
import co.gov.invima.dto.RolDTO;
import co.gov.invima.dto.SedeDTO;
import co.gov.invima.dto.service.ConsultarSedesRqService;
import co.gov.invima.service.ServiceAlerta;
import co.gov.invima.service.ServicePerfilacion;
import co.gov.invima.service.ServiceVisita;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Kubit
 */
@ManagedBean
@ViewScoped
public class RegistrarVisitaBean implements Serializable {

    private static final Logger logRegVis = Logger.getLogger(RegistrarVisitaBean.class.getName());
    private static final String MENSAJE_REQUERIDO_SELE_AGREGAR = "Por favor seleccione los registro agregar";
    private static final String MENSAJE_REQUERIDO_SELE_ELIMINAR = "Por favor seleccione los registro a eliminar";
    private static final String BASE_ERROR_MENSAJE = "------ERROR------ \n Consulte con el administrador la disponibilidad de el/los siguientes servicios: \n";
    private static final String ERROR_CREAR_TAREA = "Crear tarea: ";
    private static final String ERROR_CREAR_ANTECEDENTE = "Guardar antecedente: ";
    private static final String SOLICITUD_CORRECTA = "La solicitud de planeación de visita se realizó satisfactoriamente";

    private boolean consultarEstablecimientos;
    private boolean solicitarPlaneacion;
    private List<SedeDTO> lstSedes;
    private List<SedeDTO> lstSedesSeleccionadas;
    private SedeDTO sedeSeleccionada;
    private List<String> datosEstablecimientos;
    private String mensajeValidacion;
    private ParametrosDTO filtroBusqueda;
    private ServicePerfilacion servicePerfilacion;
    private ResponseDTO response;
    private boolean busquedaVacia = false;
    private boolean inputExpVacio = false;
    private ResponseVisitaDTO responseVisita;
    private ServiceVisita serviceVisita;
    private ServiceAlerta serviceAlerta;
    private boolean estaSeguro = false;
    private boolean solicitarConf = false;
    private boolean solicitudVacia = false;
    private String mensajeError = "";
    private List<RolDTO> lstRoles;
    private List<DepartamentoDTO> lstDeptos;
    private List<MunicipioDTO> lstMunicipios;
    private boolean activarMunicipios = true;
    private String idDepartamento;

    public static void main(String[] args) {

    }

    public RegistrarVisitaBean() {
        logRegVis.info("Ingreso RegistrarVisitaBean ");
        this.consultarEstablecimientos = false;
        this.solicitarPlaneacion = false;
        this.datosEstablecimientos = new ArrayList<>();
        this.lstSedes = new ArrayList<SedeDTO>();
        this.lstSedesSeleccionadas = new ArrayList<SedeDTO>();
        this.sedeSeleccionada = new SedeDTO();
        this.mensajeValidacion = "";
        this.filtroBusqueda = new ParametrosDTO();
        this.servicePerfilacion = new ServicePerfilacion();
        this.response = new ResponseDTO();
        this.serviceVisita = new ServiceVisita();
        this.responseVisita = new ResponseVisitaDTO();
        lstDeptos = new ArrayList<DepartamentoDTO>();
        lstDeptos = this.serviceVisita.obtenerDepartamentos();
        lstMunicipios = new ArrayList<MunicipioDTO>();
        lstRoles = new ArrayList<RolDTO>();
        lstRoles = this.serviceVisita.obtenerRoles();
        this.serviceAlerta = new ServiceAlerta();
    }

    public void onClickBuscarEstablecimientos(String inputExp, String inputNumRep) {
        logRegVis.info("Ingreso onClickBuscarEstablecimientos a buscar: " + inputExp + "Num Rep: " + inputNumRep);
        this.lstSedes.clear();
        this.lstSedesSeleccionadas.clear();
        busquedaVacia = false;
        solicitarPlaneacion = false;
        inputExpVacio = false;
        solicitudVacia = false;
        solicitarConf = false;
        if (inputExp.equals("") && inputNumRep.equals("")) {
            inputExpVacio = true;
            consultarEstablecimientos = false;
        } else {
            ConsultarSedesRqService cs = new ConsultarSedesRqService();
            filtroBusqueda.setIdExpediente(inputExp);
            filtroBusqueda.setIdRegistroSanitario(inputNumRep);
            System.out.println("filtroBusqueda " + filtroBusqueda.getIdExpediente());
            cs.setParametros(filtroBusqueda);
            cs.getAuditoria().setIp("192.168.0.1");
            cs.getAuditoria().setUsuario("Leonardo");
            this.lstSedes = servicePerfilacion.consultarEstablecimientos(cs, this.response);
            if (lstSedes.isEmpty()) {
                this.consultarEstablecimientos = false;
                this.busquedaVacia = true;
            } else {
                this.consultarEstablecimientos = true;
            }
            logRegVis.info("Fin response getStatusCode: " + this.response.getStatusCode());
            logRegVis.info("Fin onClickBuscarEstablecimientos " + lstSedes.size());
        }
    }

    public void onClickAgregarPlaneacion() {
        logRegVis.info("Ingreso onClickAgregarPlaneacion " + this.lstSedes.size());
        if (validarSeleccionado(lstSedes)) {
            this.solicitarPlaneacion = true;
            for (int i = 0; i < this.lstSedes.size(); i++) {
                if (this.lstSedes.get(i).isSeleccionado()) {
                    this.lstSedes.get(i).setSeleccionado(Boolean.FALSE);
                    this.lstSedesSeleccionadas.add(this.lstSedes.get(i));
                    this.lstSedes.remove(this.lstSedes.get(i));
                    i--;
                }
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", MENSAJE_REQUERIDO_SELE_AGREGAR));
        }
    }

    public void onClickEliminarPlaneacion() {
        logRegVis.info("Ingreso onClickEliminarPlaneacion ");
        if (validarSeleccionado(lstSedesSeleccionadas)) {
            for (int i = 0; i < this.lstSedesSeleccionadas.size(); i++) {
                if (this.lstSedesSeleccionadas.get(i).isSeleccionado()) {
                    this.lstSedesSeleccionadas.get(i).setSeleccionado(Boolean.FALSE);
                    if (this.lstSedesSeleccionadas.get(i).isEditable()) {
                        this.lstSedesSeleccionadas.remove(this.lstSedesSeleccionadas.get(i));
                    } else {
                        this.lstSedes.add(this.lstSedesSeleccionadas.get(i));
                        this.lstSedesSeleccionadas.remove(this.lstSedesSeleccionadas.get(i));
                    }

                    i--;
                }
            }
            if (this.lstSedesSeleccionadas.size() <= 0) {
                this.solicitarPlaneacion = Boolean.FALSE;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", MENSAJE_REQUERIDO_SELE_ELIMINAR));
        }
    }

    public void btnSolicitarPlaneacion() {
        ResponseVisitaDTO responsePlaneacion = new ResponseVisitaDTO();
        solicitudVacia = false;
        boolean popUpError = false;
        logRegVis.info("Ingreso btnBuscarEstablecimientos ");
        mensajeError = BASE_ERROR_MENSAJE;
        if (lstSedesSeleccionadas.isEmpty()) {
            solicitudVacia = true;
        } else {
            responsePlaneacion = this.serviceAlerta.crearTareaAlertaSanitarias(lstSedesSeleccionadas);
            if(responsePlaneacion.getStatusCode() !=HttpStatus.CREATED.value()
                    && responsePlaneacion.getStatusCode() != HttpStatus.OK.value()){
              popUpError = Boolean.TRUE;  
              mensajeError =  responsePlaneacion.getMessage();
            }
            if (!popUpError) {
                mensajeError = responsePlaneacion.getMessage();
                lstSedesSeleccionadas.clear();
                solicitarPlaneacion = false;
            }
        }
    }

    public Boolean validarSeleccionado(final List<SedeDTO> lstSedes) {
        Boolean indSeleccionado = Boolean.FALSE;
        for (int i = 0; i < lstSedes.size(); i++) {
            if (lstSedes.get(i).isSeleccionado()) {
                indSeleccionado = Boolean.TRUE;
                break;
            }
        }

        return indSeleccionado;
    }

    public void onClickCrearRegistro() {
        logRegVis.info("Ingreso onClickCrearRegistro");
        SedeDTO sedeACrear = new SedeDTO();
        sedeACrear.setEditable(true);
        lstSedesSeleccionadas.add(sedeACrear);
        lstRoles = this.serviceVisita.obtenerRoles();
    }

    public void onChangeDepartamento(ValueChangeEvent e) {
        String idDep = e.getNewValue().toString();
        String[] partes = idDep.split("\\.");
        idDep = partes[0];
        System.out.println("Valor de la variable: " + idDep);
        lstMunicipios = this.serviceVisita.obtenerMunicipios(idDep);
    }

    public void btnVacioAcciones() {
        solicitarConf = false;
        busquedaVacia = false;
        mensajeError = BASE_ERROR_MENSAJE;
        solicitarConf = true;
        System.out.println("Terminó btnVacioAcciones");
    }

    public void btnActivarCrear() {
        busquedaVacia = false;
        solicitarPlaneacion = true;
    }

    public void onClickSeleccionSede() {
        logRegVis.info("Ingreso onClickSeleccionSede ");

    }

    public boolean isSolicitarPlaneacion() {
        return solicitarPlaneacion;
    }

    public void setSolicitarPlaneacion(boolean solicitarPlaneacion) {
        this.solicitarPlaneacion = solicitarPlaneacion;
    }

    public boolean isSolicitarConf() {
        return solicitarConf;
    }

    public void setSolicitarConf(boolean solicitarConf) {
        this.solicitarConf = solicitarConf;
    }

    public boolean isConsultarEstablecimientos() {
        return consultarEstablecimientos;
    }

    public void setConsultarEstablecimientos(boolean consultarEstablecimientos) {
        this.consultarEstablecimientos = consultarEstablecimientos;
    }

    public List<SedeDTO> getLstSedes() {
        return lstSedes;
    }

    public boolean isEstaSeguro() {
        return estaSeguro;
    }

    public void setEstaSeguro(boolean estaSeguro) {
        this.estaSeguro = estaSeguro;
    }

    public void setLstSedes(List<SedeDTO> lstSedes) {
        this.lstSedes = lstSedes;
    }

    public List<SedeDTO> getLstSedesSeleccionadas() {
        return lstSedesSeleccionadas;
    }

    public void setLstSedesSeleccionadas(List<SedeDTO> lstSedesSeleccionadas) {
        this.lstSedesSeleccionadas = lstSedesSeleccionadas;
    }

    public SedeDTO getSedeSeleccionada() {
        return sedeSeleccionada;
    }

    public void setSedeSeleccionada(SedeDTO sedeSeleccionada) {
        this.sedeSeleccionada = sedeSeleccionada;
    }

    public List<String> getDatosEstablecimientos() {
        return datosEstablecimientos;
    }

    public void setDatosEstablecimientos(List<String> datosEstablecimientos) {
        this.datosEstablecimientos = datosEstablecimientos;
    }

    public String getMensajeValidacion() {
        return mensajeValidacion;
    }

    public void setMensajeValidacion(String mensajeValidacion) {
        this.mensajeValidacion = mensajeValidacion;
    }

    public ParametrosDTO getFiltroBusqueda() {
        return filtroBusqueda;
    }

    public void setFiltroBusqueda(ParametrosDTO filtroBusqueda) {
        this.filtroBusqueda = filtroBusqueda;
    }

    public boolean isBusquedaVacia() {
        return busquedaVacia;
    }

    public void setBusquedaVacia(boolean busquedaVacia) {
        this.busquedaVacia = busquedaVacia;
    }

    public List<RolDTO> getLstRoles() {
        return lstRoles;
    }

    public void setLstRoles(List<RolDTO> lstRoles) {
        this.lstRoles = lstRoles;
    }

    public List<DepartamentoDTO> getLstDeptos() {
        return lstDeptos;
    }

    public void setLstDeptos(List<DepartamentoDTO> lstDeptos) {
        this.lstDeptos = lstDeptos;
    }

    public List<MunicipioDTO> getLstMunicipios() {
        return lstMunicipios;
    }

    public void setLstMunicipios(List<MunicipioDTO> lstMunicipios) {
        this.lstMunicipios = lstMunicipios;
    }

    public boolean isActivarMunicipios() {
        return activarMunicipios;
    }

    public void setActivarMunicipios(boolean activarMunicipios) {
        this.activarMunicipios = activarMunicipios;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public boolean isSolicitudVacia() {
        return solicitudVacia;
    }

    public void setSolicitudVacia(boolean solicitudVacia) {
        this.solicitudVacia = solicitudVacia;
    }

    public boolean isInputExpVacio() {
        return inputExpVacio;
    }

    public void setInputExpVacio(boolean inputExpVacio) {
        this.inputExpVacio = inputExpVacio;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

}
