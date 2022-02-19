package com.sfm.qoentum;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import com.sfm.qoentum.model.qoentumf.PlageIpFixe;
import com.sfm.qoentum.service.MailService;
import com.sfm.qoentum.service.qoentumf.*;
import com.sfm.qoentum.service.qoentumm.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.CacheControl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.sfm.qoentum.dto.DisconnectCause;
import com.sfm.qoentum.dto.UserDto;
import com.sfm.qoentum.enumer.EnumRole;
import com.sfm.qoentum.enumer.EnumTypeMesure;
import com.sfm.qoentum.model.qoentumf.BrutMesureFixe;
import com.sfm.qoentum.model.qoentumf.MesureFixe;
import com.sfm.qoentum.model.qoentumf.Sonde;
import com.sfm.qoentum.model.qoentumm.BrutMesureMobile;
import com.sfm.qoentum.model.qoentumm.MobilePhone;
import com.sfm.qoentum.model.qoentumm.MobileTechno;
import com.sfm.qoentum.model.qoentumm.Operateur;
import com.sfm.qoentum.model.qoentumm.SystemeExploitation;
import com.sfm.qoentum.model.qoentumm.VersionOS;
import com.sfm.qoentum.model.qoentumm.mesure.MesureMobile;
import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileFTP;
import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileHTTP;
import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileSMS;
import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileVideo;
import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileVoix;
import com.sfm.qoentum.service.admin.RegulateurService;
import com.sfm.qoentum.service.admin.UserService;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.security.web.util.matcher.IpAddressMatcher;



@SpringBootApplication
@EnableScheduling
public class QoEntumApplication {

    @Autowired
    private UserService userService;

    @Autowired
    private BrutMesureMobileService brutMesureMobileService;

    @Autowired
    private BrutMesureFixeService brutMesureFixeService;

    @Autowired
    private MesureMobileService mesureMobileService;

    @Autowired
    private MesureMobileVoixService mesureMobileVoixService;

    @Autowired
    private MesureMobileSMSService mesureMobileSMSService;

    @Autowired
    private MesureMobileHTTPService mesureMobileHTTPService;

    @Autowired
    private MesureMobileFTPService mesureMobileFTPService;

    @Autowired
    private MesureMobileVideoService mesureMobileVideoService;

    @Autowired
    private OperateurService operateurService;

    @Autowired
    private VersionOSService versionOSService;

    @Autowired
    private SystemeExploitationService systemeExploitationService;

    @Autowired
    private PlageIpFixeService plageIpFixeService;

    //@Autowired
    //private SimService simService;

    //@Autowired
    //private ImeiService imeiService;

    @Autowired
    private MobilePhoneService mobilePhoneService;

    @Autowired
    private MobileTechnoService mobileTechnoService;

    @Autowired
    private SondeService sondeService;

    @Autowired
    private RegulateurService regulateurService;

    @Autowired
    private IndicateurFixeService indicateurFixeService;

    @Autowired
    private MesureFixeService mesureFixeService;

    @Autowired
    EmplacementService emplacementService;

    @Autowired
    FournisseurAccesService fournisseurAccesService;

    @Autowired
    private MailService mailService;

    @PostConstruct
    public void init() {
        if (userService.countByRole(EnumRole.ROLE_ADMIN) == 0) {

            UserDto admin = new UserDto();

            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setRole(EnumRole.ROLE_ADMIN);

            admin.setNom("AdminSFM");
            admin.setPrenom("AdminSFM");
            admin.setEmail("admin@admin.com");
            admin.setTel("11111111");
            admin.setEnable(true);
            admin.setSfm(true);

            userService.save(admin);

        }
    }

    @PostConstruct
    @Scheduled(fixedDelay = 5 * 60 * 1000) // 5 min
    public void calculMesureBrutMobile() {

        try {
            System.out.println("-----------> debut calcul brutMesuresMobiles : ");
            List<BrutMesureMobile> brutMesuresMobiles = brutMesureMobileService.findAll(2000);

            if (!brutMesuresMobiles.isEmpty() && brutMesuresMobiles != null) {
                System.out.println("-----------> not empty, brutMesuresMobiles : " + brutMesuresMobiles.size());
                for (BrutMesureMobile brutMesureMobile : brutMesuresMobiles) {

                    try {


                       //if (brutMesureMobile.getMcc() == null) continue;
//					if(!brutMesureMobile.getMcc().equals(Constants.MCC_MALI)) {
////						brutMesureMobileService.delete(brutMesureMobile.getId());
////						System.out.println("-----------> Pas du Mali");
//						continue;
//					}
                        // Supprimez les mesures Brutes nulles
                        if(brutMesureMobile.getAltitude() == 0 || brutMesureMobile.getLongitude() == 0 || brutMesureMobile.getMcc()==null || brutMesureMobile.getMnc()==null){
                            brutMesureMobileService.delete(brutMesureMobile.getId());
                        }
                        if(brutMesureMobile.getLatitude() != 0 && brutMesureMobile.getLongitude() != 0 ){
                        System.out.println("-----------> 1 : " + brutMesureMobile);
                        MesureMobile mesureMobile = new MesureMobile(brutMesureMobile.getIdProjet(),
                                brutMesureMobile.getDate(), brutMesureMobile.getRoaming(),

                                testIfUnreported(brutMesureMobile.getCi()), testIfUnreported(brutMesureMobile.getPci()), testIfUnreported(brutMesureMobile.getTac()),
                                testIfUnreported(brutMesureMobile.getEarfcn()), testIfUnreported(brutMesureMobile.getBandwidth()),

                                testIfUnreported(brutMesureMobile.getPsc()), testIfUnreported(brutMesureMobile.getUarfcn()), testIfUnreported(brutMesureMobile.getCid()),
                                testIfUnreported(brutMesureMobile.getLac()), testIfUnreported(brutMesureMobile.getBsic()), testIfUnreported(brutMesureMobile.getArfcn()),

                                testIfUnreported(brutMesureMobile.getRsrp()), testIfUnreported(brutMesureMobile.getRsrq()), testIfUnreported(brutMesureMobile.getRssnr()),
                                testIfUnreported(brutMesureMobile.getCqi()), testIfUnreported(brutMesureMobile.getTa()), testIfUnreported(brutMesureMobile.getDbm()),

                                brutMesureMobile.getLongitude(), brutMesureMobile.getLatitude(),
                                brutMesureMobile.getAltitude(), brutMesureMobile.getSpeed(), brutMesureMobile.getAccuracy(),
                                brutMesureMobile.getAsu(),
                                brutMesureMobile.getTypeMesure());

                        System.out.println("-----------> 2");

                        /*********** Affectation de l'opérateur ********/
                        Operateur operateur = operateurService.findByMccAndMnc(brutMesureMobile.getMcc(),
                                brutMesureMobile.getMnc());

                        if (operateur == null) {
                            operateur = new Operateur();
                            operateur.setMcc(brutMesureMobile.getMcc());
                            operateur.setMnc(brutMesureMobile.getMnc());

                            operateur = operateurService.save(operateur);
                            mesureMobile.setOperateur(operateur);

                        } else {

                            mesureMobile.setOperateur(operateur);
                        }
                        System.out.println("-----------> 3");
                        /*********** OS + Version ********/

                        SystemeExploitation systemeExploitation = systemeExploitationService
                                .findByNom(brutMesureMobile.getSystemeExploitation());

                        VersionOS versionOS = versionOSService.findByVersion(brutMesureMobile.getVersionSysteme());

                        if (versionOS == null) {

                            versionOS = new VersionOS();
                            versionOS.setVersion(brutMesureMobile.getVersionSysteme());

                            versionOS.setSystemeExploitation(systemeExploitation);

                            versionOS = versionOSService.save(versionOS);
                            mesureMobile.setVersionOS(versionOS);

                        } else {

                            mesureMobile.setVersionOS(versionOS);
                        }
                        System.out.println("-----------> 4");

                        /************** Mobile Phone ************/

                        MobilePhone mobilePhone = mobilePhoneService.getMobilePhoneByUuid(brutMesureMobile.getUuid());

                        if (mobilePhone == null) {
                            mobilePhone = new MobilePhone();
                            mobilePhone.setModele(brutMesureMobile.getModele());
                            mobilePhone.setSystemeExploitation(systemeExploitation);
                            mobilePhone.setUuid(brutMesureMobile.getUuid());
                            mobilePhoneService.save(mobilePhone);
                            //Affecter le mobile phone a la mesure mobile
                            mesureMobile.setMobilePhone(mobilePhone);
                            System.out.println("Saved New phone and affected to mesure" + mobilePhone.getUuid()+mobilePhone.getModele());


                        } else {
                            System.out.println("Phone non null" + mobilePhone.getUuid());
                            if(mobilePhone.getModele()==null){
                                mobilePhone.setModele(brutMesureMobile.getModele());
                                mobilePhoneService.save(mobilePhone);
                            }
                            if(mobilePhone.getSystemeExploitation()==null){
                                mobilePhone.setSystemeExploitation(systemeExploitation);
                                mobilePhoneService.save(mobilePhone);

                            }

                            mesureMobile.setMobilePhone(mobilePhone);
                            System.out.println("Existed phone affected to mesure" + mobilePhone.getUuid()+mobilePhone.getModele()+mobilePhone.getId());
                        }


                        /************** SIM + IMEI ************/
                        // Enregistrez tous les Imeis d'abord si ce n'est pas le cas

                        /*  MobilePhone mobilePhone = null;

                        List<String> imeiList = toImeiList(brutMesureMobile.getImeiList());

                        List<Imei> imeisToUpdate = new ArrayList<>();
                        if (!imeiList.isEmpty())
                            for (String imeiString : imeiList) {
                                Imei imei = imeiService.findByImei(imeiString);
                                if (imei == null) {
                                    imei = new Imei();
                                    imei.setImei(imeiString);
                                    imeisToUpdate.add(imeiService.save(imei));
                                } else {
                                    // Récupérer le mobilePhone au cas où il aurait été une fois enregistré
                                    mobilePhone = imei.getMobilePhone();
                                }
                            }

                        if (mobilePhone == null) {
                            mobilePhone = new MobilePhone();
                            mobilePhone.setModele(brutMesureMobile.getModele());
                            mobilePhone.setSystemeExploitation(systemeExploitation);
                            mobilePhone = mobilePhoneService.save(mobilePhone);
                        }

                        if (!imeisToUpdate.isEmpty()) {
                            for (Imei imei : imeisToUpdate) {
                                imei.setMobilePhone(mobilePhone);
                                imeiService.save(imei);
                            }
                        }

                        Imei imei = imeiService.findByImei(brutMesureMobile.getImei());

                        if (imei == null) {

                            imei = new Imei();
                            imei.setImei(brutMesureMobile.getImei());
                            imei.setMobilePhone(mobilePhone);

                            imei = imeiService.save(imei);
                            mesureMobile.setImei(imei);

                        } else {
                            mesureMobile.setImei(imei);
                        }

                        Sim sim = simService.findByImsi(brutMesureMobile.getImsi());

                        if (sim == null) {

                            sim = new Sim();
                            sim.setImsi(brutMesureMobile.getImsi());

                            sim.getImeis().add(imei);

                            sim = simService.save(sim);
                            mesureMobile.setSim(sim);

                        } else {

                            if (!sim.getImeis().contains(imei)) {
                                sim.getImeis().add(imei);
                                sim = simService.save(sim);
                            }

                            mesureMobile.setSim(sim);
                        } */
                        System.out.println("-----------> 5");

                        /************** Mobile Techno + Génération ************/

                        MobileTechno mobileTechno = mobileTechnoService.findById(brutMesureMobile.getTechnologie());
                        mesureMobile.setMobileTechno(mobileTechno);
                        if(mobileTechno.getId() == 18 && brutMesureMobile.getIp() != null){
                            List<PlageIpFixe> plageIpFixes = plageIpFixeService.getAllPlageIpFixe();
                            PlageIpFixe pl = plageIpFixes.stream().
                                    filter(x -> matches(brutMesureMobile.getIp(), x.getPlageIP())).
                                    findAny().orElse(null);

                            System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+ pl);
                            if(pl != null) {
                                mesureMobile.setIp(brutMesureMobile.getIp());   //mesureMobile.setIp(pl.getPlageIP());
                                mesureMobile.setFournisseurAcces(pl.getFaiTechnologieFixePlageIp().getFournisseurAcces());
                                mesureMobile.setTechnologie_wifi(pl.getFaiTechnologieFixePlageIp().getTechnologieFixe());
                            } else {
                                mesureMobile.setIp(brutMesureMobile.getIp());
                            }
                            mesureMobile.setRssi(brutMesureMobile.getRssi());
                        }

                        // Enregistrer la mesureMobile
                        mesureMobile = mesureMobileService.save(mesureMobile);

                        EnumTypeMesure typeMesure = brutMesureMobile.getTypeMesure();

                        System.out.println("-----------> 6");
                        /************** Couverture ************/

                        if (typeMesure == EnumTypeMesure.Couverture) {
                            brutMesureMobileService.delete(brutMesureMobile.getId());
                            continue;
                        }

                        System.out.println("-----------> 7");
                        /************** Voix ************/
                        if (typeMesure == EnumTypeMesure.Voix) {

                            DisconnectCause disconnectCause = new DisconnectCause(brutMesureMobile.getDisconnectCause());

                            MesureMobileVoix mesureMobileVoix = new MesureMobileVoix(
                                    disconnectCause.getCode(),
                                    disconnectCause.getDescription(),
                                    disconnectCause.getReason(),
                                    disconnectCause.getLabel(),
                                    brutMesureMobile.getDureeAppel(),
                                    brutMesureMobile.getSetupTime(),
                                    brutMesureMobile.getMos(),
                                    brutMesureMobile.getNatureAppel());

                            mesureMobileVoix.setMesureMobile(mesureMobile);

                            mesureMobileVoix = mesureMobileVoixService.save(mesureMobileVoix);

                            // Supprimez la mesure Brute
                            brutMesureMobileService.delete(brutMesureMobile.getId());
                            continue;

                        }
                        System.out.println("-----------> 8");
                        /************** SMS ************/
                        if (typeMesure == EnumTypeMesure.SMS) {
                            MesureMobileSMS mesureMobileSMS = new MesureMobileSMS(
                                    brutMesureMobile.getResult(),
                                    brutMesureMobile.getDelaisSMS(),
                                    brutMesureMobile.getMos());

                            mesureMobileSMS.setMesureMobile(mesureMobile);

                            mesureMobileSMS = mesureMobileSMSService.save(mesureMobileSMS);

                            // Supprimez la mesure Brute
                            brutMesureMobileService.delete(brutMesureMobile.getId());
                            continue;

                        }
                        System.out.println("-----------> 9");
                        /************** FTP ************/
                        if (typeMesure == EnumTypeMesure.FTP) {
                            MesureMobileFTP mesureMobileFTP = new MesureMobileFTP(brutMesureMobile.getPing(),
                                    brutMesureMobile.getJitter(), brutMesureMobile.getDownload(),
                                    brutMesureMobile.getUpload(),
                                    brutMesureMobile.getMos(),
                                    brutMesureMobile.getStateDL(),
                                    brutMesureMobile.getStateUL());

                            mesureMobileFTP.setMesureMobile(mesureMobile);

                            mesureMobileFTP = mesureMobileFTPService.save(mesureMobileFTP);

                            // Supprimez la mesure Brute
                            brutMesureMobileService.delete(brutMesureMobile.getId());
                            continue;

                        }

                        System.out.println("-----------> 10");
                        /************** HTTP ************/
                        if (typeMesure == EnumTypeMesure.HTTP) {
                            MesureMobileHTTP mesureMobileHTTP = new MesureMobileHTTP(
                                    brutMesureMobile.getTempsChargement(),
                                    brutMesureMobile.getDebitHTTP(),
                                    brutMesureMobile.getDnsLookup(),
                                    brutMesureMobile.getUrl(),
                                    brutMesureMobile.getMos());

                            mesureMobileHTTP.setMesureMobile(mesureMobile);
                            mesureMobileHTTP = mesureMobileHTTPService.save(mesureMobileHTTP);

                            // Supprimez la mesure Brute
                            brutMesureMobileService.delete(brutMesureMobile.getId());
                            continue;

                        }

                        System.out.println("-----------> 11");
                        /************** Video ************/
                        if (typeMesure == EnumTypeMesure.Video) {
                            MesureMobileVideo mesureMobileVideo = new MesureMobileVideo(brutMesureMobile.getBufferingTime(),
                                    brutMesureMobile.getDuree(), brutMesureMobile.getDebitVideo(),
                                    brutMesureMobile.getResolution(),
                                    brutMesureMobile.getMos());

                            mesureMobileVideo.setMesureMobile(mesureMobile);

                            mesureMobileVideo = mesureMobileVideoService.save(mesureMobileVideo);

                            // Supprimez la mesure Brute
                            brutMesureMobileService.delete(brutMesureMobile.getId());
                            continue;

                        }

                        System.out.println("-----------> 12");
                        }
                    } catch (Exception e) {
                        System.out.println("-----------> Exception");
                        e.printStackTrace();
                        continue;
                    }

                }

            }
        } catch (Exception e) {
            System.out.println("-----------> Exception");
            e.printStackTrace();
        } finally {
            calculMesureBrutFixe();
        }

    }

    public void calculMesureBrutFixe() {
        System.out.println("-----------> debut calcul brutMesuresFixes : ");
        List<BrutMesureFixe> brutMesuresFixes = brutMesureFixeService.findAll(2000);

        System.out.println("-----------> Envoi du mail pour sondes hors services");
        List<Sonde> sondes = new ArrayList<>();
        sondes = sondeService.findAll();
        for (Sonde s : sondes) {
            if(s.getDerniereConnexion()!=null)
                if (s.isEnabled() && mailService.diffDate(s.getDerniereConnexion(), Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))) {
                mailService.sendEmail(s.getId());

            }
        }
        if (!brutMesuresFixes.isEmpty() && brutMesuresFixes != null) {
            System.out.println("-----------> not empty, brutMesuresFixes : " + brutMesuresFixes.size());
            for (BrutMesureFixe brutMesureFixe : brutMesuresFixes) {
                try {

                    Sonde sonde = sondeService.findById(brutMesureFixe.getSonde());

                    MesureFixe mesureFixe = new MesureFixe();
                    if(brutMesureFixe.getSonde()==10) {
                        mesureFixe.setDate(new Date(brutMesureFixe.getDate().getTime() + 670050089));
                        System.out.println("tttttttttttttt11111"+brutMesureFixe.getDate());
                        System.out.println("tttttttttttttt22222"+new Date(brutMesureFixe.getDate().getTime() + findDifference(brutMesureFixe.getDate(), new Date())));
                        System.out.println("tttttttttttttt33"+ findDifference(brutMesureFixe.getDate(), new Date()));

                    }else{
                        mesureFixe.setDate(brutMesureFixe.getDate());
                    }
                    //mesureFixe.setDate(brutMesureFixe.getDate());


                    mesureFixe.setEtat(brutMesureFixe.getEtat());
                    mesureFixe.setUrl(brutMesureFixe.getUrl());
                    mesureFixe.setMesure(brutMesureFixe.getMesure());

                    mesureFixe.setSonde(
                            sonde
                    );

                    mesureFixe.setRegulateur(
                            regulateurService.findById(brutMesureFixe.getRegulateur())
                    );

                    mesureFixe.setIndicateurFixe(
                            indicateurFixeService.findById(brutMesureFixe.getIndicateurFixe())
                    );

                    mesureFixe.setEmplacement(
                            emplacementService.findById(brutMesureFixe.getEmplacement())
                    );

                    mesureFixe.setFournisseurAcces(
                            fournisseurAccesService.findById(brutMesureFixe.getFai())
                    );
                    System.out.println("befooore"+ mesureFixe.getMesure());
                    mesureFixe = mesureFixeService.save(mesureFixe);


                    if(brutMesureFixe.getSonde()==10) {
                        sonde.setDerniereConnexion(new Date(brutMesureFixe.getDate().getTime() + findDifference(brutMesureFixe.getDate(), new Date())));
                        sondeService.save(sonde);
                        mesureFixe.setDate(new Date(brutMesureFixe.getDate().getTime() + 670050089));
                        System.out.println("tttttttttttttt11111"+brutMesureFixe.getDate());
                        System.out.println("tttttttttttttt22222"+new Date(brutMesureFixe.getDate().getTime() + findDifference(brutMesureFixe.getDate(), new Date())));
                    }else{
                        sonde.setDerniereConnexion(brutMesureFixe.getDate());
                        sondeService.save(sonde);
                    }
                    //sonde.setDerniereConnexion(brutMesureFixe.getDate());
                    //sondeService.save(sonde);

                    brutMesureFixeService.delete(brutMesureFixe.getId());
                    System.out.println("afteeeerrr"+ mesureFixe.getMesure());
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(QoEntumApplication.class, args);
    }

    public List<String> toImeiList(String imeiListString) {

        if (imeiListString == null) {
            return new ArrayList<>();
        }

        imeiListString = imeiListString.replace("[", "").replace("]", "").trim();
        imeiListString = imeiListString.replaceAll("\\s+", "");
        String str[] = imeiListString.split(",");
        List<String> imeiList = new ArrayList<String>();
        imeiList = Arrays.asList(str);

        return imeiList;

    }

    public Integer testIfUnreported(Integer value) {
        if (value == null) return null;
        if (value == Integer.MAX_VALUE) return null;
        return value;
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Register resource handler for images
        registry.addResourceHandler("/client_logo/").addResourceLocations("/client_logo/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
    }

    private boolean matches(String ip, String subnet) {
        IpAddressMatcher ipAddressMatcher = new IpAddressMatcher(subnet);
        return ipAddressMatcher.matches(ip);
    }

    static long findDifference(Date start_date, Date end_date){

        // Calucalte time difference
        // in milliseconds
        long difference_In_Time = 0 ;
        if(end_date != null && start_date != null){
            difference_In_Time = end_date.getTime() - start_date.getTime();
        } else if (end_date != null || start_date != null){
            difference_In_Time = 3650000;
        }
        return difference_In_Time;

    }
}
