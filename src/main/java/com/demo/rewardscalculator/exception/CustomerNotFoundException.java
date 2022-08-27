package com.demo.rewardscalculator.exception;

public class CustomerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4092587472952842079L;

	public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException() {

    }
}
