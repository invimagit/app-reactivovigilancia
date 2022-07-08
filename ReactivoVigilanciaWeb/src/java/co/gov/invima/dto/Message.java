/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.dto;

/**
 *
 * @author Kubit
 */
public class Message {
    
    String codigoConfirmacion;
    String mensajeConfirmacion;

    public Message(){
        super();
    }
    public Message(String codigoConfirmacion, String mensajeConfirmacion) {
        this.codigoConfirmacion = codigoConfirmacion;
        this.mensajeConfirmacion = mensajeConfirmacion;
    }

    public String getCodigoConfirmacion() {
        return codigoConfirmacion;
    }

    public void setCodigoConfirmacion(String codigoConfirmacion) {
        this.codigoConfirmacion = codigoConfirmacion;
    }

    public String getMensajeConfirmacion() {
        return mensajeConfirmacion;
    }

    public void setMensajeConfirmacion(String mensajeConfirmacion) {
        this.mensajeConfirmacion = mensajeConfirmacion;
    }
    
    
    
}
