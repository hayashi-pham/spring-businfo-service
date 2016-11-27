package com.lampnc.businfo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "GeoZone", schema = "dbo", catalog = "QuanLyXeBuyt")
public class GeoZone implements java.io.Serializable {

	private int zoneId;
	private String zoneName;
	private short status;
	private Set<BusStop> busStops = new HashSet<BusStop>(0);

	public GeoZone() {
	}

	public GeoZone(int zoneId, String zoneName, short status) {
		this.zoneId = zoneId;
		this.zoneName = zoneName;
		this.status = status;
	}

	public GeoZone(int zoneId, String zoneName, short status, Set<BusStop> busStops) {
		this.zoneId = zoneId;
		this.zoneName = zoneName;
		this.status = status;
		this.busStops = busStops;
	}

	@Id

	@Column(name = "ZoneId", unique = true, nullable = false)
	public int getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	@Column(name = "ZoneName", nullable = false)
	public String getZoneName() {
		return this.zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	@Column(name = "Status", nullable = false)
	public short getStatus() {
		return this.status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "geoZone")
	public Set<BusStop> getBusStops() {
		return this.busStops;
	}

	public void setBusStops(Set<BusStop> busStops) {
		this.busStops = busStops;
	}

}
