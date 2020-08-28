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
    String uEmail;

    public User() {

    }

    public User(Integer uId, String uName, String uPwd, String uPhone, String uAddress, String eMail) {
        this.uId = uId;
        this.uName = uName;
        this.uPwd = uPwd;
        this.uPhone = uPhone;
        this.uAddress = uAddress;
        this.uEmail = eMail;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", uName='" + uName + '\'' +
                ", uPwd='" + uPwd + '\'' +
                ", uPhone='" + uPhone + '\'' +
                ", uAddress='" + uAddress + '\'' +
                ", eMail='" + uEmail + '\'' +
                '}';
    }
}
