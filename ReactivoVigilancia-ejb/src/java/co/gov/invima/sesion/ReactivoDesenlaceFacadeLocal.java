/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoDesenlace;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoDesenlaceFacadeLocal {

    void create(ReactivoDesenlace reactivoDesenlace);

    void edit(ReactivoDesenlace reactivoDesenlace);

    void remove(ReactivoDesenlace reactivoDesenlace);

    ReactivoDesenlace find(Object id);

    List<ReactivoDesenlace> findAll();

    List<ReactivoDesenlace> findRange(int[] range);

    int count();
    
}
