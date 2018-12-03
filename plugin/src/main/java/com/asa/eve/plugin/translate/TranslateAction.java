package com.asa.eve.plugin.translate;

import com.asa.eve.app.listener.input.AbstractInputAction;
import com.asa.eve.app.listener.input.Desire;
import com.asa.utils.StringUtils;

/**
 * @author andrew_asa
 * @date 2018/12/2.
 * 翻译
 */
public class TranslateAction extends AbstractInputAction {

    private static final String DEFAULT_START_KEY = "tr:";

    @Override
    public Desire desire(String key) {

        if (StringUtils.equalsIgnoreCase(DEFAULT_START_KEY, key)) {
            return Desire.WILLING;
        }
        return Desire.UNWILLING;
    }

    @Override
    public String getActionName() {

        return "TranslateAction";
    }
}
