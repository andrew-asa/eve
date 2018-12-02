package com.asa.eve.app.listener.globol;

import com.asa.eve.structure.app.listener.GlobalKeyEvent;
import com.asa.eve.structure.app.listener.GlobalKeyListener;
import com.asa.eve.utils.KeyStrokeUtils;
import com.asa.log.LoggerFactory;

import javax.swing.KeyStroke;

/**
 * @author andrew_asa
 * @date 2018/11/27.
 */
public class GlobalActionWindowListener implements GlobalKeyListener {

    @Override
    public void keyPressed(GlobalKeyEvent keyEvent) {

        KeyStroke stroke = keyEvent.getKeyStroke();
        String dis = KeyStrokeUtils.getKeyStrokeDisplayableRepresentation(stroke);
        LoggerFactory.getLogger().debug("global key pressed {}", dis);
        GlobalActionManager.getInstance().globalActionTraversing(action -> {
            if (action.accept(dis)) {
                try {
                    LoggerFactory.getLogger().debug("action {} reply {} ", action.getActionName(), dis);
                    action.reply(dis);
                } catch (Exception e) {
                    LoggerFactory.getLogger().error(e.getMessage(), e);
                }
            }
        });
    }
}
