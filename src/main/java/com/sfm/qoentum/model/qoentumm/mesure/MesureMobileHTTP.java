package com.sfm.qoentum.model.qoentumm.mesure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MesureMobileHTTP {

	public MesureMobileHTTP() {
		super();
	}

	public MesureMobileHTTP(Integer tempsChargement, Double debit, Integer dnsLookup, String url, Integer mos) {
		super();
		this.tempsChargement = tempsChargement;
		this.debit = debit;
		this.dnsLookup = dnsLookup;
		this.url = url;
		this.mos = mos;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private Integer tempsChargement;
	@Column
	private Double debit;

	@Column
	private Integer mos;

	/******/
	@Column
	private Integer dnsLookup;

	@Column
	private String url;

	/******/

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

	public Integer getTempsChargement() {
		return tempsChargement;
	}

	public void setTempsChargement(Integer tempsChargement) {
		this.tempsChargement = tempsChargement;
	}

	public Double getDebit() {
		return debit;
	}

	public void setDebit(Double debit) {
		this.debit = debit;
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

	public Integer getDnsLookup() {
		return dnsLookup;
	}

	public void setDnsLookup(Integer dnsLookup) {
		this.dnsLookup = dnsLookup;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "MesureMobileHTTP [id=" + id + ", tempsChargement=" + tempsChargement + ", debit=" + debit + ", mos="
				+ mos + ", dnsLookup=" + dnsLookup + ", url=" + url + ", mesureMobile=" + mesureMobile + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		MesureMobileHTTP obj = (MesureMobileHTTP) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
