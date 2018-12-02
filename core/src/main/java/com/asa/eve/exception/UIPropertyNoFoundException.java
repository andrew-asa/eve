package com.asa.eve.exception;

import com.asa.eve.constant.AppConstant;
import com.asa.exception.AbstractRuntimeException;

/**
 * @author andrew_asa
 * @date 2018/11/24.
 */
public class UIPropertyNoFoundException extends AbstractRuntimeException {

    public UIPropertyNoFoundException(String msg) {

        super(AppConstant.ERRORCODE.UI_PROPERTY_NO_FOUND, msg);
    }
}
