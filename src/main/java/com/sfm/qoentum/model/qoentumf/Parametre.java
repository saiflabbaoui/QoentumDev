package com.sfm.qoentum.model.qoentumf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Parametre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idParametre;

	private String paramName;

	@Column
	private String paramValue;
	
	public Parametre() {
	}

	public Parametre(String paramName, String paramValue) {
		super();
		this.paramName = paramName;
		this.paramValue = paramValue;
	}

	public long getIdParametre() {
		return idParametre;
	}

	public void setIdParametre(long idParametre) {
		this.idParametre = idParametre;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

}
