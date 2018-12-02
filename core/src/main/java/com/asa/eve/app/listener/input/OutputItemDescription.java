package com.asa.eve.app.listener.input;

import com.asa.utils.AssistUtils;
import com.asa.utils.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author andrew_asa
 * @date 2018/11/30.
 */
public class OutputItemDescription {

    private Map<String, String> properties = new HashMap<>();

    public OutputItemDescription() {

    }

    public String put(String key, String value) {

        return MapUtils.safeAddToMap(properties, key, value);
    }

    public String remove(String key) {

        return MapUtils.get(properties, key);
    }

    public String getString(String key) {

        return MapUtils.get(properties, key);
    }

    public String getString(String key, String defaultValue) {

        return MapUtils.get(properties, key, defaultValue);
    }

    @Override
    public OutputItemDescription clone() {

        OutputItemDescription outputItemDescription = new OutputItemDescription();
        for (String key : properties.keySet()) {
            outputItemDescription.put(key, properties.get(key));
        }
        return outputItemDescription;
    }

    @Override
    public String toString() {

        return AssistUtils.toString(this);
    }
}
