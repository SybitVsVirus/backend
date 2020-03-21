package de.wevsvirus.servlet.controller.data;

public class CallStatisticUpdateDTO {

    private boolean coronaCase;
    private boolean callCenterRedirection;

    public boolean isCoronaCase() {
        return coronaCase;
    }

    public void setCoronaCase(final boolean coronaCase) {
        this.coronaCase = coronaCase;
    }

    public boolean isCallCenterRedirection() {
        return callCenterRedirection;
    }

    public void setCallCenterRedirection(final boolean callCenterRedirection) {
        this.callCenterRedirection = callCenterRedirection;
    }
}
