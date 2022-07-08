/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoInstitucion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoInstitucionFacadeLocal {

    void create(ReactivoInstitucion reactivoInstitucion);

    void edit(ReactivoInstitucion reactivoInstitucion);

    void remove(ReactivoInstitucion reactivoInstitucion);

    ReactivoInstitucion find(Object id);

    List<ReactivoInstitucion> findAll();

    List<ReactivoInstitucion> findRange(int[] range);

    int count();
    
}
