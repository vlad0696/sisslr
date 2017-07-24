package org.ipps.sisslr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Gramovich_V on 24.07.2017.
 */
@Entity
public class Licenses implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int   id;

    private  String license_name;

    private  int license_number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicense_name() {
        return license_name;
    }

    public void setLicense_name(String license_name) {
        this.license_name = license_name;
    }

    public int getLicense_number() {
        return license_number;
    }

    public void setLicense_number(int license_number) {
        this.license_number = license_number;
    }
}
