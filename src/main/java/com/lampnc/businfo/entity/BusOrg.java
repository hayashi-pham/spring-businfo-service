package com.lampnc.businfo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "BusOrg", schema = "dbo", catalog = "QuanLyXeBuyt")
public class BusOrg implements java.io.Serializable {

	private int orgId;
	private BusRoute busRoute;
	private String orgName;
	private String orgPhone;

	public BusOrg() {
	}

	public BusOrg(int orgId, BusRoute busRoute, String orgName, String orgPhone) {
		this.orgId = orgId;
		this.busRoute = busRoute;
		this.orgName = orgName;
		this.orgPhone = orgPhone;
	}

	@Id
	@JsonIgnore
	@Column(name = "OrgId", unique = true, nullable = false)
	public int getOrgId() {
		return this.orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
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

	@JsonProperty("name")
	@Column(name = "OrgName", nullable = false)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@JsonProperty("phone")
	@Column(name = "OrgPhone", nullable = false)
	public String getOrgPhone() {
		return this.orgPhone;
	}

	public void setOrgPhone(String orgPhone) {
		this.orgPhone = orgPhone;
	}

}
