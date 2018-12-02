package com.asa.eve.app.listener.globol;

import com.asa.eve.structure.app.listener.GlobalKeyEvent;
import com.asa.eve.structure.app.listener.GlobalKeyListener;
import com.asa.utils.ListUtils;

import javax.swing.KeyStroke;
import java.util.ArrayList;
import java.util.List;

/**
 * @author andrew_asa
 * @date 2018/11/27.
 * 全局按键监听器者管理器
 */
public class GlobalKeyListenerManager {

    private static final List<GlobalKeyListener> GLOBAL_KEY_LISTENERS = new ArrayList<>();

    private static GlobalKeyListenerManager INSTANCE;

    private GlobalKeyListenerManager() {

    }

    public static GlobalKeyListenerManager getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new GlobalKeyListenerManager();
        }
        return INSTANCE;
    }

    public void addGlobalKeyListener(GlobalKeyListener keyListener) {

        ListUtils.putIfAbsent(GLOBAL_KEY_LISTENERS, keyListener);
    }

    public void removeGlobalKeyListener(GlobalKeyListener keyListener) {

        ListUtils.remove(GLOBAL_KEY_LISTENERS, keyListener);
    }

    public void keyPressed(KeyStroke keyStroke) {

        GLOBAL_KEY_LISTENERS.stream().forEach(item -> {
            GlobalKeyEvent keyEvent = new GlobalKeyEvent(keyStroke);
            item.keyPressed(keyEvent);
        });
    }
}
