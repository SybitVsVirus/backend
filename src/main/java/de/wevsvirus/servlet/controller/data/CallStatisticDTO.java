package de.wevsvirus.servlet.controller.data;

public class CallStatisticDTO {

    private int numberOfCallers;
    private int numberOfCoronaCases;
    private int numberOfCallCenterRedirections;

    public int getNumberOfCallers() {
        return numberOfCallers;
    }

    public void setNumberOfCallers(final int numberOfCallers) {
        this.numberOfCallers = numberOfCallers;
    }

    public int getNumberOfCoronaCases() {
        return numberOfCoronaCases;
    }

    public void setNumberOfCoronaCases(final int numberOfCoronaCases) {
        this.numberOfCoronaCases = numberOfCoronaCases;
    }

    public int getNumberOfCallCenterRedirections() {
        return numberOfCallCenterRedirections;
    }

    public void setNumberOfCallCenterRedirections(final int numberOfCallCenterRedirections) {
        this.numberOfCallCenterRedirections = numberOfCallCenterRedirections;
    }
}
