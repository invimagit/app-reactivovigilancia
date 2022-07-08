package co.gov.invima.negocio;

import co.gov.invima.reactivo.dto.reports.*;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ConsultasReactivoRemote 
{
    public List<ReporteReactivo> generarReporteReactivoVigilancia (java.util.Date fechaInicial, java.util.Date fechaFinal);
    public List<PersonaInternet> generarReportePersonaInternet (java.util.Date fechaInicial, java.util.Date fechaFinal);
    public List<ReporteFriarh> generarReporteFriahr (java.util.Date fechaInicial, java.util.Date fechaFinal);
    public List<ReporteFriarh> generarReporteFriahrFabricante(String nitEmpresa);
    public List<RegistroReactivo> generarRegistroRepReactivo (java.util.Date fechaInicial, java.util.Date fechaFinal);    
    public List<InscritoRedRV> generarReporteInscritosRedRV (java.util.Date fechaInicial, java.util.Date fechaFinal);    
    public List<String> obtenerListadoRolesUsuario();
    public List<ReportePrerdiv> generarReportePreRDivs (String departamento, String municipio, java.util.Date fechaInicial, java.util.Date fechaFinal);
    public List<ReporteTrimestralEvento> generarConsultaReportesTrimestralesConEvento (java.util.Date fechaInicial, java.util.Date fechaFinal);   
    public List<ReporteTrimestralEvento> generarConsultaReportesTrimestralesConEventoSecretaria (java.util.Date fechaInicial, java.util.Date fechaFinal, String depto);   
    public List<ReporteEfectoAdverso> generarConsultaReportesEventoAdverso (java.util.Date fechaInicial, java.util.Date fechaFinal);   
    public List<ReporteEfectoAdverso> generarConsultaReportesEventoAdversoSecretaria (java.util.Date fechaInicial, java.util.Date fechaFinal, String depto);   
    public void actualizar_REPORTE_MASIVO_TRIMESTRAL_CON_EVENTO(ReporteTrimestralEvento x);
}
