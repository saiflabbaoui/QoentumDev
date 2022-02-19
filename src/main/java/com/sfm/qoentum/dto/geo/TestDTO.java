package com.sfm.qoentum.dto.geo;

public class TestDTO {

	public TestDTO() {
		super();
	}

	public TestDTO(Integer val1, Integer val2) {
		super();
		this.val1 = val1;
		this.val2 = val2;
	}

	private long val1;
	private long val2;

	public long getVal1() {
		return val1;
	}

	public void setVal1(long val1) {
		this.val1 = val1;
	}

	public long getVal2() {
		return val2;
	}

	public void setVal2(long val2) {
		this.val2 = val2;
	}

}
