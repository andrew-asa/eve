package com.asa.eve.app.listener.input.control;

import com.asa.eve.structure.app.action.InputControlAction;
import com.asa.eve.structure.app.action.Model;

/**
 * @author andrew_asa
 * @date 2018/12/2.
 */
public abstract class AbstractInputControlAction implements InputControlAction {


    @Override
    public Model getModel() {

        return Model.INPUT;
    }

    @Override
    public boolean reply(String keyShot) {

        return false;
    }

    @Override
    public void apply(String keyShot) {

    }
}
