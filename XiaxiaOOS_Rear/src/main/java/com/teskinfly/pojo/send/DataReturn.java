package com.teskinfly.pojo.send;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DataReturn<T> {
    private int status;
    private String msg;
    private List<T> data;
    private String token;

    public DataReturn(List<T> data, ReturnCode code, String token) {
        this.data = data;
        msg = code.msg;
        status = code.statusCode;
        this.token = token;
    }

    public DataReturn(ReturnCode code, String token) {
        this.status = code.statusCode;
        this.msg = code.msg;
        this.token = token;
    }

    public DataReturn(ReturnCode code, List<T> data) {
        this.status = code.statusCode;
        this.msg = code.msg;
        this.data = data;
    }

    public DataReturn(ReturnCode code) {
        this.status = code.statusCode;
        this.msg = code.msg;
    }

    public DataReturn() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "DataReturn{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", token='" + token + '\'' +
                '}';
    }
}
