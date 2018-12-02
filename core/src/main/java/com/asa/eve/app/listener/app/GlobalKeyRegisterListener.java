package com.asa.eve.app.listener.app;

import com.asa.eve.app.listener.globol.GlobalKeyListenerManager;
import com.asa.eve.app.listener.globol.GlobalActionWindowListener;
import com.asa.eve.structure.app.ApplicationEvent;
import com.asa.local.InterProviderFactory;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.swing.KeyStroke;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author andrew_asa
 * @date 2018/11/26.
 * 全局时间监听
 */
public class GlobalKeyRegisterListener extends AbstractApplicationListener {

    private NativeHookListener hookListener;

    @Override
    public void afterStart(ApplicationEvent event) {

        createCustomGlobalListener();
    }

    /**
     * 创建默认的全局按键事件监听器
     */
    private void createDefaultGlobalListener() {

        GlobalKeyListenerManager.getInstance().addGlobalKeyListener(new GlobalActionWindowListener());
    }

    /**
     * 创建自定义的全局按键监听器
     */
    private void createCustomGlobalListener() {

        nativeRegister();
        shutdownNativeHookLogger();
        createDefaultGlobalListener();
    }

    private void shutdownNativeHookLogger() {
        // Get the LOGGER for "org.jnativehook" and set the level to off.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);
    }

    /**
     * 测试本地全局环境是否可以进行监听
     */
    private void nativeRegister() {

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println(InterProviderFactory.getProvider().getLocaleText("EVE-App_Native_Hook_Error"));
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        hookListener = new NativeHookListener();
        GlobalScreen.addNativeKeyListener(hookListener);
    }
}
