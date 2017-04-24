package domain;

import javax.persistence.*;

/**
 * @authors Martin Tchokonthe And Mohammed Sylla
 * @date on 17/04/2017.
 */

@Entity
@Table(name = "Seance")
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sid")
    private int id;

    @ManyToOne(optional = false/*, fetch = FetchType.EAGER*/)
    @JoinColumns( {
            @JoinColumn(name = "fid", referencedColumnName = "fid"),
            @JoinColumn(name = "cid", referencedColumnName = "cid")
    })
    private Cours cours;

    @ManyToOne(optional = false/*, fetch = FetchType.EAGER*/)
    @JoinColumns( {
            @JoinColumn(name = "batiment", referencedColumnName = "batiment"),
            @JoinColumn(name = "numero_salle", referencedColumnName = "numero_salle")
    })
    private Salle salle;


   public Seance() {}

    public Seance(Cours cours, Salle salle) {
        this.cours = cours;
        this.salle = salle;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seance)) return false;

        Seance seance = (Seance) o;

        return (getCours() != null ? getCours().equals(seance.getCours()) : seance.getCours() == null) && (getSalle() != null ? getSalle().equals(seance.getSalle()) : seance.getSalle() == null);
    }

    @Override
    public int hashCode() {
        int result = getCours() != null ? getCours().hashCode() : 0;
        result = 31 * result + (getSalle() != null ? getSalle().hashCode() : 0);
        return result;
    }
}
