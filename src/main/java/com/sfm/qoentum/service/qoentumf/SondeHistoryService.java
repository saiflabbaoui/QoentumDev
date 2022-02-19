package com.sfm.qoentum.service.qoentumf;


import com.sfm.qoentum.model.qoentumf.SondeHistory;

import java.util.Date;
import java.util.List;

public interface SondeHistoryService {
    SondeHistory save(SondeHistory paramSondeHistory);

    List<SondeHistory> getSondeHistoryBySondeIdBetweenDates(long paramLong, Date start, Date end);
    List<SondeHistory> getSondeHistoryBySondeId(long paramLong);

}
