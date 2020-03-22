package de.wevsvirus.servlet.controller;

import de.wevsvirus.facade.CallStatisticFacade;
import de.wevsvirus.servlet.controller.data.CallStatisticUpdateDTO;
import de.wevsvirus.servlet.controller.data.JsonResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/call-statistics")
public class CallStatisticsController {

    private final CallStatisticFacade callStatisticFacade;

    @Value("${CALL_STATS_API_TOKEN}")
    private String statsAPIToken;

    public CallStatisticsController(final CallStatisticFacade callStatisticFacade) {
        this.callStatisticFacade = callStatisticFacade;
    }

    @ApiOperation(value = "Updates call statistics.")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCallStatistics(@RequestBody final CallStatisticUpdateDTO callStatisticUpdateDTO, @RequestParam(name = "CALL_STATS_API_TOKEN") final String token) {
        if (token.equals(statsAPIToken)) {
            callStatisticFacade.updateCallStatistic(callStatisticUpdateDTO);
        } else {
            throw new SecurityException("Call statistics api token not valid.");
        }
    }

    @ApiOperation(value = "Returns call statistics.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse getCallStatistics() {
        return JsonResponse.withData(callStatisticFacade.getCallStatistics());
    }
}
