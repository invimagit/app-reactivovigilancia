/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jgutierrezme
 */
import co.gov.invima.VO.ReactivoReporteCeroVO;
import co.gov.invima.VO.UsuariosVO;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import co.gov.invima.negocio.ServicioReporteCeroRemote;
import co.gov.invima.reactivo.dto.reports.PersonaInternet;
import co.gov.invima.service.ServiceLocator;
import co.gov.invima.util.CorreoElectronico;
import co.gov.invima.util.Fecha;
import co.gov.invima.util.HtmlValidator;
import co.gov.invima.util.PropertiesReader;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.apache.log4j.Logger;
import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
/********************************************************************/
/********************************************************************/
/********************************************************************/
/********************************************************************/
/********************************************************************/
/********************************************************************/
//@RequestScoped
@ViewScoped
@ManagedBean(name="beanReporteReactivoCero")
public class BeanReporteReactivoCero implements Serializable  
{
    //****************************************************************************************
    //****************************************************************************************
    private final static Logger logBeanWebReactivo = Logger.getLogger(BeanReporteReactivoCero.class.getName());
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    private static long serialVersionUID = -71825531196747530L;
    //****************************************************************************************
    //****************************************************************************************
    private ServicioReporteCeroRemote servicioEJBReactivoReporteCero;
    //****************************************************************************************
    //****************************************************************************************
    private String ipsReportante;
    private String nombreInstitucion;
    private String nitETSReportante;
    private String correoReportante;
    private String periodoReporte;
    private String yearReporte;
    private String observacionReportante;
    private String mensajeError;
    private String alertas;
    private boolean obligatorio1;
    private boolean obligatorio2;
    private boolean obligatorio3;
    private boolean visibleConfirmacion;
    private boolean mostrarError;
    private String usuarioSesion;
    private Map<String,String> periodos;
    private Map<String,String> yearsReportado;
    private boolean reportaExtemporeaneo;
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    public BeanReporteReactivoCero()
    {
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("Iniciando constructor del Managed Bean Web BeanReporteReactivoCero");
        //logBeanWebReactivo.info("Conectandose al contexto");
        ServiceLocator service = new ServiceLocator();
        //logBeanWebReactivo.info ("ESTADO DEL SERVICE LOCATOR = " + service);
        servicioEJBReactivoReporteCero = service.getEjbRemotoReactivoReporteCero();
        //logBeanWebReactivo.info("Estado del EJB DEL REPORTE PARA EL MANAGED BEAN: " + this.servicioEJBReactivoReporteCero);
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    UsuariosVO userSesion;
    @PostConstruct
    public void init() 
    {
        //***********************************************************
        //***********************************************************
        /*
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest myRequest = (HttpServletRequest)context.getExternalContext().getRequest();
        */
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sesionUsuario = request.getSession();
        String nombreUsuario = "";
        userSesion = null;
        java.util.Date fechaRegistro = null;
        String periodoActivo = "";
        PersonaInternet usuarioEnSesion = null;
        //***********************************************************
        //***********************************************************
        try
        {
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            fechaRegistro = new java.util.Date();
            userSesion = (UsuariosVO)sesionUsuario.getAttribute("usuario");
            //**********************************************************************
            //**********************************************************************
            if (userSesion.getEmailEmpresa() != null)
            {
                logBeanWebReactivo.info ("SETEANDO USUARIO DE SECRETARIA");
                nombreUsuario = userSesion.getUsuario();
                this.setCorreoReportante(userSesion.getEmailEmpresa());
                this.setIpsReportante(nombreUsuario);
            }
            else
            {
                logBeanWebReactivo.info ("SETEANDO USUARIO DE ADMINISTRADOR INVIMA");
                nombreUsuario = userSesion.getUsuario();
                this.setCorreoReportante(userSesion.getUsuario() + "@invima.gov.co");
                this.setIpsReportante(nombreUsuario);
            }
            //**********************************************************************
            //**********************************************************************
            logBeanWebReactivo.info ("USUARIO EN SESION = " + nombreUsuario);
            logBeanWebReactivo.info ("CORREO DEL DESTINATARIO = " + this.getCorreoReportante());
            logBeanWebReactivo.info ("USUARIO IPS QUE REPORTA = " + this.getIpsReportante());
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("FECHA PARA EL REPORTE EN CERO = " + fechaRegistro);
            this.periodos = obtenerListaTrimestres(fechaRegistro);
            //logBeanWebReactivo.info ("PERIODOS = " + this.periodos);
            periodoActivo = obtenerPeriodoDeAcuerdoFecha(fechaRegistro);
            this.yearsReportado = actualizarYearsCombos(false,periodoActivo);
            usuarioEnSesion = getServicioEJBReactivoReporteCero().obtenerDatosETSUsuarioSesion(nombreUsuario);
            //**********************************************************************
            //**********************************************************************
            if (usuarioEnSesion != null)
            {
                this.setNitETSReportante(usuarioEnSesion.getIdentificacion_empresa());
                this.setNombreInstitucion(usuarioEnSesion.getNombre_empresa());
                this.setUsuarioSesion(nombreUsuario);
            }
            else
            {
                this.setNitETSReportante("8300001672");
                this.setNombreInstitucion("INSTITUTO NACIONAL DE VIGILANCIA DE MEDICAMENTOS Y ALIMENTOS INVIMA");
                this.setUsuarioSesion(nombreUsuario);
            }
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //logBeanWebReactivo.info ("nitETSReportante = " + this.getNitETSReportante());
            //logBeanWebReactivo.info ("nombreInstitucion = " + this.getNombreInstitucion());
            //logBeanWebReactivo.info ("nombre usuario = " + this.getUsuarioSesion());
            //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
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
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    public Map<String,String> obtenerListaTrimestres (java.util.Date fechaActual)
    {
        //***********************************************************
        //***********************************************************
        int i = 0;
        Map<String,String> comboEsquema = null;
        Map<String,String> comboOrganizado = null;
        String idEsquema = "";
        String nombreEsquema = "";
        //***********************************************************
        //***********************************************************
        java.util.Date fechaInicialTrim1 = null;
        java.util.Date fechaFinalTrim1 = null;
        java.util.Date fechaInicialTrim2 = null;
        java.util.Date fechaFinalTrim2 = null;
        java.util.Date fechaInicialTrim3 = null;
        java.util.Date fechaFinalTrim3 = null;
        java.util.Date fechaInicialTrim4 = null;
        java.util.Date fechaFinalTrim4 = null;
        //***********************************************************
        //***********************************************************
        String [] trimestres =
        {
            "1","I - Primer Trimestre",
            "2","II - Segundo Trimestre",
            "3","III - Tercer Trimestre",
            "4","IV - Cuarto Trimestre"
        };
        //***********************************************************
        //***********************************************************
        //***********************************************************
        //***********************************************************
        try
        {
            //***********************************************************
            //***********************************************************
            fechaInicialTrim1 = new java.util.Date(fechaActual.getYear(),0,1);
            fechaFinalTrim1 = new java.util.Date(fechaActual.getYear(),3,1);
            //***********************************************************
            fechaInicialTrim2 = new java.util.Date(fechaActual.getYear(),3,1);
            fechaFinalTrim2 = new java.util.Date(fechaActual.getYear(),6,1);
            //***********************************************************
            fechaInicialTrim3 = new java.util.Date(fechaActual.getYear(),6,1);
            fechaFinalTrim3 = new java.util.Date(fechaActual.getYear(),9,1);
            //***********************************************************
            fechaInicialTrim4 = new java.util.Date(fechaActual.getYear(),9,1);
            fechaFinalTrim4 = new java.util.Date(fechaActual.getYear(),11,31);
            //***********************************************************
            //***********************************************************
            comboEsquema = new HashMap<String, String>();
            //***********************************************************
            //***********************************************************
            //logBeanWebReactivo.info ("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            //logBeanWebReactivo.info ("Fecha actual = " + fechaActual);
            //logBeanWebReactivo.info ("FECHA INICIAL DEL TRIMESTRE 1= " + fechaInicialTrim1);
            //logBeanWebReactivo.info ("FECHA FINAL DEL TRIMESTRE 1 = " + fechaFinalTrim1);
            //logBeanWebReactivo.info ("FECHA INICIAL DEL TRIMESTRE 2= " + fechaInicialTrim2);
            //logBeanWebReactivo.info ("FECHA FINAL DEL TRIMESTRE 2 = " + fechaFinalTrim2);
            //logBeanWebReactivo.info ("FECHA INICIAL DEL TRIMESTRE 3= " + fechaInicialTrim3);
            //logBeanWebReactivo.info ("FECHA FINAL DEL TRIMESTRE 3 = " + fechaFinalTrim3);
            //logBeanWebReactivo.info ("FECHA INICIAL DEL TRIMESTRE 4 = " + fechaInicialTrim4);
            //logBeanWebReactivo.info ("FECHA FINAL DEL TRIMESTRE 4 = " + fechaFinalTrim4);
            //logBeanWebReactivo.info ("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            //***********************************************************
            //***********************************************************
            //***********************************************************
            //***********************************************************
            if (fechaActual.after(fechaInicialTrim1) && fechaActual.before(fechaFinalTrim1))
            {
                //logBeanWebReactivo.info ("FECHA INICIAL DEL TRIMESTRE = " + fechaInicialTrim1);
                //logBeanWebReactivo.info ("FECHA FINAL DEL TRIMESTRE = " + fechaFinalTrim1);
                //logBeanWebReactivo.info ("FECHA PARA EL REGISTRO DEL REPORTE EN CERO = " + fechaActual);
                //logBeanWebReactivo.info("Estamos en trimestre 1");
                
                for (i = 6;  i < trimestres.length;  i+=2)
                {
                        idEsquema = trimestres[i];
                        nombreEsquema = trimestres[i+1];
                        comboEsquema.put(nombreEsquema,idEsquema);
                }
            }
            else
            if (fechaActual.after(fechaInicialTrim2) && fechaActual.before(fechaFinalTrim2))
            {
                //logBeanWebReactivo.info ("FECHA INICIAL DEL TRIMESTRE = " + fechaInicialTrim2);
                //logBeanWebReactivo.info ("FECHA FINAL DEL TRIMESTRE = " + fechaFinalTrim2);
                //logBeanWebReactivo.info ("FECHA PARA EL REGISTRO DEL REPORTE EN CERO = " + fechaActual);
                //logBeanWebReactivo.info("Estamos en trimestre 2");
                
                for (i = 0;  i < 2;  i+=2)
                {
                        idEsquema = trimestres[i];
                        nombreEsquema = trimestres[i+1];
                        comboEsquema.put(nombreEsquema,idEsquema);
                }
            }
            else
            if (fechaActual.after(fechaInicialTrim3) && fechaActual.before(fechaFinalTrim3))
            {
                //logBeanWebReactivo.info ("FECHA INICIAL DEL TRIMESTRE = " + fechaInicialTrim3);
                //logBeanWebReactivo.info ("FECHA FINAL DEL TRIMESTRE = " + fechaFinalTrim3);
                //logBeanWebReactivo.info ("FECHA PARA EL REGISTRO DEL REPORTE EN CERO = " + fechaActual);
                //logBeanWebReactivo.info("Estamos en trimestre 3");
                
                for (i = 2;  i < 4;  i+=2)
                {
                        idEsquema = trimestres[i];
                        nombreEsquema = trimestres[i+1];
                        comboEsquema.put(nombreEsquema,idEsquema);
                }
            }
            else
            if (fechaActual.after(fechaInicialTrim4) && fechaActual.before(fechaFinalTrim4))
            {
                //logBeanWebReactivo.info ("FECHA INICIAL DEL TRIMESTRE = " + fechaInicialTrim4);
                //logBeanWebReactivo.info ("FECHA FINAL DEL TRIMESTRE = " + fechaFinalTrim4);
                //logBeanWebReactivo.info ("FECHA PARA EL REGISTRO DEL REPORTE EN CERO = " + fechaActual);
                //logBeanWebReactivo.info("Estamos en trimestre 4");
                
                for (i = 4;  i < 6;  i+=2)
                {
                        idEsquema = trimestres[i];
                        nombreEsquema = trimestres[i+1];
                        comboEsquema.put(nombreEsquema,idEsquema);
                }
            }
            //***********************************************************
            //***********************************************************
            comboOrganizado = new TreeMap<String, String>(comboEsquema);
            //***********************************************************
            //***********************************************************
            //logBeanWebReactivo.info  ("Longitud del vector de pojos de esquema = " + comboOrganizado.size() + " y vector = " + comboOrganizado);
            //logBeanWebReactivo.info ("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            //logBeanWebReactivo.info ("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
        //***********************************************************
        //***********************************************************
        catch (Exception errorobtenerListaEsquemas)
        {
            //logBeanWebReactivo.info("------------------------Error------------------------");
            //logBeanWebReactivo.info("No fue posible ejecutar el método obtenerListaEsquemas del Bean " + this.getClass().getCanonicalName() + ": " + errorobtenerListaEsquemas.getMessage());
            //logBeanWebReactivo.info("------------------------Fin Error------------------------");
            errorobtenerListaEsquemas.printStackTrace();
        }
        //***********************************************************
        //***********************************************************
        return (comboOrganizado);
    }
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    public Map<String,String> obtenerListaTrimestresAnteriores()
    {
        //***********************************************************
        //***********************************************************
        int i = 0;
        Map<String,String> comboOrganizado = null;
        Map<String,String> comboEsquema = null;
        String idEsquema = "";
        String nombreEsquema = "";
        //***********************************************************
        //***********************************************************
        String [] trimestres =
        {
            "1","I - Primer Trimestre",
            "2","II - Segundo Trimestre",
            "3","III - Tercer Trimestre",
            "4","IV - Cuarto Trimestre"
        };
        //***********************************************************
        //***********************************************************
        //***********************************************************
        //***********************************************************
        try
        {
            comboEsquema = new HashMap<String, String>();
            //***********************************************************
            //***********************************************************
            for (i = 0;  i < trimestres.length;  i+=2)
            {
                idEsquema = trimestres[i];
                nombreEsquema = trimestres[i+1];
                comboEsquema.put(nombreEsquema,idEsquema);
            }
            //***********************************************************
            //***********************************************************
            comboOrganizado = new TreeMap<String, String>(comboEsquema);
            //***********************************************************
            //***********************************************************
            //logBeanWebReactivo.info  ("Longitud del vector de pojos de esquema = " + comboOrganizado.size() + " y vector = " + comboOrganizado);
        }
        //***********************************************************
        //***********************************************************
        catch (Exception errorobtenerListaEsquemas)
        {
            //logBeanWebReactivo.info("------------------------Error------------------------");
            //logBeanWebReactivo.info("No fue posible ejecutar el método obtenerListaEsquemas del Bean " + this.getClass().getCanonicalName() + ": " + errorobtenerListaEsquemas.getMessage());
            //logBeanWebReactivo.info("------------------------Fin Error------------------------");
            errorobtenerListaEsquemas.printStackTrace();
        }
        //***********************************************************
        //***********************************************************
        return (comboEsquema);
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
    public void confirmarObligatoriedad() 
    {
        String resp = "";
        String resp1 = "";

        resp = revisarErrores();
        //logBeanWebReactivo.info ("*****************************************************************");
        //logBeanWebReactivo.info ("*****************************************************************");
        //logBeanWebReactivo.info ("RESPUESTA DE LA VALIDACIÓN INICIAL = " + resp);
        //logBeanWebReactivo.info ("*****************************************************************");
        //logBeanWebReactivo.info ("*****************************************************************");
        if (resp.compareTo("si") == 0) 
        {
            resp1 = confirmarObligatorios();
            //logBeanWebReactivo.info ("*****************************************************************");
            //logBeanWebReactivo.info ("*****************************************************************");
            //logBeanWebReactivo.info ("RESPUESTA DE CONFIRMACION DE OBLIGATORIOS = " + resp1);
            //logBeanWebReactivo.info ("*****************************************************************");
            //logBeanWebReactivo.info ("*****************************************************************");
            //****************************************************************************
            //****************************************************************************
            if (resp1.compareTo("si") == 0) 
            {
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exitoso","Se pueden almacenar los datos del Reporte en Cero"));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exitoso","Generación del Nuevo Registro"));
                setVisibleConfirmacion(true);
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error","Se han encontrado campos sin diligenciar en el formulario"));
            }
            //****************************************************************************
            //****************************************************************************
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error","Se han encontrado errores en los tipos de datos proporcionados"));
        }
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    private String confirmarObligatorios() {

        String respuesta = "si";
        try 
        {
            //*********************************************************
            //*********************************************************
            if (getPeriodoReporte().equals("0"))
            {
                setObligatorio1(true);
                respuesta = "no";
            }
            else
            {
                setObligatorio1(false);
            }
            //*********************************************************
            //*********************************************************
            if (getYearReporte().equals(""))
            {
                setObligatorio2(true);
                respuesta = "no";
            }
            else
            {
                setObligatorio2(false);
            }
            //*********************************************************
            //*********************************************************
            if (getObservacionReportante().equals(""))
            {
                setObligatorio3(true);
                respuesta = "no";
            }
            else
            {
                setObligatorio3(false);
            }
            //*********************************************************
            //*********************************************************
        } 
        
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return respuesta;
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    private String revisarErrores() 
    {
        String respuesta = "si";
        setMensajeError("");
        boolean errorPeriodo = false;
        boolean errorYearReporte = false;
        java.util.Date fechaActual = null;
        fechaActual = new java.util.Date();
        //**************************************************************
        //**************************************************************
        //**************************************************************
        //logBeanWebReactivo.info ("PERIODO DEL REPORTE = " + getPeriodoReporte());
        if (getPeriodoReporte() != null) 
        {
            if (getPeriodoReporte().length() > 0)
            {
                if (getPeriodoReporte().equals("0")) 
                {
                    errorPeriodo = true;
                    setMensajeError("Debe seleccionar un período de reporte masivo en Cero");
                    respuesta = "no";
                }
            }
            else
            {
                errorPeriodo = true;
                setMensajeError("Debe seleccionar un período del reporte masivo");
                respuesta = "no";
            }
        }
        else
        {
            errorPeriodo = true;
            setMensajeError("Debe seleccionar un período del reporte masivo");
            respuesta = "no";
        }
        //**************************************************************
        //**************************************************************
        //**************************************************************
        //logBeanWebReactivo.info ("AÑO DEL REPORTE = " + getYearReporte());
        if (getYearReporte() != null)
        {
            if (getYearReporte().length() > 0)
            {
                if (Integer.valueOf(getYearReporte()) > (fechaActual.getYear()+1900)) 
                {
                       errorYearReporte = true;
                       setMensajeError("La año del reporte de período no puede ser mayor que el año en curso");
                       respuesta = "no";
                }
            }
            else
            {
                errorYearReporte = true;
                setMensajeError("Debe ingresar un año de reporte en Cero");
                respuesta = "no";
            }
        }
        else
        {
                errorYearReporte = true;
                setMensajeError("Debe ingresar un año de reporte en Cero");
                respuesta = "no";
        }
        //**************************************************************
        //**************************************************************
        //**************************************************************
        //logBeanWebReactivo.info ("Se encontro error de errorPeriodo = " + errorPeriodo);
        //logBeanWebReactivo.info ("Se encontro error de errorYearReporte = " + errorYearReporte);
        //**************************************************************
        //**************************************************************
        if (errorPeriodo == true || errorYearReporte == true) 
        {
            setMostrarError(true);
        }
        
        return respuesta;
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    public void grabar()
    {
        String nombreInstitucionL;
        String nitETSReportanteL;
        String periodoReporteL;
        String yearReporteL;
        String observacionReportanteL;
        boolean seCreoRegistro = false;
        ReactivoReporteCeroVO registroNuevoReporteCero = null;
        java.util.Date fechaReporteEfectiva = null;
        String secuencialRadicado = "";
        java.util.Date fechaRegistro = null;
        int cantidadRegistro = 0;
        String resultado = "";
        String nombreUsuario = "";
        userSesion = null;
        java.util.ArrayList<Object> resultados = null;
        String notificacion = "";
        String periodo = "";
        
        boolean sePuedeRegistrar = false;
        java.util.ArrayList<String> divipola = null;
        String depto = "";
        String municipio = "";
        
        try
        {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession sesionUsuario = request.getSession();
            userSesion = (UsuariosVO)sesionUsuario.getAttribute("usuario");
            nombreUsuario = userSesion.getUsuario();
            //logBeanWebReactivo.info ("USUARIO EN SESION = " + nombreUsuario);
            //****************************************************************************************************
            //****************************************************************************************************
            fechaRegistro = new java.util.Date();
            nombreInstitucionL = this.getNombreInstitucion();
            nitETSReportanteL = this.getNitETSReportante();
            periodoReporteL = this.getPeriodoReporte();
            yearReporteL = this.getYearReporte();
            observacionReportanteL = this.getObservacionReportante();
            //****************************************************************************************************
            //****************************************************************************************************
            /*
            //logBeanWebReactivo.info ("SE GUARDARÁ COMO EXTEMPORANEO = " + this.reportaExtemporeaneo);
            if (this.reportaExtemporeaneo == true)
            {
                notificacion = "extemporaneo";
            }
            else
            {
                notificacion = "oportuno";
            }
            */
            sePuedeRegistrar = getServicioEJBReactivoReporteCero().verificarRegistroTrimestreReporteCero(periodoReporteL,yearReporteL,this.getIpsReportante());
            java.util.Date fechaGeneracionReporte = new java.util.Date();
            //****************************************************************************************************
            //****************************************************************************************************
            logBeanWebReactivo.info ("SE PUEDE GRABAR EL REGISTRO: " + sePuedeRegistrar);
            //****************************************************************************************************
            //****************************************************************************************************
            if (sePuedeRegistrar == false)
            {
                logBeanWebReactivo.info ("SE PUEDE ALMACENAR EL REPORTE EN CERO: " + nombreUsuario);
                notificacion = generarMarcaExtemporaneidad(fechaRegistro,periodoReporteL);
                divipola = getServicioEJBReactivoReporteCero().obtenerDivipolaUsuarioReporteCero(nombreUsuario);
                depto = divipola.get(0);
                municipio = divipola.get(1);
                //****************************************************************************************************
                //****************************************************************************************************
                //logBeanWebReactivo.info ("*******************************************************************");
                //logBeanWebReactivo.info ("*******************************************************************");
                //logBeanWebReactivo.info ("*******************************************************************");
                //logBeanWebReactivo.info ("DATOS DEL FORMULARIO DE REPORTE EN CERO");
                //logBeanWebReactivo.info ("nombreInstitucionL = " + nombreInstitucionL);
                //logBeanWebReactivo.info ("nitETSReportanteL = " + nitETSReportanteL);
                //logBeanWebReactivo.info ("periodoReporteL = " + periodoReporteL);
                //logBeanWebReactivo.info ("yearReporteL = " + yearReporteL);
                //logBeanWebReactivo.info ("observacionReportanteL = " + observacionReportanteL);
                //logBeanWebReactivo.info ("El reporte es extemporaneo = " + notificacion);
                //****************************************************************************************************
                //****************************************************************************************************
                fechaReporteEfectiva = new java.util.Date(Integer.valueOf(yearReporteL)-1900,generarMesPeriodoReporte(periodoReporteL)-1,1);
                /*
                secuencialRadicado = String.valueOf(fechaRegistro.getYear()+1900) + "-" + String.valueOf(fechaRegistro.getMonth()) + "-" + 
                                     String.valueOf(fechaRegistro.getDay()) + String.valueOf(System.currentTimeMillis());
                */
                //****************************************************************************************************
                //****************************************************************************************************
                //****************************************************************************************************
                //****************************************************************************************************
                registroNuevoReporteCero = new ReactivoReporteCeroVO();
                registroNuevoReporteCero.setFecha_actualiza(fechaGeneracionReporte);
                registroNuevoReporteCero.setFecha_reporte(fechaReporteEfectiva);
                registroNuevoReporteCero.setObservacion(observacionReportanteL);
                registroNuevoReporteCero.setRadicadocero(secuencialRadicado);
                registroNuevoReporteCero.setTrimestre(periodoReporteL);
                registroNuevoReporteCero.setUsuario_id(nombreUsuario);
                registroNuevoReporteCero.setYear_trimestre(yearReporteL);
                registroNuevoReporteCero.setNotificacion(notificacion);
                registroNuevoReporteCero.setDepto(depto);
                registroNuevoReporteCero.setMunicipio(municipio);
                //****************************************************************************************************
                //****************************************************************************************************
                //logBeanWebReactivo.info ("*******************************************************************");
                //logBeanWebReactivo.info ("*******************************************************************");
                resultados = getServicioEJBReactivoReporteCero().crearRegistroReporteCero(registroNuevoReporteCero);
                secuencialRadicado = (String)resultados.get(0);
                seCreoRegistro = (Boolean)resultados.get(1);
                //****************************************************************************************************
                //****************************************************************************************************
                logBeanWebReactivo.info ("SECUENCIAL RADICADO PARA EL REPORTE EN CERO DE REACTIVO = " + secuencialRadicado);
                logBeanWebReactivo.info ("Resultado de la creación = " + seCreoRegistro);
                //******************************************************************************************
                //******************************************************************************************
                if (seCreoRegistro == true)
                {
                    resultado = " fue creado exitosamente";
                    enviarCorreoElectronicoNotificacionReporteCero(true,this.correoReportante,fechaGeneracionReporte, 
                    secuencialRadicado, yearReporteL, obtenerPeriodo(periodoReporteL), observacionReportanteL, this.getNitETSReportante() + " - " + this.getNombreInstitucion(),notificacion);
                    //**************************************************************************************
                    //**************************************************************************************
                }
                else
                {
                    resultado = " no pudo ser creado.  Verifique sus datos";
                }
                //******************************************************************************************
                //******************************************************************************************
                //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                //logBeanWebReactivo.info ("RESETEANDO CAMPOS ANTES DEL GROWL");
                this.setPeriodoReporte("0");
                this.setYearReporte(String.valueOf(fechaGeneracionReporte.getYear()+1900));
                this.setReportaExtemporeaneo(false);
                this.setObservacionReportante("");
                periodo = obtenerPeriodoDeAcuerdoFecha(fechaRegistro);
                this.setYearsReportado(actualizarYearsCombos(false,periodo));
                //logBeanWebReactivo.info ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                //******************************************************************************************
                //******************************************************************************************
                if (seCreoRegistro == true)
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exitoso","El registro del Reporte en Cero " + resultado + " con Número de Radicado " + secuencialRadicado + ".  El reporte se hizo de forma " + notificacion.toUpperCase()));
                }
                else
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fallido","El registro del Reporte en Cero " + resultado));
                }
                //******************************************************************************************
                //******************************************************************************************
            }
            else
            {
                //******************************************************************************************
                //******************************************************************************************
                //logBeanWebReactivo.info ("RESETEANDO CAMPOS ANTES DEL GROWL");
                this.setPeriodoReporte("0");
                this.setYearReporte(String.valueOf(fechaGeneracionReporte.getYear()+1900));
                this.setReportaExtemporeaneo(false);
                this.setObservacionReportante("");
                periodo = obtenerPeriodoDeAcuerdoFecha(fechaRegistro);
                this.setYearsReportado(actualizarYearsCombos(false,periodo));
                //logBeanWebReactivo.info ("EL REPORTE EN CERO YA ESTÁ REGISTRADO");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error","El registro del Reporte en Cero no puede ser registrado, puesto que ya se encuentra almacenado"));
                //******************************************************************************************
                //******************************************************************************************
            }
            //******************************************************************************************
            //******************************************************************************************
            RequestContext.getCurrentInstance().update("panelMensajes");
            //logBeanWebReactivo.info ("ACTUALICE LOS COMPONENTES EN LA VISTA");
            //******************************************************************************************
            //******************************************************************************************
        }
        
        catch (Exception error)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fallido","El registro del Reporte no pudo ser almacenado: " + error.getMessage()));
            //logBeanWebReactivo.info("Error en el almacenamiento de archivos: " + error.getMessage());
            error.printStackTrace();
        }
    }
    //*************************************************************************************
    //*************************************************************************************
    //*************************************************************************************
    public Map<String,String> actualizarYearsCombos (boolean valorMarca, String trimestre)
    {
        //***********************************************************
        //***********************************************************
        int i = 0;
        Map<String,String> comboEsquema = null;
        String idEsquema = "";
        String nombreEsquema = "";
        //***********************************************************
        //***********************************************************
        //***********************************************************
        //***********************************************************
        java.util.Date fechaActual = null;
        java.util.Date fechaAnterior = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        try
        {
            //***********************************************************
            //***********************************************************
            fechaActual = new java.util.Date();
            fechaAnterior = new java.util.Date(fechaActual.getYear()-1,0,1);
            //logBeanWebReactivo.info ("FECHA ANTERIOR = " + dateFormat.format(fechaAnterior));
            //logBeanWebReactivo.info ("FECHA ACTUAL = " + dateFormat.format(fechaActual));
            comboEsquema = new HashMap<String, String>();
            //***********************************************************
            //***********************************************************
            /*
            if (valorMarca == true)
            {
                //logBeanWebReactivo.info ("SE MARCARÁ REPORTE EXTEMPORÁNEO");
                idEsquema = String.valueOf(fechaAnterior.getYear()+1900);
                nombreEsquema = idEsquema;
                comboEsquema.put(nombreEsquema,idEsquema);
                idEsquema = String.valueOf(fechaActual.getYear()+1900);
                nombreEsquema = idEsquema;
                comboEsquema.put(nombreEsquema,idEsquema);
            }
            else
            {
                //logBeanWebReactivo.info ("SE MARCARÁ REPORTE EN AÑO EN CURSO");
                idEsquema = String.valueOf(fechaActual.getYear()+1900);
                nombreEsquema = idEsquema;
                comboEsquema.put(nombreEsquema,idEsquema);
            }
            */
            
            //logBeanWebReactivo.info ("Trimestre para activar el año = " + trimestre);
            
            if (trimestre.equals ("1"))
            {
                //logBeanWebReactivo.info ("Se debe registrar trimestre del año anterior " + ((fechaAnterior.getYear()+1900)));
                idEsquema = String.valueOf((fechaAnterior.getYear()+1900));
                nombreEsquema = idEsquema;
                comboEsquema.put(nombreEsquema,idEsquema);
            }
            else
            {
                //logBeanWebReactivo.info ("Se debe registrar trimestre del año actual = " + (fechaActual.getYear()+1900));
                idEsquema = String.valueOf((fechaActual.getYear()+1900));
                nombreEsquema = idEsquema;
                comboEsquema.put(nombreEsquema,idEsquema);
            }
                
            //***********************************************************
            //***********************************************************
            //logBeanWebReactivo.info  ("Longitud del vector de pojos de esquema = " + comboEsquema.size() + " y vector = " + comboEsquema);
        }
        //***********************************************************
        //***********************************************************
        catch (Exception errorobtenerListaEsquemas)
        {
            //logBeanWebReactivo.info("------------------------Error------------------------");
            //logBeanWebReactivo.info("No fue posible ejecutar el método obtenerListaEsquemas del Bean " + this.getClass().getCanonicalName() + ": " + errorobtenerListaEsquemas.getMessage());
            //logBeanWebReactivo.info("------------------------Fin Error------------------------");
            errorobtenerListaEsquemas.printStackTrace();
        }
        //***********************************************************
        //***********************************************************
        return (comboEsquema);
    }
    //*************************************************************************************
    //*************************************************************************************
    //*************************************************************************************
    public String obtenerPeriodoDeAcuerdoFecha (java.util.Date fechaSistema)
    {
        String periodo = "";
        int year = 0;
        int mes = 0;
        int dia = 0;
        
        java.util.Date fechaInicial1Trimestre = null;
        java.util.Date fechaFinal1Trimestre = null;
        java.util.Date fechaInicial2Trimestre = null;
        java.util.Date fechaFinal2Trimestre = null;
        java.util.Date fechaInicial3Trimestre = null;
        java.util.Date fechaFinal3Trimestre = null;
        java.util.Date fechaInicial4Trimestre = null;
        java.util.Date fechaFinal4Trimestre = null;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        try
        {
            //******************************************************
            //******************************************************
            year = fechaSistema.getYear()+1900;
            mes = fechaSistema.getMonth();
            dia = fechaSistema.getDate();
            //******************************************************
            //******************************************************
            //logBeanWebReactivo.info ("DIA = " + dia + " - MES = " + mes + " - AÑO = " + year);
            //******************************************************
            //******************************************************
            fechaInicial1Trimestre = new java.util.Date(fechaSistema.getYear(),0,1);
            fechaFinal1Trimestre = new java.util.Date(fechaSistema.getYear(),2,31);
            
            fechaInicial2Trimestre = new java.util.Date(fechaSistema.getYear(),3,1);
            fechaFinal2Trimestre = new java.util.Date(fechaSistema.getYear(),5,30);
            
            fechaInicial3Trimestre = new java.util.Date(fechaSistema.getYear(),6,1);
            fechaFinal3Trimestre = new java.util.Date(fechaSistema.getYear(),8,30);
            
            fechaInicial4Trimestre = new java.util.Date(fechaSistema.getYear(),9,1);
            fechaFinal4Trimestre = new java.util.Date(fechaSistema.getYear(),11,31);
            //******************************************************
            //******************************************************
            //******************************************************
            //******************************************************
            //logBeanWebReactivo.info ("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            //logBeanWebReactivo.info ("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            //logBeanWebReactivo.info ("Fecha actual = " + dateFormat.format(fechaSistema));
            //logBeanWebReactivo.info ("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            //logBeanWebReactivo.info ("Fecha inicial 1 trimestre = " + dateFormat.format(fechaInicial1Trimestre) + " - Fecha final 1 trimestre = " + dateFormat.format(fechaFinal1Trimestre));
            //logBeanWebReactivo.info ("Fecha inicial 2 trimestre = " + dateFormat.format(fechaInicial2Trimestre) + " - Fecha final 2 trimestre = " + dateFormat.format(fechaFinal2Trimestre));
            //logBeanWebReactivo.info ("Fecha inicial 3 trimestre = " + dateFormat.format(fechaInicial3Trimestre) + " - Fecha final 3 trimestre = " + dateFormat.format(fechaFinal3Trimestre));
            //logBeanWebReactivo.info ("Fecha inicial 4 trimestre = " + dateFormat.format(fechaInicial4Trimestre) + " - Fecha final 4 trimestre = " + dateFormat.format(fechaFinal4Trimestre));
            //logBeanWebReactivo.info ("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            //logBeanWebReactivo.info ("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            //******************************************************
            //******************************************************
            //******************************************************
            //******************************************************
            
            if ((dia >= fechaInicial1Trimestre.getDate() && dia <= fechaFinal1Trimestre.getDate()) &&
                 (mes>= fechaInicial1Trimestre.getMonth() && mes <= fechaFinal1Trimestre.getMonth()))
                  {
                      periodo = "1";
                  }
            else
            if ((dia >= fechaInicial2Trimestre.getDate() && dia <= fechaFinal2Trimestre.getDate()) &&
                 (mes>= fechaInicial2Trimestre.getMonth() && mes <= fechaFinal2Trimestre.getMonth()))
                  {
                      periodo = "2";
                  }
            else
            if ((dia >= fechaInicial3Trimestre.getDate() && dia <= fechaFinal3Trimestre.getDate()) &&
                 (mes>= fechaInicial3Trimestre.getMonth() && mes <= fechaFinal3Trimestre.getMonth()))
                  {
                      periodo = "3";
                  }
            else
            {
                     periodo = "4";
            }
            //******************************************************
            //******************************************************
            //logBeanWebReactivo.info ("La fecha actual = " + dateFormat.format(fechaSistema) + " es del período " + periodo);
            //******************************************************
            //******************************************************
        }
        
        catch (Exception errorPeriodo)
        {
            //logBeanWebReactivo.info ("Error del período = " + errorPeriodo.getLocalizedMessage());
            errorPeriodo.printStackTrace();
            return ("1");
        }
        
        return (periodo);
    }
    //*************************************************************************************
    //*************************************************************************************
    //*************************************************************************************
    public String generarMarcaExtemporaneidad(java.util.Date fechaSistema, String periodo)
    {
        String marca = "";
        java.util.Date fechaInicial = null;
        java.util.Date fechaFinal = null;
        int dia = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        int semanaReporte = 0;
        
        try
        {
            //********************************************************
            //********************************************************
            //logBeanWebReactivo.info ("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
            dia = fechaSistema.getDate();
            //logBeanWebReactivo.info ("DIA DE LA FECHA = " + dia);
            //logBeanWebReactivo.info ("MES DEL TRIMESTRE = " + fechaSistema.getMonth());
            //********************************************************
            //********************************************************
            if (periodo.equals("1"))
            {
                //logBeanWebReactivo.info ("Evaluando cuarto trimestre año anterior");
                fechaInicial = new java.util.Date(fechaSistema.getYear(),9,1);
                fechaFinal = new java.util.Date(fechaSistema.getYear(),11,31);
                
                if ((dia >= 1 && dia <= 8) && fechaSistema.getMonth() == 3)
                {
                    //logBeanWebReactivo.info ("Estamos en la primera semana del mes de reportaje de abril");
                    semanaReporte = 1;
                }
                else
                {
                    //logBeanWebReactivo.info ("Estamos en el mes de " + fechaSistema.getMonth() + " fuera de la primera semana del mes valido que debe ser abril");
                }
            }
            else
            if (periodo.equals("2"))
            {
                //logBeanWebReactivo.info ("Evaluando primer trimestre año actual");
                fechaInicial = new java.util.Date(fechaSistema.getYear(),0,1);
                fechaFinal = new java.util.Date(fechaSistema.getYear(),2,31);
                
                if ((dia >= 1 && dia <= 8) && fechaSistema.getMonth() == 6)
                {
                    //logBeanWebReactivo.info ("Estamos en la primera semana del mes de reportaje de julio");
                    semanaReporte = 1;
                }
                else
                {
                    //logBeanWebReactivo.info ("Estamos en el mes de " + fechaSistema.getMonth() + " fuera de la primera semana del mes valido que debe ser julio");
                }
            }
            else
            if (periodo.equals("3"))
            {
                //logBeanWebReactivo.info ("Evaluando segundo trimestre año actual");
                fechaInicial = new java.util.Date(fechaSistema.getYear(),3,1);
                fechaFinal = new java.util.Date(fechaSistema.getYear(),5,30);
                
                if ((dia >= 1 && dia <= 8) && fechaSistema.getMonth() == 9)
                {
                    //logBeanWebReactivo.info ("Estamos en la primera semana del mes de reportaje de octubre");
                    semanaReporte = 1;
                }
                else
                {
                    //logBeanWebReactivo.info ("Estamos en el mes de " + fechaSistema.getMonth() + " fuera de la primera semana del mes valido que debe ser octubre");
                }
            }
            else
            if (periodo.equals("4"))
            {
                //logBeanWebReactivo.info ("Evaluando tercer trimestre año actual");
                fechaInicial = new java.util.Date(fechaSistema.getYear(),6,1);
                fechaFinal = new java.util.Date(fechaSistema.getYear(),8,30);
                
                if ((dia >= 1 && dia <= 8) && fechaSistema.getMonth() == 0)
                {
                    //logBeanWebReactivo.info ("Estamos en la primera semana del mes de reportaje de enero");
                    semanaReporte = 1;
                }
                else
                {
                    logBeanWebReactivo.info ("Estamos en el mes de " + fechaSistema.getMonth() + " fuera de la primera semana del mes valido que debe ser enero");
                }
            }
            //********************************************************
            //********************************************************
            //logBeanWebReactivo.info ("FECHA ENVIADA AL METODO DE GENERACION DE MARCA DE EXTEMPORANEIDAD = " + dateFormat.format(fechaSistema));
            //logBeanWebReactivo.info ("Trimestre actual sobre el que se analizarán los días = " + periodo);
            //logBeanWebReactivo.info ("MARCA QUE INDICA LA PRIMERA SEMANA DEL MES ACTIVO (1 PRIMERA SEMANA EN MES, 0 FUERA DEL MES VALIDO) = " + semanaReporte);
            //********************************************************
            //********************************************************
            if ((dia >= 1 && dia <= 8) && semanaReporte == 1)
            {
                //logBeanWebReactivo.info ("DÍA DEL LÍMITE DEL TRIMESTRE = " + fechaFinal.getDate());
                //logBeanWebReactivo.info ("MES LIMITE DEL TRIMESTRE = " + fechaFinal.getMonth());
                //logBeanWebReactivo.info ("La fecha del reporte " + dateFormat.format(fechaSistema) + " es oportuna, puesto que se ejecutó en la primera semana del mes de " + (fechaSistema.getMonth()-1));
                marca = "oportuna";
            }
            else
            {
                //logBeanWebReactivo.info ("DÍA DEL LÍMITE DEL TRIMESTRE = " + fechaFinal.getDate());
                //logBeanWebReactivo.info ("MES LIMITE DEL TRIMESTRE = " + fechaFinal.getMonth());
                //logBeanWebReactivo.info ("La fecha del reporte " + dateFormat.format(fechaSistema) + " es extemporánea, puesto que se ejecutó fuera de la primera semana del mes de " + (fechaSistema.getMonth()-1));
                marca = "extemporanea";
            }
            //********************************************************
            //********************************************************
            //logBeanWebReactivo.info ("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
            //********************************************************
            //********************************************************
            //********************************************************
            //********************************************************
            //********************************************************
            //********************************************************
            /*
            if (periodo.equals("1"))
            {
                fechaInicial = new java.util.Date(fechaSistema.getYear(),0,1);
                fechaFinal = new java.util.Date(fechaSistema.getYear(),2,31);
            }
            else
            if (periodo.equals("2"))
            {
                fechaInicial = new java.util.Date(fechaSistema.getYear(),3,1);
                fechaFinal = new java.util.Date(fechaSistema.getYear(),5,30);
            }
            else
            if (periodo.equals("3"))
            {
                fechaInicial = new java.util.Date(fechaSistema.getYear(),6,1);
                fechaFinal = new java.util.Date(fechaSistema.getYear(),8,30);
            }
            else
            {
                fechaInicial = new java.util.Date(fechaSistema.getYear(),9,1);
                fechaFinal = new java.util.Date(fechaSistema.getYear(),11,31);
            }
            
            //logBeanWebReactivo.info ("Periodo = " + periodo);
            //logBeanWebReactivo.info ("Fecha Inicial = " + fechaInicial.toLocaleString());
            //logBeanWebReactivo.info ("Fecha Inicial = " + fechaFinal.toLocaleString());
            //logBeanWebReactivo.info ("*********************************************************");
            //logBeanWebReactivo.info ("*********************************************************");
            //logBeanWebReactivo.info ("*********************************************************");
            
            if (fechaSistema.compareTo(fechaInicial) > 0 && fechaSistema.compareTo(fechaFinal) < 0)
            {
                //logBeanWebReactivo.info ("La fecha del reporte " + fechaSistema.toLocaleString() + " es oportuna");
                marca = "oportuna";
            }
            else
            {
                //logBeanWebReactivo.info ("La fecha del reporte " + fechaSistema.toLocaleString() + " es extemporánea");
                marca = "extemporaneo";
            }
            */
            //********************************************************
            //********************************************************
            //logBeanWebReactivo.info ("Marca del reporte = " + marca);
            //logBeanWebReactivo.info ("*********************************************************");
            //logBeanWebReactivo.info ("*********************************************************");
            //logBeanWebReactivo.info ("*********************************************************");
        }
        
        catch (Exception errorReporte)
        {
            //logBeanWebReactivo.info ("Error de extemporaneidad = " + errorReporte.getLocalizedMessage());
            return ("extemporaneo");
        }
        
        return (marca);
    }
    //*************************************************************************************
    //*************************************************************************************
    //*************************************************************************************
    public void resetearCamposForma()
    {
            //******************************************************************************
            //******************************************************************************
            java.util.Date fecha = new java.util.Date();
            //logBeanWebReactivo.info ("-------------------------------------------------------");
            //logBeanWebReactivo.info ("-------------------------------------------------------");
            //logBeanWebReactivo.info ("RESETEANDO CAMPOS FORMULARIO");
            this.setPeriodoReporte("0");
            this.setYearReporte("0");
            this.setReportaExtemporeaneo(false);
            this.setObservacionReportante("");
            //******************************************************************************
            //******************************************************************************
            //RequestContext.getCurrentInstance().update("frmactregistroUsuario:reportaExtemporeaneo");
            RequestContext.getCurrentInstance().update("frmactregistroUsuario:periodoReporte");
            RequestContext.getCurrentInstance().update("frmactregistroUsuario:yearReporte");
            RequestContext.getCurrentInstance().update("frmactregistroUsuario:observacionReportante");
            //logBeanWebReactivo.info ("-------------------------------------------------------");
            //logBeanWebReactivo.info ("-------------------------------------------------------");
            //******************************************************************************
            //******************************************************************************
    }
    //*************************************************************************************
    //*************************************************************************************
    //*************************************************************************************
    private String obtenerPeriodo (String idPeriodo)
    {
        String periodo = "";
        
        switch (idPeriodo)
        {
            case "1":
                        periodo = "I - Primer Trimestre";
                        break;
            case "2":
                        periodo = "II - Segundo Trimestre";
                        break;
            case "3":
                        periodo = "III - Tercer Trimestre";
                        break;
            case "4":
                        periodo = "IV - Cuarto Trimestre";
                        break;
        }
        
        return (periodo);
    }
    //*************************************************************************************
    //*************************************************************************************
    //*************************************************************************************
    public String cancelar() 
    {
        //logBeanWebReactivo.info ("**************************************************************");
        //logBeanWebReactivo.info ("**************************************************************");
        //logBeanWebReactivo.info ("Se cancelo el envio del formulario");
        //logBeanWebReactivo.info ("**************************************************************");
        //logBeanWebReactivo.info ("**************************************************************");
        limpiarCampos();
        return "Principal.xhtml";
    }
    //*************************************************************************************
    //*************************************************************************************
    //*************************************************************************************
    private void limpiarCampos() 
    {
        //********************************************************************
        //********************************************************************
        //********************************************************************
        setAlertas("");
        setVisibleConfirmacion(false);
        //********************************************************************
        //********************************************************************
        setPeriodoReporte("0");
        setYearReporte("");
        setObservacionReportante("");
        //********************************************************************
        //********************************************************************
        setObligatorio1(false);
        setObligatorio2(false);
        setObligatorio3(false);
        //********************************************************************
        //********************************************************************
        setMostrarError(false);
        setReportaExtemporeaneo(false);
        setMensajeError("");
    }
    //*************************************************************************************
    //*************************************************************************************
    //*************************************************************************************
    private int generarMesPeriodoReporte (String valor)
    {
        int mesReporte = 1;
        
        switch (valor)
        {
            case "1":
                        mesReporte = 1;
                        break;
            case "2":
                        mesReporte = 4;
                        break;
            case "3":
                        mesReporte = 7;
                        break;
            case "4":
                        mesReporte = 10;
                        break;
        }
                
        return (mesReporte);
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    public void enviarCorreoElectronicoNotificacionReporteCero(boolean resultado, String correoDestinatario, java.util.Date fechaReporte, String radicado, String yearReporte, String trimestre, String observacion, String datosInstitucion, String marcaExtemporaneidad)
    {
        try
        {
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //logBeanWebReactivo.info ("------------------------------------------------------------------------------");
            //logBeanWebReactivo.info ("SE GENERARÁ EL MENSAJE PARA ENVIO AL REPORTANTE PARA EL DESTINATARIO = " + correoDestinatario);
            //**********************************************************************************************
            //**********************************************************************************************
            PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
            String servidor = pr.getProperty("servidor");
            String remitente = pr.getProperty("remitente");
            String usuario = pr.getProperty("usuario");
            String passwd = pr.getProperty("password");
            String fecha = Fecha.formateaFecha(fechaReporte, "dd/MM/yyyy hh:mm a");
            String mensajeCorreo = "";

            if (resultado != false) 
            {
                //*********************************************************************************************************************
                //*********************************************************************************************************************
                mensajeCorreo = "<h1 style='font-family: verdana;'>SISTEMA DE GESTIÓN Y CONTROL DE REACTIVOS IN VITRO REACTIVOVIGILANCIA</h1>" +
                                "<br/><br/>" +
                                "<b><h2 style='font-family: verdana;'>NOTIFICACIÓN DE INGRESO DE REPORTE MASIVO EN CERO - RADICADO " + radicado + "</h2></b><br/>" +
                                "<p style='font-family: verdana;'>Su reporte ha sido ingresado al Sistema de Información del Programa Nacional de ReactivoVigilancia, a continuación se presenta un resumen del trámite efectuado: <br/><br/>" +
                                "<br/><b> Fecha y hora del ingreso:</b>  " + fecha + 
                                "<br/><b> Código asignado:</b>  " + radicado + 
                                "<br/><b>Año de Reporte:</b>  " + yearReporte + 
                                "<br/><b>Trimestre Reportado:</b>  " + trimestre + 
                                "<br/><b>Observación:</b> " + observacion +
                                "<br/><b>Nombre de la Institución:</b>  " + datosInstitucion + 
                                "<br/><b>Tipo de Registro: </b>  " + marcaExtemporaneidad.toUpperCase() + "</p><br/><br/>" +
                                "<p style='font-family: verdana;'><b>Agradecemos su atención de no contestar este correo ya que corresponde a un correo automático</b></p><br/><br/>";

                CorreoElectronico correo = CorreoElectronico.getInstance();
                logBeanWebReactivo.info ("ENVIANDO CORREO ELECTRONICO DE NOTIFICACIÓN AL DESTINATARIO " + correoDestinatario);
                correo.enviarCorreoElectronicoReactivo(correoDestinatario,"",
                        "Creación de Reporte Masivo en Cero - Radicado No. " + radicado, 
                        HtmlValidator.remplazarTildesEnTexto(mensajeCorreo),userSesion,"Reporte Masivo en Cero");
            }
        }
        
        catch (Exception errorEnvioCorreo)
        {
            errorEnvioCorreo.printStackTrace();
        }
    }

    public ServicioReporteCeroRemote getServicioEJBReactivoReporteCero() {
        return servicioEJBReactivoReporteCero;
    }

    /**
     * @param servicioEJBReactivoReporteCero the servicioEJBReactivoReporteCero to set
     */
    public void setServicioEJBReactivoReporteCero(ServicioReporteCeroRemote servicioEJBReactivoReporteCero) {
        this.servicioEJBReactivoReporteCero = servicioEJBReactivoReporteCero;
    }

    /**
     * @return the nombreInstitucion
     */
    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    /**
     * @param nombreInstitucion the nombreInstitucion to set
     */
    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    /**
     * @return the nitETSReportante
     */
    public String getNitETSReportante() {
        return nitETSReportante;
    }

    /**
     * @param nitETSReportante the nitETSReportante to set
     */
    public void setNitETSReportante(String nitETSReportante) {
        this.nitETSReportante = nitETSReportante;
    }

    /**
     * @return the periodoReporte
     */
    public String getPeriodoReporte() {
        return periodoReporte;
    }

    /**
     * @param periodoReporte the periodoReporte to set
     */
    public void setPeriodoReporte(String periodoReporte) {
        this.periodoReporte = periodoReporte;
    }

    /**
     * @return the yearReporte
     */
    public String getYearReporte() {
        return yearReporte;
    }

    /**
     * @param yearReporte the yearReporte to set
     */
    public void setYearReporte(String yearReporte) {
        this.yearReporte = yearReporte;
    }

    /**
     * @return the observacionReportante
     */
    public String getObservacionReportante() {
        return observacionReportante;
    }

    /**
     * @param observacionReportante the observacionReportante to set
     */
    public void setObservacionReportante(String observacionReportante) {
        this.observacionReportante = observacionReportante;
    }

    /**
     * @return the mensajeError
     */
    public String getMensajeError() {
        return mensajeError;
    }

    /**
     * @param mensajeError the mensajeError to set
     */
    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    /**
     * @return the alertas
     */
    public String getAlertas() {
        return alertas;
    }

    /**
     * @param alertas the alertas to set
     */
    public void setAlertas(String alertas) {
        this.alertas = alertas;
    }

    /**
     * @return the obligatorio1
     */
    public boolean isObligatorio1() {
        return obligatorio1;
    }

    /**
     * @param obligatorio1 the obligatorio1 to set
     */
    public void setObligatorio1(boolean obligatorio1) {
        this.obligatorio1 = obligatorio1;
    }

    /**
     * @return the obligatorio2
     */
    public boolean isObligatorio2() {
        return obligatorio2;
    }

    /**
     * @param obligatorio2 the obligatorio2 to set
     */
    public void setObligatorio2(boolean obligatorio2) {
        this.obligatorio2 = obligatorio2;
    }

    /**
     * @return the obligatorio3
     */
    public boolean isObligatorio3() {
        return obligatorio3;
    }

    /**
     * @param obligatorio3 the obligatorio3 to set
     */
    public void setObligatorio3(boolean obligatorio3) {
        this.obligatorio3 = obligatorio3;
    }

    /**
     * @return the visibleConfirmacion
     */
    public boolean isVisibleConfirmacion() {
        return visibleConfirmacion;
    }

    /**
     * @param visibleConfirmacion the visibleConfirmacion to set
     */
    public void setVisibleConfirmacion(boolean visibleConfirmacion) {
        this.visibleConfirmacion = visibleConfirmacion;
    }

    /**
     * @return the mostrarError
     */
    public boolean isMostrarError() {
        return mostrarError;
    }

    /**
     * @param mostrarError the mostrarError to set
     */
    public void setMostrarError(boolean mostrarError) {
        this.mostrarError = mostrarError;
    }

    /**
     * @return the usuarioSesion
     */
    public String getUsuarioSesion() {
        return usuarioSesion;
    }

    /**
     * @param usuarioSesion the usuarioSesion to set
     */
    public void setUsuarioSesion(String usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }
    /**
     * @return the periodos
     */
    public Map<String,String> getPeriodos() {
        return periodos;
    }

    /**
     * @param periodos the periodos to set
     */
    public void setPeriodos(Map<String,String> periodos) {
        this.periodos = periodos;
    }

    /**
     * @return the yearsReportado
     */
    public Map<String,String> getYearsReportado() {
        return yearsReportado;
    }

    /**
     * @param yearsReportado the yearsReportado to set
     */
    public void setYearsReportado(Map<String,String> yearsReportado) {
        this.yearsReportado = yearsReportado;
    }

    /**
     * @return the reportaExtemporeaneo
     */
    public boolean isReportaExtemporeaneo() {
        return reportaExtemporeaneo;
    }

    /**
     * @param reportaExtemporeaneo the reportaExtemporeaneo to set
     */
    public void setReportaExtemporeaneo(boolean reportaExtemporeaneo) {
        this.reportaExtemporeaneo = reportaExtemporeaneo;
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
     * @return the ipsReportante
     */
    public String getIpsReportante() {
        return ipsReportante;
    }

    /**
     * @param ipsReportante the ipsReportante to set
     */
    public void setIpsReportante(String ipsReportante) {
        this.ipsReportante = ipsReportante;
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    /*
    public void actualizarYearsReportado()
    {
        try
        {
            //logBeanWebReactivo.info ("***********************************************************");
            //logBeanWebReactivo.info ("***********************************************************");
            //logBeanWebReactivo.info ("***********************************************************");
            //logBeanWebReactivo.info ("SE MARCÓ COMO EXTEMPOREANEO = " + this.reportaExtemporeaneo);
            //logBeanWebReactivo.info ("***********************************************************");
            //logBeanWebReactivo.info ("***********************************************************");
            if (this.reportaExtemporeaneo == true)
            {
                this.yearsReportado = actualizarYearsCombos(this.reportaExtemporeaneo);
                this.periodos = obtenerListaTrimestresAnteriores();
            }
            else
            {
                this.yearsReportado = actualizarYearsCombos(false);
            }
            //logBeanWebReactivo.info ("***********************************************************");
            //logBeanWebReactivo.info ("***********************************************************");
            //logBeanWebReactivo.info ("***********************************************************");
        }
        
        catch (Exception errorReportado)
        {
            errorReportado.printStackTrace();
        }
    }
    */
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
}
