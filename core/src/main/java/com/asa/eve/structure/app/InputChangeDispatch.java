package com.asa.eve.structure.app;

/**
 * @author andrew_asa
 * @date 2018/11/30.
 * 文本改变调度
 */
public interface InputChangeDispatch {

    /**
     * 文本改变事件
     *
     * @param text
     */
    void fireTextChangedEvent(String text);
}
