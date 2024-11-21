package com.example.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.entity.Book;
import com.example.entity.User;

public class LibraryService {
  private List<User> users;
  private List<Book> books;
  private HashMap<Long, Book> idToBookMap;
  private HashMap<Long, User> idToUserMap;
  private HashMap<Long, Long> takenBookMap;
  private HashMap<Long, Set<Long>> userTakenBooksMap;

  public LibraryService(List<User> users, List<Book> books) {
    if (users.isEmpty() || books.isEmpty()) {
      throw new IllegalArgumentException("Wrong arguments");
    }
    this.users = users;
    this.books = books;

    idToBookMap = new HashMap<>();
    for (Book book : books) {
      idToBookMap.put(book.getBookId(), book);
    }

    idToUserMap = new HashMap<>();
    for (User user : users) {
      idToUserMap.put(user.getUserId(), user);
    }

    takenBookMap = new HashMap<>();
    for (Book book : books) {
      takenBookMap.put(book.getBookId(), null);
    }

    userTakenBooksMap = new HashMap<>();
    for (User user : users) {
      userTakenBooksMap.put(user.getUserId(), new HashSet<>());
    }
  }

  public List<Book> getAllBooks() {
    return books;
  }

  public Set<Book> getAllAvailableBooks() {
    Set<Book> availableBooks = new HashSet<>();
    for (Map.Entry<Long, Long> bookUser : takenBookMap.entrySet()) {
      if (bookUser.getValue() == null) {
        availableBooks.add(idToBookMap.get(bookUser.getKey()));
      }
    }
    return availableBooks;
  }

  public Set<Book> getUserBooks(Long userId) {
    if (idToUserMap.get(userId) == null) {
      throw new IllegalArgumentException("Wrong userId");
    }
    Set<Book> userBooks = new HashSet<>();
    for (Long bookId : userTakenBooksMap.get(userId)) {
      userBooks.add(idToBookMap.get(bookId));
    }
    return userBooks;
  }

  public boolean takeBook(Long userId, Long bookId) {
    if (idToUserMap.get(userId) == null ||
        idToBookMap.get(bookId) == null) {
      throw new IllegalArgumentException("Wrong ID");
    }
    if (takenBookMap.get(bookId) == null) {
      takenBookMap.put(bookId, userId);
      userTakenBooksMap.get(userId).add(bookId);
      return true;
    } else {
      return false;
    }
  }

  public void returnBook(Long userId, Long bookId) {
    if (idToUserMap.get(userId) == null ||
        idToBookMap.get(bookId) == null) {
      throw new IllegalArgumentException("Wrong ID");
    }
    userTakenBooksMap.get(userId).remove(bookId);
    takenBookMap.put(bookId, null);
  }
  
}
