package com.zx.xt.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ParkRecord implements Serializable {
	
    private String id;
    private Date startTime;
    private Date endTime;
    private double cost;
    private int payWay;
    private String plateNumber;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getPayWay() {
		return payWay;
	}

	public void setPayWay(int payWay) {
		this.payWay = payWay;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

    
}