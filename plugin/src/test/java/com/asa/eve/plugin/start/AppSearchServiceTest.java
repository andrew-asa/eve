package com.asa.eve.plugin.start;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author andrew_asa
 * @date 2018/11/28.
 */
public class AppSearchServiceTest {

    @Test
    public void getAppService() throws Exception {

        List<AppInfo> appInfos = AppSearchService.getInstance().getAppService().getInstallApp();
        appInfos.stream().forEach(item -> {
            System.out.println(item);
        });
    }

    @Test
    public void testLaunch() {

        AppService service = AppSearchService.getInstance().getAppService();
        AppInfo info = new AppInfo();
        info.setPath("/Applications/MWeb.app");
        service.launch(info);
    }
}