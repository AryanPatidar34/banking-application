package com.bankingapplication.service;

import java.util.List;

import com.bankingapplication.entity.Transaction;

public interface TransactionService {
List<Transaction> getTransactionsByAccountId(Long accountId);
}
