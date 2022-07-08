/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.beans;

import co.gov.invima.VO.MenusVO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Priority;

/**
 *
 * @author mgualdrond
 */
@Named(value = "principalBean")
@SessionScoped
public class PrincipalBean extends BaseBean {

    private final static Logger logBeanWebReactivo = Logger.getLogger(PrincipalBean.class.getName());
    private static final long serialVersionUID = 4643843891794405583L;
    private List<MenusVO> menus;
    private List<MenusVO> menusMatrizCentral;
    
    /**
     * Creates a new instance of PrincipalBean
     */
    public PrincipalBean() {
        super();
        this.menus = new ArrayList<>();
        this.menusMatrizCentral=new ArrayList<>();
        this.llenarMenusPrueba();
        
    }
    
    private void llenarMenusPrueba() {

        try {
            //Se obtiene la sesión para tomar de ella los menús disponibles
            //para el usuario que inició la sesión
            HttpSession session = (HttpSession) FacesContext
                    .getCurrentInstance().getExternalContext().getSession(true);
            this.menus = (List<MenusVO>) session.getAttribute("menus");

            for (MenusVO menusVO : menus) {
                if (menusVO.isMostrar()) {
                    menusMatrizCentral.add(menusVO);
                }
            }
        } catch (Exception e) {
            logBeanWebReactivo.log(Priority.ERROR,"Error al llenar los menus en el centro d ela pagina", e);
        }

    }
    
    @Override
    public void limpiarFormulario() {
    }

    public List<MenusVO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenusVO> menus) {
        this.menus = menus;
    }

    public List<MenusVO> getMenusMatrizCentral() {
        return menusMatrizCentral;
    }

    public void setMenusMatrizCentral(List<MenusVO> menusMatrizCentral) {
        this.menusMatrizCentral = menusMatrizCentral;
    }
    
    
}
