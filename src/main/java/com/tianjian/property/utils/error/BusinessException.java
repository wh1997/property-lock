package com.tianjian.property.utils.error;

/**
 * Date on 2020\4\28 0028  16:17
 * @description 业务异常封装类（主要用于参数校验和业务逻辑错误）
 */
// 包装器业务异常类实现
public class BusinessException extends Exception implements CommonError {
    // 通用错误接口
    private CommonError commonError;

    private Object data;

    /**
     * 空参构造器（防止切面标注异常）
     */
    public BusinessException() {
        super();
    }

    /**
     *  直接接受ErrorEnum的传参用于构造业务异常
     * @param commonError 通用错误
     */
    public BusinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }


    /**
     * 接收自定义errorMsg的方式构造业务异常
     * @param commonError 通用错误类
     * @param errorMsg 错误信息
     */
    public BusinessException(CommonError commonError, String errorMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrorMsg(errorMsg);
    }

    public BusinessException(CommonError commonError, String errorMsg, Object data){
        super();
        this.commonError = commonError;
        this.commonError.setErrorMsg(errorMsg);
        this.data = data;
    }

    public Object getData(){
        return this.data;
    }

    @Override
    public Integer getCode() {
        return this.commonError.getCode();
    }

    @Override
    public String getErrorMsg() {
        return this.commonError.getErrorMsg();
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.commonError.setErrorMsg(errorMsg);
        return this;
    }
}
