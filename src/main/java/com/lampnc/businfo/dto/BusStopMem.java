package com.lampnc.businfo.dto;

public class BusStopMem {

	private int stopId;
	private String code;
	private String name;
	private String shortName;

	public BusStopMem() {
	}

	public BusStopMem(int stopId, String code, String name, String shortName) {
		this.stopId = stopId;
		this.code = code;
		this.name = name;
		this.shortName = shortName;
	}

	public int getStopId() {
		return stopId;
	}

	public void setStopId(int stopId) {
		this.stopId = stopId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}
