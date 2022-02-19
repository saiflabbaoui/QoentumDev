package com.sfm.qoentum.model.qoentumm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.sfm.qoentum.model.admin.Regulateur;

@Entity
@Table(uniqueConstraints= @UniqueConstraint(columnNames = {"mcc", "mnc"}))
public class Operateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique=true)
	private String nom;

	@Column
	private String mcc;
	@Column
	private String mnc;

	@ManyToOne
	@JoinColumn(name = "regulateur")
	private Regulateur regulateur;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMnc() {
		return mnc;
	}

	public void setMnc(String mnc) {
		this.mnc = mnc;
	}

	public Regulateur getRegulateur() {
		return regulateur;
	}

	public void setRegulateur(Regulateur regulateur) {
		this.regulateur = regulateur;
	}

	@Override
	public String toString() {
		return "Operateur [id=" + id + ", nom=" + nom + ", mcc=" + mcc + ", mnc=" + mnc + ", regulateur=" + regulateur
				+ "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		Operateur obj = (Operateur) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
