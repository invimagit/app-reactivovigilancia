/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.negocio;

import co.gov.invima.VO.ReactivoRepCeroConsultaVO;
import co.gov.invima.VO.ReactivoReporteCeroVO;
import co.gov.invima.reactivo.dto.reports.PersonaInternet;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jgutierrezme
 */
/*********************************************************************************************/
/*********************************************************************************************/
/*********************************************************************************************/
/*********************************************************************************************/
/*********************************************************************************************/
/*********************************************************************************************/
public interface ServicioReporteCeroRemote 
{
    //**********************************************************************************************
    //**********************************************************************************************
    //**********************************************************************************************
    //**********************************************************************************************
    public java.util.ArrayList<Object> crearRegistroReporteCero (ReactivoReporteCeroVO registroReporteCero);
    public ReactivoReporteCeroVO consultarRegistroReporteCero (String radicado);
    public List<ReactivoReporteCeroVO> obtenerListadoReportesCeroReportante (String reportante);
    public PersonaInternet obtenerDatosETSUsuarioSesion (String username);
    public List<ReactivoRepCeroConsultaVO> generarConsultaReportesMasivosCero (java.util.Date fechaInicial, java.util.Date fechaFinal);
    public List<ReactivoRepCeroConsultaVO> generarConsultaReportesMasivosCero(Date fechaInicial, Date fechaFinal, String deptoSecretaria); 
    public boolean verificarRegistroTrimestreReporteCero(String periodo, String year_reporte, String usuarioIps);
    public java.util.ArrayList<String> obtenerDivipolaUsuarioReporteCero(String nombreUsuario);
    //**********************************************************************************************
    //**********************************************************************************************
    //**********************************************************************************************
}
/*********************************************************************************************/
/*********************************************************************************************/
/*********************************************************************************************/
/*********************************************************************************************/
/*********************************************************************************************/
/*********************************************************************************************/
