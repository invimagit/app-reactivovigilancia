/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.dto.service;

/**
 *
 * @author Kubit
 */
public class SolicitarVisitaRsService {
    
    private VisitaService visitaService;

    public SolicitarVisitaRsService(VisitaService visitaService) {
        this.visitaService = visitaService;
    }

    public VisitaService getVisitaService() {
        return visitaService;
    }

    public void setVisitaService(VisitaService visitaService) {
        this.visitaService = visitaService;
    }
    
}
