package com.lampnc.businfo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "BusRoute", schema = "dbo", catalog = "QuanLyXeBuyt")
public class BusRoute implements java.io.Serializable {

	private int routeId;
	private String routeNo;
	private String routeName;
	private String inBoundName;
	private String inBoundDescription;
	private String outBoundName;
	private String outBoundDescription;
	private short busType;
	private double distance;
	private String numOfSeats;
	private String operationTime;
	private String tickets;
	private String totalTrip;
	private String timeOfTrip;
	private String headway;
	private String color;
	private short status;
	private Set<BusRoutePlan> busRoutePlans = new HashSet<BusRoutePlan>(0);
	private Set<BusOrg> busOrgs = new HashSet<BusOrg>(0);

	public BusRoute() {
	}

	public BusRoute(int routeId, String routeNo, String routeName, String inBoundName, String inBoundDescription,
			String outBoundName, String outBoundDescription, short busType, double distance, String numOfSeats,
			String operationTime, String tickets, String totalTrip, String timeOfTrip, String headway, String color,
			short status) {
		this.routeId = routeId;
		this.routeNo = routeNo;
		this.routeName = routeName;
		this.inBoundName = inBoundName;
		this.inBoundDescription = inBoundDescription;
		this.outBoundName = outBoundName;
		this.outBoundDescription = outBoundDescription;
		this.busType = busType;
		this.distance = distance;
		this.numOfSeats = numOfSeats;
		this.operationTime = operationTime;
		this.tickets = tickets;
		this.totalTrip = totalTrip;
		this.timeOfTrip = timeOfTrip;
		this.headway = headway;
		this.color = color;
		this.status = status;
	}

	public BusRoute(int routeId, String routeNo, String routeName, String inBoundName, String inBoundDescription,
			String outBoundName, String outBoundDescription, short busType, double distance, String numOfSeats,
			String operationTime, String tickets, String totalTrip, String timeOfTrip, String headway, String color,
			short status, Set<BusRoutePlan> busRoutePlans, Set<BusOrg> busOrgs) {
		this.routeId = routeId;
		this.routeNo = routeNo;
		this.routeName = routeName;
		this.inBoundName = inBoundName;
		this.inBoundDescription = inBoundDescription;
		this.outBoundName = outBoundName;
		this.outBoundDescription = outBoundDescription;
		this.busType = busType;
		this.distance = distance;
		this.numOfSeats = numOfSeats;
		this.operationTime = operationTime;
		this.tickets = tickets;
		this.totalTrip = totalTrip;
		this.timeOfTrip = timeOfTrip;
		this.headway = headway;
		this.color = color;
		this.status = status;
		this.busRoutePlans = busRoutePlans;
		this.busOrgs = busOrgs;
	}

	@Id

	@Column(name = "RouteId", unique = true, nullable = false)
	public int getRouteId() {
		return this.routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	@Column(name = "RouteNo", nullable = false)
	public String getRouteNo() {
		return this.routeNo;
	}

	public void setRouteNo(String routeNo) {
		this.routeNo = routeNo;
	}

	@Column(name = "RouteName", nullable = false)
	public String getRouteName() {
		return this.routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	@Column(name = "InBoundName", nullable = false)
	public String getInBoundName() {
		return this.inBoundName;
	}

	public void setInBoundName(String inBoundName) {
		this.inBoundName = inBoundName;
	}

	@Column(name = "InBoundDescription", nullable = false)
	public String getInBoundDescription() {
		return this.inBoundDescription;
	}

	public void setInBoundDescription(String inBoundDescription) {
		this.inBoundDescription = inBoundDescription;
	}

	@Column(name = "OutBoundName", nullable = false)
	public String getOutBoundName() {
		return this.outBoundName;
	}

	public void setOutBoundName(String outBoundName) {
		this.outBoundName = outBoundName;
	}

	@Column(name = "OutBoundDescription", nullable = false)
	public String getOutBoundDescription() {
		return this.outBoundDescription;
	}

	public void setOutBoundDescription(String outBoundDescription) {
		this.outBoundDescription = outBoundDescription;
	}

	@Column(name = "BusType", nullable = false)
	public short getBusType() {
		return this.busType;
	}

	public void setBusType(short busType) {
		this.busType = busType;
	}

	@Column(name = "Distance", nullable = false, precision = 12, scale = 1)
	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Column(name = "NumOfSeats", nullable = false)
	public String getNumOfSeats() {
		return this.numOfSeats;
	}

	public void setNumOfSeats(String numOfSeats) {
		this.numOfSeats = numOfSeats;
	}

	@Column(name = "OperationTime", nullable = false)
	public String getOperationTime() {
		return this.operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	@Column(name = "Tickets", nullable = false)
	public String getTickets() {
		return this.tickets;
	}

	public void setTickets(String tickets) {
		this.tickets = tickets;
	}

	@Column(name = "TotalTrip", nullable = false)
	public String getTotalTrip() {
		return this.totalTrip;
	}

	public void setTotalTrip(String totalTrip) {
		this.totalTrip = totalTrip;
	}

	@Column(name = "TimeOfTrip", nullable = false)
	public String getTimeOfTrip() {
		return this.timeOfTrip;
	}

	public void setTimeOfTrip(String timeOfTrip) {
		this.timeOfTrip = timeOfTrip;
	}

	@Column(name = "Headway", nullable = false)
	public String getHeadway() {
		return this.headway;
	}

	public void setHeadway(String headway) {
		this.headway = headway;
	}

	@Column(name = "Color", nullable = false)
	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column(name = "Status", nullable = false)
	public short getStatus() {
		return this.status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "busRoute")
	public Set<BusRoutePlan> getBusRoutePlans() {
		return this.busRoutePlans;
	}

	public void setBusRoutePlans(Set<BusRoutePlan> busRoutePlans) {
		this.busRoutePlans = busRoutePlans;
	}

	@JsonProperty("orgs")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "busRoute")
	public Set<BusOrg> getBusOrgs() {
		return this.busOrgs;
	}

	public void setBusOrgs(Set<BusOrg> busOrgs) {
		this.busOrgs = busOrgs;
	}

}
