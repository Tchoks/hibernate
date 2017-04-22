package domain;

import javax.persistence.*;

/**
 * Created by marti on 16/04/2017.
 */

@Entity
@Table(name = "Seance")
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sid")
    private int id;

    @ManyToOne(optional = false)
    @JoinColumns( {
            @JoinColumn(name = "fid", referencedColumnName = "fid"),
            @JoinColumn(name = "cid", referencedColumnName = "cid")
    })
    private Cours cours;

    @ManyToOne(optional = false)
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
}
