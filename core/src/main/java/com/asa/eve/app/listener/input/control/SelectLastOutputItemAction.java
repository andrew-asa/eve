package com.asa.eve.app.listener.input.control;

import com.asa.eve.internalimp.ui.UIManager;
import com.asa.log.LoggerFactory;
import com.asa.utils.ComparatorUtils;
import com.asa.utils.StringUtils;

/**
 * @author andrew_asa
 * @date 2018/12/2.
 */
public class SelectLastOutputItemAction extends AbstractInputControlAction {

    @Override
    public boolean reply(String keyShot) {

        return StringUtils.equalsIgnoreCase(keyShot, "UP");
    }

    @Override
    public void apply(String keyShot) {

        UIManager.getInstance().getCurrentWindow().getOutputPanel().selectLastItem();
        LoggerFactory.getLogger().debug("select last item");
    }

    @Override
    public String getActionName() {

        return "selectLastOutputItem";
    }
}
