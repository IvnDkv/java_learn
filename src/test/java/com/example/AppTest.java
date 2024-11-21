package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;

import com.example.entity.Book;
import com.example.entity.User;
import com.example.service.LibraryService;

/**
 * Unit test for simple App.
 */
public class AppTest {

    List<User> users = Arrays.asList(
      new User("User 1", 20, UUID.randomUUID().getLeastSignificantBits()),
      new User("User 2", 30, UUID.randomUUID().getLeastSignificantBits()),
      new User("User 3", 40, UUID.randomUUID().getLeastSignificantBits())
    );

    List<Book> books = Arrays.asList(
      new Book("Book 1", "Author 1", 2000, UUID.randomUUID().getLeastSignificantBits()),
      new Book("Book 2", "Author 2", 2001, UUID.randomUUID().getLeastSignificantBits()),
      new Book("Book 3", "Author 3", 2002, UUID.randomUUID().getLeastSignificantBits()),
      new Book("Book 4", "Author 4", 2003, UUID.randomUUID().getLeastSignificantBits()),
      new Book("Book 5", "Author 5", 2004, UUID.randomUUID().getLeastSignificantBits())
    );

    LibraryService ls = new LibraryService(users, books);

  @Test
  public void libraryServiceTest() {
    boolean takeBook1 = ls.takeBook(users.get(0).getUserId(), books.get(0).getBookId());
    boolean takeBook2 = ls.takeBook(users.get(1).getUserId(), books.get(1).getBookId());
    boolean takeBook3 = ls.takeBook(users.get(2).getUserId(), books.get(2).getBookId());
    boolean takeBook4 = ls.takeBook(users.get(0).getUserId(), books.get(1).getBookId());

    assertTrue( takeBook1 );
    assertTrue( takeBook2 );
    assertTrue( takeBook3 );
    assertFalse( takeBook4 );

    Set<Book> allAvailableBooks = ls.getAllAvailableBooks();

    assertEquals(2, allAvailableBooks.size());

    Set<Book> user1 = ls.getUserBooks(users.get(0).getUserId());
    Set<Book> user2 = ls.getUserBooks(users.get(1).getUserId());
    Set<Book> user3 = ls.getUserBooks(users.get(2).getUserId());

    assertTrue( user1.contains(books.get(0)) );
    assertTrue( user2.contains(books.get(1)) );
    assertTrue( user3.contains(books.get(2)) );

    ls.returnBook(users.get(0).getUserId(), books.get(0).getBookId());
    Set<Book> user4 = ls.getUserBooks(users.get(0).getUserId());

    assertEquals(0, user4.size());

    assertEquals(3, ls.getAllAvailableBooks().size());
    assertEquals(5, ls.getAllBooks().size());
  }
}
