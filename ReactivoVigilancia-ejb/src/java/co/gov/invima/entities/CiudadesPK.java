/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mgualdrond
 */
@Embeddable
public class CiudadesPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "cdgterritorio")
    private String cdgterritorio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "ciudad")
    private String ciudad;

    public CiudadesPK() {
    }

    public CiudadesPK(String cdgterritorio, String ciudad) {
        this.cdgterritorio = cdgterritorio;
        this.ciudad = ciudad;
    }

    public String getCdgterritorio() {
        return cdgterritorio;
    }

    public void setCdgterritorio(String cdgterritorio) {
        this.cdgterritorio = cdgterritorio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdgterritorio != null ? cdgterritorio.hashCode() : 0);
        hash += (ciudad != null ? ciudad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CiudadesPK)) {
            return false;
        }
        CiudadesPK other = (CiudadesPK) object;
        if ((this.cdgterritorio == null && other.cdgterritorio != null) || (this.cdgterritorio != null && !this.cdgterritorio.equals(other.cdgterritorio))) {
            return false;
        }
        if ((this.ciudad == null && other.ciudad != null) || (this.ciudad != null && !this.ciudad.equals(other.ciudad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.CiudadesPK[ cdgterritorio=" + cdgterritorio + ", ciudad=" + ciudad + " ]";
    }
    
}
