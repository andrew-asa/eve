package com.asa.eve.structure.ui;

import java.io.InputStream;

/**
 * @author andrew_asa
 * @date 2018/12/3.
 */
public interface Icon {

    String getURI();

    void setURI(String uri);

    InputStream getInputStream();
}
