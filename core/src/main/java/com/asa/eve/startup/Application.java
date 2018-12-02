package com.asa.eve.startup;

import com.asa.eve.app.ApplicationManager;

/**
 * @author andrew_asa
 * @date 2018/11/24.
 */
public class Application {


    /**
     * Application startup
     */
    public static void main(String[] args) {

        ApplicationManager.getInstance().start();
    }
}
