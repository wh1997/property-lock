package com.tianjian.property.config;

import com.tianjian.property.utils.error.CommonError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author LiaoQuanfeng
 * Date on 2020\6\2 0002  15:51
 * @description 通用web返回类
 */
public class RequestResult {
    private static final Logger logger = LoggerFactory.getLogger(RequestResult.class);
    //错误号
    private int errorNo;
    //错误信息
    private String errorMsg;
    //返回值
    private Map<String, Object> results = null;

//    public static RequestResult create(Object result){
//        return RequestResult.create("", result);
//    }

    public static RequestResult create(int errorNo){
        RequestResult requestResult = new RequestResult();
        requestResult.setErrorNo(errorNo);
        return requestResult;
    }

    public static RequestResult create(CommonError errorEnum){
        RequestResult requestResult = new RequestResult();
        requestResult.setErrorNo(errorEnum.getCode());
        requestResult.setErrorMsg(errorEnum.getErrorMsg());
        return requestResult;
    }

    public static RequestResult create(String errorMsg, Object results) {
        return RequestResult.create(0, "", results);
    }

    public static RequestResult create(int errorNo, String errorMsg) {
        RequestResult requestResult = new RequestResult();
        requestResult.setErrorNo(errorNo);
        requestResult.setErrorMsg(errorMsg);
        return requestResult;
    }

    public static RequestResult create(int errorNo, String errorMsg, Object results) {
        RequestResult requestResult = new RequestResult();
        requestResult.setErrorNo(errorNo);
        requestResult.setErrorMsg(errorMsg);
        requestResult.setResult(results);
        return requestResult;
    }

    public static RequestResult create(int errorNo, String errorMsg, List results) {
        RequestResult requestResult = new RequestResult();
        requestResult.setErrorNo(errorNo);
        requestResult.setErrorMsg(errorMsg);
        requestResult.setResult( "list", results);
        return requestResult;
    }

    public RequestResult() {
        this.errorNo = 0;
        this.errorMsg = "";
        this.results = new HashMap();
    }

    public int getErrorNo() {
        return this.errorNo;
    }

    public void setErrorNo(int errorNo) {
        this.errorNo = errorNo;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        if (this.errorNo == 0) {
            logger.info(errorMsg);
        } else {
            logger.warn(errorMsg);
        }
        this.errorMsg = errorMsg;
    }

    public Map<String, Object> getResults() {
        return this.results;
    }

    public MapResult getData(String dsName) {
        Object value = getResult(dsName);
        if (value != null) {
            if ((value instanceof MapResult)) {
                return (MapResult) value;
            }
            if ((value instanceof Map)) {
                MapResult result = new MapResult();
                result.putAll((Map) value);
                return result;
            }
            if ((value instanceof List)) {
                List dataList = (List) value;
                if (dataList.size() > 0) {
                    value = dataList.get(0);
                    if ((value instanceof MapResult)) {
                        return (MapResult) value;
                    }
                    return null;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    public List getList(String dsName) {
        Object value = getResult(dsName);
        if ((value != null) && ((value instanceof List))) {
            return (List) value;
        }
        logger.error("!!!!!!!!!!!返回的不是List类型");
        return null;
    }

    public Object getResult() {
        Iterator<String> iter = this.results.keySet().iterator();
        if (iter.hasNext()) {
            String key = (String) iter.next();
            return getResult(key);
        }
        logger.error("!!!!!!!!!!!返回的结果集为空");
        return null;
    }

    public Object getResult(String dsName) {
        return getResults().get(dsName);
    }

    public void setResult(Object object) {
        this.results.put("data", object);
    }

    public void setResult(String name, Object object) {
        this.results.put(name, object);
    }

}
