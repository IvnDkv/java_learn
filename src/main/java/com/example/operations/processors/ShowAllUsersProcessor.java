package com.example.operations.processors;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.operations.ConsoleOperationType;
import com.example.operations.OperationCommandProcessor;
import com.example.user.User;
import com.example.user.UserService;

@Component
public class ShowAllUsersProcessor implements OperationCommandProcessor{
  private UserService userService;

  public ShowAllUsersProcessor(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void execute() {
    List<User> userList = userService.findAll();
    userList.stream().forEach(u -> System.out.println(u.getLogin() + " " + u.getId()));
  }

  @Override
  public ConsoleOperationType getOperationType() {
    return ConsoleOperationType.SHOW_ALL_USERS;
  }
  
}
