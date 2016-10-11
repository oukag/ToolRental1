package com.tools.services.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tools.enums.RentalType;
import com.tools.enums.ToolType;
import com.tools.models.Tool;

/**
 * JUnit test for the various functions of {@link ChargeServiceImpl}
 */
public class ChargeServiceImplTest {
	private ChargeServiceImpl chargeService = new ChargeServiceImpl();

	private static List<Tool> tools;

	/**
	 * Creating the "database" for the sample tools
	 */
	@BeforeClass
	public static void setUp() {
		tools = new ArrayList<Tool>();
		tools.add(new Tool("LADW", "Werner", ToolType.Ladder));
		tools.add(new Tool("CHNS", "Stihl", ToolType.Chainsaw));
		tools.add(new Tool("JAKR", "Ridgid", ToolType.Jackhammer));
		tools.add(new Tool("JAKD", "DeWalt", ToolType.Jackhammer));
	}

	/**
	 * Testing {@link ChargeServiceImpl#chargeDay(Calendar, RentalType)} for
	 * {@link RentalType#DAILY}
	 */
	@Test
	public void testDaily() {
		RentalType type = RentalType.DAILY;

		// Checking on Independence Day
		assertTrue(chargeService.chargeDay(getIndependenceDaySaturday(), type));
		assertTrue(chargeService.chargeDay(getIndependenceDaySunday(), type));
		assertTrue(chargeService.chargeDay(getIndependenceDayWeek(), type));
		assertTrue(chargeService.chargeDay(getIndependenceDayWeekend(), type));

		// Checking on Labor Day
		assertTrue(chargeService.chargeDay(getLaborDay(), type));

		// Checking on a weekday
		assertTrue(chargeService.chargeDay(getWeekday(), type));

		// Checking on the weekend
		assertTrue(chargeService.chargeDay(getWeekendDay(), type));
	}

	/**
	 * Testing {@link ChargeServiceImpl#chargeDay(Calendar, RentalType)} for
	 * {@link RentalType#NO_WEEKENDS}
	 */
	@Test
	public void testNoWeekends() {
		RentalType type = RentalType.NO_WEEKENDS;

		// Checking on Independence Day
		assertTrue(chargeService.chargeDay(getIndependenceDaySaturday(), type));
		assertTrue(chargeService.chargeDay(getIndependenceDaySunday(), type));
		assertTrue(chargeService.chargeDay(getIndependenceDayWeek(), type));
		assertFalse(chargeService.chargeDay(getIndependenceDayWeekend(), type));

		// Checking on Labor Day
		assertTrue(chargeService.chargeDay(getLaborDay(), type));

		// Checking on a weekday
		assertTrue(chargeService.chargeDay(getWeekday(), type));

		// Checking on the weekend
		assertFalse(chargeService.chargeDay(getWeekendDay(), type));
	}

	/**
	 * Testing {@link ChargeServiceImpl#chargeDay(Calendar, RentalType)} for
	 * {@link RentalType#NO_WEEKENDS}
	 */
	@Test
	public void testNoWeekendsOrHolidays() {
		RentalType type = RentalType.NO_HOLIDAYS_AND_WEEKENDS;

		// Checking on Independence Day
		assertFalse(chargeService.chargeDay(getIndependenceDaySaturday(), type));
		assertFalse(chargeService.chargeDay(getIndependenceDaySunday(), type));
		assertFalse(chargeService.chargeDay(getIndependenceDayWeek(), type));
		assertFalse(chargeService.chargeDay(getIndependenceDayWeekend(), type));

		// Checking on Labor Day
		assertFalse(chargeService.chargeDay(getLaborDay(), type));

		// Checking on a weekday
		assertTrue(chargeService.chargeDay(getWeekday(), type));

		// Checking on the weekend
		assertFalse(chargeService.chargeDay(getWeekendDay(), type));
	}

	/**
	 * Testing {@link ChargeServiceImpl#calculateCost(Tool, int, Calendar)}
	 */
	@Test
	public void testCalculateCost() {
		// Set Calendar for Friday July 3rd, 2015
		Calendar cal = getIndependenceDaySaturday();
		// We actually want to start the calendar for Thursday July 2nd, 2015
		cal.add(Calendar.DAY_OF_MONTH, -1);
		// Setting days as 5 means that we will rent the tools from Thursday
		// July 2nd, 2015 through Wednesday July 8th, 2015
		// 5 days total with 2 days during the weekend and 1 day for a holiday
		// (Friday July 3rd, 2015)
		int days = 5;

		// Test for Ladder over 5 days, $1.99 daily
		assertEquals(5, chargeService.getChargableDays(tools.get(0), days, cal));
		assertEquals(1.99 * days, chargeService.calculateCost(tools.get(0), days, cal), 0);

		// Test for Chainsaw over 5 days, should only be charged for 3 days at
		// $1.49
		assertEquals(3, chargeService.getChargableDays(tools.get(1), days, cal));
		assertEquals(1.49 * 3, chargeService.calculateCost(tools.get(1), days, cal), 0);

		// Test for Jackhammer over 5 days, should only be charged for 2 days at
		// $2.99
		assertEquals(2, chargeService.getChargableDays(tools.get(2), days, cal));
		assertEquals(2.99 * 2, chargeService.calculateCost(tools.get(2), days, cal), 0);

		// Test for the second Jackhammer over 5 days, should only be charged
		// for 2 days at $2.99
		assertEquals(2, chargeService.getChargableDays(tools.get(3), days, cal));
		assertEquals(2.99 * 2, chargeService.calculateCost(tools.get(3), days, cal), 0);
	}
	
	// END Test

	/**
	 * Returns a {@link Calendar} instance for Friday July 3rd, 2015.
	 */
	private Calendar getIndependenceDaySaturday() {
		Calendar cal = Calendar.getInstance();
		cal.set(2015, Calendar.JULY, 3);
		return cal;
	}

	/**
	 * Returns a {@link Calendar} instance for Monday July 4th, 2016.
	 */
	private Calendar getIndependenceDayWeek() {
		Calendar cal = Calendar.getInstance();
		cal.set(2016, Calendar.JULY, 4);
		return cal;
	}

	/**
	 * Returns a {@link Calendar} instance for Saturday July 4th, 2015.
	 */
	private Calendar getIndependenceDayWeekend() {
		Calendar cal = Calendar.getInstance();
		cal.set(2015, Calendar.JULY, 4);
		return cal;
	}

	/**
	 * Returns a {@link Calendar} instance for Monday July 5th, 2010.
	 */
	private Calendar getIndependenceDaySunday() {
		Calendar cal = Calendar.getInstance();
		cal.set(2010, Calendar.JULY, 5);
		return cal;
	}

	/**
	 * Returns a {@link Calendar} instance for Monday September 5th, 2016
	 */
	private Calendar getLaborDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(2016, Calendar.SEPTEMBER, 5);
		return cal;
	}

	/**
	 * Returns a {@link Calendar} instance for Saturday October 8th, 2016
	 */
	private Calendar getWeekendDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(2016, Calendar.OCTOBER, 8);
		return cal;
	}

	/**
	 * Returns a {@link Calendar} instance for Tuesday October 11th, 2016
	 */
	private Calendar getWeekday() {
		Calendar cal = Calendar.getInstance();
		cal.set(2016, Calendar.OCTOBER, 11);
		return cal;
	}

}
