package com.tools.services;

import java.util.Calendar;

import com.tools.models.RentalAgreement;

/**
 * Interface to be used when creating a {@link RentalAgreement} at the time of checkout.
 */
public interface CheckoutService {

	public RentalAgreement checkout(final String toolCode, final Calendar checkoutDate, final int rentalDays, final int discount);
}
