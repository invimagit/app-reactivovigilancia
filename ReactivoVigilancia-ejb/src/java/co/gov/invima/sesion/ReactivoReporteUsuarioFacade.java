/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoReporteUsuario;
import co.gov.invima.reactivo.dto.reports.RegistroReactivo;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author jonat
 */
@Stateless
public class ReactivoReporteUsuarioFacade extends AbstractFacade<ReactivoReporteUsuario> {

    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager em;
    private static final Logger logEJBReactivo = Logger.getLogger(ReactivoReporteUsuarioFacade.class.getName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReactivoReporteUsuarioFacade() {
        super(ReactivoReporteUsuario.class);
    }
    
    public long get_siguiente_id(){
        long retorno = 1;
        try{
            String sqlString = "SELECT MAX(id) FROM reactivo_reporte_usuario";
            Query q = em.createNativeQuery(sqlString);
            String id_maximo = q.getSingleResult().toString();
            retorno = Long.parseLong(id_maximo)+1;
        }
        catch(Exception e){
            logEJBReactivo.log(Priority.ERROR, "ReactivoReporteUsuarioFacade, get_siguiente_id CAtch: ", e);
        }
        return retorno;
    }
    
    public List<ReactivoReporteUsuario> generarRegistro(Date fechaInicial, Date fechaFinal) 
    {
        List<ReactivoReporteUsuario> reporteDatos = new ArrayList<ReactivoReporteUsuario>();  
        
        try
        {
            Query query1 = em.createNamedQuery("ReactivoReporteUsuario.findByFechaElaboracionBetween");
            //**************************************************************
            //**************************************************************
            fechaInicial.setHours(0);
            fechaInicial.setMinutes(0);
            fechaInicial.setSeconds(0);
            //**************************************************************
            fechaFinal.setHours(23);
            fechaFinal.setMinutes(59);
            fechaFinal.setSeconds(59);

            query1.setParameter("fechaElaboracion1",fechaInicial);
            query1.setParameter("fechaElaboracion2",fechaFinal);
            reporteDatos = query1.getResultList();
            return reporteDatos;
        }
        
        catch (Exception e)
        {
            //logEJBReactivo.info ("Error en la generación del método generarRegistroRepReactivo = " + errorGeneracionMUID.getLocalizedMessage());
            e.printStackTrace();
            List<RegistroReactivo> vacio = new ArrayList<RegistroReactivo>();
            return reporteDatos;
        }
    }
    
}
