package com.lampnc.businfo.entity;
// Generated Nov 18, 2016 5:58:34 AM by Hibernate Tools 5.2.0.Beta1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BusStopDetailId generated by hbm2java
 */
@Embeddable
public class BusStopDetailId implements java.io.Serializable {

	private int planId;
	private int stopId;

	public BusStopDetailId() {
	}

	public BusStopDetailId(int planId, int stopId) {
		this.planId = planId;
		this.stopId = stopId;
	}

	@Column(name = "PlanId", nullable = false)
	public int getPlanId() {
		return this.planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	@Column(name = "StopId", nullable = false)
	public int getStopId() {
		return this.stopId;
	}

	public void setStopId(int stopId) {
		this.stopId = stopId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BusStopDetailId))
			return false;
		BusStopDetailId castOther = (BusStopDetailId) other;

		return (this.getPlanId() == castOther.getPlanId()) && (this.getStopId() == castOther.getStopId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getPlanId();
		result = 37 * result + this.getStopId();
		return result;
	}

}
