/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.Departamentos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface DepartamentosFacadeLocal {

    void create(Departamentos departamentos);

    void edit(Departamentos departamentos);

    void remove(Departamentos departamentos);

    Departamentos find(Object id);

    List<Departamentos> findAll();

    List<Departamentos> findRange(int[] range);

    int count();

    public List<Departamentos> obtenerDptoOrdenado();

    public Departamentos obtenerPorNombre(String nombre);
    
    public Departamentos obtenerPorCdgTerritorio(String cdgTerritorio);
    
}
