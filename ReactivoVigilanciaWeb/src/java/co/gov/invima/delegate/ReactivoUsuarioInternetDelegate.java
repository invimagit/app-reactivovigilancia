package co.gov.invima.delegate;

import co.gov.invima.VO.UsuariosVO;
import co.gov.invima.dao.ReactivoUsuarioInternetDao;


public class ReactivoUsuarioInternetDelegate 
{
    public static UsuariosVO validarUsuarioFuncionario(String usuario, String password) 
    {
        ReactivoUsuarioInternetDao rep = new ReactivoUsuarioInternetDao();
        return rep.validarUsuarioBD(usuario,password);
    }
}
