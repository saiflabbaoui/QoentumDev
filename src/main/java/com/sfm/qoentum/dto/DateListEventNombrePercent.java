package com.sfm.qoentum.dto;

import java.util.Date;
import java.util.List;

public class DateListEventNombrePercent {

	private Date date;
	private List<EventNombrePercent> eventNombrePercents;

	public DateListEventNombrePercent() {
		super();
	}

	public DateListEventNombrePercent(Date date, List<EventNombrePercent> eventNombrePercents) {
		super();
		this.date = date;
		this.eventNombrePercents = eventNombrePercents;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<EventNombrePercent> getEventNombrePercents() {
		return eventNombrePercents;
	}

	public void setEventNombrePercents(List<EventNombrePercent> eventNombrePercents) {
		this.eventNombrePercents = eventNombrePercents;
	}

	@Override
	public String toString() {
		return "DateListEventNombrePercent [date=" + date + ", eventNombrePercents=" + eventNombrePercents + "]";
	}

}
