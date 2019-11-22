package com.midnight.common.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResult {

    private Integer code;

    private String msg;

    private Object data;

    public ApiResult() {
    }

    public ApiResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiResult success() {
        ApiResult result = new ApiResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static ApiResult success(Object data) {
        ApiResult result = new ApiResult();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static ApiResult failure(ResultCode resultCode) {
        ApiResult result = new ApiResult();
        result.setResultCode(resultCode);
        return result;
    }

    public static ApiResult failure(ResultCode resultCode, Object data) {
        ApiResult result = new ApiResult();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }
}