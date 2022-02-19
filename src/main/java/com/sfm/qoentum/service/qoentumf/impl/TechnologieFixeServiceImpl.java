package com.sfm.qoentum.service.qoentumf.impl;

import com.sfm.qoentum.dao.qoentumf.FaiTechnologieFixePlageIpDao;
import com.sfm.qoentum.dao.qoentumf.TechnologieFixeDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.dto.TechnologiesParFai;
import com.sfm.qoentum.model.qoentumf.FaiTechnologieFixePlageIp;
import com.sfm.qoentum.model.qoentumf.FournisseurAcces;
import com.sfm.qoentum.model.qoentumf.TechnologieFixe;
import com.sfm.qoentum.service.qoentumf.FournisseurAccesService;
import com.sfm.qoentum.service.qoentumf.TechnologieFixeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "TechnologieFixeService")
public class TechnologieFixeServiceImpl implements TechnologieFixeService {


    @Autowired
    TechnologieFixeDao technologieFixeDao;

    @Autowired
    FournisseurAccesService fournisseurAccesService;

    @Autowired
    private FaiTechnologieFixePlageIpDao faiTechnologieFixePlageIpDao;


    @Override
    public long save(TechnologieFixe technologieFixe) {
        technologieFixeDao.save(technologieFixe);
        return technologieFixe.getId();
    }

    @Override
    public List<TechnologieFixe> findAll() {
        List<TechnologieFixe> list = new ArrayList<>();
        technologieFixeDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Override
    public TechnologiesParFai addTechnologiesToFai(long id, List<TechnologieFixe> technologies) {
        TechnologiesParFai technologiesParFai = new TechnologiesParFai();
        technologiesParFai.setId(id);
        technologiesParFai.setTechnologies(technologies);
        FournisseurAcces fai = fournisseurAccesService.findById(id);
        FaiTechnologieFixePlageIp faiTechnologieFixePlageIp = new FaiTechnologieFixePlageIp();

        for (TechnologieFixe tech : technologies) {
            faiTechnologieFixePlageIp.setFournisseurAcces(fai);
            faiTechnologieFixePlageIp.setTechnologieFixe(tech);
        }
        List<FaiTechnologieFixePlageIp> faiTechnologieFixePlageIps = faiTechnologieFixePlageIpDao.findByFournisseurAcces_Id(id);
        FaiTechnologieFixePlageIp f = faiTechnologieFixePlageIps.stream().
                filter(x -> x.getTechnologieFixe().getId() == faiTechnologieFixePlageIp.getTechnologieFixe().getId()).
                filter(x -> x.getFournisseurAcces().getId() == faiTechnologieFixePlageIp.getFournisseurAcces().getId()).
                findAny().orElse(null);
        if (f == null) {
            faiTechnologieFixePlageIpDao.save(faiTechnologieFixePlageIp);
        } else {
            faiTechnologieFixePlageIpDao.delete(f);
        }
        return technologiesParFai;
    }

    @Override
    public List<TechnologieFixe> getTechnologiesParFai(long id) {
        FournisseurAcces fai = fournisseurAccesService.findById(id);
        List<FaiTechnologieFixePlageIp> faiTechnologieFixePlageIps = faiTechnologieFixePlageIpDao.findByFournisseurAcces_Id(id);
        List<TechnologieFixe> technologies = new ArrayList<>();
        for (FaiTechnologieFixePlageIp xx : faiTechnologieFixePlageIps) {
            technologies.add(xx.getTechnologieFixe());
        }

        return technologies;
    }

    @Override
    public List<FaiTechnologieFixePlageIp> getFaiTechnologiesPlageIpParFai(long id) {
        List<FaiTechnologieFixePlageIp> faiTechnologieFixePlageIps = faiTechnologieFixePlageIpDao.findByFournisseurAcces_Id(id);
        return faiTechnologieFixePlageIps;
    }

    @Override
    public FaiTechnologieFixePlageIp getFaiTechnologieFixePlageIpParFaiAndTechnologie(long idFai, long idTechnologie) {
        FaiTechnologieFixePlageIp faiTechnologieFixePlageIp = faiTechnologieFixePlageIpDao.findByFournisseurAcces_IdAndTechnologieFixe_Id(idFai, idTechnologie);

        return faiTechnologieFixePlageIp;
    }

    @Override
    public void delete(long id) {
        technologieFixeDao.deleteById(id);
    }

    @Override
    public EntityPage<TechnologieFixe> findByTechnologieContaining(String recherche, Pageable pageable) {
        Page<TechnologieFixe> technologieFixesPage = technologieFixeDao.findByTechnologieContaining(recherche, pageable);

        EntityPage<TechnologieFixe> technologieFixes = new EntityPage<TechnologieFixe>();

        technologieFixes.setList(technologieFixesPage.getContent());

        PageUtil pageUtil = new PageUtil();
        pageUtil.setNombreElementParPage(technologieFixesPage.getNumberOfElements());
        pageUtil.setNombrePage(technologieFixesPage.getTotalPages());
        pageUtil.setNumeroPage(technologieFixesPage.getNumber() + 1);
        pageUtil.setNombreTotalElement(technologieFixesPage.getTotalElements());

        technologieFixes.setPageUtil(pageUtil);

        return technologieFixes;
    }
}
