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
public class CiudadesVO implements Serializable{
    
    private String cdgterritorio;

    private String ciudad;
    
    private String codMun;

    private String gtt;

    public String getCdgterritorio() {
        return cdgterritorio;
    }

    public void setCdgterritorio(String cdgterritorio) {
        this.cdgterritorio = cdgterritorio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodMun() {
        return codMun;
    }

    public void setCodMun(String codMun) {
        this.codMun = codMun;
    }

    public String getGtt() {
        return gtt;
    }

    public void setGtt(String gtt) {
        this.gtt = gtt;
    }
    
    
}
