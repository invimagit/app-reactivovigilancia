/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoMenus;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoMenusFacadeLocal {
    void create(ReactivoMenus reactivoMenus);

    void edit(ReactivoMenus reactivoMenus);

    void remove(ReactivoMenus reactivoMenus);

    ReactivoMenus find(Object id);

    List<ReactivoMenus> findAll();

    List<ReactivoMenus> findRange(int[] range);

    int count();
}
