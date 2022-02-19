package com.sfm.qoentum.dto;

import java.util.Date;
import java.util.List;

public class RequestStatWifi {

    private long fournisseur_acces_id;
    private long generation_id;
    private long indicateur_id;
    private Date start_date;
    private Date end_date;

    private int numPage;
    private int nombreElement;

    public RequestStatWifi() {
    }

    public RequestStatWifi(long fournisseur_acces_id, long generation_id, long indicateur_id, Date start_date, Date end_date, int numPage, int nombreElement) {
        this.fournisseur_acces_id = fournisseur_acces_id;
        this.generation_id = generation_id;
        this.indicateur_id = indicateur_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.numPage = numPage;
        this.nombreElement = nombreElement;
    }

    public long getFournisseur_acces_id() {
        return fournisseur_acces_id;
    }

    public void setFournisseur_acces_id(long fournisseur_acces_id) {
        this.fournisseur_acces_id = fournisseur_acces_id;
    }

    public long getGeneration_id() {
        return generation_id;
    }

    public void setGeneration_id(long generation_id) {
        this.generation_id = generation_id;
    }

    public long getIndicateur_id() {
        return indicateur_id;
    }

    public void setIndicateur_id(long indicateur_id) {
        this.indicateur_id = indicateur_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getNumPage() {
        return numPage;
    }

    public void setNumPage(int numPage) {
        this.numPage = numPage;
    }

    public int getNombreElement() {
        return nombreElement;
    }

    public void setNombreElement(int nombreElement) {
        this.nombreElement = nombreElement;
    }
}
