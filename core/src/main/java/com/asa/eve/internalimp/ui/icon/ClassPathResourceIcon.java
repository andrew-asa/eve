package com.asa.eve.internalimp.ui.icon;

import com.asa.eve.exception.AppIllegalArgumentException;
import com.asa.eve.structure.ui.Icon;
import com.asa.utils.StringUtils;

import java.io.InputStream;

/**
 * @author andrew_asa
 * @date 2018/12/3.
 */
public class ClassPathResourceIcon implements Icon {

    private String uri;

    private String path;

    private static final String CLASS_PATH_PREFIX = "class:";

    public ClassPathResourceIcon(String path) {

        init(path);
    }

    private void init(String path) {

        if (StringUtils.isEmpty(path)) {
            throw new AppIllegalArgumentException(StringUtils.messageFormat(" icon path is null"));
        }
        if (StringUtils.startsWith(path, CLASS_PATH_PREFIX)) {
            uri = path;
            this.path = uri.substring(CLASS_PATH_PREFIX.length());
        } else {
            this.path = path;
        }
    }

    @Override
    public String getURI() {

        return uri;
    }

    @Override
    public void setURI(String uri) {

        this.uri = uri;
    }

    @Override
    public InputStream getInputStream() {

        return getClass().getClassLoader().getResourceAsStream(path);
    }
}
