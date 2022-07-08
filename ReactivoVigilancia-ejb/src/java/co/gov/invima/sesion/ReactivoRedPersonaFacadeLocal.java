/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoRedPersona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoRedPersonaFacadeLocal {
    void create(ReactivoRedPersona reactivoRed);

    void edit(ReactivoRedPersona reactivoRedPersona);

    void remove(ReactivoRedPersona reactivoRedPersona);

    ReactivoRedPersona find(Object id);

    List<ReactivoRedPersona> findAll();

    List<ReactivoRedPersona> findRange(int[] range);

    int count();

    public List<ReactivoRedPersona> obtenerPersonaRedPorDocumento(String cedulaSolicitante);

    public ReactivoRedPersona obtenerPersonaPorDocumentoYEntidad(String cedulaSolicitante, String nit);

    public Integer obtenerId();
}
