package com.demo.rewardscalculator.exception;

public class TransactionsNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4101809611972141058L;
	public TransactionsNotFoundException(String message) {
        super(message);
    }

    public TransactionsNotFoundException() {

    }
}
