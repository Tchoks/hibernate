package domain;

import exceptions.BusinessException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @authors Martin Tchokonthe And Mohammed Sylla
 * @date on 17/04/2017.
 */

@Entity
@Table(name = "Salle")
public class Salle {

    @EmbeddedId
    private SalleId id;

    @Column(name = "capacite", nullable = false)
    private int capacite;

    @OneToMany(mappedBy = "salle"/*, fetch = FetchType.EAGER*/)
    private Set<Seance> seances;

    public Salle() {}

    public Salle(SalleId id, int capacite) throws BusinessException {
        this.id = id;
        if (capacite < 1) throw new BusinessException(" capacity must be greater or equals to 1");
        this.capacite = capacite;
        seances = new HashSet<>();
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

    public void addSeance(Seance seance) {
        seances.add(seance);
    }

    public Set<Seance> getSeances() {
        return seances;
    }

    public void setSeances(Set<Seance> seances) {
        this.seances = seances;
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
