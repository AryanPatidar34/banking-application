package com.bankingapplication.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name ="transaction")
public class Transaction {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @Column(name ="account_id")
 private Long accountId;
     private double amount; 
     private String type;
     private LocalDateTime timestamp;

     public Transaction(){
        super();
     }

     public Transaction(long id,long accountId, double amount, String type, LocalDateTime timestamp){
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
        

        
     }

     public long getId() {
         return id;
     }

     public void setId(long id) {
         this.id = id;
     }

     public long getAccountId() {
         return accountId;
     }

     public void setAccountId(long accountId) {
         this.accountId = accountId;
     }

     public double getAmount() {
         return amount;
     }

     public void setAmount(double amount) {
         this.amount = amount;
     }

     public String getType() {
         return type;
     }

     public void setType(String type) {
         this.type = type;
     }

     public LocalDateTime getTimestamp() {
         return timestamp;
     }

     public void setTimestamp(LocalDateTime timestamp) {
         this.timestamp = timestamp;
     }



  
}
