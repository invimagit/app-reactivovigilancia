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

@Embeddable
public class ReactivoFriarhPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "friarh")
    private String friarh="";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "cdg_tipoalerta")
    private String cdgTipoalerta;

    public ReactivoFriarhPK() {
    }

    public ReactivoFriarhPK(String friarh, String cdgTipoalerta) {
        this.friarh = friarh;
        this.cdgTipoalerta = cdgTipoalerta;
    }

    public String getFriarh() {
        return friarh;
    }

    public void setFriarh(String friarh) {
        this.friarh = friarh;
    }

    public String getCdgTipoalerta() {
        return cdgTipoalerta;
    }

    public void setCdgTipoalerta(String cdgTipoalerta) {
        this.cdgTipoalerta = cdgTipoalerta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (friarh != null ? friarh.hashCode() : 0);
        hash += (cdgTipoalerta != null ? cdgTipoalerta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactivoFriarhPK)) {
            return false;
        }
        ReactivoFriarhPK other = (ReactivoFriarhPK) object;
        if ((this.friarh == null && other.friarh != null) || (this.friarh != null && !this.friarh.equals(other.friarh))) {
            return false;
        }
        if ((this.cdgTipoalerta == null && other.cdgTipoalerta != null) || (this.cdgTipoalerta != null && !this.cdgTipoalerta.equals(other.cdgTipoalerta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.ReactivoFriarhPK[ friarh=" + friarh + ", cdgTipoalerta=" + cdgTipoalerta + " ]";
    }
    
}
