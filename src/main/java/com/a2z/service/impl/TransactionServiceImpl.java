package com.a2z.service.impl;

import com.a2z.model.Order;
import com.a2z.model.Seller;
import com.a2z.model.Transaction;
import com.a2z.repository.SellerRepository;
import com.a2z.repository.TransactionRepository;
import com.a2z.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author Chetanand Meher
 */
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final SellerRepository sellerRepository;

    @Override
    public Transaction createTransaction(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order must not be null");
        }

        Seller seller = sellerRepository.findById(order.getSellerId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Seller not found with id: " + order.getSellerId()
                ));

        Transaction transaction = Transaction.builder()
                .seller(seller)
                .customer(order.getUser())
                .order(order)
                .build();

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsBySellerId(Long sellerId) {
        return transactionRepository.findBySellerId(sellerId);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
