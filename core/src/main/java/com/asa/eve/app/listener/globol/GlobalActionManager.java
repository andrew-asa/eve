package com.asa.eve.app.listener.globol;

import com.asa.eve.app.listener.input.control.InputControlActionManager;
import com.asa.eve.constant.AppConstant;
import com.asa.eve.internalimp.ui.UIManager;
import com.asa.eve.structure.app.action.InputControlAction;
import com.asa.eve.structure.app.action.Model;
import com.asa.utils.ComparatorUtils;
import com.asa.utils.ListUtils;
import com.asa.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author andrew_asa
 * @date 2018/11/28.
 */
public class GlobalActionManager {

    private static final List<GlobalAction> GLOBAL_ACTION = new ArrayList<>();

    private static GlobalActionManager INSTANCE;

    private GlobalActionManager() {

        init();
    }

    private void init() {

        setDefaultGlobalAction();
    }

    private void setDefaultGlobalAction() {

        addGlobalAction(new ShowWindow());
        addGlobalAction(new HideWindow());
        addGlobalAction(new SelectOutputItem());
    }

    public static GlobalActionManager getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new GlobalActionManager();
        }
        return INSTANCE;
    }

    public void addGlobalAction(GlobalAction action) {

        ListUtils.putIfAbsent(GLOBAL_ACTION, action);
    }

    public void removeGlobalAction(GlobalAction action) {

        ListUtils.remove(GLOBAL_ACTION, action);
    }

    public void globalActionTraversing(Consumer<GlobalAction> consumer) {

        GLOBAL_ACTION.stream().forEach(action -> {
            consumer.accept(action);
        });
    }

    class ShowWindow extends GlobalAction {

        @Override
        public String getActionName() {

            return "ShowWindow";
        }

        @Override
        public boolean accept(String pressKey) {

            return ComparatorUtils.equals(pressKey, "Meta+Space");
        }

        @Override
        public void reply(String key) {

            UIManager.getInstance().show();
        }
    }

    class HideWindow extends GlobalAction {

        @Override
        public String getActionName() {

            return "HideWindow";
        }

        @Override
        public boolean accept(String pressKey) {

            return ComparatorUtils.equals(pressKey, "Escape");
        }

        @Override
        public void reply(String key) {

            UIManager.getInstance().hide();
        }
    }

    class SelectOutputItem extends GlobalAction {

        @Override
        public String getActionName() {

            return "SelectOutputItem";
        }

        @Override
        public boolean accept(String pressKey) {

            Model model = UIManager.getInstance().getCurrentWindow().getModel();
            if (Model.OUTPUT.equals(model)) {
                return StringUtils.equalsIgnoreCase(pressKey, "Enter");
            }
            return false;
        }

        @Override
        public void reply(String key) {

            InputControlAction action = InputControlActionManager.getInstance().findInputControlActionByName(AppConstant.ACTION.HIT_SELECT_OUTPUT_ITEM_ACTION_NAME);
            if (action != null) {
                action.apply("__global__click_entry__");
            }
        }
    }
}
