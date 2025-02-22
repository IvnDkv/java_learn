package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.operations.processors.AccountTransferProcessor;
import com.example.operations.processors.AccountWithdrawProcessor;
import com.example.operations.processors.CloseAccountProcessor;
import com.example.operations.processors.CreateAccountProcessor;
import com.example.operations.processors.CreateUserProcessor;
import com.example.operations.processors.DepositAccountProcessor;
import com.example.operations.processors.ShowAllUsersProcessor;

public class App {
  public static void main( String[] args ) {
    var context = new AnnotationConfigApplicationContext("com.example");
  }
}
