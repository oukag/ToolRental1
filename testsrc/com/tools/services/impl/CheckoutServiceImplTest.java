package com.tools.services.impl;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import com.tools.models.RentalAgreement;
import com.tools.services.CheckoutService;

/**
 * JUnit test for the {@link CheckoutServiceImpl#checkout(String, Calendar, int, int)}
 */
public class CheckoutServiceImplTest {

	public CheckoutService checkoutService = new CheckoutServiceImpl();

	/**
	 * Testing for a Ladder over a weekend and a holiday
	 */
	@Test
	public void test1() {
		String toolCode = "LADW";
		Calendar checkoutDate = Calendar.getInstance();
		checkoutDate.set(2015, Calendar.SEPTEMBER, 3);
		int rentalDays = 5;
		int discount = 10;
		
		RentalAgreement ra = checkoutService.checkout(toolCode, checkoutDate, rentalDays, discount);
		
		assertEquals("9/8/15", ra.getDueDate());
		assertEquals("$1.99", ra.getDailyRentalCharge());
		assertEquals(5, ra.getChargeDays());
		assertEquals("$9.95", ra.getPrediscountCharge());
		assertEquals("10%", ra.getDiscountPercentage());
		assertEquals("$1.00", ra.getDiscountAmount());
		assertEquals("$8.95", ra.getFinalCharge());
	}
	
	/**
	 * Testing for a Chainsaw over a weekend and a holiday
	 */
	@Test
	public void test2() {
		String toolCode = "CHNS";
		Calendar checkoutDate = Calendar.getInstance();
		checkoutDate.set(2015, Calendar.JULY, 2);
		int rentalDays = 5;
		int discount = 25;
		
		RentalAgreement ra = checkoutService.checkout(toolCode, checkoutDate, rentalDays, discount);
		
		assertEquals("7/7/15", ra.getDueDate());
		assertEquals("$1.49", ra.getDailyRentalCharge());
		assertEquals(3, ra.getChargeDays());
		assertEquals("$4.47", ra.getPrediscountCharge());
		assertEquals("25%", ra.getDiscountPercentage());
		assertEquals("$1.12", ra.getDiscountAmount());
		assertEquals("$3.35", ra.getFinalCharge());
	}
	
	
	/**
	 * Testing for a Jackhammer over a weekend and a holiday
	 */
	@Test
	public void test3() {
		String toolCode = "JAKD";
		Calendar checkoutDate = Calendar.getInstance();
		checkoutDate.set(2015, Calendar.SEPTEMBER, 3);
		int rentalDays = 6;
		int discount = 0;
		
		RentalAgreement ra = checkoutService.checkout(toolCode, checkoutDate, rentalDays, discount);
		
		assertEquals("9/9/15", ra.getDueDate());
		assertEquals("$2.99", ra.getDailyRentalCharge());
		assertEquals(3, ra.getChargeDays());
		assertEquals("$8.97", ra.getPrediscountCharge());
		assertEquals("0%", ra.getDiscountPercentage());
		assertEquals("$0.00", ra.getDiscountAmount());
		assertEquals("$8.97", ra.getFinalCharge());
	}
	
	/**
	 * Testing for a Jackhammer over a holiday and a weekend.
	 */
	@Test
	public void test4() {
		String toolCode = "JAKR";
		Calendar checkoutDate = Calendar.getInstance();
		checkoutDate.set(2020, Calendar.JULY, 2);
		int rentalDays = 4;
		int discount = 50;
		
		RentalAgreement ra = checkoutService.checkout(toolCode, checkoutDate, rentalDays, discount);
		
		assertEquals("7/6/20", ra.getDueDate());
		assertEquals("$2.99", ra.getDailyRentalCharge());
		assertEquals(1, ra.getChargeDays());
		assertEquals("$2.99", ra.getPrediscountCharge());
		assertEquals("50%", ra.getDiscountPercentage());
		assertEquals("$1.50", ra.getDiscountAmount());
		assertEquals("$1.49", ra.getFinalCharge());
	}
	
	/**
	 * Tests the case where a discount would result in money being returned to the customer
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test5() {
		String toolCode = "JAKR";
		Calendar checkoutDate = Calendar.getInstance();
		checkoutDate.set(2015, Calendar.SEPTEMBER, 3);
		int rentalDays = 5;
		int discount = 101;
		
		checkoutService.checkout(toolCode, checkoutDate, rentalDays, discount);
	}

}
