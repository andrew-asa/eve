package com.asa.eve.plugin.start.mac;

import com.asa.eve.plugin.start.AppInfo;
import com.asa.eve.plugin.start.AppService;
import com.asa.log.LoggerFactory;
import com.asa.utils.ListUtils;
import com.asa.utils.StringUtils;
import com.asa.utils.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author andrew_asa
 * @date 2018/11/28.
 */
public class MacAppService implements AppService {

    public MacAppService() {
        getInstallApp();
    }

    private static final String[] APPLICATIONS_BASE_PATH = {
            "/Applications",
            "/Applications/Utilities"
    };

    private static final String APP_SUFFIX = ".app";

    private static final String OPEN = "open";

    private List<AppInfo> appsInfo;

    @Override
    public List<AppInfo> getInstallApp() {

        if (ListUtils.isNotEmpty(appsInfo)) {
            return appsInfo;
        }
        appsInfo = new ArrayList<>();
        for (String path : APPLICATIONS_BASE_PATH) {
            ListUtils.safeAdd(appsInfo, getInstallApp(path));
        }
        return appsInfo;
    }

    private List<AppInfo> getInstallApp(String path) {

        List<AppInfo> appsInfo = new ArrayList<>();
        File dir = new File(path);
        File[] files = dir.listFiles(item -> StringUtils.isNotEmpty(item.getName()) && item.getName().endsWith(APP_SUFFIX));
        for (File file : files) {
            String fileName = file.getName();
            String name = FilenameUtils.getBaseName(fileName);
            AppInfo appInfo = new AppInfo();
            appInfo.setName(name);
            appInfo.setPath(file.getAbsolutePath());
            appsInfo.add(appInfo);
        }
        return appsInfo;
    }

    @Override
    public void launch(AppInfo info) {
        // 直接open 执行
        String path = info.getPath();
        StringBuffer sb = new StringBuffer();
        sb.append(OPEN).append(" ").append(path);
        try {
            Runtime.getRuntime().exec(sb.toString());
            LoggerFactory.getLogger().debug("exec {}", sb.toString());
        } catch (IOException e) {
            LoggerFactory.getLogger().error(e.getMessage(), e);
        }
    }
}
