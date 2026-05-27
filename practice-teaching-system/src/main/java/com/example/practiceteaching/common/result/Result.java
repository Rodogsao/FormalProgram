package com.example.practiceteaching.common.result;

public class Result<T> {

    // 状态码
    private Integer code;

    // 提示信息
    private String message;

    // 返回数据
    private T data;

    public Result() {
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功返回（带数据）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    // 成功返回（无数据）
    public static <T> Result<T> success() {
        return new Result<>(200, "success", null);
    }

    // 失败返回
    public static <T> Result<T> error(String message) {
        return new Result<>(500, message, null);
    }

    // getter setter

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}