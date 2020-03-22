package de.wevsvirus.facade.impl;

import de.wevsvirus.facade.CallStatisticFacade;
import de.wevsvirus.servlet.controller.data.CallStatisticDTO;
import de.wevsvirus.servlet.controller.data.CallStatisticUpdateDTO;
import org.springframework.stereotype.Component;

@Component
public class DefaultCallStatisticFacade implements CallStatisticFacade {

    private int numberOfCallers = 0;
    private int numberOfCoronaCases = 0;
    private int numberOfCallCenterRedirections = 0;

    @Override
    public void updateCallStatistic(final CallStatisticUpdateDTO callStatisticUpdateDTO) {
        numberOfCallers += 1;
        if (callStatisticUpdateDTO.isCoronaCase()) {
            numberOfCoronaCases += 1;
        }
        if (callStatisticUpdateDTO.isCallCenterRedirection()) {
            numberOfCallCenterRedirections += 1;
        }
    }

    @Override
    public CallStatisticDTO getCallStatistics() {
        final CallStatisticDTO callStatisticDTO = new CallStatisticDTO();
        callStatisticDTO.setNumberOfCallers(numberOfCallers);
        callStatisticDTO.setNumberOfCoronaCases(numberOfCoronaCases);
        callStatisticDTO.setNumberOfCallCenterRedirections(numberOfCallCenterRedirections);
        return callStatisticDTO;
    }
}
