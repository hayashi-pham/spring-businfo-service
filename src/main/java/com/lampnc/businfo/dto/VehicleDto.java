package com.lampnc.businfo.dto;

public class VehicleDto {

	private int distance; // distance to station
	private float speed; // average speed (km/h)
	private int timeLeft; // seconds to station

	public VehicleDto() {
	}

	public VehicleDto(int distance, float speed, int timeLeft) {
		this.distance = distance;
		this.speed = speed;
		this.timeLeft = timeLeft;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

}
