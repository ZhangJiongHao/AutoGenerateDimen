package com.sunger.plugin.entity;


/**
 * Created by sunger on 2016/7/11.
 */
public class DimenNodeEntity {
    private String value;

    public ValyeType getType() {
        return type;
    }

    public void setType(ValyeType type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {

        this.value = value;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    private double number;
    private String unit;
    private ValyeType type;


    public enum ValyeType {
        common, reference
    }

}
