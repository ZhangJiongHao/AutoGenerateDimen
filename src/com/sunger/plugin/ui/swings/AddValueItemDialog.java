package com.sunger.plugin.ui.swings;

import com.sunger.plugin.entity.RowEntity;
import com.sunger.plugin.ui.control.DoubleDocument;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddValueItemDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameTextField;
    private JTextField rateTextField;
    private CallBack callBack;

    public AddValueItemDialog(final CallBack callBack) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.callBack = callBack;
        rateTextField.setDocument(new DoubleDocument());
        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        RowEntity entity = new RowEntity();
        entity.setFolderName(nameTextField.getText());
        entity.setRate(Double.parseDouble(rateTextField.getText()));
        callBack.add(entity);
        dispose();
    }

    private void onCancel() {
        dispose();
    }


    public interface CallBack {
        void add(RowEntity entity);
    }

}
