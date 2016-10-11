package com.tools.services.impl;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

/**
 * JUnit test for the various static functions of {@link RentalAgreementFormat}
 */
public class RentalAgreementFormatTest {
	
	/**
	 * Testing for {@link RentalAgreementFormat#formatCurrency(double)} for various amounts.
	 */
	@Test
	public void testCurrencyFormat() {
		assertEquals("$1,000,000.00", RentalAgreementFormat.formatCurrency(1000000));
		assertEquals("$9,999.99", RentalAgreementFormat.formatCurrency(9999.99));
		assertEquals("$123.45", RentalAgreementFormat.formatCurrency(123.45));
		assertEquals("$0.99", RentalAgreementFormat.formatCurrency(.99));
	}
	
	/**
	 * Testing {@link RentalAgreementFormat#formatPercentage(int)} when given a value < 0
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testPercentageLessThanZero() {
		RentalAgreementFormat.formatPercentage(-1);
	}
	
	/**
	 * Testing {@link RentalAgreementFormat#formatPercentage(int)} when given a value > 100
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testPercentageGreaterThanOneHundred() {
		RentalAgreementFormat.formatPercentage(101);
	}
	
	/**
	 * Testing {@link RentalAgreementFormat#formatPercentage(int)} when given a value 0 <= value <= 100
	 */
	@Test
	public void testPercentageFormat() {
		assertEquals("0%", RentalAgreementFormat.formatPercentage(00));
		assertEquals("21%", RentalAgreementFormat.formatPercentage(21));
		assertEquals("100%", RentalAgreementFormat.formatPercentage(100));
	}
	
	/**
	 * Testing {@link RentalAgreementFormat#formatDate(Calendar)
	 */
	@Test
	public void testDateFormat() {
		Calendar cal = Calendar.getInstance();
		
		cal.set(2016, Calendar.OCTOBER, 8);
		assertEquals("10/8/16", RentalAgreementFormat.formatDate(cal));
		
		cal.set(2015, Calendar.JANUARY, 15);
		assertEquals("1/15/15", RentalAgreementFormat.formatDate(cal));
	}

}
