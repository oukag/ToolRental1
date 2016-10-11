package com.tools.models;

import com.tools.enums.ToolType;

/**
 * POJO class to represent a tool object. Contains a unique tool code, the brand
 * of the specific tool, and the general tool type. Also contains a quick
 * constructor when given all three private member variables.
 */
public class Tool {
	private String code;
	private String brand;
	private ToolType type;

	public Tool(String code, String brand, ToolType type) {
		setCode(code);
		setBrand(brand);
		setType(type);
	}

	/*
	 * Getters and Setters
	 */
	public String getCode() {
		return this.code;
	}

	public String getBrand() {
		return this.brand;
	}

	public ToolType getType() {
		return this.type;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setType(ToolType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type.name() + " : " + brand + " : " + code;
	}
}
