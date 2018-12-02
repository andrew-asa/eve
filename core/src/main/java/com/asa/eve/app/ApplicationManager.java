package com.asa.eve.app;

import com.asa.eve.app.listener.app.GlobalKeyRegisterListener;
import com.asa.eve.app.module.ModuleManager;
import com.asa.eve.internalimp.ui.UIManager;
import com.asa.eve.structure.app.ApplicationEvent;
import com.asa.eve.structure.app.ApplicationListener;
import com.asa.eve.structure.app.ApplicationProperties;
import com.asa.eve.structure.ui.Window;
import com.asa.local.InterProviderFactory;
import com.asa.local.LocaleMarker;
import com.asa.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author andrew_asa
 * @date 2018/11/24.
 */
public class ApplicationManager {

    private Window window;

    private ApplicationProperties applicationProperties;

    private List<ApplicationListener> APPLICATION_LISTENER = new ArrayList<>();

    private boolean STARTED = false;

    private static ApplicationManager INSTANCE;

    private ModuleManager moduleManager;

    private ApplicationManager() {

        APPLICATION_LISTENER.add(new GlobalKeyRegisterListener());
    }

    public static ApplicationManager getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new ApplicationManager();
        }
        return INSTANCE;
    }

    public void addApplicationListener(ApplicationListener listener) {

        ListUtils.putIfAbsent(APPLICATION_LISTENER, listener);
    }

    public void removeApplicationListener(ApplicationListener listener) {

        ListUtils.remove(APPLICATION_LISTENER, listener);
    }

    public void start() {

        initI18n();
        startModule();
        if (STARTED) {
            System.err.println(InterProviderFactory.getProvider().getLocaleText("EVE-App_Stared"));
            System.exit(1);
        }
        initApplicationProperties();
        startWindow();
    }

    public void startModule() {

        moduleManager = new ModuleManager();
        moduleManager.start();
    }

    /**
     * 注册国际化
     */
    private void initI18n() {

        InterProviderFactory.registerLocale(LocaleMarker.create("com/asa/eve/i18/eve"));
    }

    /**
     * 初始化应用程序属性
     */
    private void initApplicationProperties() {

        applicationProperties = new ApplicationProperties();
    }


    /**
     * 因为应用程序就是开启一个window ，所以这里以window的事件作为应用程序的事件
     */
    public void startWindow() {

        window = UIManager.getInstance().createWindow();
        traversingAppListener(listener -> {
            ApplicationEvent applicationEvent = new ApplicationEvent(applicationProperties);
            listener.beforeInit(applicationEvent);
        });
        window.init(applicationProperties);
        traversingAppListener(listener -> {
            ApplicationEvent applicationEvent = new ApplicationEvent(applicationProperties);
            listener.afterInit(applicationEvent);
        });
        traversingAppListener(listener -> {
            ApplicationEvent applicationEvent = new ApplicationEvent(applicationProperties);
            listener.beforeStart(applicationEvent);
        });
        window.start();
        traversingAppListener(listener -> {
            ApplicationEvent applicationEvent = new ApplicationEvent(applicationProperties);
            listener.afterStart(applicationEvent);
        });
        traversingAppListener(listener -> {
            ApplicationEvent applicationEvent = new ApplicationEvent(applicationProperties);
            listener.beforeShow(applicationEvent);
        });
        window.show();
        traversingAppListener(listener -> {
            ApplicationEvent applicationEvent = new ApplicationEvent(applicationProperties);
            listener.afterShow(applicationEvent);
        });
    }

    public void traversingAppListener(Consumer<ApplicationListener> consumer) {

        APPLICATION_LISTENER.stream().forEach(item -> {
            consumer.accept(item);
        });
    }
}
