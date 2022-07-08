/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.beans.masivo;
/******************************************************************/
/******************************************************************/
/******************************************************************/
/**
 *
 * @author jgutierrezme
 */
import co.gov.invima.VO.UsuariosVO;
import co.gov.invima.entities.*;
import co.gov.invima.negocio.ReactivoVigilanciaBeanLocal;
import co.gov.invima.negocio.ServicioReporteTrimestralRemote;
import co.gov.invima.service.ServiceLocator;
import co.gov.invima.util.CorreoElectronico;
import co.gov.invima.util.Fecha;
import co.gov.invima.util.HtmlValidator;
import co.gov.invima.util.PropertiesReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;
import java.util.List;
import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.primefaces.component.api.UIData;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
//import java.nio.file.Files;
/******************************************************************/
/******************************************************************/
/******************************************************************/
/******************************************************************/
/******************************************************************/
/******************************************************************/
@SessionScoped
@ManagedBean(name="fileUploadViewMasivoReactivo")
public class FileUploadViewMasivoReactivo implements Serializable
{
    private final static Logger logBeanWebReactivo = Logger.getLogger(FileUploadViewMasivoReactivo.class.getName());
    //********************************************************************
    //********************************************************************
    //********************************************************************
    @EJB
    private ServicioReporteTrimestralRemote ejbServicioRemotoReporteMasivoTrimestral;
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private Part  file;
    private List<FilaExcelMasivoReactivo> listaDatosExcel;
    private List<ValidacionTrimestral> listadoValidacionesTrimestral;
    private ServicioReporteTrimestralRemote servicioReporteTrimestral;
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private ArrayList datosCargueCorreo = new ArrayList();
    private ArrayList listaDepartamentos = new ArrayList();
    private ArrayList listaMunicipios = new ArrayList();
    private ArrayList listaDepartamentosReporte = new ArrayList();
    private ArrayList listaMunicipiosReporte = new ArrayList();
    private FilaExcelMasivoReactivo registroActual;
    private String correoReportante;
    //********************************************************************
    //********************************************************************
    private String departamento;
    private String municipio;
    private String departamentoReporte;
    private String municipioReporte;
    //********************************************************************
    //********************************************************************
    private String departamentoPaso;
    private String municipioPaso;
    private String departamentoReportePaso;
    private String municipioReportePaso;
    //********************************************************************
    //********************************************************************
    private String nivelComplejidadInstitucion;
    private String naturaleza;
    private String tipoDocumento;
    private String sexo;
    private String edadEn;
    private String tipoDispositivo;
    private String procedenciaReactivo;
    private String requiereCadFrioReactivo;
    private String catReactivo;
    private String cumpleConAlmacena;
    private String realizaRecepcionReactivo;
    private String prodCertAnalisisReactivo;
    private String servicioFunReactivo;
    private String desenlaceIncidente;
    private String tieneProgGestion;
    private String realizoAnalisisGestion;
    private String herramientaAnalisisGestion;
    private String causaEfectoAnalisisGestion;
    private String descripcionCausaEfectoGestion;
    private String causaProbableEfectoGestion;
    private String codigoCausaEfectoGestion;
    private String inicioAccionesEfectoGestion;
    private String reportoDistribGestion;
    private String profesionReportante;
    private String autorizaDivulgaReportante;
    private String tipoReportanteInfoReactivo;
    private String estadoReporteInfoReactivo;
    private String estadoGET;
    //********************************************************************
    //********************************************************************
    private ArrayList listaTiposReporte = new ArrayList();
    private ArrayList listaDesenlaces = new ArrayList();
    private ArrayList listaTiposDocumento = new ArrayList();
    private ArrayList listaDetecciones = new ArrayList();
    private ArrayList listaCargos = new ArrayList();
    private ArrayList listaTipoReportantes = new ArrayList();
    private ArrayList listaCausasProbables = new ArrayList();
    private ArrayList listaCausasProbablesCodigos = new ArrayList();
    private ArrayList listaDescripcionesCausa = new ArrayList();
    private ArrayList listaTipoDispositivos = new ArrayList();
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private String conteoEventos;
    private String conteoPaciente;
    private String conteoReportante;
    private String conteoGestionRealizada;
    private String conteoProducto;
    private String conteoInstitucion;
    private String conteoCamposComplementarios;
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private String operacion;
    private boolean habilitaGuardado;
    private String idEPSReportante;
    private String idRolUsuarioReportante;
    private String deptoReportante;
    private String idRolReportante;
    private String nombreReportante;
    //********************************************************************
    //********************************************************************
    private java.util.Date fechaRegistro;
    //********************************************************************
    //********************************************************************
    private ArrayList<Boolean> listaFilasEditadas = new ArrayList<Boolean>();
    private int indiceFilasEditadas;
    //********************************************************************
    //********************************************************************
    //********************************************************************
    
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public FileUploadViewMasivoReactivo()
    {
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("Iniciando constructor del Managed Bean Web FileUploadViewMasivoReactivo");
        //logBeanWebReactivo.info("Conectandose al contexto");
        ServiceLocator service = new ServiceLocator();
        //logBeanWebReactivo.info ("ESTADO DEL SERVICE LOCATOR = " + service);
        servicioReporteTrimestral = service.getEjbRemotoReactivoReporteTrimestral();
        //logBeanWebReactivo.info("Estado del EJB DEL REPORTE PARA EL MANAGED BEAN: " + this.servicioReporteTrimestral);
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private ArrayList armarItemsCombo (List<String> datosLista)
    {
        ArrayList items = new ArrayList();
        int i = 0;
        String id = "";
        String nombre = "";
        
        try
        {
            for (i = 0;  i < datosLista.size();  i+=2)
            {
                id = (String)datosLista.get(i);
                nombre = (String)datosLista.get(i+1);
                //logBeanWebReactivo.info ("ID = " + id + " - NOMBRE = " + nombre);
                items.add(new SelectItem(id,nombre));
            }
        }
        
        catch (Exception errorItems)
        {
            errorItems.printStackTrace();
        }
        
        return (items);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    UsuariosVO userSesion = null;
    @PostConstruct
    public void init() 
    {
        List<String> departamentos = null;
        List<String> departamentosReporte = null;
        //***********************************************************
        //***********************************************************
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sesionUsuario = request.getSession();
        String nombreUsuario = "";
        userSesion = null;
        String idRolReportante = "";
        String depto = "";
        String correoD = "";
        //***********************************************************
        //***********************************************************
        try
        {
            //**********************************************************************
            userSesion = (UsuariosVO)sesionUsuario.getAttribute("usuario");
            nombreUsuario = userSesion.getUsuario();
            idRolReportante = userSesion.getIdRol();
            depto = userSesion.getCodDepart();
            this.setNombreReportante(generarNombreAprobadorCorreo(userSesion.getUsuario(),userSesion.getNombreEmpresa()));
            this.setIdRolReportante(idRolReportante);
            //**********************************************************************
            //**********************************************************************
            if (idRolReportante != null)
            {
                if (idRolReportante.equals("1"))
                {
                    //logBeanWebReactivo.info ("USUARIO INVIMA DEPTO POR DEFECTO BOGOTA = 11");
                    depto = "11";
                }
            }
            else
            {
                    //logBeanWebReactivo.info ("USUARIO INVIMA DEPTO POR DEFECTO BOGOTA = 11");
                    depto = "11";
            }
            //**********************************************************************
            //**********************************************************************
             if (userSesion.getEmailEmpresa() == null)
            {
                correoD = generarCorreoDestinatario(userSesion.getUsuario());
            }
            else
            {
                correoD = userSesion.getEmailEmpresa();
            }
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            this.setCorreoReportante(correoD);
            this.setIdRolUsuarioReportante(idRolReportante);
            this.setDeptoReportante(depto);
            this.setListaDatosExcel(null);
            this.setListadoValidacionesTrimestral(null);
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("USUARIO EN SESION = " + nombreUsuario);
            //logBeanWebReactivo.info ("REPORTANTE QUE CARGARA LOS REGISTROS = " + this.getNombreReportante() + " en la fecha = " + new java.util.Date().toGMTString());
            //logBeanWebReactivo.info ("CORREO DEL DESTINATARIO = " + this.getCorreoReportante());
            //logBeanWebReactivo.info ("ID ROL REPORTANTE = " + this.getIdRolUsuarioReportante());
            //logBeanWebReactivo.info ("DEPARTAMENTO DEL REPORTANTE = " + this.getDeptoReportante());
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            departamentos = getServicioReporteTrimestral().obtenerListadoDepartamentos();
            this.setListaDepartamentos(armarItemsCombo(departamentos));
            departamentosReporte = getServicioReporteTrimestral().obtenerListadoDepartamentos();
            this.setListaDepartamentosReporte(armarItemsCombo(departamentosReporte));            
            //**********************************************************************
            //**********************************************************************
            this.setIndiceFilasEditadas(0);
            cargarCombosGrillaValidadora();
            //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //**********************************************************************
            //**********************************************************************
            this.setOperacion("cargueArchivo");
            this.setHabilitaGuardado(false);
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("SE CARGARÁ ARCHIVO");
        }
        //***********************************************************
        //***********************************************************
        catch (Exception errorinit)
        {
            //**************************************************************************************
            //logBeanWebReactivo.info("------------------------Error------------------------");
            //logBeanWebReactivo.info("No fue posible ejecutar el método init del Bean " + this.getClass().getCanonicalName() + ": " + errorinit.getMessage());
            //logBeanWebReactivo.info("------------------------Fin Error------------------------");
            errorinit.printStackTrace();
            //**************************************************************************************
        }
        //***********************************************************
        //***********************************************************
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private String generarNombreAprobadorCorreo (String usuario, String nombreCompleto)
    {
        String nombre = "";
        
        if (nombreCompleto == null)
        {
            nombre = "ADMINISTRADOR INVIMA " + usuario;
        }
        else
        {
            nombre = nombreCompleto;
        }
        
        return (nombre);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private String generarCorreoDestinatario (String usuario)
    {
        String correoEnvio = "";
        
        if (usuario.contains("@"))
        {
            correoEnvio = usuario;
        }
        else
        {
            correoEnvio = usuario + "@invima.gov.co";
        }
        
        //logBeanWebReactivo.info ("CORREO AL QUE SE DESPACHARA LA CONFIRMACIÓN DE CARGUE MASIVO = " + correoEnvio);
        
        return (correoEnvio);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void cargarCombosGrillaValidadora()
    {
        List<String> listaTiposReporteLocal =  null;
        List<String> listaDesenlacesLocal =  null;
        List<String> listaTiposDocumentoLocal =  null;
        List<String> listaCargosLocal =  null;
        List<String> listaTipoReportantesLocal =  null;
        List<String> listaCausasProbablesLocal =  null;
        List<String> listaCausasProbablesCodigosLocal =  null;
        List<String> listaDescripcionesCausaLocal =  null;
        List<String> listaTipoDispositivosLocal =  null;
        
        try
        {
            //*******************************************************************
            //*******************************************************************
            //*******************************************************************
            //logBeanWebReactivo.info ("*****************************************************************");
            //logBeanWebReactivo.info ("*****************************************************************");
            //logBeanWebReactivo.info ("*****************************************************************");
            //logBeanWebReactivo.info ("Armando listado de combos para la grilla validadora ");
            //*******************************************************************
            //*******************************************************************
            listaTiposReporteLocal = getServicioReporteTrimestral().obtenerListaTipoReportes();
            setListaTiposReporte(armarItemsCombo(listaTiposReporteLocal));
            //logBeanWebReactivo.info ("Lista listaTiposReporte = " + getListaTiposReporte());
            //*******************************************************************
            listaDesenlacesLocal = getServicioReporteTrimestral().obtenerListaDesenlaces();
            setListaDesenlaces(armarItemsCombo(listaDesenlacesLocal));
            //logBeanWebReactivo.info ("Lista listaDesenlaces = " + getListaDesenlaces());
            //*******************************************************************
            listaTiposDocumentoLocal = getServicioReporteTrimestral().obtenerListaTipoDocReportante();
            setListaTiposDocumento(armarItemsCombo(listaTiposDocumentoLocal));
            //logBeanWebReactivo.info ("Lista listaTiposDocumento = " + getListaTiposDocumento());
            //*******************************************************************
            listaCargosLocal = getServicioReporteTrimestral().obtenerListadoCargos();
            setListaCargos(armarItemsCombo(listaCargosLocal));
            //logBeanWebReactivo.info ("Lista listaCargos = " + getListaCargos());
            //*******************************************************************
            listaTipoReportantesLocal = getServicioReporteTrimestral().obtenerListaTipoReportantes();
            setListaTipoReportantes(armarItemsCombo(listaTipoReportantesLocal));
            //logBeanWebReactivo.info ("Lista listaTipoReportantes = " + getListaTipoReportantes());
            //*******************************************************************
            listaCausasProbablesLocal = getServicioReporteTrimestral().obtenerListaCausasProbables();
            setListaCausasProbables(armarItemsCombo(listaCausasProbablesLocal));
            //logBeanWebReactivo.info ("Lista listaCausasProbables = " + getListaCausasProbables());
            //*******************************************************************
            listaCausasProbablesCodigosLocal = getServicioReporteTrimestral().obtenerListaCausasProbablesCodigos();
            setListaCausasProbablesCodigos(armarItemsCombo(listaCausasProbablesCodigosLocal));
            //logBeanWebReactivo.info ("Lista listaCausasProbables codigos = " + getListaCausasProbablesCodigos());
            //*******************************************************************
            listaDescripcionesCausaLocal = getServicioReporteTrimestral().obtenerListaDescripcionesCausaEfecto();
            setListaDescripcionesCausa(armarItemsCombo(listaDescripcionesCausaLocal));
            //logBeanWebReactivo.info ("Lista listaDescripcionesCausaLocal = " + getListaDescripcionesCausa());
            //*******************************************************************
            listaTipoDispositivosLocal = getServicioReporteTrimestral().obtenerListaTipoDispositivos();
            setListaTipoDispositivos(armarItemsCombo(listaTipoDispositivosLocal));
            //logBeanWebReactivo.info ("Lista listaTipoDispositivos = " + getListaTipoDispositivos());
            //*******************************************************************
            //*******************************************************************
            //logBeanWebReactivo.info ("*****************************************************************");
            //logBeanWebReactivo.info ("*****************************************************************");
            //logBeanWebReactivo.info ("*****************************************************************");
            //*******************************************************************
            //*******************************************************************
            //*******************************************************************
        }
        
        catch (Exception errorcargarCombosGrillaValidadora)
        {
            //logBeanWebReactivo.info ("Error en el cargue de combos = " + errorcargarCombosGrillaValidadora.getLocalizedMessage());
            setListaTiposReporte(new ArrayList());
            setListaDesenlaces(new ArrayList());
            setListaTiposDocumento(new ArrayList());
            setListaCargos(new ArrayList());
            setListaTipoReportantes(new ArrayList());
            setListaCausasProbables(new ArrayList());
            errorcargarCombosGrillaValidadora.printStackTrace();
        }
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void onChangeDepartamento()
    {
        List<String> ciudades = null;
        //logBeanWebReactivo.info ("Obteniendo municipios del Departamento");
        String codigo = "";
        
        try
        {
            //logBeanWebReactivo.info ("Departamento ocurrencia = " + this.getDepartamento());
            //codigo = servicioReporteTrimestral.obtenerCodigoDeptoPorNombre(this.departamento);
            codigo = this.getDepartamento();
            //logBeanWebReactivo.info ("CODIGO DEPTO COMBO MUNICIPIOS = " + codigo);
            ciudades = getServicioReporteTrimestral().obtenerListadoMunicipios(codigo);
            this.setListaMunicipios(armarItemsCombo(ciudades));
            this.setDepartamentoPaso(codigo);
            //logBeanWebReactivo.info ("ID DEPTO PARA CUANDO GUARDEN LA FILA DE LA GRILLA = " + this.getDepartamentoPaso());
        }
        
        catch (Exception errorMunicipios)
        {
            errorMunicipios.printStackTrace();
        }
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void onChangeDepartamentoReporte()
    {
        List<String> ciudadesReporte = null;
        //logBeanWebReactivo.info ("Obteniendo municipios del Departamento de Reporte");
        String codigo = "";
        
        try
        {
            //logBeanWebReactivo.info ("Departamento DEL REPORTE = " + this.getDepartamentoReporte());
            //codigo = servicioReporteTrimestral.obtenerCodigoDeptoPorNombre(this.departamento);
            codigo = this.getDepartamentoReporte();
            //logBeanWebReactivo.info ("CODIGO DEPTO COMBO MUNICIPIOS REPORTE = " + codigo);
            ciudadesReporte = getServicioReporteTrimestral().obtenerListadoMunicipios(codigo);
            this.setListaMunicipiosReporte(armarItemsCombo(ciudadesReporte));
            this.setDepartamentoReportePaso(codigo);
            //logBeanWebReactivo.info ("ID DEPTO REPORTE PARA CUANDO GUARDEN LA FILA DE LA GRILLA = " + this.getDepartamentoReportePaso());
        }
        
        catch (Exception erroronChangeDepartamentoReporte)
        {
            erroronChangeDepartamentoReporte.printStackTrace();
        }
    }    
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void onEdit(RowEditEvent event) 
    {  
        List<String> ciudades = null;
        List<String> ciudades2 = null;
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        String nombreDepto = "";
        String nombreDeptoReporte = "";
        String nombreMunicipio = "";
        String nombreMunicipioReporte = "";
        //**********************************************************************************************
        String nivelComplejidadInstitucion;
        String naturaleza;
        String tipoDocumento;
        String sexo;
        String edadEn;
        String tipoDispositivo;
        String procedenciaReactivo;
        String requiereCadFrioReactivo;
        String catReactivo;
        String cumpleConAlmacena;
        String realizaRecepcionReactivo;
        String prodCertAnalisisReactivo;
        String servicioFunReactivo;
        String desenlaceIncidente;
        String tieneProgGestion;
        String realizoAnalisisGestion;
        String herramientaAnalisisGestion;
        String causaEfectoAnalisisGestion;
        String descripcionCausaEfectoGestion;
        String causaProbableEfectoGestion;
        String codigoCausaEfectoGestion;
        String inicioAccionesEfectoGestion;
        String reportoDistribGestion;
        String profesionReportante;
        String autorizaDivulgaReportante;
        String tipoReportanteInfoReactivo;
        String estadoReporteInfoReactivo;
        String estadoGET;
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        //this.departamento = ((FilaExcelMasivoTecno) event.getObject()).getDepartamento_ocurrencia();
        //logBeanWebReactivo.info ("Dato obtenido de la fila de la grilla = " + ((FilaExcelMasivoTecno) event.getObject()).getDepartamento());
        //logBeanWebReactivo.info ("DEPARTAMENTO PASO = " + this.getDepartamentoPaso());
        //logBeanWebReactivo.info ("MUNICIPIO PASO = " + this.getMunicipioPaso());
        //logBeanWebReactivo.info ("DEPARTAMENTO REPORTE PASO = " + this.getDepartamentoReportePaso());
        //logBeanWebReactivo.info ("MUNICIPIO REPORTE PASO = " + this.getMunicipioReportePaso());
        //logBeanWebReactivo.info ("nivelComplejidadInstitucion: " + this.getNivelComplejidadInstitucion());
        //logBeanWebReactivo.info ("naturaleza: " + this.getNaturaleza());
        //logBeanWebReactivo.info ("tipoDocumento: " + this.getTipoDocumento());
        //logBeanWebReactivo.info ("sexo: " + this.getSexo());
        //logBeanWebReactivo.info ("edadEn: " + this.getEdadEn());
        //logBeanWebReactivo.info ("tipoDispositivo: " + this.getTipoDispositivo());
        //logBeanWebReactivo.info ("procedenciaReactivo: " + this.getProcedenciaReactivo());
        //logBeanWebReactivo.info ("requiereCadFrioReactivo: " + this.getRequiereCadFrioReactivo());
        //logBeanWebReactivo.info ("catReactivo: " + this.getCatReactivo());
        //logBeanWebReactivo.info ("cumpleConAlmacena: " + this.getCumpleConAlmacena());
        //logBeanWebReactivo.info ("realizaRecepcionReactivo: " + this.getRealizaRecepcionReactivo());
        //logBeanWebReactivo.info ("prodCertAnalisisReactivo: " + this.getProdCertAnalisisReactivo());
        //logBeanWebReactivo.info ("servicioFunReactivo: " + this.getServicioFunReactivo());
        //logBeanWebReactivo.info ("desenlaceIncidente: " + this.getDesenlaceIncidente());
        //logBeanWebReactivo.info ("tieneProgGestion: " + this.getTieneProgGestion());
        //logBeanWebReactivo.info ("realizoAnalisisGestion: " + this.getRealizoAnalisisGestion());
        //logBeanWebReactivo.info ("herramientaAnalisisGestion: " + this.getHerramientaAnalisisGestion());
        //logBeanWebReactivo.info ("causaEfectoAnalisisGestion: " + this.getCausaEfectoAnalisisGestion());
        //logBeanWebReactivo.info ("descripcionCausaEfectoGestion: " + this.getDescripcionCausaEfectoGestion());
        //logBeanWebReactivo.info ("causaProbableEfectoGestion: " + this.getCausaProbableEfectoGestion());
        //logBeanWebReactivo.info ("codigoCausaEfectoGestion: " + this.getCodigoCausaEfectoGestion());
        //logBeanWebReactivo.info ("inicioAccionesEfectoGestion: " + this.getInicioAccionesEfectoGestion());
        //logBeanWebReactivo.info ("reportoDistribGestion: " + this.getReportoDistribGestion());
        //logBeanWebReactivo.info ("profesionReportante: " + this.getProfesionReportante());
        //logBeanWebReactivo.info ("autorizaDivulgaReportante: " + this.getAutorizaDivulgaReportante());
        //logBeanWebReactivo.info ("tipoReportanteInfoReactivo: " + this.getTipoReportanteInfoReactivo());
        //logBeanWebReactivo.info ("estadoReporteInfoReactivo: " + this.getEstadoReporteInfoReactivo());
        //logBeanWebReactivo.info ("estadoGET: " + this.getEstadoGET());
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        if (this.getDepartamentoPaso() != null)
        {
            this.setDepartamento(this.getDepartamentoPaso());
        }
        //**********************************************************************************************
        if (this.getMunicipioPaso() != null)
        {
            this.setMunicipio(this.getMunicipioPaso());
        }
        //**********************************************************************************************
        //**********************************************************************************************
        if (this.getDepartamentoReportePaso() != null)
        {
            this.setDepartamentoReporte(this.getDepartamentoReportePaso());
        }
        //**********************************************************************************************
        if (this.getMunicipioReportePaso() != null)
        {
            this.setMunicipioReporte(this.getMunicipioReportePaso());
        }
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        //logBeanWebReactivo.info ("*****************************************");
        //logBeanWebReactivo.info ("*****************************************");
        //logBeanWebReactivo.info ("DEPARTAMENTO AL GUARDAR FILA DE LA GRILLA = " + this.getDepartamento());
        //logBeanWebReactivo.info ("MUNICIPIO AL GUARDAR LA FILA DE LA GRILLA = " + this.getMunicipio());
        //logBeanWebReactivo.info ("------------------------------------------------------------------------");
        //logBeanWebReactivo.info ("DEPARTAMENTO REPORTE AL GUARDAR FILA DE LA GRILLA = " + this.getDepartamentoReporte());
        //logBeanWebReactivo.info ("MUNICIPIO REPORTE AL GUARDAR LA FILA DE LA GRILLA = " + this.getMunicipioReporte());
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        //logBeanWebReactivo.info ("DEPARTAMENTO REPORTE = " + this.departamentoReporte);
        //logBeanWebReactivo.info ("*****************************************");
        //logBeanWebReactivo.info ("*****************************************");
        //logBeanWebReactivo.info ("ACTUALIZANDO LISTA DE DEPARTAMENTOS");
        ciudades = getServicioReporteTrimestral().obtenerListadoMunicipios(this.getDepartamento());
        this.setListaMunicipios(armarItemsCombo(ciudades));
        //**********************************************************************************************
        //**********************************************************************************************
        //logBeanWebReactivo.info ("ACTUALIZANDO LISTA DE DEPARTAMENTOS REPORTE");
        ciudades2 = getServicioReporteTrimestral().obtenerListadoMunicipios(this.getDepartamentoReporte());
        this.setListaMunicipiosReporte(armarItemsCombo(ciudades2));
        //logBeanWebReactivo.info ("*****************************************");
        //logBeanWebReactivo.info ("*****************************************");
        nombreDepto = getServicioReporteTrimestral().obtenerNombreDeptoPorCodigo(this.getDepartamento());
        nombreMunicipio = getServicioReporteTrimestral().obtenerNombreMunicipioPorCodigo(this.getMunicipio());
        nombreDeptoReporte = getServicioReporteTrimestral().obtenerNombreDeptoPorCodigo(this.getDepartamentoReporte());
        nombreMunicipioReporte = getServicioReporteTrimestral().obtenerNombreMunicipioPorCodigo(this.getMunicipioReporte());
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        nivelComplejidadInstitucion = obtenerNombreComplejidad(this.getNivelComplejidadInstitucion());
        naturaleza = obtenerNaturaleza(this.getNaturaleza());
        tipoDocumento = ejbServicioRemotoReporteMasivoTrimestral.obtenerNombreTipoIdentificacionReportantePorCodigo(this.getTipoDocumento());
        sexo = obtenerNombreSexo(this.getSexo());
        edadEn = obtenerEdadEn(this.getEdadEn());
        tipoDispositivo = ejbServicioRemotoReporteMasivoTrimestral.obtenerNombreTipoDispositivo(this.getTipoDispositivo());
        procedenciaReactivo = obtenerProcedencia(this.getProcedenciaReactivo());
        requiereCadFrioReactivo = obtenerValorSiNo(this.getRequiereCadFrioReactivo());
        catReactivo = obtenerCategoria(this.getCatReactivo());
        cumpleConAlmacena = obtenerValorSiNo(this.getCumpleConAlmacena());
        realizaRecepcionReactivo = obtenerValorSiNo(this.getRealizaRecepcionReactivo());
        prodCertAnalisisReactivo = obtenerValorSiNo(this.getProdCertAnalisisReactivo());
        servicioFunReactivo = obtenerServicioAreaFuncionamiento(this.getServicioFunReactivo());
        desenlaceIncidente = ejbServicioRemotoReporteMasivoTrimestral.obtenerNombreTipoDesenlacePorCodigo(this.getDesenlaceIncidente());
        tieneProgGestion = obtenerValorSiNo(this.getTieneProgGestion());
        realizoAnalisisGestion = obtenerValorSiNo(this.getRealizoAnalisisGestion());
        herramientaAnalisisGestion = obtenerHerramientaAnalisis(this.getHerramientaAnalisisGestion());
        causaEfectoAnalisisGestion = obtenerValorSiNo(this.getCausaEfectoAnalisisGestion());
        descripcionCausaEfectoGestion = ejbServicioRemotoReporteMasivoTrimestral.obtenerNombreDescripcionCausaEfecto(this.getDescripcionCausaEfectoGestion());
        causaProbableEfectoGestion = ejbServicioRemotoReporteMasivoTrimestral.obtenerNombreCausaProbablePorCodigo(this.getCausaProbableEfectoGestion());
        //codigoCausaEfectoGestion = ejbServicioRemotoReporteMasivoTrimestral.obtenerNombreCausaProbablePorCodigo(this.getCodigoCausaEfectoGestion());
        codigoCausaEfectoGestion = this.getCodigoCausaEfectoGestion();
        inicioAccionesEfectoGestion = obtenerValorSiNo(this.getInicioAccionesEfectoGestion());
        reportoDistribGestion = obtenerValorSiNo(this.getReportoDistribGestion());
        profesionReportante = ejbServicioRemotoReporteMasivoTrimestral.obtenerNombreCargosPorCodigo(this.getProfesionReportante());
        autorizaDivulgaReportante = obtenerValorSiNo(this.getAutorizaDivulgaReportante());
        tipoReportanteInfoReactivo = ejbServicioRemotoReporteMasivoTrimestral.obtenerNombreTipoDeReportantePorCodigo(this.getTipoReportanteInfoReactivo());
        estadoReporteInfoReactivo = obtenerNombreEstadoReporte(this.getEstadoReporteInfoReactivo());
        estadoGET = obtenerNombreEstadoReporte(this.getEstadoGET());
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        //logBeanWebReactivo.info ("NOMBRE DE nivelComplejidadInstitucion : " + nivelComplejidadInstitucion);
        //logBeanWebReactivo.info ("NOMBRE DE naturaleza : " + naturaleza);
        //logBeanWebReactivo.info ("NOMBRE DE tipoDocumento : " + tipoDocumento);
        //logBeanWebReactivo.info ("NOMBRE DE sexo : " + sexo);
        //logBeanWebReactivo.info ("NOMBRE DE edadEn : " + edadEn);
        //logBeanWebReactivo.info ("NOMBRE DE tipoDispositivo : " + tipoDispositivo);
        //logBeanWebReactivo.info ("NOMBRE DE procedenciaReactivo : " + procedenciaReactivo);
        //logBeanWebReactivo.info ("NOMBRE DE requiereCadFrioReactivo : " + requiereCadFrioReactivo);
        //logBeanWebReactivo.info ("NOMBRE DE catReactivo : " + catReactivo);
        //logBeanWebReactivo.info ("NOMBRE DE cumpleConAlmacena : " + cumpleConAlmacena);
        //logBeanWebReactivo.info ("NOMBRE DE realizaRecepcionReactivo : " + realizaRecepcionReactivo);
        //logBeanWebReactivo.info ("NOMBRE DE prodCertAnalisisReactivo : " + prodCertAnalisisReactivo);
        //logBeanWebReactivo.info ("NOMBRE DE servicioFunReactivo : " + servicioFunReactivo);
        //logBeanWebReactivo.info ("NOMBRE DE desenlaceIncidente : " + desenlaceIncidente);
        //logBeanWebReactivo.info ("NOMBRE DE tieneProgGestion : " + tieneProgGestion);
        //logBeanWebReactivo.info ("NOMBRE DE realizoAnalisisGestion : " + realizoAnalisisGestion);
        //logBeanWebReactivo.info ("NOMBRE DE herramientaAnalisisGestion : " + herramientaAnalisisGestion);
        //logBeanWebReactivo.info ("NOMBRE DE causaEfectoAnalisisGestion : " + causaEfectoAnalisisGestion);
        //logBeanWebReactivo.info ("NOMBRE DE descripcionCausaEfectoGestion : " + descripcionCausaEfectoGestion);
        //logBeanWebReactivo.info ("NOMBRE DE causaProbableEfectoGestion : " + causaProbableEfectoGestion);
        //logBeanWebReactivo.info ("NOMBRE DE codigoCausaEfectoGestion : " + codigoCausaEfectoGestion);
        //logBeanWebReactivo.info ("NOMBRE DE inicioAccionesEfectoGestion : " + inicioAccionesEfectoGestion);
        //logBeanWebReactivo.info ("NOMBRE DE reportoDistribGestion : " + reportoDistribGestion);
        //logBeanWebReactivo.info ("NOMBRE DE profesionReportante : " + profesionReportante);
        //logBeanWebReactivo.info ("NOMBRE DE autorizaDivulgaReportante : " + autorizaDivulgaReportante);
        //logBeanWebReactivo.info ("NOMBRE DE tipoReportanteInfoReactivo : " + tipoReportanteInfoReactivo);
        //logBeanWebReactivo.info ("NOMBRE DE estadoReporteInfoReactivo : " + estadoReporteInfoReactivo);
        //logBeanWebReactivo.info ("NOMBRE DE estadoGET : " + estadoGET);
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        ((FilaExcelMasivoReactivo) event.getObject()).setDepartamentoInstitucion(nombreDepto);
        ((FilaExcelMasivoReactivo) event.getObject()).setCiudadInstitucion(nombreMunicipio);
        ((FilaExcelMasivoReactivo) event.getObject()).setDepartamentoReportante(nombreDeptoReporte);
        ((FilaExcelMasivoReactivo) event.getObject()).setCiudadReportante(nombreMunicipioReporte);
        //**********************************************************************************************
        ((FilaExcelMasivoReactivo) event.getObject()).setNivelComplejidadInstitucion(nivelComplejidadInstitucion);
        ((FilaExcelMasivoReactivo) event.getObject()).setNaturalezaInstitucion(naturaleza);
        ((FilaExcelMasivoReactivo) event.getObject()).setTipIdentPaciente(tipoDocumento);
        ((FilaExcelMasivoReactivo) event.getObject()).setSexoPaciente(sexo);
        ((FilaExcelMasivoReactivo) event.getObject()).setEdadEnPaciente(edadEn);
        ((FilaExcelMasivoReactivo) event.getObject()).setCodigoTipoDiagReactivo(tipoDispositivo);
        ((FilaExcelMasivoReactivo) event.getObject()).setProcedenciaReactivo(procedenciaReactivo);
        ((FilaExcelMasivoReactivo) event.getObject()).setRequiereCadFrioReactivo(requiereCadFrioReactivo);
        ((FilaExcelMasivoReactivo) event.getObject()).setCatReactivo(catReactivo);
        ((FilaExcelMasivoReactivo) event.getObject()).setCumpleConAlmacena(cumpleConAlmacena);
        ((FilaExcelMasivoReactivo) event.getObject()).setRealizaRecepcionReactivo(realizaRecepcionReactivo);
        ((FilaExcelMasivoReactivo) event.getObject()).setProdCertAnalisisReactivo(prodCertAnalisisReactivo);
        ((FilaExcelMasivoReactivo) event.getObject()).setServicioFunReactivo(servicioFunReactivo);
        ((FilaExcelMasivoReactivo) event.getObject()).setDesenlaceIncidente(desenlaceIncidente);
        ((FilaExcelMasivoReactivo) event.getObject()).setTieneProgGestion(tieneProgGestion);
        ((FilaExcelMasivoReactivo) event.getObject()).setRealizoAnalisisGestion(realizoAnalisisGestion);
        ((FilaExcelMasivoReactivo) event.getObject()).setHerramientaAnalisisGestion(herramientaAnalisisGestion);
        ((FilaExcelMasivoReactivo) event.getObject()).setCausaEfectoAnalisisGestion(causaEfectoAnalisisGestion);
        ((FilaExcelMasivoReactivo) event.getObject()).setDescripcionCausaEfectoGestion(descripcionCausaEfectoGestion);
        ((FilaExcelMasivoReactivo) event.getObject()).setCausaProbableEfectoGestion(causaProbableEfectoGestion);
        ((FilaExcelMasivoReactivo) event.getObject()).setCodigoCausaEfectoGestion(codigoCausaEfectoGestion);
        ((FilaExcelMasivoReactivo) event.getObject()).setInicioAccionesEfectoGestion(inicioAccionesEfectoGestion);
        ((FilaExcelMasivoReactivo) event.getObject()).setReportoDistribGestion(reportoDistribGestion);
        ((FilaExcelMasivoReactivo) event.getObject()).setProfesionReportante(profesionReportante);
        ((FilaExcelMasivoReactivo) event.getObject()).setAutorizaDivulgaReportante(autorizaDivulgaReportante);
        ((FilaExcelMasivoReactivo) event.getObject()).setTipoReportanteInfoReactivo(tipoReportanteInfoReactivo);
        ((FilaExcelMasivoReactivo) event.getObject()).setEstadoReporteInfoReactivo(estadoReporteInfoReactivo);
        ((FilaExcelMasivoReactivo) event.getObject()).setEstadoGET(estadoGET);
        //**********************************************************************************************
        //**********************************************************************************************
        //logBeanWebReactivo.info ("Tamaño actual del vector = " + this.getListaFilasEditadas().size());
        if (this.getListaFilasEditadas().size() > 0)
        {
            //logBeanWebReactivo.info ("La fila editada fue cerrada correctamente con registro: " + ((FilaExcelMasivoReactivo) event.getObject()).getSecuencial());
            this.setIndiceFilasEditadas(this.getIndiceFilasEditadas() - 1);
            this.getListaFilasEditadas().remove(this.getIndiceFilasEditadas());
            
            //logBeanWebReactivo.info ("TAMAÑO DEL VECTOR DE BOOLEANOS AL DAR CLICK AL CHULO: " + this.getListaFilasEditadas().size());
            //logBeanWebReactivo.info ("INDICE DE FILAS EDITADAS AL DAR CLICK AL CHULO: " + this.getIndiceFilasEditadas());
        }
        //**********************************************************************************************
        //**********************************************************************************************
        FacesMessage msg = new FacesMessage("Fila del Reporte Editada - Dispositivo " + ((FilaExcelMasivoReactivo) event.getObject()).getNombreReactivo() + "Reactivo In Vitro","Registro de la Fila No. " + ((FilaExcelMasivoReactivo) event.getObject()).getSecuencial());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void onCancel(RowEditEvent event) 
    {  
        FacesMessage msg = new FacesMessage("Fila del Reporte Eliminada");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        this.getListaDatosExcel().remove((FilaExcelMasivoReactivo) event.getObject());
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void capturarDatoFila(RowEditEvent event) 
    {  
        List<String> deptosUpdate = null;
        List<String> ciudadesUpdate = null;
        List<String> deptosReporteUpdate = null;
        List<String> ciudadesReporteUpdate = null;
        //***************************************************************
        //***************************************************************
        String deptoSeleccionadoArchivo = "";
        String municipioSeleccionadoArchivo = "";
        String deptoReporteSeleccionadoArchivo = "";
        String municipioReporteSeleccionadoArchivo = "";
        //***************************************************************
        //***************************************************************
        String nivelComplejidadInstitucionArchivo;
        String naturalezaArchivo;
        String tipoDocumentoArchivo;
        String sexoArchivo;
        String edadEnArchivo;
        String tipoDispositivoArchivo;
        String procedenciaReactivoArchivo;
        String requiereCadFrioReactivoArchivo;
        String catReactivoArchivo;
        String cumpleConAlmacenaArchivo;
        String realizaRecepcionReactivoArchivo;
        String prodCertAnalisisReactivoArchivo;
        String servicioFunReactivoArchivo;
        String desenlaceIncidenteArchivo;
        String tieneProgGestionArchivo;
        String realizoAnalisisGestionArchivo;
        String herramientaAnalisisGestionArchivo;
        String causaEfectoAnalisisGestionArchivo;
        String descripcionCausaEfectoGestionArchivo;
        String causaProbableEfectoGestionArchivo;
        String codigoCausaEfectoGestionArchivo;
        String inicioAccionesEfectoGestionArchivo;
        String reportoDistribGestionArchivo;
        String profesionReportanteArchivo;
        String autorizaDivulgaReportanteArchivo;
        String tipoReportanteInfoReactivoArchivo;
        String estadoReporteInfoReactivoArchivo;
        String estadoGETArchivo;
        //***************************************************************
        //***************************************************************
        //***************************************************************
        //***************************************************************
        //***************************************************************
        //***************************************************************
        String updateClientId = ""; 
        String updateClientIdM = "";
        String updateClientIdDR = "";
        String updateClientIdMR = "";
        //***************************************************************
        //***************************************************************
        String updateClientnivelComplejidadInstitucion;
        String updateClientnaturaleza;
        String updateClienttipoDocumento;
        String updateClientsexo;
        String updateClientedadEn;
        String updateClienttipoDispositivo;
        String updateClientprocedenciaReactivo;
        String updateClientrequiereCadFrioReactivo;
        String updateClientcatReactivo;
        String updateClientcumpleConAlmacena;
        String updateClientrealizaRecepcionReactivo;
        String updateClientprodCertAnalisisReactivo;
        String updateClientservicioFunReactivo;
        String updateClientdesenlaceIncidente;
        String updateClienttieneProgGestion;
        String updateClientrealizoAnalisisGestion;
        String updateClientherramientaAnalisisGestion;
        String updateClientcausaEfectoAnalisisGestion;
        String updateClientdescripcionCausaEfectoGestion;
        String updateClientcausaProbableEfectoGestion;
        String updateClientcodigoCausaEfectoGestion;
        String updateClientinicioAccionesEfectoGestion;
        String updateClientreportoDistribGestion;
        String updateClientprofesionReportante;
        String updateClientautorizaDivulgaReportante;
        String updateClienttipoReportanteInfoReactivo;
        String updateClientestadoReporteInfoReactivo;
        String updateClientestadoGET;
        //***************************************************************
        //***************************************************************
        //***************************************************************
        //***************************************************************
        try
        {
            this.getListaFilasEditadas().add(getIndiceFilasEditadas(),new Boolean(true));
            this.setIndiceFilasEditadas(this.getIndiceFilasEditadas() + 1);
            //logBeanWebReactivo.info ("EJECUTANDO capturarDatoFila(RowEditEvent event) ");
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            UIData table = (UIData) event.getComponent();
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            updateClientId = table.getClientId() + ":" + table.getRowIndex() + ":departamentoCombo";
            updateClientIdM = table.getClientId() + ":" + table.getRowIndex() + ":municipioCombo";
            updateClientIdDR = table.getClientId() + ":" + table.getRowIndex() + ":departamentoReporteCombo";
            updateClientIdMR = table.getClientId() + ":" + table.getRowIndex() + ":municipioReporteCombo";
            //***********************************************************************************
            //***********************************************************************************
            updateClientnivelComplejidadInstitucion = table.getClientId() + ":" + table.getRowIndex() + ":nivelComplejidadInstitucionGrilla";
            updateClientnaturaleza = table.getClientId() + ":" + table.getRowIndex() + ":naturalezaGrilla";
            updateClienttipoDocumento = table.getClientId() + ":" + table.getRowIndex() + ":tipoDocumentoGrilla";
            updateClientsexo = table.getClientId() + ":" + table.getRowIndex() + ":sexoGrilla";
            updateClientedadEn = table.getClientId() + ":" + table.getRowIndex() + ":edadEnGrilla";
            updateClienttipoDispositivo = table.getClientId() + ":" + table.getRowIndex() + ":tipoDispositivoGrilla";
            updateClientprocedenciaReactivo = table.getClientId() + ":" + table.getRowIndex() + ":procedenciaReactivoGrilla";
            updateClientrequiereCadFrioReactivo = table.getClientId() + ":" + table.getRowIndex() + ":requiereCadFrioReactivoGrilla";
            updateClientcatReactivo = table.getClientId() + ":" + table.getRowIndex() + ":catReactivoGrilla";
            updateClientcumpleConAlmacena = table.getClientId() + ":" + table.getRowIndex() + ":cumpleConAlmacenaGrilla";
            updateClientrealizaRecepcionReactivo = table.getClientId() + ":" + table.getRowIndex() + ":realizaRecepcionReactivoGrilla";
            updateClientprodCertAnalisisReactivo = table.getClientId() + ":" + table.getRowIndex() + ":prodCertAnalisisReactivoGrilla";
            updateClientservicioFunReactivo = table.getClientId() + ":" + table.getRowIndex() + ":servicioFunReactivoGrilla";
            updateClientdesenlaceIncidente = table.getClientId() + ":" + table.getRowIndex() + ":desenlaceIncidenteGrilla";
            updateClienttieneProgGestion = table.getClientId() + ":" + table.getRowIndex() + ":tieneProgGestionGrilla";
            updateClientrealizoAnalisisGestion = table.getClientId() + ":" + table.getRowIndex() + ":realizoAnalisisGestionGrilla";
            updateClientherramientaAnalisisGestion = table.getClientId() + ":" + table.getRowIndex() + ":herramientaAnalisisGestionGrilla";
            updateClientcausaEfectoAnalisisGestion = table.getClientId() + ":" + table.getRowIndex() + ":causaEfectoAnalisisGestionGrilla";
            updateClientdescripcionCausaEfectoGestion = table.getClientId() + ":" + table.getRowIndex() + ":descripcionCausaEfectoGestionGrilla";
            updateClientcausaProbableEfectoGestion = table.getClientId() + ":" + table.getRowIndex() + ":causaProbableEfectoGestionGrilla";
            updateClientcodigoCausaEfectoGestion = table.getClientId() + ":" + table.getRowIndex() + ":codigoCausaEfectoGestionGrilla";
            updateClientinicioAccionesEfectoGestion = table.getClientId() + ":" + table.getRowIndex() + ":inicioAccionesEfectoGestionGrilla";
            updateClientreportoDistribGestion = table.getClientId() + ":" + table.getRowIndex() + ":reportoDistribGestionGrilla";
            updateClientprofesionReportante = table.getClientId() + ":" + table.getRowIndex() + ":profesionReportanteGrilla";
            updateClientautorizaDivulgaReportante = table.getClientId() + ":" + table.getRowIndex() + ":autorizaDivulgaReportanteGrilla";
            updateClienttipoReportanteInfoReactivo = table.getClientId() + ":" + table.getRowIndex() + ":tipoReportanteInfoReactivoGrilla";
            updateClientestadoReporteInfoReactivo = table.getClientId() + ":" + table.getRowIndex() + ":estadoReporteInfoReactivoGrilla";
            updateClientestadoGET = table.getClientId() + ":" + table.getRowIndex() + ":estadoGETGrilla";
            
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE nivelComplejidadInstitucion: " + updateClientnivelComplejidadInstitucion);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE naturaleza: " + updateClientnaturaleza);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE tipoDocumento: " + updateClienttipoDocumento);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE sexo: " + updateClientsexo);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE edadEn: " + updateClientedadEn);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE tipoDispositivo: " + updateClienttipoDispositivo);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE procedenciaReactivo: " + updateClientprocedenciaReactivo);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE requiereCadFrioReactivo: " + updateClientrequiereCadFrioReactivo);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE catReactivo: " + updateClientcatReactivo);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE cumpleConAlmacena: " + updateClientcumpleConAlmacena);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE realizaRecepcionReactivo: " + updateClientrealizaRecepcionReactivo);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE prodCertAnalisisReactivo: " + updateClientprodCertAnalisisReactivo);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE servicioFunReactivo: " + updateClientservicioFunReactivo);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE desenlaceIncidente: " + updateClientdesenlaceIncidente);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE tieneProgGestion: " + updateClienttieneProgGestion);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE realizoAnalisisGestion: " + updateClientrealizoAnalisisGestion);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE herramientaAnalisisGestion: " + updateClientherramientaAnalisisGestion);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE causaEfectoAnalisisGestion: " + updateClientcausaEfectoAnalisisGestion);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE descripcionCausaEfectoGestion: " + updateClientdescripcionCausaEfectoGestion);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE causaProbableEfectoGestion: " + updateClientcausaProbableEfectoGestion);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE codigoCausaEfectoGestion: " + updateClientcodigoCausaEfectoGestion);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE inicioAccionesEfectoGestion: " + updateClientinicioAccionesEfectoGestion);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE reportoDistribGestion: " + updateClientreportoDistribGestion);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE profesionReportante: " + updateClientprofesionReportante);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE autorizaDivulgaReportante: " + updateClientautorizaDivulgaReportante);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE tipoReportanteInfoReactivo: " + updateClienttipoReportanteInfoReactivo);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE estadoReporteInfoReactivo: " + updateClientestadoReporteInfoReactivo);
            //logBeanWebReactivo.info ("ID DE LA FILA ESCOGIDA  DE estadoGET: " + updateClientestadoGET);
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //logBeanWebReactivo.info ("-------------------------------------------------------------");
            this.setRegistroActual((FilaExcelMasivoReactivo) event.getObject());
            //***********************************************************************************
            //***********************************************************************************
            deptoSeleccionadoArchivo = this.getRegistroActual().getDepartamentoInstitucion();
            municipioSeleccionadoArchivo = this.getRegistroActual().getCiudadInstitucion();
            deptoReporteSeleccionadoArchivo = this.getRegistroActual().getDepartamentoReportante();
            municipioReporteSeleccionadoArchivo = this.getRegistroActual().getCiudadReportante();
            //***********************************************************************************
            //***********************************************************************************
            nivelComplejidadInstitucionArchivo = this.getRegistroActual().getNivelComplejidadInstitucion();
            naturalezaArchivo = this.getRegistroActual().getNaturalezaInstitucion();
            tipoDocumentoArchivo = this.getRegistroActual().getTipIdentPaciente();
            sexoArchivo = this.getRegistroActual().getSexoPaciente();
            edadEnArchivo = this.getRegistroActual().getEdadEnPaciente();
            tipoDispositivoArchivo = this.getRegistroActual().getCodigoTipoDiagReactivo();
            procedenciaReactivoArchivo = this.getRegistroActual().getProcedenciaReactivo();
            requiereCadFrioReactivoArchivo = this.getRegistroActual().getRequiereCadFrioReactivo();
            catReactivoArchivo = this.getRegistroActual().getCatReactivo();
            cumpleConAlmacenaArchivo = this.getRegistroActual().getCumpleConAlmacena();
            realizaRecepcionReactivoArchivo = this.getRegistroActual().getRealizaRecepcionReactivo();
            prodCertAnalisisReactivoArchivo = this.getRegistroActual().getProdCertAnalisisReactivo();
            servicioFunReactivoArchivo = this.getRegistroActual().getServicioFunReactivo();
            desenlaceIncidenteArchivo = this.getRegistroActual().getDesenlaceIncidente();
            tieneProgGestionArchivo = this.getRegistroActual().getTieneProgGestion();
            realizoAnalisisGestionArchivo = this.getRegistroActual().getRealizoAnalisisGestion();
            herramientaAnalisisGestionArchivo = this.getRegistroActual().getHerramientaAnalisisGestion();
            causaEfectoAnalisisGestionArchivo = this.getRegistroActual().getCausaEfectoAnalisisGestion();
            descripcionCausaEfectoGestionArchivo = this.getRegistroActual().getDescripcionCausaEfectoGestion();
            causaProbableEfectoGestionArchivo = this.getRegistroActual().getCausaProbableEfectoGestion();
            codigoCausaEfectoGestionArchivo = this.getRegistroActual().getCodigoCausaEfectoGestion();
            inicioAccionesEfectoGestionArchivo = this.getRegistroActual().getInicioAccionesEfectoGestion();
            reportoDistribGestionArchivo = this.getRegistroActual().getReportoDistribGestion();
            profesionReportanteArchivo = this.getRegistroActual().getProfesionReportante();
            autorizaDivulgaReportanteArchivo = this.getRegistroActual().getAutorizaDivulgaReportante();
            tipoReportanteInfoReactivoArchivo = this.getRegistroActual().getTipoReportanteInfoReactivo();
            estadoReporteInfoReactivoArchivo = this.getRegistroActual().getEstadoReporteInfoReactivo();
            estadoGETArchivo = this.getRegistroActual().getEstadoGET();
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //logBeanWebReactivo.info ("ID DEPTO TRAIDO AL HACER CLIC PARA EDITAR LA GRILLA = " + deptoSeleccionadoArchivo);
            //logBeanWebReactivo.info ("ID MUNICIPIO TRAIDO AL HACER CLIC PARA EDITAR LA GRILLA = " + municipioSeleccionadoArchivo);
            //logBeanWebReactivo.info ("ID DEPTO REPORTE TRAIDO AL HACER CLIC PARA EDITAR LA GRILLA = " + deptoReporteSeleccionadoArchivo);
            //logBeanWebReactivo.info ("ID MUNICIPIO REPORTE TRAIDO AL HACER CLIC PARA EDITAR LA GRILLA = " + municipioReporteSeleccionadoArchivo);
            //***********************************************************************************
            //logBeanWebReactivo.info ("ID nivelComplejidadInstitucionArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + nivelComplejidadInstitucionArchivo);
            //logBeanWebReactivo.info ("ID naturalezaArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + naturalezaArchivo);
            //logBeanWebReactivo.info ("ID tipoDocumentoArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + tipoDocumentoArchivo);
            //logBeanWebReactivo.info ("ID sexoArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + sexoArchivo);
            //logBeanWebReactivo.info ("ID edadEnArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + edadEnArchivo);
            //logBeanWebReactivo.info ("ID tipoDispositivoArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + tipoDispositivoArchivo);
            //logBeanWebReactivo.info ("ID procedenciaReactivoArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + procedenciaReactivoArchivo);
            //logBeanWebReactivo.info ("ID requiereCadFrioReactivoArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + requiereCadFrioReactivoArchivo);
            //logBeanWebReactivo.info ("ID catReactivoArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + catReactivoArchivo);
            //logBeanWebReactivo.info ("ID cumpleConAlmacenaArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + cumpleConAlmacenaArchivo);
            //logBeanWebReactivo.info ("ID realizaRecepcionReactivoArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + realizaRecepcionReactivoArchivo);
            //logBeanWebReactivo.info ("ID prodCertAnalisisReactivoArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + prodCertAnalisisReactivoArchivo);
            //logBeanWebReactivo.info ("ID servicioFunReactivoArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + servicioFunReactivoArchivo);
            //logBeanWebReactivo.info ("ID desenlaceIncidenteArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + desenlaceIncidenteArchivo);
            //logBeanWebReactivo.info ("ID tieneProgGestionArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + tieneProgGestionArchivo);
            //logBeanWebReactivo.info ("ID realizoAnalisisGestionArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + realizoAnalisisGestionArchivo);
            //logBeanWebReactivo.info ("ID herramientaAnalisisGestionArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + herramientaAnalisisGestionArchivo);
            //logBeanWebReactivo.info ("ID causaEfectoAnalisisGestionArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + causaEfectoAnalisisGestionArchivo);
            //logBeanWebReactivo.info ("ID descripcionCausaEfectoGestionArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + descripcionCausaEfectoGestionArchivo);
            //logBeanWebReactivo.info ("ID causaProbableEfectoGestionArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + causaProbableEfectoGestionArchivo);
            //logBeanWebReactivo.info ("ID codigoCausaEfectoGestionArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + codigoCausaEfectoGestionArchivo);
            //logBeanWebReactivo.info ("ID inicioAccionesEfectoGestionArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + inicioAccionesEfectoGestionArchivo);
            //logBeanWebReactivo.info ("ID reportoDistribGestionArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + reportoDistribGestionArchivo);
            //logBeanWebReactivo.info ("ID profesionReportanteArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + profesionReportanteArchivo);
            //logBeanWebReactivo.info ("ID autorizaDivulgaReportanteArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + autorizaDivulgaReportanteArchivo);
            //logBeanWebReactivo.info ("ID tipoReportanteInfoReactivoArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + tipoReportanteInfoReactivoArchivo);
            //logBeanWebReactivo.info ("ID estadoReporteInfoReactivoArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + estadoReporteInfoReactivoArchivo);
            //logBeanWebReactivo.info ("ID estadoGETArchivoTRAIDO AL HACER CLIC PARA EDITAR EN LA GRILLA: " + estadoGETArchivo);
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            if (this.getDepartamento() == null)
            {
                //logBeanWebReactivo.info ("Seteando id departamento cuando es null");
                this.setDepartamento(getServicioReporteTrimestral().obtenerCodigoDeptoPorNombre(deptoSeleccionadoArchivo));
                //this.departamento = deptoSeleccionadoArchivo;
            }
            else
            {
                //logBeanWebReactivo.info ("Seteando id depto cuando no es null");
                this.setDepartamento(getServicioReporteTrimestral().obtenerCodigoDeptoPorNombre(deptoSeleccionadoArchivo));
            }
            //***********************************************************************************
            //***********************************************************************************
            if (this.getMunicipio() == null)
            {
                //logBeanWebReactivo.info ("Seteando id municipio cuando es null");
                this.setMunicipio(getServicioReporteTrimestral().obtenerCodigoMunicipioPorNombre(this.getDepartamento(), municipioSeleccionadoArchivo));
                //this.municipio = municipioSeleccionadoArchivo;
            }
            else
            {
                //logBeanWebReactivo.info ("Seteando id municipio cuando no es null");
                this.setMunicipio(getServicioReporteTrimestral().obtenerCodigoMunicipioPorNombre(this.getDepartamento(), municipioSeleccionadoArchivo));
            }
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            if (this.getDepartamentoReporte() == null)
            {
                //logBeanWebReactivo.info ("Seteando id departamento reporte cuando es null");
                this.setDepartamentoReporte(getServicioReporteTrimestral().obtenerCodigoDeptoPorNombre(deptoReporteSeleccionadoArchivo));
                //this.departamento = deptoSeleccionadoArchivo;
            }
            else
            {
                //logBeanWebReactivo.info ("Seteando id depto reporte cuando no es null");
                this.setDepartamentoReporte(getServicioReporteTrimestral().obtenerCodigoDeptoPorNombre(deptoReporteSeleccionadoArchivo));
            }
            //***********************************************************************************
            //***********************************************************************************
            if (this.getMunicipioReporte() == null)
            {
                //logBeanWebReactivo.info ("Seteando id municipio reporte cuando es null");
                this.setMunicipioReporte(getServicioReporteTrimestral().obtenerCodigoMunicipioPorNombre(this.getDepartamentoReporte(), municipioSeleccionadoArchivo));
                //this.municipio = municipioSeleccionadoArchivo;
            }
            else
            {
                //logBeanWebReactivo.info ("Seteando id municipio reporte cuando no es null");
                this.setMunicipioReporte(getServicioReporteTrimestral().obtenerCodigoMunicipioPorNombre(this.getDepartamentoReporte(), municipioSeleccionadoArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            if (this.getNivelComplejidadInstitucion() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE nivelComplejidadInstitucionArchivo cuando es null");
                this.setNivelComplejidadInstitucion(obtenerIdComplejidad(nivelComplejidadInstitucionArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE nivelComplejidadInstitucionArchivo cuando no es null");
                this.setNivelComplejidadInstitucion(obtenerIdComplejidad(nivelComplejidadInstitucionArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getNaturaleza() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE naturalezaArchivo cuando es null");
                this.setNaturaleza(obtenerIdNaturaleza(naturalezaArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE naturalezaArchivo cuando es no es null");
                this.setNaturaleza(obtenerIdNaturaleza(naturalezaArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getTipoDocumento() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE tipoDocumentoArchivo cuando es null");
                this.setTipoDocumento(ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoTipoIdentificacionReportante(tipoDocumentoArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE tipoDocumentoArchivo cuando no es null");
                this.setTipoDocumento(ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoTipoIdentificacionReportante(tipoDocumentoArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getSexo() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE sexoArchivo cuando es null");
                this.setSexo(obtenerIdSexo(sexoArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE sexoArchivo cuando no es null");
                this.setSexo(obtenerIdSexo(sexoArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getEdadEn() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE edadEnArchivo cuando es null");
                this.setEdadEn(obtenerIdEdadEn(edadEnArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE edadEnArchivo cuando no es null");
                this.setEdadEn(obtenerIdEdadEn(edadEnArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getTipoDispositivo() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE tipoDispositivoArchivo cuando es null");
                this.setTipoDispositivo(ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoTipoDispositivo(tipoDispositivoArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE tipoDispositivoArchivo cuando no es null");
                this.setTipoDispositivo(ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoTipoDispositivo(tipoDispositivoArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getProcedenciaReactivo() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE procedenciaReactivoArchivo cuando es null");
                this.setProcedenciaReactivo(obtenerIdProcedencia(procedenciaReactivoArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE procedenciaReactivoArchivo cuando no es null");
                this.setProcedenciaReactivo(obtenerIdProcedencia(procedenciaReactivoArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getRequiereCadFrioReactivo() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE requiereCadFrioReactivoArchivo cuando es null");
                this.setRequiereCadFrioReactivo(obtenerIdValorSiNo(requiereCadFrioReactivoArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE requiereCadFrioReactivoArchivo cuando no es null");
                this.setRequiereCadFrioReactivo(obtenerIdValorSiNo(requiereCadFrioReactivoArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getCatReactivo() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE catReactivoArchivo cuando es null");
                this.setCatReactivo(obtenerIdCategoria(catReactivoArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE catReactivoArchivo cuando no es null");
                this.setCatReactivo(obtenerIdCategoria(catReactivoArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getCumpleConAlmacena() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE cumpleConAlmacenaArchivo cuando es null");
                this.setCumpleConAlmacena(obtenerIdValorSiNo(cumpleConAlmacenaArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE cumpleConAlmacenaArchivo cuando no es null");
                this.setCumpleConAlmacena(obtenerIdValorSiNo(cumpleConAlmacenaArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getRealizaRecepcionReactivo() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE realizaRecepcionReactivoArchivo cuando es null");
                this.setRealizaRecepcionReactivo(obtenerIdValorSiNo(realizaRecepcionReactivoArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE realizaRecepcionReactivoArchivo cuando no es null");
                this.setRealizaRecepcionReactivo(obtenerIdValorSiNo(realizaRecepcionReactivoArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getProdCertAnalisisReactivo() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE prodCertAnalisisReactivoArchivo cuando es null");
                this.setProdCertAnalisisReactivo(obtenerIdValorSiNo(prodCertAnalisisReactivoArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE prodCertAnalisisReactivoArchivo cuando no es null");
                this.setProdCertAnalisisReactivo(obtenerIdValorSiNo(prodCertAnalisisReactivoArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getServicioFunReactivo() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE servicioFunReactivoArchivo cuando es null");
                this.setServicioFunReactivo(obtenerIdServicioAreaFuncionamiento(servicioFunReactivoArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE servicioFunReactivoArchivo cuando no es null");
                this.setServicioFunReactivo(obtenerIdServicioAreaFuncionamiento(servicioFunReactivoArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getDesenlaceIncidente() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE desenlaceIncidenteArchivo cuando es null");
                this.setDesenlaceIncidente(ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoTipoDesenlace(desenlaceIncidenteArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE desenlaceIncidenteArchivo cuando no es null");
                this.setDesenlaceIncidente(ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoTipoDesenlace(desenlaceIncidenteArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getTieneProgGestion() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE tieneProgGestionArchivo cuando es null");
                this.setTieneProgGestion(obtenerIdValorSiNo(tieneProgGestionArchivo));
            } 
            else 
            {     
                this.setTieneProgGestion(obtenerIdValorSiNo(tieneProgGestionArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getRealizoAnalisisGestion() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE realizoAnalisisGestionArchivo cuando es null");
                this.setRealizoAnalisisGestion(obtenerIdValorSiNo(realizoAnalisisGestionArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE realizoAnalisisGestionArchivo cuando no es null");
                this.setRealizoAnalisisGestion(obtenerIdValorSiNo(realizoAnalisisGestionArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getHerramientaAnalisisGestion() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE herramientaAnalisisGestionArchivo cuando es null");
                this.setHerramientaAnalisisGestion(obtenerIdHerramientaAnalisis(herramientaAnalisisGestionArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE herramientaAnalisisGestionArchivo cuando no es null");
                this.setHerramientaAnalisisGestion(obtenerIdHerramientaAnalisis(herramientaAnalisisGestionArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getCausaEfectoAnalisisGestion() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE causaEfectoAnalisisGestionArchivo cuando es null");
                this.setCausaEfectoAnalisisGestion(obtenerIdValorSiNo(causaEfectoAnalisisGestionArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE causaEfectoAnalisisGestionArchivo cuando no es null");
                this.setCausaEfectoAnalisisGestion(obtenerIdValorSiNo(causaEfectoAnalisisGestionArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getDescripcionCausaEfectoGestion() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE descripcionCausaEfectoGestionArchivo cuando es null");
                this.setDescripcionCausaEfectoGestion(ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoDescripcionCausaEfecto(descripcionCausaEfectoGestionArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE descripcionCausaEfectoGestionArchivo cuando no es null");
                this.setDescripcionCausaEfectoGestion(ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoDescripcionCausaEfecto(descripcionCausaEfectoGestionArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getCausaProbableEfectoGestion() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE causaProbableEfectoGestionArchivo cuando es null");
                this.setCausaProbableEfectoGestion(ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoCausaProbable(causaProbableEfectoGestionArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE causaProbableEfectoGestionArchivo cuando no es null");
                this.setCausaProbableEfectoGestion(ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoCausaProbable(causaProbableEfectoGestionArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getCodigoCausaEfectoGestion() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE codigoCausaEfectoGestionArchivo cuando es null");
                this.setCodigoCausaEfectoGestion(codigoCausaEfectoGestionArchivo);
                //this.setCodigoCausaEfectoGestion(ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoCausaProbable(codigoCausaEfectoGestionArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE codigoCausaEfectoGestionArchivo cuando no es null");
                this.setCodigoCausaEfectoGestion(codigoCausaEfectoGestionArchivo);
                //this.setCodigoCausaEfectoGestion(ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoCausaProbable(codigoCausaEfectoGestionArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getInicioAccionesEfectoGestion() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE inicioAccionesEfectoGestionArchivo cuando es null");
                this.setInicioAccionesEfectoGestion(obtenerIdValorSiNo(inicioAccionesEfectoGestionArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE inicioAccionesEfectoGestionArchivo cuando no es null");
                this.setInicioAccionesEfectoGestion(obtenerIdValorSiNo(inicioAccionesEfectoGestionArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getReportoDistribGestion() == null)
            {      
                    //logBeanWebReactivo.info ("SETEANDO ID DE reportoDistribGestionArchivo cuando es null");
                    this.setReportoDistribGestion(obtenerIdValorSiNo(reportoDistribGestionArchivo));
            } 
            else 
            {     
                    //logBeanWebReactivo.info ("SETEANDO ID DE reportoDistribGestionArchivo cuando no es null");
                    this.setReportoDistribGestion(obtenerIdValorSiNo(reportoDistribGestionArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getProfesionReportante() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE profesionReportanteArchivo cuando es null");
                this.setProfesionReportante(ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoCargos(profesionReportanteArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE profesionReportanteArchivo cuando no es null");
                this.setProfesionReportante(ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoCargos(profesionReportanteArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getAutorizaDivulgaReportante() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE autorizaDivulgaReportanteArchivo cuando es null");
                this.setAutorizaDivulgaReportante(obtenerIdValorSiNo(autorizaDivulgaReportanteArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE autorizaDivulgaReportanteArchivo cuando no es null");
                this.setAutorizaDivulgaReportante(obtenerIdValorSiNo(autorizaDivulgaReportanteArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getTipoReportanteInfoReactivo() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE tipoReportanteInfoReactivoArchivo cuando es null");
                this.setTipoReportanteInfoReactivo(ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoTipoDeReportante(tipoReportanteInfoReactivoArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE tipoReportanteInfoReactivoArchivo cuando no es null");
                this.setTipoReportanteInfoReactivo(ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoTipoDeReportante(tipoReportanteInfoReactivoArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getEstadoReporteInfoReactivo() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE estadoReporteInfoReactivoArchivo cuando es null");
                this.setEstadoReporteInfoReactivo(obtenerIdEstadoReporte(estadoReporteInfoReactivoArchivo));
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE estadoReporteInfoReactivoArchivo cuando no es null");
                this.setEstadoReporteInfoReactivo(obtenerIdEstadoReporte(estadoReporteInfoReactivoArchivo));
            }
            //****************************************************************************
            //****************************************************************************
            if (this.getEstadoGET() == null)
            {      
                //logBeanWebReactivo.info ("SETEANDO ID DE estadoGETArchivo cuando es null");
                if (estadoGETArchivo != null)
                {
                    this.setEstadoGET(obtenerIdEstadoReporte(estadoGETArchivo));
                }
            } 
            else 
            {     
                //logBeanWebReactivo.info ("SETEANDO ID DE estadoGETArchivo cuando no es null");
                if (estadoGETArchivo != null)
                {
                    this.setEstadoGET(obtenerIdEstadoReporte(estadoGETArchivo));
                }
            }
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //logBeanWebReactivo.info ("-------------------------------------------------------------");
            //logBeanWebReactivo.info ("DEPARTAMENTO ESCOGIDO = " + this.getDepartamento());
            deptosUpdate = getServicioReporteTrimestral().obtenerListadoDepartamentos();
            this.setListaDepartamentos(armarItemsCombo(deptosUpdate));
            //logBeanWebReactivo.info ("Tamano lista deptos = " + this.getListaDepartamentos().size());
            ciudadesUpdate = getServicioReporteTrimestral().obtenerListadoMunicipios(this.getDepartamento());
            this.setListaMunicipios(armarItemsCombo(ciudadesUpdate));
            //logBeanWebReactivo.info ("Tamaño lista municipios = " + this.getListaMunicipios().size());
            //****************************************************************************
            //****************************************************************************
            //logBeanWebReactivo.info ("DEPARTAMENTO REPORTE ESCOGIDO = " + this.getDepartamentoReporte());
            deptosReporteUpdate = getServicioReporteTrimestral().obtenerListadoDepartamentos();
            this.setListaDepartamentosReporte(armarItemsCombo(deptosReporteUpdate));
            //logBeanWebReactivo.info ("Tamano lista deptos reporte = " + this.getListaDepartamentosReporte().size());
            ciudadesReporteUpdate = getServicioReporteTrimestral().obtenerListadoMunicipios(this.getDepartamentoReporte());
            this.setListaMunicipiosReporte(armarItemsCombo(ciudadesReporteUpdate));
            //logBeanWebReactivo.info ("Tamaño lista municipios reporte = " + this.getListaMunicipiosReporte().size());
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            
            
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            ((FilaExcelMasivoReactivo) event.getObject()).setDepartamentoInstitucion(this.getDepartamento());
            ((FilaExcelMasivoReactivo) event.getObject()).setCiudadInstitucion(this.getMunicipio());
            ((FilaExcelMasivoReactivo) event.getObject()).setDepartamentoReportante(this.getDepartamentoReporte());
            ((FilaExcelMasivoReactivo) event.getObject()).setCiudadReportante(this.getMunicipioReporte());
            //****************************************************************************
            //****************************************************************************
            ((FilaExcelMasivoReactivo) event.getObject()).setNivelComplejidadInstitucion(this.getNivelComplejidadInstitucion());
            ((FilaExcelMasivoReactivo) event.getObject()).setNaturalezaInstitucion(this.getNaturaleza());
            ((FilaExcelMasivoReactivo) event.getObject()).setTipIdentPaciente(this.getTipoDocumento());
            ((FilaExcelMasivoReactivo) event.getObject()).setSexoPaciente(this.getSexo());
            ((FilaExcelMasivoReactivo) event.getObject()).setEdadEnPaciente(this.getEdadEn());
            ((FilaExcelMasivoReactivo) event.getObject()).setCodigoTipoDiagReactivo(this.getTipoDispositivo());
            ((FilaExcelMasivoReactivo) event.getObject()).setProcedenciaReactivo(this.getProcedenciaReactivo());
            ((FilaExcelMasivoReactivo) event.getObject()).setRequiereCadFrioReactivo(this.getRequiereCadFrioReactivo());
            ((FilaExcelMasivoReactivo) event.getObject()).setCatReactivo(this.getCatReactivo());
            ((FilaExcelMasivoReactivo) event.getObject()).setCumpleConAlmacena(this.getCumpleConAlmacena());
            ((FilaExcelMasivoReactivo) event.getObject()).setRealizaRecepcionReactivo(this.getRealizaRecepcionReactivo());
            ((FilaExcelMasivoReactivo) event.getObject()).setProdCertAnalisisReactivo(this.getProdCertAnalisisReactivo());
            ((FilaExcelMasivoReactivo) event.getObject()).setServicioFunReactivo(this.getServicioFunReactivo());
            ((FilaExcelMasivoReactivo) event.getObject()).setDesenlaceIncidente(this.getDesenlaceIncidente());
            ((FilaExcelMasivoReactivo) event.getObject()).setTieneProgGestion(this.getTieneProgGestion());
            ((FilaExcelMasivoReactivo) event.getObject()).setRealizoAnalisisGestion(this.getRealizoAnalisisGestion());
            ((FilaExcelMasivoReactivo) event.getObject()).setHerramientaAnalisisGestion(this.getHerramientaAnalisisGestion());
            ((FilaExcelMasivoReactivo) event.getObject()).setCausaEfectoAnalisisGestion(this.getCausaEfectoAnalisisGestion());
            ((FilaExcelMasivoReactivo) event.getObject()).setDescripcionCausaEfectoGestion(this.getDescripcionCausaEfectoGestion());
            ((FilaExcelMasivoReactivo) event.getObject()).setCausaProbableEfectoGestion(this.getCausaProbableEfectoGestion());
            ((FilaExcelMasivoReactivo) event.getObject()).setCodigoCausaEfectoGestion(this.getCodigoCausaEfectoGestion());
            ((FilaExcelMasivoReactivo) event.getObject()).setInicioAccionesEfectoGestion(this.getInicioAccionesEfectoGestion());
            ((FilaExcelMasivoReactivo) event.getObject()).setReportoDistribGestion(this.getReportoDistribGestion());
            ((FilaExcelMasivoReactivo) event.getObject()).setProfesionReportante(this.getProfesionReportante());
            ((FilaExcelMasivoReactivo) event.getObject()).setAutorizaDivulgaReportante(this.getAutorizaDivulgaReportante());
            ((FilaExcelMasivoReactivo) event.getObject()).setTipoReportanteInfoReactivo(this.getTipoReportanteInfoReactivo());
            ((FilaExcelMasivoReactivo) event.getObject()).setEstadoReporteInfoReactivo(this.getEstadoReporteInfoReactivo());
            ((FilaExcelMasivoReactivo) event.getObject()).setEstadoGET(this.getEstadoGET());            
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //logBeanWebReactivo.info ("-------------------------------------------------------------");
            //logBeanWebReactivo.info ("Actualizando combos en la fila de la grilla");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientId);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientIdM);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientIdDR);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientIdMR);
            //****************************************************************************
            //****************************************************************************
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientnivelComplejidadInstitucion);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientnaturaleza);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClienttipoDocumento);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientsexo);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientedadEn);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClienttipoDispositivo);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientprocedenciaReactivo);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientrequiereCadFrioReactivo);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientcatReactivo);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientcumpleConAlmacena);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientrealizaRecepcionReactivo);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientprodCertAnalisisReactivo);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientservicioFunReactivo);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientdesenlaceIncidente);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClienttieneProgGestion);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientrealizoAnalisisGestion);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientherramientaAnalisisGestion);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientcausaEfectoAnalisisGestion);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientdescripcionCausaEfectoGestion);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientcausaProbableEfectoGestion);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientcodigoCausaEfectoGestion);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientinicioAccionesEfectoGestion);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientreportoDistribGestion);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientprofesionReportante);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientautorizaDivulgaReportante);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClienttipoReportanteInfoReactivo);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientestadoReporteInfoReactivo);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientestadoGET);
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //logBeanWebReactivo.info ("TAMAÑO DE VECTOR DE FILAS EDITADAS: " + this.getListaFilasEditadas().size());
            //logBeanWebReactivo.info ("INDICE DEL FILAS EDITADAS: " + this.getIndiceFilasEditadas());
            //****************************************************************************
            //****************************************************************************
            //****************************************************************************
            //logBeanWebReactivo.info ("-------------------------------------------------------------");
            //logBeanWebReactivo.info ("-------------------------------------------------------------");
            //logBeanWebReactivo.info ("-------------------------------------------------------------");
            //logBeanWebReactivo.info ("-------------------------------------------------------------");
        }
        
        catch (Exception error)
        {
            //logBeanWebReactivo.info ("Error de edicion de fila = " + error.getLocalizedMessage());
            error.printStackTrace();
        }
    }  
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerIdServicioAreaFuncionamiento (String codigo)    
    {
        String valor = "";
        int i = 0;
        
        String [] categorias =
        {
            "Laboratorio clinico","1",
            "Laboratorio salud pública","2",
            "Servicio transfusional","3",
            "Banco de Sangre","4",
            "Banco de Tejidos","5",
            "Banco de Gametos","6"
        };
        
        for (i = 0;  i < categorias.length;  i+=2)
        {
            if (categorias[i].equals(codigo))
            {
                valor = categorias[i+1];
                break;
            }
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerServicioAreaFuncionamiento (String codigo)    
    {
        String valor = "";
        int i = 0;
        
        String [] categorias =
        {
            "1","Laboratorio clinico",
            "2","Laboratorio salud pública",
            "3","Servicio transfusional",
            "4","Banco de Sangre",
            "5","Banco de Tejidos",
            "6","Banco de Gametos"
        };
        
        for (i = 0;  i < categorias.length;  i+=2)
        {
            if (categorias[i].equals(codigo))
            {
                valor = categorias[i+1];
                break;
            }
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerIdHerramientaAnalisis (String codigo)    
    {
        String valor = "";
        int i = 0;
        
        String [] categorias =
        {
            "Protocolo de Londres","1",
            "AMFE","2",
            "Espina de pescado","3",
            "Otro/cual","4"
        };
        
        for (i = 0;  i < categorias.length;  i+=2)
        {
            if (categorias[i].equals(codigo))
            {
                valor = categorias[i+1];
                break;
            }
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerHerramientaAnalisis (String codigo)    
    {
        String valor = "";
        int i = 0;
        
        String [] categorias =
        {
            "1","Protocolo de Londres",
            "2","AMFE",
            "3","Espina de pescado",
            "4","Otro/cual"
        };
        
        for (i = 0;  i < categorias.length;  i+=2)
        {
            if (categorias[i].equals(codigo))
            {
                valor = categorias[i+1];
                break;
            }
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerCategoria (String codigo)    
    {
        String valor = "";
        
        if (codigo.equals("1"))
        {
            valor = "Categoría I";
        }
        else
        if (codigo.equals("2"))
        {
            valor = "Categoría II";
        }
        else
        {
            valor = "Categoría III";
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerNaturaleza (String codigo)    
    {
        String valor = "";
        
        if (codigo.equals("PR"))
        {
            valor = "Privada";
        }
        else
        if (codigo.equals("PU"))
        {
            valor = "Pública";
        }
        else
        {
            valor = "Mixta";
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerNombreComplejidad (String codigo) 
    {
        String valor = "";
        
        if (codigo.equals("1"))
        {
            valor = "Alto";
        }
        else
        if (codigo.equals("2"))
        {
            valor = "Medio";
        }
        else
        if (codigo.equals("3"))
        {
            valor = "Bajo";
        }
        else
        {
            valor = "N/A";
        }
        
        return (valor);
    }    
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerIdComplejidad (String codigo) 
    {
        String valor = "";
        
        if (codigo.equals("Alto"))
        {
            valor = "1";
        }
        else
        if (codigo.equals("Medio"))
        {
            valor = "2";
        }
        else
        if (codigo.equals("Bajo"))
        {
            valor = "3";
        }
        else
        {
            valor = "4";
        }
        
        return (valor);
    }    
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerNombreSexo (String codigo) 
    {
        String valor = "";
        
        if (codigo.equals("M"))
        {
            valor = "Masculino";
        }
        else
        if (codigo.equals("F"))
        {
            valor = "Femenino";
        }
        else
        {
            valor = "Sin dato";
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerEdadEn (String codigo) 
    { 
        String valor = "";
        
        if (codigo.equals("A"))
        {
            valor = "Años";
        }
        else
        if (codigo.equals("M"))
        {
            valor = "Meses";
        }
        else
        if (codigo.equals("S"))
        {
            valor = "Semanas";
        }
        else
        if (codigo.equals("D"))
        {
            valor = "Días";
        }
        else
        {
            valor = "Sin dato";
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerValorSiNo (String codigo) 
    { 
        String valor = "";
        
        if (codigo.equals("S"))
        {
            valor = "Si";
        }
        else
        {
            valor = "No";
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerProcedencia (String codigo) 
    { 
        String valor = "";
        
        if (codigo.equals("N"))
        {
            valor = "Nacional";
        }
        else
        {
            valor = "Importado";
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerNombreClasificacion (String codigo)
    {
        String nombre = "";
        
        switch (codigo)
        {
            case "1":
                nombre = "Evento adverso serio";
                break;
            case "2":        
                nombre = "Evento adverso no serio";
                break;
            case "3":
                nombre = "Incidente adverso serio";
                break;
            case "4":
                nombre = "Incidente adverso no serio";
                break;
        }
        
        return (nombre);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String consultarConsecutivoPreRDIVSistema() 
    {
        ServiceLocator service = new ServiceLocator();
        ReactivoVigilanciaBeanLocal reporteEjb = service.getEJBReactivo();
        ArrayList datos = reporteEjb.obtenerConsecutivos();
        String consecutivo = "";
        Parametros p = (Parametros) datos.get(0);
        //Se requiere nuevo parámetro porque se está tomando del parametro consec_bpc
        int i = (int)p.getConsecBpc();
        String a = new Integer(i + 1).toString();
        if (a.length() < 5) {
            for (int j = 0; a.length() < 5; j++) {
                a = "0" + a;
            }
        }
        consecutivo = a;
        
        //Actualizar el valor del parametro una vez generado el siguiente secuencial para el PRERDIV
        p.setConsecBpc((long) i + 1);
        reporteEjb.actualizarParametros(p);

        consecutivo = "PRDIV" + Fecha.formateaFecha(Fecha.crearFecha(), "yyMM") + consecutivo;
        return consecutivo;
    }
    //********************************************************************
   //********************************************************************
    //********************************************************************
    public String obtenerNombreEstadoReporte (String codigo) 
    { 
        String valor = "";
        
        if (codigo.equals("S"))
        {
            valor = "S";
        }
        else
        if (codigo.equals("A"))
        {
            valor = "A";
        }
        else
        if (codigo.equals("C"))
        {
            valor = "C";
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerIdNaturaleza (String codigo)    
    {
        String valor = "";
        
        if (codigo.equals("Privada"))
        {
            valor = "PR";
        }
        else
        if (codigo.equals("Pública"))
        {
            valor = "PU";
        }
        else
        {
            valor = "MI";
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerIdSexo(String codigo) 
    { 
        String valor = "";
        
        if (codigo.equals("Masculino"))
        {
            valor = "M";
        }
        else
        if (codigo.equals("Femenino"))
        {
            valor = "F";
        }
        else
        {
            valor = "N";
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerIdEdadEn(String codigo) 
    { 
        String valor = "";
        
        if (codigo.toUpperCase().equals("AÑOS"))
        {
            valor = "A";
        }
        else
        if (codigo.toUpperCase().equals("MESES"))
        {
            valor = "M";
        }
        else
        if (codigo.toUpperCase().equals("SEMANAS"))
        {
            valor = "S";
        }
        else
        if (codigo.toUpperCase().equals("DÍAS"))
        {
            valor = "D";
        }
        else
        {
            valor = "N";
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerIdValorSiNo(String codigo) 
    {
        String valor = "";
        
        if (codigo.equals("Si"))
        {
            valor = "S";
        }
        else
        {
            valor = "N";
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerIdProcedencia(String codigo) 
    {
        String valor = "";
        
        if (codigo.equals("Nacional"))
        {
            valor = "N";
        }
        else
        {
            valor = "I";
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerIdClasificacion (String codigo)
    {
        String id = "";
        
        switch (codigo)
        {
            case "Evento adverso serio":
                id = "1";
                break;
            case "Evento adverso no serio":
                id = "2";
                break;
            case "Incidente adverso serio":
                id = "3";
                break;
            case "Incidente adverso no serio":
                id = "4";
                break;
        }
        
        //logBeanWebReactivo.info ("ID DE CLASIFICACION OBTENIDO = " + id);
        
        return (id);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerIdEstadoReporte(String codigo) 
    { 
        String valor = "";
        
        if (codigo.equals("S"))
        {
            valor = "S";
        }
        else
        if (codigo.equals("A"))
        {
            valor = "A";
        }
        else
        if (codigo.equals("C"))
        {
            valor = "C";
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerIdCategoria (String codigo)    
    {
        String valor = "";
        
        if (codigo.equals("Categoría I"))
        {
            valor = "1";
        }
        else
        if (codigo.equals("Categoría II"))
        {
            valor = "2";
        }
        else
        {
            valor = "3";
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************

    
    
    
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public List<FilaExcelMasivoReactivo> generarCargueGrilla (java.util.ArrayList<String> datos)
    {
        List<FilaExcelMasivoReactivo> registros = null;
        int i = 0;
        FilaExcelMasivoReactivo registroFila = null;
        registros = new java.util.ArrayList<FilaExcelMasivoReactivo>();
        String depto = "";
        String municipio = "";
        
        String dato0 = "";
        String dato1 = "";
        String dato2 = "";
        String dato3 = "";
        String dato4 = "";
        String dato5 = "";
        String dato6 = "";
        String dato7 = "";
        String dato8 = "";
        String dato9 = "";
        String dato10 = "";
        String dato11 = "";
        String dato12 = "";
        String dato13 = "";
        String dato14 = "";
        String dato15 = "";
        String dato16 = "";
        String dato17 = "";
        String dato18 = "";
        String dato19 = "";
        String dato20 = "";
        String dato21 = "";
        String dato22 = "";
        String dato23 = "";
        String dato24 = "";
        String dato25 = "";
        String dato26 = "";
        String dato27 = "";
        String dato28 = "";
        String dato29 = "";
        String dato30 = "";
        String dato31 = "";
        String dato32 = "";
        String dato33 = "";
        String dato34 = "";
        String dato35 = "";
        String dato36 = "";
        String dato37 = "";
        String dato38 = "";
        String dato39 = "";
        String dato40 = "";
        String dato41 = "";
        String dato42 = "";
        String dato43 = "";
        String dato44 = "";
        String dato45 = "";
        String dato46 = "";
        String dato47 = "";
        String dato48 = "";
        String dato49 = "";
        String dato50 = "";
        String dato51 = "";
        String dato52 = "";
        String dato53 = "";
        String dato54 = "";
        String dato55 = "";
        String dato56 = "";
        String dato57 = "";
        String dato58 = "";
        String dato59 = "";
        String dato60 = "";
        String dato61 = "";
        String dato62 = "";
        String dato63 = "";
        String dato64 = "";
        String dato65 = "";
        String dato66 = "";
        int secuencial = 1;

        for (i = 0;  i < datos.size();  i+=66)
        {
            dato0 = String.valueOf(secuencial);
            dato1 = (String)datos.get(i+1);
            dato2 = (String)datos.get(i+2);
            dato3 = (String)datos.get(i+3);
            dato4 = (String)datos.get(i+4);
            dato5 = (String)datos.get(i+5);
            dato6 = (String)datos.get(i+6);
            dato7 = (String)datos.get(i+7);
            dato8 = (String)datos.get(i+8);
            dato9 = (String)datos.get(i+9);
            dato10 = (String)datos.get(i+10);
            dato11 = (String)datos.get(i+11);
            dato12 = (String)datos.get(i+12);
            dato13 = (String)datos.get(i+13);
            dato14 = (String)datos.get(i+14);
            dato15 = (String)datos.get(i+15);
            dato16 = (String)datos.get(i+16);
            dato17 = (String)datos.get(i+17);
            dato18 = (String)datos.get(i+18);
            dato19 = (String)datos.get(i+19);
            dato20 = (String)datos.get(i+20);
            dato21 = (String)datos.get(i+21);
            dato22 = (String)datos.get(i+22);
            dato23 = (String)datos.get(i+23);
            dato24 = (String)datos.get(i+24);
            dato25 = (String)datos.get(i+25);
            dato26 = (String)datos.get(i+26);
            dato27 = (String)datos.get(i+27);
            dato28 = (String)datos.get(i+28);
            dato29 = (String)datos.get(i+29);
            dato30 = (String)datos.get(i+30);
            dato31 = (String)datos.get(i+31);
            dato32 = (String)datos.get(i+32);
            dato33 = (String)datos.get(i+33);
            dato34 = (String)datos.get(i+34);
            dato35 = (String)datos.get(i+35);
            dato36 = (String)datos.get(i+36);
            dato37 = (String)datos.get(i+37);
            dato38 = (String)datos.get(i+38);
            dato39 = (String)datos.get(i+39);
            dato40 = (String)datos.get(i+40);
            dato41 = (String)datos.get(i+41);
            dato42 = (String)datos.get(i+42);
            dato43 = (String)datos.get(i+43);
            dato44 = (String)datos.get(i+44);
            dato45 = (String)datos.get(i+45);
            dato46 = (String)datos.get(i+46);
            dato47 = (String)datos.get(i+47);
            dato48 = (String)datos.get(i+48);
            dato49 = (String)datos.get(i+49);
            dato50 = (String)datos.get(i+50);
            dato51 = (String)datos.get(i+51);
            dato52 = (String)datos.get(i+52);
            dato53 = (String)datos.get(i+53);
            dato54 = (String)datos.get(i+54);
            dato55 = (String)datos.get(i+55);
            dato56 = (String)datos.get(i+56);
            dato57 = (String)datos.get(i+57);
            dato58 = (String)datos.get(i+58);
            dato59 = (String)datos.get(i+59);
            dato60 = (String)datos.get(i+60);
            dato61 = (String)datos.get(i+61);
            dato62 = (String)datos.get(i+62);
            dato63 = (String)datos.get(i+63);
            dato64 = (String)datos.get(i+64);
            dato65 = (String)datos.get(i+65);
            //dato66 = (String)datos.get(i+65);
            //System.out.println (dato0 + " - " + dato1 + " - " + dato2 + " - " + dato3 + " - " + dato4 + " - " + dato5);
            //***************************************************
            //***************************************************
            //***************************************************
            registroFila = new FilaExcelMasivoReactivo(
                    dato0,
                    dato1,
                    dato2,
                    dato3,
                    dato4,
                    dato5,
                    dato6,
                    dato7,
                    dato8,
                    dato9,
                    dato10,
                    dato11,
                    dato12,
                    dato13,
                    dato14,
                    dato15,
                    dato16,
                    dato17,
                    dato18,
                    dato19,
                    dato20,
                    dato21,
                    dato22,
                    dato23,
                    dato24,
                    dato25,
                    dato26,
                    dato27,
                    dato28,
                    dato29,
                    dato30,
                    dato31,
                    dato32,
                    dato33,
                    dato34,
                    dato35,
                    dato36,
                    dato37,
                    dato38,
                    dato39,
                    dato40,
                    dato41,
                    dato42,
                    dato43,
                    dato44,
                    dato45,
                    dato46,
                    dato47,
                    dato48,
                    dato49,
                    dato50,
                    dato51,
                    dato52,
                    dato53,
                    dato54,
                    dato55,
                    dato56,
                    dato57,
                    dato58,
                    dato59,
                    dato60,
                    dato61,
                    dato62,
                    dato63,
                    dato64,
                    dato65
            );
            //***************************************************
            //***************************************************
            //***************************************************
            registros.add(registroFila);
            secuencial++;
        }
        
        return (registros);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void subirArchivo() throws IOException 
    {
        //logBeanWebReactivo.info ("Archivo de ReactivoVigilancia enviado al MB = " + this.getFile());
        CargadorDatosExcel manejadorArchivo = null;
        java.util.ArrayList<String> datos = null;
        
        try
        {
            //**********************************************************************************
            //**********************************************************************************
            //**********************************************************************************
            if (getFile() != null) 
            {
                    manejadorArchivo = new CargadorDatosExcel();
                    String prefix = FilenameUtils.getBaseName(getFile().getSubmittedFileName()); 
                    String suffix = FilenameUtils.getExtension(getFile().getSubmittedFileName());
                    //logBeanWebReactivo.info ("prefix = " + prefix);
                    //logBeanWebReactivo.info ("suffix = " + suffix);

                    if (suffix.equals("xls") || suffix.equals("xlsx"))
                    {
                        
                        if (suffix.equals("xls"))
                        {
                            //logBeanWebTecno.info ("Procesando archivo de Office antiguo");
                            datos = manejadorArchivo.obtenerDatosArchivoCargueExcelAntiguo(getFile().getInputStream());
                        }
                        else
                        {
                            //logBeanWebTecno.info ("Procesando archivo de Office actual");
                            datos = manejadorArchivo.obtenerDatosArchivoCargue(getFile().getInputStream());
                        }
                        //logBeanWebReactivo.info ("***************************************************************");
                        //logBeanWebReactivo.info ("***************************************************************");
                        //File save = File.createTempFile(prefix + "-", "." + suffix, LOCATION);
                        //Files.write(save.toPath(), getFile().getContents());
                        // Add success message here.
                        imprimirDatosExcel(datos);
                        this.setListaDatosExcel(generarCargueGrilla(datos));
                        //*****************************************************************
                        //*****************************************************************
                        this.listaFilasEditadas = new java.util.ArrayList<Boolean>();
                        this.indiceFilasEditadas = 0;
                        //logBeanWebReactivo.info ("TAMAÑO DEL VECTOR DE BOOLEANOS: " + this.getListaFilasEditadas().size());
                        //logBeanWebReactivo.info ("INDICE DE FILAS EDITADAS: " + this.getIndiceFilasEditadas());
                        //*****************************************************************
                        //*****************************************************************
                        //logBeanWebReactivo.info ("ARCHIVO EXITOSAMENTE CARGADO EN LA GRILLA");
                        //logBeanWebReactivo.info ("***************************************************************");
                        //logBeanWebReactivo.info ("***************************************************************");
                        FacesMessage message = new FacesMessage("Exitoso", getFile().getSubmittedFileName()+ " fue cargado exitosamente en la Grilla de Edición");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    }
                    else
                    {
                        this.setListaDatosExcel(new java.util.ArrayList<FilaExcelMasivoReactivo>());
                        FacesMessage message = new FacesMessage("Error", getFile().getSubmittedFileName() + " no puede ser cargado, puesto que no es un archivo de Excel Válido");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    }
                }
            //**********************************************************************************
            //**********************************************************************************
            //**********************************************************************************
        }//Fin del try
        
        catch (Exception errorArchivo)
        {
            this.setListaDatosExcel(new java.util.ArrayList<FilaExcelMasivoReactivo>());
            FacesMessage message = new FacesMessage("Error", getFile().getSubmittedFileName() + " no puede ser cargado, verifique sus datos.  \n Verifique que la cantidad de columnas en el archivo por cada fila esté completa");
            FacesContext.getCurrentInstance().addMessage(null, message);
            errorArchivo.printStackTrace();
        }
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void imprimirDatosExcel (java.util.ArrayList<String> datos)
    {
        String linea = "";
        
            for (int i = 0;  i < datos.size();  i+=66)
            {
                linea =
                    (String)datos.get(i+0) + ";" +
                    (String)datos.get(i+1) + ";" +
                    (String)datos.get(i+2) + ";" +
                    (String)datos.get(i+3) + ";" +
                    (String)datos.get(i+4) + ";" +
                    (String)datos.get(i+5) + ";" +
                    (String)datos.get(i+6) + ";" +
                    (String)datos.get(i+7) + ";" +
                    (String)datos.get(i+8) + ";" +
                    (String)datos.get(i+9) + ";" +
                    (String)datos.get(i+10) + ";" +
                    (String)datos.get(i+11) + ";" +
                    (String)datos.get(i+12) + ";" +
                    (String)datos.get(i+13) + ";" +
                    (String)datos.get(i+14) + ";" +
                    (String)datos.get(i+15) + ";" +
                    (String)datos.get(i+16) + ";" +
                    (String)datos.get(i+17) + ";" +
                    (String)datos.get(i+18) + ";" +
                    (String)datos.get(i+19) + ";" +
                    (String)datos.get(i+20) + ";" +
                    (String)datos.get(i+21) + ";" +
                    (String)datos.get(i+22) + ";" +
                    (String)datos.get(i+23) + ";" +
                    (String)datos.get(i+24) + ";" +
                    (String)datos.get(i+25) + ";" +
                    (String)datos.get(i+26) + ";" +
                    (String)datos.get(i+27) + ";" +
                    (String)datos.get(i+28) + ";" +
                    (String)datos.get(i+29) + ";" +
                    (String)datos.get(i+30) + ";" +
                    (String)datos.get(i+31) + ";" +
                    (String)datos.get(i+32) + ";" +
                    (String)datos.get(i+33) + ";" +
                    (String)datos.get(i+34) + ";" +
                    (String)datos.get(i+35) + ";" +
                    (String)datos.get(i+36) + ";" +
                    (String)datos.get(i+37) + ";" +
                    (String)datos.get(i+38) + ";" +
                    (String)datos.get(i+39) + ";" +
                    (String)datos.get(i+40) + ";" +
                    (String)datos.get(i+41) + ";" +
                    (String)datos.get(i+42) + ";" +
                    (String)datos.get(i+43) + ";" +
                    (String)datos.get(i+44) + ";" +
                    (String)datos.get(i+45) + ";" +
                    (String)datos.get(i+46) + ";" +
                    (String)datos.get(i+47) + ";" +
                    (String)datos.get(i+48) + ";" +
                    (String)datos.get(i+49) + ";" +
                    (String)datos.get(i+50) + ";" +
                    (String)datos.get(i+51) + ";" +
                    (String)datos.get(i+52) + ";" +
                    (String)datos.get(i+53) + ";" +
                    (String)datos.get(i+54) + ";" +
                    (String)datos.get(i+55) + ";" +
                    (String)datos.get(i+56) + ";" +
                    (String)datos.get(i+57) + ";" +
                    (String)datos.get(i+58) + ";" +
                    (String)datos.get(i+59) + ";" +
                    (String)datos.get(i+60) + ";" +
                    (String)datos.get(i+61) + ";" +
                    (String)datos.get(i+62) + ";" +
                    (String)datos.get(i+63) + ";" +
                    (String)datos.get(i+64) + ";" +
                    (String)datos.get(i+65);
                //**************************************************
                //**************************************************
                logBeanWebReactivo.info (linea);
            }
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void validarArchivo()
    {
        //*****************************************************************************
        //*****************************************************************************
        String secuencial;
        String nombreInstitucion;
        String departamentoInstitucion;
        String ciudadInstitucion;
        String direccionInstitucion;
        String nitInstitucion;
        String nivelComplejidadInstitucion;
        String naturalezaInstitucion;
        String tipIdentPaciente;
        String identificacionPaciente;
        String sexoPaciente;
        String edadPaciente;
        String edadEnPaciente;
        String nombreReactivo;
        String registroSanReactivo;
        String expedienteReactivo;
        String codUnicoReactivo;
        String codigoTipoDiagReactivo;
        String loteReactivo;
        String referenciaReactivo;
        java.util.Date fechaVenReactivo;
        String procedenciaReactivo;
        String requiereCadFrioReactivo;
        String temperaturaReactivo;
        String catReactivo;
        String cumpleConAlmacena;
        String realizaRecepcionReactivo;
        String prodCertAnalisisReactivo;
        String nombreFabReactivo;
        String nombreImportadorReactivo;
        String servicioFunReactivo;
        java.util.Date fechaIncidente;
        String descripcionIncidente;
        String desenlaceIncidente;
        String otroIncidente;
        String tieneProgGestion;
        String realizoAnalisisGestion;
        String herramientaAnalisisGestion;
        String otroAnalisisGestion;
        String causaEfectoAnalisisGestion;
        String causaProbableEfectoGestion;
        String descripcionCausaEfectoGestion;
        String codigoCausaEfectoGestion;
        String inicioAccionesEfectoGestion;
        String accionesGestion;
        String reportoDistribGestion;
        java.util.Date fechaReporteImportadorGestion;
        java.util.Date fechaEnvioDistribuidorGestion;
        String nombreReportante;
        String profesionReportante;
        String organizacionReportante;
        String direccionReportante;
        String telefonoReportante;
        String departamentoReportante;
        String ciudadReportante;
        String correoReportante;
        java.util.Date fechaNotIPSReportante;
        String autorizaDivulgaReportante;
        String tipoReportanteInfoReactivo;
        String estadoReporteInfoReactivo;
        java.util.Date fechaGET;
        String gestionET;
        String estadoGET;
        java.util.Date fechaGInvima;
        String observacionesGInvima;        
        //*****************************************************************************
        //*****************************************************************************
        //*****************************************************************************
        String codigoExpedienteConsultado = "";
        //*****************************************************************************
        //*****************************************************************************
        //*****************************************************************************
        //*****************************************************************************
        int i = 0;
        FilaExcelMasivoReactivo registro = null;
        String expediente = "";
        //*****************************************************************************
        //*****************************************************************************
        //*****************************************************************************
        java.util.Date fechaSistema = new java.util.Date();
        //*****************************************************************************
        //*****************************************************************************
        //*****************************************************************************
        //*****************************************************************************
        String codDepto1 = "";
        String codMunicipio1 = "";
        String codDepto2 = "";
        String codMunicipio2 = "";
        //*****************************************************************************
        //*****************************************************************************
        String codigonivelComplejidadInstitucion;
        String codigonaturaleza;
        String codigotipoDocumento;
        String codigosexo;
        String codigoedadEn;
        String codigotipoDispositivo;
        String codigoprocedenciaReactivo;
        String codigorequiereCadFrioReactivo;
        String codigocatReactivo;
        String codigocumpleConAlmacena;
        String codigorealizaRecepcionReactivo;
        String codigoprodCertAnalisisReactivo;
        String codigoservicioFunReactivo;
        String codigodesenlaceIncidente;
        String codigotieneProgGestion;
        String codigorealizoAnalisisGestion;
        String codigoherramientaAnalisisGestion;
        String codigocausaEfectoAnalisisGestion;
        String codigodescripcionCausaEfectoGestion;
        String codigocausaProbableEfectoGestion;
        String codigocodigoCausaEfectoGestion;
        String codigoinicioAccionesEfectoGestion;
        String codigoreportoDistribGestion;
        String codigoprofesionReportante;
        String codigoautorizaDivulgaReportante;
        String codigotipoReportanteInfoReactivo;
        String codigoestadoReporteInfoReactivo;
        String codigoestadoGET;
        
        String codigoCausaValidar;
        //*****************************************************************************
        //*****************************************************************************
        //*****************************************************************************
        String mensajeSalida = "";
        String nombreDeptoSesion = "";
        String nombreDeptoReportado = "";
        //*****************************************************************************
        //*****************************************************************************
        boolean estaBienFila = false;
        int contadorColumnasFila = 0;
        boolean estaBienArchivo = false;
        int contadorFilasArchivo = 0;
        ValidacionTrimestral registroValidacion = null;
        int contadorErrores = 0;
        boolean valorEsNumero = false;
        boolean sePuedeValidar = false;
        int valorCodigoCausaP = 0;
        //*****************************************************************************
        //*****************************************************************************
        try
        {
            //logBeanWebReactivo.info ("*************************************************************");
            //logBeanWebReactivo.info ("*************************************************************");
            //logBeanWebReactivo.info ("*************************************************************");
            //logBeanWebReactivo.info ("*************************************************************");
            //***************************************************************************
            //***************************************************************************
            this.setListadoValidacionesTrimestral(new ArrayList<ValidacionTrimestral>());
            //logBeanWebReactivo.info ("ESTADO DE VALIDACIONES = " + this.getListadoValidacionesTrimestral());
            //***************************************************************************
            //***************************************************************************
            //***************************************************************************
            //***************************************************************************
            //***************************************************************************
            if (this.getListaFilasEditadas().size() > 0)
            {
                    //logBeanWebReactivo.info ("No se puede validar porque hay filas sin cerrar");
                    sePuedeValidar = false;
                    //***************************************************
                    //***************************************************
                    //logBeanWebReactivo.info ("TAMAÑO DEL VECTOR DE BOOLEANOS: " + this.getListaFilasEditadas().size());
                    //logBeanWebReactivo.info ("INDICE DE FILAS EDITADAS: " + this.getIndiceFilasEditadas());
                    //***************************************************
                    //***************************************************
            }
            else
            {
                    //logBeanWebReactivo.info ("Se puede validar la grilla porque hay filas cerradas");
                    sePuedeValidar = true;
            }
            //***************************************************************************
            //***************************************************************************
            //***************************************************************************
            
            //***************************************************************************
            //***************************************************************************
            //***************************************************************************
            if (sePuedeValidar == true)
            {
                    //logBeanWebReactivo.info ("YA SE PUEDEN VALIDAR LOS REGISTROS EN: " + new java.util.Date());
                    //***************************************************************************
                    //***************************************************************************
                    //***************************************************************************
                    for (i = 0;  i < this.getListaDatosExcel().size();  i++)
                    {
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        contadorColumnasFila = 0;
                        //***************************************************************************
                        //***************************************************************************
                        registro = (FilaExcelMasivoReactivo)this.getListaDatosExcel().get(i);
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        secuencial = registro.getSecuencial();
                        nombreInstitucion = registro.getNombreInstitucion();
                        departamentoInstitucion = registro.getDepartamentoInstitucion();
                        ciudadInstitucion = registro.getCiudadInstitucion();
                        direccionInstitucion = registro.getDireccionInstitucion();
                        nitInstitucion = registro.getNitInstitucion();
                        nivelComplejidadInstitucion = registro.getNivelComplejidadInstitucion();
                        naturalezaInstitucion = registro.getNaturalezaInstitucion();
                        tipIdentPaciente = registro.getTipIdentPaciente();
                        identificacionPaciente = registro.getIdentificacionPaciente();
                        sexoPaciente = registro.getSexoPaciente();
                        edadPaciente = registro.getEdadPaciente();
                        edadEnPaciente = registro.getEdadEnPaciente();
                        nombreReactivo = registro.getNombreReactivo();
                        registroSanReactivo = registro.getRegistroSanReactivo();
                        expedienteReactivo = registro.getExpedienteReactivo();
                        codUnicoReactivo = registro.getCodUnicoReactivo();
                        codigoTipoDiagReactivo = registro.getCodigoTipoDiagReactivo();
                        loteReactivo = registro.getLoteReactivo();
                        referenciaReactivo = registro.getReferenciaReactivo();
                        fechaVenReactivo = registro.getFechaVenReactivo();
                        procedenciaReactivo = registro.getProcedenciaReactivo();
                        requiereCadFrioReactivo = registro.getRequiereCadFrioReactivo();
                        temperaturaReactivo = registro.getTemperaturaReactivo();
                        catReactivo = registro.getCatReactivo();
                        cumpleConAlmacena = registro.getCumpleConAlmacena();
                        realizaRecepcionReactivo = registro.getRealizaRecepcionReactivo();
                        prodCertAnalisisReactivo = registro.getProdCertAnalisisReactivo();
                        nombreFabReactivo = registro.getNombreFabReactivo();
                        nombreImportadorReactivo = registro.getNombreImportadorReactivo();
                        servicioFunReactivo = registro.getServicioFunReactivo();
                        fechaIncidente = registro.getFechaIncidente();
                        descripcionIncidente = registro.getDescripcionIncidente();
                        desenlaceIncidente = registro.getDesenlaceIncidente();
                        otroIncidente = registro.getOtroIncidente();
                        tieneProgGestion = registro.getTieneProgGestion();
                        realizoAnalisisGestion = registro.getRealizoAnalisisGestion();
                        herramientaAnalisisGestion = registro.getHerramientaAnalisisGestion();
                        otroAnalisisGestion = registro.getOtroAnalisisGestion();
                        causaEfectoAnalisisGestion = registro.getCausaEfectoAnalisisGestion();
                        causaProbableEfectoGestion = registro.getCausaProbableEfectoGestion();
                        descripcionCausaEfectoGestion = registro.getDescripcionCausaEfectoGestion();
                        codigoCausaEfectoGestion = registro.getCodigoCausaEfectoGestion();
                        inicioAccionesEfectoGestion = registro.getInicioAccionesEfectoGestion();
                        accionesGestion = registro.getAccionesGestion();
                        reportoDistribGestion = registro.getReportoDistribGestion();
                        fechaReporteImportadorGestion = registro.getFechaReporteImportadorGestion();
                        fechaEnvioDistribuidorGestion = registro.getFechaEnvioDistribuidorGestion();
                        nombreReportante = registro.getNombreReportante();
                        profesionReportante = registro.getProfesionReportante();
                        organizacionReportante = registro.getOrganizacionReportante();
                        direccionReportante = registro.getDireccionReportante();
                        telefonoReportante = registro.getTelefonoReportante();
                        departamentoReportante = registro.getDepartamentoReportante();
                        ciudadReportante = registro.getCiudadReportante();
                        correoReportante = registro.getCorreoReportante();
                        fechaNotIPSReportante = registro.getFechaNotIPSReportante();
                        autorizaDivulgaReportante = registro.getAutorizaDivulgaReportante();
                        tipoReportanteInfoReactivo = registro.getTipoReportanteInfoReactivo();
                        estadoReporteInfoReactivo = registro.getEstadoReporteInfoReactivo();
                        fechaGET = registro.getFechaGET();
                        gestionET = registro.getGestionET();
                        estadoGET = registro.getEstadoGET();
                        fechaGInvima = registro.getFechaGInvima();
                        observacionesGInvima = registro.getObservacionesGInvima();
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //logBeanWebReactivo.info ("*****************************************************************************");
                        //logBeanWebReactivo.info ("*****************************************************************************");
                        //logBeanWebReactivo.info ("*****************************************************************************");
                        /*
                        //logBeanWebReactivo.info ("Trayendo registro de la grilla " + (i+1));
                        //logBeanWebReactivo.info ("Campo secuencial: " +secuencial);
                        //logBeanWebReactivo.info ("Campo nombreInstitucion: " +nombreInstitucion);
                        //logBeanWebReactivo.info ("Campo departamentoInstitucion: " +departamentoInstitucion);
                        //logBeanWebReactivo.info ("Campo ciudadInstitucion: " +ciudadInstitucion);
                        //logBeanWebReactivo.info ("Campo direccionInstitucion: " +direccionInstitucion);
                        //logBeanWebReactivo.info ("Campo nitInstitucion: " +nitInstitucion);
                        //logBeanWebReactivo.info ("Campo nivelComplejidadInstitucion: " +nivelComplejidadInstitucion);
                        //logBeanWebReactivo.info ("Campo naturalezaInstitucion: " +naturalezaInstitucion);
                        //logBeanWebReactivo.info ("Campo tipIdentPaciente: " +tipIdentPaciente);
                        //logBeanWebReactivo.info ("Campo identificacionPaciente: " +identificacionPaciente);
                        //logBeanWebReactivo.info ("Campo sexoPaciente: " +sexoPaciente);
                        //logBeanWebReactivo.info ("Campo edadPaciente: " +edadPaciente);
                        //logBeanWebReactivo.info ("Campo edadEnPaciente: " +edadEnPaciente);
                        //logBeanWebReactivo.info ("Campo nombreReactivo: " +nombreReactivo);
                        //logBeanWebReactivo.info ("Campo registroSanReactivo: " +registroSanReactivo);
                        //logBeanWebReactivo.info ("Campo expedienteReactivo: " +expedienteReactivo);
                        //logBeanWebReactivo.info ("Campo codUnicoReactivo: " +codUnicoReactivo);
                        //logBeanWebReactivo.info ("Campo codigoTipoDiagReactivo: " +codigoTipoDiagReactivo);
                        //logBeanWebReactivo.info ("Campo loteReactivo: " +loteReactivo);
                        //logBeanWebReactivo.info ("Campo referenciaReactivo: " +referenciaReactivo);
                        //logBeanWebReactivo.info ("Campo fechaVenReactivo: " +fechaVenReactivo);
                        //logBeanWebReactivo.info ("Campo procedenciaReactivo: " +procedenciaReactivo);
                        //logBeanWebReactivo.info ("Campo requiereCadFrioReactivo: " +requiereCadFrioReactivo);
                        //logBeanWebReactivo.info ("Campo temperaturaReactivo: " +temperaturaReactivo);
                        //logBeanWebReactivo.info ("Campo catReactivo: " +catReactivo);
                        //logBeanWebReactivo.info ("Campo cumpleConAlmacena: " +cumpleConAlmacena);
                        //logBeanWebReactivo.info ("Campo realizaRecepcionReactivo: " +realizaRecepcionReactivo);
                        //logBeanWebReactivo.info ("Campo prodCertAnalisisReactivo: " +prodCertAnalisisReactivo);
                        //logBeanWebReactivo.info ("Campo nombreFabReactivo: " +nombreFabReactivo);
                        //logBeanWebReactivo.info ("Campo nombreImportadorReactivo: " +nombreImportadorReactivo);
                        //logBeanWebReactivo.info ("Campo servicioFunReactivo: " +servicioFunReactivo);
                        //logBeanWebReactivo.info ("Campo fechaIncidente: " +fechaIncidente);
                        //logBeanWebReactivo.info ("Campo descripcionIncidente: " +descripcionIncidente);
                        //logBeanWebReactivo.info ("Campo desenlaceIncidente: " +desenlaceIncidente);
                        //logBeanWebReactivo.info ("Campo otroIncidente: " +otroIncidente);
                        //logBeanWebReactivo.info ("Campo tieneProgGestion: " +tieneProgGestion);
                        //logBeanWebReactivo.info ("Campo realizoAnalisisGestion: " +realizoAnalisisGestion);
                        //logBeanWebReactivo.info ("Campo herramientaAnalisisGestion: " +herramientaAnalisisGestion);
                        //logBeanWebReactivo.info ("Campo otroAnalisisGestion: " +otroAnalisisGestion);
                        //logBeanWebReactivo.info ("Campo causaEfectoAnalisisGestion: " +causaEfectoAnalisisGestion);
                        //logBeanWebReactivo.info ("Campo causaProbableEfectoGestion: " +causaProbableEfectoGestion);
                        //logBeanWebReactivo.info ("Campo descripcionCausaEfectoGestion: " +descripcionCausaEfectoGestion);
                        //logBeanWebReactivo.info ("Campo codigoCausaEfectoGestion: " +codigoCausaEfectoGestion);
                        //logBeanWebReactivo.info ("Campo inicioAccionesEfectoGestion: " +inicioAccionesEfectoGestion);
                        //logBeanWebReactivo.info ("Campo accionesGestion: " +accionesGestion);
                        //logBeanWebReactivo.info ("Campo reportoDistribGestion: " +reportoDistribGestion);
                        //logBeanWebReactivo.info ("Campo fechaReporteImportadorGestion: " +fechaReporteImportadorGestion);
                        //logBeanWebReactivo.info ("Campo fechaEnvioDistribuidorGestion: " +fechaEnvioDistribuidorGestion);
                        //logBeanWebReactivo.info ("Campo nombreReportante: " +nombreReportante);
                        //logBeanWebReactivo.info ("Campo profesionReportante: " +profesionReportante);
                        //logBeanWebReactivo.info ("Campo organizacionReportante: " +organizacionReportante);
                        //logBeanWebReactivo.info ("Campo direccionReportante: " +direccionReportante);
                        //logBeanWebReactivo.info ("Campo telefonoReportante: " +telefonoReportante);
                        //logBeanWebReactivo.info ("Campo departamentoReportante: " +departamentoReportante);
                        //logBeanWebReactivo.info ("Campo ciudadReportante: " +ciudadReportante);
                        //logBeanWebReactivo.info ("Campo correoReportante: " +correoReportante);
                        //logBeanWebReactivo.info ("Campo fechaNotIPSReportante: " +fechaNotIPSReportante);
                        //logBeanWebReactivo.info ("Campo autorizaDivulgaReportante: " +autorizaDivulgaReportante);
                        //logBeanWebReactivo.info ("Campo tipoReportanteInfoReactivo: " +tipoReportanteInfoReactivo);
                        //logBeanWebReactivo.info ("Campo estadoReporteInfoReactivo: " +estadoReporteInfoReactivo);
                        //logBeanWebReactivo.info ("Campo fechaGET: " +fechaGET);
                        //logBeanWebReactivo.info ("Campo gestionET: " +gestionET);
                        //logBeanWebReactivo.info ("Campo estadoGET: " +estadoGET);
                        //logBeanWebReactivo.info ("Campo fechaGInvima: " +fechaGInvima);
                        //logBeanWebReactivo.info ("Campo observacionesGInvima: " +observacionesGInvima);
                        //logBeanWebReactivo.info ("*****************************************************************************");
                        //logBeanWebReactivo.info ("*****************************************************************************");
                        //logBeanWebReactivo.info ("*****************************************************************************");
                        */
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        codDepto1 = getServicioReporteTrimestral().obtenerCodigoDeptoPorNombre(departamentoInstitucion);
                        codMunicipio1 = getServicioReporteTrimestral().obtenerCodigoMunicipioPorNombre(codDepto1,ciudadInstitucion);
                        codDepto2 = getServicioReporteTrimestral().obtenerCodigoDeptoPorNombre(departamentoReportante);
                        codMunicipio2 = getServicioReporteTrimestral().obtenerCodigoMunicipioPorNombre(codDepto2,ciudadReportante);
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        codigonivelComplejidadInstitucion = obtenerIdComplejidad(nivelComplejidadInstitucion);
                        codigonaturaleza = obtenerIdNaturaleza(naturalezaInstitucion);
                        codigotipoDocumento = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoTipoIdentificacionReportante(tipIdentPaciente);
                        codigosexo = obtenerIdSexo(sexoPaciente);
                        codigoedadEn = obtenerIdEdadEn(edadEnPaciente);
                        codigotipoDispositivo = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoTipoDispositivo(codigoTipoDiagReactivo);
                        codigoprocedenciaReactivo = obtenerIdValorSiNo(procedenciaReactivo);
                        codigorequiereCadFrioReactivo = obtenerIdValorSiNo(requiereCadFrioReactivo);
                        codigocatReactivo = obtenerIdCategoria(catReactivo);
                        codigocumpleConAlmacena = obtenerIdValorSiNo(cumpleConAlmacena);
                        codigorealizaRecepcionReactivo = obtenerIdValorSiNo(realizaRecepcionReactivo);
                        codigoprodCertAnalisisReactivo = obtenerIdValorSiNo(prodCertAnalisisReactivo);
                        codigoservicioFunReactivo = obtenerIdServicioAreaFuncionamiento(servicioFunReactivo);
                        codigodesenlaceIncidente = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoTipoDesenlace(desenlaceIncidente);
                        codigotieneProgGestion = obtenerIdValorSiNo(tieneProgGestion);
                        codigorealizoAnalisisGestion = obtenerIdValorSiNo(realizoAnalisisGestion);
                        codigoherramientaAnalisisGestion = obtenerIdHerramientaAnalisis(herramientaAnalisisGestion);
                        codigocausaEfectoAnalisisGestion = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoCausaProbable(codigoCausaEfectoGestion);
                        codigodescripcionCausaEfectoGestion = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoDescripcionCausaEfecto(descripcionCausaEfectoGestion);
                        codigocausaProbableEfectoGestion = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoCausaProbable(causaProbableEfectoGestion);
                        codigocodigoCausaEfectoGestion = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoCausaProbable(codigoCausaEfectoGestion);
                        codigoinicioAccionesEfectoGestion = obtenerIdValorSiNo(inicioAccionesEfectoGestion);
                        codigoreportoDistribGestion = obtenerIdValorSiNo(reportoDistribGestion);
                        codigoprofesionReportante = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoCargos(profesionReportante);
                        codigoautorizaDivulgaReportante = obtenerIdValorSiNo(autorizaDivulgaReportante);
                        codigotipoReportanteInfoReactivo = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoTipoDeReportante(tipoReportanteInfoReactivo);
                        codigoestadoReporteInfoReactivo = obtenerIdEstadoReporte(estadoReporteInfoReactivo);
                        //codigoestadoGET = obtenerIdEstadoReporte(estadoGET);
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //logBeanWebReactivo.info ("----------------------------------------------------------");
                        //logBeanWebReactivo.info ("----------------------------------------------------------");
                        /*
                        //logBeanWebReactivo.info ("CODIGOS INTERNOS DETECTADOS");
                        //logBeanWebReactivo.info ("codDepto1 = " + codDepto1);
                        //logBeanWebReactivo.info ("codMunicipio1 = " + codMunicipio1);
                        //logBeanWebReactivo.info ("codDepto2 = " + codDepto2);
                        //logBeanWebReactivo.info ("codMunicipio2 = " + codMunicipio2);
                        */
                        //****************************************************************************
                        //****************************************************************************
                        //logBeanWebReactivo.info ("-------------------------------------------------------------------------");
                        /*
                        //logBeanWebReactivo.info ("REGISTRO SANITARIO ENCONTRADO = " + registroSanReactivo);
                        //logBeanWebReactivo.info ("CODIGO EXPEDIENTE = " + expedienteReactivo);
                        */
                        //logBeanWebReactivo.info ("-------------------------------------------------------------------------");
                        //****************************************************************************
                        //****************************************************************************
                        /*
                        //logBeanWebReactivo.info("codigonivelComplejidadInstitucion EN LA VALIDACION: " + codigonivelComplejidadInstitucion);
                        //logBeanWebReactivo.info("codigonaturaleza EN LA VALIDACION: " + codigonaturaleza);
                        //logBeanWebReactivo.info("codigotipoDocumento EN LA VALIDACION: " + codigotipoDocumento);
                        //logBeanWebReactivo.info("codigosexo EN LA VALIDACION: " + codigosexo);
                        //logBeanWebReactivo.info("codigoedadEn EN LA VALIDACION: " + codigoedadEn);
                        //logBeanWebReactivo.info("codigotipoDispositivo EN LA VALIDACION: " + codigotipoDispositivo);
                        //logBeanWebReactivo.info("codigoprocedenciaReactivo EN LA VALIDACION: " + codigoprocedenciaReactivo);
                        //logBeanWebReactivo.info("codigorequiereCadFrioReactivo EN LA VALIDACION: " + codigorequiereCadFrioReactivo);
                        //logBeanWebReactivo.info("codigocatReactivo EN LA VALIDACION: " + codigocatReactivo);
                        //logBeanWebReactivo.info("codigocumpleConAlmacena EN LA VALIDACION: " + codigocumpleConAlmacena);
                        //logBeanWebReactivo.info("codigorealizaRecepcionReactivo EN LA VALIDACION: " + codigorealizaRecepcionReactivo);
                        //logBeanWebReactivo.info("codigoprodCertAnalisisReactivo EN LA VALIDACION: " + codigoprodCertAnalisisReactivo);
                        //logBeanWebReactivo.info("codigoservicioFunReactivo EN LA VALIDACION: " + codigoservicioFunReactivo);
                        //logBeanWebReactivo.info("codigodesenlaceIncidente EN LA VALIDACION: " + codigodesenlaceIncidente);
                        //logBeanWebReactivo.info("codigotieneProgGestion EN LA VALIDACION: " + codigotieneProgGestion);
                        //logBeanWebReactivo.info("codigorealizoAnalisisGestion EN LA VALIDACION: " + codigorealizoAnalisisGestion);
                        //logBeanWebReactivo.info("codigoherramientaAnalisisGestion EN LA VALIDACION: " + codigoherramientaAnalisisGestion);
                        //logBeanWebReactivo.info("codigocausaEfectoAnalisisGestion EN LA VALIDACION: " + codigocausaEfectoAnalisisGestion);
                        //logBeanWebReactivo.info("codigodescripcionCausaEfectoGestion EN LA VALIDACION: " + codigodescripcionCausaEfectoGestion);
                        //logBeanWebReactivo.info("codigocausaProbableEfectoGestion EN LA VALIDACION: " + codigocausaProbableEfectoGestion);
                        //logBeanWebReactivo.info("codigocodigoCausaEfectoGestion EN LA VALIDACION: " + codigocodigoCausaEfectoGestion);
                        //logBeanWebReactivo.info("codigoinicioAccionesEfectoGestion EN LA VALIDACION: " + codigoinicioAccionesEfectoGestion);
                        //logBeanWebReactivo.info("codigoreportoDistribGestion EN LA VALIDACION: " + codigoreportoDistribGestion);
                        //logBeanWebReactivo.info("codigoprofesionReportante EN LA VALIDACION: " + codigoprofesionReportante);
                        //logBeanWebReactivo.info("codigoautorizaDivulgaReportante EN LA VALIDACION: " + codigoautorizaDivulgaReportante);
                        //logBeanWebReactivo.info("codigotipoReportanteInfoReactivo EN LA VALIDACION: " + codigotipoReportanteInfoReactivo);
                        //logBeanWebReactivo.info("codigoestadoReporteInfoReactivo EN LA VALIDACION: " + codigoestadoReporteInfoReactivo);
                        */
                        //logBeanWebReactivo.info("codigoestadoGET EN LA VALIDACION: " + codigoestadoGET);
                        //****************************************************************************
                        //****************************************************************************
                        //****************************************************************************
                        /*
                        //logBeanWebReactivo.info ("FIN CODIGOS INTERNOS");
                        */
                        //logBeanWebReactivo.info ("----------------------------------------------------------");
                        //logBeanWebReactivo.info ("----------------------------------------------------------");
                        //***************************************************************************
                        //***************************************************************************
                        /*
                        //logBeanWebReactivo.info ("CODIGO DEL REPORTANTE EN SESION = " + this.getDeptoReportante() + " - CODIGO DEPTO REPORTADO EN ARCHIVO = " + codDepto2);
                        */
                        nombreDeptoSesion = getServicioReporteTrimestral().obtenerNombreDeptoPorCodigo(this.getDeptoReportante());
                        nombreDeptoReportado = getServicioReporteTrimestral().obtenerNombreDeptoPorCodigo(codDepto2);
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        if (codDepto2.equals(this.getDeptoReportante()))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CONSISTENCIA DE DATO");
                            registroValidacion.setDescripcion("El Departamento del Reportante en archivo (" + nombreDeptoReportado + ") no corresponde al de la Entidad Reportante que es " + nombreDeptoSesion);
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("F6.  Departamento");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        if (nombreInstitucion.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar el nombre de la institución donde ocurrió el incidente");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("A1. Nombre de la Institución");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!codDepto1.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe seleccionar el Departamento donde ocurrió el incidente");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("A2. Departamento");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!codMunicipio1.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe seleccionar el municipio del departamento donde ocurrió el incidente");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("A3. Ciudad");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (verificarMunicipioDepto(codDepto1,codMunicipio1))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("MUNICIPIO INCORRECTO");
                            registroValidacion.setDescripcion("El Municipio " + ciudadInstitucion + " no corresponde al departamento de " + departamentoInstitucion);
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("A3. Municipio");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (direccionInstitucion.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe la dirección del lugar de ocurrencia del incidente");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("A4.  Dirección");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (nitInstitucion.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar A5. NIT");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("A5. NIT");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        valorEsNumero = verificarSiEsNumero(nitInstitucion);
                        if (nitInstitucion.length() > 0 && valorEsNumero == true)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("TIPO DE DATO INCORRECTO");
                            registroValidacion.setDescripcion("El valor ingresado del NIT de la Institución " + nitInstitucion + " NO ES UN VALOR NUMÉRICO VÁLIDO PARA EL CAMPO");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("A5. NIT");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!nivelComplejidadInstitucion.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar A6. Nivel de Complejidad  (si aplica)");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("A6. Nivel de Complejidad  (si aplica)");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!naturalezaInstitucion.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar A7. Naturaleza");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("A7. Naturaleza");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!tipIdentPaciente.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar B1.Tipo de Identificación");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("B1.Tipo de Identificación");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (identificacionPaciente.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo B2.   Identificación");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("B2.   Identificación");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!sexoPaciente.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo B3.Sexo");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("B3.Sexo");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (edadPaciente.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo B4.Edad");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("B4.Edad");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        valorEsNumero = verificarSiEsNumero(edadPaciente);
                        if (valorEsNumero == true)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("TIPO DE DATO INCORRECTO");
                            registroValidacion.setDescripcion("El valor ingresado de la edad del paciente " + edadPaciente + " NO ES UN VALOR NUMÉRICO VÁLIDO PARA EL CAMPO");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("B4.Edad");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!edadEnPaciente.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo B5.Edad en");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("B5.Edad en");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (nombreReactivo.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo C1. Nombre Comercial del Reactivo de Diagnostico In Vitro");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C1. Nombre Comercial del Reactivo de Diagnostico In Vitro");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (registroSanReactivo.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo C2. Registro sanitario o permiso de comercialización");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C2. Registro sanitario o permiso de comercialización");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (expedienteReactivo.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo C3.Expediente");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C3.Expediente");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (registroSanReactivo != null)
                        {
                            codigoExpedienteConsultado = getEjbServicioRemotoReporteMasivoTrimestral().verificarExpedienteDispositivoPorRegistroSanitario(registroSanReactivo);
                            //logBeanWebReactivo.info ("----------------------------------------------------------------------------------------------------------------------------------");
                            //logBeanWebReactivo.info ("REGISTRO SANITARIO = " + registroSanReactivo + " - CÓDIGO DE EXPEDIENTE OBTENIDO = " + codigoExpedienteConsultado);
                            //logBeanWebReactivo.info ("----------------------------------------------------------------------------------------------------------------------------------");
                            //*********************************************************************************************
                            //*********************************************************************************************
                            if (!codigoExpedienteConsultado.equals("00000000"))
                            {
                                contadorColumnasFila++;
                            }
                            else
                            {
                                contadorErrores++;
                                registroValidacion = new ValidacionTrimestral();
                                registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                                registroValidacion.setTipo_error("ERROR DE REGISTRO SANITARIO");
                                registroValidacion.setDescripcion("El Registro Sanitario " + registroSanReactivo + " proporcionado no cuenta con Expediente");
                                registroValidacion.setLinea(i+1);
                                registroValidacion.setCampo("Registro Sanitario");
                                this.getListadoValidacionesTrimestral().add(registroValidacion);
                            }
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar el Registro Sanitario del Dispositivo");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("Registro Sanitario");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (registroSanReactivo != null)
                        {
                            if (!registroSanReactivo.contains("TD"))
                            {
                                contadorColumnasFila++;
                            }
                            else
                            {
                                contadorErrores++;
                                registroValidacion = new ValidacionTrimestral();
                                registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                                registroValidacion.setTipo_error("ERROR DE REGISTRO SANITARIO DE REACTIVOS IN VITRO");
                                registroValidacion.setDescripcion("El Registro Sanitario " + registroSanReactivo + " proporcionado es un registro de Dispositivos Médicos (ReactivoVigilancia)");
                                registroValidacion.setLinea(i+1);
                                registroValidacion.setCampo("Registro Sanitario de Reactivo");
                                this.getListadoValidacionesTrimestral().add(registroValidacion);
                            }
                            //*********************************************************************************************
                            //*********************************************************************************************
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar el Registro Sanitario del Dispositivo");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("Registro Sanitario");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (codigoExpedienteConsultado.equals(expedienteReactivo))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                                contadorErrores++;
                                registroValidacion = new ValidacionTrimestral();
                                registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                                registroValidacion.setTipo_error("ERROR DE EXPEDIENTE");
                                registroValidacion.setDescripcion("El Número de Expediente " + expedienteReactivo + " ingresado no corresponde al registro sanitario proporcionado " + registroSanReactivo);
                                registroValidacion.setLinea(i+1);
                                registroValidacion.setCampo("Expediente");
                                this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************

                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        if (codUnicoReactivo.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo C4.Código Unico del Reactivo de Diagnostico In Vitro");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C4.Código Unico del Reactivo de Diagnostico In Vitro");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        valorEsNumero = verificarSiEsNumero(codUnicoReactivo);
                        if (codUnicoReactivo.length() > 0 && valorEsNumero == true)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("TIPO DE DATO INCORRECTO");
                            registroValidacion.setDescripcion("El valor ingresado del Código Único del Reactivo " + nitInstitucion + " NO ES UN VALOR NUMÉRICO VÁLIDO PARA EL CAMPO");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C4.Código Unico del Reactivo de Diagnostico In Vitro");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!codigoTipoDiagReactivo.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo C5.Código Tipo Diagnostico In Vitro");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C5.Código Tipo Diagnostico In Vitro");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (loteReactivo.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo C6. Lote");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C6. Lote");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (referenciaReactivo.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo C7. Referencia");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C7. Referencia");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (fechaVenReactivo != null)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo C8.  Fecha de vencimiento");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C8.  Fecha de vencimiento");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        logBeanWebReactivo.info ("VALOR DEL CAMPO DE PROCEDENCIA: " + procedenciaReactivo);
                        //if (!procedenciaReactivo.equals("0"))
                        if (procedenciaReactivo.toUpperCase().equals("IMPORTADO") || procedenciaReactivo.toUpperCase().equals("NACIONAL"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor correcto para el campo C9. Procedencia (NACIONAL O IMPORTADO)");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C9. Procedencia");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!requiereCadFrioReactivo.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo C10.Requiere cadena de frío");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C10.Requiere cadena de frío");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (temperaturaReactivo.length() > 0 && esNumerico(temperaturaReactivo))
                        {
                            if (esNumerico(temperaturaReactivo)){
                                contadorColumnasFila++;
                            }else{
                                contadorErrores++;
                                registroValidacion = new ValidacionTrimestral();
                                registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                                registroValidacion.setTipo_error("CAMPO NUMÉRICO");
                                registroValidacion.setDescripcion("Debe ingresar un valor para el campo C11.Temperatura almacenamiento requerida");
                                registroValidacion.setLinea(i+1);
                                registroValidacion.setCampo("C11.Temperatura almacenamiento requerida");
                                this.getListadoValidacionesTrimestral().add(registroValidacion);
                            }
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo C11.Temperatura almacenamiento requerida");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C11.Temperatura almacenamiento requerida");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        
                        //***************************************************************************
                        //***************************************************************************
                        if (!catReactivo.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo C12. Categoría del reactivo según el riesgo (Decreto 3770 de 2004)");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C12. Categoría del reactivo según el riesgo (Decreto 3770 de 2004)");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!cumpleConAlmacena.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo C13. Se cumplieron con las condiciones de almacenamiento según especificaciones del fabricante");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C13. Se cumplieron con las condiciones de almacenamiento según especificaciones del fabricante");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!realizaRecepcionReactivo.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo C14. ¿Se realiza recepción frente a especificaciones técnicas del RDIV? ");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C14. ¿Se realiza recepción frente a especificaciones técnicas del RDIV? ");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!prodCertAnalisisReactivo.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo C15. ¿El producto cuenta con certificado de análisis? ");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C15. ¿El producto cuenta con certificado de análisis? ");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (nombreFabReactivo.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo C16.  Nombre o Razón social del Fabricante ");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C16.  Nombre o Razón social del Fabricante ");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (nombreImportadorReactivo.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo C17.  Nombre o razón social del importador y/o distribuidor");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C17.  Nombre o razón social del importador y/o distribuidor");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!servicioFunReactivo.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo C18.  Servicio o Área de funcionamiento del reactivo en el momento del efecto indeseado");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("C18.  Servicio o Área de funcionamiento del reactivo en el momento del efecto indeseado");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (fechaIncidente != null)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo D1. Fecha del incidente dd/mm/aaaa ");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("D1. Fecha del incidente dd/mm/aaaa ");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (descripcionIncidente.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo D2.  Descripción delincidente ");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("D2.  Descripción delincidente ");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!desenlaceIncidente.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo D3.  Desenlace del incidente adverso");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("D3.  Desenlace del incidente adverso");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (otroIncidente.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo D4 Otro ¿Cuál?");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("D4 Otro ¿Cuál?");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!tieneProgGestion.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo E1. ¿La institución tiene implementado un programa de gestión de riesgos? ");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("E1. ¿La institución tiene implementado un programa de gestión de riesgos? ");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!realizoAnalisisGestion.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo E2. ¿Se realizó algún tipo de análisis del Incidente? ");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("E2. ¿Se realizó algún tipo de análisis del Incidente? ");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!herramientaAnalisisGestion.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo E3. ¿Qué herramienta utilizó para el análisis del evento adverso o incidente?");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("E3. ¿Qué herramienta utilizó para el análisis del evento adverso o incidente?");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (otroAnalisisGestion.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo E4.  Otro Cual");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("E4.  Otro Cual");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!causaEfectoAnalisisGestion.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo E5. ¿Se detectó la causa que generó el efecto indeseado? ");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("E5. ¿Se detectó la causa que generó el efecto indeseado? ");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        logBeanWebReactivo.info ("CODIGO CAUSA PROBABLE ANTES DE VALIDACION = " + causaProbableEfectoGestion);
                        if (!causaProbableEfectoGestion.equals("0"))
                        {
                            valorCodigoCausaP = Integer.parseInt(ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoCausaProbable(causaProbableEfectoGestion));
                            logBeanWebReactivo.info ("Codigo causa probable: " + valorCodigoCausaP);
                            
                            //if (!causaProbableEfectoGestion.equals("0"))
                            if (valorCodigoCausaP >= 540 && valorCodigoCausaP <= 960)
                            {
                                contadorColumnasFila++;
                            }
                            else
                            {
                                contadorErrores++;
                                registroValidacion = new ValidacionTrimestral();
                                registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                                registroValidacion.setTipo_error("CAMPO VACÍO");
                                registroValidacion.setDescripcion("Debe ingresar un valor correcto de código de Causa para el campo E6.  Causa probable del efecto indeseado.  El valor no puede ser " + valorCodigoCausaP);
                                registroValidacion.setLinea(i+1);
                                registroValidacion.setCampo("E6.  Causa probable del efecto indeseado");
                                this.getListadoValidacionesTrimestral().add(registroValidacion);
                            }
                        }
                        else
                        {
                                contadorErrores++;
                                registroValidacion = new ValidacionTrimestral();
                                registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                                registroValidacion.setTipo_error("CAMPO VACÍO");
                                registroValidacion.setDescripcion("Debe ingresar un valor para el campo E6.  Causa probable del efecto indeseado");
                                registroValidacion.setLinea(i+1);
                                registroValidacion.setCampo("E6.  Causa probable del efecto indeseado");
                                this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!descripcionCausaEfectoGestion.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo E7.  Descripción de la Causa");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("E7.  Descripción de la Causa");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!codigoCausaEfectoGestion.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo E8.  Codigo de la Causa");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("E8.  Codigo de la Causa");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //Validación de cambio entre el nombre y el código de la causa probable del evento 
                        codigoCausaValidar = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoCausaProbable(causaProbableEfectoGestion);
                        logBeanWebReactivo.info ("ANALISIS CAUSA PROBABLE ------------------------------------------->");
                        logBeanWebReactivo.info ("CAUSA PROBABLE REGISTRADA NOMBRE = " + causaProbableEfectoGestion);
                        logBeanWebReactivo.info ("CAUSA PROBABLE REGISTRADA ID = " + codigoCausaValidar);
                        logBeanWebReactivo.info ("CAUSA PROBABLE CODIGO NUMERO ENVIADO EN LA GRILLA = " + codigoCausaEfectoGestion);
                        logBeanWebReactivo.info ("ANALISIS CAUSA PROBABLE ------------------------------------------->");
                        //***************************************************************************
                        if (codigoCausaValidar.equals(codigoCausaEfectoGestion))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("INCONSISTENCIA DE CAMPO");
                            registroValidacion.setDescripcion("El código ingresado de la causa probable de gestión (" + codigoCausaEfectoGestion  + ") del evento (campo E8. Código de la Causa) debe corresponder con la causa específica " + causaProbableEfectoGestion + " (" + codigoCausaValidar+ ") del Campo E6.  Causa probable del efecto indeseado");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("E8.  Codigo de la Causa Probable");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!inicioAccionesEfectoGestion.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo E9.  ¿Inició acciones de mejoramiento (preventivas/correctivas)?");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("E9.  ¿Inició acciones de mejoramiento (preventivas/correctivas)?");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (accionesGestion.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo E10.  Describa las acciones emprendidas");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("E10.  Describa las acciones emprendidas");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!reportoDistribGestion.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo E11. Reportó al Importador/Distribuidor/fabricante");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("E11. Reportó al Importador/Distribuidor/fabricante");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (fechaReporteImportadorGestion != null)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo E12. Fecha Reporte al Importador/Distribuidor/fabricante");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("E12. Fecha Reporte al Importador/Distribuidor/fabricante");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (fechaEnvioDistribuidorGestion != null)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo E13. Fecha de envió del Reactivo de Diagnostico in vitro a Distribuidor/Importador/fabricante");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("E13. Fecha de envió del Reactivo de Diagnostico in vitro a Distribuidor/Importador/fabricante");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (nombreReportante.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo F1. Nombre");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("F1. Nombre");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!profesionReportante.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo F2. Profesión");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("F2. Profesión");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (organizacionReportante.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo F3.  Organización");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("F3.  Organización");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (direccionReportante.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo F4.  Dirección");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("F4.  Dirección");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (telefonoReportante.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo F5.  Teléfono");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("F5.  Teléfono");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!codDepto2.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo F6.  Departamento");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("F6.  Departamento");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!codMunicipio2.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo F7. Ciudad");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("F7. Ciudad");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (verificarMunicipioDepto(codDepto2,codMunicipio2))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("MUNICIPIO INCORRECTO");
                            registroValidacion.setDescripcion("El Municipio " + ciudadReportante + " no corresponde al departamento de " + departamentoReportante);
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("A3. Municipio");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************

                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        if (correoReportante.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo F8. Correo electrónico Institucional");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("F8. Correo electrónico Institucional");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (fechaNotIPSReportante != null)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo F9. Fecha de notificación de IPS dd/mm/aaaa");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("F9. Fecha de notificación de IPS dd/mm/aaaa");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!autorizaDivulgaReportante.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo F10.  Autoriza la divulgación de la información y origen del reporte ");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("F10.  Autoriza la divulgación de la información y origen del reporte ");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!tipoReportanteInfoReactivo.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo G1. Tipo de Reportante");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("G1. Tipo de Reportante");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        if (!estadoReporteInfoReactivo.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo G2. Estado del Reporte");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("G2. Estado del Reporte");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        /*
                        if (fechaGET != null)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo H1.  Fecha");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("H1.  Fecha");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        */
                        //***************************************************************************
                        //***************************************************************************
                        /*
                        if (gestionET.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo H2.  Gestión");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("H2.  Gestión");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        */
                        //***************************************************************************
                        //***************************************************************************
                        /*
                        if (!estadoGET.equals("0"))
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo H3.  Estado");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("H3.  Estado");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        */
                        //***************************************************************************
                        //***************************************************************************
                        /*
                        if (fechaGInvima != null)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo I1.  Fecha");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("I1.  Fecha");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        */
                        //***************************************************************************
                        //***************************************************************************
                        /*
                        if (observacionesGInvima.length() > 0)
                        {
                            contadorColumnasFila++;
                        }
                        else
                        {
                            contadorErrores++;
                            registroValidacion = new ValidacionTrimestral();
                            registroValidacion.setId_error("Error-Reactivo-" + String.valueOf(contadorErrores));
                            registroValidacion.setTipo_error("CAMPO VACÍO");
                            registroValidacion.setDescripcion("Debe ingresar un valor para el campo I2.  Observaciones");
                            registroValidacion.setLinea(i+1);
                            registroValidacion.setCampo("I2.  Observaciones");
                            this.getListadoValidacionesTrimestral().add(registroValidacion);
                        }
                        */
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //logBeanWebReactivo.info ("TOTAL DE CAMPOS VALIDOS EN LA FILA " + (i+1) + ": " + contadorColumnasFila);
                        //logBeanWebReactivo.info ("TOTAL DE ERRORES EN LA FILA " + (i+1) + ": " + contadorErrores);
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        
                        //Validación con los 64 campos
                        //if (contadorColumnasFila == 74)
                        //***************************************************************************
                        //***************************************************************************
                        if (contadorColumnasFila == 69)
                        {
                            estaBienFila = true;
                        }
                        else
                        {
                            estaBienFila = false;
                        }
                        //***************************************************************************
                        //***************************************************************************
                        //logBeanWebReactivo.info ("CONTADOR DE VALIDACION DE COLUMNAS DE LA FILA DEL ARCHIVO DE REACTIVOVIGILANCIA = " + contadorColumnasFila);
                        //logBeanWebReactivo.info ("ESTADO DE VALIDACIÓN DE LA FILA = " + estaBienFila);

                        if (estaBienFila == true)
                        {
                            contadorFilasArchivo++;
                        }
                        //***************************************************************************
                        //***************************************************************************
                    }//Fin del for de recorrido de la grilla
                    //*************************************************************
                    //*************************************************************
                    //*************************************************************
                    //*************************************************************
                    //logBeanWebReactivo.info ("*************************************************************");
                    //logBeanWebReactivo.info ("*************************************************************");
                    if (contadorFilasArchivo == this.getListaDatosExcel().size())
                    {
                        estaBienArchivo = true;
                        this.setHabilitaGuardado(true);
                        //logBeanWebReactivo.info ("TODAS LAS FILAS DEL ARCHIVO SON CORRECTAS SE PUEDE GUARDAR = " + estaBienArchivo);
                        mensajeSalida = "El Archivo de Cargue Masivo Trimestral con Evento cargado fue correctamente validado y puede ser cargado en Reactivo Vigilancia";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proceso Exitoso",mensajeSalida));
                    }
                    else
                    {
                        estaBienArchivo = false;
                        this.setHabilitaGuardado(false);
                        //logBeanWebReactivo.info ("HAY FILAS CON ERRORES, NO SE PUEDE PERMITIR EL GUARDADO DEL ARCHIVO = " + estaBienArchivo);
                        mensajeSalida = "El Archivo de Cargue Masivo Trimestral con Evento cargado contiene errores de validación.  Corríjalos en la plantilla de Excel y realice nuevamente el cargue";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error",mensajeSalida));
                    }
                    //*************************************************************
                    //*************************************************************
                    RequestContext.getCurrentInstance().update("datosReporte");
                    //logBeanWebReactivo.info ("ACTUALICE LOS COMPONENTES EN LA VISTA PARA LA VALIDACION");            
                    //logBeanWebReactivo.info ("*************************************************************");
                    //logBeanWebReactivo.info ("*************************************************************");
                    //***************************************************************************
                    //***************************************************************************
                    //***************************************************************************
            }
            else
            {
                        //logBeanWebReactivo.info ("No se puede grabar porque hay filas sin cerrar");
                        mensajeSalida = "No es posible realizar la validación de los Registros de Cargue Masivo puesto que el usuario debe guardar los cambios en las filas de la grilla que ha seleccionado";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error",mensajeSalida));
            }
            //***************************************************************************
            //***************************************************************************
            //***************************************************************************
        }
        
        catch (Exception errorMetodo)
        {
            //logBeanWebReactivo.info ("GENERANDO ERROR POR REGISTROS DUPLICADOS");
            errorMetodo.printStackTrace();
            mensajeSalida = "El Archivo de Cargue Masivo Trimestral con Evento no puede ser cargado, puesto que hay errores de validación en su estructura";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error",mensajeSalida));
        }
    }    
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private boolean verificarMunicipioDepto (String codigoDepto, String codigoMunicipio)
    {
        boolean estaBien = false;
        String codDeptoInterno = "";
        
        //logBeanWebReactivo.info ("COD DEPTO = "  + codigoDepto);
        //logBeanWebReactivo.info ("CODIGO MUNICIPIO = " + codigoMunicipio);
        
        if (codigoMunicipio.length() > 0)
        {
            codDeptoInterno = codigoMunicipio.substring(0,2);
            //logBeanWebReactivo.info ("COD INTERNO DEPTO DEL MUNICIPIO = " + codDeptoInterno);
            
            if (codigoDepto.equals(codDeptoInterno))
            {
                estaBien = true;
                //logBeanWebReactivo.info ("EL MUNICIPIO ES DEL DEPARTAMENTO");
            }
            else
            {
                estaBien = false;
                //logBeanWebReactivo.info ("EL MUNICIPIO = " + codigoMunicipio + " NO CORRESPONDE AL DEPARTAMENTO = " + codigoDepto);
            }
        }
        else
        {
                estaBien = false;
                //logBeanWebReactivo.info ("NO HAY CODIGO DE MUNICIPIO DEFINIDO");
        }
        
        return (estaBien);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private String rellenoCeros (String original, int cantidad)
    {
            String str = original;
            StringBuilder sb = new StringBuilder();

            for (int toPrepend=cantidad-str.length(); toPrepend>0; toPrepend--) {
                sb.append('0');
            }

            sb.append(str);
            String result = sb.toString();    
            
            return (result);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void cargarDatosValidados()
    {
        String secuencialGrilla;
        String nombreInstitucionGrilla;
        String departamentoInstitucionGrilla;
        String ciudadInstitucionGrilla;
        String direccionInstitucionGrilla;
        String nitInstitucionGrilla;
        String nivelComplejidadInstitucionGrilla;
        String naturalezaInstitucionGrilla;
        String tipIdentPacienteGrilla;
        String identificacionPacienteGrilla;
        String sexoPacienteGrilla;
        String edadPacienteGrilla;
        String edadEnPacienteGrilla;
        String nombreReactivoGrilla;
        String registroSanReactivoGrilla;
        String expedienteReactivoGrilla;
        String codUnicoReactivoGrilla;
        String codigoTipoDiagReactivoGrilla;
        String loteReactivoGrilla;
        String referenciaReactivoGrilla;
        java.util.Date fechaVenReactivoGrilla;
        String procedenciaReactivoGrilla;
        String requiereCadFrioReactivoGrilla;
        String temperaturaReactivoGrilla;
        String catReactivoGrilla;
        String cumpleConAlmacenaGrilla;
        String realizaRecepcionReactivoGrilla;
        String prodCertAnalisisReactivoGrilla;
        String nombreFabReactivoGrilla;
        String nombreImportadorReactivoGrilla;
        String servicioFunReactivoGrilla;
        java.util.Date fechaIncidenteGrilla;
        String descripcionIncidenteGrilla;
        String desenlaceIncidenteGrilla;
        String otroIncidenteGrilla;
        String tieneProgGestionGrilla;
        String realizoAnalisisGestionGrilla;
        String herramientaAnalisisGestionGrilla;
        String otroAnalisisGestionGrilla;
        String causaEfectoAnalisisGestionGrilla;
        String causaProbableEfectoGestionGrilla;
        String descripcionCausaEfectoGestionGrilla;
        String codigoCausaEfectoGestionGrilla;
        String inicioAccionesEfectoGestionGrilla;
        String accionesGestionGrilla;
        String reportoDistribGestionGrilla;
        java.util.Date fechaReporteImportadorGestionGrilla;
        java.util.Date fechaEnvioDistribuidorGestionGrilla;
        String nombreReportanteGrilla;
        String profesionReportanteGrilla;
        String organizacionReportanteGrilla;
        String direccionReportanteGrilla;
        String telefonoReportanteGrilla;
        String departamentoReportanteGrilla;
        String ciudadReportanteGrilla;
        String correoReportanteGrilla;
        java.util.Date fechaNotIPSReportanteGrilla;
        String autorizaDivulgaReportanteGrilla;
        String tipoReportanteInfoReactivoGrilla;
        String estadoReporteInfoReactivoGrilla;
        java.util.Date fechaGETGrilla;
        String gestionETGrilla;
        String estadoGETGrilla;
        java.util.Date fechaGInvimaGrilla;
        String observacionesGInvimaGrilla;
        int i = 0;
        FilaExcelMasivoReactivo registro = null;
        long secuenciaPreCol = 0L;
        String valorPrecol = "";
        String precolNuevo = "";
        String expediente = "";
        ReactivoEventosTemp registroEventoTemporal = null;
        ReactivoPacienteTemp registroPacienteTemporal = null;
        ReactivoReportanteTemp registroReportanteTemporal = null;
        ReactivoGestionrealizadaTemp registroGestionRealizadaTemporal = null;
        ReactivoCamposcompTemp registroCamposComplementariosTemporal = null;
        ReactivoInstitucionTemp registroInstitucionTemporal = null;
        ReactivoProductoTemp registroProductoTemporal = null;        
        java.util.Date fechaSistema = new java.util.Date();
        boolean seInsertoReactivoEventosTemp = false;
        boolean seInsertoReactivoPacienteTemp = false;
        boolean seInsertoReactivoReportanteTemp = false;
        boolean seInsertoReactivoGestionrealizadaTemp = false;
        boolean seInsertoReactivoCamposcompTemp = false;
        boolean seInsertoReactivoInstitucionTemp = false;
        boolean seInsertoReactivoProductoTemp = false;
        boolean seInsertoPaqueteCompleto = false;
        int contadorReactivoEventosTemp = 0;
        int contadorReactivoPacienteTemp = 0;
        int contadorReactivoReportanteTemp = 0;
        int contadorReactivoGestionrealizadaTemp = 0;
        int contadorReactivoCamposcompTemp = 0;
        int contadorReactivoInstitucionTemp = 0;
        int contadorReactivoProductoTemp = 0;
        int contadorRegistroPrecol = 0;
        String codDepto1 = "";
        String codMunicipio1 = "";
        String codDepto2 = "";
        String codMunicipio2 = "";
        String codigonivelComplejidadInstitucion;
        String codigonaturaleza;
        String codigotipoDocumento;
        String codigosexo;
        String codigoedadEn;
        String codigotipoDispositivo;
        String codigoprocedenciaReactivo;
        String codigorequiereCadFrioReactivo;
        String codigocatReactivo;
        String codigocumpleConAlmacena;
        String codigorealizaRecepcionReactivo;
        String codigoprodCertAnalisisReactivo;
        String codigoservicioFunReactivo;
        String codigodesenlaceIncidente;
        String codigotieneProgGestion;
        String codigorealizoAnalisisGestion;
        String codigoherramientaAnalisisGestion;
        String codigocausaEfectoAnalisisGestion;
        String codigodescripcionCausaEfectoGestion;
        String codigocausaProbableEfectoGestion;
        String codigocodigoCausaEfectoGestion;
        String codigoinicioAccionesEfectoGestion;
        String codigoreportoDistribGestion;
        String codigoprofesionReportante;
        String codigoautorizaDivulgaReportante;
        String codigotipoReportanteInfoReactivo;
        String codigoestadoReporteInfoReactivo;
        String codigoestadoGET = "";
        String mensajeSalida = "";
        boolean sePuedeGuardar = false;
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sesionUsuario = request.getSession();
        String nombreUsuario = "";
        userSesion = null;
        try
        {
            if (this.getListaFilasEditadas().size() > 0)
            {
                    sePuedeGuardar = false;
                    this.listaFilasEditadas = new java.util.ArrayList<Boolean>();
                    this.indiceFilasEditadas = 0;
            }
            else
            {
                    sePuedeGuardar = true;
            }
            
            if (this.getIdRolReportante().equals("1"))
            {
                //logBeanWebReactivo.info ("INVIMA ESTÁ INSERTADO REGISTROS DE UNA IPS, SE CAMBIA ROL A 3");
                this.setIdRolReportante("3");
            }
            else
            {
                //logBeanWebReactivo.info ("SE MANTIENE EL ROL DE LA IPS O FABRICANTE EN SESIÓN: " + this.getIdRolReportante());
            }
            //*****************************************************************************
            //*****************************************************************************
            //*****************************************************************************
            if (sePuedeGuardar == true)
            {
                    //logBeanWebReactivo.info ("YA SE PUEDEN GUARDAR LOS REGISTROS INICIO PROCESO: " + new java.util.Date());
                    //*************************************************************
                    //*************************************************************
                    //*************************************************************
                    userSesion = (UsuariosVO)sesionUsuario.getAttribute("usuario");
                    nombreUsuario = userSesion.getUsuario();
                    this.setIdEPSReportante(String.valueOf(userSesion.getIdentificacionEmpresa()));
                    //logBeanWebReactivo.info ("ID EPS REPORTANTE DEL MASIVO = " + this.getIdEPSReportante());
                    //logBeanWebReactivo.info ("NOMBRE DEL FUNCIONARIO ENCARGADO = " + nombreUsuario);
                    //logBeanWebReactivo.info ("*************************************************************");
                    //logBeanWebReactivo.info ("*************************************************************");
                    //logBeanWebReactivo.info ("Ejecutando cargue del archivo masivo");
                    valorPrecol = getServicioReporteTrimestral().obtenerPrecolSecuenciaCargue();
                    secuenciaPreCol = Long.valueOf(valorPrecol);
                    //logBeanWebReactivo.info ("ULTIMO PRERDIV ACTIVO = " + secuenciaPreCol);
                    //logBeanWebReactivo.info ("*************************************************************");
                    //logBeanWebReactivo.info ("*************************************************************");
                    //logBeanWebReactivo.info ("*************************************************************");
                    //logBeanWebReactivo.info ("*************************************************************");
                    //***************************************************************************
                    //***************************************************************************
                    //***************************************************************************
                    //***************************************************************************
                    precolNuevo = "";
                    for (i = 0;  i < this.getListaDatosExcel().size();  i++)
                    {
                        //***************************************************************************
                        //***************************************************************************
                        //precolNuevo = "PRERDIV" + rellenoCeros(String.valueOf(secuenciaPreCol),9);
                        //Idea peligrosa de la usuaria funcional
                        precolNuevo = consultarConsecutivoPreRDIVSistema();
                        //***************************************************************************
                        //***************************************************************************
                        //logBeanWebReactivo.info ("PRERDIV GENERADO PARA EL REGISTRO (" + (i+1) + ")= " + precolNuevo);
                        //***************************************************************************
                        //***************************************************************************
                        registro = (FilaExcelMasivoReactivo)this.getListaDatosExcel().get(i);
                        secuencialGrilla =  registro.getSecuencial();
                        nombreInstitucionGrilla =  registro.getNombreInstitucion();
                        departamentoInstitucionGrilla =  registro.getDepartamentoInstitucion();
                        ciudadInstitucionGrilla =  registro.getCiudadInstitucion();
                        direccionInstitucionGrilla =  registro.getDireccionInstitucion();
                        nitInstitucionGrilla =  registro.getNitInstitucion();
                        nivelComplejidadInstitucionGrilla =  registro.getNivelComplejidadInstitucion();
                        naturalezaInstitucionGrilla =  registro.getNaturalezaInstitucion();
                        tipIdentPacienteGrilla =  registro.getTipIdentPaciente();
                        identificacionPacienteGrilla =  registro.getIdentificacionPaciente();
                        sexoPacienteGrilla =  registro.getSexoPaciente();
                        edadPacienteGrilla =  registro.getEdadPaciente();
                        edadEnPacienteGrilla =  registro.getEdadEnPaciente();
                        nombreReactivoGrilla =  registro.getNombreReactivo();
                        registroSanReactivoGrilla =  registro.getRegistroSanReactivo();
                        expedienteReactivoGrilla =  registro.getExpedienteReactivo();
                        codUnicoReactivoGrilla =  registro.getCodUnicoReactivo();
                        codigoTipoDiagReactivoGrilla =  registro.getCodigoTipoDiagReactivo();
                        loteReactivoGrilla =  registro.getLoteReactivo();
                        referenciaReactivoGrilla =  registro.getReferenciaReactivo();
                        fechaVenReactivoGrilla =  registro.getFechaVenReactivo();
                        procedenciaReactivoGrilla =  registro.getProcedenciaReactivo();
                        requiereCadFrioReactivoGrilla =  registro.getRequiereCadFrioReactivo();
                        temperaturaReactivoGrilla =  registro.getTemperaturaReactivo();
                        catReactivoGrilla =  registro.getCatReactivo();
                        cumpleConAlmacenaGrilla =  registro.getCumpleConAlmacena();
                        realizaRecepcionReactivoGrilla =  registro.getRealizaRecepcionReactivo();
                        prodCertAnalisisReactivoGrilla =  registro.getProdCertAnalisisReactivo();
                        nombreFabReactivoGrilla =  registro.getNombreFabReactivo();
                        nombreImportadorReactivoGrilla =  registro.getNombreImportadorReactivo();
                        servicioFunReactivoGrilla =  registro.getServicioFunReactivo();
                        fechaIncidenteGrilla =  registro.getFechaIncidente();
                        descripcionIncidenteGrilla =  registro.getDescripcionIncidente();
                        desenlaceIncidenteGrilla =  registro.getDesenlaceIncidente();
                        otroIncidenteGrilla =  registro.getOtroIncidente();
                        tieneProgGestionGrilla =  registro.getTieneProgGestion();
                        realizoAnalisisGestionGrilla =  registro.getRealizoAnalisisGestion();
                        herramientaAnalisisGestionGrilla =  registro.getHerramientaAnalisisGestion();
                        otroAnalisisGestionGrilla =  registro.getOtroAnalisisGestion();
                        causaEfectoAnalisisGestionGrilla =  registro.getCausaEfectoAnalisisGestion();
                        causaProbableEfectoGestionGrilla =  registro.getCausaProbableEfectoGestion();
                        descripcionCausaEfectoGestionGrilla =  registro.getDescripcionCausaEfectoGestion();
                        codigoCausaEfectoGestionGrilla =  registro.getCodigoCausaEfectoGestion();
                        inicioAccionesEfectoGestionGrilla =  registro.getInicioAccionesEfectoGestion();
                        accionesGestionGrilla =  registro.getAccionesGestion();
                        reportoDistribGestionGrilla =  registro.getReportoDistribGestion();
                        fechaReporteImportadorGestionGrilla =  registro.getFechaReporteImportadorGestion();
                        fechaEnvioDistribuidorGestionGrilla =  registro.getFechaEnvioDistribuidorGestion();
                        nombreReportanteGrilla =  registro.getNombreReportante();
                        profesionReportanteGrilla =  registro.getProfesionReportante();
                        organizacionReportanteGrilla =  registro.getOrganizacionReportante();
                        direccionReportanteGrilla =  registro.getDireccionReportante();
                        telefonoReportanteGrilla =  registro.getTelefonoReportante();
                        departamentoReportanteGrilla =  registro.getDepartamentoReportante();
                        ciudadReportanteGrilla =  registro.getCiudadReportante();
                        correoReportanteGrilla =  registro.getCorreoReportante();
                        fechaNotIPSReportanteGrilla =  registro.getFechaNotIPSReportante();
                        autorizaDivulgaReportanteGrilla =  registro.getAutorizaDivulgaReportante();
                        tipoReportanteInfoReactivoGrilla =  registro.getTipoReportanteInfoReactivo();
                        estadoReporteInfoReactivoGrilla =  registro.getEstadoReporteInfoReactivo();
                        fechaGETGrilla =  registro.getFechaGET();
                        gestionETGrilla =  registro.getGestionET();
                        estadoGETGrilla =  registro.getEstadoGET();
                        fechaGInvimaGrilla =  registro.getFechaGInvima();
                        observacionesGInvimaGrilla =  registro.getObservacionesGInvima();
                        //***************************************************************************
                        //***************************************************************************
                        //logBeanWebReactivo.info ("*****************************************************************************");
                        //logBeanWebReactivo.info ("*****************************************************************************");
                        //logBeanWebReactivo.info ("*****************************************************************************");
                        /*
                        //logBeanWebReactivo.info ("Trayendo registro de la grilla " + (i+1));
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO secuencialGrilla: " + secuencialGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO nombreInstitucionGrilla: " + nombreInstitucionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO departamentoInstitucionGrilla: " + departamentoInstitucionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO ciudadInstitucionGrilla: " + ciudadInstitucionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO direccionInstitucionGrilla: " + direccionInstitucionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO nitInstitucionGrilla: " + nitInstitucionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO nivelComplejidadInstitucionGrilla: " + nivelComplejidadInstitucionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO naturalezaInstitucionGrilla: " + naturalezaInstitucionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO tipIdentPacienteGrilla: " + tipIdentPacienteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO identificacionPacienteGrilla: " + identificacionPacienteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO sexoPacienteGrilla: " + sexoPacienteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO edadPacienteGrilla: " + edadPacienteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO edadEnPacienteGrilla: " + edadEnPacienteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO nombreReactivoGrilla: " + nombreReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO registroSanReactivoGrilla: " + registroSanReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO expedienteReactivoGrilla: " + expedienteReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO codUnicoReactivoGrilla: " + codUnicoReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO codigoTipoDiagReactivoGrilla: " + codigoTipoDiagReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO loteReactivoGrilla: " + loteReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO referenciaReactivoGrilla: " + referenciaReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO fechaVenReactivoGrilla: " + fechaVenReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO procedenciaReactivoGrilla: " + procedenciaReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO requiereCadFrioReactivoGrilla: " + requiereCadFrioReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO temperaturaReactivoGrilla: " + temperaturaReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO catReactivoGrilla: " + catReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO cumpleConAlmacenaGrilla: " + cumpleConAlmacenaGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO realizaRecepcionReactivoGrilla: " + realizaRecepcionReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO prodCertAnalisisReactivoGrilla: " + prodCertAnalisisReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO nombreFabReactivoGrilla: " + nombreFabReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO nombreImportadorReactivoGrilla: " + nombreImportadorReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO servicioFunReactivoGrilla: " + servicioFunReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO fechaIncidenteGrilla: " + fechaIncidenteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO descripcionIncidenteGrilla: " + descripcionIncidenteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO desenlaceIncidenteGrilla: " + desenlaceIncidenteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO otroIncidenteGrilla: " + otroIncidenteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO tieneProgGestionGrilla: " + tieneProgGestionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO realizoAnalisisGestionGrilla: " + realizoAnalisisGestionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO herramientaAnalisisGestionGrilla: " + herramientaAnalisisGestionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO otroAnalisisGestionGrilla: " + otroAnalisisGestionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO causaEfectoAnalisisGestionGrilla: " + causaEfectoAnalisisGestionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO causaProbableEfectoGestionGrilla: " + causaProbableEfectoGestionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO descripcionCausaEfectoGestionGrilla: " + descripcionCausaEfectoGestionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO codigoCausaEfectoGestionGrilla: " + codigoCausaEfectoGestionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO inicioAccionesEfectoGestionGrilla: " + inicioAccionesEfectoGestionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO accionesGestionGrilla: " + accionesGestionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO reportoDistribGestionGrilla: " + reportoDistribGestionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO fechaReporteImportadorGestionGrilla: " + fechaReporteImportadorGestionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO fechaEnvioDistribuidorGestionGrilla: " + fechaEnvioDistribuidorGestionGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO nombreReportanteGrilla: " + nombreReportanteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO profesionReportanteGrilla: " + profesionReportanteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO organizacionReportanteGrilla: " + organizacionReportanteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO direccionReportanteGrilla: " + direccionReportanteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO telefonoReportanteGrilla: " + telefonoReportanteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO departamentoReportanteGrilla: " + departamentoReportanteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO ciudadReportanteGrilla: " + ciudadReportanteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO correoReportanteGrilla: " + correoReportanteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO fechaNotIPSReportanteGrilla: " + fechaNotIPSReportanteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO autorizaDivulgaReportanteGrilla: " + autorizaDivulgaReportanteGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO tipoReportanteInfoReactivoGrilla: " + tipoReportanteInfoReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO estadoReporteInfoReactivoGrilla: " + estadoReporteInfoReactivoGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO fechaGETGrilla: " + fechaGETGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO gestionETGrilla: " + gestionETGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO estadoGETGrilla: " + estadoGETGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO fechaGInvimaGrilla: " + fechaGInvimaGrilla);
                        //logBeanWebReactivo.info ("VALOR DEL CAMPO observacionesGInvimaGrilla: " + observacionesGInvimaGrilla);
                        //logBeanWebReactivo.info ("*****************************************************************************");
                        //logBeanWebReactivo.info ("*****************************************************************************");
                        //logBeanWebReactivo.info ("*****************************************************************************");
                        */
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        codDepto1 = getServicioReporteTrimestral().obtenerCodigoDeptoPorNombre(departamentoInstitucionGrilla);
                        codMunicipio1 = getServicioReporteTrimestral().obtenerCodigoMunicipioPorNombre(codDepto1,ciudadInstitucionGrilla);
                        codDepto2 = getServicioReporteTrimestral().obtenerCodigoDeptoPorNombre(departamentoReportanteGrilla);
                        codMunicipio2 = getServicioReporteTrimestral().obtenerCodigoMunicipioPorNombre(codDepto2,ciudadReportanteGrilla);
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        codigonivelComplejidadInstitucion = obtenerIdComplejidad(nivelComplejidadInstitucionGrilla);
                        codigonaturaleza = obtenerIdNaturaleza(naturalezaInstitucionGrilla);
                        codigotipoDocumento = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoTipoIdentificacionReportante(tipIdentPacienteGrilla);
                        codigosexo = obtenerIdSexo(sexoPacienteGrilla);
                        codigoedadEn = obtenerIdEdadEn(edadEnPacienteGrilla);
                        codigotipoDispositivo = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoTipoDispositivo(codigoTipoDiagReactivoGrilla);
                        codigoprocedenciaReactivo = obtenerIdValorSiNo(procedenciaReactivoGrilla);
                        codigorequiereCadFrioReactivo = obtenerIdValorSiNo(requiereCadFrioReactivoGrilla);
                        codigocatReactivo = obtenerIdCategoria(catReactivoGrilla);
                        codigocumpleConAlmacena = obtenerIdValorSiNo(cumpleConAlmacenaGrilla);
                        codigorealizaRecepcionReactivo = obtenerIdValorSiNo(realizaRecepcionReactivoGrilla);
                        codigoprodCertAnalisisReactivo = obtenerIdValorSiNo(prodCertAnalisisReactivoGrilla);
                        codigoservicioFunReactivo = obtenerIdServicioAreaFuncionamiento(servicioFunReactivoGrilla);
                        codigodesenlaceIncidente = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoTipoDesenlace(desenlaceIncidenteGrilla);
                        codigotieneProgGestion = obtenerIdValorSiNo(tieneProgGestionGrilla);
                        codigorealizoAnalisisGestion = obtenerIdValorSiNo(realizoAnalisisGestionGrilla);
                        codigoherramientaAnalisisGestion = obtenerIdHerramientaAnalisis(herramientaAnalisisGestionGrilla);
                        codigocausaEfectoAnalisisGestion = obtenerIdSiNoCodigoCausa(causaEfectoAnalisisGestionGrilla);
                        codigodescripcionCausaEfectoGestion = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoDescripcionCausaEfecto(descripcionCausaEfectoGestionGrilla);
                        codigocausaProbableEfectoGestion = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoCausaProbable(causaProbableEfectoGestionGrilla);
                        codigocodigoCausaEfectoGestion = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoCausaProbable(codigoCausaEfectoGestionGrilla);
                        codigoinicioAccionesEfectoGestion = obtenerIdValorSiNo(inicioAccionesEfectoGestionGrilla);
                        codigoreportoDistribGestion = obtenerIdValorSiNo(reportoDistribGestionGrilla);
                        codigoprofesionReportante = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoCargos(profesionReportanteGrilla);
                        codigoautorizaDivulgaReportante = obtenerIdValorSiNo(autorizaDivulgaReportanteGrilla);
                        codigotipoReportanteInfoReactivo = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoTipoDeReportante(tipoReportanteInfoReactivoGrilla);
                        codigoestadoReporteInfoReactivo = obtenerIdEstadoReporte(estadoReporteInfoReactivoGrilla);
                        
                        if (estadoGETGrilla != null)
                        {
                            codigoestadoGET = obtenerIdEstadoReporte(estadoGETGrilla);
                        }
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //logBeanWebReactivo.info ("----------------------------------------------------------");
                        //logBeanWebReactivo.info ("----------------------------------------------------------");
                        /*
                        //logBeanWebReactivo.info ("CODIGOS INTERNOS DETECTADOS");
                        //logBeanWebReactivo.info ("codDepto1 = " + codDepto1);
                        //logBeanWebReactivo.info ("codMunicipio1 = " + codMunicipio1);
                        //logBeanWebReactivo.info ("codDepto2 = " + codDepto2);
                        //logBeanWebReactivo.info ("codMunicipio2 = " + codMunicipio2);
                        */
                        //****************************************************************************
                        //****************************************************************************
                        //logBeanWebReactivo.info ("-------------------------------------------------------------------------");
                        /*
                        //logBeanWebReactivo.info ("REGISTRO SANITARIO ENCONTRADO = " + registroSanReactivoGrilla);
                        //logBeanWebReactivo.info ("CODIGO EXPEDIENTE = " + expedienteReactivoGrilla);
                        */
                        //logBeanWebReactivo.info ("-------------------------------------------------------------------------");
                        //****************************************************************************
                        //****************************************************************************
                        /*
                        //logBeanWebReactivo.info("codigonivelComplejidadInstitucion EN EL ALMACENAMIENTO: " + codigonivelComplejidadInstitucion);
                        //logBeanWebReactivo.info("codigonaturaleza EN EL ALMACENAMIENTO: " + codigonaturaleza);
                        //logBeanWebReactivo.info("codigotipoDocumento EN EL ALMACENAMIENTO: " + codigotipoDocumento);
                        //logBeanWebReactivo.info("codigosexo EN EL ALMACENAMIENTO: " + codigosexo);
                        //logBeanWebReactivo.info("codigoedadEn EN EL ALMACENAMIENTO: " + codigoedadEn);
                        //logBeanWebReactivo.info("codigotipoDispositivo EN EL ALMACENAMIENTO: " + codigotipoDispositivo);
                        //logBeanWebReactivo.info("codigoprocedenciaReactivo EN EL ALMACENAMIENTO: " + codigoprocedenciaReactivo);
                        //logBeanWebReactivo.info("codigorequiereCadFrioReactivo EN EL ALMACENAMIENTO: " + codigorequiereCadFrioReactivo);
                        //logBeanWebReactivo.info("codigocatReactivo EN EL ALMACENAMIENTO: " + codigocatReactivo);
                        //logBeanWebReactivo.info("codigocumpleConAlmacena EN EL ALMACENAMIENTO: " + codigocumpleConAlmacena);
                        //logBeanWebReactivo.info("codigorealizaRecepcionReactivo EN EL ALMACENAMIENTO: " + codigorealizaRecepcionReactivo);
                        //logBeanWebReactivo.info("codigoprodCertAnalisisReactivo EN EL ALMACENAMIENTO: " + codigoprodCertAnalisisReactivo);
                        //logBeanWebReactivo.info("codigoservicioFunReactivo EN EL ALMACENAMIENTO: " + codigoservicioFunReactivo);
                        //logBeanWebReactivo.info("codigodesenlaceIncidente EN EL ALMACENAMIENTO: " + codigodesenlaceIncidente);
                        //logBeanWebReactivo.info("codigotieneProgGestion EN EL ALMACENAMIENTO: " + codigotieneProgGestion);
                        //logBeanWebReactivo.info("codigorealizoAnalisisGestion EN EL ALMACENAMIENTO: " + codigorealizoAnalisisGestion);
                        //logBeanWebReactivo.info("codigoherramientaAnalisisGestion EN EL ALMACENAMIENTO: " + codigoherramientaAnalisisGestion);
                        */
                        logBeanWebReactivo.info("codigocausaEfectoAnalisisGestion EN EL ALMACENAMIENTO: " + codigocausaEfectoAnalisisGestion);
                        logBeanWebReactivo.info("codigodescripcionCausaEfectoGestion EN EL ALMACENAMIENTO: " + codigodescripcionCausaEfectoGestion);
                        logBeanWebReactivo.info("codigocausaProbableEfectoGestion EN EL ALMACENAMIENTO: " + codigocausaProbableEfectoGestion);
                        logBeanWebReactivo.info("codigocodigoCausaEfectoGestion EN EL ALMACENAMIENTO: " + codigocodigoCausaEfectoGestion);
                        /*
                        //logBeanWebReactivo.info("codigoinicioAccionesEfectoGestion EN EL ALMACENAMIENTO: " + codigoinicioAccionesEfectoGestion);
                        //logBeanWebReactivo.info("codigoreportoDistribGestion EN EL ALMACENAMIENTO: " + codigoreportoDistribGestion);
                        //logBeanWebReactivo.info("codigoprofesionReportante EN EL ALMACENAMIENTO: " + codigoprofesionReportante);
                        //logBeanWebReactivo.info("codigoautorizaDivulgaReportante EN EL ALMACENAMIENTO: " + codigoautorizaDivulgaReportante);
                        //logBeanWebReactivo.info("codigotipoReportanteInfoReactivo EN EL ALMACENAMIENTO: " + codigotipoReportanteInfoReactivo);
                        //logBeanWebReactivo.info("codigoestadoReporteInfoReactivo EN EL ALMACENAMIENTO: " + codigoestadoReporteInfoReactivo);
                        //logBeanWebReactivo.info("codigoestadoGET EN EL ALMACENAMIENTO: " + codigoestadoGET);
                        */
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************


                        //Armamos los objetos
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //logBeanWebReactivo.info ("<<<<<<<<<<<< -------------------------- INICIO INSERCIÓN DE REGISTRO ------------------------------ >>>>>>>>>>>>>>>>>>>>>>>>");
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        registroEventoTemporal = new ReactivoEventosTemp();
                        registroEventoTemporal.setReporte(precolNuevo);
                        registroEventoTemporal.setFechEvento(fechaIncidenteGrilla);
                        registroEventoTemporal.setEfectoIndeseado(null);
                        registroEventoTemporal.setFechReporte(fechaSistema);
                        registroEventoTemporal.setCdgProblema(null);
                        registroEventoTemporal.setDescripEfecto(descripcionIncidenteGrilla);
                        registroEventoTemporal.setClasificacion(null);
                        registroEventoTemporal.setCdgDesenlace(Integer.valueOf(codigodesenlaceIncidente));
                        registroEventoTemporal.setOtroDesenlace(otroIncidenteGrilla);
                        registroEventoTemporal.setDesempeno(null);
                        registroEventoTemporal.setInternet(null);
                        registroEventoTemporal.setFechaIngreso(fechaSistema);
                        registroEventoTemporal.setReportado(null);
                        registroEventoTemporal.setIdrol(Integer.valueOf(this.getIdRolReportante()));
                        registroEventoTemporal.setIdips(this.getIdEPSReportante());
                        registroEventoTemporal.setReportepre(precolNuevo);
                        registroEventoTemporal.setCdgSeriedad(0);
                        //***************************************************************************
                        seInsertoReactivoEventosTemp = ejbServicioRemotoReporteMasivoTrimestral.crearRegistroReactivoEventosTemporal(registroEventoTemporal, precolNuevo);
                        //logBeanWebReactivo.info ("INSERTANDO REGISTRO DE EVENTOS DE REACTIVO VIGILANCIA CON PRERDIV = " + precolNuevo + " - RESULTADO: " + seInsertoReactivoEventosTemp);

                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        registroPacienteTemporal = new ReactivoPacienteTemp();
                        registroPacienteTemporal.setReporte(precolNuevo);
                        registroPacienteTemporal.setNombrePaciente(null);
                        registroPacienteTemporal.setTipidentifi(codigotipoDocumento);
                        registroPacienteTemporal.setIdentifi(identificacionPacienteGrilla);
                        registroPacienteTemporal.setEdad(Integer.valueOf(edadPacienteGrilla));
                        registroPacienteTemporal.setEdadEn(codigoedadEn);
                        registroPacienteTemporal.setGenero(codigosexo);
                        registroPacienteTemporal.setDireccPaciente(null);
                        registroPacienteTemporal.setTelefoPaciente(null);
                        //***************************************************************************
                        seInsertoReactivoPacienteTemp = ejbServicioRemotoReporteMasivoTrimestral.crearRegistroReactivoPacienteTemporal(registroPacienteTemporal, precolNuevo);
                        //logBeanWebReactivo.info ("INSERTANDO REGISTRO DE PACIENTE DE REACTIVO VIGILANCIA CON PRERDIV = " + precolNuevo + " - RESULTADO: " + seInsertoReactivoPacienteTemp);

                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        registroReportanteTemporal = new ReactivoReportanteTemp();
                        registroReportanteTemporal.setReporte(precolNuevo);
                        registroReportanteTemporal.setNombresApellidos(nombreReportanteGrilla);
                        registroReportanteTemporal.setIdentificacion(null);
                        registroReportanteTemporal.setCargo(codigoprofesionReportante);
                        registroReportanteTemporal.setProfesion(profesionReportanteGrilla);
                        registroReportanteTemporal.setDireccion(direccionReportanteGrilla);
                        registroReportanteTemporal.setPais("CO");
                        registroReportanteTemporal.setCodDepart(codDepto2);
                        registroReportanteTemporal.setCodMun(codMunicipio2);
                        registroReportanteTemporal.setTelefono(telefonoReportanteGrilla);
                        registroReportanteTemporal.setMovil(null);
                        registroReportanteTemporal.setEmail(correoReportanteGrilla);
                        registroReportanteTemporal.setDivulgacion(codigoautorizaDivulgaReportante);
                        registroReportanteTemporal.setFechaNotificacion(fechaNotIPSReportanteGrilla);
                        registroReportanteTemporal.setAreaPertenece(null);
                        //***************************************************************************
                        seInsertoReactivoReportanteTemp = ejbServicioRemotoReporteMasivoTrimestral.crearRegistroReactivoReportanteTemporal(registroReportanteTemporal, precolNuevo);
                        //logBeanWebReactivo.info ("INSERTANDO REGISTRO DE REPORTANTE DE REACTIVO VIGILANCIA CON PRERDIV = " + precolNuevo + " - RESULTADO: " + seInsertoReactivoReportanteTemp);


                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        registroGestionRealizadaTemporal = new ReactivoGestionrealizadaTemp();
                        registroGestionRealizadaTemporal.setReporte(precolNuevo);
                        registroGestionRealizadaTemporal.setCausaProbable(codigocausaProbableEfectoGestion);
                        registroGestionRealizadaTemporal.setCausaEfecto(codigocodigoCausaEfectoGestion);
                        registroGestionRealizadaTemporal.setImportador(nombreImportadorReactivoGrilla);
                        registroGestionRealizadaTemporal.setFabricante(nombreFabReactivoGrilla);
                        registroGestionRealizadaTemporal.setComercializador(null);
                        registroGestionRealizadaTemporal.setDistribuidor(codigoreportoDistribGestion);
                        registroGestionRealizadaTemporal.setFechaNotificacion(fechaReporteImportadorGestionGrilla);
                        registroGestionRealizadaTemporal.setEnviadoDistriImport(reportoDistribGestionGrilla);
                        registroGestionRealizadaTemporal.setGestionRiesgo(codigotieneProgGestion);
                        registroGestionRealizadaTemporal.setAnalisisEfecto(codigorealizoAnalisisGestion);
                        registroGestionRealizadaTemporal.setHerramientaAnalisis(codigoherramientaAnalisisGestion);
                        registroGestionRealizadaTemporal.setOtraHerramienta(otroAnalisisGestionGrilla);
                        registroGestionRealizadaTemporal.setDescripcionCausa(codigodescripcionCausaEfectoGestion);
                        registroGestionRealizadaTemporal.setAcciones(codigoinicioAccionesEfectoGestion);
                        registroGestionRealizadaTemporal.setDescripcionAcciones(accionesGestionGrilla);
                        //***************************************************************************
                        seInsertoReactivoGestionrealizadaTemp = ejbServicioRemotoReporteMasivoTrimestral.crearRegistroReactivoGestionRealizadaTemporal(registroGestionRealizadaTemporal,precolNuevo);
                        //logBeanWebReactivo.info ("INSERTANDO REGISTRO DE GESTIÓN REALIZADA DE REACTIVO VIGILANCIA CON PRERDIV = " + precolNuevo + " - RESULTADO: " + seInsertoReactivoGestionrealizadaTemp);

                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        registroCamposComplementariosTemporal = new ReactivoCamposcompTemp();
                        //***************************************************************************
                        //***************************************************************************
                        registroCamposComplementariosTemporal.setReporte(precolNuevo);
                        //registroCamposComplementariosTemporal.setCodigoCausa(Integer.valueOf(codigocausaEfectoAnalisisGestion));
                        registroCamposComplementariosTemporal.setCodigoCausa(Integer.valueOf(codigodescripcionCausaEfectoGestion));
                        registroCamposComplementariosTemporal.setOrganizacion(organizacionReportanteGrilla);
                        registroCamposComplementariosTemporal.setFechaEnvioReactivo(fechaEnvioDistribuidorGestionGrilla);
                        registroCamposComplementariosTemporal.setExpediente(expedienteReactivoGrilla);
                        registroCamposComplementariosTemporal.setCodigoUnicoReactivo(codUnicoReactivoGrilla);
                        registroCamposComplementariosTemporal.setCodigoTipoDiagnostico(codigotipoDispositivo);
                        registroCamposComplementariosTemporal.setReferencia(referenciaReactivoGrilla);
                        registroCamposComplementariosTemporal.setCategoriaReactivo(Integer.valueOf(codigocatReactivo));
                        registroCamposComplementariosTemporal.setIndRealizaRecepcion(codigorealizaRecepcionReactivo);
                        registroCamposComplementariosTemporal.setCdgTiporeportante(Integer.valueOf(codigotipoReportanteInfoReactivo));
                        registroCamposComplementariosTemporal.setEstadoReporte(obtenerCodigoEstadoReporte(estadoReporteInfoReactivoGrilla));
                        //Se activará más adelante porque quitaron los 5 campos de la gestión (Secciones H e I)
                        
                        //*****************************************************************************
                        //*****************************************************************************
                        if (fechaGETGrilla != null)
                        {
                            registroCamposComplementariosTemporal.setFechaGestionEnte(fechaGETGrilla);
                        }
                        //*****************************************************************************
                        //*****************************************************************************
                        if (gestionETGrilla != null)
                        {
                            registroCamposComplementariosTemporal.setGestionEnte(gestionETGrilla);
                        }
                        //*****************************************************************************
                        //*****************************************************************************
                        if (estadoGETGrilla != null)
                        {
                            registroCamposComplementariosTemporal.setEstadoGestionEnte(obtenerCodigoEstadoReporte(estadoGETGrilla));
                        }
                        //*****************************************************************************
                        //*****************************************************************************
                        if (fechaGInvimaGrilla != null)
                        {
                            registroCamposComplementariosTemporal.setFechaGestionInvima(fechaGInvimaGrilla);
                        }
                        //*****************************************************************************
                        //*****************************************************************************
                        if (observacionesGInvimaGrilla != null)
                        {
                            registroCamposComplementariosTemporal.setObservacionInvima(observacionesGInvimaGrilla);
                        }
                        //*****************************************************************************
                        //*****************************************************************************
                        
                        //*****************************************************************************
                        //*****************************************************************************
                        //*****************************************************************************
                        //*****************************************************************************
                        //Pasan los 5 campos en limpio
                        /*
                        registroCamposComplementariosTemporal.setFechaGestionEnte(null);
                        registroCamposComplementariosTemporal.setGestionEnte(null);
                        registroCamposComplementariosTemporal.setEstadoGestionEnte(null);
                        registroCamposComplementariosTemporal.setFechaGestionInvima(null);
                        registroCamposComplementariosTemporal.setObservacionInvima(null);
                        */
                        //***************************************************************************
                        //***************************************************************************
                        registroCamposComplementariosTemporal.setCodigoFuncionario(0);
                        //***************************************************************************
                        seInsertoReactivoCamposcompTemp = ejbServicioRemotoReporteMasivoTrimestral.crearRegistroReactivoCamposComplementariosTemporal(registroCamposComplementariosTemporal,precolNuevo);
                        //logBeanWebReactivo.info ("INSERTANDO REGISTRO DE CAMPOS COMPLEMENTARIOS DE REACTIVO VIGILANCIA CON PRERDIV = " + precolNuevo + " - RESULTADO: " + seInsertoReactivoCamposcompTemp);


                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        registroInstitucionTemporal = new ReactivoInstitucionTemp();
                        registroInstitucionTemporal.setReporte(precolNuevo);
                        registroInstitucionTemporal.setInstitucion(nombreInstitucionGrilla);
                        registroInstitucionTemporal.setIdentificacion(nitInstitucionGrilla);
                        registroInstitucionTemporal.setDireccion(direccionInstitucionGrilla);
                        registroInstitucionTemporal.setNaturaleza(codigonaturaleza);
                        registroInstitucionTemporal.setComplejidad(codigonivelComplejidadInstitucion);
                        registroInstitucionTemporal.setCodDepart(codDepto1);
                        registroInstitucionTemporal.setCodMun(codMunicipio1);
                        registroInstitucionTemporal.setEmail(null);
                        registroInstitucionTemporal.setFechaReporte(fechaSistema);
                        registroInstitucionTemporal.setTelefono(null);
                        //***************************************************************************
                        seInsertoReactivoInstitucionTemp = ejbServicioRemotoReporteMasivoTrimestral.crearRegistroReactivoInstitucionTemporal(registroInstitucionTemporal,precolNuevo);
                        //logBeanWebReactivo.info ("INSERTANDO REGISTRO DE INSTITUCION DE REACTIVO VIGILANCIA CON PRERDIV = " + precolNuevo + " - RESULTADO: " + seInsertoReactivoInstitucionTemp);


                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        registroProductoTemporal = new ReactivoProductoTemp();        
                        registroProductoTemporal.setReporte(precolNuevo);
                        registroProductoTemporal.setNombreReactivo(nombreReactivoGrilla);
                        registroProductoTemporal.setNroregsan(registroSanReactivoGrilla);
                        registroProductoTemporal.setFechaVenci(fechaVenReactivoGrilla);
                        registroProductoTemporal.setLote(loteReactivoGrilla);
                        registroProductoTemporal.setProcedencia(codigoprocedenciaReactivo);
                        registroProductoTemporal.setCadenaFrio(codigorequiereCadFrioReactivo);
                        registroProductoTemporal.setTemperatura(temperaturaReactivoGrilla);
                        registroProductoTemporal.setCertiAnalisis(codigoprodCertAnalisisReactivo);
                        registroProductoTemporal.setDistriUsuario(null);
                        registroProductoTemporal.setCondiciAlmacen(codigocumpleConAlmacena);
                        try{registroProductoTemporal.setAreaFunciona(Integer.valueOf(codigoservicioFunReactivo));}catch(Exception e){}
                        registroProductoTemporal.setOtraArea(null);
                        //***************************************************************************
                        seInsertoReactivoProductoTemp = ejbServicioRemotoReporteMasivoTrimestral.crearRegistroReactivoProductoTemporal(registroProductoTemporal,precolNuevo);
                        //logBeanWebReactivo.info ("INSERTANDO REGISTRO PRODUCTO (REACTIVO IN VITRO) DE REACTIVO VIGILANCIA CON PRERDIV = " + precolNuevo + " - RESULTADO: " + seInsertoReactivoProductoTemp);


                        //logBeanWebReactivo.info ("<<<<<<<<<<<< -------------------------- FIN INSERCIÓN DE REGISTRO ------------------------------ >>>>>>>>>>>>>>>>>>>>>>>>");

                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //Incrementamos el precol
                        secuenciaPreCol++;
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        //***************************************************************************
                        if (seInsertoReactivoEventosTemp == true)
                        {
                            contadorReactivoEventosTemp++;
                        }

                        if (seInsertoReactivoPacienteTemp == true)
                        {
                            contadorReactivoPacienteTemp++;
                        }

                        if (seInsertoReactivoReportanteTemp  == true)
                        {
                            contadorReactivoReportanteTemp++;
                        }

                        if (seInsertoReactivoGestionrealizadaTemp  == true)
                        {
                          contadorReactivoGestionrealizadaTemp++;  
                        }

                        if (seInsertoReactivoCamposcompTemp == true)
                        {
                            contadorReactivoCamposcompTemp++;
                        }

                        if (seInsertoReactivoInstitucionTemp == true)
                        {
                            contadorReactivoInstitucionTemp++;
                        }

                        if (seInsertoReactivoProductoTemp == true)
                        {
                            contadorReactivoProductoTemp++;
                        }

                        //***************************************************************************
                        //***************************************************************************
                        if (contadorReactivoEventosTemp == contadorReactivoPacienteTemp &&
                            contadorReactivoReportanteTemp == contadorReactivoGestionrealizadaTemp &&
                            contadorReactivoCamposcompTemp == contadorReactivoInstitucionTemp &&
                            contadorReactivoInstitucionTemp  == contadorReactivoProductoTemp)
                        {
                            //logBeanWebReactivo.info ("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                            //logBeanWebReactivo.info ("SE INSERTARON CORRECTAMENTE LOS REGISTROS EN LAS 7 TABLAS PARA EL PRERDIV =" + valorPrecol);
                            //logBeanWebReactivo.info ("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                            contadorRegistroPrecol++;
                        }
                        //***************************************************************************
                        //***************************************************************************
                        this.getDatosCargueCorreo().add(precolNuevo);
                        this.getDatosCargueCorreo().add(fechaSistema.toString());
                        this.getDatosCargueCorreo().add(nombreReactivoGrilla);
                        this.getDatosCargueCorreo().add(registroSanReactivoGrilla);
                        this.getDatosCargueCorreo().add(expedienteReactivoGrilla);
                        this.getDatosCargueCorreo().add(descripcionCausaEfectoGestionGrilla);
                        this.getDatosCargueCorreo().add(causaProbableEfectoGestionGrilla);
                        this.getDatosCargueCorreo().add(accionesGestionGrilla);
                        this.getDatosCargueCorreo().add(estadoReporteInfoReactivoGrilla);
                        //***************************************************************************
                        //***************************************************************************
                    }//Fin del for de recorrido de la grilla
                    //*************************************************************
                    //*************************************************************
                    //*************************************************************
                    //*************************************************************
                    //logBeanWebReactivo.info ("*************************************************************");
                    //logBeanWebReactivo.info ("*************************************************************");
                    //logBeanWebReactivo.info ("TOTAL DE REGISTROS DE LA TABLA dbo.reactivo_eventos:  " + contadorReactivoEventosTemp);
                    //logBeanWebReactivo.info ("TOTAL DE REGISTROS DE LA TABLA dbo.reactivo_paciente:  " + contadorReactivoPacienteTemp);
                    //logBeanWebReactivo.info ("TOTAL DE REGISTROS DE LA TABLA dbo.reactivo_reportante:  " + contadorReactivoReportanteTemp);
                    //logBeanWebReactivo.info ("TOTAL DE REGISTROS DE LA TABLA dbo.reactivo_gestionrealizada:  " + contadorReactivoGestionrealizadaTemp);
                    //logBeanWebReactivo.info ("TOTAL DE REGISTROS DE LA TABLA dbo.reactivo_camposcomp:  " + contadorReactivoCamposcompTemp);
                    //logBeanWebReactivo.info ("TOTAL DE REGISTROS DE LA TABLA dbo.reactivo_institucion:  " + contadorReactivoInstitucionTemp);
                    //logBeanWebReactivo.info ("TOTAL DE REGISTROS DE LA TABLA dbo.reactivo_producto:  " + contadorReactivoProductoTemp);            
                    //logBeanWebReactivo.info ("*************************************************************");
                    //logBeanWebReactivo.info ("*************************************************************");
                    //*************************************************************
                    //*************************************************************
                    if (contadorReactivoEventosTemp == this.getListaDatosExcel().size() &&
                        contadorReactivoPacienteTemp == this.getListaDatosExcel().size() &&
                        contadorReactivoReportanteTemp == this.getListaDatosExcel().size() &&
                        contadorReactivoGestionrealizadaTemp == this.getListaDatosExcel().size() &&
                        contadorReactivoCamposcompTemp == this.getListaDatosExcel().size() &&
                        contadorReactivoInstitucionTemp == this.getListaDatosExcel().size() &&
                        contadorReactivoProductoTemp == this.getListaDatosExcel().size())
                    {
                        this.setOperacion("resultadoCargue");
                        //**************************************************************
                        //**************************************************************
                        this.setConteoEventos(String.valueOf(contadorReactivoEventosTemp));
                        this.setConteoPaciente(String.valueOf(contadorReactivoPacienteTemp));
                        this.setConteoReportante(String.valueOf(contadorReactivoReportanteTemp));
                        this.setConteoGestionRealizada(String.valueOf(contadorReactivoGestionrealizadaTemp));
                        this.setConteoCamposComplementarios(String.valueOf(contadorReactivoCamposcompTemp));
                        this.setConteoInstitucion(String.valueOf(contadorReactivoInstitucionTemp));
                        this.setConteoProducto(String.valueOf(contadorReactivoProductoTemp));
                        //**************************************************************
                        //**************************************************************
                        seInsertoPaqueteCompleto = true;
                        //**************************************************************
                        //**************************************************************
                        //datosProcesados = obtenerDatosTablaCorreo(this.datosCargueCorreo);
                        enviarCorreoElectronicoNotificacionReporteTrimestral(seInsertoPaqueteCompleto, this.getCorreoReportante(),fechaSistema,
                        contadorReactivoEventosTemp,this.getDatosCargueCorreo(), this.getIdRolUsuarioReportante());
                        //**************************************************************
                        //**************************************************************
                        this.setHabilitaGuardado(false);
                        //**************************************************************
                        //**************************************************************
                        //logBeanWebReactivo.info ("SE INSERTÓ COMPLETO EL ARCHIVO DE EXCEL DE REPORTES MASIVOS = " + seInsertoPaqueteCompleto);
                        //mensajeSalida = "El Archivo de Cargue Masivo Trimestral con Evento fue cargado exitosamente en las tablas del Sistema";
                        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proceso Exitoso",mensajeSalida));
                        this.setListaDatosExcel(null);
                        this.setListadoValidacionesTrimestral(null);
                        RequestContext.getCurrentInstance().execute("redireccionarMismaVentana('confirmacionCargue.xhtml');");
                        this.setFechaRegistro(new java.util.Date());
                    }
                    else
                    {
                        this.setOperacion("cargueArchivo");
                        this.setHabilitaGuardado(false);
                        /*
                        //logBeanWebReactivo.info ("LIMPIANDO TABLAS ANTE CARGUE FALLIDO");
                        getServicioReporteTrimestral().limpiarTablaCargueMasivo("dbo.reactivo_eventos_temp");
                        getServicioReporteTrimestral().limpiarTablaCargueMasivo("dbo.reactivo_paciente_temp");
                        getServicioReporteTrimestral().limpiarTablaCargueMasivo("dbo.reactivo_reportante_temp");
                        getServicioReporteTrimestral().limpiarTablaCargueMasivo("dbo.reactivo_gestionrealizada_temp");
                        getServicioReporteTrimestral().limpiarTablaCargueMasivo("dbo.reactivo_camposcomp_temp");
                        getServicioReporteTrimestral().limpiarTablaCargueMasivo("dbo.reactivo_institucion_temp");
                        getServicioReporteTrimestral().limpiarTablaCargueMasivo("dbo.reactivo_producto_temp");
                        */
                        //logBeanWebReactivo.info ("NO FUE POSIBLE INSERTAR LOS REGISTROS COMPLETOS DEL ARCHIVO DE CARGUE TRIMESTRAL = " + seInsertoPaqueteCompleto);
                        //logBeanWebReactivo.info ("****************************************************************************************");
                        //logBeanWebReactivo.info ("****************************************************************************************");
                        //mensajeSalida = "El Archivo de Cargue Masivo Trimestral con Evento no pudo ser cargado completo.  Se eliminarán los registros";
                        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error",mensajeSalida));
                        this.setListaDatosExcel(null);
                        this.setListadoValidacionesTrimestral(null);
                        //RequestContext.getCurrentInstance().update("tablaGrillaMasiva");
                        mensajeSalida = "No fue posible realizar el cargue masivo.  Verifique el contenido de su archivo, e inténtelo nuevamente";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error",mensajeSalida));
                    }
                    //*************************************************************
                    //*************************************************************
                    //logBeanWebReactivo.info ("ACTUALICE LOS COMPONENTES EN LA VISTA");            
                    //logBeanWebReactivo.info ("*************************************************************");
                    //logBeanWebReactivo.info ("*************************************************************");
            
                    //*************************************************************
                    //*************************************************************
                    //*************************************************************
            }
            else
            {
                        //logBeanWebReactivo.info ("No se puede grabar porque hay filas sin cerrar");
                        mensajeSalida = "No es posible realizar el almacenamiento de los Registros de Cargue Masivo Trimestral puesto que el usuario debe guardar los cambios en las filas de la grilla que ha seleccionado";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error",mensajeSalida));
            }
            //***************************************************************************
            //***************************************************************************
            //***************************************************************************
        }
        
        catch (Exception errorMetodo)
        {
            //logBeanWebReactivo.info ("GENERANDO ERROR POR REGISTROS DUPLICADOS");
            errorMetodo.printStackTrace();
            this.setHabilitaGuardado(false);
            mensajeSalida = "El Archivo de Cargue Masivo Trimestral con Evento no puede ser cargado, puesto que sus registros ya fueron enviados al Sistema";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error",mensajeSalida));
        }
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerIdSiNoCodigoCausa (String valor)
    {
        String resultado = "";
        
        if (valor.equals("S"))
        {
            resultado = "1";
        }
        else
        {
            resultado = "0";
        }
        
        return (resultado);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void enviarCorreoElectronicoNotificacionReporteTrimestral (boolean resultado, String correoDestinatario, java.util.Date fechaReporte, int totalRegistros, 
    ArrayList datosTablaGrilla, String rolUsuario)
    {
        try
        {
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //logBeanWebReactivo.info ("SE GENERARÁ EL MENSAJE PARA ENVIO AL REPORTANTE");
            //**********************************************************************************************
            //**********************************************************************************************
            PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
            String servidor = pr.getProperty("servidor");
            String remitente = pr.getProperty("remitente");
            String usuario = pr.getProperty("usuario");
            String passwd = pr.getProperty("password");
            String fecha = Fecha.formateaFecha(fechaReporte, "dd/MM/yyyy hh:mm a");
            String mensajeCorreo = "";
            int i = 0;
            //**********************************************************************************************
            //**********************************************************************************************
            //logBeanWebReactivo.info ("---------------------------------------------------->");
            //logBeanWebReactivo.info ("FECHA DEL ENVIO DEL CORREO: " + fecha);
            //logBeanWebReactivo.info ("SERVIDOR CORREO: " + servidor);
            //logBeanWebReactivo.info ("SE ENVIA CORREO DESDE: " + remitente);
            //logBeanWebReactivo.info ("CORREO DESTINATARIO = " + correoDestinatario);
            //logBeanWebReactivo.info ("---------------------------------------------------->");
            //**********************************************************************************************
            //**********************************************************************************************
            if (resultado != false) 
            {
                /*
                mensajeCorreo = "<h1 style='font-family: verdana;'>SISTEMA DE GESTIÓN Y CONTROL DE DISPOSITIVOS REACTIVOVIGILANCIA</h1>" +
                                "<br/><br/>" +
                                "<b><h2 style='font-family: verdana;'>NOTIFICACIÓN DE INGRESO DE REPORTE MASIVO TRIMESTRAL POR CARGUE DE EXCEL</h2></b><br/>" +
                                "<p style='font-family: verdana;'>EL listado de incidentes de Reporte Masivo Trimestral con Evento han sido ingresados al Sistema de Información del Programa Nacional de Reactivovigilancia, a continuación se presenta un resumen del trámite efectuado: <br/><br/>" +
                                "<br/><b> Fecha y hora del ingreso:</b>  " + fecha + 
                                "<br/><b> Número de Registros de Eventos Adversos cargados:</b>  " + totReg1 + 
                                "<br/><b> Número de Registros de Dispositivos Médicos con incidente:</b>  " + totReg2 + 
                                "<br/><b> Número de Registros de Pacientes afectados por los incidentes de dispositivo:</b>  " + totReg3 + 
                                "<br/><b> Número de Registros de Evaluación de Impacto de nivel de incidencia:</b>  " + totReg4 + 
                                "<br/><br/>" +
                                "<br/><b> TOTAL DE REGISTROS CARGADOS EN EL SISTEMA:</b>  " + (totReg1+totReg2+totReg3+totReg4) + "<br/><br/>";
                */
                //*********************************************************************************************************************
                //*********************************************************************************************************************
                mensajeCorreo = "<h1 style='font-family: verdana;'>SISTEMA DE GESTIÓN Y CONTROL DE REACTIVOS IN VITRO REACTIVOVIGILANCIA</h1>" +
                "<br/><br/>";
                
                //logBeanWebReactivo.info ("NOMBRE DEL REPORTANTE = " + this.getNombreReportante());
                
                if (rolUsuario.equals("2"))
                {
                                mensajeCorreo +=
                                "<b><h2 style='font-family: verdana;'>NOTIFICACIÓN DE INGRESO DEL REPORTE MASIVO TRIMESTRAL - ENTIDAD FABRICANTE/IMPORTADOR: " + this.getNombreReportante().toUpperCase() + "</h2></b><br/>";
                }
                else
                {
                                mensajeCorreo +=
                                "<b><h2 style='font-family: verdana;'>NOTIFICACIÓN DE INGRESO DEL REPORTE MASIVO TRIMESTRAL - ENTIDAD PRESTADORA DE SALUD (IPS): " + this.getNombreReportante().toUpperCase() + "</h2></b><br/>";
                }
                
                mensajeCorreo +=
                "<p style='font-family: verdana;'>El Reporte Masivo Trimestral ha sido ingresado al Sistema de Información del Programa Nacional de ReactivoVigilancia, a continuación se presenta un resumen del trámite efectuado: <br/><br/>" +
                "<br/><b> Fecha y hora del ingreso:</b>  " + fecha + 
                "<br/><b>TOTAL DE REGISTROS CARGADOS EN EL SISTEMA TEMPORAL: </b>" + totalRegistros +
                "<br/><br/>";
                
                mensajeCorreo +=
                "<table id='tablaRegistrosCreados' cellpadding='0' cellspacing='0' border='1' style='font-family: verdana;'>" +
                "<tr>" +
                   "<td><b>CÓDIGO INVIMA</b></td>" +
                   "<td><b>Fecha ingreso reporte</b></td>" +
                   "<td><b>C1. Nombre Genérico del Dispositivo Médico</b></td>" +
                   "<td><b>C3. Registro sanitario o permiso de comercialización</b></td>" +
                   "<td><b>C4. Expediente</b></td>" +
                   "<td><b>D5. Descripción del evento o incidente adverso</b></td>" +
                   "<td><b>E1. Causa probable del evento/incidente</b></td>" +
                   "<td><b>E2.Acciones correctivas y preventivas iniciadas</b></td>" +
                   "<td><b>G4.Estado del Reporte</b></td>" +
                "</tr>";
                //****************************************************************************
                //****************************************************************************
                for (i = 0;  i < datosTablaGrilla.size();  i+=9)
                {
                    //******************************************************
                    //******************************************************
                    mensajeCorreo +=
                    "<tr>" +
                    "<td>" + (String)datosTablaGrilla.get(i) + "</td>" +
                    "<td>" + (String)datosTablaGrilla.get(i+1) + "</td>" +
                    "<td>" + (String)datosTablaGrilla.get(i+2) + "</td>" +
                    "<td>" + (String)datosTablaGrilla.get(i+3) + "</td>" +
                    "<td>" + (String)datosTablaGrilla.get(i+4) + "</td>" +
                    "<td>" + (String)datosTablaGrilla.get(i+5) + "</td>" +
                    "<td>" + (String)datosTablaGrilla.get(i+6) + "</td>" +
                    "<td>" + (String)datosTablaGrilla.get(i+7) + "</td>" +
                    "<td>" + (String)datosTablaGrilla.get(i+8) + "</td>" +
                    "</tr>";
                    //******************************************************
                    //******************************************************
                }//Fin del for de la tabla de correo
                //****************************************************************************
                //****************************************************************************
                mensajeCorreo +=
                "</table> <br/><br/><br/>";
                //****************************************************************************
                //****************************************************************************
                //****************************************************************************
                //****************************************************************************
                //logBeanWebReactivo.info ("ROL DE USUARIO PARA EL CUERPO FINAL DEL CORREO = " + rolUsuario);
                if (rolUsuario.equals("2"))
                {
                    //logBeanWebReactivo.info ("Postfijo correo para Fabricante/Importador");
                    mensajeCorreo +=
                    "<p style='font-family: verdana;'>Una vez el Grupo de ReactivoVigilancia del Invima evalúe la causalidad y gravedad de los reportes precargados recibirá vía correo electrónico el código Invima asignado de los reportes aprobados para su respectiva trazabilidad, junto con el requerimiento por cada caso registrado (si es necesario) y con el estado del reporte (abierto, seguimiento, cerrado).</p> " +
                    "<p style='font-family: verdana;'>Si se requiere ampliar información sobre el caso deberá allegar los soportes correspondientes al correo reactivovigilancia@invima.gov.co (citando el código asignado), acorde al tipo requerimiento y en los tiempos establecidos.</p> " +
                    "<p style='font-family: verdana;'>Agradecemos mantener el estado de alerta, realizando un seguimiento permanente a los Reactivos de Diagnóstico in Vitro que importan y/o fabrican y comercializan en el territorio nacional divulgando la información de seguridad respectiva entre los profesionales de la salud que hacen uso de estos recursos tecnológicos.</p> " +
                    "<p style='font-family: verdana;'>Finalmente, su notificación es fundamental para fortalecer la vigilancia post-mercado de los Reactivos de Diagnóstico in Vitro en el país, en beneficio de la seguridad del paciente.</p> " +
                    "<p style='font-family: verdana;'>Lo anterior, en cumplimiento a lo establecido en la Resolución 2020007532 de 2020 \"Por el cual se modifica el Programa Nacional de ReactivoVigilancia\"</p> " +
                    "<p style='font-family: verdana;'>Nota: Síguenos en Nuestro Fan Page  <b><a href='https://www.facebook.com/RedNacionaldeReactivovigilancia/'>https://www.facebook.com/RedNacionaldeReactivovigilancia/</a></b></p>" +        
                    "<p style='font-family: verdana;'>Cordialmente</p>" +
                    "<br/><br/><br/>" +
                    "<p style='font-family: verdana;'><b>GRUPO DE REACTIVOVIGILANCIA<br/>" +
                    "Dirección de Dispositivos Médicos y otras Tecnologías<br/>" +
                    "INVIMA<br/>" +
                    "Teléfono: (1)2948700 Ext. 3880<br/>" +
                    "<a href='mailto:reactivovigilancia@invima.gov.co'>reactivovigilancia@invima.gov.co</a><b></p>";
                }
                else
                {
                    //logBeanWebReactivo.info ("Postfijo correo para IPS reportante");
                    mensajeCorreo +=
                    "<p style='font-family: verdana;'>Una vez la Secretaria de Salud de su departamento o Distrito, evalúe la causalidad y gravedad de los reportes precargados recibirá vía correo electrónico el código Invima asignado de los reportes aprobados para su respectiva trazabilidad, junto con el requerimiento por cada caso registrado (si es necesario) y con el estado del reporte (abierto, seguimiento, cerrado). </p> " +
                    "<p style='font-family: verdana;'>Si se requiere ampliar información sobre el caso deberá allegar los soportes correspondientes al referente de la Secretaria de Salud, acorde al tipo requerimiento y en los tiempos establecidos.</p>" +        
                    "<p style='font-family: verdana;'>Agradecemos mantener el estado de alerta, realizando un seguimiento permanente a los Reactivos de Diagnóstico in Vitro involucrados, divulgando la información seguridad respectiva entre los profesionales de la salud que realizan uso de estos, a fin de prevenir la ocurrencia de los mismos.</p>" +        
                    "<p style='font-family: verdana;'>Finalmente, su notificación es fundamental para fortalecer la vigilancia post-mercado de los Reactivos de Diagnóstico in Vitro en el país, en beneficio de la seguridad del paciente.</p><br/><br/>" +        
                    "<p style='font-family: verdana;'>Lo anterior, en cumplimiento a lo establecido en la Resolución 2020007532 de 2020 \"Por el cual se modifica el Programa Nacional de ReactivoVigilancia\"</p>" +        
                    "<p style='font-family: verdana;'>Nota: Síguenos en Nuestro Fan Page  <b><a href='https://www.facebook.com/RedNacionaldeReactivovigilancia/'>https://www.facebook.com/RedNacionaldeReactivovigilancia/</a></b></p>" +        
                    "<p style='font-family: verdana;'>Cordialmente</p>" +
                    "<br/><br/><br/>" +
                    "<p style='font-family: verdana;'><b>GRUPO DE REACTIVOVIGILANCIA<br/>" +
                    "Dirección de Dispositivos Médicos y otras Tecnologías<br/>" +
                    "INVIMA<br/>" +
                    "Teléfono: (1)2948700 Ext. 3880<br/>" +
                    "<a href='mailto:reactivovigilancia@invima.gov.co'>reactivovigilancia@invima.gov.co</a><b></p>";
                }

                //*********************************************************************************************************************
                CorreoElectronico correo = CorreoElectronico.getInstance();
                correo.enviarCorreoElectronicoReactivo(correoDestinatario,"","Ingreso de Reporte Masivo Trimestral con Evento de la Entidad", 
                        HtmlValidator.remplazarTildesEnTexto(mensajeCorreo),userSesion,"Reporte Masivo Trimestral");

            }

        }
        
        catch (Exception errorEnvioCorreo)
        {
            errorEnvioCorreo.printStackTrace();
        }
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public int obtenerCodigoEstadoReporte(String nombre)
    {
        int valor = 0;
        
        //logBeanWebReactivo.info ("CODIGO DE ESTADO OBTENIDO = " + nombre);
        
        switch (nombre)
        {
            case "S":  valor = 1;  break;
            case "C":  valor = 2;  break;
            case "A":  valor = 3;  break;
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerNombreEstadoReporte(int valor)
    {
        String nombre = "";
        
        switch (valor)
        {
            case 1:  nombre = "S";  break;
            case 2:  nombre = "C";  break;
            case 3:  nombre = "A";  break;
        }
        
        return (nombre);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void limpiarArchivo()
    {
        try
        {
            //logBeanWebReactivo.info ("*************************************************************");
            //logBeanWebReactivo.info ("*************************************************************");
            //logBeanWebReactivo.info ("Limpiando datos de la grilla y referencia del archivo");
            this.setFile(null);
            this.setListaDatosExcel(null);

            this.listaFilasEditadas = new java.util.ArrayList<Boolean>();
            this.indiceFilasEditadas = 0;
            //logBeanWebReactivo.info ("TAMAÑO DEL VECTOR DE BOOLEANOS: " + this.getListaFilasEditadas().size());
            //logBeanWebReactivo.info ("INDICE DE FILAS EDITADAS: " + this.getIndiceFilasEditadas());
             
            RequestContext.getCurrentInstance().update("tablaGrillaMasiva");
            //logBeanWebReactivo.info ("ACTUALICE LOS COMPONENTES EN LA VISTA AL LIMPIAR EL ARCHIVO");            
            //logBeanWebReactivo.info ("*************************************************************");
            //logBeanWebReactivo.info ("*************************************************************");
        }
        
        catch (Exception errorMetodo)
        {
            errorMetodo.printStackTrace();
        }
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void resetearProcesoCargue()
    {
        try
        {
            //logBeanWebReactivo.info ("*************************************************************");
            //logBeanWebReactivo.info ("*************************************************************");
            //logBeanWebReactivo.info ("Cerrando el proceso de cargue de grilla");
            this.setFile(null);
            this.setListaDatosExcel(null);
            this.setOperacion("cargueArchivo");
            //logBeanWebReactivo.info ("ACTUALICE LOS COMPONENTES EN LA VISTA AL RESETEAR EL PROCESO");            
            //logBeanWebReactivo.info ("*************************************************************");
            //logBeanWebReactivo.info ("*************************************************************");
        }
        
        catch (Exception errorMetodo)
        {
            errorMetodo.printStackTrace();
        }
    }
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    public String formatearFechaSalida (java.util.Date fecha)
    {
        String fechaSalida = "";
        
        fechaSalida = String.valueOf(fecha.getDate()) + "/" + 
                            String.valueOf(fecha.getMonth()+1) + "/" +
                            String.valueOf (fecha.getYear()+1900) + 
                            " " +
                            String.valueOf(fecha.getHours()) + ":" +
                            String.valueOf(fecha.getMinutes());
        
        return (fechaSalida);
    }
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    public void onChangeCausaProbable()
    {
        try
        {
            //***************************************************************
            //***************************************************************
            //logBeanWebReactivo.info ("--------------------------------------------------------------------");
            //logBeanWebReactivo.info ("--------------------------------------------------------------------");
            //logBeanWebReactivo.info ("ESTADO DEL COMBO E6:  " + this.causaProbableEfectoGestion);
            this.codigoCausaEfectoGestion = this.causaProbableEfectoGestion;
            this.descripcionCausaEfectoGestion = this.causaProbableEfectoGestion;
            //***************************************************************
            //***************************************************************
            //logBeanWebReactivo.info ("ACTUALIZANDO E7: " + this.descripcionCausaEfectoGestion);
            //logBeanWebReactivo.info ("ACTUALIZANDO E8:  " + this.codigoCausaEfectoGestion);
            //logBeanWebReactivo.info ("--------------------------------------------------------------------");
            //logBeanWebReactivo.info ("--------------------------------------------------------------------");
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorActualizacionCombos)
        {
            //logBeanWebReactivo.info ("ERROR ACTUALIZACION COMBOS:  " + errorActualizacionCombos.getLocalizedMessage());
            errorActualizacionCombos.printStackTrace();
        }
    }
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    public boolean verificarSiEsNumero( String input )
    {
       boolean esNumero = false;
       
       long valor = 0L;
       try
       {
          valor = Long.parseLong(input );
          
          if (valor >= 0)
          {
              esNumero = true;
              //logBeanWebReactivo.info ("EL NUMERO ENVIADO " + valor + " es numero: " + esNumero);
          }
       }
       catch( Exception error)
       {
           //logBeanWebReactivo.info ("LA CADENA ENVIADA NO ES NUMERO: " + error.getLocalizedMessage());
           error.printStackTrace();
           return false;
       }
       
       return (esNumero);
    }    
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
    /**
     * @return the ejbServicioRemotoReporteMasivoTrimestral
     */
    public ServicioReporteTrimestralRemote getEjbServicioRemotoReporteMasivoTrimestral() {
        return ejbServicioRemotoReporteMasivoTrimestral;
    }

    /**
     * @return the file
     */
    public Part getFile() {
        return file;
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @param file the file to set
     */
    public void setFile(Part  file) {
        this.file = file;
    }

    /**
     * @return the listaDatosExcel
     */
    public List<FilaExcelMasivoReactivo> getListaDatosExcel() {
        return listaDatosExcel;
    }

    /**
     * @param listaDatosExcel the listaDatosExcel to set
     */
    public void setListaDatosExcel(List<FilaExcelMasivoReactivo> listaDatosExcel) {
        this.listaDatosExcel = listaDatosExcel;
    }

    /**
     * @return the listadoValidacionesTrimestral
     */
    public List<ValidacionTrimestral> getListadoValidacionesTrimestral() {
        return listadoValidacionesTrimestral;
    }

    /**
     * @param listadoValidacionesTrimestral the listadoValidacionesTrimestral to set
     */
    public void setListadoValidacionesTrimestral(List<ValidacionTrimestral> listadoValidacionesTrimestral) {
        this.listadoValidacionesTrimestral = listadoValidacionesTrimestral;
    }

    /**
     * @return the servicioReporteTrimestral
     */
    public ServicioReporteTrimestralRemote getServicioReporteTrimestral() {
        return servicioReporteTrimestral;
    }

    /**
     * @param servicioReporteTrimestral the servicioReporteTrimestral to set
     */
    public void setServicioReporteTrimestral(ServicioReporteTrimestralRemote servicioReporteTrimestral) {
        this.servicioReporteTrimestral = servicioReporteTrimestral;
    }

    /**
     * @return the datosCargueCorreo
     */
    public ArrayList getDatosCargueCorreo() {
        return datosCargueCorreo;
    }

    /**
     * @param datosCargueCorreo the datosCargueCorreo to set
     */
    public void setDatosCargueCorreo(ArrayList datosCargueCorreo) {
        this.datosCargueCorreo = datosCargueCorreo;
    }

    /**
     * @return the listaDepartamentos
     */
    public ArrayList getListaDepartamentos() {
        return listaDepartamentos;
    }

    /**
     * @param listaDepartamentos the listaDepartamentos to set
     */
    public void setListaDepartamentos(ArrayList listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    /**
     * @return the listaMunicipios
     */
    public ArrayList getListaMunicipios() {
        return listaMunicipios;
    }

    /**
     * @param listaMunicipios the listaMunicipios to set
     */
    public void setListaMunicipios(ArrayList listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    /**
     * @return the listaDepartamentosReporte
     */
    public ArrayList getListaDepartamentosReporte() {
        return listaDepartamentosReporte;
    }

    /**
     * @param listaDepartamentosReporte the listaDepartamentosReporte to set
     */
    public void setListaDepartamentosReporte(ArrayList listaDepartamentosReporte) {
        this.listaDepartamentosReporte = listaDepartamentosReporte;
    }

    /**
     * @return the listaMunicipiosReporte
     */
    public ArrayList getListaMunicipiosReporte() {
        return listaMunicipiosReporte;
    }

    /**
     * @param listaMunicipiosReporte the listaMunicipiosReporte to set
     */
    public void setListaMunicipiosReporte(ArrayList listaMunicipiosReporte) {
        this.listaMunicipiosReporte = listaMunicipiosReporte;
    }

    /**
     * @return the registroActual
     */
    public FilaExcelMasivoReactivo getRegistroActual() {
        return registroActual;
    }

    /**
     * @param registroActual the registroActual to set
     */
    public void setRegistroActual(FilaExcelMasivoReactivo registroActual) {
        this.registroActual = registroActual;
    }

    /**
     * @return the correoReportante
     */
    public String getCorreoReportante() {
        return correoReportante;
    }

    /**
     * @param correoReportante the correoReportante to set
     */
    public void setCorreoReportante(String correoReportante) {
        this.correoReportante = correoReportante;
    }

    /**
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the departamentoReporte
     */
    public String getDepartamentoReporte() {
        return departamentoReporte;
    }

    /**
     * @param departamentoReporte the departamentoReporte to set
     */
    public void setDepartamentoReporte(String departamentoReporte) {
        this.departamentoReporte = departamentoReporte;
    }

    /**
     * @return the municipioReporte
     */
    public String getMunicipioReporte() {
        return municipioReporte;
    }

    /**
     * @param municipioReporte the municipioReporte to set
     */
    public void setMunicipioReporte(String municipioReporte) {
        this.municipioReporte = municipioReporte;
    }

    /**
     * @return the departamentoPaso
     */
    public String getDepartamentoPaso() {
        return departamentoPaso;
    }

    /**
     * @param departamentoPaso the departamentoPaso to set
     */
    public void setDepartamentoPaso(String departamentoPaso) {
        this.departamentoPaso = departamentoPaso;
    }

    /**
     * @return the municipioPaso
     */
    public String getMunicipioPaso() {
        return municipioPaso;
    }

    /**
     * @param municipioPaso the municipioPaso to set
     */
    public void setMunicipioPaso(String municipioPaso) {
        this.municipioPaso = municipioPaso;
    }

    /**
     * @return the departamentoReportePaso
     */
    public String getDepartamentoReportePaso() {
        return departamentoReportePaso;
    }

    /**
     * @param departamentoReportePaso the departamentoReportePaso to set
     */
    public void setDepartamentoReportePaso(String departamentoReportePaso) {
        this.departamentoReportePaso = departamentoReportePaso;
    }

    /**
     * @return the municipioReportePaso
     */
    public String getMunicipioReportePaso() {
        return municipioReportePaso;
    }

    /**
     * @param municipioReportePaso the municipioReportePaso to set
     */
    public void setMunicipioReportePaso(String municipioReportePaso) {
        this.municipioReportePaso = municipioReportePaso;
    }

    /**
     * @return the nivelComplejidadInstitucion
     */
    public String getNivelComplejidadInstitucion() {
        return nivelComplejidadInstitucion;
    }

    /**
     * @param nivelComplejidadInstitucion the nivelComplejidadInstitucion to set
     */
    public void setNivelComplejidadInstitucion(String nivelComplejidadInstitucion) {
        this.nivelComplejidadInstitucion = nivelComplejidadInstitucion;
    }

    /**
     * @return the naturaleza
     */
    public String getNaturaleza() {
        return naturaleza;
    }

    /**
     * @param naturaleza the naturaleza to set
     */
    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    /**
     * @return the tipoDocumento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the edadEn
     */
    public String getEdadEn() {
        return edadEn;
    }

    /**
     * @param edadEn the edadEn to set
     */
    public void setEdadEn(String edadEn) {
        this.edadEn = edadEn;
    }

    /**
     * @return the tipoDispositivo
     */
    public String getTipoDispositivo() {
        return tipoDispositivo;
    }

    /**
     * @param tipoDispositivo the tipoDispositivo to set
     */
    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    /**
     * @return the procedenciaReactivo
     */
    public String getProcedenciaReactivo() {
        return procedenciaReactivo;
    }

    /**
     * @param procedenciaReactivo the procedenciaReactivo to set
     */
    public void setProcedenciaReactivo(String procedenciaReactivo) {
        this.procedenciaReactivo = procedenciaReactivo;
    }

    /**
     * @return the requiereCadFrioReactivo
     */
    public String getRequiereCadFrioReactivo() {
        return requiereCadFrioReactivo;
    }

    /**
     * @param requiereCadFrioReactivo the requiereCadFrioReactivo to set
     */
    public void setRequiereCadFrioReactivo(String requiereCadFrioReactivo) {
        this.requiereCadFrioReactivo = requiereCadFrioReactivo;
    }

    /**
     * @return the catReactivo
     */
    public String getCatReactivo() {
        return catReactivo;
    }

    /**
     * @param catReactivo the catReactivo to set
     */
    public void setCatReactivo(String catReactivo) {
        this.catReactivo = catReactivo;
    }

    /**
     * @return the cumpleConAlmacena
     */
    public String getCumpleConAlmacena() {
        return cumpleConAlmacena;
    }

    /**
     * @param cumpleConAlmacena the cumpleConAlmacena to set
     */
    public void setCumpleConAlmacena(String cumpleConAlmacena) {
        this.cumpleConAlmacena = cumpleConAlmacena;
    }

    /**
     * @return the realizaRecepcionReactivo
     */
    public String getRealizaRecepcionReactivo() {
        return realizaRecepcionReactivo;
    }

    /**
     * @param realizaRecepcionReactivo the realizaRecepcionReactivo to set
     */
    public void setRealizaRecepcionReactivo(String realizaRecepcionReactivo) {
        this.realizaRecepcionReactivo = realizaRecepcionReactivo;
    }

    /**
     * @return the prodCertAnalisisReactivo
     */
    public String getProdCertAnalisisReactivo() {
        return prodCertAnalisisReactivo;
    }

    /**
     * @param prodCertAnalisisReactivo the prodCertAnalisisReactivo to set
     */
    public void setProdCertAnalisisReactivo(String prodCertAnalisisReactivo) {
        this.prodCertAnalisisReactivo = prodCertAnalisisReactivo;
    }

    /**
     * @return the servicioFunReactivo
     */
    public String getServicioFunReactivo() {
        return servicioFunReactivo;
    }

    /**
     * @param servicioFunReactivo the servicioFunReactivo to set
     */
    public void setServicioFunReactivo(String servicioFunReactivo) {
        this.servicioFunReactivo = servicioFunReactivo;
    }

    /**
     * @return the desenlaceIncidente
     */
    public String getDesenlaceIncidente() {
        return desenlaceIncidente;
    }

    /**
     * @param desenlaceIncidente the desenlaceIncidente to set
     */
    public void setDesenlaceIncidente(String desenlaceIncidente) {
        this.desenlaceIncidente = desenlaceIncidente;
    }

    /**
     * @return the tieneProgGestion
     */
    public String getTieneProgGestion() {
        return tieneProgGestion;
    }

    /**
     * @param tieneProgGestion the tieneProgGestion to set
     */
    public void setTieneProgGestion(String tieneProgGestion) {
        this.tieneProgGestion = tieneProgGestion;
    }

    /**
     * @return the realizoAnalisisGestion
     */
    public String getRealizoAnalisisGestion() {
        return realizoAnalisisGestion;
    }

    /**
     * @param realizoAnalisisGestion the realizoAnalisisGestion to set
     */
    public void setRealizoAnalisisGestion(String realizoAnalisisGestion) {
        this.realizoAnalisisGestion = realizoAnalisisGestion;
    }

    /**
     * @return the herramientaAnalisisGestion
     */
    public String getHerramientaAnalisisGestion() {
        return herramientaAnalisisGestion;
    }

    /**
     * @param herramientaAnalisisGestion the herramientaAnalisisGestion to set
     */
    public void setHerramientaAnalisisGestion(String herramientaAnalisisGestion) {
        this.herramientaAnalisisGestion = herramientaAnalisisGestion;
    }

    /**
     * @return the causaEfectoAnalisisGestion
     */
    public String getCausaEfectoAnalisisGestion() {
        return causaEfectoAnalisisGestion;
    }

    /**
     * @param causaEfectoAnalisisGestion the causaEfectoAnalisisGestion to set
     */
    public void setCausaEfectoAnalisisGestion(String causaEfectoAnalisisGestion) {
        this.causaEfectoAnalisisGestion = causaEfectoAnalisisGestion;
    }

    /**
     * @return the descripcionCausaEfectoGestion
     */
    public String getDescripcionCausaEfectoGestion() {
        return descripcionCausaEfectoGestion;
    }

    /**
     * @param descripcionCausaEfectoGestion the descripcionCausaEfectoGestion to set
     */
    public void setDescripcionCausaEfectoGestion(String descripcionCausaEfectoGestion) {
        this.descripcionCausaEfectoGestion = descripcionCausaEfectoGestion;
    }

    /**
     * @return the causaProbableEfectoGestion
     */
    public String getCausaProbableEfectoGestion() {
        return causaProbableEfectoGestion;
    }

    /**
     * @param causaProbableEfectoGestion the causaProbableEfectoGestion to set
     */
    public void setCausaProbableEfectoGestion(String causaProbableEfectoGestion) {
        this.causaProbableEfectoGestion = causaProbableEfectoGestion;
    }

    /**
     * @return the codigoCausaEfectoGestion
     */
    public String getCodigoCausaEfectoGestion() {
        return codigoCausaEfectoGestion;
    }

    /**
     * @param codigoCausaEfectoGestion the codigoCausaEfectoGestion to set
     */
    public void setCodigoCausaEfectoGestion(String codigoCausaEfectoGestion) {
        this.codigoCausaEfectoGestion = codigoCausaEfectoGestion;
    }

    /**
     * @return the inicioAccionesEfectoGestion
     */
    public String getInicioAccionesEfectoGestion() {
        return inicioAccionesEfectoGestion;
    }

    /**
     * @param inicioAccionesEfectoGestion the inicioAccionesEfectoGestion to set
     */
    public void setInicioAccionesEfectoGestion(String inicioAccionesEfectoGestion) {
        this.inicioAccionesEfectoGestion = inicioAccionesEfectoGestion;
    }

    /**
     * @return the reportoDistribGestion
     */
    public String getReportoDistribGestion() {
        return reportoDistribGestion;
    }

    /**
     * @param reportoDistribGestion the reportoDistribGestion to set
     */
    public void setReportoDistribGestion(String reportoDistribGestion) {
        this.reportoDistribGestion = reportoDistribGestion;
    }

    /**
     * @return the profesionReportante
     */
    public String getProfesionReportante() {
        return profesionReportante;
    }

    /**
     * @param profesionReportante the profesionReportante to set
     */
    public void setProfesionReportante(String profesionReportante) {
        this.profesionReportante = profesionReportante;
    }

    /**
     * @return the autorizaDivulgaReportante
     */
    public String getAutorizaDivulgaReportante() {
        return autorizaDivulgaReportante;
    }

    /**
     * @param autorizaDivulgaReportante the autorizaDivulgaReportante to set
     */
    public void setAutorizaDivulgaReportante(String autorizaDivulgaReportante) {
        this.autorizaDivulgaReportante = autorizaDivulgaReportante;
    }

    /**
     * @return the tipoReportanteInfoReactivo
     */
    public String getTipoReportanteInfoReactivo() {
        return tipoReportanteInfoReactivo;
    }

    /**
     * @param tipoReportanteInfoReactivo the tipoReportanteInfoReactivo to set
     */
    public void setTipoReportanteInfoReactivo(String tipoReportanteInfoReactivo) {
        this.tipoReportanteInfoReactivo = tipoReportanteInfoReactivo;
    }

    /**
     * @return the estadoReporteInfoReactivo
     */
    public String getEstadoReporteInfoReactivo() {
        return estadoReporteInfoReactivo;
    }

    /**
     * @param estadoReporteInfoReactivo the estadoReporteInfoReactivo to set
     */
    public void setEstadoReporteInfoReactivo(String estadoReporteInfoReactivo) {
        this.estadoReporteInfoReactivo = estadoReporteInfoReactivo;
    }

    /**
     * @return the estadoGET
     */
    public String getEstadoGET() {
        return estadoGET;
    }

    /**
     * @param estadoGET the estadoGET to set
     */
    public void setEstadoGET(String estadoGET) {
        this.estadoGET = estadoGET;
    }

    /**
     * @return the listaTiposReporte
     */
    public ArrayList getListaTiposReporte() {
        return listaTiposReporte;
    }

    /**
     * @param listaTiposReporte the listaTiposReporte to set
     */
    public void setListaTiposReporte(ArrayList listaTiposReporte) {
        this.listaTiposReporte = listaTiposReporte;
    }

    /**
     * @return the listaDesenlaces
     */
    public ArrayList getListaDesenlaces() {
        return listaDesenlaces;
    }

    /**
     * @param listaDesenlaces the listaDesenlaces to set
     */
    public void setListaDesenlaces(ArrayList listaDesenlaces) {
        this.listaDesenlaces = listaDesenlaces;
    }

    /**
     * @return the listaTiposDocumento
     */
    public ArrayList getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    /**
     * @param listaTiposDocumento the listaTiposDocumento to set
     */
    public void setListaTiposDocumento(ArrayList listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    /**
     * @return the listaDetecciones
     */
    public ArrayList getListaDetecciones() {
        return listaDetecciones;
    }

    /**
     * @param listaDetecciones the listaDetecciones to set
     */
    public void setListaDetecciones(ArrayList listaDetecciones) {
        this.listaDetecciones = listaDetecciones;
    }

    /**
     * @return the listaCargos
     */
    public ArrayList getListaCargos() {
        return listaCargos;
    }

    /**
     * @param listaCargos the listaCargos to set
     */
    public void setListaCargos(ArrayList listaCargos) {
        this.listaCargos = listaCargos;
    }

    /**
     * @return the listaTipoReportantes
     */
    public ArrayList getListaTipoReportantes() {
        return listaTipoReportantes;
    }

    /**
     * @param listaTipoReportantes the listaTipoReportantes to set
     */
    public void setListaTipoReportantes(ArrayList listaTipoReportantes) {
        this.listaTipoReportantes = listaTipoReportantes;
    }

    /**
     * @return the listaCausasProbables
     */
    public ArrayList getListaCausasProbables() {
        return listaCausasProbables;
    }

    /**
     * @param listaCausasProbables the listaCausasProbables to set
     */
    public void setListaCausasProbables(ArrayList listaCausasProbables) {
        this.listaCausasProbables = listaCausasProbables;
    }

    /**
     * @return the listaDescripcionesCausa
     */
    public ArrayList getListaDescripcionesCausa() {
        return listaDescripcionesCausa;
    }

    /**
     * @param listaDescripcionesCausa the listaDescripcionesCausa to set
     */
    public void setListaDescripcionesCausa(ArrayList listaDescripcionesCausa) {
        this.listaDescripcionesCausa = listaDescripcionesCausa;
    }

    /**
     * @return the listaTipoDispositivos
     */
    public ArrayList getListaTipoDispositivos() {
        return listaTipoDispositivos;
    }

    /**
     * @param listaTipoDispositivos the listaTipoDispositivos to set
     */
    public void setListaTipoDispositivos(ArrayList listaTipoDispositivos) {
        this.listaTipoDispositivos = listaTipoDispositivos;
    }

    /**
     * @return the conteoEventos
     */
    public String getConteoEventos() {
        return conteoEventos;
    }

    /**
     * @param conteoEventos the conteoEventos to set
     */
    public void setConteoEventos(String conteoEventos) {
        this.conteoEventos = conteoEventos;
    }

    /**
     * @return the conteoPaciente
     */
    public String getConteoPaciente() {
        return conteoPaciente;
    }

    /**
     * @param conteoPaciente the conteoPaciente to set
     */
    public void setConteoPaciente(String conteoPaciente) {
        this.conteoPaciente = conteoPaciente;
    }

    /**
     * @return the conteoReportante
     */
    public String getConteoReportante() {
        return conteoReportante;
    }

    /**
     * @param conteoReportante the conteoReportante to set
     */
    public void setConteoReportante(String conteoReportante) {
        this.conteoReportante = conteoReportante;
    }

    /**
     * @return the conteoGestionRealizada
     */
    public String getConteoGestionRealizada() {
        return conteoGestionRealizada;
    }

    /**
     * @param conteoGestionRealizada the conteoGestionRealizada to set
     */
    public void setConteoGestionRealizada(String conteoGestionRealizada) {
        this.conteoGestionRealizada = conteoGestionRealizada;
    }

    /**
     * @return the conteoProducto
     */
    public String getConteoProducto() {
        return conteoProducto;
    }

    /**
     * @param conteoProducto the conteoProducto to set
     */
    public void setConteoProducto(String conteoProducto) {
        this.conteoProducto = conteoProducto;
    }

    /**
     * @return the conteoInstitucion
     */
    public String getConteoInstitucion() {
        return conteoInstitucion;
    }

    /**
     * @param conteoInstitucion the conteoInstitucion to set
     */
    public void setConteoInstitucion(String conteoInstitucion) {
        this.conteoInstitucion = conteoInstitucion;
    }

    /**
     * @return the conteoCamposComplementarios
     */
    public String getConteoCamposComplementarios() {
        return conteoCamposComplementarios;
    }

    /**
     * @param conteoCamposComplementarios the conteoCamposComplementarios to set
     */
    public void setConteoCamposComplementarios(String conteoCamposComplementarios) {
        this.conteoCamposComplementarios = conteoCamposComplementarios;
    }

    /**
     * @return the operacion
     */
    public String getOperacion() {
        return operacion;
    }

    /**
     * @param operacion the operacion to set
     */
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    /**
     * @return the habilitaGuardado
     */
    public boolean isHabilitaGuardado() {
        return habilitaGuardado;
    }

    /**
     * @param habilitaGuardado the habilitaGuardado to set
     */
    public void setHabilitaGuardado(boolean habilitaGuardado) {
        this.habilitaGuardado = habilitaGuardado;
    }

    /**
     * @return the idEPSReportante
     */
    public String getIdEPSReportante() {
        return idEPSReportante;
    }

    /**
     * @param idEPSReportante the idEPSReportante to set
     */
    public void setIdEPSReportante(String idEPSReportante) {
        this.idEPSReportante = idEPSReportante;
    }

    /**
     * @return the idRolUsuarioReportante
     */
    public String getIdRolUsuarioReportante() {
        return idRolUsuarioReportante;
    }

    /**
     * @param idRolUsuarioReportante the idRolUsuarioReportante to set
     */
    public void setIdRolUsuarioReportante(String idRolUsuarioReportante) {
        this.idRolUsuarioReportante = idRolUsuarioReportante;
    }

    /**
     * @return the deptoReportante
     */
    public String getDeptoReportante() {
        return deptoReportante;
    }

    /**
     * @param deptoReportante the deptoReportante to set
     */
    public void setDeptoReportante(String deptoReportante) {
        this.deptoReportante = deptoReportante;
    }

    /**
     * @return the idRolReportante
     */
    public String getIdRolReportante() {
        return idRolReportante;
    }

    /**
     * @param idRolReportante the idRolReportante to set
     */
    public void setIdRolReportante(String idRolReportante) {
        this.idRolReportante = idRolReportante;
    }

    /**
     * @return the nombreReportante
     */
    public String getNombreReportante() {
        return nombreReportante;
    }

    /**
     * @param nombreReportante the nombreReportante to set
     */
    public void setNombreReportante(String nombreReportante) {
        this.nombreReportante = nombreReportante;
    }

    /**
     * @return the fechaRegistro
     */
    public java.util.Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(java.util.Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    /**
     * @param ejbServicioRemotoReporteMasivoTrimestral the ejbServicioRemotoReporteMasivoTrimestral to set
     */
    public void setEjbServicioRemotoReporteMasivoTrimestral(ServicioReporteTrimestralRemote ejbServicioRemotoReporteMasivoTrimestral) {
        this.ejbServicioRemotoReporteMasivoTrimestral = ejbServicioRemotoReporteMasivoTrimestral;
    }

    /**
     * @return the listaCausasProbablesCodigos
     */
    public ArrayList getListaCausasProbablesCodigos() {
        return listaCausasProbablesCodigos;
    }

    /**
     * @param listaCausasProbablesCodigos the listaCausasProbablesCodigos to set
     */
    public void setListaCausasProbablesCodigos(ArrayList listaCausasProbablesCodigos) {
        this.listaCausasProbablesCodigos = listaCausasProbablesCodigos;
    }

    /**
     * @return the listaFilasEditadas
     */
    public ArrayList<Boolean> getListaFilasEditadas() {
        return listaFilasEditadas;
    }

    /**
     * @param listaFilasEditadas the listaFilasEditadas to set
     */
    public void setListaFilasEditadas(ArrayList<Boolean> listaFilasEditadas) {
        this.listaFilasEditadas = listaFilasEditadas;
    }

    /**
     * @return the indiceFilasEditadas
     */
    public int getIndiceFilasEditadas() {
        return indiceFilasEditadas;
    }

    /**
     * @param indiceFilasEditadas the indiceFilasEditadas to set
     */
    public void setIndiceFilasEditadas(int indiceFilasEditadas) {
        this.indiceFilasEditadas = indiceFilasEditadas;
    }
    
    public static boolean esNumerico(String entrada){
        try{
            Long.parseLong(entrada);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }
    
    public static boolean esFecha(String entrada){
        try{
            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(entrada);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }
    
} //Fin del managed bean

