package com.sfm.qoentum.model.qoentumf;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sfm.qoentum.model.admin.Regulateur;
import com.sfm.qoentum.model.qoentumm.MobileTechno;

@Entity
public class MesureFixe {

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
	private String url;

	@ManyToOne
	@JoinColumn(name = "indicateurFixe")
	private IndicateurFixe indicateurFixe;

	@ManyToOne
	@JoinColumn(name = "fournisseurAcces")
	private FournisseurAcces fournisseurAcces;

	@ManyToOne
	@JoinColumn(name = "sonde")
	private Sonde sonde;

	@ManyToOne
	@JoinColumn(name = "regulateur")
	private Regulateur regulateur;

	@ManyToOne
	@JoinColumn(name = "emplacement")
	private Emplacement emplacement;

	@ManyToOne
	@JoinColumn(name = "technologieFixe")
	private TechnologieFixe technologieFixe;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getMesure() {
		return mesure;
	}

	public void setMesure(Double mesure) {
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

	public IndicateurFixe getIndicateurFixe() {
		return indicateurFixe;
	}

	public void setIndicateurFixe(IndicateurFixe indicateurFixe) {
		this.indicateurFixe = indicateurFixe;
	}

	public Sonde getSonde() {
		return sonde;
	}

	public void setSonde(Sonde sonde) {
		this.sonde = sonde;
	}

	public Emplacement getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(Emplacement emplacement) {
		this.emplacement = emplacement;
	}

	public Regulateur getRegulateur() {
		return regulateur;
	}

	public void setRegulateur(Regulateur regulateur) {
		this.regulateur = regulateur;
	}

	public FournisseurAcces getFournisseurAcces() {
		return fournisseurAcces;
	}

	public void setFournisseurAcces(FournisseurAcces fournisseurAcces) {
		this.fournisseurAcces = fournisseurAcces;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "MesureFixe [id=" + id + ", mesure=" + mesure + ", date=" + date + ", etat=" + etat + ", indicateurFixe="
				+ indicateurFixe + ", fournisseurAcces=" + fournisseurAcces + ", sonde=" + sonde + ", regulateur="
				+ regulateur + ", emplacement=" + emplacement + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		MesureFixe obj = (MesureFixe) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
