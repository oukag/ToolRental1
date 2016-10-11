package com.tools.services;

import java.util.Calendar;

import com.tools.enums.RentalType;
import com.tools.models.Tool;

/**
 * Interface to be used at the time of checkout to aid in calculation of rental costs.
 */
public interface ChargeService {

	/**
	 * Determines whether the given {@link Calendar} day should be charged for
	 * the rental based on the given {@link RentalType}
	 */
	public boolean chargeDay(final Calendar cal, final RentalType type);

	/**
	 * Calculates the total rental cost for the specified {@link Tool} for the
	 * specified number of days starting with the given {@link Calendar}
	 */
	public double calculateCost(final Tool tool, final int days, Calendar start);

	/**
	 * Determines the number of chargeable days specified {@link Tool} for the
	 * specified number of days starting with the given {@link Calendar}
	 */
	public int getChargableDays(final Tool tool, final int days, final Calendar start);
}
