/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoArea;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoAreaFacadeLocal {

    void create(ReactivoArea reactivoArea);

    void edit(ReactivoArea reactivoArea);

    void remove(ReactivoArea reactivoArea);

    ReactivoArea find(Object id);

    List<ReactivoArea> findAll();

    List<ReactivoArea> findRange(int[] range);

    int count();
    
}
