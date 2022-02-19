package com.sfm.qoentum.model.qoentumf;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sfm.qoentum.model.admin.Regulateur;

import java.util.List;

@Entity
public class FournisseurAcces {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String nom;

	@ManyToOne
	@JoinColumn(name = "regulateur")
	private Regulateur regulateur;

    @JsonIgnore
	@OneToMany(mappedBy = "fournisseurAcces", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<FaiTechnologieFixePlageIp> technologiesFixes;

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

	public Regulateur getRegulateur() {
		return regulateur;
	}

	public void setRegulateur(Regulateur regulateur) {
		this.regulateur = regulateur;
	}

	public List<FaiTechnologieFixePlageIp> getTechnologiesFixes() {
		return technologiesFixes;
	}

	public void setTechnologiesFixes(List<FaiTechnologieFixePlageIp> technologiesFixes) {
		this.technologiesFixes = technologiesFixes;
	}

	@Override
	public String toString() {
		return "FournisseurAcces{" +
				"id=" + id +
				", nom='" + nom + '\'' +
				", regulateur=" + regulateur +
				", technologiesFixes=" + technologiesFixes +
				'}';
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		FournisseurAcces obj = (FournisseurAcces) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
