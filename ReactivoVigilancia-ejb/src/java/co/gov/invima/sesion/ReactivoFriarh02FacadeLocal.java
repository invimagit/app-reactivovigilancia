/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoFriarh02;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoFriarh02FacadeLocal {
    void create(ReactivoFriarh02 reactivoFriarh02);

    void edit(ReactivoFriarh02 reactivoFriarh02);

    void remove(ReactivoFriarh02 reactivoFriarh02);

    ReactivoFriarh02 find(Object id);

    List<ReactivoFriarh02> findAll();

    List<ReactivoFriarh02> findRange(int[] range);

    int count();
}
