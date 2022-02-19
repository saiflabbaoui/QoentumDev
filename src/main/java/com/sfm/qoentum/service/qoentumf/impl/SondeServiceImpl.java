package com.sfm.qoentum.service.qoentumf.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sfm.qoentum.dao.qoentumf.SondeHistoryDao;
import com.sfm.qoentum.model.qoentumf.SondeHistory;
import com.sfm.qoentum.service.qoentumf.SondeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumf.SondeDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.qoentumf.Sonde;
import com.sfm.qoentum.service.qoentumf.SondeService;


@Service(value = "sondeService")
public class SondeServiceImpl implements SondeService {

    @Autowired
    private SondeDao sondeDao;

    @Autowired
    private SondeHistoryService sondeHistoryService;

    @Autowired
    private SondeHistoryDao sondeHistoryDao;

    @Autowired
    private SondeService sondeService;

    @Override
    public Sonde save(Sonde sonde) {

        if (sonde.getId() != 0) {
            Sonde s = sondeDao.findById(sonde.getId()).get();
            //sonde.setDerniereConnexion(s.getDerniereConnexion());
        }

        return sondeDao.save(sonde);
    }

    @Override
    public Sonde configure(Sonde sonde) {
        return null;
    }

    @Override
    public List<Sonde> findAll() {
        List<Sonde> list = new ArrayList<>();
        sondeDao.findAll().iterator().forEachRemaining(list::add);

        /****** Enregistrement de l'historique des sondes ******/
        for (Sonde sonde : list) {
            List<SondeHistory> sondeHistories = new ArrayList<>();
            sondeHistories = sondeHistoryService.getSondeHistoryBySondeId(sonde.getId());
            if (sondeHistories.size()!= 0) {
                SondeHistory sondeHistory = new SondeHistory();
                sondeHistory = sondeHistories.get(sondeHistories.size() - 1);
                //if(sondeHistory.getEtat()  != etatSonde(sonde.isEnabled()) ){
                    if (sondeHistory.getEtat() == 1 && (sonde.isEnabled()) && (sonde.getDerniereConnexion() != null) && ((new Date().getTime() - sonde.getDerniereConnexion().getTime()) > 3600000) ) {
                        sondeHistoryService.save(new SondeHistory(sonde, -1, new Date()));
                    } else if (sondeHistory.getEtat()  != etatSonde(sonde.isEnabled()) && sonde.getDerniereConnexion() != null && (new Date().getTime() - sonde.getDerniereConnexion().getTime()) < 3600000 ) {
                        sondeHistoryService.save(new SondeHistory(sonde, (sonde.isEnabled()) ? 1 : 0, new Date()));
                    } else if (sondeHistory.getEtat() == -1 && !sonde.isEnabled()){
                        sondeHistoryService.save(new SondeHistory(sonde, 0, new Date()));
                    }
                //}
            } else {
                if (((sonde.isEnabled()) && (sonde.getDerniereConnexion() != null) && ((new Date().getTime() - sonde.getDerniereConnexion().getTime()) > 3600000)) || (sonde.isEnabled()) && (sonde.getDerniereConnexion() == null) ) {
                    sondeHistoryService.save(new SondeHistory(sonde, -1, new Date()));
                } else {
                    sondeHistoryService.save(new SondeHistory(sonde, (sonde.isEnabled()) ? 1 : 0, new Date()));
                }
            }

        }
        /****** Fin Enregistrement de l'historique des sondes ******/

        return list;
    }

    @Override
    public void delete(long id) {
        sondeDao.deleteById(id);

    }

    @Override
    public Sonde findById(Long id) {
        return sondeDao.findById(id).get();
    }

    @Override
    public long count(long client_id) {
        if (client_id == -1)
            return sondeDao.count();
        else
            return sondeDao.countByClientId(client_id);
    }

    @Override
    public Sonde setDerniereConnexion(long idSonde, String hostname, String ipAddress, Date date) {
        Sonde sonde = sondeDao.findById(idSonde).get();

        sonde.setDerniereConnexion(date);
        sonde.setIpAddress(ipAddress);
        sonde.setHostname(hostname);

        return sondeDao.save(sonde);
    }

    @Override
    public EntityPage<Sonde> findByEmplacementNomContainingOrFournisseurAccesNomContaining(String recherche, Pageable pageable) {

        Page<Sonde> sondesPage = sondeDao.findByEmplacementNomContainingOrFournisseurAccesNomContaining(recherche, recherche, pageable);

        EntityPage<Sonde> sondes = new EntityPage<Sonde>();

        sondes.setList(sondesPage.getContent());

        PageUtil pageUtil = new PageUtil();
        pageUtil.setNombreElementParPage(sondesPage.getNumberOfElements());
        pageUtil.setNombrePage(sondesPage.getTotalPages());
        pageUtil.setNumeroPage(sondesPage.getNumber() + 1);
        pageUtil.setNombreTotalElement(sondesPage.getTotalElements());

        sondes.setPageUtil(pageUtil);

        return sondes;
    }



    public int etatSonde(boolean b){
        if(b == true){
            return 1;
        }else{
            return 0;
        }

    }


}
