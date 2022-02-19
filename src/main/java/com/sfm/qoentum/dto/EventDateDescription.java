package com.sfm.qoentum.dto;

import java.util.Date;

import com.sfm.qoentum.enumer.EnumResultSMS;

public class EventDateDescription {

	private Date date;
	
	private String event;
	private String description;

	private double longitude;
	private double latitude;

	public EventDateDescription() {
		super();
	}

	public EventDateDescription(Date date, String event, String description, double longitude, double latitude) {
		super();
		this.date = date;
		this.event = event;
		this.description = description;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public EventDateDescription(Date date, EnumResultSMS resultSMS, double longitude, double latitude) {
		super();
		this.date = date;
		this.event = resultSMS.name();
		this.description = resultSMS.name();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "EventDateDescription [date=" + date + ", event=" + event + ", description=" + description
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", getDate()=" + getDate() + ", getEvent()="
				+ getEvent() + ", getDescription()=" + getDescription() + ", getLongitude()=" + getLongitude()
				+ ", getLatitude()=" + getLatitude() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
