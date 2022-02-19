package com.sfm.qoentum.controller.qoentumf;

import com.sfm.qoentum.dto.AvgMinMaxPerDate;
import com.sfm.qoentum.dto.RequestStatFixe;
import com.sfm.qoentum.dto.StatParSeuil;
import com.sfm.qoentum.enumer.EnumGranularite;
import com.sfm.qoentum.model.qoentumf.Emplacement;
import com.sfm.qoentum.model.qoentumf.GroupeSonde;
import com.sfm.qoentum.model.qoentumf.SeuilFixe;
import com.sfm.qoentum.model.qoentumf.Sonde;
import com.sfm.qoentum.service.qoentumf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/dynamicDashboard")
public class DynamicDashboardController {

    @Autowired
    private DynamicDashboardService dynamicDashboardService;

    @Autowired
    private SondeService sondeService;

    @Autowired
    private GroupeSondeService groupeSondeService;

    @Autowired
    private EmplacementService emplacementService;

    @RequestMapping(value = "/avgMinMaxPerDatePerGroupe", method = RequestMethod.GET)
    public List<AvgMinMaxPerDate> findAvgMinMaxPerDatePerGroupe(
            @RequestParam(name = "granularite", defaultValue = "Heure", required = false) EnumGranularite granularite,
            @RequestParam(name = "groupe_sonde_id", required = true) long groupe_sonde_id,
            @RequestParam(name = "indicateur_fixe_id", required = true) long indicateur_fixe_id,
            @RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start,
            @RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end) {

        return dynamicDashboardService.findAvgMinMaxPerDatePerGroupe(groupe_sonde_id, indicateur_fixe_id, start, end, granularite);
    }

    @RequestMapping(value = "/avgMinMaxPerDatePerFai", method = RequestMethod.GET)
    public List<AvgMinMaxPerDate> findAvgMinMaxPerDatePerFai(
            @RequestParam(name = "granularite", defaultValue = "Heure", required = false) EnumGranularite granularite,
            @RequestParam(name = "fai_id", required = true) long fai_id,
            @RequestParam(name = "indicateur_fixe_id", required = true) long indicateur_fixe_id,
            @RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start,
            @RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end) {

        return dynamicDashboardService.findAvgMinMaxPerDatePerFai(fai_id, indicateur_fixe_id, start, end, granularite);
    }
    @RequestMapping(value = "/avgMinMaxPerDatePerEmplacement", method = RequestMethod.GET)
    public List<AvgMinMaxPerDate> findAvgMinMaxPerDatePerEmplacement(
            @RequestParam(name = "granularite", defaultValue = "Heure", required = false) EnumGranularite granularite,
            @RequestParam(name = "emplacement_id", required = true) long emplacement_id,
            @RequestParam(name = "indicateur_fixe_id", required = true) long indicateur_fixe_id,
            @RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start,
            @RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end) {

        return dynamicDashboardService.findAvgMinMaxPerDatePerEmplacement(emplacement_id, indicateur_fixe_id, start, end, granularite);
    }

    @RequestMapping(value = "/avgMinMaxPerDatePerSonde", method = RequestMethod.GET)
    public List<AvgMinMaxPerDate> findAvgMinMaxPerDatePerSonde(
            @RequestParam(name = "granularite", defaultValue = "Heure", required = false) EnumGranularite granularite,
            @RequestParam(name = "sonde_id", required = true) long sonde_id,
            @RequestParam(name = "indicateur_fixe_id", required = true) long indicateur_fixe_id,
            @RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start,
            @RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end) {

        return dynamicDashboardService.findAvgMinMaxPerDatePerSonde(sonde_id, indicateur_fixe_id, start, end, granularite);
    }

    @RequestMapping(value = "/statPercentSonde", method = RequestMethod.GET)
    public StatParSeuil statPercentSonde() {
        List<Sonde> sondes = new ArrayList<>();
        sondes = sondeService.findAll();
        return dynamicDashboardService.statPercentSonde(sondes);
    }

    @RequestMapping(value = "/statPercentGroupeSonde", method = RequestMethod.GET)
    public StatParSeuil statPercentGroupeSonde() {
        List<GroupeSonde> groupeSonde = new ArrayList<>();
        groupeSonde = groupeSondeService.findAll();
        return dynamicDashboardService.statPercentGroupeSonde(groupeSonde);
    }

    @RequestMapping(value = "/statPercentEmplacement", method = RequestMethod.GET)
    public StatParSeuil statPercentEmplacement() {
        List<Emplacement> emplacements = new ArrayList<>();
        emplacements = emplacementService.findAll();
        return dynamicDashboardService.statPercentEmplacement(emplacements);
    }

    @RequestMapping(value = "/statPercentSondeDisponibility", method = RequestMethod.GET)
    public StatParSeuil statPercentSondeDisponibility() {
        List<Sonde> sondes = new ArrayList<>();
        sondes = sondeService.findAll();
        return dynamicDashboardService.statPercentSondeDisponibility(sondes);
    }

}
