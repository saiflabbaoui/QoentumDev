package com.sfm.qoentum.dto;

import java.util.Date;

public class CommentaireDTO {

	private Date date;
	private String commentaire;
	private Integer note;
	private long itemCommentaire;

	private long operateur;

	private String imei;
	private String imeiList;

	private String modele;
	private String systemeExploitation;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public long getItemCommentaire() {
		return itemCommentaire;
	}

	public void setItemCommentaire(long itemCommentaire) {
		this.itemCommentaire = itemCommentaire;
	}

	public long getOperateur() {
		return operateur;
	}

	public void setOperateur(long operateur) {
		this.operateur = operateur;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getImeiList() {
		return imeiList;
	}

	public void setImeiList(String imeiList) {
		this.imeiList = imeiList;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getSystemeExploitation() {
		return systemeExploitation;
	}

	public void setSystemeExploitation(String systemeExploitation) {
		this.systemeExploitation = systemeExploitation;
	}

	@Override
	public String toString() {
		return "CommentaireDTO [date=" + date + ", commentaire=" + commentaire + ", note=" + note + ", itemCommentaire="
				+ itemCommentaire + ", operateur=" + operateur + ", imei=" + imei + ", imeiList=" + imeiList
				+ ", modele=" + modele + ", systemeExploitation=" + systemeExploitation + "]";
	}

}
