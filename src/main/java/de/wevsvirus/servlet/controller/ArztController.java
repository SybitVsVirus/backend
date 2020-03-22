package de.wevsvirus.servlet.controller;

import de.wevsvirus.facade.impl.ArztFacade;
import de.wevsvirus.servlet.controller.data.JsonResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/arzt")
public class ArztController {

    @Resource
    private ArztFacade arztFacade;

    @ApiOperation("Find all doctors in the area of the given PLZ")
    @GetMapping("/{plz}")
    public JsonResponse findArztByPLZ(final @PathVariable String plz) {
        return JsonResponse.withData(arztFacade.findArztByPLZ(plz));
    }
}
