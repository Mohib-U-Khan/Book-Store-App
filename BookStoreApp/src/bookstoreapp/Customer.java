/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.Math;

public class Customer extends Account {

    private String status;
    private int points;
    private int id;
    private double totalPrice = 0;

    public Customer(String username, String password) {
        super(username, password);
        this.points = 0;
        this.status = "S";
    }

    public String getUsername(){
        return getUser();
    }
    
    public String getPassword(){
        return getPass();
    }
    
    public int getPoints(){
        return this.points;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }

    public String checkMember() {
        if (this.points >= 1000) {
            this.status = "Gold";
        } else {
            this.status = "Silver";
        }
        return status;
    }

    public void buy(Book book) {
        this.points += (int) Math.floor(book.getPrice()) * 10;
        checkMember();
    }

    public void pointsbuy(ArrayList<Book> books) { 
        while((this.points - 100) >= 0){
            this.totalPrice--;
            this.points = this.points - 100;
            if(this.points == 0 || this.totalPrice == 0)
                break;
        }
        
        for(Book b : books){
            this.points += (int) Math.floor(this.totalPrice) * 10;
        }
        
        checkMember();
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public double getTotalPrice(){
        return this.totalPrice;
    }
    
    public void setTotalPrice(double total){
        this.totalPrice = total;
    }
    
    @Override
    public String toString() {
        return "Welcome " + super.getUser() + ". You have " + getPoints() + " points. Your status is " + getStatus() + ".";
    }
}