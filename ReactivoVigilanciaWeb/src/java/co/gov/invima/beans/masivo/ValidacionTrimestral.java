/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.beans.masivo;

import java.io.Serializable;

/**
 *
 * @author jgutierrezme
 */
public class ValidacionTrimestral implements Serializable {
 
    private static final long serialVersionUID = 77552254L;
    private String id_error;
    private String tipo_error;
    private String descripcion;
    private int linea;
    private String campo;

    /**
     * @return the id_error
     */
    public String getId_error() {
        return id_error;
    }

    /**
     * @param id_error the id_error to set
     */
    public void setId_error(String id_error) {
        this.id_error = id_error;
    }

    /**
     * @return the tipo_error
     */
    public String getTipo_error() {
        return tipo_error;
    }

    /**
     * @param tipo_error the tipo_error to set
     */
    public void setTipo_error(String tipo_error) {
        this.tipo_error = tipo_error;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the linea
     */
    public int getLinea() {
        return linea;
    }

    /**
     * @param linea the linea to set
     */
    public void setLinea(int linea) {
        this.linea = linea;
    }

    /**
     * @return the campo
     */
    public String getCampo() {
        return campo;
    }

    /**
     * @param campo the campo to set
     */
    public void setCampo(String campo) {
        this.campo = campo;
    }
    
    
    
}
