package com.asa.eve.structure.ui;

import com.asa.eve.structure.app.ApplicationProperties;
import com.asa.eve.structure.app.action.Model;

/**
 * @author andrew_asa
 * @date 2018/11/24.
 */
public interface Window {

    /**
     * 初始化
     */
    void init(ApplicationProperties properties);

    /**
     * 开始启动
     */
    void start();

    /**
     * 停止
     */
    void stop();

    /**
     * 显示
     */
    void show();

    /**
     * 显示
     *
     * @return
     */
    boolean isShow();

    /***
     * 隐藏
     */
    void hide();

    /**
     * 重新刷新
     */
    void resize();

    /**
     * 获取输出面板
     *
     * @return
     */
    OutputPanel getOutputPanel();

    /**
     * 模式
     *
     * @return
     */
    Model getModel();
}
