package com.sfm.qoentum.dao.qoentumf;

import com.sfm.qoentum.model.qoentumf.Sonde;
import com.sfm.qoentum.model.qoentumf.SondeHistory;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SondeHistoryDao extends JpaRepository<SondeHistory, Long> {
    List<SondeHistory> findAllBySondeIdAndDateTimeBetweenOrderByDateTime(long paramLong, Date start, Date end);
    List<SondeHistory> findAllBySondeIdOrderByDateTime(long paramLong);

    SondeHistory findSondeHistoryBySonde(Sonde paramSonde);
}