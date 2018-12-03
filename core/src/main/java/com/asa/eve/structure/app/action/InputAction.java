package com.asa.eve.structure.app.action;

import com.asa.eve.app.listener.input.Desire;
import com.asa.eve.app.listener.input.OutputItemDescription;
import com.asa.eve.structure.ui.Component;
import com.asa.eve.structure.ui.TextPanel;

import java.util.List;

/**
 * @author andrew_asa
 * @date 2018/11/30.
 */
public interface InputAction extends Action {

    /**
     * 分析意愿
     *
     * @param key
     * @return
     */
    Desire desire(String key);

    /**
     * 分析输出报告
     *
     * @param text
     * @return
     */
    List<OutputItemDescription> analysis(String text);

    /**
     * 相应生成报告项
     *
     * @param description
     */
    Component describe(OutputItemDescription description);

    /**
     * 详细描述具体项
     *
     * @param description
     */
    void detailDescription(OutputItemDescription description);

    /**
     * 应用
     *
     * @param description
     */
    void apply(OutputItemDescription description);
}
