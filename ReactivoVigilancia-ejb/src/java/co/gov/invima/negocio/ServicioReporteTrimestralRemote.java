/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.negocio;

import co.gov.invima.VO.ReportePreRDIVVO;
import co.gov.invima.entities.*;
import java.util.List;

/**
 *
 * @author jgutierrezme
 */
public interface ServicioReporteTrimestralRemote 
{
    //********************************************************
    //********************************************************
    //********************************************************
    public String obtenerCodigoDeptoPorNombre (String nombreDepto);
    public String obtenerNombreDeptoPorCodigo (String codigo);
    //********************************************************
    //********************************************************
    public String obtenerCodigoMunicipioPorNombre (String codigoDepto,String nombreMunicipio);
    public String obtenerNombreMunicipioPorCodigo (String codigo);
    //********************************************************
    //********************************************************
    //********************************************************
    public boolean crearRegistroReactivoEventosCol (ReactivoEventos registroEvento, String codigoColDefinitivo);
    public boolean crearRegistroReactivoPacienteCol (ReactivoPaciente registroPaciente, String codigoColDefinitivo);
    public boolean crearRegistroReactivoReportanteCol (ReactivoReportante registroReportante, String codigoColDefinitivo);
    public boolean crearRegistroReactivoGestionRealizadaCol (ReactivoGestionrealizada registroGestionRealizada, String codigoColDefinitivo);
    public boolean crearRegistroReactivoCamposComplementariosCol (ReactivoCamposcomp registroCamposComplementarios, String codigoColDefinitivo);
    public boolean crearRegistroReactivoInstitucionCol (ReactivoInstitucion registroInstitucion, String codigoColDefinitivo);
    public boolean crearRegistroReactivoProductoCol (ReactivoProducto registroProducto, String codigoColDefinitivo);
    //********************************************************
    //********************************************************
    public boolean crearRegistroReactivoEventosTemporal (ReactivoEventosTemp registroEventoTemporal, String codigoTemporalDefinitivo);
    public boolean crearRegistroReactivoPacienteTemporal (ReactivoPacienteTemp registroPacienteTemporal, String codigoTemporalDefinitivo);
    public boolean crearRegistroReactivoReportanteTemporal (ReactivoReportanteTemp registroReportanteTemporal, String codigoTemporalDefinitivo);
    public boolean crearRegistroReactivoGestionRealizadaTemporal (ReactivoGestionrealizadaTemp registroGestionRealizadaTemporal, String codigoTemporalDefinitivo);
    public boolean crearRegistroReactivoCamposComplementariosTemporal (ReactivoCamposcompTemp registroCamposComplementariosTemporal, String codigoTemporalDefinitivo);
    public boolean crearRegistroReactivoInstitucionTemporal (ReactivoInstitucionTemp registroInstitucion, String codigoTemporalDefinitivo);
    public boolean crearRegistroReactivoProductoTemporal (ReactivoProductoTemp registroProducto, String codigoTemporalDefinitivo);
    //********************************************************
    //********************************************************
    //********************************************************
    //Cambiar el estado del registro de precol de pendiente a aprobado (cambiándolo de 0 a 1.  0 = Pendiente, 1 = Aprobado)
    public boolean modificarRegistroReactivoEventosEstadoAprobacion (ReactivoEventosTemp registroEventos, String codigoColDefinitivo);
    //********************************************************
    //********************************************************
    //********************************************************
    public String obtenerPrecolSecuenciaCargue ();
    public String obtenerSiguienteColSecuenciaSistema();
    public String obtenerExpedientePorRegistroSanitario(String registroSanitario);
    //********************************************************
    //********************************************************
    public void limpiarTablaCargueMasivo (String nombreTabla);
    //********************************************************
    //********************************************************
    //Combos
    //public String obtenerCodigoEventoDeteccion (String nombreEvento);
    public String obtenerCodigoTipoDesenlace (String descripcion);
    public String obtenerCodigoTipoIdentificacionReportante (String nombreTipoDocumento);
    public String obtenerCodigoCargos (String descripcion);
    public String obtenerCodigoTipoDeReportante (String nombreTipoReportante);
    public String obtenerCodigoCausaProbable (String descripcion);
    public String obtenerCodigoTipoDispositivo (String nombreTipoDispositivo);
    public String obtenerCodigoDescripcionCausaEfecto (String nombre);
    //********************************************************
    //********************************************************
    //public String obtenerNombreEventoDeteccionPorCodigo (String codigoEvento);
    public String obtenerNombreTipoDesenlacePorCodigo (String codigo);
    public String obtenerNombreTipoIdentificacionReportantePorCodigo (String codigoTipoDocumento);
    public String obtenerNombreCargosPorCodigo (String codigo);
    public String obtenerNombreTipoDeReportantePorCodigo (String codigoTipoReportante);
    public String obtenerNombreCausaProbablePorCodigo (String codigo);
    public String obtenerNombreTipoDispositivo (String codigoTipoDispositivo);
    public String obtenerNombreDescripcionCausaEfecto (String codigo);
    //********************************************************
    //********************************************************
    //********************************************************
    //********************************************************
    //********************************************************
    public List<String> obtenerListadoDepartamentos();
    public List<String> obtenerListadoDepartamentos(String deptoActivo);
    public List<String> obtenerListadoMunicipios(String codigoDepto);
    public List<String> obtenerListadoMunicipiosControlDistrito(String codigoDepto, String idDistrito);
    public List<String> obtenerListadoMunicipiosSoloDistrito(String codigoDepto, String idDistrito);
    //********************************************************
    //********************************************************
    public List<String> obtenerListadoCargos();
    public List<String> obtenerListaCausasProbables();
    public List<String> obtenerListaCausasProbablesCodigos();
    public List<String> obtenerListaDesenlaces();
    //public List<String> obtenerListaDetecciones();
    public List<String> obtenerListaTipoDocReportante();
    public List<String> obtenerListaCargos();
    public List<String> obtenerListaTipoReportes();
    public List<String> obtenerListaTipoReportantes();
    public List<String> obtenerListaTipoDispositivos();
    public List<String> obtenerListaDescripcionesCausaEfecto();
    //********************************************************
    //********************************************************
    //********************************************************
    //********************************************************
    //Rol ETS aprobadora de precols
    public List<String> obtenerListadoUsuariosIPS();
    public List<String> obtenerListadoUsuariosIPS(String tipo);
    public List<String> obtenerListadoUsuariosIPS(String tipo, String depto);
    public List<String> obtenerListadoUsuariosIPS(String tipo, String depto, String idMunicipio, boolean esDistrito); 
    public List<ReportePreRDIVVO> buscarReportesMasivosTrimestralesPorIPS (String depto, String municipio, String idIPS);
    public List<ReportePreRDIVVO> buscarReportesMasivosTrimestralesPorIPS (String depto, String municipio, java.util.Date fechaIni, java.util.Date fechaFin);
    public List<ReportePreRDIVVO> buscarReportesMasivosTrimestralesPorIPS (String depto, String municipio, java.util.Date fechaIni, java.util.Date fechaFin, String idIps, String idRol, String municipioSecretaria);
    //********************************************************
    //********************************************************
    public ReactivoEventosTemp consultarRegistroReactivoEventosTemporal (String codigoPrecol);
    public List<ReactivoEventosTemp> consultarReactivoEventosTemporal ();
    public ReactivoPacienteTemp consultarRegistroReactivoPacienteTemporal (String codigoPrecol);
    public ReactivoReportanteTemp consultarRegistroReactivoReportanteTemporal (String codigoPrecol);
    public ReactivoGestionrealizadaTemp consultarRegistroReactivoGestionRealizadaTemporal (String codigoPrecol);
    public ReactivoCamposcompTemp consultarRegistroReactivoCamposComplementariosTemporal (String codigoPrecol);
    public ReactivoInstitucionTemp consultarRegistroReactivoInstitucionTemporal (String codigoPrecol);
    public ReactivoProductoTemp consultarRegistroReactivoProductoTemporal (String codigoPrecol);
    //********************************************************
    //********************************************************
    //********************************************************
    //********************************************************
    public ReactivoEventos consultarRegistroReactivoEventos (String codigoCol);
    public ReactivoPaciente consultarRegistroReactivoPaciente (String codigoCol);
    public ReactivoReportante consultarRegistroReactivoReportante (String codigoCol);
    public ReactivoGestionrealizada consultarRegistroReactivoGestionRealizada (String codigoCol);
    public ReactivoCamposcomp consultarRegistroReactivoCamposComplementarios (String codigoCol);
    public ReactivoInstitucion consultarRegistroReactivoInstitucion (String codigoCol);
    public ReactivoProducto consultarRegistroReactivoProducto (String codigoCol);
    //********************************************************
    //********************************************************
    public String obtenerCodigoRegistroSanitarioPorPrecol (String codigoPrecol);
    public String obtenerCodigoRegistroSanitarioPorCOL(String codigoCOL);
    //********************************************************
    //********************************************************
    //********************************************************
    public boolean eliminarRegistroReactivoEventosTemporal (ReactivoEventosTemp registroEventoTemporal, String codigoTemporalDefinitivo);
    public boolean eliminarRegistroReactivoPacienteTemporal (ReactivoPacienteTemp registroPacienteTemporal, String codigoTemporalDefinitivo);
    public boolean eliminarRegistroReactivoReportanteTemporal (ReactivoReportanteTemp registroReportanteTemporal, String codigoTemporalDefinitivo);
    public boolean eliminarRegistroReactivoGestionRealizadaTemporal (ReactivoGestionrealizadaTemp registroGestionRealizadaTemporal, String codigoTemporalDefinitivo);
    public boolean eliminarRegistroReactivoCamposComplementariosTemporal (ReactivoCamposcompTemp registroCamposComplementariosTemporal, String codigoTemporalDefinitivo);
    public boolean eliminarRegistroReactivoInstitucionTemporal (ReactivoInstitucionTemp registroInstitucionTemporal, String codigoTemporalDefinitivo);
    public boolean eliminarRegistroReactivoProductoTemporal (ReactivoProductoTemp registroProductoTemporal, String codigoTemporalDefinitivo);
    //********************************************************
    //********************************************************
    public String verificarExpedienteDispositivoPorRegistroSanitario (String codigoRegistroSanitario);
    //********************************************************
    //********************************************************
    public String obtenerIdRolUsuarioPorNIT(String nitEmpresa);
    public String obtenerCorreoEmpresaPorNIT (String nitEmpresa);
    public String obtenerNombreEmpresaIPSPorNit (String nitEmpresa);
    //********************************************************
    //********************************************************
    public String obtenerCodigoFuncionarioInvima (String nombreUsuario);
    //********************************************************
    //********************************************************
    public boolean modificarRegistroReactivoEventosCol (ReactivoEventos registroEvento, String codigoColDefinitivo);
    public boolean modificarRegistroReactivoPacienteCol (ReactivoPaciente registroPaciente, String codigoColDefinitivo);
    public boolean modificarRegistroReactivoReportanteCol (ReactivoReportante registroReportante, String codigoColDefinitivo);
    public boolean modificarRegistroReactivoGestionRealizadaCol (ReactivoGestionrealizada registroGestionRealizada, String codigoColDefinitivo);
    public boolean modificarRegistroReactivoCamposComplementariosCol (ReactivoCamposcomp registroCamposComplementarios, String codigoColDefinitivo);
    public boolean modificarRegistroReactivoInstitucionTemporal (ReactivoInstitucionTemp registroInstitucion, String codigoColDefinitivo);
    public boolean modificarRegistroReactivoProductoTemporal (ReactivoProductoTemp registroProducto, String codigoColDefinitivo);
    //********************************************************
    //********************************************************
    //********************************************************
    public String obtenerDescripcionCausaProbable (String codigoCausa);
    //********************************************************
    //********************************************************
}
