/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.beans;

import co.gov.invima.BO.ReactivoBO;
import co.gov.invima.VO.AreasVO;
import co.gov.invima.VO.CiudadesVO;
import co.gov.invima.VO.DepartamentoVO;
import co.gov.invima.VO.DesenlacesVO;
import co.gov.invima.VO.EfectosAdversosVO;
import co.gov.invima.VO.EventosVO;
import co.gov.invima.VO.GestionRealizadaVO;
import co.gov.invima.VO.InstitucionVO;
import co.gov.invima.VO.PacienteVO;
import co.gov.invima.VO.ProblemasVO;
import co.gov.invima.VO.ProductoVO;
import co.gov.invima.VO.ProfesionesVO;
import co.gov.invima.VO.ReportanteVO;
import co.gov.invima.VO.RespuestaVO;
import co.gov.invima.VO.UsuariosVO;
import co.gov.invima.entities.ReactivoSeguimiento;
import co.gov.invima.negocio.ServicioReporteTrimestralRemote;
import co.gov.invima.service.ServiceLocator;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mgualdrond
 */
@Named(value = "reporteEfectosBean")
@SessionScoped
public class ReporteEfectosBean implements Serializable {

    private final HttpSession session;
    private final ReactivoBO reactivoBO;
    private final UsuariosVO usuariosVO;
    private EfectosAdversosVO adversosVO;
    private List<DepartamentoVO> lstDepartamentos;
    
    private List<String> lstReportes;
    private String Reporte_seleccionado_temporal;
    
    private List<CiudadesVO> lstCiudades;
    private List<AreasVO> lstAreas;
    private List<ProblemasVO> lstProblemas;
    private List<DesenlacesVO> lstDesenlace;
    private List<ProfesionesVO> lstProfesiones;
    //************************************************************
    //************************************************************
    //************************************************************
    private ArrayList listaCausasProbables;
    private String codigoCausaPV;
    private String descripcionCausaProbableV;
    //************************************************************
    //************************************************************
    //************************************************************
    private String mensajes;
    private boolean otraArea;
    private boolean desenlaceActiva;
    private boolean otroDesenlace;
    private boolean otraHerramienta;
    private boolean inicioAcciones;
    private boolean mostrar_formulario = false;
    
    
    
    @EJB
    private ServicioReporteTrimestralRemote ejbServicioRemotoReporteMasivoTrimestralPantalla;
    //*************************************************
    private ReactivoSeguimiento reactivoSeguimiento;
    private List<ReactivoSeguimiento> ltSeguimientos;
    //************************************************************

    public ReactivoSeguimiento getReactivoSeguimiento() {
        return reactivoSeguimiento;
    }

    public void setReactivoSeguimiento(ReactivoSeguimiento reactivoSeguimiento) {
        this.reactivoSeguimiento = reactivoSeguimiento;
    }

    public List<ReactivoSeguimiento> getLtSeguimientos() {
        return ltSeguimientos;
    }

    public void setLtSeguimientos(List<ReactivoSeguimiento> ltSeguimientos) {
        this.ltSeguimientos = ltSeguimientos;
    }
    

    /**
     * Creates a new instance of ReporteEfectosBean
     */
    public ReporteEfectosBean() 
    {
        //*****************************************************************************************************
        //*****************************************************************************************************
        ServiceLocator service = new ServiceLocator();
        ejbServicioRemotoReporteMasivoTrimestralPantalla = service.getEjbRemotoReactivoReporteTrimestral();
        //*****************************************************************************************************
        //*****************************************************************************************************
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        reactivoBO = new ReactivoBO();
        usuariosVO = (UsuariosVO) session.getAttribute("usuario");
        adversosVO = this.iniciarEventos();
        lstDepartamentos = reactivoBO.obtenerDptos();
        setLstReportes(reactivoBO.obtenerReportes());
        lstCiudades = new ArrayList<>();
        lstAreas = reactivoBO.obtenerCatalogoAreas();
        otraArea = true;
        lstProblemas = reactivoBO.obtenerCatalogoProblemas();
        lstDesenlace = reactivoBO.obtenerCatalogoDesenlace();
        desenlaceActiva = true;
        otroDesenlace = true;
        otraHerramienta = true;
        inicioAcciones = true;
        lstProfesiones = reactivoBO.obtenerProfesiones();
        //*****************************************************************************************************
        //*****************************************************************************************************
        listaCausasProbables = armarItemsCombo(getEjbServicioRemotoReporteMasivoTrimestralPantalla().obtenerListaCausasProbables());
        //*****************************************************************************************************
        reactivoSeguimiento = new ReactivoSeguimiento();
        reactivoSeguimiento.setUsuario(usuariosVO.getUsuario());
        reactivoSeguimiento.setFechaIngreso(new Date());
        changeClasificacion(adversosVO.getEventosVO().getClasificacion());
        if(usuariosVO.getiDRolUsuario()!=1)
            {
                System.out.println ("ReporteEfectosBean() , usuariosVO.getiDRolUsuario()!=1 ");
                mostrar_formulario = true;
                btnNuevoMonitoreo();
            }
        
        
        //*****************************************************************************************************
    }
    
    public void btnNuevoReporte_usuario_diferente_invima() 
    {
        if(usuariosVO.getiDRolUsuario()!=1)
            {
                btnNuevoMonitoreo();
            }
    }

    public boolean isMostrar_formulario() {
        return mostrar_formulario;
    }

    public void setMostrar_formulario(boolean mostrar_formulario) {
        this.mostrar_formulario = mostrar_formulario;
    }

    public List<String> getLstReportes() {
        return lstReportes;
    }

    public void setLstReportes(List<String> lstReportes) {
        this.lstReportes = lstReportes;
    }

    public String getReporte_seleccionado_temporal() {
        return Reporte_seleccionado_temporal;
    }

    public void setReporte_seleccionado_temporal(String Reporte_seleccionado_temporal) {
        this.Reporte_seleccionado_temporal = Reporte_seleccionado_temporal;
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public EfectosAdversosVO getAdversosVO() {
        return adversosVO;
    }

    public void setAdversosVO(EfectosAdversosVO adversosVO) {
        this.adversosVO = adversosVO;
    }

    public List<DepartamentoVO> getLstDepartamentos() {
        return lstDepartamentos;
    }

    public void setLstDepartamentos(List<DepartamentoVO> lstDepartamentos) {
        this.lstDepartamentos = lstDepartamentos;
    }

    public List<CiudadesVO> getLstCiudades() {
        return lstCiudades;
    }

    public void setLstCiudades(List<CiudadesVO> lstCiudades) {
        this.lstCiudades = lstCiudades;
    }

    public List<AreasVO> getLstAreas() {
        return lstAreas;
    }

    public void setLstAreas(List<AreasVO> lstAreas) {
        this.lstAreas = lstAreas;
    }

    public boolean isOtraArea() {
        return otraArea;
    }

    public void setOtraArea(boolean otraArea) {
        this.otraArea = otraArea;
    }

    public List<ProblemasVO> getLstProblemas() {
        return lstProblemas;
    }

    public void setLstProblemas(List<ProblemasVO> lstProblemas) {
        this.lstProblemas = lstProblemas;
    }

    public List<DesenlacesVO> getLstDesenlace() {
        return lstDesenlace;
    }

    public void setLstDesenlace(List<DesenlacesVO> lstDesenlace) {
        this.lstDesenlace = lstDesenlace;
    }

    public boolean isDesenlaceActiva() {
        return desenlaceActiva;
    }

    public void setDesenlaceActiva(boolean desenlaceActiva) {
        this.desenlaceActiva = desenlaceActiva;
    }

    public boolean isOtroDesenlace() {
        return otroDesenlace;
    }

    public void setOtroDesenlace(boolean otroDesenlace) {
        this.otroDesenlace = otroDesenlace;
    }

    public boolean isOtraHerramienta() {
        return otraHerramienta;
    }

    public void setOtraHerramienta(boolean otraHerramienta) {
        this.otraHerramienta = otraHerramienta;
    }

    public boolean isInicioAcciones() {
        return inicioAcciones;
    }

    public void setInicioAcciones(boolean inicioAcciones) {
        this.inicioAcciones = inicioAcciones;
    }

    public List<ProfesionesVO> getLstProfesiones() {
        return lstProfesiones;
    }

    public void setLstProfesiones(List<ProfesionesVO> lstProfesiones) {
        this.lstProfesiones = lstProfesiones;
    }

    public void changeDepartamento(ValueChangeEvent event) {
        String valor = (String) event.getNewValue();
        if (valor != null && !valor.isEmpty()) {
            lstCiudades = new ArrayList<>();
            for (DepartamentoVO dvo : lstDepartamentos) {
                if (dvo.getCodDepart().equals(valor)) {
                    lstCiudades = dvo.getCiudadesVOs();
                    break;
                }
            }
        }
    }
    
    public void changeDepartamento_al_cargar_reporte(String codigo_departamento) {
        String valor = codigo_departamento;
        if (valor != null && !valor.isEmpty()) {
            lstCiudades = new ArrayList<>();
            for (DepartamentoVO dvo : lstDepartamentos) {
                if (dvo.getCodDepart().equals(valor)) {
                    lstCiudades = dvo.getCiudadesVOs();
                    break;
                }
            }
        }
    }

    private EfectosAdversosVO iniciarEventos() {
        EfectosAdversosVO adversosVO1 = new EfectosAdversosVO();
        InstitucionVO institucionVO = new InstitucionVO();
        adversosVO1.setInstitucionVO(institucionVO);
        PacienteVO pacienteVO = new PacienteVO();
        adversosVO1.setPacienteVO(pacienteVO);
        ProductoVO productoVO = new ProductoVO();
        adversosVO1.setProductoVO(productoVO);
        EventosVO eventosVO = new EventosVO();
        adversosVO1.setEventosVO(eventosVO);
        GestionRealizadaVO gestionRealizadaVO = new GestionRealizadaVO();
        adversosVO1.setGestionRealizadaVO(gestionRealizadaVO);
        ReportanteVO reportanteVO = new ReportanteVO();
        adversosVO1.setReportanteVO(reportanteVO);
        adversosVO1.getInstitucionVO().setFechaReporte(new Date());
        //pasar los datos del reportante
        if (usuariosVO != null) {
            //adversosVO1.getReportanteVO().setAreaPertenece("");
            adversosVO1.getReportanteVO().setCargo(usuariosVO.getCargoPersona());
            adversosVO1.getReportanteVO().setCodDepart(usuariosVO.getNombreDpto());
            adversosVO1.getReportanteVO().setCodMun(usuariosVO.getNombreMpio());
            adversosVO1.getReportanteVO().setDireccion(usuariosVO.getDireccionEmpresa());
            //adversosVO1.getReportanteVO().setDivulgacion(null);
            adversosVO1.getReportanteVO().setEmail(usuariosVO.getEmailPersona());
            adversosVO1.getReportanteVO().setFechaNotificacion(adversosVO1.getInstitucionVO().getFechaReporte());
            adversosVO1.getReportanteVO().setIdentificacion(String.valueOf(usuariosVO.getIdentificacionPersona()));
            //adversosVO1.getReportanteVO().setMovil("");
            adversosVO1.getReportanteVO().setNombresApellidos(usuariosVO.getNombrePersona());
            adversosVO1.getReportanteVO().setPais(usuariosVO.getNombrePais());
            //adversosVO1.getReportanteVO().setProfesion(null);
            //adversosVO1.getReportanteVO().setReporte(null);
            adversosVO1.getReportanteVO().setTelefono(usuariosVO.getTelefonoPersona());
        }

        return adversosVO1;
    }

    public void changeArea(ValueChangeEvent event) {
        String valor = event.getNewValue().toString();
        if (!valor.isEmpty()) {
            otraArea = !valor.equals("5");
        }
    }

    public void changeClasificacion(ValueChangeEvent event) {
        String valor = event.getNewValue().toString();
        changeClasificacion(valor);
    }
    
    public void changeClasificacion(String valorCampo) {
        String valor = valorCampo;
        if (!valor.isEmpty()) {
            desenlaceActiva = valor.equals("Incidente") || valor.equals("-1");
            if (desenlaceActiva) {
                adversosVO.getEventosVO().setCdgDesenlace(0);
            }
            if (!otroDesenlace) {
                adversosVO.getEventosVO().setOtroDesenlace("");
                otroDesenlace = true;
            }
        }
    }

    public void changeDesenlace(ValueChangeEvent event) {
        String valor = event.getNewValue().toString();
        if (!valor.isEmpty()) {
            otroDesenlace = !valor.equals("9") || valor.equals("-1");
        }
    }

    public void changeHerramienta(ValueChangeEvent event) {
        String valor = (String) event.getNewValue();
        if (!valor.isEmpty()) {
            otraHerramienta = !valor.equals("Otra");
        }
    }

    public void changeAcciones(ValueChangeEvent event) {
        String valor = (String) event.getNewValue();
        if (!valor.isEmpty()) {
            inicioAcciones = !valor.equals("SI");
        }
    }
    
    public void btnNuevoMonitoreo() 
    {
        try {
            setMostrar_formulario(true);
            adversosVO = new EfectosAdversosVO();
            adversosVO.getInstitucionVO().setFechaReporte(new Date());
            adversosVO = iniciarEventos() ;
            reactivoSeguimiento.setObservacion("");
            Reporte_seleccionado_temporal = "";
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ReactivoVigilanciaWeb/faces/pages/reporteEfectos.xhtml");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ReporteSeguridadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void btnCargarReporte() 
    {
        try {
            //reactivoMonitoreo = new ReactivoMonitoreo();
            setMostrar_formulario(true);
            System.out.println ("btnCargarReporte, id_reporte = "+getReporte_seleccionado_temporal());
            try{adversosVO = reactivoBO.obtenerAdversosVO(getReporte_seleccionado_temporal());}catch(Exception e){java.util.logging.Logger.getLogger(ReporteSeguridadBean.class.getName()).log(Level.INFO, "ReporteEfectosBean.btnCargarReporte - linea obtenerAdversosVO", e.getMessage());}
            try{setLtSeguimientos(reactivoBO.obtenerSeguimientosFRIARH(getReporte_seleccionado_temporal(),"adversos"));}catch(Exception e){java.util.logging.Logger.getLogger(ReporteSeguridadBean.class.getName()).log(Level.INFO, "ReporteEfectosBean.btnCargarReporte - linea setLtSeguimientos", e.getMessage());}
            try{changeDepartamento_al_cargar_reporte(adversosVO.getInstitucionVO().getCodDepart());}catch(Exception e){java.util.logging.Logger.getLogger(ReporteSeguridadBean.class.getName()).log(Level.INFO, "ReporteEfectosBean.btnCargarReporte - linea changeDepartamento_al_cargar_reporte", e.getMessage());}
            try{cambiarCodigosCausa (adversosVO.getGestionRealizadaVO().getCausaProbable());}catch(Exception e){java.util.logging.Logger.getLogger(ReporteSeguridadBean.class.getName()).log(Level.INFO, "ReporteEfectosBean.btnCargarReporte - linea cambiarCodigosCausa", e.getMessage());}
            changeClasificacion(adversosVO.getEventosVO().getClasificacion());
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ReactivoVigilanciaWeb/faces/pages/reporteEfectos.xhtml");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ReporteSeguridadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnAceptar() {
        
        System.out.println ("btnAceptar(), adversosVO.getEventosVO().getFechEvento() = "+adversosVO.getEventosVO().getFechEvento().toString());
        System.out.println ("btnAceptar(), adversosVO.getEventosVO().getFechEvento() = "+adversosVO.getEventosVO().getFechEvento().toString());
        
        

        if (adversosVO.getEventosVO().getFechEvento().after(adversosVO.getEventosVO().getFechReporte())
                || adversosVO.getEventosVO().getFechEvento().after(adversosVO.getInstitucionVO().getFechaReporte())) {
            //Mensaje que la fecha no puede ser superior a la actual ni a la del reporte
            mensajes = "La fecha de Ocurrencia debe ser menor o igual ala fecha de Reporte";
            FacesContext.getCurrentInstance().addMessage("panelEfectos", new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    mensajes, null));
        } else {
            //Validar otra fecha
            if (adversosVO.getEventosVO().getFechReporte().after(adversosVO.getInstitucionVO().getFechaReporte())) {
                //Mensaje de fecha
                mensajes = "La fecha de Reporte debe ser menor o igual ala fecha de Notificación";
                FacesContext.getCurrentInstance().addMessage("panelEfectos", new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        mensajes, null));
            } else {
                //guardar
                
                //****************************************************************************
                //****************************************************************************
                //****************************************************************************
                //Ajuste de datos
                /*if (adversosVO.getGestionRealizadaVO().getCausaEfecto().equals("SI"))
                {
                    System.out.println ("CAMBIANDO DE SI A 1");
                    adversosVO.getGestionRealizadaVO().setCausaEfecto("1");
                }
                else
                {
                    System.out.println ("CAMBIANDO DE NO A 0");
                    adversosVO.getGestionRealizadaVO().setCausaEfecto("0");
                }*/
                //****************************************************************************
                //****************************************************************************
                System.out.println ("*********************************************************");
                System.out.println ("*********************************************************");
                System.out.println ("CAUSA EFECTO PARA EL REGISTRO: " + adversosVO.getGestionRealizadaVO().getCausaEfecto());
                System.out.println ("*********************************************************");
                System.out.println ("*********************************************************");
                //****************************************************************************
                //****************************************************************************
                //****************************************************************************
                adversosVO.setSeguimiento(reactivoSeguimiento);
                adversosVO.getEventosVO().setFechaIngreso(adversosVO.getInstitucionVO().getFechaReporte());
                adversosVO.getReportanteVO().setCodDepart(usuariosVO.getCodDepart());
                adversosVO.getReportanteVO().setCodMun(usuariosVO.getCodMun());
                adversosVO.getReportanteVO().setPais(usuariosVO.getCdgPais());
                adversosVO.setUsuario(usuariosVO.getUsuario());
                RespuestaVO rvo = reactivoBO.guardarEventos(adversosVO);
                if (rvo.getStatus().equals("OK")) {
                    //operacion realizada
                    mensajes = "Reporte guardado con éxito, No. : " + rvo.getMensajeDetallado();
                    FacesContext.getCurrentInstance().addMessage("panelEfectos", new FacesMessage(FacesMessage.SEVERITY_INFO,
                            mensajes, null));
                    this.limpiarPantalla();
                } else {
                    //operacion no realizada
                    mensajes = "Error al amacenar Reporte: " + rvo.getMensaje();
                    FacesContext.getCurrentInstance().addMessage("panelEfectos", new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            mensajes, null));
                }
            }
        }
    }

    public void btnCancelar() {
        this.limpiarPantalla();
    }

    private void limpiarPantalla() {
        adversosVO = this.iniciarEventos();
        lstDepartamentos = reactivoBO.obtenerDptos();
        
        lstCiudades = new ArrayList<>();
        lstAreas = reactivoBO.obtenerCatalogoAreas();
        otraArea = true;
        lstProblemas = reactivoBO.obtenerCatalogoProblemas();
        lstDesenlace = reactivoBO.obtenerCatalogoDesenlace();
        desenlaceActiva = true;
        otroDesenlace = true;
        otraHerramienta = true;
        inicioAcciones = true;
        lstProfesiones = reactivoBO.obtenerProfesiones();
        setMostrar_formulario(false);
        if(usuariosVO.getiDRolUsuario()!=1)
            {
                //btnNuevoMonitoreo();
            }
        reactivoSeguimiento = new ReactivoSeguimiento();
        reactivoSeguimiento.setUsuario(usuariosVO.getUsuario());
        reactivoSeguimiento.setFechaIngreso(new Date());
    }
    
    public boolean isUsuarioInvima() {
        if(usuariosVO.getiDRolUsuario()==1)
        {
            return true;
        }
        else
        {
           return false; 
        }
    }
    //******************************************************
    //******************************************************
    //******************************************************
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
    //******************************************************
    //******************************************************
    //******************************************************
    public void cambiarCodigosCausa (ValueChangeEvent event) 
    {
        String valor = (String) event.getNewValue();
        System.out.println ("--------------------------------");
        System.out.println ("ID CODIGO CAUSA: " + valor);
        System.out.println ("--------------------------------");
        
        this.codigoCausaPV = valor;
        this.descripcionCausaProbableV = ejbServicioRemotoReporteMasivoTrimestralPantalla.obtenerDescripcionCausaProbable(valor);
        
        System.out.println ("codigoCausaPV: " + this.codigoCausaPV);
        System.out.println ("descripcionCausaProbableV: " + this.descripcionCausaProbableV);
    }
    
    public void cambiarCodigosCausa (String valor_causa) 
    {
        String valor = valor_causa;
        System.out.println ("--------------------------------");
        System.out.println ("ID CODIGO CAUSA: " + valor);
        System.out.println ("--------------------------------");
        
        this.codigoCausaPV = valor;
        this.descripcionCausaProbableV = ejbServicioRemotoReporteMasivoTrimestralPantalla.obtenerDescripcionCausaProbable(valor);
        
        System.out.println ("codigoCausaPV: " + this.codigoCausaPV);
        System.out.println ("descripcionCausaProbableV: " + this.descripcionCausaProbableV);
    }
    //******************************************************
    //******************************************************
    //******************************************************
    //******************************************************
    //******************************************************
    //******************************************************
    /**
     * @return the ejbServicioRemotoReporteMasivoTrimestralPantalla
     */
    public ServicioReporteTrimestralRemote getEjbServicioRemotoReporteMasivoTrimestralPantalla() {
        return ejbServicioRemotoReporteMasivoTrimestralPantalla;
    }

    /**
     * @param ejbServicioRemotoReporteMasivoTrimestralPantalla the ejbServicioRemotoReporteMasivoTrimestralPantalla to set
     */
    public void setEjbServicioRemotoReporteMasivoTrimestralPantalla(ServicioReporteTrimestralRemote ejbServicioRemotoReporteMasivoTrimestralPantalla) {
        this.ejbServicioRemotoReporteMasivoTrimestralPantalla = ejbServicioRemotoReporteMasivoTrimestralPantalla;
    }
    
    /**
     * @return the listaCausasProbables
     */
    public ArrayList getListaCausasProbables() {
        return listaCausasProbables;
    }

    /**
     * @param listaCausasProbables the listaCausasProbables to set
     */
    public void setListaCausasProbables(ArrayList listaCausasProbables) {
        this.listaCausasProbables = listaCausasProbables;
    }

    /**
     * @return the session
     */
    public HttpSession getSession() {
        return session;
    }

    /**
     * @return the codigoCausaPV
     */
    public String getCodigoCausaPV() {
        return codigoCausaPV;
    }

    /**
     * @param codigoCausaPV the codigoCausaPV to set
     */
    public void setCodigoCausaPV(String codigoCausaPV) {
        this.codigoCausaPV = codigoCausaPV;
    }

    /**
     * @return the descripcionCausaProbableV
     */
    public String getDescripcionCausaProbableV() {
        return descripcionCausaProbableV;
    }

    /**
     * @param descripcionCausaProbableV the descripcionCausaProbableV to set
     */
    public void setDescripcionCausaProbableV(String descripcionCausaProbableV) {
        this.descripcionCausaProbableV = descripcionCausaProbableV;
    }
    
    

    
    
}
