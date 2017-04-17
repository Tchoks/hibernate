package domain;

import javax.persistence.*;

/**
 * Created by marti on 16/04/2017.
 */

// TODO : utiliser une clé composée? à revoir.


@Entity
@Table(name = "Cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cours_id")
    private int id;

    @Column(name = "nom")
    private String nom_cours;

    @ManyToOne(optional = false)
    @JoinColumn(name = "formation_id")
    private Formation formation;

    private Cours() {}

    public Cours(String nom_cours, Formation formation) {
        this.nom_cours = nom_cours;
        this.formation = formation;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_cours() {
        return nom_cours;
    }

    public void setNom_cours(String nom_cours) {
        this.nom_cours = nom_cours;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cours)) return false;

        Cours cours = (Cours) o;

        if (getId() != cours.getId()) return false;
        if (!getNom_cours().equals(cours.getNom_cours())) return false;
        return getFormation().equals(cours.getFormation());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getNom_cours().hashCode();
        result = 31 * result + getFormation().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "id=" + id +
                ", nom_cours='" + nom_cours + '\'' +
                ", formation=" + formation +
                '}';
    }
}
