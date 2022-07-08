/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoCamposcompTemp;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ingeniero Jaime Alberto Gutiérrez Mejía
 */
@Local
public interface ReactivoCamposComplementariosTempFacadeLocal 
{
    //******************************************************
    //******************************************************
    //******************************************************
    //******************************************************
    //******************************************************
    //******************************************************
    void create(ReactivoCamposcompTemp reactivoEventosTemp);
    //******************************************************
    //******************************************************
    void edit(ReactivoCamposcompTemp reactivoEventosTemp);
    //******************************************************
    //******************************************************
    void remove(ReactivoCamposcompTemp reactivoEventosTemp);
    //******************************************************
    //******************************************************
    ReactivoCamposcompTemp find(Object id);
    //******************************************************
    //******************************************************
    List<ReactivoCamposcompTemp> findAll();
    //******************************************************
    //******************************************************
    List<ReactivoCamposcompTemp> findRange(int[] range);
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
