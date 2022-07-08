/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.Funcionario_sin;
import co.gov.invima.entities.Funcionarios;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Priority;

/**
 *
 * @author mgualdrond
 */
@Stateless
public class FuncionariosFacade extends AbstractReactivoFacade<Funcionarios> implements FuncionariosFacadeLocal {
    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    public EntityManager em;
    
    private final static Logger logEJBReactivo = Logger.getLogger(FuncionariosFacade.class.getName());
    
    public FuncionariosFacade() {
        super(Funcionarios.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Obtiene la información del usuario registrado en SIVICOS Standalone
     * @param loginName: nombre de Usuario
     * @return Objeto con los datos del funcionario
     */
    @Override
    public Funcionarios obtenerUsuarioPorLogin(String loginName){
        Funcionarios funcionarios = null;
        try {
            Query q = em.createNamedQuery("Funcionarios.findByLoginName");
            q.setParameter("loginName", loginName);
            funcionarios = (Funcionarios) q.getSingleResult();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "No se encontro Login Name: ", e);
        } finally {
            return funcionarios;
        }
    }
    
    @Override
    public List<Funcionario_sin> obtenerUsuarioActivos(){
        List<Funcionario_sin> funcionarios = new ArrayList<Funcionario_sin>();
        try {
            String sqlString = "Select cdgfuncionario, nmbfuncionario from funcionarios where activo=1";
            Query q = em.createNativeQuery(sqlString);
            //funcionarios = q.getResultList();
            
            
            
            List<Object[]> list = q.getResultList();

            for(Object[] q1 : list){
                Funcionario_sin  x = new Funcionario_sin();
                x.setCdgfuncionario(Short.parseShort(q1[0].toString()));
                x.setNmbfuncionario(q1[1].toString());
                //..
                //do something more on 
                funcionarios.add(x);
            }
                logEJBReactivo.log(Priority.INFO, "obtenerUsuarioActivos funcionarios.size(): "+funcionarios.size() );
            
            
            
            /*Query q = em.createNamedQuery("Funcionarios.findByActivo");
            q.setParameter("activo", true);
            funcionarios = q.getResultList();
            logEJBReactivo.log(Priority.INFO, "FuncionariosFacade - obtenerUsuarioActivos, funcionarios.size()= "+funcionarios.size());*/
            
            
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "obtenerUsuarioActivos CAtch: ", e);
        } finally {
            return funcionarios;
        }
    }
    
}
