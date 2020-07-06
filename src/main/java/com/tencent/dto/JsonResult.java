package com.tencent.dto;

public class JsonResult<T> {

    //错误码
    private Integer code;

    //提示信息
    private String msg;

    //返回的具体内容
    private T data;

    public JsonResult(){}

    public JsonResult(Integer code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public JsonResult fail(){
        return new JsonResult(1,"操作失败",null);
    }

    public JsonResult fail(String msg){
        return new JsonResult(1,msg,null);
    }

    public JsonResult ok(){
        return new JsonResult(0,"操作成功",null);
    }

    public JsonResult ok(T data){
        return new JsonResult(0,"操作成功",data);
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
