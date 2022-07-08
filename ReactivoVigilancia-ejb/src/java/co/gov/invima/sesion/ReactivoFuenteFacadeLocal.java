/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoFuente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoFuenteFacadeLocal {
    void create(ReactivoFuente reactivoFuente);

    void edit(ReactivoFuente reactivoFuente);

    void remove(ReactivoFuente reactivoFuente);

    ReactivoFuente find(Object id);

    List<ReactivoFuente> findAll();

    List<ReactivoFuente> findRange(int[] range);

    int count();
}
