package com.groupware.employwise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ektorp.support.CouchDbDocument;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.regex.Pattern;

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

    public static boolean validateEmail(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return email.matches(regexPattern);
    }

    public static boolean validateID(String id){
        Pattern UUID_PATTERN = Pattern.compile("^[a-fA-F0-9]{32}$");
        return UUID_PATTERN.matcher(id).matches();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^[1-9][0-9]{9}$");
    }

    public static ResponseEntity<Response> validateRequiredFields(User user){
        if(!Objects.equals(user.getEmail(), "") && !User.validateEmail(user.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new Response("Please enter a valid email", null));
        }
        if(!Objects.equals(user.getPhoneNumber(), "") && !User.validatePhoneNumber(user.getPhoneNumber())){
            return ResponseEntity
                    .badRequest()
                    .body(new Response("Please enter a valid phone number", null));
        }
        if(!Objects.equals(user.getReportsTo(), "") && !User.validateID(user.getReportsTo()))
            return ResponseEntity
                    .badRequest()
                    .body(new Response("Please enter a valid Manager ID", null));
        return null;
    }

}
