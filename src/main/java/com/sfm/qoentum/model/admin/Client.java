package com.sfm.qoentum.model.admin;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sfm.qoentum.enumer.EnumTypeLicence;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	private String nom;

	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String logo;
	
	@Column
	private String adresse;

	@Column
	private String idProjet;

	@Column
	private Date dateDebutLicence;

	@Column
	private Date dateFinLicence;

	@Enumerated(EnumType.STRING)
	@Column
	private EnumTypeLicence typeLicence;

	@JsonIgnoreProperties("clients")
	@JoinTable(name = "ClientRegulateur", joinColumns = {
			@JoinColumn(name = "client", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "regulateur", referencedColumnName = "id") })
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Regulateur> regulateurs;

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

	public Date getDateDebutLicence() {
		return dateDebutLicence;
	}

	public void setDateDebutLicence(Date dateDebutLicence) {
		this.dateDebutLicence = dateDebutLicence;
	}

	public Date getDateFinLicence() {
		return dateFinLicence;
	}

	public void setDateFinLicence(Date dateFinLicence) {
		this.dateFinLicence = dateFinLicence;
	}

	public EnumTypeLicence getTypeLicence() {
		return typeLicence;
	}

	public void setTypeLicence(EnumTypeLicence typeLicence) {
		this.typeLicence = typeLicence;
	}

	public List<Regulateur> getRegulateurs() {
		return regulateurs;
	}

	public void setRegulateurs(List<Regulateur> regulateurs) {
		this.regulateurs = regulateurs;
	}

	public String getIdProjet() {
		return idProjet;
	}

	public void setIdProjet(String idProjet) {
		this.idProjet = idProjet;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", idProjet=" + idProjet + ", dateDebutLicence=" + dateDebutLicence
				+ ", dateFinLicence=" + dateFinLicence + ", typeLicence=" + typeLicence + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		Client obj = (Client) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
