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
public class MesureMobileFTP {

	public MesureMobileFTP() {
		super();
	}

	public MesureMobileFTP(Double ping, Double jitter, Double download, Double upload, Integer mos, Integer stateDL,
			Integer stateUL) {
		super();
		this.ping = ping;
		this.jitter = jitter;
		this.download = download;
		this.upload = upload;
		this.mos = mos;
		this.stateDL = stateDL;
		this.stateUL = stateUL;
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private Double ping;
	@Column
	private Double jitter;
	@Column
	private Double download;
	@Column
	private Double upload;

	@Column
	private Integer mos;
	
	@Column
	private Integer stateDL; // 0 : Conexion non réussie (au cas où le ping est passé)
							// 1 : Drop
							// 2 : Succès
	@Column
	private Integer stateUL;

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

	public Double getPing() {
		return ping;
	}

	public void setPing(Double ping) {
		this.ping = ping;
	}

	public Double getJitter() {
		return jitter;
	}

	public void setJitter(Double jitter) {
		this.jitter = jitter;
	}

	public Double getDownload() {
		return download;
	}

	public void setDownload(Double download) {
		this.download = download;
	}

	public Double getUpload() {
		return upload;
	}

	public void setUpload(Double upload) {
		this.upload = upload;
	}

	public Integer getMos() {
		return mos;
	}

	public void setMos(Integer mos) {
		this.mos = mos;
	}

	public Integer getStateDL() {
		return stateDL;
	}

	public void setStateDL(Integer stateDL) {
		this.stateDL = stateDL;
	}

	public Integer getStateUL() {
		return stateUL;
	}

	public void setStateUL(Integer stateUL) {
		this.stateUL = stateUL;
	}

	public MesureMobile getMesureMobile() {
		return mesureMobile;
	}

	public void setMesureMobile(MesureMobile mesureMobile) {
		this.mesureMobile = mesureMobile;
	}

	@Override
	public String toString() {
		return "MesureMobileFTP [id=" + id + ", ping=" + ping + ", jitter=" + jitter + ", download=" + download
				+ ", upload=" + upload + ", mos=" + mos + ", mesureMobile=" + mesureMobile + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		MesureMobileFTP obj = (MesureMobileFTP) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
