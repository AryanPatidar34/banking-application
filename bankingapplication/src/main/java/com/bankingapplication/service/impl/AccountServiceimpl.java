package com.bankingapplication.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankingapplication.AccountDto.AccountDto;
import com.bankingapplication.Mapper.AccountMapper;
import com.bankingapplication.entity.Account;
import com.bankingapplication.repository.AccountRepository;
import com.bankingapplication.service.Accountservice;

@Service
public class AccountServiceimpl implements Accountservice {
  private AccountRepository accountRepository;


  public AccountServiceimpl(AccountRepository accountRepository) {
    super();
    this.accountRepository = accountRepository;
  
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

    return AccountMapper.mapToAccountDto(savedAccount);
    

  }

  @Override
  public AccountDto withdraw(Long id, double amount) {
    @SuppressWarnings("null")
    Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("account does not exist"));
    if (account.getBalance() < amount) {
      throw new RuntimeException("insufficient balance");
    }
    Double totalbalance = account.getBalance() - amount;
    account.setBalance(totalbalance);
    Account savedAccount = accountRepository.save(account);


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