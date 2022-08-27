package com.demo.rewardscalculator.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRANSACTION", schema = "DEMO")
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANS_ID")
    private Long transactionId;

    @Column(name = "CUST_ID")
    private Long customerId;
    
    @Column(name = "TRANS_DATE")
    private LocalDate transactionDate;
    
    @Column(name = "TRANS_AMOUNT")
    private Double amount;

    @Transient
    private String month;
    
    @Transient
    private int year;
    
}

