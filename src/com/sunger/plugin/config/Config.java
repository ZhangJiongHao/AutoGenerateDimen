package com.sunger.plugin.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intellij.ide.util.PropertiesComponent;
import com.sunger.plugin.entity.RowEntity;

import java.util.List;

/**
 * Created by sunger on 16/7/30.
 */
public class Config {

    private static final String DEFAULT_RAW = "[{\"rate\":0.75,\"folderName\":\"values-ldpi\"},{\"rate\":1.0,\"folderName\":\"values-mdpi\"},{\"rate\":1.5,\"folderName\":\"values-hdpi\"},{\"rate\":2.0,\"folderName\":\"values-xhdpi\"},{\"rate\":3.0,\"folderName\":\"values-xxhdpi\"},{\"rate\":4.0,\"folderName\":\"values-xxxhdpi\"}]";


    private Config() {
    }

    private static Config config;

    public static Config getInstant() {
        if (config == null) {
            config = new Config();
            config.setNotRemind(PropertiesComponent.getInstance().getBoolean("notRemind",false));
            String rawString = PropertiesComponent.getInstance().getValue("rowDatas", DEFAULT_RAW);
            List<RowEntity> retList = new Gson().fromJson(rawString,
                    new TypeToken<List<RowEntity>>() {
                    }.getType());
            config.setRowDatas(retList);
        }
        return config;
    }

    public void save() {
        PropertiesComponent.getInstance().setValue("notRemind", notRemind);
        PropertiesComponent.getInstance().setValue("rowDatas", new Gson().toJson(rowDatas));
    }

    public List<RowEntity> getRowDatas() {
        return rowDatas;
    }

    public void setRowDatas(List<RowEntity> rowDatas) {
        this.rowDatas = rowDatas;
    }

    public boolean isNotRemind() {
        return notRemind;
    }

    public void setNotRemind(boolean notRemind) {
        this.notRemind = notRemind;
    }

    private boolean notRemind = true;
    private List<RowEntity> rowDatas;





}
