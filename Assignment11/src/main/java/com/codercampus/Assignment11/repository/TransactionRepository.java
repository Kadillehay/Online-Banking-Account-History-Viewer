package com.codercampus.Assignment11.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.codercampus.Assignment11.domain.Transaction;

@Repository
public class TransactionRepository {
	private List<Transaction> transactions = new ArrayList<>(100);
	
	public TransactionRepository () {
		super();
		populateData();
		sortTransactions();
	}
	
	public List<Transaction> findAll () {
		sortTransactions();
		return transactions;
	}
	public Transaction findById(Long id) {
	    for (Transaction transaction : transactions) {
	        if (transaction.getId().equals(id)) {
	            return transaction;
	        }
	    }
	    return null;
	}

	@SuppressWarnings("unchecked")
	public void populateData() {
		try (FileInputStream fileInputStream = new FileInputStream("transactions.txt");
			 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
			this.transactions = (List<Transaction>) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
	}
	public void sortTransactions() {
        Collections.sort(transactions);
    }
}
