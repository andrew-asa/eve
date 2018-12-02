package com.asa.eve.app.listener.input;

import com.asa.eve.structure.app.action.InputAction;
import com.asa.eve.structure.app.action.Model;
import com.asa.eve.structure.ui.TextPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrew_asa
 * @date 2018/11/28.
 * 输入动作的请求
 */
public abstract class AbstractInputAction implements InputAction {

    @Override
    public Model getModel() {

        return Model.INPUT;
    }


    @Override
    public Desire desire(String key) {

        return Desire.UNWILLING;
    }

    @Override
    public List<OutputItemDescription> analysis(String text) {

        return new ArrayList<>();
    }

    @Override
    public TextPanel describe(OutputItemDescription description) {

        return null;
    }

    @Override
    public void detailDescription(OutputItemDescription description) {

    }

    @Override
    public void apply(OutputItemDescription description) {

    }
}
