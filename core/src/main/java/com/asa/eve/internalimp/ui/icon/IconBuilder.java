package com.asa.eve.internalimp.ui.icon;

import com.asa.eve.structure.ui.Icon;

/**
 * @author andrew_asa
 * @date 2018/12/3.
 * 图标构建器  
 */
public class IconBuilder {

    public Icon getClassPathIcon(String path) {

        ClassPathResourceIcon classPathResourceIcon = new ClassPathResourceIcon(path);
        return classPathResourceIcon;
    }
}
