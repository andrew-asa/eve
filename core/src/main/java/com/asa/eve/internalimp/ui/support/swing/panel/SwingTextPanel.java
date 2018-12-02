package com.asa.eve.internalimp.ui.support.swing.panel;

import com.asa.eve.constant.AppConstant;
import com.asa.eve.structure.ui.TextPanel;

import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

/**
 * @author andrew_asa
 * @date 2018/12/2.
 */
public class SwingTextPanel implements TextPanel {

    private JTextPane textPane;

    public SwingTextPanel() {

        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setBackground(Color.decode("#333333"));
        textPane.setForeground(Color.decode("#ffffff"));
        textPane.setBorder(new EmptyBorder(AppConstant.UI.DEFAULT_OUTPUT_PANEL_BORDER_SIZE,
                                           AppConstant.UI.DEFAULT_OUTPUT_PANEL_BORDER_SIZE,
                                           AppConstant.UI.DEFAULT_OUTPUT_PANEL_BORDER_SIZE,
                                           AppConstant.UI.DEFAULT_OUTPUT_PANEL_BORDER_SIZE));
    }

    public JTextPane getTextPane() {

        return textPane;
    }

    public void setTextPane(JTextPane textPane) {

        this.textPane = textPane;
    }

    @Override
    public void setText(String text) {

        textPane.setText(text);
    }

    @Override
    public String getText() {

        return textPane.getText();
    }

    public void select() {

        textPane.setBackground(Color.blue);
    }

    public void unSelect() {

        textPane.setBackground(Color.decode("#333333"));
        textPane.setForeground(Color.decode("#ffffff"));
    }
}
