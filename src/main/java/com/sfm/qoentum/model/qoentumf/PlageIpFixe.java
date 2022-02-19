package com.sfm.qoentum.model.qoentumf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class PlageIpFixe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column()
    private String plageIP;

    @ManyToOne
    private FaiTechnologieFixePlageIp faiTechnologieFixePlageIp;

    //@ManyToOne
    //@JoinColumn(name = "fournisseurAcces")
    //private FournisseurAcces fournisseurAcces;

    //@ManyToOne
    //@JoinColumn(name = "technologieFixe")
    //private TechnologieFixe technologieFixe;


    public PlageIpFixe() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlageIP() {
        return plageIP;
    }

    public void setPlageIP(String plageIP) {
        this.plageIP = plageIP;
    }

    public FaiTechnologieFixePlageIp getFaiTechnologieFixePlageIp() {
        return faiTechnologieFixePlageIp;
    }

    public void setFaiTechnologieFixePlageIp(FaiTechnologieFixePlageIp faiTechnologieFixePlageIp) {
        this.faiTechnologieFixePlageIp = faiTechnologieFixePlageIp;
    }
}
