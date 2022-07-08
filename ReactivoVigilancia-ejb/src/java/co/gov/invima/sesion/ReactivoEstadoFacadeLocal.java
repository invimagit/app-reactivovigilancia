/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoEstado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoEstadoFacadeLocal {
    void create(ReactivoEstado reactivoEstado);

    void edit(ReactivoEstado reactivoEstado);

    void remove(ReactivoEstado reactivoEstado);

    ReactivoEstado find(Object id);

    List<ReactivoEstado> findAll();

    List<ReactivoEstado> findRange(int[] range);

    int count();
}
