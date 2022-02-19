package com.sfm.qoentum.controller.qoentumf;

import com.sfm.qoentum.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.qoentumf.Sonde;
import com.sfm.qoentum.service.qoentumf.SondeService;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/sonde")
public class SondeController {

    @Autowired
    private SondeService sondeService;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Sonde saveSonde(@RequestBody Sonde sonde) {
        return sondeService.save(sonde);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public EntityPage<Sonde> listSonde(
            @RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
            @RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
            @RequestParam(name = "recherche", defaultValue = "", required = false) String recherche) {

        Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("id").ascending());
        return sondeService.findByEmplacementNomContainingOrFournisseurAccesNomContaining(recherche, pageable);
    }


    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public Sonde getById(@RequestParam(name = "id") Long id) {
        return sondeService.findById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteById(@RequestParam(name = "id") Long id) {
        sondeService.delete(id);
    }

}
