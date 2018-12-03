package com.asa.eve.plugin.search.listener;

import com.asa.eve.app.listener.app.AbstractApplicationListener;
import com.asa.eve.app.listener.input.InputActionManager;
import com.asa.eve.plugin.search.action.SearchAction;
import com.asa.eve.plugin.start.action.StartAppAction;
import com.asa.eve.structure.app.ApplicationEvent;

/**
 * @author andrew_asa
 * @date 2018/11/29.
 */
public class SearchAppApplicationListener extends AbstractApplicationListener {

    @Override
    public void afterInit(ApplicationEvent event) {

        InputActionManager.getInstance().addInputAction(new SearchAction());
    }
}
