/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.Parametros;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ParametrosFacadeLocal {
    
    void create(Parametros parametros);

    void edit(Parametros parametros);

    void remove(Parametros parametros);

    Parametros find(Object id);

    List<Parametros> findAll();

    List<Parametros> findRange(int[] range);

    int count();
}
