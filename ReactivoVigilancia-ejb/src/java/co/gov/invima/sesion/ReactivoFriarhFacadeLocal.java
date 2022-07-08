/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoFriarh;
import co.gov.invima.entities.ReactivoFriarhPK;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoFriarhFacadeLocal {
    void create(ReactivoFriarh reactivoFriarh);

    void edit(ReactivoFriarh reactivoFriarh);

    void remove(ReactivoFriarh reactivoFriarh);

    ReactivoFriarh find(Object id);

    List<ReactivoFriarh> findAll();
    
    List<String> findAll_id();
    
    ReactivoFriarh obtenerListaReportesFRIARHporID(String FRIARH);

    List<ReactivoFriarh> findRange(int[] range);

    int count();
}
