package com.example.account;

import com.example.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "money_amount")
  private int moneyAmount;
  
  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;


  public Account() {
  }

  public Account(int moneyAmount, User user) {
    this.moneyAmount = moneyAmount;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public int getMoneyAmount() {
    return moneyAmount;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setMoneyAmount(int moneyAmount) {
    this.moneyAmount = moneyAmount;
  }

  @Override
  public String toString() {
    return "Account [id=" + id + ", user=" + user + ", moneyAmount=" + moneyAmount + "]";
  }

}
