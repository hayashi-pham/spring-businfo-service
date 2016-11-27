package com.lampnc.businfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lampnc.businfo.dao.BusStopDao;
import com.lampnc.businfo.dto.BusStopDto;
import com.lampnc.businfo.dto.BusStopMem;
import com.lampnc.businfo.dto.GeoLocation;

@RestController
public class BusStopController {

	@Autowired
	private BusStopDao busStopDao;

	@RequestMapping(value = "/stops", method = RequestMethod.GET)
	public List<BusStopMem> getStops() {
		return busStopDao.listAllStops();
	}

	@RequestMapping(value = "/stop/all", method = RequestMethod.GET)
	public List<BusStopDto> getAllStops() {
		return busStopDao.listBusStops();
	}

	@RequestMapping(value = "/stops/{lng}/{lat}/", method = RequestMethod.GET)
	public List<BusStopDto> getStops(@PathVariable double lng, @PathVariable double lat) {
		GeoLocation cur = GeoLocation.fromDegrees(lat, lng);
		GeoLocation[] loc = cur.boundingCoordinates(100, 6371010);
		double lng1 = loc[0].getLongitudeInDegrees();
		double lat1 = loc[0].getLatitudeInDegrees();
		double lng2 = loc[1].getLongitudeInDegrees();
		double lat2 = loc[1].getLatitudeInDegrees();
		return busStopDao.listBusStops(lng1, lat1, lng2, lat2);
	}

	@RequestMapping(value = "/stops/{lng1}/{lat1}/{lng2}/{lat2}/", method = RequestMethod.GET)
	public List<BusStopDto> getStops(@PathVariable double lng1, @PathVariable double lat1, @PathVariable double lng2,
			@PathVariable double lat2) {
		return busStopDao.listBusStops(lng1, lat1, lng2, lat2);
	}

	@RequestMapping(value = "/stop/{stopId}", method = RequestMethod.GET)
	public BusStopDto getStop(@PathVariable int stopId) {
		return busStopDao.getBusStopById(stopId);
	}

}
