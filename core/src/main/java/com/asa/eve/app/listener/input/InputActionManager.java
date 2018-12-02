package com.asa.eve.app.listener.input;

import com.asa.eve.structure.app.action.InputAction;
import com.asa.eve.structure.app.action.InputControlAction;
import com.asa.utils.ComparatorUtils;
import com.asa.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author andrew_asa
 * @date 2018/11/28.
 * 输入框响应动作
 */
public class InputActionManager {

    private static final List<InputAction> INPUT_ACTION = new ArrayList<>();

    private static InputActionManager INSTANCE;

    private InputActionManager() {

        init();
    }

    private void init() {

        setDefaultInputAction();
    }

    private void setDefaultInputAction() {

        INPUT_ACTION.add(new echo());
    }

    public static InputActionManager getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new InputActionManager();
        }
        return INSTANCE;
    }

    public void addInputAction(InputAction action) {

        ListUtils.putIfAbsent(INPUT_ACTION, action);
    }

    public void removeInputAction(InputAction action) {

        ListUtils.remove(INPUT_ACTION, action);
    }

    public void globalActionTraversing(Consumer<InputAction> consumer) {

        INPUT_ACTION.stream().forEach(action -> {
            consumer.accept(action);
        });
    }

    public List<InputAction> getInputAction() {

        return INPUT_ACTION;
    }

    class echo extends AbstractInputAction {

        @Override
        public Desire desire(String key) {

            return Desire.WILLING;
        }

        @Override
        public String getActionName() {

            return "echo";
        }
    }

    public InputAction findActionByName(OutputItemDescription description) {

        Optional<InputAction> optional = INPUT_ACTION.stream().filter(action -> ComparatorUtils.equals(action.getActionName(), OutputItemDescriptionHelper.getConnectActionName(description))).findAny();
        return optional.isPresent() ? optional.get() : null;
    }
}
