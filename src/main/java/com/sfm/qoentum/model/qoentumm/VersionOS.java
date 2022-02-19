package com.sfm.qoentum.model.qoentumm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class VersionOS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String version;

	@ManyToOne
	@JoinColumn(name = "systemeExploitation")
	private SystemeExploitation systemeExploitation;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public SystemeExploitation getSystemeExploitation() {
		return systemeExploitation;
	}

	public void setSystemeExploitation(SystemeExploitation systemeExploitation) {
		this.systemeExploitation = systemeExploitation;
	}

	@Override
	public String toString() {
		return "VersionOS [id=" + id + ", version=" + version + ", systemeExploitation=" + systemeExploitation + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		VersionOS obj = (VersionOS) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
