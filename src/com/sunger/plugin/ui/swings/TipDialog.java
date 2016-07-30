package com.sunger.plugin.ui.swings;

import com.sunger.plugin.config.Config;

import javax.swing.*;
import java.awt.event.*;

public class TipDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox checkBox;
    private CallBack callBack;

    public TipDialog(CallBack callBack) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.callBack=callBack;
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

    boolean isnotRemind= checkBox.isSelected();
        Config.getInstant().setNotRemind(isnotRemind);
        callBack.ok();
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public interface  CallBack{

        void ok();

    }
}
