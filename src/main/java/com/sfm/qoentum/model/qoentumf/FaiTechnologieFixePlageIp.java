package com.sfm.qoentum.model.qoentumf;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class FaiTechnologieFixePlageIp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnoreProperties("faiTechnologieFixePlageIp")
    @OneToMany(mappedBy = "faiTechnologieFixePlageIp", cascade = {CascadeType.REMOVE})
    private List<PlageIpFixe> plageIp;

    @ManyToOne
    private FournisseurAcces fournisseurAcces;

    //@JsonIgnoreProperties(value={"technologiesFixes"}, allowSetters = true)
    @ManyToOne()
    private TechnologieFixe technologieFixe;

    public FaiTechnologieFixePlageIp() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<PlageIpFixe> getPlageIp() {
        return plageIp;
    }

    public void setPlageIp(List<PlageIpFixe> plageIp) {
        this.plageIp = plageIp;
    }

    public FournisseurAcces getFournisseurAcces() {
        return fournisseurAcces;
    }

    public void setFournisseurAcces(FournisseurAcces fournisseurAcces) {
        this.fournisseurAcces = fournisseurAcces;
    }

    public TechnologieFixe getTechnologieFixe() {
        return technologieFixe;
    }

    public void setTechnologieFixe(TechnologieFixe technologieFixe) {
        this.technologieFixe = technologieFixe;
    }
}
