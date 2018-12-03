package com.asa.eve.plugin;

import com.asa.eve.app.ApplicationManager;
import com.asa.eve.plugin.search.listener.SearchAppApplicationListener;
import com.asa.eve.plugin.start.listener.StartAppApplicationListener;
import com.asa.eve.structure.app.module.Module;
import com.asa.log.LoggerFactory;

/**
 * @author andrew_asa
 * @date 2018/11/29.
 */
public class PluginModule implements Module {

    @Override
    public void start() {

        LoggerFactory.getLogger().debug("start plugin module");
        ApplicationManager.getInstance().addApplicationListener(new StartAppApplicationListener());
        ApplicationManager.getInstance().addApplicationListener(new SearchAppApplicationListener());
    }
}
