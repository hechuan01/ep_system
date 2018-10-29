package com.zx.xt.model;

import java.util.Date;

public class RecordInfo {

	private String carNumber;
	
	private Date parkDate;
	
	private double cost;

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Date getParkDate() {
		return parkDate;
	}

	public void setParkDate(Date parkDate) {
		this.parkDate = parkDate;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
}
