package com.lampnc.businfo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BusStop", schema = "dbo", catalog = "QuanLyXeBuyt")
public class BusStop implements java.io.Serializable {

	private int stopId;
	private GeoZone geoZone;
	private short stopType;
	private String code;
	private String name;
	private String shortName;
	private String addressNo;
	private String street;
	private String ward;
	private double latitude;
	private double longitude;
	private boolean disabilitySupport;
	private short status;
	private Set<BusStopDetail> busStopDetails = new HashSet<BusStopDetail>(0);

	public BusStop() {
	}

	public BusStop(int stopId, GeoZone geoZone, short stopType, String code, String name, String shortName,
			String addressNo, String street, String ward, double latitude, double longitude, boolean disabilitySupport,
			short status) {
		this.stopId = stopId;
		this.geoZone = geoZone;
		this.stopType = stopType;
		this.code = code;
		this.name = name;
		this.shortName = shortName;
		this.addressNo = addressNo;
		this.street = street;
		this.ward = ward;
		this.latitude = latitude;
		this.longitude = longitude;
		this.disabilitySupport = disabilitySupport;
		this.status = status;
	}

	public BusStop(int stopId, GeoZone geoZone, short stopType, String code, String name, String shortName,
			String addressNo, String street, String ward, double latitude, double longitude, boolean disabilitySupport,
			short status, Set<BusStopDetail> busStopDetails) {
		this.stopId = stopId;
		this.geoZone = geoZone;
		this.stopType = stopType;
		this.code = code;
		this.name = name;
		this.shortName = shortName;
		this.addressNo = addressNo;
		this.street = street;
		this.ward = ward;
		this.latitude = latitude;
		this.longitude = longitude;
		this.disabilitySupport = disabilitySupport;
		this.status = status;
		this.busStopDetails = busStopDetails;
	}

	@Id

	@Column(name = "StopId", unique = true, nullable = false)
	public int getStopId() {
		return this.stopId;
	}

	public void setStopId(int stopId) {
		this.stopId = stopId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ZoneId", nullable = false)
	public GeoZone getGeoZone() {
		return this.geoZone;
	}

	public void setGeoZone(GeoZone geoZone) {
		this.geoZone = geoZone;
	}

	@Column(name = "StopType", nullable = false)
	public short getStopType() {
		return this.stopType;
	}

	public void setStopType(short stopType) {
		this.stopType = stopType;
	}

	@Column(name = "Code", nullable = false)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "Name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ShortName", nullable = false)
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "AddressNo", nullable = false)
	public String getAddressNo() {
		return this.addressNo;
	}

	public void setAddressNo(String addressNo) {
		this.addressNo = addressNo;
	}

	@Column(name = "Street", nullable = false)
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "Ward", nullable = false)
	public String getWard() {
		return this.ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	@Column(name = "Latitude", nullable = false, precision = 12, scale = 9)
	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "Longitude", nullable = false, precision = 12, scale = 9)
	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "DisabilitySupport", nullable = false)
	public boolean isDisabilitySupport() {
		return this.disabilitySupport;
	}

	public void setDisabilitySupport(boolean disabilitySupport) {
		this.disabilitySupport = disabilitySupport;
	}

	@Column(name = "Status", nullable = false)
	public short getStatus() {
		return this.status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "busStop")
	public Set<BusStopDetail> getBusStopDetails() {
		return this.busStopDetails;
	}

	public void setBusStopDetails(Set<BusStopDetail> busStopDetails) {
		this.busStopDetails = busStopDetails;
	}

}
