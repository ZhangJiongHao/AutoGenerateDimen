package com.sunger.plugin.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by zhangqiangze on 2016/7/11.
 */
public class IOUtils {

    public  static InputStream convertStringTotStream(String str){
        return    new ByteArrayInputStream(str.getBytes());
    }
}
