package com.sfm.qoentum.dao.qoentumf;

import com.sfm.qoentum.model.qoentumf.FaiTechnologieFixePlageIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaiTechnologieFixePlageIpDao extends JpaRepository<FaiTechnologieFixePlageIp, Long> {
    FaiTechnologieFixePlageIp findByFournisseurAcces_IdAndTechnologieFixe_Id(long idFai,long idTechnologie);
    List<FaiTechnologieFixePlageIp> findByFournisseurAcces_Id(long id);
}
