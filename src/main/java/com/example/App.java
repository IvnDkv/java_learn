package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.Product;
import com.example.utils.CustomerInitializer;

public class App {
  public static void main( String[] args ) {
    List<Customer> customers = CustomerInitializer.getCustomers();

    //1. Получите список продуктов из категории "Books" с ценой более 100.
    List<Product> booksPriceGt100 = customers.stream()
      .flatMap(c -> c.getOrders().stream())
      .flatMap(o -> o.getProducts().stream())
      .filter(p -> p.getCategory().equals("Books") && p.getPrice().compareTo(new BigDecimal("100")) > 0)
      .collect(Collectors.toList());

    System.out.println("***********************************************");
    System.out.println(booksPriceGt100);

    //2. Получите список заказов с продуктами из категории "Children's products".
    List<Order> childrenProducts = customers.stream()
      .flatMap(c -> c.getOrders().stream())
      .filter(o -> o.getProducts().stream().anyMatch(p -> p.getCategory().equals("Children's products")))
      .collect(Collectors.toList());

    System.out.println("***********************************************");
    System.out.println(childrenProducts);

    //3. Получите список продуктов из категории "Toys" и примените скидку 10% и получите сумму всех продуктов.
    double totalPriceToy = customers.stream()
      .flatMap(c -> c.getOrders().stream())
      .flatMap(o -> o.getProducts().stream())
      .filter(p -> p.getCategory().equals("Toys"))
      .collect(Collectors.summingDouble(p -> p.getPrice().multiply(BigDecimal.valueOf(0.9)).doubleValue()));

    System.out.println("***********************************************");
    System.out.println(totalPriceToy);

    //4. Получите список продуктов, заказанных клиентом второго уровня между 01-фев-2021 и 01-апр-2021.
    List<Product> clientSecondLevel = customers.stream()
      .filter(c -> c.getLevel() == 2)
      .flatMap(c -> c.getOrders().stream())
      .filter(o -> o.getOrderDate().isAfter(LocalDate.of(2021, 02, 1)))
      .filter(o -> o.getOrderDate().isBefore(LocalDate.of(2021, 04, 1)))
      .flatMap(o -> o.getProducts().stream())
      .collect(Collectors.toList());

    System.out.println("***********************************************");
    System.out.println(clientSecondLevel);

    //5. Получите топ 2 самые дешевые продукты из категории "Books".
    List<Product> booksLowPrice = customers.stream()
      .flatMap(c -> c.getOrders().stream())
      .flatMap(o -> o.getProducts().stream())
      .filter(p -> p.getCategory().equals("Books"))
      .sorted((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()))
      .limit(2)
      .collect(Collectors.toList());

    System.out.println("***********************************************");
    System.out.println(booksLowPrice);

    //6. Получите 3 самых последних сделанных заказа.
    List<Order> threeLast = customers.stream()
      .flatMap(c -> c.getOrders().stream())
      .sorted((o1, o2) -> o1.getOrderDate().compareTo(o2.getOrderDate()))
      .skip(customers.stream().flatMap(c -> c.getOrders().stream()).count() - 3)
      .collect(Collectors.toList());

    System.out.println("***********************************************");
    System.out.println(threeLast);

    //7. Получите список заказов, сделанных 15-марта-2021, выведите id заказов в консоль и затем верните список их родуктов.
    List<Product> productsAtMarch = customers.stream()
      .flatMap(c -> c.getOrders().stream())
      .filter(o -> o.getOrderDate().equals(LocalDate.of(2021, 3, 15)))
      .peek(o -> System.out.println(o.getId()))
      .flatMap(o -> o.getProducts().stream())
      .collect(Collectors.toList());

    System.out.println("***********************************************");
    System.out.println(productsAtMarch);

    //8. Рассчитайте общую сумму всех заказов, сделанных в феврале 2021.
    double sumOrderFebrary = customers.stream()
      .flatMap(c -> c.getOrders().stream())
      .filter(o -> o.getOrderDate().getMonthValue() == 2)
      .flatMap(o -> o.getProducts().stream())
      .collect(Collectors.summingDouble(p -> p.getPrice().doubleValue()));

    System.out.println("***********************************************");
    System.out.println(sumOrderFebrary);

    //9. Рассчитайте средний платеж по заказам, сделанным 14-марта-2021.
    double avaragePriceMarch = customers.stream()
      .flatMap(c -> c.getOrders().stream())
      .filter(o -> o.getOrderDate().equals(LocalDate.of(2021, 3, 14)))
      .flatMap(o -> o.getProducts().stream())
      .collect(Collectors.averagingDouble(p -> p.getPrice().doubleValue()));

    System.out.println("***********************************************");
    System.out.println(avaragePriceMarch);

    //10. Получите набор статистических данных (сумма, среднее, максимум, минимум, количество) для всех продуктов категории "Книги".
    Supplier<Stream<Product>> streamProduct = () -> customers.stream()
      .flatMap(c -> c.getOrders().stream())
      .flatMap(o -> o.getProducts().stream())
      .filter(p -> p.getCategory().equals("Books"));
    
    long bookQuantity = streamProduct.get().count();

    double bookMax = 0;
    double bookMin = 0;

    Optional<Product> optionalMax = streamProduct.get()
      .max((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));
    if (optionalMax.isPresent()) {
      bookMax = optionalMax.get().getPrice().doubleValue();
    }

    Optional<Product> optionalMin = streamProduct.get()
      .min((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));
    if (optionalMin.isPresent()) {
      bookMin = optionalMin.get().getPrice().doubleValue();
    }

    double bookAverage = streamProduct.get()
      .collect(Collectors.averagingDouble(p -> p.getPrice().doubleValue()));

    double bookSum = streamProduct.get()
      .collect(Collectors.summingDouble(p -> p.getPrice().doubleValue()));

    System.out.println("***********************************************");
    System.out.println("bookQuantity: " + bookQuantity);
    System.out.println("bookMax: " + bookMax);
    System.out.println("bookMin: " + bookMin);
    System.out.println("bookAverage: " + bookAverage);
    System.out.println("bookSum: " + bookSum);

    //11. Получите данные Map<Long, Integer> → key - id заказа, value - кол-во товаров в заказе
    Map<Long, Integer> mapOrderIdtoProductQuantity = customers.stream()
      .flatMap(c -> c.getOrders().stream())
      .collect(Collectors.groupingBy(Order::getId, Collectors.summingInt(o -> o.getProducts().size())));

    System.out.println("***********************************************");
    System.out.println(mapOrderIdtoProductQuantity);

    //12. Создайте Map<Customer, List<Order>> → key - покупатель, value - список его заказов
    Map<Customer, List<Order>> mapCustomerToOrder = customers.stream()
      .collect(Collectors.toMap(c -> c, c -> new ArrayList<Order>(c.getOrders())));

    System.out.println("***********************************************");
    System.out.println(mapCustomerToOrder);

    //13. Создайте Map<Order, Double> → key - заказ, value - общая сумма продуктов заказа.
    Map<Order, Double> totalOrderSum = customers.stream()
      .flatMap( c -> c.getOrders().stream())
      .collect(Collectors.groupingBy(
        o -> o,
        Collectors.flatMapping(
          o -> o.getProducts().stream(),
          Collectors.summingDouble(p -> p.getPrice().doubleValue())
      )));

    System.out.println("***********************************************");
    System.out.println(totalOrderSum);

    //14. Получите Map<String, List<String>> → key - категория, value - список названий товаров в категории
    Map<String, List<String>> mapCategoryToProductName = customers.stream()
      .flatMap(c -> c.getOrders().stream())
      .flatMap(o -> o.getProducts().stream())
      .collect(Collectors.groupingBy(Product::getCategory, Collectors.mapping(Product::getName, Collectors.toList())));

    System.out.println("***********************************************");
    System.out.println(mapCategoryToProductName);

    //15. Получите Map<String, Product> → самый дорогой продукт по каждой категории.
    Map<String, Product> topProductInCategory = customers.stream()
    .flatMap(c -> c.getOrders().stream())
    .flatMap(o -> o.getProducts().stream())
    .collect(Collectors.groupingBy(
      Product::getCategory,
      Collectors.collectingAndThen(
        Collectors.maxBy((p1, p2) -> p1.getPrice().compareTo(p2.getPrice())),
        Optional::get
      )));
      
    System.out.println("***********************************************");
    System.out.println(topProductInCategory);

  }
}
