package co.gov.invima.beans;

import co.gov.invima.BO.ReactivoBO;
import co.gov.invima.VO.CiudadesVO;
import co.gov.invima.VO.DepartamentoVO;
import co.gov.invima.VO.EstadoVO;
import co.gov.invima.VO.Friarh01VO;
import co.gov.invima.VO.Friarh02VO;
import co.gov.invima.VO.FriarhVO;
import co.gov.invima.VO.FuenteVO;
import co.gov.invima.VO.NotificacionVO;
import co.gov.invima.VO.NotificanteVO;
import co.gov.invima.VO.RespuestaVO;
import co.gov.invima.VO.SeguimientoVO;
import co.gov.invima.VO.UsuariosVO;
import co.gov.invima.entities.Ciudades;
import co.gov.invima.entities.CiudadesPK;
import co.gov.invima.entities.Departamentos;
import co.gov.invima.entities.Funcionarios;
import co.gov.invima.entities.Funcionario_sin;
import co.gov.invima.entities.ReactivoFriarh;
import co.gov.invima.entities.ReactivoFriarh01;
import co.gov.invima.entities.ReactivoFriarh02;
import co.gov.invima.entities.ReactivoMonitoreo;
import co.gov.invima.entities.ReactivoSeguimiento;
import co.gov.invima.entities.TecnoTipoalertas;
import co.gov.invima.negocio.ServicioReporteTrimestralRemote;
import co.gov.invima.service.ServiceLocator;
import co.gov.invima.utils.util;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Priority;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

@Named(value = "reporteSeguridadBean")
@SessionScoped
public class ReporteSeguridadBean implements Serializable {

    private final Logger logBeanWebReactivo = Logger.getLogger(ReporteSeguridadBean.class.getName());
    private ReactivoBO reactivoBO;
    private final HttpSession session;
    private FriarhVO friarhVO;
    private String fechaReporte;
    private String cdgNotificacion;
    private String cdgNotificante;
    private String cdgFuente;
    private String razonSocial;
    private String nit;
    private String direccion;
    private String pais;
    private String departamento;
    private String ciudad;
    private String telefono;
    private String personaReporta;
    private String profesion;
    private String email;
    private String mensajes;
    private UsuariosVO usuariosVO;
    private UsuariosVO usuariosVO_original;
    private Date fechaHurto;
    private SimpleDateFormat sdf;
    private List<NotificacionVO> notificacionVOs;
    private List<NotificanteVO> lstNotificante;
    private List<FuenteVO> lstFuentes;
    private List<TecnoTipoalertas> lstTecnoTipoalertas;
    private List<Funcionario_sin> lstFuncionarios;
    private List<String> lstAplica;
    private List<String> lstReportesFRIARH;
    private List<String> lstReactivoMonitoreo;
    private List<EstadoVO> lstEstados;
    private List<DepartamentoVO> lstDpto;
    private List<CiudadesVO> lstCiudades;
    private boolean mostrarHurto = false;
    private List<Friarh01VO> friarh01;
    private Friarh01VO friarh01VO;
    private List<Friarh02VO> friarh02;
    private Friarh02VO friarh02VO;
    private boolean mostrarOtroEstado;
    private boolean mostrarOtroNotificante;
    private boolean mostrarNombreAgencia;
    private boolean mostrarCuantosDesc;
    private boolean monitoreo_activo = false;
    private boolean monitoreo_activo_formulario = false;
    private boolean reporte_friarh_activo = false;
    private boolean reporte_friarh_activo_formulario = false;
    private String reactivoMonitoreo_fechaIngreso_temporal;
    ReactivoMonitoreo reactivoMonitoreo = new ReactivoMonitoreo();
    String id_monitoreo_temporal = "";
    private ReactivoSeguimiento reactivoSeguimiento;
    private List<ReactivoSeguimiento> ltSeguimientos;
    private ArrayList listaCausasProbables;
    private String codigoCausaPV;
    private String descripcionCausaProbableV;
    private boolean enableRegistrarVisita;
    
    @EJB
    private ServicioReporteTrimestralRemote ejbServicioRemotoReporteMasivoTrimestralPantalla;
    boolean guardado_correctamente = true;

    public ArrayList getListaCausasProbables() {
        return listaCausasProbables;
    }

    public void setListaCausasProbables(ArrayList listaCausasProbables) {
        this.listaCausasProbables = listaCausasProbables;
    }

    public ServicioReporteTrimestralRemote getEjbServicioRemotoReporteMasivoTrimestralPantalla() {
        return ejbServicioRemotoReporteMasivoTrimestralPantalla;
    }

    public void setEjbServicioRemotoReporteMasivoTrimestralPantalla(ServicioReporteTrimestralRemote ejbServicioRemotoReporteMasivoTrimestralPantalla) {
        this.ejbServicioRemotoReporteMasivoTrimestralPantalla = ejbServicioRemotoReporteMasivoTrimestralPantalla;
    }

    public String getCodigoCausaPV() {
        return codigoCausaPV;
    }

    public void setCodigoCausaPV(String codigoCausaPV) {
        this.codigoCausaPV = codigoCausaPV;
    }

    public String getDescripcionCausaProbableV() {
        return descripcionCausaProbableV;
    }

    public void setDescripcionCausaProbableV(String descripcionCausaProbableV) {
        this.descripcionCausaProbableV = descripcionCausaProbableV;
    }

    public List<ReactivoSeguimiento> getLtSeguimientos() {
        return ltSeguimientos;
    }

    public void setLtSeguimientos(List<ReactivoSeguimiento> ltSeguimientos) {
        this.ltSeguimientos = ltSeguimientos;
    }

    private List<DepartamentoVO> lstDptoTabla;
    private List<CiudadesVO> lstMpioTabla;

    /**
     * Creates a new instance of ReporteSeguridadBean
     *
     * @throws java.text.ParseException
     */
    public ReporteSeguridadBean() throws ParseException {
        ServiceLocator service = new ServiceLocator();
        ejbServicioRemotoReporteMasivoTrimestralPantalla = service.getEjbRemotoReactivoReporteTrimestral();
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        reactivoBO = new ReactivoBO();
        usuariosVO = (UsuariosVO) session.getAttribute("usuario");
        usuariosVO_original = new UsuariosVO(usuariosVO);
        fechaReporte = this.obtenerFecha();
        friarhVO = new FriarhVO();
        sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", new Locale("es_ES"));
        friarhVO.setFechaRadicado(new Date());
       
        System.out.println("setFechaRadicado = "+friarhVO.getFechaRadicado());
        System.out.println("getFechaRadicadoWithFormat = "+friarhVO.getFechaRadicadoWithFormat());
        notificacionVOs = reactivoBO.obtenerCatalogoNotificacion();
        lstNotificante = reactivoBO.obtenerCatalogoNotificante();
        lstReportesFRIARH = reactivoBO.obtenerReportesFRIARH();
        lstReactivoMonitoreo = reactivoBO.obtenerLstReactivoMonitoreo();
        lstFuentes = reactivoBO.obtenerCatalogFuente();
        lstTecnoTipoalertas = reactivoBO.obtenerlstTecnoTipoalertas();
        lstFuncionarios = reactivoBO.obtenerlstFuncionarios();
        lstEstados = reactivoBO.obtenerCatalogoEstados();
        lstDpto = reactivoBO.obtenerDptos();
        mostrarHurto = true;
        friarh01 = new ArrayList<>();
        friarh02 = new ArrayList<>();
        mostrarOtroEstado = true;
        mostrarOtroNotificante = true;
        mostrarNombreAgencia = true;
        mostrarCuantosDesc = true;
        lstDptoTabla = reactivoBO.obtenerDptos();
        lstMpioTabla = new ArrayList<>();
        reactivoSeguimiento = new ReactivoSeguimiento();
        reactivoSeguimiento.setUsuario(usuariosVO.getUsuario());
        reactivoSeguimiento.setFechaIngreso(new Date());
        listaCausasProbables = armarItemsCombo(getEjbServicioRemotoReporteMasivoTrimestralPantalla().obtenerListaCausasProbables());
        if (usuariosVO.getiDRolUsuario() != 1) {
            btnNuevoReporte();
            reporte_friarh_activo_formulario = true;
            monitoreo_activo = false;
            monitoreo_activo_formulario = false;
        }
        enableRegistrarVisita= Boolean.TRUE;
    }

    public ReactivoSeguimiento getReactivoSeguimiento() {
        return reactivoSeguimiento;
    }

    public void setReactivoSeguimiento(ReactivoSeguimiento reactivoSeguimiento) {
        this.reactivoSeguimiento = reactivoSeguimiento;
    }

    private ArrayList armarItemsCombo(List<String> datosLista) {
        ArrayList items = new ArrayList();
        int i = 0;
        String id = "";
        String nombre = "";

        try {
            for (i = 0; i < datosLista.size(); i += 2) {
                id = (String) datosLista.get(i);
                nombre = (String) datosLista.get(i + 1);
                items.add(new SelectItem(id, nombre));
            }
        } catch (Exception errorItems) {
            errorItems.printStackTrace();
        }

        return (items);
    }

    public void changeDpto(ValueChangeEvent event) {
        String valor = (String) event.getNewValue();
        changeDpto_(valor);
    }
    
    public void changeDpto_(String codDepart1) {
        String valor = codDepart1;
        if (valor != null && !valor.isEmpty()) {
            lstCiudades = new ArrayList<>();
            for (DepartamentoVO dvo : lstDpto) {
                if (dvo.getCodDepart().equals(valor)) {
                    lstCiudades = dvo.getCiudadesVOs();
                    break;
                }
            }
        }
    }

    public void changeNotificacion(ValueChangeEvent event) {
        String valor = (String) event.getNewValue();
        if(valor.equalsIgnoreCase("H")){
            enableRegistrarVisita = true;
        }else{
            enableRegistrarVisita = false;
        }
        changeNotificacion_(valor);

    }
    
    public void changeNotificacion_(String cdgTipoalerta) {
       
        String valor = cdgTipoalerta;
        if (!valor.isEmpty()) {
            if (valor.equals("H")) {
                mostrarHurto = false;
                enableRegistrarVisita = true;
            } else {
                mostrarHurto = true;
                enableRegistrarVisita = false;
            }
        }

    }

    public void changeNotificante(ValueChangeEvent event) {
        String valor = event.getNewValue().toString();
        changeNotificante_(valor);
    }
    
    public void changeNotificante_(String cdgNotificante) {
        //String valor = friarhVO.getCdgNotificante()+"";
        String valor = cdgNotificante;
        if (!valor.isEmpty()) {
            if (valor.equals("4")) {
                mostrarOtroNotificante = false;
            } else {
                mostrarOtroNotificante = true;
            }
        }
    }
    
    public void changeFuenteMonitoreo(ValueChangeEvent event) {
        try{
        String valor = event.getNewValue().toString();
        reactivoMonitoreo.setFuente(Integer.parseInt(valor));
        }catch(Exception e){}
    }

    public void changeFuente(ValueChangeEvent event) {
        try{
        String valor = event.getNewValue().toString();
        changeFuente_(valor);
        }catch(Exception e){}
    }
    public void changeFuente_(String fuente) {
        try {
            String valor = fuente;
            if (!valor.isEmpty()) {
                if (valor.equals("4")) {
                    mostrarNombreAgencia = false;
                } else {
                    mostrarNombreAgencia = true;
                }
            }
        } catch (Exception e) {
        }

    }

    public void changeid_monitoreo_temporal(ValueChangeEvent event) {
        try {
            String valor = event.getNewValue().toString();
            id_monitoreo_temporal = valor;
        } catch (Exception e) {
            logBeanWebReactivo.info("changeid_monitoreo_temporal, catch: " + e.getLocalizedMessage());
        }
    }

    public void changeCuantos(ValueChangeEvent event) {
        if (!friarhVO.getCdgTipoalerta().equals("H")) {
            String valor = event.getNewValue().toString();
            changeCuantos_(valor);
        }
    }
    public void changeCuantos_(String reporteEventos) {
        if (!friarhVO.getCdgTipoalerta().equals("H")) {
            String valor = reporteEventos;
            if (!valor.isEmpty()) {
                mostrarCuantosDesc = !valor.equals("SI");
            }
        }
    }

    public void changeEstado(ValueChangeEvent event) {
        String valor = event.getNewValue().toString();
        changeEstado_(valor);
    }
    
    public void changeEstado_(String friarhVO_estadoReac) {
        String valor = friarhVO_estadoReac;
        if (!valor.isEmpty()) {
            if (valor.equals("8")) {
                mostrarOtroEstado = false;
            } else {
                mostrarOtroEstado = true;
            }
        }
    }

    public void changeDptoTabla(ValueChangeEvent event) {
        String valor = (String) event.getNewValue();
        if (!valor.isEmpty()) {
            lstMpioTabla = new ArrayList<>();
            for (DepartamentoVO dvo : lstDpto) {
                if (dvo.getDescripcion().equals(valor)) {
                    lstMpioTabla = dvo.getCiudadesVOs();
                    break;
                }
            }
        }
    }

    private String obtenerFecha() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(d);
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Listado Editado", ((Friarh01VO) event.getObject()).getNombreCliente());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowEditF2(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Listado Editado", ((Friarh02VO) event.getObject()).getNombreReactivo());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición Cancelada", ((Friarh01VO) event.getObject()).getNombreCliente());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancelF2(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición Cancelada", ((Friarh02VO) event.getObject()).getNombreReactivo());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private boolean verificaFriarh01(List<Friarh01VO> friarh01VOs) {
        boolean b = false;
        try {
            for (Friarh01VO friarh01VO1 : friarh01VOs) {
                if (friarh01VO1.getCantidad() > 0 && !friarh01VO1.getDepartamento().isEmpty()
                        && !friarh01VO1.getDireccion().isEmpty() && !friarh01VO1.getLote().isEmpty()
                        && !friarh01VO1.getMarca().isEmpty() && !friarh01VO1.getMunicipio().isEmpty()
                        && !friarh01VO1.getNombreCliente().isEmpty() && !friarh01VO1.getNombreContacto().isEmpty()
                        && !friarh01VO1.getNombreReactivo().isEmpty() && !friarh01VO1.getReferencia().isEmpty()
                        && !friarh01VO1.getRegistroSanitario().isEmpty() && !friarh01VO1.getTelefono().isEmpty()) {
                    Pattern pattern = Pattern.compile("[A-Za-z0-9Ññ].{0,150}");
                    Matcher match = pattern.matcher(friarh01VO1.getDepartamento());
                    if (match.matches()) {
                        match = pattern.matcher(friarh01VO1.getDireccion());
                        if (match.matches()) {
                            match = pattern.matcher(friarh01VO1.getLote());
                            if (match.matches()) {
                                match = pattern.matcher(friarh01VO1.getMarca());
                                if (match.matches()) {
                                    match = pattern.matcher(friarh01VO1.getNombreCliente());
                                    if (match.matches()) {
                                        match = pattern.matcher(friarh01VO1.getNombreContacto());
                                        if (match.matches()) {
                                            match = pattern.matcher(friarh01VO1.getNombreReactivo());
                                            if (match.matches()) {
                                                match = pattern.matcher(friarh01VO1.getReferencia());
                                                if (match.matches()) {
                                                    match = pattern.matcher(friarh01VO1.getRegistroSanitario());
                                                    if (match.matches()) {
                                                        pattern = Pattern.compile("^[0-9].*\\d");
                                                        match = pattern.matcher(friarh01VO1.getTelefono());
                                                        if (match.matches()) {
                                                            b = true;
                                                        } else {
                                                            b = false;
                                                            break;
                                                        }
                                                    } else {
                                                        b = false;
                                                        break;
                                                    }
                                                } else {
                                                    b = false;
                                                    break;
                                                }
                                            } else {
                                                b = false;
                                                break;
                                            }
                                        } else {
                                            b = false;
                                            break;
                                        }
                                    } else {
                                        b = false;
                                        break;
                                    }
                                } else {
                                    b = false;
                                    break;
                                }
                            } else {
                                b = false;
                                break;
                            }
                        } else {
                            b = false;
                            break;
                        }
                    } else {
                        b = false;
                        break;
                    }

                }
            }
        } catch (Exception e) {
            logBeanWebReactivo.log(Priority.ERROR, "Error al validar friarh01", e);
        } finally {
            return b;
        }
    }

    private boolean verificarFriarh02(List<Friarh02VO> friarh02VOs) {
        boolean b = false;
        try {
            for (Friarh02VO friarh02VO1 : friarh02VOs) {
                if (friarh02VO1.getCantidad() > 0 && !friarh02VO1.getLote().isEmpty()
                        && !friarh02VO1.getMarca().isEmpty() && !friarh02VO1.getNombreReactivo().isEmpty()
                        && !friarh02VO1.getReferencia().isEmpty() && !friarh02VO1.getRegistroSanitario().isEmpty()) {
                    Pattern pattern = Pattern.compile("[A-Za-z0-9Ññ].{1,150}");
                    Matcher match = pattern.matcher(friarh02VO1.getLote());
                    if (match.matches()) {
                        match = pattern.matcher(friarh02VO1.getMarca());
                        if (match.matches()) {
                            match = pattern.matcher(friarh02VO1.getNombreReactivo());
                            if (match.matches()) {
                                match = pattern.matcher(friarh02VO1.getReferencia());
                                if (match.matches()) {
                                    match = pattern.matcher(friarh02VO1.getRegistroSanitario());
                                    if (match.matches()) {
                                        b = true;
                                    } else {
                                        b = false;
                                        break;
                                    }
                                } else {
                                    b = false;
                                    break;
                                }
                            } else {
                                b = false;
                                break;
                            }
                        } else {
                            b = false;
                            break;
                        }
                    } else {
                        b = false;
                        break;
                    }
                } else {
                    b = false;
                    break;
                }
            }
        } catch (Exception e) {
            logBeanWebReactivo.log(Priority.ERROR, "Error al validar friarh02", e);
        } finally {
            return b;
        }
    }

    public boolean isMonitoreo_activo_formulario() {
        return monitoreo_activo_formulario;
    }

    public void setMonitoreo_activo_formulario(boolean monitoreo_activo_formulario) {
        this.monitoreo_activo_formulario = monitoreo_activo_formulario;
    }

    public boolean isReporte_friarh_activo() {
        return reporte_friarh_activo;
    }

    public void setReporte_friarh_activo(boolean reporte_friarh_activo) {
        this.reporte_friarh_activo = reporte_friarh_activo;
    }

    public boolean isReporte_friarh_activo_formulario() {
        return reporte_friarh_activo_formulario;
    }

    public void setReporte_friarh_activo_formulario(boolean reporte_friarh_activo_formulario) {
        this.reporte_friarh_activo_formulario = reporte_friarh_activo_formulario;
    }

    public void btnNuevoMonitoreo() {
        try {
            setMonitoreo_activo_formulario(true);
            reactivoMonitoreo = new ReactivoMonitoreo();
            id_monitoreo_temporal = "";
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ReactivoVigilanciaWeb/faces/pages/reporteSeguridad.xhtml");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ReporteSeguridadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnNuevoReporte() {
        try {
            setReporte_friarh_activo_formulario(true);
            reactivoBO = new ReactivoBO();
            friarhVO = new FriarhVO();
            reactivoSeguimiento.setObservacion("");
            friarhVO.setFriarh("");
            friarh01 = new ArrayList<>();
            friarh02 = new ArrayList<>();
            usuariosVO = new UsuariosVO();
            usuariosVO = new UsuariosVO(usuariosVO_original);
            ltSeguimientos = new ArrayList<ReactivoSeguimiento>();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ReactivoVigilanciaWeb/faces/pages/reporteSeguridad.xhtml");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ReporteSeguridadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnNuevoReporte_usuario_diferente_invima() {
        mensajes="";
        if (usuariosVO.getiDRolUsuario() != 1 && guardado_correctamente) {
            btnNuevoReporte();
            reporte_friarh_activo_formulario = true;
            monitoreo_activo = false;
            monitoreo_activo_formulario = false;
        }
    }

    public void btnMonitoreo() {
        try {
            monitoreo_activo = true;
            monitoreo_activo_formulario = false;
            reporte_friarh_activo = false;
            reporte_friarh_activo_formulario = false;
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ReactivoVigilanciaWeb/faces/pages/reporteSeguridad.xhtml");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ReporteSeguridadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnREPORTE_FRIARH() {
        try {
            monitoreo_activo = false;
            monitoreo_activo_formulario = false;
            reporte_friarh_activo = true;
            reporte_friarh_activo_formulario = false;
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ReactivoVigilanciaWeb/faces/pages/reporteSeguridad.xhtml");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ReporteSeguridadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnCargarMonitoreo() {
        try {
            if (!getLstReactivoMonitoreo().contains(id_monitoreo_temporal)) {
                mensajes = "Monitoreo no encontrado, No.: " + id_monitoreo_temporal;
                FacesContext.getCurrentInstance().addMessage("panelContenedorSeguridad", new FacesMessage(FacesMessage.SEVERITY_FATAL, mensajes, null));
            } else {
                setMonitoreo_activo_formulario(true);
                reactivoMonitoreo = reactivoBO.obtenerReactivoMonitoreo(id_monitoreo_temporal);
                if (reactivoMonitoreo != null) {
                    reactivoMonitoreo_fechaIngreso_temporal = reactivoMonitoreo.getFechaIngreso().toString();
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/ReactivoVigilanciaWeb/faces/pages/reporteSeguridad.xhtml");
                } else {
                    mensajes = "Monitoreo no encontrado, No.: " + id_monitoreo_temporal;
                    FacesContext.getCurrentInstance().addMessage("panelContenedorSeguridad", new FacesMessage(FacesMessage.SEVERITY_FATAL, mensajes, null));
                }
            }
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ReporteSeguridadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnBuscar() {
        try {
            session.setAttribute("numeroReporte", friarhVO.getFriarh());
            lstReportesFRIARH = reactivoBO.obtenerReportesFRIARH();
            if (!getLstReportesFRIARH().contains(friarhVO.getFriarh())) {
                mensajes = "Reporte no encontrado, No. de FRIARH: " + friarhVO.getFriarh();
                FacesContext.getCurrentInstance().addMessage("panelContenedorSeguridad", new FacesMessage(FacesMessage.SEVERITY_FATAL, mensajes, null));
            } else {
                setReporte_friarh_activo_formulario(true);
                ReactivoFriarh x = reactivoBO.obtenerReporteFRIARH(friarhVO.getFriarh());
                if (x == null) {
                    friarhVO.setReporte_con_friah_pero_sin_crear(true);
                    reactivoMonitoreo = reactivoBO.obtenerReactivoMonitoreo(friarhVO.getFriarh());
                    try{friarhVO.setCdgTipoalerta(reactivoMonitoreo.getTipo());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setFuente(Short.parseShort(reactivoMonitoreo.getFuente()+""));}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setNombreAgencia(reactivoMonitoreo.getAgenciaSanitaria());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{changeFuente_(friarhVO.getFuente()+"");}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    
                } else {
                    try{friarhVO.setReactivoFriarhPK(x.getReactivoFriarhPK());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setFechaRadicado(x.getFechaRadicado());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setCdgTipoalerta(x.getReactivoFriarhPK().getCdgTipoalerta());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setCdgNotificante(x.getCdgNotificante());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setOtroNotificante(x.getOtroNotificante());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{changeNotificante_(friarhVO.getCdgNotificante()+"");}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    
                    try{friarhVO.setFuente(x.getFuente());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setNombreAgencia(x.getNombreAgencia());   }catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}    
                    try{changeFuente_(friarhVO.getFuente()+"");}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}

                    ReactivoBO reactivoBO = new ReactivoBO();
                    try {usuariosVO.setNombreEmpresa(x.getRazonSocialNotificante());} catch (Exception e) {logBeanWebReactivo.log(Priority.INFO, "Error al cargar parametro usuariosVO, Ubicacion = ReporteSeguridadBean.btnBuscar");}
                    try {usuariosVO.setNombreMpio(reactivoBO.obtenerNombreMpioPorID(x.getCodDepart(), x.getCodMun()));} catch (Exception e) {logBeanWebReactivo.log(Priority.INFO, "Error al cargar parametro usuariosVO, Ubicacion = ReporteSeguridadBean.btnBuscar");}
                    try {usuariosVO.setCodMun(x.getCodMun());} catch (Exception e) {logBeanWebReactivo.log(Priority.INFO, "Error al cargar parametro usuariosVO, Ubicacion = ReporteSeguridadBean.btnBuscar");}
                    try {usuariosVO.setIdentificacionEmpresa(x.getNit());} catch (Exception e) {logBeanWebReactivo.log(Priority.INFO, "Error al cargar parametro usuariosVO, Ubicacion = ReporteSeguridadBean.btnBuscar");}
                    try {usuariosVO.setTelefonoEmpresa(x.getTelefono());} catch (Exception e) {logBeanWebReactivo.log(Priority.INFO, "Error al cargar parametro usuariosVO, Ubicacion = ReporteSeguridadBean.btnBuscar");}
                    try {usuariosVO.setDireccionEmpresa(x.getDireccion());} catch (Exception e) {logBeanWebReactivo.log(Priority.INFO, "Error al cargar parametro usuariosVO, Ubicacion = ReporteSeguridadBean.btnBuscar");}
                    try {usuariosVO.setNombrePersona(x.getNombreNotificante());} catch (Exception e) {logBeanWebReactivo.log(Priority.INFO, "Error al cargar parametro usuariosVO, Ubicacion = ReporteSeguridadBean.btnBuscar");}
                    try {usuariosVO.setNombrePais(reactivoBO.obtenerPaisPorID(x.getPais()));} catch (Exception e) {logBeanWebReactivo.log(Priority.INFO, "Error al cargar parametro usuariosVO, Ubicacion = ReporteSeguridadBean.btnBuscar");}
                    try {usuariosVO.setCdgPais(x.getPais());} catch (Exception e) {logBeanWebReactivo.log(Priority.INFO, "Error al cargar parametro usuariosVO, Ubicacion = ReporteSeguridadBean.btnBuscar");}
                    try {usuariosVO.setNombreDpto(reactivoBO.obtenerDptoPorID(x.getCodDepart()));} catch (Exception e) {logBeanWebReactivo.log(Priority.INFO, "Error al cargar parametro usuariosVO, Ubicacion = ReporteSeguridadBean.btnBuscar");}
                    try {usuariosVO.setCodDepart(x.getCodDepart());} catch (Exception e) {logBeanWebReactivo.log(Priority.INFO, "Error al cargar parametro usuariosVO, Ubicacion = ReporteSeguridadBean.btnBuscar");}
                    try {usuariosVO.setEmailEmpresa(x.getEmail());} catch (Exception e) {logBeanWebReactivo.log(Priority.INFO, "Error al cargar parametro usuariosVO, Ubicacion = ReporteSeguridadBean.btnBuscar");}
                    
                    try{friarhVO.setCdgRiesgo(x.getCdgRiesgo());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setModelo(x.getModelo());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setNroregsan(x.getNroregsan());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setMarca(x.getMarca());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setNombreReac(x.getNombreReac());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setLote(x.getLote());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setEstadoReac(x.getEstadoReac());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setOtroEstadoreac(x.getOtroEstadoreac());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setProblemaReac(x.getProblemaReac());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setCausas(x.getCausas());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{cambiarCodigosCausa(x.getCausas());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}

                    try{friarhVO.setMedidasCorrectivas(x.getMedidasCorrectivas());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setReporteEventos(x.getReporteEventos());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setCuantosEventos(x.getCuantosEventos());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setDescripcionEvento(x.getDescripcionEvento());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}

                    try{friarhVO.setCodDepart1(x.getCodDepart1());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{changeDpto_(friarhVO.getCodDepart1());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setCodMun1(x.getCodMun1());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setFechaHurto(x.getFechaHurto());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setFiscalia(x.getFiscalia());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setAutorizacion(x.getAutorizacion());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                    try{friarhVO.setDescripcionHurto(x.getDescripcionHurto());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}

                    friarh02 = new ArrayList<>();
                    for (ReactivoFriarh02 fvo : x.getReactivoFriarh02List()) {
                        Friarh02VO friarh02VO = new Friarh02VO();
                        try{friarh02VO.setId(fvo.getId());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                        try{friarh02VO.setCantidad(fvo.getCantidad());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                        try{friarh02VO.setLote(fvo.getLote().toUpperCase());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                        try{friarh02VO.setMarca(fvo.getMarca().toUpperCase());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                        try{friarh02VO.setNombreReactivo(fvo.getNombreReactivo().toUpperCase());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                        try{friarh02VO.setReferencia(fvo.getReferencia().toUpperCase());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                        try{friarh02VO.setRegistroSanitario(fvo.getRegistroSanitario().toUpperCase());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                        friarh02.add(friarh02VO);
                    }

                    friarh01 = new ArrayList<>();
                    for (ReactivoFriarh01 fvo : x.getReactivoFriarh01List()) {
                        Friarh01VO friarh01VO = new Friarh01VO();
                        try{friarh01VO.setId(fvo.getId());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                        try{friarh01VO.setCantidad(fvo.getCantidad());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}

                        try {
                            Ciudades c = reactivoBO.obtenerPorCdgMunicipio(fvo.getCdgMunicipio() + "");
                            Departamentos d = reactivoBO.obtenerPorCdgTerritorio(c.getCiudadesPK().getCdgterritorio());
                            friarh01VO.setDepartamento(d.getDescripcion());
                            friarh01VO.setMunicipio(c.getCiudadesPK().getCiudad());
                        } catch (Exception e) {                      }
                            try{friarh01VO.setCdgMunicipio(fvo.getCdgMunicipio());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                            try{friarh01VO.setDireccion(fvo.getDireccion().toUpperCase());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                            try{friarh01VO.setLote(fvo.getLote().toUpperCase());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                            try{friarh01VO.setMarca(fvo.getMarca().toUpperCase());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                            try{friarh01VO.setNombreCliente(fvo.getNombreCliente().toUpperCase());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                            try{friarh01VO.setNombreContacto(fvo.getNombreContacto().toUpperCase());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                            try{friarh01VO.setNombreReactivo(fvo.getNombreReactivo().toUpperCase());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                            try{friarh01VO.setReferencia(fvo.getReferencia().toUpperCase());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                            try{friarh01VO.setRegistroSanitario(fvo.getRegistroSanitario().toUpperCase());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                            try{friarh01VO.setTelefono(fvo.getTelefono());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                            friarh01.add(friarh01VO);
                    }
                }
                try{setLtSeguimientos(reactivoBO.obtenerSeguimientosFRIARH(friarhVO.getFriarh(), "friarh"));}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                try{reactivoSeguimiento.setObservacion("");}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
                FacesContext.getCurrentInstance().getExternalContext().redirect("/ReactivoVigilanciaWeb/faces/pages/reporteSeguridad.xhtml");
            }
            try{changeNotificacion_(friarhVO.getCdgTipoalerta()+"");}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
            try{changeEstado_(friarhVO.getEstadoReac()+"");}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
            try{changeCuantos_(friarhVO.getReporteEventos());}catch(Exception e){logBeanWebReactivo.log(Priority.ERROR, "Error al cargar parametro, Ubicacion = ReporteSeguridadBean.btnBuscar", e);}
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ReporteSeguridadBean.class.getName()).log(Level.SEVERE, null, ex.getStackTrace());
        }
    }

    public void btnGuardarMonitoreo() {

        try {
            RespuestaVO rvo = new RespuestaVO();
            if (reactivoMonitoreo.getFriarh() == null) {
                reactivoMonitoreo.setFechaIngreso(new Date());
                reactivoMonitoreo.setInternet("S");
                
                String ll_consec = reactivoBO.obtener_parametro_consec_friarh();
                String ls_friarh = "RD" + reactivoMonitoreo.getTipo() + ll_consec;
                reactivoMonitoreo.setFriarh(ls_friarh);
                
                rvo = reactivoBO.guardarFriarhMonitoreo(reactivoMonitoreo);
            } else {
                reactivoMonitoreo.setInternet("S");
                reactivoMonitoreo.registro_nuevo = false;
                rvo = reactivoBO.guardarFriarhMonitoreo(reactivoMonitoreo);
            }

            if (rvo.getStatus().equals("OK")) {
                mensajes = "Reporte guardado con éxito, No. de FRIARH: " + reactivoMonitoreo.getFriarh();
                FacesContext.getCurrentInstance().addMessage("panelContenedorSeguridad", new FacesMessage(FacesMessage.SEVERITY_INFO, mensajes, null));
                this.limpiarPantalla();
            } else {
                mensajes = "ERROR, No. de FRIARH: " + reactivoMonitoreo.getFriarh();
                FacesContext.getCurrentInstance().addMessage("panelContenedorSeguridad", new FacesMessage(FacesMessage.SEVERITY_FATAL, mensajes, null));
                this.limpiarPantalla();
            }

        } catch (Exception e) {
            {
                mensajes = "Error al amacenar Reporte FRIARH ";
                FacesContext.getCurrentInstance().addMessage("panelContenedorSeguridad", new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        mensajes, null));
            }

        }

    }

    public void btnAceptar() {
        friarhVO.setSeguimiento(reactivoSeguimiento);
        if (friarhVO.getCdgTipoalerta().equals("H")) {
            //Hurto
            friarhVO.setCodDepart(usuariosVO.getCodDepart());
            friarhVO.setCodMun(usuariosVO.getCodMun());
            friarhVO.setDireccion(usuariosVO.getDireccionEmpresa());
            friarhVO.setEmail(usuariosVO.getEmailEmpresa());
            friarhVO.setNit(usuariosVO.getIdentificacionEmpresa());
            friarhVO.setNombreNotificante(usuariosVO.getNombrePersona());
            friarhVO.setRazonSocialNotificante(usuariosVO.getNombreEmpresa());
            friarhVO.setUsuario(usuariosVO.getUsuario());
            friarhVO.setFechaIngreso(friarhVO.getFechaRadicado());
            friarhVO.setPais(usuariosVO.getCdgPais());
            if (friarh02 != null && !friarh02.isEmpty()) {
                if (this.verificarFriarh02(friarh02)) {
                    friarhVO.setHurtados(friarh02);
                    RespuestaVO rvo = reactivoBO.guardarFriarh(friarhVO);
                    if (rvo.getStatus().equals("OK")) {
                        guardado_correctamente = true;
                        mensajes = "Reporte guardado con éxito, No. de FRIARH: " + rvo.getMensajeDetallado();
                        FacesContext.getCurrentInstance().addMessage("panelContenedorSeguridad", new FacesMessage(FacesMessage.SEVERITY_INFO, mensajes, null));
                        this.limpiarPantalla();
                    } else {
                        guardado_correctamente = false;
                        mensajes = "Error al amacenar Reporte FRIARH: " + rvo.getMensaje();
                        FacesContext.getCurrentInstance().addMessage("panelContenedorSeguridad", new FacesMessage(FacesMessage.SEVERITY_FATAL,
                                mensajes, null));
                    }
                } else {
                    mensajes = "Debe diligenciar todos los campos del formato L-FRIARH-02";
                    FacesContext.getCurrentInstance().addMessage("panelContenedorSeguridad", new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            mensajes, null));
                }
            } else {
                mensajes = "Debe diligenciar al menos una fila del formato L-FRIARH-02";
                FacesContext.getCurrentInstance().addMessage("panelContenedorSeguridad", new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        mensajes, null));
            }
        } else {
            //Alerta, Informe de Seguridad,Retiro dle producto dle mercado
            friarhVO.setCodDepart(usuariosVO.getCodDepart());
            friarhVO.setCodMun(usuariosVO.getCodMun());
            friarhVO.setDireccion(usuariosVO.getDireccionEmpresa());
            friarhVO.setEmail(usuariosVO.getEmailEmpresa());
            friarhVO.setNit(usuariosVO.getIdentificacionEmpresa());
            friarhVO.setNombreNotificante(usuariosVO.getNombrePersona());
            friarhVO.setRazonSocialNotificante(usuariosVO.getNombreEmpresa());
            friarhVO.setUsuario(usuariosVO.getUsuario());
            friarhVO.setFechaIngreso(friarhVO.getFechaRadicado());
            friarhVO.setPais(usuariosVO.getCdgPais());
            friarhVO.setTrazabilidad(friarh01);
            RespuestaVO rvo = reactivoBO.guardarFriarh(friarhVO);
            if (rvo.getStatus().equals("OK")) {
                mensajes = "Reporte guardado con éxito, No. de FRIARH: " + rvo.getMensajeDetallado();
                FacesContext.getCurrentInstance().addMessage("panelContenedorSeguridad", new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        mensajes, null));
                this.limpiarPantalla();
            } else {
                mensajes = "Error al amacenar Reporte FRIARH: " + rvo.getMensaje();
                FacesContext.getCurrentInstance().addMessage("panelContenedorSeguridad", new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        mensajes, null));
            }
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ReactivoVigilanciaWeb/faces/pages/reporteSeguridad.xhtml");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ReporteSeguridadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnCancelar() {
        this.limpiarPantalla();
    }

    private void limpiarPantalla() {
        try {
            setReporte_friarh_activo_formulario(false);
            setReporte_friarh_activo(false);
            setMonitoreo_activo(false);
            setMonitoreo_activo_formulario(false);
            fechaReporte = this.obtenerFecha();
            friarhVO = new FriarhVO();
            sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", new Locale("es_ES"));
            friarhVO.setFechaRadicado(new Date());
            friarh01 = new ArrayList<>();
            friarh02 = new ArrayList<>();
            mostrarOtroEstado = true;
            mostrarOtroNotificante = true;
            mostrarNombreAgencia = true;
            mostrarCuantosDesc = true;
            lstReportesFRIARH = reactivoBO.obtenerReportesFRIARH();
            lstReactivoMonitoreo = reactivoBO.obtenerLstReactivoMonitoreo();
            if (usuariosVO.getiDRolUsuario() != 1) {
                //btnNuevoReporte();
                reporte_friarh_activo_formulario = false;
                monitoreo_activo = false;
                monitoreo_activo_formulario = false;
            }
        } catch (Exception ex) {
            logBeanWebReactivo.log(Priority.ERROR, null, ex);
        }
    }

    public void cambiarCodigosCausa(ValueChangeEvent event) {
        String valor = (String) event.getNewValue();
        System.out.println("--------------------------------");
        System.out.println("ID CODIGO CAUSA: " + valor);
        System.out.println("--------------------------------");

        this.codigoCausaPV = valor;
        this.descripcionCausaProbableV = ejbServicioRemotoReporteMasivoTrimestralPantalla.obtenerDescripcionCausaProbable(valor);

        System.out.println("codigoCausaPV: " + this.codigoCausaPV);
        System.out.println("descripcionCausaProbableV: " + this.descripcionCausaProbableV);
    }

    public void cambiarCodigosCausa(String valor_causa) {
        try {
            String valor = valor_causa;
            System.out.println("--------------------------------");
            System.out.println("ID CODIGO CAUSA: " + valor);
            System.out.println("--------------------------------");

            this.codigoCausaPV = valor;
            this.descripcionCausaProbableV = ejbServicioRemotoReporteMasivoTrimestralPantalla.obtenerDescripcionCausaProbable(valor);

            System.out.println("codigoCausaPV: " + this.codigoCausaPV);
            System.out.println("descripcionCausaProbableV: " + this.descripcionCausaProbableV);
        } catch (Exception e) {
            System.out.println("cambiarCodigosCausa, CAtch exception =  " + e);
        }
    }

    public String getId_monitoreo_temporal() {
        return id_monitoreo_temporal;
    }

    public void setId_monitoreo_temporal(String id_monitoreo_temporal) {
        this.id_monitoreo_temporal = id_monitoreo_temporal;
    }

    public String getReactivoMonitoreo_fechaIngreso_temporal() {
        String x = new Date().toString();
        return x;
    }

    public void setReactivoMonitoreo_fechaIngreso_temporal(String reactivoMonitoreo_fechaIngreso_temporal) {
        this.reactivoMonitoreo_fechaIngreso_temporal = reactivoMonitoreo_fechaIngreso_temporal;
    }

    public boolean isMonitoreo_activo() {
        return monitoreo_activo;
    }

    public boolean isUsuarioInvima() {
        if (usuariosVO.getiDRolUsuario() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isMostrar_row_agencia_sanitaria() {
        try {
            if (reactivoMonitoreo.getFuente() == 4) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getLstReactivoMonitoreo() {
        return lstReactivoMonitoreo;
    }

    public void setLstReactivoMonitoreo(List<String> lstReactivoMonitoreo) {
        this.lstReactivoMonitoreo = lstReactivoMonitoreo;
    }

    public void setMonitoreo_activo(boolean monitoreo_activo) {
        this.monitoreo_activo = monitoreo_activo;
    }

    public ReactivoMonitoreo getReactivoMonitoreo() {
        return reactivoMonitoreo;
    }

    public void setReactivoMonitoreo(ReactivoMonitoreo reactivoMonitoreo) {
        this.reactivoMonitoreo = reactivoMonitoreo;
    }

    public String getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(String fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getCdgNotificacion() {
        return cdgNotificacion;
    }

    public void setCdgNotificacion(String cdgNotificacion) {
        this.cdgNotificacion = cdgNotificacion;
    }

    public String getCdgNotificante() {
        return cdgNotificante;
    }

    public void setCdgNotificante(String cdgNotificante) {
        this.cdgNotificante = cdgNotificante;
    }

    public String getCdgFuente() {
        return cdgFuente;
    }

    public void setCdgFuente(String cdgFuente) {
        this.cdgFuente = cdgFuente;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPersonaReporta() {
        return personaReporta;
    }

    public void setPersonaReporta(String personaReporta) {
        this.personaReporta = personaReporta;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UsuariosVO getUsuariosVO() {
        return usuariosVO;
    }

    public void setUsuariosVO(UsuariosVO usuariosVO) {
        this.usuariosVO = usuariosVO;
    }

    public Date getFechaHurto() {
        return fechaHurto;
    }

    public void setFechaHurto(Date fechaHurto) {
        this.fechaHurto = fechaHurto;
    }

    public FriarhVO getFriarhVO() {
        return friarhVO;
    }

    public void setFriarhVO(FriarhVO friarhVO) {
        this.friarhVO = friarhVO;
    }

    public List<NotificacionVO> getNotificacionVOs() {
        return notificacionVOs;
    }

    public void setNotificacionVOs(List<NotificacionVO> notificacionVOs) {
        this.notificacionVOs = notificacionVOs;
    }

    public List<NotificanteVO> getLstNotificante() {
        return lstNotificante;
    }

    public void setLstNotificante(List<NotificanteVO> lstNotificante) {
        this.lstNotificante = lstNotificante;
    }

    public List<Funcionario_sin> getLstFuncionarios() {
        return lstFuncionarios;
    }

    public void setLstFuncionarios(List<Funcionario_sin> lstFuncionarios) {
        this.lstFuncionarios = lstFuncionarios;
    }

    public List<TecnoTipoalertas> getLstTecnoTipoalertas() {
        return lstTecnoTipoalertas;
    }

    public void setLstTecnoTipoalertas(List<TecnoTipoalertas> lstTecnoTipoalertas) {
        this.lstTecnoTipoalertas = lstTecnoTipoalertas;
    }

    public List<FuenteVO> getLstFuentes() {
        return lstFuentes;
    }

    public void setLstFuentes(List<FuenteVO> lstFuentes) {
        this.lstFuentes = lstFuentes;
    }

    public List<String> getLstAplica() {
        lstAplica = new ArrayList<String>();
        lstAplica.add("SI");
        lstAplica.add("NO");
        return lstAplica;
    }

    public void setLstAplica(List<String> lstAplica) {
        this.lstAplica = lstAplica;
    }

    public List<String> getLstReportesFRIARH() {
        return lstReportesFRIARH;
    }

    public void setLstReportesFRIARH(List<String> lstReportesFRIARH) {
        this.lstReportesFRIARH = lstReportesFRIARH;
    }

    public List<EstadoVO> getLstEstados() {
        return lstEstados;
    }

    public void setLstEstados(List<EstadoVO> lstEstados) {
        this.lstEstados = lstEstados;
    }

    public List<DepartamentoVO> getLstDpto() {
        return lstDpto;
    }

    public void setLstDpto(List<DepartamentoVO> lstDpto) {
        this.lstDpto = lstDpto;
    }

    public List<CiudadesVO> getLstCiudades() {
        return lstCiudades;
    }

    public void setLstCiudades(List<CiudadesVO> lstCiudades) {
        this.lstCiudades = lstCiudades;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public boolean isMostrarHurto() {
        return mostrarHurto;
    }

    public void setMostrarHurto(boolean mostrarHurto) {
        this.mostrarHurto = mostrarHurto;
    }

    public List<Friarh01VO> getFriarh01() {
        return friarh01;
    }

    public void setFriarh01(List<Friarh01VO> friarh01) {
        this.friarh01 = friarh01;
    }

    public List<Friarh02VO> getFriarh02() {
        return friarh02;
    }

    public void setFriarh02(List<Friarh02VO> friarh02) {
        this.friarh02 = friarh02;
    }

    public Friarh01VO getFriarh01VO() {
        return friarh01VO;
    }

    public void setFriarh01VO(Friarh01VO friarh01VO) {
        friarh01VO = new Friarh01VO();
        friarh01.add(friarh01VO);
        this.friarh01VO = friarh01VO;
    }

    public Friarh02VO getFriarh02VO() {
        return friarh02VO;
    }

    public void setFriarh02VO(Friarh02VO friarh02VO) {
        friarh02VO = new Friarh02VO();
        friarh02.add(friarh02VO);
        this.friarh02VO = friarh02VO;
    }

    public void removeFriarh01(Friarh01VO friarh01VO) {
        friarh01.remove(friarh01VO);
    }

    public void removeFriarh02(Friarh02VO friarh02VO) {
        friarh02.remove(friarh02VO);
    }

    public boolean isMostrarOtroEstado() {
        return mostrarOtroEstado;
    }

    public void setMostrarOtroEstado(boolean mostrarOtroEstado) {
        this.mostrarOtroEstado = mostrarOtroEstado;
    }

    public boolean isMostrarOtroNotificante() {
        return mostrarOtroNotificante;
    }

    public void setMostrarOtroNotificante(boolean mostrarOtroNotificante) {
        this.mostrarOtroNotificante = mostrarOtroNotificante;
    }

    public boolean isMostrarNombreAgencia() {
        return mostrarNombreAgencia;
    }

    public void setMostrarNombreAgencia(boolean mostrarNombreAgencia) {
        this.mostrarNombreAgencia = mostrarNombreAgencia;
    }

    public boolean isMostrarCuantosDesc() {
        return mostrarCuantosDesc;
    }

    public void setMostrarCuantosDesc(boolean mostrarCuantosDesc) {
        this.mostrarCuantosDesc = mostrarCuantosDesc;
    }

    public List<DepartamentoVO> getLstDptoTabla() {
        return lstDptoTabla;
    }

    public void setLstDptoTabla(List<DepartamentoVO> lstDptoTabla) {
        this.lstDptoTabla = lstDptoTabla;
    }

    public List<CiudadesVO> getLstMpioTabla() {
        return lstMpioTabla;
    }

    public void setLstMpioTabla(List<CiudadesVO> lstMpioTabla) {
        this.lstMpioTabla = lstMpioTabla;
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public boolean isEnableRegistrarVisita() {
        return enableRegistrarVisita;
    }

    public void setEnableRegistrarVisita(boolean enableRegistrarVisita) {
        this.enableRegistrarVisita = enableRegistrarVisita;
    }
    
    
}