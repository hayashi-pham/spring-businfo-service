package com.lampnc.businfo.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.LocaleResolver;

import com.lampnc.businfo.NotFoundException;
import com.lampnc.businfo.dto.PredictionDto;
import com.lampnc.businfo.dto.VehicleDto;
import com.lampnc.businfo.entity.BusRoute;
import com.lampnc.businfo.entity.BusRoutePlan;
import com.lampnc.businfo.entity.BusStop;
import com.lampnc.businfo.entity.BusStopDetail;
import com.lampnc.businfo.entity.BusStopDetailId;
import com.lampnc.businfo.entity.BusTrip;

@Repository
@Transactional
public class PredictionDaoImpl implements PredictionDao {

	@Autowired
	private SessionFactory sessionFactory;

	// @Autowired
	// private LocaleResolver localeResolver;

	private String getDayOfWeekAlias(Calendar cal) {
		if (cal == null) {
			// TimeZone timeZone = ((SessionLocaleResolver)
			// localeResolver).getDefaultTimeZone();
			cal = Calendar.getInstance();
		}
		int dow = cal.get(Calendar.DAY_OF_WEEK);
		switch (dow) {
		case Calendar.MONDAY:
			return "T2";
		case Calendar.TUESDAY:
			return "T3";
		case Calendar.WEDNESDAY:
			return "T4";
		case Calendar.THURSDAY:
			return "T5";
		case Calendar.FRIDAY:
			return "T6";
		case Calendar.SATURDAY:
			return "T7";
		case Calendar.SUNDAY:
			return "CN";
		default:
			return "";
		}
	}

	private Date getDate(int totalSecs) {
		try {
			int hours = totalSecs / 3600;
			int minutes = (totalSecs % 3600) / 60;
			int seconds = totalSecs % 60;
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			String str = String.format("%02d:%02d:%02d", hours, minutes, seconds);
			return df.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

	private int getSecs(Date date) {
		if (date == null)
			date = new Date();
		// TimeZone timeZone = ((SessionLocaleResolver)
		// localeResolver).getDefaultTimeZone();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY) * 60 * 60 + c.get(Calendar.MINUTE) * 60 + c.get(Calendar.SECOND);
	}

	@Override
	public List<PredictionDto> predictByStopId(int stopId) {
		Criteria c1 = sessionFactory.getCurrentSession().createCriteria(BusRoutePlan.class);
		c1.createAlias("busRoute", "r");
		c1.createAlias("busStopDetails", "d");
		c1.createAlias("d.busStop", "s");
		// c1.add(Restrictions.eq("outbound", true));
		c1.add(Restrictions.eq("s.stopId", stopId));
		c1.addOrder(Order.asc("r.routeId"));
		c1.addOrder(Order.asc("planId"));
		c1.addOrder(Order.asc("outbound"));
		List<BusRoutePlan> rs = c1.list();
		if (rs.size() == 0)
			throw new NotFoundException();
		List<PredictionDto> pds = new ArrayList<PredictionDto>();
		for (BusRoutePlan p : rs) {
			pds.add(predictByPlan(stopId, p));
		}
		return pds;
	}

	@Override
	public PredictionDto predictByStopRoute(int stopId, int routeId) {
		Criteria c1 = sessionFactory.getCurrentSession().createCriteria(BusRoutePlan.class);
		c1.createAlias("busRoute", "r");
		c1.createAlias("busStopDetails", "d");
		c1.createAlias("d.busStop", "s");
		c1.add(Restrictions.eq("outbound", true));
		c1.add(Restrictions.eq("s.stopId", stopId));
		c1.add(Restrictions.eq("r.routeId", routeId));
		c1.addOrder(Order.asc("r.routeId"));
		c1.addOrder(Order.asc("planId"));
		c1.setMaxResults(1);
		List<BusRoutePlan> rs = c1.list();
		if (rs.size() == 0)
			throw new NotFoundException();
		return predictByPlan(stopId, rs.get(0));
	}

	@Override
	public PredictionDto predictByPlan(int stopId, BusRoutePlan plan) {
		BusRoute r = plan.getBusRoute();
		BusStop s = sessionFactory.getCurrentSession().get(BusStop.class, stopId);

		PredictionDto pd = new PredictionDto();
		pd.setRouteId(r.getRouteId());
		pd.setRouteNo(r.getRouteNo());
		pd.setRouteName(r.getRouteName());
		pd.setStopId(s.getStopId());
		pd.setStopName(s.getShortName());
		pd.setPlanId(plan.getPlanId());
		pd.setPlanShortName(plan.getShortName());

		// Criteria c2 =
		// sessionFactory.getCurrentSession().createCriteria(BusStopDetail.class);
		// c2.createAlias("busRoutePlan", "p");
		// c2.createAlias("busStop", "s");
		// c2.add(Restrictions.eq("p.planId", p.getPlanId()));
		// c2.add(Restrictions.eq("s.stopId", s.getStopId()));
		// c2.addOrder(Order.asc("orderNo"));
		// c2.setMaxResults(1);

		// List<BusStopDetail> ds = c2.list();
		// if (ds.size() == 0) throw new NotFoundException();
		// BusStopDetail d = ds.get(0);

		BusStopDetailId id = new BusStopDetailId(plan.getPlanId(), s.getStopId());
		BusStopDetail d = sessionFactory.getCurrentSession().get(BusStopDetail.class, id);
		if (d == null)
			throw new NotFoundException();
		int loc = d.getOrderNo();

		String dow = getDayOfWeekAlias(null);
		int now_time = getSecs(null);
		int stops_count = plan.getBusStopDetails().size();
		int avg_time = Math.round(plan.getRunningTime() * 60f / (stops_count - 1f));
		Date start_time = getDate(now_time - avg_time * loc);

		Criteria c3 = sessionFactory.getCurrentSession().createCriteria(BusTrip.class);
		c3.createAlias("busTimeTable", "t");
		c3.createAlias("t.busRoutePlan", "p");
		c3.add(Restrictions.eq("p.planId", plan.getPlanId()));
		c3.add(Restrictions.ilike("t.applyDates", "%" + dow + "%"));
		c3.add(Restrictions.ge("startTime", start_time));
		c3.addOrder(Order.asc("startTime"));
		c3.addOrder(Order.asc("p.planId"));
		c3.addOrder(Order.asc("t.id.timeTableId"));
		c3.addOrder(Order.asc("p.outbound"));
		c3.setFirstResult(0);
		c3.setMaxResults(2);

		List<BusTrip> ts = c3.list();
		List<VehicleDto> vs = new ArrayList<VehicleDto>();
		for (BusTrip t : ts) {
			VehicleDto v = new VehicleDto();
			BusRoutePlan b = t.getBusTimeTable().getBusRoutePlan();
			int start_t = getSecs(t.getStartTime());
			int stop_time = start_t + avg_time * loc;
			int timeleft = (int) (stop_time - now_time);
			float speed = (float) (b.getDistance() * 0.06 / b.getRunningTime());
			int distance = (int) (speed * 1000 * 60 * 60);
			v.setDistance(distance);
			v.setSpeed(speed);
			v.setTimeLeft(timeleft);
			vs.add(v);
		}
		pd.setVehicles(vs);

		return pd;
	}

}
