package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
  public static void main( String[] args ) {
    
    String info = "";
    String pathIn = "./src/main/resources/exceptions/source.txt".replace("/", File.separator);
    String pathOut = "./src/main/resources/exceptions/destination.txt".replace("/", File.separator);

    // Read file
    try (Scanner sc = new Scanner(new File(pathIn))) {
      while (sc.hasNextLine()) {
        info = sc.nextLine();
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }

    //Write to file
    try (FileWriter writer = new FileWriter(new File(pathOut))) {
      writer.write(info);
    } catch (IOException e) {
      System.out.println("Cant write file");
    }

  }
}
