package com.example;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.stereotype.Component;

@Component
public class TransactionHelper {

  private final SessionFactory sessionFactory;

  public TransactionHelper(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public void executeInTransaction(Consumer<Session> action) {
    // Transaction transaction = null;
    //   try (Session session = sessionFactory.openSession()) {
    //     transaction = session.getTransaction();
    //     transaction.begin();
        
    //     action.accept(session);

    //     session.getTransaction().commit();
    //   } catch (Exception e) {
    //     if (transaction != null) {
    //         transaction.rollback();
    //     }
    //     throw e;
    //   }
    var session = sessionFactory.getCurrentSession();
    Transaction transaction = session.getTransaction();
    if (!transaction.getStatus().equals(TransactionStatus.NOT_ACTIVE)) {
      action.accept(session);
    }
    try {
      session.beginTransaction();
      action.accept(session);
      transaction.commit();
    } catch (Exception e) {
      transaction.rollback();
      throw e;
    } finally {
      session.close();
    }
  }

  public <T> T executeInTransaction(Supplier<T> action) {
    // Transaction transaction = null;
    //   try (Session session = sessionFactory.openSession()) {
    //     transaction = session.getTransaction();
    //     transaction.begin();
        
    //     var result = action.apply(session);

    //     session.getTransaction().commit();
    //     return result;
    //   } catch (Exception e) {
    //     if (transaction != null) {
    //         transaction.rollback();
    //     }
    //     throw e;
    //   }
    var session = sessionFactory.getCurrentSession();
    Transaction transaction = session.getTransaction();
    if (!transaction.getStatus().equals(TransactionStatus.NOT_ACTIVE)) {
      return action.get();
    }
    try {
      session.beginTransaction();
      T returnValue = action.get();
      transaction.commit();
      return returnValue;
    } catch (Exception e) {
      transaction.rollback();
      throw e;
    } finally {
      session.close();
    }
  }
}

