package com.lampnc.businfo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

	@RequestMapping(value = "/dist/{lng1}/{lat1}/{lng2}/{lat2}/", method = RequestMethod.GET)
	public double getDistance(@PathVariable double lng1, @PathVariable double lat1, @PathVariable double lng2,
			@PathVariable double lat2) {
		double radius = 6371010;
		double radLat1 = Math.toRadians(lat1);
		double radLat2 = Math.toRadians(lat2);
		double radLon1 = Math.toRadians(lng1);
		double radLon2 = Math.toRadians(lng1);
		return Math.acos(Math.sin(radLat1) * Math.sin(radLat2)
				+ Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radLon1 - radLon2)) * radius;
	}

}
