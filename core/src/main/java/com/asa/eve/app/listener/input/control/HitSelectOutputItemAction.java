package com.asa.eve.app.listener.input.control;

import com.asa.eve.app.history.HistoryCentre;
import com.asa.eve.app.listener.input.InputActionManager;
import com.asa.eve.app.listener.input.OutputItemDescription;
import com.asa.eve.constant.AppConstant;
import com.asa.eve.internalimp.ui.UIManager;
import com.asa.eve.structure.app.action.InputAction;
import com.asa.log.LoggerFactory;
import com.asa.utils.StringUtils;

/**
 * @author andrew_asa
 * @date 2018/12/2.
 * 执行当前选中选项
 */
public class HitSelectOutputItemAction extends AbstractInputControlAction {

    @Override
    public boolean reply(String keyShot) {

        return StringUtils.equalsIgnoreCase(keyShot, "Enter");
    }

    @Override
    public void apply(String keyShot) {

        OutputItemDescription outputItemDescription = UIManager.getInstance().getCurrentWindow().getOutputPanel().getSelectItem();
        LoggerFactory.getLogger().debug("hit select item {}", outputItemDescription);
        InputAction action = InputActionManager.getInstance().findActionByName(outputItemDescription);
        if (action != null) {
            action.apply(outputItemDescription);
            // 保存历史记录
            HistoryCentre.getInstance().addHistory(outputItemDescription);
        }
    }

    @Override
    public String getActionName() {

        return AppConstant.ACTION.HIT_SELECT_OUTPUT_ITEM_ACTION_NAME;
    }
}
