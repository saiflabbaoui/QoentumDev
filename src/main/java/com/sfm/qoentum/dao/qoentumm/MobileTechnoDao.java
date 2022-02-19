package com.sfm.qoentum.dao.qoentumm;

import com.sfm.qoentum.model.qoentumm.GenerationTechno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.qoentumm.MobileTechno;

import java.util.List;

@Repository
public interface MobileTechnoDao extends JpaRepository< MobileTechno, Long> {
	Page<MobileTechno> findByTechnologieContaining(String technologie, Pageable pageable);
List<MobileTechno> findByGenerationTechno(GenerationTechno generationTechno);
}
