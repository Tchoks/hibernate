package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by marti on 16/04/2017.
 */

@Entity
@Table(name = "Formation")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "formation_id")
    private int id;
    @Column(name = "nom", nullable = false, length = 50)
    private String nom_formation;
    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL)
    //@JoinColumn(name = "formation_id")
    private Set<Cours> list_cours;

    private Formation() {}

    public Formation(String nom_formation) {
        this.nom_formation = nom_formation;
        list_cours = new HashSet<>();
    }


    public void addCours(Cours cours) {
        list_cours.add(cours);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_formation() {
        return nom_formation;
    }

    public void setNom_formation(String nom_formation) {
        this.nom_formation = nom_formation;
    }

   public Set<Cours> getList_cours() {
        return list_cours;
    }

    public void setList_cours(Set<Cours> list_cours) {
        this.list_cours = list_cours;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Formation)) return false;

        Formation formation = (Formation) o;

        if (getId() != formation.getId()) return false;
        return getNom_formation().equals(formation.getNom_formation());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getNom_formation().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "id=" + id +
                ", nom_formation='" + nom_formation + '\'' +
                '}';
    }
}
