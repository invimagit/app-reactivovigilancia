/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoGestionrealizada;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoGestionrealizadaFacadeLocal {

    void create(ReactivoGestionrealizada reactivoGestionrealizada);

    void edit(ReactivoGestionrealizada reactivoGestionrealizada);

    void remove(ReactivoGestionrealizada reactivoGestionrealizada);

    ReactivoGestionrealizada find(Object id);

    List<ReactivoGestionrealizada> findAll();

    List<ReactivoGestionrealizada> findRange(int[] range);

    int count();
    
}
