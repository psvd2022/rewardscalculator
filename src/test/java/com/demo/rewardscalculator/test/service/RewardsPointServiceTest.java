package com.demo.rewardscalculator.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.demo.rewardscalculator.dto.RewardPoint;
import com.demo.rewardscalculator.dto.RewardPointResponse;
import com.demo.rewardscalculator.exception.CustomerNotFoundException;
import com.demo.rewardscalculator.exception.TransactionsNotFoundException;
import com.demo.rewardscalculator.model.Customer;
import com.demo.rewardscalculator.model.Transaction;
import com.demo.rewardscalculator.repository.CustomerRepository;
import com.demo.rewardscalculator.repository.TransactionRepository;
import com.demo.rewardscalculator.service.RewardPointService;

class RewardsPointServiceTest {
	

 
    private CustomerRepository customerRepository;


    private TransactionRepository transactionRepository;


    private RewardPointService rewardPointService;

    @BeforeEach
    void init_mocks() {
    	customerRepository = mock(CustomerRepository.class);
    	transactionRepository = mock(TransactionRepository.class);
    	rewardPointService = new RewardPointService(customerRepository,transactionRepository);
    }

    @Test
    void getRewardPointsTest_success() {
        Customer customer = Customer.builder().customerId(1L).customerName("CUST 1").build();
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction = Transaction.builder().transactionId(1L).customerId(1L).transactionDate(LocalDate.of(2022,8,1)).amount(20.00).build();
        transactionList.add(transaction);
        transaction = Transaction.builder().transactionId(1L).customerId(1L).transactionDate(LocalDate.of(2022,8,1)).amount(29.99).build();
        transactionList.add(transaction);
        transaction = Transaction.builder().transactionId(1L).customerId(1L).transactionDate(LocalDate.of(2022,7,1)).amount(99.99).build();
        transactionList.add(transaction);
        transaction = Transaction.builder().transactionId(1L).customerId(1L).transactionDate(LocalDate.of(2022,6,1)).amount(200.00).build();
        transactionList.add(transaction);
        when(transactionRepository.findByCustomerId(1L)).thenReturn(transactionList);

        RewardPointResponse rewardPointResponse = rewardPointService.getRewardPoints(1L);
        assertNotNull(rewardPointResponse);
        assertEquals(1L,rewardPointResponse.getCustomerId());

        Optional<RewardPoint> rewardPoint = rewardPointResponse.getMonthlyPoints().stream().filter(point -> point.getMonth().equals("AUGUST 2022")).findAny();
        assertEquals(0,rewardPoint.get().getPoints());

        rewardPoint = rewardPointResponse.getMonthlyPoints().stream().filter(point -> point.getMonth().equals("JULY 2022")).findAny();
        assertEquals(50,rewardPoint.get().getPoints());

        rewardPoint = rewardPointResponse.getMonthlyPoints().stream().filter(point -> point.getMonth().equals("JUNE 2022")).findAny();
        assertEquals(250,rewardPoint.get().getPoints());

        assertEquals(300,rewardPointResponse.getTotalPoints());
        
        
    }
    
    
    @Test
    void getRewardPointsTest_customerNotFoundException() {  
        
    	assertThrows(CustomerNotFoundException.class, new Executable() {
            
            @Override
            public void execute() throws Throwable {
            	when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
                rewardPointService.getRewardPoints(1L);
            }
        });
    }
    
    @Test
    void getRewardPointsTest_transactionNotFoundException() {  
        
    	assertThrows(TransactionsNotFoundException.class, new Executable() {
            
            @Override
            public void execute() throws Throwable {
            	Customer customer = Customer.builder().customerId(1L).customerName("CUST 1").build();
                when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));                
                when(transactionRepository.findByCustomerId(1L)).thenReturn(null);
                rewardPointService.getRewardPoints(1L);
            }
        });
    }
    
}
