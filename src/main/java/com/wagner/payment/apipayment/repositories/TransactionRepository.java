package com.wagner.payment.apipayment.repositories;

import com.wagner.payment.apipayment.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
