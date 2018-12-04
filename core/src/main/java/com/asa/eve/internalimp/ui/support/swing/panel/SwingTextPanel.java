package com.asa.eve.internalimp.ui.support.swing.panel;

import com.asa.eve.constant.AppConstant;
import com.asa.eve.internalimp.ui.support.swing.SwingComponent;
import com.asa.eve.structure.ui.TextPanel;

import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseListener;

/**
 * @author andrew_asa
 * @date 2018/12/2.
 */
public class SwingTextPanel implements TextPanel, SwingComponent {

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

    @Override
    public void select() {

        textPane.setBackground(Color.blue);
    }

    @Override
    public void unSelect() {

        textPane.setBackground(Color.decode("#333333"));
        textPane.setForeground(Color.decode("#ffffff"));
    }

    @Override
    public void addMouseListener(MouseListener l) {

        textPane.addMouseListener(l);
    }

    @Override
    public Component getComponent() {

        return textPane;
    }
}
