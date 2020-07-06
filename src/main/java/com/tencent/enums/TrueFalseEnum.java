package com.tencent.enums;

public enum TrueFalseEnum {

    TRUE("true"),

    FALSE("false");

    private String value;

    TrueFalseEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
