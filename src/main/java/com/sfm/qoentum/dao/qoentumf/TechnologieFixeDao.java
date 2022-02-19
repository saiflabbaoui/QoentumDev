package com.sfm.qoentum.dao.qoentumf;

import com.sfm.qoentum.model.qoentumf.TechnologieFixe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologieFixeDao extends JpaRepository<TechnologieFixe, Long> {
    Page<TechnologieFixe> findByTechnologieContaining(String technologie, Pageable pageable);
}
