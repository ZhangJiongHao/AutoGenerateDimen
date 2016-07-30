package com.sunger.plugin.generate;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.sunger.plugin.entity.RowEntity;
import com.sunger.plugin.entity.DimenNodeEntity;
import com.sunger.plugin.utils.DomUtils;
import com.sunger.plugin.utils.FileUtils;
import com.sunger.plugin.utils.PatternUtils;
import org.w3c.dom.*;

import java.io.File;
import java.util.List;


/**
 * Created by sunger on 16/7/28.
 */
public class Generate {

    private Application application = ApplicationManager.getApplication();

    private DimenNodeEntity createValueEntity(String value) {
        DimenNodeEntity entity = new DimenNodeEntity();
        if (PatternUtils.isReference(value)) {
            entity.setValue(value);
            entity.setType(DimenNodeEntity.ValyeType.reference);
            return entity;
        } else {
            entity.setType(DimenNodeEntity.ValyeType.common);
            entity.setNumber(PatternUtils.getNumber(value));
            entity.setUnit(PatternUtils.getUnit(value));
        }
        return entity;
    }

    private String createNewValueEntity(DimenNodeEntity nodeValue, double rate) {
        String valueText ;
        if (nodeValue.getType() == DimenNodeEntity.ValyeType.reference) {
            valueText = nodeValue.getValue();
        } else {
            double newNumber = Double.parseDouble(String.format("%.2f", nodeValue.getNumber() / rate));
            valueText = newNumber + nodeValue.getUnit();
        }
        return valueText;
    }

    private String createNewDomString(String xml, double rate) {
        Document document = DomUtils.createDocument(xml);
        NodeList nodeList = document.getElementsByTagName("dimen");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element childNode = (Element) nodeList.item(i);
            Node firstChildNode = childNode.getFirstChild();
            String nodeValue = firstChildNode.getNodeValue();
            childNode.removeChild(firstChildNode);
            DimenNodeEntity valueEntity = createValueEntity(nodeValue);
            String newNodeValue = createNewValueEntity(valueEntity, rate);
            Text textNode = document.createTextNode(newNodeValue);
            childNode.appendChild(textNode);
        }
        return DomUtils.doc2FormatString(document);
    }

    public void generate(Project mProject, VirtualFile selectedFile, List<RowEntity> dataList) {
        File filePath = new File(selectedFile.getCanonicalPath());
        String data = DomUtils.getDomString(filePath);
        for (RowEntity entity : dataList) {
            String newDom = createNewDomString(data, entity.getRate());
            String path = filePath.getParentFile().getParentFile().getPath() + "/" + entity.getFolderName() + "/" + filePath.getName();
            application.runWriteAction(() -> {
                File file = FileUtils.createFile(path);
                FileUtils.writeFile(file, newDom);
            });
        }
    }

//    private void read(Project mProject, File file, String newDom) {
//        application.runReadAction(() -> {
//            VirtualFile newVirtualFile = VFSFileUtils.createVirtualFile(file);
//            write(mProject, newVirtualFile, newDom);
//        });
//    }
//
//    private void write(Project mProject, VirtualFile virtualFile, String data) {
//        application.runWriteAction(() -> {
//            VFSFileUtils.saveFile(mProject, virtualFile, data);
//        });
//    }


}
