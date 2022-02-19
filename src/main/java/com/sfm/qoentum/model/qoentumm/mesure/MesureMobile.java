package com.sfm.qoentum.model.qoentumm.mesure;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.sfm.qoentum.enumer.EnumTypeMesure;
import com.sfm.qoentum.model.qoentumf.FournisseurAcces;
import com.sfm.qoentum.model.qoentumf.TechnologieFixe;
import com.sfm.qoentum.model.qoentumm.*;

@Entity
public class MesureMobile {

    public MesureMobile() {
        super();
    }

    public MesureMobile(String idProjet, Date date, Boolean roaming, Integer ci, Integer pci, Integer tac,
                        Integer earfcn, Integer bandwidth, Integer psc, Integer uarfcn, Integer cid, Integer lac, Integer bsic,
                        Integer arfcn, Integer rsrp, Integer rsrq, Integer rssnr, Integer cqi, Integer ta, Integer dbm,
                        double longitude, double latitude, double altitude, float speed, float accuracy, Integer asu,
                        EnumTypeMesure typeMesure) {
        super();
        this.idProjet = idProjet;
        this.date = date;
        this.roaming = roaming;
        this.ci = ci;
        this.pci = pci;
        this.tac = tac;
        this.earfcn = earfcn;
        this.bandwidth = bandwidth;
        this.psc = psc;
        this.uarfcn = uarfcn;
        this.cid = cid;
        this.lac = lac;
        this.bsic = bsic;
        this.arfcn = arfcn;
        Rsrp = rsrp;
        Rsrq = rsrq;
        Rssnr = rssnr;
        this.cqi = cqi;
        this.ta = ta;
        this.dbm = dbm;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.speed = speed;
        this.accuracy = accuracy;
        this.typeMesure = typeMesure;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String ip;

    @Column
    private String idProjet;

    @Column
    private Date date;
    @Column
    private Boolean roaming;

    /******************* Cell Info *************************/

    // LTE //
    @Column
    private Integer ci;
    @Column
    private Integer pci;
    @Column
    private Integer tac;
    @Column
    private Integer earfcn;
    @Column
    private Integer bandwidth;

    // WCDMA //
    @Column
    private Integer psc;
    @Column
    private Integer uarfcn;

    // GSM //
    @Column
    private Integer cid; // Also WCDMA
    @Column
    private Integer lac; // Also WCDMA

    @Column
    private Integer bsic;
    @Column
    private Integer arfcn;

    /******************* Signal Strength ******************/

    @Column
    private Integer asu;

    // LTE //
    @Column
    private Integer Rsrp;
    @Column
    private Integer Rsrq;
    @Column
    private Integer Rssnr;
    @Column
    private Integer cqi;

    // WCDMA //

    // GSM //
    @Column
    private Integer ta; // Also lte
    @Column
    private Integer dbm; // Also WCDMA and LTE

    @Column
    private Integer rssi;

    /************** Géolocalisation ***************/
    @Column
    private double longitude;
    @Column
    private double latitude;
    @Column
    private double altitude;
    @Column
    private float speed;
    @Column
    private float accuracy;

    @ManyToOne
    @JoinColumn(name = "operateur")
    private Operateur operateur;

        @ManyToOne
        @JoinColumn(name = "imei")
        private Imei imei;

        @ManyToOne
        @JoinColumn(name = "sim")
        private Sim sim;
/*  Ajouté en modification de imei et sim  */
    @ManyToOne
    @JoinColumn(name = "mobilePhone")
    private MobilePhone mobilePhone;

    @ManyToOne
    @JoinColumn(name = "versionOS")
    private VersionOS versionOS;

    @ManyToOne
    @JoinColumn(name = "mobileTechno")
    private MobileTechno mobileTechno;

    @ManyToOne
    @JoinColumn(name = "fournisseurAcces")
    private FournisseurAcces fournisseurAcces;

    @ManyToOne
    @JoinColumn(name = "technologie_wifi")
    private TechnologieFixe technologie_wifi;

    @Enumerated(EnumType.STRING)
    @Column
    private EnumTypeMesure typeMesure;

    @OneToOne(mappedBy = "mesureMobile")
    private MesureMobileFTP mesureMobileFTP;

    @OneToOne(mappedBy = "mesureMobile")
    private MesureMobileHTTP mesureMobileHTTP;

    @OneToOne(mappedBy = "mesureMobile")
    private MesureMobileSMS mesureMobileSMS;

    @OneToOne(mappedBy = "mesureMobile")
    private MesureMobileVideo mesureMobileVideo;

    @OneToOne(mappedBy = "mesureMobile")
    private MesureMobileVoix mesureMobileVoix;

    public FournisseurAcces getFournisseurAcces() {
        return fournisseurAcces;
    }

    public void setFournisseurAcces(FournisseurAcces fournisseurAcces) {
        this.fournisseurAcces = fournisseurAcces;
    }

    public TechnologieFixe getTechnologie_wifi() {
        return technologie_wifi;
    }

    public void setTechnologie_wifi(TechnologieFixe technologie_wifi) {
        this.technologie_wifi = technologie_wifi;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(String idProjet) {
        this.idProjet = idProjet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getRoaming() {
        return roaming;
    }

    public void setRoaming(Boolean roaming) {
        this.roaming = roaming;
    }

    public Integer getCi() {
        return ci;
    }

    public void setCi(Integer ci) {
        this.ci = ci;
    }

    public Integer getPci() {
        return pci;
    }

    public void setPci(Integer pci) {
        this.pci = pci;
    }

    public Integer getTac() {
        return tac;
    }

    public void setTac(Integer tac) {
        this.tac = tac;
    }

    public Integer getEarfcn() {
        return earfcn;
    }

    public void setEarfcn(Integer earfcn) {
        this.earfcn = earfcn;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    public Integer getPsc() {
        return psc;
    }

    public void setPsc(Integer psc) {
        this.psc = psc;
    }

    public Integer getUarfcn() {
        return uarfcn;
    }

    public void setUarfcn(Integer uarfcn) {
        this.uarfcn = uarfcn;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getLac() {
        return lac;
    }

    public void setLac(Integer lac) {
        this.lac = lac;
    }

    public Integer getBsic() {
        return bsic;
    }

    public void setBsic(Integer bsic) {
        this.bsic = bsic;
    }

    public Integer getArfcn() {
        return arfcn;
    }

    public void setArfcn(Integer arfcn) {
        this.arfcn = arfcn;
    }

    public Integer getRsrp() {
        return Rsrp;
    }

    public void setRsrp(Integer rsrp) {
        Rsrp = rsrp;
    }

    public Integer getRsrq() {
        return Rsrq;
    }

    public void setRsrq(Integer rsrq) {
        Rsrq = rsrq;
    }

    public Integer getRssnr() {
        return Rssnr;
    }

    public void setRssnr(Integer rssnr) {
        Rssnr = rssnr;
    }

    public Integer getCqi() {
        return cqi;
    }

    public void setCqi(Integer cqi) {
        this.cqi = cqi;
    }

    public Integer getTa() {
        return ta;
    }

    public void setTa(Integer ta) {
        this.ta = ta;
    }

    public Integer getDbm() {
        return dbm;
    }

    public void setDbm(Integer dbm) {
        this.dbm = dbm;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public Operateur getOperateur() {
        return operateur;
    }

    public void setOperateur(Operateur operateur) {
        this.operateur = operateur;
    }


    public VersionOS getVersionOS() {
        return versionOS;
    }

    public void setVersionOS(VersionOS versionOS) {
        this.versionOS = versionOS;
    }

    public MobileTechno getMobileTechno() {
        return mobileTechno;
    }

    public void setMobileTechno(MobileTechno mobileTechno) {
        this.mobileTechno = mobileTechno;
    }

    public EnumTypeMesure getTypeMesure() {
        return typeMesure;
    }

    public void setTypeMesure(EnumTypeMesure typeMesure) {
        this.typeMesure = typeMesure;
    }

    public MesureMobileFTP getMesureMobileFTP() {
        return mesureMobileFTP;
    }

    public void setMesureMobileFTP(MesureMobileFTP mesureMobileFTP) {
        this.mesureMobileFTP = mesureMobileFTP;
    }

    public MesureMobileHTTP getMesureMobileHTTP() {
        return mesureMobileHTTP;
    }

    public void setMesureMobileHTTP(MesureMobileHTTP mesureMobileHTTP) {
        this.mesureMobileHTTP = mesureMobileHTTP;
    }

    public MesureMobileSMS getMesureMobileSMS() {
        return mesureMobileSMS;
    }

    public void setMesureMobileSMS(MesureMobileSMS mesureMobileSMS) {
        this.mesureMobileSMS = mesureMobileSMS;
    }

    public MesureMobileVideo getMesureMobileVideo() {
        return mesureMobileVideo;
    }

    public void setMesureMobileVideo(MesureMobileVideo mesureMobileVideo) {
        this.mesureMobileVideo = mesureMobileVideo;
    }

    public MesureMobileVoix getMesureMobileVoix() {
        return mesureMobileVoix;
    }

    public void setMesureMobileVoix(MesureMobileVoix mesureMobileVoix) {
        this.mesureMobileVoix = mesureMobileVoix;
    }

    public Integer getAsu() {
        return asu;
    }

    public void setAsu(Integer asu) {
        this.asu = asu;
    }

    public MobilePhone getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(MobilePhone mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Imei getImei() {
        return imei;
    }

    public void setImei(Imei imei) {
        this.imei = imei;
    }

    public Sim getSim() {
        return sim;
    }

    public void setSim(Sim sim) {
        this.sim = sim;
    }

    public Integer getRssi() {
        return rssi;
    }

    public void setRssi(Integer rssi) {
        this.rssi = rssi;
    }

    @Override
    public String toString() {
        return "MesureMobile{" +
                "id=" + id +
                ", idProjet='" + idProjet + '\'' +
                ", date=" + date +
                ", roaming=" + roaming +
                ", ci=" + ci +
                ", pci=" + pci +
                ", tac=" + tac +
                ", earfcn=" + earfcn +
                ", bandwidth=" + bandwidth +
                ", psc=" + psc +
                ", uarfcn=" + uarfcn +
                ", cid=" + cid +
                ", lac=" + lac +
                ", bsic=" + bsic +
                ", arfcn=" + arfcn +
                ", asu=" + asu +
                ", Rsrp=" + Rsrp +
                ", Rsrq=" + Rsrq +
                ", Rssnr=" + Rssnr +
                ", cqi=" + cqi +
                ", ta=" + ta +
                ", dbm=" + dbm +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", altitude=" + altitude +
                ", speed=" + speed +
                ", accuracy=" + accuracy +
                ", operateur=" + operateur +
                ", imei=" + imei +
                ", sim=" + sim +
                ", mobilePhone=" + mobilePhone +
                ", versionOS=" + versionOS +
                ", mobileTechno=" + mobileTechno +
                ", typeMesure=" + typeMesure +
                ", mesureMobileFTP=" + mesureMobileFTP +
                ", mesureMobileHTTP=" + mesureMobileHTTP +
                ", mesureMobileSMS=" + mesureMobileSMS +
                ", mesureMobileVideo=" + mesureMobileVideo +
                ", mesureMobileVoix=" + mesureMobileVoix +
                '}';
    }

    @Override
    public boolean equals(Object arg0) {
        if (arg0 == null)
            return false;
        if (getClass() != arg0.getClass())
            return false;
        MesureMobile obj = (MesureMobile) arg0;
        if (obj.id == this.id)
            return true;
        return false;
    }

}
