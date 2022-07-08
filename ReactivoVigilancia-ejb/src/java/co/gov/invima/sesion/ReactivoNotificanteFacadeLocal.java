/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoNotificante;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoNotificanteFacadeLocal {
    void create(ReactivoNotificante reactivoNotificante);

    void edit(ReactivoNotificante reactivoNotificante);

    void remove(ReactivoNotificante reactivoNotificante);

    ReactivoNotificante find(Object id);

    List<ReactivoNotificante> findAll();

    List<ReactivoNotificante> findRange(int[] range);

    int count();
}
