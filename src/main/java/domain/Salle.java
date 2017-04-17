package domain;

import exceptions.BusinessException;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by marti on 16/04/2017.
 */

@Entity
@Table(name = "Salle")
public class Salle {

    @EmbeddedId
    private SalleId id;

    @Column(name = "capacite", nullable = false)
    private int capacite;

    public Salle() {}

    public Salle(SalleId id, int capacite) throws BusinessException {
        this.id = id;
        if (capacite < 1) throw new BusinessException(" capacity must be greater or equals to 1");
        this.capacite = capacite;
    }

    public SalleId getId() {
        return id;
    }

    public void setId(SalleId id) {
        this.id = id;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Salle)) return false;

        Salle salle = (Salle) o;

        if (getCapacite() != salle.getCapacite()) return false;
        return getId().equals(salle.getId());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getCapacite();
        return result;
    }
}
