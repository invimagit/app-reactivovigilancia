/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoDatosExpedientes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoDatosExpedientesFacadeLocal {
    void create(ReactivoDatosExpedientes reactivoDatosExpedientes);

    void edit(ReactivoDatosExpedientes reactivoDatosExpedientes);

    void remove(ReactivoDatosExpedientes reactivoDatosExpedientes);

    ReactivoDatosExpedientes find(Object id);

    List<ReactivoDatosExpedientes> findAll();

    List<ReactivoDatosExpedientes> findRange(int[] range);

    int count();
}
