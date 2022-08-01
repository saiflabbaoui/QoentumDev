package com.sfm.qoentum.model.qoentumf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Emplacement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	private String nom;
	
	@Column(unique = true)
	private String nom1;

	@Column
	private double latitude;
	@Column
	private double longitude;
	@Column
	private boolean enabled;
	@Column
	private boolean enabled1;

	@JsonIgnoreProperties("emplacement")
	@OneToMany(mappedBy = "emplacement")
	private List<Sonde> sondes = new ArrayList<>();

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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Sonde> getSondes() {
		return sondes;
	}

	public void setSondes(List<Sonde> sondes) {
		this.sondes = sondes;
	}

	@Override
	public String toString() {
		return "Emplacement [id=" + id + ", nom=" + nom + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		Emplacement obj = (Emplacement) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
