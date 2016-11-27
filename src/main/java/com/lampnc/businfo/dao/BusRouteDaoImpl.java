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
import com.lampnc.businfo.dto.BusRouteMem;
import com.lampnc.businfo.dto.BusStopDto;
import com.lampnc.businfo.dto.BusTimeTableDto;
import com.lampnc.businfo.dto.CoordinateDto;
import com.lampnc.businfo.entity.BusPathDetail;
import com.lampnc.businfo.entity.BusRoute;
import com.lampnc.businfo.entity.BusRoutePlan;
import com.lampnc.businfo.entity.BusStopDetail;
import com.lampnc.businfo.entity.BusTimeTable;
import com.lampnc.businfo.entity.BusTrip;

@Repository
@Transactional
public class BusRouteDaoImpl implements BusRouteDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<BusRouteMem> listAllRoutes() {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(BusRoute.class);
		ProjectionList pr = Projections.projectionList();
		pr.add(Projections.property("routeId"), "routeId");
		pr.add(Projections.property("routeNo"), "routeNo");
		pr.add(Projections.property("routeName"), "routeName");
		cr.setProjection(pr);
		// cr.add(Restrictions.ne("status", (short) 3));
		cr.addOrder(Order.asc("routeNo"));
		cr.setResultTransformer(Transformers.aliasToBean(BusRouteMem.class));
		return cr.list();
	}

	@Override
	public List<BusRoute> listBusRoutes() {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(BusRoute.class);
		// cr.add(Restrictions.ne("status", (short) 3));
		return cr.list();
	}

	@Override
	public BusRoute getBusRouteById(int routeId) {
		BusRoute rt = sessionFactory.getCurrentSession().get(BusRoute.class, routeId);
		if (rt == null /* || rt.getStatus() == (short) 3 */) {
			throw new NotFoundException();
		}
		return rt;
	}

	@Override
	public List<BusRouteMem> listRoutesThroughStop(int stopId) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(BusRoute.class);
		cr.createAlias("busRoutePlans", "p");
		cr.createAlias("p.busStopDetails", "d");
		cr.createAlias("d.busStop", "s");
		ProjectionList pr = Projections.projectionList();
		pr.add(Projections.property("routeId"), "routeId");
		pr.add(Projections.property("routeNo"), "routeNo");
		pr.add(Projections.property("routeName"), "routeName");
		cr.setProjection(pr);
		cr.add(Restrictions.eq("s.stopId", stopId));
		cr.addOrder(Order.asc("routeId"));
		cr.setResultTransformer(Transformers.aliasToBean(BusRouteMem.class));
		return cr.list();
	}

	@Override
	public List<BusRoutePlan> listRoutePlans(int routeId) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(BusRoutePlan.class);
		cr.createAlias("busRoute", "r");
		cr.add(Restrictions.eq("r.routeId", routeId));
		cr.addOrder(Order.asc("planId"));
		return cr.list();
	}

	@Override
	public List<BusTimeTable> listTimeTable(int routeId) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(BusTimeTable.class);
		cr.createAlias("busRoutePlan", "p");
		cr.createAlias("p.busRoute", "r");
		ProjectionList pr = Projections.projectionList();
		pr.add(Projections.property("id.planId"), "planId");
		pr.add(Projections.property("id.timeTableId"), "timeTableId");
		pr.add(Projections.property("applyDates"), "applyDates");
		pr.add(Projections.property("endDate"), "endDate");
		pr.add(Projections.property("headway"), "headway");
		pr.add(Projections.property("operationTime"), "operationTime");
		pr.add(Projections.property("runningTime"), "runningTime");
		pr.add(Projections.property("startDate"), "startDate");
		pr.add(Projections.property("totalTrip"), "totalTrip");
		pr.add(Projections.property("status"), "status");
		cr.setProjection(pr);
		cr.add(Restrictions.eq("r.routeId", routeId));
		cr.addOrder(Order.asc("id.timeTableId"));
		cr.setResultTransformer(Transformers.aliasToBean(BusTimeTableDto.class));
		return cr.list();
	}

	@Override
	public List<BusTrip> listTripsByTimeTable(int routeId, int timeTableId) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(BusTrip.class);
		cr.createAlias("busTimeTable", "t");
		cr.createAlias("t.busRoutePlan", "p");
		cr.createAlias("p.busRoute", "r");
		cr.add(Restrictions.eq("t.id.timeTableId", timeTableId));
		cr.add(Restrictions.eq("r.routeId", routeId));
		cr.addOrder(Order.asc("tripId"));
		return cr.list();
	}

	@Override
	public List<BusStopDto> listStopsByPlan(int routeId, int planId) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(BusStopDetail.class);
		cr.createAlias("busStop", "s");
		cr.createAlias("s.geoZone", "g");
		cr.createAlias("busRoutePlan", "p");
		cr.createAlias("p.busRoute", "r");
		ProjectionList pr = Projections.projectionList();
		pr.add(Projections.property("s.stopId"), "stopId");
		pr.add(Projections.property("s.stopType"), "stopType");
		pr.add(Projections.property("s.code"), "code");
		pr.add(Projections.property("s.name"), "name");
		pr.add(Projections.property("s.shortName"), "shortName");
		pr.add(Projections.property("s.addressNo"), "addressNo");
		pr.add(Projections.property("s.street"), "street");
		pr.add(Projections.property("s.ward"), "ward");
		pr.add(Projections.property("g.zoneName"), "zone");
		pr.add(Projections.property("s.latitude"), "latitude");
		pr.add(Projections.property("s.longitude"), "longitude");
		pr.add(Projections.property("s.disabilitySupport"), "disabilitySupport");
		pr.add(Projections.property("s.status"), "status");
		cr.setProjection(pr);
		cr.add(Restrictions.eq("id.planId", planId));
		cr.add(Restrictions.eq("r.routeId", routeId));
		cr.addOrder(Order.asc("orderNo"));
		cr.setResultTransformer(Transformers.aliasToBean(BusStopDto.class));
		return cr.list();
	}

	@Override
	public CoordinateDto listPointsByPlan(int routeId, int planId) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(BusPathDetail.class);
		cr.createAlias("busRoutePlan", "p");
		cr.createAlias("p.busRoute", "r");
		cr.add(Restrictions.eq("id.planId", planId));
		cr.add(Restrictions.eq("r.routeId", routeId));
		cr.addOrder(Order.asc("id.orderNo"));
		List<BusPathDetail> ls = cr.list();
		if (ls.size() == 0) {
			throw new NotFoundException();
		}
		CoordinateDto dto = new CoordinateDto();
		for (BusPathDetail dt : ls) {
			dto.add(dt.getLatitude(), dt.getLongitude());
		}
		return dto;
	}

}
