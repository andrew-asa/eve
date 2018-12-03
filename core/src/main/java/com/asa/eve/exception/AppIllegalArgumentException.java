package com.asa.eve.exception;

import com.asa.eve.constant.AppConstant;
import com.asa.exception.AbstractRuntimeException;

/**
 * @author andrew_asa
 * @date 2018/12/3.
 */
public class AppIllegalArgumentException extends AbstractRuntimeException {

    public AppIllegalArgumentException(String msg) {

        super(AppConstant.ERRORCODE.APP_ILLLEGAL_ARGUMENT, msg);
    }
}
