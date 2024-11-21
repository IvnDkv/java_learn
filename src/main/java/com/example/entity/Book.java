package com.example.entity;

public class Book {
  private String title;
  private String author;
  private int year;
  private Long bookId;
  
  public Book(String title, String author, int year, Long bookId) {
    this.title = title;
    this.author = author;
    this.year = year;
    this.bookId = bookId;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public int getYear() {
    return year;
  }

  public Long getBookId() {
    return bookId;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  @Override
  public String toString() {
    return "Book [title=" + title + ", author=" + author + ", year=" + year + ", bookId=" + bookId + "]";
  }

}
