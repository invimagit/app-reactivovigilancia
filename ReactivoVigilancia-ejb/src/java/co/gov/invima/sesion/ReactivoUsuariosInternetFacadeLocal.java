/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoUsuariosInternet;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface ReactivoUsuariosInternetFacadeLocal {

    void create(ReactivoUsuariosInternet reactivoUsuariosInternet);

    void edit(ReactivoUsuariosInternet reactivoUsuariosInternet);

    void remove(ReactivoUsuariosInternet reactivoUsuariosInternet);

    ReactivoUsuariosInternet find(Object id);

    List<ReactivoUsuariosInternet> findAll();

    List<ReactivoUsuariosInternet> findRange(int[] range);

    int count();

    public ReactivoUsuariosInternet validarUsuarioExiste(String usuario);

    public ReactivoUsuariosInternet validarCedulaExiste(long identificacionPersona);

    public List<ReactivoUsuariosInternet> obtenerUsuariosPorCedula(long identificacionPersona);

    public List<ReactivoUsuariosInternet> obtenerUsuariosPorNombre(String nombre);

    public List<ReactivoUsuariosInternet> obtenerUsuariosPorEstado(Character estado);
    
    
    public boolean esUsuarioDeBD(String usuario, String clave, String conexion);
    
    
}
