package com.sfm.qoentum.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sfm.qoentum.enumer.EnumRole;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	private String username;
	@Column
	@JsonIgnore
	private String password;

	@Enumerated(EnumType.STRING)
	@Column
	private EnumRole role;

	@Column
	private String nom;
	@Column
	private String prenom;

	@Column
	private String email;
	@Column
	private String tel;

	@Column
	private boolean enable;

	@Column
	private boolean sfm;

	@ManyToOne
	@JoinColumn(name = "client")
	private Client client;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EnumRole getRole() {
		return role;
	}

	public void setRole(EnumRole role) {
		this.role = role;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public boolean isSfm() {
		return sfm;
	}

	public void setSfm(boolean sfm) {
		this.sfm = sfm;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", nom="
				+ nom + ", prenom=" + prenom + ", email=" + email + ", tel=" + tel + ", enable=" + enable + ", isSFM="
				+ sfm + "]";
	}

}
