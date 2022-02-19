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
import com.sfm.qoentum.enumer.EnumNatureAppel;

@Entity
public class MesureMobileVoix {

	public MesureMobileVoix() {
		super();
	}

	public MesureMobileVoix(String code, String description, String reason, String label, Integer dureeAppel,
			Double setupTime, Integer mos, EnumNatureAppel natureAppel) {
		super();
		this.code = code;
		this.description = description;
		this.reason = reason;
		this.label = label;
		this.dureeAppel = dureeAppel;
		this.setupTime = setupTime;
		this.mos = mos;
		this.natureAppel = natureAppel;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String code;
	@Column
	private String description;
	@Column
	private String reason;
	@Column
	private String label;
	@Column
	private Integer dureeAppel;
	@Column
	private Double setupTime;
	@Column
	private Integer mos;

	@Enumerated(EnumType.STRING)
	@Column
	private EnumNatureAppel natureAppel;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getDureeAppel() {
		return dureeAppel;
	}

	public void setDureeAppel(Integer dureeAppel) {
		this.dureeAppel = dureeAppel;
	}

	public Double getSetupTime() {
		return setupTime;
	}

	public void setSetupTime(Double setupTime) {
		this.setupTime = setupTime;
	}

	public Integer getMos() {
		return mos;
	}

	public void setMos(Integer mos) {
		this.mos = mos;
	}

	public EnumNatureAppel getNatureAppel() {
		return natureAppel;
	}

	public void setNatureAppel(EnumNatureAppel natureAppel) {
		this.natureAppel = natureAppel;
	}

	public MesureMobile getMesureMobile() {
		return mesureMobile;
	}

	public void setMesureMobile(MesureMobile mesureMobile) {
		this.mesureMobile = mesureMobile;
	}

	@Override
	public String toString() {
		return "MesureMobileVoix [id=" + id + ", code=" + code + ", description=" + description + ", reason=" + reason
				+ ", label=" + label + ", dureeAppel=" + dureeAppel + ", setupTime=" + setupTime + ", mos=" + mos
				+ ", natureAppel=" + natureAppel + ", mesureMobile=" + mesureMobile + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		MesureMobileVoix obj = (MesureMobileVoix) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
