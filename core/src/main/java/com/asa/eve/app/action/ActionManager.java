package com.asa.eve.app.action;

/**
 * @author andrew_asa
 * @date 2018/11/27.
 * action 管理器
 */
public class ActionManager {

    private static ActionManager INSTANCE;

    private ActionManager() {

    }

    public static ActionManager getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new ActionManager();
        }
        return INSTANCE;
    }
}
