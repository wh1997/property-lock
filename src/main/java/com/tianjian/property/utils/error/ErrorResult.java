package com.tianjian.property.utils.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date on 2020\6\2 0002  16:27
 * @description
 */
public class ErrorResult implements CommonError {
    private static final Logger logger = LoggerFactory.getLogger(ErrorResult.class);

    // 错误号
    private int errorNo;

    // 错误信息
    private String errorMsg;

    public ErrorResult(){
        this.errorNo = 0;
        this.errorMsg = "";
    }

    public ErrorResult(int errorNo, String errorMsg){
        this.errorNo = errorNo;
        this.errorMsg = errorMsg;
    }

    public ErrorResult(CommonError commonError){
        this.errorNo = commonError.getCode();
        this.errorMsg = commonError.getErrorMsg();
    }

    public ErrorResult(ErrorEnum unknownError) {
    }


    @Override
    public Integer getCode() {
        return this.errorNo;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorNo(int errorNo) {
        this.errorNo = errorNo;
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public void setErrorResult(CommonError commonError){
        this.errorNo = commonError.getCode();
        this.errorMsg = commonError.getErrorMsg();
    }
}
