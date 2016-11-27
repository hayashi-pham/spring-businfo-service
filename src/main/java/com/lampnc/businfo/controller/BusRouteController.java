package com.lampnc.businfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lampnc.businfo.dao.BusRouteDao;
import com.lampnc.businfo.dto.BusRouteMem;
import com.lampnc.businfo.dto.BusStopDto;
import com.lampnc.businfo.dto.CoordinateDto;
import com.lampnc.businfo.entity.BusRoute;
import com.lampnc.businfo.entity.BusRoutePlan;
import com.lampnc.businfo.entity.BusTimeTable;
import com.lampnc.businfo.entity.BusTrip;

@RestController
public class BusRouteController {

	@Autowired
	private BusRouteDao busRouteDao;

	// get all route	
	@RequestMapping(value = "/routes", method = RequestMethod.GET)
	public List<BusRouteMem> getRoutes() {
		return busRouteDao.listAllRoutes();
	}

	// get routes through stop
	@RequestMapping(value = "/routes", params = { "stopId" }, method = RequestMethod.GET)
	public List<BusRouteMem> getStopsList(@RequestParam int stopId) {
		return busRouteDao.listRoutesThroughStop(stopId);
	}
	
	@RequestMapping(value = "/stop/{stopId}/routes", method = RequestMethod.GET)
	public List<BusRouteMem> getStops(@PathVariable int stopId) {
		return busRouteDao.listRoutesThroughStop(stopId);
	}

	// get route by id
	@RequestMapping(value = "/route/{routeId}", method = RequestMethod.GET)
	public BusRoute getRoute(@PathVariable int routeId) {
		return busRouteDao.getBusRouteById(routeId);
	}

	// get vars by route
	@RequestMapping(value = "/route/{routeId}/plans", method = RequestMethod.GET)
	public List<BusRoutePlan> getPlans(@PathVariable int routeId) {
		return busRouteDao.listRoutePlans(routeId);
	}

	// get timetable by route
	@RequestMapping(value = "/route/{routeId}/trips", method = RequestMethod.GET)
	public List<BusTimeTable> getTimeTables(@PathVariable int routeId) {
		return busRouteDao.listTimeTable(routeId);
	}

	// get trips by timetable
	@RequestMapping(value = "/route/{routeId}/trips/{timeTableId}", method = RequestMethod.GET)
	public List<BusTrip> getTrips(@PathVariable int routeId, @PathVariable int timeTableId) {
		return busRouteDao.listTripsByTimeTable(routeId, timeTableId);
	}

	// get stops by var
	@RequestMapping(value = "/route/{routeId}/{planId}/stops", method = RequestMethod.GET)
	public List<BusStopDto> getStops(@PathVariable int routeId, @PathVariable int planId) {
		return busRouteDao.listStopsByPlan(routeId, planId);
	}

	// get paths by var
	@RequestMapping(value = "/route/{routeId}/{planId}/paths", method = RequestMethod.GET)
	public CoordinateDto getPaths(@PathVariable int routeId, @PathVariable int planId) {
		return busRouteDao.listPointsByPlan(routeId, planId);
	}

}
