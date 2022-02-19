package com.sfm.qoentum.dto;

public class PageUtil {

	private int numeroPage;
	private int nombreElementParPage;
	private int nombrePage;
	private long nombreTotalElement;

	public int getNumeroPage() {
		return numeroPage;
	}

	public void setNumeroPage(int numeroPage) {
		this.numeroPage = numeroPage;
	}

	public int getNombreElementParPage() {
		return nombreElementParPage;
	}

	public void setNombreElementParPage(int nombreElementParPage) {
		this.nombreElementParPage = nombreElementParPage;
	}

	public int getNombrePage() {
		return nombrePage;
	}

	public void setNombrePage(int nombrePage) {
		this.nombrePage = nombrePage;
	}

	public long getNombreTotalElement() {
		return nombreTotalElement;
	}

	public void setNombreTotalElement(long nombreTotalElement) {
		this.nombreTotalElement = nombreTotalElement;
	}

	@Override
	public String toString() {
		return "PageUtil [numeroPage=" + numeroPage + ", nombreElementParPage=" + nombreElementParPage + ", nombrePage="
				+ nombrePage + ", nombreTotalElement=" + nombreTotalElement + "]";
	}

}
