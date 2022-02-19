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

import com.sfm.qoentum.model.admin.User;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "libelle", "indicateurMobile", "generationTechno", "user" }))
public class SeuilMobile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String libelle;

	@Column
	private Double borneSup;
	@Column
	private Boolean incluseSup;
	@Column
	private Double borneInf;
	@Column
	private Boolean incluseInf;
	@Column
	private Boolean defaut;
	@Column
	private String color;

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	@ManyToOne
	@JoinColumn(name = "indicateurMobile")
	private IndicateurMobile indicateurMobile;

	@ManyToOne
	@JoinColumn(name = "generationTechno")
	private GenerationTechno generationTechno;
	@Column
	private boolean enabled;

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

	public Double getBorneSup() {
		return borneSup;
	}

	public void setBorneSup(Double borneSup) {
		this.borneSup = borneSup;
	}

	public Boolean getIncluseSup() {
		return incluseSup;
	}

	public void setIncluseSup(Boolean incluseSup) {
		this.incluseSup = incluseSup;
	}

	public Double getBorneInf() {
		return borneInf;
	}

	public void setBorneInf(Double borneInf) {
		this.borneInf = borneInf;
	}

	public Boolean getIncluseInf() {
		return incluseInf;
	}

	public void setIncluseInf(Boolean incluseInf) {
		this.incluseInf = incluseInf;
	}

	public Boolean getDefaut() {
		return defaut;
	}

	public void setDefaut(Boolean defaut) {
		this.defaut = defaut;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public IndicateurMobile getIndicateurMobile() {
		return indicateurMobile;
	}

	public void setIndicateurMobile(IndicateurMobile indicateurMobile) {
		this.indicateurMobile = indicateurMobile;
	}

	public GenerationTechno getGenerationTechno() {
		return generationTechno;
	}

	public void setGenerationTechno(GenerationTechno generationTechno) {
		this.generationTechno = generationTechno;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "SeuilMobile [id=" + id + ", libelle=" + libelle + ", borneSup=" + borneSup + ", incluseSup="
				+ incluseSup + ", borneInf=" + borneInf + ", incluseInf=" + incluseInf + ", defaut=" + defaut
				+ ", color=" + color + ", user=" + user + ", indicateurMobile=" + indicateurMobile
				+ ", generationTechno=" + generationTechno + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		SeuilMobile obj = (SeuilMobile) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
