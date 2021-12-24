package com.tianjian.property.handler;

import com.tianjian.property.config.RequestResult;
import com.tianjian.property.utils.error.BusinessException;
import com.tianjian.property.utils.error.ErrorEnum;
import com.tianjian.property.utils.error.ErrorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @Author LiaoQuanfeng
 * Date on 2020\4\28 0028  16:50
 * @description 业务异常处理封装类（主要用于参数校验和业务逻辑错误）
 */
@ControllerAdvice
public class BusinessExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(BusinessExceptionHandler.class);

    /**
     * 检验异常是否返回详细信息
     * 默认返回自定义模糊信息
     */
    @Value("${exception.business.showDetail:false}")
    private String showDetail;

    @Value("${exception.business.showAll:false}")
    private boolean showAll;

    /**
     * 统一错误处理
     * @param request
     * @param e
     * @return
     * 对象参数接收请求体校验不通过会抛出 MethodArgumentNotValidException
     * 普通参数校验校验不通过会抛出 ConstraintViolationException(自定义参数校验)
     * 必填参数没传校验不通过会抛出 ServletRequestBindingException
     * 请求参数绑定到对象上校验不通过会抛出 BindException (@valid)
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception e){
        // 统一返回通知
        RequestResult result = RequestResult.create(0);
        // 统一错误日志
        String msg = "";
        if(e instanceof BusinessException){
            // 对业务异常进行封装
            BusinessException ce = (BusinessException) e;
            result.setErrorNo(ce.getCode());
            result.setErrorMsg(ce.getErrorMsg());
            if(null != ce.getData()){
                result.setResult(ce.getData());
            }
            msg = ce.getErrorMsg();
        } else if(e instanceof BindException){
            // 对spring validator的错误进行封装
            BindException bindException = (BindException) e;
            // 获取错误信息
            msg = getBindingResultMsg(bindException.getBindingResult());
            // 统一错误返回封装
            result.setErrorNo(ErrorEnum.PARAMETER_VALIDATION_ERROR.getCode());
            if(Boolean.valueOf(showDetail)){
                result.setErrorMsg(msg);
            } else {
                result.setErrorMsg(ErrorEnum.PARAMETER_VALIDATION_ERROR.getErrorMsg());
            }
        } else if(e instanceof ConstraintViolationException){
            // 对bean validator的错误进行封装
            ConstraintViolationException t = (ConstraintViolationException) e;
            // 获取并拼接错误信息
            msg = t.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(","));
            // 统一错误返回封装
            result.setErrorNo(ErrorEnum.PARAMETER_VALIDATION_ERROR.getCode());
            if(Boolean.valueOf(showDetail)){
                result.setErrorMsg(msg);
            } else {
                result.setErrorMsg(ErrorEnum.PARAMETER_VALIDATION_ERROR.getErrorMsg());
            }
        } else if(e instanceof MethodArgumentNotValidException){
            // 对bean validator的错误进行封装
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            msg = getBindingResultMsg(methodArgumentNotValidException.getBindingResult());
            // 统一错误返回封装
            result.setErrorNo(ErrorEnum.PARAMETER_VALIDATION_ERROR.getCode());
            if(Boolean.valueOf(showDetail)){
                result.setErrorMsg(msg);
            } else {
                result.setErrorMsg(ErrorEnum.PARAMETER_VALIDATION_ERROR.getErrorMsg());
            }
        } else if(e instanceof MissingServletRequestParameterException){
            // 对bean validator的错误进行封装
            MissingServletRequestParameterException servletRequestParameterException = (MissingServletRequestParameterException) e;
            msg = servletRequestParameterException.getParameterName() + " 不能为空";
            // 统一错误返回封装
            result.setErrorNo(ErrorEnum.PARAMETER_VALIDATION_ERROR.getCode());
            result.setErrorMsg(msg);
        } else if(e instanceof MissingPathVariableException){
            // 对bean validator的错误进行封装
            MissingPathVariableException pathVariableException = (MissingPathVariableException) e;
            msg = pathVariableException.getVariableName() + " 不能为空";
            // 统一错误返回封装
            result.setErrorNo(ErrorEnum.PARAMETER_VALIDATION_ERROR.getCode());
            result.setErrorMsg(msg);
        } else if (e instanceof MethodArgumentTypeMismatchException){
            MethodArgumentTypeMismatchException argumentTypeMismatchException = (MethodArgumentTypeMismatchException) e;
//            msg = "参数" + argumentTypeMismatchException.getName()+ "发生错误：" +argumentTypeMismatchException.getMessage();
            msg = "参数" + argumentTypeMismatchException.getName()+ "传入类型错误！";
            result.setErrorNo(ErrorEnum.PARAMETER_VALIDATION_ERROR.getCode());
            result.setErrorMsg(msg);
        } else if(e instanceof HttpMessageNotReadableException){
            // 没有入参的报错
            msg = ErrorEnum.PARAMETER_NONE_ERROR.getErrorMsg();
            result.setErrorNo(ErrorEnum.PARAMETER_NONE_ERROR.getCode());
            result.setErrorMsg(msg);
        } else if(e instanceof RestClientException){
            // RestTemplate 请求报错
            RestClientException restClientException = (RestClientException) e;
            msg = restClientException.getMessage();
            result.setErrorNo(ErrorEnum.UNKNOWN_ERROR.getCode());
            result.setErrorMsg(msg);
        } else if(e instanceof IllegalStateException){
            IllegalStateException stateException = (IllegalStateException) e;
            msg = stateException.getMessage();
            result.setErrorNo(ErrorEnum.CONNECT_FAIL.getCode());
            result.setErrorMsg("系统断开链接");
        }else {
            return handlerUnkownException(e);
        }
        logger.error("参数校验不通过：" + msg);
        return result;
    }

    /**
     * 统一未处理的错误
     * @param t
     * @return
     */
    private ErrorResult handlerUnkownException(Throwable t) {
        ErrorResult result = new ErrorResult(ErrorEnum.UNKNOWN_ERROR);
        // 统一未知错误异常返回信息
        result.setErrorMsg("系统异常");
        logger.error("发生错误:" + t.getMessage());
        return result;
    }

    /**
     * 获取BindException类型错误的错误日志
     * @param result BindException错误集
     * @return
     */
    private String getBindingResultMsg(BindingResult result) {
        if(showAll){
            return result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));
        } else {
            return result.getAllErrors().get(0).getDefaultMessage();
        }
    }
}
