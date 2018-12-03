package com.asa.eve.internalimp.ui.support.swing.panel;

import com.asa.eve.structure.ui.Icon;
import com.asa.eve.structure.ui.IconTextPanel;
import com.asa.eve.utils.ImageUtils;
import com.asa.eve.utils.SwingUtils;
import com.asa.log.LoggerFactory;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

/**
 * @author andrew_asa
 * @date 2018/12/3.
 */
public class SwingIconTextPanel implements IconTextPanel {

    private JPanel panel;

    private JPanel container;

    private JLabel iconLabel;

    private JPanel middlePanel;

    private JTextPane content;

    private JTextPane description;

    private JLabel numLabel;

    private static final int ITEM_HEIGHT = 58;

    private static final int TEXT_LEN = 60;

    private Icon icon;

    public SwingIconTextPanel() {

        init();
    }

    private void init() {

        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());
        this.panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        this.container = SwingUtils.createPanelWithXBoxLayout();
        this.container.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 5));
        this.middlePanel = SwingUtils.createPanelWithYBoxLayout();
        this.middlePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        iconLabel = new JLabel();
        this.container.add(iconLabel);
        this.container.add(middlePanel);
        this.panel.add(container);
        this.panel.setPreferredSize(new Dimension(580, ITEM_HEIGHT));
        initContent();
        initDescription();
        initNum();
    }

    @Override
    public void setText(String text) {

        content.setText(text);
    }

    @Override
    public String getText() {

        return content.getText();
    }

    private void initContent() {

        this.content = new JTextPane();
        this.content.setEditable(false);
        this.middlePanel.add(this.content);
    }

    private void initNum() {

        this.numLabel = new JLabel("text");
        this.container.add(this.numLabel);
    }

    private void initDescription() {

        this.description = new JTextPane();
        this.description.setEditable(false);
        this.middlePanel.add(this.description);
    }


    public JPanel getPanel() {

        return panel;
    }

    public void setPanel(JPanel panel) {

        this.panel = panel;
    }

    @Override
    public void setIcon(Icon icon) {

        this.icon = icon;
        try {
            iconLabel.setIcon(ImageUtils.scale(icon.getInputStream(), ITEM_HEIGHT, ITEM_HEIGHT));
        } catch (IOException e) {
            LoggerFactory.getLogger().error(e.getMessage(), e);
        }
    }

    @Override
    public Icon getIcon() {

        return icon;
    }
}
