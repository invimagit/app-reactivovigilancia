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
public class RespuestaVO implements Serializable {
    private Integer code;
    private String Status;
    private String mensaje;
    private String mensajeDetallado;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeDetallado() {
        return mensajeDetallado;
    }

    public void setMensajeDetallado(String mensajeDetallado) {
        this.mensajeDetallado = mensajeDetallado;
    }
    
    
}
