package com.example.practiceteaching.common.result;

import lombok.Data;

@Data
public class Result {

    private int code;
    private String msg;
    private Object data;

    // 成功
    public static Result success(Object data) {
        Result r = new Result();
        r.setCode(200);
        r.setMsg("操作成功");
        r.setData(data);
        return r;
    }

    // 失败（静态方法）
    public static Result error(String msg) {
        Result r = new Result();
        r.setCode(500);
        r.setMsg(msg);
        r.setData(null);
        return r;
    }
}
