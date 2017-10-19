package com.flypaas.customExceptions;

public class OrderBusinessException extends RuntimeException {

    private static final long serialVersionUID = -3477616872887672429L;

    public Integer errorCode;

    public OrderBusinessException(Integer errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public OrderBusinessException(Integer errorCode, String message,
            Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public OrderBusinessException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public OrderBusinessException(Integer errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public OrderBusinessException() {
        super();
    }

    public OrderBusinessException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public OrderBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderBusinessException(String message) {
        super(message);
    }

    public OrderBusinessException(Throwable cause) {
        super(cause);
    }

}
