package com.sfm.qoentum.dto;

import com.sfm.qoentum.enumer.EnumRole;
import com.sfm.qoentum.model.admin.Client;
import com.sfm.qoentum.model.admin.User;

public class UserDto {

	private long id;

	private String username;
	private String password;
	private EnumRole role;

	private String nom;
	private String prenom;
	private String email;
	private String tel;
	private boolean enable;
	private boolean sfm;
	private Client client;

	public UserDto() {
	}

	public UserDto(User user) {
		this.id = user.getId();

		this.username = user.getUsername();
		this.password = user.getPassword();
		this.role = user.getRole();

		this.nom = user.getNom();
		this.prenom = user.getPrenom();
		this.email = user.getEmail();
		this.tel = user.getTel();
		this.enable = user.isEnable();
		this.sfm = user.isSfm();
		this.client = user.getClient();
	}

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

}
