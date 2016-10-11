package com.tools.services.impl;

import java.util.Calendar;

import com.tools.enums.ToolType;
import com.tools.models.RentalAgreement;
import com.tools.models.Tool;
import com.tools.services.ChargeService;
import com.tools.services.CheckoutService;

public class CheckoutServiceImpl implements CheckoutService {
	private ChargeService chargeService = new ChargeServiceImpl();

	@Override
	public RentalAgreement checkout(String toolCode, Calendar checkoutDate, int rentalDays, int discount) {
		// Validate input
		if (rentalDays < 1) {
			throw new IllegalArgumentException("Rental day count is not 1 or greater");
		}
		if (discount < 0 || discount > 100) {
			throw new IllegalArgumentException("Discount percent is not in the range 0-100");
		}

		// Get the tool for the given code using the mocked service call. Will
		// fail if asking for a tool that does not exist.
		Tool tool = getToolForCode(toolCode);

		// Calculate the different totals: pre-discount charge, discount amount,
		// and final charge
		// All calculations are done prior to creating the rental agreement.
		double beforeDiscount = chargeService.calculateCost(tool, rentalDays, checkoutDate);
		// If there is not discount specified, there is no need in calculating
		// the discount amount
		double discountAmount = (discount > 0) ? beforeDiscount * (discount / 100.00) + 0.005 : 0;
		double afterDiscount = beforeDiscount - discountAmount;
		int chargeDays = chargeService.getChargableDays(tool, rentalDays, checkoutDate);
		Calendar dueDate = getDueDate(checkoutDate, rentalDays);

		// Create the Rental Agreement
		RentalAgreement ra = new RentalAgreement();
		/**
		 * The following comments lines were used to ensure that all of the
		 * necessary attributes were being set.
		 */
		// Tool code
		ra.setToolCode(tool.getCode());
		// Tool type
		ra.setToolType(tool.getType().name());
		// Tool brand
		ra.setToolBrand(tool.getBrand());
		// Rental days
		ra.setRentalDays(rentalDays);
		// Check out date
		ra.setCheckoutDate(RentalAgreementFormat.formatDate(checkoutDate));
		// Due date
		ra.setDueDate(RentalAgreementFormat.formatDate(dueDate));
		// Daily rental charge
		ra.setDailyRentalCharge(RentalAgreementFormat.formatCurrency(tool.getType().getRate()));
		// Charge days
		ra.setChargeDays(chargeDays);
		// Pre-discount charge
		ra.setPrediscountCharge(RentalAgreementFormat.formatCurrency(beforeDiscount));
		// Discount percent
		ra.setDiscountPercentage(RentalAgreementFormat.formatPercentage(discount));
		// Discount amount
		ra.setDiscountAmount(RentalAgreementFormat.formatCurrency(discountAmount));
		// Final charge
		ra.setFinalCharge(RentalAgreementFormat.formatCurrency(afterDiscount));
		return ra;
	}

	/**
	 * Determines the rentals due date based on the specified checkout date and
	 * the number of rental days.
	 */
	private Calendar getDueDate(final Calendar checkoutDate, final int rentalDays) {
		Calendar dueDate = (Calendar) checkoutDate.clone();
		dueDate.add(Calendar.DAY_OF_MONTH, rentalDays);
		return dueDate;
	}

	/**
	 * I have mocked the service calls to the database due to a very small
	 * subset of tools.
	 */
	private Tool getToolForCode(String code) {
		switch (code) {
		case "LADW":
			return new Tool(code, "Werner", ToolType.Ladder);
		case "CHNS":
			return new Tool(code, "Stihl", ToolType.Chainsaw);
		case "JAKR":
			return new Tool(code, "Ridgid", ToolType.Jackhammer);
		case "JAKD":
			return new Tool(code, "DeWalt", ToolType.Jackhammer);
		}
		throw new IllegalArgumentException("Code not found, try 'LADW', 'CHNS', 'JAKR', or 'JAKD'");
	}

}
