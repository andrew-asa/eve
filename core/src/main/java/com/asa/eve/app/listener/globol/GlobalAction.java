package com.asa.eve.app.listener.globol;

import com.asa.eve.structure.app.action.Action;
import com.asa.eve.structure.app.action.Model;

/**
 * @author andrew_asa
 * @date 2018/11/27.
 * 全局动作
 */
public abstract class GlobalAction implements Action {

    @Override
    public Model getModel() {

        return Model.GLOBAL;
    }

    /**
     * 是否响应全局按键
     * @param pressKey
     * @return
     */
    public boolean accept(String pressKey) {

        return false;
    }

    /**
     * 响应
     */
    public void reply(String key) {

    }
}
