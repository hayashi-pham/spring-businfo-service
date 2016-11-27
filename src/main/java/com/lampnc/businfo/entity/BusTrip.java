package com.lampnc.businfo.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BusTrip", schema = "dbo", catalog = "QuanLyXeBuyt")
public class BusTrip implements java.io.Serializable {

	private int tripId;
	private BusTimeTable busTimeTable;
	private Date startTime;
	private Date endTime;

	public BusTrip() {
	}

	public BusTrip(int tripId, BusTimeTable busTimeTable, Date startTime, Date endTime) {
		this.tripId = tripId;
		this.busTimeTable = busTimeTable;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Id
	@Column(name = "TripId", unique = true, nullable = false)
	public int getTripId() {
		return this.tripId;
	}

	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "PlanId", referencedColumnName = "PlanId", nullable = false),
			@JoinColumn(name = "TimeTableId", referencedColumnName = "TimeTableId", nullable = false) })
	public BusTimeTable getBusTimeTable() {
		return this.busTimeTable;
	}

	public void setBusTimeTable(BusTimeTable busTimeTable) {
		this.busTimeTable = busTimeTable;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "StartTime", nullable = false, length = 16)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "EndTime", nullable = false, length = 16)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
