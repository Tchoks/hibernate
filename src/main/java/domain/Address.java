package domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by marti on 16/04/2017.
 */

@Entity
@Table(name = "Address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private int id;
    @Column(name = "street_name", nullable = false, length = 50)
    private String street_name;
    @Column(name = "city_name", nullable = false, length = 50)
    private String city_name;
    @Column(name = "state_name", nullable = false, length = 50)
    private String state_name;
    @Column(name = "zipcode", nullable = false)
    private int zipcode;


    public Address() {}
    public Address(String street_name, String city_name, String state_name, int zipcode) {
        this.street_name = street_name;
        this.city_name = city_name;
        this.state_name = state_name;
        this.zipcode = zipcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (getId() != address.getId()) return false;
        if (getZipcode() != address.getZipcode()) return false;
        if (!getStreet_name().equals(address.getStreet_name())) return false;
        if (!getCity_name().equals(address.getCity_name())) return false;
        return getState_name().equals(address.getState_name());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getStreet_name().hashCode();
        result = 31 * result + getCity_name().hashCode();
        result = 31 * result + getState_name().hashCode();
        result = 31 * result + getZipcode();
        return result;
    }

}
