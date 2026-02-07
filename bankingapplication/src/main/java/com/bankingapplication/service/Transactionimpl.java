package com.bankingapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapplication.entity.Transaction;
import com.bankingapplication.repository.AccountRepository;
import com.bankingapplication.repository.TransactionRepository;
@Service
public class Transactionimpl implements TransactionService {

@Autowired
    private TransactionRepository transactionRepository;

    @Autowired
private AccountRepository accountRepository;

   public  Transactionimpl (TransactionRepository transactionRepository,AccountRepository accountRepository){
    this.transactionRepository = transactionRepository;
    this.accountRepository = accountRepository;
   }

    @Override
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        accountRepository.findById(accountId).orElseThrow(()-> new RuntimeException("Accound does not found"));
         return transactionRepository.findByAccountId(accountId);

}
}