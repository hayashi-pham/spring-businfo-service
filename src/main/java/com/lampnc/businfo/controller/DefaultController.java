package com.lampnc.businfo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
	@Autowired
	private Environment env;

	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public Map<String, Double> getDefaultCoords() {
		Map<String, Double> corrds = new HashMap<String, Double>();
		try {
			Double lat = Double.parseDouble(env.getProperty("com.lampnc.businfo.default.lat"));
			Double lng = Double.parseDouble(env.getProperty("com.lampnc.businfo.default.lng"));
			corrds.put("lat", lat);
			corrds.put("lng", lng);
		} catch (Exception e) {
			corrds.clear();
			corrds.put("lat", 0.0);
			corrds.put("lng", 0.0);
		}
		return corrds;
	}

}
