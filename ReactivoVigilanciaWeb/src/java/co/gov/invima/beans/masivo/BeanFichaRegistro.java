/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.beans.masivo;

import co.gov.invima.entities.ReactivoEventosTemp;
import co.gov.invima.entities.ReactivoPacienteTemp;
import co.gov.invima.entities.ReactivoReportanteTemp;
import co.gov.invima.entities.ReactivoGestionrealizadaTemp;
import co.gov.invima.entities.ReactivoCamposcompTemp;
import co.gov.invima.entities.ReactivoInstitucionTemp;
import co.gov.invima.entities.ReactivoProductoTemp;
import co.gov.invima.negocio.ServicioReporteTrimestralRemote;
import java.io.Serializable;
import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jgutierrezme
 */
//@ViewScoped
@SessionScoped
@ManagedBean(name="beanFichaRegistro")
public class BeanFichaRegistro implements Serializable
{
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private static Logger logBeanWebReactivo = Logger.getLogger(BeanFichaRegistro.class.getName());
    private static long serialVersionUID = -328254864967157566L;
    //********************************************************************
    //********************************************************************
    //********************************************************************
    @EJB
    private ServicioReporteTrimestralRemote ejbServicioReporteTrimestral;
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
    private String nombreDepto;
    private String nombreMunicipio;
    private String nombreDeptoReporte;
    private String nombreMunReporte;
    private String nombreNaturaleza;
    private String nombreTipoIdentificacion;
    private String nombreEdadEn;
    private String nombreDeteccion;
    private String nombreDesenlace;
    private String nombreCausaProbable;
    private String nombreProfesion;
    private String nombreTipoReportante;
    private String nombreTipoDispositivo;
    private String nombreEstadoReporte;
    private String nombreEstadoGestion;
    private String nombreArea;
    private String nombreDescripcionCausa;
    private String nombreTipoReactivoInvitro;  
    //********************************************************************    
    //********************************************************************    
    //********************************************************************    
    //********************************************************************    
    //********************************************************************    
    //********************************************************************    
    //********************************************************************    
    //********************************************************************    
    @PostConstruct
    public void init() 
    {
        /*
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sesionUsuario = request.getSession();
        String nombreUsuario = "";
        */
        //***********************************************************
        //***********************************************************
        try
        {
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("EJECUTANDO BEAN FICHA DE CONSULTA");
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
    //********************************************************************    
    //********************************************************************    
    //********************************************************************    
    public void consultarDatosParaFichaProyecto()
    {
        String id_registro = "";
        String urlPopup = "";
        try
        {
            //logBeanWebReactivo.info  ("------------------------------------------------->>>>");
            //logBeanWebReactivo.info  ("------------------------------------------------->>>>");
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest myRequest = (HttpServletRequest)context.getExternalContext().getRequest();
            //logBeanWebReactivo.info  ("OBTENIENDO HIPERVÍNCULO PARA CONSULTAR BEAN");
            //logBeanWebReactivo.info  ("id = " + myRequest.getParameterMap());
            //***************************************************************
            //***************************************************************
            id_registro = myRequest.getParameter("id_registro");
            //logBeanWebReactivo.info  ("--> id proyecto consultar nueva ventana = " + id_registro);
            //***************************************************************
            //***************************************************************
            consultarRegistroCargueMasivo(id_registro);
            //***************************************************************
            //***************************************************************
            urlPopup = "abrirVentanaModificacionSD('" + id_registro + "','reporteBusquedaPrecols','fichaConsultaRegistro.xhtml')";
            //logBeanWebReactivo.info ("Abrir popup desde el managed bean");
            RequestContext.getCurrentInstance().execute(urlPopup);
            //***************************************************************
            //***************************************************************
            //logBeanWebReactivo.info  ("------------------------------------------------->>>>");
            //logBeanWebReactivo.info  ("------------------------------------------------->>>>");
        }
        
        catch (Exception error)
        {
            error.printStackTrace();
        }
    }
    //********************************************************************    
    //********************************************************************    
    //********************************************************************    
    //********************************************************************    
    //********************************************************************    
    //********************************************************************    
    public void consultarRegistroCargueMasivo(String idRegistro)
    {
        //********************************************************************
        //********************************************************************
        //********************************************************************
        //FacesContext context = FacesContext.getCurrentInstance();
        //HttpServletRequest myRequest = (HttpServletRequest)context.getExternalContext().getRequest();
        String expediente = "";
        //HttpSession mySession = myRequest.getSession();     
        //********************************************************************
        try
        {
            //logBeanWebReactivo.info ("EJECUTANDO EL MÉTODO consultarRegistroCargueMasivo");
           //id_registroPrecolSesion = (String)myRequest.getParameter("id_registro");
            //logBeanWebReactivo.info ("*********************************************************");
            //logBeanWebReactivo.info ("*********************************************************");
            //logBeanWebReactivo.info ("ID REGISTRO PRECOL EN SESION = " + idRegistro);
            //logBeanWebReactivo.info ("*********************************************************");
            //logBeanWebReactivo.info ("*********************************************************");
            //**********************************************************************************
            //**********************************************************************************
            //**********************************************************************************
            expediente = getEjbServicioReporteTrimestral().obtenerCodigoRegistroSanitarioPorPrecol(idRegistro);
            //logBeanWebReactivo.info ("PRECOL = " + idRegistro + " - EXPEDIENTE = " + expediente);
            //**********************************************************************************
            //**********************************************************************************
            //**********************************************************************************
            setDatosReactivoEventosTemp(ejbServicioReporteTrimestral.consultarRegistroReactivoEventosTemporal(idRegistro));
            setDatosReactivoPacienteTemp(ejbServicioReporteTrimestral.consultarRegistroReactivoPacienteTemporal(idRegistro));
            setDatosReactivoReportanteTemp(ejbServicioReporteTrimestral.consultarRegistroReactivoReportanteTemporal(idRegistro));
            setDatosReactivoGestionrealizadaTemp(ejbServicioReporteTrimestral.consultarRegistroReactivoGestionRealizadaTemporal(idRegistro));
            setDatosReactivoCamposcompTemp(ejbServicioReporteTrimestral.consultarRegistroReactivoCamposComplementariosTemporal(idRegistro));
            setDatosReactivoInstitucionTemp(ejbServicioReporteTrimestral.consultarRegistroReactivoInstitucionTemporal(idRegistro));
            setDatosReactivoProductoTemp(ejbServicioReporteTrimestral.consultarRegistroReactivoProductoTemporal(idRegistro));
            //**********************************************************************************
            //**********************************************************************************
            //**********************************************************************************
            //Datos complementarios
            this.setNombreDepto(getEjbServicioReporteTrimestral().obtenerNombreDeptoPorCodigo(getDatosReactivoInstitucionTemp().getCodDepart()));
            this.setNombreMunicipio(getEjbServicioReporteTrimestral().obtenerNombreMunicipioPorCodigo(getDatosReactivoInstitucionTemp().getCodMun()));
            this.setNombreNaturaleza(obtenerNaturaleza(getDatosReactivoInstitucionTemp().getNaturaleza()));
            //**********************************************************************************
            //**********************************************************************************
            this.setNombreTipoIdentificacion(getEjbServicioReporteTrimestral().obtenerNombreTipoIdentificacionReportantePorCodigo(getDatosReactivoPacienteTemp().getTipidentifi()));
            this.setNombreEdadEn(obtenerEdadEn(getDatosReactivoPacienteTemp().getEdadEn()));
            this.setNombreDesenlace(getEjbServicioReporteTrimestral().obtenerNombreTipoDesenlacePorCodigo(String.valueOf(getDatosReactivoEventosTemp().getCdgDesenlace())));
            //**********************************************************************************
            //**********************************************************************************
            //this.setNombreDeteccion(obtenerNombreDeteccion(String.valueOf(getRegistroTecnoReporteEventosTemp().getCdgEventodeteccion())));
            this.setNombreDesenlace(getEjbServicioReporteTrimestral().obtenerNombreTipoDesenlacePorCodigo(String.valueOf(getDatosReactivoEventosTemp().getCdgDesenlace())));
            //**********************************************************************************
            //**********************************************************************************
            this.setNombreCausaProbable(getEjbServicioReporteTrimestral().obtenerNombreCausaProbablePorCodigo(String.valueOf(getDatosReactivoGestionrealizadaTemp().getCausaProbable())));
            this.setNombreDescripcionCausa(getEjbServicioReporteTrimestral().obtenerNombreDescripcionCausaEfecto(getDatosReactivoGestionrealizadaTemp().getDescripcionCausa()));
            //**********************************************************************************
            //**********************************************************************************
            this.setNombreProfesion(getDatosReactivoReportanteTemp().getProfesion());
            this.setNombreDeptoReporte(getEjbServicioReporteTrimestral().obtenerNombreDeptoPorCodigo(getDatosReactivoReportanteTemp().getCodDepart()));
            this.setNombreMunReporte(getEjbServicioReporteTrimestral().obtenerNombreMunicipioPorCodigo(getDatosReactivoReportanteTemp().getCodMun()));
            //**********************************************************************************
            //**********************************************************************************
            this.setNombreTipoReportante(getEjbServicioReporteTrimestral().obtenerNombreTipoDeReportantePorCodigo(String.valueOf(getDatosReactivoCamposcompTemp().getCdgTiporeportante())));
            this.setNombreEstadoReporte(obtenerNombreEstadoReporte(getDatosReactivoCamposcompTemp().getEstadoReporte()));
            //this.setNombreTipoDispositivo(getEjbServicioReporteTrimestral().obtenerNombreTipoDispositivo(String.valueOf(getRegistroTecnoDispositivoTemp().getCdgTipodispositivo())));
           //**********************************************************************************
           //**********************************************************************************
           //logBeanWebReactivo.info ("ID DE TIPO DE REACTIVO IN VITRO: " + getDatosReactivoCamposcompTemp().getCodigoTipoDiagnostico());
           //logBeanWebReactivo.info ("TIPO DE REACTIVO INVITRO: " +ejbServicioReporteTrimestral.obtenerNombreTipoDispositivo(getDatosReactivoCamposcompTemp().getCodigoTipoDiagnostico()));
           this.setNombreTipoReactivoInvitro(ejbServicioReporteTrimestral.obtenerNombreTipoDispositivo(getDatosReactivoCamposcompTemp().getCodigoTipoDiagnostico()));
           //**********************************************************************************
           //**********************************************************************************
           this.setNombreArea(obtenerNombreArea(String.valueOf(getDatosReactivoProductoTemp().getAreaFunciona())));
           this.setNombreEstadoGestion(obtenerNombreEstadoReporte(getDatosReactivoCamposcompTemp().getEstadoGestionEnte()));
           //**********************************************************************************
           //**********************************************************************************
           //**********************************************************************************
           //**********************************************************************************
           //**********************************************************************************
           //**********************************************************************************
           //logBeanWebReactivo.info ("----------------------------------------------------------------------------");
           //logBeanWebReactivo.info ("----------------------------------------------------------------------------");
           //logBeanWebReactivo.info ("Registro setDatosReactivoEventosTemp = " + this.getDatosReactivoEventosTemp());
           //logBeanWebReactivo.info ("Registro setDatosReactivoPacienteTemp =  " + this.getDatosReactivoPacienteTemp());
           //logBeanWebReactivo.info ("Registro setDatosReactivoReportanteTemp = " + this.getDatosReactivoReportanteTemp());
           //logBeanWebReactivo.info ("Registro setDatosReactivoGestionrealizadaTemp =  " + this.getDatosReactivoGestionrealizadaTemp());
           //logBeanWebReactivo.info ("Registro setDatosReactivoCamposcompTemp =  " + this.getDatosReactivoCamposcompTemp());
           //logBeanWebReactivo.info ("Registro setDatosReactivoInstitucionTemp =  " + this.getDatosReactivoInstitucionTemp());
           //logBeanWebReactivo.info ("Registro setDatosReactivoProductoTemp = " + this.getDatosReactivoProductoTemp());
           //logBeanWebReactivo.info ("----------------------------------------------------------------------------");
           //logBeanWebReactivo.info ("----------------------------------------------------------------------------");
           //**********************************************************************************
           //**********************************************************************************
           //**********************************************************************************
        }
        
        catch (Exception errorInit)
        {
            errorInit.printStackTrace();
        }
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obtenerNombreEstadoReporte(int valor)
    {
        String nombre = "";
        
        //logBeanWebReactivo.info ("VALOR DE ID ESTADO: " + valor);
        
        try
        {
            switch (valor)
            {
                case 1:  nombre = "S";  break;
                case 2:  nombre = "C";  break;
                case 3:  nombre = "A";  break;
            }

        }
        
        catch (Exception error)
        {
            error.printStackTrace();
            return ("A");
        }
        
        return (nombre);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public String obteneNombreSiNoCodigoCausa (String valor)
    {
        String resultado = "";
        
        if (valor.equals("1"))
        {
            resultado = "Si";
        }
        else
        {
            resultado = "No";
        }
        
        return (resultado);
    }
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
    public String obtenerNombreHerramienta (String codigoTipo)
    {
        String nombre = "";
        
        switch (codigoTipo)
        {
            case "1":
                nombre = "Protocolo de Londres";
                break;
            case "2":        
                nombre = "AMFE";
                break;
            case "3":
                nombre = "Espina de pescado";
                break;
            default:
                nombre = "Otro/cual";
        }
        
        return (nombre);
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
   public String obtenerNombreArea (String codigoTipo)
    {
        String nombre = "";
        
        switch (codigoTipo)
        {
            case "1":
                nombre = "Laboratorio clinico";
                break;
            case "2":        
                nombre = "Laboratorio salud pública";
                break;
            case "3":
                nombre = "Servicio transfusional";
                break;
            case "4":
                nombre = "Banco de Sangre";
                break;
            case "5":
                nombre = "Banco de Tejidos";
                break;
            default:
                nombre = "Banco de Gametos";
                break;
        }
        
        return (nombre);
    }
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
    public String obtenerNombreDeteccion (String codigo)    
    {
        String valor = "";
        
        if (codigo.equals("1"))
        {
            valor = "Antes del Uso del DM";
        }
        else
        if (codigo.equals("2"))
        {
            valor = "Durante el Uso del DM";
        }
        else
        {
            valor = "Después del Uso del DM";
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
            valor = "Sí";
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
    //************************************************************************
    //************************************************************************
    //************************************************************************
    public String formatearFechaSalida (java.util.Date fecha)
    {
        String fechaSalida = "";
        
        fechaSalida = organizarCeroFecha(fecha.getDate()) + "/" + 
                            organizarCeroFecha(fecha.getMonth()+1) + "/" +
                            String.valueOf (fecha.getYear()+1900);
        
        return (fechaSalida);
    }
    //************************************************************************
    //************************************************************************
    //************************************************************************
    private String organizarCeroFecha (int dato)
    {
        String numero = "";
        
        if (dato < 10)
        {
            numero = "0" + String.valueOf(dato);
        }
        else
        {
            numero = String.valueOf(dato);
        }
        
        return (numero);
    }
    //************************************************************************
    //************************************************************************
    //************************************************************************
    
    
    //************************************************************************
    //************************************************************************
    //************************************************************************
    //************************************************************************
    //************************************************************************
    //************************************************************************
    /**
     * @return the ejbServicioReporteTrimestral
     */
    public ServicioReporteTrimestralRemote getEjbServicioReporteTrimestral() {
        return ejbServicioReporteTrimestral;
    }
    
    /**
     * @param ejbServicioReporteTrimestral the ejbServicioReporteTrimestral to set
     */
    public void setEjbServicioReporteTrimestral(ServicioReporteTrimestralRemote ejbServicioReporteTrimestral) {
        this.ejbServicioReporteTrimestral = ejbServicioReporteTrimestral;
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
     * @return the nombreArea
     */
    public String getNombreArea() {
        return nombreArea;
    }

    /**
     * @param nombreArea the nombreArea to set
     */
    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    /**
     * @return the nombreEstadoReporte
     */
    public String getNombreEstadoReporte() {
        return nombreEstadoReporte;
    }

    /**
     * @param nombreEstadoReporte the nombreEstadoReporte to set
     */
    public void setNombreEstadoReporte(String nombreEstadoReporte) {
        this.nombreEstadoReporte = nombreEstadoReporte;
    }

    /**
     * @return the nombreEstadoGestion
     */
    public String getNombreEstadoGestion() {
        return nombreEstadoGestion;
    }

    /**
     * @param nombreEstadoGestion the nombreEstadoGestion to set
     */
    public void setNombreEstadoGestion(String nombreEstadoGestion) {
        this.nombreEstadoGestion = nombreEstadoGestion;
    }

    /**
     * @return the nombreDescripcionCausa
     */
    public String getNombreDescripcionCausa() {
        return nombreDescripcionCausa;
    }

    /**
     * @param nombreDescripcionCausa the nombreDescripcionCausa to set
     */
    public void setNombreDescripcionCausa(String nombreDescripcionCausa) {
        this.nombreDescripcionCausa = nombreDescripcionCausa;
    }

    /**
     * @return the nombreTipoReactivoInvitro
     */
    public String getNombreTipoReactivoInvitro() {
        return nombreTipoReactivoInvitro;
    }

    /**
     * @param nombreTipoReactivoInvitro the nombreTipoReactivoInvitro to set
     */
    public void setNombreTipoReactivoInvitro(String nombreTipoReactivoInvitro) {
        this.nombreTipoReactivoInvitro = nombreTipoReactivoInvitro;
    }
    //************************************************************************
    //************************************************************************
    //************************************************************************
    //************************************************************************
    //************************************************************************
    //************************************************************************
    //************************************************************************
    //************************************************************************
    //************************************************************************
    //************************************************************************
    //************************************************************************
    //************************************************************************
}
