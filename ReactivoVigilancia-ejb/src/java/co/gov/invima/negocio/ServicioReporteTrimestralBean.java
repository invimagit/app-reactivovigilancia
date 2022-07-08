/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.negocio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.gov.invima.entities.*;
import co.gov.invima.VO.ReportePreRDIVVO;
import co.gov.invima.sesion.ReactivoCamposComplementariosFacadeLocal;
import co.gov.invima.sesion.ReactivoCamposComplementariosTempFacadeLocal;
import co.gov.invima.sesion.ReactivoEventosFacadeLocal;
import co.gov.invima.sesion.ReactivoEventosTempFacadeLocal;
import co.gov.invima.sesion.ReactivoGestionRealizadaTempFacadeLocal;
import co.gov.invima.sesion.ReactivoGestionrealizadaFacadeLocal;
import co.gov.invima.sesion.ReactivoInstitucionFacadeLocal;
import co.gov.invima.sesion.ReactivoInstitucionTempFacadeLocal;
import co.gov.invima.sesion.ReactivoPacienteFacadeLocal;
import co.gov.invima.sesion.ReactivoPacienteTempFacadeLocal;
import co.gov.invima.sesion.ReactivoProductoFacadeLocal;
import co.gov.invima.sesion.ReactivoProductoTempFacadeLocal;
import co.gov.invima.sesion.ReactivoReportanteFacadeLocal;
import co.gov.invima.sesion.ReactivoReportanteTempFacadeLocal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

/**
 *
 * @author jgutierrezme
 */
@Stateless
public class ServicioReporteTrimestralBean implements ServicioReporteTrimestralRemote 
{
    private static Logger logEJBReactivo = Logger.getLogger(ServicioReporteTrimestralBean.class.getName());
    //************************************************************************************************
    //************************************************************************************************
    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager emReporteTrimestral;
    //************************************************************************************************
    //************************************************************************************************
    @Resource
    private EJBContext contextoBean;
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @EJB
    private ReactivoPacienteTempFacadeLocal ejbReactivoPacienteTempFacadeLocal;
    @EJB
    private ReactivoReportanteTempFacadeLocal ejbReactivoReportanteTempFacadeLocal;
    @EJB
    private ReactivoGestionRealizadaTempFacadeLocal ejbReactivoGestionRealizadaTempFacadeLocal;
    @EJB
    private ReactivoCamposComplementariosTempFacadeLocal ejbReactivoCamposComplementariosTempFacadeLocal;
    @EJB
    private ReactivoEventosTempFacadeLocal ejbReactivoEventosTempFacadeLocal;
    @EJB
    private ReactivoInstitucionTempFacadeLocal ejbReactivoInstitucionTempFacadeLocal;
    @EJB
    private ReactivoProductoTempFacadeLocal ejbReactivoProductoTempFacadeLocal;
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @EJB
    private ReactivoPacienteFacadeLocal ejbReactivoPacienteFacadeLocal;
    @EJB
    private ReactivoReportanteFacadeLocal ejbReactivoReportanteFacadeLocal;
    @EJB
    private ReactivoGestionrealizadaFacadeLocal ejbReactivoGestionRealizadaFacadeLocal;
    @EJB
    private ReactivoCamposComplementariosFacadeLocal ejbReactivoCamposComplementariosFacadeLocal;
    @EJB
    private ReactivoEventosFacadeLocal ejbReactivoEventosFacadeLocal;
    @EJB
    private ReactivoInstitucionFacadeLocal ejbReactivoInstitucionFacadeLocal;
    @EJB
    private ReactivoProductoFacadeLocal ejbReactivoProductoFacadeLocal;
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
    private static java.sql.Timestamp obtenerFechaTimeStamp(java.util.Date fechaOriginal, String tipo)
    {
        // 1) create a java calendar instance
        // 2) get a java.util.Date from the calendar instance.
        //    this date will represent the current instant, or "now".
        // 3) a java current time (now) instance
        java.sql.Timestamp currentTimestamp = null;
        
        if (tipo.equals("inicial"))
        {
            fechaOriginal.setHours(0);
            fechaOriginal.setMinutes(0);
            fechaOriginal.setSeconds(0);
        }
        else
        {
            fechaOriginal.setHours(23);
            fechaOriginal.setMinutes(59);
            fechaOriginal.setSeconds(59);
        }
        
        currentTimestamp = new java.sql.Timestamp(fechaOriginal.getTime());
        //logEJBReactivo.info ("Fecha timestamp original = " + fechaOriginal.toString());
        //logEJBReactivo.info ("Fecha timestamp formateada = " + currentTimestamp.toString());
        
        return (currentTimestamp);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    private java.sql.Connection obtenerConexionDataSource()
    {
        java.sql.Connection conexionBD = null;
        try
        {
            DataSource dataSourceJBoss = null;
            Context ctx = new InitialContext();
            //dataSourceJBoss = (DataSource)ctx.lookup("java:/jboss/SybaseTecno");
            dataSourceJBoss = (DataSource)ctx.lookup("java:/jboss/ReactivoVigilancia");
            logEJBReactivo.info ("Datasource del contenedor: " + dataSourceJBoss);
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
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerCodigoDeptoPorNombre(String nombreDepto) 
    {
        String codigo = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Nombre departamento enviado = " + nombreDepto);
            consulta = "select dep.cod_depart from dbo.departamentos dep where dep.descripcion like ? ";
            //logEJBReactivo.info ("Consulta del obtenerCodigoDeptoPorNombre = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,nombreDepto);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    codigo = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerCodigoDeptoPorNombre = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            return ("0");
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
                //logEJBReactivo.info ("Error de sql de obtenerCodigoDeptoPorNombre = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("0");
            }
        }
        
        return (codigo);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerNombreDeptoPorCodigo(String codigo) 
    {
        String nombre = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("CODIGO DE DEPARTAMENTO ENVIADO = " + codigo);
            consulta = "select dep.descripcion from dbo.departamentos dep where dep.cod_depart = ? ";
            //logEJBReactivo.info ("Consulta del obtenerCodigoDeptoPorNombre = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,codigo);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    nombre = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerCodigoDeptoPorNombre = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            return ("SIN DEPARTAMENTO");
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
                //logEJBReactivo.info ("Error de sql de obtenerCodigoDeptoPorNombre = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("SIN DEPARTAMENTO");
            }
        }
        
        return (nombre);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerCodigoMunicipioPorNombre(String codigoDepto, String nombreMunicipio) 
    {
        String codigo = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("codigoDepto = " + codigoDepto);
            //logEJBReactivo.info ("Nombre del municipio a consultar = " + nombreMunicipio);
            consulta = "select mun.cod_mun from dbo.municipios mun where mun.descripcion like ? and mun.cod_depart = ? ";
            //logEJBReactivo.info ("Consulta del obtenerCodigoMunicipioPorNombre = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,nombreMunicipio);
            sentencia.setString(2,codigoDepto);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    codigo = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerCodigoMunicipioPorNombre = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            return ("0");
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
                //logEJBReactivo.info ("Error de sql de obtenerCodigoMunicipioPorNombre = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("0");
            }
        }
        
        return (codigo);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerNombreMunicipioPorCodigo(String codigo) 
    {
        String nombre = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("CODIGO DE DEPARTAMENTO ENVIADO = " + codigo);
            consulta = "select mun.descripcion from dbo.municipios mun where mun.cod_mun = ? ";
            //logEJBReactivo.info ("Consulta del obtenerNombreMunicipioPorCodigo = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,codigo);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    nombre = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerNombreMunicipioPorCodigo = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            return ("SIN MUNICIPIO");
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
                //logEJBReactivo.info ("Error de sql de obtenerNombreMunicipioPorCodigo = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("SIN MUNICIPIO");
            }
        }
        
        return (nombre);
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
    @Override
    public boolean crearRegistroReactivoEventosCol(ReactivoEventos registroEvento, String codigoColDefinitivo) 
    {
        boolean seInserta = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método crearRegistroReactivoEventosCol");
            ejbReactivoEventosFacadeLocal.create(registroEvento);
            seInserta = true;
            //logEJBReactivo.info ("Se generó la inserción del registro del registroEvento = " + seInserta + " con COL = " + codigoColDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método crearRegistroReactivoEventosCol = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seInserta);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean crearRegistroReactivoPacienteCol(ReactivoPaciente registroPaciente, String codigoColDefinitivo) 
    {
        boolean seInserta = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método crearRegistroReactivoPacienteCol");
            ejbReactivoPacienteFacadeLocal.create(registroPaciente);
            seInserta = true;
            //logEJBReactivo.info ("Se generó la inserción del registro del registroPaciente = " + seInserta + " con COL = " + codigoColDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método crearRegistroReactivoPacienteCol = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seInserta);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean crearRegistroReactivoReportanteCol(ReactivoReportante registroReportante, String codigoColDefinitivo) 
    {
        boolean seInserta = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método crearRegistroReactivoReportanteCol");
            ejbReactivoReportanteFacadeLocal.create(registroReportante);
            seInserta = true;
            //logEJBReactivo.info ("Se generó la inserción del registro del registroReportante = " + seInserta + " con COL = " + codigoColDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método crearRegistroReactivoReportanteCol = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seInserta);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean crearRegistroReactivoGestionRealizadaCol(ReactivoGestionrealizada registroGestionRealizada, String codigoColDefinitivo) 
    {
        boolean seInserta = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método crearRegistroReactivoGestionRealizadaCol");
            ejbReactivoGestionRealizadaFacadeLocal.create(registroGestionRealizada);
            seInserta = true;
            //logEJBReactivo.info ("Se generó la inserción del registro del registroGestionRealizada = " + seInserta + " con COL = " + codigoColDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método crearRegistroReactivoGestionRealizadaCol = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seInserta);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean crearRegistroReactivoCamposComplementariosCol(ReactivoCamposcomp registroCamposComplementarios, String codigoColDefinitivo) 
    {
        boolean seInserta = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método crearRegistroReactivoCamposComplementariosCol");
            ejbReactivoCamposComplementariosFacadeLocal.create(registroCamposComplementarios);
            seInserta = true;
            //logEJBReactivo.info ("Se generó la inserción del registro del registroCamposComplementarios = " + seInserta + " con COL = " + codigoColDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método crearRegistroReactivoCamposComplementariosCol = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seInserta);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean crearRegistroReactivoInstitucionCol(ReactivoInstitucion registroInstitucion, String codigoColDefinitivo) 
    {
        boolean seInserta = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método crearRegistroReactivoInstitucionCol");
            ejbReactivoInstitucionFacadeLocal.create(registroInstitucion);
            seInserta = true;
            //logEJBReactivo.info ("Se generó la inserción del registro del registroInstitucion = " + seInserta + " con COL = " + codigoColDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método crearRegistroReactivoCamposComplementariosCol = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seInserta);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean crearRegistroReactivoProductoCol(ReactivoProducto registroProducto, String codigoColDefinitivo) 
    {
        boolean seInserta = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método crearRegistroReactivoProductoCol");
            ejbReactivoProductoFacadeLocal.create(registroProducto);
            seInserta = true;
            //logEJBReactivo.info ("Se generó la inserción del registro del registroProducto = " + seInserta + " con COL = " + codigoColDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método crearRegistroReactivoProductoCol = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seInserta);
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
    @Override
    public boolean crearRegistroReactivoEventosTemporal(ReactivoEventosTemp registroEvento, String codigoTemporalDefinitivo) 
    {
        boolean seInserta = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método crearRegistroReactivoEventosTemporal");
            ejbReactivoEventosTempFacadeLocal.create(registroEvento);
            seInserta = true;
            //logEJBReactivo.info ("Se generó la inserción del registro del registroEvento = " + seInserta + " con COL = " + codigoTemporalDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método crearRegistroReactivoEventosTemporal = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seInserta);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean crearRegistroReactivoPacienteTemporal(ReactivoPacienteTemp registroPaciente, String codigoTemporalDefinitivo) 
    {
        boolean seInserta = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método crearRegistroReactivoPacienteTemporal");
            ejbReactivoPacienteTempFacadeLocal.create(registroPaciente);
            seInserta = true;
            //logEJBReactivo.info ("Se generó la inserción del registro del registroPaciente = " + seInserta + " con COL = " + codigoTemporalDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método crearRegistroReactivoPacienteTemporal = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seInserta);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean crearRegistroReactivoReportanteTemporal(ReactivoReportanteTemp registroReportante, String codigoTemporalDefinitivo) 
    {
        boolean seInserta = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método crearRegistroReactivoReportanteTemporal");
            ejbReactivoReportanteTempFacadeLocal.create(registroReportante);
            seInserta = true;
            //logEJBReactivo.info ("Se generó la inserción del registro del registroReportante = " + seInserta + " con COL = " + codigoTemporalDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método crearRegistroReactivoReportanteTemporal = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seInserta);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean crearRegistroReactivoGestionRealizadaTemporal(ReactivoGestionrealizadaTemp registroGestionRealizada, String codigoTemporalDefinitivo) 
    {
        boolean seInserta = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método crearRegistroReactivoGestionRealizadaTemporal");
            ejbReactivoGestionRealizadaTempFacadeLocal.create(registroGestionRealizada);
            seInserta = true;
            //logEJBReactivo.info ("Se generó la inserción del registro del registroGestionRealizada = " + seInserta + " con COL = " + codigoTemporalDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método crearRegistroReactivoGestionRealizadaTemporal = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seInserta);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean crearRegistroReactivoCamposComplementariosTemporal(ReactivoCamposcompTemp registroCamposComplementarios, String codigoTemporalDefinitivo) 
    {
        boolean seInserta = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método crearRegistroReactivoCamposComplementariosTemporal");
            ejbReactivoCamposComplementariosTempFacadeLocal.create(registroCamposComplementarios);
            seInserta = true;
            //logEJBReactivo.info ("Se generó la inserción del registro del registroCamposComplementarios = " + seInserta + " con COL = " + codigoTemporalDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método crearRegistroReactivoCamposComplementariosTemporal = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seInserta);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean crearRegistroReactivoInstitucionTemporal(ReactivoInstitucionTemp registroInstitucion, String codigoTemporalDefinitivo) 
    {
        boolean seInserta = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método crearRegistroReactivoInstitucionTemporal");
            ejbReactivoInstitucionTempFacadeLocal.create(registroInstitucion);
            seInserta = true;
            //logEJBReactivo.info ("Se generó la inserción del registro del registroInstitucion = " + seInserta + " con COL = " + codigoTemporalDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método crearRegistroReactivoInstitucionTemporal = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seInserta);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean crearRegistroReactivoProductoTemporal(ReactivoProductoTemp registroProducto, String codigoTemporalDefinitivo) 
    {
        boolean seInserta = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método crearRegistroReactivoProductoTemporal");
            ejbReactivoProductoTempFacadeLocal.create(registroProducto);
            seInserta = true;
            //logEJBReactivo.info ("Se generó la inserción del registro del registroProducto = " + seInserta + " con COL = " + codigoTemporalDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método crearRegistroReactivoProductoTemporal = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seInserta);
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
    @Override
    public boolean modificarRegistroReactivoEventosCol(ReactivoEventos registroEvento, String codigoColDefinitivo) 
    {
        boolean seModifica = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método modificarRegistroReactivoEventosCol");
            ejbReactivoEventosFacadeLocal.edit(registroEvento);
            seModifica = true;
            //logEJBReactivo.info ("Se generó la modificación del registro del registroEvento = " + seModifica + " con COL = " + codigoColDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método modificarRegistroReactivoEventosCol = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seModifica);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean modificarRegistroReactivoPacienteCol(ReactivoPaciente registroPaciente, String codigoColDefinitivo) 
    {
        boolean seModifica = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método modificarRegistroReactivoPacienteCol");
            ejbReactivoPacienteFacadeLocal.edit(registroPaciente);
            seModifica = true;
            //logEJBReactivo.info ("Se generó la modificación del registro del registroPaciente = " + seModifica + " con COL = " + codigoColDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método modificarRegistroReactivoPacienteCol = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seModifica);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean modificarRegistroReactivoReportanteCol(ReactivoReportante registroReportante, String codigoColDefinitivo) 
    {
        boolean seModifica = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método modificarRegistroReactivoReportanteCol");
            ejbReactivoReportanteFacadeLocal.edit(registroReportante);
            seModifica = true;
            //logEJBReactivo.info ("Se generó la modificación del registro del registroReportante = " + seModifica + " con COL = " + codigoColDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método modificarRegistroReactivoReportanteCol = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seModifica);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean modificarRegistroReactivoGestionRealizadaCol(ReactivoGestionrealizada registroGestionRealizada, String codigoColDefinitivo) 
    {
        boolean seModifica = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método modificarRegistroReactivoGestionRealizadaCol");
            ejbReactivoGestionRealizadaFacadeLocal.edit(registroGestionRealizada);
            seModifica = true;
            //logEJBReactivo.info ("Se generó la modificación del registro del registroGestionRealizada = " + seModifica + " con COL = " + codigoColDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método modificarRegistroReactivoGestionRealizadaCol = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seModifica);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean modificarRegistroReactivoCamposComplementariosCol(ReactivoCamposcomp registroCamposComplementarios, String codigoColDefinitivo) 
    {
        boolean seModifica = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método modificarRegistroReactivoCamposComplementariosCol");
            ejbReactivoCamposComplementariosFacadeLocal.edit(registroCamposComplementarios);
            seModifica = true;
            //logEJBReactivo.info ("Se generó la modificación del registro del registroCamposComplementarios = " + seModifica + " con COL = " + codigoColDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método modificarRegistroReactivoCamposComplementariosCol = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seModifica);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean modificarRegistroReactivoInstitucionTemporal(ReactivoInstitucionTemp registroInstitucion, String codigoColDefinitivo) 
    {
        boolean seModifica = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método modificarRegistroReactivoInstitucionTemporal");
            ejbReactivoInstitucionTempFacadeLocal.edit(registroInstitucion);
            seModifica = true;
            //logEJBReactivo.info ("Se generó la modificación del registro del registroInstitucion = " + seModifica + " con COL = " + codigoColDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método modificarRegistroReactivoInstitucionTemporal = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seModifica);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean modificarRegistroReactivoProductoTemporal(ReactivoProductoTemp registroProducto, String codigoColDefinitivo) 
    {
        boolean seModifica = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método modificarRegistroReactivoProductoTemporal");
            ejbReactivoProductoTempFacadeLocal.edit(registroProducto);
            seModifica = true;
            //logEJBReactivo.info ("Se generó la modificación del registro del registroProducto = " + seModifica + " con COL = " + codigoColDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método modificarRegistroReactivoProductoTemporal = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seModifica);
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
    @Override
    public boolean modificarRegistroReactivoEventosEstadoAprobacion(ReactivoEventosTemp registroEventos, String codigoColDefinitivo) 
    {
        boolean seModifica = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método modificarRegistroReactivoEventosEstadoAprobacion");
            ejbReactivoEventosTempFacadeLocal.edit(registroEventos);
            seModifica = true;
            //logEJBReactivo.info ("Se generó la modificación del registro del registroEvento para cambiar estado cdg_seriedad = " + seModifica + " con COL = " + codigoColDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception errorMetodoNegocio)
        {
            //logEJBReactivo.info ("ERROR en el método modificarRegistroReactivoEventosEstadoAprobacion = " + errorMetodoNegocio.getLocalizedMessage());
            errorMetodoNegocio.printStackTrace();
            return (false);
        }
        
        return (seModifica);
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
    @Override
    public String obtenerPrecolSecuenciaCargue() 
    {
        String nuevoPrecol = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("EJECUTANDO MÉTODO PARA OBTENER EL ÚLTIMO PRECOL");
            //***************************************************************
            //***************************************************************
            consulta = "select max(cast(substring(reporte,6,len(reporte)-1) as int))+1 as nuevoPrecol from dbo.reactivo_eventos_temp ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerPrecolSecuenciaCargue = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("rs de precol = " + cursor);
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    nuevoPrecol = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
                
                //logEJBReactivo.info ("---->");
                //logEJBReactivo.info ("NUEVO PRECOL OBTENIDO = " + nuevoPrecol);
                //logEJBReactivo.info ("---->");
                
                if (nuevoPrecol != null)
                {
                    if (nuevoPrecol.equals(""))
                    {
                        nuevoPrecol = "000000001";
                    }
                }
                else
                {
                  nuevoPrecol = "000000001";
                }
            }
            else
            {
                nuevoPrecol = "000000001";
            }
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("---------------------------------------------------------");
            //logEJBReactivo.info ("SECUENCIA DE PRECOL OBTENIDO = " + nuevoPrecol);
            //logEJBReactivo.info ("---------------------------------------------------------");
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerPrecolSecuenciaCargue = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            return ("000000001");
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
                //logEJBReactivo.info ("Error de sql de obtenerPrecolSecuenciaCargue = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("000000001");
            }
        }
        
        return (nuevoPrecol);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerSiguienteColSecuenciaSistema() 
    {
        String nuevoPrecol = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("EJECUTANDO MÉTODO PARA OBTENER EL ÚLTIMO PRECOL");
            //***************************************************************
            //***************************************************************
            consulta = "select max(cast(substring(reporte,6,len(reporte)-1) as int))+1 as nuevoCol from dbo.reactivo_eventos_temp ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerSiguienteColSecuenciaSistema = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("rs de precol = " + cursor);
            
            
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    nuevoPrecol = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
                
                //logEJBReactivo.info ("---->");
                //logEJBReactivo.info ("NUEVO COL OBTENIDO = " + nuevoPrecol);
                //logEJBReactivo.info ("---->");
                
                if (nuevoPrecol != null)
                {
                    if (nuevoPrecol.equals(""))
                    {
                        nuevoPrecol = "000000001";
                    }
                }
                else
                {
                  nuevoPrecol = "000000001";
                }
            }
            else
            {
                nuevoPrecol = "000000001";
            }
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("---------------------------------------------------------");
            //logEJBReactivo.info ("SECUENCIA DE PRECOL OBTENIDO = " + nuevoPrecol);
            //logEJBReactivo.info ("---------------------------------------------------------");
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerSiguienteColSecuenciaSistema = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            return ("000000001");
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
                //logEJBReactivo.info ("Error de sql de obtenerSiguienteColSecuenciaSistema = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("000000001");
            }
        }
        
        return (nuevoPrecol);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerExpedientePorRegistroSanitario(String registroSanitario) 
    {
        String codigoExpediente = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        String codigo = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("EJECUTANDO MÉTODO PARA OBTENER EL EXPEDIENTE POR EL CODIGO DE REGISTRO SANITARIO no. " + registroSanitario);
            consulta =
                "SELECT " +
                "expe.nroexpediente " +
                "from " +
                "registro.dbo.registro_sanitario rg, " +
                "registro.dbo.expedientes expe " +
                "where (rg.cdgregsan = expe.cdgregsan and " +
                "rg.cdgexpediente = expe.cdgexpediente) " +
                "and rg.nroregsan like ? ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerExpedientePorRegistroSanitario");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info (consulta);
            //logEJBReactivo.info ("----------------------------------------------------");
            //Desactivar y reemplazar consulta con LIKE de registro mas aproximado
            //codigo = "INVIMA " + registroSanitario;
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,"%" + codigo + "%");
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    codigoExpediente = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
                //**********************************************
                //**********************************************
                if (codigoExpediente.equals(""))
                {
                    codigoExpediente  = "000000000";
                }
                //**********************************************
                //**********************************************
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerExpedientePorRegistroSanitario = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            return ("000000000");
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
                //logEJBReactivo.info ("Error de sql de obtenerExpedientePorRegistroSanitario = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("000000000");
            }
        }
        
        return (codigoExpediente);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public void limpiarTablaCargueMasivo(String nombreTabla) 
    {
        java.sql.Connection conexionUpdate = null;
	//java.sql.Statement st = null;
	java.sql.PreparedStatement ps = null;

	//String consulta = "TRUNCATE " + nombreTabla;
	String consulta = "DELETE FROM " + nombreTabla; 
	int resultado = 0;
		
	try
	{
                    //logEJBReactivo.info("***********************************************************************************");
                    //logEJBReactivo.info("***********************************************************************************");
                    //logEJBReactivo.info(consulta);
                    //logEJBReactivo.info("PROCESO DE LIMPIEZA DE LA TABLA " + nombreTabla);

                    conexionUpdate = obtenerConexionDataSource();

                    conexionUpdate.setAutoCommit(false);
                    ps = conexionUpdate.prepareStatement(consulta);
                    resultado = ps.executeUpdate();
                    //logEJBReactivo.info("RESULTADO " + resultado);

                    if (resultado == 0)
                    {
                        //logEJBReactivo.info("SE EJECUTÓ EL BORRADO DE LA TABLA " + nombreTabla);
                        //logEJBReactivo.info("TABLA TRUNCADA " + nombreTabla);
                    }

                    conexionUpdate.commit();

                    //logEJBReactivo.info("***********************************************************************************");
                    //logEJBReactivo.info("***********************************************************************************");
	}
		
	catch (Exception errorCargue)
	{
                    //logEJBReactivo.info("Error cargue: " + errorCargue.getMessage());
                    errorCargue.printStackTrace();
	}
		
	finally
	{
                    try
                    {
                        if (ps != null)
                        {
                                ps.close();
                        }

                        if (conexionUpdate != null)
                        {
                                conexionUpdate.close();
                        }
                    }

                    catch (Exception errorsql)
                    {
                        //logEJBReactivo.info("Error de base de datos: " + errorsql.getMessage());
                        errorsql.printStackTrace();
                    }
                }
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerCodigoTipoIdentificacionReportante(String nombreTipoDocumento) 
    {
        String codigoConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO PARA OBTENER CODIGO DE TIPO DE IDENTIFICACIÓN DEL REPORTANTE DEL NOMBRE = " + nombreTipoDocumento);
            consulta = "select cdg_tipodoc from dbo.reactivo_tipodocident where nombre_tipodoc = ? ";

            //logEJBReactivo.info ("Consulta del obtenerCodigoTipoIdentificacionReportante = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,nombreTipoDocumento);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    codigoConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
                
                if (codigoConcepto.equals(""))
                {
                    codigoConcepto = "1";
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerCodigoTipoIdentificacionReportante = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("1");
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
                //logEJBReactivo.info ("Error de sql de obtenerCodigoTipoIdentificacionReportante = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("1");
            }
        }
        
        return (codigoConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerCodigoCargos(String descripcion) 
    {
        String codigoConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO PARA OBTENER CODIGO DE CARGO A PARTIR DEL NOMBRE = " + descripcion);
            consulta =
                      "select cdg_cargos from dbo.reactivo_cargos where descripcion = ? ";
            
            //logEJBReactivo.info ("Consulta del obtenerCodigoCargos = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,descripcion);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    codigoConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
                
                if (codigoConcepto.equals(""))
                {
                    codigoConcepto = "1";
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerCodigoCargos = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("1");
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
                //logEJBReactivo.info ("Error de sql de obtenerCodigoCargos = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("1");
            }
        }
        
        return (codigoConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerCodigoTipoDeReportante(String nombreTipoReportante) 
    {
        String codigoConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO PARA OBTENER CODIGO DE TIPO DE REPORTANTE A PARTIR DEL NOMBRE = " + nombreTipoReportante);
            consulta = "select cdg_tiporeportante from dbo.reactivo_tiporeportante where nombre_tiporeportante = ? ";
            
            //logEJBReactivo.info ("Consulta del obtenerCodigoTipoDeReportante = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,nombreTipoReportante);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    codigoConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
                
                if (codigoConcepto.equals(""))
                {
                    codigoConcepto = "1";
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerCodigoTipoDeReportante = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("1");
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
                //logEJBReactivo.info ("Error de sql de obtenerCodigoTipoDeReportante = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("1");
            }
        }
        
        return (codigoConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerCodigoCausaProbable(String descripcion) 
    {
        String codigoConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO PARA OBTENER CODIGO DE CAUSA PROBABLE A PARTIR DEL NOMBRE = " + descripcion);
            consulta = "select cdg_causa from dbo.reactivo_causa_probable where termino_ea = ? ";
            
            //logEJBReactivo.info ("Consulta del obtenerCodigoCausaProbable = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,descripcion);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    codigoConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
                
                if (codigoConcepto.equals(""))
                {
                    codigoConcepto = "1";
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerCodigoCausaProbable = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("1");
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
                //logEJBReactivo.info ("Error de sql de obtenerCodigoCausaProbable = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("1");
            }
        }
        
        return (codigoConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    /*
    @Override
    public String obtenerCodigoTipoDispositivo(String nombreTipoDispositivo) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    /*
    @Override
    public String obtenerNombreEventoDeteccionPorCodigo(String codigoEvento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    /*
    @Override
    public String obtenerNombreTipoDesenlacePorCodigo(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerNombreTipoIdentificacionReportantePorCodigo(String codigoTipoDocumento) 
    {
        String nombreConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO PARA OBTENER NOMBRE TIPO DE IDENTIFICACION POR CODIGO = " + codigoTipoDocumento);
            consulta =
                    "select nombre_tipodoc from dbo.reactivo_tipodocident where cdg_tipodoc = ? ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerNombreTipoIdentificacionReportantePorCodigo = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,codigoTipoDocumento);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    nombreConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerNombreTipoIdentificacionReportantePorCodigo = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("SIN CONCEPTO");
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
                //logEJBReactivo.info ("Error de sql de obtenerNombreTipoIdentificacionReportantePorCodigo = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("SIN CONCEPTO");
            }
        }
        
        return (nombreConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerNombreCargosPorCodigo(String codigo) 
    {
        String nombreConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO PARA OBTENER NOMBRE CARGO POR CODIGO = " + codigo);
            consulta =
                    "select descripcion from dbo.reactivo_cargos where cdg_cargos = ? ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerNombreCargosPorCodigo = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,codigo);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    nombreConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerNombreCargosPorCodigo = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("SIN CONCEPTO");
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
                //logEJBReactivo.info ("Error de sql de obtenerNombreCargosPorCodigo = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("SIN CONCEPTO");
            }
        }
        
        return (nombreConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerNombreTipoDeReportantePorCodigo(String codigoTipoReportante) 
    {
        String nombreConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO PARA OBTENER TIPO DE REPORTANTE POR CODIGO = " + codigoTipoReportante);
            consulta =
                    "select nombre_tiporeportante from dbo.reactivo_tiporeportante where cdg_tiporeportante = ? ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerNombreTipoDeReportantePorCodigo = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,codigoTipoReportante);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    nombreConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerNombreTipoDeReportantePorCodigo = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("SIN CONCEPTO");
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
                //logEJBReactivo.info ("Error de sql de obtenerNombreTipoDeReportantePorCodigo = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("SIN CONCEPTO");
            }
        }
        
        return (nombreConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerNombreCausaProbablePorCodigo(String codigo) 
    {
        String nombreConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO PARA OBTENER CAUSA PROBABLE POR CODIGO = " + codigo);
            consulta =
                    "select termino_ea from dbo.reactivo_causa_probable where cdg_causa = ? ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerNombreCausaProbablePorCodigo = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,codigo);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    nombreConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerNombreCausaProbablePorCodigo = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("SIN CONCEPTO");
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
                //logEJBReactivo.info ("Error de sql de obtenerNombreCausaProbablePorCodigo = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("SIN CONCEPTO");
            }
        }
        
        return (nombreConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    /*
    @Override
    public String obtenerNombreTipoDispositivo(String codigoTipoDispositivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListadoDepartamentos() 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            consulta = "select dep.cod_depart, dep.descripcion from dbo.departamentos dep where dep.cod_depart <> '00' order by dep.descripcion asc ";
            //logEJBReactivo.info ("Consulta del obtenerListadoDepartamentos = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
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
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListadoDepartamentos = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListadoDepartamentos = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListadoDepartamentos(String deptoActivo) 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            consulta = "select dep.cod_depart, dep.descripcion from dbo.departamentos dep where dep.cod_depart <> '00' and dep.cod_depart = ? order by dep.descripcion asc ";
            //logEJBReactivo.info ("Consulta del obtenerListadoDepartamentos = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,deptoActivo);
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
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListadoDepartamentos = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListadoDepartamentos = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListadoMunicipios(String codigoDepto) 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            consulta = "select ciu.cod_mun, ciu.ciudad from dbo.ciudades ciu where substring(ciu.cod_mun,1,2) = ? order by ciu.ciudad asc ";
            //logEJBReactivo.info ("Consulta del obtenerListadoMunicipios = " + consulta);
            //logEJBReactivo.info ("ID DE DEPARTAMENTO PARA SUS MUNICIPIOS = " + codigoDepto);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,codigoDepto);
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
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListadoMunicipios = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListadoMunicipios = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListadoMunicipiosControlDistrito(String codigoDepto, String idDistrito) 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        String idMunicipio = "";
        String nombreMunicipio = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            consulta = "select ciu.cod_mun, ciu.ciudad from dbo.ciudades ciu where substring(ciu.cod_mun,1,2) = ? order by ciu.cod_mun  asc ";
            //logEJBReactivo.info ("Consulta del obtenerListadoMunicipios = " + consulta);
            //logEJBReactivo.info ("ID DE DEPARTAMENTO PARA SUS MUNICIPIOS = " + codigoDepto);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,codigoDepto);
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
                    idMunicipio = cursor.getString(1);
                    nombreMunicipio = cursor.getString(2);
                    //logEJBReactivo.info ("ID MUNICIPIO = " + idMunicipio + " - ID DISTRITO QUE NO SE DEBE MOSTRAR = " + idDistrito);
                    //*******************************************
                    if (!idMunicipio.equals(idDistrito))
                    {
                        comboDatos.add(idMunicipio);
                        comboDatos.add(nombreMunicipio);
                    }
                    //*******************************************
                    //*******************************************
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListadoMunicipios = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListadoMunicipios = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListadoMunicipiosSoloDistrito(String codigoDepto, String idDistrito) 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        String idMunicipio = "";
        String nombreMunicipio = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            consulta = "select ciu.cod_mun, ciu.ciudad from dbo.ciudades ciu where substring(ciu.cod_mun,1,2) = ? order by ciu.cod_mun  asc ";
            //logEJBReactivo.info ("Consulta del obtenerListadoMunicipios = " + consulta);
            //logEJBReactivo.info ("ID DE DEPARTAMENTO PARA SUS MUNICIPIOS = " + codigoDepto);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,codigoDepto);
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
                    idMunicipio = cursor.getString(1);
                    nombreMunicipio = cursor.getString(2);
                    //logEJBReactivo.info ("ID MUNICIPIO = " + idMunicipio + " - ID DISTRITO QUE NO SE DEBE MOSTRAR = " + idDistrito);
                    //*******************************************
                    if (idMunicipio.equals(idDistrito))
                    {
                        comboDatos.add(idMunicipio);
                        comboDatos.add(nombreMunicipio);
                    }
                    //*******************************************
                    //*******************************************
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListadoMunicipios = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListadoMunicipios = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListadoCargos() 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            consulta = "select tc.cdg_cargos, tc.descripcion from dbo.reactivo_cargos tc order by tc.cdg_cargos asc ";
            //logEJBReactivo.info ("Consulta del obtenerListadoCargos = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
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
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListadoCargos = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListadoCargos = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListaCausasProbables() 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            consulta = "select tcpr.cdg_causa, tcpr.termino_ea from dbo.reactivo_causa_probable tcpr order by tcpr.cdg_causa asc ";
            //logEJBReactivo.info ("Consulta del obtenerListaCausasProbables = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
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
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListaCausasProbables = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListaCausasProbables = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    /*
    @Override
    public List<String> obtenerListaDesenlaces() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    /*
    @Override
    public List<String> obtenerListaDetecciones() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListaTipoDocReportante() 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            consulta = 
                "select " +
                "cdg_tipodoc as ID, " +
                "nombre_tipodoc as NOMBRE from " +
                "dbo.reactivo_tipodocident order by cdg_tipodoc asc ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerListaTipoIncidentes = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
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
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerListaDetecciones)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListaTipoIncidentes = " + errorobtenerListaDetecciones.getLocalizedMessage());
            errorobtenerListaDetecciones.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListaTipoIncidentes = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListaCargos() 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            consulta = 
                "select " +
                "cdg_cargos as ID, " +
                "descripcion as NOMBRE from " +
                "dbo.reactivo_cargos order by cdg_cargos asc ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerListaCargos = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
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
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerListaDetecciones)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListaCargos = " + errorobtenerListaDetecciones.getLocalizedMessage());
            errorobtenerListaDetecciones.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListaCargos = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);        
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListaTipoReportes() 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            consulta = 
                "select " +
                "cdg_tiporep as ID, " +
                "nombre_tiprep as NOMBRE from " +
                "dbo.reactivo_tiporeporte order by cdg_tiporep asc ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerListaTipoReportes = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
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
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerListaDetecciones)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListaTipoReportes = " + errorobtenerListaDetecciones.getLocalizedMessage());
            errorobtenerListaDetecciones.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListaTipoReportes = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);        
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListaTipoReportantes() 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            consulta = 
                "select " +
                "cdg_tiporeportante as ID, " +
                "nombre_tiporeportante as NOMBRE from " +
                "dbo.reactivo_tiporeportante order by cdg_tiporeportante asc ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerListaTipoReportantes = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
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
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerListaDetecciones)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListaTipoReportantes = " + errorobtenerListaDetecciones.getLocalizedMessage());
            errorobtenerListaDetecciones.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListaTipoReportantes = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    /*
    @Override
    public List<String> obtenerListaTipoDispositivos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListadoUsuariosIPS() 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            /*
            consulta = 
                "SELECT " +
                "datos.identificacion_empresa as ID, " +
                "datos.nombre_empresa as NOMBRE " +
                "from " +
                "dbo.tecno_usuarios_internet datos " + 
                "where datos.id_rol_usuario = 3 " +
                "order by datos.nombre_empresa asc ";  
            */
            //***************************************************************
            //***************************************************************
            //Solo se traen las empresas que tengan registros de precoles
            //en la tabla tecno_reporte_eventos_temp
            consulta =
                "SELECT distinct " +
                "  datos.identificacion_empresa as ID, " +  
                "  datos.nombre_empresa as NOMBRE " +  
                "  from " +  
                "  dbo.reactivo_usuarios_internet datos, " +
	"  reactivo_eventos_temp registros " +
                "  where " + 
	"  (datos.identificacion_empresa = registros.idips) and " +
	"  (datos.id_rol_usuario = 3 or datos.id_rol_usuario = 5) " +  
                "  order by datos.nombre_empresa asc ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerListadoUsuariosIPS = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
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
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListadoUsuariosIPS = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListadoUsuariosIPS = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListadoUsuariosIPS(String tipo) 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            /*
            consulta = 
                "SELECT " +
                "datos.identificacion_empresa as ID, " +
                "datos.nombre_empresa as NOMBRE " +
                "from " +
                "dbo.tecno_usuarios_internet datos " + 
                "where datos.id_rol_usuario = 3 " +
                "order by datos.nombre_empresa asc ";  
            */
            //***************************************************************
            //***************************************************************
            //Solo se traen las empresas que tengan registros de precoles
            //en la tabla tecno_reporte_eventos_temp
            /*
            if (tipo.equals("todos"))
            {
                consulta =
                    "SELECT distinct " +
                    "  datos.identificacion_empresa as ID, " +  
                    "  datos.nombre_empresa as NOMBRE " +  
                    "  from " +  
                    "  dbo.tecno_usuarios_internet datos, " +
                    "  tecno_reporte_eventos_temp registros " +
                    "  where " + 
                    "  (datos.identificacion_empresa = registros.idips) and " +
                    "  datos.id_rol_usuario in (2,3) " +  
                    "  order by datos.nombre_empresa asc ";
            }
            else
            {
                consulta =
                    "SELECT distinct " +
                    "  datos.identificacion_empresa as ID, " +  
                    "  datos.nombre_empresa as NOMBRE " +  
                    "  from " +  
                    "  dbo.tecno_usuarios_internet datos, " +
                    "  tecno_reporte_eventos_temp registros " +
                    "  where " + 
                    "  (datos.identificacion_empresa = registros.idips) and " +
                    "  datos.id_rol_usuario = 3 " +  
                    "  order by datos.nombre_empresa asc ";
            }
            */
            if (tipo.equals("todos"))
            {
                consulta =
                "SELECT distinct " +
                "  datos.identificacion_empresa as ID, " +    
                "  datos.nombre_empresa as NOMBRE " +    
                "  from " +    
                "  dbo.reactivo_usuarios_internet datos, " +  
	"  reactivo_eventos_temp registros " +  
                "  where " +   
	"  (datos.identificacion_empresa = registros.idips) and " +  
	"  datos.id_rol_usuario in (2,3,5) " +    
                "  order by datos.nombre_empresa asc ";
            }
            else
            {
                consulta =
                "SELECT distinct " +
                "  datos.identificacion_empresa as ID, " +    
                "  datos.nombre_empresa as NOMBRE " +    
                "  from " +    
                "  dbo.reactivo_usuarios_internet datos, " +  
	"  reactivo_eventos_temp registros " +  
                "  where " +   
	"  (datos.identificacion_empresa = registros.idips) and " +  
	"  (datos.id_rol_usuario = 3 or datos.id_rol_usuario = 5) " +    
                "  order by datos.nombre_empresa asc ";
            }
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerListadoUsuariosIPS tipo = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
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
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListadoUsuariosIPS = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListadoUsuariosIPS = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListadoUsuariosIPS(String tipo, String depto) 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            /*
            consulta = 
                "SELECT " +
                "datos.identificacion_empresa as ID, " +
                "datos.nombre_empresa as NOMBRE " +
                "from " +
                "dbo.tecno_usuarios_internet datos " + 
                "where datos.id_rol_usuario = 3 " +
                "order by datos.nombre_empresa asc ";  
            */
            //***************************************************************
            //***************************************************************
            //Solo se traen las empresas que tengan registros de precoles
            //en la tabla tecno_reporte_eventos_temp
            /*
            if (tipo.equals("todos"))
            {
                consulta =
                    "SELECT distinct " +
                    "  datos.identificacion_empresa as ID, " +  
                    "  datos.nombre_empresa as NOMBRE " +  
                    "  from " +  
                    "  dbo.tecno_usuarios_internet datos, " +
                    "  tecno_reporte_eventos_temp registros " +
                    "  where " + 
                    "  (datos.identificacion_empresa = registros.idips) and " +
                    "  datos.cod_depart = ? and " +
                    "  datos.id_rol_usuario in (2,3) " +  
                    "  and datos.estado_usuario = 'A' " +
                    "  order by datos.nombre_empresa asc ";
            }
            else
            {
                consulta =
                  "SELECT distinct " +
                  "    datos.identificacion_empresa as ID, " +   
                  "    datos.nombre_empresa as NOMBRE " +   
                  "    from " +   
                  "    dbo.tecno_usuarios_internet datos, " + 
                  "    tecno_reporte_eventos_temp registros " + 
                  "    where " +  
                  "    (datos.identificacion_empresa = registros.idips) and " + 
   	  "    datos.cod_depart = ? and " +
                  "    datos.id_rol_usuario = 3 and " +
	  "    datos.estado_usuario = 'A' " +
                  "    order by datos.nombre_empresa asc ";
            }
            */
            if (tipo.equals("todos"))
            {
                consulta =
                    "SELECT distinct " +
                    "  datos.idips as ID, " +
                    "  (select top 1 dbo.reactivo_usuarios_internet.nombre_empresa from dbo.reactivo_usuarios_internet where dbo.reactivo_usuarios_internet.identificacion_empresa = datos.idips group by dbo.reactivo_usuarios_internet.nombre_empresa) as NOMBRE " +
                    "  from " +   
                    "  dbo.reactivo_reportante_temp registros, " +
                    "  dbo.reactivo_eventos_temp datos " +
                    "  where " +  
                    "  (datos.reporte = registros.reporte) and " +
                    "  registros.cod_depart = ? and " + 
                    "  datos.idrol IN (2,3,5) and datos.idips <> '0' and "+
                    "  datos.cdg_seriedad = 0 " + 
                    "  order by datos.idips asc ";
            }
            else
            {
                consulta =
                    "SELECT distinct " +
                    "  datos.idips as ID, " +
                    "  (select top 1 dbo.reactivo_usuarios_internet.nombre_empresa from dbo.reactivo_usuarios_internet where dbo.reactivo_usuarios_internet.identificacion_empresa = datos.idips group by dbo.reactivo_usuarios_internet.nombre_empresa) as NOMBRE " +
                    "  from " +   
                    "  dbo.reactivo_reportante_temp registros, " +
                    "  dbo.reactivo_eventos_temp datos " +
                    "  where " +  
                    "  (datos.reporte = registros.reporte) and " +
                    "  registros.cod_depart = ? and " + 
                    "  (datos.idrol = '3' or datos.idrol = '5') and datos.idips <> '0' and " +
                    "  datos.cdg_seriedad = 0 " + 
                    "  order by datos.idips asc ";
            }
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("DEPARTAMENTO A BUSCAR = " + depto);
            //logEJBReactivo.info ("Consulta del obtenerListadoUsuariosIPS tipo = " + consulta);
            conexion = obtenerConexionDataSource();
            
            if (tipo.equals("todos"))
            {
               sentencia = conexion.prepareStatement(consulta);
               sentencia.setString(1,depto);
            }
            else
            {
               sentencia = conexion.prepareStatement(consulta);
               sentencia.setString(1,depto);
            }
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
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListadoUsuariosIPS = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListadoUsuariosIPS = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListadoUsuariosIPS(String tipo, String depto, String idMunicipio, boolean esDistrito) 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        String restomun = "";
        String prefijoDepto = "";
        
        try
        {
            prefijoDepto = idMunicipio.substring(0,2);
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("PREFIJO DEL DEPARTAMENTO = "+ prefijoDepto);
            //logEJBReactivo.info ("DEPARTAMENTO A BUSCAR = " + depto);
            //logEJBReactivo.info ("MUNICIPIO = " + idMunicipio);
            //logEJBReactivo.info ("SE BUSCA CON DISTRITO ESPECIAL: " + esDistrito);
            //***************************************************************
            //***************************************************************
            if (tipo.equals("todos"))
            {
                consulta =
                    "SELECT distinct " +
                    "  datos.idips as ID, " +
                    "  (select top 1 dbo.reactivo_usuarios_internet.nombre_empresa from dbo.reactivo_usuarios_internet where dbo.reactivo_usuarios_internet.identificacion_empresa = datos.idips group by dbo.reactivo_usuarios_internet.nombre_empresa) as NOMBRE " +
                    "  from " +   
                    "  dbo.reactivo_reportante_temp registros, " +
                    "  dbo.reactivo_eventos_temp datos " +
                    "  where " +  
                    "  (datos.reporte = registros.reporte) and " +
                    "  registros.cod_depart = ? and " + 
                    "  datos.idrol IN (2,3,5)  and  datos.idips <> '0' and  "+
                    "  datos.cdg_seriedad = 0 " + 
                    " group by  datos.idips   order by datos.idips asc ";
            }
            else
            {
                
                if (esDistrito == true)
                {
                        //***************************************************************************
                        //***************************************************************************
                        consulta =
                            "SELECT distinct " +
                            "  datos.idips as ID, " +
                            "  (select top 1 dbo.reactivo_usuarios_internet.nombre_empresa from dbo.reactivo_usuarios_internet where dbo.reactivo_usuarios_internet.identificacion_empresa = datos.idips group by dbo.reactivo_usuarios_internet.nombre_empresa) as NOMBRE " +
                            "  from " +   
                            "  dbo.reactivo_reportante_temp registros, " +
                            "  dbo.reactivo_eventos_temp datos " +
                            "  where " +  
                            "  (datos.reporte = registros.reporte) and " +
                            "  registros.cod_depart = ? and  datos.idips <> '0' and " + 
                            "  datos.idrol IN (2,3,5)  and "+
                            "  datos.cdg_seriedad = 0 and " + 
                             " registros.cod_mun = ? " + 
                             " group by  datos.idips   order by datos.idips asc ";
                        //***************************************************************************
                        //***************************************************************************
                }
                else
                {
                        //***************************************************************************
                        //***************************************************************************
                        if (prefijoDepto.equals("08") || prefijoDepto.equals("11") || prefijoDepto.equals("13") || prefijoDepto.equals("47") || prefijoDepto.equals("76"))
                        {
                            //logEJBReactivo.info ("TRAYENDO IPS DEL DEPTO DISTINTAS AL DISTRITO ESPECIAL");
                            consulta =
                                "SELECT distinct " +
                                "  datos.idips as ID, " +
                                "  (select top 1 dbo.reactivo_usuarios_internet.nombre_empresa from dbo.reactivo_usuarios_internet where dbo.reactivo_usuarios_internet.identificacion_empresa = datos.idips group by dbo.reactivo_usuarios_internet.nombre_empresa) as NOMBRE " +
                                "  from " +   
                                "  dbo.reactivo_reportante_temp registros, " +
                                "  dbo.reactivo_eventos_temp datos " +
                                "  where " +  
                                "  (datos.reporte = registros.reporte) and " +
                                "  registros.cod_depart = ? and " + 
                                "  (datos.idrol = '3' OR datos.idrol = '5') and "+
                                "  datos.cdg_seriedad = 0 and " + 
                                " (registros.cod_mun like ? and registros.cod_mun <> ?) " +
                                " group by  datos.idips   order by datos.idips asc ";
                        }
                        else
                        {
                            //logEJBReactivo.info ("TRAYENDO IPS DE TODO EL DEPARTAMENTO PORQUE NO ES DISTRITO ESPECIAL");
                            consulta =
                                "SELECT distinct " +
                                "  datos.idips as ID, " +
                                "  (select top 1 dbo.reactivo_usuarios_internet.nombre_empresa from dbo.reactivo_usuarios_internet where dbo.reactivo_usuarios_internet.identificacion_empresa = datos.idips group by dbo.reactivo_usuarios_internet.nombre_empresa) as NOMBRE " +
                                "  from " +   
                                "  dbo.reactivo_reportante_temp registros, " +
                                "  dbo.reactivo_eventos_temp datos " +
                                "  where " +  
                                "  (datos.reporte = registros.reporte) and " +
                                "  registros.cod_depart = ? and " + 
                                "  (datos.idrol = '3' OR datos.idrol = '5') and "+
                                "  datos.cdg_seriedad = 0 and " + 
                                 " (registros.cod_mun like ?) " +
                                 " group by  datos.idips   order by datos.idips asc ";
                        }
                        //***************************************************************************
                        //***************************************************************************
                }
                //***************************************************************************
                //***************************************************************************
            }
            //***************************************************************
            //***************************************************************
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerListadoUsuariosIPS tipo = " + consulta);
            conexion = obtenerConexionDataSource();
            //***************************************************************
            //***************************************************************
            sentencia = conexion.prepareStatement(consulta);
            //***************************************************************
            //***************************************************************
            if (tipo.equals("todos"))
            {
               sentencia.setString(1,depto);
            }
            else
            {
                if (esDistrito == true)
                {
                    sentencia.setString(1,depto);
                    sentencia.setString(2,idMunicipio);
                }
                else
                {
                    if (prefijoDepto.equals("08") || prefijoDepto.equals("11") || prefijoDepto.equals("13") || prefijoDepto.equals("47") || prefijoDepto.equals("76"))
                    {
                        restomun = depto + "%";
                        sentencia.setString(1,depto);
                        sentencia.setString(2,restomun);
                        sentencia.setString(3,obtenerIdDistritoEspecial(depto));
                        //logEJBReactivo.info ("restomun = " + restomun);
                        //logEJBReactivo.info ("municipio descartar = " + obtenerIdDistritoEspecial(depto));
                    }
                    else
                    {
                        restomun = depto + "%";
                        sentencia.setString(1,depto);
                        sentencia.setString(2,restomun);
                        //logEJBReactivo.info ("restomun = " + restomun);
                    }
                }
            }
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
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListadoUsuariosIPS = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListadoUsuariosIPS = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    private String obtenerIdDistritoEspecial (String idDeptoMunicipio)
    {
        String idDistrito = "";
        
                if (idDeptoMunicipio.equals("08"))
                {
                    idDistrito = "08001";
                }
                else
                if (idDeptoMunicipio.equals("11"))
                {
                    idDistrito = "11001";
                }
                else
                if (idDeptoMunicipio.equals("13"))
                {
                    idDistrito = "13001";
                }
                else
                if (idDeptoMunicipio.equals("47"))
                {
                    idDistrito = "47001";
                }
                else
                if (idDeptoMunicipio.equals("76"))
                {
                    idDistrito = "76109";
                }
                
                return (idDistrito);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<ReportePreRDIVVO> buscarReportesMasivosTrimestralesPorIPS(String depto, String municipio, String idIPS) 
    {
        List<ReportePreRDIVVO> comboDatos = null;
        List<ReportePreRDIVVO> comboDatosVacio = null;
        ReportePreRDIVVO registroIndividual = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("+++++++++++++++++++++++++++++++++++++");
            //logEJBReactivo.info ("+++++++++++++++++++++++++++++++++++++");
            //logEJBReactivo.info ("ID DEPARTAMENTO = " + depto);
            //logEJBReactivo.info ("ID municipio = " + municipio);
            //logEJBReactivo.info ("ID idIPS = " + idIPS);
            //logEJBReactivo.info ("+++++++++++++++++++++++++++++++++++++");
            //logEJBReactivo.info ("+++++++++++++++++++++++++++++++++++++");
            //******************************************************************
            //******************************************************************
            consulta =
               "SELECT " +
               "     datos.reporte as precol,   " +
               "     (select dep.descripcion from dbo.departamentos dep where dep.cod_depart = reportante.cod_depart) as departamento,   " +
               "     (select mun.descripcion from dbo.municipios mun where mun.cod_mun = reportante.cod_mun) as municipio,   " +
               "     reportante.nombres_apellidos as nombre_reportante,  " +
               "     datos.idrol as id_rol,  " +
               "     datos.fecha_ingreso as fecha_ingreso,  " +
               "     datos.descrip_efecto as descripcionEvento " +
               "     from    " +
               "     dbo.reactivo_eventos_temp datos,   " +
               "     dbo.reactivo_reportante_temp reportante,   " +
               "     dbo.reactivo_paciente_temp paci,  " +
               "     dbo.reactivo_gestionrealizada_temp evalua " +
               "     where    " +
               "     (datos.reporte = reportante.reporte) and   " +
               "     (datos.reporte = paci.reporte) and  " +
               "     (datos.reporte = evalua.reporte) ";
                //******************************************************************
                //******************************************************************
                //******************************************************************
                //******************************************************************
                if (depto != null)
                {
                    consulta +=
                    "and paci.cod_depart = ? ";
                    //"and paci.cod_depart = ? ";

                    if (municipio != null)
                    {
                        consulta +=
                        "and paci.cod_mun = ? ";
                        //"and paci.cod_mun = ? ";

                        if (idIPS != null)
                        {
                            consulta +=
                            "and datos.idips = ? ";
                        }
                    }
                    else
                    {
                        if (idIPS != null)
                        {
                                consulta +=
                                "and datos.idips = ? ";
                        }
                    }
                }
                else
                {
                    if (idIPS != null)
                    {
                        consulta +=
                        "and datos.idips = ? ";
                    }
                }
                //******************************************************************
                //******************************************************************
                //******************************************************************
                consulta +=
                "order by datos.reporte, paci.cod_depart, paci.cod_mun asc ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del buscarReportesMasivosTrimestralesPorIPS = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            //***************************************************************
            //***************************************************************
            if (depto != null)
            {
                sentencia.setString(1,depto);
                    
                if (municipio != null)
                {
                    sentencia.setString(2,municipio);
                    
                    if (idIPS != null)
                    {
                        sentencia.setInt(3,Integer.valueOf(idIPS));
                    }
                }
                else
                {
                    if (idIPS != null)
                    {
                        sentencia.setInt(2,Integer.valueOf(idIPS));
                    }
                }
            }
            else
            {
                if (idIPS != null)
                {
                    sentencia.setInt(1,Integer.valueOf(idIPS));
                }
            }
            //******************************************************************
            //******************************************************************
            //***************************************************************
            //***************************************************************
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                comboDatos = new ArrayList<ReportePreRDIVVO>();
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    registroIndividual = new ReportePreRDIVVO();
                    registroIndividual.setPrecol(cursor.getString(1));
                    registroIndividual.setDepartamento(cursor.getString(2));
                    registroIndividual.setMunicipio(cursor.getString(3));
                    registroIndividual.setNombre_reportante(cursor.getString(4));
                    registroIndividual.setId_rol(cursor.getString(5));
                    registroIndividual.setFecha_ingreso(cursor.getDate(6));
                    registroIndividual.setDescripcionEvento(cursor.getString(7));
                    /*
                    registroIndividual.setNombre_dispositivo(cursor.getString(8));
                    registroIndividual.setTipo_reporte(cursor.getString(9));
                    registroIndividual.setCodigo_causa(cursor.getString(10));
                    registroIndividual.setEstadoCaso(cursor.getString(11));
                    */
                    //*******************************************
                    //*******************************************
                    /*
                    registroIndividual = new ReportePrecol();
                    registroIndividual.setPrecol (cursor.getString(1));
                    registroIndividual.setDepartamento (cursor.getString(2));
                    registroIndividual.setMunicipio (cursor.getString(3));
                    registroIndividual.setFechaEvento (cursor.getDate(4));
                    registroIndividual.setDescripcionEvento (cursor.getString(5));
                    registroIndividual.setDeteccion_evento (cursor.getString(6));
                    registroIndividual.setFechaIngreso (cursor.getDate(7));
                    registroIndividual.setNombreComercial (cursor.getString(8));
                    registroIndividual.setIdentificacion (cursor.getString(9));
                    registroIndividual.setDiagnostico (cursor.getString(10));
                    */
                    //*******************************************
                    //*******************************************
                    comboDatos.add(registroIndividual);
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del buscarReportesMasivosTrimestralesPorIPS = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<ReportePreRDIVVO>();
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
                //logEJBReactivo.info ("Error de sql de buscarReportesMasivosTrimestralesPorIPS = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<ReportePreRDIVVO>();
                return (comboDatosVacio);
            }
        }
        
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<ReportePreRDIVVO> buscarReportesMasivosTrimestralesPorIPS(String depto, String municipio, Date fechaIni, Date fechaFin) 
    {
        List<ReportePreRDIVVO> comboDatos = null;
        List<ReportePreRDIVVO> comboDatosVacio = null;
        ReportePreRDIVVO registroIndividual = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("+++++++++++++++++++++++++++++++++++++");
            //logEJBReactivo.info ("+++++++++++++++++++++++++++++++++++++");
            //logEJBReactivo.info ("ID DEPARTAMENTO = " + depto);
            //logEJBReactivo.info ("ID municipio = " + municipio);
            //logEJBReactivo.info ("fecha inicial = " + fechaIni);
            //logEJBReactivo.info ("fecha inicial = " + fechaFin);
            //logEJBReactivo.info ("+++++++++++++++++++++++++++++++++++++");
            //logEJBReactivo.info ("+++++++++++++++++++++++++++++++++++++");
                //******************************************************************
                //******************************************************************
                //******************************************************************
                //******************************************************************
            consulta =
               "SELECT " +
               "     datos.reporte as precol,   " +
               "     (select dep.descripcion from dbo.departamentos dep where dep.cod_depart = reportante.cod_depart) as departamento,   " +
               "     (select mun.descripcion from dbo.municipios mun where mun.cod_mun = reportante.cod_mun) as municipio,   " +
               "     reportante.nombres_apellidos as nombre_reportante,  " +
               "     datos.idrol as id_rol,  " +
               "     datos.fecha_ingreso as fecha_ingreso,  " +
               "     datos.descrip_efecto as descripcionEvento " +
               "     from    " +
               "     dbo.reactivo_eventos_temp datos,   " +
               "     dbo.reactivo_reportante_temp reportante,   " +
               "     dbo.reactivo_paciente_temp paci,  " +
               "     dbo.reactivo_gestionrealizada_temp evalua " +
               "     where    " +
               "     (datos.reporte = reportante.reporte) and   " +
               "     (datos.reporte = paci.reporte) and  " +
               "     (datos.reporte = evalua.reporte) ";
                //******************************************************************
                //******************************************************************
                //******************************************************************
                if (depto != null)
                {
                    consulta +=
                    "and paci.cod_depart = ? ";
                    //"and paci.cod_depart = ? ";

                    if (municipio != null)
                    {
                        consulta +=
                        "and paci.cod_mun = ? ";
                        //"and paci.cod_mun = ? ";
                        
                        if (fechaIni != null)
                        {
                            consulta +=
                            "and datos.fecha_ingreso >= ? ";
                        }
                        
                        if (fechaFin != null)
                        {
                            consulta +=
                            "and datos.fecha_ingreso <= ? ";
                        }
                    }
                    else
                    {
                        if (fechaIni != null)
                        {
                            consulta +=
                            "and datos.fecha_ingreso >= ? ";
                        }
                        
                        if (fechaFin != null)
                        {
                            consulta +=
                            "and datos.fecha_ingreso <= ? ";
                        }
                        
                    }
                }
                else
                {
                        if (fechaIni != null)
                        {
                            consulta +=
                            "and datos.fecha_ingreso >= ? ";
                        }
                        
                        if (fechaFin != null)
                        {
                            consulta +=
                            "and datos.fecha_ingreso <= ? ";
                        }
                }
                //******************************************************************
                //******************************************************************
                //******************************************************************
                consulta +=
                "order by datos.reporte, paci.cod_depart, paci.cod_mun asc ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del buscarReportesMasivosTrimestralesPorIPS = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            //***************************************************************
            //***************************************************************
            if (depto != null)
            {
                sentencia.setString(1,depto);
                    
                if (municipio != null)
                {
                    sentencia.setString(2,municipio);
                    
                    if (fechaIni != null)
                    {
                        sentencia.setTimestamp(3,obtenerFechaTimeStamp(fechaIni,"inicial"));
                    }
                    
                    if (fechaFin != null)
                    {
                        sentencia.setTimestamp(4,obtenerFechaTimeStamp(fechaFin,"final"));
                    }
                }
                else
                {
                    if (fechaIni != null)
                    {
                        sentencia.setTimestamp(2,obtenerFechaTimeStamp(fechaIni,"inicial"));
                    }
                    
                    if (fechaFin != null)
                    {
                        sentencia.setTimestamp(3,obtenerFechaTimeStamp(fechaFin,"final"));
                    }
                }
            }
            else
            {
                    if (fechaIni != null)
                    {
                        sentencia.setTimestamp(1,obtenerFechaTimeStamp(fechaIni,"inicial"));
                    }
                    
                    if (fechaFin != null)
                    {
                        sentencia.setTimestamp(2,obtenerFechaTimeStamp(fechaFin,"final"));
                    }
            }
            //******************************************************************
            //******************************************************************
            //***************************************************************
            //***************************************************************
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                comboDatos = new ArrayList<ReportePreRDIVVO>();
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    registroIndividual = new ReportePreRDIVVO();
                    //*******************************************
                    //*******************************************
                    registroIndividual = new ReportePreRDIVVO();
                    registroIndividual.setPrecol(cursor.getString(1));
                    registroIndividual.setDepartamento(cursor.getString(2));
                    registroIndividual.setMunicipio(cursor.getString(3));
                    registroIndividual.setNombre_reportante(cursor.getString(4));
                    registroIndividual.setId_rol(cursor.getString(5));
                    registroIndividual.setFecha_ingreso(cursor.getDate(6));
                    registroIndividual.setDescripcionEvento(cursor.getString(7));
                    /*
                    registroIndividual.setNombre_dispositivo(cursor.getString(8));
                    registroIndividual.setTipo_reporte(cursor.getString(9));
                    registroIndividual.setCodigo_causa(cursor.getString(10));
                    */
                    //*******************************************
                    //*******************************************
                    comboDatos.add(registroIndividual);
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del buscarReportesMasivosTrimestralesPorIPS = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<ReportePreRDIVVO>();
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
                //logEJBReactivo.info ("Error de sql de buscarReportesMasivosTrimestralesPorIPS = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<ReportePreRDIVVO>();
                return (comboDatosVacio);
            }
        }
        
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<ReportePreRDIVVO> buscarReportesMasivosTrimestralesPorIPS(String depto, String municipio, Date fechaIni, Date fechaFin, String idIps, String idRol, String municipioSecretaria) 
    {
        List<ReportePreRDIVVO> comboDatos = null;
        List<ReportePreRDIVVO> comboDatosVacio = null;
        ReportePreRDIVVO registroIndividual = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        //String deptoBuscar = "";
        String municipioBuscar = "";
        boolean seBusca = false;
        java.util.ArrayList<String> tiposDatos = null;
        java.util.ArrayList<String> parametrosBusqueda = null;
        java.util.ArrayList<Object> valoresParametros = null;
        int i = 0;
        int j = 0;
        boolean buscarConMunicipio = false;
        String valorMunicipio = "";
        try
        {
            logEJBReactivo.info ("ID DEPARTAMENTO = " + depto);
            logEJBReactivo.info ("ID MUNICIPIO SECRETARÍA = " + municipioSecretaria);
            logEJBReactivo.info ("ID MUNICIPIO COMBO = " + municipio);
            logEJBReactivo.info ("ID IPS FILTRO = " + idIps);
            logEJBReactivo.info ("fecha inicial = " + fechaIni);
            logEJBReactivo.info ("fecha final = " + fechaFin);
            logEJBReactivo.info ("ID ROL = " + idRol);
            
            if (municipio != null)
            {
                    if (depto != null && municipio != null)
                    {
                            //logEJBReactivo.info ("CONSULTANDO CUANDO DEPTO Y MUNICIPIO NO SON NULOS - CASO MUNICIPIO EN EL COMBO");

                            if ((depto.equals("08") && municipio.equals("08001")) || 
                                (depto.equals("11") && municipio.equals("11001")) ||
                                (depto.equals("13") && municipio.equals("13001")) ||
                                (depto.equals("47") && municipio.equals("47001")) ||
                                (depto.equals("76") && municipio.equals("76109")))
                            {
                                //logEJBReactivo.info ("SE EJECUTARA CONSULTA SOBRE DISTRITO ESPECIAL ESPECIFICO = " + municipio);
                                municipioBuscar = " = ";
                                buscarConMunicipio = true;
                                valorMunicipio = municipio;
                            }
                            else
                            {
                                //logEJBReactivo.info ("SE EJECUTARA CONSULTA SOBRE MUNICIPIOS DE LA SECRETARIA DEPARTAMENTAL CON EL MUNICIPIO ESPECIFICO = " + municipio);
                                municipioBuscar = " = ";
                                buscarConMunicipio = true;
                                valorMunicipio = municipio;
                            }
                    }
            }
            else
            {
                   //logEJBReactivo.info ("------------------------------------------------------>2");
                   //logEJBReactivo.info ("Buscando por defecto con municipio de la sesión de la secretaría");
                        
                    if (depto != null && municipioSecretaria != null)
                    {
                            //logEJBReactivo.info ("CONSULTANDO CUANDO DEPTO Y MUNICIPIO NO SON NULOS - CASO SESION");

                            if ((depto.equals("08") && municipioSecretaria.equals("08001")) || 
                                (depto.equals("11") && municipioSecretaria.equals("11001")) ||
                                (depto.equals("13") && municipioSecretaria.equals("13001")) ||
                                (depto.equals("47") && municipioSecretaria.equals("47001")) ||
                                (depto.equals("76") && municipioSecretaria.equals("76109")))
                            {
                                //logEJBReactivo.info ("SE EJECUTARA CONSULTA SOBRE DISTRITO ESPECIAL ESPECIFICO = " + municipioSecretaria);
                                municipioBuscar = " = ";
                                buscarConMunicipio = true;
                                valorMunicipio = municipioSecretaria;
                            }
                            else
                            {
                                //logEJBReactivo.info ("SE EJECUTARA CONSULTA SOBRE MUNICIPIOS DE LA SECRETARIA DEPARTAMENTAL EXCEPTO LA DEL DISTRITO ESPECIAL = " + municipioSecretaria);
                                municipioBuscar = " <> ";
                                buscarConMunicipio = true;
                                //*******************************************************
                                //*******************************************************
                                if (depto.equals("08"))
                                {
                                    valorMunicipio = "08001";
                                }
                                else
                                if (depto.equals("11"))
                                {
                                    valorMunicipio = "11001";
                                }
                                else
                                if (depto.equals("13"))
                                {
                                    valorMunicipio = "13001";
                                }
                                else
                                if (depto.equals("47"))
                                {
                                    valorMunicipio = "47001";
                                }
                                else
                                {
                                    valorMunicipio = "76109";
                                }
                                //*******************************************************
                                //*******************************************************
                            }
                    }
                    
                   //logEJBReactivo.info ("------------------------------------------------------>2");
            }
            //***************************************************************
            //***************************************************************
            consulta =
               "SELECT " +
               "     datos.reporte as precol,   " +
               "     (select dep.descripcion from dbo.departamentos dep where dep.cod_depart = reportante.cod_depart) as departamento,   " +
               "     (select mun.descripcion from dbo.municipios mun where mun.cod_mun = reportante.cod_mun) as municipio,   " +
               "     reportante.nombres_apellidos as nombre_reportante,  " +
               "     datos.idrol as id_rol,  " +
               "     datos.fecha_ingreso as fecha_ingreso,  " +
               "     datos.descrip_efecto as descripcionEvento, " +
               "     evalua.causa_probable, " +
               "     ccom.gestionEnte, " + 
               "     ccom.observacionInvima,  " +
               "     ccom.estadoReporte, " +
               "     prod.nombre_reactivo, instit.institucion, instit.identificacion, prod.nroregsan " +
               "     from    " +
               "     dbo.reactivo_eventos_temp datos,   " +
               "     dbo.reactivo_reportante_temp reportante,   " +
               "     dbo.reactivo_paciente_temp paci,  " +
               "     dbo.reactivo_gestionrealizada_temp evalua, " +
               "     dbo.reactivo_camposcomp_temp ccom, " +
               "     dbo.reactivo_producto_temp prod, " +
               "     dbo.reactivo_institucion_temp instit " +
               "     where    " +
               "     (datos.reporte = reportante.reporte) and   " +
               "     (datos.reporte = paci.reporte) and  " +
               "     (datos.reporte = evalua.reporte) and " +
               "     (datos.reporte = ccom.reporte) and (datos.reporte = prod.reporte) and (datos.reporte = instit.reporte) ";     
            
                tiposDatos = new ArrayList<String>();
                parametrosBusqueda = new ArrayList<String>();
                valoresParametros = new ArrayList<Object>();
                //******************************************************************
                //******************************************************************
            if (depto == null  && municipio == null  && idIps == null  && fechaIni == null  && fechaFin == null  && idRol == null) 
            {
                //logEJBReactivo.info ("NO SE BUSCARÁ NADA");
                seBusca = false;
            }
            else
            {
                seBusca = true;
                if (depto != null && !depto.equals("0"))
                {
                    consulta += "and reportante.cod_depart = ? ";
                    parametrosBusqueda.add (consulta);
                    valoresParametros.add(depto);
                    tiposDatos.add("cadena");
                }
                
                if (buscarConMunicipio == true)
                {
                    consulta += " and reportante.cod_mun " + municipioBuscar + " ? ";
                    parametrosBusqueda.add (consulta);
                    valoresParametros.add(valorMunicipio);
                    tiposDatos.add("cadena");
                }
                
                if (idIps != null && !idIps.equals("-1"))
                {
                    consulta += " and datos.idips = ? ";
                    parametrosBusqueda.add (consulta);
                    valoresParametros.add(idIps);
                    tiposDatos.add("cadena");
                }
                
                if (fechaIni != null)
                {
                    consulta += " and datos.fecha_ingreso >= ? ";
                    parametrosBusqueda.add (consulta);
                    valoresParametros.add(fechaIni);
                    tiposDatos.add("fechaInicial");
                }
                //***********************************************************************************
                //***********************************************************************************
                if (fechaFin != null)
                {
                    consulta += " and datos.fecha_ingreso <= ? ";
                    parametrosBusqueda.add (consulta);
                    valoresParametros.add(fechaFin);
                    tiposDatos.add("fechaFinal");
                }
                //***********************************************************************************
                //***********************************************************************************
                logEJBReactivo.info ("ID ROL PARA LA QUERY = " + idRol);
                //***********************************************************************************
                //***********************************************************************************
                //***********************************************************************************
                //***********************************************************************************
                if (idRol != null)
                {
                        if (!idRol.equals("todos"))
                        {
                            //logEJBReactivo.info ("SE MOSTRARAN SOLO REGISTROS DE ENTIDADES IPS");
                            consulta += " and datos.idrol = ? ";
                            parametrosBusqueda.add (consulta);
                            valoresParametros.add(idRol);
                            tiposDatos.add("cadena");
                        }
                        else
                        {
                            //logEJBReactivo.info ("SE MOSTRARAN TODOS LOS REGISTROS");
                        }
                }
                else
                {
                            //logEJBReactivo.info ("SE MOSTRARAN SOLO REGISTROS DE ENTIDADES IPS");
                            consulta += " and datos.idrol = ? ";
                            parametrosBusqueda.add (consulta);
                            valoresParametros.add("3");
                            tiposDatos.add("cadena");
                }
                //***********************************************************************************
                //***********************************************************************************
                //***********************************************************************************
                //***********************************************************************************
                consulta +=
                "  and datos.cdg_seriedad = 0  ORDER BY datos.reporte, reportante.cod_depart, reportante.cod_mun asc ";
                //***********************************************************************************
                //***********************************************************************************
                //***********************************************************************************
            }
            //***************************************************************
            //***************************************************************
            //***************************************************************
            //***************************************************************
            //***************************************************************
            //***************************************************************
            logEJBReactivo.info ("Consulta del buscarReportesMasivosTrimestralesPorIPS");
            logEJBReactivo.info ("------------------------------------------------------------------------");
            logEJBReactivo.info (consulta);
            logEJBReactivo.info ("------------------------------------------------------------------------");
            //***************************************************************
            //***************************************************************
            String nombreParametro = "";
            String tipo = "";
            if (seBusca == true)
            {
                //********************************************************
                //********************************************************
                conexion = obtenerConexionDataSource();
                sentencia = conexion.prepareStatement(consulta);
                //********************************************************
                //********************************************************
                //********************************************************
                i = 1;
                j = 0;
                for (Object objeto: valoresParametros) 
                {
                    if (objeto instanceof String) 
                    {
                        //logEJBReactivo.info ("Se encontro parametro cadena = " + objeto);
                        sentencia.setString(i,String.valueOf(objeto));
                        i++;
                        j++;
                    }
                    else 
                    if (objeto instanceof java.util.Date) 
                    {
                        //logEJBReactivo.info ("Se encontro parametro fecha");
                        java.util.Date fecha = (java.util.Date)objeto;
                        //********************************************
                        //********************************************
                        nombreParametro = (String)tiposDatos.get(j);
                        if (nombreParametro.equals("fechaInicial"))
                        {
                            tipo = "inicial";
                        }
                        else
                        {
                            tipo = "final";
                        }
                        //********************************************
                        //********************************************
                        sentencia.setTimestamp(i,obtenerFechaTimeStamp(fecha,tipo));
                        i++;
                        j++;
                    }
                }//Fin del for de parametros
                //********************************************************
                //********************************************************
                //********************************************************
                cursor = sentencia.executeQuery();
                //********************************************************
                //********************************************************
                //********************************************************
                if (cursor != null)
                {
                    comboDatos = new ArrayList<ReportePreRDIVVO>();
                    while (cursor.next())
                    {
                        //*******************************************
                        //*******************************************
                        registroIndividual = new ReportePreRDIVVO();
                        registroIndividual.setPrecol(cursor.getString(1));
                        registroIndividual.setDepartamento(cursor.getString(2));
                        registroIndividual.setMunicipio(cursor.getString(3));
                        registroIndividual.setNombre_reportante(cursor.getString(4));
                        registroIndividual.setId_rol(cursor.getString(5));
                        registroIndividual.setFecha_ingreso(cursor.getDate(6));
                        registroIndividual.setDescripcionEvento(cursor.getString(7));
                        registroIndividual.setCodigoCausa(cursor.getString(8));
                        registroIndividual.setGestionEnte(cursor.getString(9));
                        registroIndividual.setObservacionInvima(cursor.getString(10));
                        registroIndividual.setEstadoCaso(cursor.getString(11));
                        registroIndividual.setNombreReactivo(cursor.getString(12));
                        registroIndividual.setNombreInstitucion(cursor.getString(13));
                        registroIndividual.setNitInstitucion(cursor.getString(14));
                        registroIndividual.setRegistroSanitario(cursor.getString(15));
                        //*******************************************
                        //*******************************************
                        comboDatos.add(registroIndividual);
                        //*******************************************
                        //*******************************************
                    }
                }
            }
            else
            {
                comboDatos = new ArrayList<ReportePreRDIVVO>();
                //logEJBReactivo.info ("NO SE TRAE NADA EN LA CONSULTA");
            }
            //***************************************************************
            //***************************************************************
            //***************************************************************
            //***************************************************************
        }//fin del try//fin del try
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del buscarReportesMasivosTrimestralesPorIPS = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<ReportePreRDIVVO>();
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
                //logEJBReactivo.info ("Error de sql de buscarReportesMasivosTrimestralesPorIPS = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<ReportePreRDIVVO>();
                return (comboDatosVacio);
            }
        }
        
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public ReactivoEventosTemp consultarRegistroReactivoEventosTemporal(String codigoPrecol) 
    {
        return (ejbReactivoEventosTempFacadeLocal.find(codigoPrecol));
    }
    
    @Override
    public List<ReactivoEventosTemp> consultarReactivoEventosTemporal() 
    {
        return (ejbReactivoEventosTempFacadeLocal.findAll());
    }
    
    
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public ReactivoPacienteTemp consultarRegistroReactivoPacienteTemporal(String codigoPrecol) 
    {
        return (ejbReactivoPacienteTempFacadeLocal.find(codigoPrecol));
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public ReactivoReportanteTemp consultarRegistroReactivoReportanteTemporal(String codigoPrecol) 
    {
        return (ejbReactivoReportanteTempFacadeLocal.find(codigoPrecol));
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public ReactivoGestionrealizadaTemp consultarRegistroReactivoGestionRealizadaTemporal(String codigoPrecol) 
    {
        return (ejbReactivoGestionRealizadaTempFacadeLocal.find(codigoPrecol));
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public ReactivoCamposcompTemp consultarRegistroReactivoCamposComplementariosTemporal(String codigoPrecol) 
    {
        return (ejbReactivoCamposComplementariosTempFacadeLocal.find(codigoPrecol));
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public ReactivoInstitucionTemp consultarRegistroReactivoInstitucionTemporal(String codigoPrecol) 
    {
        return (ejbReactivoInstitucionTempFacadeLocal.find(codigoPrecol));
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public ReactivoProductoTemp consultarRegistroReactivoProductoTemporal(String codigoPrecol) {
        return (ejbReactivoProductoTempFacadeLocal.find(codigoPrecol));
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
    @Override
    public ReactivoEventos consultarRegistroReactivoEventos(String codigoCol) 
    {
        return (ejbReactivoEventosFacadeLocal.find(codigoCol));
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public ReactivoPaciente consultarRegistroReactivoPaciente(String codigoCol) 
    {
        return (ejbReactivoPacienteFacadeLocal.find(codigoCol));
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public ReactivoReportante consultarRegistroReactivoReportante(String codigoCol) 
    {
        return (ejbReactivoReportanteFacadeLocal.find(codigoCol));
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public ReactivoGestionrealizada consultarRegistroReactivoGestionRealizada(String codigoCol) 
    {
        return (ejbReactivoGestionRealizadaFacadeLocal.find(codigoCol));
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public ReactivoCamposcomp consultarRegistroReactivoCamposComplementarios(String codigoCol) 
    {
        return (ejbReactivoCamposComplementariosFacadeLocal.find(codigoCol));
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public ReactivoInstitucion consultarRegistroReactivoInstitucion(String codigoCol) 
    {
        return (ejbReactivoInstitucionFacadeLocal.find(codigoCol));
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public ReactivoProducto consultarRegistroReactivoProducto(String codigoCol) 
    {
        return (ejbReactivoProductoFacadeLocal.find(codigoCol));
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
    @Override
    public String obtenerCodigoRegistroSanitarioPorPrecol(String codigoPrecol) 
    {
        String nombreConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO obtenerCodigoRegistroSanitarioPorPrecol = " + codigoPrecol);
            consulta =
                    "select expediente from dbo.reactivo_camposcomp_temp where reporte = ? ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerNombreTipoDispositivo = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,codigoPrecol);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    nombreConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
                
                //logEJBReactivo.info ("CODIGO REGISTRO SANITARIO = " + nombreConcepto);
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerCodigoRegistroSanitarioPorPrecol = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("SIN CONCEPTO");
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
                //logEJBReactivo.info ("Error de sql de obtenerCodigoRegistroSanitarioPorPrecol = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("SIN CONCEPTO");
            }
        }
        
        return (nombreConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerCodigoRegistroSanitarioPorCOL(String codigoCOL) 
    {
        String nombreConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO obtenerCodigoRegistroSanitarioPorCOL = " + codigoCOL);
            consulta =
                    "select expediente from dbo.reactivo_camposcomp where reporte = ? ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerCodigoRegistroSanitarioPorCOL = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,codigoCOL);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    nombreConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
                
                if (nombreConcepto.equals(""))
                {
                    nombreConcepto = "1";
                }
                
                //logEJBReactivo.info ("CODIGO REGISTRO SANITARIO POR COL = " + nombreConcepto);
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerCodigoRegistroSanitarioPorPrecol = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("SIN CONCEPTO");
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
                //logEJBReactivo.info ("Error de sql de obtenerCodigoRegistroSanitarioPorPrecol = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("SIN CONCEPTO");
            }
        }
        
        return (nombreConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String verificarExpedienteDispositivoPorRegistroSanitario(String codigoRegistroSanitario) 
    {
        String codigoExpedienteDispositivo = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        String parametro = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO verificarExpedienteDispositivoPorRegistroSanitario por codigo de Registro Sanitario INVIMA = " + codigoRegistroSanitario);
            consulta =
                            "SELECT " +
                            "expe.nroexpediente " +
                            "from " +
                            "registro.dbo.registro_sanitario rg, " +
                            "registro.dbo.expedientes expe " +
                            "where (rg.cdgregsan = expe.cdgregsan and " +
                            "rg.cdgexpediente = expe.cdgexpediente) " +
                            "and rg.nroregsan like ? ";
            
            parametro = "%" + codigoRegistroSanitario.trim() + "%";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del verificarExpedienteDispositivoPorRegistroSanitario = " + consulta);
            //logEJBReactivo.info ("PARAMETRO BUSQUEDA = " + parametro);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,parametro);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    codigoExpedienteDispositivo = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
                //*********************************************************
                //*********************************************************
                //logEJBReactivo.info ("codigoExpedienteDispositivo = " + codigoExpedienteDispositivo);
                //*********************************************************
                //*********************************************************
                //*********************************************************
                if (codigoExpedienteDispositivo != null)
                {
                    if (codigoExpedienteDispositivo.length() > 0)
                    {
                        //logEJBReactivo.info ("CODIGO EXPEDIENTE = " + codigoExpedienteDispositivo);
                    }
                    else
                    {
                        codigoExpedienteDispositivo = "00000000";
                        //logEJBReactivo.info ("CODIGO NULO = " + codigoExpedienteDispositivo);
                    }
                }
                else
                {
                    codigoExpedienteDispositivo = "00000000";
                    //logEJBReactivo.info ("CODIGO NULO = " + codigoExpedienteDispositivo);
                }
                //*********************************************************
                //*********************************************************
                //*********************************************************
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorverificarExpedienteDispositivoPorRegistroSanitario)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerCodigoRegistroSanitarioPorPrecol = " + errorverificarExpedienteDispositivoPorRegistroSanitario.getLocalizedMessage());
            errorverificarExpedienteDispositivoPorRegistroSanitario.printStackTrace();
            return ("00000000");
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
                //logEJBReactivo.info ("Error de sql de obtenerCodigoRegistroSanitarioPorPrecol = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("00000000");
            }
        }
        
        return (codigoExpedienteDispositivo);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerIdRolUsuarioPorNIT(String nitEmpresa) 
    {
        String nombreConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO obtenerIdRolUsuarioPorNIT = " + nitEmpresa);
            consulta =
                    "select ID_Rol_Usuario from dbo.reactivo_usuarios_internet where identificacion_empresa = ?  and estado_usuario = 'A' ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerCorreoEmpresaPorNIT = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,nitEmpresa);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    nombreConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
                
                //logEJBReactivo.info ("ID ROL EMPRESA = " + nombreConcepto);
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerIdRolUsuarioPorNIT = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("3");
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
                //logEJBReactivo.info ("Error de sql de obtenerIdRolUsuarioPorNIT = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("3");
            }
        }
        
        return (nombreConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerCorreoEmpresaPorNIT(String nitEmpresa) 
    {
        String nombreConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO obtenerCorreoEmpresaPorNIT = " + nitEmpresa);
            consulta =
                    "select email_empresa from dbo.reactivo_usuarios_internet where identificacion_empresa = ?  and estado_usuario = 'A' ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerCorreoEmpresaPorNIT = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,nitEmpresa);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    nombreConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
                
                //logEJBReactivo.info ("EMAIL EMPRESA = " + nombreConcepto);
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerCodigoRegistroSanitarioPorPrecol = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("reactivovigilancia@invima.gov.co");
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
                //logEJBReactivo.info ("Error de sql de obtenerCodigoRegistroSanitarioPorPrecol = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("reactivovigilancia@invima.gov.co");
            }
        }
        
        return (nombreConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerNombreEmpresaIPSPorNit(String nitEmpresa) 
    {
        String nombreConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO obtenerNombreEmpresaIPSPorNit = " + nitEmpresa);
            consulta =
                    "select concat(identificacion_empresa,' - ',nombre_empresa) from dbo.reactivo_usuarios_internet where identificacion_empresa = ? ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerNombreEmpresaIPSPorNit = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,nitEmpresa);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    nombreConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
                
                //logEJBReactivo.info ("NIT Y NOMBRE EMPRESA = " + nombreConcepto);
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerNombreEmpresaIPSPorNit = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("SIN EMPRESA");
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
                //logEJBReactivo.info ("Error de sql de obtenerNombreEmpresaIPSPorNit = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("SIN EMPRESA");
            }
        }
        
        return (nombreConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerCodigoFuncionarioInvima(String nombreUsuario) 
    {
        String nombreConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO obtenerCodigoFuncionarioInvima = " + nombreUsuario);
            consulta =
                    "select cdgfuncionario from dbo.funcionarios where login_name = ? ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerCodigoFuncionarioInvima = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,nombreUsuario);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    nombreConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
                
                //logEJBReactivo.info ("CODIGO DEL FUNCIONARIO " + nombreUsuario + " = " + nombreConcepto);
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerNombreEmpresaIPSPorNit = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("938");
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
                //logEJBReactivo.info ("Error de sql de obtenerNombreEmpresaIPSPorNit = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("938");
            }
        }
        
        return (nombreConcepto);
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
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean eliminarRegistroReactivoEventosTemporal(ReactivoEventosTemp registroEventoTemporal, String codigoTemporalDefinitivo) 
    {
        boolean seElimina = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método eliminarRegistroTecnoReporteEventos");
            ejbReactivoEventosTempFacadeLocal.remove(registroEventoTemporal);
            seElimina = true;
            //logEJBReactivo.info ("Se generó la eliminación del registro del registroTRE = " + seElimina + " con precol = " + codigoTemporalDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception erroreliminarRegistroTecnoReporteEventos)
        {
            //logEJBReactivo.info ("ERROR en el método eliminarRegistroTecnoReporteEventos = " + erroreliminarRegistroTecnoReporteEventos.getLocalizedMessage());
            erroreliminarRegistroTecnoReporteEventos.printStackTrace();
            return (false);
        }
        
        return (seElimina);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean eliminarRegistroReactivoPacienteTemporal(ReactivoPacienteTemp registroPacienteTemporal, String codigoTemporalDefinitivo) 
    {
        boolean seElimina = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método eliminarRegistroReactivoPacienteTemporal");
            ejbReactivoPacienteTempFacadeLocal.remove(registroPacienteTemporal);
            seElimina = true;
            //logEJBReactivo.info ("Se generó la eliminación del registro del registroTRE = " + seElimina + " con precol = " + codigoTemporalDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception erroreliminarRegistroTecnoReporteEventos)
        {
            //logEJBReactivo.info ("ERROR en el método eliminarRegistroReactivoPacienteTemporal = " + erroreliminarRegistroTecnoReporteEventos.getLocalizedMessage());
            erroreliminarRegistroTecnoReporteEventos.printStackTrace();
            return (false);
        }
        
        return (seElimina);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean eliminarRegistroReactivoReportanteTemporal(ReactivoReportanteTemp registroReportanteTemporal, String codigoTemporalDefinitivo) 
    {
        boolean seElimina = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método eliminarRegistroReactivoReportanteTemporal");
            ejbReactivoReportanteTempFacadeLocal.remove(registroReportanteTemporal);
            seElimina = true;
            //logEJBReactivo.info ("Se generó la eliminación del registro del registroReportanteTemporal = " + seElimina + " con precol = " + codigoTemporalDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception erroreliminarRegistroTecnoReporteEventos)
        {
            //logEJBReactivo.info ("ERROR en el método eliminarRegistroReactivoReportanteTemporal = " + erroreliminarRegistroTecnoReporteEventos.getLocalizedMessage());
            erroreliminarRegistroTecnoReporteEventos.printStackTrace();
            return (false);
        }
        
        return (seElimina);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean eliminarRegistroReactivoGestionRealizadaTemporal(ReactivoGestionrealizadaTemp registroGestionRealizadaTemporal, String codigoTemporalDefinitivo) 
    {
        boolean seElimina = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método eliminarRegistroReactivoGestionRealizadaTemporal");
            ejbReactivoGestionRealizadaTempFacadeLocal.remove(registroGestionRealizadaTemporal);
            seElimina = true;
            //logEJBReactivo.info ("Se generó la eliminación del registro del registroGestionRealizadaTemporal = " + seElimina + " con precol = " + codigoTemporalDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception erroreliminarRegistroTecnoReporteEventos)
        {
            //logEJBReactivo.info ("ERROR en el método eliminarRegistroReactivoGestionRealizadaTemporal = " + erroreliminarRegistroTecnoReporteEventos.getLocalizedMessage());
            erroreliminarRegistroTecnoReporteEventos.printStackTrace();
            return (false);
        }
        
        return (seElimina);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean eliminarRegistroReactivoCamposComplementariosTemporal(ReactivoCamposcompTemp registroCamposComplementariosTemporal, String codigoTemporalDefinitivo) 
    {
        boolean seElimina = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método eliminarRegistroReactivoCamposComplementariosTemporal");
            ejbReactivoCamposComplementariosTempFacadeLocal.remove(registroCamposComplementariosTemporal);
            seElimina = true;
            //logEJBReactivo.info ("Se generó la eliminación del registro del registroCamposComplementariosTemporal = " + seElimina + " con precol = " + codigoTemporalDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception erroreliminarRegistroTecnoReporteEventos)
        {
            //logEJBReactivo.info ("ERROR en el método eliminarRegistroReactivoCamposComplementariosTemporal = " + erroreliminarRegistroTecnoReporteEventos.getLocalizedMessage());
            erroreliminarRegistroTecnoReporteEventos.printStackTrace();
            return (false);
        }
        
        return (seElimina);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean eliminarRegistroReactivoInstitucionTemporal(ReactivoInstitucionTemp registroInstitucionTemporal, String codigoTemporalDefinitivo) 
    {
        boolean seElimina = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método eliminarRegistroReactivoInstitucionTemporal");
            ejbReactivoInstitucionTempFacadeLocal.remove(registroInstitucionTemporal);
            seElimina = true;
            //logEJBReactivo.info ("Se generó la eliminación del registro del registroInstitucionTemporal = " + seElimina + " con precol = " + codigoTemporalDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception erroreliminarRegistroTecnoReporteEventos)
        {
            //logEJBReactivo.info ("ERROR en el método eliminarRegistroReactivoInstitucionTemporal = " + erroreliminarRegistroTecnoReporteEventos.getLocalizedMessage());
            erroreliminarRegistroTecnoReporteEventos.printStackTrace();
            return (false);
        }
        
        return (seElimina);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public boolean eliminarRegistroReactivoProductoTemporal(ReactivoProductoTemp registroProductoTemporal, String codigoTemporalDefinitivo) 
    {
        boolean seElimina = false;
        
        try
        {
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("Ejecutando método eliminarRegistroReactivoProductoTemporal");
            ejbReactivoProductoTempFacadeLocal.remove(registroProductoTemporal);
            seElimina = true;
            //logEJBReactivo.info ("Se generó la eliminación del registro del registroProductoTemporal = " + seElimina + " con precol = " + codigoTemporalDefinitivo);
            //logEJBReactivo.info ("----------------------------------------------------");
            //logEJBReactivo.info ("----------------------------------------------------");
        }
        
        catch (Exception erroreliminarRegistroTecnoReporteEventos)
        {
            //logEJBReactivo.info ("ERROR en el método eliminarRegistroReactivoProductoTemporal = " + erroreliminarRegistroTecnoReporteEventos.getLocalizedMessage());
            erroreliminarRegistroTecnoReporteEventos.printStackTrace();
            return (false);
        }
        
        return (seElimina);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerCodigoTipoDesenlace(String descripcion) 
    {
        String codigoConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO PARA OBTENER CODIGO DE TIPO DE DESENLACE DEL NOMBRE = " + descripcion);
            consulta =
                        "select cdg_desenlace from dbo.reactivo_desenlace where descripcion = ? ";

            //logEJBReactivo.info ("Consulta del obtenerCodigoTipoDesenlace = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,descripcion);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    codigoConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
                //*******************************************
                //*******************************************
                if (codigoConcepto.equals(""))
                {
                    codigoConcepto = "1";
                }
                //*******************************************
                //*******************************************
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoTipoDesenlace)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerCodigoTipoDesenlace = " + errorobtenerCodigoTipoDesenlace.getLocalizedMessage());
            errorobtenerCodigoTipoDesenlace.printStackTrace();
            return ("1");
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
                //logEJBReactivo.info ("Error de sql de obtenerCodigoTipoDesenlace = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("1");
            }
        }
        
        return (codigoConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerNombreTipoDesenlacePorCodigo(String codigo) 
    {
        String nombreConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO PARA OBTENER NOMBRE TIPO DE DESENLACE POR CODIGO = " + codigo);
            consulta =
                    "SELECT descripcion from dbo.reactivo_desenlace where cdg_desenlace = ? ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerNombreTipoDesenlacePorCodigo = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,codigo);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    nombreConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerNombreTipoDesenlacePorCodigo = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("SIN CONCEPTO");
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
                //logEJBReactivo.info ("Error de sql de obtenerNombreTipoDesenlacePorCodigo = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("SIN CONCEPTO");
            }
        }
        
        return (nombreConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListaDesenlaces() 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            consulta = "select d.cdg_desenlace, d.descripcion from dbo.reactivo_desenlace d order by d.cdg_desenlace asc ";
            //logEJBReactivo.info ("Consulta del obtenerListaDesenlaces = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
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
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListaDesenlaces = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListaDesenlaces = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerCodigoTipoDispositivo(String nombreTipoDispositivo) 
    {
        String codigoConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO PARA OBTENER CODIGO DE TIPO DE DISPOSITIVO A PARTIR DEL NOMBRE = " + nombreTipoDispositivo);
            consulta =
                        "select cdg_tipdis from dbo.reactivo_tipodispositivo where nombre_tipodis = ? ";

            //logEJBReactivo.info ("Consulta del obtenerCodigoTipoDispositivo = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,nombreTipoDispositivo);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    codigoConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
                
                if (codigoConcepto.equals(""))
                {
                    codigoConcepto = "1";
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerCodigoTipoDispositivo = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("1");
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
                //logEJBReactivo.info ("Error de sql de obtenerCodigoTipoDispositivo = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("1");
            }
        }
        
        return (codigoConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerNombreTipoDispositivo(String codigoTipoDispositivo) 
    {
        String nombreConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO PARA OBTENER NOMBRE TIPO DE DISPOSITIVO CODIGO = " + codigoTipoDispositivo);
            consulta =
                    "select nombre_tipodis from dbo.reactivo_tipodispositivo where cdg_tipdis = ? ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerNombreTipoDispositivo = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,codigoTipoDispositivo);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    nombreConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerNombreTipoDispositivo = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("SIN CONCEPTO");
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
                //logEJBReactivo.info ("Error de sql de obtenerNombreTipoDispositivo = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("SIN CONCEPTO");
            }
        }
        
        return (nombreConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListaTipoDispositivos() 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            consulta = 
                "SELECT " +
                "cdg_tipdis as ID, " +
                "nombre_tipodis as NOMBRE " +
                "from dbo.reactivo_tipodispositivo " +
                "order by cdg_tipdis asc ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerListaTipoDispositivos = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
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
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListaTipoDispositivos = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListaTipoDispositivos = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerCodigoDescripcionCausaEfecto(String nombre) 
    {
        String codigoConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            logEJBReactivo.info ("MÉTODO PARA OBTENER CODIGO DE DESCRIPCIÓN CAUSA EFECTO POR NOMBRE = " + nombre);
            consulta = "select tc.codigo from dbo.reactivo_desccausa tc where tc.nombre_tiprep like ? ";

            //logEJBReactivo.info ("Consulta del obtenerCodigoTipoIdentificacionReportante = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,nombre+"%");
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    codigoConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
                
                if (codigoConcepto.equals(""))
                {
                    codigoConcepto = "1";
                }
                
                logEJBReactivo.info ("CODIGO CONCEPTO OBTENIDO = " + codigoConcepto);
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerCodigoTipoIdentificacionReportante = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("1");
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
                //logEJBReactivo.info ("Error de sql de obtenerCodigoTipoIdentificacionReportante = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("1");
            }
        }
        
        return (codigoConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerNombreDescripcionCausaEfecto(String codigo) 
    {
        String nombreConcepto = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("MÉTODO PARA OBTENER NOMBRE DE DESCRIPCION DE CAUSA EFECTO POR CODIGO = " + codigo);
            consulta =
                    "select tc.nombre_tiprep from dbo.reactivo_desccausa tc where tc.codigo = ? ";
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Consulta del obtenerNombreTipoIdentificacionReportantePorCodigo = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,codigo);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    nombreConcepto = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerCodigoEventoDeteccion)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerNombreTipoIdentificacionReportantePorCodigo = " + errorobtenerCodigoEventoDeteccion.getLocalizedMessage());
            errorobtenerCodigoEventoDeteccion.printStackTrace();
            return ("SIN CONCEPTO");
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
                //logEJBReactivo.info ("Error de sql de obtenerNombreTipoIdentificacionReportantePorCodigo = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("SIN CONCEPTO");
            }
        }
        
        return (nombreConcepto);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListaDescripcionesCausaEfecto() 
    {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            consulta = "select tc.codigo, tc.nombre_tiprep from dbo.reactivo_desccausa tc order by tc.codigo asc ";
            //logEJBReactivo.info ("Consulta del obtenerListadoCargos = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
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
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListadoCargos = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListadoCargos = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public List<String> obtenerListaCausasProbablesCodigos() {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            consulta = "select tcpr.cdg_causa, tcpr.cdg_causa from dbo.reactivo_causa_probable tcpr order by tcpr.cdg_causa asc ";
            //logEJBReactivo.info ("Consulta del obtenerListaCausasProbablesCodigos = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
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
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerListaCausasProbablesCodigos = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
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
                //logEJBReactivo.info ("Error de sql de obtenerListaCausasProbablesCodigos = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }
        
        return (comboDatos);
    }
    //************************************************************************************************
    //************************************************************************************************
    //************************************************************************************************
    @Override
    public String obtenerDescripcionCausaProbable(String codigoCausa) 
    {
        String descripcionCausa = "";
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        
        try
        {
            //***************************************************************
            //***************************************************************
            //logEJBReactivo.info ("Nombre departamento enviado = " + nombreDepto);
            consulta = "select descripcion_ea from dbo.reactivo_causa_probable where cdg_causa = ? ";
            //logEJBReactivo.info ("Consulta del obtenerCodigoDeptoPorNombre = " + consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1,codigoCausa);
            cursor = sentencia.executeQuery();
            //***************************************************************
            //***************************************************************
            if (cursor != null)
            {
                while (cursor.next())
                {
                    //*******************************************
                    //*******************************************
                    descripcionCausa = cursor.getString(1);
                    //*******************************************
                    //*******************************************
                }
            }
            //***************************************************************
            //***************************************************************
        }
        
        catch (Exception errorobtenerTiposDeSucursal)
        {
            //logEJBReactivo.info ("Error en la generación del obtenerCodigoDeptoPorNombre = " + errorobtenerTiposDeSucursal.getLocalizedMessage());
            errorobtenerTiposDeSucursal.printStackTrace();
            return ("No disponible");
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
                //logEJBReactivo.info ("Error de sql de obtenerCodigoDeptoPorNombre = " + errorSQL.getLocalizedMessage());
                errorSQL.printStackTrace();
                return ("No disponible");
            }
        }
        
        return (descripcionCausa);
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
}//Fin del EJB
//*******************************************************************************
//*******************************************************************************
//*******************************************************************************
//*******************************************************************************
//*******************************************************************************
//*******************************************************************************
//*******************************************************************************
//*******************************************************************************
//*******************************************************************************
