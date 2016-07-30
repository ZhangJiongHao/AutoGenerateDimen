package com.sunger.plugin.utils;

import com.intellij.openapi.actionSystem.AnActionEvent;
import org.w3c.dom.Document;

/**
 * Created by sunger on 16/7/17.
 */
public class DimenUtils {

    public static boolean isDimenResourcesFile(AnActionEvent anActionEvent) {
        Document document = DomUtils.createDocument(anActionEvent);
        if (document == null)
            return false;
        boolean isResources = document.getElementsByTagName("resources").getLength() == 1;
        boolean isDimen = document.getElementsByTagName("dimen").getLength() > 0;
        return isResources && isDimen;
    }
}
