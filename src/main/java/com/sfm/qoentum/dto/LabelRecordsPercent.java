package com.sfm.qoentum.dto;

public class LabelRecordsPercent {

	private String label;
	private Double percent;
	private Integer records;
	private String color;

	public LabelRecordsPercent() {
		super();
	}

	public LabelRecordsPercent(String label, Double percent, Integer records, String color) {
		super();
		this.label = label;
		this.percent = percent;
		this.records = records;
		this.color = color;
	}
	public LabelRecordsPercent(Double percent, Integer records) {
		super();
		this.percent = percent;
		this.records = records;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "LabelRecordsPercent [label=" + label + ", percent=" + percent + ", records=" + records + ", color="
				+ color + "]";
	}

}
