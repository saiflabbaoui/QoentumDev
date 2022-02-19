package com.sfm.qoentum.model.qoentumm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServiceMobile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	private String service;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "ServiceMobile [id=" + id + ", service=" + service + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		ServiceMobile obj = (ServiceMobile) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
