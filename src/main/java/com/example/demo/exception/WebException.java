/*
 * @Copyright: (c) 2000-2016 www.huateng.com Inc. All Rights Reserved.
 * @项目名称:上海什马消费信贷项目
 * @创建时间: 2016年6月1日
 * 
 */
package com.example.demo.exception;

/**
 * 自定义异常类：封装所有业务异常。
 *
 * @author jiangong
 * @version 1.0
 */
public class WebException extends RuntimeException {

    private static final long serialVersionUID = -1543635206144211907L;

    private String exceptionCode;

    private String exceptionMsg;

    public WebException() {
    }

    public WebException(String paramString) {
        super(paramString);
        this.exceptionMsg = paramString;
    }

    public WebException(String paramString, Throwable paramThrowable) {
        super(paramString, paramThrowable);
    }

    public WebException(Throwable paramThrowable) {
        super(paramThrowable);
    }

    /**
     * 自定义异常方法
     *
     * @param exceptionCode 异常码
     * @param exceptionMsg  异常消息
     */
    public WebException(String exceptionCode, String exceptionMsg) {
        super(exceptionMsg);
        this.exceptionCode = exceptionCode;
        this.exceptionMsg = exceptionMsg;
    }

    /**
     * 自定义异常方法
     *
     * @param exceptionCode  异常码
     * @param exceptionMsg   异常消息
     * @param paramThrowable 异常堆栈
     */
    public WebException(String exceptionCode, String exceptionMsg, Throwable paramThrowable) {
        super(paramThrowable);
        this.exceptionCode = exceptionCode;
        this.exceptionMsg = exceptionMsg;
    }

    /**
     * @return Returns the exceptionCode.
     */
    public String getExceptionCode() {
        return exceptionCode;
    }

    /**
     * @return Returns the exceptionMsg.
     */
    public String getExceptionMsg() {
        return exceptionMsg;
    }

}