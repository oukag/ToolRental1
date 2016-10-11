package com.tools.services.impl;

import java.util.Calendar;

import com.tools.enums.RentalType;
import com.tools.models.Tool;
import com.tools.services.ChargeService;

/**
 * Basic implementation of {@link ChargeService}
 */
public class ChargeServiceImpl implements ChargeService {

	@Override
	public boolean chargeDay(final Calendar cal, final RentalType type) {
		switch (type) {
		case NO_WEEKENDS:
			// Is charged if the day does NOT fall on a weekend
			return !isWeekend(cal);
		case NO_HOLIDAYS_AND_WEEKENDS:
			// Is charged if the day is NOT a weekend AND NOT a holiday
			return !isWeekend(cal) && !isHoliday(cal);
		default:
			// Is always charged
			return true;
		}
	}
	
	@Override
	public double calculateCost(final Tool tool, final int days, final Calendar start) {
		return tool.getType().getRate() * getChargableDays(tool, days, start);
	}
	
	@Override
	public int getChargableDays(final Tool tool, final int days, final Calendar start) {
		int total = 0;
		// Create a clone of the calendar so that we do not change the variable
		// that was passed
		Calendar cal = (Calendar) start.clone();
		// Calculate the total cost for the rental
		for (int i = 0; i < days; i++) {
			// We increment the calendar first because the cost starts on the
			// day after the rental takes place.
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// Determine if we will charge for the day based on the tool's
			// rental type
			if (chargeDay(cal, tool.getType().getRentalType())) {
				// Add the tool's rental rate to the total cost.
				total++;
			}
		}
		return total;
	}

	/**
	 * Determines whether the given {@link Calendar} day falls on the weekend.
	 */
	private boolean isWeekend(final Calendar cal) {
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		}
		return false;
	}

	/**
	 * Determines whether the given {@link Calendar} day falls on a holiday.
	 */
	private boolean isHoliday(final Calendar cal) {
		return isIndependenceDay(cal) || isLaborDay(cal);
	}

	/**
	 * Determines whether the given {@link Calendar} day falls on Independence
	 * Day (July 4th). If Independence day falls on the weekend, Independence
	 * Day is then considered the closest weekday (if Saturday, then Friday
	 * before. If Sunday, then Monday after).
	 */
	private boolean isIndependenceDay(final Calendar cal) {
		// Variable to hold the result to be returned
		boolean res = false;

		// Independence Day is always in July
		if (cal.get(Calendar.MONTH) == Calendar.JULY) {
			// To be considered for the holiday, the day of the month must be
			// either the 3rd, 4th, or 5th
			switch (cal.get(Calendar.DAY_OF_MONTH)) {
			case 3:
				// To be considered the Holiday, the day of the week must be
				// Friday
				if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
					res = true;
				}
				break;
			case 4:
				res = true;
				break;
			case 5:
				// To be considered the Holiday, the day of the week must be
				// Monday
				if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
					res = true;
				}
				break;
			}
		}
		return res;
	}

	/**
	 * Determines whether the given {@link Calendar} day falls on Labor Day (1st
	 * Monday in September).
	 */
	private boolean isLaborDay(final Calendar cal) {
		if (cal.get(Calendar.MONTH) == Calendar.SEPTEMBER //
				// Labor Day must be in September
				&& cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY //
				// Labor Day must be a Monday
				&& cal.get(Calendar.DAY_OF_MONTH) < 8) //
		// Labor Day must be the first Monday so the Day of the month must be
		// between 1 and 7
		{
			return true;
		}
		return false;
	}

}
