package com.asa.eve.plugin;

import com.asa.eve.app.listener.input.AbstractInputAction;
import com.asa.eve.app.listener.input.Desire;
import com.asa.utils.StringUtils;

/**
 * @author andrew_asa
 * @date 2018/12/3.
 */
public abstract class AbstractPluginInputAction extends AbstractInputAction {

    public String getActionText(String text) {

        if (StringUtils.isNotEmpty(text) && StringUtils.containsIgnoreCase(text, getPluginActionKey())) {
            return text.substring(StringUtils.length(getPluginActionKey()));
        }
        return StringUtils.EMPTY;
    }

    @Override
    public Desire desire(String key) {

        if (StringUtils.containsIgnoreCase(key, getPluginActionKey())) {
            return Desire.WILLING;
        }
        return Desire.UNWILLING;
    }

    /**
     * 插件相应的key
     *
     * @return
     */
    abstract public String getPluginActionKey();
}
