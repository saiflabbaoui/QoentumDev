package com.sfm.qoentum.model.qoentumm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Sim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	private String imsi;

	@JsonIgnoreProperties("sims")
	@JoinTable(name = "ImeiSim", joinColumns = {
			@JoinColumn(name = "sim", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "imei", referencedColumnName = "id") })
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Imei> imeis = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public List<Imei> getImeis() {
		return imeis;
	}

	public void setImeis(List<Imei> imeis) {
		this.imeis = imeis;
	}

	@Override
	public String toString() {
		return "Sim [id=" + id + ", imsi=" + imsi + ", imeis=" + imeis + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		Sim obj = (Sim) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
