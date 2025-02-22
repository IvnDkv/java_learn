package com.example.operations.processors;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.example.TransactionHelper;
import com.example.account.Account;
import com.example.account.AccountService;
import com.example.operations.ConsoleOperationType;
import com.example.operations.OperationCommandProcessor;
import com.example.user.User;
import com.example.user.UserService;

@Component
public class CreateUserProcessor implements OperationCommandProcessor{
  private Scanner scanner;
  private TransactionHelper transactionHelper;
  private UserService userService;
  private AccountService accountService;

  public CreateUserProcessor(Scanner scanner, TransactionHelper transactionHelper, UserService userService,
      AccountService accountService) {
    this.scanner = scanner;
    this.transactionHelper = transactionHelper;
    this.userService = userService;
    this.accountService = accountService;
  }

  @Override
  public void execute() {
    System.out.println("Enter login:");
    String login = scanner.nextLine().trim();
    User user = userService.createUser(login);
    Account account = accountService.createAccount(user);
    transactionHelper.executeInTransaction(session -> {
      session.persist(account);
      session.persist(user);
    });
    System.out.println("User created");
  }

  @Override
  public ConsoleOperationType getOperationType() {
    return ConsoleOperationType.USER_CREATE;
  }
  
}
