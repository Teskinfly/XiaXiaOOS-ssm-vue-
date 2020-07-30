package com.teskinfly.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class User implements Serializable {
    Integer uId;
    String uName;
    String uPwd;
    String uPhone;
    String uAddress;
    public User() {

    }

    public User(String uName, String uPwd, String uPhone, String uAddress) {
        this.uName = uName;
        this.uPwd = uPwd;
        this.uPhone = uPhone;
        this.uAddress = uAddress;
    }
//    {"uName":"lqk","uPwd":"123"}
    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", uName='" + uName + '\'' +
                ", uPwd='" + uPwd + '\'' +
                ", uPhone='" + uPhone + '\'' +
                ", uAddress='" + uAddress + '\'' +
                '}';
    }
}
