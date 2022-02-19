package com.sfm.qoentum.dto.geo;

public class GeoJSONMobile {

	public GeoJSONMobile() {
		super();
	}

	public GeoJSONMobile(double longitude, double latitude, Integer signal, String color) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		Signal = signal;
		Color = color;
	}

	private double longitude;
	private double latitude;
	private Integer Signal;
	private String Color;

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

	public Integer getSignal() {
		return Signal;
	}

	public void setSignal(Integer signal) {
		Signal = signal;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	@Override
	public String toString() {
		return "GeoJSONMobile [longitude=" + longitude + ", latitude=" + latitude + ", Signal=" + Signal + ", Color="
				+ Color + "]";
	}
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		GeoJSONMobile obj = (GeoJSONMobile) arg0;
		if ((obj.longitude == this.longitude) 
				&& (obj.latitude==this.latitude) 
				&& (obj.Signal.equals(this.Signal))
				&& (obj.Color.equals(this.Color)))
			return true;
		return false;
	}

}
