package com.demo.rewardscalculator.util;

import java.util.List;
import java.util.stream.Collectors;

import com.demo.rewardscalculator.dto.RewardPoint;
import com.demo.rewardscalculator.model.Transaction;

public class RewardsCalculator {
	
	private RewardsCalculator() { }

	public static long calculate(List<Transaction> transactions) {
		
		Double totalAmount = transactions.stream().map(Transaction::getAmount).collect(Collectors.summingDouble(Double::doubleValue));

		if(totalAmount > 0 && totalAmount <= 50) return 0;
		
		if(totalAmount > 50 && totalAmount <= 100) {
			return Math.round(totalAmount - 50) * 1;
		}

		if(totalAmount > 100) {
			return 50 + Math.round(totalAmount - 100) * 2;
		}
		return 0;
	}

	public static long calculateTotalRewardPoints(List<RewardPoint> rewardPoints) {
		return rewardPoints.stream().map(RewardPoint::getPoints).reduce(0L,Long::sum);
	}
	
}
