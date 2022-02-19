package com.sfm.qoentum.dao.qoentumf;

import com.sfm.qoentum.model.qoentumf.PlageIpFixe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlageIpFixeDao extends JpaRepository<PlageIpFixe, Long> {

    List<PlageIpFixe> findByFaiTechnologieFixePlageIp_Id(long id);

}
