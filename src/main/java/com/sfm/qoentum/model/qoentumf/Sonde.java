package com.sfm.qoentum.model.qoentumf;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sfm.qoentum.enumer.EnumVersionSonde;
import com.sfm.qoentum.model.admin.Client;
import com.sfm.qoentum.model.admin.Regulateur;

@Entity
public class Sonde {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "emplacement")
	private Emplacement emplacement;

	@Column
	private Date derniereConnexion;
	
	@Column
	private String ipAddress;
	
	@Column
	private String hostname;
	

	@ManyToOne
	@JoinColumn(name = "fournisseurAcces")
	private FournisseurAcces fournisseurAcces;

	@ManyToOne
	@JoinColumn(name = "regulateur")
	private Regulateur regulateur;

	@ManyToOne
	@JoinColumn(name = "client")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "groupeSonde")
	private GroupeSonde groupeSonde;

	@Column
	private String MACAddressEth;
	@Column
	private String MACAddressWlan;

	@Enumerated(EnumType.STRING)
	@Column
	private EnumVersionSonde versionSonde;

	@Column
	private boolean enabled;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Emplacement getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(Emplacement emplacement) {
		this.emplacement = emplacement;
	}

	public Date getDerniereConnexion() {
		return derniereConnexion;
	}

	public void setDerniereConnexion(Date derniereConnexion) {
		this.derniereConnexion = derniereConnexion;
	}

	public FournisseurAcces getFournisseurAcces() {
		return fournisseurAcces;
	}

	public void setFournisseurAcces(FournisseurAcces fournisseurAcces) {
		this.fournisseurAcces = fournisseurAcces;
	}

	public Regulateur getRegulateur() {
		return regulateur;
	}

	public void setRegulateur(Regulateur regulateur) {
		this.regulateur = regulateur;
	}

	public EnumVersionSonde getVersionSonde() {
		return versionSonde;
	}

	public void setVersionSonde(EnumVersionSonde versionSonde) {
		this.versionSonde = versionSonde;
	}

	public GroupeSonde getGroupeSonde() {
		return groupeSonde;
	}

	public void setGroupeSonde(GroupeSonde groupeSonde) {
		this.groupeSonde = groupeSonde;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getMACAddressEth() {
		return MACAddressEth;
	}

	public void setMACAddressEth(String MACAddressEth) {
		this.MACAddressEth = MACAddressEth;
	}

	public String getMACAddressWlan() {
		return MACAddressWlan;
	}

	public void setMACAddressWlan(String MACAddressWlan) {
		this.MACAddressWlan = MACAddressWlan;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		Sonde obj = (Sonde) arg0;
		if (obj.id == this.id)
			return true;
		return false;
	}

}
