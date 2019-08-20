package com.ctyun.web.exception;

/**
 * @author hga
 * @date 2019-07-23 21:46
 */
  //自定义异常
public class CustomerException extends Exception {
    private String message;

    public CustomerException(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}