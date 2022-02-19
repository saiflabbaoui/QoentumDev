package com.sfm.qoentum.dto;

import com.sfm.qoentum.enumer.EnumResultSMS;

public class EventNombrePercent {

	private String event;
	private String description;
	private long count;
	private Double percent;

	public EventNombrePercent() {
		super();
	}

	public EventNombrePercent(String event, String description, long nombre) {
		super();
		this.event = event;
		this.description = description;
		this.count = nombre;
	}
	
	public EventNombrePercent(EnumResultSMS resultSMS, long nombre) {
		super();
		this.event = resultSMS.name();
		this.description = resultSMS.name();
		this.count = nombre;
	}

	public void calculPercent(Integer total) {
		this.percent = ((double) this.count / total) * 100;
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

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	@Override
	public String toString() {
		return "EventNombrePercent [event=" + event + ", description=" + description + ", count=" + count + ", percent="
				+ percent + "]";
	}

}
