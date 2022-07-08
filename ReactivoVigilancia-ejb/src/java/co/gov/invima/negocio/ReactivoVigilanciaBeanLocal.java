/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.negocio;

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
import co.gov.invima.entities.Funcionario_sin;
import co.gov.invima.entities.Funcionarios;
import co.gov.invima.entities.Parametros;
import co.gov.invima.entities.ReactivoFriarh;
import co.gov.invima.entities.ReactivoMonitoreo;
import co.gov.invima.entities.ReactivoSeguimiento;
import co.gov.invima.entities.TecnoTipoalertas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoVigilanciaBeanLocal {

    public List<ReactivoModalidadesVO> obtenerCatalogoModalidades();

    public List<PaisesVO> obtenerCatalogoPaises();

    public List<DepartamentoVO> obtenerCatalogoDptos();
    
    public List<String> obtenerReportes();
    
    

    public List<ProfesionesVO> obtenerProfesiones();

    public String verificarUsuarioLocal(String usuario, String clave);

    public String guardarInscricpcionRed(RedVO redVO);

    public String guardarUsuarioInternet(UsuariosVO uvo);

    public Boolean existeUsuario(String usuario);

    public Boolean verificarUsuarioInternet(String usuario);

    public Boolean verificarDocumentoUsuarioInternet(long identificacionPersona);

    public UsuariosVO validarUsuarioFuncionario(String usuario, String clave);

    public List<MenusVO> obtenerMenuPorRol(Integer idRol);

    public List<UsuariosVO> buscarUsuarioPoNombre(String nombrePersona);

    public List<UsuariosVO> buscarUsuarioPorCedula(long numeroDcoumento);

    public List<UsuariosVO> buscarUsuarioPorEstado(Character estado);

    public String activacionUsuarioInternet(UsuariosVO usuariosVO);

    public UsuariosVO validarContrasenaInternet(String usuario, String clave);
    
    public String obtenerNombreMpioPorID(String cdgDpto, String cdgMpio) ;
    public String obtenerPaisPorID(String codPais);
    public String obtenerDptoPorID(String codDpto);

    public List<NotificacionVO> obtenerCatalogoNotificacion();

    public List<NotificanteVO> obtenerCatalogNotificante();

    public List<FuenteVO> obtenerCatalogoFuente();
    
    
    public List<TecnoTipoalertas> obtenerlstTecnoTipoalertas();
    
    public List<Funcionario_sin> obtenerlstFuncionarios();
    
    public List<String> obtenerListaReportesFRIARH() ;
    
    public List<String> obtenerLstReactivoMonitoreo() ;
    
    ReactivoFriarh obtenerListaReportesFRIARHporID(String FRIARH);
    
    List<ReactivoSeguimiento> obtenerSeguimientosFRIARH(String reporte, String categoria);
    
    
    ReactivoMonitoreo obtenerListaReactivoMonitoreoporID(String FRIARH);
    
    EfectosAdversosVO obtenerAdversosVO(String reporte);
    
    
    
    public Departamentos obtenerPorCdgTerritorio(String CdgTerritorio);
    
    public Ciudades obtenerPorCdgMunicipio(String CdgMunicipio);
    
    

    public List<EstadoVO> obtenerCatalogoEstados();

    public RespuestaVO guardarReporteSeguridad(FriarhVO friarhVO);
    
    public RespuestaVO guardarFriarhMonitoreo(ReactivoMonitoreo reactivoMonitoreo);
    
    public String obtener_parametro_consec_friarh();
    

    public List<AreasVO> obtenerCatalogoAreas();

    public List<ProblemasVO> obtenerCatalogoProblemas();

    public List<DesenlacesVO> obtenerCatalogDesenlaces();

    public RespuestaVO guardarEfectosAdversos(EfectosAdversosVO adversosVO);

    /**
     *
     * @param usuario
     * @return
     */
    public UsuariosVO buscarUsuarioPorNombreUSuario(String usuario);

    public UsuariosVO buscarUsuarioPorCedulaYNombre(long cedula, String nombre);
    
    public String recuperarContrasena(UsuariosVO usuariosVO);

    public java.util.ArrayList obtenerConsecutivos();
    
    public void actualizarParametros(Parametros param);
    
}
