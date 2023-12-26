package com.groupware.employwise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.View;
import org.ektorp.support.Views;

import java.util.UUID;

public class User extends CouchDbDocument {

    @JsonProperty("name")
    private String Name;
    @JsonProperty("phoneNumber")
    private String PhoneNumber;
    @JsonProperty("email")
    private String Email;
    @JsonProperty("reportsTo")
    private String ReportsTo;
    @JsonProperty("profileImage")
    private String ProfileImage;
    @JsonProperty("_id")
    private String ID;

    public String getName() {
        return Name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public String getReportsTo() {
        return ReportsTo;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public String getID() {
        return ID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setReportsTo(String ReportsTo) {
        this.ReportsTo = ReportsTo;
    }

    public void setProfileImage(String ProfileImage) {
        this.ProfileImage = ProfileImage;
    }

    public void setID(String EmployeeID) {
        this.ID = EmployeeID;
    }

    public User() {
        this.Email = "";
        this.Name = "";
        this.PhoneNumber = "";
        this.ReportsTo = "";
        this.ProfileImage = "";
        this.ID = UUID.randomUUID().toString();
    }

    public User(String Name, String Email, String PhoneNumber, String ReportsTo, String ProfileImage) {
        this.Name = Name;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.ReportsTo = ReportsTo;
        this.ProfileImage = ProfileImage;
        this.ID = UUID.randomUUID().toString();
    }

}
