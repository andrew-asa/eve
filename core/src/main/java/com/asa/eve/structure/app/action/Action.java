package com.asa.eve.structure.app.action;

/**
 * @author andrew_asa
 * @date 2018/11/27.
 */
public interface Action {

    /**
     * 动作名字
     *
     * @return
     */
    String getActionName();

    /**
     * 模式
     *
     * @return
     */
    Model getModel();
}
