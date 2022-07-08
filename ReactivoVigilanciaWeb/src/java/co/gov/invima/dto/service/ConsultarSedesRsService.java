/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.dto.service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chavarro
 */
public class ConsultarSedesRsService {

    private List<SedesService> Sedes;

    public ConsultarSedesRsService() {
        this.Sedes = new ArrayList<>();
    }

    public List<SedesService> getSedes() {
        return Sedes;
    }

    public void setSedes(List<SedesService> Sedes) {
        this.Sedes = Sedes;
    }

}
