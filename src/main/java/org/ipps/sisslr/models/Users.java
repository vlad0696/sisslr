package org.ipps.sisslr.models;


import javax.persistence.*;

/**
 * Created by Gramovich_V on 24.07.2017.
 */
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int Id;

    private  String FirstName;

    private  String LastName;

    private  String Email;

    private  String PhoneNumber;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getfirstname() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getlastname() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setemail(String email) {
        Email = email;
    }


    public String getphonenumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }


}
