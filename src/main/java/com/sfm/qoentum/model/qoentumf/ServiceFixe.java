package com.sfm.qoentum.model.qoentumf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServiceFixe {

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
		return "ServiceFixe [id=" + id + ", service=" + service + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		ServiceFixe obj = (ServiceFixe) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
