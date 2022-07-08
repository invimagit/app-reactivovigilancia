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
public class PaisesVO implements Serializable {
    
    private String cdgPais;

    private String pais;

    private Character referencia;

    private String tipo;

    public String getCdgPais() {
        return cdgPais;
    }

    public void setCdgPais(String cdgPais) {
        this.cdgPais = cdgPais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Character getReferencia() {
        return referencia;
    }

    public void setReferencia(Character referencia) {
        this.referencia = referencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
