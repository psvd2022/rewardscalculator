package com.demo.rewardscalculator.test.util;

import com.demo.rewardscalculator.dto.RewardPoint;
import com.demo.rewardscalculator.model.Transaction;
import com.demo.rewardscalculator.util.RewardsCalculator;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RewardsCalculatorTest {

    @Test
    void calculate_TotalAmount_LessThan_50() {
        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction = Transaction.builder().
                transactionId(1L).customerId(1L).
                transactionDate(LocalDate.of(2022,8,1))
                .amount(20.00).build();
        transactionList.add(transaction);

        transaction = Transaction.builder().
                transactionId(1L).customerId(1L).
                transactionDate(LocalDate.of(2022,8,1))
                .amount(29.99).build();
        transactionList.add(transaction);

        long rewardPoints = RewardsCalculator.calculate(transactionList);
        assertEquals(rewardPoints,0);
    }

    @Test
    void calculate_TotalAmount_GreaterThan_50_And_LessThan_100() {
        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction = Transaction.builder().
                transactionId(1L).customerId(1L).
                transactionDate(LocalDate.of(2022,8,1))
                .amount(50.00).build();
        transactionList.add(transaction);

        transaction = Transaction.builder().
                transactionId(1L).customerId(1L).
                transactionDate(LocalDate.of(2022,8,1))
                .amount(49.99).build();
        transactionList.add(transaction);

        long rewardPoints = RewardsCalculator.calculate(transactionList);
        assertEquals(rewardPoints,50);
    }

    @Test
    void calculate_TotalAmount_Equals_150() {
        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction = Transaction.builder().
                transactionId(1L).customerId(1L).
                transactionDate(LocalDate.of(2022,8,1))
                .amount(50.00).build();
        transactionList.add(transaction);

        transaction = Transaction.builder().
                transactionId(1L).customerId(1L).
                transactionDate(LocalDate.of(2022,8,1))
                .amount(99.99).build();
        transactionList.add(transaction);

        long rewardPoints = RewardsCalculator.calculate(transactionList);
        assertEquals(rewardPoints,150);
    }

    @Test
    void calculateTotalRewardPoints() {
        List<RewardPoint> rewardPoints = new ArrayList<>();
        RewardPoint rewardPoint = RewardPoint.builder().month("August").points(10).build();
        rewardPoints.add(rewardPoint);

        rewardPoint = RewardPoint.builder().month("July").points(20).build();
        rewardPoints.add(rewardPoint);

        rewardPoint = RewardPoint.builder().month("June").points(20).build();
        rewardPoints.add(rewardPoint);

        long totalRewardPoints = RewardsCalculator.calculateTotalRewardPoints(rewardPoints);
        assertEquals(totalRewardPoints,50);

    }


}
