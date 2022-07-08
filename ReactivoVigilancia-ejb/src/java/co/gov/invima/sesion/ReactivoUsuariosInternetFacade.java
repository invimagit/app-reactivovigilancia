/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.VO.UsuariosVO;
import co.gov.invima.entities.ReactivoUsuariosInternet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
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
public class ReactivoUsuariosInternetFacade extends AbstractReactivoFacade<ReactivoUsuariosInternet> implements ReactivoUsuariosInternetFacadeLocal {

    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager em;

    private static Logger logEJBReactivo = Logger.getLogger(ReactivoUsuariosInternetFacade.class.getName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReactivoUsuariosInternetFacade() {
        super(ReactivoUsuariosInternet.class);
    }

    /**
     * Verifica si un nombre de usuario ya existe.
     *
     * @param usuario: nombre de usuario a verificar
     * @return Objeto con los datos del usuario registrado en el programa de
     * reactivoVigilancia
     */
    @Override
    public ReactivoUsuariosInternet validarUsuarioExiste(String usuario) {
        ReactivoUsuariosInternet internet = null;
        try {
            Query q = em.createNamedQuery("ReactivoUsuariosInternet.findByUsuario");
            q.setParameter("usuario", usuario);
            internet = (ReactivoUsuariosInternet) q.getSingleResult();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener usuario", e);
        } finally {
            return internet;
        }
    }

    /**
     * Busca si un umero de cedula existe o no en los usuarios registrados de
     * Internet
     *
     * @param identificacionPersona: numero de identificacion a buscar
     * @return Objeto ReactivoUsuariosInternet
     */
    @Override
    public ReactivoUsuariosInternet validarCedulaExiste(long identificacionPersona) {
        ReactivoUsuariosInternet rui = null;
        try {
            Query q = em.createNamedQuery("ReactivoUsuariosInternet.findByIdentificacionPersona");
            q.setParameter("identificacionPersona", identificacionPersona);
            rui = (ReactivoUsuariosInternet) q.getSingleResult();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al verificar usuario por cedula", e);
        } finally {
            return rui;
        }
    }

    /**
     * Obtiene el listado de ususarios por cedula
     *
     * @param identificacionPersona: numero de identificacion de la persona
     * @return
     */
    @Override
    public List<ReactivoUsuariosInternet> obtenerUsuariosPorCedula(long identificacionPersona) {
        List<ReactivoUsuariosInternet> rui = null;
        try {
            Query q = em.createNamedQuery("ReactivoUsuariosInternet.findByIdentificacionPersona");
            q.setParameter("identificacionPersona", identificacionPersona);
            rui = q.getResultList();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al verificar usuario por cedula", e);
        } finally {
            return rui;
        }
    }

    /**
     * Obtiene el listado de usuarios por nombre de persona
     *
     * @param nombre: nombre a buscar
     * @return listado de usuarios por nombre
     */
    @Override
    public List<ReactivoUsuariosInternet> obtenerUsuariosPorNombre(String nombre) {
        List<ReactivoUsuariosInternet> rui = null;
        try {
            Query q = em.createNamedQuery("ReactivoUsuariosInternet.findByNombrePersona");
            q.setParameter("nombrePersona", nombre);
            rui = q.getResultList();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al verificar usuario por cedula", e);
        } finally {
            return rui;
        }
    }

    /**
     * Obtiene el listado de usuarios por estado
     *
     * @param estado: estados del usuario
     * @return listado de usuarios
     */
    @Override
    public List<ReactivoUsuariosInternet> obtenerUsuariosPorEstado(Character estado) {
        List<ReactivoUsuariosInternet> rui = null;
        try {
            Query q = null;
            if (estado != null) {
                q = em.createNamedQuery("ReactivoUsuariosInternet.findByEstadoUsuario");
                q.setParameter("estadoUsuario", estado);
            } else {
                String consulta = "select * from reactivo_usuarios_internet where estado_usuario is null";
                q = em.createNativeQuery(consulta, ReactivoUsuariosInternet.class);
            }

            rui = q.getResultList();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al verificar usuario por cedula", e);
        } finally {
            return rui;
        }
    }
    
    
        @Override
    public boolean esUsuarioDeBD(String usuario, String clave, String conexion) {

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Properties props = new Properties();
            props.put("user", usuario);
            props.put("password", clave);

            String url = "jdbc:jtds:sqlserver://" + conexion;
            Connection conn = DriverManager.getConnection(url, props);
            logEJBReactivo.log(Priority.INFO, "Conexion funcionario: url="+url+", usuario="+usuario+"-clave="+"*******");
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            //Logger.getLogger(ReactivoUsuariosInternetFacade.class.getName()).log(Priority.ERROR, "Ocurrio un error: ", e);
            return false;
        }
        return true;
    }



}
