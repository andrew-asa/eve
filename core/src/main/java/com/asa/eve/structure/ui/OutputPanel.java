package com.asa.eve.structure.ui;

import com.asa.eve.app.listener.input.OutputItemDescription;
import com.asa.eve.internalimp.ui.support.swing.panel.SwingTextPanel;

/**
 * @author andrew_asa
 * @date 2018/11/29.
 */
public interface OutputPanel {

    /**
     * 重置内容面板
     */
    void resetContent();

    /**
     * 选中下一个选项
     */
    OutputItemDescription selectLastItem();

    /**
     * 选中下一个选项
     */
    OutputItemDescription selectNextItem();

    /**
     * 添加可选项
     *
     * @param label
     * @param outputItemDescription
     */
    void addSelectItem(TextPanel label, OutputItemDescription outputItemDescription);

    /**
     * 获取当前选中项的描述信息
     *
     * @return
     */
    OutputItemDescription getSelectItem();
}
