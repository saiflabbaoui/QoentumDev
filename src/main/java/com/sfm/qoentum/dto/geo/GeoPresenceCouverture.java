package com.sfm.qoentum.dto.geo;

public class GeoPresenceCouverture {

	public GeoPresenceCouverture() {
		super();
	}

	public GeoPresenceCouverture(double longitude, double latitude, Boolean presence) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.presence = presence;
	}

	private double longitude;
	private double latitude;
	private Boolean presence;

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
	
	public Boolean getPresence() {
		return presence;
	}

	public void setPresence(Boolean presence) {
		this.presence = presence;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		GeoPresenceCouverture obj = (GeoPresenceCouverture) arg0;
		if ((obj.longitude == this.longitude) 
				&& (obj.latitude==this.latitude) 
				&& (obj.presence.equals(this.presence)))
			return true;
		return false;
	}

}
