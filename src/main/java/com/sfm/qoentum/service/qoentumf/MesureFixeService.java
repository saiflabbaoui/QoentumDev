package com.sfm.qoentum.service.qoentumf;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.AvgMinMaxPerDate;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.StatParSeuil;
import com.sfm.qoentum.enumer.EnumGranularite;
import com.sfm.qoentum.model.qoentumf.MesureFixe;
import com.sfm.qoentum.model.qoentumf.SeuilFixe;

public interface MesureFixeService {
	
	MesureFixe save(MesureFixe mesureFixe);
	
    List<MesureFixe> findAll();
    
    void delete(long id);
    
    MesureFixe findById(Long id);
    
    long count();
    
    EntityPage<MesureFixe> listMesureFixe(long sonde_id, long fournisseur_acces_id, long indicateur_fixe_id, long emplacement_id, Date start, Date end, Pageable pageable);
    
    StatParSeuil statPercent(List<SeuilFixe> plages, long sonde_id, long fournisseur_acces_id, long indicateur_fixe_id, long emplacement_id, Date start, Date end);
    
    List<AvgMinMaxPerDate> findAvgMinMaxPerDate(long sonde_id, long fournisseur_acces_id, long indicateur_fixe_id, long emplacement_id, Date start, Date end, EnumGranularite granularite);
}
