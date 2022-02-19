package com.sfm.qoentum.model.qoentumm;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sfm.qoentum.enumer.EnumNatureAppel;
import com.sfm.qoentum.enumer.EnumResultSMS;
import com.sfm.qoentum.enumer.EnumTypeMesure;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class BrutMesureMobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    @Column
    private String uuid;

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

    /***************** Opérateur *************************/
    @Column
    private String mcc;
    @Column
    private String mnc;

    /***************** OS *************************/
    @Column
    private String systemeExploitation;
    @Column
    private String versionSysteme;

    /***************** SIM *************************/
    @Column
    private String imsi;

    /***************** Phone *************************/
    @Column
    private String modele;

    @Column
    private String imei;
    @Column
    private String imeiList;

    /******************* Technologie ******************/
    @Column
    private long technologie;

    /******************* Type Indicateur ******************/
    @Enumerated(EnumType.STRING)
    @Column
    private EnumTypeMesure typeMesure;

    /******************* Voix **********************/
    @Column
    private String disconnectCause;
    @Column
    private Integer dureeAppel;
    @Column
    private Double setupTime;
    @Column
    private Integer mos;
    @Enumerated(EnumType.STRING)
    @Column
    private EnumNatureAppel natureAppel;

    /******************* SMS **********************/
    @Enumerated(EnumType.STRING)
    @Column
    private EnumResultSMS result;

    @Column
    private Double delaisSMS;

    /******************* FTP **********************/
    @Column
    private Double ping;
    @Column
    private Double jitter;
    @Column
    private Double download;
    @Column
    private Double upload;
    @Column
    private Integer stateDL;
    @Column
    private Integer stateUL;

    /******************* HTTP **********************/
    @Column
    private Integer tempsChargement;
    @Column
    private Double debitHTTP;
    @Column
    private Integer dnsLookup;
    @Column
    private String url;

    /******************* Video **********************/
    @Column
    private Integer bufferingTime;
    @Column
    private Integer duree;
    @Column
    private Double debitVideo;
    @Column
    private String resolution;

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

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getSystemeExploitation() {
        return systemeExploitation;
    }

    public void setSystemeExploitation(String systemeExploitation) {
        this.systemeExploitation = systemeExploitation;
    }

    public String getVersionSysteme() {
        return versionSysteme;
    }

    public void setVersionSysteme(String versionSysteme) {
        this.versionSysteme = versionSysteme;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImeiList() {
        return imeiList;
    }

    public void setImeiList(String imeiList) {
        this.imeiList = imeiList;
    }

    public long getTechnologie() {
        return technologie;
    }

    public void setTechnologie(long technologie) {
        this.technologie = technologie;
    }

    public EnumTypeMesure getTypeMesure() {
        return typeMesure;
    }

    public void setTypeMesure(EnumTypeMesure typeMesure) {
        this.typeMesure = typeMesure;
    }

    public EnumResultSMS getResult() {
        return result;
    }

    public void setResult(EnumResultSMS result) {
        this.result = result;
    }

    public Double getDelaisSMS() {
        return delaisSMS;
    }

    public void setDelaisSMS(Double delaisSMS) {
        this.delaisSMS = delaisSMS;
    }

    public Double getPing() {
        return ping;
    }

    public void setPing(Double ping) {
        this.ping = ping;
    }

    public Double getJitter() {
        return jitter;
    }

    public void setJitter(Double jitter) {
        this.jitter = jitter;
    }

    public Double getDownload() {
        return download;
    }

    public void setDownload(Double download) {
        this.download = download;
    }

    public Double getUpload() {
        return upload;
    }

    public void setUpload(Double upload) {
        this.upload = upload;
    }

    public Integer getStateDL() {
        return stateDL;
    }

    public void setStateDL(Integer stateDL) {
        this.stateDL = stateDL;
    }

    public Integer getStateUL() {
        return stateUL;
    }

    public void setStateUL(Integer stateUL) {
        this.stateUL = stateUL;
    }

    public Integer getTempsChargement() {
        return tempsChargement;
    }

    public void setTempsChargement(Integer tempsChargement) {
        this.tempsChargement = tempsChargement;
    }

    public Double getDebitHTTP() {
        return debitHTTP;
    }

    public void setDebitHTTP(Double debitHTTP) {
        this.debitHTTP = debitHTTP;
    }

    public Integer getBufferingTime() {
        return bufferingTime;
    }

    public void setBufferingTime(Integer bufferingTime) {
        this.bufferingTime = bufferingTime;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Double getDebitVideo() {
        return debitVideo;
    }

    public void setDebitVideo(Double debitVideo) {
        this.debitVideo = debitVideo;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Integer getDureeAppel() {
        return dureeAppel;
    }

    public void setDureeAppel(Integer dureeAppel) {
        this.dureeAppel = dureeAppel;
    }

    public Double getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(Double setupTime) {
        this.setupTime = setupTime;
    }

    public Integer getMos() {
        return mos;
    }

    public void setMos(Integer mos) {
        this.mos = mos;
    }

    public String getDisconnectCause() {
        return disconnectCause;
    }

    public void setDisconnectCause(String disconnectCause) {
        this.disconnectCause = disconnectCause;
    }

    public EnumNatureAppel getNatureAppel() {
        return natureAppel;
    }

    public void setNatureAppel(EnumNatureAppel natureAppel) {
        this.natureAppel = natureAppel;
    }

    public Integer getAsu() {
        return asu;
    }

    public void setAsu(Integer asu) {
        this.asu = asu;
    }

    public Integer getDnsLookup() {
        return dnsLookup;
    }

    public void setDnsLookup(Integer dnsLookup) {
        this.dnsLookup = dnsLookup;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getRssi() {
        return rssi;
    }

    public void setRssi(Integer rssi) {
        this.rssi = rssi;
    }

    @Override
    public String toString() {
        return "BrutMesureMobile{" +
                "id=" + id +
                ", uuid=" + uuid +
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
                ", mcc='" + mcc + '\'' +
                ", mnc='" + mnc + '\'' +
                ", systemeExploitation='" + systemeExploitation + '\'' +
                ", versionSysteme='" + versionSysteme + '\'' +
                ", imsi='" + imsi + '\'' +
                ", modele='" + modele + '\'' +
                ", imei='" + imei + '\'' +
                ", imeiList='" + imeiList + '\'' +
                ", technologie=" + technologie +
                ", typeMesure=" + typeMesure +
                ", disconnectCause='" + disconnectCause + '\'' +
                ", dureeAppel=" + dureeAppel +
                ", setupTime=" + setupTime +
                ", mos=" + mos +
                ", natureAppel=" + natureAppel +
                ", result=" + result +
                ", delaisSMS=" + delaisSMS +
                ", ping=" + ping +
                ", jitter=" + jitter +
                ", download=" + download +
                ", upload=" + upload +
                ", stateDL=" + stateDL +
                ", stateUL=" + stateUL +
                ", tempsChargement=" + tempsChargement +
                ", debitHTTP=" + debitHTTP +
                ", dnsLookup=" + dnsLookup +
                ", url='" + url + '\'' +
                ", bufferingTime=" + bufferingTime +
                ", duree=" + duree +
                ", debitVideo=" + debitVideo +
                ", resolution='" + resolution + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object arg0) {
        if (arg0 == null)
            return false;
        if (getClass() != arg0.getClass())
            return false;
        BrutMesureMobile obj = (BrutMesureMobile) arg0;
        if (obj.id == this.id)
            return true;
        return false;
    }
}
