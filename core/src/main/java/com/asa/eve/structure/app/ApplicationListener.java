package com.asa.eve.structure.app;

/**
 * @author andrew_asa
 * @date 2018/11/26.
 * 应用程序监听器
 */
public interface ApplicationListener {

    /**
     * 初始化前
     *
     * @param event
     */
    void beforeInit(ApplicationEvent event);

    /**
     * 初始化后
     *
     * @param event
     */
    void afterInit(ApplicationEvent event);

    /**
     * 开始前
     *
     * @param event
     */
    void beforeStart(ApplicationEvent event);

    /**
     * 开始后
     *
     * @param event
     */
    void afterStart(ApplicationEvent event);

    /**
     * 显示前
     *
     * @param event
     */
    void beforeShow(ApplicationEvent event);

    /**
     * 显示后
     *
     * @param event
     */
    void afterShow(ApplicationEvent event);

    /**
     * 停止前
     *
     * @param event
     */
    void beforeStop(ApplicationEvent event);

    /**
     * 停止后
     *
     * @param event
     */
    void afterStop(ApplicationEvent event);
}
