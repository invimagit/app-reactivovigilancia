/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.dao;

import co.gov.invima.VO.UsuariosVO;
import co.gov.invima.negocio.ReactivoVigilanciaBeanLocal;
import co.gov.invima.service.ServiceLocator;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Diana Silva
 */
public class ReactivoUsuarioInternetDao {

    public Connection getConexion() {
        Connection con = null;
        try {
            // hago el lookup del pool de conexiones
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/sivicosDS");
            // pido una conexion
            con = ds.getConnection();

            // la uso
            return con;


        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        /*
         * finally { try { if( con!=null ) { // devuelvo la conexion al pool
         * con.close(); } } catch(Exception ex) { ex.printStackTrace(); throw
         * new RuntimeException(ex); }
          }
         */
    }

    /**
     * Verificar el usuairo y al contraseña en lña base de datos
     * @param usuario
     * @param password
     * @return 
     */
    //**************************************************************
    //**************************************************************
    //**************************************************************
    public UsuariosVO validarUsuarioBD(String usuario, String password) {
        ServiceLocator service = new ServiceLocator();
        ReactivoVigilanciaBeanLocal reporteEjb = service.getEJBReactivo();
        return reporteEjb.validarUsuarioFuncionario(usuario, password);
    }
    //**************************************************************
    //**************************************************************
    public UsuariosVO validarUsuarioInternet(String usuario, String password) {
        ServiceLocator service = new ServiceLocator();
        ReactivoVigilanciaBeanLocal reporteEjb = service.getEJBReactivo();
        return reporteEjb.validarContrasenaInternet(usuario, password);
    }
    //**************************************************************
    //**************************************************************
    //**************************************************************
}