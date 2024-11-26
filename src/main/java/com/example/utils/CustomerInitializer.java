package com.example.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.Product;

public class CustomerInitializer {
  public static List<Customer> getCustomers() {
    // Инициализация продуктов
    Product book1 = new Product(1L, "Effective Java", "Books", new BigDecimal("150"));
    Product book2 = new Product(2L, "Clean Code", "Books", new BigDecimal("120"));
    Product book3 = new Product(3L, "Java Concurrency in Practice", "Books", new BigDecimal("100"));
    Product toy1 = new Product(4L, "Toy Car", "Toys", new BigDecimal("50"));
    Product toy2 = new Product(5L, "Doll", "Toys", new BigDecimal("30"));
    Product childrenProduct1 = new Product(6L, "Baby Powder", "Children's products", new BigDecimal("25"));
    Product childrenProduct2 = new Product(7L, "Diapers", "Children's products", new BigDecimal("40"));
    Product electronics1 = new Product(8L, "Smartphone", "Electronics", new BigDecimal("500"));
    Product electronics2 = new Product(9L, "Laptop", "Electronics", new BigDecimal("1000"));
    Product toy3 = new Product(10L, "Lego Set", "Toys", new BigDecimal("60"));

    // Инициализация заказов для клиента 1
    Order order1 = new Order(1L, LocalDate.of(2021, 2, 15), LocalDate.of(2021, 2, 20), "Delivered", Set.of(book1, toy1, book3, book2));
    Order order2 = new Order(2L, LocalDate.of(2021, 3, 14), LocalDate.of(2021, 3, 19), "Delivered", Set.of(book2, childrenProduct1));
    Order order3 = new Order(3L, LocalDate.of(2021, 1, 10), LocalDate.of(2021, 1, 15), "Delivered", Set.of(electronics1, toy2));
    Order order4 = new Order(4L, LocalDate.of(2021, 4, 20), LocalDate.of(2021, 4, 25), "Shipped", Set.of(toy3, electronics2));
    Order order5 = new Order(5L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 20), "Delivered", Set.of(book3, childrenProduct2));

    // Инициализация заказов для клиента 2
    Order order6 = new Order(6L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 20), "Delivered", Set.of(toy2, childrenProduct1));
    Order order7 = new Order(7L, LocalDate.of(2021, 5, 10), LocalDate.of(2021, 5, 15), "Delivered", Set.of(book1, toy1));
    Order order8 = new Order(8L, LocalDate.of(2021, 6, 5), LocalDate.of(2021, 6, 10), "Delivered", Set.of(electronics1, childrenProduct2));
    Order order9 = new Order(9L, LocalDate.of(2021, 7, 1), LocalDate.of(2021, 7, 5), "Shipped", Set.of(toy3, book2));
    Order order10 = new Order(10L, LocalDate.of(2021, 8, 1), LocalDate.of(2021, 8, 5), "Delivered", Set.of(toy1, electronics2));

    // Инициализация заказов для клиента 3
    Order order11 = new Order(11L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 20), "Delivered", Set.of(toy3, electronics1, toy1, toy2));
    Order order12 = new Order(12L, LocalDate.of(2021, 4, 14), LocalDate.of(2021, 4, 19), "Delivered", Set.of(book1, toy2));
    Order order13 = new Order(13L, LocalDate.of(2021, 5, 1), LocalDate.of(2021, 5, 5), "Delivered", Set.of(book3, childrenProduct2));
    Order order14 = new Order(14L, LocalDate.of(2021, 6, 15), LocalDate.of(2021, 6, 20), "Shipped", Set.of(electronics2, toy1));
    Order order15 = new Order(15L, LocalDate.of(2021, 7, 15), LocalDate.of(2021, 7, 20), "Delivered", Set.of(toy2, childrenProduct1));

    // Инициализация заказов для клиента 4
    Order order16 = new Order(16L, LocalDate.of(2021, 3, 1), LocalDate.of(2021, 3, 5), "Delivered", Set.of(book1, electronics1));
    Order order17 = new Order(17L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 20), "Delivered", Set.of(toy3, childrenProduct2));
    Order order18 = new Order(18L, LocalDate.of(2021, 4, 1), LocalDate.of(2021, 4, 5), "Delivered", Set.of(book2, toy2));
    Order order19 = new Order(19L, LocalDate.of(2021, 5, 1), LocalDate.of(2021, 5, 5), "Delivered", Set.of(book3, electronics2));
    Order order20 = new Order(20L, LocalDate.of(2021, 6, 1), LocalDate.of(2021, 6, 5), "Shipped", Set.of(toy1, childrenProduct1));

    // Инициализация заказов для клиента 5
    Order order21 = new Order(21L, LocalDate.of(2021, 2, 14), LocalDate.of(2021, 2, 19), "Delivered", Set.of(book1, toy3));
    Order order22 = new Order(22L, LocalDate.of(2021, 3, 10), LocalDate.of(2021, 3, 15), "Delivered", Set.of(electronics1, toy2));
    Order order23 = new Order(23L, LocalDate.of(2021, 4, 1), LocalDate.of(2021, 4, 5), "Delivered", Set.of(book3, childrenProduct1));
    Order order24 = new Order(24L, LocalDate.of(2021, 5, 20), LocalDate.of(2021, 5, 25), "Delivered", Set.of(toy1, electronics2));
    Order order25 = new Order(25L, LocalDate.of(2021, 6, 15), LocalDate.of(2021, 6, 20), "Delivered", Set.of(toy2, childrenProduct2));

    // Инициализация клиентов
    Customer customer1 = new Customer(1L, "John Doe", 2L, Set.of(order1, order2, order3, order4, order5));
    Customer customer2 = new Customer(2L, "Jane Roe", 1L, Set.of(order6, order7, order8, order9, order10));
    Customer customer3 = new Customer(3L, "Alice Smith", 2L, Set.of(order11, order12, order13, order14, order15));
    Customer customer4 = new Customer(4L, "Bob Johnson", 3L, Set.of(order16, order17, order18, order19, order20));
    Customer customer5 = new Customer(5L, "Charlie Brown", 1L, Set.of(order21, order22, order23, order24, order25));

    return List.of(customer1, customer2, customer3, customer4, customer5);
  }
}
