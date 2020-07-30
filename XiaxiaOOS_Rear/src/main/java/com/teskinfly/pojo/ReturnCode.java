package com.teskinfly.pojo;

public enum ReturnCode {
    SUCCESS(200,"操作成功"),
    FAIL(400,"操作失败"),
    WITHOUTTOKEN(401,"没有token");
    int statusCode;
    String msg;
    ReturnCode(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ReturnCode{" +
                "statusCode=" + statusCode +
                ", msg='" + msg + '\'' +
                '}';
    }
}
