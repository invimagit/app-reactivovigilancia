/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.beans.masivo;

/**
 *
 * @author jgutierrezme
 */

import co.gov.invima.VO.ReportePreRDIVVO;
import co.gov.invima.VO.UsuariosVO;
import co.gov.invima.entities.*;
import co.gov.invima.negocio.ReactivoVigilanciaBeanLocal;
import co.gov.invima.negocio.ServicioReporteTrimestralRemote;
import co.gov.invima.service.ServiceLocator;
import co.gov.invima.util.CorreoElectronico;
import co.gov.invima.util.Fecha;
import co.gov.invima.util.HtmlValidator;
import co.gov.invima.util.PropertiesReader;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.List;
import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.component.api.UIData;
//import java.nio.file.Files;
/******************************************************************/
/******************************************************************/
/******************************************************************/

//@ViewScoped
@SessionScoped
@ManagedBean(name="beanBusquedaPrerdivs")
public class BeanBusquedaPrerdivs implements Serializable
{
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private static Logger logBeanWebReactivo = Logger.getLogger(BeanBusquedaPrerdivs.class.getName());
    private static long serialVersionUID = -2582548649674756614L;
    //********************************************************************
    //********************************************************************
    //********************************************************************
    @EJB
    private ServicioReporteTrimestralRemote ejbServicioRemotoReporteMasivoTrimestral;
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private List<ReportePreRDIVVO> listadoPrerdivsParaAprobacion;
    private List<ReportePreRDIVVO> listadoPrerdivsParaAprobacionEscogidos;
    private ServicioReporteTrimestralRemote servicioReporteTrimestral;
    private ReportePreRDIVVO registroActual;
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private ArrayList listaDepartamentos = new ArrayList();
    private ArrayList listaMunicipios = new ArrayList();
    private ArrayList listaIPSReportante = new ArrayList();
    //********************************************************************
    //********************************************************************
    private String departamento;
    private String municipio;
    private String ipsreportante;
    private String correoReportante;
    private String nombreAprobador;
    private String idRolBusqueda;
    private String idFuncionario;
    //********************************************************************
    //********************************************************************
    private ReactivoEventosTemp datosReactivoEventosTemp;
    private ReactivoPacienteTemp datosReactivoPacienteTemp;
    private ReactivoReportanteTemp datosReactivoReportanteTemp;
    private ReactivoGestionrealizadaTemp datosReactivoGestionrealizadaTemp;
    private ReactivoCamposcompTemp datosReactivoCamposcompTemp;
    private ReactivoInstitucionTemp datosReactivoInstitucionTemp;
    private ReactivoProductoTemp datosReactivoProductoTemp;
    //********************************************************************
    //********************************************************************
    private boolean todosCamposBasicos;
    private String idDeptoSesion;
    private String idMunicipioSesion;
    private String nombreSecretariaSalud;
    private java.util.Date fechaInicial;
    private java.util.Date fechaFinal;
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private String nombreDepto;
    private String nombreMunicipio;
    private String nombreNaturaleza;
    private String nombreTipoIdentificacion;
    private String nombreEdadEn;
    private String nombreDeteccion;
    private String nombreDesenlace;
    private String nombreCausaProbable;
    private String nombreProfesion;
    private String nombreDeptoReporte;
    private String nombreMunReporte;
    private String nombreTipoReportante;
    private String nombreTipoDispositivo;
    //********************************************************************
    //********************************************************************
    private String tipoReporte;
    private String causaProbable;
    private String medidaEjecucion;
    private String estadoCaso;
    private String rolUsuario;
    private String idRolFuncionarioAprobador;
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private ArrayList listaTiposReporte = new ArrayList();
    private ArrayList listaCausasProbables = new ArrayList();
    //********************************************************************
    //********************************************************************
    private ArrayList<Boolean> listaFilasEditadas = new ArrayList<Boolean>();
    private int indiceFilasEditadas;
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private boolean noBorrar;
    private boolean esAdministrador;
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public BeanBusquedaPrerdivs()
    {
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("Iniciando constructor del Managed Bean Web BeanBusquedaPrerdivs");
        //logBeanWebReactivo.info("Conectandose al contexto");
        ServiceLocator service = new ServiceLocator();
        //logBeanWebReactivo.info ("ESTADO DEL SERVICE LOCATOR = " + service);
        servicioReporteTrimestral = service.getEjbRemotoReactivoReporteTrimestral();
        //logBeanWebReactivo.info("Estado del EJB DEL REPORTE PARA EL MANAGED BEAN: " + this.servicioReporteTrimestral);
        //****************************************************
        //****************************************************
        //logBeanWebReactivo.info ("Estado de no borrar en el constructor = " + this.isNoBorrar());
        
        if (this.isNoBorrar() == true)
        {
            //logBeanWebReactivo.info ("Limpiando grilla al salir");
            this.setListadoPrerdivsParaAprobacion(new ArrayList<ReportePreRDIVVO>());
        }
        //****************************************************
        //****************************************************
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    UsuariosVO userSesion;
    @PostConstruct
    public void init() 
    {
        List<ReportePreRDIVVO> registrosLocales = null;
        List<String> departamentos = null;
        List<String> municipios = null;
        List<String> ipsreportantes = null;
        //***********************************************************
        //***********************************************************
        //***********************************************************
        //***********************************************************
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sesionUsuario = request.getSession();
        String nombreUsuario = "";
        userSesion = null;
        String idDepto = "";
        String idMunicipio = "";
        String idDistrito = "";
        String idRolAprobador = "";
        String correoD = "";
        String idFuncionario = "";
        boolean esDistritoEspecial = false;
        String prefijoDepto = "";
        //***********************************************************
        //***********************************************************
        try
        {
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            userSesion = (UsuariosVO)sesionUsuario.getAttribute("usuario");
            //logBeanWebReactivo.info ("USUARIO PARA LA BUSQUEDA DE PRECOLS = " + userSesion);
            if (userSesion != null)
            {
                //logBeanWebReactivo.info ("USUARIO CORRECTO DE LA SESIÓN");
                nombreUsuario = userSesion.getUsuario();
                idDepto = userSesion.getCodDepart();
                idMunicipio = userSesion.getCodMun();
                idRolAprobador = userSesion.getIdRol();
                idFuncionario = String.valueOf(userSesion.getIdentificacionPersona());
                this.esAdministrador = false;
            }
            else
            {
                //logBeanWebReactivo.info ("USUARIO DE EMERGENCIA ADMINISTRATIVO INVIMA");
                nombreUsuario = "USUARIO ADMINISTRADOR INVIMA";
                idDepto = "11";
                idMunicipio = "11001";
                idRolAprobador = "1";
                idFuncionario = "938";
                this.esAdministrador = true;
            }
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("NOMBRE DEL REPORTANTE QUE APROBARÁ LOS REGISTROS = " + this.getNombreAprobador());
            //logBeanWebReactivo.info ("ID DEPTO USUARIO SESION = " + idDepto);
            //logBeanWebReactivo.info ("ID MUNICIPIO USUARIO SESION = " + idMunicipio);
            //logBeanWebReactivo.info ("ID ROL DEL APROBADOR DE PRECOLES = " + idRolAprobador);
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            this.setIdRolFuncionarioAprobador(idRolAprobador);
            this.setIdFuncionario(idFuncionario);
            this.setNombreSecretariaSalud(userSesion.getNombreEmpresa());
            this.setNombreAprobador(generarNombreAprobadorCorreo(userSesion.getUsuario(),userSesion.getNombreEmpresa()));
            //**********************************************************************
            //**********************************************************************
            if (idDepto == null && idMunicipio == null)
            {
                idDepto = "11";
                idMunicipio = "11001";
                 //logBeanWebReactivo.info ("SETEANDO DEPTO Y MUNICIPIO DEL ADMIN DOMINIO INVIMA EN BOGOTA");
            }
            else
            {
                 //logBeanWebReactivo.info ("SE TIENE DEPTO EN " + idDepto + " Y MUNICIPIO EN " + idMunicipio);
            }
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            if (idRolAprobador.equals("1"))
            {
                //logBeanWebReactivo.info ("PUEDO BUSCAR PRECOLES DE PRESTADORES Y FABRICANTES");
                this.setIdRolBusqueda("todos");
                //A futuro volver a activar el listado de todas las IPS para el administrador
                //ipsreportantes = getServicioReporteTrimestral().obtenerListadoUsuariosIPS("todos");
                ipsreportantes = getServicioReporteTrimestral().obtenerListadoUsuariosIPS("todos",idDepto);
                this.setListaIPSReportante(armarItemsCombo(ipsreportantes));
            }
            else
            if (idRolAprobador.equals("4"))
            {
                //logBeanWebReactivo.info ("PUEDO BUSCAR PRECOLES SOLO DE PRESTADORES DE SALUD");
                this.setIdRolBusqueda("prestadores");
                ipsreportantes = getServicioReporteTrimestral().obtenerListadoUsuariosIPS("ips",idDepto);
                this.setListaIPSReportante(armarItemsCombo(ipsreportantes));
            }
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
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
            this.setIdDeptoSesion(idDepto);
            this.setDepartamento(this.getIdDeptoSesion());
            this.setIdMunicipioSesion(idMunicipio);
            //this.setMunicipio(this.getIdMunicipioSesion());
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("**********************************************");
            //logBeanWebReactivo.info ("ID ROL PARA EL FILTRO DE BUSQUEDA DE PRECOLES = " + this.getIdRolBusqueda());
            //logBeanWebReactivo.info ("IDENTIFICACION DEL USUARIO EN SESION = " + this.getIdFuncionario());
            //logBeanWebReactivo.info ("**********************************************");
            //**********************************************************************
            //**********************************************************************
            /*
            //logBeanWebReactivo.info ("USUARIO EN SESION = " + nombreUsuario);
            //logBeanWebReactivo.info ("CORREO DEL DESTINATARIO = " + this.getCorreoReportante());
            //logBeanWebReactivo.info ("DEPTO DE LA ETS = " + this.getIdDeptoSesion());
            //logBeanWebReactivo.info ("MUNICIPIO DE LA ETS = " + this.getIdMunicipioSesion());
            */
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            idDistrito = obtenerIdDistrito(idDepto,idMunicipio);
            //logBeanWebReactivo.info ("ID DISTRITO PARA EL APROBADOR = " + idDistrito);
            prefijoDepto = idMunicipio.substring(0,2);
            //logBeanWebReactivo.info ("PREFIJO DEL DEPARTAMENTO DEL MUNICIPIO DEL APROBADOR: ");
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("ARMANDO DEPARTAMENTOS Y MUNICIPIOS CON BASE EN EL ETS");
            //departamentos = getServicioReporteTrimestral().obtenerListadoDepartamentos();
            //departamentos = getServicioReporteTrimestral().obtenerListadoDepartamentos();
            logBeanWebReactivo.info ("-------------------------------------------------------");
            logBeanWebReactivo.info ("-------------------------------------------------------");
            if (idRolAprobador.equals("1"))
            {
                logBeanWebReactivo.info ("MOSTRANDO TODOS LOS DEPARTAMENTOS");
                departamentos = getServicioReporteTrimestral().obtenerListadoDepartamentos();
            }
            else
            {
                logBeanWebReactivo.info ("MOSTRANDO SOLO EL DEPTO DE MI SECRETARIA");
                departamentos = getServicioReporteTrimestral().obtenerListadoDepartamentos(idDepto);
            }
            logBeanWebReactivo.info ("-------------------------------------------------------");
            logBeanWebReactivo.info ("-------------------------------------------------------");
            //**********************************************************************
            //**********************************************************************
            this.setListaDepartamentos(armarItemsCombo(departamentos));
            //**********************************************************************
            //**********************************************************************
            
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            if (idDepto.equals(prefijoDepto))
            {
                //logBeanWebReactivo.info ("EL MUNICIPIO DEL APROBADOR ESTÁ EN EL DEPARTAMENTO");
                if (prefijoDepto.equals("08") || prefijoDepto.equals("11") || prefijoDepto.equals("13") || prefijoDepto.equals("47") || prefijoDepto.equals("76"))
                {
                    if (idMunicipio.equals("08001") || idMunicipio.equals("11001") || idMunicipio.equals("13001") || idMunicipio.equals("47001") || idMunicipio.equals("76109"))
                    {
                        //logBeanWebReactivo.info ("SE CONFIGURA UNICAMENTE CIUDAD DEL DISTRITO");
                        municipios = getServicioReporteTrimestral().obtenerListadoMunicipiosSoloDistrito(this.getDepartamento(),idMunicipio);
                        esDistritoEspecial = true;
                    }
                    else
                    {
                        //logBeanWebReactivo.info ("MOSTRANDO TODOS LOS MUNICIPIOS DEL DEPARTAMENTO SIN EL DISTRITO");
                        municipios = getServicioReporteTrimestral().obtenerListadoMunicipiosControlDistrito(this.getDepartamento(),idDistrito);
                    }
                }
                else
                {
                        //logBeanWebReactivo.info ("SE MOSTRARAN LOS MUNICIPIOS NORMALES DEL DEPARTAMENTO " + this.getDepartamento() + " VIA 1");
                        municipios = getServicioReporteTrimestral().obtenerListadoMunicipios(this.getDepartamento());
                }
            }
            /*
            if (idDistrito.equals("0"))
            {
                //logBeanWebReactivo.info ("EL USUARIO PERTENECE AL DISTRITO");
                //Usuario que pertenece al DISTRITO
                //logBeanWebReactivo.info ("MOSTRANDO SOLO EL DISTRITO EN EL COMBO");
                if (idMunicipio.equals("08001") || idMunicipio.equals("11001") || idMunicipio.equals("13001") || idMunicipio.equals("47001") || idMunicipio.equals("76109"))
                {
                    //logBeanWebReactivo.info ("SE CONFIGURA UNICAMENTE CIUDAD DEL DISTRITO");
                    municipios = getServicioReporteTrimestral().obtenerListadoMunicipiosSoloDistrito(this.getDepartamento(),idMunicipio);
                    esDistritoEspecial = true;
                }
                else
                {
                    //logBeanWebReactivo.info ("SE MOSTRARAN LOS MUNICIPIOS NORMALES DEL DEPARTAMENTO " + this.getDepartamento() + " VIA 1");
                    municipios = getServicioReporteTrimestral().obtenerListadoMunicipios(this.getDepartamento());
                }
            }
            else
            {
                //logBeanWebReactivo.info ("EL USUARIO NO PERTENECE AL DISTRITO");
                //Usuario que no pertenece al DISTRITO
                if (idMunicipio.equals("08001") || idMunicipio.equals("11001") || idMunicipio.equals("13001") || idMunicipio.equals("47001") || idMunicipio.equals("76109"))
                {
                        //logBeanWebReactivo.info ("MOSTRANDO TODOS LOS MUNICIPIOS DEL DEPARTAMENTO SIN EL DISTRITO");
                        municipios = getServicioReporteTrimestral().obtenerListadoMunicipiosSoloDistrito(this.getDepartamento(),idMunicipio);
                }
                else
                {
                        //logBeanWebReactivo.info ("SE MOSTRARAN LOS MUNICIPIOS NORMALES DEL DEPARTAMENTO " + this.getDepartamento() + " VIA 2");
                        municipios = getServicioReporteTrimestral().obtenerListadoMunicipios(this.getDepartamento());
                        //municipios = getServicioReporteTrimestral().obtenerListadoMunicipiosControlDistrito(this.getDepartamento(),idDistrito);
                }
            }
            */
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            this.setListaMunicipios(armarItemsCombo(municipios));
            //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //this.listadoPrerdivsParaAprobacion = getServicioReporteTrimestral().buscarReportesMasivosTrimestralesPorIPS(null,null,null);
            //this.setListadoPrerdivsParaAprobacion(getServicioReporteTrimestral().buscarReportesMasivosTrimestralesPorIPS(this.getDepartamento(), this.getMunicipio(), null));
            this.setIndiceFilasEditadas(0);
            cargarCombosGrillaValidadora();         
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            if (idRolAprobador.equals("1"))
            {
                //logBeanWebReactivo.info ("PUEDO BUSCAR PRECOLES DE PRESTADORES Y FABRICANTES");
                this.setIdRolBusqueda("todos");
                //A futuro volver a activar el listado de todas las IPS para el administrador
                ipsreportantes = getServicioReporteTrimestral().obtenerListadoUsuariosIPS("todos",idDepto,idMunicipio,false);
                this.setListaIPSReportante(armarItemsCombo(ipsreportantes));
            }
            else
            if (idRolAprobador.equals("4"))
            {
                //logBeanWebReactivo.info ("PUEDO BUSCAR PRECOLES SOLO DE PRESTADORES DE SALUD");
                this.setIdRolBusqueda("prestadores");
                ipsreportantes = getServicioReporteTrimestral().obtenerListadoUsuariosIPS("ips",idDepto,idMunicipio,esDistritoEspecial);
                this.setListaIPSReportante(armarItemsCombo(ipsreportantes));
            }
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            if ((List<ReportePreRDIVVO>)sesionUsuario.getAttribute("datosGrilla") != null)
            {
                //logBeanWebReactivo.info ("RECUPERANDO DE LA SESIÓN");
                this.setListadoPrerdivsParaAprobacion((List<ReportePreRDIVVO>)sesionUsuario.getAttribute("datosGrilla"));
                this.setNoBorrar(true);
            }
            else
            {
                //logBeanWebReactivo.info ("SETEANDO EN BLANCO");
                this.setListadoPrerdivsParaAprobacion(new ArrayList<ReportePreRDIVVO>());
            }
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("TOTAL DE REGISTROS DEL REPORTE TRIMESTRAL CARGADOS = " + this.getListadoPrerdivsParaAprobacion().size());
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("SE REALIZARÁ LA APROBACIÓN DE PRECOLS");
            //this.setNoBorrar(true);
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
    //********************************************************************
    //********************************************************************
    //********************************************************************
    /*
    //No activar
    <f:metadata>
        <f:event type="postAddToView" listener="#{beanBusquedaPrerdivs.limpiarCamposPagina}"/>
        <f:event type="preRenderView" listener="#{beanBusquedaPrerdivs.verificarEstadoVista}"/>
    </f:metadata>    
    */
    /*
    public void limpiarCamposPagina()
    {
        //logBeanWebReactivo.info ("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        //logBeanWebReactivo.info ("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        //logBeanWebReactivo.info ("Actualizando la página - Limpiando grilla de preaprobacion");
        //logBeanWebReactivo.info ("Estado de no borrar en el evento = " + this.isNoBorrar());
        
        if (this.isNoBorrar() == false)
        {
            //logBeanWebReactivo.info ("Limpiando grilla al salir");
            this.setListadoPrerdivsParaAprobacion(new ArrayList<ReportePrecolVO>());
        }
        //logBeanWebReactivo.info ("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        //logBeanWebReactivo.info ("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    }
    */
    //********************************************************************
    //********************************************************************
    //********************************************************************
    /*
    public void verificarEstadoVista()
    {
        //logBeanWebReactivo.info ("---------------------------------------->>");
        //logBeanWebReactivo.info ("---------------------------------------->>");
        //logBeanWebReactivo.info ("VERIFICANDO ESTADO");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sesionUsuario = request.getSession();
        
        if (this.getListadoPrerdivsParaAprobacion() != null)
        {
            //logBeanWebReactivo.info ("SETEANDO LOS DATOS DE LA GRILLA PARA CUANDO DEN CLICK AL LAPIZ");
            sesionUsuario.setAttribute("datosGrilla", this.getListadoPrerdivsParaAprobacion());
        }
        //logBeanWebReactivo.info ("---------------------------------------->>");
        //logBeanWebReactivo.info ("---------------------------------------->>");
    }
    */
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public List<ReportePreRDIVVO> organizarDatosCargue (List<ReportePreRDIVVO> registrosServicio)
    {
        int i = 0;
        ReportePreRDIVVO registroActual = null;
        String nombreTipo = "";
        String nombreCausa = "";
        String nombreMedida = "";
        String idMedida = "";
        String estado = "";
        
        for (i = 0;  i < registrosServicio.size();  i++)
        {
            //***********************************************************************************************
            //***********************************************************************************************
            registroActual = (ReportePreRDIVVO)registrosServicio.get(i);
            nombreCausa = getServicioReporteTrimestral().obtenerNombreCausaProbablePorCodigo(registroActual.getCodigoCausa());
            registroActual.setCodigoCausa(nombreCausa);
            estado = obtenerNombreEstadoLocal(registroActual.getEstadoCaso());
            registroActual.setEstadoCaso(estado);
            //***********************************************************************************************
            //***********************************************************************************************
        }
        
        return (registrosServicio);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private String obtenerNombreEstadoLocal(String codigoEstado)
    {
        String codigo = "";
        
        switch (codigoEstado)
        {
            case "1":  codigo = "S";  break;
            case "2":  codigo = "C";  break;
            case "3":  codigo = "A";  break;
        }
        
        return (codigo);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private String obtenerIdEstadoLocal (String codigoEstado)
    {
        String codigo = "";
        
        switch (codigoEstado)
        {
            case "S":  codigo = "1";  break;
            case "C":  codigo = "2";  break;
            case "A":  codigo = "3";  break;
            default:    codigo = "3";  break;
        }
        
        return (codigo);
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
            //Usuario de Secretaría de Salud
            correoEnvio = usuario;
        }
        else
        {
            //Usuario administrativo Invima
            //correoEnvio = usuario + "@invima.gov.co";
            correoEnvio = "reactivovigilancia@invima.gov.co";
        }
        
        //logBeanWebReactivo.info ("CORREO AL QUE SE DESPACHARA LA CONFIRMACIÓN DE APROBACION = " + correoEnvio);
        
        return (correoEnvio);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private String obtenerIdDistrito (String idDepartamento, String idMunicipioAprobador)
    {
        String idDistrito = "";
        String idDeptoMunicipio = "";
        
        if (idDepartamento.equals("08") || idDepartamento.equals("11") || idDepartamento.equals("13") || idDepartamento.equals("47") || idDepartamento.equals("76"))
        {
            //logBeanWebReactivo.info ("SE ENCONTRO QUE EL USUARIO TIENE DEPARTAMENTO "  + idDepartamento);
            
            if (!idMunicipioAprobador.equals("08001") || !idMunicipioAprobador.equals("11001") || !idMunicipioAprobador.equals("13001") || 
                !idMunicipioAprobador.equals("47001") || !idMunicipioAprobador.equals("76109"))
            {
                idDeptoMunicipio = idMunicipioAprobador.substring(0,2);
                
                if (idDeptoMunicipio.equals("08") && !idMunicipioAprobador.equals("08001"))
                {
                    idDistrito = "08001";
                }
                else
                if (idDeptoMunicipio.equals("11") && !idMunicipioAprobador.equals("11001"))
                {
                    idDistrito = "11001";
                }
                else
                if (idDeptoMunicipio.equals("13") && !idMunicipioAprobador.equals("13001"))
                {
                    idDistrito = "13001";
                }
                else
                if (idDeptoMunicipio.equals("47") && !idMunicipioAprobador.equals("47001"))
                {
                    idDistrito = "47001";
                }
                else
                if (idDeptoMunicipio.equals("76") && !idMunicipioAprobador.equals("76109"))
                {
                    idDistrito = "76109";
                }
                else
                {
                    idDistrito = "0";
                }
                
                idMunicipioAprobador = idDistrito;
            }
            else
            {
                idMunicipioAprobador = "0";
            }
        }
        
        //logBeanWebReactivo.info ("ID MUNICIPIO DISTRITO =" + idMunicipioAprobador);
        
        return (idMunicipioAprobador);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void onChangeDepartamento()
    {
        List<String> ciudades = null;
        //logBeanWebReactivo.info ("Obteniendo municipios del Departamento");
        String codigo = "";
        List<String> ipsreportantesActualizadas = null;
        
        try
        {
            //logBeanWebReactivo.info ("Departamento ocurrencia = " + this.getDepartamento());
            //codigo = servicioReporteTrimestral.obtenerCodigoDeptoPorNombre(this.departamento);
            codigo = this.getDepartamento();
            //logBeanWebReactivo.info ("CODIGO DEPTO COMBO MUNICIPIOS = " + codigo);
            ciudades = getServicioReporteTrimestral().obtenerListadoMunicipios(codigo);
            this.setListaMunicipios(armarItemsCombo(ciudades));
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //Actualizamos combo de IPS reportante
            //logBeanWebReactivo.info ("*****************************************************");
            //logBeanWebReactivo.info ("*****************************************************");
            //logBeanWebReactivo.info ("ACTUALIZANDO LAS IPS REPORTANTES");
            //**********************************************************************
            //**********************************************************************
            if (this.getIdRolFuncionarioAprobador().equals("1"))
            {
                //logBeanWebReactivo.info ("ACTUALIZANDO COMBO DEL ADMINISTRADOR DE SUS IPS");
                ipsreportantesActualizadas = getServicioReporteTrimestral().obtenerListadoUsuariosIPS("todos",codigo);
                this.setListaIPSReportante(armarItemsCombo(ipsreportantesActualizadas));
            }
            else
            if (this.getIdRolFuncionarioAprobador().equals("4"))
            {
                //logBeanWebReactivo.info ("ACTUALIZANDO COMBO DE SECRETARÍA DE SALUD DE SUS IPS");
                ipsreportantesActualizadas = getServicioReporteTrimestral().obtenerListadoUsuariosIPS("ips",codigo);
            }
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("*****************************************************");
            //logBeanWebReactivo.info ("*****************************************************");
            //**********************************************************************
            //**********************************************************************
        }
        
        catch (Exception errorMunicipios)
        {
            errorMunicipios.printStackTrace();
        }
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void actualizarMunicipio()
    {
        //logBeanWebReactivo.info ("ACTUALIZANDO GRILLA CON EL MUNICIPIO");
        this.setListadoPrerdivsParaAprobacion(new ArrayList<ReportePreRDIVVO>());
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void generarReporte()
    {
        String iddepto = "";
        String idmunicipio = "";
        String idips = "";
        java.util.Date fechaIn = null;
        java.util.Date fechaFin = null;
        String roluser = "";
        List<ReportePreRDIVVO> registrosLocales = null;
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest myRequest = (HttpServletRequest)context.getExternalContext().getRequest();
        HttpSession sesionUsuario = myRequest.getSession();     
        
        
        try
        {
            logBeanWebReactivo.info ("GENERANDO REPORTE DE PRERDIVS DE REACTIVO");
            logBeanWebReactivo.info ("RECUPERANDO DATOS DEL  FORMULARIO");
            logBeanWebReactivo.info ("this.departamento : "+ this.departamento);
            logBeanWebReactivo.info ("this.municipio : "+ this.municipio);
            logBeanWebReactivo.info ("this.ipsreportante : "+ this.ipsreportante);
            logBeanWebReactivo.info ("this.fechaInicial : "+ this.fechaInicial);
            logBeanWebReactivo.info ("this.fechaFinal : "+ this.fechaFinal);
            logBeanWebReactivo.info ("this.rolUsuario : " + this.rolUsuario);
            
            if (this.getDepartamento() != null)
            {
                if (!this.departamento.equals("0"))
                {
                    iddepto = this.getDepartamento();
                }
                else
                {
                    iddepto = null;
                }
            }
            else
            {
                iddepto = null;
            }
            
            if (this.getMunicipio() != null)
            {
                if (!this.municipio.equals("0"))
                {
                    idmunicipio = this.getMunicipio();
                }
                else
                {
                    idmunicipio = null;
                }
            }
            else
            {
                idmunicipio = null;
            }
            
            if (this.getIpsreportante() != null)
            {
                if (!this.ipsreportante.equals("-1"))
                {
                    idips = this.getIpsreportante();
                }
                else
                {
                    idips = null;
                }
            }
            else
            {
                idips = null;
            }
            
            if (this.getFechaInicial() != null)
            {
                fechaIn = this.getFechaInicial();
            }
            else
            {
                fechaIn = null;
            }
            //************************************************************
            //************************************************************
            if (this.getFechaFinal() != null)
            {
                fechaFin = this.getFechaFinal();
            }
            else
            {
                fechaFin = null;
            }
            
            if (this.getRolUsuario() != null)
            {
                if (!this.rolUsuario.equals("-1"))
                {
                     roluser = getRolUsuario();
                }
                else
                {
                     roluser = null;
                }            
            }
            else
            {
               roluser = null;
            }
            
            logBeanWebReactivo.info ("Departamento = " + iddepto);
            logBeanWebReactivo.info ("Municipio = " + idmunicipio);
            logBeanWebReactivo.info ("idips = " + idips);
            logBeanWebReactivo.info ("fecha inicial = " + fechaIn);
            logBeanWebReactivo.info ("fecha final = " + fechaFin);
            logBeanWebReactivo.info ("IPS reportante = " + idips);
            logBeanWebReactivo.info ("ROL de usuario de combo = " + roluser);
            logBeanWebReactivo.info ("ROL DE USUARIO DE SESION = " + this.getIdRolBusqueda());
            
             if (this.getIdRolBusqueda().equals("todos"))
             {
                if (roluser == null)
                {
                    registrosLocales = getServicioReporteTrimestral().buscarReportesMasivosTrimestralesPorIPS(iddepto,idmunicipio,fechaIn,fechaFin,idips,"todos",this.getIdMunicipioSesion());
                }
                else
                {
                    registrosLocales = getServicioReporteTrimestral().buscarReportesMasivosTrimestralesPorIPS(iddepto,idmunicipio,fechaIn,fechaFin,idips,roluser,this.getIdMunicipioSesion());
                }
                this.setListadoPrerdivsParaAprobacion(organizarDatosCargue(registrosLocales));
             }
             else
             {
                if (roluser == null)
                {
                        registrosLocales = getServicioReporteTrimestral().buscarReportesMasivosTrimestralesPorIPS(iddepto,idmunicipio,fechaIn,fechaFin,idips,roluser,this.getIdMunicipioSesion());
                        this.setListadoPrerdivsParaAprobacion(organizarDatosCargue(registrosLocales));
                }
                else
                {
                    if (roluser.equals("2") && this.getIdRolBusqueda().equals("prestadores"))
                    {
                        if (idips != null)
                        {
                            //logBeanWebReactivo.info ("Buscando normalmente pero solo registro de prestadores de salud");
                            registrosLocales = getServicioReporteTrimestral().buscarReportesMasivosTrimestralesPorIPS(iddepto,idmunicipio,fechaIn,fechaFin,idips,roluser,this.getIdMunicipioSesion());
                            this.setListadoPrerdivsParaAprobacion(organizarDatosCargue(registrosLocales));
                        }
                        else
                        {
                            //logBeanWebReactivo.info ("No puede consultar registro porque son fabricantes");
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error","El usuario de Secretaría no puede consultar registros de Fabricantes ni Importadores"));
                            RequestContext.getCurrentInstance().update("messages");
                             this.setListadoPrerdivsParaAprobacion(new ArrayList<ReportePreRDIVVO>());
                        }
                    }
                    else
                    {
                        //logBeanWebReactivo.info ("Buscando normalmente pero solo registro de prestadores de salud");
                        registrosLocales = getServicioReporteTrimestral().buscarReportesMasivosTrimestralesPorIPS(iddepto,idmunicipio,fechaIn,fechaFin,idips,roluser,this.getIdMunicipioSesion());
                        this.setListadoPrerdivsParaAprobacion(organizarDatosCargue(registrosLocales));
                    }
                }
             }
            logBeanWebReactivo.info ("TOTAL DE REGISTROS DEL REPORTE TRIMESTRAL CARGADOS PARAMETROS = " + this.getListadoPrerdivsParaAprobacion().size());
            //logBeanWebReactivo.info ("****************************************");
            if (this.getListadoPrerdivsParaAprobacion() != null)
            {
                //logBeanWebReactivo.info ("Seteando resultado en la sesión");
                sesionUsuario.setAttribute("datosGrilla", this.getListadoPrerdivsParaAprobacion());
            }
        }
        
        catch (Exception errorgenerarReporte)
        {
            //logBeanWebReactivo.info ("ERROR DE REPORTE = " + errorgenerarReporte.getLocalizedMessage());
            errorgenerarReporte.printStackTrace();
        }
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerNombreRolUsuario (String codigo)
    {
        String nombre = "";
        
        switch (codigo)
        {
            case "1":
                        nombre = "INVIMA";
                        break;
            case "2":
                        nombre = "Fabricante/Importador";
                        break;
            case "3":
                        nombre = "Prestador de Servicio de Salud";
                        break;
            case "4":
                        nombre = "Secretarias de Salud";
                        break;
            case "5":
                        nombre = "Bancos de Sangre y Componentes Anatómicos";
                        break;
        }
        
        return (nombre);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void limpiarCampos()
    {
        try
        {
            //logBeanWebReactivo.info ("****************************************");
            //logBeanWebReactivo.info ("****************************************");
            //logBeanWebReactivo.info ("GENERANDO LIMPIEZA DE CAMPOS");
            this.setDepartamento("0");
            this.setMunicipio("0");
            this.setIpsreportante("0");
            this.setListadoPrerdivsParaAprobacion(new ArrayList<ReportePreRDIVVO>());
            //logBeanWebReactivo.info ("****************************************");
            //logBeanWebReactivo.info ("****************************************");
        }
        
        catch (Exception errorlimpiarCampos)
        {
            //logBeanWebReactivo.info ("ERROR DE LIMPIEZA = " + errorlimpiarCampos.getLocalizedMessage());
            errorlimpiarCampos.printStackTrace();
        }
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void convertirPrerdivsEnRDivs() {
        int i = 0;
        ReportePreRDIVVO registro = null;
        boolean estado = false;
        String idPrecol = "";
        ArrayList<String> precolsSeleccionados = null;
        String causaProbable = "";
        String gestionEnte = "";
        String observacionInvima = "";
        String estadoCaso = "";
        String codCausaProb = "";
        String codMedida = "";
        boolean sePuedeGrabar = false;
        try {
            this.setNoBorrar(true);
            precolsSeleccionados = new ArrayList<String>();
            String mensajeSalida = "";

            if (this.getListadoPrerdivsParaAprobacionEscogidos().size() == 0) {
                mensajeSalida = "Debe seleccionar a lo menos un Registro de Reporte de Incidente o Evento Adverso con el Reactivo In Vitro para su aprobación";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", mensajeSalida));
            } else {
                if (this.getListaFilasEditadas().size() > 0) {
                    sePuedeGrabar = false;
                    this.listaFilasEditadas = new java.util.ArrayList<Boolean>();
                    this.indiceFilasEditadas = 0;
                } else {
                    sePuedeGrabar = true;
                }
                for (i = 0; i < this.getListadoPrerdivsParaAprobacionEscogidos().size(); i++) {
                    registro = (ReportePreRDIVVO) this.getListadoPrerdivsParaAprobacionEscogidos().get(i);
                    idPrecol = registro.getPrecol();
                    causaProbable = registro.getCodigoCausa();
                    codCausaProb = getEjbServicioRemotoReporteMasivoTrimestral().obtenerCodigoCausaProbable(causaProbable);
                    gestionEnte = registro.getGestionEnte();
                    observacionInvima = registro.getObservacionInvima();
                    estadoCaso = obtenerIdEstadoLocal(registro.getEstadoCaso());
                    precolsSeleccionados.add(idPrecol);
                    precolsSeleccionados.add(codCausaProb);
                    precolsSeleccionados.add(gestionEnte);
                    precolsSeleccionados.add(observacionInvima);
                    precolsSeleccionados.add(estadoCaso);
                }

                if (precolsSeleccionados.size() > 0) {
                    if (sePuedeGrabar == true) {
                        ejecutarConversionPrerdivsEnCols(precolsSeleccionados);
                    } else {
                        mensajeSalida = "No es posible realizar la aprobación puesto que el usuario debe guardar los cambios en las filas de la grilla que ha seleccionado";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", mensajeSalida));
                    }
                } else {
                    mensajeSalida = "Verifique todos los datos de la grilla de aprobación, puesto que hay registros incompletos en las columnas editables que deben ser diligenciadas";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", mensajeSalida));
                }
            }
        } catch (Exception errorconvertirPrerdivsEnCols) {
            errorconvertirPrerdivsEnCols.printStackTrace();
        }
    }

    public void rechazarPrerdivsCargue()
    {
        int i = 0;
        ReportePreRDIVVO registro = null;
        boolean estado = false;
        String idPrecol = "";
        ArrayList<String> precolsSeleccionados = null;
        //********************************************************
        //********************************************************
        String causaProbable = "";
        String gestionEnte = "";
        String observacionInvima = "";
        String estadoCaso = "";
        //********************************************************
        //********************************************************
        String codCausaProb = "";
        String codMedida = "";
        //********************************************************
        //********************************************************
        //********************************************************
        boolean sePuedeGrabar = false;
        //********************************************************
        //********************************************************
        //********************************************************
        try
        {
            this.setNoBorrar(true);
            //logBeanWebReactivo.info ("----------------------------------------------------->");
            //logBeanWebReactivo.info ("----------------------------------------------------->");
            //logBeanWebReactivo.info ("Ejecutando rechazo de precols de los Reportantes");
            
            precolsSeleccionados = new ArrayList<String>();
            
            //logBeanWebReactivo.info ("*****************************************");
            //logBeanWebReactivo.info ("*****************************************");
            //logBeanWebReactivo.info ("PRECOLS DE LA GRILLA");
            //logBeanWebReactivo.info ("Tamaño de los registros: " + this.getListadoPrerdivsParaAprobacion().size());
            //logBeanWebReactivo.info ("*****************************************");
            //logBeanWebReactivo.info ("Tamaño de los registros escogidos: " + this.getListadoPrerdivsParaAprobacionEscogidos().size());
            //logBeanWebReactivo.info ("*****************************************");
            //logBeanWebReactivo.info ("*****************************************");
            
            String mensajeSalida = "";
            //****************************************************************
            //****************************************************************
            //****************************************************************
            if (this.getListadoPrerdivsParaAprobacionEscogidos().size() == 0)
            {
                //logBeanWebReactivo.info ("NO SE SELECCIONÓ NADA");
                //**********************************************************************
                //**********************************************************************
                //**********************************************************************
                mensajeSalida = "Debe seleccionar a lo menos un Registro de Reporte de Incidente o Evento Adverso con el Reactivo In Vitro para su rechazo y remisión a Reportantes";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error",mensajeSalida));
            }
            else
            {
                //logBeanWebReactivo.info ("RECUPERANDO PRECOLES SELECCIONADOS DE LA GRILLA PARA RECHAZO");
                //**********************************************************************
                //**********************************************************************
                //**********************************************************************
                //logBeanWebReactivo.info ("**********************************************");
                //logBeanWebReactivo.info ("Tamaño del vector de control de filas cerradas = " + this.getListaFilasEditadas().size());
                //logBeanWebReactivo.info ("**********************************************");
                if (this.getListaFilasEditadas().size() > 0)
                {
                    //logBeanWebReactivo.info ("Se puede grabar porque hay filas cerradas");
                    sePuedeGrabar = false;
                }
                else
                {
                    //logBeanWebReactivo.info ("No se puede grabar porque hay filas sin cerrar");
                    sePuedeGrabar = true;
                }
                //**********************************************************************
                //**********************************************************************
                //logBeanWebReactivo.info ("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                //logBeanWebReactivo.info ("Recuperando precols seleccionados");
                for (i = 0;  i < this.getListadoPrerdivsParaAprobacionEscogidos().size();  i++)
                {
                    registro = (ReportePreRDIVVO)this.getListadoPrerdivsParaAprobacionEscogidos().get(i);
                    idPrecol = registro.getPrecol();
                    //logBeanWebReactivo.info ("Recuperando precol actual (" + (i+1) + "): " + idPrecol);
                    //**********************************************************
                    //**********************************************************
                    causaProbable = registro.getCodigoCausa();
                    codCausaProb = getEjbServicioRemotoReporteMasivoTrimestral().obtenerCodigoCausaProbable(causaProbable);
                    gestionEnte = registro.getGestionEnte();
                    observacionInvima = registro.getObservacionInvima();
                    estadoCaso = obtenerIdEstadoLocal(registro.getEstadoCaso());
                    //**********************************************************
                    //**********************************************************
                    //**********************************************************
                    logBeanWebReactivo.info ("-----------------------------------------------------------------------------------------------");
                    logBeanWebReactivo.info ("-----------------------------------------------------------------------------------------------");
                    logBeanWebReactivo.info ("causaProbable = " + causaProbable + " - codCausaProb = " + codCausaProb);
                    logBeanWebReactivo.info ("gestionEnte = " + gestionEnte);
                    logBeanWebReactivo.info ("observacionInvima = " + observacionInvima);
                    logBeanWebReactivo.info ("estadoCaso = " + estadoCaso);
                    logBeanWebReactivo.info ("-----------------------------------------------------------------------------------------------");
                    logBeanWebReactivo.info ("-----------------------------------------------------------------------------------------------");
                    //**********************************************************
                    //**********************************************************
                    /*
                    if (idPrecol != null && codTipoReporte != null && codCausaProb != null && codMedida != null && seguimiento != null && estadoCaso != null)
                    {
                    */
                        //logBeanWebReactivo.info ("-------------------------------------------------------------------------");
                        //logBeanWebReactivo.info ("TODOS LOS REGISTROS ESTÁN COMPLETOS");
                        //logBeanWebReactivo.info ("-------------------------------------------------------------------------");
                        precolsSeleccionados.add(idPrecol);
                        precolsSeleccionados.add(codCausaProb);
                        precolsSeleccionados.add(gestionEnte);
                        precolsSeleccionados.add(observacionInvima);
                        precolsSeleccionados.add(estadoCaso);
                    /*
                    }
                    else
                    {
                        //logBeanWebReactivo.info ("-------------------------------------------------------------------------");
                        //logBeanWebReactivo.info ("ALGUNOS DATOS ESTÁN INCOMPLETOS PARA EL PRECOL = " + idPrecol);
                        //logBeanWebReactivo.info ("-------------------------------------------------------------------------");
                    }
                    */
                    //**********************************************************
                    //**********************************************************
                }
                //logBeanWebReactivo.info ("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                //****************************************************************
                //****************************************************************
                //****************************************************************
                //****************************************************************
                //logBeanWebReactivo.info ("----------------------------------------------------->");
                //logBeanWebReactivo.info ("----------------------------------------------------->");
                //logBeanWebReactivo.info ("TAMAÑO TOTAL DEL ARREGLO PARA PODER GRABAR = " + precolsSeleccionados.size());
                //logBeanWebReactivo.info ("----------------------------------------------------->");
                //logBeanWebReactivo.info ("----------------------------------------------------->");
                //if (precolsSeleccionados.size() == (this.getListadoPrerdivsParaAprobacionEscogidos().size()*6))
                if (precolsSeleccionados.size() > 0)
                {
                    //***********************************************************
                    //***********************************************************
                    for (i = 0;  i < precolsSeleccionados.size();  i+=5)
                    {
                        //logBeanWebReactivo.info ("Precol para convertir en col = " + (String)precolsSeleccionados.get(i));
                        //logBeanWebReactivo.info ("COD CAUSA PROBABLE = " + (String)precolsSeleccionados.get(i+1));
                        //logBeanWebReactivo.info ("GESTION ENTE = " + (String)precolsSeleccionados.get(i+2));
                        //logBeanWebReactivo.info ("OBSERVACION INVIMA = " + (String)precolsSeleccionados.get(i+3));
                        //logBeanWebReactivo.info ("ESTADO CASO = " + (String)precolsSeleccionados.get(i+4));
                    }
                    //***********************************************************
                    //***********************************************************
                    if (sePuedeGrabar == true)
                    {
                        //logBeanWebReactivo.info ("Las filas están cerradas");
                        rechazarPrerdivsSeleccionadosParaDevolucion(precolsSeleccionados);
                    }
                    else
                    {
                        //logBeanWebReactivo.info ("No se puede grabar porque hay filas sin cerrar");
                        mensajeSalida = "No es posible realizar la aprobación puesto que el usuario debe guardar los cambios en las filas de la grilla que ha seleccionado";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error",mensajeSalida));
                    }
                    //***********************************************************
                    //***********************************************************
                }
                else
                {
                    mensajeSalida = "Verifique todos los datos de la grilla de aprobación, puesto que hay registros incompletos en las columnas editables que deben ser diligenciadas";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error",mensajeSalida));
                }
                //logBeanWebReactivo.info ("----------------------------------------------------->");
                //logBeanWebReactivo.info ("----------------------------------------------------->");
                //**********************************************************************
                //**********************************************************************
                //**********************************************************************
            }
            //****************************************************************
            //****************************************************************
        }
        
        catch (Exception errorconvertirPrerdivsEnCols)
        {
            //logBeanWebReactivo.info ("Error en el metodo rechazarPrerdivsCargue = " + errorconvertirPrerdivsEnCols.getLocalizedMessage());
            errorconvertirPrerdivsEnCols.printStackTrace();
        }
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String consultarConsecutivoRDIVSistema() 
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
        
        //Actualizar el valor del parametro una vez generado el siguiente secuencial para el PRECOL
        p.setConsecBpc((long) i + 1);
        reporteEjb.actualizarParametros(p);

        consecutivo = "RDIV" + Fecha.formateaFecha(Fecha.crearFecha(), "yyMM") + consecutivo;
        logBeanWebReactivo.info ("CONSECUTIVO RDIV GENERADO: " + consecutivo);
        return consecutivo;
    }
    public void ejecutarConversionPrerdivsEnCols(java.util.ArrayList<String> registros) {
        List<ReportePreRDIVVO> listadoLocal = null;
        int i = 0;
        String idPrecol = "";
        ReactivoEventosTemp registroEventoTemporal;
        ReactivoPacienteTemp registroPacienteTemporal;
        ReactivoReportanteTemp registroReportanteTemporal;
        ReactivoGestionrealizadaTemp registroGestionRealizadaTemporal;
        ReactivoCamposcompTemp registroCamposComplementariosTemporal;
        ReactivoInstitucionTemp registroInstitucionTemporal;
        ReactivoProductoTemp registroProductoTemporal;
        ReactivoEventos registroEvento;
        ReactivoPaciente registroPaciente;
        ReactivoReportante registroReportante;
        ReactivoGestionrealizada registroGestionRealizada;
        ReactivoCamposcomp registroCamposComplementarios;
        ReactivoInstitucion registroInstitucion;
        ReactivoProducto registroProducto;
        String expediente = "";
        String ultimoCol = "";
        String nuevoCol = "";
        long siguienteRDIV = 0L;
        boolean seInsertoReactivoEventos = false;
        boolean seInsertoReactivoPaciente = false;
        boolean seInsertoReactivoReportante = false;
        boolean seInsertoReactivoGestionrealizada = false;
        boolean seInsertoReactivoCamposcomp = false;
        boolean seInsertoReactivoInstitucion = false;
        boolean seInsertoReactivoProducto = false;
        boolean seInsertoPaqueteCompleto = false;
        int contadorRegistrosReactivoEventos = 0;
        int contadorRegistrosReactivoPaciente = 0;
        int contadorRegistrosReactivoReportante = 0;
        int contadorRegistrosReactivoGestionrealizada = 0;
        int contadorRegistrosReactivoCamposcomp = 0;
        int contadorRegistrosReactivoInstitucion = 0;
        int contadorRegistrosReactivoProducto = 0;
        int contadorRegistrosPaqueteCompleto = 0;
        java.util.Date fechaSistema = null;
        String mensajeSalida = "";
        java.util.ArrayList<String> registrosColYPrecol = null;
        java.util.ArrayList<String> arregloReportantes = null;
        String nitEmpresaCorreo = "";
        String correoEmpresa = "";
        String nombreEmpresa = "";
        String tipoReporte = "";
        String causaProbable = "";
        String gestionEnte = "";
        String observacionInvima = "";
        String estadoCaso = "";
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sesionUsuario = request.getSession();
        String nombreUsuario = "";
        userSesion = null;
        String idFuncionarioResponsable = "0";
        fechaSistema = new java.util.Date();
        String idRol = "";
        boolean seActualizoTemporalPrecol = false;
        try {
            userSesion = (UsuariosVO) sesionUsuario.getAttribute("usuario");
            nombreUsuario = userSesion.getUsuario();
            registrosColYPrecol = new java.util.ArrayList<String>();
            arregloReportantes = new java.util.ArrayList<String>();
            for (i = 0; i < registros.size(); i += 5) {
                idPrecol = (String) registros.get(i);
                causaProbable = (String) registros.get(i + 1);
                gestionEnte = (String) registros.get(i + 2);
                observacionInvima = (String) registros.get(i + 3);
                estadoCaso = (String) registros.get(i + 4);
                expediente = getServicioReporteTrimestral().obtenerCodigoRegistroSanitarioPorPrecol(idPrecol);
                if (this.getIdRolFuncionarioAprobador().equals("1")) {
                    idFuncionarioResponsable = ejbServicioRemotoReporteMasivoTrimestral.obtenerCodigoFuncionarioInvima(nombreUsuario);
                } else {
                    idFuncionarioResponsable = String.valueOf(userSesion.getIdentificacionPersona());
                }
                List<ReactivoEventosTemp> x = getServicioReporteTrimestral().consultarReactivoEventosTemporal();
                registroEventoTemporal = getServicioReporteTrimestral().consultarRegistroReactivoEventosTemporal(idPrecol);
                registroPacienteTemporal = getServicioReporteTrimestral().consultarRegistroReactivoPacienteTemporal(idPrecol);
                registroReportanteTemporal = getServicioReporteTrimestral().consultarRegistroReactivoReportanteTemporal(idPrecol);
                registroGestionRealizadaTemporal = getServicioReporteTrimestral().consultarRegistroReactivoGestionRealizadaTemporal(idPrecol);
                registroCamposComplementariosTemporal = getServicioReporteTrimestral().consultarRegistroReactivoCamposComplementariosTemporal(idPrecol);
                registroInstitucionTemporal = getServicioReporteTrimestral().consultarRegistroReactivoInstitucionTemporal(idPrecol);
                registroProductoTemporal = getServicioReporteTrimestral().consultarRegistroReactivoProductoTemporal(idPrecol);
                registroEventoTemporal.setCdgSeriedad(1);
                seActualizoTemporalPrecol = getServicioReporteTrimestral().modificarRegistroReactivoEventosEstadoAprobacion(registroEventoTemporal, idPrecol);
                nuevoCol = consultarConsecutivoRDIVSistema();

                if (this.getIdRolFuncionarioAprobador().equals("1")) {
                    //logBeanWebReactivo.info ("SE OBTENEDRA EL FUNCIONARIO DEL DIRECTORIO ACTIVO");
                    idFuncionarioResponsable = getEjbServicioRemotoReporteMasivoTrimestral().obtenerCodigoFuncionarioInvima(nombreUsuario);
                } else {
                    //logBeanWebReactivo.info ("SE OBTENDRA EL USUARIO INTERNET DE LA SECRETARÍA");
                    idFuncionarioResponsable = String.valueOf(userSesion.getIdentificacionPersona());
                }

                //Generamos los nuevos objetos
                registroEvento = new ReactivoEventos();
                registroEvento.setReporte(nuevoCol);
                registroEvento.setFechEvento(registroEventoTemporal.getFechEvento());
                registroEvento.setEfectoIndeseado(registroEventoTemporal.getEfectoIndeseado());
                registroEvento.setFechReporte(registroEventoTemporal.getFechReporte());
                registroEvento.setCdgProblema(registroEventoTemporal.getCdgProblema());
                registroEvento.setDescripEfecto(registroEventoTemporal.getDescripEfecto());
                registroEvento.setClasificacion(registroEventoTemporal.getClasificacion());
                registroEvento.setCdgDesenlace(registroEventoTemporal.getCdgDesenlace());
                registroEvento.setOtroDesenlace(registroEventoTemporal.getOtroDesenlace());
                registroEvento.setDesempeno(registroEventoTemporal.getDesempeno());
                registroEvento.setInternet(registroEventoTemporal.getInternet());
                registroEvento.setFechaIngreso(registroEventoTemporal.getFechaIngreso());
                registroEvento.setReportado(registroEventoTemporal.getReportado());
                registroEvento.setIdrol(registroEventoTemporal.getIdrol());
                registroEvento.setIdips(registroEventoTemporal.getIdips());
                registroEvento.setReportepre(registroEventoTemporal.getReportepre());
                registroEvento.setCdgSeriedad(1);
                seInsertoReactivoEventos = getEjbServicioRemotoReporteMasivoTrimestral().crearRegistroReactivoEventosCol(registroEvento, nuevoCol);

                registroPaciente = new ReactivoPaciente();
                registroPaciente.setReporte(nuevoCol);
                registroPaciente.setNombrePaciente(registroPacienteTemporal.getNombrePaciente());
                registroPaciente.setTipidentifi(registroPacienteTemporal.getTipidentifi());
                registroPaciente.setIdentifi(registroPacienteTemporal.getIdentifi());
                registroPaciente.setEdad(registroPacienteTemporal.getEdad());
                registroPaciente.setEdadEn(registroPacienteTemporal.getEdadEn());
                registroPaciente.setGenero(registroPacienteTemporal.getGenero());
                registroPaciente.setDireccPaciente(registroPacienteTemporal.getDireccPaciente());
                registroPaciente.setTelefoPaciente(registroPacienteTemporal.getTelefoPaciente());
                seInsertoReactivoPaciente = getEjbServicioRemotoReporteMasivoTrimestral().crearRegistroReactivoPacienteCol(registroPaciente, nuevoCol);

                registroReportante = new ReactivoReportante();
                registroReportante.setReporte(nuevoCol);
                registroReportante.setNombresApellidos(registroReportanteTemporal.getNombresApellidos());
                registroReportante.setIdentificacion(registroReportanteTemporal.getIdentificacion());
                registroReportante.setCargo(registroReportanteTemporal.getCargo());
                registroReportante.setProfesion(registroReportanteTemporal.getProfesion());
                registroReportante.setDireccion(registroReportanteTemporal.getDireccion());
                registroReportante.setPais(registroReportanteTemporal.getPais());
                registroReportante.setCodDepart(registroReportanteTemporal.getCodDepart());
                registroReportante.setCodMun(registroReportanteTemporal.getCodMun());
                registroReportante.setTelefono(registroReportanteTemporal.getTelefono());
                registroReportante.setMovil(registroReportanteTemporal.getMovil());
                registroReportante.setEmail(registroReportanteTemporal.getEmail());
                registroReportante.setDivulgacion(registroReportanteTemporal.getDivulgacion());
                registroReportante.setFechaNotificacion(registroReportanteTemporal.getFechaNotificacion());
                registroReportante.setAreaPertenece(registroReportanteTemporal.getAreaPertenece());
                seInsertoReactivoReportante = getEjbServicioRemotoReporteMasivoTrimestral().crearRegistroReactivoReportanteCol(registroReportante, nuevoCol);

                registroCamposComplementarios = new ReactivoCamposcomp();
                registroCamposComplementarios.setReporte(nuevoCol);
                registroCamposComplementarios.setOrganizacion(registroCamposComplementariosTemporal.getOrganizacion());
                registroCamposComplementarios.setCodigoCausa(registroCamposComplementariosTemporal.getCodigoCausa());
                registroCamposComplementarios.setFechaEnvioReactivo(registroCamposComplementariosTemporal.getFechaEnvioReactivo());
                registroCamposComplementarios.setExpediente(registroCamposComplementariosTemporal.getExpediente());
                registroCamposComplementarios.setCodigoUnicoReactivo(registroCamposComplementariosTemporal.getCodigoUnicoReactivo());
                registroCamposComplementarios.setCodigoTipoDiagnostico(registroCamposComplementariosTemporal.getCodigoTipoDiagnostico());
                registroCamposComplementarios.setReferencia(registroCamposComplementariosTemporal.getReferencia());
                registroCamposComplementarios.setCategoriaReactivo(registroCamposComplementariosTemporal.getCategoriaReactivo());
                registroCamposComplementarios.setIndRealizaRecepcion(registroCamposComplementariosTemporal.getIndRealizaRecepcion());

                ReactivoTiporeportante idTipoReportante = new ReactivoTiporeportante();
                idTipoReportante.setCdgTiporeportante(Integer.valueOf(registroCamposComplementariosTemporal.getCdgTiporeportante()));
                registroCamposComplementarios.setCdgTiporeportante(idTipoReportante);
                if (!estadoCaso.equals("")) {
                    registroCamposComplementarios.setEstadoReporte(Integer.valueOf(estadoCaso));   //Estado de caso de la grilla de preaprobación
                } else {
                    //Caso abierto pendiente de resolver
                    registroCamposComplementarios.setEstadoReporte(3);   //Estado de caso de la grilla de preaprobación
                }
                registroCamposComplementarios.setFechaGestionEnte(null);
                registroCamposComplementarios.setGestionEnte(gestionEnte);
                registroCamposComplementarios.setEstadoGestionEnte(null);
                registroCamposComplementarios.setFechaGestionInvima(null);
                registroCamposComplementarios.setObservacionInvima(observacionInvima);  //Gestión invima de la grilla de preaprobación
                try {
                    registroCamposComplementarios.setCodigoFuncionario(Long.valueOf(idFuncionarioResponsable));
                } catch (Exception e) {
                }  //Código funcionario que aprueba
                seInsertoReactivoCamposcomp = getEjbServicioRemotoReporteMasivoTrimestral().crearRegistroReactivoCamposComplementariosCol(registroCamposComplementarios, nuevoCol);

                registroGestionRealizada = new ReactivoGestionrealizada();
                registroGestionRealizada.setReporte(nuevoCol);
                registroGestionRealizada.setCausaProbable(causaProbable);  //Causa probable de la grilla de preaprobación
                registroGestionRealizada.setCausaEfecto(registroGestionRealizadaTemporal.getCausaEfecto());
                registroGestionRealizada.setImportador(registroGestionRealizadaTemporal.getImportador());
                registroGestionRealizada.setFabricante(registroGestionRealizadaTemporal.getFabricante());
                registroGestionRealizada.setComercializador(registroGestionRealizadaTemporal.getComercializador());
                registroGestionRealizada.setDistribuidor(registroGestionRealizadaTemporal.getDistribuidor());
                registroGestionRealizada.setFechaNotificacion(registroGestionRealizadaTemporal.getFechaNotificacion());
                registroGestionRealizada.setEnviadoDistriImport(registroGestionRealizadaTemporal.getEnviadoDistriImport());
                registroGestionRealizada.setGestionRiesgo(registroGestionRealizadaTemporal.getGestionRiesgo());
                registroGestionRealizada.setAnalisisEfecto(registroGestionRealizadaTemporal.getAnalisisEfecto());
                registroGestionRealizada.setHerramientaAnalisis(registroGestionRealizadaTemporal.getHerramientaAnalisis());
                registroGestionRealizada.setOtraHerramienta(registroGestionRealizadaTemporal.getOtraHerramienta());
                registroGestionRealizada.setDescripcionCausa(registroGestionRealizadaTemporal.getDescripcionCausa());
                registroGestionRealizada.setAcciones(registroGestionRealizadaTemporal.getAcciones());
                registroGestionRealizada.setDescripcionAcciones(registroGestionRealizadaTemporal.getDescripcionAcciones());
                try {
                    registroGestionRealizada.setCdgfuncionario(new BigInteger(idFuncionarioResponsable));
                } catch (Exception e) {
                }
                seInsertoReactivoGestionrealizada = getEjbServicioRemotoReporteMasivoTrimestral().crearRegistroReactivoGestionRealizadaCol(registroGestionRealizada, nuevoCol);

                registroInstitucion = new ReactivoInstitucion();
                registroInstitucion.setReporte(nuevoCol);
                registroInstitucion.setInstitucion(registroInstitucionTemporal.getInstitucion());
                registroInstitucion.setIdentificacion(registroInstitucionTemporal.getIdentificacion());
                registroInstitucion.setDireccion(registroInstitucionTemporal.getDireccion());
                registroInstitucion.setNaturaleza(registroInstitucionTemporal.getNaturaleza());
                registroInstitucion.setComplejidad(registroInstitucionTemporal.getComplejidad());
                registroInstitucion.setCodDepart(registroInstitucionTemporal.getCodDepart());
                registroInstitucion.setCodMun(registroInstitucionTemporal.getCodMun());
                registroInstitucion.setEmail(registroInstitucionTemporal.getEmail());
                registroInstitucion.setFechaReporte(registroInstitucionTemporal.getFechaReporte());
                registroInstitucion.setTelefono(registroInstitucionTemporal.getTelefono());
                seInsertoReactivoInstitucion = getEjbServicioRemotoReporteMasivoTrimestral().crearRegistroReactivoInstitucionCol(registroInstitucion, nuevoCol);

                registroProducto = new ReactivoProducto();
                registroProducto.setReporte(nuevoCol);
                registroProducto.setNombreReactivo(registroProductoTemporal.getNombreReactivo());
                registroProducto.setNroregsan(registroProductoTemporal.getNroregsan());
                registroProducto.setFechaVenci(registroProductoTemporal.getFechaVenci());
                registroProducto.setLote(registroProductoTemporal.getLote());
                registroProducto.setProcedencia(registroProductoTemporal.getProcedencia());
                registroProducto.setCadenaFrio(registroProductoTemporal.getCadenaFrio());
                registroProducto.setTemperatura(registroProductoTemporal.getTemperatura());
                registroProducto.setCertiAnalisis(registroProductoTemporal.getCertiAnalisis());
                registroProducto.setDistriUsuario(registroProductoTemporal.getDistriUsuario());
                registroProducto.setCondiciAlmacen(registroProductoTemporal.getCondiciAlmacen());
                registroProducto.setAreaFunciona(registroProductoTemporal.getAreaFunciona());
                registroProducto.setOtraArea(registroProductoTemporal.getOtraArea());
                seInsertoReactivoProducto = getEjbServicioRemotoReporteMasivoTrimestral().crearRegistroReactivoProductoCol(registroProducto, nuevoCol);
                logBeanWebReactivo.info("INSERTANDO REGISTRO DE PRODUCTO DE REACTIVO VIGILANCIA CON PRERDIV = " + nuevoCol + " - RESULTADO: " + seInsertoReactivoProducto);

                siguienteRDIV++;
                registrosColYPrecol.add(idPrecol);
                registrosColYPrecol.add(nuevoCol);
                registrosColYPrecol.add(ejbServicioRemotoReporteMasivoTrimestral.obtenerNombreCausaProbablePorCodigo(causaProbable));
                registrosColYPrecol.add(gestionEnte);
                registrosColYPrecol.add(observacionInvima);
                registrosColYPrecol.add(obtenerNombreEstadoLocal(estadoCaso));
                if (seInsertoReactivoEventos == true) {
                    contadorRegistrosReactivoEventos++;
                }
                if (seInsertoReactivoPaciente == true) {
                    contadorRegistrosReactivoPaciente++;
                }
                if (seInsertoReactivoReportante == true) {
                    contadorRegistrosReactivoReportante++;
                }
                if (seInsertoReactivoGestionrealizada == true) {
                    contadorRegistrosReactivoGestionrealizada++;
                }
                if (seInsertoReactivoCamposcomp == true) {
                    contadorRegistrosReactivoCamposcomp++;
                }
                if (seInsertoReactivoInstitucion == true) {
                    contadorRegistrosReactivoInstitucion++;
                }
                if (seInsertoReactivoProducto == true) {
                    contadorRegistrosReactivoProducto++;
                }
                if (seInsertoPaqueteCompleto == true) {
                    contadorRegistrosPaqueteCompleto++;
                }

                if (contadorRegistrosReactivoEventos == contadorRegistrosReactivoPaciente
                        && contadorRegistrosReactivoPaciente == contadorRegistrosReactivoReportante
                        && contadorRegistrosReactivoReportante == contadorRegistrosReactivoGestionrealizada
                        && contadorRegistrosReactivoGestionrealizada == contadorRegistrosReactivoCamposcomp
                        && contadorRegistrosReactivoCamposcomp == contadorRegistrosReactivoInstitucion
                        && contadorRegistrosReactivoInstitucion == contadorRegistrosReactivoProducto) {
                    contadorRegistrosPaqueteCompleto++;
                    arregloReportantes = agregarItemEmpresaReportante(arregloReportantes, registroEventoTemporal.getIdips());
                }
            }

            if (contadorRegistrosReactivoEventos == (registros.size() / 5)
                    && contadorRegistrosReactivoPaciente == (registros.size() / 5)
                    && contadorRegistrosReactivoReportante == (registros.size() / 5)
                    && contadorRegistrosReactivoGestionrealizada == (registros.size() / 5)
                    && contadorRegistrosReactivoCamposcomp == (registros.size() / 5)
                    && contadorRegistrosReactivoInstitucion == (registros.size() / 5)
                    && contadorRegistrosReactivoProducto == (registros.size() / 5)) {
                seInsertoPaqueteCompleto = true;
                logBeanWebReactivo.info("VAMOS A GENERAR CORREO DE APROBACION DE PRERDIVS REACTIVO A LAS " + new java.util.Date());
                enviarCorreoElectronicoNotificacionReporteTrimestralRDivsSecretariaAprobacion(seInsertoPaqueteCompleto, this.getCorreoReportante(), fechaSistema,
                        contadorRegistrosReactivoEventos, registrosColYPrecol);
                logBeanWebReactivo.info("GENERANDO CORREO DE COPIA PARA LAS EMPRESAS REPORANTE DE PRECOLES");
                if (arregloReportantes != null) {
                    for (i = 0; i < arregloReportantes.size(); i++) {
                        nitEmpresaCorreo = (String) arregloReportantes.get(i);
                        correoEmpresa = getServicioReporteTrimestral().obtenerCorreoEmpresaPorNIT(nitEmpresaCorreo);
                        
                        if(correoEmpresa.equals(this.getCorreoReportante())){    continue;}
                        
                        nombreEmpresa = getServicioReporteTrimestral().obtenerNombreEmpresaIPSPorNit(nitEmpresaCorreo);
                        idRol = getServicioReporteTrimestral().obtenerIdRolUsuarioPorNIT(nitEmpresaCorreo);
                        if (nitEmpresaCorreo.equals("0")) {
                            correoEmpresa = "reactivovigilancia@invima.gov.co";
                            nombreEmpresa = "INSTITUTO NACIONAL DE VIGILANCIA DE MEDICAMENTOS Y ALIMENTOS INVIMA";
                        }
                        enviarCorreoElectronicoNotificacionReporteTrimestralRDivsIPSAprobacion(seInsertoPaqueteCompleto, correoEmpresa, fechaSistema,
                                contadorRegistrosReactivoEventos, registrosColYPrecol, nombreEmpresa, idRol);
                    }
                }
                mensajeSalida = "La Migración de Registros de Cargue Trimestral Masivo fue ejecutada exitosamente insertando " + contadorRegistrosPaqueteCompleto + " registros";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proceso Exitoso", mensajeSalida));
            } else {
                mensajeSalida = "La Migración de Registros de Cargue Trimestral Masivo no pudo ser ejecutada para actualizar " + contadorRegistrosPaqueteCompleto + " registros";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", mensajeSalida));
            }
            listadoLocal = getServicioReporteTrimestral().buscarReportesMasivosTrimestralesPorIPS(this.getDepartamento(), null, null, null, null, null, this.getIdMunicipioSesion());
            this.setListadoPrerdivsParaAprobacion(organizarDatosCargue(listadoLocal));
            RequestContext.getCurrentInstance().update("formularioBusquedaPrerdivs:datosReporte");
        } catch (Exception errorConversion) {
            errorConversion.printStackTrace();
        }
    }

    public void rechazarPrerdivsSeleccionadosParaDevolucion (java.util.ArrayList<String> registros)
    {
        //***************************************************
        //***************************************************
        //***************************************************
        List<ReportePreRDIVVO> listadoLocal = null;
        int i = 0;
        String idPrecol = "";
        //***************************************************
        //***************************************************
        ReactivoEventosTemp registroEventoTemp;
        //***************************************************
        //***************************************************
        //***************************************************
        //***************************************************
        String expediente = "";
        String ultimoCol = "";
        String nuevoCol = "";
        long siguienteCol = 0L;
        //***************************************************
        //***************************************************
        boolean seRechazoPaqueteCompleto = false;
        boolean seModificoRegistroReporte = false;
        //***************************************************
        //***************************************************
        int contadorTecnoReporteEventos = 0;
        /*
        int contadorTecnoDispositivo = 0;
        int contadorTecnoPaciente = 0;
        int contadorTecnoEvaluacionCaso = 0;
        int contadorRegistroCol = 0;
        */
        //***************************************************
        //***************************************************
        java.util.Date fechaSistema = null;
        String mensajeSalida = "";
        java.util.ArrayList<String> registrosColYPrecol = null;
        java.util.ArrayList<String> arregloReportantes = null;
        String nitEmpresaCorreo = "";
        String correoEmpresa = "";
        String nombreEmpresa = "";
        //***************************************************
        //***************************************************
        //***************************************************
        //***************************************************
        String causaProbable = "";
        String gestionEnte = "";
        String observacionInvima = "";
        String estadoCaso = "";
        //***************************************************
        //***************************************************
        //***************************************************
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sesionUsuario = request.getSession();
        String nombreUsuario = "";
        userSesion = null;
        String idFuncionarioResponsable = "";
        fechaSistema = new java.util.Date();
        String idRol = "";
        boolean seActualizoTemporalPrecol = false;
        //***************************************************
        //***************************************************
        try
        {  
                //***************************************************
                //***************************************************
                userSesion = (UsuariosVO)sesionUsuario.getAttribute("usuario");
                nombreUsuario = userSesion.getUsuario();
               //***************************************************
                //***************************************************
                //logBeanWebReactivo.info ("EJECUCIÓN DEL PROCESO DE ELIMINACIÓN DE PRECOLS POR EL INVIMA O SECRETARÍA = " + fechaSistema.toString());
                //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                //ultimoCol = getServicioReporteTrimestral().obtenerSiguienteColSecuenciaSistema();
                //logBeanWebReactivo.info ("SIGUIENTE COL PARA INSERCIÓN = " + ultimoCol);
                //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                //************************************************************************
                //************************************************************************
                //************************************************************************
                registrosColYPrecol = new java.util.ArrayList<String>();
                arregloReportantes = new java.util.ArrayList<String>();
                //************************************************************************
                //************************************************************************
                //siguienteCol = Long.valueOf(ultimoCol);
                //************************************************************************
                //************************************************************************
                //for (i = 0;  i < registros.size();  i++)
                for (i = 0;  i < registros.size();  i+=5)
                {
                    //******************************************************
                    //******************************************************
                    idPrecol = (String)registros.get(i);
                    causaProbable = (String)registros.get(i+1);
                    gestionEnte = (String)registros.get(i+2);
                    observacionInvima = (String)registros.get(i+3);
                    estadoCaso = (String)registros.get(i+4);
                    //******************************************************
                    //******************************************************
                    //******************************************************
                    //logBeanWebReactivo.info ("ID PRECOL PARA RECHAZAR = " + idPrecol);
                    //************************************************
                    //************************************************
                    expediente = getServicioReporteTrimestral().obtenerCodigoRegistroSanitarioPorPrecol(idPrecol);
                    //logBeanWebReactivo.info ("PRECOL = " + idPrecol + " - EXPEDIENTE = " + expediente);
                    //************************************************
                    //************************************************
                    registroEventoTemp = getServicioReporteTrimestral().consultarRegistroReactivoEventosTemporal(idPrecol);
                    /*
                    regPrecolTecnoPacienteTemp = getServicioReporteTrimestral().consultarRegistroTecnoPacienteTemp(idPrecol);
                    regPrecolTecnoDispositivoTemp = getServicioReporteTrimestral().consultarRegistroTecnoDispositivoTemp(llaveDispositivo);
                    regPrecolTecnoEvaluacionCasoTemp = getServicioReporteTrimestral().consultarRregistroTecnoEvaluacionCasoTemp(idPrecol);   
                    */
                    //************************************************
                    //************************************************
                    //************************************************
                    //************************************************
                    //Eliminamos el registro
                    registroEventoTemp.setCdgSeriedad(2);
                    seModificoRegistroReporte = getServicioReporteTrimestral().modificarRegistroReactivoEventosEstadoAprobacion(registroEventoTemp,idPrecol);
                    //logBeanWebReactivo.info ("SE ACTUALIZÓ EL ESTADO DE APROBACIÓN DEL REGISTO TEMPORAL DEL PRERDIV EN RECHAZADO = " + idPrecol + " (2) = " + seModificoRegistroReporte);
                    //************************************************
                    //************************************************
                    registrosColYPrecol.add(idPrecol);
                    registrosColYPrecol.add("Rechazado");
                    registrosColYPrecol.add(ejbServicioRemotoReporteMasivoTrimestral.obtenerNombreCausaProbablePorCodigo(causaProbable));
                    registrosColYPrecol.add(gestionEnte);
                    registrosColYPrecol.add(observacionInvima);
                    registrosColYPrecol.add(obtenerNombreEstadoLocal(estadoCaso));
                    //************************************************
                    //************************************************
                    //************************************************
                    //************************************************
                    if (seModificoRegistroReporte == true)
                    {
                        contadorTecnoReporteEventos++;
                        arregloReportantes = agregarItemEmpresaReportante(arregloReportantes,registroEventoTemp.getIdips());
                    }
                    //************************************************
                    //************************************************
                    //************************************************
                    //************************************************
                }//Fin del for
            //*******************************************************************
            //*******************************************************************
            //*******************************************************************
            //logBeanWebReactivo.info ("*************************************************************");
            //logBeanWebReactivo.info ("*************************************************************");
            //logBeanWebReactivo.info ("TOTAL DE REGISTROS QUE FUERON RECHAZADOS: " + contadorTecnoReporteEventos);
            //logBeanWebReactivo.info ("*************************************************************");
            //logBeanWebReactivo.info ("*************************************************************");
            //*******************************************************************
            //*******************************************************************
            //*******************************************************************
            //*******************************************************************
            if (contadorTecnoReporteEventos == (registros.size()/5))
            {
                //**************************************************************
                //**************************************************************
                seRechazoPaqueteCompleto = true;
                //**************************************************************
                //**************************************************************
                logBeanWebReactivo.info ("***********************************************************");
                logBeanWebReactivo.info ("***********************************************************");
                logBeanWebReactivo.info ("VAMOS A GENERAR CORREO RECHAZO PRERDIVS REACTIVO A LAS " + new java.util.Date());
                logBeanWebReactivo.info ("***********************************************************");
                logBeanWebReactivo.info ("***********************************************************");
                enviarCorreoElectronicoNotificacionReporteTrimestralRDivsSecretariaRechazo(seRechazoPaqueteCompleto, this.getCorreoReportante(),
                fechaSistema,contadorTecnoReporteEventos,registrosColYPrecol);
                //**************************************************************
                //**************************************************************
                //logBeanWebReactivo.info ("GENERANDO CORREO DE COPIA PARA LAS EMPRESAS REPORANTE DE PRECOLES");
                //**************************************************************
                //**************************************************************
                if (arregloReportantes != null)
                {
                        //logBeanWebReactivo.info ("//////////////////////////////////////////////////////////////////////////////////////////////");
                        //logBeanWebReactivo.info ("//////////////////////////////////////////////////////////////////////////////////////////////");
                        for (i = 0;  i < arregloReportantes.size();  i++)
                        {
                            //*****************************************************************************
                            //*****************************************************************************
                            nitEmpresaCorreo = (String)arregloReportantes.get(i);
                            correoEmpresa = getServicioReporteTrimestral().obtenerCorreoEmpresaPorNIT(nitEmpresaCorreo);
                            nombreEmpresa = getServicioReporteTrimestral().obtenerNombreEmpresaIPSPorNit(nitEmpresaCorreo);
                            idRol = getServicioReporteTrimestral().obtenerIdRolUsuarioPorNIT(nitEmpresaCorreo);
                            //logBeanWebReactivo.info ("ID EMPRESA REPORTANTE PARA SACAR EL CORREO = " + nitEmpresaCorreo);
                            //logBeanWebReactivo.info ("EMAIL REPORTANTE PARA SACAR EL CORREO = " + correoEmpresa);
                            //logBeanWebReactivo.info ("EMPRESA NOMBRE Y NIT = " + nombreEmpresa);
                            //logBeanWebReactivo.info ("ID ROL = " + idRol);
                            //*****************************************************************************
                            //*****************************************************************************
                            if (nitEmpresaCorreo.equals("0"))
                            {
                                correoEmpresa = "reactivovigilancia@invima.gov.co";
                                nombreEmpresa = "INSTITUTO NACIONAL DE VIGILANCIA DE MEDICAMENTOS Y ALIMENTOS INVIMA";
                            }
                            //*****************************************************************************
                            //*****************************************************************************
                            enviarCorreoElectronicoNotificacionReporteTrimestralRDivsIPSRechazo(seRechazoPaqueteCompleto,correoEmpresa,
                            fechaSistema,contadorTecnoReporteEventos,registrosColYPrecol,nombreEmpresa,idRol);
                            //*****************************************************************************
                            //*****************************************************************************
                        }
                        //logBeanWebReactivo.info ("//////////////////////////////////////////////////////////////////////////////////////////////");
                        //logBeanWebReactivo.info ("//////////////////////////////////////////////////////////////////////////////////////////////");
                }
                //**************************************************************
                //**************************************************************
                //logBeanWebReactivo.info ("SE REALIZÓ COMPLETA LA MIGRACIÓN DE PRERDVIS A RDIVS = " + seRechazoPaqueteCompleto);
                mensajeSalida = "Se realizó exitosamente el rechazo de " + contadorTecnoReporteEventos + " registros de Reporte Masivo Trimestral con Evento de la Entidad Reportante";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proceso Exitoso",mensajeSalida));
            }
            else
            {
                //logBeanWebReactivo.info ("NO FUE POSIBLE EL RECHAZO DE LOS PRERDIVS = " + seRechazoPaqueteCompleto);
                //logBeanWebReactivo.info ("****************************************************************************************");
                //logBeanWebReactivo.info ("LIMPIANDO TABLAS ANTE CARGUE FALLIDO");
                //logBeanWebReactivo.info ("****************************************************************************************");
                mensajeSalida = "La Migración de Registros de Cargue Trimestral Masivo no pudo ser ejecutada para actualizar " + contadorTecnoReporteEventos + " registros";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error",mensajeSalida));
            }
            //*************************************************************
            //*************************************************************
            //*************************************************************
            listadoLocal = getServicioReporteTrimestral().buscarReportesMasivosTrimestralesPorIPS(this.getDepartamento(),null, null,null,null,null,this.getIdMunicipioSesion());
            this.setListadoPrerdivsParaAprobacion(organizarDatosCargue(listadoLocal));
            //logBeanWebReactivo.info ("TOTAL DE REGISTROS DEL REPORTE TRIMESTRAL CONSULTADOS LUEGO DE RECHAZOS = " + this.getListadoPrerdivsParaAprobacion().size());
            //*************************************************************
            //*************************************************************
            //*************************************************************
            RequestContext.getCurrentInstance().update("formularioBusquedaPrerdivs:datosReporte");
            //logBeanWebReactivo.info ("ACTUALICE LOS COMPONENTES EN LA VISTA DEL BUSCADOR DE PRERDIVS LUEGO DE LOS RECHAZOS");            
            //logBeanWebReactivo.info ("*************************************************************");
            //logBeanWebReactivo.info ("*************************************************************");
        }//Fin del try
        
        catch (Exception errorConversion)
        {
            errorConversion.printStackTrace();
        }
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public ArrayList<String> agregarItemEmpresaReportante (ArrayList<String> datos, String registro)
    {
        int i = 0;
        String idActual = "";
        boolean yaEsta = false;
        //*******************************************************
        //*******************************************************
        if (datos != null)
        {
            //*******************************************************
            //*******************************************************
            if (datos.size() == 0)
            {
                //logBeanWebReactivo.info ("INSERTADO ID DE EMPRESA POR PRIMERA VEZ");
                datos.add(registro);
            }
            else
            {
                //*******************************************
                //*******************************************
                //logBeanWebReactivo.info ("EVALUANDO PRESENCIA DEL ID " + registro + " EN EL VECTOR YA EXISTENTE = " + datos.toArray());
                //*******************************************
                //*******************************************
                yaEsta = false;
                for (i = 0;  i < datos.size();  i++)
                {
                    idActual = (String)datos.get(i);
                    
                    if (idActual.equals(registro))
                    {
                        //logBeanWebReactivo.info ("El registro " + registro + " ya esta insertado.  No se volverá a meter");
                        yaEsta = true;
                        break;
                    }
                }
                //*******************************************
                //*******************************************
                if (yaEsta == false)
                {
                    datos.add(registro);
                }
                //*******************************************
                //*******************************************
            }
            //*******************************************************
            //*******************************************************
        }
        
        return (datos);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void limpiarCamposGrilla()
    {
        int i = 0;
        ReportePreRDIVVO registro = null;
        
        try
        {
            //logBeanWebReactivo.info ("----------------------------------------------------->");
            //logBeanWebReactivo.info ("----------------------------------------------------->");
            //logBeanWebReactivo.info ("Ejecutando limpieza del Formulario");
            //****************************************************************
            //****************************************************************
            for (i = 0;  i < this.getListadoPrerdivsParaAprobacion().size();  i++)
            {
                registro = (ReportePreRDIVVO)this.getListadoPrerdivsParaAprobacion().get(i);
                //logBeanWebReactivo.info ("Limpiando campo precol = " + registro.getPrecol());
                registro.setEscogerPrecol(false);
            }
            //****************************************************************
            //****************************************************************
            //RequestContext.getCurrentInstance().update("datosReporte");
            //logBeanWebReactivo.info ("ACTUALICE LOS COMPONENTES EN LA VISTA PARA LA VALIDACION");            
            //logBeanWebReactivo.info ("----------------------------------------------------->");
            //logBeanWebReactivo.info ("----------------------------------------------------->");
            //****************************************************************
            //****************************************************************
        }
        
        catch (Exception errorconvertirPrerdivsEnCols)
        {
            //logBeanWebReactivo.info ("Error en el metodo convertirPrerdivsEnCols = " + errorconvertirPrerdivsEnCols.getLocalizedMessage());
            errorconvertirPrerdivsEnCols.printStackTrace();
        }
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void enviarCorreoElectronicoNotificacionReporteTrimestralRDivsSecretariaAprobacion (boolean resultado, String correoDestinatario, 
    java.util.Date fechaReporte, int totalRegistros, java.util.ArrayList<String> registrosColesInsertados)
    {
        ReactivoEventosTemp registroEventoTemporal;
        ReactivoPacienteTemp registroPacienteTemporal;
        ReactivoReportanteTemp registroReportanteTemporal;
        ReactivoGestionrealizadaTemp registroGestionRealizadaTemporal;
        ReactivoCamposcompTemp registroCamposComplementariosTemporal;
        ReactivoInstitucionTemp registroInstitucionTemporal;
        ReactivoProductoTemp registroProductoTemporal;        
        //***************************************************
        //***************************************************
        ReactivoEventos registroEvento;
        ReactivoPaciente registroPaciente;
        ReactivoReportante registroReportante;
        ReactivoGestionrealizada registroGestionRealizada;
        ReactivoCamposcomp registroCamposComplementarios;
        ReactivoInstitucion registroInstitucion;
        ReactivoProducto registroProducto;            
        //***************************************************
        //***************************************************
        String expedientec = "";
        //***************************************************
        //***************************************************
        //***************************************************
        //***************************************************
        try
        {
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //logBeanWebReactivo.info ("SE GENERARÁ EL MENSAJE PARA ENVIO A LA SECRETARIA DE SALUD APROBADORA");
            //**********************************************************************************************
            //**********************************************************************************************
            PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
            String servidor = pr.getProperty("servidor");
            String remitente = pr.getProperty("remitente");
            String usuario = pr.getProperty("usuario");
            String passwd = pr.getProperty("password");
            String fecha = Fecha.formateaFecha(fechaReporte, "dd/MM/yyyy hh:mm a");
            String mensajeCorreo = "";
            String idCol = "";
            String idPreCol = "";
            String tipReporte = "";
            String medida = "";
            String seguimiento = "";
            String caso = "";
            int i = 0;
            String nombreAdministrador = "";
            //**********************************************************************************************
            //**********************************************************************************************
            //**********************************************************************************************
            //**********************************************************************************************
            if (this.getNombreSecretariaSalud() != null)
            {
                nombreAdministrador = this.getNombreSecretariaSalud();
            }
            else
            {
                nombreAdministrador = "INSTITUTO NACIONAL DE VIGILANCIA DE MEDICAMENTOS Y ALIMENTOS";
            }
            //**********************************************************************************************
            //**********************************************************************************************
            if (resultado != false) 
            {
                //*********************************************************************************************************************
                //*********************************************************************************************************************
                mensajeCorreo = "<h1 style='font-family: verdana;'>SISTEMA DE GESTIÓN Y CONTROL DE REACTIVOS DE DIAGNÓSTICO IN VITRO REACTIVOVIGILANCIA</h1>" +
                                "<br/><br/>" +
                                "<b><h2 style='font-family: verdana;'>NOTIFICACIÓN DE APROBACIÓN DE REGISTROS DE CARGUE MASIVO TRIMESTRAL POR PARTE DE LA SECRETARÍA DE SALUD (" +  nombreAdministrador + ") Y/O INVIMA</h2></b><br/>" +
                                "<p style='font-family: verdana;'>Apreciado Usuario:  Se realizó la aprobación de los siguientes registros para el Sistema de ReactivoVigilancia: <br/>" +
                                "<br/><b> Fecha y hora del ingreso:</b>  " + fecha + 
                                "<br/><b> TOTAL DE REGISTROS CARGADOS EN EL SISTEMA:</b>  " + (totalRegistros) + "<br/><br/>";
                //****************************************************************************
                //****************************************************************************
                mensajeCorreo +=
                "<table id='tablaRegistrosCreados' cellpadding='0' cellspacing='0' border='1' style='font-family: verdana;'>" +
                "<tr>" +
                   "<td><b>Precol Temporal</b></td>" +
                   "<td><b>COL Asignado</b></td>" +
                   "<td><b>Departamento Reportante</b></td>" +
                   "<td><b>Municipio Reportante</b></td>" +
                   "<td><b>Fecha del Evento</b></td>" +
                   "<td><b>Nombre del Dispositivo</b></td>" +
                   "<td><b>Causa Probable</b></td>" +
                   "<td><b>Gestión Ente</b></td>" +
                   "<td><b>Seguimiento Invima</b></td>" +
                   "<td><b>Estado del Caso</b></td>" +
                "</tr>";
                //****************************************************************************
                //****************************************************************************
                for (i = 0;  i < registrosColesInsertados.size();  i+=6)
                {
                    idPreCol = (String)registrosColesInsertados.get(i); 
                    idCol = (String)registrosColesInsertados.get(i+1);
                    tipReporte = (String)registrosColesInsertados.get(i+2);
                    medida =  (String)registrosColesInsertados.get(i+3);
                    seguimiento =  (String)registrosColesInsertados.get(i+4);
                    caso = (String)registrosColesInsertados.get(i+5);
                    expedientec = getServicioReporteTrimestral().obtenerCodigoRegistroSanitarioPorCOL(idCol);
                    registroEventoTemporal = getServicioReporteTrimestral().consultarRegistroReactivoEventosTemporal(idPreCol);
                    registroPacienteTemporal = getServicioReporteTrimestral().consultarRegistroReactivoPacienteTemporal(idPreCol);
                    registroReportanteTemporal = getServicioReporteTrimestral().consultarRegistroReactivoReportanteTemporal(idPreCol);
                    registroGestionRealizadaTemporal = getServicioReporteTrimestral().consultarRegistroReactivoGestionRealizadaTemporal(idPreCol);
                    registroCamposComplementariosTemporal = getServicioReporteTrimestral().consultarRegistroReactivoCamposComplementariosTemporal(idPreCol);
                    registroInstitucionTemporal = getServicioReporteTrimestral().consultarRegistroReactivoInstitucionTemporal(idPreCol);
                    registroProductoTemporal = getServicioReporteTrimestral().consultarRegistroReactivoProductoTemporal(idPreCol);
                    mensajeCorreo +=
                    "<tr>" +
                    "<td>" + idPreCol + "</td>" +
                    "<td>" + idCol + "</td>" +
                    "<td>" + registroReportanteTemporal.getCodDepart() + " - " + getServicioReporteTrimestral().obtenerNombreDeptoPorCodigo(registroReportanteTemporal.getCodDepart()) + "</td>" +
                    "<td>" + registroReportanteTemporal.getCodMun() + " - " + getServicioReporteTrimestral().obtenerNombreMunicipioPorCodigo(registroReportanteTemporal.getCodMun()) + "</td>" +
                    "<td>" + registroEventoTemporal.getFechEvento() + "</td>" +
                    "<td>" + registroProductoTemporal.getNombreReactivo() + "</td>" +
                    "<td>" + tipReporte + "</td>" +
                    "<td>" + medida + "</td>" +
                    "<td>" + seguimiento + "</td>" +
                    "<td>" + caso + "</td>" +
                    "</tr>";
                }
                mensajeCorreo +=
                "</table> <br/><br/><br/>";
                mensajeCorreo +=
                "<p style='font-family: verdana;'>Agradecemos su atención, su notificación es fundamental para fortalecer la vigilancia post-mercado de los Reactivos de Diagnóstico in Vitro en el país, en beneficio de la seguridad del paciente.</p><br/><br/>" +
                "<p style='font-family: verdana;'><b>Lo anterior, en cumplimiento a lo establecido en la Resolución 2020007532 del 28 de febrero del 2020 \"Por la cual se implementa el Programa Nacional de ReactivoVigilancia\"</b></p> <br/><br/>" +
                "<p style='font-family: verdana;'>Nota: Síguenos en Nuestro Fan Page  <b><a href='https://www.facebook.com/RedNacionaldeReactivoVigilancia/'>https://www.facebook.com/RedNacionaldeReactivoVigilancia/</a></b></p>" +        
                "<p style='font-family: verdana;'>Cordialmente</p>" +
                "<br/><br/><br/>" +
                "<p style='font-family: verdana;'><b>GRUPO DE REACTIVOVIGILANCIA<br/>" +
                "Dirección de Dispositivos Médicos y otras Tecnologías<br/>" +
                "INVIMA<br/>" +
                "Teléfono: (1)2948700 Ext. 3880<br/>" +
                "<a href='mailto:reactivovigilancia@invima.gov.co'>reactivovigilancia@invima.gov.co</a><b></p>";
                
                CorreoElectronico correo = CorreoElectronico.getInstance();
                correo.enviarCorreoElectronicoReactivo(correoDestinatario,"",
                        "Aprobación de reporte masivo trimestral ReactivoVigilancia", 
                        HtmlValidator.remplazarTildesEnTexto(mensajeCorreo),userSesion,"Aprobación de reporte masivo trimestral -1");
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
    public void enviarCorreoElectronicoNotificacionReporteTrimestralRDivsIPSAprobacion (boolean resultado, String correoDestinatario, 
    java.util.Date fechaReporte, int totalRegistros, java.util.ArrayList<String> registrosColesInsertados, 
    String nombreIPS, String idRolUsuario)
    {
        //***************************************************
        //***************************************************
        //***************************************************
        //***************************************************
        ReactivoEventosTemp registroEventoTemporal;
        ReactivoPacienteTemp registroPacienteTemporal;
        ReactivoReportanteTemp registroReportanteTemporal;
        ReactivoGestionrealizadaTemp registroGestionRealizadaTemporal;
        ReactivoCamposcompTemp registroCamposComplementariosTemporal;
        ReactivoInstitucionTemp registroInstitucionTemporal;
        ReactivoProductoTemp registroProductoTemporal;        
        //***************************************************
        //***************************************************
        ReactivoEventos registroEvento;
        ReactivoPaciente registroPaciente;
        ReactivoReportante registroReportante;
        ReactivoGestionrealizada registroGestionRealizada;
        ReactivoCamposcomp registroCamposComplementarios;
        ReactivoInstitucion registroInstitucion;
        ReactivoProducto registroProducto;            
        //***************************************************
        //***************************************************
        String expedientec = "";
        //***************************************************
        //***************************************************
        //***************************************************
        //***************************************************
        try
        {
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //logBeanWebReactivo.info ("SE GENERARÁ EL MENSAJE PARA ENVIO A LA IPS REPORTANTE");
            //**********************************************************************************************
            //**********************************************************************************************
            PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
            String servidor = pr.getProperty("servidor");
            String remitente = pr.getProperty("remitente");
            String usuario = pr.getProperty("usuario");
            String passwd = pr.getProperty("password");
            String fecha = Fecha.formateaFecha(fechaReporte, "dd/MM/yyyy hh:mm a");
            String encabezado = "";
            String mensajeCorreo = "";
            String idCol = "";
            String idPreCol = "";
            String tipReporte = "";
            String medida = "";
            String seguimiento = "";
            String caso = "";
            int i = 0;
            String nombreAdministrador = "";
            //**********************************************************************************************
            //**********************************************************************************************
            if (this.getNombreSecretariaSalud() != null)
            {
                nombreAdministrador = this.getNombreSecretariaSalud();
            }
            else
            {
                nombreAdministrador = "INSTITUTO NACIONAL DE VIGILANCIA DE MEDICAMENTOS Y ALIMENTOS";
            }
            //**********************************************************************************************
            //**********************************************************************************************
            if (idRolUsuario.equals("2"))
            {
                //logBeanWebReactivo.info ("SETEANDO ENCABEZADO PARA IMPORTADOR");
                encabezado = "<b><h2 style='font-family: verdana;'>NOTIFICACIÓN A LA ENTIDAD REPORTANTE DE LA APROBACIÓN DE REGISTROS DE CARGUE MASIVO TRIMESTRAL POR PARTE DEL INVIMA</h2></b><br/>";
            }
            else
            if (idRolUsuario.equals("3") || idRolUsuario.equals("5"))
            {
                //logBeanWebReactivo.info ("SETEANDO ENCABEZADO PARA PRESTADOR DE SALUD");
                encabezado = "<b><h2 style='font-family: verdana;'>NOTIFICACIÓN A LA ENTIDAD REPORTANTE DE LA APROBACIÓN DE REGISTROS DE CARGUE MASIVO TRIMESTRAL POR PARTE:  " + nombreAdministrador + "</h2></b><br/>";
            }
            //**********************************************************************************************
            //**********************************************************************************************
            //**********************************************************************************************
            //**********************************************************************************************
            if (resultado != false) 
            {
                //*********************************************************************************************************************
                //*********************************************************************************************************************
                mensajeCorreo = "<h1 style='font-family: verdana;'>SISTEMA DE GESTIÓN Y CONTROL DE REACTIVOS DE DIAGNÓSTICO IN VITRO REACTIVOVIGILANCIA</h1>" +
                                "<br/><br/>" +
                                encabezado +
                                "<p style='font-family: verdana;'>Apreciado Usuario:  Se realizó la aprobación de los siguientes registros para el Sistema de ReactivoVigilancia: <br/>" +
                                "<br/><b>Fecha y hora del ingreso:</b>  " + fecha + 
                                "<br/><b>TOTAL DE REGISTROS CARGADOS EN EL SISTEMA:</b>  " + (totalRegistros) + "<br/><br/>" +
                                "<br/><b>NOMBRE REPORTANTE: </b>" + nombreIPS + "<br/><br/>";
                //****************************************************************************
                //****************************************************************************
                mensajeCorreo +=
                "<table id='tablaRegistrosCreados' cellpadding='0' cellspacing='0' border='1' style='font-family: verdana;'>" +
                "<tr>" +
                   "<td><b>Precol Temporal</b></td>" +
                   "<td><b>COL Asignado</b></td>" +
                   "<td><b>Departamento Reportante</b></td>" +
                   "<td><b>Municipio Reportante</b></td>" +
                   "<td><b>Fecha del Evento</b></td>" +
                   "<td><b>Nombre del Dispositivo</b></td>" +
                   "<td><b>Causa Probable</b></td>" +
                   "<td><b>Gestión Ente</b></td>" +
                   "<td><b>Seguimiento Invima</b></td>" +
                   "<td><b>Estado del Caso</b></td>" +
                "</tr>";
                //****************************************************************************
                //****************************************************************************
                for (i = 0;  i < registrosColesInsertados.size();  i+=6)
                {
                    idPreCol = (String)registrosColesInsertados.get(i); 
                    idCol = (String)registrosColesInsertados.get(i+1);
                    tipReporte = (String)registrosColesInsertados.get(i+2);
                    medida =  (String)registrosColesInsertados.get(i+3);
                    seguimiento =  (String)registrosColesInsertados.get(i+4);
                    caso = (String)registrosColesInsertados.get(i+5);
                    //******************************************************
                    //******************************************************
                    //expedientec = getServicioReporteTrimestral().obtenerCodigoRegistroSanitarioPorPrecol(idPreCol);
                    //logBeanWebReactivo.info ("PRECOL = " + idPreCol + " - EXPEDIENTE = " + expedientec);
                    expedientec = getServicioReporteTrimestral().obtenerCodigoRegistroSanitarioPorCOL(idCol);
                    //logBeanWebReactivo.info ("COL = " + idCol + " - EXPEDIENTE = " + expedientec);
                    //******************************************************
                    //******************************************************
                    registroEventoTemporal = getServicioReporteTrimestral().consultarRegistroReactivoEventosTemporal(idPreCol);
                    registroPacienteTemporal = getServicioReporteTrimestral().consultarRegistroReactivoPacienteTemporal(idPreCol);
                    registroReportanteTemporal = getServicioReporteTrimestral().consultarRegistroReactivoReportanteTemporal(idPreCol);
                    registroGestionRealizadaTemporal = getServicioReporteTrimestral().consultarRegistroReactivoGestionRealizadaTemporal(idPreCol);
                    registroCamposComplementariosTemporal = getServicioReporteTrimestral().consultarRegistroReactivoCamposComplementariosTemporal(idPreCol);
                    registroInstitucionTemporal = getServicioReporteTrimestral().consultarRegistroReactivoInstitucionTemporal(idPreCol);
                    registroProductoTemporal = getServicioReporteTrimestral().consultarRegistroReactivoProductoTemporal(idPreCol);
                    //******************************************************
                    //******************************************************
                    //******************************************************
                    //******************************************************
                    mensajeCorreo +=
                    "<tr>" +
                    "<td>" + idPreCol + "</td>" +
                    "<td>" + idCol + "</td>" +
                    "<td>" + registroReportanteTemporal.getCodDepart() + " - " + getServicioReporteTrimestral().obtenerNombreDeptoPorCodigo(registroReportanteTemporal.getCodDepart()) + "</td>" +
                    "<td>" + registroReportanteTemporal.getCodMun() + " - " + getServicioReporteTrimestral().obtenerNombreMunicipioPorCodigo(registroReportanteTemporal.getCodMun()) + "</td>" +
                    "<td>" + registroEventoTemporal.getFechEvento() + "</td>" +
                    "<td>" + registroProductoTemporal.getNombreReactivo() + "</td>" +
                    "<td>" + tipReporte + "</td>" +
                    "<td>" + medida + "</td>" +
                    "<td>" + seguimiento + "</td>" +
                    "<td>" + caso + "</td>" +
                    "</tr>";
                    //******************************************************
                    //******************************************************
                    //******************************************************
                    //******************************************************
                }//Fin del for de la tabla de correo
                //****************************************************************************
                //****************************************************************************
                mensajeCorreo +=
                "</table> <br/><br/><br/>";
                //****************************************************************************
                //****************************************************************************
                mensajeCorreo +=
                "<p style='font-family: verdana;'>Agradecemos su atención, su notificación es fundamental para fortalecer la vigilancia post-mercado de los Reactivos de Diagnóstico in Vitro en el país, en beneficio de la seguridad del paciente.</p><br/><br/>" +
                "<p style='font-family: verdana;'><b>Lo anterior, en cumplimiento a lo establecido en la Resolución 2020007532 del 28 de febrero del 2020 \"Por la cual se implementa el Programa Nacional de ReactivoVigilancia\"</b></p> <br/><br/>" +
                "<p style='font-family: verdana;'>Nota: Síguenos en Nuestro Fan Page  <b><a href='https://www.facebook.com/RedNacionaldeReactivoVigilancia/'>https://www.facebook.com/RedNacionaldeReactivoVigilancia/</a></b></p>" +        
                "<p style='font-family: verdana;'>Cordialmente</p>" +
                "<br/><br/><br/>" +
                "<p style='font-family: verdana;'><b>GRUPO DE REACTIVOVIGILANCIA<br/>" +
                "Dirección de Dispositivos Médicos y otras Tecnologías<br/>" +
                "INVIMA<br/>" +
                "Teléfono: (1)2948700 Ext. 3880<br/>" +
                "<a href='mailto:reactivovigilancia@invima.gov.co'>reactivovigilancia@invima.gov.co</a><b></p>";
                //****************************************************************************
                //****************************************************************************
                //****************************************************************************
                //****************************************************************************
                //****************************************************************************
                //****************************************************************************
                //logBeanWebReactivo.info ("MENSAJE A ENVIAR = " + mensajeCorreo);
                //****************************************************************************
                //****************************************************************************
                CorreoElectronico correo = CorreoElectronico.getInstance();
                //logBeanWebReactivo.info ("CORREO DEL DESTINATARIO = " + correoDestinatario);
                //logBeanWebReactivo.info ("**********************************************************************************");
                //logBeanWebReactivo.info ("**********************************************************************************");
                //logBeanWebReactivo.info ("ENVIANDO CORREO ELECTRONICO DE NOTIFICACIÓN");
                //Titulo modificado:  Notificación a IPS Reportante de la Aprobación de reporte masivo trimestral ReactivoVigilancia
                correo.enviarCorreoElectronicoReactivo(correoDestinatario,"","Aprobación de reporte masivo trimestral ReactivoVigilancia",
                        HtmlValidator.remplazarTildesEnTexto(mensajeCorreo),userSesion,"Aprobación de reporte masivo trimestral -1");
                //logBeanWebReactivo.info ("**********************************************************************************");
                //logBeanWebReactivo.info ("**********************************************************************************");
                //logBeanWebReactivo.info ("EL CORREO ELECTRÓNICO PARA EL REPORTANTE FUE ENVIADO INFORMANDO DEL CARGUE MASIVO TRIMESTRAL");
                //*********************************************************************************************************************
                //*********************************************************************************************************************
            }
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //**********************************************************************************************
            //**********************************************************************************************
        }
        
        catch (Exception errorEnvioCorreo)
        {
            errorEnvioCorreo.printStackTrace();
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
    public void enviarCorreoElectronicoNotificacionReporteTrimestralRDivsSecretariaRechazo (boolean resultado, String correoDestinatario, 
    java.util.Date fechaReporte, int totalRegistros, java.util.ArrayList<String> registrosColesInsertados)
    {
        ReactivoEventosTemp registroEventoTemporal;
        ReactivoPacienteTemp registroPacienteTemporal;
        ReactivoReportanteTemp registroReportanteTemporal;
        ReactivoGestionrealizadaTemp registroGestionRealizadaTemporal;
        ReactivoCamposcompTemp registroCamposComplementariosTemporal;
        ReactivoInstitucionTemp registroInstitucionTemporal;
        ReactivoProductoTemp registroProductoTemporal;        
        //***************************************************
        //***************************************************
        ReactivoEventos registroEvento;
        ReactivoPaciente registroPaciente;
        ReactivoReportante registroReportante;
        ReactivoGestionrealizada registroGestionRealizada;
        ReactivoCamposcomp registroCamposComplementarios;
        ReactivoInstitucion registroInstitucion;
        ReactivoProducto registroProducto;            
        //***************************************************
        //***************************************************
        String expedientec = "";
        //***************************************************
        //***************************************************
        //***************************************************
        //***************************************************
        try
        {
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //logBeanWebReactivo.info ("SE GENERARÁ EL MENSAJE PARA ENVIO A LA SECRETARIA DE SALUD APROBADORA INFORMANDO EL RECHAZO DE PREDIVS");
            //**********************************************************************************************
            //**********************************************************************************************
            PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
            String servidor = pr.getProperty("servidor");
            String remitente = pr.getProperty("remitente");
            String usuario = pr.getProperty("usuario");
            String passwd = pr.getProperty("password");
            String fecha = Fecha.formateaFecha(fechaReporte, "dd/MM/yyyy hh:mm a");
            String mensajeCorreo = "";
            String idCol = "";
            String idPreCol = "";
            String tipReporte = "";
            String medida = "";
            String seguimiento = "";
            String caso = "";
            int i = 0;
            String nombreAdministrador = "";
            //**********************************************************************************************
            //**********************************************************************************************
            //**********************************************************************************************
            //**********************************************************************************************
            if (this.getNombreSecretariaSalud() != null)
            {
                nombreAdministrador = this.getNombreSecretariaSalud();
            }
            else
            {
                nombreAdministrador = "INSTITUTO NACIONAL DE VIGILANCIA DE MEDICAMENTOS Y ALIMENTOS";
            }
            //**********************************************************************************************
            //**********************************************************************************************
            if (resultado != false) 
            {
                //*********************************************************************************************************************
                //*********************************************************************************************************************
                mensajeCorreo = "<h1 style='font-family: verdana;'>SISTEMA DE GESTIÓN Y CONTROL DE REACTIVOS DE DIAGNÓSTICO IN VITRO REACTIVOVIGILANCIA</h1>" +
                                "<br/><br/>" +
                                "<b><h2 style='font-family: verdana;'>NOTIFICACIÓN DE RECHAZO DE REGISTROS DE CARGUE MASIVO TRIMESTRAL POR PARTE DE LA SECRETARÍA DE SALUD (" +  nombreAdministrador + ") Y/O INVIMA</h2></b><br/>" +
                                "<p style='font-family: verdana;'>Apreciado Usuario:  Se realizó la aprobación de los siguientes registros para el Sistema de ReactivoVigilancia: <br/>" +
                                "<br/><b> Fecha y hora del ingreso:</b>  " + fecha + 
                                "<br/><b> TOTAL DE REGISTROS RECHAZADOS POR EL SISTEMA:</b>  " + (totalRegistros) + "<br/><br/>";
                //****************************************************************************
                //****************************************************************************
                mensajeCorreo +=
                "<table id='tablaRegistrosCreados' cellpadding='0' cellspacing='0' border='1' style='font-family: verdana;'>" +
                "<tr>" +
                   "<td><b>Precol Temporal</b></td>" +
                   "<td><b>COL Asignado</b></td>" +
                   "<td><b>Departamento Reportante</b></td>" +
                   "<td><b>Municipio Reportante</b></td>" +
                   "<td><b>Fecha del Evento</b></td>" +
                   "<td><b>Nombre del Dispositivo</b></td>" +
                   "<td><b>Causa Probable</b></td>" +
                   "<td><b>Gestión Ente</b></td>" +
                   "<td><b>Seguimiento Invima</b></td>" +
                   "<td><b>Estado del Caso</b></td>" +
                "</tr>";
                //****************************************************************************
                //****************************************************************************
                for (i = 0;  i < registrosColesInsertados.size();  i+=6)
                {
                    idPreCol = (String)registrosColesInsertados.get(i); 
                    idCol = (String)registrosColesInsertados.get(i+1);
                    tipReporte = (String)registrosColesInsertados.get(i+2);
                    medida =  (String)registrosColesInsertados.get(i+3);
                    seguimiento =  (String)registrosColesInsertados.get(i+4);
                    caso = (String)registrosColesInsertados.get(i+5);
                    //******************************************************
                    //******************************************************
                    //expedientec = getServicioReporteTrimestral().obtenerCodigoRegistroSanitarioPorPrecol(idPreCol);
                    //logBeanWebReactivo.info ("PRECOL = " + idPreCol + " - EXPEDIENTE = " + expedientec);
                    expedientec = getServicioReporteTrimestral().obtenerCodigoRegistroSanitarioPorCOL(idCol);
                    //logBeanWebReactivo.info ("COL = " + idCol + " - EXPEDIENTE = " + expedientec);
                    //******************************************************
                    //******************************************************
                    registroEventoTemporal = getServicioReporteTrimestral().consultarRegistroReactivoEventosTemporal(idPreCol);
                    registroPacienteTemporal = getServicioReporteTrimestral().consultarRegistroReactivoPacienteTemporal(idPreCol);
                    registroReportanteTemporal = getServicioReporteTrimestral().consultarRegistroReactivoReportanteTemporal(idPreCol);
                    registroGestionRealizadaTemporal = getServicioReporteTrimestral().consultarRegistroReactivoGestionRealizadaTemporal(idPreCol);
                    registroCamposComplementariosTemporal = getServicioReporteTrimestral().consultarRegistroReactivoCamposComplementariosTemporal(idPreCol);
                    registroInstitucionTemporal = getServicioReporteTrimestral().consultarRegistroReactivoInstitucionTemporal(idPreCol);
                    registroProductoTemporal = getServicioReporteTrimestral().consultarRegistroReactivoProductoTemporal(idPreCol);
                    //******************************************************
                    //******************************************************
                    //******************************************************
                    //******************************************************
                    mensajeCorreo +=
                    "<tr>" +
                    "<td>" + idPreCol + "</td>" +
                    "<td>" + idCol + "</td>" +
                    "<td>" + registroReportanteTemporal.getCodDepart() + " - " + getServicioReporteTrimestral().obtenerNombreDeptoPorCodigo(registroReportanteTemporal.getCodDepart()) + "</td>" +
                    "<td>" + registroReportanteTemporal.getCodMun() + " - " + getServicioReporteTrimestral().obtenerNombreMunicipioPorCodigo(registroReportanteTemporal.getCodMun()) + "</td>" +
                    "<td>" + registroEventoTemporal.getFechEvento() + "</td>" +
                    "<td>" + registroProductoTemporal.getNombreReactivo() + "</td>" +
                    "<td>" + tipReporte + "</td>" +
                    "<td>" + medida + "</td>" +
                    "<td>" + seguimiento + "</td>" +
                    "<td>" + caso + "</td>" +
                    "</tr>";
                    //******************************************************
                    //******************************************************
                    //******************************************************
                    //******************************************************
                    //OJO:  ES POSIBLE QUE QUE SE REQUIERA REACTIVAR EL BORRADO
                    
                    /*
                    //logBeanWebReactivo.info ("ELIMINANDO PRECOL YA NO NECESARIO = " + idPreCol);
                    if (regPrecolTecnoReporteEventosTempC != null)
                    {
                        //logBeanWebReactivo.info ("ELIMINANDO REGISTRO TEMPORAL DE regPrecolTecnoReporteEventosTempC = " + idPreCol);
                        getServicioReporteTrimestral().eliminarRegistroTecnoReporteEventos(regPrecolTecnoReporteEventosTempC,idPreCol);
                    }
                    
                    if (regPrecolTecnoDispositivoTempC != null)
                    {
                        //logBeanWebReactivo.info ("ELIMINANDO REGISTRO TEMPORAL DE regPrecolTecnoDispositivoTempC = " + idPreCol);
                        getServicioReporteTrimestral().eliminarRegistroTecnoDispositivo(regPrecolTecnoDispositivoTempC,idPreCol);
                    }
                    
                    if (regPrecolTecnoPacienteTempC != null)
                    {
                        //logBeanWebReactivo.info ("ELIMINANDO REGISTRO TEMPORAL DE regPrecolTecnoPacienteTempC = " + idPreCol);
                        getServicioReporteTrimestral().eliminarRegistroTecnoPaciente(regPrecolTecnoPacienteTempC,idPreCol);
                    }
                    
                    if (regPrecolTecnoEvaluacionCasoTempC != null)
                    {
                        //logBeanWebReactivo.info ("ELIMINANDO REGISTRO TEMPORAL DE regPrecolTecnoEvaluacionCasoTempC = " + idPreCol);
                        getServicioReporteTrimestral().eliminarRegistroTecnoEvaluacionCaso(regPrecolTecnoEvaluacionCasoTempC,idPreCol);
                    }
                    */
                    //******************************************************
                    //******************************************************
                    //******************************************************
                    //******************************************************
                }//Fin del for de la tabla de correo
                //****************************************************************************
                //****************************************************************************
                mensajeCorreo +=
                "</table> <br/><br/><br/>";
                //****************************************************************************
                //****************************************************************************
                mensajeCorreo +=
                "<p style='font-family: verdana;'>Agradecemos su atención, su notificación es fundamental para fortalecer la vigilancia post-mercado de los Reactivos de Diagnóstico in Vitro en el país, en beneficio de la seguridad del paciente.</p><br/><br/>" +
                "<p style='font-family: verdana;'><b>Lo anterior, en cumplimiento a lo establecido en la Resolución 2020007532 del 28 de febrero del 2020 \"Por la cual se implementa el Programa Nacional de ReactivoVigilancia\"</b></p> <br/><br/>" +
                "<p style='font-family: verdana;'>Nota: Síguenos en Nuestro Fan Page  <b><a href='https://www.facebook.com/RedNacionaldeReactivoVigilancia/'>https://www.facebook.com/RedNacionaldeReactivoVigilancia/</a></b></p>" +        
                "<p style='font-family: verdana;'>Cordialmente</p>" +
                "<br/><br/><br/>" +
                "<p style='font-family: verdana;'><b>GRUPO DE REACTIVOVIGILANCIA<br/>" +
                "Dirección de Dispositivos Médicos y otras Tecnologías<br/>" +
                "INVIMA<br/>" +
                "Teléfono: (1)2948700 Ext. 3880<br/>" +
                "<a href='mailto:reactivovigilancia@invima.gov.co'>reactivovigilancia@invima.gov.co</a><b></p>";
                //****************************************************************************
                //****************************************************************************
                //****************************************************************************
                //****************************************************************************
                //****************************************************************************
                //****************************************************************************
                //logBeanWebReactivo.info ("MENSAJE A ENVIAR = " + mensajeCorreo);
                //****************************************************************************
                //****************************************************************************
                CorreoElectronico correo = CorreoElectronico.getInstance();
                //logBeanWebReactivo.info ("CORREO DEL DESTINATARIO = " + correoDestinatario);
                //logBeanWebReactivo.info ("**********************************************************************************");
                //logBeanWebReactivo.info ("**********************************************************************************");
                //logBeanWebReactivo.info ("ENVIANDO CORREO ELECTRONICO DE NOTIFICACIÓN");
                correo.enviarCorreoElectronicoReactivo(correoDestinatario,"","Aprobación de reporte masivo trimestral ReactivoVigilancia",
                        HtmlValidator.remplazarTildesEnTexto(mensajeCorreo),userSesion,"Aprobación de reporte masivo trimestral -1");
                //logBeanWebReactivo.info ("**********************************************************************************");
                //logBeanWebReactivo.info ("**********************************************************************************");
                //logBeanWebReactivo.info ("EL CORREO ELECTRÓNICO PARA EL REPORTANTE FUE ENVIADO INFORMANDO DEL CARGUE MASIVO TRIMESTRAL");
                //*********************************************************************************************************************
                //*********************************************************************************************************************
            }
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //**********************************************************************************************
            //**********************************************************************************************
        }
        
        catch (Exception errorEnvioCorreo)
        {
            errorEnvioCorreo.printStackTrace();
        }
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void enviarCorreoElectronicoNotificacionReporteTrimestralRDivsIPSRechazo (boolean resultado, String correoDestinatario, 
    java.util.Date fechaReporte, int totalRegistros, java.util.ArrayList<String> registrosColesInsertados, 
    String nombreIPS, String idRolUsuario)
    {
        //***************************************************
        //***************************************************
        //***************************************************
        //***************************************************
        ReactivoEventosTemp registroEventoTemporal;
        ReactivoPacienteTemp registroPacienteTemporal;
        ReactivoReportanteTemp registroReportanteTemporal;
        ReactivoGestionrealizadaTemp registroGestionRealizadaTemporal;
        ReactivoCamposcompTemp registroCamposComplementariosTemporal;
        ReactivoInstitucionTemp registroInstitucionTemporal;
        ReactivoProductoTemp registroProductoTemporal;        
        //***************************************************
        //***************************************************
        ReactivoEventos registroEvento;
        ReactivoPaciente registroPaciente;
        ReactivoReportante registroReportante;
        ReactivoGestionrealizada registroGestionRealizada;
        ReactivoCamposcomp registroCamposComplementarios;
        ReactivoInstitucion registroInstitucion;
        ReactivoProducto registroProducto;            
        //***************************************************
        //***************************************************
        String expedientec = "";
        //***************************************************
        //***************************************************
        //***************************************************
        //***************************************************
        try
        {
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //logBeanWebReactivo.info ("SE GENERARÁ EL MENSAJE PARA ENVIO A LA IPS REPORTANTE INFORMANDO DEL RECHAZO DE PREDIVS");
            //**********************************************************************************************
            //**********************************************************************************************
            PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
            String servidor = pr.getProperty("servidor");
            String remitente = pr.getProperty("remitente");
            String usuario = pr.getProperty("usuario");
            String passwd = pr.getProperty("password");
            String fecha = Fecha.formateaFecha(fechaReporte, "dd/MM/yyyy hh:mm a");
            String encabezado = "";
            String mensajeCorreo = "";
            String idCol = "";
            String idPreCol = "";
            String tipReporte = "";
            String medida = "";
            String seguimiento = "";
            String caso = "";
            int i = 0;
            String nombreAdministrador = "";
            //**********************************************************************************************
            //**********************************************************************************************
            if (this.getNombreSecretariaSalud() != null)
            {
                nombreAdministrador = this.getNombreSecretariaSalud();
            }
            else
            {
                nombreAdministrador = "INSTITUTO NACIONAL DE VIGILANCIA DE MEDICAMENTOS Y ALIMENTOS";
            }
            //**********************************************************************************************
            //**********************************************************************************************
            if (idRolUsuario.equals("2"))
            {
                //logBeanWebReactivo.info ("SETEANDO ENCABEZADO PARA IMPORTADOR");
                encabezado = "<b><h2 style='font-family: verdana;'>NOTIFICACIÓN A LA ENTIDAD REPORTANTE DEL RECHAZO DE REGISTROS DE CARGUE MASIVO TRIMESTRAL POR PARTE DEL INVIMA</h2></b><br/>";
            }
            else
            if (idRolUsuario.equals("3") || idRolUsuario.equals("5"))
            {
                //logBeanWebReactivo.info ("SETEANDO ENCABEZADO PARA PRESTADOR DE SALUD");
                encabezado = "<b><h2 style='font-family: verdana;'>NOTIFICACIÓN A LA ENTIDAD REPORTANTE DEL RECHAZO DE REGISTROS DE CARGUE MASIVO TRIMESTRAL POR PARTE:  " + nombreAdministrador + "</h2></b><br/>";
            }
            //**********************************************************************************************
            //**********************************************************************************************
            //**********************************************************************************************
            //**********************************************************************************************
            if (resultado != false) 
            {
                //*********************************************************************************************************************
                //*********************************************************************************************************************
                mensajeCorreo = "<h1 style='font-family: verdana;'>SISTEMA DE GESTIÓN Y CONTROL DE REACTIVOS DE DIAGNÓSTICO IN VITRO REACTIVOVIGILANCIA</h1>" +
                                "<br/><br/>" +
                                encabezado +
                                "<b><h2 style='font-family: verdana;'>NOTIFICACIÓN DE RECHAZO DE REGISTROS DE CARGUE MASIVO TRIMESTRAL POR PARTE DE LA SECRETARÍA DE SALUD (" +  nombreAdministrador + ") Y/O INVIMA</h2></b><br/>" +
                                "<p style='font-family: verdana;'>Apreciado Usuario:  Se realizó el rechazo de los siguientes registros para el Sistema de ReactivoVigilancia: <br/>" +
                                "<br/><b>Fecha y hora del ingreso:</b>  " + fecha + 
                                "<br/><b> TOTAL DE REGISTROS RECHAZADOS POR EL SISTEMA:</b>  " + (totalRegistros) + "<br/><br/>" +
                                "<br/><b>NOMBRE REPORTANTE: </b>" + nombreIPS + "<br/><br/>";
                //****************************************************************************
                //****************************************************************************
                mensajeCorreo +=
                "<table id='tablaRegistrosCreados' cellpadding='0' cellspacing='0' border='1' style='font-family: verdana;'>" +
                "<tr>" +
                   "<td><b>Precol Temporal</b></td>" +
                   "<td><b>COL Asignado</b></td>" +
                   "<td><b>Departamento Reportante</b></td>" +
                   "<td><b>Municipio Reportante</b></td>" +
                   "<td><b>Fecha del Evento</b></td>" +
                   "<td><b>Nombre del Dispositivo</b></td>" +
                   "<td><b>Causa Probable</b></td>" +
                   "<td><b>Gestión Ente</b></td>" +
                   "<td><b>Seguimiento Invima</b></td>" +
                   "<td><b>Estado del Caso</b></td>" +
                "</tr>";
                //****************************************************************************
                //****************************************************************************
                for (i = 0;  i < registrosColesInsertados.size();  i+=6)
                {
                    idPreCol = (String)registrosColesInsertados.get(i); 
                    idCol = (String)registrosColesInsertados.get(i+1);
                    tipReporte = (String)registrosColesInsertados.get(i+2);
                    medida =  (String)registrosColesInsertados.get(i+3);
                    seguimiento =  (String)registrosColesInsertados.get(i+4);
                    caso = (String)registrosColesInsertados.get(i+5);
                    //******************************************************
                    //******************************************************
                    //expedientec = getServicioReporteTrimestral().obtenerCodigoRegistroSanitarioPorPrecol(idPreCol);
                    //logBeanWebReactivo.info ("PRECOL = " + idPreCol + " - EXPEDIENTE = " + expedientec);
                    expedientec = getServicioReporteTrimestral().obtenerCodigoRegistroSanitarioPorCOL(idCol);
                    //logBeanWebReactivo.info ("COL = " + idCol + " - EXPEDIENTE = " + expedientec);
                    //******************************************************
                    //******************************************************
                    //******************************************************
                    //******************************************************
                    registroEventoTemporal = getServicioReporteTrimestral().consultarRegistroReactivoEventosTemporal(idPreCol);
                    registroPacienteTemporal = getServicioReporteTrimestral().consultarRegistroReactivoPacienteTemporal(idPreCol);
                    registroReportanteTemporal = getServicioReporteTrimestral().consultarRegistroReactivoReportanteTemporal(idPreCol);
                    registroGestionRealizadaTemporal = getServicioReporteTrimestral().consultarRegistroReactivoGestionRealizadaTemporal(idPreCol);
                    registroCamposComplementariosTemporal = getServicioReporteTrimestral().consultarRegistroReactivoCamposComplementariosTemporal(idPreCol);
                    registroInstitucionTemporal = getServicioReporteTrimestral().consultarRegistroReactivoInstitucionTemporal(idPreCol);
                    registroProductoTemporal = getServicioReporteTrimestral().consultarRegistroReactivoProductoTemporal(idPreCol);
                    //******************************************************
                    //******************************************************
                    //******************************************************
                    //******************************************************
                    mensajeCorreo +=
                    "<tr>" +
                    "<td>" + idPreCol + "</td>" +
                    "<td>" + idCol + "</td>" +
                    "<td>" + registroReportanteTemporal.getCodDepart() + " - " + getServicioReporteTrimestral().obtenerNombreDeptoPorCodigo(registroReportanteTemporal.getCodDepart()) + "</td>" +
                    "<td>" + registroReportanteTemporal.getCodMun() + " - " + getServicioReporteTrimestral().obtenerNombreMunicipioPorCodigo(registroReportanteTemporal.getCodMun()) + "</td>" +
                    "<td>" + registroEventoTemporal.getFechEvento() + "</td>" +
                    "<td>" + registroProductoTemporal.getNombreReactivo() + "</td>" +
                    "<td>" + tipReporte + "</td>" +
                    "<td>" + medida + "</td>" +
                    "<td>" + seguimiento + "</td>" +
                    "<td>" + caso + "</td>" +
                    "</tr>";
                    //******************************************************
                    //******************************************************
                    //******************************************************
                    //******************************************************
                }//Fin del for de la tabla de correo
                //****************************************************************************
                //****************************************************************************
                mensajeCorreo +=
                "</table> <br/><br/><br/>";
                //****************************************************************************
                //****************************************************************************
                mensajeCorreo +=
                "<p style='font-family: verdana;'>Agradecemos su atención, su notificación es fundamental para fortalecer la vigilancia post-mercado de los Reactivos de Diagnóstico in Vitro en el país, en beneficio de la seguridad del paciente.</p><br/><br/>" +
                "<p style='font-family: verdana;'><b>Lo anterior, en cumplimiento a lo establecido en la Resolución 2020007532 del 28 de febrero del 2020 \"Por la cual se implementa el Programa Nacional de ReactivoVigilancia\"</b></p> <br/><br/>" +
                "<p style='font-family: verdana;'>Nota: Síguenos en Nuestro Fan Page  <b><a href='https://www.facebook.com/RedNacionaldeReactivoVigilancia/'>https://www.facebook.com/RedNacionaldeReactivoVigilancia/</a></b></p>" +        
                "<p style='font-family: verdana;'>Cordialmente</p>" +
                "<br/><br/><br/>" +
                "<p style='font-family: verdana;'><b>GRUPO DE REACTIVOVIGILANCIA<br/>" +
                "Dirección de Dispositivos Médicos y otras Tecnologías<br/>" +
                "INVIMA<br/>" +
                "Teléfono: (1)2948700 Ext. 3880<br/>" +
                "<a href='mailto:reactivovigilancia@invima.gov.co'>reactivovigilancia@invima.gov.co</a><b></p>";
                //****************************************************************************
                //****************************************************************************
                //****************************************************************************
                //****************************************************************************
                //****************************************************************************
                //****************************************************************************
                //logBeanWebReactivo.info ("MENSAJE A ENVIAR = " + mensajeCorreo);
                //****************************************************************************
                //****************************************************************************
                CorreoElectronico correo = CorreoElectronico.getInstance();
                //logBeanWebReactivo.info ("CORREO DEL DESTINATARIO = " + correoDestinatario);
                //logBeanWebReactivo.info ("**********************************************************************************");
                //logBeanWebReactivo.info ("**********************************************************************************");
                //logBeanWebReactivo.info ("ENVIANDO CORREO ELECTRONICO DE NOTIFICACIÓN");
                //Titulo modificado:  Notificación a IPS Reportante de la Aprobación de reporte masivo trimestral ReactivoVigilancia
                correo.enviarCorreoElectronicoReactivo(correoDestinatario,"","Aprobación de reporte masivo trimestral ReactivoVigilancia", 
                        HtmlValidator.remplazarTildesEnTexto(mensajeCorreo),userSesion,"Aprobación de reporte masivo trimestral -1");
                //logBeanWebReactivo.info ("**********************************************************************************");
                //logBeanWebReactivo.info ("**********************************************************************************");
                //logBeanWebReactivo.info ("EL CORREO ELECTRÓNICO PARA EL REPORTANTE FUE ENVIADO INFORMANDO DEL RECHAZO DEL CARGUE MASIVO TRIMESTRAL");
                //*********************************************************************************************************************
                //*********************************************************************************************************************
            }
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //**********************************************************************************************
            //**********************************************************************************************
        }
        
        catch (Exception errorEnvioCorreo)
        {
            errorEnvioCorreo.printStackTrace();
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
    public String obtenerMedidaEjecutada (String codigo)    
    {
        String valor = "";
        
        switch (codigo)
        {
            case "1":
                    valor = "Oficio de requerimiento";
                    break;
            case "2":
                    valor = "Visita IVC al Prestador de Servicio de Salud";
                    break;
            case "3":
                    valor = "Visita IVC al fabricante/importador";
                    break;
            case "4":
                    valor = "Visita IVC al distribuidor/comercializador";
                    break;
            case "5":
                    valor = "Actividades de IVC de la SDS con el INVIMA";
                    break;
            case "6":
                    valor = "Asistencia Técnica al fabricante/importador";
                    break;
            case "7":
                    valor = "Asistencia Técnica al Prestador de Servicio de Salud";
                    break;
            case "8":
                    valor = "Otro";
                    break;
            case "9":
                    valor = "Ninguna acción";
                    break;
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
    public String obtenerNombreSexo (String codigo) 
    {
        String valor = "";
        
        if (codigo.equals("M"))
        {
            valor = "Masculino";
        }
        else
        {
            valor = "Femenino";
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerNombreDeteccion (String codigo)    
    {
        String valor = "";
        
        if (codigo.equals("1"))
        {
            valor = "Antes del Evento";
        }
        else
        if (codigo.equals("2"))
        {
            valor = "Durante el Evento";
        }
        else
        {
            valor = "Después del Evento";
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
            valor = "AÑOS";
        }
        else
        if (codigo.equals("M"))
        {
            valor = "MESES";
        }
        else
        if (codigo.equals("S"))
        {
            valor = "SEMANAS";
        }
        else
        if (codigo.equals("D"))
        {
            valor = "DÍAS";
        }
        else
        {
            valor = "SIN DATO";
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
    public String obtenerNombreEstadoReporte (String codigo) 
    { 
        String valor = "";
        
        if (codigo.equals("S"))
        {
            valor = "S";
        }
        else
        {
            valor = "C";
        }
        
        return (valor);
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
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void onEdit(RowEditEvent event) 
    {  
        //**********************************************************************************************
        //**********************************************************************************************
        /*
        //logBeanWebReactivo.info ("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        //logBeanWebReactivo.info ("EVITANDO QUE SE PIERDAN LOS DATOS");
        this.setNoBorrar(true);
        //logBeanWebReactivo.info ("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        */
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        String tipoReporte = "";
        String causaProbable = "";
        String medidaEjecutada = "";
        String estadoCaso = "";
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        //logBeanWebReactivo.info ("tipoReporte = "  + this.getTipoReporte());
        //logBeanWebReactivo.info ("causaProbable = "  + this.getCausaProbable());
        //logBeanWebReactivo.info ("medidaEjecutada = "  + this.getMedidaEjecucion());
        //logBeanWebReactivo.info ("estadoCaso = "  + this.getEstadoCaso());
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        if (this.getTipoReporte() != null)
        {
            this.setTipoReporte(this.getTipoReporte());
        }
        //**********************************************************************************************
        if (this.getCausaProbable()!= null)
        {
            this.setCausaProbable(this.getCausaProbable());
        }
        //**********************************************************************************************
        if (this.getMedidaEjecucion() == null)
        {
            this.setMedidaEjecucion(this.getMedidaEjecucion());
        }
        //**********************************************************************************************
        if (this.getEstadoCaso() == null)
        {
            this.setEstadoCaso(this.getEstadoCaso());
        }
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        tipoReporte = obtenerNombreTipoReporte (this.getTipoReporte());
        causaProbable =  getServicioReporteTrimestral().obtenerNombreCausaProbablePorCodigo(this.getCausaProbable());
        medidaEjecutada = obtenerNombreMedidaEjecutada(this.getMedidaEjecucion());
        estadoCaso = obtenerNombreEstadoLocal(this.getEstadoCaso());
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        //logBeanWebReactivo.info ("NOMBRE DEL tipoReporte = " + tipoReporte);
        //logBeanWebReactivo.info ("NOMBRE DEL causaProbable = " + causaProbable);
        //logBeanWebReactivo.info ("NOMBRE DEL medidaEjecutada = " + medidaEjecutada);
        //logBeanWebReactivo.info ("NOMBRE DEL estadoCaso = " + estadoCaso);
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        //**********************************************************************************************
        //((ReportePrecolVO) event.getObject()).setTipo_reporte(tipoReporte);
        ((ReportePreRDIVVO) event.getObject()).setCodigoCausa(causaProbable);
        //((ReportePrecolVO) event.getObject()).setMedidaEjecutada(medidaEjecutada);
        ((ReportePreRDIVVO) event.getObject()).setEstadoCaso(estadoCaso);
        //**********************************************************************************************
        //**********************************************************************************************
        //logBeanWebReactivo.info ("Tamaño actual del vector = " + this.getListaFilasEditadas().size());
        if (this.getListaFilasEditadas().size() > 0)
        {
            //logBeanWebReactivo.info ("La fila editada fue cerrada correctamente con precol " +((ReportePreRDIVVO) event.getObject()).getPrecol());
            this.setIndiceFilasEditadas(this.getIndiceFilasEditadas() - 1);
            this.getListaFilasEditadas().remove(this.getIndiceFilasEditadas());
            
            //logBeanWebReactivo.info ("TAMAÑO DEL VECTOR DE BOOLEANOS AL DAR CLICK AL CHULO: " + this.getListaFilasEditadas().size());
            //logBeanWebReactivo.info ("INDICE DE FILAS EDITADAS AL DAR CLICK AL CHULO: " + this.getIndiceFilasEditadas());
        }
        //**********************************************************************************************
        //**********************************************************************************************
        FacesMessage msg = new FacesMessage("Fila del Reporte Editada - Dispositivo " + ((ReportePreRDIVVO) event.getObject()).getPrecol(),"Registro del Reporte Temporal");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerNombreTipoReporte (String codigoTipo)
    {
        String nombre = "";
        
        switch (codigoTipo)
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
    private String obtenerNombreMedidaEjecutada (String codigoMedida)
    {
        String valor = "";
        
        switch (codigoMedida)
        {
            case "1":
                    valor = "Oficio de requerimiento";
                    break;
            case "2":
                    valor = "Visita IVC al Prestador de Servicio de Salud";
                    break;
            case "3":
                    valor = "Visita IVC al fabricante/importador";
                    break;
            case "4":
                    valor = "Visita IVC al distribuidor/comercializador";
                    break;
            case "5":
                    valor = "Actividades de IVC de la SDS con el INVIMA";
                    break;
            case "6":
                    valor = "Asistencia Técnica al fabricante/importador";
                    break;
            case "7":
                    valor = "Asistencia Técnica al Prestador de Servicio de Salud";
                    break;
            case "8":
                    valor = "Otro";
                    break;
            case "9":
                    valor = "Ninguna acción";
                    break;
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
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
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void onCancel(RowEditEvent event) 
    {  
        FacesMessage msg = new FacesMessage("Fila del Reporte Eliminada");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        this.getListadoPrerdivsParaAprobacion().remove((ReportePreRDIVVO) event.getObject());
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void capturarDatoFila(RowEditEvent event) 
    {  
        //***************************************************************
        //***************************************************************
        String tipoReporte = "";
        String causaProbable = "";
        String medidaEjecutada = "";
        String estadoCaso = "";
        //***************************************************************
        //***************************************************************
        String updateClienttipoReporte = ""; 
        String updateClientcausaProbable = "";
        String updateClientmedidaEjecutada = "";
        String updateClientestadoCaso = "";
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
            //updateClienttipoReporte = table.getClientId() + ":" + table.getRowIndex() + ":tipoReporteGrilla";
            updateClientcausaProbable = table.getClientId() + ":" + table.getRowIndex() + ":codigoCausaGrilla";
            //updateClientmedidaEjecutada = table.getClientId() + ":" + table.getRowIndex() + ":medidaEjecutadaGrilla";
            updateClientestadoCaso = table.getClientId() + ":" + table.getRowIndex() + ":estadoreporteGrilla";
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //logBeanWebReactivo.info ("ID DEL TIPO DE REPORTE DE LA GRILLA ESCOGIDO = " + updateClienttipoReporte);
            //logBeanWebReactivo.info ("ID DE LA CAUSA PROBABLE DE LA GRILLA ESCOGIDO = " + updateClientcausaProbable);
            //logBeanWebReactivo.info ("ID DE MEDIDA EJECUTADA DE LA GRILLA ESCOGIDO = " + updateClientmedidaEjecutada);
            //logBeanWebReactivo.info ("ID DE ESTADO CASO DE LA GRILLA ESCOGIDO = " + updateClientestadoCaso);
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //logBeanWebReactivo.info ("-------------------------------------------------------------");
            this.setRegistroActual((ReportePreRDIVVO) event.getObject());
            //***********************************************************************************
            //***********************************************************************************
            //tipoReporte = this.getRegistroActual().getTipo_reporte();
            causaProbable = this.getRegistroActual().getCodigoCausa();
            //medidaEjecutada = this.getRegistroActual().getMedidaEjecutada();
            estadoCaso = this.getRegistroActual().getEstadoCaso();
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //logBeanWebReactivo.info ("ID TIPO REPORTE TRAIDO AL HACER CLIC PARA EDITAR LA GRILLA = " + tipoReporte);
            //logBeanWebReactivo.info ("ID CAUSA PROBABLE TRAIDO AL HACER CLIC PARA EDITAR LA GRILLA = " + causaProbable);
            //logBeanWebReactivo.info ("ID MEDIDA EJECUTADA TRAIDO AL HACER CLIC PARA EDITAR LA GRILLA = " + medidaEjecutada);
            //logBeanWebReactivo.info ("ID ESTADO CASO TRAIDO AL HACER CLIC PARA EDITAR LA GRILLA = " + estadoCaso);
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            if (this.getTipoReporte() == null)
            {
                //logBeanWebReactivo.info ("Seteando id tipo reporte cuando es null");
                this.setTipoReporte(obtenerCodigoTipoReporte(tipoReporte));
            }
            else
            {
                //logBeanWebReactivo.info ("Seteando id tipo reporte cuando no es null");
                this.setTipoReporte(obtenerCodigoTipoReporte(tipoReporte));
            }
            //***********************************************************************************
            //***********************************************************************************
            if (this.getCausaProbable() == null)
            {
                //logBeanWebReactivo.info ("Seteando id causa probable cuando es null");
                this.setCausaProbable(getServicioReporteTrimestral().obtenerCodigoCausaProbable(causaProbable));
            }
            else
            {
                //logBeanWebReactivo.info ("Seteando id causa probable cuando no es null");
                this.setCausaProbable(getServicioReporteTrimestral().obtenerCodigoCausaProbable(causaProbable));
            }
            //***********************************************************************************
            //***********************************************************************************
            if (this.getMedidaEjecucion() == null)
            {
                //logBeanWebReactivo.info ("Seteando id medida ejecutada cuando es null");
                this.setMedidaEjecucion(obtenerCodigoMedidaEjecutada(medidaEjecutada));
            }
            else
            {
                //logBeanWebReactivo.info ("Seteando id medida ejecutada cuando no es null");
                this.setMedidaEjecucion(obtenerCodigoMedidaEjecutada(medidaEjecutada));
            }
            //***********************************************************************************
            //***********************************************************************************
            if (this.getEstadoCaso() == null)
            {
                //logBeanWebReactivo.info ("Seteando id estado caso cuando es null");
                this.setEstadoCaso(obtenerIdEstadoLocal(estadoCaso));
            }
            else
            {
                //logBeanWebReactivo.info ("Seteando id estado caso cuando no es null");
                this.setEstadoCaso(obtenerIdEstadoLocal(estadoCaso));
            }
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //logBeanWebReactivo.info ("-------------------------------------------------------------");
            //logBeanWebReactivo.info ("-------------------------------------------------------------");
            //logBeanWebReactivo.info ("CODIGO TIPO REPORTE = " + this.getTipoReporte());
            //logBeanWebReactivo.info ("CODIGO CAUSA PROBABLE = " + this.getCausaProbable());
            //logBeanWebReactivo.info ("CODIGO MEDIDA EJECUTADA = " + this.getMedidaEjecucion());
            //logBeanWebReactivo.info ("CODIGO ESTADO CASO = " + this.getEstadoCaso());
            //logBeanWebReactivo.info ("-------------------------------------------------------------");
            //logBeanWebReactivo.info ("-------------------------------------------------------------");
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //((ReportePrecolVO) event.getObject()).setTipo_reporte(this.getTipoReporte());
            ((ReportePreRDIVVO) event.getObject()).setCodigoCausa(this.getCausaProbable());
            //((ReportePrecolVO) event.getObject()).setMedidaEjecutada(this.getMedidaEjecucion());
            ((ReportePreRDIVVO) event.getObject()).setEstadoCaso(this.getEstadoCaso());
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //logBeanWebReactivo.info ("-------------------------------------------------------------");
            //logBeanWebReactivo.info ("Actualizando combos en la fila de la grilla");
            //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClienttipoReporte);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientcausaProbable);
            //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientmedidaEjecutada);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientestadoCaso);
            //logBeanWebReactivo.info ("-------------------------------------------------------------");
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //***********************************************************************************
            //logBeanWebReactivo.info ("TAMAÑO DE VECTOR DE FILAS EDITADAS: " + this.getListaFilasEditadas().size());
            //logBeanWebReactivo.info ("INDICE DEL FILAS EDITADAS: " + this.getIndiceFilasEditadas());
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
    private String obtenerCodigoTipoReporte(String nombre)
    {
        String id = "";
        
        switch (nombre)
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
    private String obtenerCodigoMedidaEjecutada (String nombre)
    {
        String valor = "";
   
        if (nombre != null)
        {
                switch (nombre)
                {
                    case "Oficio de requerimiento":
                            valor = "1";
                            break;
                    case "Visita IVC al Prestador de Servicio de Salud":
                            valor = "2";
                            break;
                    case "Visita IVC al fabricante/importador":
                            valor = "3";
                            break;
                    case "Visita IVC al distribuidor/comercializador":
                            valor = "4";
                            break;
                    case "Actividades de IVC de la SDS con el INVIMA":
                            valor = "5";
                            break;
                    case "Asistencia Técnica al fabricante/importador":
                            valor = "6";
                            break;
                    case "Asistencia Técnica al Prestador de Servicio de Salud":
                            valor = "7";
                            break;
                }
        }
        else
        {
            valor = "1";
        }
        
        return (valor);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public void cargarCombosGrillaValidadora()
    {
        List<String> listaTipoReportesLocal = null;
        List<String> listaCausasProbablesLocal =  null;
    
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
            listaTipoReportesLocal = getServicioReporteTrimestral().obtenerListaTipoReportes();
            setListaTiposReporte(armarItemsCombo(listaTipoReportesLocal));
            //logBeanWebReactivo.info ("Lista listaTiposReporte = " + this.getListaTiposReporte());
            //*******************************************************************
            //*******************************************************************
            listaCausasProbablesLocal = getServicioReporteTrimestral().obtenerListaCausasProbables();
            setListaCausasProbables(armarItemsCombo(listaCausasProbablesLocal));
            //logBeanWebReactivo.info ("Lista listaCausasProbables = " + this.getListaCausasProbables());
            //*******************************************************************
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
            setListaCausasProbables(new ArrayList());
            errorcargarCombosGrillaValidadora.printStackTrace();
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
    //********************************************************************
    //********************************************************************
    //Métodos de encapsulamiento del Bean
    /**
     * @return the ejbServicioRemotoReporteMasivoTrimestral
     */
    public ServicioReporteTrimestralRemote getEjbServicioRemotoReporteMasivoTrimestral() {
        return ejbServicioRemotoReporteMasivoTrimestral;
    }

    /**
     * @param ejbServicioRemotoReporteMasivoTrimestral the ejbServicioRemotoReporteMasivoTrimestral to set
     */
    public void setEjbServicioRemotoReporteMasivoTrimestral(ServicioReporteTrimestralRemote ejbServicioRemotoReporteMasivoTrimestral) {
        this.ejbServicioRemotoReporteMasivoTrimestral = ejbServicioRemotoReporteMasivoTrimestral;
    }

    /**
     * @return the listadoPrerdivsParaAprobacion
     */
    public List<ReportePreRDIVVO> getListadoPrerdivsParaAprobacion() {
        return listadoPrerdivsParaAprobacion;
    }

    /**
     * @param listadoPrerdivsParaAprobacion the listadoPrerdivsParaAprobacion to set
     */
    public void setListadoPrerdivsParaAprobacion(List<ReportePreRDIVVO> listadoPrerdivsParaAprobacion) {
        this.listadoPrerdivsParaAprobacion = listadoPrerdivsParaAprobacion;
    }

    /**
     * @return the listadoPrerdivsParaAprobacionEscogidos
     */
    public List<ReportePreRDIVVO> getListadoPrerdivsParaAprobacionEscogidos() {
        return listadoPrerdivsParaAprobacionEscogidos;
    }

    /**
     * @param listadoPrerdivsParaAprobacionEscogidos the listadoPrerdivsParaAprobacionEscogidos to set
     */
    public void setListadoPrerdivsParaAprobacionEscogidos(List<ReportePreRDIVVO> listadoPrerdivsParaAprobacionEscogidos) {
        this.listadoPrerdivsParaAprobacionEscogidos = listadoPrerdivsParaAprobacionEscogidos;
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
     * @return the registroActual
     */
    public ReportePreRDIVVO getRegistroActual() {
        return registroActual;
    }

    /**
     * @param registroActual the registroActual to set
     */
    public void setRegistroActual(ReportePreRDIVVO registroActual) {
        this.registroActual = registroActual;
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
     * @return the listaIPSReportante
     */
    public ArrayList getListaIPSReportante() {
        return listaIPSReportante;
    }

    /**
     * @param listaIPSReportante the listaIPSReportante to set
     */
    public void setListaIPSReportante(ArrayList listaIPSReportante) {
        this.listaIPSReportante = listaIPSReportante;
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
     * @return the ipsreportante
     */
    public String getIpsreportante() {
        return ipsreportante;
    }

    /**
     * @param ipsreportante the ipsreportante to set
     */
    public void setIpsreportante(String ipsreportante) {
        this.ipsreportante = ipsreportante;
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
     * @return the nombreAprobador
     */
    public String getNombreAprobador() {
        return nombreAprobador;
    }

    /**
     * @param nombreAprobador the nombreAprobador to set
     */
    public void setNombreAprobador(String nombreAprobador) {
        this.nombreAprobador = nombreAprobador;
    }

    /**
     * @return the idRolBusqueda
     */
    public String getIdRolBusqueda() {
        return idRolBusqueda;
    }

    /**
     * @param idRolBusqueda the idRolBusqueda to set
     */
    public void setIdRolBusqueda(String idRolBusqueda) {
        this.idRolBusqueda = idRolBusqueda;
    }

    /**
     * @return the idFuncionario
     */
    public String getIdFuncionario() {
        return idFuncionario;
    }

    /**
     * @param idFuncionario the idFuncionario to set
     */
    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    /**
     * @return the datosReactivoEventosTemp
     */
    public ReactivoEventosTemp getDatosReactivoEventosTemp() {
        return datosReactivoEventosTemp;
    }

    /**
     * @param datosReactivoEventosTemp the datosReactivoEventosTemp to set
     */
    public void setDatosReactivoEventosTemp(ReactivoEventosTemp datosReactivoEventosTemp) {
        this.datosReactivoEventosTemp = datosReactivoEventosTemp;
    }

    /**
     * @return the datosReactivoPacienteTemp
     */
    public ReactivoPacienteTemp getDatosReactivoPacienteTemp() {
        return datosReactivoPacienteTemp;
    }

    /**
     * @param datosReactivoPacienteTemp the datosReactivoPacienteTemp to set
     */
    public void setDatosReactivoPacienteTemp(ReactivoPacienteTemp datosReactivoPacienteTemp) {
        this.datosReactivoPacienteTemp = datosReactivoPacienteTemp;
    }

    /**
     * @return the datosReactivoReportanteTemp
     */
    public ReactivoReportanteTemp getDatosReactivoReportanteTemp() {
        return datosReactivoReportanteTemp;
    }

    /**
     * @param datosReactivoReportanteTemp the datosReactivoReportanteTemp to set
     */
    public void setDatosReactivoReportanteTemp(ReactivoReportanteTemp datosReactivoReportanteTemp) {
        this.datosReactivoReportanteTemp = datosReactivoReportanteTemp;
    }

    /**
     * @return the datosReactivoGestionrealizadaTemp
     */
    public ReactivoGestionrealizadaTemp getDatosReactivoGestionrealizadaTemp() {
        return datosReactivoGestionrealizadaTemp;
    }

    /**
     * @param datosReactivoGestionrealizadaTemp the datosReactivoGestionrealizadaTemp to set
     */
    public void setDatosReactivoGestionrealizadaTemp(ReactivoGestionrealizadaTemp datosReactivoGestionrealizadaTemp) {
        this.datosReactivoGestionrealizadaTemp = datosReactivoGestionrealizadaTemp;
    }

    /**
     * @return the datosReactivoCamposcompTemp
     */
    public ReactivoCamposcompTemp getDatosReactivoCamposcompTemp() {
        return datosReactivoCamposcompTemp;
    }

    /**
     * @param datosReactivoCamposcompTemp the datosReactivoCamposcompTemp to set
     */
    public void setDatosReactivoCamposcompTemp(ReactivoCamposcompTemp datosReactivoCamposcompTemp) {
        this.datosReactivoCamposcompTemp = datosReactivoCamposcompTemp;
    }

    /**
     * @return the datosReactivoInstitucionTemp
     */
    public ReactivoInstitucionTemp getDatosReactivoInstitucionTemp() {
        return datosReactivoInstitucionTemp;
    }

    /**
     * @param datosReactivoInstitucionTemp the datosReactivoInstitucionTemp to set
     */
    public void setDatosReactivoInstitucionTemp(ReactivoInstitucionTemp datosReactivoInstitucionTemp) {
        this.datosReactivoInstitucionTemp = datosReactivoInstitucionTemp;
    }

    /**
     * @return the datosReactivoProductoTemp
     */
    public ReactivoProductoTemp getDatosReactivoProductoTemp() {
        return datosReactivoProductoTemp;
    }

    /**
     * @param datosReactivoProductoTemp the datosReactivoProductoTemp to set
     */
    public void setDatosReactivoProductoTemp(ReactivoProductoTemp datosReactivoProductoTemp) {
        this.datosReactivoProductoTemp = datosReactivoProductoTemp;
    }

    /**
     * @return the todosCamposBasicos
     */
    public boolean isTodosCamposBasicos() {
        return todosCamposBasicos;
    }

    /**
     * @param todosCamposBasicos the todosCamposBasicos to set
     */
    public void setTodosCamposBasicos(boolean todosCamposBasicos) {
        this.todosCamposBasicos = todosCamposBasicos;
    }

    /**
     * @return the idDeptoSesion
     */
    public String getIdDeptoSesion() {
        return idDeptoSesion;
    }

    /**
     * @param idDeptoSesion the idDeptoSesion to set
     */
    public void setIdDeptoSesion(String idDeptoSesion) {
        this.idDeptoSesion = idDeptoSesion;
    }

    /**
     * @return the idMunicipioSesion
     */
    public String getIdMunicipioSesion() {
        return idMunicipioSesion;
    }

    /**
     * @param idMunicipioSesion the idMunicipioSesion to set
     */
    public void setIdMunicipioSesion(String idMunicipioSesion) {
        this.idMunicipioSesion = idMunicipioSesion;
    }

    /**
     * @return the nombreSecretariaSalud
     */
    public String getNombreSecretariaSalud() {
        return nombreSecretariaSalud;
    }

    /**
     * @param nombreSecretariaSalud the nombreSecretariaSalud to set
     */
    public void setNombreSecretariaSalud(String nombreSecretariaSalud) {
        this.nombreSecretariaSalud = nombreSecretariaSalud;
    }

    /**
     * @return the fechaInicial
     */
    public java.util.Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(java.util.Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public java.util.Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(java.util.Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the nombreDepto
     */
    public String getNombreDepto() {
        return nombreDepto;
    }

    /**
     * @param nombreDepto the nombreDepto to set
     */
    public void setNombreDepto(String nombreDepto) {
        this.nombreDepto = nombreDepto;
    }

    /**
     * @return the nombreMunicipio
     */
    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    /**
     * @param nombreMunicipio the nombreMunicipio to set
     */
    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    /**
     * @return the nombreNaturaleza
     */
    public String getNombreNaturaleza() {
        return nombreNaturaleza;
    }

    /**
     * @param nombreNaturaleza the nombreNaturaleza to set
     */
    public void setNombreNaturaleza(String nombreNaturaleza) {
        this.nombreNaturaleza = nombreNaturaleza;
    }

    /**
     * @return the nombreTipoIdentificacion
     */
    public String getNombreTipoIdentificacion() {
        return nombreTipoIdentificacion;
    }

    /**
     * @param nombreTipoIdentificacion the nombreTipoIdentificacion to set
     */
    public void setNombreTipoIdentificacion(String nombreTipoIdentificacion) {
        this.nombreTipoIdentificacion = nombreTipoIdentificacion;
    }

    /**
     * @return the nombreEdadEn
     */
    public String getNombreEdadEn() {
        return nombreEdadEn;
    }

    /**
     * @param nombreEdadEn the nombreEdadEn to set
     */
    public void setNombreEdadEn(String nombreEdadEn) {
        this.nombreEdadEn = nombreEdadEn;
    }

    /**
     * @return the nombreDeteccion
     */
    public String getNombreDeteccion() {
        return nombreDeteccion;
    }

    /**
     * @param nombreDeteccion the nombreDeteccion to set
     */
    public void setNombreDeteccion(String nombreDeteccion) {
        this.nombreDeteccion = nombreDeteccion;
    }

    /**
     * @return the nombreDesenlace
     */
    public String getNombreDesenlace() {
        return nombreDesenlace;
    }

    /**
     * @param nombreDesenlace the nombreDesenlace to set
     */
    public void setNombreDesenlace(String nombreDesenlace) {
        this.nombreDesenlace = nombreDesenlace;
    }

    /**
     * @return the nombreCausaProbable
     */
    public String getNombreCausaProbable() {
        return nombreCausaProbable;
    }

    /**
     * @param nombreCausaProbable the nombreCausaProbable to set
     */
    public void setNombreCausaProbable(String nombreCausaProbable) {
        this.nombreCausaProbable = nombreCausaProbable;
    }

    /**
     * @return the nombreProfesion
     */
    public String getNombreProfesion() {
        return nombreProfesion;
    }

    /**
     * @param nombreProfesion the nombreProfesion to set
     */
    public void setNombreProfesion(String nombreProfesion) {
        this.nombreProfesion = nombreProfesion;
    }

    /**
     * @return the nombreDeptoReporte
     */
    public String getNombreDeptoReporte() {
        return nombreDeptoReporte;
    }

    /**
     * @param nombreDeptoReporte the nombreDeptoReporte to set
     */
    public void setNombreDeptoReporte(String nombreDeptoReporte) {
        this.nombreDeptoReporte = nombreDeptoReporte;
    }

    /**
     * @return the nombreMunReporte
     */
    public String getNombreMunReporte() {
        return nombreMunReporte;
    }

    /**
     * @param nombreMunReporte the nombreMunReporte to set
     */
    public void setNombreMunReporte(String nombreMunReporte) {
        this.nombreMunReporte = nombreMunReporte;
    }

    /**
     * @return the nombreTipoReportante
     */
    public String getNombreTipoReportante() {
        return nombreTipoReportante;
    }

    /**
     * @param nombreTipoReportante the nombreTipoReportante to set
     */
    public void setNombreTipoReportante(String nombreTipoReportante) {
        this.nombreTipoReportante = nombreTipoReportante;
    }

    /**
     * @return the nombreTipoDispositivo
     */
    public String getNombreTipoDispositivo() {
        return nombreTipoDispositivo;
    }

    /**
     * @param nombreTipoDispositivo the nombreTipoDispositivo to set
     */
    public void setNombreTipoDispositivo(String nombreTipoDispositivo) {
        this.nombreTipoDispositivo = nombreTipoDispositivo;
    }

    /**
     * @return the tipoReporte
     */
    public String getTipoReporte() {
        return tipoReporte;
    }

    /**
     * @param tipoReporte the tipoReporte to set
     */
    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    /**
     * @return the causaProbable
     */
    public String getCausaProbable() {
        return causaProbable;
    }

    /**
     * @param causaProbable the causaProbable to set
     */
    public void setCausaProbable(String causaProbable) {
        this.causaProbable = causaProbable;
    }

    /**
     * @return the medidaEjecucion
     */
    public String getMedidaEjecucion() {
        return medidaEjecucion;
    }

    /**
     * @param medidaEjecucion the medidaEjecucion to set
     */
    public void setMedidaEjecucion(String medidaEjecucion) {
        this.medidaEjecucion = medidaEjecucion;
    }

    /**
     * @return the estadoCaso
     */
    public String getEstadoCaso() {
        return estadoCaso;
    }

    /**
     * @param estadoCaso the estadoCaso to set
     */
    public void setEstadoCaso(String estadoCaso) {
        this.estadoCaso = estadoCaso;
    }

    /**
     * @return the rolUsuario
     */
    public String getRolUsuario() {
        return rolUsuario;
    }

    /**
     * @param rolUsuario the rolUsuario to set
     */
    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    /**
     * @return the idRolFuncionarioAprobador
     */
    public String getIdRolFuncionarioAprobador() {
        return idRolFuncionarioAprobador;
    }

    /**
     * @param idRolFuncionarioAprobador the idRolFuncionarioAprobador to set
     */
    public void setIdRolFuncionarioAprobador(String idRolFuncionarioAprobador) {
        this.idRolFuncionarioAprobador = idRolFuncionarioAprobador;
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

    /**
     * @return the noBorrar
     */
    public boolean isNoBorrar() {
        return noBorrar;
    }

    /**
     * @param noBorrar the noBorrar to set
     */
    public void setNoBorrar(boolean noBorrar) {
        this.noBorrar = noBorrar;
    }

    /**
     * @return the esAdministrador
     */
    public boolean isEsAdministrador() {
        return esAdministrador;
    }

    /**
     * @param esAdministrador the esAdministrador to set
     */
    public void setEsAdministrador(boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
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
} //Fin del managed bean
/******************************************************************/
/******************************************************************/
/******************************************************************/
/******************************************************************/
/******************************************************************/
/******************************************************************/