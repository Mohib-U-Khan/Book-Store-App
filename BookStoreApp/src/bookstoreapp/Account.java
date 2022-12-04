/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Account {
  private String username;
  private String password;
  private Customer currentUser;
  private int points;
  public ArrayList<Book> books = new ArrayList<Book>();
  public ArrayList<Customer> customers = new ArrayList<Customer>();
  
  public Account(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUser(){
    return this.username;
  }

  public String getPass(){
    return this.password;
  }
  
  public Customer getCurrentCustomer(){
      return this.currentUser;
  }
  
  public void setCurrentCustomer(int index){
      this.currentUser = customers.get(index);
  }
  
  public ArrayList<Customer> getCustomers(){
      return customers;
  }
  
  public ArrayList<Book> getBooks(){
      return books;
  }

  public void saveBooks() throws IOException {
    FileWriter writer = new FileWriter("books.txt");
    writer.write("");
    for (int i = 0; i < books.size(); i++) {
      writer.append("" + books.get(i).getName() + "\n");
      writer.append("" + books.get(i).getPrice() + "\n");
    }
    writer.close();
  }

  public void saveCustomers() throws IOException {
    FileWriter writer = new FileWriter("customers.txt");
    writer.write("");
    for (int i = 0; i < customers.size(); i++) {
      writer.append("" + customers.get(i).getUser() + "\n");
      writer.append("" + customers.get(i).getPass() + "\n");
      writer.append("" + customers.get(i).getPoints() + "\n");
    }
    writer.close();
  }
  
    public void readBooks() throws FileNotFoundException, IOException {
    File f  = new File("books.txt");
    
    //Added
    if(f.exists() == false)
        f.createNewFile();

    Scanner s = new Scanner(f);
    String ch="";
    String num;
        while(s.hasNextLine()){
          ch = s.nextLine();
         num = s.nextLine();
         books.add(new Book(ch, Double.parseDouble(num)));
        }
  }

  public void readCustomers() throws FileNotFoundException, IOException{
    File f = new File("customers.txt");
    
    //Added
    if(f.exists() == false)
        f.createNewFile();
    
    Scanner s = new Scanner(f);
    String name = "";
    String password = "";
    String points = "";
    int i=0;
        while(s.hasNextLine()){
          name  = s.nextLine();
          password = s.nextLine();
          points = s.nextLine();
        customers.add(new Customer(name, password));
        customers.get(i).setPoints(Integer.parseInt(points));
        i++;
        }
  }
  
  public int login(String username, String password) {
      int login = 99;
      for(int i =0; i < this.customers.size(); i++){
          if ((username.equals(this.customers.get(i).getUser()) && (password.equals(this.customers.get(i).getPass())))) {
              login = i;
              setCurrentCustomer(login);
          }
      }
      return login;
  }
} 