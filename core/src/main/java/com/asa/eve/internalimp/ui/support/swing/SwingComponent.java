package com.asa.eve.internalimp.ui.support.swing;

import java.awt.Component;
import java.awt.event.MouseListener;

/**
 * @author andrew_asa
 * @date 2018/12/4.
 */
public interface SwingComponent {

    /**
     * 获取swing 组件
     *
     * @return
     */
    Component getComponent();

    /**
     * 添加鼠标事件
     *
     * @param l
     */
    void addMouseListener(MouseListener l);

    /**
     * 选中
     */
    void select();

    /**
     * 取消选中
     */
    void unSelect();
}
