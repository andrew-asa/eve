package com.asa.eve.plugin.start;

import com.asa.eve.plugin.start.mac.MacAppService;
import com.asa.third.org.apache.commons.lang3.SystemUtils;

/**
 * @author andrew_asa
 * @date 2018/11/28.
 */
public class AppSearchService {

    private static AppSearchService INSTANCE;

    private AppService CURRENT_SYSTEM_APP_SERVICE;

    private AppSearchService() {

        init();
    }

    private void init() {

        if (SystemUtils.IS_OS_MAC) {
            CURRENT_SYSTEM_APP_SERVICE = new MacAppService();
        }
    }

    public static AppSearchService getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new AppSearchService();
        }
        return INSTANCE;
    }

    public AppService getAppService() {

        return CURRENT_SYSTEM_APP_SERVICE;
    }
}
