package com.tools.services.impl;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Formating class to be used when creating a new {@link RentalAgreement} object.
 */
public class RentalAgreementFormat {

	public static final String DATE_FORMAT_STRING = "M/d/yy";
	public static SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_STRING);
	public static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
	
	public static String formatCurrency(double amount) {
		return currencyFormatter.format(amount);
	}
	
	public static String formatPercentage(int amount) {
		if(amount < 0 || amount > 100) {
			throw new IllegalArgumentException("Discount percent is not in the range 0-100");
		}
		return amount + "%";
	}
	
	public static String formatDate(Calendar cal) {
		return df.format(cal.getTime());
	}
}
