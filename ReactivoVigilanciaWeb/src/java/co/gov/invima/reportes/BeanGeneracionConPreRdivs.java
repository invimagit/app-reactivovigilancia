/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.reportes;

/**
 *
 * @author jgutierrezme
 */
import co.gov.invima.VO.UsuariosVO;
import co.gov.invima.negocio.ConsultasReactivoRemote;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import co.gov.invima.negocio.ServicioReporteTrimestralRemote;
import co.gov.invima.reactivo.dto.reports.ReportePrerdiv;
import co.gov.invima.service.ServiceLocator;
import org.apache.log4j.Logger;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/********************************************************************/
/********************************************************************/
/********************************************************************/
/********************************************************************/
/********************************************************************/
/********************************************************************/
@ViewScoped
@ManagedBean(name="beanGeneracionConPreRdivs")
//@RequestScoped
public class BeanGeneracionConPreRdivs implements Serializable  
{
    //****************************************************************************************
    //****************************************************************************************
    private final static Logger logBeanWebReactivo = Logger.getLogger(BeanGeneracionConPreRdivs.class.getName());
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    private static long serialVersionUID = -32825486496747532L;
    //****************************************************************************************
    //****************************************************************************************
    @EJB
    private ConsultasReactivoRemote servicioEJBReportesReactivoRemote;
    //****************************************************************************************
    //****************************************************************************************
    @EJB
    private ServicioReporteTrimestralRemote ejbServicioRemotoReporteMasivoTrimestral;
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    private java.util.Date fechaInicial;
    private java.util.Date fechaFinal;
    private ArrayList listaDepartamentos = new ArrayList();
    private ArrayList listaMunicipios = new ArrayList();
    //****************************************************************************************
    //****************************************************************************************
    private String departamento;
    private String municipio;
    private boolean esAdminstrador;
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    private List<ReportePrerdiv> datosReporteReactivo;
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    public BeanGeneracionConPreRdivs()
    {
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("Iniciando constructor del Managed Bean Web BeanGeneracionConPreRdivs");
        //logBeanWebReactivo.info("Conectandose al contexto");
        ServiceLocator service = new ServiceLocator();
        servicioEJBReportesReactivoRemote = service.getEJBReportesReactivo();
        //logBeanWebReactivo.info("Estado del EJB DEL REPORTE PARA EL MANAGED BEAN: " + this.servicioEJBReportesReactivoRemote);
        ejbServicioRemotoReporteMasivoTrimestral = service.getEjbRemotoReactivoReporteTrimestral();
        //logBeanWebReactivo.info("Estado del EJB DE REPORTE TRIMESTRAL CON EVENTO: " + this.ejbServicioRemotoReporteMasivoTrimestral);
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    @PostConstruct
    public void init() 
    {
        //***********************************************************
        //***********************************************************
        List<String> departamentos = null;
        String idRolAprobador = "";
        String idDepto = "";
        UsuariosVO userSesion = null;
        //***********************************************************
        //***********************************************************
        try
        {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession sesionUsuario = request.getSession();
            userSesion = (UsuariosVO)sesionUsuario.getAttribute("usuario");
            //logBeanWebReactivo.info ("USUARIO PARA LA BUSQUEDA DE PRECOLS = " + userSesion);
            if (userSesion != null)
            {
                //logBeanWebReactivo.info ("USUARIO CORRECTO DE LA SESIÓN");
                idDepto = userSesion.getCodDepart();
                idRolAprobador = userSesion.getIdRol();
            }
            else
            {
                //logBeanWebReactivo.info ("USUARIO DE EMERGENCIA ADMINISTRATIVO INVIMA");
                idDepto = "11";
                idRolAprobador = "1";
            }
            
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            if (idRolAprobador.equals("1"))
            {
                this.esAdminstrador = true;
                logBeanWebReactivo.info ("MOSTRANDO TODOS LOS DEPARTAMENTOS");
                departamentos = getEjbServicioRemotoReporteMasivoTrimestral().obtenerListadoDepartamentos();
            }
            else
            {
                this.esAdminstrador = false;
                logBeanWebReactivo.info ("MOSTRANDO SOLO EL DEPTO DE MI SECRETARIA");
                departamentos = getEjbServicioRemotoReporteMasivoTrimestral().obtenerListadoDepartamentos(idDepto);
            }
            //**************************************************************************************
            //**************************************************************************************
            logBeanWebReactivo.info ("TIPO DE REPORTE COMO ADMINISTRADOR: " + this.esAdminstrador);
            
            //**************************************************************************************
            //**************************************************************************************
            //**************************************************************************************
            //**************************************************************************************
            this.setListaDepartamentos(armarItemsCombo(departamentos));
            //**************************************************************************************
            //**************************************************************************************
            this.setDatosReporteReactivo(new ArrayList<ReportePrerdiv>());
            //**************************************************************************************
            //**************************************************************************************
            //**************************************************************************************
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
    public void generarReporte()
    {
        String depto = "";
        String municip = "";
        String fechaIn = null;
        String fechaFin = null;
        
        try
        {
            //logBeanWebReactivo.info  ("****************************************************");
            //logBeanWebReactivo.info  ("****************************************************");
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest myRequest = (HttpServletRequest)context.getExternalContext().getRequest();
            HttpSession mySession = myRequest.getSession();     
            //******************************************************************
            //******************************************************************
            //logBeanWebReactivo.info  ("****************************************************");
            //logBeanWebReactivo.info ("REQUEST = " + myRequest.getParameterMap());
            //logBeanWebReactivo.info  ("****************************************************");
            //logBeanWebReactivo.info  ("****************************************************");
            /*
            //logBeanWebReactivo.info ("FECHA 1 = " + myRequest.getParameter("formularioReportes:fechaInicial_input"));
            //logBeanWebReactivo.info ("FECHA 2 = " + myRequest.getParameter("formularioReportes:fechaFinal_input"));
            //logBeanWebReactivo.info ("FECHA 3 = " + this.getFechaInicial());
            //logBeanWebReactivo.info ("FECHA 4 = " + this.getFechaFinal());
            */
            
            fechaIn = myRequest.getParameter("frmReportePreRDivsBusqueda:fechaInicial_input");
            fechaFin = myRequest.getParameter("frmReportePreRDivsBusqueda:fechaFinal_input");
            depto = myRequest.getParameter("frmReportePreRDivsBusqueda:departamento_input");
            municip = myRequest.getParameter("frmReportePreRDivsBusqueda:municipio_input");
            
            logBeanWebReactivo.info ("DEPARTAMENTO = " + depto);
            logBeanWebReactivo.info ("MUNICIPIO = " + municip);
            logBeanWebReactivo.info ("FECHA INICIAL DEL REPORTE = " + fechaIn);
            logBeanWebReactivo.info ("FECHA FINAL DEL REPORTE = " + fechaFin);
            
            
            /*
            logBeanWebReactivo.info ("DEPARTAMENTO = " + this.departamento);
            logBeanWebReactivo.info ("MUNICIPIO = " + this.municipio);
            logBeanWebReactivo.info ("FECHA INICIAL DEL REPORTE = " + this.fechaInicial);
            logBeanWebReactivo.info ("FECHA FINAL DEL REPORTE = " + this.fechaFinal);
            */
            //******************************************************************
            //******************************************************************
            //******************************************************************
            //******************************************************************
            if (depto != null && depto.length() > 0)
            {
                this.setDepartamento(depto);
            }
            
            if (municip != null && municip.length() > 0)
            {
                this.setMunicipio(municip);
            }
            //******************************************************************
            //******************************************************************
            if (fechaIn != null && fechaIn.length() > 0)
            {
                this.setFechaInicial(new java.util.Date(fechaIn));
            }
            else
            {
                this.setFechaInicial(null);
            }
            //******************************************************************
            //******************************************************************
            if (fechaFin != null && fechaFin.length() > 0)
            {
                this.setFechaFinal(new java.util.Date(fechaFin));
            }
            else
            {
                this.setFechaFinal(null);
            }
            //******************************************************************
            //******************************************************************
            //logBeanWebReactivo.info ("---------------------------------------------------------");
            //logBeanWebReactivo.info ("---------------------------------------------------------");
            //logBeanWebReactivo.info ("DEPARTAMENTO: " + this.getDepartamento());
            //logBeanWebReactivo.info ("MUNICIPIO: " + this.getMunicipio());
            //logBeanWebReactivo.info ("FECHA 1 = " + this.getFechaInicial());
            //logBeanWebReactivo.info ("FECHA 1 = " + this.getFechaFinal());
            //logBeanWebReactivo.info ("---------------------------------------------------------");
            //logBeanWebReactivo.info ("---------------------------------------------------------");
            //******************************************************************
            //******************************************************************
            this.setDatosReporteReactivo(servicioEJBReportesReactivoRemote.generarReportePreRDivs(this.departamento,this.municipio,this.fechaInicial,this.fechaFinal));
            //******************************************************************
            //******************************************************************
            if (this.getDatosReporteReactivo()!= null)
            {
                //logBeanWebReactivo.info ("TAMAÑO DEL REPORTE = " + this.getDatosReporteReactivo().size());
            }
            //******************************************************************
            //******************************************************************
            //logBeanWebReactivo.info  ("****************************************************");
            //logBeanWebReactivo.info  ("****************************************************");
        }
        
        catch (Exception errorobtenerIdProgramaVivienda)
        {
            //logBeanWebReactivo.info("------------------------Error------------------------");
            //logBeanWebReactivo.info("No fue posible ejecutar el método generarReporte del Bean " + this.getClass().getCanonicalName() + ": " + errorobtenerIdProgramaVivienda.getMessage());
            //logBeanWebReactivo.info("------------------------Fin Error------------------------");
            errorobtenerIdProgramaVivienda.printStackTrace();
        }
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
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
            ciudades = getEjbServicioRemotoReporteMasivoTrimestral().obtenerListadoMunicipios(codigo);
            this.setListaMunicipios(armarItemsCombo(ciudades));
            //**********************************************************************
            //**********************************************************************
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
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
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
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    public void limpiarCampos()
    {
        try
        {
            //logBeanWebReactivo.info  ("****************************************************");
            //logBeanWebReactivo.info  ("****************************************************");
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest myRequest = (HttpServletRequest)context.getExternalContext().getRequest();
            //logBeanWebReactivo.info ("RESETEANDO CAMPOS DEL REPORTE");
            //******************************************************************
            //******************************************************************
            this.setFechaInicial(null);
            this.setFechaFinal(null);
            this.datosReporteReactivo = null;
            //******************************************************************
            //******************************************************************
            //logBeanWebReactivo.info  ("****************************************************");
            //logBeanWebReactivo.info  ("****************************************************");
        }
        
        catch (Exception errorobtenerIdProgramaVivienda)
        {
            //logBeanWebReactivo.info("------------------------Error------------------------");
            //logBeanWebReactivo.info("No fue posible ejecutar el método limpiarCampos del Bean " + this.getClass().getCanonicalName() + ": " + errorobtenerIdProgramaVivienda.getMessage());
            //logBeanWebReactivo.info("------------------------Fin Error------------------------");
            errorobtenerIdProgramaVivienda.printStackTrace();
        }
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    public String obtenerNombreMedidaEjecutadaPantalla (String codigoMedida)
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
            default:
                    valor = "No Disponible";
                    break;
        }
        
        return (valor);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    public String obtenerNombreTipoReportePantalla (String codigoTipo)
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
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    public String obtenerNombreCausaProbablePantalla (String codigo)
    {
        String nombreCausa = "";
        nombreCausa = getEjbServicioRemotoReporteMasivoTrimestral().obtenerNombreCausaProbablePorCodigo(codigo);
        return (nombreCausa);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    /**
     * @return the servicioEJBReportesReactivoRemote
     */
    public ConsultasReactivoRemote getServicioEJBReportesReactivoRemote() {
        return servicioEJBReportesReactivoRemote;
    }

    /**
     * @param servicioEJBReportesReactivoRemote the servicioEJBReportesReactivoRemote to set
     */
    public void setServicioEJBReportesReactivoRemote(ConsultasReactivoRemote servicioEJBReportesReactivoRemote) {
        this.servicioEJBReportesReactivoRemote = servicioEJBReportesReactivoRemote;
    }

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
     * @return the datosReporteReactivo
     */
    public List<ReportePrerdiv> getDatosReporteReactivo() {
        return datosReporteReactivo;
    }

    /**
     * @param datosReporteReactivo the datosReporteReactivo to set
     */
    public void setDatosReporteReactivo(List<ReportePrerdiv> datosReporteReactivo) {
        this.datosReporteReactivo = datosReporteReactivo;
    }
    
    
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************

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
     * @return the esAdminstrador
     */
    public boolean isEsAdminstrador() {
        return esAdminstrador;
    }

    /**
     * @param esAdminstrador the esAdminstrador to set
     */
    public void setEsAdminstrador(boolean esAdminstrador) {
        this.esAdminstrador = esAdminstrador;
    }
    
    
}