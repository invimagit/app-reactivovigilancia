package co.gov.invima.negocio;

import co.gov.invima.reactivo.dto.reports.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

@Stateless
public class ConsultasReactivoBean implements ConsultasReactivoRemote {

    private final static Logger logEJBReactivo = Logger.getLogger(ConsultasReactivoBean.class.getName());
    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager manejadorEntidadesDireccion;
    @Resource
    private EJBContext contextoBean;

    public ConsultasReactivoBean() {
    }

    private static java.sql.Date obtenerFechaTimeStamp(java.util.Date fechaOriginal) {
        java.sql.Date currentTimestamp = new java.sql.Date(fechaOriginal.getTime());
        return (currentTimestamp);
    }

    private java.sql.Connection obtenerConexionDataSource() {
        java.sql.Connection conexionBD = null;
        try {
            DataSource dataSourceJBoss = null;
            Context ctx = new InitialContext();
            dataSourceJBoss = (DataSource) ctx.lookup("java:/jboss/ReactivoVigilancia");
            conexionBD = dataSourceJBoss.getConnection();
        } catch (SQLException errorConexionSQL) {
            errorConexionSQL.printStackTrace();
        } catch (NamingException errorConexionDS) {
            errorConexionDS.printStackTrace();
        }
        return (conexionBD);
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
     * @return the contextoBean
     */
    public EJBContext getContextoBean() {
        return contextoBean;
    }

    /**
     * @param contextoBean the contextoBean to set
     */
    public void setContextoBean(EJBContext contextoBean) {
        this.contextoBean = contextoBean;
    }

    @Override
    public List<ReporteReactivo> generarReporteReactivoVigilancia(Date fechaInicial, Date fechaFinal) {
        List<ReporteReactivo> reporteDatos = null;
        ReporteReactivo registroIndividual = null;

        try {
            Query query1 = getManejadorEntidadesDireccion().createNamedQuery("reporteReactivoVigilancia");
            fechaInicial.setHours(0);
            fechaInicial.setMinutes(0);
            fechaInicial.setSeconds(0);
            fechaFinal.setHours(23);
            fechaFinal.setMinutes(59);
            fechaFinal.setSeconds(59);
            query1.setParameter(1, fechaInicial);
            query1.setParameter(2, fechaFinal);
            List<Object> cursor = query1.getResultList();
            if (cursor != null) {
                reporteDatos = new ArrayList<ReporteReactivo>();
                Iterator iteradorRegistros = cursor.iterator();

                while (iteradorRegistros.hasNext()) {
                    Object[] tupla = (Object[]) iteradorRegistros.next();
                    registroIndividual = new ReporteReactivo();
                    registroIndividual.setReporte(String.valueOf(tupla[0]));
                    registroIndividual.setDescrip_efecto(String.valueOf(tupla[1]));
                    registroIndividual.setFecha_ingreso(String.valueOf(tupla[2]));
                    registroIndividual.setInstitucion(String.valueOf(tupla[3]));
                    registroIndividual.setIdentificacion(String.valueOf(tupla[4]));
                    registroIndividual.setDireccion(String.valueOf(tupla[5]));
                    registroIndividual.setNaturaleza(String.valueOf(tupla[6]));
                    registroIndividual.setNombre_paciente(String.valueOf(tupla[7]));
                    registroIndividual.setTipidentifi(String.valueOf(tupla[8]));
                    registroIndividual.setIdentifi_paciente(String.valueOf(tupla[9]));
                    registroIndividual.setNombre_reactivo(String.valueOf(tupla[10]));
                    registroIndividual.setNroregsan(String.valueOf(tupla[11]));
                    registroIndividual.setFecha_venci(String.valueOf(tupla[12]));
                    registroIndividual.setLote(String.valueOf(tupla[13]));
                    registroIndividual.setPreRDiv(String.valueOf(tupla[14]));
                    reporteDatos.add(registroIndividual);
                }
            }
        } catch (Exception errorGeneracionMUID) {
            errorGeneracionMUID.printStackTrace();
            List<ReporteReactivo> vacio = new ArrayList<ReporteReactivo>();
            return (vacio);
        } finally {
            try {
            } catch (Exception errorSQL) {
                errorSQL.printStackTrace();
                List<ReporteReactivo> vacio = new ArrayList<ReporteReactivo>();
                return (vacio);
            }
        }

        return (reporteDatos);
    }

    @Override
    public List<PersonaInternet> generarReportePersonaInternet(Date fechaInicial, Date fechaFinal) {
        List<PersonaInternet> reporteDatos = null;
        PersonaInternet registroIndividual = null;

        try {
            Query query1 = getManejadorEntidadesDireccion().createNamedQuery("reportePersonaInternet");
            fechaInicial.setHours(0);
            fechaInicial.setMinutes(0);
            fechaInicial.setSeconds(0);
            fechaFinal.setHours(23);
            fechaFinal.setMinutes(59);
            fechaFinal.setSeconds(59);
            query1.setParameter(1, fechaInicial);
            query1.setParameter(2, fechaFinal);
            List<Object> cursor = query1.getResultList();
            if (cursor != null) {
                reporteDatos = new ArrayList<PersonaInternet>();
                Iterator iteradorRegistros = cursor.iterator();

                while (iteradorRegistros.hasNext()) {
                    Object[] tupla = (Object[]) iteradorRegistros.next();
                    registroIndividual = new PersonaInternet();
                    registroIndividual.setIdentificacion_empresa(String.valueOf(tupla[0]));
                    registroIndividual.setTipidentificacion_empresa(String.valueOf(tupla[1]));
                    registroIndividual.setNombre_empresa(String.valueOf(tupla[2]));
                    registroIndividual.setDireccion_empresa(String.valueOf(tupla[3]));
                    registroIndividual.setTelefono_empresa(String.valueOf(tupla[4]));
                    registroIndividual.setEmail_empresa(String.valueOf(tupla[5]));
                    registroIndividual.setFax(String.valueOf(tupla[6]));
                    registroIndividual.setIdentificacion_persona(String.valueOf(tupla[7]));
                    registroIndividual.setTipidentificacion_persona(String.valueOf(tupla[8]));
                    registroIndividual.setNombre_persona(String.valueOf(tupla[9]));
                    registroIndividual.setCargo_persona(String.valueOf(tupla[10]));
                    registroIndividual.setTelefono_persona(String.valueOf(tupla[11]));
                    registroIndividual.setEmail_persona(String.valueOf(tupla[12]));
                    registroIndividual.setUsuario(String.valueOf(tupla[13]));
                    registroIndividual.setClaveUsuario(String.valueOf(tupla[14]));
                    registroIndividual.setActivo(String.valueOf(tupla[15]));
                    registroIndividual.setID_Rol_Usuario(String.valueOf(tupla[16]));
                    registroIndividual.setFecha_ingreso(String.valueOf(tupla[17]));
                    registroIndividual.setEstado_usuario(String.valueOf(tupla[18]));
                    registroIndividual.setClasificacion_usuario(String.valueOf(tupla[19]));
                    registroIndividual.setPais(String.valueOf(tupla[20]));
                    registroIndividual.setDepartamento(String.valueOf(tupla[21]));
                    registroIndividual.setMunicipio(String.valueOf(tupla[22]));
                    reporteDatos.add(registroIndividual);
                }
            }
        } catch (Exception errorGeneracionMUID) {
            errorGeneracionMUID.printStackTrace();
            List<PersonaInternet> vacio = new ArrayList<PersonaInternet>();
            return (vacio);
        } finally {
            try {
            } catch (Exception errorSQL) {
                errorSQL.printStackTrace();
                List<PersonaInternet> vacio = new ArrayList<PersonaInternet>();
                return (vacio);
            }
        }

        return (reporteDatos);
    }

    @Override
    public List<ReporteFriarh> generarReporteFriahr(Date fechaInicial, Date fechaFinal) {
        List<ReporteFriarh> reporteDatos = null;
        ReporteFriarh registroIndividual = null;

        try {
            Query query1 = getManejadorEntidadesDireccion().createNamedQuery("reporteFriahr");
            fechaInicial.setHours(0);
            fechaInicial.setMinutes(0);
            fechaInicial.setSeconds(0);
            fechaFinal.setHours(23);
            fechaFinal.setMinutes(59);
            fechaFinal.setSeconds(59);
            query1.setParameter(1, fechaInicial);
            query1.setParameter(2, fechaFinal);
            List<Object> cursor = query1.getResultList();
            if (cursor != null) {
                reporteDatos = new ArrayList<ReporteFriarh>();
                Iterator iteradorRegistros = cursor.iterator();

                while (iteradorRegistros.hasNext()) {
                    Object[] tupla = (Object[]) iteradorRegistros.next();
                    registroIndividual = new ReporteFriarh();

                    registroIndividual.setFriarh(String.valueOf(tupla[0]));
                    registroIndividual.setCdg_tipoalerta(String.valueOf(tupla[1]));
                    registroIndividual.setFecha_ingreso(String.valueOf(tupla[2]));
                    registroIndividual.setNombre_reac(String.valueOf(tupla[3]));
                    registroIndividual.setNroregsan(String.valueOf(tupla[4]));
                    registroIndividual.setMarca(String.valueOf(tupla[5]));
                    registroIndividual.setModelo(String.valueOf(tupla[6]));
                    registroIndividual.setLote(String.valueOf(tupla[7]));
                    registroIndividual.setCdg_riesgo(String.valueOf(tupla[8]));
                    registroIndividual.setFuente(String.valueOf(tupla[9]));
                    registroIndividual.setCausas(String.valueOf(tupla[10]));
                    registroIndividual.setMedidas_correctivas(String.valueOf(tupla[11]));
                    registroIndividual.setCdg_notificante(String.valueOf(tupla[12]));
                    registroIndividual.setRazon_social_notificante(String.valueOf(tupla[13]));
                    registroIndividual.setNit(String.valueOf(tupla[14]));
                    registroIndividual.setDireccion(String.valueOf(tupla[15]));
                    registroIndividual.setDepartamento(String.valueOf(tupla[16]));
                    registroIndividual.setCiudad(String.valueOf(tupla[17]));
                    registroIndividual.setTelefono(String.valueOf(tupla[18]));
                    registroIndividual.setProfesion(String.valueOf(tupla[19]));
                    registroIndividual.setEmail(String.valueOf(tupla[20]));
                    registroIndividual.setNombre_notificante(String.valueOf(tupla[21]));
                    registroIndividual.setOtro_notificante(String.valueOf(tupla[22]));
                    registroIndividual.setEstado_reac(String.valueOf(tupla[23]));
                    registroIndividual.setReporte_eventos(String.valueOf(tupla[24]));
                    registroIndividual.setCuantos_eventos(String.valueOf(tupla[25]));
                    registroIndividual.setDescripcion_evento(String.valueOf(tupla[26]));
                    registroIndividual.setFecha_hurto(String.valueOf(tupla[27]));
                    registroIndividual.setDescripcion_hurto(String.valueOf(tupla[28]));
                    registroIndividual.setFiscalia(String.valueOf(tupla[29]));
                    registroIndividual.setAutorizacion(String.valueOf(tupla[30]));
                    registroIndividual.setEstado_reporte(String.valueOf(tupla[31]));
                    registroIndividual.setInternet(String.valueOf(tupla[32]));
                    registroIndividual.setExpediente_reac(String.valueOf(tupla[33]));
                    registroIndividual.setNombre_agencia(String.valueOf(tupla[34]));
                    registroIndividual.setFecha_radicado(String.valueOf(tupla[35]));
                    registroIndividual.setRadicado(String.valueOf(tupla[36]));
                    registroIndividual.setProblema_reac(String.valueOf(tupla[37]));
                    registroIndividual.setOtro_estadoreac(String.valueOf(tupla[38]));

                    reporteDatos.add(registroIndividual);
                }
            }
        } catch (Exception errorGeneracionMUID) {
            errorGeneracionMUID.printStackTrace();
            List<ReporteFriarh> vacio = new ArrayList<ReporteFriarh>();
            return (vacio);
        } finally {
            try {
            } catch (Exception errorSQL) {
                errorSQL.printStackTrace();
                List<ReporteFriarh> vacio = new ArrayList<ReporteFriarh>();
                return (vacio);
            }
        }

        return (reporteDatos);
    }

    @Override
    public List<ReporteFriarh> generarReporteFriahrFabricante(String nitEmpresa) {
        List<ReporteFriarh> reporteDatos = null;
        ReporteFriarh registroIndividual = null;

        try {
            Query query1 = getManejadorEntidadesDireccion().createNamedQuery("reporteFriahrSecretaria");
            query1.setParameter(1, nitEmpresa);
            List<Object> cursor = query1.getResultList();
            if (cursor != null) {
                reporteDatos = new ArrayList<ReporteFriarh>();
                Iterator iteradorRegistros = cursor.iterator();

                while (iteradorRegistros.hasNext()) {
                    Object[] tupla = (Object[]) iteradorRegistros.next();
                    registroIndividual = new ReporteFriarh();

                    registroIndividual.setFriarh(String.valueOf(tupla[0]));
                    registroIndividual.setCdg_tipoalerta(String.valueOf(tupla[1]));
                    registroIndividual.setFecha_ingreso(String.valueOf(tupla[2]));
                    registroIndividual.setNombre_reac(String.valueOf(tupla[3]));
                    registroIndividual.setNroregsan(String.valueOf(tupla[4]));
                    registroIndividual.setMarca(String.valueOf(tupla[5]));
                    registroIndividual.setModelo(String.valueOf(tupla[6]));
                    registroIndividual.setLote(String.valueOf(tupla[7]));
                    registroIndividual.setCdg_riesgo(String.valueOf(tupla[8]));
                    registroIndividual.setFuente(String.valueOf(tupla[9]));
                    registroIndividual.setCausas(String.valueOf(tupla[10]));
                    registroIndividual.setMedidas_correctivas(String.valueOf(tupla[11]));
                    registroIndividual.setCdg_notificante(String.valueOf(tupla[12]));
                    registroIndividual.setRazon_social_notificante(String.valueOf(tupla[13]));
                    registroIndividual.setNit(String.valueOf(tupla[14]));
                    registroIndividual.setDireccion(String.valueOf(tupla[15]));
                    registroIndividual.setDepartamento(String.valueOf(tupla[16]));
                    registroIndividual.setCiudad(String.valueOf(tupla[17]));
                    registroIndividual.setTelefono(String.valueOf(tupla[18]));
                    registroIndividual.setProfesion(String.valueOf(tupla[19]));
                    registroIndividual.setEmail(String.valueOf(tupla[20]));
                    registroIndividual.setNombre_notificante(String.valueOf(tupla[21]));
                    registroIndividual.setOtro_notificante(String.valueOf(tupla[22]));
                    registroIndividual.setEstado_reac(String.valueOf(tupla[23]));
                    registroIndividual.setReporte_eventos(String.valueOf(tupla[24]));
                    registroIndividual.setCuantos_eventos(String.valueOf(tupla[25]));
                    registroIndividual.setDescripcion_evento(String.valueOf(tupla[26]));
                    registroIndividual.setFecha_hurto(String.valueOf(tupla[27]));
                    registroIndividual.setDescripcion_hurto(String.valueOf(tupla[28]));
                    registroIndividual.setFiscalia(String.valueOf(tupla[29]));
                    registroIndividual.setAutorizacion(String.valueOf(tupla[30]));
                    registroIndividual.setEstado_reporte(String.valueOf(tupla[31]));
                    registroIndividual.setInternet(String.valueOf(tupla[32]));
                    registroIndividual.setExpediente_reac(String.valueOf(tupla[33]));
                    registroIndividual.setNombre_agencia(String.valueOf(tupla[34]));
                    registroIndividual.setFecha_radicado(String.valueOf(tupla[35]));
                    registroIndividual.setRadicado(String.valueOf(tupla[36]));
                    registroIndividual.setProblema_reac(String.valueOf(tupla[37]));
                    registroIndividual.setOtro_estadoreac(String.valueOf(tupla[38]));
                    reporteDatos.add(registroIndividual);
                }
            }
        } catch (Exception errorGeneracionMUID) {
            errorGeneracionMUID.printStackTrace();
            List<ReporteFriarh> vacio = new ArrayList<ReporteFriarh>();
            return (vacio);
        } finally {
            try {
            } catch (Exception errorSQL) {
                errorSQL.printStackTrace();
                List<ReporteFriarh> vacio = new ArrayList<ReporteFriarh>();
                return (vacio);
            }
        }

        return (reporteDatos);
    }

    @Override
    public List<RegistroReactivo> generarRegistroRepReactivo(Date fechaInicial, Date fechaFinal) {
        List<RegistroReactivo> reporteDatos = null;
        RegistroReactivo registroIndividual = null;

        try {
            Query query1 = getManejadorEntidadesDireccion().createNamedQuery("registroRepReactivo");
            fechaInicial.setHours(0);
            fechaInicial.setMinutes(0);
            fechaInicial.setSeconds(0);
            fechaFinal.setHours(23);
            fechaFinal.setMinutes(59);
            fechaFinal.setSeconds(59);
            query1.setParameter(1, fechaInicial);
            query1.setParameter(2, fechaFinal);
            List<Object> cursor = query1.getResultList();
            if (cursor != null) {
                reporteDatos = new ArrayList<RegistroReactivo>();
                Iterator iteradorRegistros = cursor.iterator();

                while (iteradorRegistros.hasNext()) {
                    Object[] tupla = (Object[]) iteradorRegistros.next();
                    registroIndividual = new RegistroReactivo();
                    registroIndividual.setId(String.valueOf(tupla[0]));
                    registroIndividual.setFecha_solicitud(String.valueOf(tupla[1]));
                    registroIndividual.setNombre_institucion(String.valueOf(tupla[2]));
                    registroIndividual.setNaturaleza(String.valueOf(tupla[3]));
                    registroIndividual.setComplejidad(String.valueOf(tupla[4]));
                    registroIndividual.setDireccion_organizacion(String.valueOf(tupla[5]));
                    registroIndividual.setNit(String.valueOf(tupla[6]));
                    registroIndividual.setEmail_corporativo(String.valueOf(tupla[7]));
                    registroIndividual.setCdg_funcionario(String.valueOf(tupla[8]));
                    registroIndividual.setTelefono_organiz(String.valueOf(tupla[9]));
                    registroIndividual.setFax_organiz(String.valueOf(tupla[10]));
                    registroIndividual.setExt_organiz(String.valueOf(tupla[11]));
                    registroIndividual.setOtra_modalidad(String.valueOf(tupla[12]));
                    registroIndividual.setDescripcionmodalidad(String.valueOf(tupla[13]));
                    registroIndividual.setMunicipio(String.valueOf(tupla[14]));
                    registroIndividual.setDepartamento(String.valueOf(tupla[15]));
                    registroIndividual.setPais(String.valueOf(tupla[16]));
                    registroIndividual.setFecha_solicitud(String.valueOf(tupla[17]));
                    registroIndividual.setNombre_solic(String.valueOf(tupla[18]));
                    registroIndividual.setArea_empresa(String.valueOf(tupla[19]));
                    registroIndividual.setCargos(String.valueOf(tupla[20]));
                    registroIndividual.setDireccion_solicitante(String.valueOf(tupla[21]));
                    registroIndividual.setTelefono_solic(String.valueOf(tupla[22]));
                    registroIndividual.setEmail_personal(String.valueOf(tupla[23]));
                    registroIndividual.setPais_notificacion(String.valueOf(tupla[24]));
                    registroIndividual.setMunicipio_notificacion(String.valueOf(tupla[25]));
                    registroIndividual.setDepartamento_notificacion(String.valueOf(tupla[26]));
                    registroIndividual.setCelular_solic(String.valueOf(tupla[27]));
                    registroIndividual.setCdg_funcionario(String.valueOf(tupla[28]));
                    registroIndividual.setCedula_solicitante(String.valueOf(tupla[29]));
                    registroIndividual.setId_red_persona(String.valueOf(tupla[30]));
                    reporteDatos.add(registroIndividual);
                }
            }
        } catch (Exception errorGeneracionMUID) {
            errorGeneracionMUID.printStackTrace();
            List<RegistroReactivo> vacio = new ArrayList<RegistroReactivo>();
            return (vacio);
        } finally {
            try {
            } catch (Exception errorSQL) {
                errorSQL.printStackTrace();
                List<RegistroReactivo> vacio = new ArrayList<RegistroReactivo>();
                return (vacio);
            }
        }

        return (reporteDatos);
    }

    @Override
    public List<String> obtenerListadoRolesUsuario() {
        List<String> comboDatos = null;
        List<String> comboDatosVacio = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";

        try {
            consulta = "SELECT IDROL, nombre_rol from  dbo.reactivo_roles ORDER BY IDROL ASC ";
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            cursor = sentencia.executeQuery();
            if (cursor != null) {
                comboDatos = new ArrayList<String>();
                while (cursor.next()) {
                    comboDatos.add(cursor.getString(1));
                    comboDatos.add(cursor.getString(2));
                }
            }
        } catch (Exception errorobtenerTiposDeSucursal) {
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<String>();
            comboDatosVacio.add("0");
            comboDatosVacio.add("SIN VALOR");
            return (comboDatosVacio);
        } finally {
            try {
                if (cursor != null) {
                    cursor.close();
                }

                if (sentencia != null) {
                    sentencia.close();
                }

                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException errorSQL) {
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<String>();
                comboDatosVacio.add("0");
                comboDatosVacio.add("SIN VALOR");
                return (comboDatosVacio);
            }
        }

        return (comboDatos);
    }

    @Override
    public List<ReportePrerdiv> generarReportePreRDivs(String departamento, String municipio, Date fechaInicial, Date fechaFinal) {
        List<ReportePrerdiv> comboDatos = null;
        List<ReportePrerdiv> comboDatosVacio = null;
        ReportePrerdiv registroIndividual = null;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet cursor = null;
        String consulta = "";
        int i = 0;
        int j = 0;
        try {
            logEJBReactivo.info("Departamento: " + departamento);
            logEJBReactivo.info("Municipio: " + municipio);
            logEJBReactivo.info("Fecha inicial = " + fechaInicial);
            logEJBReactivo.info("Fecha final = " + fechaFinal);
            consulta
                    = "SELECT "
                    + "    datos.reporte as precol, "
                    + "    (select dep.descripcion from dbo.departamentos dep where dep.cod_depart = reportante.cod_depart) as departamento, "
                    + "    (select mun.descripcion from dbo.municipios mun where mun.cod_mun = reportante.cod_mun) as municipio, "
                    + "    camposcomp.organizacion as nombre_reportante, "
                    + "    datos.idrol as id_rol, "
                    + "    datos.fecha_ingreso as fecha_ingreso, "
                    + "    datos.descrip_efecto as descripcionEvento, "
                    + "    reactivo.nombre_reactivo as nombre_dispositivo, "
                    + "    camposcomp.codigoTipoDiagnostico as tipo_diagnostico, "
                    + "    evalua.causa_probable as codigo_causa, "
                    + "    camposcomp.estadoReporte as estado_caso, "
                    + "    instituc.institucion as institucion_incidente, "
                    + "    instituc.identificacion as nit_incidente, "
                    + "    reactivo.nroregsan as registro_sanitario,  "
                    + "    camposcomp.gestionEnte as medida_ejecutada,  "
                    + "    CASE datos.cdg_seriedad  WHEN 1 then 'APROBADO' WHEN 0 THEN 'PENDIENTE' ELSE 'RECHAZADO ' END as codigo_seriedad  "
                    + "    from "
                    + "    dbo.reactivo_eventos_temp datos, "
                    + "    dbo.reactivo_producto_temp reactivo, "
                    + "    dbo.reactivo_paciente_temp paci, "
                    + "    dbo.reactivo_gestionrealizada_temp evalua, "
                    + "        dbo.reactivo_reportante_temp reportante, "
                    + "        dbo.reactivo_camposcomp_temp camposcomp, "
                    + "        dbo.reactivo_institucion_temp instituc "
                    + "    where    "
                    + "    (datos.reporte = reactivo.reporte) and "
                    + "    (datos.reporte = paci.reporte) and "
                    + "    (datos.reporte = evalua.reporte)  and "
                    + "        (datos.reporte = reportante.reporte) and "
                    + "        (datos.reporte = camposcomp.reporte) and "
                    + "        (datos.reporte = instituc.reporte) "
                    + "  ";
            if (departamento != null) {
                consulta += "and  reportante.cod_depart = ?  ";

                if (municipio != null) {
                    consulta += " and reportante.cod_mun = ? ";
                }
            }
            if (fechaInicial != null && fechaFinal != null) {
                if (departamento != null) {
                    if (municipio != null) {
                        consulta += " and (datos.fecha_ingreso >= ? and datos.fecha_ingreso <= ?) ";
                    } else {
                        consulta += " and (datos.fecha_ingreso >= ? and datos.fecha_ingreso <= ?) ";
                    }

                } else {
                    consulta += " and (datos.fecha_ingreso >= ? and datos.fecha_ingreso <= ?) ";
                }
            }
            logEJBReactivo.info(consulta);
            conexion = obtenerConexionDataSource();
            sentencia = conexion.prepareStatement(consulta);
            if (departamento != null) {
                sentencia.setString(1, departamento);

                if (municipio != null) {
                    sentencia.setString(2, municipio);
                }
            }
            if (fechaInicial != null && fechaFinal != null) {
                if (departamento != null) {
                    if (municipio != null) {
                        sentencia.setTimestamp(3, obtenerFechaTimeStamp(fechaInicial, "inicial"));
                        sentencia.setTimestamp(4, obtenerFechaTimeStamp(fechaFinal, "final"));
                    } else {
                        sentencia.setTimestamp(2, obtenerFechaTimeStamp(fechaInicial, "inicial"));
                        sentencia.setTimestamp(3, obtenerFechaTimeStamp(fechaFinal, "final"));
                    }
                } else {
                    sentencia.setTimestamp(1, obtenerFechaTimeStamp(fechaInicial, "inicial"));
                    sentencia.setTimestamp(2, obtenerFechaTimeStamp(fechaFinal, "final"));
                }
            }
            cursor = sentencia.executeQuery();
            if (cursor != null) {
                comboDatos = new ArrayList<ReportePrerdiv>();
                while (cursor.next()) {
                    registroIndividual = new ReportePrerdiv();
                    registroIndividual.setPrecol(cursor.getString(1));
                    registroIndividual.setDepartamento(cursor.getString(2));
                    registroIndividual.setMunicipio(cursor.getString(3));
                    registroIndividual.setNombre_reportante(cursor.getString(4));
                    registroIndividual.setId_rol(cursor.getString(5));
                    registroIndividual.setFecha_ingreso(cursor.getDate(6));
                    registroIndividual.setDescripcionEvento(cursor.getString(7));
                    registroIndividual.setNombre_dispositivo(cursor.getString(8));
                    registroIndividual.setTipo_reporte(cursor.getString(9));
                    registroIndividual.setCodigo_causa(cursor.getString(10));
                    registroIndividual.setEstadoCaso(cursor.getString(11));
                    registroIndividual.setEntidadIncidente(cursor.getString(12));
                    registroIndividual.setNitEntidadIncidente(cursor.getString(13));
                    registroIndividual.setRegistroSanitarioDisp(cursor.getString(14));
                    registroIndividual.setMedidaEjecutada(cursor.getString(15));
                    registroIndividual.setEstadoReporte(cursor.getString(16));
                    comboDatos.add(registroIndividual);
                }
            } else {
                comboDatos = new ArrayList<ReportePrerdiv>();
            }
        }//fin del try
        catch (Exception errorobtenerTiposDeSucursal) {
            errorobtenerTiposDeSucursal.printStackTrace();
            comboDatosVacio = new ArrayList<ReportePrerdiv>();
            return (comboDatosVacio);
        } finally {
            try {
                if (cursor != null) {
                    cursor.close();
                }

                if (sentencia != null) {
                    sentencia.close();
                }

                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException errorSQL) {
                errorSQL.printStackTrace();
                comboDatosVacio = new ArrayList<ReportePrerdiv>();
                return (comboDatosVacio);
            }
        }

        return (comboDatos);
    }

    @Override
    public List<ReporteTrimestralEvento> generarConsultaReportesTrimestralesConEvento(Date fechaInicial, Date fechaFinal
    ) {
        List<ReporteTrimestralEvento> reporteDatos = null;
        ReporteTrimestralEvento registroIndividual = null;

        try {
            Query query1 = getManejadorEntidadesDireccion().createNamedQuery("consultaReportesTrimestrales");

            fechaInicial.setHours(0);
            fechaInicial.setMinutes(0);
            fechaInicial.setSeconds(0);
            fechaFinal.setHours(23);
            fechaFinal.setMinutes(59);
            fechaFinal.setSeconds(59);
            query1.setParameter(1, fechaInicial);
            query1.setParameter(2, fechaFinal);
            List<Object> cursor = query1.getResultList();
            if (cursor != null) {
                reporteDatos = new ArrayList<ReporteTrimestralEvento>();
                Iterator iteradorRegistros = cursor.iterator();

                while (iteradorRegistros.hasNext()) {
                    Object[] tupla = (Object[]) iteradorRegistros.next();
                    registroIndividual = new ReporteTrimestralEvento();
                    registroIndividual.setReporte(String.valueOf(tupla[0]));
                    registroIndividual.setInstitucion(String.valueOf(tupla[1]));
                    registroIndividual.setDepartamento(String.valueOf(tupla[2]));
                    registroIndividual.setCiudad(String.valueOf(tupla[3]));
                    registroIndividual.setDireccion(String.valueOf(tupla[4]));
                    registroIndividual.setIdentificacion(String.valueOf(tupla[5]));
                    registroIndividual.setComplejidad(String.valueOf(tupla[6]));
                    registroIndividual.setNaturaleza(String.valueOf(tupla[7]));
                    registroIndividual.setNombre_paciente(String.valueOf(tupla[8]));
                    registroIndividual.setTipidentifi(String.valueOf(tupla[9]));
                    registroIndividual.setIdentifi(String.valueOf(tupla[10]));
                    registroIndividual.setGenero(String.valueOf(tupla[11]));
                    registroIndividual.setEdad(String.valueOf(tupla[12]));
                    registroIndividual.setEdad_en(String.valueOf(tupla[13]));
                    registroIndividual.setNombre_reactivo(String.valueOf(tupla[14]));
                    registroIndividual.setNroregsan(String.valueOf(tupla[15]));
                    registroIndividual.setExpediente(String.valueOf(tupla[16]));
                    registroIndividual.setCodigoUnicoReactivo(String.valueOf(tupla[17]));
                    registroIndividual.setCodigoTipoDiagnostico(String.valueOf(tupla[18]));
                    registroIndividual.setLote(String.valueOf(tupla[19]));
                    registroIndividual.setReferencia(String.valueOf(tupla[20]));
                    registroIndividual.setFecha_venci(String.valueOf(tupla[21]));
                    registroIndividual.setProcedencia(String.valueOf(tupla[22]));
                    registroIndividual.setCadena_frio(String.valueOf(tupla[23]));
                    registroIndividual.setTemperatura(String.valueOf(tupla[24]));
                    registroIndividual.setCategoriaReactivo(String.valueOf(tupla[25]));
                    registroIndividual.setCondici_almacen(String.valueOf(tupla[26]));
                    registroIndividual.setIndRealizaRecepcion(String.valueOf(tupla[27]));
                    registroIndividual.setCerti_analisis(String.valueOf(tupla[28]));
                    registroIndividual.setFabricante(String.valueOf(tupla[29]));
                    registroIndividual.setImportador(String.valueOf(tupla[30]));
                    registroIndividual.setArea_funciona(String.valueOf(tupla[31]));
                    registroIndividual.setFecha_ingreso(String.valueOf(tupla[32]));
                    registroIndividual.setDescrip_efecto(String.valueOf(tupla[33]));
                    registroIndividual.setDesenlace(String.valueOf(tupla[34]));
                    registroIndividual.setOtro_desenlace(String.valueOf(tupla[35]));
                    registroIndividual.setGestion_riesgo(String.valueOf(tupla[36]));
                    registroIndividual.setAnalisis_efecto(String.valueOf(tupla[37]));
                    registroIndividual.setHerramienta_analisis(String.valueOf(tupla[38]));
                    registroIndividual.setOtra_herramienta(String.valueOf(tupla[39]));
                    registroIndividual.setCausa_efecto(String.valueOf(tupla[40]));
                    registroIndividual.setCausa_probable(String.valueOf(tupla[41]));
                    registroIndividual.setDescripcion_causa(String.valueOf(tupla[42]));
                    registroIndividual.setCodigoCausa(String.valueOf(tupla[43]));
                    registroIndividual.setAcciones(String.valueOf(tupla[44]));
                    registroIndividual.setDescripcion_acciones(String.valueOf(tupla[45]));
                    registroIndividual.setEnviado_distri_import(String.valueOf(tupla[46]));
                    registroIndividual.setFecha_notificacion(String.valueOf(tupla[47]));
                    registroIndividual.setFechaEnvioReactivo(String.valueOf(tupla[48]));
                    registroIndividual.setNombres_apellidos(String.valueOf(tupla[49]));
                    registroIndividual.setProfesion(String.valueOf(tupla[50]));
                    registroIndividual.setOrganizacion(String.valueOf(tupla[51]));
                    registroIndividual.setDireccion_reportante(String.valueOf(tupla[52]));
                    registroIndividual.setTelefono(String.valueOf(tupla[53]));
                    registroIndividual.setCod_depart(String.valueOf(tupla[54]));
                    registroIndividual.setCod_mun(String.valueOf(tupla[55]));
                    registroIndividual.setEmail(String.valueOf(tupla[56]));
                    registroIndividual.setFecha_notificacion_reportante(String.valueOf(tupla[57]));
                    registroIndividual.setDivulgacion(String.valueOf(tupla[58]));
                    registroIndividual.setCdg_tiporeportante(String.valueOf(tupla[59]));
                    registroIndividual.setEstadoReporte(String.valueOf(tupla[60]));
                    registroIndividual.setFechaGestionEnte(String.valueOf(tupla[61]));
                    registroIndividual.setGestionEnte(String.valueOf(tupla[62]));
                    registroIndividual.setEstadoGestionEnte(String.valueOf(tupla[63]));
                    registroIndividual.setFechaGestionInvima(String.valueOf(tupla[64]));
                    registroIndividual.setObservacionInvima(String.valueOf(tupla[65]));
                    registroIndividual.setCodigoCausaProbable(String.valueOf(tupla[66]));
                    registroIndividual.setTerminoCausa(String.valueOf(tupla[67]));
                    registroIndividual.setDescripcionCausa(String.valueOf(tupla[68]));
                    reporteDatos.add(registroIndividual);
                }
            }
        } catch (Exception errorGeneracionMUID) {
            errorGeneracionMUID.printStackTrace();
            List<ReporteTrimestralEvento> vacio = new ArrayList<ReporteTrimestralEvento>();
            return (vacio);
        } finally {
            try {
            } catch (Exception errorSQL) {
                errorSQL.printStackTrace();
                List<ReporteTrimestralEvento> vacio = new ArrayList<ReporteTrimestralEvento>();
                return (vacio);
            }
        }

        return (reporteDatos);
    }

    @Override
    public List<ReporteTrimestralEvento> generarConsultaReportesTrimestralesConEventoSecretaria(Date fechaInicial, Date fechaFinal,
             String depto
    ) {
        List<ReporteTrimestralEvento> reporteDatos = null;
        ReporteTrimestralEvento registroIndividual = null;

        try {
            Query query1 = getManejadorEntidadesDireccion().createNamedQuery("consultaReportesTrimestralesSecretaria");
            fechaInicial.setHours(0);
            fechaInicial.setMinutes(0);
            fechaInicial.setSeconds(0);
            fechaFinal.setHours(23);
            fechaFinal.setMinutes(59);
            fechaFinal.setSeconds(59);
            logEJBReactivo.info("DEPARTAMENTO DE LA SECRETARIA = " + depto);
            logEJBReactivo.info("FECHA INICIAL EN EL EJB = " + fechaInicial);
            logEJBReactivo.info("FECHA FINAL EN EL EJB = " + fechaFinal);
            query1.setParameter(1, fechaInicial);
            query1.setParameter(2, fechaFinal);
            query1.setParameter(3, depto);
            List<Object> cursor = query1.getResultList();
            if (cursor != null) {
                reporteDatos = new ArrayList<ReporteTrimestralEvento>();
                Iterator iteradorRegistros = cursor.iterator();

                while (iteradorRegistros.hasNext()) {
                    Object[] tupla = (Object[]) iteradorRegistros.next();
                    registroIndividual = new ReporteTrimestralEvento();
                    registroIndividual.setReporte(String.valueOf(tupla[0]));
                    registroIndividual.setInstitucion(String.valueOf(tupla[1]));
                    registroIndividual.setDepartamento(String.valueOf(tupla[2]));
                    registroIndividual.setCiudad(String.valueOf(tupla[3]));
                    registroIndividual.setDireccion(String.valueOf(tupla[4]));
                    registroIndividual.setIdentificacion(String.valueOf(tupla[5]));
                    registroIndividual.setComplejidad(String.valueOf(tupla[6]));
                    registroIndividual.setNaturaleza(String.valueOf(tupla[7]));
                    registroIndividual.setNombre_paciente(String.valueOf(tupla[8]));
                    registroIndividual.setTipidentifi(String.valueOf(tupla[9]));
                    registroIndividual.setIdentifi(String.valueOf(tupla[10]));
                    registroIndividual.setGenero(String.valueOf(tupla[11]));
                    registroIndividual.setEdad(String.valueOf(tupla[12]));
                    registroIndividual.setEdad_en(String.valueOf(tupla[13]));
                    registroIndividual.setNombre_reactivo(String.valueOf(tupla[14]));
                    registroIndividual.setNroregsan(String.valueOf(tupla[15]));
                    registroIndividual.setExpediente(String.valueOf(tupla[16]));
                    registroIndividual.setCodigoUnicoReactivo(String.valueOf(tupla[17]));
                    registroIndividual.setCodigoTipoDiagnostico(String.valueOf(tupla[18]));
                    registroIndividual.setLote(String.valueOf(tupla[19]));
                    registroIndividual.setReferencia(String.valueOf(tupla[20]));
                    registroIndividual.setFecha_venci(String.valueOf(tupla[21]));
                    registroIndividual.setProcedencia(String.valueOf(tupla[22]));
                    registroIndividual.setCadena_frio(String.valueOf(tupla[23]));
                    registroIndividual.setTemperatura(String.valueOf(tupla[24]));
                    registroIndividual.setCategoriaReactivo(String.valueOf(tupla[25]));
                    registroIndividual.setCondici_almacen(String.valueOf(tupla[26]));
                    registroIndividual.setIndRealizaRecepcion(String.valueOf(tupla[27]));
                    registroIndividual.setCerti_analisis(String.valueOf(tupla[28]));
                    registroIndividual.setFabricante(String.valueOf(tupla[29]));
                    registroIndividual.setImportador(String.valueOf(tupla[30]));
                    registroIndividual.setArea_funciona(String.valueOf(tupla[31]));
                    registroIndividual.setFecha_ingreso(String.valueOf(tupla[32]));
                    registroIndividual.setDescrip_efecto(String.valueOf(tupla[33]));
                    registroIndividual.setDesenlace(String.valueOf(tupla[34]));
                    registroIndividual.setOtro_desenlace(String.valueOf(tupla[35]));
                    registroIndividual.setGestion_riesgo(String.valueOf(tupla[36]));
                    registroIndividual.setAnalisis_efecto(String.valueOf(tupla[37]));
                    registroIndividual.setHerramienta_analisis(String.valueOf(tupla[38]));
                    registroIndividual.setOtra_herramienta(String.valueOf(tupla[39]));
                    registroIndividual.setCausa_efecto(String.valueOf(tupla[40]));
                    registroIndividual.setCausa_probable(String.valueOf(tupla[41]));
                    registroIndividual.setDescripcion_causa(String.valueOf(tupla[42]));
                    registroIndividual.setCodigoCausa(String.valueOf(tupla[43]));
                    registroIndividual.setAcciones(String.valueOf(tupla[44]));
                    registroIndividual.setDescripcion_acciones(String.valueOf(tupla[45]));
                    registroIndividual.setEnviado_distri_import(String.valueOf(tupla[46]));
                    registroIndividual.setFecha_notificacion(String.valueOf(tupla[47]));
                    registroIndividual.setFechaEnvioReactivo(String.valueOf(tupla[48]));
                    registroIndividual.setNombres_apellidos(String.valueOf(tupla[49]));
                    registroIndividual.setProfesion(String.valueOf(tupla[50]));
                    registroIndividual.setOrganizacion(String.valueOf(tupla[51]));
                    registroIndividual.setDireccion_reportante(String.valueOf(tupla[52]));
                    registroIndividual.setTelefono(String.valueOf(tupla[53]));
                    registroIndividual.setCod_depart(String.valueOf(tupla[54]));
                    registroIndividual.setCod_mun(String.valueOf(tupla[55]));
                    registroIndividual.setEmail(String.valueOf(tupla[56]));
                    registroIndividual.setFecha_notificacion_reportante(String.valueOf(tupla[57]));
                    registroIndividual.setDivulgacion(String.valueOf(tupla[58]));
                    registroIndividual.setCdg_tiporeportante(String.valueOf(tupla[59]));
                    registroIndividual.setEstadoReporte(String.valueOf(tupla[60]));
                    registroIndividual.setFechaGestionEnte(String.valueOf(tupla[61]));
                    registroIndividual.setGestionEnte(String.valueOf(tupla[62]));
                    registroIndividual.setEstadoGestionEnte(String.valueOf(tupla[63]));
                    registroIndividual.setFechaGestionInvima(String.valueOf(tupla[64]));
                    registroIndividual.setObservacionInvima(String.valueOf(tupla[65]));
                    registroIndividual.setCodigoCausaProbable(String.valueOf(tupla[66]));
                    registroIndividual.setTerminoCausa(String.valueOf(tupla[67]));
                    registroIndividual.setDescripcionCausa(String.valueOf(tupla[68]));
                    reporteDatos.add(registroIndividual);
                }
            }
        } catch (Exception errorGeneracionMUID) {
            errorGeneracionMUID.printStackTrace();
            List<ReporteTrimestralEvento> vacio = new ArrayList<ReporteTrimestralEvento>();
            return (vacio);
        } finally {
            try {
            } catch (Exception errorSQL) {
                errorSQL.printStackTrace();
                List<ReporteTrimestralEvento> vacio = new ArrayList<ReporteTrimestralEvento>();
                return (vacio);
            }
        }

        return (reporteDatos);
    }

    private static java.sql.Timestamp obtenerFechaTimeStamp(java.util.Date fechaOriginal, String tipo) {
        java.sql.Timestamp currentTimestamp = null;

        if (tipo.equals("inicial")) {
            fechaOriginal.setHours(0);
            fechaOriginal.setMinutes(0);
            fechaOriginal.setSeconds(0);
        } else {
            fechaOriginal.setHours(23);
            fechaOriginal.setMinutes(59);
            fechaOriginal.setSeconds(59);
        }

        currentTimestamp = new java.sql.Timestamp(fechaOriginal.getTime());

        return (currentTimestamp);
    }

    @Override
    public List<ReporteEfectoAdverso> generarConsultaReportesEventoAdverso(Date fechaInicial, Date fechaFinal) {
        List<ReporteEfectoAdverso> reporteDatos = null;
        ReporteEfectoAdverso registroIndividual = null;

        try {
            Query query1 = getManejadorEntidadesDireccion().createNamedQuery("reporteEventoAdversoAdministrador");
            fechaInicial.setHours(0);
            fechaInicial.setMinutes(0);
            fechaInicial.setSeconds(0);
            fechaFinal.setHours(23);
            fechaFinal.setMinutes(59);
            fechaFinal.setSeconds(59);
            query1.setParameter(1, fechaInicial);
            query1.setParameter(2, fechaFinal);
            List<Object> cursor = query1.getResultList();
            if (cursor != null) {
                reporteDatos = new ArrayList<ReporteEfectoAdverso>();
                Iterator iteradorRegistros = cursor.iterator();

                while (iteradorRegistros.hasNext()) {
                    Object[] tupla = (Object[]) iteradorRegistros.next();
                    registroIndividual = new ReporteEfectoAdverso();
                    registroIndividual.setReporte(String.valueOf(tupla[0]));
                    registroIndividual.setDescrip_efecto(String.valueOf(tupla[1]));
                    registroIndividual.setFecha_ingreso(String.valueOf(tupla[2]));
                    registroIndividual.setFech_evento(String.valueOf(tupla[3]));
                    registroIndividual.setEfecto_indeseado(String.valueOf(tupla[4]));
                    registroIndividual.setFech_reporte(String.valueOf(tupla[5]));
                    registroIndividual.setCdg_problema(String.valueOf(tupla[6]));
                    registroIndividual.setClasificacion(String.valueOf(tupla[7]));
                    registroIndividual.setInstitucion(String.valueOf(tupla[8]));
                    registroIndividual.setIdentificacion(String.valueOf(tupla[9]));
                    registroIndividual.setDireccion(String.valueOf(tupla[10]));
                    registroIndividual.setNaturaleza(String.valueOf(tupla[11]));
                    registroIndividual.setComplejidad(String.valueOf(tupla[12]));
                    registroIndividual.setCod_depart(String.valueOf(tupla[13]));
                    registroIndividual.setCod_mun(String.valueOf(tupla[14]));
                    registroIndividual.setEmail(String.valueOf(tupla[15]));
                    registroIndividual.setTelefono(String.valueOf(tupla[16]));
                    registroIndividual.setNombre_paciente(String.valueOf(tupla[17]));
                    registroIndividual.setTipidentifi(String.valueOf(tupla[18]));
                    registroIndividual.setIdentifi(String.valueOf(tupla[19]));
                    registroIndividual.setEdad(String.valueOf(tupla[20]));
                    registroIndividual.setEdad_en(String.valueOf(tupla[21]));
                    registroIndividual.setGenero(String.valueOf(tupla[22]));
                    registroIndividual.setDirecc_paciente(String.valueOf(tupla[23]));
                    registroIndividual.setTelefo_paciente(String.valueOf(tupla[24]));
                    registroIndividual.setNombre_reactivo(String.valueOf(tupla[25]));
                    registroIndividual.setNroregsan(String.valueOf(tupla[26]));
                    registroIndividual.setFecha_venci(String.valueOf(tupla[27]));
                    registroIndividual.setLote(String.valueOf(tupla[28]));
                    registroIndividual.setProcedencia(String.valueOf(tupla[29]));
                    registroIndividual.setCadena_frio(String.valueOf(tupla[30]));
                    registroIndividual.setTemperatura(String.valueOf(tupla[31]));
                    registroIndividual.setCerti_analisis(String.valueOf(tupla[32]));
                    registroIndividual.setDistri_usuario(String.valueOf(tupla[33]));
                    registroIndividual.setCondici_almacen(String.valueOf(tupla[34]));
                    registroIndividual.setArea_funciona(String.valueOf(tupla[35]));
                    registroIndividual.setOtra_area(String.valueOf(tupla[36]));
                    registroIndividual.setCausa_efecto(String.valueOf(tupla[37]));
                    registroIndividual.setCausa_probable(String.valueOf(tupla[38]));

                    registroIndividual.setNotifico_importador(String.valueOf(tupla[39]));
                    registroIndividual.setNotifico_fabricante(String.valueOf(tupla[40]));
                    registroIndividual.setNotifico_comercializador(String.valueOf(tupla[41]));
                    registroIndividual.setNotifico_distribuidor(String.valueOf(tupla[42]));

                    registroIndividual.setFechaNotificacion(String.valueOf(tupla[43]));
                    registroIndividual.setEnviadoDistImportador(String.valueOf(tupla[44]));
                    registroIndividual.setGestionRiesgo(String.valueOf(tupla[45]));
                    registroIndividual.setAnalisisEfecto(String.valueOf(tupla[46]));
                    registroIndividual.setHerramientaAnalisis(String.valueOf(tupla[47]));

                    registroIndividual.setOtra_herramienta(String.valueOf(tupla[48]));
                    registroIndividual.setDescripcionCausa(String.valueOf(tupla[49]));
                    registroIndividual.setAcciones(String.valueOf(tupla[50]));
                    registroIndividual.setDescripcion_acciones(String.valueOf(tupla[51]));
                    registroIndividual.setCodCausaProbable(String.valueOf(tupla[52]));
                    registroIndividual.setTerminoCausa(String.valueOf(tupla[53]));
                    registroIndividual.setDescripcionCausaProb(String.valueOf(tupla[54]));
                    reporteDatos.add(registroIndividual);
                }
            }
        } catch (Exception errorGeneracionMUID) {
            errorGeneracionMUID.printStackTrace();
            List<ReporteEfectoAdverso> vacio = new ArrayList<ReporteEfectoAdverso>();
            return (vacio);
        } finally {
            try {
            } catch (Exception errorSQL) {
                errorSQL.printStackTrace();
                List<ReporteEfectoAdverso> vacio = new ArrayList<ReporteEfectoAdverso>();
                return (vacio);
            }
        }

        return (reporteDatos);
    }

    @Override
    public List<ReporteEfectoAdverso> generarConsultaReportesEventoAdversoSecretaria(Date fechaInicial, Date fechaFinal, String depto) {
        List<ReporteEfectoAdverso> reporteDatos = null;
        ReporteEfectoAdverso registroIndividual = null;

        try {
            Query query1 = getManejadorEntidadesDireccion().createNamedQuery("reporteEventoAdversoSecretaria");
            fechaInicial.setHours(0);
            fechaInicial.setMinutes(0);
            fechaInicial.setSeconds(0);
            fechaFinal.setHours(23);
            fechaFinal.setMinutes(59);
            fechaFinal.setSeconds(59);
            query1.setParameter(1, depto);
            query1.setParameter(2, fechaInicial);
            query1.setParameter(3, fechaFinal);
            List<Object> cursor = query1.getResultList();
            if (cursor != null) {
                reporteDatos = new ArrayList<ReporteEfectoAdverso>();
                Iterator iteradorRegistros = cursor.iterator();

                while (iteradorRegistros.hasNext()) {
                    Object[] tupla = (Object[]) iteradorRegistros.next();
                    registroIndividual = new ReporteEfectoAdverso();
                    registroIndividual.setReporte(String.valueOf(tupla[0]));
                    registroIndividual.setDescrip_efecto(String.valueOf(tupla[1]));
                    registroIndividual.setFecha_ingreso(String.valueOf(tupla[2]));
                    registroIndividual.setFech_evento(String.valueOf(tupla[3]));
                    registroIndividual.setEfecto_indeseado(String.valueOf(tupla[4]));
                    registroIndividual.setFech_reporte(String.valueOf(tupla[5]));
                    registroIndividual.setCdg_problema(String.valueOf(tupla[6]));
                    registroIndividual.setClasificacion(String.valueOf(tupla[7]));
                    registroIndividual.setInstitucion(String.valueOf(tupla[8]));
                    registroIndividual.setIdentificacion(String.valueOf(tupla[9]));
                    registroIndividual.setDireccion(String.valueOf(tupla[10]));
                    registroIndividual.setNaturaleza(String.valueOf(tupla[11]));
                    registroIndividual.setComplejidad(String.valueOf(tupla[12]));
                    registroIndividual.setCod_depart(String.valueOf(tupla[13]));
                    registroIndividual.setCod_mun(String.valueOf(tupla[14]));
                    registroIndividual.setEmail(String.valueOf(tupla[15]));
                    registroIndividual.setTelefono(String.valueOf(tupla[16]));
                    registroIndividual.setNombre_paciente(String.valueOf(tupla[17]));
                    registroIndividual.setTipidentifi(String.valueOf(tupla[18]));
                    registroIndividual.setIdentifi(String.valueOf(tupla[19]));
                    registroIndividual.setEdad(String.valueOf(tupla[20]));
                    registroIndividual.setEdad_en(String.valueOf(tupla[21]));
                    registroIndividual.setGenero(String.valueOf(tupla[22]));
                    registroIndividual.setDirecc_paciente(String.valueOf(tupla[23]));
                    registroIndividual.setTelefo_paciente(String.valueOf(tupla[24]));
                    registroIndividual.setNombre_reactivo(String.valueOf(tupla[25]));
                    registroIndividual.setNroregsan(String.valueOf(tupla[26]));
                    registroIndividual.setFecha_venci(String.valueOf(tupla[27]));
                    registroIndividual.setLote(String.valueOf(tupla[28]));
                    registroIndividual.setProcedencia(String.valueOf(tupla[29]));
                    registroIndividual.setCadena_frio(String.valueOf(tupla[30]));
                    registroIndividual.setTemperatura(String.valueOf(tupla[31]));
                    registroIndividual.setCerti_analisis(String.valueOf(tupla[32]));
                    registroIndividual.setDistri_usuario(String.valueOf(tupla[33]));
                    registroIndividual.setCondici_almacen(String.valueOf(tupla[34]));
                    registroIndividual.setArea_funciona(String.valueOf(tupla[35]));
                    registroIndividual.setOtra_area(String.valueOf(tupla[36]));
                    registroIndividual.setCausa_efecto(String.valueOf(tupla[37]));
                    registroIndividual.setCausa_probable(String.valueOf(tupla[38]));

                    registroIndividual.setNotifico_importador(String.valueOf(tupla[39]));
                    registroIndividual.setNotifico_fabricante(String.valueOf(tupla[40]));
                    registroIndividual.setNotifico_comercializador(String.valueOf(tupla[41]));
                    registroIndividual.setNotifico_distribuidor(String.valueOf(tupla[42]));

                    registroIndividual.setFechaNotificacion(String.valueOf(tupla[43]));
                    registroIndividual.setEnviadoDistImportador(String.valueOf(tupla[44]));
                    registroIndividual.setGestionRiesgo(String.valueOf(tupla[45]));
                    registroIndividual.setAnalisisEfecto(String.valueOf(tupla[46]));
                    registroIndividual.setHerramientaAnalisis(String.valueOf(tupla[47]));

                    registroIndividual.setOtra_herramienta(String.valueOf(tupla[48]));
                    registroIndividual.setDescripcionCausa(String.valueOf(tupla[49]));
                    registroIndividual.setAcciones(String.valueOf(tupla[50]));
                    registroIndividual.setDescripcion_acciones(String.valueOf(tupla[51]));
                    registroIndividual.setCodCausaProbable(String.valueOf(tupla[52]));
                    registroIndividual.setTerminoCausa(String.valueOf(tupla[53]));
                    registroIndividual.setDescripcionCausaProb(String.valueOf(tupla[54]));
                    reporteDatos.add(registroIndividual);
                }
            }
        } catch (Exception errorGeneracionMUID) {
            errorGeneracionMUID.printStackTrace();
            List<ReporteEfectoAdverso> vacio = new ArrayList<ReporteEfectoAdverso>();
            return (vacio);
        } finally {
            try {
            } catch (Exception errorSQL) {
                errorSQL.printStackTrace();
                List<ReporteEfectoAdverso> vacio = new ArrayList<ReporteEfectoAdverso>();
                return (vacio);
            }
        }

        return (reporteDatos);
    }

    @Override
    public List<InscritoRedRV> generarReporteInscritosRedRV(Date fechaInicial, Date fechaFinal) {
        List<InscritoRedRV> reporteDatos = null;
        InscritoRedRV registroIndividual = null;

        try {
            Query query1 = getManejadorEntidadesDireccion().createNamedQuery("reporteInscritosRed");
            fechaInicial.setHours(0);
            fechaInicial.setMinutes(0);
            fechaInicial.setSeconds(0);
            fechaFinal.setHours(23);
            fechaFinal.setMinutes(59);
            fechaFinal.setSeconds(59);
            query1.setParameter(1, fechaInicial);
            query1.setParameter(2, fechaFinal);
            List<Object> cursor = query1.getResultList();
            if (cursor != null) {
                reporteDatos = new ArrayList<InscritoRedRV>();
                Iterator iteradorRegistros = cursor.iterator();

                while (iteradorRegistros.hasNext()) {
                    Object[] tupla = (Object[]) iteradorRegistros.next();
                    registroIndividual = new InscritoRedRV();
                    registroIndividual.setId(String.valueOf(tupla[0]));
                    registroIndividual.setFecha_solicitud(String.valueOf(tupla[1]));
                    registroIndividual.setNombre_institucion(String.valueOf(tupla[2]));
                    registroIndividual.setNaturaleza(String.valueOf(tupla[3]));
                    registroIndividual.setComplejidad(String.valueOf(tupla[4]));
                    registroIndividual.setDireccion_organizacion(String.valueOf(tupla[5]));
                    registroIndividual.setNit(String.valueOf(tupla[6]));
                    registroIndividual.setEmail_corporativo(String.valueOf(tupla[7]));
                    registroIndividual.setCdg_funcionario(String.valueOf(tupla[8]));
                    registroIndividual.setTelefono_organiz(String.valueOf(tupla[9]));
                    registroIndividual.setFax_organiz(String.valueOf(tupla[10]));
                    registroIndividual.setExt_organiz(String.valueOf(tupla[11]));
                    registroIndividual.setOtra_modalidad(String.valueOf(tupla[12]));
                    registroIndividual.setDescripcion(String.valueOf(tupla[13]));
                    registroIndividual.setDepartamento(String.valueOf(tupla[14]));
                    registroIndividual.setMunicipio(String.valueOf(tupla[15]));
                    registroIndividual.setPais(String.valueOf(tupla[16]));
                    registroIndividual.setFecha_solicitud2(String.valueOf(tupla[17]));
                    registroIndividual.setNombre_solic(String.valueOf(tupla[18]));
                    registroIndividual.setArea_empresa(String.valueOf(tupla[19]));
                    registroIndividual.setCargos(String.valueOf(tupla[20]));
                    registroIndividual.setDireccion_solicitante(String.valueOf(tupla[21]));
                    registroIndividual.setTelefono_solic(String.valueOf(tupla[22]));
                    registroIndividual.setEmail_personal(String.valueOf(tupla[23]));
                    registroIndividual.setPais_rep(String.valueOf(tupla[24]));
                    registroIndividual.setDepartamento_rep(String.valueOf(tupla[25]));
                    registroIndividual.setMunicipio_rep(String.valueOf(tupla[26]));
                    registroIndividual.setCelular_solic(String.valueOf(tupla[27]));
                    registroIndividual.setCdg_funcionario2(String.valueOf(tupla[28]));
                    registroIndividual.setCedula_solicitante(String.valueOf(tupla[29]));
                    registroIndividual.setId_red(String.valueOf(tupla[30]));
                    reporteDatos.add(registroIndividual);
                }
            }
        } catch (Exception errorGeneracionMUID) {
            errorGeneracionMUID.printStackTrace();
            List<InscritoRedRV> vacio = new ArrayList<InscritoRedRV>();
            return (vacio);
        } finally {
            try {
            } catch (Exception errorSQL) {
                errorSQL.printStackTrace();
                List<InscritoRedRV> vacio = new ArrayList<InscritoRedRV>();
                return (vacio);
            }
        }

        return (reporteDatos);
    }

    @Override
    public void actualizar_REPORTE_MASIVO_TRIMESTRAL_CON_EVENTO(ReporteTrimestralEvento x) {
        try {

            String update_tabla = "UPDATE [dbo].[reactivo_camposcomp]\n"
                    + " SET [estadoReporte] = '" + x.getEstadoReporte() + "'\n"
                    + "      ,[fechaGestionEnte] = '" + x.getFechaGestionEnte() + "'\n"
                    + "      ,[gestionEnte] = '" + x.getGestionEnte() + "'\n"
                    + "      ,[estadoGestionEnte] = '" + x.getEstadoGestionEnte() + "'\n"
                    + "      ,[fechaGestionInvima] = '" + x.getFechaGestionInvima() + "'\n"
                    + "      ,[observacionInvima] = '" + x.getObservacionInvima() + "'\n"
                    + " WHERE [reporte] = '" + x.getReporte() + "'";
            Query query1 = getManejadorEntidadesDireccion().createNativeQuery(update_tabla);
            query1.executeUpdate();
        } catch (Exception e) {
            System.out.println("ConsultasReactivoBean - actualizar_REPORTE_MASIVO_TRIMESTRAL_CON_EVENTO, catch:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
