package com.asa.eve.app.listener.input.control;

import com.asa.eve.structure.app.action.InputControlAction;
import com.asa.log.LoggerFactory;
import com.asa.utils.ComparatorUtils;
import com.asa.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author andrew_asa
 * @date 2018/11/28.
 * 输入框控制动作
 */
public class InputControlActionManager {

    private static final List<InputControlAction> INPUT_CONTROL_ACTION = new ArrayList<>();

    private static InputControlActionManager INSTANCE;

    private InputControlActionManager() {

        init();
    }

    private void init() {

        setDefaultInputAction();
    }

    private void setDefaultInputAction() {

        addInputControlAction(new SelectNextOutputItemAction());
        addInputControlAction(new SelectLastOutputItemAction());
        addInputControlAction(new HitSelectOutputItemAction());
    }

    public static InputControlActionManager getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new InputControlActionManager();
        }
        return INSTANCE;
    }

    public void addInputControlAction(InputControlAction action) {

        ListUtils.putIfAbsent(INPUT_CONTROL_ACTION, action);
    }

    public void removeinputControlAction(InputControlAction action) {

        ListUtils.remove(INPUT_CONTROL_ACTION, action);
    }

    public void inputControlActionTraversing(Consumer<InputControlAction> consumer) {

        INPUT_CONTROL_ACTION.stream().forEach(action -> {
            try {
                consumer.accept(action);
            } catch (Exception e) {
                LoggerFactory.getLogger().error(e.getMessage(), e);
            }
        });
    }

    public InputControlAction findInputControlActionByName(String name) {

        Optional<InputControlAction> optional = INPUT_CONTROL_ACTION
                .stream()
                .filter(action -> ComparatorUtils.equals(action.getActionName(), name))
                .findFirst();
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
