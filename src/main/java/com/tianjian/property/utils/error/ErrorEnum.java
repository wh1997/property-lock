package com.tianjian.property.utils.error;


/**
 * Date on 2020\4\26 0026  14:40
 * @description 错误提示枚举类
 */
public enum ErrorEnum  implements CommonError {
    UNKNOWN_ERROR(-1, "未知异常"),
    SUCCESS(0, "成功"),
    COMMON_ERROR(1, "数据维护中"),
    OPERATION_ERROR(2, "操作失败"),
    TOKEN_EXPIRE( 3, "token已过期，请重新登录"),
    TOKEN_MISS(4, "缺少token参数"),
    USER_ERROR(5, "用户不存在"),
    PARAMETER_VALIDATION_ERROR(6, "入参异常"),
    PARAMETER_NONE_ERROR(7,"入参不能为空！"),
    CONNECT_FAIL(8, "链接失败"),
    RIGHT(9, "没有权限"),
    SYSTEM_ERROR(10, "系统错误"),
    REGISTER_ERROR(11, "没有注册,无法同步用户信息"),
    ;

    private Integer  code;
    private String  errorMsg;

    ErrorEnum(Integer code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }


}
