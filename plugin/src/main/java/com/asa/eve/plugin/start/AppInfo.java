package com.asa.eve.plugin.start;

import com.asa.utils.AssistUtils;

/**
 * @author andrew_asa
 * @date 2018/11/28.
 */
public class AppInfo {

    private String name;

    private String path;

    private String iconPath;


    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPath() {

        return path;
    }

    public void setPath(String path) {

        this.path = path;
    }

    @Override
    public String toString() {

        return AssistUtils.toString(this);
    }

    public String getIconPath() {

        return iconPath;
    }

    public void setIconPath(String iconPath) {

        this.iconPath = iconPath;
    }
}
