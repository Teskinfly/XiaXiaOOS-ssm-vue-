package com.teskinfly.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Admin implements Serializable {
    Integer aId;
    String aName;
    String aPwd;

    public Admin() {

    }

    public Admin(String aName, String aPwd) {
        this.aName = aName;
        this.aPwd = aPwd;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aId=" + aId +
                ", aName='" + aName + '\'' +
                ", aPwd='" + aPwd + '\'' +
                '}';
    }
}
