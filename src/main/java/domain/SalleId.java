package domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by marti on 17/04/2017.
 */

@Embeddable
public class SalleId implements Serializable {

    @Column(name = "batiment")
    private String batiment;

    @Column(name = "numero_salle")
    private String numero_salle;

    public SalleId() {}

    public SalleId(String batiment, String numero_salle) {
        this.batiment = batiment;
        this.numero_salle = numero_salle;
    }

    public String getBatiment() {
        return batiment;
    }

    public void setBatiment(String batiment) {
        this.batiment = batiment;
    }

    public String getNumero_salle() {
        return numero_salle;
    }

    public void setNumero_salle(String numero_salle) {
        this.numero_salle = numero_salle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalleId)) return false;

        SalleId salleId = (SalleId) o;

        if (!getBatiment().equals(salleId.getBatiment())) return false;
        return getNumero_salle().equals(salleId.getNumero_salle());
    }

    @Override
    public int hashCode() {
        int result = getBatiment().hashCode();
        result = 31 * result + getNumero_salle().hashCode();
        return result;
    }
}
