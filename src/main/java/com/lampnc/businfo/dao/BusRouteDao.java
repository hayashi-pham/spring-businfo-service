package com.lampnc.businfo.dao;

import java.util.List;

import com.lampnc.businfo.dto.BusRouteMem;
import com.lampnc.businfo.dto.BusStopDto;
import com.lampnc.businfo.dto.CoordinateDto;
import com.lampnc.businfo.entity.BusRoute;
import com.lampnc.businfo.entity.BusRoutePlan;
import com.lampnc.businfo.entity.BusTimeTable;
import com.lampnc.businfo.entity.BusTrip;

public interface BusRouteDao {

	public List<BusRoute> listBusRoutes();

	public List<BusRouteMem> listAllRoutes();

	public BusRoute getBusRouteById(int routeId);

	public List<BusRouteMem> listRoutesThroughStop(int stopId);

	public List<BusRoutePlan> listRoutePlans(int routeId);

	public List<BusTimeTable> listTimeTable(int routeId);

	public List<BusTrip> listTripsByTimeTable(int routeId, int timeTableId);

	public List<BusStopDto> listStopsByPlan(int routeId, int planId);

	public CoordinateDto listPointsByPlan(int routeId, int planId);

}
