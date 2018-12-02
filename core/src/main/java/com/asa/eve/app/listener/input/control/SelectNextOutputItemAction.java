package com.asa.eve.app.listener.input.control;

import com.asa.eve.internalimp.ui.UIManager;
import com.asa.log.LoggerFactory;
import com.asa.utils.StringUtils;

/**
 * @author andrew_asa
 * @date 2018/12/2.
 * 选择下一个输出选项
 */
public class SelectNextOutputItemAction extends AbstractInputControlAction {

    @Override
    public boolean reply(String keyShot) {

        return StringUtils.equalsIgnoreCase(keyShot, "Tab")
                || StringUtils.equalsIgnoreCase(keyShot, "Down");
    }

    @Override
    public void apply(String keyShot) {

        UIManager.getInstance().getCurrentWindow().getOutputPanel().selectNextItem();
        LoggerFactory.getLogger().debug("select next item");
    }

    @Override
    public String getActionName() {

        return "selectOutputItem";
    }
}
