package com.example.testunit.demo.enumeration;

/**
 * @Despriction: 自定义枚举类
 * @Author: wangcheng
 * @Date: 2018/4/7 18:48
 */
public enum EnumClassName {
    A("100-80"){
        public String localValue(){
            return "甲";
        }
    },
    B("79-60"){

        public String localValue(){
            return "乙";
        }
    },
    C("59-0"){

        public String localValue(){
            return "丙";
        }
    };
    private String value;
    private EnumClassName(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
    public abstract String localValue();

}
