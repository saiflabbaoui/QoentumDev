package com.sfm.qoentum.model.qoentumm;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Imei {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	private String imei;

	@ManyToOne
	@JoinColumn(name = "mobilePhone")
	private MobilePhone mobilePhone;

	@JsonIgnoreProperties("imeis")
	@ManyToMany(mappedBy = "imeis")
	private List<Sim> sims;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public MobilePhone getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(MobilePhone mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public List<Sim> getSims() {
		return sims;
	}

	public void setSims(List<Sim> sims) {
		this.sims = sims;
	}

	@Override
	public String toString() {
		return "Imei [id=" + id + ", imei=" + imei + ", mobilePhone=" + mobilePhone + ", sims=" + sims + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		Imei obj = (Imei) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
