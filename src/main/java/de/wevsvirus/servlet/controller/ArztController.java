package de.wevsvirus.servlet.controller;

import de.wevsvirus.facades.ArztFacade;
import de.wevsvirus.model.ArztModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/arzt")
public class ArztController {

    @Resource
    private ArztFacade arztFacade;

    @GetMapping("/{plz}")
    public List<ArztModel> findArztByPLZ(final @PathVariable String plz) {
        return arztFacade.findArztByPLZ(plz);
    }
}
