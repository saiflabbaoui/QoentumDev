package com.sfm.qoentum.model.qoentumm.mesure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sfm.qoentum.enumer.EnumResultSMS;

@Entity
public class MesureMobileSMS {

	public MesureMobileSMS() {
		super();
	}

	public MesureMobileSMS(EnumResultSMS result, Double delaisSMS, Integer mos) {
		super();
		this.result = result;
		this.delaisSMS = delaisSMS;
		this.mos = mos;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated(EnumType.STRING)
	@Column
	private EnumResultSMS result;

	@Column
	private Double delaisSMS; // delais d'envoi

	@Column
	private Integer mos;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "mesureMobile", referencedColumnName = "id")
	private MesureMobile mesureMobile;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EnumResultSMS getResult() {
		return result;
	}

	public void setResult(EnumResultSMS result) {
		this.result = result;
	}

	public Double getDelaisSMS() {
		return delaisSMS;
	}

	public void setDelaisSMS(Double delaisSMS) {
		this.delaisSMS = delaisSMS;
	}

	public Integer getMos() {
		return mos;
	}

	public void setMos(Integer mos) {
		this.mos = mos;
	}

	public MesureMobile getMesureMobile() {
		return mesureMobile;
	}

	public void setMesureMobile(MesureMobile mesureMobile) {
		this.mesureMobile = mesureMobile;
	}

	@Override
	public String toString() {
		return "MesureMobileSMS [id=" + id + ", result=" + result + ", delaisSMS=" + delaisSMS + ", mos=" + mos
				+ ", mesureMobile=" + mesureMobile + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		MesureMobileSMS obj = (MesureMobileSMS) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
