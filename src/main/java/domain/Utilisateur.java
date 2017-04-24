package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @authors Martin Tchokonthe And Mohammed Sylla
 * @date on 17/04/2017.
 */

@Entity
@Table(name = "Utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "utilisateur_id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @ManyToMany(targetEntity = Droit.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name="gestion_droits",
            joinColumns=@JoinColumn(name="utilisateur_id"),
            inverseJoinColumns=@JoinColumn(name="droit_id")
    )
    private Set<Droit> droits;


    public Utilisateur() { droits = new HashSet<>(); }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Set<Droit> getDroits() {
        return droits;
    }

    public void setDroits(Set<Droit> droits) {
        this.droits = droits;
    }

    public void addToDroits(Droit droit) {
        this.getDroits().add(droit);
        droit.getUtilisateurs().add(this);
    }

}
