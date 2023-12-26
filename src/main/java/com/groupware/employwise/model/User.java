package com.groupware.employwise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ektorp.support.CouchDbDocument;

import java.util.UUID;

public class User extends CouchDbDocument {

    @JsonProperty("Name")
    private String Name;
    @JsonProperty("PhoneNumber")
    private String PhoneNumber;
    @JsonProperty("Email")
    private String Email;
    @JsonProperty("ReportsTo")
    private String ReportsTo;
    @JsonProperty("ProfileImage")
    private String ProfileImage;
    @JsonProperty("EmployeeID")
    private String EmployeeID;

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

    public String getEmployeeID() {
        return EmployeeID;
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

    public void setEmployeeID(String EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public User() {
        this.Email = "";
        this.Name = "";
        this.PhoneNumber = "";
        this.ReportsTo = "";
        this.ProfileImage = "";
        this.EmployeeID = UUID.randomUUID().toString();
    }

    public User(String Name, String Email, String PhoneNumber, String ReportsTo, String ProfileImage) {
        this.Name = Name;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.ReportsTo = ReportsTo;
        this.ProfileImage = ProfileImage;
        this.EmployeeID = UUID.randomUUID().toString();
    }

}
