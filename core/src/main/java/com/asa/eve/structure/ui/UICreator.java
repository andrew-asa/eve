package com.asa.eve.structure.ui;

/**
 * @author andrew_asa
 * @date 2018/11/28.
 * ui 生成器
 * 主要用于处理不同ui技术
 */
public interface UICreator {

    /**
     * 生成一个窗口
     *
     * @return
     */
    Window createWindow();

    /**
     * 生成标签
     *
     * @return
     */
    Label createLabel();

    /**
     * 文本面板
     *
     * @return
     */
    TextPanel createTextPanel();
}
