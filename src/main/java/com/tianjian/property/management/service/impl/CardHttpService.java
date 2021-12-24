package com.tianjian.property.management.service.impl;

import com.tianjian.property.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map.Entry;

import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/9/17
 */
@Slf4j
public class CardHttpService {
    public Map postResult(String url, Map<String,String> map){
        log.info(url+"请求的参数为： "+map.toString());
        Map resultMap = HttpClientUtil.doPost(url, map);
        log.info(url+"请求返回值为： "+resultMap.toString());
        return resultMap;
    }
}
