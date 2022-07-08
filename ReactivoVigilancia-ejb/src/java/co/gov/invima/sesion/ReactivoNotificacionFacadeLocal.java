/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoNotificacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoNotificacionFacadeLocal {
    void create(ReactivoNotificacion reactivoNotificacion);

    void edit(ReactivoNotificacion reactivoNotificacion);

    void remove(ReactivoNotificacion reactivoNotificacion);

    ReactivoNotificacion find(Object id);

    List<ReactivoNotificacion> findAll();

    List<ReactivoNotificacion> findRange(int[] range);

    int count();
}
