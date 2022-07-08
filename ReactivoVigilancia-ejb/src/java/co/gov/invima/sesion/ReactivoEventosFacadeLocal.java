/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoEventos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoEventosFacadeLocal {

    void create(ReactivoEventos reactivoEventos);

    void edit(ReactivoEventos reactivoEventos);

    void remove(ReactivoEventos reactivoEventos);

    ReactivoEventos find(Object id);

    List<ReactivoEventos> findAll();

    List<ReactivoEventos> findRange(int[] range);
    
    List<String> findAll_id();

    int count();
    
}
