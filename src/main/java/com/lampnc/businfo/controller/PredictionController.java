package com.lampnc.businfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lampnc.businfo.NotFoundException;
import com.lampnc.businfo.dao.PredictionDao;
import com.lampnc.businfo.dto.PredictionDto;

@RestController
@RequestMapping(value = "/predict")
public class PredictionController {

	@Autowired
	private PredictionDao predictionDao;

	@RequestMapping
	public void fallback() {
		throw new NotFoundException();
	}

	@RequestMapping(params = { "s" }, method = RequestMethod.GET)
	public List<PredictionDto> predictByStopId(int s) {
		return predictionDao.predictByStopId(s);
	}

	@RequestMapping(params = { "s", "r" }, method = RequestMethod.GET)
	public PredictionDto predictByStopRoute(int s, int r) {
		// return predictionDao.predictByStopRoute(3692, 116);
		return predictionDao.predictByStopRoute(s, r);
	}

}
