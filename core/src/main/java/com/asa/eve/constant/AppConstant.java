package com.asa.eve.constant;

/**
 * @author andrew_asa
 * @date 2018/11/24.
 */
public class AppConstant {

    public static final class ERRORCODE {

        /**
         * ui 启动时异常
         */
        public static final String UI_START_EXCEPTION = "10000001";

        /**
         * 属性没有找到
         */
        public static final String UI_PROPERTY_NO_FOUND = "10000002";
    }

    public static final class UI {

        /**
         * 输入面板的宽
         */
        public static final int DEFAULT_INPUT_PANEL_WIDTH = 600;

        /**
         * 默认输出面板高度
         */
        public static final int DEFAULT_OUT_PANEL_HEIGHT = 400;

        /**
         * 输入框的高
         */
        public static final int DEFAULT_INPUT_PANEL_HEIGHT = 70;

        public static final int DEFAULT_OUTPUT_LABEL_HEIGHT = 30;

        /**
         * 输入框padding
         */
        public static final int DEFAULT_INPUT_PANEL_PADDING = 5;

        /**
         * 输入框默认字体大小
         */
        public static final int DEFAULT_INPUT_PANEL_FONT_SIZE = 24;

        /**
         * 输出文本框pdding默认大小
         */
        public static final int DEFAULT_OUTPUT_PANEL_BORDER_SIZE = 2;
    }

    public static final class COMMON {

        /**
         * 应用具体插件分隔符
         */
        public static final String PLUGIN_SEPARATOR = ":";
    }

    public static final class ACTION {

        public static final String HIT_SELECT_OUTPUT_ITEM_ACTION_NAME = "hitSelectOutItem";
    }
}
