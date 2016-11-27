package com.lampnc.businfo.dao;

import java.util.List;

import com.lampnc.businfo.dto.PredictionDto;
import com.lampnc.businfo.entity.BusRoutePlan;

public interface PredictionDao {

	public List<PredictionDto> predictByStopId(int stopId);

	public PredictionDto predictByStopRoute(int stopId, int routeId);

	public PredictionDto predictByPlan(int stopId, BusRoutePlan plan);

	// public PredictionDto predictByStop(int sStopId, int dStopId);

}
