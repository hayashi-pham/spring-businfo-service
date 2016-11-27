package com.lampnc.businfo.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BusRoutePlan", schema = "dbo", catalog = "QuanLyXeBuyt")
public class BusRoutePlan implements java.io.Serializable {

	private int planId;
	private BusRoute busRoute;
	private String name;
	private String shortName;
	private double distance;
	private boolean outbound;
	private int runningTime;
	private short status;
	private Set<BusStopDetail> busStopDetails = new HashSet<BusStopDetail>(0);
	private Set<BusTimeTable> busTimeTables = new HashSet<BusTimeTable>(0);
	private Set<BusPathDetail> busPathDetails = new HashSet<BusPathDetail>(0);

	public BusRoutePlan() {
	}

	public BusRoutePlan(int planId, BusRoute busRoute, String name, String shortName, double distance, boolean outbound,
			int runningTime, short status) {
		this.planId = planId;
		this.busRoute = busRoute;
		this.name = name;
		this.shortName = shortName;
		this.distance = distance;
		this.outbound = outbound;
		this.runningTime = runningTime;
		this.status = status;
	}

	public BusRoutePlan(int planId, BusRoute busRoute, String name, String shortName, double distance, boolean outbound,
			int runningTime, short status, Set<BusStopDetail> busStopDetails, Set<BusTimeTable> busTimeTables,
			Set<BusPathDetail> busPathDetails) {
		this.planId = planId;
		this.busRoute = busRoute;
		this.name = name;
		this.shortName = shortName;
		this.distance = distance;
		this.outbound = outbound;
		this.runningTime = runningTime;
		this.status = status;
		this.busStopDetails = busStopDetails;
		this.busTimeTables = busTimeTables;
		this.busPathDetails = busPathDetails;
	}

	@Id
	@Column(name = "PlanId", unique = true, nullable = false)
	public int getPlanId() {
		return this.planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RouteId", nullable = false)
	public BusRoute getBusRoute() {
		return this.busRoute;
	}

	public void setBusRoute(BusRoute busRoute) {
		this.busRoute = busRoute;
	}

	@Column(name = "Name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ShortName", nullable = false)
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "Distance", nullable = false, precision = 18, scale = 12)
	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Column(name = "Outbound", nullable = false)
	public boolean isOutbound() {
		return this.outbound;
	}

	public void setOutbound(boolean outbound) {
		this.outbound = outbound;
	}

	@Column(name = "RunningTime", nullable = false)
	public int getRunningTime() {
		return this.runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	@JsonIgnore
	@Column(name = "Status", nullable = false)
	public short getStatus() {
		return this.status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "busRoutePlan")
	public Set<BusStopDetail> getBusStopDetails() {
		return this.busStopDetails;
	}

	public void setBusStopDetails(Set<BusStopDetail> busStopDetails) {
		this.busStopDetails = busStopDetails;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "busRoutePlan")
	public Set<BusTimeTable> getBusTimeTables() {
		return this.busTimeTables;
	}

	public void setBusTimeTables(Set<BusTimeTable> busTimeTables) {
		this.busTimeTables = busTimeTables;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "busRoutePlan")
	public Set<BusPathDetail> getBusPathDetails() {
		return this.busPathDetails;
	}

	public void setBusPathDetails(Set<BusPathDetail> busPathDetails) {
		this.busPathDetails = busPathDetails;
	}

}
