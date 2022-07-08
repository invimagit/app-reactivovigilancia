/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.negocio;

import co.gov.invima.VO.AreasVO;
import co.gov.invima.VO.CiudadesVO;
import co.gov.invima.VO.DepartamentoVO;
import co.gov.invima.VO.DesenlacesVO;
import co.gov.invima.VO.EfectosAdversosVO;
import co.gov.invima.VO.EstadoVO;
import co.gov.invima.VO.Friarh01VO;
import co.gov.invima.VO.Friarh02VO;
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
import co.gov.invima.conexion.ConeccionSybaseJtds;
import co.gov.invima.entities.Ciudades;
import co.gov.invima.entities.CiudadesPK;
import co.gov.invima.entities.Departamentos;
import co.gov.invima.entities.Funcionario_sin;
import co.gov.invima.entities.Funcionarios;
import co.gov.invima.entities.Paises;
import co.gov.invima.entities.Parametros;
import co.gov.invima.entities.Perfil;
import co.gov.invima.entities.ReactivoArea;
import co.gov.invima.entities.ReactivoDatosExpedientes;
import co.gov.invima.entities.ReactivoDatosExpedientesPK;
import co.gov.invima.entities.ReactivoDesenlace;
import co.gov.invima.entities.ReactivoEstado;
import co.gov.invima.entities.ReactivoEventos;
import co.gov.invima.entities.ReactivoFriarh;
import co.gov.invima.entities.ReactivoFriarh01;
import co.gov.invima.entities.ReactivoFriarh02;
import co.gov.invima.entities.ReactivoFriarhPK;
import co.gov.invima.entities.ReactivoFuente;
import co.gov.invima.entities.ReactivoGestionrealizada;
import co.gov.invima.entities.ReactivoInstitucion;
import co.gov.invima.entities.ReactivoMenus;
import co.gov.invima.entities.ReactivoModalidad;
import co.gov.invima.entities.ReactivoMonitoreo;
import co.gov.invima.entities.ReactivoNotificacion;
import co.gov.invima.entities.ReactivoNotificante;
import co.gov.invima.entities.ReactivoPaciente;
import co.gov.invima.entities.ReactivoProblema;
import co.gov.invima.entities.ReactivoProducto;
import co.gov.invima.entities.ReactivoRed;
import co.gov.invima.entities.ReactivoRedPersona;
import co.gov.invima.entities.ReactivoReportante;
import co.gov.invima.entities.ReactivoRoles;
import co.gov.invima.entities.ReactivoSeguimiento;
import co.gov.invima.entities.ReactivoUsuariosInternet;
import co.gov.invima.entities.TecnoProfesion;
import co.gov.invima.entities.TecnoTipoalertas;
import co.gov.invima.excepcion.ISAPEntityException;
import co.gov.invima.reactivo.dto.reports.RegistroReactivo;
import co.gov.invima.sesion.CiudadesFacadeLocal;
import co.gov.invima.sesion.DepartamentosFacadeLocal;
import co.gov.invima.sesion.FuncionariosFacadeLocal;
import co.gov.invima.sesion.PaisesFacadeLocal;
import co.gov.invima.sesion.ParametrosFacadeLocal;
import co.gov.invima.sesion.PerfilFacadeLocal;
import co.gov.invima.sesion.ReactivoAreaFacadeLocal;
import co.gov.invima.sesion.ReactivoDatosExpedientesFacadeLocal;
import co.gov.invima.sesion.ReactivoDesenlaceFacadeLocal;
import co.gov.invima.sesion.ReactivoEstadoFacadeLocal;
import co.gov.invima.sesion.ReactivoEventosFacadeLocal;
import co.gov.invima.sesion.ReactivoFriarh01FacadeLocal;
import co.gov.invima.sesion.ReactivoFriarh02FacadeLocal;
import co.gov.invima.sesion.ReactivoFriarhFacadeLocal;
import co.gov.invima.sesion.ReactivoFuenteFacadeLocal;
import co.gov.invima.sesion.ReactivoGestionrealizadaFacadeLocal;
import co.gov.invima.sesion.ReactivoInstitucionFacadeLocal;
import co.gov.invima.sesion.ReactivoModalidadFacadeLocal;
import co.gov.invima.sesion.ReactivoMonitoreoFacade;
import co.gov.invima.sesion.ReactivoNotificacionFacadeLocal;
import co.gov.invima.sesion.ReactivoNotificanteFacadeLocal;
import co.gov.invima.sesion.ReactivoPacienteFacadeLocal;
import co.gov.invima.sesion.ReactivoProblemaFacadeLocal;
import co.gov.invima.sesion.ReactivoProductoFacadeLocal;
import co.gov.invima.sesion.ReactivoRedFacadeLocal;
import co.gov.invima.sesion.ReactivoRedPersonaFacadeLocal;
import co.gov.invima.sesion.ReactivoReportanteFacadeLocal;
import co.gov.invima.sesion.ReactivoRolesFacadeLocal;
import co.gov.invima.sesion.ReactivoSeguimientoFacade;
import co.gov.invima.sesion.ReactivoUsuariosInternetFacadeLocal;
import co.gov.invima.sesion.TecnoProfesionFacadeLocal;
import co.gov.invima.sesion.TecnoTipoalertasFacade;
import co.gov.invima.sesion.TecnoTipoalertasJpaController;
import co.gov.invima.utils.CorreoElectronico;
import co.gov.invima.utils.PropertiesReader;
import co.gov.invima.utils.util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import org.apache.log4j.Priority;

/**
 *
 * @author mgualdrond
 */
@Stateless
public class ReactivoVigilanciaBean implements ReactivoVigilanciaBeanLocal {

    private static final Logger logEJBReactivo = Logger.getLogger(ReactivoVigilanciaBean.class.getName());

    @EJB
    private ReactivoModalidadFacadeLocal reactivoModalidadDao;
    @EJB
    private PaisesFacadeLocal paisesDAO;
    @EJB
    private DepartamentosFacadeLocal departamentosDAO;
    @EJB
    private CiudadesFacadeLocal ciudadesDAO;
    @EJB
    private TecnoProfesionFacadeLocal tecnoProfesionDAO;
    @EJB
    private ReactivoRedFacadeLocal reactivoRedDAO;
    @EJB
    private ReactivoUsuariosInternetFacadeLocal reactivoUsuariosInternetDAO;
    @EJB
    private ReactivoRedPersonaFacadeLocal reactivoRedPersonaDAO;
    @EJB
    private FuncionariosFacadeLocal funcionariosDAO;
    @EJB
    private PerfilFacadeLocal perfilDAO;
    @EJB
    private ReactivoRolesFacadeLocal reactivoRolesDAO;
    @EJB
    private ReactivoNotificacionFacadeLocal reactivoNotificacionDAO;
    @EJB
    private ReactivoNotificanteFacadeLocal reactivoNotificanteDAO;
    @EJB
    private ReactivoMonitoreoFacade ReactivoMonitoreoFacadeDAO;
    
    @EJB
    private ReactivoEstadoFacadeLocal reactivoEstadoDAO;
    @EJB
    private ReactivoFuenteFacadeLocal reactivoFuenteDAO;
    
    @EJB
    private TecnoTipoalertasFacade tecnoTipoalertasFacade;
    @EJB
    private ReactivoFriarhFacadeLocal reactivoFriarhDAO;
    @EJB
    private ReactivoFriarh01FacadeLocal reactivoFriarh01DAO;
    @EJB
    private ReactivoFriarh02FacadeLocal reactivoFriarh02DAO;
    @EJB
    private ReactivoSeguimientoFacade reactivoSeguimientoFacade;
    @EJB
    private ParametrosFacadeLocal parametrosDAO;
    @EJB
    private ReactivoAreaFacadeLocal reactivoAreaDAO;
    @EJB
    private ReactivoProblemaFacadeLocal reactivoProblemaDAO;
    @EJB
    private ReactivoDesenlaceFacadeLocal reactivoDesenlaceDAO;
    @EJB
    private ReactivoInstitucionFacadeLocal reactivoInstitucionDAO;
    @EJB
    private ReactivoPacienteFacadeLocal reactivoPacienteDAO;
    @EJB
    private ReactivoProductoFacadeLocal reactivoProductoDAO;
    @EJB
    private ReactivoEventosFacadeLocal reactivoEventosDAO;
    @EJB
    private ReactivoGestionrealizadaFacadeLocal reactivoGestionrealizadaDAO;
    @EJB
    private ReactivoReportanteFacadeLocal reactivoReportanteDAO;
    @EJB
    private ReactivoRedFacadeLocal reactivoRedConsulta;
    @EJB
    private ReactivoDatosExpedientesFacadeLocal reactivoDatosExpedientesDAO;
    
    @EJB
    private ParametrosFacadeLocal parametros;
    
    
    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager manejadorEntidadesDireccion;
    

    /**
     * Retorna el Catalog de Modalidades
     *
     * @return Lsita de modalidades activas
     */
    @Override
    public List<ReactivoModalidadesVO> obtenerCatalogoModalidades() {
        List<ReactivoModalidadesVO> modalidadesVOs = null;
        try {
            List<ReactivoModalidad> modalidad = reactivoModalidadDao.obtenerModalidadActivos();
            if (modalidad != null && !modalidad.isEmpty()) {
                modalidadesVOs = new ArrayList<>();
                for (ReactivoModalidad reactivoModalidad : modalidad) {
                    ReactivoModalidadesVO rmvo = new ReactivoModalidadesVO();
                    rmvo.setCdgModalidad(reactivoModalidad.getCdgModalidad());
                    rmvo.setDescripcion(reactivoModalidad.getDescripcion());
                    rmvo.setEstado(reactivoModalidad.getEstado());
                    modalidadesVOs.add(rmvo);
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener catalogo  modalidad: ", e);
        } finally {
            return modalidadesVOs;
        }
    }

    /**
     * Obtiene el catalogo de paises para seleccionar en la capa de presentación
     *
     * @return Listado de paises
     */
    @Override
    public List<PaisesVO> obtenerCatalogoPaises() {
        logEJBReactivo.log(Priority.INFO, "obtenerCatalogoPaises() ");
        List<PaisesVO> paisesVOs = null;
        try {
            List<Paises> paiseses = paisesDAO.obtenerPaisesOrdenado();
            if (paiseses != null && !paiseses.isEmpty()) {
                paisesVOs = new ArrayList<>();
                for (Paises paises : paiseses) {
                    PaisesVO pvo = new PaisesVO();
                    pvo.setCdgPais(paises.getCdgPais());
                    pvo.setPais(paises.getPais());
                    pvo.setReferencia(paises.getReferencia());
                    pvo.setTipo(paises.getTipo());
                    paisesVOs.add(pvo);
                }
            }
            logEJBReactivo.log(Priority.INFO, "obtenerCatalogoPaises(),paisesVOs.size=  "+paisesVOs.size());
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener catalogo de paises", e);
        } finally {
            return paisesVOs;
        }
    }

    /**
     * Obtiene el catalogo de departamentos y ciudades en uno solo
     *
     * @return Listado de los departamentos con las ciudades
     */
    
    
    @Override
    public List<String> obtenerReportes() {
        List<String> retorno = new ArrayList<String>();
        try {
            retorno = reactivoEventosDAO.findAll_id();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "obtenerListaReportesFRIARH, Error al obtener Lista de reportes FRIARH: ", e);
        } finally {
            return retorno;
        }
    }
    
    
    
    @Override
    public List<DepartamentoVO> obtenerCatalogoDptos() {
        List<DepartamentoVO> departamentoVOs = null;
        try {
            List<Departamentos> departamentoses = departamentosDAO.obtenerDptoOrdenado();
            if (departamentoses != null && !departamentoses.isEmpty()) {
                departamentoVOs = new ArrayList<>();
                for (Departamentos departamentos : departamentoses) {
                    DepartamentoVO dvo = new DepartamentoVO();
                    dvo.setCdgTerritorio(departamentos.getCdgTerritorio());
                    dvo.setCodDepart(departamentos.getCodDepart());
                    dvo.setDescripcion(departamentos.getDescripcion());
                    dvo.setIso31662(departamentos.getIso31662());
                    List<Ciudades> ciudadeses = ciudadesDAO.obtenerCiudadPorDepartamento(departamentos.getCdgTerritorio());
                    if (ciudadeses != null && !ciudadeses.isEmpty()) {
                        List<CiudadesVO> ciudadesVOs = new ArrayList<>();
                        for (Ciudades ciudades : ciudadeses) {
                            CiudadesVO cvo = new CiudadesVO();
                            cvo.setCdgterritorio(ciudades.getCiudadesPK().getCdgterritorio());
                            cvo.setCiudad(ciudades.getCiudadesPK().getCiudad());
                            cvo.setCodMun(ciudades.getCodMun());
                            cvo.setGtt(ciudades.getGtt());
                            ciudadesVOs.add(cvo);
                        }
                        dvo.setCiudadesVOs(ciudadesVOs);
                    }
                    departamentoVOs.add(dvo);
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener Catalogo DPTO EJB", e);
        } finally {
            return departamentoVOs;
        }
    }

    /**
     * Obtiene las profesiones para ser asociadas al solicitante
     *
     * @return Listado de profesiones
     */
    @Override
    public List<ProfesionesVO> obtenerProfesiones() {
        List<ProfesionesVO> profesionesVOs = null;
        try {
            List<TecnoProfesion> profesions = tecnoProfesionDAO.findAll();
            if (profesions != null && !profesions.isEmpty()) {
                profesionesVOs = new ArrayList<>();
                for (TecnoProfesion tecnoProfesion : profesions) {
                    ProfesionesVO pvo = new ProfesionesVO();
                    pvo.setCdgProfesion(tecnoProfesion.getCdgProfesion());
                    pvo.setDescripcion(tecnoProfesion.getDescripcion());
                    profesionesVOs.add(pvo);
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener listado de profesiones", e);
        } finally {
            return profesionesVOs;
        }
    }

    /**
     * Verifica las credenciales de acceso de un susario d ela base de datos
     * para los efectos de ingreso a la solución pr parte de los funcionarios
     * para activar usuarios o rechazarlos.
     *
     * @param usuario: susuario de base de datos
     * @param clave: clave base de datos
     * @return OK: si son validas las crdenciales, ERROR: credenciales invalidas
     */
    @Override
    public String verificarUsuarioLocal(String usuario, String clave) {
        String resultado = "";
        try {
            Connection connection = ConeccionSybaseJtds.getConnection(usuario, clave);
            if (connection != null) {
                resultado = "OK";
                connection.close();
            } else {
                resultado = "ERROR";
            }
        } catch (SQLException e) {
            logEJBReactivo.log(Priority.ERROR, "Error al verificar usuario y contraseña", e);
        } finally {
            return resultado;
        }
    }

    /**
     * Alamacena los registros de información en la red de Reactivo Vigilancia
     *
     * @param redVO: Objeto con la información obtenida en pantalla
     * @return OK: cuando sea correcta y diferente cuando NO
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String guardarInscricpcionRed(RedVO redVO) {
        String respuesta = "";
        try {
            if (redVO != null) {
                //verifica si el usuario ya esta inscrito para esa entidad
                ReactivoRedPersona persona = reactivoRedPersonaDAO.obtenerPersonaPorDocumentoYEntidad(redVO.getCedulaSolicitante(),
                        redVO.getNit());
                
                if (persona == null) {
                    Integer idNuevo = reactivoRedDAO.obtenerIdRed();
                    ReactivoRed red = reactivoRedDAO.obtenerEntidadPorNit(redVO.getNit());
                    if (red == null) {
                        red = new ReactivoRed();
                        red.setCdgModalidad(redVO.getCdgModalidad());
                        red.setOtraModalidad(redVO.getOtraModalidad());
                        red.setCodDepart(redVO.getCodDepart());
                        red.setCodMun(redVO.getCodMun());
                        red.setComplejidad(redVO.getComplejidad());
                        red.setDireccionOrganizacion(redVO.getDireccionOrganizacion());
                        red.setEmailCorporativo(redVO.getEmailCorporativo());
                        red.setFaxOrganiz(redVO.getFaxOrganiz());
                        red.setFechaSolicitud(redVO.getFechaSolicitud());
                        //ReactivoRed rr = reactivoRedConsulta.obtenerIdRedObj();
                        red.setId((idNuevo == null ? 0 : idNuevo) + 1);
                        red.setNaturaleza(redVO.getNaturaleza());
                        red.setNit(redVO.getNit());
                        red.setNombreInstitucion(redVO.getNombreInstitucion());
                        red.setPaisOrganiz(redVO.getPaisOrganiz());
                        red.setTelefonoOrganiz(redVO.getTelefonoOrganiz());
                        red.setExtOrganiz(redVO.getExtOrganiz() != null || !redVO.getExtOrganiz().isEmpty() ? redVO.getExtOrganiz() : null);
                        reactivoRedDAO.create(red);
                    }
                    ReactivoRedPersona nuevoInscrito = new ReactivoRedPersona();
                    nuevoInscrito.setAreaEmpresa(redVO.getAreaEmpresa());
                    nuevoInscrito.setCargos(redVO.getCargos());
                    nuevoInscrito.setCedulaSolicitante(redVO.getCedulaSolicitante());
                    nuevoInscrito.setCelularSolic(redVO.getCelularSolic());
                    nuevoInscrito.setCodDepart(redVO.getCodDepart1());
                    nuevoInscrito.setCodMun(redVO.getCodMun1());
                    nuevoInscrito.setDireccionSolicitante(redVO.getDireccionSolicitante());
                    nuevoInscrito.setEmailPersonal(redVO.getEmailPersonal());
                    nuevoInscrito.setFechaSolicitud(redVO.getFechaSolicitud());
                    nuevoInscrito.setIdRed(red);
                    Integer idNuevoPersona = reactivoRedPersonaDAO.obtenerId();
                    nuevoInscrito.setId((idNuevoPersona == null ? 0 : idNuevoPersona) + 1);
                    nuevoInscrito.setNombreSolic(redVO.getNombreSolic());
                    nuevoInscrito.setPaisSolic(redVO.getPaisSolic());
                    nuevoInscrito.setProfesion(redVO.getProfesion());
                    nuevoInscrito.setTelefonoSolic(redVO.getTelefonoSolic());
                    reactivoRedPersonaDAO.create(nuevoInscrito);
                    //em.persist(nuevoInscrito);
                    respuesta = "OK-" + nuevoInscrito.getIdRed().getId().toString() + "-" + nuevoInscrito.getId().toString();
                    ReactivoModalidad modalidad = reactivoModalidadDao.find(red.getCdgModalidad());
                    String mensaje = "<html>Bogotá D.C., " + redVO.getFechaSolicitud() + "<br><br>"
                            + "Respetado(a) Doctor(a)<br><br>"
                            + "<p>En respuesta a su solicitud de inscripción a la Red Nacional de Reactivovigilancia, le informamos que fue realizada de forma satisfactoria y por lo cual se incluyó en la base de datos correspondiente con el consecutivo INVIMA No." + red.getId() + "-" + nuevoInscrito.getId() + ", mediante la modalidad de " + modalidad.getDescripcion() + ".</p><br>"
                            + "<p>Aprovechamos esta oportunidad para brindarle la bienvenida al Programa Nacional de Reactivovigilancia, diseñado e implementado con el fin de fortalecer la protección de la salud y la seguridad de los pacientes, usuarios y todas aquellas personas que se encuentren relacionada  directa o indirectamente con los reactivos de diagnóstico in vitro, según lo establecido en la Resolución 2020007532 del 28 de febrero del 2020, \"Por el cual se Implementa el Programa Nacional de Reactivovigilancia\".</p><br>"
                            + "<p>Recuerde estar atento (a) a su correo electrónico, debido a que la información de éste programa se envía principalmente por este medio. Así mismo lo invitamos a consultar la información publicada en la página web del INVIMA la cual puede consultar en la siguiente ruta:</p>"
                            + "<br><p>- Portal www.invima.gov.co<br>- Clic en el link Reactivovigilancia</p><br>"
                            + "<p>Esta inscripción ratifica su compromiso de participar en las actividades que se desarrollen en el marco de la Reactivovigilancia en Colombia.</p><br><br>"
                            + "Cordialmente,<br><br>"
                            + "<p><b>Grupo de Vigilancia Epidemiológica<br>"
                            + "Dirección de Dispositivos Médicos y Otras Tecnologías<br>"
                            + "INVIMA<br>Tel: (57-1) 2948700 Ext. 3607</b></p><br></html>";
                    boolean envioCorreo = this.envioCorreo("Registro Red Nacional Reactivovigilancia", mensaje, red.getEmailCorporativo(),
                            nuevoInscrito.getEmailPersonal(),"GuardarInscripcionRed");
                } else {
                    respuesta = "Ya se encuentra inscrito en la red para esta entidad";
                }
            } else {
                respuesta = "No  hay datos para almacenar";
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al guardar inscripción en red", e);
            respuesta = "ERROR";
            throw new ISAPEntityException("Error al guardar inscripcion en red");
        } finally {
            return respuesta;
        }
    }

    
    @Override
    public ArrayList obtenerConsecutivos() {
        ArrayList arreglo = (ArrayList) parametros.findAll();
        return arreglo;
    }
    
    @Override
    public void actualizarParametros(Parametros param) {
        parametros.edit(param);
    }
    
    
    /**
     * Guarda los registros de Internet para los reportes en el Programa
     * Nacional de reactivovigilancia
     *
     * @param uvo: Objeto con los datos de usuario para inscricion en la red
     * @return OK: operacion realizada con exito; ERROR: error al realizar el
     * registro
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String guardarUsuarioInternet(UsuariosVO uvo) {
        String resultado = "";
        try {
            if (uvo != null) {
                ReactivoUsuariosInternet internet = new ReactivoUsuariosInternet();
                internet.setActivo(uvo.getActivo());
                internet.setCargoPersona(uvo.getCargoPersona());
                internet.setCdgPais(uvo.getCdgPais());
                internet.setClasificacionUsuario(uvo.getClasificacionUsuario());
                internet.setCodDepart(uvo.getCodDepart());
                internet.setCodMun(uvo.getCodMun());
                internet.setDireccionEmpresa(uvo.getDireccionEmpresa());
                internet.setEmailEmpresa(uvo.getEmailEmpresa());
                internet.setEmailPersona(uvo.getEmailPersona());
                internet.setFax(uvo.getFax());
                internet.setFechaIngreso(uvo.getFechaIngreso());
                internet.setIdentificacionEmpresa(uvo.getIdentificacionEmpresa());
                internet.setIdentificacionPersona(uvo.getIdentificacionPersona());
                internet.setNombreEmpresa(uvo.getNombreEmpresa());
                internet.setNombrePersona(uvo.getNombrePersona());
                internet.setPassword(uvo.getPassword());
                internet.setPregunta(uvo.getPregunta());
                internet.setRespuesta(uvo.getRespuesta());
                internet.setTelefonoEmpresa(uvo.getTelefonoEmpresa());
                internet.setTelefonoPersona(uvo.getTelefonoPersona());
                internet.setTipidentificacionEmpresa(uvo.getTipidentificacionEmpresa());
                internet.setTipidentificacionPersona(uvo.getTipidentificacionPersona());
                internet.setUrl("");
                internet.setUsuario(uvo.getUsuario());
                reactivoUsuariosInternetDAO.create(internet);
                resultado = "OK";
            } else {
                resultado = "ERROR";
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al guardar registro usuario internet", e);
            resultado = "ERROR";
        } finally {
            return resultado;
        }
    }

    /**
     * Valida si el nombre de usuario existe
     *
     * @param usuario: nombre de ususario a validar
     * @return TRUE: si existe; false si no existe
     */
    @Override
    public Boolean existeUsuario(String usuario) {
        Boolean b = false;
        try {
            ReactivoUsuariosInternet internet = reactivoUsuariosInternetDAO.validarUsuarioExiste(usuario);
            if (internet != null) {
                b = true;
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al verificar nombre de usuario: ", e);
        } finally {
            return b;
        }
    }

    private Boolean envioCorreo(String asunto, String mensaje, String destino, String destino2,String operacion) {
        Boolean b = false;
        try {
            CorreoElectronico correoElectronico = new CorreoElectronico();
            b = correoElectronico.enviarCorreo(asunto, mensaje, destino, destino2,uvo,operacion);
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al enviar correo electrónico; ", e);
        } finally {
            return b;
        }
    }

    /**
     * Verifica si existe un nombre de usuario o no
     *
     * @param usuario: nombre de usuario a verificar
     * @return: true si existe, flase: si no existe
     */
    @Override
    public Boolean verificarUsuarioInternet(String usuario) {
        Boolean b = false;
        try {
            ReactivoUsuariosInternet rui = reactivoUsuariosInternetDAO.validarUsuarioExiste(usuario);
            if (rui != null) {
                b = true;
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al verificar Usuario Internet ");
        } finally {
            return b;
        }
    }

    /**
     * Verifica si esta o no un numero de documento de persona en Internet
     *
     * @param identificacionPersona: numeor de documento a verificar
     * @return true: si existe, false: si no existe
     */
    @Override
    public Boolean verificarDocumentoUsuarioInternet(long identificacionPersona) {
        Boolean b = false;
        try {
            ReactivoUsuariosInternet rui = reactivoUsuariosInternetDAO.validarCedulaExiste(identificacionPersona);
            if (rui != null) {
                b = true;
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al verficar cedula de usuario de internet: ", e);
        } finally {
            return b;
        }
    }

    /**
     * Valida si el acceso es por la red interna con el usuario asociado al rol
     * de reactivovigilancia
     *
     * @param usuario: nombre de usuario
     * @param clave: contraseña
     * @return el objeto
     */
    UsuariosVO uvo = null;
    @Override
    public UsuariosVO validarUsuarioFuncionario(String usuario, String clave) {
        uvo = null;
        
        try {
            PropertiesReader pr = new PropertiesReader("co.gov.invima.utils.conexiones");
            String usrEsquema = pr.getProperty("usrEsquema");
            String claveEsquema = pr.getProperty("claveEsquema");
            String conecta= pr.getProperty("urlBdUsuarios")+";instance="+pr.getProperty("urlInstanciaUsuarios");
            Boolean connection = reactivoUsuariosInternetDAO.esUsuarioDeBD(usuario, clave,conecta);
            if (connection) 
            //if (true) 
            {
                //**********************************************************************
                //**********************************************************************
                //Funcionarios f = funcionariosDAO.obtenerUsuarioPorLogin(usrEsquema);
                Funcionarios f = funcionariosDAO.obtenerUsuarioPorLogin(usuario);
                //**********************************************************************
                //**********************************************************************
                if (f != null) 
                {
                    //**********************************************************************
                    //**********************************************************************
                    //logEJBReactivo.info ("******************************************************");
                    //logEJBReactivo.info ("Se sesiono con usuario de la Entidad exitosamente: " + usuario);
                    //logEJBReactivo.info ("******************************************************");
                    //**********************************************************************
                    //**********************************************************************
                    String perfil = pr.getProperty("perfil");
                    //logEJBReactivo.info ("PERFIL PARA CONSULTAR = " + perfil);
                    Perfil p = perfilDAO.obtenerPErfilPorNombre(perfil.toUpperCase());
                    //************************************************************************
                    //************************************************************************
                    if (p != null) 
                    {
                        //valida los permisos
                        boolean sw = false;
                        for (Perfil p1 : f.getPerfilList()) {
                            if (p.getCdgPerfil().equals(p1.getCdgPerfil())) {
                                sw = true;
                                break;
                            }
                    }
                    //************************************************************************
                    //************************************************************************
                    if (sw) {
                            uvo = new UsuariosVO();
                            uvo.setNombrePersona(f.getNmbfuncionario());
                            uvo.setUsuario(usuario);
                            uvo.setRespuesta("OK");
                            uvo.setRolPalabra(perfil);
                            uvo.setIdRol("1");
                            uvo.setiDRolUsuario(1);
                        } else {
                            uvo = new UsuariosVO();
                            uvo.setRespuesta("Perfil ReactivoVigilancia no asociado al usuario");
                        }
                    } else {
                        uvo = new UsuariosVO();
                        uvo.setRespuesta("Perfil no habilitado para este usuario");
                    }
                } else {
                    uvo = new UsuariosVO();
                    uvo.setRespuesta("Usuario No encontrado, contacte con el administrador del sistema");
                }
            } else 
            {
                //logEJBReactivo.info ("No se pudo realizar la autenticacion");
                uvo = new UsuariosVO();
                uvo.setRespuesta("Usuario o contraseña errados");
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al validar funcionario: ", e);
        } finally {
            return uvo;
        }
    }
    /*
    @Override
    public UsuariosVO validarUsuarioFuncionario(String usuario, String clave) {
        UsuariosVO uvo = null;
        try {
            Connection connection = ConeccionSybaseJtds.getConnection(usuario, clave);
            if (connection != null) {
                Funcionarios f = funcionariosDAO.obtenerUsuarioPorLogin(usuario);
                if (f != null) {
                    PropertiesReader pr = new PropertiesReader("co.gov.invima.utils.conexiones");
                    String perfil = pr.getProperty("perfil");
                    Perfil p = perfilDAO.obtenerPErfilPorNombre(perfil.toUpperCase());
                    if (p != null) {
                        //valida los permisos
                        boolean sw = false;
                        for (Perfil p1 : f.getPerfilList()) {
                            if (p.getCdgPerfil().equals(p1.getCdgPerfil())) {
                                sw = true;
                                break;
                            }
                        }
                        if (sw) {
                            uvo = new UsuariosVO();
                            uvo.setNombrePersona(f.getNmbfuncionario());
                            uvo.setUsuario(usuario);
                            uvo.setRespuesta("OK");
                            uvo.setRolPalabra(perfil);
                        } else {
                            uvo = new UsuariosVO();
                            uvo.setRespuesta("Perfil ReactivoVigilancia no asociado al usuario");
                        }
                    } else {
                        uvo = new UsuariosVO();
                        uvo.setRespuesta("Perfil no habilitado para este usuario");
                    }
                } else {
                    uvo = new UsuariosVO();
                    uvo.setRespuesta("Usuario No encontrado, contacte con el administrador del sistema");
                }
            } else {
                uvo = new UsuariosVO();
                uvo.setRespuesta("Usuario o contraseña errados");
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al validar funcionario: ", e);
        } finally {
            return uvo;
        }
    }
    */

    /**
     * Obtiene la lista de opsiones de acuerdo al rol asociado al usuario
     * logueado
     *
     * @param idRol: 1: externo, 2: interno
     * @return listado de opciones de menu de acuerdo al rol
     */
    @Override
    public List<MenusVO> obtenerMenuPorRol(Integer idRol) {
        List<MenusVO> menusVO = null;
        try {
            ReactivoRoles roles = reactivoRolesDAO.obtenerMenuPorRol(idRol);
            if (roles != null) {
                if (roles.getReactivoMenusList() != null && !roles.getReactivoMenusList().isEmpty()) {
                    menusVO = new ArrayList<>();
                    for (ReactivoMenus rm : roles.getReactivoMenusList()) {
                        MenusVO mvo = new MenusVO();
                        mvo.setAccionMenu(rm.getAccionMenu());
                        mvo.setDescripcionMenu(rm.getDescripcionMenu());
                        mvo.setMostrar(rm.getMostrarMenu() == 'S' ? true : false);
                        mvo.setOpcionMenu(rm.getIdopcion());
                        mvo.setPermitidoMenu(rm.getPermitidoMenu() == 1 ? "S" : "N");
                        mvo.setUrlMenu(rm.getUrlMenu());
                        menusVO.add(mvo);
                        logEJBReactivo.log(Priority.INFO, "obtenerMenuPorRol , mvo.getUrlMenu() "+mvo.getUrlMenu());
                    }
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener la lista de opciones ", e);
        } finally {
            return menusVO;
        }
    }

    /**
     * Obtener listado de usuario por cedula
     *
     * @param numeroDcoumento: numero de documento a buscar
     * @return listado de usuarios
     */
    @Override
    public List<UsuariosVO> buscarUsuarioPorCedula(long numeroDcoumento) {
        List<UsuariosVO> usuariosVOs = null;
        try {
            List<ReactivoUsuariosInternet> internets = reactivoUsuariosInternetDAO.obtenerUsuariosPorCedula(numeroDcoumento);
            if (internets != null && !internets.isEmpty()) {
                usuariosVOs = new ArrayList<>();
                for (ReactivoUsuariosInternet rui : internets) {
                    uvo = new UsuariosVO();
                    uvo.setActivo(rui.getActivo());
                    uvo.setCargoPersona(rui.getCargoPersona());
                    uvo.setCdgPais(rui.getCdgPais());
                    uvo.setClasificacionUsuario(rui.getClasificacionUsuario());
                    Departamentos departamentos = departamentosDAO.find(rui.getCodDepart());
                    uvo.setCodDepart(departamentos.getDescripcion());
                    Ciudades ciudades = ciudadesDAO.obtenerCiudadPorDptoYCiudad(departamentos.getCdgTerritorio(), rui.getCodMun());
                    uvo.setCodMun(ciudades.getCiudadesPK().getCiudad());
                    uvo.setDireccionEmpresa(rui.getDireccionEmpresa());
                    uvo.setEmailEmpresa(rui.getEmailEmpresa());
                    uvo.setEmailPersona(rui.getEmailPersona());
                    uvo.setEstadoUsuario(rui.getEstadoUsuario());
                    uvo.setFax(rui.getFax());
                    uvo.setFechaIngreso(rui.getFechaIngreso());
                    uvo.setIdentificacionEmpresa(rui.getIdentificacionEmpresa());
                    uvo.setIdentificacionPersona(rui.getIdentificacionPersona());
                    uvo.setNombreEmpresa(rui.getNombreEmpresa());
                    uvo.setNombrePersona(rui.getNombrePersona());
                    uvo.setPassword(rui.getPassword());
                    uvo.setPregunta(rui.getPregunta());
                    uvo.setRespuesta(rui.getRespuesta());
                    uvo.setSession(rui.getSession());
                    uvo.setTelefonoEmpresa(rui.getTelefonoEmpresa());
                    uvo.setTelefonoPersona(rui.getTelefonoPersona());
                    uvo.setTipidentificacionEmpresa(rui.getTipidentificacionEmpresa());
                    uvo.setTipidentificacionPersona(rui.getTipidentificacionPersona());
                    uvo.setUrl(rui.getUrl());
                    uvo.setUsuario(rui.getUsuario());
                    uvo.setiDRolUsuario(rui.getIDRolUsuario());
                    uvo.setIdRol(String.valueOf(rui.getIDRolUsuario()));
                    usuariosVOs.add(uvo);
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener usuarios por cedula: ", e);
        } finally {
            return usuariosVOs;
        }
    }

    /**
     * Obtener listado de usuarios por nombre
     *
     * @param nombrePersona nombre de la persona a buscar
     * @return listado de usuarios
     */
    @Override
    public List<UsuariosVO> buscarUsuarioPoNombre(String nombrePersona) {
        List<UsuariosVO> usuariosVOs = null;
        try {
            List<ReactivoUsuariosInternet> internets = reactivoUsuariosInternetDAO.obtenerUsuariosPorNombre(nombrePersona);
            if (internets != null && !internets.isEmpty()) {
                usuariosVOs = new ArrayList<>();
                for (ReactivoUsuariosInternet rui : internets) {
                    uvo = new UsuariosVO();
                    uvo.setActivo(rui.getActivo());
                    uvo.setCargoPersona(rui.getCargoPersona());
                    uvo.setCdgPais(rui.getCdgPais());
                    uvo.setClasificacionUsuario(rui.getClasificacionUsuario());
                    Departamentos departamentos = departamentosDAO.find(rui.getCodDepart());
                    uvo.setCodDepart(departamentos.getDescripcion());
                    Ciudades ciudades = ciudadesDAO.obtenerCiudadPorDptoYCiudad(departamentos.getCdgTerritorio(), rui.getCodMun());
                    uvo.setCodMun(ciudades.getCiudadesPK().getCiudad());
                    uvo.setDireccionEmpresa(rui.getDireccionEmpresa());
                    uvo.setEmailEmpresa(rui.getEmailEmpresa());
                    uvo.setEmailPersona(rui.getEmailPersona());
                    uvo.setEstadoUsuario(rui.getEstadoUsuario());
                    uvo.setFax(rui.getFax());
                    uvo.setFechaIngreso(rui.getFechaIngreso());
                    uvo.setIdentificacionEmpresa(rui.getIdentificacionEmpresa());
                    uvo.setIdentificacionPersona(rui.getIdentificacionPersona());
                    uvo.setNombreEmpresa(rui.getNombreEmpresa());
                    uvo.setNombrePersona(rui.getNombrePersona());
                    uvo.setPassword(rui.getPassword());
                    uvo.setPregunta(rui.getPregunta());
                    uvo.setRespuesta(rui.getRespuesta());
                    uvo.setSession(rui.getSession());
                    uvo.setTelefonoEmpresa(rui.getTelefonoEmpresa());
                    uvo.setTelefonoPersona(rui.getTelefonoPersona());
                    uvo.setTipidentificacionEmpresa(rui.getTipidentificacionEmpresa());
                    uvo.setTipidentificacionPersona(rui.getTipidentificacionPersona());
                    uvo.setUrl(rui.getUrl());
                    uvo.setUsuario(rui.getUsuario());
                    uvo.setiDRolUsuario(rui.getIDRolUsuario());
                    uvo.setIdRol(Integer.toString(rui.getIDRolUsuario()));
                    usuariosVOs.add(uvo);
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener usuarios por documento: ", e);
        } finally {
            return usuariosVOs;
        }
    }

    /**
     * Obtiene el listado de usuarios por estado
     *
     * @param estado: estado del usuario
     * @return Listado de usuarios por estado
     */
    @Override
    public List<UsuariosVO> buscarUsuarioPorEstado(Character estado) {
        List<UsuariosVO> usuariosVOs = null;
        try {
            List<ReactivoUsuariosInternet> internets = reactivoUsuariosInternetDAO.obtenerUsuariosPorEstado(estado);
            if (internets != null && !internets.isEmpty()) {
                usuariosVOs = new ArrayList<>();
                for (ReactivoUsuariosInternet rui : internets) {
                    uvo = new UsuariosVO();
                    uvo.setActivo(rui.getActivo());
                    uvo.setCargoPersona(rui.getCargoPersona());
                    uvo.setCdgPais(rui.getCdgPais());
                    uvo.setClasificacionUsuario(rui.getClasificacionUsuario());
                    if (rui.getCodDepart() != null) {
                        Departamentos departamentos = departamentosDAO.find(rui.getCodDepart());
                        uvo.setCodDepart(departamentos.getDescripcion());
                        Ciudades ciudades = ciudadesDAO.obtenerCiudadPorDptoYCiudad(departamentos.getCdgTerritorio(), rui.getCodMun());
                        uvo.setCodMun(ciudades.getCiudadesPK().getCiudad());
                    } else {
                        uvo.setCodDepart(null);
                        uvo.setCodMun(null);
                    }
                    uvo.setDireccionEmpresa(rui.getDireccionEmpresa());
                    uvo.setEmailEmpresa(rui.getEmailEmpresa());
                    uvo.setEmailPersona(rui.getEmailPersona());
                    uvo.setEstadoUsuario(rui.getEstadoUsuario());
                    uvo.setFax(rui.getFax());
                    uvo.setFechaIngreso(rui.getFechaIngreso());
                    uvo.setIdentificacionEmpresa(rui.getIdentificacionEmpresa());
                    uvo.setIdentificacionPersona(rui.getIdentificacionPersona());
                    uvo.setNombreEmpresa(rui.getNombreEmpresa());
                    uvo.setNombrePersona(rui.getNombrePersona());
                    uvo.setPassword(rui.getPassword());
                    uvo.setPregunta(rui.getPregunta());
                    uvo.setRespuesta(rui.getRespuesta());
                    uvo.setSession(rui.getSession());
                    uvo.setTelefonoEmpresa(rui.getTelefonoEmpresa());
                    uvo.setTelefonoPersona(rui.getTelefonoPersona());
                    uvo.setTipidentificacionEmpresa(rui.getTipidentificacionEmpresa());
                    uvo.setTipidentificacionPersona(rui.getTipidentificacionPersona());
                    uvo.setUrl(rui.getUrl());
                    uvo.setUsuario(rui.getUsuario());
                    uvo.setiDRolUsuario(rui.getIDRolUsuario());
                    uvo.setIdRol(String.valueOf(rui.getIDRolUsuario()));
                    usuariosVOs.add(uvo);
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener usuarios por estado: ", e);
        } finally {
            return usuariosVOs;
        }
    }

    /**
     * Actualiza el estado y el rol para el usuario registrado
     *
     * @param usuariosVO: objeto con los datos del usuario
     * @return OK: si es correcto, diferente si errado
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String activacionUsuarioInternet(UsuariosVO usuariosVO) 
    {
        String resultado = "";
        try 
        {
            //*****************************************************************
            //*****************************************************************
            //*****************************************************************
            ReactivoUsuariosInternet internet = reactivoUsuariosInternetDAO.find(usuariosVO.getUsuario());
            //*****************************************************************
            //*****************************************************************
            if (internet != null) 
            {
                internet.setEstadoUsuario(usuariosVO.getEstadoUsuario());
                internet.setIDRolUsuario(usuariosVO.getiDRolUsuario());
                internet.setFax("NA");
                reactivoUsuariosInternetDAO.edit(internet);
                //**********************************************************************
                //**********************************************************************
                //**********************************************************************
                if (internet.getEstadoUsuario().equals('A')) 
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
                    Date fechaACtual = new Date();
                    String fechaCarta = sdf.format(fechaACtual);
                    sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String fechaSolicitud = sdf.format(usuariosVO.getFechaIngreso());
                    String rol = "";
                    //**********************************************************************
                    //**********************************************************************
                    //**********************************************************************
                    switch (usuariosVO.getiDRolUsuario()) 
                    {
                        case 1:
                            rol = "INVIMA";
                            break;
                        case 2:
                            rol = "Fabricante/Importador";
                            break;
                        case 3:
                            rol = "Prestador de Servicio de Salud";
                            break;
                        case 4:
                            rol = "Secretarias de Salud";
                            break;
                        case 5:
                            rol = "Bancos de Sangre y Componentes Anatómicos";
                            break;
                    }
                    //**********************************************************************
                    //**********************************************************************
                    //**********************************************************************
                    //logEJBReactivo.info ("ROL DEL USUARIO DE SESIÓN: " + rol + " PARA EL ID DE ROL = " + usuariosVO.getiDRolUsuario());
                    //**********************************************************************
                    //**********************************************************************
                    //**********************************************************************
                    String correo = "<html>Bogotá D.C., " + fechaCarta + "<br><br>"
                            + "Doctor(a)<br>" + usuariosVO.getNombrePersona().toUpperCase() + "<br>" + usuariosVO.getCargoPersona() + "<br><b>"
                            + usuariosVO.getNombreEmpresa().toUpperCase() + "</b><br>Email personal: " + usuariosVO.getEmailPersona() + "<br>"
                            + "Email corporativo: " + usuariosVO.getEmailEmpresa() + "<br><br>Asunto: Activación de Cuenta de Usuario<br>"
                            + "Respetado (a) Señor (a) " + usuariosVO.getNombrePersona() + ":<br>"
                            + "<p align=\"justify\">En respuesta a su solicitud de fecha " + fechaSolicitud + ", relacionado con la activación de su Cuenta de Usuario en calidad de " + rol + ", para el acceso al Aplicativo de Reporte en línea del Programa Nacional de Reactivovigilancia, le informo que una vez analizados y validados los datos de registro, su cuenta ha sido <b>ACTIVADA</b>.</p><br>"
                            + "<p align=\"justify\">Por lo anterior, lo invitamos a ingresar en el link <a href=\"https://www.invima.gov.co\">www.invima.gov.co</a>, con el nombre de Usuario <b>" + usuariosVO.getUsuario() + "</b> y la Contraseña que usted asignó en el momento del registro, (por motivos de seguridad se excluye del presente comunicado), con el objeto de verificar el correcto acceso al (los) formulario (s) de reporte.</p><br>"
                            + "<p align=\"justify\">Recuerde que los datos de usuario y clave aprobados, deben ser entregados al Profesional competente, designado por su Organización como el responsable del Programa Institucional de Reactivovigilancia, conforme a lo establecido en el numeral 1, articulo 11 de la Resolución 2020007532 del 28 de febrero del 2020 \"Por la cual se Implementa el Programa Nacional de Reactivovigilancia\" y quien deberá dar cumplimiento a los lineamientos mencionados en los artículos 12 y 13, de la citada Resolución.</p><br>"
                            + "<p align=\"justify\">Aprovechamos para darle la bienvenida y resaltar su compromiso en el desarrollo de la Reactivovigilancia  en Colombia, que permitirá el trabajo colectivo y articulado por parte de todos los actores del Programa en sus diferentes niveles de operación y la gestión oportuna y en tiempo real, de los problemas de seguridad relacionados con el uso de los reactivos de diagnóstico in vitro, para la toma decisiones en materia sanitaria, apuntando fundamentalmente a la protección de la salud pública.</p><br>"
                            + "<br><br>Cordialmente,<br><br><br><br><p><b>Grupo de Vigilancia Epidemiológica<br>"
                            + "Dirección de Dispositivos Médicos y Otras Tecnologías<br>"
                            + "INVIMA<br>"
                            + "Tel: (57-1) 2948700 Ext. 3607</b></p></html>";
                    //**********************************************************************
                    //**********************************************************************
                    //**********************************************************************
                    Boolean envioCorreo = this.envioCorreo("Activación de Cuenta de Usuario", correo, internet.getEmailEmpresa()
                            , internet.getEmailPersona(),"activacionUsuarioInternet");
                    //**********************************************************************
                    //**********************************************************************
                    //**********************************************************************
                }
                //***********************************************************
                //***********************************************************
                resultado = "OK";
                //***********************************************************
                //***********************************************************
            } 
            else 
            {
                //***********************************************************
                //***********************************************************
                resultado = "No se encuentra Usuario";
                //***********************************************************
                //***********************************************************
            }
        } 
        //*****************************************************************
        //*****************************************************************
        //*****************************************************************
        catch (Exception e) 
        {
            logEJBReactivo.log(Priority.ERROR, "Error al activar usuario: ", e);
            resultado = "ERROR";
        } 
        //*****************************************************************
        //*****************************************************************
        //*****************************************************************
        finally 
        {
            return resultado;
        }
        //*****************************************************************
        //*****************************************************************
        //*****************************************************************
    }

    /**
     * Valida usuario y contraseña de los usuarios registrados por Internet
     *
     * @param usuario: nombre d eusuario registrado
     * @param clave: contraseña registrada
     * @return Objeto UsuariosVO con la información básica del usuario.
     */
    @Override
    public UsuariosVO validarContrasenaInternet(String usuario, String clave) {
        uvo = null;
        try {
            ReactivoUsuariosInternet rui = reactivoUsuariosInternetDAO.find(usuario);
            if (rui != null) {
                if (rui.getPassword().equals(clave)) {
                    //logueado
                    if (rui.getEstadoUsuario().equals('A')) {
                        uvo = new UsuariosVO();
                        uvo.setActivo(rui.getActivo());
                        uvo.setCargoPersona(rui.getCargoPersona());
                        uvo.setCdgPais(rui.getCdgPais());
                        uvo.setClasificacionUsuario(rui.getClasificacionUsuario());
                        uvo.setCodDepart(rui.getCodDepart());
                        uvo.setCodMun(rui.getCodMun());
                        uvo.setDireccionEmpresa(rui.getDireccionEmpresa());
                        uvo.setEmailEmpresa(rui.getEmailEmpresa());
                        uvo.setEmailPersona(rui.getEmailPersona());
                        uvo.setEstadoPalabra("ACTIVO");
                        uvo.setEstadoUsuario(rui.getEstadoUsuario());
                        uvo.setFechaIngreso(rui.getFechaIngreso());
                        uvo.setIdRol(String.valueOf(rui.getIDRolUsuario()));
                        uvo.setIdentificacionEmpresa(rui.getIdentificacionEmpresa());
                        uvo.setIdentificacionPersona(rui.getIdentificacionPersona());
                        uvo.setNombreEmpresa(rui.getNombreEmpresa());
                        uvo.setNombrePersona(rui.getNombrePersona());
                        uvo.setPassword(rui.getPassword());
                        uvo.setPregunta(rui.getPregunta());
                        uvo.setRespuesta(rui.getRespuesta());
                        uvo.setRolPalabra("OK");
                        uvo.setTelefonoEmpresa(rui.getTelefonoEmpresa());
                        uvo.setTelefonoPersona(rui.getTelefonoPersona());
                        uvo.setTipidentificacionEmpresa(rui.getTipidentificacionEmpresa());
                        uvo.setTipidentificacionPersona(rui.getTipidentificacionPersona());
                        uvo.setUsuario(rui.getUsuario());
                        uvo.setiDRolUsuario(rui.getIDRolUsuario());
                        uvo.setNombrePais(this.obtenerPaisPorID(rui.getCdgPais()));
                        uvo.setNombreDpto(this.obtenerDptoPorID(rui.getCodDepart()));
                        uvo.setNombreMpio(this.obtenerNombreMpioPorID(rui.getCodDepart(), rui.getCodMun()));
                    } else {
                        uvo = new UsuariosVO();
                        uvo.setRespuesta("Usuario Inactivo");
                    }
                } else {
                    //contraseña errada
                    uvo = new UsuariosVO();
                    uvo.setRespuesta("Contraseña errada");
                }
            } else {
                //usuario no existe
                uvo = new UsuariosVO();
                uvo.setRespuesta("Usuario No encontrado");
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al vaslidar usuario internet", e);
        } finally {
            return uvo;
        }
    }


    
    
    /**
     * Obtiene el catalogo de notificación del Friarh
     *
     * @return Listado de notificación de Friarh
     */
    @Override
    public List<NotificacionVO> obtenerCatalogoNotificacion() {
        List<NotificacionVO> nvo = null;
        try {
            List<ReactivoNotificacion> notificacion = reactivoNotificacionDAO.findAll();
            if (notificacion != null && !notificacion.isEmpty()) {
                nvo = new ArrayList<>();
                for (ReactivoNotificacion rn : notificacion) {
                    NotificacionVO nvo1 = new NotificacionVO();
                    nvo1.setCdgNotificacion(rn.getCdgNotificacion());
                    nvo1.setDescripcion(rn.getDescripcion());
                    nvo.add(nvo1);
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener catalogo tipo notificación: ", e);
        } finally {
            return nvo;
        }
    }

    /**
     * Obtieene el catalogo de notificante del Friarh
     *
     * @return Listado de Opciones de notificante
     */
    @Override
    public List<NotificanteVO> obtenerCatalogNotificante() {
        List<NotificanteVO> nvos = null;
        try {
            List<ReactivoNotificante> notificantes = reactivoNotificanteDAO.findAll();
            if (notificantes != null && !notificantes.isEmpty()) {
                nvos = new ArrayList<>();
                for (ReactivoNotificante rn : notificantes) {
                    NotificanteVO nvo = new NotificanteVO();
                    nvo.setCdgNotificante(rn.getCdgNotificante());
                    nvo.setDescripcion(rn.getDescripcion());
                    nvos.add(nvo);
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener Cataslogo de Notificante: ", e);
        } finally {
            return nvos;
        }
    }

    /**
     * Obtiene el catalogo de fuentes pata Friarh
     *
     * @return Listado de fuentes para Friarh
     */
    @Override
    public List<FuenteVO> obtenerCatalogoFuente() {
        List<FuenteVO> fuenteVOs = null;
        try {
            List<ReactivoFuente> fuentes = reactivoFuenteDAO.findAll();
            if (fuentes != null && !fuentes.isEmpty()) {
                fuenteVOs = new ArrayList<>();
                for (ReactivoFuente fuente : fuentes) {
                    FuenteVO fvo = new FuenteVO();
                    fvo.setCdgFuente(fuente.getCdgFuente());
                    fvo.setDescripcion(fuente.getDescripcion());
                    fuenteVOs.add(fvo);
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener Catalogo de Fuentes: ", e);
        } finally {
            return fuenteVOs;
        }
    }
    @Override
    public List<TecnoTipoalertas> obtenerlstTecnoTipoalertas(){
        List<TecnoTipoalertas> fuentes = null;
        try {
            fuentes = tecnoTipoalertasFacade.findAll();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener Catalogo de Fuentes: ", e);
        } finally {
            return fuentes;
        }
    }
    @Override
    public List<Funcionario_sin> obtenerlstFuncionarios(){
        List<Funcionario_sin> fuentes = null;
        try {
            fuentes = funcionariosDAO.obtenerUsuarioActivos();
            //fuentes = funcionariosDAO.findAll();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener Catalogo de Fuentes: ", e);
        } finally {
            return fuentes;
        }
    }
    
    @Override
    public List<String> obtenerListaReportesFRIARH() {
        List<String> retorno = new ArrayList<String>();
        try {
            retorno = reactivoFriarhDAO.findAll_id();
            
            /*if (lista_de_ReportesFRIARH != null && !lista_de_ReportesFRIARH.isEmpty()) {
                for (ReactivoFriarhPK reporte : lista_de_ReportesFRIARH) {
                    retorno.add(reporte.getFriarh());
                }
            }*/
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "obtenerListaReportesFRIARH, Error al obtener Lista de reportes FRIARH: ", e);
        } finally {
            return retorno;
        }
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<String> obtenerLstReactivoMonitoreo() {
        List<String> retorno = new ArrayList<String>();
        try {
            retorno = ReactivoMonitoreoFacadeDAO.findAll_id();
            
            

        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "obtenerLstReactivoMonitoreo, Error al obtener Lista de reportes FRIARH: ", e);
            logEJBReactivo.info ("ReactivoVigilanciaBean, obtenerLstReactivoMonitoreo(), catch "+e.getMessage());
            logEJBReactivo.info ("ReactivoVigilanciaBean, obtenerLstReactivoMonitoreo(), catch "+e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            return retorno;
        }
    }
    
    @Override
    public EfectosAdversosVO obtenerAdversosVO(String reporte){
        Logger xx = Logger.getLogger(ReactivoVigilanciaBean.class.getName());
        xx.info ("obtenerAdversosVO, reporte = "+reporte);
        EfectosAdversosVO x = new EfectosAdversosVO();
        try {
            try{
                ReactivoInstitucion institucion = reactivoInstitucionDAO.find(reporte);
                x.setInstitucionVO(institucion);
            }catch(Exception e){xx.info ("ReactivoVigilanciaBean.obtenerAdversosVO, linea = setInstitucionVO, msg = "+e.getMessage());}
            
            try{
                ReactivoPaciente paciente = reactivoPacienteDAO.find(reporte);
                x.setPacienteVO(paciente);
            }catch(Exception e){xx.info ("ReactivoVigilanciaBean.obtenerAdversosVO, linea = setPacienteVO, msg = "+e.getMessage());}
            
            try{
                ReactivoProducto producto = reactivoProductoDAO.find(reporte);
                x.setProductoVO(producto);
            }catch(Exception e){xx.info ("ReactivoVigilanciaBean.obtenerAdversosVO, linea = setProductoVO, msg = "+e.getMessage());}
            
            try{
                ReactivoEventos eventos = reactivoEventosDAO.find(reporte);
                x.setEventosVO(eventos);
            }catch(Exception e){xx.info ("ReactivoVigilanciaBean.obtenerAdversosVO, linea = setEventosVO, msg = "+e.getMessage());}
            
            try{
                ReactivoGestionrealizada rg = reactivoGestionrealizadaDAO.find(reporte);
                x.setGestionRealizadaVO(rg);
            }catch(Exception e){xx.info ("ReactivoVigilanciaBean.obtenerAdversosVO, linea = setGestionRealizadaVO, msg = "+e.getMessage());}
            
            try{
                ReactivoReportante rr = reactivoReportanteDAO.find(reporte);
                x.setReportanteVO(rr);
            }catch(Exception e){xx.info ("ReactivoVigilanciaBean.obtenerAdversosVO, linea = setReportanteVO, msg = "+e.getMessage());}
            
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener obtenerAdversosVO()", e);
        } finally {
            return x;
        }
    }
    
    public ReactivoMonitoreo obtenerListaReactivoMonitoreoporID(String FRIARH){
        Logger xx = Logger.getLogger(ReactivoVigilanciaBean.class.getName());
        xx.info ("obtenerListaReactivoMonitoreoporID");
        ReactivoMonitoreo d = null;
        try {
            d = ReactivoMonitoreoFacadeDAO.obtenerReactivoMonitoreoporID(FRIARH);
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener obtenerListaReportesFRIARHporID()", e);
        } finally {
            return d;
        }
    }
    
    public ReactivoFriarh obtenerListaReportesFRIARHporID(String FRIARH){
        Logger xx = Logger.getLogger(ReactivoVigilanciaBean.class.getName());
        xx.info ("obtenerListaReportesFRIARHporID");
        ReactivoFriarh d = null;
        try {
            d = reactivoFriarhDAO.obtenerListaReportesFRIARHporID(FRIARH);
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error obtenerListaReportesFRIARHporID");
        } finally {
            return d;
        }
    }
    
    public List<ReactivoSeguimiento> obtenerSeguimientosFRIARH(String reporte, String categoria){
        Logger xx = Logger.getLogger(ReactivoVigilanciaBean.class.getName());
        xx.info ("obtenerSeguimientosFRIARH");
        List<ReactivoSeguimiento> d = null;
        try {
            d = reactivoSeguimientoFacade.buscar_seguimientos(categoria, reporte);
            xx.info ("obtenerSeguimientosFRIARH, d.size() = "+d.size());
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener obtenerSeguimientosFRIARH()", e);
        } finally {
            return d;
        }
    }
    
    
    
    public Departamentos obtenerPorCdgTerritorio(String CdgTerritorio){
        Logger xx = Logger.getLogger(ReactivoVigilanciaBean.class.getName());
        xx.info ("obtenerPorCdgTerritorio");
        Departamentos d = null;
        try {
            d = departamentosDAO.obtenerPorCdgTerritorio(CdgTerritorio);
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener obtenerListaReportesFRIARHporID()", e);
        } finally {
            return d;
        }
    }
    
    public Ciudades obtenerPorCdgMunicipio(String CdgMunicipio){
        Logger xx = Logger.getLogger(ReactivoVigilanciaBean.class.getName());
        xx.info ("obtenerPorCdgMunicipio");
        Ciudades d = null;
        try {
            d = ciudadesDAO.obtenerCiudadPorCodMun(CdgMunicipio);
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener obtenerListaReportesFRIARHporID()", e);
        } finally {
            return d;
        }
    }

    /**
     * Obtiene el Catalogo de estados para el friarh
     *
     * @return Lsitado de Estado para Friarh
     */
    @Override
    public List<EstadoVO> obtenerCatalogoEstados() {
        List<EstadoVO> estadoVOs = null;
        try {
            List<ReactivoEstado> estados = reactivoEstadoDAO.findAll();
            if (estados != null && !estados.isEmpty()) {
                estadoVOs = new ArrayList<>();
                for (ReactivoEstado estado : estados) {
                    EstadoVO evo = new EstadoVO();
                    evo.setCdgEstado(estado.getCdgEstado());
                    evo.setDescripcion(estado.getDescripcion());
                    estadoVOs.add(evo);
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener Catalog de Estados Friarh: ", e);
        } finally {
            return estadoVOs;
        }
    }

    public String obtenerPaisPorID(String codPais) {
        String pvo = "";
        try {
            if(!codPais.isEmpty() && !codPais.equals(" ")){    
                Paises paises = paisesDAO.find(codPais);
                if (paises != null) {
                    pvo = paises.getPais();
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.INFO, "Error al obtener nombre de PAIS, ReactivoVigilanciaBean.obtenerPaisPorID(), message = "+e.getMessage());
        } finally {
            return pvo;
        }
    }

    public String obtenerDptoPorID(String codDpto) {
        String dpto = "";
        try {
            if(!dpto.isEmpty() && !dpto.equals(" ")){
                Departamentos d = departamentosDAO.find(codDpto);
                if (d != null) {
                    dpto = d.getDescripcion();
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.INFO, "Error al obtener nombre de departamento, ReactivoVigilanciaBean.obtenerDptoPorID(), message = "+e.getMessage());
        } finally {
            return dpto;
        }
    }

    public String obtenerNombreMpioPorID(String cdgDpto, String cdgMpio) {
        String mpio = "";
        try {
            if(!mpio.isEmpty() && !mpio.equals(" ")){
                Departamentos d = departamentosDAO.find(cdgDpto);
                Ciudades c = ciudadesDAO.obtenerCiudadPorDptoYCiudad(d.getCdgTerritorio(), cdgMpio);
                if (c != null) {
                    mpio = c.getCiudadesPK().getCiudad();
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.INFO, "Error al obtener nombre de Municipio, ReactivoVigilanciaBean.obtenerNombreMpioPorID(), message = "+e.getMessage());
        } finally {
            return mpio;
        }
    }

    /**
     * Guarda el reporte de seguridad del Friarh
     *
     * @param reactivoMonitoreo
     * @param friarhVO: Objeto que contiene el reporte
     * @return Objeto de resultado
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RespuestaVO guardarFriarhMonitoreo(ReactivoMonitoreo reactivoMonitoreo){
        Logger xx = Logger.getLogger(ReactivoVigilanciaBean.class.getName());
        RespuestaVO respuestaVO = new RespuestaVO();
        try
        {
            xx.info ("ReactivoVigilanciaBean, guardarFriarhMonitoreo(), reactivoMonitoreo.getFriarh()="+reactivoMonitoreo.getFriarh());
            if(reactivoMonitoreo.registro_nuevo)
            {
                ReactivoMonitoreoFacadeDAO.create(reactivoMonitoreo);
                respuestaVO.setCode(1);
                respuestaVO.setStatus("OK");
                respuestaVO.setMensaje("Reporte guardado exitosamente");
                respuestaVO.setMensajeDetallado(reactivoMonitoreo.getFriarh());
                return respuestaVO;
            }
            else
            {
                ReactivoMonitoreoFacadeDAO.actualizarReactivoMonitoreoporID(reactivoMonitoreo);
                respuestaVO.setCode(1);
                respuestaVO.setStatus("OK");
                respuestaVO.setMensaje("Reporte actualizado exitosamente");
                respuestaVO.setMensajeDetallado(reactivoMonitoreo.getFriarh());
                return respuestaVO;
            }
            
            
            
        }
        catch(Exception e){
            xx.info ("ReactivoVigilanciaBean, guardarFriarhMonitoreo(), catch "+e.getMessage());
            xx.info ("ReactivoVigilanciaBean, guardarFriarhMonitoreo(), catch "+e.getLocalizedMessage());
            e.printStackTrace();
        }
        return respuestaVO;
        
    }
    @Override
    public String obtener_parametro_consec_friarh(){

        Parametros p = parametrosDAO.find(1);
        String consecutivo_siguiente = util.generarConsecutivoSiguienteGenerico(p.getConsecFriarh()+"", "-");
        int consecutivo_siguiente_sin_formato = Integer.parseInt(consecutivo_siguiente.replaceAll("-", ""));
        p.setConsecFriarh(consecutivo_siguiente_sin_formato);
        parametrosDAO.edit(p);
        return consecutivo_siguiente;
    }
    
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RespuestaVO guardarReporteSeguridad(FriarhVO friarhVO) {
        Logger xx = Logger.getLogger(ReactivoVigilanciaBean.class.getName());
        RespuestaVO respuestaVO = null;
        String correo = null;
        ReactivoUsuariosInternet internet = null;
        try {
            if (friarhVO != null) {
                ReactivoFriarh friarh = new ReactivoFriarh();
                try{friarh.setAutorizacion(friarhVO.getAutorizacion() != null ? friarhVO.getAutorizacion().toUpperCase() : null);}catch(Exception e){}
                try{friarh.setCausas(friarhVO.getCausas() != null ? friarhVO.getCausas().toUpperCase() : null);}catch(Exception e){}
                friarh.setCdgNotificante(friarhVO.getCdgNotificante());
                friarh.setCdgProfesion(friarhVO.getCdgProfesion());
                try{friarh.setCdgRiesgo(friarhVO.getCdgRiesgo().toUpperCase());}catch(Exception e){}
                friarh.setFechaIngreso(new Date());
                friarh.setCodDepart(friarhVO.getCodDepart());
                friarh.setCodDepart1(friarhVO.getCodDepart1());
                friarh.setCodMun(friarhVO.getCodMun());
                friarh.setCodMun1(friarhVO.getCodMun1());
                friarh.setCuantosEventos(friarhVO.getCuantosEventos());
                try{friarh.setDescripcionEvento(friarhVO.getDescripcionEvento() != null ? friarhVO.getDescripcionEvento().toUpperCase() : null);}catch(Exception e){}
                try{friarh.setDescripcionHurto(friarhVO.getDescripcionHurto() != null ? friarhVO.getDescripcionHurto().toUpperCase() : null);}catch(Exception e){}
                try{friarh.setDireccion(friarhVO.getDireccion().toUpperCase());}catch(Exception e){}
                friarh.setEmail(friarhVO.getEmail());
                friarh.setEstadoReac(friarhVO.getEstadoReac());
                friarh.setEstadoReporte(friarhVO.getEstadoReporte());
                friarh.setExpedienteReac(friarhVO.getExpedienteReac());
                friarh.setFechaHurto(friarhVO.getFechaHurto());
                friarh.setFechaIngreso(friarhVO.getFechaIngreso());
                friarh.setFechaRadicado(friarhVO.getFechaRadicado());
                try{friarh.setFiscalia(friarhVO.getFiscalia() != null ? friarhVO.getFiscalia().toUpperCase() : null);}catch(Exception e){}
                friarh.setFuente(friarhVO.getFuente());
                friarh.setInternet('S');
                try{friarh.setLote(friarhVO.getLote().toUpperCase());}catch(Exception e){}
                try{friarh.setMarca(friarhVO.getMarca().toUpperCase());}catch(Exception e){}
                friarh.setMedidasCorrectivas(friarhVO.getMedidasCorrectivas() != null ? friarhVO.getMedidasCorrectivas().toUpperCase() : null);
                friarh.setModelo(friarhVO.getModelo() != null ? friarhVO.getModelo().toUpperCase() : null);
                friarh.setNit(friarhVO.getNit());
                try{friarh.setNombreAgencia(friarhVO.getNombreAgencia() != null ? friarhVO.getNombreAgencia().toUpperCase() : null);}catch(Exception e){}
                try{friarh.setNombreNotificante(friarhVO.getNombreNotificante().toUpperCase());}catch(Exception e){}
                try{friarh.setNombreReac(friarhVO.getNombreReac().toUpperCase());}catch(Exception e){}
                try{friarh.setNroregsan(friarhVO.getNroregsan().toUpperCase());}catch(Exception e){}
                try{friarh.setOtroEstadoreac(friarhVO.getOtroEstadoreac() != null ? friarhVO.getOtroEstadoreac().toUpperCase() : null);}catch(Exception e){}
                try{friarh.setOtroNotificante(friarhVO.getOtroNotificante() != null ? friarhVO.getOtroNotificante().toUpperCase() : null);}catch(Exception e){}
                friarh.setPais(friarhVO.getPais());
                friarh.setProblemaReac(friarhVO.getProblemaReac());
                friarh.setRadicado(friarhVO.getRadicado());
                try{friarh.setRazonSocialNotificante(friarhVO.getRazonSocialNotificante().toUpperCase());}catch(Exception e){}

                if (friarhVO.getReporteEventos() != null){
                    friarh.setReporteEventos(friarhVO.getReporteEventos().toUpperCase());
                }else{
                    friarh.setReporteEventos(null);
                }

                friarh.setTelefono(friarhVO.getTelefono());
                ReactivoFriarhPK friarhPK;
                
                if(friarhVO.getReactivoFriarhPK()!=null){
                    //xx.info ("ReactivoVigilanciaBean, guardarReporteSeguridad() - ACTUALIZANDO REGISTRO");
                    friarhPK = friarhVO.getReactivoFriarhPK();
                    friarh.setReactivoFriarhPK(friarhPK);
                    reactivoFriarhDAO.edit(friarh);
                    respuestaVO = new RespuestaVO();
                    respuestaVO.setCode(1);
                    respuestaVO.setStatus("OK");
                    respuestaVO.setMensaje("Reporte actualizado exitosamente");
                    respuestaVO.setMensajeDetallado(friarhPK.getFriarh());
                }
                else
                {
                    //xx.info ("ReactivoVigilanciaBean, guardarReporteSeguridad() - CREANDO REGISTRO");
                    friarhPK = new ReactivoFriarhPK();
                    friarhPK.setCdgTipoalerta(friarhVO.getCdgTipoalerta().toUpperCase());
                    Date fechaActual = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
                    String fechaTexto = sdf.format(fechaActual);
                        
                    if(friarhVO.isReporte_con_friah_pero_sin_crear()){
                       friarhPK.setFriarh(friarhVO.getFriarh());
                    }else{
                        String ll_consec = obtener_parametro_consec_friarh();
                        String ls_friarh = "RD" + friarhVO.getCdgTipoalerta() + ll_consec;
                        friarhPK.setFriarh(ls_friarh);
                    }
                    
                    friarh.setReactivoFriarhPK(friarhPK);
                    friarh.setReactivoFriarh01List(null);
                    friarh.setReactivoFriarh02List(null);
                    reactivoFriarhDAO.create(friarh);
                    
                    internet = reactivoUsuariosInternetDAO.find(friarhVO.getUsuario());
                    sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
                    String fechaHora = sdf.format(friarhVO.getFechaIngreso());
                    correo = "<html><p align=\"justify\">Su reporte ha sido ingresado al Sistema de Información del Programa Nacional de Reactivovigilancia, a continuación se presenta un resumen del trámite efectuado:</p><br><br>"
                            + "<p align=\"center\"><b>Fecha y hora del ingreso:</b> " + fechaHora + "<br>"
                            + "<b>Código asignado:</b> " + friarh.getReactivoFriarhPK().getFriarh()
                            + "<br><b>Reactivo de Diagnóstico In Vitro: </b>" + friarhVO.getNombreReac().toUpperCase();
                    
                    //resultado positivo
                    respuestaVO = new RespuestaVO();
                    respuestaVO.setCode(1);
                    respuestaVO.setStatus("OK");
                    respuestaVO.setMensaje("Reporte almacenado exitosamente");
                    respuestaVO.setMensajeDetallado(friarhPK.getFriarh());
                }
                
                //xx.info ("ReactivoVigilanciaBean, guardarReporteSeguridad() - friarhVO.getTrazabilidad()");
                if (friarhVO.getTrazabilidad() != null && !friarhVO.getTrazabilidad().isEmpty()) {
                    //Guarda formato Friarh 1
                        for (Friarh01VO fvo : friarhVO.getTrazabilidad()) {
                            ReactivoFriarh01 friarh01 = new ReactivoFriarh01();
                            friarh01.setCantidad(fvo.getCantidad());
                            xx.info ("ReactivoVigilanciaBean, guardarReporteSeguridad() - friarhVO.getTrazabilidad() -1");
                            Departamentos d = departamentosDAO.obtenerPorNombre(fvo.getDepartamento());
                            CiudadesPK cpk = new CiudadesPK();
                            cpk.setCdgterritorio(d.getCdgTerritorio());
                            cpk.setCiudad(fvo.getMunicipio());
                            Ciudades c = ciudadesDAO.find(cpk);
                            try{
                            friarh01.setCdgMunicipio(Integer.parseInt(c.getCodMun()));
                            }
                            catch(Exception e){
                                friarh01.setCdgMunicipio(1);
                            }
                            xx.info ("ReactivoVigilanciaBean, guardarReporteSeguridad() - friarhVO.getTrazabilidad() -2");
                            friarh01.setDireccion(fvo.getDireccion().toUpperCase());
                            friarh01.setLote(fvo.getLote().toUpperCase());
                            friarh01.setMarca(fvo.getMarca().toUpperCase());
                            friarh01.setNombreCliente(fvo.getNombreCliente().toUpperCase());
                            friarh01.setNombreContacto(fvo.getNombreContacto().toUpperCase());
                            friarh01.setNombreReactivo(fvo.getNombreReactivo().toUpperCase());
                            friarh01.setReactivoFriarh(friarh);
                            friarh01.setReferencia(fvo.getReferencia().toUpperCase());
                            xx.info ("ReactivoVigilanciaBean, guardarReporteSeguridad() - friarhVO.getTrazabilidad() -3");
                            friarh01.setRegistroSanitario(fvo.getRegistroSanitario().toUpperCase());
                            friarh01.setTelefono(fvo.getTelefono());
                            if(fvo.getId()!=null) {
                                friarh01.setId(fvo.getId());
                                //xx.info ("ReactivoVigilanciaBean, guardarReporteSeguridad() - friarhVO.getTrazabilidad() - reactivoFriarh01DAO.edit(friarh01);");
                                reactivoFriarh01DAO.edit(friarh01);
                            } else{
                                //xx.info ("ReactivoVigilanciaBean, guardarReporteSeguridad() - friarhVO.getTrazabilidad() - reactivoFriarh01DAO.create(friarh01);");
                                reactivoFriarh01DAO.create(friarh01);
                            }
                        }
                    }
                    
                    if (friarhVO.getHurtados() != null && !friarhVO.getHurtados().isEmpty()) {
                    //Guardar hurtados
                        for (Friarh02VO fvo : friarhVO.getHurtados()) {
                            ReactivoFriarh02 friarh02 = new ReactivoFriarh02();
                            friarh02.setCantidad(fvo.getCantidad());
                            friarh02.setLote(fvo.getLote().toUpperCase());
                            friarh02.setMarca(fvo.getMarca().toUpperCase());
                            friarh02.setNombreReactivo(fvo.getNombreReactivo().toUpperCase());
                            friarh02.setReactivoFriarh(friarh);
                            friarh02.setReferencia(fvo.getReferencia().toUpperCase());
                            friarh02.setRegistroSanitario(fvo.getRegistroSanitario().toUpperCase());
                            
                            if(fvo.getId()!=null){
                                friarh02.setId(fvo.getId());
                                reactivoFriarh02DAO.edit(friarh02);
                            }else{
                                reactivoFriarh02DAO.create(friarh02);
                            }
                        }
                    }
                    try
                    {
                    friarhVO.getSeguimiento().setCategoria("friarh");
                    friarhVO.getSeguimiento().setIdReporte(friarh.getReactivoFriarhPK().getFriarh());
                    reactivoSeguimientoFacade.insertar_observacion(friarhVO.getSeguimiento());
                    }
                    catch(Exception r1){
                        //xx.info ("ReactivoVigilanciaBean, Error al ingresar seguimiento: catch"+r1.getMessage());
                        r1.printStackTrace();
                    }
                    //xx.info ("ReactivoVigilanciaBean, guardarReporteSeguridad() - Fin reactivoSeguimientoFacade.insertar_observacion");
                    if (internet != null) {
                        boolean enviarCorreo = this.envioCorreo("Reporte " + friarh.getReactivoFriarhPK().getFriarh(), correo, 
                                internet.getEmailEmpresa(), internet.getEmailPersona(),"guardarReporteSeguridad");
                    }
            } else {
                respuestaVO = new RespuestaVO();
                respuestaVO.setCode(0);
                respuestaVO.setStatus("ER");
                respuestaVO.setMensaje("Falta Información");
                respuestaVO.setMensajeDetallado("El objeto de entrda viene vacio");
            }

        } catch (Exception e) 
        {
            logEJBReactivo.log(Priority.ERROR, "Error al Guardar Reporte de Seguridad", e);
            respuestaVO = new RespuestaVO();
            respuestaVO.setCode(0);
            respuestaVO.setStatus("ER");
            respuestaVO.setMensaje("Error Tecnico");
            respuestaVO.setMensajeDetallado(e.toString());
            
        } finally {
            xx.info ("ReactivoVigilanciaBean, guardarReporteSeguridad() - respuestaVO = "+respuestaVO.getMensajeDetallado()+"-"+respuestaVO.getStatus());
            return respuestaVO;
        }
        
        
            
        
    }

    /**
     * Obtiene el catalogo de areas para evento adverso
     *
     * @return listado de areas
     */
    @Override
    public List<AreasVO> obtenerCatalogoAreas() {
        List<AreasVO> areasVOs = null;
        try {
            List<ReactivoArea> areas = reactivoAreaDAO.findAll();
            if (areas != null && !areas.isEmpty()) {
                areasVOs = new ArrayList<>();
                for (ReactivoArea area : areas) {
                    AreasVO avo = new AreasVO();
                    avo.setCdgArea(area.getCdgArea());
                    avo.setDescripcion(area.getDescripcion());
                    areasVOs.add(avo);
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener catalogo de areas: ", e);
        } finally {
            return areasVOs;
        }
    }

    /**
     * Obtiene el catalogo de problemas del reactivo
     *
     * @returnlistado de problemas reactivo
     */
    @Override
    public List<ProblemasVO> obtenerCatalogoProblemas() {
        List<ProblemasVO> problemasVOs = null;
        try {
            List<ReactivoProblema> problemas = reactivoProblemaDAO.findAll();
            if (problemas != null && !problemas.isEmpty()) {
                problemasVOs = new ArrayList<>();
                for (ReactivoProblema problema : problemas) {
                    ProblemasVO pvo = new ProblemasVO();
                    pvo.setCdgProblema(problema.getCdgProblema());
                    pvo.setDescripcion(problema.getDescripcion());
                    problemasVOs.add(pvo);
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Erro Obtener catalogos de problemas: ", e);
        } finally {
            return problemasVOs;
        }
    }

    /**
     * Obtiene el catalogo de desenlaces del reactivo
     *
     * @return listado de psoibles desenlaces
     */
    @Override
    public List<DesenlacesVO> obtenerCatalogDesenlaces() {
        List<DesenlacesVO> desenlacesVOs = null;
        try {
            List<ReactivoDesenlace> desenlaces = reactivoDesenlaceDAO.findAll();
            if (desenlaces != null && !desenlaces.isEmpty()) {
                desenlacesVOs = new ArrayList<>();
                for (ReactivoDesenlace desenlace : desenlaces) {
                    DesenlacesVO dvo = new DesenlacesVO();
                    dvo.setCdgDesenlace(desenlace.getCdgDesenlace());
                    dvo.setDescripcion(desenlace.getDescripcion());
                    desenlacesVOs.add(dvo);
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener catalogo desenlaces; ", e);
        } finally {
            return desenlacesVOs;
        }
    }

    /**
     * Guarda el formulario de reporte de efectos adversos
     *
     * @param adversosVO: objeto con los datos del formulario
     * @return Objeto de respuesta
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RespuestaVO guardarEfectosAdversos(EfectosAdversosVO adversosVO) {
        RespuestaVO rvo = null;
        try {
            if (adversosVO != null) {
                boolean actualizando_registro = false;
                //calcular radicado
                Parametros p = parametrosDAO.find(1);
                p.setConsecReactivo(p.getConsecReactivo() + 1);
                parametrosDAO.edit(p);
                Date fechaActual = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
                String fechaTexto = sdf.format(fechaActual);
                String[] fechaPartes = fechaTexto.split("-");
                Formatter f = new Formatter();
                String reporte="";
                if(adversosVO.getInstitucionVO().getReporte()!=null){
                    reporte = adversosVO.getInstitucionVO().getReporte();
                    actualizando_registro = true;
                }
                else{
                    reporte = p.getPrefijoReactivo() + fechaPartes[0] + fechaPartes[1] + f.format("%05d", p.getConsecReactivo()).toString();
                }
                
                // procesa
                //institucion
                if (adversosVO.getInstitucionVO() != null) {
                    ReactivoInstitucion institucion = new ReactivoInstitucion();
                    institucion.setCodDepart(adversosVO.getInstitucionVO().getCodDepart());
                    institucion.setCodMun(adversosVO.getInstitucionVO().getCodMun());
                    institucion.setComplejidad(adversosVO.getInstitucionVO().getComplejidad());
                    institucion.setDireccion(adversosVO.getInstitucionVO().getDireccion());
                    institucion.setEmail(adversosVO.getInstitucionVO().getEmail());
                    institucion.setFechaReporte(adversosVO.getInstitucionVO().getFechaReporte());
                    institucion.setIdentificacion(adversosVO.getInstitucionVO().getIdentificacion());
                    institucion.setInstitucion(adversosVO.getInstitucionVO().getInstitucion());
                    institucion.setNaturaleza(adversosVO.getInstitucionVO().getNaturaleza());
                    //reporte
                    institucion.setReporte(reporte);
                    institucion.setTelefono(adversosVO.getInstitucionVO().getTelefono());
                    if(actualizando_registro){
                        reactivoInstitucionDAO.edit(institucion);
                    }
                    else
                    {
                        reactivoInstitucionDAO.create(institucion);
                    }
                    
                }
                //paciente
                if (adversosVO.getPacienteVO() != null) {
                    ReactivoPaciente paciente = new ReactivoPaciente();
                    paciente.setDireccPaciente(adversosVO.getPacienteVO().getDireccPaciente().toUpperCase());
                    paciente.setEdad(adversosVO.getPacienteVO().getEdad());
                    paciente.setEdadEn(adversosVO.getPacienteVO().getEdadEn());
                    paciente.setGenero(adversosVO.getPacienteVO().getGenero().toString());
                    paciente.setIdentifi(adversosVO.getPacienteVO().getIdentifi());
                    paciente.setNombrePaciente(adversosVO.getPacienteVO().getNombrePaciente().toUpperCase());
                    paciente.setReporte(reporte);
                    paciente.setTelefoPaciente(adversosVO.getPacienteVO().getTelefoPaciente());
                    paciente.setTipidentifi(adversosVO.getPacienteVO().getTipidentifi());
                    
                    if(actualizando_registro){
                        reactivoPacienteDAO.edit(paciente);
                    }
                    else
                    {
                        reactivoPacienteDAO.create(paciente);
                    }
                }
                //producto
                if (adversosVO.getProductoVO() != null) {
                    ReactivoProducto producto = new ReactivoProducto();
                    producto.setAreaFunciona(adversosVO.getProductoVO().getAreaFunciona());
                    producto.setCadenaFrio(adversosVO.getProductoVO().getCadenaFrio());
                    producto.setCertiAnalisis(adversosVO.getProductoVO().getCertiAnalisis());
                    producto.setCondiciAlmacen(adversosVO.getProductoVO().getCondiciAlmacen());
                    producto.setDistriUsuario(adversosVO.getProductoVO().getDistriUsuario());
                    producto.setFechaVenci(adversosVO.getProductoVO().getFechaVenci());
                    producto.setLote(adversosVO.getProductoVO().getLote());
                    producto.setNombreReactivo(adversosVO.getProductoVO().getNombreReactivo().toUpperCase());
                    producto.setNroregsan(adversosVO.getProductoVO().getNroregsan().toUpperCase());
                    producto.setOtraArea(adversosVO.getProductoVO().getOtraArea() == null ? " " : adversosVO.getProductoVO().getOtraArea().toUpperCase());
                    producto.setProcedencia(adversosVO.getProductoVO().getProcedencia());
                    producto.setReporte(reporte);
                    producto.setTemperatura(adversosVO.getProductoVO().getTemperatura());
                    
                    if(actualizando_registro){
                        reactivoProductoDAO.edit(producto);
                    }
                    else
                    {
                        reactivoProductoDAO.create(producto);
                    }
                }
                //eventos
                if (adversosVO.getEventosVO() != null) {
                    ReactivoEventos eventos = new ReactivoEventos();
                    eventos.setCdgDesenlace(adversosVO.getEventosVO().getCdgDesenlace());
                    eventos.setCdgProblema(adversosVO.getEventosVO().getCdgProblema());
                    eventos.setClasificacion(adversosVO.getEventosVO().getClasificacion());
                    eventos.setDescripEfecto(adversosVO.getEventosVO().getDescripEfecto());
                    //eventos.setDesempeno(adversosVO.getEventosVO().getDesempeno().toString());
                    eventos.setDesempeno(null);
                    eventos.setEfectoIndeseado(adversosVO.getEventosVO().getEfectoIndeseado());
                    eventos.setFechEvento(adversosVO.getEventosVO().getFechEvento());
                    eventos.setFechReporte(adversosVO.getEventosVO().getFechReporte());
                    eventos.setFechaIngreso(adversosVO.getEventosVO().getFechaIngreso());
                    eventos.setInternet("S");
                    eventos.setOtroDesenlace(adversosVO.getEventosVO().getOtroDesenlace() == null ? " " : adversosVO.getEventosVO().getOtroDesenlace().toUpperCase());
                    //eventos.setReportado(adversosVO.getEventosVO().getReportado().toString());
                    eventos.setReportado(null);
                    eventos.setReporte(reporte);
                    
                    if(actualizando_registro){
                        reactivoEventosDAO.edit(eventos);
                    }
                    else
                    {
                        reactivoEventosDAO.create(eventos);
                    }
                    //Guarda Datos Expediente del reactivo para complementar con la información del reporte
                    //Libardo López
                    ReactivoDatosExpedientesPK rdepk = new ReactivoDatosExpedientesPK();
                    rdepk.setReporte(reporte);
                    rdepk.setNroexpediente(0);
                    ReactivoDatosExpedientes rde = new ReactivoDatosExpedientes();
                    rde.setReactivoDatosExpedientesPK(rdepk);
                    if(actualizando_registro){
                        
                    }
                    else
                    {
                        reactivoDatosExpedientesDAO.create(rde);
                    }
                    
                }
                //Gestion realizada
                if (adversosVO.getGestionRealizadaVO() != null) {
                    ReactivoGestionrealizada rg = new ReactivoGestionrealizada();
                    rg.setAcciones(adversosVO.getGestionRealizadaVO().getAcciones());
                    rg.setAnalisisEfecto(adversosVO.getGestionRealizadaVO().getAnalisisEfecto());
                    rg.setCausaEfecto(adversosVO.getGestionRealizadaVO().getCausaEfecto());
                    rg.setCausaProbable(adversosVO.getGestionRealizadaVO().getCausaProbable());
                    rg.setComercializador(adversosVO.getGestionRealizadaVO().getComercializador().toUpperCase());
                    rg.setDescripcionAcciones(adversosVO.getGestionRealizadaVO().getDescripcionAcciones() == null ? " "
                            : adversosVO.getGestionRealizadaVO().getDescripcionAcciones());
                    rg.setDescripcionCausa(adversosVO.getGestionRealizadaVO().getDescripcionCausa());
                    rg.setDistribuidor(adversosVO.getGestionRealizadaVO().getDistribuidor().toUpperCase());
                    rg.setEnviadoDistriImport(adversosVO.getGestionRealizadaVO().getEnviadoDistriImport().toUpperCase());
                    rg.setFabricante(adversosVO.getGestionRealizadaVO().getFabricante().toUpperCase());
                    rg.setFechaNotificacion(adversosVO.getGestionRealizadaVO().getFechaNotificacion());
                    rg.setGestionRiesgo(adversosVO.getGestionRealizadaVO().getGestionRiesgo());
                    rg.setHerramientaAnalisis(adversosVO.getGestionRealizadaVO().getHerramientaAnalisis());
                    rg.setImportador(adversosVO.getGestionRealizadaVO().getImportador());
                    rg.setOtraHerramienta(adversosVO.getGestionRealizadaVO().getOtraHerramienta() == null ? " "
                            : adversosVO.getGestionRealizadaVO().getOtraHerramienta().toUpperCase());
                    rg.setReporte(reporte);
                   
                    if(actualizando_registro){
                        reactivoGestionrealizadaDAO.edit(rg);
                    }
                    else
                    {
                         reactivoGestionrealizadaDAO.create(rg);
                    }
                }
                //Reportante
                if (adversosVO.getReportanteVO() != null) 
                {
                    ReactivoReportante rr = new ReactivoReportante();
                    rr.setAreaPertenece(adversosVO.getReportanteVO().getAreaPertenece().toUpperCase());
                    rr.setCargo(obtenerValorValidado(adversosVO.getReportanteVO().getCargo()));
                    rr.setCodDepart(obtenerCodigoDivipola(adversosVO.getReportanteVO().getCodDepart(),"depto"));
                    rr.setCodMun(obtenerCodigoDivipola(adversosVO.getReportanteVO().getCodMun(),"municipio"));
                    rr.setDireccion(obtenerValorValidado(adversosVO.getReportanteVO().getDireccion()).toUpperCase());
                    rr.setDivulgacion(adversosVO.getReportanteVO().getDivulgacion());
                    rr.setEmail(adversosVO.getReportanteVO().getEmail());
                    rr.setFechaNotificacion(adversosVO.getReportanteVO().getFechaNotificacion());
                    rr.setIdentificacion(adversosVO.getReportanteVO().getIdentificacion());
                    rr.setMovil(adversosVO.getReportanteVO().getMovil());
                    rr.setNombresApellidos(adversosVO.getReportanteVO().getNombresApellidos().toUpperCase());
                    rr.setPais(obtenerCodigoDivipola(adversosVO.getReportanteVO().getPais(),"pais"));
                    rr.setProfesion(adversosVO.getReportanteVO().getProfesion());
                    rr.setReporte(reporte);
                    rr.setTelefono(obtenerValorValidado(adversosVO.getReportanteVO().getTelefono()));
                    
                    if(actualizando_registro){
                        reactivoReportanteDAO.edit(rr);
                    }
                    else
                    {
                         reactivoReportanteDAO.create(rr);
                    }
                    adversosVO.getSeguimiento().setCategoria("adversos");
                    adversosVO.getSeguimiento().setIdReporte(reporte);
                    reactivoSeguimientoFacade.insertar_observacion(adversosVO.getSeguimiento());
                }
                
                if(actualizando_registro){
                        
                    }
                else
                    {
                ReactivoUsuariosInternet internet = reactivoUsuariosInternetDAO.find(adversosVO.getUsuario());
                
                sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
                String fechaHora = sdf.format(adversosVO.getReportanteVO().getFechaNotificacion());
                
                String correo = "<html><p align=\"justify\">Su reporte ha sido ingresado al Sistema de Información del Programa Nacional de Reactivovigilancia, a continuación se presenta un resumen del trámite efectuado:</p><br><br>"
                        + "<p align=\"center\"><b>Fecha y hora del ingreso:</b> " + fechaHora + "<br>"
                        + "<b>Código asignado:</b> " + reporte
                        + "<br><b>Reactivo de Diagnóstico In Vitro: </b>" + adversosVO.getProductoVO().getNombreReactivo().toUpperCase();
                
                if (internet != null) {
                    boolean enviarCorreo = this.envioCorreo("Reporte " + reporte, correo, internet.getEmailEmpresa()
                            , internet.getEmailPersona(),"guardarEfectosAdversos");
                }
                    }
                
                
                //Se almaceno el reporte
                rvo = new RespuestaVO();
                rvo.setCode(1);
                rvo.setStatus("OK");
                rvo.setMensaje("Almacenado exitosamente");
                rvo.setMensajeDetallado(reporte);
            } else {
                rvo = new RespuestaVO();
                rvo.setCode(0);
                rvo.setStatus("ER");
                rvo.setMensaje("Error no hay datos");
                rvo.setMensajeDetallado("No hay datos de entrada");
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al guardar reporte", e);
            rvo = new RespuestaVO();
            rvo.setCode(0);
            rvo.setStatus("ER");
            rvo.setMensaje("Error técnico contacte al administrador");
            rvo.setMensajeDetallado(e.toString());
        } finally {
            return rvo;
        }
    }
    //******************************************************************************
    //******************************************************************************
    //******************************************************************************
    private String obtenerValorValidado (Object valor)
    {
        String valorReal =  "";
        
        //System.out.println ("VALOR DEL OBJETO: " + valor);
        
        try
        {
            if (valor != null)
            {
                valorReal = (String)valor;
            }
            else
            {
                valorReal = "SIN DEFINIR";
            }
            
            //System.out.println ("VALOR OBTENIDO DE VALIDACION: " + valorReal);
        }
        
        catch (Exception errorValor)
        {
            errorValor.printStackTrace();
            return  ("SIN DEFINIR");
        }
        
        return (valorReal);
    }
    //******************************************************************************
    //******************************************************************************
    //******************************************************************************
    private String obtenerCodigoDivipola (String valor, String tipo)
    {
        String resultado = "";
        
        if (tipo.equals("pais"))
        {
            if (valor != null)
            {
                resultado = valor;
            }
            else
            {
                resultado = "CO";
            }
        }
        else
        if (tipo.equals("depto"))
        {
            if (valor != null)
            {
                resultado = valor;
            }
            else
            {
                resultado = "11";
            }
        }
        else
        {
            if (valor != null)
            {
                resultado = valor;
            }
            else
            {
                resultado = "11";
            }
        }
            
        
        return (resultado);
    }
    /**
     * Obtiene el usuario por nombre de usuario
     *
     * @param usuario: nombre de usuario
     * @return Objeto con los datos para validar la respuesta
     */
    @Override
    public UsuariosVO buscarUsuarioPorNombreUSuario(String usuario) {
        uvo = null;
        try {
            ReactivoUsuariosInternet rui = reactivoUsuariosInternetDAO.find(usuario);
            if (rui != null) {
                uvo = new UsuariosVO();
                uvo.setActivo(rui.getActivo());
                uvo.setCargoPersona(rui.getCargoPersona());
                uvo.setCdgPais(rui.getCdgPais());
                uvo.setClasificacionUsuario(rui.getClasificacionUsuario());
                uvo.setCodDepart(rui.getCodDepart());
                uvo.setCodMun(rui.getCodMun());
                uvo.setDireccionEmpresa(rui.getDireccionEmpresa());
                uvo.setEmailEmpresa(rui.getEmailEmpresa());
                uvo.setEmailPersona(rui.getEmailPersona());
                uvo.setEstadoUsuario(rui.getEstadoUsuario());
                uvo.setFax(rui.getFax());
                uvo.setFechaIngreso(rui.getFechaIngreso());
                uvo.setIdentificacionEmpresa(rui.getIdentificacionEmpresa());
                uvo.setIdentificacionPersona(rui.getIdentificacionPersona());
                uvo.setNombreEmpresa(rui.getNombreEmpresa());
                uvo.setNombrePersona(rui.getNombrePersona());
                uvo.setPassword(rui.getPassword());
                uvo.setPregunta(rui.getPregunta());
                uvo.setRespuesta("");
                uvo.setSession(rui.getSession());
                uvo.setTelefonoEmpresa(rui.getTelefonoEmpresa());
                uvo.setTelefonoPersona(rui.getTelefonoPersona());
                uvo.setTipidentificacionEmpresa(rui.getTipidentificacionEmpresa());
                uvo.setTipidentificacionPersona(rui.getTipidentificacionPersona());
                uvo.setUrl(rui.getUrl());
                uvo.setUsuario(rui.getUsuario());
                uvo.setiDRolUsuario(rui.getIDRolUsuario());
                uvo.setIdRol(Integer.toString(rui.getIDRolUsuario()));
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener usuario", e);
        } finally {
            return uvo;
        }
    }


    @Override
    public UsuariosVO buscarUsuarioPorCedulaYNombre(long cedula, String nombre)
    {
        uvo = null;
        Connection conexion = null;
        
        
        try
        {
            logEJBReactivo.info ("EJECUTANDO METODO buscarUsuarioPorCedulaYNombre ");
            logEJBReactivo.info ("CEDULA USUARIO: " + cedula);
            logEJBReactivo.info ("NOMBRE USUARIO: " + nombre);
            ReactivoUsuariosInternet rui = null;
            List<ReactivoUsuariosInternet> ruiLista = null;
            
            Query query1 = manejadorEntidadesDireccion.createQuery("from ReactivoUsuariosInternet as rui where rui.identificacionPersona=:identificacionPersona and rui.nombrePersona=:nombrePersona");
            query1.setParameter("identificacionPersona",cedula);
            query1.setParameter("nombrePersona",nombre);
            ruiLista = (List<ReactivoUsuariosInternet>)query1.getResultList();
            
            if(ruiLista.isEmpty()){
                query1 = manejadorEntidadesDireccion.createQuery("from ReactivoUsuariosInternet as rui where rui.identificacionPersona=:identificacionPersona");
                query1.setParameter("identificacionPersona",cedula);
                ruiLista = (List<ReactivoUsuariosInternet>)query1.getResultList();
            }
            
            if (ruiLista.size() > 1)
            {
                logEJBReactivo.info ("Se obtuvo más de un resultado del usuario");
                rui = (ReactivoUsuariosInternet)ruiLista.get(0);
            }
            else
            {
                logEJBReactivo.info ("Se obtuvo un solo resultado del usuario");
                rui = (ReactivoUsuariosInternet)query1.getSingleResult();
            }
            //**************************************************************
            //**************************************************************
            //**************************************************************
            //**************************************************************
            logEJBReactivo.info ("Reactivo encontrado: " + rui);
            //**************************************************************
            //**************************************************************
            if (rui != null)
            {        
                    uvo = new UsuariosVO();
                    uvo.setActivo(rui.getActivo());
                    uvo.setCargoPersona(rui.getCargoPersona());
                    uvo.setCdgPais(rui.getCdgPais());
                    uvo.setClasificacionUsuario(rui.getClasificacionUsuario());
                    uvo.setCodDepart(rui.getCodDepart());
                    uvo.setCodMun(rui.getCodMun());
                    uvo.setDireccionEmpresa(rui.getDireccionEmpresa());
                    uvo.setEmailEmpresa(rui.getEmailEmpresa());
                    uvo.setEmailPersona(rui.getEmailPersona());
                    uvo.setEstadoUsuario(rui.getEstadoUsuario());
                    uvo.setFax(rui.getFax());
                    uvo.setFechaIngreso(rui.getFechaIngreso());
                    uvo.setIdentificacionEmpresa(rui.getIdentificacionEmpresa());
                    uvo.setIdentificacionPersona(rui.getIdentificacionPersona());
                    uvo.setNombreEmpresa(rui.getNombreEmpresa());
                    uvo.setNombrePersona(rui.getNombrePersona());
                    uvo.setPassword(rui.getPassword());
                    uvo.setPregunta(rui.getPregunta());
                    uvo.setRespuesta("");
                    uvo.setSession(rui.getSession());
                    uvo.setTelefonoEmpresa(rui.getTelefonoEmpresa());
                    uvo.setTelefonoPersona(rui.getTelefonoPersona());
                    uvo.setTipidentificacionEmpresa(rui.getTipidentificacionEmpresa());
                    uvo.setTipidentificacionPersona(rui.getTipidentificacionPersona());
                    uvo.setUrl(rui.getUrl());
                    uvo.setUsuario(rui.getUsuario());
                    uvo.setiDRolUsuario(rui.getIDRolUsuario());
                    uvo.setIdRol(Integer.toString(rui.getIDRolUsuario()));
            }   
            
        } 
        
        catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener usuario", e);
        } finally {
            return uvo;
        }
    }

    /**
     * Envia el correo electrónico recuperando la contraseña
     *
     * @param usuariosVO: obejto con los datos de usuario
     * @return Cadena con la respuesta de la operación
     */
    @Override
    public String recuperarContrasena(UsuariosVO usuariosVO) {
        String respuesta = "";
        try {
            ReactivoUsuariosInternet internet = reactivoUsuariosInternetDAO.find(usuariosVO.getUsuario());
            if (internet != null) {
                if (internet.getRespuesta().toUpperCase().equals(usuariosVO.getRespuesta().toUpperCase())) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
                    Date fechaActual = new Date();
                    String correo = "<html>Bogotá D.C., " + sdf.format(fechaActual) + "<br><br>"
                            + "Doctor(a)<br>" + internet.getNombrePersona().toUpperCase() + "<br>"
                            + "<b>" + internet.getNombreEmpresa().toUpperCase() + "</b><br><br>"
                            + "<p align=\"justify\"> Por medio del presente se le hace entrega de sus credenciales de acceso al Programa NAcional de ReactivoVigilancia.</p>"
                            + "<p align=\"justify\"> Nombre de usuario: <b>" + internet.getUsuario() + "</b><br>"
                            + "Contraseña: <b>" + internet.getPassword() + "</b></p><br><br>Cordialmente,<br><br>"
                            + "<p><b>Grupo de Vigilancia Epidemiológica<br>"
                            + "Dirección de Dispositivos Médicos y Otras Tecnologías<br>"
                            + "INVIMA<br>"
                            + "Tel: (57-1) 2948700 Ext. 3607</b></p></html>";
                    boolean enviarCorreo = this.envioCorreo("Recuperar Contraseña", correo, internet.getEmailEmpresa()
                            , internet.getEmailPersona(),"recuperarContraseña");
                    respuesta = "Correo enviado Exitosamente";
                } else {
                    respuesta = "Su correo no ha sido enviado";
                }
            }
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "error al recuperar clave: ", e);
            respuesta = "Error al recuperar contraseña, Contacte con el administrador";
        } finally {
            return respuesta;
        }
    }
}
