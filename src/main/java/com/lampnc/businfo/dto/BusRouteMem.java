package com.lampnc.businfo.dto;

public class BusRouteMem {

	private int routeId;
	private String routeNo;
	private String routeName;

	public BusRouteMem() {
	}

	public BusRouteMem(int routeId, String routeNo, String routeName) {
		this.routeId = routeId;
		this.routeNo = routeNo;
		this.routeName = routeName;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getRouteNo() {
		return routeNo;
	}

	public void setRouteNo(String routeNo) {
		this.routeNo = routeNo;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

}
