package com.example.springdatintro.services;

import com.example.springdatintro.models.Account;

import java.math.BigDecimal;

public interface AccountService {
    void saveAccount(Account account);
    void withdrawMoney(BigDecimal money, Long id);
    void transferMoney(BigDecimal money, Long id);
}
