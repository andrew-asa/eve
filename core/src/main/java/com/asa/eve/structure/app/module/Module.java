package com.asa.eve.structure.app.module;

/**
 * @author andrew_asa
 * @date 2018/11/29.
 */
public interface Module {

    /**
     * 模块先于所有的 应用监听之前进行处理化，所以可以在模块启动
     */
    void start();
}
