package com.sfm.qoentum.service.qoentumf;

import com.sfm.qoentum.model.qoentumf.FaiTechnologieFixePlageIp;
import com.sfm.qoentum.model.qoentumf.PlageIpFixe;

import java.util.List;

public interface PlageIpFixeService {

    PlageIpFixe save(PlageIpFixe plageIpFixe);
    List<PlageIpFixe> getPlageIpParFaiTechnologie(long id);
    List<PlageIpFixe> getAllPlageIpFixe();
    void deletePlageIp(long id);
}
