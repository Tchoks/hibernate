package domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by marti on 22/04/2017.
 */

@Embeddable
public class CoursId implements Serializable {

    @Column(name = "cid")
    private int id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "fid")
    private Formation formation;

    public CoursId() {}

    public CoursId(int id, Formation formation) {
        this.id = id;
        this.formation = formation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (!(o instanceof CoursId)) return false;

        CoursId coursId = (CoursId) o;

        if (getId() != coursId.getId()) return false;
        return getFormation().equals(coursId.getFormation());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getFormation().hashCode();
        return result;
    }
}
