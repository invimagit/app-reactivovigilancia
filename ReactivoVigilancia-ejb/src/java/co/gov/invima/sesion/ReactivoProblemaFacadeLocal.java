/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoProblema;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoProblemaFacadeLocal {

    void create(ReactivoProblema reactivoProblema);

    void edit(ReactivoProblema reactivoProblema);

    void remove(ReactivoProblema reactivoProblema);

    ReactivoProblema find(Object id);

    List<ReactivoProblema> findAll();

    List<ReactivoProblema> findRange(int[] range);

    int count();
    
}
