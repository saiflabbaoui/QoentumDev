package com.sfm.qoentum.model.qoentumm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class IndicateurMobile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	private String libelle;

	@ManyToOne
	@JoinColumn(name = "serviceMobile")
	private ServiceMobile serviceMobile;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public ServiceMobile getServiceMobile() {
		return serviceMobile;
	}

	public void setServiceMobile(ServiceMobile serviceMobile) {
		this.serviceMobile = serviceMobile;
	}

	@Override
	public String toString() {
		return "IndicateurMobile [id=" + id + ", libelle=" + libelle + ", serviceMobile=" + serviceMobile + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		IndicateurMobile obj = (IndicateurMobile) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
