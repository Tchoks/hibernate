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
    @Column(name = "seance_id")
    private int id;

    private Cours cours;
    private int num_salle;
    private String nom_batiment;


}
