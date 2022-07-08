/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.dto.service;

import co.gov.invima.dto.ParametrosSolicitarVisitaDTO;

/**
 *
 * @author Kubit
 */
public class SolicitarVisitaRqService {
    
    private ParametrosSolicitarVisitaDTO parametrosSolicitarVisita;

    public SolicitarVisitaRqService() {
        this.parametrosSolicitarVisita = new ParametrosSolicitarVisitaDTO();
    }

    public ParametrosSolicitarVisitaDTO getParametrosSolicitarVisita() {
        return parametrosSolicitarVisita;
    }

    public void setParametrosSolicitarVisita(ParametrosSolicitarVisitaDTO parametrosSolicitarVisita) {
        this.parametrosSolicitarVisita = parametrosSolicitarVisita;
    }
    
}
