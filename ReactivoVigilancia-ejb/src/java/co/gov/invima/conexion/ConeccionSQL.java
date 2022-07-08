/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.conexion;

import co.gov.invima.utils.PropertiesReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author mgualdrond
 */
public class ConeccionSQL {

    private static final Logger logEJBReactivo = Logger.getLogger(ConeccionSQL.class.getName());

    public static Connection GetConnection(String usuario,String password) {
        Connection conexion = null;
        try {
            PropertiesReader pr = new PropertiesReader("co.gov.invima.utils.conexiones");
            Class.forName(pr.getProperty("driver"));
            String url = pr.getProperty("cadena")+pr.getProperty("basedatos")+";user="+usuario+";password="+password+";";
            conexion = DriverManager.getConnection(url);
        } catch (Exception ex) {
            logEJBReactivo.log(Priority.ERROR, null, ex);
            conexion = null;
        } finally {
            return conexion;
        }
    }
}
