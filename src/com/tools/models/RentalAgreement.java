package com.tools.models;

/**
 * Basic POJO class to represent a rental agreement. Only contains getters and setters for the private variables.
 */
public class RentalAgreement {

	private String toolCode;
	private String toolType;
	private String toolBrand;
	private int rentalDays;
	private String checkoutDate;
	private String dueDate;
	private String dailyRentalCharge;
	private int chargeDays;
	private String prediscountCharge;
	private String discountPercentage;
	private String discountAmount;
	private String finalCharge;

	public String getToolCode() {
		return toolCode;
	}

	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}

	public String getToolType() {
		return toolType;
	}

	public void setToolType(String toolType) {
		this.toolType = toolType;
	}

	public String getToolBrand() {
		return toolBrand;
	}

	public void setToolBrand(String toolBrand) {
		this.toolBrand = toolBrand;
	}

	public int getRentalDays() {
		return rentalDays;
	}

	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getDailyRentalCharge() {
		return dailyRentalCharge;
	}

	public void setDailyRentalCharge(String dailyRentalCharge) {
		this.dailyRentalCharge = dailyRentalCharge;
	}

	public int getChargeDays() {
		return chargeDays;
	}

	public void setChargeDays(int chargeDays) {
		this.chargeDays = chargeDays;
	}

	public String getPrediscountCharge() {
		return prediscountCharge;
	}

	public void setPrediscountCharge(String prediscountCharge) {
		this.prediscountCharge = prediscountCharge;
	}

	public String getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getFinalCharge() {
		return finalCharge;
	}

	public void setFinalCharge(String finalCharge) {
		this.finalCharge = finalCharge;
	}
}
