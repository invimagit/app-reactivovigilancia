/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.VO;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mgualdrond
 */
public class DepartamentoVO implements Serializable {
    
    private String codDepart;

    private String descripcion;

    private String cdgTerritorio;

    private String iso31662;
    
    private List<CiudadesVO> ciudadesVOs;

    public String getCodDepart() {
        return codDepart;
    }

    public void setCodDepart(String codDepart) {
        this.codDepart = codDepart;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCdgTerritorio() {
        return cdgTerritorio;
    }

    public void setCdgTerritorio(String cdgTerritorio) {
        this.cdgTerritorio = cdgTerritorio;
    }

    public String getIso31662() {
        return iso31662;
    }

    public void setIso31662(String iso31662) {
        this.iso31662 = iso31662;
    }

    public List<CiudadesVO> getCiudadesVOs() {
        return ciudadesVOs;
    }

    public void setCiudadesVOs(List<CiudadesVO> ciudadesVOs) {
        this.ciudadesVOs = ciudadesVOs;
    }

    
}
