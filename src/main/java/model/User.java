package model;

import utils.Hashing;

public class User {

  public int id;
  public String firstname;
  public String lastname;
  public String email;
  private String password;
  private String token;
  private static String created_at;

  public User(int id, String firstname, String lastname, String password, String email, String created_at) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.password = Hashing.shaWithSalt(password);
    this.email = email;
    this.created_at = created_at;
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

  public static String getCreatedTime() {
    return created_at;
  }

  public void setCreatedTime(String created_at) {
    this.created_at = created_at;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
