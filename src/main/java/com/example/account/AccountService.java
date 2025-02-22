package com.example.account;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.example.user.User;

@Component
public class AccountService {

  private final AccountProperties accountProperties;
  private final SessionFactory sessionFactory;

  public AccountService(AccountProperties accountProperties, SessionFactory sessionFactory) {
    this.accountProperties = accountProperties;
    this.sessionFactory = sessionFactory;
  }

  public Account createAccount(User user) {
    return new Account(accountProperties.getDefaultAmount(), user);
  }

  public Account findAccountById(Long id) {
    try (Session session = sessionFactory.openSession()) {
      return session.get(Account.class, id);
    }
  }
}
