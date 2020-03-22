package de.wevsvirus.servlet.controller;

import de.wevsvirus.facade.ArztFacade;
import de.wevsvirus.model.ArztModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
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

    @ApiOperation("Find all doctors in the area of the given PLZ")
    @GetMapping(value="/byPlz/{plz}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArztModel> findArztByPLZ(final @PathVariable String plz) {
        return arztFacade.findArztByPLZ(plz);
    }

    @ApiOperation("Find all doctors by phone Number")
    @GetMapping(value = "/byPhone/{phoneNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArztModel> findArztByPhoneNumber(final @PathVariable String phoneNumber) {
        return arztFacade.findByPhoneNumber(phoneNumber);
    }


}
