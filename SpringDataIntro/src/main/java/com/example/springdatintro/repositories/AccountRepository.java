package com.example.springdatintro.repositories;

import com.example.springdatintro.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
