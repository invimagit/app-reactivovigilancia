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
public class Friarh02VO implements Serializable {

    private Integer id;
    private String nombreReactivo;
    private String registroSanitario;
    private String referencia;
    private String lote;
    private String marca;
    private short cantidad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreReactivo() {
        return nombreReactivo;
    }

    public void setNombreReactivo(String nombreReactivo) {
        this.nombreReactivo = nombreReactivo;
    }

    public String getRegistroSanitario() {
        return registroSanitario;
    }

    public void setRegistroSanitario(String registroSanitario) {
        this.registroSanitario = registroSanitario;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
