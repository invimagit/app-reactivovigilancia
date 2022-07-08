/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoFriarh01;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoFriarh01FacadeLocal {
    void create(ReactivoFriarh01 reactivoFriarh01);

    void edit(ReactivoFriarh01 reactivoFriarh01);

    void remove(ReactivoFriarh01 reactivoFriarh01);

    ReactivoFriarh01 find(Object id);

    List<ReactivoFriarh01> findAll();

    List<ReactivoFriarh01> findRange(int[] range);

    int count();
}
