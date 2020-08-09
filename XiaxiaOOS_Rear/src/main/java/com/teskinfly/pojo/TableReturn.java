package com.teskinfly.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TableReturn<T> {
    int total;
    int pageNum;
    List<T> data;
    String msg;
    int code;
    String query;

    public TableReturn() {
    }

    public TableReturn(int total, int pageNum, List<T> data, ReturnCode returnCode) {
        this.total = total;
        this.pageNum = pageNum;
        this.data = data;
        this.msg = returnCode.msg;
        this.code = returnCode.statusCode;
    }

    public TableReturn(List<T> data, ReturnCode returnCode) {
        this.data = data;
        this.msg = returnCode.msg;
        this.code = returnCode.statusCode;
    }

    @Override
    public String toString() {
        return "TableReturn{" +
                "total=" + total +
                ", pageNum=" + pageNum +
                ", data=" + data +
                '}';
    }
}
