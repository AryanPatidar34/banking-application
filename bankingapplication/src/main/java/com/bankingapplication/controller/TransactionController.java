package com.bankingapplication.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapplication.entity.Transaction;
import com.bankingapplication.repository.TransactionRepository;
import com.bankingapplication.service.TransactionService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {


     private TransactionRepository transactionRepository;
    public  TransactionService transactionService;

    public  TransactionController(TransactionService transactionService,TransactionRepository transactionRepository){
       
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
    }
@GetMapping("/account/{accoundId}")
public ResponseEntity<List<Transaction>>getByAccountId(@PathVariable ("accoundId")Long accountId){
    return ResponseEntity.ok(transactionService.getTransactionsByAccountId(accountId));
}

}




