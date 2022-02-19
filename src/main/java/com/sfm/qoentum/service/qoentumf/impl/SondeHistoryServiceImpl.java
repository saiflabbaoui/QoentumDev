package com.sfm.qoentum.service.qoentumf.impl;


import com.sfm.qoentum.dao.qoentumf.SondeHistoryDao;
import com.sfm.qoentum.model.qoentumf.SondeHistory;
import com.sfm.qoentum.service.qoentumf.SondeHistoryService;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sondeHistoryService")
public class SondeHistoryServiceImpl implements SondeHistoryService {
    @Autowired
    private SondeHistoryDao sondeHistoryDao;

    public SondeHistory save(SondeHistory sondeHistory) {
        return (SondeHistory)this.sondeHistoryDao.save(sondeHistory);
    }

    public List<SondeHistory> getSondeHistoryBySondeIdBetweenDates(long sondeId, Date start, Date end) {
        return this.sondeHistoryDao.findAllBySondeIdAndDateTimeBetweenOrderByDateTime(sondeId, start, end);
    }

    @Override
    public List<SondeHistory> getSondeHistoryBySondeId(long sondeId) {
        return this.sondeHistoryDao.findAllBySondeIdOrderByDateTime(sondeId);
    }
}