/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.BO;

import co.gov.invima.VO.AreasVO;
import co.gov.invima.VO.DepartamentoVO;
import co.gov.invima.VO.DesenlacesVO;
import co.gov.invima.VO.EfectosAdversosVO;
import co.gov.invima.VO.EstadoVO;
import co.gov.invima.VO.FriarhVO;
import co.gov.invima.VO.FuenteVO;
import co.gov.invima.VO.MenusVO;
import co.gov.invima.VO.NotificacionVO;
import co.gov.invima.VO.NotificanteVO;
import co.gov.invima.VO.PaisesVO;
import co.gov.invima.VO.ProblemasVO;
import co.gov.invima.VO.ProfesionesVO;
import co.gov.invima.VO.ReactivoModalidadesVO;
import co.gov.invima.VO.RedVO;
import co.gov.invima.VO.RespuestaVO;
import co.gov.invima.VO.UsuariosVO;
import co.gov.invima.entities.Ciudades;
import co.gov.invima.entities.Departamentos;
import co.gov.invima.entities.Funcionarios;
import co.gov.invima.entities.Funcionario_sin;
import co.gov.invima.entities.ReactivoFriarh;
import co.gov.invima.entities.ReactivoFuente;
import co.gov.invima.entities.ReactivoMonitoreo;
import co.gov.invima.entities.ReactivoSeguimiento;
import co.gov.invima.entities.TecnoTipoalertas;
import co.gov.invima.negocio.ReactivoVigilanciaBeanLocal;
import co.gov.invima.service.ServiceLocator;
import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author mgualdrond
 */
public class ReactivoBO implements Serializable {
    
    private final ReactivoVigilanciaBeanLocal reactivoEJB;
    private final ServiceLocator sl;

    public ReactivoBO() {
        sl = new ServiceLocator();
        reactivoEJB = sl.getEJBReactivo();
    }
    
    /**
     * Obtiene las modalidades activas desde la base de datos de sivicos de las
     * tablas de reactivovigilancia
     * @return Listado de modalidades activas
     */
    public List<ReactivoModalidadesVO> obtnerModalidades(){
        return reactivoEJB.obtenerCatalogoModalidades();
    }
    
    public List<PaisesVO> obtenerPaises(){
        return reactivoEJB.obtenerCatalogoPaises();
    }
    
    public List<DepartamentoVO> obtenerDptos(){
        return reactivoEJB.obtenerCatalogoDptos();
    }
    
    public List<String> obtenerReportes(){
        return reactivoEJB.obtenerReportes();
    }
    
    
    
    /**
     * Obtiene las profesiones para asociarlas al solicitante
     * @return Listado de profesiones
     */
    public List<ProfesionesVO> obtenerProfesiones(){
        return reactivoEJB.obtenerProfesiones();
    }
    
    public String guradrInscripcionRed(RedVO redVO){
        return reactivoEJB.guardarInscricpcionRed(redVO);
    }
    
    public String guardarInscripcionPrograma(UsuariosVO usuariosVO){
        return reactivoEJB.guardarUsuarioInternet(usuariosVO);
    }
    
    public Boolean existeCedulaUsuarioInternet(long identificacionPersona){
        return reactivoEJB.verificarDocumentoUsuarioInternet(identificacionPersona);
    }
    
    public Boolean existeUsuarioInternet(String usuario){
        return reactivoEJB.verificarUsuarioInternet(usuario);
    }
    
    public UsuariosVO validarUsuarioFuncionario(String usuario,String clave){
        return reactivoEJB.validarUsuarioFuncionario(usuario, clave);
    }
    
    public List<MenusVO> obtenerMenus(Integer idRol){
        return reactivoEJB.obtenerMenuPorRol(idRol);
    }
    
    public List<UsuariosVO> obtenerUsuariosPorNombre(String nombre){
        return reactivoEJB.buscarUsuarioPoNombre(nombre);
    }
    
    public List<UsuariosVO> obtenerUsuariosPorCedula(long numeroDocumento){
        return reactivoEJB.buscarUsuarioPorCedula(numeroDocumento);
    }
    
    public List<UsuariosVO> obtenerUsuarioPorEstado(Character estado){
        return reactivoEJB.buscarUsuarioPorEstado(estado);
    }
    
    public String activarUsuario(UsuariosVO usuariosVO){
        return reactivoEJB.activacionUsuarioInternet(usuariosVO);
    }
    
    public UsuariosVO validarUsuarioInternet(String usuario,String clave){
        return reactivoEJB.validarContrasenaInternet(usuario, clave);
    }
    
    public String obtenerNombreMpioPorID(String cdgDpto, String cdgMpio) {
        return reactivoEJB.obtenerNombreMpioPorID(cdgDpto, cdgMpio) ;
    }
    
    public String obtenerPaisPorID(String codPais){
        return reactivoEJB.obtenerPaisPorID(codPais) ;
    }
    
    public String obtenerDptoPorID(String codDpto){
        return reactivoEJB.obtenerDptoPorID(codDpto);
    }
    
    
    
    public List<NotificacionVO> obtenerCatalogoNotificacion(){
        return reactivoEJB.obtenerCatalogoNotificacion();
    }
    
    public List<NotificanteVO> obtenerCatalogoNotificante(){
        return reactivoEJB.obtenerCatalogNotificante();
    }
    
    public List<FuenteVO> obtenerCatalogFuente(){
        return reactivoEJB.obtenerCatalogoFuente();
    }
    
    public List<TecnoTipoalertas> obtenerlstTecnoTipoalertas(){
        return reactivoEJB.obtenerlstTecnoTipoalertas();
    }
    
    public List<Funcionario_sin> obtenerlstFuncionarios(){
        return reactivoEJB.obtenerlstFuncionarios();
    }
    
    
    
    
    public List<String> obtenerReportesFRIARH(){
        return reactivoEJB.obtenerListaReportesFRIARH();
    }
    
    public List<String> obtenerLstReactivoMonitoreo(){
        return reactivoEJB.obtenerLstReactivoMonitoreo();
    }
    
    
    public ReactivoMonitoreo obtenerReactivoMonitoreo(String friarh){
        return reactivoEJB.obtenerListaReactivoMonitoreoporID(friarh);
    }
    
    public EfectosAdversosVO obtenerAdversosVO(String reporte){
        return reactivoEJB.obtenerAdversosVO(reporte);
    }
    
    
    
    
    public ReactivoFriarh obtenerReporteFRIARH(String friarh){
        return reactivoEJB.obtenerListaReportesFRIARHporID(friarh);
    }
    
    public List<ReactivoSeguimiento> obtenerSeguimientosFRIARH(String id_reporte, String categoria){
        return reactivoEJB.obtenerSeguimientosFRIARH(id_reporte, categoria);
    }
    
    
    public Departamentos obtenerPorCdgTerritorio(String CdgTerritorio){
        return reactivoEJB.obtenerPorCdgTerritorio(CdgTerritorio);
    }
    
    public Ciudades obtenerPorCdgMunicipio(String CdgMunicipio){
        return reactivoEJB.obtenerPorCdgMunicipio(CdgMunicipio);
    }
    public List<EstadoVO> obtenerCatalogoEstados(){
        return reactivoEJB.obtenerCatalogoEstados();
    }
    
  
    
    
    
    public RespuestaVO guardarFriarh(FriarhVO friarhVO){
        Logger xx = Logger.getLogger(ReactivoBO.class.getName());
        xx.info ("ReactivoBO - guardarFriarh");
        return reactivoEJB.guardarReporteSeguridad(friarhVO);
    }
    
    public RespuestaVO guardarFriarhMonitoreo(ReactivoMonitoreo reactivoMonitoreo){
        Logger xx = Logger.getLogger(ReactivoBO.class.getName());
        xx.info ("ReactivoBO - guardarFriarhMonitoreo");
        return reactivoEJB.guardarFriarhMonitoreo(reactivoMonitoreo);
    }
    
    public String obtener_parametro_consec_friarh(){
        Logger xx = Logger.getLogger(ReactivoBO.class.getName());
        xx.info ("ReactivoBO - obtener_parametro_consec_friarh");
        return reactivoEJB.obtener_parametro_consec_friarh();
    }
    
    
    
    
    
    
    
    public List<AreasVO> obtenerCatalogoAreas(){
        return reactivoEJB.obtenerCatalogoAreas();
    }
    
    public List<ProblemasVO> obtenerCatalogoProblemas(){
        return reactivoEJB.obtenerCatalogoProblemas();
    }
    
    public List<DesenlacesVO> obtenerCatalogoDesenlace(){
        return reactivoEJB.obtenerCatalogDesenlaces();
    }
    
    public RespuestaVO guardarEventos(EfectosAdversosVO adversosVO){
        return reactivoEJB.guardarEfectosAdversos(adversosVO);
    }
    
    public UsuariosVO busrcarUsuario(String usuario){
        return reactivoEJB.buscarUsuarioPorNombreUSuario(usuario);
    }
    
    public UsuariosVO buscarUsuarioCedulaYNombre (long cedula, String nombre)
    {
        return reactivoEJB.buscarUsuarioPorCedulaYNombre(cedula,nombre);
    }
    
    public String recuperarContrasena(UsuariosVO usuariosVO){
        return reactivoEJB.recuperarContrasena(usuariosVO);
    }
}
