package com.tools.enums;

/**
 * Tool Type class that will be used to determine the type of tool to be rented.
 * Also contains the rental type for the tool.
 */
public enum ToolType {
	Ladder(RentalType.DAILY,1.99), Chainsaw(RentalType.NO_WEEKENDS,1.49), Jackhammer(RentalType.NO_HOLIDAYS_AND_WEEKENDS,2.99);

	private RentalType type;
	private double rate;

	ToolType(RentalType type, double rate) {
		this.type = type;
		this.rate = rate;
	}

	/**
	 * Getter method for {@link RentalType} attribute
	 */
	public RentalType getRentalType() {
		return this.type;
	}
	
	public double getRate() {
		return this.rate;
	}

	// No Setter method is needed because the ToolType is not supposed to
	// change.

}
