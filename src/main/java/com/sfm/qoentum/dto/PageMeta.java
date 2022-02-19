package com.sfm.qoentum.dto;

public class PageMeta {

	private int numeroPage;
	private int nombrePage;
	private int count;
	private long totalElement;
	private String title;

	public int getNumeroPage() {
		return numeroPage;
	}

	public void setNumeroPage(int numeroPage) {
		this.numeroPage = numeroPage;
	}

	public int getNombrePage() {
		return nombrePage;
	}

	public void setNombrePage(int nombrePage) {
		this.nombrePage = nombrePage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getTotalElement() {
		return totalElement;
	}

	public void setTotalElement(long totalElement) {
		this.totalElement = totalElement;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "PageMeta [numeroPage=" + numeroPage + ", nombrePage=" + nombrePage + ", count=" + count
				+ ", totalElement=" + totalElement + ", title=" + title + "]";
	}

}
