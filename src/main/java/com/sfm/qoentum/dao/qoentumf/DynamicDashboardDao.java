package com.sfm.qoentum.dao.qoentumf;

import com.sfm.qoentum.dto.AvgMinMaxPerDate;
import com.sfm.qoentum.model.qoentumf.MesureFixe;
import com.sfm.qoentum.model.qoentumf.Sonde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DynamicDashboardDao extends JpaRepository<MesureFixe, Long> {



    List<MesureFixe> findAllByMesureIsNotNullAndSondeGroupeSondeIdAndIndicateurFixeIdAndDateBetweenOrderByMesureAsc(long groupe_sonde_id, long indicateur_fixe_id, Date start, Date end);

    @Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate("
            + "AVG(mesure) as avg, "
            + "MIN(mesure) as min, "
            + "MAX(mesure) as max, "
            + "COUNT(mesure) as records) "
            + "FROM MesureFixe "
            + "WHERE mesure is not NULL "
            + "And sonde.groupeSonde.id=:groupe_sonde_id "
            + "And indicateurFixe.id=:indicateur_fixe_id "
            + "And date between :start AND :end")
    public AvgMinMaxPerDate findMesureAvgMinMaxPerDatePerGroupe(
            @Param("groupe_sonde_id") long groupe_sonde_id,
            @Param("indicateur_fixe_id") long indicateur_fixe_id,
            @Param("start") Date start,
            @Param("end") Date end);

    List<MesureFixe> findAllByMesureIsNotNullAndFournisseurAccesIdAndIndicateurFixeIdAndDateBetweenOrderByMesureAsc(long fai_id, long indicateur_fixe_id, Date start, Date end);

    @Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate("
            + "AVG(mesure) as avg, "
            + "MIN(mesure) as min, "
            + "MAX(mesure) as max, "
            + "COUNT(mesure) as records) "
            + "FROM MesureFixe "
            + "WHERE mesure is not NULL "
            + "And fournisseurAcces.id=:fai_id "
            + "And indicateurFixe.id=:indicateur_fixe_id "
            + "And date between :start AND :end")
    public AvgMinMaxPerDate findMesureAvgMinMaxPerDatePerFai(
            @Param("fai_id") long fai_id,
            @Param("indicateur_fixe_id") long indicateur_fixe_id,
            @Param("start") Date start,
            @Param("end") Date end);

    List<MesureFixe> findAllByMesureIsNotNullAndEmplacementIdAndIndicateurFixeIdAndDateBetweenOrderByMesureAsc(long emplacement_id, long indicateur_fixe_id, Date start, Date end);

    @Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate("
            + "AVG(mesure) as avg, "
            + "MIN(mesure) as min, "
            + "MAX(mesure) as max, "
            + "COUNT(mesure) as records) "
            + "FROM MesureFixe "
            + "WHERE mesure is not NULL "
            + "And emplacement.id=:emplacement_id "
            + "And indicateurFixe.id=:indicateur_fixe_id "
            + "And date between :start AND :end")
    public AvgMinMaxPerDate findMesureAvgMinMaxPerDatePerEmplacement(
            @Param("emplacement_id") long emplacement_id,
            @Param("indicateur_fixe_id") long indicateur_fixe_id,
            @Param("start") Date start,
            @Param("end") Date end);

    List<MesureFixe> findAllByMesureIsNotNullAndSondeIdAndIndicateurFixeIdAndDateBetweenOrderByMesureAsc(long sonde_id, long indicateur_fixe_id, Date start, Date end);

    @Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate("
            + "AVG(mesure) as avg, "
            + "MIN(mesure) as min, "
            + "MAX(mesure) as max, "
            + "COUNT(mesure) as records) "
            + "FROM MesureFixe "
            + "WHERE mesure is not NULL "
            + "And sonde.id=:sonde_id "
            + "And indicateurFixe.id=:indicateur_fixe_id "
            + "And date between :start AND :end")
    public AvgMinMaxPerDate findMesureAvgMinMaxPerDatePerSonde(
            @Param("sonde_id") long sonde_id,
            @Param("indicateur_fixe_id") long indicateur_fixe_id,
            @Param("start") Date start,
            @Param("end") Date end);



}
