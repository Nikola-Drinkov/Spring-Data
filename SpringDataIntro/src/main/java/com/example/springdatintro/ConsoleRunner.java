package com.example.springdatintro;

import com.example.springdatintro.models.Account;
import com.example.springdatintro.models.User;
import com.example.springdatintro.services.AccountService;
import com.example.springdatintro.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final AccountService accountService;

    private final UserService userService;


    public ConsoleRunner(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
        /*User someuser = new User("Pesho",25);
        userService.registerUser(someuser);

        Account account = new Account(BigDecimal.valueOf(25623.31));
        account.setUser(someuser);
        accountService.saveAccount(account);


        List<Account> accountList = new ArrayList<>();
        accountList.add(account);
        someuser.setAccounts(accountList);*/

        accountService.withdrawMoney(BigDecimal.valueOf(300),1L);
    }
}
