package com.example.operations.processors;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.example.TransactionHelper;
import com.example.account.Account;
import com.example.account.AccountService;
import com.example.operations.ConsoleOperationType;
import com.example.operations.OperationCommandProcessor;

@Component
public class AccountWithdrawProcessor implements OperationCommandProcessor{
  
  private Scanner scanner;
  private TransactionHelper transactionHelper;
  private AccountService accountService;

  public AccountWithdrawProcessor(Scanner scanner, TransactionHelper transactionHelper, AccountService accountService) {
    this.scanner = scanner;
    this.transactionHelper = transactionHelper;
    this.accountService = accountService;
  }

  @Override
  public void execute() {
    System.out.println("Enter account id:");
    Long id = scanner.nextLong();
    System.out.println("Enter amount:");
    int amount = scanner.nextInt();
    Account account = accountService.findAccountById(id);
    account.setMoneyAmount(account.getMoneyAmount() - amount);
    transactionHelper.executeInTransaction(session -> {
      session.merge(account);
    });
    System.out.println("Account withdraw");
  }

  @Override
  public ConsoleOperationType getOperationType() {
    return ConsoleOperationType.ACCOUNT_WITHDRAW;
  }
  
}
