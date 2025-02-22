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
public class CloseAccountProcessor implements OperationCommandProcessor{
  private Scanner scanner;
  private TransactionHelper transactionHelper;
  private UserService userService;
  private AccountService accountService;

  public CloseAccountProcessor(Scanner scanner, TransactionHelper transactionHelper, UserService userService,
      AccountService accountService) {
    this.scanner = scanner;
    this.transactionHelper = transactionHelper;
    this.userService = userService;
    this.accountService = accountService;
  }

  @Override
  public void execute() {
    System.out.println("Enter account id:");
    Long id = scanner.nextLong();
    Account account = accountService.findAccountById(id);
    transactionHelper.executeInTransaction(session -> {
      session.remove(account);
    });
    System.out.println("Account close");
  }

  @Override
  public ConsoleOperationType getOperationType() {
    return ConsoleOperationType.ACCOUNT_CLOSE;
  }
  
}
