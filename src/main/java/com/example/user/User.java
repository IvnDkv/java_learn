package com.example.user;

import java.util.List;

import com.example.account.Account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "login", unique = true, updatable = false, nullable = false)
  private String login;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private List<Account> accountList;

  public User() {
  }

  public User(String login) {
    this.login = login;
  }

  public Long getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public List<Account> getAccountList() {
    return accountList;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public void setAccountList(List<Account> accountList) {
    this.accountList = accountList;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", login=" + login + ", accountList=" + accountList + "]";
  }

}
