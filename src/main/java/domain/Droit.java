package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by marti on 24/04/2017.
 */

@Entity
@Table(name = "Droit")
public class Droit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "droit_id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "droits", targetEntity = Utilisateur.class, fetch = FetchType.EAGER)
    private Set<Utilisateur> utilisateurs;

    public Droit() { utilisateurs = new HashSet<>(); }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
}
