package com.asa.eve.internalimp.ui.support.swing;

import com.asa.eve.app.listener.input.OutputItemDescription;
import com.asa.eve.app.listener.input.control.InputControlActionManager;
import com.asa.eve.constant.AppConstant;
import com.asa.eve.structure.app.action.InputControlAction;
import com.asa.eve.structure.app.action.Model;
import com.asa.eve.structure.ui.Component;
import com.asa.eve.structure.ui.OutputPanel;
import com.asa.log.LoggerFactory;
import com.asa.utils.ListUtils;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author andrew_asa
 * @date 2018/11/26.
 * 内容面板
 */
public class SwingOutputPanel implements OutputPanel {

    private JPanel panel;

    private SwingInputPanel inputPanel;

    private SwingWindow window;

    private List<SwingComponent> itemComponents;

    private List<OutputItemDescription> outputItemDescriptions;

    private int currentIndex;

    private static final int DOUBLUE_CLICK_COUNT = 2;

    public SwingOutputPanel(SwingWindow window) {

        this.window = window;
        init();
    }

    private void init() {

        this.panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(panel);

        //window.getFrame().getContentPane().add(scrollPane);
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        panel.setLayout(boxLayout);

        resetIndex();
    }

    private void resetIndex() {

        currentIndex = -1;
        itemComponents = new ArrayList<>();
        outputItemDescriptions = new ArrayList<>();
    }


    public JPanel getPanel() {

        return panel;
    }

    public JComponent getComponent() {
        //添加到带有滚动条的panel
        JScrollPane scr = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scr.setBackground(Color.decode("#616161"));
        scr.setForeground(Color.decode("#616161"));
        scr.setBorder(BorderFactory.createLineBorder(Color.decode("#616161"), 0));
        return scr;
    }

    public void setPanel(JPanel panel) {

        this.panel = panel;
    }

    @Override
    public void addSelectItem(Component component, OutputItemDescription outputItemDescription) {

        if (component == null) {
            return;
        }
        if (component instanceof SwingComponent) {
            int len = ListUtils.length(itemComponents);
            SwingComponent swingComponent = (SwingComponent) component;
            swingComponent.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {

                    unSelectLast();
                    currentIndex = len;
                    selectCurrent();
                    if (e.getClickCount() == DOUBLUE_CLICK_COUNT) {
                        InputControlAction action = InputControlActionManager.getInstance().findInputControlActionByName(AppConstant.ACTION.HIT_SELECT_OUTPUT_ITEM_ACTION_NAME);
                        if (action != null) {
                            action.apply("__output_model_double_click__");
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                    window.setModel(Model.OUTPUT);
                    LoggerFactory.getLogger().debug("mouse entry out panel");
                }

                @Override
                public void mouseExited(MouseEvent e) {

                    window.setModel(Model.GLOBAL);
                }
            });
            panel.add(swingComponent.getComponent());
            outputItemDescriptions.add(outputItemDescription);
            itemComponents.add(swingComponent);
        }
    }

    @Override
    public void resetContent() {

        resetIndex();
        panel.removeAll();
    }

    @Override
    public OutputItemDescription selectLastItem() {

        int len = ListUtils.length(itemComponents);
        if (len > 0) {
            unSelectLast();
            if (currentIndex == 0) {
                currentIndex = len - 1;
            } else {
                currentIndex--;
            }
            selectCurrent();
        }
        return getSelectItem();
    }

    private void unSelectLast() {

        int len = ListUtils.length(itemComponents);
        if (len > 0) {
            if (currentIndex >= 0) {
                int lastIndex = currentIndex % len;
                SwingComponent component = itemComponents.get(lastIndex);
                component.unSelect();
            }
        }
    }

    private void selectCurrent() {

        int len = ListUtils.length(itemComponents);
        if (len > 0 && currentIndex >= 0) {
            SwingComponent component = itemComponents.get(currentIndex);
            component.select();
        }
    }

    @Override
    public OutputItemDescription selectNextItem() {

        int len = ListUtils.length(itemComponents);
        if (len > 0) {
            unSelectLast();
            currentIndex++;
            currentIndex = currentIndex % len;
            selectCurrent();
        }
        return getSelectItem();
    }

    @Override
    public OutputItemDescription getSelectItem() {

        int len = ListUtils.length(itemComponents);
        if (len > 0) {
            OutputItemDescription outputItemDescription = outputItemDescriptions.get(currentIndex);
            return outputItemDescription;
        }
        return null;
    }
}
