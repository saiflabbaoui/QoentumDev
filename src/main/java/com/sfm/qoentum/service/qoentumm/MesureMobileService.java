package com.sfm.qoentum.service.qoentumm;

import java.util.Date;
import java.util.List;

import com.sfm.qoentum.model.qoentumf.FournisseurAcces;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.AvgMinMaxPerDate;
import com.sfm.qoentum.dto.DateListEventNombrePercent;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.EventNombrePercent;
import com.sfm.qoentum.dto.EvolutionIndicateur;
import com.sfm.qoentum.dto.StatParSeuil;
import com.sfm.qoentum.dto.geo.GeoJSONMobile;
import com.sfm.qoentum.dto.geo.TestDTO;
import com.sfm.qoentum.enumer.EnumGranularite;
import com.sfm.qoentum.enumer.EnumTypeMesure;
import com.sfm.qoentum.model.qoentumm.GenerationTechno;
import com.sfm.qoentum.model.qoentumm.Operateur;
import com.sfm.qoentum.model.qoentumm.SeuilMobile;
import com.sfm.qoentum.model.qoentumm.mesure.MesureMobile;

public interface MesureMobileService {
	
	MesureMobile save(MesureMobile mesureMobile);
	
    List<MesureMobile> findAll();
    
    void delete(long id);
    
    MesureMobile findById(Long id);
    
    long count();
    
    EntityPage<MesureMobile> findAllByOperateurAndMobileTechnoGenerationTechno(Operateur operateur, GenerationTechno generationTechno, Date start, Date end, Pageable pageable, Long indicateurId);

    EntityPage<MesureMobile> findAllmesureWifi(FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, Date start, Date end, Pageable pageable, Long indicateurId);

    EntityPage<MesureMobile> findNoAffectedmesureWifi(GenerationTechno generationTechno, Date start, Date end, Pageable pageable, Long indicateurId);


    StatParSeuil statPercent(List<SeuilMobile> plages, Operateur operateur, GenerationTechno generationTechno, Date start, Date end, Long indicateurId);
    
    JSONObject geoPlage(Operateur operateur, GenerationTechno generationTechno, Date start, Date end, Long indicateurId);
    
    JSONObject geoNew(Operateur operateur, GenerationTechno generationTechno, Date start, Date end, Long indicateurId, List<Long> seuilIds, Pageable pageable);

    JSONObject geoNewWifi(FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, Date start, Date end, Long indicateurId,  Pageable pageable);
    
    List<GeoJSONMobile> geoCouvertureMobile(Operateur operateur, GenerationTechno generationTechno);
    
    List<EvolutionIndicateur> evolutionIndicateur (Operateur operateur, GenerationTechno generationTechno, Date start, Date end, Long indicateurId);
    
    List<AvgMinMaxPerDate> findAvgMinMaxPerDate(Operateur operateur,GenerationTechno generationTechno, Date startDate, Date endDate, EnumGranularite granularite, Long indicateurId);

    List<AvgMinMaxPerDate> findAvgMinMaxPerDateWifi(FournisseurAcces fournisseurAcces,GenerationTechno generationTechno, Date startDate, Date endDate, EnumGranularite granularite, Long indicateurId);

    JSONObject geoPresenceCouverture(Operateur operateur, Date start, Date end);
    
    
    List<EventNombrePercent> findStatEventPerDate(Operateur operateur, EnumTypeMesure typeMesure, Date start, Date end);
    
    List<DateListEventNombrePercent> findEvolutionEventPerDate(Operateur operateur, EnumTypeMesure typeMesure, Date start, Date end, EnumGranularite granularite);
    
    JSONObject findEventPerDate(Operateur operateur, EnumTypeMesure typeMesure, Date start, Date end);
    
    List<TestDTO> test();

}
