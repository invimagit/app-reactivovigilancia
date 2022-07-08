/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.VO;

import co.gov.invima.entities.ReactivoEventos;
import co.gov.invima.entities.ReactivoGestionrealizada;
import co.gov.invima.entities.ReactivoInstitucion;
import co.gov.invima.entities.ReactivoPaciente;
import co.gov.invima.entities.ReactivoProducto;
import co.gov.invima.entities.ReactivoReportante;
import co.gov.invima.entities.ReactivoSeguimiento;
import java.io.Serializable;

/**
 *
 * @author mgualdrond
 */
public class EfectosAdversosVO implements Serializable {
    
    private InstitucionVO institucionVO;
    
    private PacienteVO pacienteVO;
    
    private ProductoVO productoVO;
    
    private EventosVO eventosVO;
    
    private GestionRealizadaVO gestionRealizadaVO;
    
    private ReportanteVO reportanteVO;
    
    private String usuario;
    
    private ReactivoSeguimiento seguimiento;
    
    public EfectosAdversosVO (){
        institucionVO = new InstitucionVO();
        pacienteVO = new PacienteVO();
        productoVO = new ProductoVO();
        eventosVO = new EventosVO();
        this.gestionRealizadaVO = new GestionRealizadaVO();
        this.reportanteVO = new ReportanteVO();
    }

    public ReactivoSeguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(ReactivoSeguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }

    public InstitucionVO getInstitucionVO() {
        return institucionVO;
    }

    public void setInstitucionVO(InstitucionVO institucionVO) {
        this.institucionVO = institucionVO;
    }
    
    public void setInstitucionVO(ReactivoInstitucion reactivoInstitucion) {
        //this.institucionVO = institucionVO;
        institucionVO = new InstitucionVO();
        institucionVO.setCodDepart(reactivoInstitucion.getCodDepart());
        institucionVO.setCodMun(reactivoInstitucion.getCodMun());
        institucionVO.setComplejidad(reactivoInstitucion.getComplejidad());
        institucionVO.setDireccion(reactivoInstitucion.getDireccion());
        institucionVO.setEmail(reactivoInstitucion.getEmail());
        institucionVO.setFechaReporte(reactivoInstitucion.getFechaReporte());
        institucionVO.setIdentificacion(reactivoInstitucion.getIdentificacion());
        institucionVO.setInstitucion(reactivoInstitucion.getInstitucion());
        institucionVO.setNaturaleza(reactivoInstitucion.getNaturaleza());
        institucionVO.setReporte(reactivoInstitucion.getReporte());
        institucionVO.setTelefono(reactivoInstitucion.getTelefono());
    }
    
    

    public PacienteVO getPacienteVO() {
        return pacienteVO;
    }

    public void setPacienteVO(PacienteVO pacienteVO) {
        this.pacienteVO = pacienteVO;
    }
    
    public void setPacienteVO(ReactivoPaciente reactivoPaciente) {
        pacienteVO = new PacienteVO();
        pacienteVO.setDireccPaciente(reactivoPaciente.getDireccPaciente());
        pacienteVO.setEdad(reactivoPaciente.getEdad());
        pacienteVO.setEdadEn(reactivoPaciente.getEdadEn());
        try{
        pacienteVO.setGenero(reactivoPaciente.getGenero().toCharArray()[0]);
        }catch(Exception e){}
        pacienteVO.setIdentifi(reactivoPaciente.getIdentifi());
        pacienteVO.setNombrePaciente(reactivoPaciente.getNombrePaciente());
        pacienteVO.setReporte(reactivoPaciente.getReporte());
        pacienteVO.setTelefoPaciente(reactivoPaciente.getTelefoPaciente());
        pacienteVO.setTipidentifi(reactivoPaciente.getTipidentifi());
    }
    
    

    public ProductoVO getProductoVO() {
        return productoVO;
    }

    public void setProductoVO(ProductoVO productoVO) {
        this.productoVO = productoVO;
    }
    
    public void setProductoVO(ReactivoProducto reactivoProducto) {
        productoVO = new ProductoVO();
        productoVO.setAreaFunciona(reactivoProducto.getAreaFunciona());
        productoVO.setCadenaFrio(reactivoProducto.getCadenaFrio());
        productoVO.setCertiAnalisis(reactivoProducto.getCertiAnalisis());
        productoVO.setCondiciAlmacen(reactivoProducto.getCondiciAlmacen());
        productoVO.setDistriUsuario(reactivoProducto.getDistriUsuario());
        productoVO.setFechaVenci(reactivoProducto.getFechaVenci());
        productoVO.setLote(reactivoProducto.getLote());
        productoVO.setNombreReactivo(reactivoProducto.getNombreReactivo());
        productoVO.setNroregsan(reactivoProducto.getNroregsan());
        productoVO.setOtraArea(reactivoProducto.getOtraArea());
        productoVO.setProcedencia(reactivoProducto.getProcedencia());
        productoVO.setReporte(reactivoProducto.getReporte());
        productoVO.setTemperatura(reactivoProducto.getTemperatura());               
    }

    public EventosVO getEventosVO() {
        return eventosVO;
    }

    public void setEventosVO(EventosVO eventosVO) {
        this.eventosVO = eventosVO;
    }
    
    public void setEventosVO(ReactivoEventos reactivoEventos) {
        eventosVO = new EventosVO();
        eventosVO.setCdgDesenlace(reactivoEventos.getCdgDesenlace());
        eventosVO.setCdgProblema(reactivoEventos.getCdgProblema());
        eventosVO.setClasificacion(reactivoEventos.getClasificacion());
        eventosVO.setDescripEfecto(reactivoEventos.getDescripEfecto());
        try{
        eventosVO.setDesempeno(reactivoEventos.getDesempeno().toCharArray()[0]);
        }catch(Exception e){}
        eventosVO.setEfectoIndeseado(reactivoEventos.getEfectoIndeseado());
        eventosVO.setFechEvento(reactivoEventos.getFechEvento());
        eventosVO.setFechReporte(reactivoEventos.getFechReporte());
        eventosVO.setFechaIngreso(reactivoEventos.getFechaIngreso());
        try{
        eventosVO.setInternet(reactivoEventos.getInternet().toCharArray()[0]);
        }catch(Exception e){}
        eventosVO.setOtroDesenlace(reactivoEventos.getOtroDesenlace());
        try{
        eventosVO.setReportado(reactivoEventos.getReportado().toCharArray()[0]);
        }catch(Exception e){}
        eventosVO.setReporte(reactivoEventos.getReporte());
    }

    public GestionRealizadaVO getGestionRealizadaVO() {
        return gestionRealizadaVO;
    }

    public void setGestionRealizadaVO(GestionRealizadaVO gestionRealizadaVO) {
        this.gestionRealizadaVO = gestionRealizadaVO;
    }
    
    public void setGestionRealizadaVO(ReactivoGestionrealizada reactivoGestionrealizada) {
        this.gestionRealizadaVO = new GestionRealizadaVO();
        gestionRealizadaVO.setAcciones(reactivoGestionrealizada.getAcciones());
        gestionRealizadaVO.setAnalisisEfecto(reactivoGestionrealizada.getAnalisisEfecto());
        gestionRealizadaVO.setCausaEfecto(reactivoGestionrealizada.getCausaEfecto());
        gestionRealizadaVO.setCausaProbable(reactivoGestionrealizada.getCausaProbable());
        gestionRealizadaVO.setComercializador(reactivoGestionrealizada.getComercializador());
        gestionRealizadaVO.setDescripcionAcciones(reactivoGestionrealizada.getDescripcionAcciones());
        gestionRealizadaVO.setDescripcionCausa(reactivoGestionrealizada.getDescripcionCausa());
        gestionRealizadaVO.setDistribuidor(reactivoGestionrealizada.getDistribuidor());
        gestionRealizadaVO.setEnviadoDistriImport(reactivoGestionrealizada.getEnviadoDistriImport());
        gestionRealizadaVO.setFabricante(reactivoGestionrealizada.getFabricante());
        gestionRealizadaVO.setFechaNotificacion(reactivoGestionrealizada.getFechaNotificacion());
        gestionRealizadaVO.setGestionRiesgo(reactivoGestionrealizada.getGestionRiesgo());
        gestionRealizadaVO.setHerramientaAnalisis(reactivoGestionrealizada.getHerramientaAnalisis());
        gestionRealizadaVO.setImportador(reactivoGestionrealizada.getImportador());
        gestionRealizadaVO.setOtraHerramienta(reactivoGestionrealizada.getOtraHerramienta());
        gestionRealizadaVO.setReporte(reactivoGestionrealizada.getReporte());
    }

    public ReportanteVO getReportanteVO() {
        return reportanteVO;
    }

    public void setReportanteVO(ReportanteVO reportanteVO) {
        this.reportanteVO = reportanteVO;
    }
    public void setReportanteVO(ReactivoReportante reactivoReportante) {
        this.reportanteVO = new ReportanteVO();
        reportanteVO.setAreaPertenece(reactivoReportante.getAreaPertenece());
        reportanteVO.setCargo(reactivoReportante.getCargo());
        reportanteVO.setCodDepart(reactivoReportante.getCodDepart());
        reportanteVO.setCodMun(reactivoReportante.getCodMun());
        reportanteVO.setDireccion(reactivoReportante.getDireccion());
        reportanteVO.setDivulgacion(reactivoReportante.getDivulgacion());
        reportanteVO.setEmail(reactivoReportante.getEmail());
        reportanteVO.setFechaNotificacion(reactivoReportante.getFechaNotificacion());
        reportanteVO.setIdentificacion(reactivoReportante.getIdentificacion());
        reportanteVO.setMovil(reactivoReportante.getMovil());
        reportanteVO.setNombresApellidos(reactivoReportante.getNombresApellidos());
        reportanteVO.setPais(reactivoReportante.getPais());
        reportanteVO.setProfesion(reactivoReportante.getProfesion());
        reportanteVO.setReporte(reactivoReportante.getReporte());
        reportanteVO.setTelefono(reactivoReportante.getTelefono());
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
    
}
