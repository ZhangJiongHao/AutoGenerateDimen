package com.sunger.plugin.ui.swings;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.sunger.plugin.config.Config;
import com.sunger.plugin.entity.RowEntity;
import com.sunger.plugin.generate.Generate;
import com.sunger.plugin.ui.control.ButtonEditor;
import com.sunger.plugin.ui.control.ButtonRenderer;
import com.sunger.plugin.ui.tablemodel.MainDialogTableModel;
import com.sunger.plugin.utils.UITools;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.List;


public class MainDialog extends JFrame {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonAdd;
    private JButton buttonCancel;
    public JPanel filedPanel;
    private JScrollPane sp;
    private JXTable mJxTable;
    public Project project;
    private VirtualFile selectedFile;
    private Config config;
    private MainDialogTableModel mainDialogTableModel;


    public MainDialog(Project project, VirtualFile selectedFile) {
        this.project = project;
        this.selectedFile = selectedFile;
        setTitle("Dimen Config");
        setContentPane(contentPane);
        config = Config.getInstant();
        initView();
        initListeners();
    }

    private void initView() {
        initTable();
        setSize(500, 350);
        UITools.showViewInCenter(this);
    }

    private void initTable() {
        List<RowEntity> itemData = config.getRowDatas();
        mainDialogTableModel = new MainDialogTableModel(itemData);
        mJxTable = new JXTable(mainDialogTableModel);
        mJxTable.setRowHeight(30);
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        mJxTable.setDefaultRenderer(Object.class, defaultTableCellRenderer);
        mJxTable.getColumn(3).setCellEditor(new ButtonEditor(new JTextField(), mJxTable));
        mJxTable.getColumn(3).setCellRenderer(new ButtonRenderer());
        sp.setViewportView(mJxTable);

    }

    private void gen() {
        List<RowEntity> rowdDatas = mainDialogTableModel.getAllRows();
        config.setRowDatas(rowdDatas);
        config.save();
        new Generate().generate(project, selectedFile, rowdDatas);
        showSuccess();
        dispose();
    }

    public void showSuccess() {
        Messages.showMessageDialog(
                "Generate Success",

                "",
                Messages.getInformationIcon()

        );

    }

    private void initListeners() {
        buttonOK.addActionListener(e -> {
            if (config.isNotRemind()) {
                gen();
            } else {
                showTipDialog();
            }
        });
        buttonAdd.addActionListener(e -> {
            showRowDialog();
        });
        buttonCancel.addActionListener(e -> {
            dispose();
        });

    }

    private void showTipDialog() {
        TipDialog dialog = new TipDialog(() -> gen());
        dialog.pack();
        UITools.showViewInCenter(dialog);
        dialog.setVisible(true);
    }

    private void addTableRow(RowEntity entity) {
        mainDialogTableModel.addRow(entity);
    }

    private void showRowDialog() {
        AddValueItemDialog dialog = new AddValueItemDialog(entity -> addTableRow(entity));
        dialog.pack();
        UITools.showViewInCenter(dialog);
        dialog.setVisible(true);
    }


}
