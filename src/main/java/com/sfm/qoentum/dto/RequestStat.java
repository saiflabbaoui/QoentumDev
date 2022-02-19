package com.sfm.qoentum.dto;

import java.util.Date;
import java.util.List;

public class RequestStat {

	private long operateur_id;
	private long generation_id;
	private long indicateur_id;
	private Date start_date;
	private Date end_date;

	private int numPage;
	private int nombreElement;

	private List<Long> seuils_id;

	public RequestStat() {
		super();
	}

	public RequestStat(long operateur_id, long generation_id, long indicateur_id, Date start_date, Date end_date,
			int numPage, int nombreElement, List<Long> seuils_id) {
		super();
		this.operateur_id = operateur_id;
		this.generation_id = generation_id;
		this.indicateur_id = indicateur_id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.numPage = numPage;
		this.nombreElement = nombreElement;
		this.seuils_id = seuils_id;
	}

	public RequestStat(long operateur_id, long generation_id, long indicateur_id, Date start_date, Date end_date,
			List<Long> seuils_id, List<Long> bornes_id) {
		super();
		this.operateur_id = operateur_id;
		this.generation_id = generation_id;
		this.indicateur_id = indicateur_id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.seuils_id = seuils_id;
	}

	public long getOperateur_id() {
		return operateur_id;
	}

	public void setOperateur_id(long operateur_id) {
		this.operateur_id = operateur_id;
	}

	public long getGeneration_id() {
		return generation_id;
	}

	public void setGeneration_id(long generation_id) {
		this.generation_id = generation_id;
	}

	public long getIndicateur_id() {
		return indicateur_id;
	}

	public void setIndicateur_id(long indicateur_id) {
		this.indicateur_id = indicateur_id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public List<Long> getSeuils_id() {
		return seuils_id;
	}

	public void setSeuils_id(List<Long> seuils_id) {
		this.seuils_id = seuils_id;
	}

	public int getNumPage() {
		return numPage;
	}

	public void setNumPage(int numPage) {
		this.numPage = numPage;
	}

	public int getNombreElement() {
		return nombreElement;
	}

	public void setNombreElement(int nombreElement) {
		this.nombreElement = nombreElement;
	}

	@Override
	public String toString() {
		return "RequestStat [operateur_id=" + operateur_id + ", generation_id=" + generation_id + ", indicateur_id="
				+ indicateur_id + ", start_date=" + start_date + ", end_date=" + end_date + ", numPage=" + numPage
				+ ", nombreElement=" + nombreElement + ", seuils_id=" + seuils_id + "]";
	}

}
