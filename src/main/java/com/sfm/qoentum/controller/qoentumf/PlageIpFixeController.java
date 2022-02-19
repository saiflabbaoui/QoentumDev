package com.sfm.qoentum.controller.qoentumf;


import com.sfm.qoentum.model.qoentumf.PlageIpFixe;
import com.sfm.qoentum.model.qoentumf.TechnologieFixe;
import com.sfm.qoentum.service.qoentumf.PlageIpFixeService;
import com.sfm.qoentum.service.qoentumf.TechnologieFixeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 3600L)
@RestController
@RequestMapping({"/plageIpFixe"})
public class PlageIpFixeController {


    @Autowired
    private PlageIpFixeService plageIpFixeService;




    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PlageIpFixe savePlageIpFixe(@RequestBody PlageIpFixe plageIpFixe) {
        return plageIpFixeService.save(plageIpFixe);
    }

    @RequestMapping(value = "/getPlageIpParFaiTechnologie", method = RequestMethod.GET)
    public List<PlageIpFixe> getTechnologiesParFai(@RequestParam(name = "id") long id){
        return plageIpFixeService.getPlageIpParFaiTechnologie(id);
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteById(@RequestParam(name = "id") Long id) {
        plageIpFixeService.deletePlageIp(id);
    }
}
