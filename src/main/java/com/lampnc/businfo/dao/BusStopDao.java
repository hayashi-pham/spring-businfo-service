package com.lampnc.businfo.dao;

import java.util.List;

import com.lampnc.businfo.dto.BusStopDto;
import com.lampnc.businfo.dto.BusStopMem;
import com.lampnc.businfo.entity.BusStopDetail;

public interface BusStopDao {

	public List<BusStopDto> listBusStops();

	public List<BusStopMem> listAllStops();

	public List<BusStopDto> listBusStops(double lng1, double lat1, double lng2, double lat2);

	public BusStopDto getBusStopById(int stopId);

	public List<BusStopDetail> listBusStopDetails(int planId, int stopId);

}
