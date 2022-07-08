/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.TecnoProfesion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface TecnoProfesionFacadeLocal {

    void create(TecnoProfesion tecnoProfesion);

    void edit(TecnoProfesion tecnoProfesion);

    void remove(TecnoProfesion tecnoProfesion);

    TecnoProfesion find(Object id);

    List<TecnoProfesion> findAll();

    List<TecnoProfesion> findRange(int[] range);

    int count();
    
}
