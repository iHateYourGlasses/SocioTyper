package ru.npotpz.sociotyper.models;

/**
 * Created by Art on 26.01.2017.
 */

public class ReininTypeModel {
    public String typeOne;
    public String typeTwo;
    public Boolean isSwitchActive;
    public Boolean isFirstTypeActive;

    public ReininTypeModel(String typeOne, String typeTwo, Boolean isSwitchActive, Boolean isFirstTypeActive) {
        this.typeOne = typeOne;
        this.typeTwo = typeTwo;
        this.isSwitchActive = isSwitchActive;
        this.isFirstTypeActive = isFirstTypeActive;
    }

}
