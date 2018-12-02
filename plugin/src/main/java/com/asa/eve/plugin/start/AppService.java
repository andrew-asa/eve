package com.asa.eve.plugin.start;

import java.util.List;

/**
 * @author andrew_asa
 * @date 2018/11/28.
 */
public interface AppService {

    /**
     * 获取所有已经安装的app
     *
     * @return
     */
    List<AppInfo> getInstallApp();

    /**
     * 启动app
     *
     * @param info
     */
    void launch(AppInfo info);
}
