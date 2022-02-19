package com.sfm.qoentum.model.qoentumm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MobileTechno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	private String technologie;

	@ManyToOne
	@JoinColumn(name = "generationTechno")
	private GenerationTechno generationTechno;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTechnologie() {
		return technologie;
	}

	public void setTechnologie(String technologie) {
		this.technologie = technologie;
	}

	public GenerationTechno getGenerationTechno() {
		return generationTechno;
	}

	public void setGenerationTechno(GenerationTechno generationTechno) {
		this.generationTechno = generationTechno;
	}

	@Override
	public String toString() {
		return "MobileTechno [id=" + id + ", technologie=" + technologie + ", generationTechno=" + generationTechno
				+ "]";
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		MobileTechno obj = (MobileTechno) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
