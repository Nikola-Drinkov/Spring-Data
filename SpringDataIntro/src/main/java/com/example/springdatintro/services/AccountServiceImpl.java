package com.example.springdatintro.services;

import com.example.springdatintro.models.Account;
import com.example.springdatintro.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Optional<Account> accountOpt = accountRepository.findById(id);
        if(accountOpt.isPresent()){
            Account account = accountOpt.get();
            BigDecimal newSum = account.getBalance().subtract(money);

            if(newSum.compareTo(BigDecimal.ZERO)<0) {
                throw new IllegalArgumentException("Not enough money");
            }
            account.setBalance(newSum);
            accountRepository.save(account);
        } else throw new IllegalArgumentException("Account not found");
    }
    @Override
    public void transferMoney(BigDecimal money, Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        if (money.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cannot deposit negative money");
        }

        BigDecimal newBalance = account.getBalance().add(money);
        account.setBalance(newBalance);

        accountRepository.save(account);
    }
}
