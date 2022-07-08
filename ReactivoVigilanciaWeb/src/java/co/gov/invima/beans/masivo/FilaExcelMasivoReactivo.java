/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.beans.masivo;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import org.apache.log4j.Logger;
 
public class FilaExcelMasivoReactivo implements Serializable {
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private final static Logger logBeanWebReactivo = Logger.getLogger(FilaExcelMasivoReactivo.class.getName());
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private static final long serialVersionUID = 5235221L;
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private String secuencial;
    private String codigoEntidad;
    private String nombreInstitucion;
    private String departamentoInstitucion;
    private String ciudadInstitucion;
    private String direccionInstitucion;
    private String nitInstitucion;
    private String nivelComplejidadInstitucion;
    private String naturalezaInstitucion;
    private String tipIdentPaciente;
    private String identificacionPaciente;
    private String sexoPaciente;
    private String edadPaciente;
    private String edadEnPaciente;
    private String nombreReactivo;
    private String registroSanReactivo;
    private String expedienteReactivo;
    private String codUnicoReactivo;
    private String codigoTipoDiagReactivo;
    private String loteReactivo;
    private String referenciaReactivo;
    private java.util.Date fechaVenReactivo;
    private String procedenciaReactivo;
    private String requiereCadFrioReactivo;
    private String temperaturaReactivo;
    private String catReactivo;
    private String cumpleConAlmacena;
    private String realizaRecepcionReactivo;
    private String prodCertAnalisisReactivo;
    private String nombreFabReactivo;
    private String nombreImportadorReactivo;
    private String servicioFunReactivo;
    private java.util.Date fechaIncidente;
    private String descripcionIncidente;
    private String desenlaceIncidente;
    private String otroIncidente;
    private String tieneProgGestion;
    private String realizoAnalisisGestion;
    private String herramientaAnalisisGestion;
    private String otroAnalisisGestion;
    private String causaEfectoAnalisisGestion;
    private String causaProbableEfectoGestion;
    private String descripcionCausaEfectoGestion;
    private String codigoCausaEfectoGestion;
    private String inicioAccionesEfectoGestion;
    private String accionesGestion;
    private String reportoDistribGestion;
    private java.util.Date fechaReporteImportadorGestion;
    private java.util.Date fechaEnvioDistribuidorGestion;
    private String nombreReportante;
    private String profesionReportante;
    private String organizacionReportante;
    private String direccionReportante;
    private String telefonoReportante;
    private String departamentoReportante;
    private String ciudadReportante;
    private String correoReportante;
    private java.util.Date fechaNotIPSReportante;
    private String autorizaDivulgaReportante;
    private String tipoReportanteInfoReactivo;
    private String estadoReporteInfoReactivo;
    private java.util.Date fechaGET;
    private String gestionET;
    private String estadoGET;
    private java.util.Date fechaGInvima;
    private String observacionesGInvima;
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public FilaExcelMasivoReactivo()
    {
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    public FilaExcelMasivoReactivo
    (String secuencialP,
    String codigoEntidadP,
    String nombreInstitucionP,
    String departamentoInstitucionP,
    String ciudadInstitucionP,
    String direccionInstitucionP,
    String nitInstitucionP,
    String nivelComplejidadInstitucionP,
    String naturalezaInstitucionP,
    String tipIdentPacienteP,
    String identificacionPacienteP,
    String sexoPacienteP,
    String edadPacienteP,
    String edadEnPacienteP,
    String nombreReactivoP,
    String registroSanReactivoP,
    String expedienteReactivoP,
    String codUnicoReactivoP,
    String codigoTipoDiagReactivoP,
    String loteReactivoP,
    String referenciaReactivoP,
    String fechaVenReactivoP,
    String procedenciaReactivoP,
    String requiereCadFrioReactivoP,
    String temperaturaReactivoP,
    String catReactivoP,
    String cumpleConAlmacenaP,
    String realizaRecepcionReactivoP,
    String prodCertAnalisisReactivoP,
    String nombreFabReactivoP,
    String nombreImportadorReactivoP,
    String servicioFunReactivoP,
    String fechaIncidenteP,
    String descripcionIncidenteP,
    String desenlaceIncidenteP,
    String otroIncidenteP,
    String tieneProgGestionP,
    String realizoAnalisisGestionP,
    String herramientaAnalisisGestionP,
    String otroAnalisisGestionP,
    String causaEfectoAnalisisGestionP,
    String causaProbableEfectoGestionP,
    String descripcionCausaEfectoGestionP,
    String codigoCausaEfectoGestionP,
    String inicioAccionesEfectoGestionP,
    String accionesGestionP,
    String reportoDistribGestionP,
    String fechaReporteImportadorGestionP,
    String fechaEnvioDistribuidorGestionP,
    String nombreReportanteP,
    String profesionReportanteP,
    String organizacionReportanteP,
    String direccionReportanteP,
    String telefonoReportanteP,
    String departamentoReportanteP,
    String ciudadReportanteP,
    String correoReportanteP,
    String fechaNotIPSReportanteP,
    String autorizaDivulgaReportanteP,
    String tipoReportanteInfoReactivoP,
    String estadoReporteInfoReactivoP,
    String fechaGETP,
    String gestionETP,
    String estadoGETP,
    String fechaGInvimaP,
    String observacionesGInvimaP    
    )
    {
        //*********************************************************************
        //*********************************************************************
        //*********************************************************************
        /*
        System.out.println ("secuencialP = " + secuencialP);
        System.out.println ("codigoEntidadP = " + codigoEntidadP);
        System.out.println ("nombreInstitucionP = " + nombreInstitucionP);
        System.out.println ("departamentoInstitucionP = " + departamentoInstitucionP);
        System.out.println ("ciudadInstitucionP = " + ciudadInstitucionP);
        System.out.println ("direccionInstitucionP = " + direccionInstitucionP);
        System.out.println ("nitInstitucionP = " + nitInstitucionP);
        */
        //*********************************************************************
        //*********************************************************************
        //*********************************************************************
        this.secuencial = secuencialP;
        this.codigoEntidad = codigoEntidadP;
        this.nombreInstitucion = nombreInstitucionP;
        this.departamentoInstitucion = departamentoInstitucionP;
        this.ciudadInstitucion = ciudadInstitucionP;
        this.direccionInstitucion = direccionInstitucionP;
        this.nitInstitucion = nitInstitucionP;
        this.nivelComplejidadInstitucion = nivelComplejidadInstitucionP;
        this.naturalezaInstitucion = naturalezaInstitucionP;
        this.tipIdentPaciente = tipIdentPacienteP;
        this.identificacionPaciente = identificacionPacienteP;
        this.sexoPaciente = sexoPacienteP;
        this.edadPaciente = edadPacienteP;
        this.edadEnPaciente = edadEnPacienteP;
        this.nombreReactivo = nombreReactivoP;
        this.registroSanReactivo = registroSanReactivoP;
        this.expedienteReactivo = expedienteReactivoP;
        this.codUnicoReactivo = codUnicoReactivoP;
        this.codigoTipoDiagReactivo = codigoTipoDiagReactivoP;
        this.loteReactivo = loteReactivoP;
        this.referenciaReactivo = referenciaReactivoP;
        this.fechaVenReactivo = formatearFecha("this.fechaVenReactivo",fechaVenReactivoP);
        this.procedenciaReactivo = procedenciaReactivoP;
        this.requiereCadFrioReactivo = requiereCadFrioReactivoP;
        this.temperaturaReactivo = temperaturaReactivoP;
        this.catReactivo = catReactivoP;
        this.cumpleConAlmacena = cumpleConAlmacenaP;
        this.realizaRecepcionReactivo = realizaRecepcionReactivoP;
        this.prodCertAnalisisReactivo = prodCertAnalisisReactivoP;
        this.nombreFabReactivo = nombreFabReactivoP;
        this.nombreImportadorReactivo = nombreImportadorReactivoP;
        this.servicioFunReactivo = servicioFunReactivoP;
        this.fechaIncidente = formatearFecha("this.fechaIncidente",fechaIncidenteP);
        this.descripcionIncidente = descripcionIncidenteP;
        this.desenlaceIncidente = desenlaceIncidenteP;
        this.otroIncidente = otroIncidenteP;
        this.tieneProgGestion = tieneProgGestionP;
        this.realizoAnalisisGestion = realizoAnalisisGestionP;
        this.herramientaAnalisisGestion = herramientaAnalisisGestionP;
        this.otroAnalisisGestion = otroAnalisisGestionP;
        this.causaEfectoAnalisisGestion = causaEfectoAnalisisGestionP;
        this.causaProbableEfectoGestion = causaProbableEfectoGestionP;
        this.descripcionCausaEfectoGestion = descripcionCausaEfectoGestionP;
        this.codigoCausaEfectoGestion = codigoCausaEfectoGestionP;
        this.inicioAccionesEfectoGestion = inicioAccionesEfectoGestionP;
        this.accionesGestion = accionesGestionP;
        this.reportoDistribGestion = reportoDistribGestionP;
        this.fechaReporteImportadorGestion = formatearFecha("this.fechaReporteImportadorGestion",fechaReporteImportadorGestionP);
        this.fechaEnvioDistribuidorGestion = formatearFecha("this.fechaEnvioDistribuidorGestion",fechaEnvioDistribuidorGestionP);
        this.nombreReportante = nombreReportanteP;
        this.profesionReportante = profesionReportanteP;
        this.organizacionReportante = organizacionReportanteP;
        this.direccionReportante = direccionReportanteP;
        this.telefonoReportante = telefonoReportanteP;
        this.departamentoReportante = departamentoReportanteP;
        this.ciudadReportante = ciudadReportanteP;
        this.correoReportante = correoReportanteP;
        this.fechaNotIPSReportante = formatearFecha("this.fechaNotIPSReportante",fechaNotIPSReportanteP);
        this.autorizaDivulgaReportante = autorizaDivulgaReportanteP;
        this.tipoReportanteInfoReactivo = tipoReportanteInfoReactivoP;
        this.estadoReporteInfoReactivo = estadoReporteInfoReactivoP;
        //*********************************************************************
        //*********************************************************************
        //*********************************************************************
        //*********************************************************************
        //*********************************************************************
        //*********************************************************************
        //No validar secciones H e I
        if (fechaGETP != null)
        {
            this.fechaGET = formatearFecha("this.fechaGET",fechaGETP);
        }
        else
        {
            this.fechaGET = null;
        }
        //*********************************************************************
        //*********************************************************************
        //*********************************************************************
        if (gestionETP != null && gestionETP.length() > 0)
        {
            this.gestionET = gestionETP;
        }
        else
        {
            this.gestionET = null;
        }
        //*********************************************************************
        //*********************************************************************
        if (estadoGETP != null && estadoGETP.length() > 0)
        {
            this.estadoGET = estadoGETP;
        }
        else
        {
            this.estadoGET = null;
        }
        //*********************************************************************
        //*********************************************************************
        if (fechaGInvimaP != null)
        {
            this.fechaGInvima = formatearFecha("this.fechaGInvima",fechaGInvimaP);
        }
        else
        {
            this.fechaGInvima = null;
        }
        //*********************************************************************
        //*********************************************************************
        if (observacionesGInvimaP != null && observacionesGInvimaP.length() > 0)
        {
            this.observacionesGInvima = observacionesGInvimaP;
        }
        else
        {
            this.observacionesGInvima = null;
        }
        //*********************************************************************
        //*********************************************************************
        //*********************************************************************
        //*********************************************************************
        //*********************************************************************
        //*********************************************************************
        //*********************************************************************
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private java.util.Date formatearFecha (String nombreCampo, String valor)
    {
        java.util.Date fechaRetornar = null;
        String year = "";
        String mes = "";
        String dia = "";
        
        if (valor == null)
        {
            fechaRetornar = null;
        }
        else
        {
            //logBeanWebReactivo.info  ("********************************************");
            //logBeanWebReactivo.info  ("Valor de fecha enviado = " + valor + " para el campo = " + nombreCampo);
            //logBeanWebReactivo.info  ("********************************************");
            //logBeanWebReactivo.info  ("Longitud de " + valor  + " = " + valor.length());

            if (valor.length() >= 10)
            {
                if (valor.equals("01/01/1900"))
                {
                    fechaRetornar = null;
                }
                else
                {
                    if (valor.length() <= 9)
                    {
                        dia = valor.substring(0,1);
                        mes = valor.substring(2,4);
                        year = valor.substring(5,9);
                    }
                    else
                    {
                        dia = valor.substring(0,2);
                        mes = valor.substring(4,5);
                        year = valor.substring(6,10);
                    }
                    //******************************************************************
                    //******************************************************************
                    //logBeanWebReactivo.info  ("dia = " + dia);
                    //logBeanWebReactivo.info  ("mes = " + mes);
                    //logBeanWebReactivo.info  ("year = " + year);
                    //******************************************************************
                    //******************************************************************
                    fechaRetornar = new java.util.Date(Integer.valueOf(year)-1900,Integer.valueOf(mes),Integer.valueOf(dia));
                }
            }
            else
            {
                fechaRetornar = null;
            }
        }
        //******************************************************************
        //******************************************************************
        return (fechaRetornar);
    }        
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    /**
     * @return the secuencial
     */
    public String getSecuencial() {
        return secuencial;
    }

    /**
     * @param secuencial the secuencial to set
     */
    public void setSecuencial(String secuencial) {
        this.secuencial = secuencial;
    }

    /**
     * @return the nombreInstitucion
     */
    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    /**
     * @param nombreInstitucion the nombreInstitucion to set
     */
    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    /**
     * @return the departamentoInstitucion
     */
    public String getDepartamentoInstitucion() {
        return departamentoInstitucion;
    }

    /**
     * @param departamentoInstitucion the departamentoInstitucion to set
     */
    public void setDepartamentoInstitucion(String departamentoInstitucion) {
        this.departamentoInstitucion = departamentoInstitucion;
    }

    /**
     * @return the ciudadInstitucion
     */
    public String getCiudadInstitucion() {
        return ciudadInstitucion;
    }

    /**
     * @param ciudadInstitucion the ciudadInstitucion to set
     */
    public void setCiudadInstitucion(String ciudadInstitucion) {
        this.ciudadInstitucion = ciudadInstitucion;
    }

    /**
     * @return the direccionInstitucion
     */
    public String getDireccionInstitucion() {
        return direccionInstitucion;
    }

    /**
     * @param direccionInstitucion the direccionInstitucion to set
     */
    public void setDireccionInstitucion(String direccionInstitucion) {
        this.direccionInstitucion = direccionInstitucion;
    }

    /**
     * @return the nitInstitucion
     */
    public String getNitInstitucion() {
        return nitInstitucion;
    }

    /**
     * @param nitInstitucion the nitInstitucion to set
     */
    public void setNitInstitucion(String nitInstitucion) {
        this.nitInstitucion = nitInstitucion;
    }

    /**
     * @return the nivelComplejidadInstitucion
     */
    public String getNivelComplejidadInstitucion() {
        return nivelComplejidadInstitucion;
    }

    /**
     * @param nivelComplejidadInstitucion the nivelComplejidadInstitucion to set
     */
    public void setNivelComplejidadInstitucion(String nivelComplejidadInstitucion) {
        this.nivelComplejidadInstitucion = nivelComplejidadInstitucion;
    }

    /**
     * @return the naturalezaInstitucion
     */
    public String getNaturalezaInstitucion() {
        return naturalezaInstitucion;
    }

    /**
     * @param naturalezaInstitucion the naturalezaInstitucion to set
     */
    public void setNaturalezaInstitucion(String naturalezaInstitucion) {
        this.naturalezaInstitucion = naturalezaInstitucion;
    }

    /**
     * @return the tipIdentPaciente
     */
    public String getTipIdentPaciente() {
        return tipIdentPaciente;
    }

    /**
     * @param tipIdentPaciente the tipIdentPaciente to set
     */
    public void setTipIdentPaciente(String tipIdentPaciente) {
        this.tipIdentPaciente = tipIdentPaciente;
    }

    /**
     * @return the identificacionPaciente
     */
    public String getIdentificacionPaciente() {
        return identificacionPaciente;
    }

    /**
     * @param identificacionPaciente the identificacionPaciente to set
     */
    public void setIdentificacionPaciente(String identificacionPaciente) {
        this.identificacionPaciente = identificacionPaciente;
    }

    /**
     * @return the sexoPaciente
     */
    public String getSexoPaciente() {
        return sexoPaciente;
    }

    /**
     * @param sexoPaciente the sexoPaciente to set
     */
    public void setSexoPaciente(String sexoPaciente) {
        this.sexoPaciente = sexoPaciente;
    }

    /**
     * @return the edadPaciente
     */
    public String getEdadPaciente() {
        return edadPaciente;
    }

    /**
     * @param edadPaciente the edadPaciente to set
     */
    public void setEdadPaciente(String edadPaciente) {
        this.edadPaciente = edadPaciente;
    }

    /**
     * @return the edadEnPaciente
     */
    public String getEdadEnPaciente() {
        return edadEnPaciente;
    }

    /**
     * @param edadEnPaciente the edadEnPaciente to set
     */
    public void setEdadEnPaciente(String edadEnPaciente) {
        this.edadEnPaciente = edadEnPaciente;
    }

    /**
     * @return the nombreReactivo
     */
    public String getNombreReactivo() {
        return nombreReactivo;
    }

    /**
     * @param nombreReactivo the nombreReactivo to set
     */
    public void setNombreReactivo(String nombreReactivo) {
        this.nombreReactivo = nombreReactivo;
    }

    /**
     * @return the registroSanReactivo
     */
    public String getRegistroSanReactivo() {
        return registroSanReactivo;
    }

    /**
     * @param registroSanReactivo the registroSanReactivo to set
     */
    public void setRegistroSanReactivo(String registroSanReactivo) {
        this.registroSanReactivo = registroSanReactivo;
    }

    /**
     * @return the expedienteReactivo
     */
    public String getExpedienteReactivo() {
        return expedienteReactivo;
    }

    /**
     * @param expedienteReactivo the expedienteReactivo to set
     */
    public void setExpedienteReactivo(String expedienteReactivo) {
        this.expedienteReactivo = expedienteReactivo;
    }

    /**
     * @return the codUnicoReactivo
     */
    public String getCodUnicoReactivo() {
        return codUnicoReactivo;
    }

    /**
     * @param codUnicoReactivo the codUnicoReactivo to set
     */
    public void setCodUnicoReactivo(String codUnicoReactivo) {
        this.codUnicoReactivo = codUnicoReactivo;
    }

    /**
     * @return the codigoTipoDiagReactivo
     */
    public String getCodigoTipoDiagReactivo() {
        return codigoTipoDiagReactivo;
    }

    /**
     * @param codigoTipoDiagReactivo the codigoTipoDiagReactivo to set
     */
    public void setCodigoTipoDiagReactivo(String codigoTipoDiagReactivo) {
        this.codigoTipoDiagReactivo = codigoTipoDiagReactivo;
    }

    /**
     * @return the loteReactivo
     */
    public String getLoteReactivo() {
        return loteReactivo;
    }

    /**
     * @param loteReactivo the loteReactivo to set
     */
    public void setLoteReactivo(String loteReactivo) {
        this.loteReactivo = loteReactivo;
    }

    /**
     * @return the referenciaReactivo
     */
    public String getReferenciaReactivo() {
        return referenciaReactivo;
    }

    /**
     * @param referenciaReactivo the referenciaReactivo to set
     */
    public void setReferenciaReactivo(String referenciaReactivo) {
        this.referenciaReactivo = referenciaReactivo;
    }

    /**
     * @return the fechaVenReactivo
     */
    public java.util.Date getFechaVenReactivo() {
        return fechaVenReactivo;
    }

    /**
     * @param fechaVenReactivo the fechaVenReactivo to set
     */
    public void setFechaVenReactivo(java.util.Date fechaVenReactivo) {
        this.fechaVenReactivo = fechaVenReactivo;
    }

    /**
     * @return the procedenciaReactivo
     */
    public String getProcedenciaReactivo() {
        return procedenciaReactivo;
    }

    /**
     * @param procedenciaReactivo the procedenciaReactivo to set
     */
    public void setProcedenciaReactivo(String procedenciaReactivo) {
        this.procedenciaReactivo = procedenciaReactivo;
    }

    /**
     * @return the requiereCadFrioReactivo
     */
    public String getRequiereCadFrioReactivo() {
        return requiereCadFrioReactivo;
    }

    /**
     * @param requiereCadFrioReactivo the requiereCadFrioReactivo to set
     */
    public void setRequiereCadFrioReactivo(String requiereCadFrioReactivo) {
        this.requiereCadFrioReactivo = requiereCadFrioReactivo;
    }

    /**
     * @return the temperaturaReactivo
     */
    public String getTemperaturaReactivo() {
        return temperaturaReactivo;
    }

    /**
     * @param temperaturaReactivo the temperaturaReactivo to set
     */
    public void setTemperaturaReactivo(String temperaturaReactivo) {
        this.temperaturaReactivo = temperaturaReactivo;
    }

    /**
     * @return the catReactivo
     */
    public String getCatReactivo() {
        return catReactivo;
    }

    /**
     * @param catReactivo the catReactivo to set
     */
    public void setCatReactivo(String catReactivo) {
        this.catReactivo = catReactivo;
    }

    /**
     * @return the cumpleConAlmacena
     */
    public String getCumpleConAlmacena() {
        return cumpleConAlmacena;
    }

    /**
     * @param cumpleConAlmacena the cumpleConAlmacena to set
     */
    public void setCumpleConAlmacena(String cumpleConAlmacena) {
        this.cumpleConAlmacena = cumpleConAlmacena;
    }

    /**
     * @return the realizaRecepcionReactivo
     */
    public String getRealizaRecepcionReactivo() {
        return realizaRecepcionReactivo;
    }

    /**
     * @param realizaRecepcionReactivo the realizaRecepcionReactivo to set
     */
    public void setRealizaRecepcionReactivo(String realizaRecepcionReactivo) {
        this.realizaRecepcionReactivo = realizaRecepcionReactivo;
    }

    /**
     * @return the prodCertAnalisisReactivo
     */
    public String getProdCertAnalisisReactivo() {
        return prodCertAnalisisReactivo;
    }

    /**
     * @param prodCertAnalisisReactivo the prodCertAnalisisReactivo to set
     */
    public void setProdCertAnalisisReactivo(String prodCertAnalisisReactivo) {
        this.prodCertAnalisisReactivo = prodCertAnalisisReactivo;
    }

    /**
     * @return the nombreFabReactivo
     */
    public String getNombreFabReactivo() {
        return nombreFabReactivo;
    }

    /**
     * @param nombreFabReactivo the nombreFabReactivo to set
     */
    public void setNombreFabReactivo(String nombreFabReactivo) {
        this.nombreFabReactivo = nombreFabReactivo;
    }

    /**
     * @return the nombreImportadorReactivo
     */
    public String getNombreImportadorReactivo() {
        return nombreImportadorReactivo;
    }

    /**
     * @param nombreImportadorReactivo the nombreImportadorReactivo to set
     */
    public void setNombreImportadorReactivo(String nombreImportadorReactivo) {
        this.nombreImportadorReactivo = nombreImportadorReactivo;
    }

    /**
     * @return the servicioFunReactivo
     */
    public String getServicioFunReactivo() {
        return servicioFunReactivo;
    }

    /**
     * @param servicioFunReactivo the servicioFunReactivo to set
     */
    public void setServicioFunReactivo(String servicioFunReactivo) {
        this.servicioFunReactivo = servicioFunReactivo;
    }

    /**
     * @return the fechaIncidente
     */
    public java.util.Date getFechaIncidente() {
        return fechaIncidente;
    }

    /**
     * @param fechaIncidente the fechaIncidente to set
     */
    public void setFechaIncidente(java.util.Date fechaIncidente) {
        this.fechaIncidente = fechaIncidente;
    }

    /**
     * @return the descripcionIncidente
     */
    public String getDescripcionIncidente() {
        return descripcionIncidente;
    }

    /**
     * @param descripcionIncidente the descripcionIncidente to set
     */
    public void setDescripcionIncidente(String descripcionIncidente) {
        this.descripcionIncidente = descripcionIncidente;
    }

    /**
     * @return the desenlaceIncidente
     */
    public String getDesenlaceIncidente() {
        return desenlaceIncidente;
    }

    /**
     * @param desenlaceIncidente the desenlaceIncidente to set
     */
    public void setDesenlaceIncidente(String desenlaceIncidente) {
        this.desenlaceIncidente = desenlaceIncidente;
    }

    /**
     * @return the otroIncidente
     */
    public String getOtroIncidente() {
        return otroIncidente;
    }

    /**
     * @param otroIncidente the otroIncidente to set
     */
    public void setOtroIncidente(String otroIncidente) {
        this.otroIncidente = otroIncidente;
    }

    /**
     * @return the tieneProgGestion
     */
    public String getTieneProgGestion() {
        return tieneProgGestion;
    }

    /**
     * @param tieneProgGestion the tieneProgGestion to set
     */
    public void setTieneProgGestion(String tieneProgGestion) {
        this.tieneProgGestion = tieneProgGestion;
    }

    /**
     * @return the realizoAnalisisGestion
     */
    public String getRealizoAnalisisGestion() {
        return realizoAnalisisGestion;
    }

    /**
     * @param realizoAnalisisGestion the realizoAnalisisGestion to set
     */
    public void setRealizoAnalisisGestion(String realizoAnalisisGestion) {
        this.realizoAnalisisGestion = realizoAnalisisGestion;
    }

    /**
     * @return the herramientaAnalisisGestion
     */
    public String getHerramientaAnalisisGestion() {
        return herramientaAnalisisGestion;
    }

    /**
     * @param herramientaAnalisisGestion the herramientaAnalisisGestion to set
     */
    public void setHerramientaAnalisisGestion(String herramientaAnalisisGestion) {
        this.herramientaAnalisisGestion = herramientaAnalisisGestion;
    }

    /**
     * @return the otroAnalisisGestion
     */
    public String getOtroAnalisisGestion() {
        return otroAnalisisGestion;
    }

    /**
     * @param otroAnalisisGestion the otroAnalisisGestion to set
     */
    public void setOtroAnalisisGestion(String otroAnalisisGestion) {
        this.otroAnalisisGestion = otroAnalisisGestion;
    }

    /**
     * @return the causaEfectoAnalisisGestion
     */
    public String getCausaEfectoAnalisisGestion() {
        return causaEfectoAnalisisGestion;
    }

    /**
     * @param causaEfectoAnalisisGestion the causaEfectoAnalisisGestion to set
     */
    public void setCausaEfectoAnalisisGestion(String causaEfectoAnalisisGestion) {
        this.causaEfectoAnalisisGestion = causaEfectoAnalisisGestion;
    }

    /**
     * @return the causaProbableEfectoGestion
     */
    public String getCausaProbableEfectoGestion() {
        return causaProbableEfectoGestion;
    }

    /**
     * @param causaProbableEfectoGestion the causaProbableEfectoGestion to set
     */
    public void setCausaProbableEfectoGestion(String causaProbableEfectoGestion) {
        this.causaProbableEfectoGestion = causaProbableEfectoGestion;
    }

    /**
     * @return the descripcionCausaEfectoGestion
     */
    public String getDescripcionCausaEfectoGestion() {
        return descripcionCausaEfectoGestion;
    }

    /**
     * @param descripcionCausaEfectoGestion the descripcionCausaEfectoGestion to set
     */
    public void setDescripcionCausaEfectoGestion(String descripcionCausaEfectoGestion) {
        this.descripcionCausaEfectoGestion = descripcionCausaEfectoGestion;
    }

    /**
     * @return the codigoCausaEfectoGestion
     */
    public String getCodigoCausaEfectoGestion() {
        return codigoCausaEfectoGestion;
    }

    /**
     * @param codigoCausaEfectoGestion the codigoCausaEfectoGestion to set
     */
    public void setCodigoCausaEfectoGestion(String codigoCausaEfectoGestion) {
        this.codigoCausaEfectoGestion = codigoCausaEfectoGestion;
    }

    /**
     * @return the inicioAccionesEfectoGestion
     */
    public String getInicioAccionesEfectoGestion() {
        return inicioAccionesEfectoGestion;
    }

    /**
     * @param inicioAccionesEfectoGestion the inicioAccionesEfectoGestion to set
     */
    public void setInicioAccionesEfectoGestion(String inicioAccionesEfectoGestion) {
        this.inicioAccionesEfectoGestion = inicioAccionesEfectoGestion;
    }

    /**
     * @return the accionesGestion
     */
    public String getAccionesGestion() {
        return accionesGestion;
    }

    /**
     * @param accionesGestion the accionesGestion to set
     */
    public void setAccionesGestion(String accionesGestion) {
        this.accionesGestion = accionesGestion;
    }

    /**
     * @return the reportoDistribGestion
     */
    public String getReportoDistribGestion() {
        return reportoDistribGestion;
    }

    /**
     * @param reportoDistribGestion the reportoDistribGestion to set
     */
    public void setReportoDistribGestion(String reportoDistribGestion) {
        this.reportoDistribGestion = reportoDistribGestion;
    }

    /**
     * @return the fechaReporteImportadorGestion
     */
    public java.util.Date getFechaReporteImportadorGestion() {
        return fechaReporteImportadorGestion;
    }

    /**
     * @param fechaReporteImportadorGestion the fechaReporteImportadorGestion to set
     */
    public void setFechaReporteImportadorGestion(java.util.Date fechaReporteImportadorGestion) {
        this.fechaReporteImportadorGestion = fechaReporteImportadorGestion;
    }

    /**
     * @return the fechaEnvioDistribuidorGestion
     */
    public java.util.Date getFechaEnvioDistribuidorGestion() {
        return fechaEnvioDistribuidorGestion;
    }

    /**
     * @param fechaEnvioDistribuidorGestion the fechaEnvioDistribuidorGestion to set
     */
    public void setFechaEnvioDistribuidorGestion(java.util.Date fechaEnvioDistribuidorGestion) {
        this.fechaEnvioDistribuidorGestion = fechaEnvioDistribuidorGestion;
    }

    /**
     * @return the nombreReportante
     */
    public String getNombreReportante() {
        return nombreReportante;
    }

    /**
     * @param nombreReportante the nombreReportante to set
     */
    public void setNombreReportante(String nombreReportante) {
        this.nombreReportante = nombreReportante;
    }

    /**
     * @return the profesionReportante
     */
    public String getProfesionReportante() {
        return profesionReportante;
    }

    /**
     * @param profesionReportante the profesionReportante to set
     */
    public void setProfesionReportante(String profesionReportante) {
        this.profesionReportante = profesionReportante;
    }

    /**
     * @return the organizacionReportante
     */
    public String getOrganizacionReportante() {
        return organizacionReportante;
    }

    /**
     * @param organizacionReportante the organizacionReportante to set
     */
    public void setOrganizacionReportante(String organizacionReportante) {
        this.organizacionReportante = organizacionReportante;
    }

    /**
     * @return the direccionReportante
     */
    public String getDireccionReportante() {
        return direccionReportante;
    }

    /**
     * @param direccionReportante the direccionReportante to set
     */
    public void setDireccionReportante(String direccionReportante) {
        this.direccionReportante = direccionReportante;
    }

    /**
     * @return the telefonoReportante
     */
    public String getTelefonoReportante() {
        return telefonoReportante;
    }

    /**
     * @param telefonoReportante the telefonoReportante to set
     */
    public void setTelefonoReportante(String telefonoReportante) {
        this.telefonoReportante = telefonoReportante;
    }

    /**
     * @return the departamentoReportante
     */
    public String getDepartamentoReportante() {
        return departamentoReportante;
    }

    /**
     * @param departamentoReportante the departamentoReportante to set
     */
    public void setDepartamentoReportante(String departamentoReportante) {
        this.departamentoReportante = departamentoReportante;
    }

    /**
     * @return the ciudadReportante
     */
    public String getCiudadReportante() {
        return ciudadReportante;
    }

    /**
     * @param ciudadReportante the ciudadReportante to set
     */
    public void setCiudadReportante(String ciudadReportante) {
        this.ciudadReportante = ciudadReportante;
    }

    /**
     * @return the correoReportante
     */
    public String getCorreoReportante() {
        return correoReportante;
    }

    /**
     * @param correoReportante the correoReportante to set
     */
    public void setCorreoReportante(String correoReportante) {
        this.correoReportante = correoReportante;
    }

    /**
     * @return the fechaNotIPSReportante
     */
    public java.util.Date getFechaNotIPSReportante() {
        return fechaNotIPSReportante;
    }

    /**
     * @param fechaNotIPSReportante the fechaNotIPSReportante to set
     */
    public void setFechaNotIPSReportante(java.util.Date fechaNotIPSReportante) {
        this.fechaNotIPSReportante = fechaNotIPSReportante;
    }

    /**
     * @return the autorizaDivulgaReportante
     */
    public String getAutorizaDivulgaReportante() {
        return autorizaDivulgaReportante;
    }

    /**
     * @param autorizaDivulgaReportante the autorizaDivulgaReportante to set
     */
    public void setAutorizaDivulgaReportante(String autorizaDivulgaReportante) {
        this.autorizaDivulgaReportante = autorizaDivulgaReportante;
    }

    /**
     * @return the tipoReportanteInfoReactivo
     */
    public String getTipoReportanteInfoReactivo() {
        return tipoReportanteInfoReactivo;
    }

    /**
     * @param tipoReportanteInfoReactivo the tipoReportanteInfoReactivo to set
     */
    public void setTipoReportanteInfoReactivo(String tipoReportanteInfoReactivo) {
        this.tipoReportanteInfoReactivo = tipoReportanteInfoReactivo;
    }

    /**
     * @return the estadoReporteInfoReactivo
     */
    public String getEstadoReporteInfoReactivo() {
        return estadoReporteInfoReactivo;
    }

    /**
     * @param estadoReporteInfoReactivo the estadoReporteInfoReactivo to set
     */
    public void setEstadoReporteInfoReactivo(String estadoReporteInfoReactivo) {
        this.estadoReporteInfoReactivo = estadoReporteInfoReactivo;
    }

    /**
     * @return the fechaGET
     */
    public java.util.Date getFechaGET() {
        return fechaGET;
    }

    /**
     * @param fechaGET the fechaGET to set
     */
    public void setFechaGET(java.util.Date fechaGET) {
        this.fechaGET = fechaGET;
    }

    /**
     * @return the gestionET
     */
    public String getGestionET() {
        return gestionET;
    }

    /**
     * @param gestionET the gestionET to set
     */
    public void setGestionET(String gestionET) {
        this.gestionET = gestionET;
    }

    /**
     * @return the estadoGET
     */
    public String getEstadoGET() {
        return estadoGET;
    }

    /**
     * @param estadoGET the estadoGET to set
     */
    public void setEstadoGET(String estadoGET) {
        this.estadoGET = estadoGET;
    }

    /**
     * @return the fechaGInvima
     */
    public java.util.Date getFechaGInvima() {
        return fechaGInvima;
    }

    /**
     * @param fechaGInvima the fechaGInvima to set
     */
    public void setFechaGInvima(java.util.Date fechaGInvima) {
        this.fechaGInvima = fechaGInvima;
    }

    /**
     * @return the observacionesGInvima
     */
    public String getObservacionesGInvima() {
        return observacionesGInvima;
    }

    /**
     * @param observacionesGInvima the observacionesGInvima to set
     */
    public void setObservacionesGInvima(String observacionesGInvima) {
        this.observacionesGInvima = observacionesGInvima;
    }
    
    
   //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************
    //********************************************************************

    /**
     * @return the codigoEntidad
     */
    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    /**
     * @param codigoEntidad the codigoEntidad to set
     */
    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }
}