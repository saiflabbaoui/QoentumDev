package com.sfm.qoentum.model.qoentumf;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SondeConfs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "sonde")
    private Sonde sonde;

    @Column
    private Double mesure;
    @Column
    private Date date;
    @Column
    private String etat;
    @Column
    private String url; // Site pour le DNS et pour le HTTP
    @Column
    private long indicateurFixe;
    @Column
    private long regulateur;
    @Column
    private long emplacement;
    @Column
    private long fai;

    public SondeConfs() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sonde getSonde() {
        return sonde;
    }

    public void setSonde(Sonde sonde) {
        this.sonde = sonde;
    }

    public Double getMesure() {
        return mesure;
    }

    public void setMesure(Double mesure) {
        this.mesure = mesure;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getIndicateurFixe() {
        return indicateurFixe;
    }

    public void setIndicateurFixe(long indicateurFixe) {
        this.indicateurFixe = indicateurFixe;
    }

    public long getRegulateur() {
        return regulateur;
    }

    public void setRegulateur(long regulateur) {
        this.regulateur = regulateur;
    }

    public long getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(long emplacement) {
        this.emplacement = emplacement;
    }

    public long getFai() {
        return fai;
    }

    public void setFai(long fai) {
        this.fai = fai;
    }
}
