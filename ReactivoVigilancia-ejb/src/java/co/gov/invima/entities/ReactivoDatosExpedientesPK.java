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
public class ReactivoDatosExpedientesPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "reporte")
    private String reporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nroexpediente")
    private long nroexpediente;

    public ReactivoDatosExpedientesPK() {
    }

    public ReactivoDatosExpedientesPK(String reporte, long nroexpediente) {
        this.reporte = reporte;
        this.nroexpediente = nroexpediente;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public long getNroexpediente() {
        return nroexpediente;
    }

    public void setNroexpediente(long nroexpediente) {
        this.nroexpediente = nroexpediente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reporte != null ? reporte.hashCode() : 0);
        hash += (int) nroexpediente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoDatosExpedientesPK)) {
            return false;
        }
        ReactivoDatosExpedientesPK other = (ReactivoDatosExpedientesPK) object;
        if ((this.reporte == null && other.reporte != null) || (this.reporte != null && !this.reporte.equals(other.reporte))) {
            return false;
        }
        if (this.nroexpediente != other.nroexpediente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoDatosExpedientesPK[ reporte=" + reporte + ", nroexpediente=" + nroexpediente + " ]";
    }
    
}
