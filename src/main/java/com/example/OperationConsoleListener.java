package com.example;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.operations.ConsoleOperationType;
import com.example.operations.OperationCommandProcessor;

@Component
public class OperationConsoleListener {
  private final Scanner scanner;
  private final Map<ConsoleOperationType, OperationCommandProcessor> processorsMap;

  public OperationConsoleListener(Scanner scanner, List<OperationCommandProcessor> processorsList) {
    this.scanner = scanner;
    this.processorsMap = processorsList.stream()
      .collect(Collectors.toMap(OperationCommandProcessor::getOperationType, p -> p));
  }

  public void start() {
    System.out.println("Console listener started");
  }

  public void endListen() {
      System.out.println("Console listener end listen");
  }
  
  public void printAllAvailableOperations() {
    processorsMap.keySet().stream().forEach(System.out::println);
  }
  
  public void listenUpdates() {
    while (!Thread.currentThread().isInterrupted()) {
      var operationType = listenNextOperation();
      if (operationType == null) {
        return;
      }
      processNextOperation(operationType);
    }
  }

  private ConsoleOperationType listenNextOperation() {
    System.out.println("\nPlease type next operation: ");
    printAllAvailableOperations();
    System.out.println();
    while (!Thread.currentThread().isInterrupted()) {
      var nextOperation = scanner.nextLine();
      try {
        return ConsoleOperationType.valueOf(nextOperation);
      } catch (IllegalArgumentException e) {
        System.out.println("No such command found");
      }
    }
    return null;
  }

  private void processNextOperation(ConsoleOperationType operation) {
    try {
      var processor = processorsMap.get(operation);
      processor.execute();
    } catch (Exception e) {
      System.out.printf(
        "Error executing command %s: error=%s%n", operation,
        e.getMessage()
      );
    }
  }
}
