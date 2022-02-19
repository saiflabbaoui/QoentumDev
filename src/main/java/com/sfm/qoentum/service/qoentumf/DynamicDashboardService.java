package com.sfm.qoentum.service.qoentumf;

import com.sfm.qoentum.dto.AvgMinMaxPerDate;
import com.sfm.qoentum.dto.StatParSeuil;
import com.sfm.qoentum.enumer.EnumGranularite;
import com.sfm.qoentum.model.qoentumf.Emplacement;
import com.sfm.qoentum.model.qoentumf.GroupeSonde;
import com.sfm.qoentum.model.qoentumf.SeuilFixe;
import com.sfm.qoentum.model.qoentumf.Sonde;

import java.util.Date;
import java.util.List;

public interface DynamicDashboardService {

    List<AvgMinMaxPerDate> findAvgMinMaxPerDatePerGroupe(long groupe_sonde_id, long indicateur_fixe_id, Date start, Date end, EnumGranularite granularite);

    List<AvgMinMaxPerDate> findAvgMinMaxPerDatePerFai(long fai_id, long indicateur_fixe_id, Date start, Date end, EnumGranularite granularite);

    List<AvgMinMaxPerDate> findAvgMinMaxPerDatePerEmplacement(long emplacement_id, long indicateur_fixe_id, Date start, Date end, EnumGranularite granularite);

    List<AvgMinMaxPerDate> findAvgMinMaxPerDatePerSonde(long sonde_id, long indicateur_fixe_id, Date start, Date end, EnumGranularite granularite);

    StatParSeuil statPercentSonde(List<Sonde> sondes);

    StatParSeuil statPercentGroupeSonde(List<GroupeSonde> groupes);

    StatParSeuil statPercentEmplacement(List<Emplacement> emplacements);

    StatParSeuil statPercentSondeDisponibility(List<Sonde> sondes);




}
