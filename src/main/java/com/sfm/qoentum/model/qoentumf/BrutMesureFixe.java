package com.sfm.qoentum.model.qoentumf;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BrutMesureFixe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private Double mesure;
	@Column
	private Date date;
	@Column
	private String etat;
	@Column
	private String url; // Site pour le DNS et pour le HTTP

	@Column
	private long sonde;
	@Column
	private long indicateurFixe;
	@Column
	private long regulateur;
	@Column
	private long emplacement;
	@Column 
	private long fai;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getMesure() {
		return mesure;
	}

	public void setMesure(double mesure) {
		this.mesure = mesure;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public long getSonde() {
		return sonde;
	}

	public void setSonde(long sonde) {
		this.sonde = sonde;
	}

	public long getIndicateurFixe() {
		return indicateurFixe;
	}

	public void setIndicateurFixe(long indicateurFixe) {
		this.indicateurFixe = indicateurFixe;
	}

	public void setMesure(Double mesure) {
		this.mesure = mesure;
	}

	public long getRegulateur() {
		return regulateur;
	}

	public void setRegulateur(long regulateur) {
		this.regulateur = regulateur;
	}

	public long getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(long emplacement) {
		this.emplacement = emplacement;
	}

	public long getFai() {
		return fai;
	}

	public void setFai(long fai) {
		this.fai = fai;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "BrutMesureFixe [id=" + id + ", mesure=" + mesure + ", date=" + date + ", etat=" + etat + ", sonde="
				+ sonde + ", indicateurFixe=" + indicateurFixe + ", regulateur=" + regulateur + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		BrutMesureFixe obj = (BrutMesureFixe) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
