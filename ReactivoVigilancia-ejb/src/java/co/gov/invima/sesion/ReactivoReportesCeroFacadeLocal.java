/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import co.gov.invima.entities.ReactivoReportesCero;
import java.util.List;
import javax.ejb.Local;
/*************************************************************************/
/*************************************************************************/
/*************************************************************************/
/**
 *
 * @author Jaime Alberto Gutiérrez Mejía
 */
/*************************************************************************/
/*************************************************************************/
/*************************************************************************/
/*************************************************************************/
/*************************************************************************/
/*************************************************************************/
@Local
public interface ReactivoReportesCeroFacadeLocal 
{
    //*******************************************************************
    //*******************************************************************
    //*******************************************************************
    public void create(ReactivoReportesCero tecnoReporteCero);
    //*******************************************************************
    //*******************************************************************
    public void edit(ReactivoReportesCero tecnoReporteCero);
    //*******************************************************************
    //*******************************************************************
    public void remove(ReactivoReportesCero tecnoReporteCero);
    //*******************************************************************
    //*******************************************************************
    public ReactivoReportesCero find(Object id);
    //*******************************************************************
    //*******************************************************************
    public List<ReactivoReportesCero> findAll();
    //*******************************************************************
    //*******************************************************************
    //public long buscarMaxId();
    public int buscarMaxId();
    //*******************************************************************
    //*******************************************************************
}
/*************************************************************************/
/*************************************************************************/
/*************************************************************************/
/*************************************************************************/
/*************************************************************************/
/*************************************************************************/