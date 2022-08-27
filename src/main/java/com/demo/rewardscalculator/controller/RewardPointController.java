package com.demo.rewardscalculator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rewardscalculator.dto.RewardPointResponse;
import com.demo.rewardscalculator.service.RewardPointService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/")
public class RewardPointController {
	private RewardPointService rewardPointService;

	public RewardPointController(final RewardPointService rewardPointService) {
		this.rewardPointService = rewardPointService;
	}
	
	@GetMapping("v1/customers/{customerId}/rewards")
	public ResponseEntity<RewardPointResponse> getRewardPoints(@PathVariable("customerId")Long customerId) {
		log.info("Retrieving reward points for the customer - {}", customerId);
		return new ResponseEntity<>(rewardPointService.getRewardPoints(customerId), HttpStatus.OK);
	}

}
