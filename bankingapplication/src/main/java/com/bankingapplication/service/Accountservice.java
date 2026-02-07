package com.bankingapplication.service;

import java.util.List;

import com.bankingapplication.AccountDto.AccountDto;

public interface Accountservice {
   abstract AccountDto createAccount (AccountDto account);
 
   AccountDto getAccountByid(Long id);

   AccountDto deposit(Long id,double amount);
    
   AccountDto withdraw(Long id, double amount);

   List<AccountDto> getAllAccounts();

   void deleteAccount(Long id);

   
   
  



}

