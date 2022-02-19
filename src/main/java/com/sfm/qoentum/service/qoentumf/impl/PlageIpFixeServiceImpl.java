package com.sfm.qoentum.service.qoentumf.impl;

import com.sfm.qoentum.dao.qoentumf.PlageIpFixeDao;
import com.sfm.qoentum.model.qoentumf.FaiTechnologieFixePlageIp;
import com.sfm.qoentum.model.qoentumf.PlageIpFixe;
import com.sfm.qoentum.service.qoentumf.PlageIpFixeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "PlageIpFixeService")
public class PlageIpFixeServiceImpl implements PlageIpFixeService {


    @Autowired
    PlageIpFixeDao plageIpFixeDao;

    @Override
    public PlageIpFixe save(PlageIpFixe plageIpFixe) {
        return plageIpFixeDao.save(plageIpFixe);
    }

    @Override
    public List<PlageIpFixe> getPlageIpParFaiTechnologie(long id) {
        return plageIpFixeDao.findByFaiTechnologieFixePlageIp_Id(id);
    }

    @Override
    public List<PlageIpFixe> getAllPlageIpFixe() {
        return plageIpFixeDao.findAll();
    }

    @Override
    public void deletePlageIp(long id) {
        plageIpFixeDao.deleteById(id);
    }
}
