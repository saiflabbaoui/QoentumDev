package com.sfm.qoentum.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Features implements Serializable {

    private Metadata metadata;
    private List<Feature> features;
    private String type;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public static class Metadata {
        private long totalElement;
        private int nombrePage;
        private int count;
        private String title;
        private int numeroPage;

        public long getTotalElement() {
            return totalElement;
        }

        public void setTotalElement(long totalElement) {
            this.totalElement = totalElement;
        }

        public int getNombrePage() {
            return nombrePage;
        }

        public void setNombrePage(int nombrePage) {
            this.nombrePage = nombrePage;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getNumeroPage() {
            return numeroPage;
        }

        public void setNumeroPage(int numeroPage) {
            this.numeroPage = numeroPage;
        }
    }
    public static class Feature{
        private Geometry geometry;
        private long id;
        private String type = "Feature";
        private Properties properties;

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Properties getProperties() {
            return properties;
        }

        public void setProperties(Properties properties) {
            this.properties = properties;
        }
        public static class Geometry {
            private Double[] coordinates;
            private String type = "Point";

            public Double[] getCoordinates() {
                return coordinates;
            }

            public void setCoordinates(Double[] coordinates) {
                this.coordinates = coordinates;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
        public static class Properties {
            private Date date;
            private String seuil;
            private String color;
            private long operateur;
            private String mobile;

            @JsonInclude(JsonInclude.Include.NON_NULL)
            private double Signal;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private double Latence;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private double Jitter;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private double DL;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private double UL;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private double Score;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private double Temps;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private double DNSLookup;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private double Debit;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private double Duree;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private double Delais;

            public Date getDate() {
                return date;
            }

            public void setDate(Date date) {
                this.date = date;
            }

            public String getSeuil() {
                return seuil;
            }

            public void setSeuil(String seuil) {
                this.seuil = seuil;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public long getOperateur() {
                return operateur;
            }

            public void setOperateur(long operateur) {
                this.operateur = operateur;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public double getSignal() {
                return Signal;
            }

            public void setSignal(double signal) {
                this.Signal = signal;
            }

            public double getLatence() {
                return Latence;
            }

            public void setLatence(double latence) {
                this.Latence = latence;
            }

            public double getJitter() {
                return Jitter;
            }

            public void setJitter(double jitter) {
                this.Jitter = jitter;
            }

            public double getDl() {
                return DL;
            }

            public void setDl(double dl) {
                this.DL = dl;
            }

            public double getUl() {
                return UL;
            }

            public void setUl(double ul) {
                this.UL = ul;
            }

            public double getScore() {
                return Score;
            }

            public void setScore(double score) {
                this.Score = score;
            }

            public double getTemps() {
                return Temps;
            }

            public void setTemps(double temps) {
                this.Temps = temps;
            }

            public double getDnslookup() {
                return DNSLookup;
            }

            public void setDnslookup(double dnslookup) {
                this.DNSLookup = dnslookup;
            }

            public double getDebit() {
                return Debit;
            }

            public void setDebit(double debit) {
                this.Debit = debit;
            }

            public double getDuree() {
                return Duree;
            }

            public void setDuree(double duree) {
                this.Duree = duree;
            }

            public double getDelais() {
                return Delais;
            }

            public void setDelais(double delais) {
                this.Delais = delais;
            }
        }
    }
}






