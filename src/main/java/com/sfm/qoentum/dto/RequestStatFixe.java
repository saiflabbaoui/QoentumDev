package com.sfm.qoentum.dto;

import java.util.Date;
import java.util.List;

public class RequestStatFixe {

	private long sonde_id;
	private long fournisseur_acces_id;
	private long indicateur_fixe_id;
	private long emplacement_id;
	private Date start_date;
	private Date end_date;

	private List<Long> seuils_id;

	public RequestStatFixe() {
		super();
	}

	public RequestStatFixe(long sonde_id, long fournisseur_acces_id, long indicateur_fixe_id, long emplacement_id,
			Date start_date, Date end_date, List<Long> seuils_id) {
		super();
		this.sonde_id = sonde_id;
		this.fournisseur_acces_id = fournisseur_acces_id;
		this.indicateur_fixe_id = indicateur_fixe_id;
		this.emplacement_id = emplacement_id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.seuils_id = seuils_id;
	}

	public long getSonde_id() {
		return sonde_id;
	}

	public void setSonde_id(long sonde_id) {
		this.sonde_id = sonde_id;
	}

	public long getFournisseur_acces_id() {
		return fournisseur_acces_id;
	}

	public void setFournisseur_acces_id(long fournisseur_acces_id) {
		this.fournisseur_acces_id = fournisseur_acces_id;
	}

	public long getIndicateur_fixe_id() {
		return indicateur_fixe_id;
	}

	public void setIndicateur_fixe_id(long indicateur_fixe_id) {
		this.indicateur_fixe_id = indicateur_fixe_id;
	}

	public long getEmplacement_id() {
		return emplacement_id;
	}

	public void setEmplacement_id(long emplacement_id) {
		this.emplacement_id = emplacement_id;
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

	@Override
	public String toString() {
		return "RequestStatFixe [sonde_id=" + sonde_id + ", fournisseur_acces_id=" + fournisseur_acces_id
				+ ", indicateur_fixe_id=" + indicateur_fixe_id + ", emplacement_id=" + emplacement_id + ", start_date="
				+ start_date + ", end_date=" + end_date + ", seuils_id=" + seuils_id + "]";
	}

}
