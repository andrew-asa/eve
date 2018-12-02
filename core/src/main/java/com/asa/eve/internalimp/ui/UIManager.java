package com.asa.eve.internalimp.ui;

import com.asa.eve.internalimp.ui.support.swing.SwingUICreator;
import com.asa.eve.structure.app.InputChangeDispatch;
import com.asa.eve.structure.app.InputKeyPressDispatch;
import com.asa.eve.structure.ui.UICreator;
import com.asa.eve.structure.ui.Window;

/**
 * @author andrew_asa
 * @date 2018/11/27.
 */
public class UIManager {

    private static UIManager INSTANCE;

    private Window currentWindow;

    private UICreator uiCreator;

    private UIManager() {

        uiCreator = new SwingUICreator();
        currentWindow = uiCreator.createWindow();
    }

    public static UIManager getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new UIManager();
        }
        return INSTANCE;
    }

    /**
     * 生成一个窗口
     * 窗口管理自己的事件
     *
     * @return
     */
    public Window createWindow() {

        return currentWindow;
    }

    public boolean isShow() {

        return currentWindow.isShow();
    }

    public void show() {

        currentWindow.show();
    }

    public void hide() {

        currentWindow.hide();
    }

    public Window getCurrentWindow() {

        return currentWindow;
    }

    public UICreator getUICreator() {

        return uiCreator;
    }

    public InputChangeDispatch createDefaultInputChangeDispatch() {

        return new DefaultInputChangeDispatch(createWindow());
    }

    public InputKeyPressDispatch createInputKeyPressDispatch() {

        return new DefaultInputKeyPressDispatch();
    }
}
