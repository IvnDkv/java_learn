package com.example.operations;

public interface OperationCommandProcessor {
  void execute();
  ConsoleOperationType getOperationType();
}
