package com.sfm.qoentum.dto;

import com.sfm.qoentum.model.qoentumf.TechnologieFixe;

import java.util.List;


public class TechnologiesParFai {
    long id;
    List<TechnologieFixe> technologies;

    public TechnologiesParFai() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<TechnologieFixe> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<TechnologieFixe> technologies) {
        this.technologies = technologies;
    }
}
