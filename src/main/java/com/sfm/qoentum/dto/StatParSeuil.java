package com.sfm.qoentum.dto;

import java.util.List;

public class StatParSeuil {

	private Integer totalRecords;
	private List<LabelRecordsPercent> labelRecordsPercents;

	public StatParSeuil() {
		super();
	}

	public StatParSeuil(Integer totalRecords, List<LabelRecordsPercent> labelRecordsPercents) {
		super();
		this.totalRecords = totalRecords;
		this.labelRecordsPercents = labelRecordsPercents;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<LabelRecordsPercent> getLabelRecordsPercents() {
		return labelRecordsPercents;
	}

	public void setLabelRecordsPercents(List<LabelRecordsPercent> labelRecordsPercents) {
		this.labelRecordsPercents = labelRecordsPercents;
	}

	@Override
	public String toString() {
		return "StatPerPlage [totalRecords=" + totalRecords + ", labelRecordsPercents=" + labelRecordsPercents + "]";
	}

}
