package com.tianjian.property.utils;
//数据返回类
public class LockResult {
    private boolean success;  //true表示成功
    private String errorMessage;
    private Integer code;
    private Object data;


    public LockResult(boolean success, String errorMessage, Integer code, Object data) {
        super();
        this.success = success;
        this.errorMessage = errorMessage;
        this.code = code;
        this.data = data;
    }
    public LockResult(){
    }
    //成功，就存入数据
    public LockResult(Object data){
        this.success=true;
        this.code = 200;
        this.data=data;
    }
    //成功，就存入数据
    public LockResult(Integer code,Object data){
        this.success=true;
        this.code = code;
        this.data=data;
    }
    //成功，就存入数据
    public LockResult(Object data,boolean success,Integer code, String errorMessage){
        this.success=true;
        this.code = code;
        this.data=data;
        this.errorMessage=errorMessage;
    }
    //失败，返回原因和错误码
    public LockResult(String errorMessage,Integer code){
        this.success=false;
        this.code = code;
        this.errorMessage=errorMessage;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return " AjaxResult[success=" + success + ", errorMessage=" + errorMessage + ", code=" + code
                + ", data=" + data + "]";
    }
}
