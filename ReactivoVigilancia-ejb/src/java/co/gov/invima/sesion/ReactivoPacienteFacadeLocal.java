/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoPaciente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoPacienteFacadeLocal {

    void create(ReactivoPaciente reactivoPaciente);

    void edit(ReactivoPaciente reactivoPaciente);

    void remove(ReactivoPaciente reactivoPaciente);

    ReactivoPaciente find(Object id);

    List<ReactivoPaciente> findAll();

    List<ReactivoPaciente> findRange(int[] range);

    int count();
    
}
