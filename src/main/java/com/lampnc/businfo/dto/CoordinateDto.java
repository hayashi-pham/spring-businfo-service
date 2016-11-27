package com.lampnc.businfo.dto;

import java.util.ArrayList;
import java.util.List;

public class CoordinateDto {

	private List<Double> lat;
	private List<Double> lng;

	public CoordinateDto() {
		lat = new ArrayList<Double>();
		lng = new ArrayList<Double>();
	}

	public List<Double> getLat() {
		return lat;
	}

	public List<Double> getLng() {
		return lng;
	}

	public void add(double latitude, double longitude) {
		lat.add(latitude);
		lng.add(longitude);
	}

}
