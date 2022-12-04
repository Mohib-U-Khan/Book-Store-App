/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

import javafx.scene.control.CheckBox;

public class Book {
  private String name;
  private double price;
  private CheckBox select;

  public Book(String name, double price) {
    this.name = name;
    this.price = price;
    this.select = new CheckBox();
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
  
  public CheckBox getSelect(){
      return this.select;
  }
  
  public void setSelect(CheckBox select){
      this.select = select;
  }
}
