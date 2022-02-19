package com.sfm.qoentum.dao.qoentumm;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.qoentumm.GenerationTechno;

@Repository
public interface GenerationTechnoDao extends JpaRepository<GenerationTechno, Long> {
	Page<GenerationTechno> findByGenerationContaining(String nom, Pageable pageable);
	boolean existsGenerationTechnoByGeneration(String generation);
	boolean existsGenerationTechnoByGenerationAndIdIsNot(String generation, Long id);
	
	List<GenerationTechno> findByGenerationIsNotAndGenerationIsNot(String generation1, String generation2);
	
	GenerationTechno findByGeneration(String generation);
}
