package com.asa.eve.internalimp.ui;

import com.asa.eve.app.listener.input.control.InputControlActionManager;
import com.asa.eve.structure.app.InputKeyPressDispatch;
import com.asa.eve.utils.KeyStrokeUtils;
import com.asa.log.LoggerFactory;

import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

/**
 * @author andrew_asa
 * @date 2018/12/2.
 * 输入框按键事件，在这里处理控制命令
 */
public class DefaultInputKeyPressDispatch implements InputKeyPressDispatch {

    @Override
    public void keyPressed(KeyEvent e) {

        KeyStroke stroke = KeyStroke.getKeyStroke(e.getKeyCode(), e.getModifiers());
        String disp = KeyStrokeUtils.getKeyStrokeDisplayableRepresentation(stroke);
        LoggerFactory.getLogger().debug("input key press {}", stroke);
        InputControlActionManager.getInstance().inputControlActionTraversing(action -> {
            if (action.reply(disp)) {
                action.apply(disp);
            }
        });
    }
}
