package com.sfm.qoentum.model.qoentumf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class IndicateurFixe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	private String libelle;

	@ManyToOne
	@JoinColumn(name = "serviceFixe")
	private ServiceFixe serviceFixe;

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

	public ServiceFixe getServiceFixe() {
		return serviceFixe;
	}

	public void setServiceFixe(ServiceFixe serviceFixe) {
		this.serviceFixe = serviceFixe;
	}

	@Override
	public String toString() {
		return "IndicateurFixe [id=" + id + ", libelle=" + libelle + ", serviceFixe=" + serviceFixe + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		IndicateurFixe obj = (IndicateurFixe) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
