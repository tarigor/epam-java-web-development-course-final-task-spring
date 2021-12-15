package com.epam.hotelgrodnoinn.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * The entity of user table.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    private long userID;
    @Column(name = "first_name")
    @Pattern(regexp = "^[A-Z]+([._]?[a-zA-Z]+)*$")
    private String firstName;
    @Column(name = "last_name")
    @Pattern(regexp = "^[A-Z]+([._]?[a-zA-Z]+)*$")
    private String lastName;
    @Column(name = "user_type")
    private String userType;
    @Column(name = "email")
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")
    private String email;
    @Column(name = "password")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!=])(?=\\S+$).{4,}$")
    private String password;
    @Transient
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!=])(?=\\S+$).{4,}$")
    private String repeatedPassword;
    @Column(name = "account")
    private Double account;

    public User() {
    }

    public User(long userID,
                @Pattern(regexp = "^[A-Z]+([._]?[a-zA-Z]+)*$") String firstName,
                @Pattern(regexp = "^[A-Z]+([._]?[a-zA-Z]+)*$") String lastName, String userType,
                @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}") String email,
                @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!=])(?=\\S+$).{4,}$") String password,
                @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!=])(?=\\S+$).{4,}$") String repeatedPassword) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
        this.email = email;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
    }

    public User(long userID,
                @Pattern(regexp = "^[A-Z]+([._]?[a-zA-Z]+)*$") String firstName,
                @Pattern(regexp = "^[A-Z]+([._]?[a-zA-Z]+)*$") String lastName, String userType,
                @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}") String email,
                @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!=])(?=\\S+$).{4,}$") String password,
                Double account) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
        this.email = email;
        this.password = password;
        this.account = account;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(userType, user.userType) &&
                email.equals(user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(repeatedPassword, user.repeatedPassword) &&
                Objects.equals(account, user.account);
    }

    @Override
    public int hashCode() {
        return Math.abs(Objects.hash(email));
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userType='" + userType + '\'' +
                ", email='" + email + '\'' +
                ", account=" + account +
                '}';
    }
}
