package com.asa.eve.plugin.jump;

import com.asa.eve.app.listener.input.AbstractInputAction;
import com.asa.eve.app.listener.input.Desire;
import com.asa.eve.plugin.AbstractPluginInputAction;
import com.asa.utils.StringUtils;

/**
 * @author andrew_asa
 * @date 2018/12/2.
 * 调到文件夹
 */
public class JumpToFolderAction extends AbstractPluginInputAction {

    private static final String DEFAULT_START_KEY = "j:";

    @Override
    public String getPluginActionKey() {

        return DEFAULT_START_KEY;
    }

    @Override
    public String getActionName() {

        return "JumpToFolder";
    }
}
