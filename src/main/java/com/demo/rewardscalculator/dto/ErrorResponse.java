package com.demo.rewardscalculator.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private String errorMessage;
    private int errorCode;
}
