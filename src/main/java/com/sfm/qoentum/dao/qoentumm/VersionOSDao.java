package com.sfm.qoentum.dao.qoentumm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.qoentumm.VersionOS;

@Repository
public interface VersionOSDao extends JpaRepository<VersionOS, Long> {
	VersionOS findByVersion(String version);
	Page<VersionOS> findByVersionContaining(String version, Pageable pageable);
	boolean existsVersionOSByVersion(String version);
	boolean existsVersionOSByVersionAndIdIsNot(String version, Long id);
}
