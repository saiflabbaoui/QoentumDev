package com.sfm.qoentum.controller.qoentumf;

import com.sfm.qoentum.model.qoentumf.SondeHistory;
import com.sfm.qoentum.service.qoentumf.SondeHistoryService;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"*"}, maxAge = 3600L)
@RestController
@RequestMapping({"/sondeHistory"})
public class SondeHistoryController {
    @Autowired
    private SondeHistoryService sondeHistoryService;

    @RequestMapping(value = {"/"}, method = {RequestMethod.GET})
    List<SondeHistory> getSondeHistory(@RequestParam long sonde_id, @RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start,
                                       @RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end) {
        return this.sondeHistoryService.getSondeHistoryBySondeIdBetweenDates(sonde_id, start, end);
    }
}
