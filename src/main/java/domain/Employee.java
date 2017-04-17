package domain;


import javax.persistence.*;

/**
 * Created by marti on 15/04/2017.
 */


@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private int id;
    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "salary", nullable = false)
    private int salary;
    @ManyToOne(/*cascade = CascadeType.ALLfetch = FetchType.LAZY*/ optional = false)
    @JoinColumn(name = "address_id")
    private Address address;


    public Employee() {}
    public Employee(String firstname, String lastname, int salary, Address address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.salary = salary;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (getId() != employee.getId()) return false;
        if (getSalary() != employee.getSalary()) return false;
        if (!getFirstname().equals(employee.getFirstname())) return false;
        if (!getLastname().equals(employee.getLastname())) return false;
        return getAddress().equals(employee.getAddress());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getFirstname().hashCode();
        result = 31 * result + getLastname().hashCode();
        result = 31 * result + getSalary();
        result = 31 * result + getAddress().hashCode();
        return result;
    }
}

