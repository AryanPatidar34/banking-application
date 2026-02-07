package com.bankingapplication.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapplication.AccountDto.AccountDto;
import com.bankingapplication.repository.AccountRepository;
import com.bankingapplication.service.Accountservice;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/accounts")
public class AccountController {

  private final AccountRepository accountRepository;
  private Accountservice accountservice;
 

  

 

  public AccountController(Accountservice accountservice, AccountRepository accountRepository) {
    super();
    this.accountservice = accountservice;
    this.accountRepository = accountRepository;
    
  }

  @PostMapping // add acccount REST api
  public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
    System.out.println(accountDto);
    return new ResponseEntity<>(accountservice.createAccount(accountDto), HttpStatus.CREATED);

  }

  @GetMapping("/{id}") // get account api
  public ResponseEntity<AccountDto> getAccountByid(@PathVariable Long id) {
    AccountDto accountDto = accountservice.getAccountByid(id);
    return ResponseEntity.ok(accountDto);
  }

  @PutMapping("/{id}/deposit") // deposit balance api

  public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
    Double amount = request.get("amount");
    AccountDto accountDto = accountservice.deposit(id, amount);
    return ResponseEntity.ok(accountDto);
  }

  @PutMapping("/{id}/withdraw") // withdraw amount api
  public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
    Double amount = request.get("amount");
    AccountDto accountDto = accountservice.withdraw(id, amount);
    return ResponseEntity.ok(accountDto);
  }

@GetMapping
  public ResponseEntity<List<AccountDto>>getAllAccounts(){
    List<AccountDto> accountDto =  accountservice.getAllAccounts();
    return ResponseEntity.ok(accountDto);
  }

@DeleteMapping("/{id}")
public ResponseEntity<String> deleteAccount(@PathVariable Long id){
    accountservice.deleteAccount(id);
     return ResponseEntity.ok("Account Deleted Successfully");

}





}
