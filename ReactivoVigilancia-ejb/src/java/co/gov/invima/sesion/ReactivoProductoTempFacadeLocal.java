/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoProductoTemp;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoProductoTempFacadeLocal {

    void create(ReactivoProductoTemp reactivoProducto);

    void edit(ReactivoProductoTemp reactivoProducto);

    void remove(ReactivoProductoTemp reactivoProducto);

    ReactivoProductoTemp find(Object id);

    List<ReactivoProductoTemp> findAll();

    List<ReactivoProductoTemp> findRange(int[] range);

    int count();
    
}
