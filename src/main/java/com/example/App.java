package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.example.entity.Book;
import com.example.entity.User;
import com.example.service.LibraryService;

public class App {
  public static void main( String[] args ) {
    List<User> users = Arrays.asList(
      new User("John", 20, UUID.randomUUID().getLeastSignificantBits()),
      new User("Bob", 40, UUID.randomUUID().getLeastSignificantBits()),
      new User("Alice", 30, UUID.randomUUID().getLeastSignificantBits())
    );

    List<Book> books = Arrays.asList(
      new Book("Book 1", "Author 1", 2000, UUID.randomUUID().getLeastSignificantBits()),
      new Book("Book 2", "Author 2", 2001, UUID.randomUUID().getLeastSignificantBits()),
      new Book("Book 3", "Author 3", 2002, UUID.randomUUID().getLeastSignificantBits()),
      new Book("Book 4", "Author 4", 2003, UUID.randomUUID().getLeastSignificantBits()),
      new Book("Book 5", "Author 5", 2004, UUID.randomUUID().getLeastSignificantBits())
    );

    LibraryService ls = new LibraryService(users, books);
  }
}
