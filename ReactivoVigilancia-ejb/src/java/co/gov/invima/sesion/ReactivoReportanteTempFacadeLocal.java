/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoReportanteTemp;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ingeniero Jaime Alberto Gutiérrez Mejía
 */
@Local
public interface ReactivoReportanteTempFacadeLocal 
{
    //******************************************************
    //******************************************************
    //******************************************************
    //******************************************************
    //******************************************************
    //******************************************************
    void create(ReactivoReportanteTemp reactivoReportanteTemp);
    //******************************************************
    //******************************************************
    void edit(ReactivoReportanteTemp reactivoReportanteTemp);
    //******************************************************
    //******************************************************
    void remove(ReactivoReportanteTemp reactivoReportanteTemp);
    //******************************************************
    //******************************************************
    ReactivoReportanteTemp find(Object id);
    //******************************************************
    //******************************************************
    List<ReactivoReportanteTemp> findAll();
    //******************************************************
    //******************************************************
    List<ReactivoReportanteTemp> findRange(int[] range);
    //******************************************************
    //******************************************************
    int count();
    //******************************************************
    //******************************************************
    //******************************************************
    //******************************************************
    //******************************************************
    //******************************************************
}
