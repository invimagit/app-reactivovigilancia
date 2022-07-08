/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoInstitucionTemp;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoInstitucionTempFacadeLocal {

    void create(ReactivoInstitucionTemp reactivoInstitucion);

    void edit(ReactivoInstitucionTemp reactivoInstitucion);

    void remove(ReactivoInstitucionTemp reactivoInstitucion);

    ReactivoInstitucionTemp find(Object id);

    List<ReactivoInstitucionTemp> findAll();

    List<ReactivoInstitucionTemp> findRange(int[] range);

    int count();
    
}
