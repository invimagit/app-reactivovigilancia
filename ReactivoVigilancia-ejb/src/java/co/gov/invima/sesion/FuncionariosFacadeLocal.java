/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.Funcionario_sin;
import co.gov.invima.entities.Funcionarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mgualdrond
 */
@Local
public interface FuncionariosFacadeLocal {
    void create(Funcionarios funcionarios);

    void edit(Funcionarios funcionarios);

    void remove(Funcionarios funcionarios);

    Funcionarios find(Object id);

    List<Funcionarios> findAll();

    List<Funcionarios> findRange(int[] range);

    int count();

    public Funcionarios obtenerUsuarioPorLogin(String loginName);
    
    public List<Funcionario_sin> obtenerUsuarioActivos();
}
