package com.oredoo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Response {

    private int status;
    private Object data;
    private String message;
}