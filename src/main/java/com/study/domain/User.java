package com.study.domain;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    private int id;
    private String firstName;
    private String middleName; //optional
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender; //optional
    private String email;
    private String phoneNumber; //optional
    private String password;
    private Ticket ticket;

    public User(){ }

    public User(int id, String firstName, String lastName, LocalDate dateOfBirth, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User gender(String gender) {
        this.gender = gender;
        return this;
    }

    public User middleName(String middleName){
        this.middleName = middleName;
        return this;
    }

    public User phoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", ticket=" + ticket +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(middleName, user.middleName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(dateOfBirth, user.dateOfBirth) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(password, user.password) &&
                Objects.equals(ticket, user.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, dateOfBirth,
                gender, email, phoneNumber, password, ticket);
    }
}








//                  Inner static class
//    public static class UserBuilder{
//        private int id;
//        private String firstName;
//        private String middleName; //optional
//        private String lastName;
//        private LocalDate dateOfBirth;
//        private String gender; //optional
//        private String email;
//        private String phoneNumber; //optional
//        private String password;
//
//
//        //constructor for required fields
//        public UserBuilder(int id, String firstName, String lastName, LocalDate dateOfBirth, String email, String password){
//            this.id = id;
//            this.firstName = firstName;
//            this.lastName = lastName;
//            this.dateOfBirth = dateOfBirth;
//            this.email = email;
//            this.password = password;
//        }
//
//        public UserBuilder setMiddleName(String middleName){
//            this.middleName = middleName;
//            return this;
//        }
//
//        public UserBuilder setGender(String gender){
//            this.gender = gender;
//            return this;
//        }
//
//        public UserBuilder setPhoneNumber(String phoneNumber){
//            this.phoneNumber = phoneNumber;
//            return this;
//        }
//
//        public User build(){
//            return new User(this);
//        }
//
//    }
//}
