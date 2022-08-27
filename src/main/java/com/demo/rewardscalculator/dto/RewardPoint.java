package com.demo.rewardscalculator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RewardPoint {
    private String month;
    private long points;
}
