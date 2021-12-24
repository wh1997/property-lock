package com.tianjian.property.management.service.impl;

import com.alibaba.fastjson.JSON;
import com.tianjian.property.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/9/16
 */
@Slf4j
public class HttpService {
    public Object postResult(String url,Map map){
        String json = JSON.toJSONString(map);
        log.info(url+"请求的参数为："+json);
        Map result = HttpClientUtil.doPostJson(url, json);
        log.info(url+"请求返回值为："+result.toString());
        return result.get("result");
    }
    public Object getResult(String url){
        log.info("请求的地址为： "+url);
        Map<String, Object> result = HttpClientUtil.doGet(url);
        log.info("请求地址："+url+"请求返回值为："+result.toString());
        return result.get("result");
    }
    public Object staffPostResult(String url,Map map){
        String json = JSON.toJSONString(map);
        log.info(url+"请求的参数为："+json);
        Map result = HttpClientUtil.baiwdoPostJson("https://staff.api.melifego.cn/Apartments/Query?page=1%7C20", json);
        log.info(url+"请求返回值为："+result.toString());
        return result.get("result");
    }
}