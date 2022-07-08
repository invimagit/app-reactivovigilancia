/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoProducto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoProductoFacadeLocal {

    void create(ReactivoProducto reactivoProducto);

    void edit(ReactivoProducto reactivoProducto);

    void remove(ReactivoProducto reactivoProducto);

    ReactivoProducto find(Object id);

    List<ReactivoProducto> findAll();

    List<ReactivoProducto> findRange(int[] range);

    int count();
    
}
