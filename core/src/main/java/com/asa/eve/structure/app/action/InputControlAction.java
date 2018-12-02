package com.asa.eve.structure.app.action;


/**
 * @author andrew_asa
 * @date 2018/11/28.
 */
public interface InputControlAction extends Action {

    /**
     * 是否响应
     *
     * @param keyShot
     * @return
     */
    boolean reply(String keyShot);

    /**
     * 响应
     *
     * @param keyShot
     */
    void apply(String keyShot);
}
