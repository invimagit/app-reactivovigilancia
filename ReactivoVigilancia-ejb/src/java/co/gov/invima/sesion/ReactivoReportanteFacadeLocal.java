/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoReportante;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoReportanteFacadeLocal {

    void create(ReactivoReportante reactivoReportante);

    void edit(ReactivoReportante reactivoReportante);

    void remove(ReactivoReportante reactivoReportante);

    ReactivoReportante find(Object id);

    List<ReactivoReportante> findAll();

    List<ReactivoReportante> findRange(int[] range);

    int count();
    
}
