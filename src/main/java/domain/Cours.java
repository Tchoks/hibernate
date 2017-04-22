package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by marti on 16/04/2017.
 */

// TODO : utiliser une clé composée? à revoir.


@Entity
@Table(name = "Cours")
public class Cours {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "fid", column = @Column(name = "fid")),
            @AttributeOverride(name = "cid", column = @Column(name = "cid"))
    })
    private CoursId coursId;

    @Column(name = "nom")
    private String nom_cours;

    @OneToMany(mappedBy = "cours", cascade = CascadeType.ALL)
    private Set<Seance> seances;

    private Cours() {}

    public Cours(CoursId coursId, String nom_cours) {
        this.coursId = coursId;
        this.nom_cours = nom_cours;
        seances = new HashSet<>();
    }

    public CoursId getCoursId() {
        return coursId;
    }

    public void setCoursId(CoursId coursId) {
        this.coursId = coursId;
    }

    public String getNom_cours() {
        return nom_cours;
    }

    public void setNom_cours(String nom_cours) {
        this.nom_cours = nom_cours;
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
        if (!(o instanceof Cours)) return false;

        Cours cours = (Cours) o;

        if (!getCoursId().equals(cours.getCoursId())) return false;
        return getNom_cours().equals(cours.getNom_cours());
    }

    @Override
    public int hashCode() {
        int result = getCoursId().hashCode();
        result = 31 * result + getNom_cours().hashCode();
        return result;
    }
}
