package com.sunger.plugin.entity;

/**
 * Created by sunger on 16/7/23.
 */
public class RowEntity {
    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    private String folderName;
    private double rate;
}
