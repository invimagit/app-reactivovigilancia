/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mgualdrond
 */
@Entity
@Table(name = "ciudades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciudades.findAll", query = "SELECT c FROM Ciudades c"),
    @NamedQuery(name = "Ciudades.findByCdgterritorio", query = "SELECT c FROM Ciudades c WHERE c.ciudadesPK.cdgterritorio = :cdgterritorio"),
    @NamedQuery(name = "Ciudades.findByCiudad", query = "SELECT c FROM Ciudades c WHERE c.ciudadesPK.ciudad = :ciudad"),
    @NamedQuery(name = "Ciudades.findByCodMun", query = "SELECT c FROM Ciudades c WHERE c.codMun = :codMun"),
    @NamedQuery(name = "Ciudades.findByGtt", query = "SELECT c FROM Ciudades c WHERE c.gtt = :gtt")})
public class Ciudades implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CiudadesPK ciudadesPK;
    @Size(max = 5)
    @Column(name = "cod_mun")
    private String codMun;
    @Size(max = 4)
    @Column(name = "gtt")
    private String gtt;

    public Ciudades() {
    }

    public Ciudades(CiudadesPK ciudadesPK) {
        this.ciudadesPK = ciudadesPK;
    }

    public Ciudades(String cdgterritorio, String ciudad) {
        this.ciudadesPK = new CiudadesPK(cdgterritorio, ciudad);
    }

    public CiudadesPK getCiudadesPK() {
        return ciudadesPK;
    }

    public void setCiudadesPK(CiudadesPK ciudadesPK) {
        this.ciudadesPK = ciudadesPK;
    }

    public String getCodMun() {
        return codMun;
    }

    public void setCodMun(String codMun) {
        this.codMun = codMun;
    }

    public String getGtt() {
        return gtt;
    }

    public void setGtt(String gtt) {
        this.gtt = gtt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ciudadesPK != null ? ciudadesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciudades)) {
            return false;
        }
        Ciudades other = (Ciudades) object;
        if ((this.ciudadesPK == null && other.ciudadesPK != null) || (this.ciudadesPK != null && !this.ciudadesPK.equals(other.ciudadesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.invima.entities.Ciudades[ ciudadesPK=" + ciudadesPK + " ]";
    }
    
}
