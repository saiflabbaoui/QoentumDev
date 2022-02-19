package com.sfm.qoentum.dao.qoentumm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.qoentumm.MobilePhone;

@Repository
public interface MobilePhoneDao extends JpaRepository< MobilePhone, Long> {
	Page<MobilePhone> findByModeleContaining(String modele, Pageable pageable);
	MobilePhone getMobilePhoneByUuid(String uuid);
}
