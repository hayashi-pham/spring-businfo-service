package com.lampnc.businfo.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BusTimeTableDto {

	private int planId;
	private int timeTableId;
	private String applyDates;
	private Date endDate;
	private String headway;
	private String operationTime;
	private String runningTime;
	private Date startDate;
	private int totalTrip;
	private short status;

	public BusTimeTableDto() {
	}

	public BusTimeTableDto(int planId, int timeTableId, String applyDates, Date endDate, String headway,
			String operationTime, String runningTime, Date startDate, int totalTrip, short status) {
		this.planId = planId;
		this.timeTableId = timeTableId;
		this.applyDates = applyDates;
		this.endDate = endDate;
		this.headway = headway;
		this.operationTime = operationTime;
		this.runningTime = runningTime;
		this.startDate = startDate;
		this.totalTrip = totalTrip;
		this.status = status;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getTimeTableId() {
		return timeTableId;
	}

	public void setTimeTableId(int timeTableId) {
		this.timeTableId = timeTableId;
	}

	public String getApplyDates() {
		return applyDates;
	}

	public void setApplyDates(String applyDates) {
		this.applyDates = applyDates;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getHeadway() {
		return headway;
	}

	public void setHeadway(String headway) {
		this.headway = headway;
	}

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	public String getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getTotalTrip() {
		return totalTrip;
	}

	public void setTotalTrip(int totalTrip) {
		this.totalTrip = totalTrip;
	}

	@JsonIgnore
	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

}
