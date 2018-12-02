package com.asa.eve.internalimp.ui.support.swing.label;

import com.asa.eve.structure.ui.Label;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

/**
 * @author andrew_asa
 * @date 2018/11/29.
 */
public class SwingLabel implements Label {

    private JLabel label;

    public SwingLabel() {

        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setBackground(Color.GRAY);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createLineBorder(Color.red));
    }

    public JLabel getLabel() {

        return label;
    }

    public void setLabel(JLabel label) {

        this.label = label;
    }

    @Override
    public void setText(String text) {

        label.setText(text);
    }

    @Override
    public String getText() {

        return label.getText();
    }
}
