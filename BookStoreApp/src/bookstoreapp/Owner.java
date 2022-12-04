/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Owner extends Account {
  private static Owner instance;
  
  protected Owner(String username, String password) {
    super(username, password);
  }

  public static Owner getInstance(String username, String password) {
    if (instance == null) {
      instance = new Owner(username, password);
    }
    return instance;
  }

  public boolean addBook(String name, double price) {
    if (!super.books.isEmpty()){
      for (int i = 0; i < super.books.size(); i++){
        if (name.equals(super.books.get(i).getName())){
          return false;
        }
      }
    }
    return true;
  }

  public boolean addCustomer(String username, String password) {
    for (int i = 0; i < super.customers.size(); i++){
      if (username.equals(super.customers.get(i).getUser())){
        return false;
      }
    }
    return true;
  }


  public void deleteCustomer(Customer customer) {
    super.customers.remove(customer);
  }
  
  public void deleteBook(Book b){
      super.books.remove(b);
  }
}