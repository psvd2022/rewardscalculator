package com.demo.rewardscalculator.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.rewardscalculator.controller.RewardPointController;
import com.demo.rewardscalculator.dto.RewardPoint;
import com.demo.rewardscalculator.dto.RewardPointResponse;
import com.demo.rewardscalculator.service.RewardPointService;

class RewardsCalculatorControllerTest {
	
	RewardPointService rewardPointService;
	
	RewardPointController controller;
	
	@BeforeEach
    void init_mocks() {
		rewardPointService = mock(RewardPointService.class);
		controller = new RewardPointController(rewardPointService);
    }
	
	
	@Test
	void getRewardPoints_success() {
		
		
		RewardPoint rewardPoint = RewardPoint.builder().month("JULY 2022").points(200).build();
		List<RewardPoint> points = Arrays.asList(rewardPoint);
		
		RewardPointResponse pointResponse= RewardPointResponse.builder().customerId(1L).monthlyPoints(points).totalPoints(200L).build();		
		when(rewardPointService.getRewardPoints(1L)).thenReturn(pointResponse);
		
		ResponseEntity<RewardPointResponse> controllerResponse = controller.getRewardPoints(1L);
		assertNotNull(controllerResponse);
		assertEquals(HttpStatus.OK,controllerResponse.getStatusCode());
		assertEquals(1L,controllerResponse.getBody().getCustomerId().longValue());
		assertEquals(200L,controllerResponse.getBody().getTotalPoints().longValue());
	}

}
