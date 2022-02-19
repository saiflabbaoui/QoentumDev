package com.sfm.qoentum.model.qoentumf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.util.List;

@Entity
public class TechnologieFixe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String technologie;

    //@JsonIgnoreProperties("technologieFixe")
    @JsonIgnoreProperties(value={"technologieFixe"}, allowSetters = true)
    @OneToMany(mappedBy = "technologieFixe", cascade = {CascadeType.REMOVE})
    private List<FaiTechnologieFixePlageIp> technologiesFixes;

    //@JsonIgnoreProperties("technologiesFixes")
    //@ManyToMany(fetch = FetchType.LAZY, mappedBy = "technologiesFixes")
    //private List<FournisseurAcces> fais = new ArrayList<>();

    //@JsonIgnoreProperties("technologieFixe")
    //@OneToMany
    //@Transient
    //private List<String> plageIpFixes = new ArrayList<>();


    public TechnologieFixe() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTechnologie() {
        return technologie;
    }

    public void setTechnologie(String technologie) {
        this.technologie = technologie;
    }

    public List<FaiTechnologieFixePlageIp> getTechnologiesFixes() {
        return technologiesFixes;
    }

    public void setTechnologiesFixes(List<FaiTechnologieFixePlageIp> technologiesFixes) {
        this.technologiesFixes = technologiesFixes;
    }

//public List<FournisseurAcces> getFais() {
    //    return fais;
   // }

    //public void setFais(List<FournisseurAcces> fais) {
    //    this.fais = fais;
   // }
}
