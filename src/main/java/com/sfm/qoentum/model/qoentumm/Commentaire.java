package com.sfm.qoentum.model.qoentumm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Commentaire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private Date date;

	@Column
	private String commentaire;

	@Column
	private Integer note;

	@ManyToOne
	@JoinColumn(name = "itemCommentaire")
	private ItemCommentaire itemCommentaire;

	@ManyToOne
	@JoinColumn(name = "operateur")
	private Operateur operateur;
/*
	@ManyToOne
	@JoinColumn(name = "imei")
	private Imei imei;
*/
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Operateur getOperateur() {
		return operateur;
	}

	public void setOperateur(Operateur operateur) {
		this.operateur = operateur;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ItemCommentaire getItemCommentaire() {
		return itemCommentaire;
	}

	public void setItemCommentaire(ItemCommentaire itemCommentaire) {
		this.itemCommentaire = itemCommentaire;
	}

	@Override
	public String toString() {
		return "Commentaire{" +
				"id=" + id +
				", date=" + date +
				", commentaire='" + commentaire + '\'' +
				", note=" + note +
				", itemCommentaire=" + itemCommentaire +
				", operateur=" + operateur +
				'}';
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		Commentaire obj = (Commentaire) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
