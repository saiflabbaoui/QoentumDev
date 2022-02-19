package com.sfm.qoentum.dto;

public class AvgMinMaxPerDate {

	private long date;
	private double avg;
	private double min;
	private double max;
	private double median;
	private int records;

	public AvgMinMaxPerDate() {
		super();
	}

	public AvgMinMaxPerDate(Double avg, Double min, Double max, Long records) {
		super();
		this.avg = avg == null ? 0 : avg;
		this.min = min == null ? 0 : min;
		this.max = max == null ? 0 : max;
		this.records = records == null ? 0 : records.intValue();
	}

	public AvgMinMaxPerDate(Double avg, Integer min, Integer max, Long records) {
		super();

		this.avg = avg == null ? 0 : avg;
		this.min = min == null ? 0 : min.doubleValue();
		this.max = max == null ? 0 : max.doubleValue();
		this.records = records == null ? 0 : records.intValue();
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMedian() {
		return median;
	}

	public void setMedian(double median) {
		this.median = median;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "AvgMinMaxPerDate [date=" + date + ", avg=" + avg + ", min=" + min + ", max=" + max + ", median="
				+ median + ", records=" + records + "]";
	}

}
