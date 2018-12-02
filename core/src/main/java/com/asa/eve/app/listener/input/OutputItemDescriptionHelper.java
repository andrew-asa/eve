package com.asa.eve.app.listener.input;

import com.asa.eve.structure.app.action.InputAction;
import com.asa.log.LoggerFactory;
import com.asa.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author andrew_asa
 * @date 2018/11/30.
 */
public class OutputItemDescriptionHelper {

    private static ObjectMapper mapper = new ObjectMapper();

    public static final String DEFAULT_KEY = "__default_key__";

    /**
     * 动作名字
     */
    public static final String DEFAULT_ACTION_NAME = "__default_action_key__";

    /**
     * 产生动作的文本
     */
    public static final String DEFAULT_ACTION_INPUT_TEXT = "__default_action_input_text__";

    public static OutputItemDescription safeCreateDefaultItem(Object bean) {

        OutputItemDescription description = new OutputItemDescription();
        try {
            description.put(DEFAULT_KEY, mapper.writeValueAsString(bean));
        } catch (JsonProcessingException e) {
            LoggerFactory.getLogger().error(e.getMessage(), e);
        }
        return description;
    }

    public static <T> T safeParseCreateDefaultItem(OutputItemDescription description, Class<T> clazz, T defaultValue) {

        try {
            String v = description.getString(DEFAULT_KEY);
            if (v != null) {
                return mapper.readValue(v, clazz);
            }
        } catch (Exception e) {
            LoggerFactory.getLogger().error(e.getMessage(), e);
        }
        return defaultValue;
    }

    public static <T> T safeParseCreateDefaultItem(OutputItemDescription description, Class<T> clazz) {

        return safeParseCreateDefaultItem(description, clazz, null);
    }

    public static <T, R> R safeDescribe(OutputItemDescription description, Class<T> clazz, Function<T, R> consumer) {

        T item = safeParseCreateDefaultItem(description, clazz, null);
        if (item != null) {
            return consumer.apply(item);
        }
        return null;
    }

    public static <T> void safeApply(OutputItemDescription description, Class<T> clazz, Consumer<T> consumer) {

        T item = safeParseCreateDefaultItem(description, clazz, null);
        if (item != null) {
            consumer.accept(item);
        }
    }

    /**
     * 应用程序以及选项秒选联系起来
     *
     * @param action
     * @param description
     * @param text
     */
    public static void connectActionAndOutputItemDescription(InputAction action, OutputItemDescription description, String text) {

        if (action != null && description != null) {
            // 动作名
            description.put(DEFAULT_ACTION_NAME, action.getActionName());
            // 输入的文本
            description.put(DEFAULT_ACTION_INPUT_TEXT, text);
        }
    }

    public static String getConnectActionName(OutputItemDescription description) {

        if (description != null) {
            return description.getString(DEFAULT_ACTION_NAME);
        }
        return StringUtils.EMPTY;
    }
}
