package com.example.entity;

public class User {
  private String name;
  private int age;
  private Long userId;
  
  public User(String name, int age, Long userId) {
    this.name = name;
    this.age = age;
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public Long getUserId() {
    return userId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "User [name=" + name + ", age=" + age + ", userId=" + userId + "]";
  }

  
}
