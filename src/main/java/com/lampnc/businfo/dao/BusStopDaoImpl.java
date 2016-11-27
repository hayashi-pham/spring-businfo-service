package com.lampnc.businfo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lampnc.businfo.NotFoundException;
import com.lampnc.businfo.dto.BusStopDto;
import com.lampnc.businfo.dto.BusStopMem;
import com.lampnc.businfo.entity.BusStop;
import com.lampnc.businfo.entity.BusStopDetail;

@Repository
@Transactional
public class BusStopDaoImpl implements BusStopDao {

	@Autowired
	private SessionFactory sessionFactory;

	private void setBusStopDtoProjectionList(Criteria cr) {
		cr.createAlias("geoZone", "g");
		ProjectionList pr = Projections.projectionList();
		pr.add(Projections.property("stopId"), "stopId");
		pr.add(Projections.property("stopType"), "stopType");
		pr.add(Projections.property("code"), "code");
		pr.add(Projections.property("name"), "name");
		pr.add(Projections.property("shortName"), "shortName");
		pr.add(Projections.property("addressNo"), "addressNo");
		pr.add(Projections.property("street"), "street");
		pr.add(Projections.property("ward"), "ward");
		pr.add(Projections.property("g.zoneName"), "zone");
		pr.add(Projections.property("latitude"), "latitude");
		pr.add(Projections.property("longitude"), "longitude");
		pr.add(Projections.property("disabilitySupport"), "disabilitySupport");
		pr.add(Projections.property("status"), "status");
		cr.setProjection(pr);
	}

	@Override
	public List<BusStopDto> listBusStops() {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(BusStop.class);
		setBusStopDtoProjectionList(cr);
		// cr.add(Restrictions.ne("status", (short) 3));
		cr.addOrder(Order.asc("stopId"));
		cr.setResultTransformer(Transformers.aliasToBean(BusStopDto.class));
		return cr.list();
	}

	@Override
	public List<BusStopMem> listAllStops() {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(BusStop.class);
		ProjectionList pr = Projections.projectionList();
		pr.add(Projections.property("stopId"), "stopId");
		pr.add(Projections.property("code"), "code");
		pr.add(Projections.property("name"), "name");
		pr.add(Projections.property("shortName"), "shortName");
		cr.setProjection(pr);
		// cr.add(Restrictions.ne("status", (short) 3));
		cr.addOrder(Order.asc("stopId"));
		cr.setResultTransformer(Transformers.aliasToBean(BusStopMem.class));
		return cr.list();
	}

	@Override
	public List<BusStopDto> listBusStops(double lng1, double lat1, double lng2, double lat2) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(BusStop.class);
		setBusStopDtoProjectionList(cr);
		cr.add(Restrictions.between("longitude", lng1, lng2));
		cr.add(Restrictions.between("latitude", lat1, lat2));
		// cr.add(Restrictions.ne("status", (short) 3));
		cr.addOrder(Order.asc("stopId"));
		cr.setResultTransformer(Transformers.aliasToBean(BusStopDto.class));
		return cr.list();
	}

	@Override
	public BusStopDto getBusStopById(int stopId) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(BusStop.class);
		setBusStopDtoProjectionList(cr);
		cr.add(Restrictions.eq("stopId", stopId));
		cr.setResultTransformer(Transformers.aliasToBean(BusStopDto.class));
		List<BusStopDto> rs = cr.list();
		if (rs.size() == 0) {
			throw new NotFoundException();
		}
		return rs.get(0);
	}

	@Override
	public List<BusStopDetail> listBusStopDetails(int planId, int stopId) {
		// TODO Auto-generated method stub
		return null;
	}

}
