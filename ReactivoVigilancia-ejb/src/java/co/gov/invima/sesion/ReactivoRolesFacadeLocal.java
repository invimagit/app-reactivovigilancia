/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoRoles;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoRolesFacadeLocal {
    void create(ReactivoRoles reactivoRoles);

    void edit(ReactivoRoles reactivoRoles);

    void remove(ReactivoRoles reactivoRoles);

    ReactivoRoles find(Object id);

    List<ReactivoRoles> findAll();

    List<ReactivoRoles> findRange(int[] range);

    int count();

    public ReactivoRoles obtenerMenuPorRol(Integer idRol);
}
