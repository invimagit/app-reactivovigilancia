/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.excepcion;

import javax.ejb.ApplicationException;

/**
 * Clase para controlar los rallback en las trab¿nsacciones d ela base de datos
 * @author mgualdrond
 */
@ApplicationException(rollback = true)
public class ISAPEntityException extends Exception {
    
    public ISAPEntityException() {
        super();
    }

    public ISAPEntityException(String message) {
        super(message);
    }
}
