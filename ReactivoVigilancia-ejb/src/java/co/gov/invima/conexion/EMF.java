/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mgualdrond
 */
public class EMF {

    private  EntityManagerFactory emf;
    
    public EMF() {
        emf = Persistence.createEntityManagerFactory("ReactivoVigilancia-ejbPU");
    }
    
    public EntityManager getEntityManager(){
        if(emf==null){
            emf = Persistence.createEntityManagerFactory("ReactivoVigilancia-ejbPU");
        }
        return emf.createEntityManager();
    }
    
}
