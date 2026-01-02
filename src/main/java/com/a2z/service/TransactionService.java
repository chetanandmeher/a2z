package com.a2z.service;

import com.a2z.model.Order;
import com.a2z.model.Transaction;
import org.hibernate.internal.TransactionManagement;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Order order);
    List<Transaction> getTransactionsBySellerId(Long sellerId);
    List<Transaction> getAllTransactions();

}
