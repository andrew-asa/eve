package com.asa.eve.internalimp.ui;

import com.asa.eve.app.listener.input.Desire;
import com.asa.eve.app.listener.input.InputActionManager;
import com.asa.eve.app.listener.input.OutputItemDescription;
import com.asa.eve.app.listener.input.OutputItemDescriptionHelper;
import com.asa.eve.structure.app.InputChangeDispatch;
import com.asa.eve.structure.ui.Component;
import com.asa.eve.structure.ui.TextPanel;
import com.asa.eve.structure.ui.Window;
import com.asa.utils.ListUtils;

import java.util.List;

/**
 * @author andrew_asa
 * @date 2018/11/30.
 */
public class DefaultInputChangeDispatch implements InputChangeDispatch {

    private Window window;

    public DefaultInputChangeDispatch(Window window) {

        this.window = window;
    }

    @Override
    public void fireTextChangedEvent(String text) {
        // 清掉主面板让action 进行生成选项
        window.getOutputPanel().resetContent();
        InputActionManager
                .getInstance()
                .getInputAction()
                .stream()
                .filter(action -> !Desire.UNWILLING.equals(action.desire(text)))
                .sorted((action1, action2) -> {
                    // 有意愿处理的放在前面
                    Desire desire1 = action1.desire(text);
                    Desire desire2 = action2.desire(text);
                    if (Desire.WILLING.equals(desire2) && Desire.WHATEVER.equals(desire1)) {
                        return -1;
                    }
                    return 1;
                })
                .forEach(action -> {
                    List<OutputItemDescription> outputItemDescriptions = action.analysis(text);
                    ListUtils.forEach(outputItemDescriptions, item -> {
                        OutputItemDescription itemDescription = item.clone();
                        Component component = action.describe(item);
                        window.getOutputPanel().addSelectItem(component, itemDescription);
                    });
                });
        window.getOutputPanel().selectNextItem();
        window.resize();
    }
}
