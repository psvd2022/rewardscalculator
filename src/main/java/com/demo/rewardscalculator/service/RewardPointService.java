package com.demo.rewardscalculator.service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.demo.rewardscalculator.dto.RewardPoint;
import com.demo.rewardscalculator.dto.RewardPointResponse;
import com.demo.rewardscalculator.exception.CustomerNotFoundException;
import com.demo.rewardscalculator.exception.TransactionsNotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.demo.rewardscalculator.model.Customer;
import com.demo.rewardscalculator.model.Transaction;
import com.demo.rewardscalculator.repository.CustomerRepository;
import com.demo.rewardscalculator.repository.TransactionRepository;
import com.demo.rewardscalculator.util.RewardsCalculator;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
public class RewardPointService {
	
	private CustomerRepository customerRepository;
	
	private TransactionRepository transactionRepository;
	
	public RewardPointService(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
		this.customerRepository = customerRepository;
		this.transactionRepository = transactionRepository;
	}
	
	public RewardPointResponse getRewardPoints(Long customerId) {

		Optional<Customer> customerOptional = customerRepository.findById(customerId);
		validateCustomer(customerOptional);

		List<Transaction> transactionList = transactionRepository.findByCustomerId(customerId);
		
		
		if (CollectionUtils.isEmpty(transactionList)) {
			throw new TransactionsNotFoundException();
		}
		
		log.info("Total number of transactions {} found for the customer {}",transactionList.size(),customerId);

		List<RewardPoint> rewardPoints = prepareMonthlyRewardPointMap(transactionList);

		long totalPoints = RewardsCalculator.calculateTotalRewardPoints(rewardPoints);

		return RewardPointResponse.builder()
				.customerId(customerId)
				.monthlyPoints(rewardPoints)
				.totalPoints(totalPoints)
				.build();


	}

	private void validateCustomer(Optional<Customer> customerOptional) {
		if(!customerOptional.isPresent()) {
			log.error("Customer Not Found with the given custId");
			throw new CustomerNotFoundException();
		}
	}

	protected List<RewardPoint> prepareMonthlyRewardPointMap(List<Transaction> transactionList) {
		
		transactionList.forEach(transaction -> {
			LocalDate date = transaction.getTransactionDate();
			int year = date.getYear();
			transaction.setMonth(date.getMonth().name()+" "+year);
		});

		Map<String,List<Transaction>> monthlyTransactionMap = transactionList.stream().collect(Collectors.groupingBy(Transaction::getMonth));		

		return monthlyTransactionMap.keySet().stream().map(m -> {
			long points = RewardsCalculator.calculate(monthlyTransactionMap.get(m));
			return RewardPoint.builder().month(m).points(points).build();
		}).collect(Collectors.toList());
	}
	
}
