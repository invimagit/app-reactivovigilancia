/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.VO;

import java.io.Serializable;

/**
 *
 * @author mgualdrond
 */
public class MenusVO implements Serializable {
    
    private Integer opcionMenu;
    private String descripcionMenu;
    private String permitidoMenu;
    private String urlMenu;
    private String accionMenu;
    private boolean mostrar;

    public Integer getOpcionMenu() {
        return opcionMenu;
    }

    public void setOpcionMenu(Integer opcionMenu) {
        this.opcionMenu = opcionMenu;
    }

    public String getDescripcionMenu() {
        return descripcionMenu;
    }

    public void setDescripcionMenu(String descripcionMenu) {
        this.descripcionMenu = descripcionMenu;
    }

    public String getPermitidoMenu() {
        return permitidoMenu;
    }

    public void setPermitidoMenu(String permitidoMenu) {
        this.permitidoMenu = permitidoMenu;
    }

    public String getUrlMenu() {
        return urlMenu;
    }

    public void setUrlMenu(String urlMenu) {
        this.urlMenu = urlMenu;
    }

    public String getAccionMenu() {
        return accionMenu;
    }

    public void setAccionMenu(String accionMenu) {
        this.accionMenu = accionMenu;
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }
    
    
}
