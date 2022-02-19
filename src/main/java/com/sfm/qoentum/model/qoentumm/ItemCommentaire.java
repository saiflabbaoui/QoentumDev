package com.sfm.qoentum.model.qoentumm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sfm.qoentum.enumer.EnumServiceCommentaire;

@Entity
public class ItemCommentaire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated(EnumType.STRING)
	@Column
	private EnumServiceCommentaire service;

	private String item;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EnumServiceCommentaire getService() {
		return service;
	}

	public void setService(EnumServiceCommentaire service) {
		this.service = service;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "ItemCommentaire [id=" + id + ", service=" + service + ", item=" + item + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		ItemCommentaire obj = (ItemCommentaire) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
