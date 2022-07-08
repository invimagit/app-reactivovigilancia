/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.BO;

import co.gov.invima.VO.DepartamentoVO;
import co.gov.invima.VO.PaisesVO;
import co.gov.invima.VO.ProfesionesVO;
import co.gov.invima.VO.ReactivoModalidadesVO;
import co.gov.invima.VO.RedVO;
import co.gov.invima.negocio.ReactivoVigilanciaBeanLocal;
import co.gov.invima.service.ServiceLocator;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mgualdrond
 */
public class ReactivoBO implements Serializable {
    
    private final ReactivoVigilanciaBeanLocal reactivoEJB;
    private final ServiceLocator sl;

    public ReactivoBO() {
        sl = new ServiceLocator();
        reactivoEJB = sl.getEJBReactivo();
    }
    
    /**
     * Obtiene las modalidades activas desde la base de datos de sivicos de las
     * tablas de reactivovigilancia
     * @return Listado de modalidades activas
     */
    public List<ReactivoModalidadesVO> obtnerModalidades(){
        return reactivoEJB.obtenerCatalogoModalidades();
    }
    
    public List<PaisesVO> obtenerPaises(){
        return reactivoEJB.obtenerCatalogoPaises();
    }
    
    public List<DepartamentoVO> obtenerDptos(){
        return reactivoEJB.obtenerCatalogoDptos();
    }
    
    /**
     * Obtiene las profesiones para asociarlas al solicitante
     * @return Listado de profesiones
     */
    public List<ProfesionesVO> obtenerProfesiones(){
        return reactivoEJB.obtenerProfesiones();
    }
    
    public String guradrInscripcionRed(RedVO redVO){
        return reactivoEJB.guardarInscricpcionRed(redVO);
    }
}
