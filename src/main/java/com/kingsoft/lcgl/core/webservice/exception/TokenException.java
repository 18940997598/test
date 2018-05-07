package com.kingsoft.lcgl.core.webservice.exception;


import com.kingsoft.lcgl.core.webservice.support.RestExceptionResponse;

/**
 * Created by wangyongkui on 15-2-12.
 */
public class TokenException extends RestException {

    public TokenException(int key) {
        super(key);
    }

    public TokenException(RestExceptionResponse response) {
        super(response);
    }

    public TokenException(int key, Throwable cause) {
        super(key,cause);
    }

    public TokenException(RestExceptionResponse response, Throwable cause) {
        super(response,cause);
    }
}
