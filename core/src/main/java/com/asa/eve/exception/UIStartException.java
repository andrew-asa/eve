package com.asa.eve.exception;


import com.asa.eve.constant.AppConstant;
import com.asa.exception.AbstractRuntimeException;

/**
 * @author andrew_asa
 * @date 2018/11/24.
 * ui 启动异常
 */
public class UIStartException extends AbstractRuntimeException {

    public UIStartException(String msg) {

        super(AppConstant.ERRORCODE.UI_START_EXCEPTION, msg);
    }
}
