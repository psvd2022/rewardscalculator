package com.demo.rewardscalculator.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RewardPointResponse {
    private Long customerId;
    private Long totalPoints;
    private List<RewardPoint> monthlyPoints;
}
