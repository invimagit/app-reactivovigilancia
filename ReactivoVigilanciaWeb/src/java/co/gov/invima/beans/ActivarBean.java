/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.beans;
/*********************************************************************/
/*********************************************************************/
/*********************************************************************/
/*********************************************************************/
/*********************************************************************/
/*********************************************************************/
import co.gov.invima.BO.ReactivoBO;
import co.gov.invima.VO.UsuariosVO;
import co.gov.invima.negocio.ConsultasReactivoRemote;
import co.gov.invima.service.ServiceLocator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
//import javax.faces.event.ValueChangeListener;
/*********************************************************************/
/*********************************************************************/
/*********************************************************************/
/*********************************************************************/
/*********************************************************************/
/*********************************************************************/
/**
 *
 * @author mgualdrond
 */
@Named(value = "activarBean")
@SessionScoped
public class ActivarBean extends BaseBean implements Serializable 
{
    //*********************************************************
    //*********************************************************
    //*********************************************************
    private final static Logger logBeanWebReactivo = Logger.getLogger(ActivarBean.class.getName());
    //*********************************************************
    //*********************************************************
    //*********************************************************
    private ReactivoBO reactivoBO;
    private String codigoCriterioBuscar;
    private boolean requeridoBusqueda;
    private String criterioBusqueda;
    private String cedulaNombre;
    private List<SelectItem> lstUsuarios;
    private boolean mostrarResultado;
    private Integer codigoNombreSeleccionado;
    private List<SelectItem> lstEstados;
    private List<SelectItem> lstRoles;
    private UsuariosVO usuarioSeleccionado;
    private List<UsuariosVO> usuariosVOs;
    private boolean mostrarCuadro;
    private ConsultasReactivoRemote servicioEJBMaestrosReactivoRemote;
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    /**
     * Creates a new instance of ActivarBean
     */
    public ActivarBean() 
    {
        super();
        reactivoBO = new ReactivoBO();
        requeridoBusqueda = true;
        mostrarResultado = false;
        mostrarCuadro = false;
        usuarioSeleccionado = new UsuariosVO();
        usuarioSeleccionado.setEstadoUsuario('I');
        usuarioSeleccionado.setIdRol("0");
        this.llenarEstados();
    }
    //*********************************************************
    //*********************************************************
    //*********************************************************
    public void obtenerAccesoEJB()
    {
        //logBeanWebReactivo.info("Conectandose al contexto");
        ServiceLocator service = new ServiceLocator();
        servicioEJBMaestrosReactivoRemote = service.getEJBReportesReactivo();
        //logBeanWebReactivo.info("Estado del EJB DEL MAESTROS PARA EL MANAGED BEAN: " + this.servicioEJBMaestrosReactivoRemote);
    }
    //*********************************************************
    //*********************************************************
    //*********************************************************
    public void changeCriteria(ValueChangeEvent event) 
    {
        String opcion = (String) event.getNewValue();
        if (opcion != null && !opcion.isEmpty()) {
            codigoCriterioBuscar = opcion;
            if (opcion.equals("CC")) {
                criterioBusqueda = "Número de Cédula";
            } else {
                criterioBusqueda = "Nombre";
            }
        }
    }
    //*********************************************************
    //*********************************************************
    //*********************************************************
    public void buscar() 
    {
        usuariosVOs = null;
        //*************************************************************************
        //*************************************************************************
        //*************************************************************************
        logBeanWebReactivo.info ("CODIGO CRITERIO DE BÚSQUEDA ENVIADO = " + codigoCriterioBuscar);
        //*************************************************************************
        //*************************************************************************
        if (codigoCriterioBuscar == null)
        {
            codigoCriterioBuscar = "CC";
        }
        //*************************************************************************
        //*************************************************************************
        //*************************************************************************
        //*************************************************************************
        if (codigoCriterioBuscar.equals("CC")) {
            usuariosVOs = getReactivoBO().obtenerUsuariosPorCedula(Long.parseLong(cedulaNombre));
        }
        if (codigoCriterioBuscar.equals("NO")) {
            usuariosVOs = getReactivoBO().obtenerUsuariosPorNombre(cedulaNombre);
        }
        this.limpiar();
        if (usuariosVOs != null && !usuariosVOs.isEmpty()) {
            lstUsuarios = new ArrayList<>();
            Integer i = 1;
            for (UsuariosVO uvo : usuariosVOs) {
                lstUsuarios.add(new SelectItem(i, uvo.getIdentificacionPersona() + "-" + uvo.getNombrePersona()));
                i++;
            }
            mostrarResultado = true;
            requeridoBusqueda = false;
        } else {
            FacesContext.getCurrentInstance().addMessage("mensajesActivar", new FacesMessage(FacesMessage.SEVERITY_WARN, "Sin resultado", "No se encuentran datos"));
        }
    }
    //*********************************************************
    //*********************************************************
    //*********************************************************
    public void pendientes() 
    {
        this.limpiar();
        requeridoBusqueda = false;
        usuariosVOs = null;
        usuariosVOs = getReactivoBO().obtenerUsuarioPorEstado(null);
        if (usuariosVOs != null && !usuariosVOs.isEmpty()) {
            lstUsuarios = new ArrayList<>();
            Integer i = 1;
            for (UsuariosVO uvo : usuariosVOs) {
                lstUsuarios.add(new SelectItem(i, uvo.getIdentificacionPersona() + "-" + uvo.getNombrePersona()));
                i++;
            }
            mostrarResultado = true;
            requeridoBusqueda = false;
        } else {
            FacesContext.getCurrentInstance().addMessage("mensajesActivar", new FacesMessage(FacesMessage.SEVERITY_WARN, "Sin resultado", "No hay usuarios pendientes por activación"));
        }
    }
    //*********************************************************
    //*********************************************************
    //*********************************************************
    public void listenerPendientes() 
    {
        requeridoBusqueda = false;
    }

    //*********************************************************
    //*********************************************************
    //*********************************************************
    public void listenerSubmit(ValueChangeEvent event) 
    {
        Integer selecionado = (Integer) event.getNewValue();
        requeridoBusqueda = false;
        //this.llenarEstados();
        //obtiene usuario seleccionado
        
        if (selecionado != null) 
        {
            //*********************************************************************
            //*********************************************************************
            //*********************************************************************
            usuarioSeleccionado = usuariosVOs.get(selecionado - 1);
            usuarioSeleccionado.setIdRol(Integer.toString(usuarioSeleccionado.getiDRolUsuario()));
            //*********************************************************************
            //*********************************************************************
            if (usuarioSeleccionado.getEstadoUsuario() == null) {
                usuarioSeleccionado.setEstadoUsuario('I');
            }
            //*********************************************************************
            //*********************************************************************
            switch (usuarioSeleccionado.getEstadoUsuario())
            {
                case 'I' : usuarioSeleccionado.setEstadoPalabra("Inactivo");
                    break;
                case 'A': usuarioSeleccionado.setEstadoPalabra("Activo");
            }
            //*********************************************************************
            //*********************************************************************
            /*
            switch (usuarioSeleccionado.getiDRolUsuario())
             {
                    case 0 : usuarioSeleccionado.setRolPalabra("No tiene");
                        break;
                    case 1: usuarioSeleccionado.setRolPalabra("Comercializador");
                        break;
                    case 2: usuarioSeleccionado.setRolPalabra("Distribuidor");
                        break;
                    case 3: usuarioSeleccionado.setRolPalabra("Fabricante/Importador");
                        break;
                    case 4: usuarioSeleccionado.setRolPalabra("Prestador de servicios de salud");
                        break;
             }
            */
            switch (usuarioSeleccionado.getiDRolUsuario())
             {
                    case 0 : usuarioSeleccionado.setRolPalabra("SIN ROL ASIGNADO");
                        break;
                    case 1: usuarioSeleccionado.setRolPalabra("INVIMA");
                        break;
                    case 2: usuarioSeleccionado.setRolPalabra("Fabricante/Importador");
                        break;
                    case 3: usuarioSeleccionado.setRolPalabra("IPS");
                        break;
                    case 4: usuarioSeleccionado.setRolPalabra("Secretarias de Salud");
                        break;
             }
            //*********************************************************************
            //*********************************************************************
            mostrarCuadro = true;
            //*********************************************************************
            //*********************************************************************
        }
        //*********************************************************************
        //*********************************************************************
    }
    //*********************************************************
    //*********************************************************
    //*********************************************************
    private void llenarEstados() 
    {
        lstEstados = new ArrayList<>();
        lstRoles = new ArrayList<>();
        List<String> rolesUsuario = null;
        
        getLstEstados().add(new SelectItem('I', "Inactivo"));
        getLstEstados().add(new SelectItem('A', "Activo"));
        /*
        getLstRoles().add(new SelectItem("0", " "));
        getLstRoles().add(new SelectItem("1", "Comercializador"));
        getLstRoles().add(new SelectItem("2", "Distribuidor"));
        getLstRoles().add(new SelectItem("3", "Fabricante/Importador"));
        getLstRoles().add(new SelectItem("4", "Prestador de servicios de salud"));
        */
        //*****************************************************************************
        //*****************************************************************************
        //*****************************************************************************
        //Roles de usuario        //logBeanWebReactivo.info (">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //logBeanWebReactivo.info ("CONECTANDO AL EJB PARA POBLAR LOS COMBOS");
        obtenerAccesoEJB();       
        
        rolesUsuario = servicioEJBMaestrosReactivoRemote.obtenerListadoRolesUsuario();
        //logBeanWebReactivo.info (">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        int i = 0;
        String id = "";
        String nombre = "";
        
        //************************************************************
        //************************************************************
        //logBeanWebReactivo.info ("LIMPIANDO LISTA DE ROLES");
        setLstRoles(new ArrayList());
        //************************************************************
        //************************************************************
        if (rolesUsuario != null)
        {
            //logBeanWebReactivo.info ("POBLANDO EL COMBO DE ROLES DEL USUARIO EN REACTIVO VIGILANCIA");
            for (i = 0;  i < rolesUsuario.size();  i+=2)
            {
                id = (String)rolesUsuario.get(i);
                nombre = (String)rolesUsuario.get(i+1);
                //logBeanWebReactivo.info ("ID = " + id + " - NOMBRE = " + nombre);
                getLstRoles().add(new SelectItem(id,nombre));
            }
        }
        else
        {
            //logBeanWebReactivo.info ("POBLANDO EL COMBO DE ROLES DEL USUARIO - CONTINGENCIA");
            getLstRoles().add(new SelectItem("1", "INVIMA"));
            getLstRoles().add(new SelectItem("2", "Fabricante/Importador"));
            getLstRoles().add(new SelectItem("3", "Prestador de servicios de salud"));
            getLstRoles().add(new SelectItem("4", "Secretarias de Salud"));
        }
        //*****************************************************************************
        //*****************************************************************************
        //*****************************************************************************
    }
    //*********************************************************
    //*********************************************************
    //*********************************************************
    private void limpiar() 
    {
        lstUsuarios = new ArrayList<>();
        usuarioSeleccionado = new UsuariosVO();
        cedulaNombre = "";
        codigoCriterioBuscar = "";
    }
    //*********************************************************
    //*********************************************************
    //*********************************************************
    public void btnAceptar() 
    {
        logBeanWebReactivo.info ("*********************************************************");
        logBeanWebReactivo.info ("*********************************************************");
        logBeanWebReactivo.info ("Realizando activación de usuario");
        
        try
        {
            //****************************************************************************************
            //****************************************************************************************
            //****************************************************************************************
            if (mostrarCuadro) 
            {
                //procesara la informacion
                //********************************************************************************
                //********************************************************************************
                //********************************************************************************
                logBeanWebReactivo.info ("CEDULA USUARIO: " + usuarioSeleccionado.getIdentificacionPersona());
                logBeanWebReactivo.info ("NOMBRE USUARIO: " + usuarioSeleccionado.getNombrePersona());
                //UsuariosVO registro = getReactivoBO().busrcarUsuario(String.valueOf(usuarioSeleccionado.getUsuario()));
                UsuariosVO registro = getReactivoBO().buscarUsuarioCedulaYNombre(usuarioSeleccionado.getIdentificacionPersona(),usuarioSeleccionado.getNombrePersona());
                logBeanWebReactivo.info ("REGISTRO ENCONTRADO: "+ registro);
                //********************************************************************************
                //********************************************************************************
                //********************************************************************************
                if (registro != null)
                {
                    //********************************************************************************
                    //********************************************************************************
                    if (registro.getEstadoUsuario() == null)
                    {
                        registro.setEstadoUsuario("I".charAt(0));
                    }
                    //********************************************************************************
                    //********************************************************************************
                    logBeanWebReactivo.info ("************************************************************");
                    logBeanWebReactivo.info ("************************************************************");
                    logBeanWebReactivo.info ("ESTADO USUARIO COMBO = "  + usuarioSeleccionado.getEstadoUsuario());
                    logBeanWebReactivo.info ("ESTADO USUARIO BD = "  + registro.getEstadoUsuario());
                    logBeanWebReactivo.info ("************************************************************");
                    logBeanWebReactivo.info ("************************************************************");
                    //********************************************************************************
                    //********************************************************************************
                    //********************************************************************************
                        if (usuarioSeleccionado.getEstadoUsuario().equals('I') && registro.getEstadoUsuario().equals('A')) 
                        {
                            if (usuarioSeleccionado.getIdRol().equals("0")) 
                            {
                                //guarda la cuenta inactiva
                                //*******************************************************
                                //*******************************************************
                                usuarioSeleccionado.setiDRolUsuario(0);
                                String respuesta = getReactivoBO().activarUsuario(usuarioSeleccionado);
                                //*******************************************************
                                //*******************************************************
                                //*******************************************************
                                if (respuesta.equals("OK")) 
                                {
                                    logBeanWebReactivo.info ("USUARIO ACTIVADO CON EXITO");
                                    FacesContext.getCurrentInstance().addMessage("mensajesActivar", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Usuario inactivado con exito"));
                                    this.limpiar();
                                    mostrarCuadro = false;
                                    mostrarResultado = false;
                                } 
                                else 
                                {
                                    FacesContext.getCurrentInstance().addMessage("mensajesActivar", new FacesMessage(FacesMessage.SEVERITY_WARN, "Error operación", "Operación no exitosa"));
                                }
                                //*******************************************************
                                //*******************************************************
                                //*******************************************************
                            } 
                            else 
                            {
                                //*******************************************************
                                //*******************************************************
                                FacesContext.getCurrentInstance().addMessage("mensajesActivar", new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario Inactivo", "Debe dejar vacio el rol, ya que el usuario esta inactivo"));
                                //*******************************************************
                                //*******************************************************
                            }
                             //*******************************************************
                             //*******************************************************
                             //*******************************************************
                        } 
                        else 
                        {
                            //procesar activo
                            if (usuarioSeleccionado.getEstadoUsuario().equals('A') && (registro.getEstadoUsuario().equals('I') || registro.getEstadoUsuario().equals('A'))) 
                            {
                                if (!usuarioSeleccionado.getIdRol().isEmpty() && !usuarioSeleccionado.getIdRol().equals("0")) {
                                    //guardada la cuanta activa
                                    usuarioSeleccionado.setiDRolUsuario(Integer.parseInt(usuarioSeleccionado.getIdRol()));
                                    String respuesta = getReactivoBO().activarUsuario(usuarioSeleccionado);
                                    if (respuesta.equals("OK")) 
                                    {
                                        //logBeanWebReactivo.info ("Activando usuario exitosamente");
                                        this.limpiar();
                                        mostrarCuadro = false;
                                        mostrarResultado = true;
                                        logBeanWebReactivo.info ("USUARIO ACTIVADO CON EXITO CASO 2");
                                        FacesContext.getCurrentInstance().addMessage("mensajesActivar", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Usuario activado con exito"));
                                    } else 
                                    {
                                        FacesContext.getCurrentInstance().addMessage("mensajesActivar", new FacesMessage(FacesMessage.SEVERITY_WARN, "Error operación", "Operación no exitosa"));
                                    }
                                } else {
                                    FacesContext.getCurrentInstance().addMessage("mensajesActivar", new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario Activo", "Debe selecionar un Rol"));
                                }
                            } else {
                                //debe seleccionar un estado
                                FacesContext.getCurrentInstance().addMessage("mensajesActivar", new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario sin Estado", "Debe seleccionar un estado"));
                            }
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage("mensajesActivar", new FacesMessage(FacesMessage.SEVERITY_WARN, "Sin realizar búsqueda", "Realice la búsqueda y selecione una de las opciones"));
                    }
                    //********************************************************************************
                    //********************************************************************************
                }
                else
                {
                    FacesContext.getCurrentInstance().addMessage("mensajesActivar", new FacesMessage(FacesMessage.SEVERITY_WARN, "Error operación", "Operación no exitosa, puesto que el usuario no existe"));
                }
            //****************************************************************************************
            //****************************************************************************************
            //****************************************************************************************
            logBeanWebReactivo.info ("*********************************************************");
            logBeanWebReactivo.info ("*********************************************************");
        }
        
        catch (Exception errorActivacion)
        {
            FacesContext.getCurrentInstance().addMessage("mensajesActivar", new FacesMessage(FacesMessage.SEVERITY_WARN, "Error operación", "Operación no exitosa"));
            logBeanWebReactivo.info ("No se pudo realizar la activación: " + errorActivacion.getLocalizedMessage());
            errorActivacion.printStackTrace();
        }
    }
    //*********************************************************
    //*********************************************************
    //*********************************************************
    public void btnCancelar() 
    {
        this.limpiar();
        mostrarCuadro = false;
        mostrarResultado = false;
    }
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    //*********************************************************
    /**
     * @return the reactivoBO
     */
    public ReactivoBO getReactivoBO() {
        return reactivoBO;
    }

    /**
     * @param reactivoBO the reactivoBO to set
     */
    public void setReactivoBO(ReactivoBO reactivoBO) {
        this.reactivoBO = reactivoBO;
    }
    
    public String getCodigoCriterioBuscar() {
        return codigoCriterioBuscar;
    }

    public void setCodigoCriterioBuscar(String codigoCriterioBuscar) {
        this.codigoCriterioBuscar = codigoCriterioBuscar;
    }

    public boolean isRequeridoBusqueda() {
        return requeridoBusqueda;
    }

    public void setRequeridoBusqueda(boolean requeridoBusqueda) {
        this.requeridoBusqueda = requeridoBusqueda;
    }

    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

    public String getCedulaNombre() {
        return cedulaNombre;
    }

    public void setCedulaNombre(String cedulaNombre) {
        this.cedulaNombre = cedulaNombre;
    }

    public List<SelectItem> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(List<SelectItem> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    public boolean isMostrarResultado() {
        return mostrarResultado;
    }

    public void setMostrarResultado(boolean mostrarResultado) {
        this.mostrarResultado = mostrarResultado;
    }

    public Integer getCodigoNombreSeleccionado() {
        return codigoNombreSeleccionado;
    }

    public void setCodigoNombreSeleccionado(Integer codigoNombreSeleccionado) {
        this.codigoNombreSeleccionado = codigoNombreSeleccionado;
    }

    public List<SelectItem> getLstEstados() {
        return lstEstados;
    }

    public void setLstEstados(List<SelectItem> lstEstados) {
        this.lstEstados = lstEstados;
    }

    public List<SelectItem> getLstRoles() {
        return lstRoles;
    }

    public void setLstRoles(List<SelectItem> lstRoles) {
        this.lstRoles = lstRoles;
    }

    public UsuariosVO getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(UsuariosVO usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public boolean isMostrarCuadro() {
        return mostrarCuadro;
    }

    public void setMostrarCuadro(boolean mostrarCuadro) {
        this.mostrarCuadro = mostrarCuadro;
    }
    
    public void changeEstadoUsuario (ValueChangeEvent event) {
        try{
        String valor = event.getNewValue().toString();
        usuarioSeleccionado.setEstadoUsuario(valor.charAt(0));
        }catch(Exception e){logBeanWebReactivo.info ("ActivarBean.changeEstadoUsuario(), " + e.getStackTrace()[0]);}
    }
    
    public void changeRol (ValueChangeEvent event) {
        try{
        String valor = event.getNewValue().toString();
        usuarioSeleccionado.setIdRol(valor);
        }catch(Exception e){logBeanWebReactivo.info ("ActivarBean.changeRol(), " + e.getStackTrace()[0]);}
    }
    //*********************************************************
}//Fin de la clase
/*********************************************************************/
/*********************************************************************/
/*********************************************************************/
/*********************************************************************/
/*********************************************************************/
/*********************************************************************/
