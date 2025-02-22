package com.example;

import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.example.account.Account;
import com.example.user.User;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

  @Bean
  public Scanner scanner() {
    return new Scanner(System.in);
  }

  @Bean
  public SessionFactory sessionFactory() {
    org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();

    configuration
      .addAnnotatedClass(User.class)
      .addAnnotatedClass(Account.class)
      .addPackage("com.example")
      .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
      .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres")
      .setProperty("hibernate.connection.username", "postgres")
      .setProperty("hibernate.connection.password", "hiber")
      .setProperty("hibernate.show_sql", "true")
      .setProperty("hibernate.hbm2ddl.auto", "create-drop")
      .setProperty("hibernate.current_session_context_class", "thread");

    return configuration.buildSessionFactory();
  }
}
