package de.wevsvirus.facade;

import de.wevsvirus.servlet.controller.data.CallStatisticDTO;
import de.wevsvirus.servlet.controller.data.CallStatisticUpdateDTO;

public interface CallStatisticFacade {

    void updateCallStatistic(CallStatisticUpdateDTO callStatisticUpdateDTO);

    CallStatisticDTO getCallStatistics();
}
