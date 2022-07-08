/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoModalidad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoModalidadFacadeLocal {

    void create(ReactivoModalidad reactivoModalidad);

    void edit(ReactivoModalidad reactivoModalidad);

    void remove(ReactivoModalidad reactivoModalidad);

    ReactivoModalidad find(Object id);

    List<ReactivoModalidad> findAll();

    List<ReactivoModalidad> findRange(int[] range);

    int count();

    public List<ReactivoModalidad> obtenerModalidadActivos();
    
}
