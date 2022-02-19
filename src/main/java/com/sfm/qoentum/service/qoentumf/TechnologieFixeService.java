package com.sfm.qoentum.service.qoentumf;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.TechnologiesParFai;
import com.sfm.qoentum.model.qoentumf.FaiTechnologieFixePlageIp;
import com.sfm.qoentum.model.qoentumf.TechnologieFixe;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TechnologieFixeService {

    long save(TechnologieFixe technologieFixe);

    List<TechnologieFixe> findAll();

    TechnologiesParFai addTechnologiesToFai(long id, List<TechnologieFixe> technologies);

    List<TechnologieFixe> getTechnologiesParFai(long id);

    List<FaiTechnologieFixePlageIp> getFaiTechnologiesPlageIpParFai(long id);

    FaiTechnologieFixePlageIp getFaiTechnologieFixePlageIpParFaiAndTechnologie(long idFai, long idTechnologie);

    void delete(long id);

    EntityPage<TechnologieFixe> findByTechnologieContaining(String recherche, Pageable pageable);

}
