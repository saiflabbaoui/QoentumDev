package com.sfm.qoentum.controller.qoentumf;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.TechnologiesParFai;
import com.sfm.qoentum.model.qoentumf.FaiTechnologieFixePlageIp;
import com.sfm.qoentum.model.qoentumf.TechnologieFixe;
import com.sfm.qoentum.service.qoentumf.TechnologieFixeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 3600L)
@RestController
@RequestMapping({"/technologieFixe"})
public class TechnologieFixeController {

    @Autowired
    private TechnologieFixeService technologieFixeService;




    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public long saveTechnologieFixe(@RequestBody TechnologieFixe technologieFixe) {
        return technologieFixeService.save(technologieFixe);
    }

    @RequestMapping(value="/saveTechnologies", method = RequestMethod.POST)
    public void saveTechnologies(@RequestBody TechnologiesParFai technologiesParFai){
        technologieFixeService.addTechnologiesToFai(technologiesParFai.getId(), technologiesParFai.getTechnologies());
    }

    @RequestMapping(value = "/getTechnologiesParFai", method = RequestMethod.GET)
    public List<TechnologieFixe> getTechnologiesParFai(@RequestParam(name = "id") long id){
        return technologieFixeService.getTechnologiesParFai(id);
    }

    @RequestMapping(value = "/getFaiTechnologiePlageIp", method = RequestMethod.GET)
    public FaiTechnologieFixePlageIp getFaiTechnologiePlageIpParFaiAndTechnologie(@RequestParam(name = "idF") long idF, @RequestParam(name = "idT") long idT){
        return technologieFixeService.getFaiTechnologieFixePlageIpParFaiAndTechnologie(idF, idT);
    }

    @RequestMapping(value = "/getFaiTechnologiesPlageIpParFai", method = RequestMethod.GET)
    public List<FaiTechnologieFixePlageIp> getFaiTechnologiesPlageIpParFai(@RequestParam(name = "id") long id){
        return technologieFixeService.getFaiTechnologiesPlageIpParFai(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public EntityPage<TechnologieFixe> listTechnologieFixe(
            @RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
            @RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
            @RequestParam(name = "recherche", defaultValue = "", required = false) String recherche) {

        Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("id").ascending());
        return technologieFixeService.findByTechnologieContaining(recherche, pageable);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteById(@RequestParam(name = "id") Long id) {
        technologieFixeService.delete(id);
    }
}
