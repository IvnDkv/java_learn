package com.example.operations.processors;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.example.TransactionHelper;
import com.example.account.Account;
import com.example.account.AccountService;
import com.example.operations.ConsoleOperationType;
import com.example.operations.OperationCommandProcessor;

@Component
public class AccountTransferProcessor implements OperationCommandProcessor{
  private Scanner scanner;
  private TransactionHelper transactionHelper;
  private AccountService accountService;

  public AccountTransferProcessor(Scanner scanner, TransactionHelper transactionHelper, AccountService accountService) {
    this.scanner = scanner;
    this.transactionHelper = transactionHelper;
    this.accountService = accountService;
  }

  @Override
  public void execute() {
    System.out.println("Enter from account id:");
    Long idFrom = scanner.nextLong();
    System.out.println("Enter to account id:");
    Long idTo = scanner.nextLong();
    System.out.println("Enter amount:");
    int amount = scanner.nextInt();
    Account accountFrom = accountService.findAccountById(idFrom);
    Account accountTo = accountService.findAccountById(idTo);
    accountFrom.setMoneyAmount(accountFrom.getMoneyAmount() - amount);
    accountTo.setMoneyAmount(accountTo.getMoneyAmount() + amount);
    transactionHelper.executeInTransaction(session -> {
      session.merge(accountFrom);
      session.merge(accountTo);
    });
    System.out.println("Account withdraw");
  }

  @Override
  public ConsoleOperationType getOperationType() {
    return ConsoleOperationType.ACCOUNT_TRANSFER;
  }
  
}
