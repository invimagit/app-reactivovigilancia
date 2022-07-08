/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.dto.service;

import co.gov.invima.dto.AuditoriaDTO;
import co.gov.invima.dto.ParametrosDTO;

/**
 *
 * @author Chavarro
 */
public class ConsultarSedesRqService {
    
    private AuditoriaDTO Auditoria;
    private ParametrosDTO Parametros;
    
   public ConsultarSedesRqService(){
       this.Auditoria = new AuditoriaDTO();
       this.Parametros = new ParametrosDTO();
   }

    public AuditoriaDTO getAuditoria() {
        return Auditoria;
    }

    public void setAuditoria(AuditoriaDTO Auditoria) {
        this.Auditoria = Auditoria;
    }

    public ParametrosDTO getParametros() {
        return Parametros;
    }

    public void setParametros(ParametrosDTO Parametros) {
        this.Parametros = Parametros;
    }
    
}
