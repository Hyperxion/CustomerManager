package net.codejava.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
To map this java class with a customer table in database, we need to use @Entity annotation
provide by JPA. Because class name is the same as table name, we dont have to specify it within
@Entity(name = "")
 */
@Entity
public class Customer {

    @Id //required for Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //required for autoincrement
    private Long id;

    private String name;
    private String email;
    private String address;

    //default constructor required by hibernate
    protected Customer() {
    }

    protected Customer(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
