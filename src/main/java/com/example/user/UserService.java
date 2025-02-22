package com.example.user;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class UserService {

  private final SessionFactory sessionFactory;

  public UserService(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public User createUser(String login) {
    return new User(login);
  }

  public User findUserById(Long id) {
    try (Session session = sessionFactory.openSession()) {
      return session.get(User.class, id);
    }
  }

  public List<User> findAll() {
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery("SELECT u FROM User u", User.class)
        .list();
    }
  }
}
