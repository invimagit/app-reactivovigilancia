/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.negocio;
/******************************************************************/
/******************************************************************/
/******************************************************************/
/******************************************************************/
/******************************************************************/
/******************************************************************/
import co.gov.invima.sesion.ReactivoReportesCeroFacadeLocal;
import co.gov.invima.sesion.ReactivoUsuariosInternetFacadeLocal;
import co.gov.invima.entities.ReactivoReportesCero;
import co.gov.invima.VO.ReactivoRepCeroConsultaVO;
import co.gov.invima.VO.ReactivoReporteCeroVO;
import co.gov.invima.entities.ReactivoUsuariosInternet;
import co.gov.invima.reactivo.dto.reports.PersonaInternet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
/******************************************************************/
/******************************************************************/
/******************************************************************/
/******************************************************************/
/******************************************************************/
/******************************************************************/
/**
 *
 * @author Ingeniero Jaime Alberto Gutiérrez Mejía
 * OFICINA DE TECNOLOGÍAS DE LA INFORMACIÓN (OTI)
 * Proyecto de Rediseño del Sistema de ReactivoVigilancia
 * 
 * Febrero 2018
 */
/******************************************************************/
/******************************************************************/
/******************************************************************/
/******************************************************************/
@Stateless
public class ServicioReporteCero implements ServicioReporteCeroRemote 
{
    //*******************************************************************************************
    //*******************************************************************************************
    private final static Logger logEJBReactivo = Logger.getLogger(ServicioReporteCero.class.getName());
    //*******************************************************************************************
    //*******************************************************************************************
    @EJB
    private ReactivoReportesCeroFacadeLocal ejbReactivoReporteCero;  
    //*******************************************************************************************
    //*******************************************************************************************
    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager manejadorEntidadesDireccion;
    //*******************************************************************************************
    //*******************************************************************************************
    @EJB
    private ReactivoUsuariosInternetFacadeLocal ejbReactivoUsuariosInternet;
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    private java.sql.Connection obtenerConexionDataSource()
    {
        java.sql.Connection conexionBD = null;
        try
        {
            DataSource dataSourceJBoss = null;
            Context ctx = new InitialContext();
            dataSourceJBoss = (DataSource)ctx.lookup("java:/jboss/ReactivoVigilancia");
            //logEJBReactivo.info ("Datasource del contenedor: " + dataSourceJBoss);
            conexionBD = dataSourceJBoss.getConnection();
        }

        catch (SQLException errorConexionSQL)
        {
            errorConexionSQL.printStackTrace();
        }
        
        catch (NamingException errorConexionDS)
        {
            errorConexionDS.printStackTrace();
        }
        return (conexionBD);
    }        
    //*******************************************************************************************
    //*******************************************************************************************
    @Override
    //public boolean crearRegistroReporteCero(ReactivoReporteCeroVO registroReporteCero) 
    public java.util.ArrayList<Object> crearRegistroReporteCero(ReactivoReporteCeroVO registroReporteCero) 
    {
        boolean seCreoReporte = false;
        ReactivoReportesCero registro = null;
        //long id = 1L;
        int id = 0;
        String radicado = "";
        java.util.Date fechaSistema = null;
        java.util.ArrayList<Object> registros = new java.util.ArrayList<Object>();
        
        try
        {
            //logEJBReactivo.info ("***************************************************************************************************************");
            //logEJBReactivo.info ("***************************************************************************************************************");
            //logEJBReactivo.info ("***************************************************************************************************************");
            //logEJBReactivo.info ("************************************* EJECUTANDO crearRegistroReporteCero *************************************");
            fechaSistema = new java.util.Date();
            //logEJBReactivo.info ("FECHA DE SISTEMA = " + fechaSistema);
            //id = ejbReactivoReporteCero.buscarMaxId();
            id = getEjbReactivoReporteCero().buscarMaxId();
            radicado = String.valueOf(fechaSistema.getYear()+1900) + "-" +
                       String.valueOf(fechaSistema.getMonth()+1) + "-" +
                       arreglarDia(fechaSistema.getDate()) + "-" + String.valueOf(id+1);
            
            //************************************************************************
            //************************************************************************
            /*
            ReactivoReportesCeroPK llavePrimariaReporteCero = new ReactivoReportesCeroPK();
            llavePrimariaReporteCero.setRadicadocero(radicado);
            */
            //llavePrimariaReporteCero.setIdregistro(1);
            //registro.setReactivoReportesCeroPK(llavePrimariaReporteCero);
            //************************************************************************
            //************************************************************************
            //************************************************************************
            //************************************************************************
            //************************************************************************
            //************************************************************************
            //registro.setIdregistro(1);
            registro = new ReactivoReportesCero();
            registro.setRadicadocero(radicado);
            registro.setFechaActualiza(registroReporteCero.getFecha_actualiza());
            registro.setFechaReporte(registroReporteCero.getFecha_reporte());
            registro.setObservacion(registroReporteCero.getObservacion());
            registro.setTrimestre(registroReporteCero.getTrimestre());
            registro.setUsuarioId(registroReporteCero.getUsuario_id());
            registro.setYearTrimestre(Short.valueOf(registroReporteCero.getYear_trimestre()));
            registro.setNotificacion(registroReporteCero.getNotificacion());
            registro.setDepto(registroReporteCero.getDepto());
            registro.setMunicipio(registroReporteCero.getMunicipio());
            //************************************************************************
            //************************************************************************
            //************************************************************************
            //************************************************************************
            //logEJBReactivo.info ("OBJETO PARA ENVIAR A LA BD = " + registro);
            getEjbReactivoReporteCero().create(registro);
            seCreoReporte = true;
            //******************************************************************************************
            //******************************************************************************************
            //******************************************************************************************
            if (seCreoReporte == true)
            {
                registros.add(radicado);
                registros.add(new Boolean(seCreoReporte));
            }
            else
            {
                registros.add("SIN RADICADO ASIGNADO");
                registros.add(new Boolean(false));
            }
            //******************************************************************************************
            //******************************************************************************************
            //******************************************************************************************
            //logEJBReactivo.info ("Se creo el registro del reporte con radicado " + radicado + ": " + seCreoReporte);
            //logEJBReactivo.info ("***************************************************************************************************************");
            //logEJBReactivo.info ("***************************************************************************************************************");
            //logEJBReactivo.info ("***************************************************************************************************************");
        }
        
        catch (Exception errorReporteCero)
        {
            //logEJBReactivo.info ("Error en el método crearRegistroReporteCero = " + errorReporteCero.getMessage());
            errorReporteCero.printStackTrace();
            registros.add("SIN RADICADO ASIGNADO");
            registros.add(new Boolean(false));
        }
        
        return (registros);
    }
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    private String arreglarDia (int valor)
    {
        String numero = "";
        
        if (valor < 10)
        {
            numero = "0" + String.valueOf(valor);
        }
        else
        {
            numero = String.valueOf(valor);
        }
        
        return (numero);
    }
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    @Override
    public List<ReactivoReporteCeroVO> obtenerListadoReportesCeroReportante (String reportante)
    {
        List<ReactivoReporteCeroVO> registros = null;
        List<ReactivoReportesCero> registrosBD = null;
        int i = 0;
        String usuario = "";
        ReactivoReportesCero registroIndividual = null;
        ReactivoReporteCeroVO registroInsertar = null;
        
        try
        {
            //logEJBReactivo.info ("EJECUTANDO CONSULTA DE REPORTES EN CERO DEL USUARIO = " + reportante);
            registrosBD = getEjbReactivoReporteCero().findAll();
            
            registros = new java.util.ArrayList<ReactivoReporteCeroVO>();
            for (i = 0;  i < registrosBD.size();  i++)
            {
               registroIndividual = (ReactivoReportesCero)registrosBD.get(i);
               usuario = registroIndividual.getUsuarioId();
               
               if (usuario.equals(reportante))
               {
                    //****************************************************************************
                    //****************************************************************************
                    registroInsertar = new ReactivoReporteCeroVO();
                    //****************************************************************************
                    //****************************************************************************
                    registroInsertar.setFecha_actualiza(registroIndividual.getFechaActualiza());
                    registroInsertar.setFecha_reporte(registroIndividual.getFechaReporte());
                    registroInsertar.setObservacion(registroIndividual.getObservacion());
                    registroInsertar.setTrimestre(registroIndividual.getTrimestre());
                    registroInsertar.setUsuario_id(registroIndividual.getUsuarioId());
                    registroInsertar.setYear_trimestre(String.valueOf(registroIndividual.getYearTrimestre()));
                    registroInsertar.setIdregistro(String.valueOf(registroIndividual.getIdregistro()));
                    registroInsertar.setRadicadocero(registroIndividual.getRadicadocero());
                    //****************************************************************************
                    //****************************************************************************
                    registros.add(registroInsertar);
                    //****************************************************************************
                    //****************************************************************************
               }//Fin del if
            }//Fin del for
            
        }//Fin del try
        
        catch (Exception errorLista)
        {
            errorLista.printStackTrace();
            return (null);
        }
        
        return (registros);
    }
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    public ReactivoReporteCeroVO consultarRegistroReporteCero(String radicado) 
    {
        ReactivoReportesCero registro = null;
        ReactivoReporteCeroVO reporteSalida = null;
        
        try
        {
            //logEJBReactivo.info ("***************************************************************************************************************");
            //logEJBReactivo.info ("***************************************************************************************************************");
            //logEJBReactivo.info ("***************************************************************************************************************");
            //logEJBReactivo.info ("************************************* EJECUTANDO consultarRegistroReporteCero *************************************");
            registro = getEjbReactivoReporteCero().find(radicado);
            reporteSalida = new ReactivoReporteCeroVO();
            //***************************************************************************************************
            //***************************************************************************************************
            reporteSalida.setFecha_actualiza(registro.getFechaActualiza());
            reporteSalida.setFecha_reporte(registro.getFechaReporte());
            reporteSalida.setObservacion(registro.getObservacion());
            reporteSalida.setTrimestre(registro.getTrimestre());
            reporteSalida.setUsuario_id(registro.getUsuarioId());
            reporteSalida.setYear_trimestre(String.valueOf(registro.getYearTrimestre()));
            reporteSalida.setIdregistro(String.valueOf(registro.getIdregistro()));
            reporteSalida.setRadicadocero(registro.getRadicadocero());
            //***************************************************************************************************
            //***************************************************************************************************
            //logEJBReactivo.info ("***************************************************************************************************************");
            //logEJBReactivo.info ("***************************************************************************************************************");
            //logEJBReactivo.info ("OBJETO A RETORNAR DE LA LLAVE " + radicado + ": " + reporteSalida);
            //logEJBReactivo.info ("***************************************************************************************************************");
            //logEJBReactivo.info ("***************************************************************************************************************");
            //logEJBReactivo.info ("***************************************************************************************************************");
        }
        
        catch (Exception errorReporteCero)
        {
            //logEJBReactivo.info ("Error en el método crearRegistroReporteCero = " + errorReporteCero.getMessage());
            errorReporteCero.printStackTrace();
            return (null);
        }
        
        return (reporteSalida);
    }
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    @Override
    public PersonaInternet obtenerDatosETSUsuarioSesion(String username) 
    {
        PersonaInternet registro = null;
        ReactivoUsuariosInternet registroBusqueda = null;
        try
        {
            //logEJBReactivo.info ("****************************************************************");
            //logEJBReactivo.info ("****************************************************************");
            //logEJBReactivo.info ("****************************************************************");
            registroBusqueda = getEjbReactivoUsuariosInternet().find(username);
            //logEJBReactivo.info ("USUARIO TRAIDO = " + registroBusqueda.getUsuario());
            registro = new PersonaInternet();
            //********************************************************************
            //********************************************************************
            if (registroBusqueda.getNombreEmpresa() != null)
            {
                registro.setNombre_empresa(registroBusqueda.getNombreEmpresa());
                registro.setIdentificacion_empresa(String.valueOf(registroBusqueda.getIdentificacionEmpresa()));
            }
            else
            {
                registro.setNombre_empresa("USUARIO ADMINISTRADOR INVIMA");
                registro.setIdentificacion_empresa("830.000.167-2");
            }
            //********************************************************************
            //********************************************************************
            //logEJBReactivo.info ("****************************************************************");
            //logEJBReactivo.info ("****************************************************************");
            //logEJBReactivo.info ("****************************************************************");
        }
        
        catch (Exception errorUsuario)
        {
            errorUsuario.printStackTrace();
            registro = new PersonaInternet();
        }
        
        return (registro);
    }
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    @Override
    public List<ReactivoRepCeroConsultaVO> generarConsultaReportesMasivosCero(Date fechaInicial, Date fechaFinal) 
    {
        List<ReactivoRepCeroConsultaVO> reporteDatos = null;  
        ReactivoRepCeroConsultaVO registroIndividual = null;
        
        try
        {
            //logEJBReactivo.info ("****----------------------------------------------");
            //logEJBReactivo.info ("****----------------------------------------------");
            //logEJBReactivo.info ("EJECUTANDO EL MÉTODO EN EL EJB generarConsultaReportesMasivosCero");
            //logEJBReactivo.info ("****----------------------------------------------");
            //logEJBReactivo.info ("****----------------------------------------------");
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("*-********************************************************");
            //logEJBReactivo.info ("*-********************************************************");
            //logEJBReactivo.info ("VALOR = " + getManejadorEntidadesDireccion().getProperties());
            //logEJBReactivo.info ("*-********************************************************");
            //logEJBReactivo.info ("*-********************************************************");
            Query query1 = getManejadorEntidadesDireccion().createNamedQuery("consultaReportesEnCero");
            //***************************************************************
            //***************************************************************
            fechaInicial.setHours(0);
            fechaInicial.setMinutes(0);
            fechaInicial.setSeconds(0);
            //***************************************************************
            //***************************************************************
            fechaFinal.setHours(23);
            fechaFinal.setMinutes(59);
            fechaFinal.setSeconds(59);
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("fechaInicial = " + fechaInicial);
            //logEJBReactivo.info ("fechaFinal = " + fechaFinal);
            //***************************************************************
            //***************************************************************
            //***************************************************************
            //***************************************************************
            query1.setParameter(1,fechaInicial);
            query1.setParameter(2,fechaFinal);
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("OBJETO DEL QUERY = "+ query1.toString());
            //logEJBReactivo.info ("TAMAÑO DEL QUERY = "+ query1.getMaxResults());
            List<Object> cursor = query1.getResultList();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                reporteDatos = new ArrayList<ReactivoRepCeroConsultaVO>();
                //logEJBReactivo.info ("TAMAÑO DEL CURSOR = " + cursor.size());
                Iterator iteradorRegistros = cursor.iterator();
                
                while (iteradorRegistros.hasNext())
                {
                    Object[] tupla = (Object[]) iteradorRegistros.next();  
                    registroIndividual = new ReactivoRepCeroConsultaVO();
                    //********************************************************************
                    //********************************************************************
                    /*
                    registroIndividual.setNumero_radicado(String.valueOf(tupla[0]));
                    registroIndividual.setRazon_social(String.valueOf(tupla[1]));
                    registroIndividual.setNit_empresa(String.valueOf(tupla[2]));
                    registroIndividual.setDepartamento(String.valueOf(tupla[3]));
                    registroIndividual.setMunicipio(String.valueOf(tupla[4]));
                    registroIndividual.setTrimestre_reportado(String.valueOf(tupla[5]));
                    registroIndividual.setYear_trimestre(String.valueOf(tupla[6]));
                    registroIndividual.setTipo_actor(String.valueOf(tupla[7]));
                    registroIndividual.setNotificacion(String.valueOf(tupla[8]));
                    registroIndividual.setFecha_radicado(String.valueOf(tupla[9]));
                    registroIndividual.setObservacion_reportante(String.valueOf(tupla[10]));
                    */
                    //********************************************************************
                    //********************************************************************
                    registroIndividual.setRadicado(String.valueOf(tupla[0]));
                    registroIndividual.setEmpresa(String.valueOf(tupla[1]));
                    registroIndividual.setNit(String.valueOf(tupla[2]));
                    registroIndividual.setCod_dpto(String.valueOf(tupla[3]));
                    registroIndividual.setDepartamento(String.valueOf(tupla[4]));
                    registroIndividual.setCod_mun(String.valueOf(tupla[5]));
                    registroIndividual.setMunicipio(String.valueOf(tupla[6]));
                    registroIndividual.setTrimestre(String.valueOf(tupla[7]));
                    registroIndividual.setAnio_reporte(String.valueOf(tupla[8]));
                    registroIndividual.setTipo_actor(String.valueOf(tupla[9]));
                    registroIndividual.setNotificacion(String.valueOf(tupla[10]));
                    registroIndividual.setFecha_actualiza(String.valueOf(tupla[11]));
                    registroIndividual.setObservacion(String.valueOf(tupla[12]));
                    registroIndividual.setFecha_reporte(String.valueOf(tupla[13]));
                    registroIndividual.setIdregistro(String.valueOf(tupla[14]));
                    registroIndividual.setUsuario_id(String.valueOf(tupla[15]));
                    //********************************************************************
                    //********************************************************************
                    reporteDatos.add(registroIndividual);
                }
            }
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("---------------------------------------------->");
            //logEJBReactivo.info ("TOTAL DE REGISTROS DEL REPORTE BASADO EN NATIVE QUERY generarConsultaReportesMasivosCero = " + reporteDatos.size());
            //logEJBReactivo.info ("---------------------------------------------->");
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorGeneracionMUID)
        {
            //logEJBReactivo.info ("Error en la generación del método generarConsultaReportesMasivosCero = " + errorGeneracionMUID.getLocalizedMessage());
            errorGeneracionMUID.printStackTrace();
            List<ReactivoRepCeroConsultaVO> vacio = new ArrayList<ReactivoRepCeroConsultaVO>();
            return (vacio);
        }
        
        finally
        {
            try
            {
                //logEJBReactivo.info ("***********************************************");
                //logEJBReactivo.info ("***********************************************");
                //logEJBReactivo.info ("CERRANDO TRANSACCION DE NATIVE QUERY del método generarConsultaReportesMasivosCero");
                //logEJBReactivo.info ("***********************************************");
                //logEJBReactivo.info ("***********************************************");
            }
            
            catch (Exception errorSQL)
            {
                //logEJBReactivo.info ("Error de sql en el método generarConsultaReportesMasivosCero = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                List<ReactivoRepCeroConsultaVO> vaciobd = new ArrayList<ReactivoRepCeroConsultaVO>();
                return (vaciobd);
            }
        }
        
        return (reporteDatos);
    }
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    @Override
    public List<ReactivoRepCeroConsultaVO> generarConsultaReportesMasivosCero(Date fechaInicial, Date fechaFinal, String deptoSecretaria) 
    {
        List<ReactivoRepCeroConsultaVO> reporteDatos = null;  
        ReactivoRepCeroConsultaVO registroIndividual = null;
        
        try
        {
            //logEJBReactivo.info ("****----------------------------------------------");
            //logEJBReactivo.info ("****----------------------------------------------");
            //logEJBReactivo.info ("EJECUTANDO EL MÉTODO EN EL EJB generarConsultaReportesMasivosCero");
            //logEJBReactivo.info ("****----------------------------------------------");
            //logEJBReactivo.info ("****----------------------------------------------");
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("*-********************************************************");
            //logEJBReactivo.info ("*-********************************************************");
            //logEJBReactivo.info ("VALOR = " + getManejadorEntidadesDireccion().getProperties());
            //logEJBReactivo.info ("*-********************************************************");
            //logEJBReactivo.info ("*-********************************************************");
            Query query1 = getManejadorEntidadesDireccion().createNamedQuery("consultaReportesEnCeroSecretaria");
            //***************************************************************
            //***************************************************************
            fechaInicial.setHours(0);
            fechaInicial.setMinutes(0);
            fechaInicial.setSeconds(0);
            //***************************************************************
            //***************************************************************
            fechaFinal.setHours(23);
            fechaFinal.setMinutes(59);
            fechaFinal.setSeconds(59);
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("fechaInicial = " + fechaInicial);
            //logEJBReactivo.info ("fechaFinal = " + fechaFinal);
            //***************************************************************
            //***************************************************************
            //***************************************************************
            //***************************************************************
            query1.setParameter(1,fechaInicial);
            query1.setParameter(2,fechaFinal);
            query1.setParameter(3,deptoSecretaria);
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("OBJETO DEL QUERY = "+ query1.toString());
            //logEJBReactivo.info ("TAMAÑO DEL QUERY = "+ query1.getMaxResults());
            List<Object> cursor = query1.getResultList();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                reporteDatos = new ArrayList<ReactivoRepCeroConsultaVO>();
                //logEJBReactivo.info ("TAMAÑO DEL CURSOR = " + cursor.size());
                Iterator iteradorRegistros = cursor.iterator();
                
                while (iteradorRegistros.hasNext())
                {
                    Object[] tupla = (Object[]) iteradorRegistros.next();  
                    registroIndividual = new ReactivoRepCeroConsultaVO();
                    //********************************************************************
                    //********************************************************************
                    /*
                    registroIndividual.setNumero_radicado(String.valueOf(tupla[0]));
                    registroIndividual.setRazon_social(String.valueOf(tupla[1]));
                    registroIndividual.setNit_empresa(String.valueOf(tupla[2]));
                    registroIndividual.setDepartamento(String.valueOf(tupla[3]));
                    registroIndividual.setMunicipio(String.valueOf(tupla[4]));
                    registroIndividual.setTrimestre_reportado(String.valueOf(tupla[5]));
                    registroIndividual.setYear_trimestre(String.valueOf(tupla[6]));
                    registroIndividual.setTipo_actor(String.valueOf(tupla[7]));
                    registroIndividual.setNotificacion(String.valueOf(tupla[8]));
                    registroIndividual.setFecha_radicado(String.valueOf(tupla[9]));
                    registroIndividual.setObservacion_reportante(String.valueOf(tupla[10]));
                    */
                    //********************************************************************
                    //********************************************************************
                    registroIndividual.setRadicado(String.valueOf(tupla[0]));
                    registroIndividual.setEmpresa(String.valueOf(tupla[1]));
                    registroIndividual.setNit(String.valueOf(tupla[2]));
                    registroIndividual.setCod_dpto(String.valueOf(tupla[3]));
                    registroIndividual.setDepartamento(String.valueOf(tupla[4]));
                    registroIndividual.setCod_mun(String.valueOf(tupla[5]));
                    registroIndividual.setMunicipio(String.valueOf(tupla[6]));
                    registroIndividual.setTrimestre(String.valueOf(tupla[7]));
                    registroIndividual.setAnio_reporte(String.valueOf(tupla[8]));
                    registroIndividual.setTipo_actor(String.valueOf(tupla[9]));
                    registroIndividual.setNotificacion(String.valueOf(tupla[10]));
                    registroIndividual.setFecha_actualiza(String.valueOf(tupla[11]));
                    registroIndividual.setObservacion(String.valueOf(tupla[12]));
                    registroIndividual.setFecha_reporte(String.valueOf(tupla[13]));
                    registroIndividual.setIdregistro(String.valueOf(tupla[14]));
                    registroIndividual.setUsuario_id(String.valueOf(tupla[15]));
                    //********************************************************************
                    //********************************************************************
                    reporteDatos.add(registroIndividual);
                    //********************************************************************
                    //********************************************************************
                }
            }
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("---------------------------------------------->");
            //logEJBReactivo.info ("TOTAL DE REGISTROS DEL REPORTE BASADO EN NATIVE QUERY generarConsultaReportesMasivosCero = " + reporteDatos.size());
            //logEJBReactivo.info ("---------------------------------------------->");
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorGeneracionMUID)
        {
            //logEJBReactivo.info ("Error en la generación del método generarConsultaReportesMasivosCero = " + errorGeneracionMUID.getLocalizedMessage());
            errorGeneracionMUID.printStackTrace();
            List<ReactivoRepCeroConsultaVO> vacio = new ArrayList<ReactivoRepCeroConsultaVO>();
            return (vacio);
        }
        
        finally
        {
            try
            {
                //logEJBReactivo.info ("***********************************************");
                //logEJBReactivo.info ("***********************************************");
                //logEJBReactivo.info ("CERRANDO TRANSACCION DE NATIVE QUERY del método generarConsultaReportesMasivosCero");
                //logEJBReactivo.info ("***********************************************");
                //logEJBReactivo.info ("***********************************************");
            }
            
            catch (Exception errorSQL)
            {
                //logEJBReactivo.info ("Error de sql en el método generarConsultaReportesMasivosCero = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                List<ReactivoRepCeroConsultaVO> vaciobd = new ArrayList<ReactivoRepCeroConsultaVO>();
                return (vaciobd);
            }
        }
        
        return (reporteDatos);
    }
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    @Override
    public boolean verificarRegistroTrimestreReporteCero(String periodo, String year_reporte, String usuarioIps) 
    {
        boolean yaEsta = false;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        String periodoBD = "";
        String yearBD = "";
        String usuarioBD = "";
        
        try
        {
            logEJBReactivo.info ("****----------------------------------------------");
            logEJBReactivo.info ("****----------------------------------------------");
            logEJBReactivo.info ("EJECUTANDO METODO verificarRegistroTrimestreReporteCero EN EL EJB");
            logEJBReactivo.info ("PERIODO EN EL EJB = " + periodo);
            logEJBReactivo.info ("AÑO DEL REPORTE EN EL EJB = " + year_reporte);
            logEJBReactivo.info ("USUARIO DE IPS REPORTANTE = " + usuarioIps);
            logEJBReactivo.info ("****----------------------------------------------");
            logEJBReactivo.info ("****----------------------------------------------");
            //***************************************************************
            //***************************************************************
            consulta = 
                "select rc.trimestre, rc.year_trimestre, rc.usuario_id " +
                "from dbo.reactivo_reportes_cero rc " +
                "where rc.trimestre = ? and rc.year_trimestre = ? and rc.usuario_id = ? ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del Reporte de Registros de Incidentes = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,periodo);
            sentencia.setString(2,year_reporte);
            sentencia.setString(3,usuarioIps);
            //***************************************************************
            //***************************************************************
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    periodoBD = cursor.getString(1);
                    yearBD = cursor.getString(2);
                    usuarioBD = cursor.getString(3);
                }
            }
            //***************************************************************
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("---------------------------------------------->");
            //logEJBReactivo.info ("PERIODO PARAMETRO = " + periodo + " - PERIODO EN LA BASE = " + periodoBD);
            //logEJBReactivo.info ("AÑO PARAMETRO = " + year_reporte + " - AÑO EN LA BASE = " + yearBD);
            //logEJBReactivo.info ("USUARIO PARAMETRO = " + usuarioIps + " - USUARIO REPORTANTE BASE = " + usuarioBD);
            //***************************************************************
            //***************************************************************
            if (periodo.equals(periodoBD) && year_reporte.equals(yearBD) && usuarioBD.equals(usuarioIps))
            {
                //logEJBReactivo.info ("EL PERIODO Y EL AÑO YA ESTÁN REGISTRADOS POR EL USUARIO = " + usuarioBD + ".  ERROR");
                yaEsta = true;
            }
            else
            {
                //logEJBReactivo.info ("EL PERIODO Y EL AÑO NO ESTÁN REGISTRADOS.  SE PUEDE ALMACENAR");
                yaEsta = false;
            }
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("NO SE PUEDE REGISTRAR = " + yaEsta);
            //logEJBReactivo.info ("---------------------------------------------->");
            //***************************************************************
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorGeneracionMUID)
        {
            //logEJBReactivo.info ("Error en la generación del MUID = " + errorGeneracionMUID.getLocalizedMessage());
            errorGeneracionMUID.printStackTrace();
            return (true);
        }
        
        finally
        {
            try
            {
                if (cursor != null)
                {
                    cursor.close();
                }

                if (sentencia != null)
                {
                    sentencia.close();
                }

                if (conexion != null)
                {
                    conexion.close();
                }
            }
            
            catch (SQLException errorSQL)
            {
                //logEJBReactivo.info ("Error de sql = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return (true);
            }
        }
        
        return (yaEsta);
    }
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    @Override
    public java.util.ArrayList<String> obtenerDivipolaUsuarioReporteCero(String nombreUsuario) 
    {
        java.util.ArrayList<String> comboDatos = null;
        java.util.ArrayList<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            consulta = "select cod_depart, cod_mun from dbo.reactivo_usuarios_internet where usuario = ? ";
            //logEJBTecno.info ("Consulta del obtenerDivipolaUsuarioReporteCero = " + consulta);
            //logEJBTecno.info ("PARAMETRO DE USUARIO = " + nombreUsuario);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,nombreUsuario);
            //***************************************************************
            //***************************************************************
            //***************************************************************
            //***************************************************************
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                comboDatos = new ArrayList<String>();
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    comboDatos.add(cursor.getString(1));
                    comboDatos.add(cursor.getString(2));
                    //*******************************************
                    //*******************************************
                }
                
                //****************************************************************
                //****************************************************************
                if (comboDatos.size() == 0)
                {
                    comboDatosVacio.add("11");
                    comboDatosVacio.add("11001");
                }
                //****************************************************************
                //****************************************************************
                logEJBReactivo.info ("ID DEPTO DEL USUARIO = " + (String)comboDatos.get(0));
                logEJBReactivo.info ("ID MUNICIPIO DEL USUARIO = " + (String)comboDatos.get(1));
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBTecno.info ("Error en la generación del obtenerDivipolaUsuarioReporteCero = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("11");
            comboDatosVacio.add("11001");
            return (comboDatosVacio);
        }
        
        finally
        {
            try
            {
                if (cursor != null)
                {
                    cursor.close();
                }

                if (sentencia != null)
                {
                    sentencia.close();
                }

                if (conexion != null)
                {
                    conexion.close();
                }
            }
            
            catch (SQLException errorSQL)
            {
                //logEJBTecno.info ("Error de sql de obtenerDivipolaUsuarioReporteCero = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("11");
                comboDatosVacio.add("11001");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    /**
     * @return the ejbReactivoReporteCero
     */
    public ReactivoReportesCeroFacadeLocal getEjbReactivoReporteCero() {
        return ejbReactivoReporteCero;
    }

    /**
     * @param ejbReactivoReporteCero the ejbReactivoReporteCero to set
     */
    public void setEjbReactivoReporteCero(ReactivoReportesCeroFacadeLocal ejbReactivoReporteCero) {
        this.ejbReactivoReporteCero = ejbReactivoReporteCero;
    }

    /**
     * @return the manejadorEntidadesDireccion
     */
    public EntityManager getManejadorEntidadesDireccion() {
        return manejadorEntidadesDireccion;
    }

    /**
     * @param manejadorEntidadesDireccion the manejadorEntidadesDireccion to set
     */
    public void setManejadorEntidadesDireccion(EntityManager manejadorEntidadesDireccion) {
        this.manejadorEntidadesDireccion = manejadorEntidadesDireccion;
    }

    /**
     * @return the ejbReactivoUsuariosInternet
     */
    public ReactivoUsuariosInternetFacadeLocal getEjbReactivoUsuariosInternet() {
        return ejbReactivoUsuariosInternet;
    }

    /**
     * @param ejbReactivoUsuariosInternet the ejbReactivoUsuariosInternet to set
     */
    public void setEjbReactivoUsuariosInternet(ReactivoUsuariosInternetFacadeLocal ejbReactivoUsuariosInternet) {
        this.ejbReactivoUsuariosInternet = ejbReactivoUsuariosInternet;
    }
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
    //*******************************************************************************************
}
