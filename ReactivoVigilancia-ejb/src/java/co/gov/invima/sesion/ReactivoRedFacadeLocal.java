/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoRed;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoRedFacadeLocal {

    void create(ReactivoRed reactivoRed);

    void edit(ReactivoRed reactivoRed);

    void remove(ReactivoRed reactivoRed);

    ReactivoRed find(Object id);

    List<ReactivoRed> findAll();

    List<ReactivoRed> findRange(int[] range);

    int count();

    public Integer obtenerIdRed();

    public ReactivoRed obtenerEntidadPorNit(String nit);

    public ReactivoRed obtenerIdRedObj();

    
}
