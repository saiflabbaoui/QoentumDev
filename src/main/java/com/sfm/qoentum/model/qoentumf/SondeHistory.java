package com.sfm.qoentum.model.qoentumf;


import com.sfm.qoentum.model.qoentumf.Sonde;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SondeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sonde")
    private Sonde sonde;

    @Column
    private double etat;

    @Column
    private Date dateTime;

    public SondeHistory() {}

    public SondeHistory(long id, Sonde sonde, double etat, Date dateTime) {
        this.id = id;
        this.sonde = sonde;
        this.etat = etat;
        this.dateTime = dateTime;
    }

    public SondeHistory(Sonde sonde, double etat, Date dateTime) {
        this.sonde = sonde;
        this.etat = etat;
        this.dateTime = dateTime;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sonde getSonde() {
        return this.sonde;
    }

    public void setSonde(Sonde sonde) {
        this.sonde = sonde;
    }

    public double getEtat() {
        return this.etat;
    }

    public void setEtat(double etat) {
        this.etat = etat;
    }

    public Date getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}