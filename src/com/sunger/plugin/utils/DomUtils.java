package com.sunger.plugin.utils;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.vfs.VirtualFile;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Created by zhangqiangze on 2016/7/11.
 */
public class DomUtils {


    public static Document createDocument(@NotNull String domText) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            InputStream inputStream = IOUtils.convertStringTotStream(domText);
            return dbBuilder.parse(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Document createDocument(AnActionEvent event) {
        VirtualFile selectedFile = event.getData(PlatformDataKeys.VIRTUAL_FILE);
        File filePath = new File(selectedFile.getCanonicalPath());
        if (filePath.isDirectory())
            return null;
        return createDocument(getDomString(filePath));
    }


    public static String doc2FormatString(Document doc) {
        StringWriter stringWriter = null;
        try {
            stringWriter = new StringWriter();
            if (doc != null) {
                OutputFormat format = new OutputFormat(doc, "UTF-8", true);
                format.setIndenting(true);//设置是否缩进，默认为true
                format.setIndent(4);//设置缩进字符数
                format.setPreserveSpace(false);//设置是否保持原来的格式,默认为 false
                XMLSerializer serializer = new XMLSerializer(stringWriter, format);
                serializer.asDOMSerializer();
                serializer.serialize(doc);
                return stringWriter.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stringWriter != null) {
                try {
                    stringWriter.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }


    public static String getDomString(File file) {
         if (file.exists()) {
            String fileText = FileUtils.readFileToString(file);
            return fileText;
        }
        return "";
    }
}
