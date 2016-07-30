package com.sunger.plugin.utils;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

/**
 * Created by sunger on 16/7/17.
 */
public class VFSFileUtils {
    public static void saveFile(Project project, VirtualFile virtualFile, @NotNull String content) {
        try {
            virtualFile.setBinaryContent(content.getBytes());
            FileEditorManager.getInstance(project).openFile(virtualFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static VirtualFile createVirtualFile(@NotNull File file) {
        VirtualFileManager.getInstance().syncRefresh();
        VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByIoFile(file);
        return virtualFile;
    }
}
