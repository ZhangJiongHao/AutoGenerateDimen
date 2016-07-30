package com.sunger.plugin.utils;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Created by sunger on 16/7/23.
 */
public class UITools {

    public static void showViewInCenter(@NotNull Window window) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int) (toolkit.getScreenSize().getWidth() - window.getWidth()) / 2;
        int y = (int) (toolkit.getScreenSize().getHeight() - window.getHeight()) / 2;
        window.setLocation(x, y);
    }
}
