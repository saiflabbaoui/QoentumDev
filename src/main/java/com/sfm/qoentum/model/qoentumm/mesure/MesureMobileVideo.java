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
public class MesureMobileVideo {

	public MesureMobileVideo() {
		super();
	}

	public MesureMobileVideo(Integer bufferingTime, Integer duree, Double debit, String resolution, Integer mos) {
		super();
		this.bufferingTime = bufferingTime;
		this.duree = duree;
		this.debit = debit;
		this.resolution = resolution;
		this.mos = mos;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private Integer bufferingTime;
	@Column
	private Integer duree;
	@Column
	private Double debit;
	@Column
	private String resolution;

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

	public Integer getBufferingTime() {
		return bufferingTime;
	}

	public void setBufferingTime(Integer bufferingTime) {
		this.bufferingTime = bufferingTime;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
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

	@Override
	public String toString() {
		return "MesureMobileVideo [id=" + id + ", bufferingTime=" + bufferingTime + ", duree=" + duree + ", debit="
				+ debit + ", resolution=" + resolution + ", mos=" + mos + ", mesureMobile=" + mesureMobile + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		MesureMobileVideo obj = (MesureMobileVideo) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
