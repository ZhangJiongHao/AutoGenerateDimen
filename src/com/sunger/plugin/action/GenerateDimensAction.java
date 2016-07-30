package com.sunger.plugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.sunger.plugin.ui.swings.MainDialog;
import com.sunger.plugin.utils.DimenUtils;

import java.io.File;

/**
 * Created by sunger on 16/7/9.
 */
public class GenerateDimensAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        Project mProject = getEventProject(event);
        VirtualFile selectedFile = event.getData(PlatformDataKeys.VIRTUAL_FILE);
        File filePath = new File(selectedFile.getCanonicalPath());
        MainDialog frame = new MainDialog(mProject, selectedFile);
        frame.setVisible(true);

    }

    @Override
    public void update(AnActionEvent e) {
        boolean isPluginShow = DimenUtils.isDimenResourcesFile(e);
        Presentation presentation = e.getPresentation();
        presentation.setVisible(isPluginShow);
    }

}
