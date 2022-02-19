package com.sfm.qoentum.dto;

import java.util.Date;

public class DateStartEnd {

	private Date dateStart;
	private Date dateEnd;

	public DateStartEnd(Date dateStart, Date dateEnd) {
		super();
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Override
	public String toString() {
		return "DateStartEnd [dateStart=" + dateStart + ", dateEnd=" + dateEnd + "]";
	}

}
