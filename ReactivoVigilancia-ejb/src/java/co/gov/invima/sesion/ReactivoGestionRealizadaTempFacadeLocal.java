/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoGestionrealizadaTemp;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ingeniero Jaime Alberto Gutiérrez Mejía
 */
@Local
public interface ReactivoGestionRealizadaTempFacadeLocal 
{
    //******************************************************
    //******************************************************
    //******************************************************
    //******************************************************
    //******************************************************
    //******************************************************
    void create(ReactivoGestionrealizadaTemp reactivoEventosTemp);
    //******************************************************
    //******************************************************
    void edit(ReactivoGestionrealizadaTemp reactivoEventosTemp);
    //******************************************************
    //******************************************************
    void remove(ReactivoGestionrealizadaTemp reactivoEventosTemp);
    //******************************************************
    //******************************************************
    ReactivoGestionrealizadaTemp find(Object id);
    //******************************************************
    //******************************************************
    List<ReactivoGestionrealizadaTemp> findAll();
    //******************************************************
    //******************************************************
    List<ReactivoGestionrealizadaTemp> findRange(int[] range);
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
