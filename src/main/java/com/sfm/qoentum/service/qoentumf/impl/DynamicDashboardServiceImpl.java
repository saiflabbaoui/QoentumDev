package com.sfm.qoentum.service.qoentumf.impl;


import com.sfm.qoentum.dao.qoentumf.*;
import com.sfm.qoentum.dto.AvgMinMaxPerDate;
import com.sfm.qoentum.dto.DateStartEnd;
import com.sfm.qoentum.dto.LabelRecordsPercent;
import com.sfm.qoentum.dto.StatParSeuil;
import com.sfm.qoentum.enumer.EnumGranularite;
import com.sfm.qoentum.model.qoentumf.*;
import com.sfm.qoentum.service.qoentumf.DynamicDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "dynamicDashboardService")
public class DynamicDashboardServiceImpl implements DynamicDashboardService {


    @Autowired
    private DynamicDashboardDao dynamicDashboardDao;
    @Autowired
    private SondeDao sondeDao;
    @Autowired
    private GroupeSondeDao groupeSondeDao;
    @Autowired
    private EmplacementDao emplacementDao;

    @Override
    public List<AvgMinMaxPerDate> findAvgMinMaxPerDatePerGroupe(long groupe_sonde_id, long indicateur_fixe_id, Date start, Date end, EnumGranularite granularite) {
        List<AvgMinMaxPerDate> resultList = new ArrayList<>();

        // Récupérer la liste des dates en fonction de la granularité entre les deux
        // dates
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        start = cal.getTime();

        System.out.println("start date --------->" + start);

        cal.setTime(end);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        end = cal.getTime();

        System.out.println("end date --------->" + end);

        List<Date> datesBetween = getDatesBetween(start, end);

        System.out.println("dates between --------->" + datesBetween);

        // Générer la liste des dates debut et fin en fonction de la granularité
        List<DateStartEnd> dateStartEnds = generatesDateStartDebut(datesBetween, granularite);

        // Récupérer depuis le DAO tous les résultats un à un par intervalle de la
        // granularité selon l'indicateur mobile

        for (DateStartEnd dateStartEnd : dateStartEnds) {

            AvgMinMaxPerDate avgMinMaxPerDate = dynamicDashboardDao.findMesureAvgMinMaxPerDatePerGroupe(groupe_sonde_id,
                    indicateur_fixe_id, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
            avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

            List<MesureFixe> ms = dynamicDashboardDao.findAllByMesureIsNotNullAndSondeGroupeSondeIdAndIndicateurFixeIdAndDateBetweenOrderByMesureAsc(groupe_sonde_id, indicateur_fixe_id, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());


            if (ms.isEmpty())
                avgMinMaxPerDate.setMedian(0);

            else {
                if (ms.size() % 2 == 0)
                    avgMinMaxPerDate.setMedian(
                            (ms.get(ms.size() / 2).getMesure() + ms.get((ms.size() / 2) - 1).getMesure()) / 2);
                else
                    avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesure());
            }

            resultList.add(avgMinMaxPerDate);
        }

        return resultList;
    }

    @Override
    public List<AvgMinMaxPerDate> findAvgMinMaxPerDatePerFai(long fai_id, long indicateur_fixe_id, Date start, Date end, EnumGranularite granularite) {
        List<AvgMinMaxPerDate> resultList = new ArrayList<>();

        // Récupérer la liste des dates en fonction de la granularité entre les deux
        // dates
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        start = cal.getTime();

        System.out.println("start date --------->" + start);

        cal.setTime(end);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        end = cal.getTime();

        System.out.println("end date --------->" + end);

        List<Date> datesBetween = getDatesBetween(start, end);

        System.out.println("dates between --------->" + datesBetween);

        // Générer la liste des dates debut et fin en fonction de la granularité
        List<DateStartEnd> dateStartEnds = generatesDateStartDebut(datesBetween, granularite);

        // Récupérer depuis le DAO tous les résultats un à un par intervalle de la
        // granularité selon l'indicateur mobile

        for (DateStartEnd dateStartEnd : dateStartEnds) {

            AvgMinMaxPerDate avgMinMaxPerDate = dynamicDashboardDao.findMesureAvgMinMaxPerDatePerFai(fai_id,
                    indicateur_fixe_id, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
            avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

            List<MesureFixe> ms = dynamicDashboardDao.findAllByMesureIsNotNullAndFournisseurAccesIdAndIndicateurFixeIdAndDateBetweenOrderByMesureAsc(fai_id, indicateur_fixe_id, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());


            if (ms.isEmpty())
                avgMinMaxPerDate.setMedian(0);

            else {
                if (ms.size() % 2 == 0)
                    avgMinMaxPerDate.setMedian(
                            (ms.get(ms.size() / 2).getMesure() + ms.get((ms.size() / 2) - 1).getMesure()) / 2);
                else
                    avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesure());
            }

            resultList.add(avgMinMaxPerDate);
        }

        return resultList;    }

    @Override
    public List<AvgMinMaxPerDate> findAvgMinMaxPerDatePerEmplacement(long emplacement_id, long indicateur_fixe_id, Date start, Date end, EnumGranularite granularite) {
        List<AvgMinMaxPerDate> resultList = new ArrayList<>();

        // Récupérer la liste des dates en fonction de la granularité entre les deux
        // dates
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        start = cal.getTime();

        System.out.println("start date --------->" + start);

        cal.setTime(end);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        end = cal.getTime();

        System.out.println("end date --------->" + end);

        List<Date> datesBetween = getDatesBetween(start, end);

        System.out.println("dates between --------->" + datesBetween);

        // Générer la liste des dates debut et fin en fonction de la granularité
        List<DateStartEnd> dateStartEnds = generatesDateStartDebut(datesBetween, granularite);

        // Récupérer depuis le DAO tous les résultats un à un par intervalle de la
        // granularité selon l'indicateur mobile

        for (DateStartEnd dateStartEnd : dateStartEnds) {

            AvgMinMaxPerDate avgMinMaxPerDate = dynamicDashboardDao.findMesureAvgMinMaxPerDatePerEmplacement(emplacement_id,
                    indicateur_fixe_id, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
            avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

            List<MesureFixe> ms = dynamicDashboardDao.findAllByMesureIsNotNullAndEmplacementIdAndIndicateurFixeIdAndDateBetweenOrderByMesureAsc(emplacement_id, indicateur_fixe_id, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());


            if (ms.isEmpty())
                avgMinMaxPerDate.setMedian(0);

            else {
                if (ms.size() % 2 == 0)
                    avgMinMaxPerDate.setMedian(
                            (ms.get(ms.size() / 2).getMesure() + ms.get((ms.size() / 2) - 1).getMesure()) / 2);
                else
                    avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesure());
            }

            resultList.add(avgMinMaxPerDate);
        }

        return resultList;    }

    @Override
    public List<AvgMinMaxPerDate> findAvgMinMaxPerDatePerSonde(long sonde_id, long indicateur_fixe_id, Date start, Date end, EnumGranularite granularite) {
        List<AvgMinMaxPerDate> resultList = new ArrayList<>();

        // Récupérer la liste des dates en fonction de la granularité entre les deux
        // dates
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        start = cal.getTime();

        System.out.println("start date --------->" + start);

        cal.setTime(end);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        end = cal.getTime();

        System.out.println("end date --------->" + end);

        List<Date> datesBetween = getDatesBetween(start, end);

        System.out.println("dates between --------->" + datesBetween);

        // Générer la liste des dates debut et fin en fonction de la granularité
        List<DateStartEnd> dateStartEnds = generatesDateStartDebut(datesBetween, granularite);

        // Récupérer depuis le DAO tous les résultats un à un par intervalle de la
        // granularité selon l'indicateur mobile

        for (DateStartEnd dateStartEnd : dateStartEnds) {

            AvgMinMaxPerDate avgMinMaxPerDate = dynamicDashboardDao.findMesureAvgMinMaxPerDatePerSonde(sonde_id,
                    indicateur_fixe_id, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
            avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

            List<MesureFixe> ms = dynamicDashboardDao.findAllByMesureIsNotNullAndSondeIdAndIndicateurFixeIdAndDateBetweenOrderByMesureAsc(sonde_id, indicateur_fixe_id, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
            System.out.println("karem"+ms);

            if (ms.isEmpty())
                avgMinMaxPerDate.setMedian(0);

            else {
                if (ms.size() % 2 == 0)
                    avgMinMaxPerDate.setMedian(
                            (ms.get(ms.size() / 2).getMesure() + ms.get((ms.size() / 2) - 1).getMesure()) / 2);
                else
                    avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesure());
            }

            resultList.add(avgMinMaxPerDate);
        }

        return resultList;     }

    @Override
    public StatParSeuil statPercentSonde(List<Sonde> sondes) {
        List<LabelRecordsPercent> labelRecordsPercents = new ArrayList<>();

        int totalRecords = sondes.size();
        if (totalRecords == 0) {
            return new StatParSeuil(totalRecords, labelRecordsPercents);
        }
            int records = 0;
        for(Sonde s : sondes) {
            records = sondeDao.countSondeByEnabled(s.isEnabled());
        }
            double percent = ((double) records / totalRecords) * 100;
            labelRecordsPercents.add(new LabelRecordsPercent(percent, records));

        return new StatParSeuil(totalRecords, labelRecordsPercents);
    }


    @Override
    public StatParSeuil statPercentGroupeSonde(List<GroupeSonde> groupes) {
        List<LabelRecordsPercent> labelRecordsPercents = new ArrayList<>();

        int totalRecords = groupes.size();
        if (totalRecords == 0) {
            return new StatParSeuil(totalRecords, labelRecordsPercents);
        }
        int records = 0;
        for(GroupeSonde g : groupes) {
            records = groupeSondeDao.countGroupeSondeByEnabled(g.isEnabled());

        }
        double percent = ((double) records / totalRecords) * 100;
        labelRecordsPercents.add(new LabelRecordsPercent(percent, records));
        return new StatParSeuil(totalRecords, labelRecordsPercents);    }

    @Override
    public StatParSeuil statPercentEmplacement(List<Emplacement> emplacements) {
        List<LabelRecordsPercent> labelRecordsPercents = new ArrayList<>();

        int totalRecords = emplacements.size();

        if (totalRecords == 0) {
            return new StatParSeuil(totalRecords, labelRecordsPercents);
        }
        int records = 0;
        for(Emplacement e : emplacements) {
            records = emplacementDao.countEmplacementByEnabled(e.isEnabled());
        }
        double percent = ((double) records / totalRecords) * 100;
        labelRecordsPercents.add(new LabelRecordsPercent(percent, records));
        return new StatParSeuil(totalRecords, labelRecordsPercents);    }

    @Override
    public StatParSeuil statPercentSondeDisponibility(List<Sonde> sondes) {

        List<String> disponibilitySondes = new ArrayList<>();
        disponibilitySondes.add("Activée");
        disponibilitySondes.add("Désactivée");
        disponibilitySondes.add("Hors service");

        List<LabelRecordsPercent> labelRecordsPercents = new ArrayList<>();
        int totalRecords = sondes.size();
        if (totalRecords == 0) {
            return new StatParSeuil(totalRecords, labelRecordsPercents);
        }
        Integer sondeNotEnabled= sondes.stream()
                .filter(x-> !x.isEnabled())
                .collect(Collectors.toList())
                .size();
        Integer sondeHorsService= sondes.stream()
                .filter(x-> x.isEnabled()  )
                .filter(w-> findDifference(w.getDerniereConnexion(), new Date()) > 3600000)
                .collect(Collectors.toList())
                .size();
        Integer sondeEnabled= sondes.stream()
                .filter(x-> x.isEnabled()  )
                .filter(w-> findDifference(w.getDerniereConnexion(), new Date()) < 3600000)
                .collect(Collectors.toList())
                .size();

        labelRecordsPercents.add(new LabelRecordsPercent((((double) sondeNotEnabled / totalRecords) * 100), sondeNotEnabled));
        labelRecordsPercents.add(new LabelRecordsPercent((((double) sondeHorsService / totalRecords) * 100), sondeHorsService));
        labelRecordsPercents.add(new LabelRecordsPercent((((double) sondeEnabled / totalRecords) * 100), sondeEnabled));

        return new StatParSeuil(totalRecords, labelRecordsPercents);
    }


    public static List<Date> getDatesBetween(Date startDate, Date endDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return datesInRange;
    }

    public static List<DateStartEnd> generatesDateStartDebut(List<Date> dates, EnumGranularite granularite) {
        List<DateStartEnd> dateStartEnds = new ArrayList<>();

        if (granularite.equals(EnumGranularite.Heure)) {
            for (Date date : dates) {
                for (int i = 0; i < 24; i++) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);

                    cal.set(Calendar.HOUR_OF_DAY, i);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    Date start = cal.getTime();

                    cal.set(Calendar.HOUR_OF_DAY, i);
                    cal.set(Calendar.MINUTE, 59);
                    cal.set(Calendar.SECOND, 59);
                    Date end = cal.getTime();

                    dateStartEnds.add(new DateStartEnd(start, end));

                }
            }
        } else {

            for (Date date : dates) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);

                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                Date start = cal.getTime();

                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59);
                Date end = cal.getTime();

                dateStartEnds.add(new DateStartEnd(start, end));
            }

        }

        System.out.println("--------> les dates : " + dateStartEnds);

        return dateStartEnds;
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
