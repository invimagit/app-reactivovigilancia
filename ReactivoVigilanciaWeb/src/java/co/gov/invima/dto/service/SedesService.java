/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.dto.service;


/**
 *
 * @author Chavarro
 */
public class SedesService {
    
    private SedeService sede;

    public SedesService() {
        this.sede = new SedeService();
    }

    public SedeService getSede() {
        return sede;
    }

    public void setSede(SedeService sede) {
        this.sede = sede;
    }
    
    
}
