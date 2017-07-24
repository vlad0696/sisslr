package org.ipps.sisslr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Gramovich_V on 20.07.2017.
 */
@Entity
public class Address implements Serializable {

    private  String city;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int   id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
