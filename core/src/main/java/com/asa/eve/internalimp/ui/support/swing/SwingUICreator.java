package com.asa.eve.internalimp.ui.support.swing;

import com.asa.eve.internalimp.ui.support.swing.label.SwingLabel;
import com.asa.eve.internalimp.ui.support.swing.panel.SwingTextPanel;
import com.asa.eve.structure.ui.Label;
import com.asa.eve.structure.ui.TextPanel;
import com.asa.eve.structure.ui.UICreator;
import com.asa.eve.structure.ui.Window;

/**
 * @author andrew_asa
 * @date 2018/11/28.
 */
public class SwingUICreator implements UICreator {

    @Override
    public Window createWindow() {

        return new SwingWindow();
    }

    @Override
    public Label createLabel() {

        return new SwingLabel();
    }

    @Override
    public TextPanel createTextPanel() {

        return new SwingTextPanel();
    }
}
