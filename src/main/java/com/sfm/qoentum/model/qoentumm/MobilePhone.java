package com.sfm.qoentum.model.qoentumm;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class MobilePhone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    @Column
    private String uuid;

    @Column
    private String modele;

    @ManyToOne
    @JoinColumn(name = "systemeExploitation")
    private SystemeExploitation systemeExploitation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public SystemeExploitation getSystemeExploitation() {
        return systemeExploitation;
    }

    public void setSystemeExploitation(SystemeExploitation systemeExploitation) {
        this.systemeExploitation = systemeExploitation;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "MobilePhone{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", modele='" + modele + '\'' +
                ", systemeExploitation=" + systemeExploitation +
                '}';
    }

    @Override
    public boolean equals(Object arg0) {
        if (arg0 == null)
            return false;
        if (getClass() != arg0.getClass())
            return false;
        MobilePhone obj = (MobilePhone) arg0;
        if (obj.id == this.id)
            return true;
        return false;
    }

}
