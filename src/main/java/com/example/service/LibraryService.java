package com.example.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.entity.Book;
import com.example.entity.User;

public class LibraryService {
  private List<Book> books;
  private HashMap<Long, Book> idToBookMap;
  private HashMap<Long, User> idToUserMap;
  private HashMap<Long, Long> takenBookMap;
  private HashMap<Long, Set<Long>> userTakenBooksMap;

  public LibraryService(List<User> users, List<Book> books) {
    if (users.isEmpty() || books.isEmpty()) {
      throw new IllegalArgumentException("users or books list should not be empty");
    }
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
    return books.stream()
      .filter(book -> takenBookMap.get(book.getBookId()) == null)
      .collect(Collectors.toSet());
  }

  public Set<Book> getUserBooks(Long userId) {
    checkExist(userId, idToUserMap, "user");

    return userTakenBooksMap.get(userId).stream()
      .map(idToBookMap::get)
      .collect(Collectors.toSet());
  }

  public boolean takeBook(Long userId, Long bookId) {
    checkExist(userId, idToUserMap, "user");
    checkExist(bookId, idToBookMap, "book");

    if (takenBookMap.get(bookId) == null) {
      takenBookMap.put(bookId, userId);
      userTakenBooksMap.get(userId).add(bookId);
      return true;
    } else {
      return false;
    }
  }

  public void returnBook(Long userId, Long bookId) {
    checkExist(userId, idToUserMap, "user");
    checkExist(bookId, idToBookMap, "book");

    userTakenBooksMap.get(userId).remove(bookId);
    takenBookMap.put(bookId, null);
  }

  private void checkExist(Long id, Map<Long, ? extends Object> map, String entity) {
    if (id == null || !map.containsKey(id)) {
      throw new IllegalArgumentException(String.format("%s with id %d not found", entity, id));
    } 
  }
  
}
