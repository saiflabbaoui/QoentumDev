package com.sfm.qoentum.config;

public class Constants {
    
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 30 * 24 * 60 * 60;
    public static final String SIGNING_KEY = "SFMqoentum2020";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_ISSUER = "QoEntum";
    public static final String HEADER_STRING = "Authorization";
    
    public static final String MCC_MALI = "610";
    
    // Couverture
    public static final long Force_Signal_Id = 1;
    
    // FTP
    public static final long Latence_Id = 3;
    public static final long Jitter_Id = 4;
    public static final long FTP_Download_Id = 5;
    public static final long FTP_Upload_Id = 6;
    public static final long MOS_TELECHARGEMENT = 15;
    
    // HTTP
    public static final long HTTP_Temps_Chargement = 7;
    public static final long HTTP_Debit = 8;
    public static final long HTTP_DNS_LOOKUP = 20;
    public static final long MOS_NAVIGATION = 17;
    
    // Video
    public static final long Video_Buffering_Time = 9;
    public static final long Video_Duree = 10;
    public static final long Video_Debit = 11;
    public static final long MOS_VIDEO = 16;
    
    // Voix
    public static final long Voix_Duree_Appel = 12;
    public static final long Voix_Setup_Time = 13;
    public static final long Voix_Mos = 14;
    
    // SMS
    public static final long MOS_SMS = 18;
    public static final long DELAIS_SMS = 19;
    
    
    public static final String dossierGeoJSON = "/var/www/html/geoJSON/";
//    public static final String urlAccessGeoJSON = "http://vps449053.ovh.net/geoJSON/";
    public static final String urlAccessGeoJSON = "https://api.qoentum.sfmtechnologies.com/geoJSON/";
    
}
