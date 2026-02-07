package com.bankingapplication.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankingapplication.AccountDto.AccountDto;
import com.bankingapplication.Mapper.AccountMapper;
import com.bankingapplication.entity.Account;
import com.bankingapplication.entity.Transaction;
import com.bankingapplication.repository.AccountRepository;
import com.bankingapplication.repository.TransactionRepository;
import com.bankingapplication.service.Accountservice;

@Service
public class AccountServiceimpl implements Accountservice {
  private AccountRepository accountRepository;



  private TransactionRepository transactionRepository;
 

  


  public AccountServiceimpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
    super();
    this.accountRepository = accountRepository;
    this.transactionRepository = transactionRepository;
   
  }


  @SuppressWarnings("null")
  @Override
  public AccountDto createAccount(AccountDto accountDto) {
    Account account = AccountMapper.mapToAccount(accountDto);
    Account savedAccount = accountRepository.save(account);
    return AccountMapper.mapToAccountDto(savedAccount);
  }

  @SuppressWarnings("null")
  @Override
  public AccountDto getAccountByid(Long id) {
    Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
    return AccountMapper.mapToAccountDto(account);
  }

  @SuppressWarnings("null")
  @Override
  @Transactional
  public AccountDto deposit(Long id, double amount) {

    
    Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
    double totalabalance = account.getBalance() + amount;
    account.setBalance(totalabalance);
    Account savedAccount = accountRepository.save(account);


    Transaction transaction = new Transaction();
    transaction.setAccountId(id);
    transaction.setAmount(amount);
     transaction.setTimestamp(LocalDateTime.now());
     transaction.setType("deposit");
            transactionRepository.save(transaction);
    return AccountMapper .mapToAccountDto(savedAccount);
  }





  @Override
  @Transactional
  public AccountDto withdraw(Long id, double amount) {
    @SuppressWarnings("null")
    Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("account does not exist"));
    if (account.getBalance() < amount) {
      throw new RuntimeException("insufficient balance");
    }
    Double totalbalance = account.getBalance() - amount;
    account.setBalance(totalbalance);
    Account savedAccount = accountRepository.save(account);


  Transaction transaction = new Transaction();
  transaction.setAccountId(id);
  transaction.setAmount(amount);
  transaction.setType("Withdraw");
  transaction.setTimestamp(LocalDateTime.now());
  transactionRepository.save(transaction);

    return AccountMapper.mapToAccountDto(savedAccount);
  }

  @Override
  public List<AccountDto> getAllAccounts() {
    return accountRepository.findAll().stream().map((account) -> AccountMapper.mapToAccountDto(account))
        .collect(Collectors.toList());

  }

  @Override
  public void deleteAccount(Long id) {
    Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("account does not exist"));
    accountRepository.delete(account);
  }



  


  

  

}