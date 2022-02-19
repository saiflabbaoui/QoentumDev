package com.sfm.qoentum.dto;

public class DashboardStats {

	private long user;
	private long regulateur;
	private long operateur;
	private long fournisseurAcces;
	private long client;
	private long sonde;

	public DashboardStats() {
		super();
	}

	public DashboardStats(long user, long regulateur, long operateur, long fournisseurAcces, long client, long sonde) {
		super();
		this.user = user;
		this.regulateur = regulateur;
		this.operateur = operateur;
		this.fournisseurAcces = fournisseurAcces;
		this.client = client;
		this.sonde = sonde;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public long getRegulateur() {
		return regulateur;
	}

	public void setRegulateur(long regulateur) {
		this.regulateur = regulateur;
	}

	public long getOperateur() {
		return operateur;
	}

	public void setOperateur(long operateur) {
		this.operateur = operateur;
	}

	public long getFournisseurAcces() {
		return fournisseurAcces;
	}

	public void setFournisseurAcces(long fournisseurAcces) {
		this.fournisseurAcces = fournisseurAcces;
	}

	public long getClient() {
		return client;
	}

	public void setClient(long client) {
		this.client = client;
	}

	public long getSonde() {
		return sonde;
	}

	public void setSonde(long sonde) {
		this.sonde = sonde;
	}

	@Override
	public String toString() {
		return "DashboardStats [user=" + user + ", regulateur=" + regulateur + ", operateur=" + operateur
				+ ", fournisseurAcces=" + fournisseurAcces + ", client=" + client + ", sonde=" + sonde + "]";
	}

}
