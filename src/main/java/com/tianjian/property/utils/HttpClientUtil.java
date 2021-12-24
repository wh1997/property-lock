package com.tianjian.property.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
    public static Map<String, Object> doGet(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Object result = null;
        String code = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            RequestConfig build = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(5000).build();
            httpGet.setHeader("Accept", "*/*");
            httpGet.setConfig(build);
            // 执行请求
            response = httpclient.execute(httpGet);
            code=String.valueOf(response.getStatusLine().getStatusCode());
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200||response.getStatusLine().getStatusCode()==204) {
                String resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
                result = JSONObject.parse(resultString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("result",result);
        map.put("code",code);
        return map;
    }
    public static Map BWdoGet(String url,String credentialId,String header) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Object result = null;
        Integer code = null;
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            RequestConfig build = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(5000).build();
            httpGet.setHeader("Accept", "*/*");
            httpGet.setHeader("Authorization", "Credential "+credentialId);
            httpGet.setHeader("x-data-schema",header);
            httpGet.setConfig(build);
            // 执行请求
            response = httpclient.execute(httpGet);
            code=response.getStatusLine().getStatusCode();
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200/*||response.getStatusLine().getStatusCode()==204*/) {
                String resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
                result = JSONObject.parse(resultString);
                code=response.getStatusLine().getStatusCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("result",result);
        map.put("code",code);
        return map;
    }

    public static String doGet(String url, Map<String, String> param,String contenttype) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setHeader("Accept", "application/json");
            httpGet.addHeader("content-type", contenttype);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static Map<String, Object> doGet(String url) {
        return doGet(url, null);
    }

    public static Map doPost(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        Object result = null;
        Integer code = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            code = response.getStatusLine().getStatusCode();
            String resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            result = JSONObject.parse(resultString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("result",result);
        map.put("code",code);
        return map;
    }

   /* @SuppressWarnings("deprecation")
    public static String doPostType(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Accept", "application/json");
            httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
            // 创建参数列表
    		*//*if (param != null) {
    			List<NameValuePair> paramList = new ArrayList<>();
    			for (String key : param.keySet()) {
    				paramList.add(new BasicNameValuePair(key, param.get(key)));
    			}*//*
            // 模拟表单
    			*//*JSONObject postData = new JSONObject();  
    			                  
    			postData.put("supervisor", supervisorEt.getEditableText().toString());  
    			postData.put("content", superviseContentEt.getEditableText().toString());  
    			postData.put("userId", signedUser.getId());  
    			                  
    			httpPost.setEntity(new StringEntity(postData.toString(), HTTP.UTF_8));  *//*
            JSONObject postData = new JSONObject();
            postData.put("uname", param.get("uname"));
            postData.put("secret", param.get("secret"));
            StringEntity stringEntity = new StringEntity(postData.toString(), HTTP.UTF_8);
            httpPost.setEntity(stringEntity);
            //}
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doPost(String url, Map<String, String> param, String token, String contenttyp) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Accept", "application/json");
            if (token != null) {
                httpPost.addHeader("token", token);
                httpPost.addHeader("content-typ", contenttyp);
            }
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doPost1(String url, Map<String, Object> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Accept", "application/json");
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, String.valueOf(param.get(key))));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }


    public static String doPost(String url) {
        return doPost(url, null);
    }*/
    /**
     * 请求的参数类型为json
     *
     * @param url
     * @param json
     * @return {username:"",pass:""}
     */
    public static Map doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        Object result = null;
        Integer code = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            String resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            result = JSONObject.parse(resultString);
            code=response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("result",result);
        map.put("code",code);
        return map;
    }
    public static Map baiwdoPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        Object result = null;
        Integer code = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type","application/json");
            httpPost.setHeader("Authorization","Credential 69295401242SWKSPM");
            httpPost.setHeader("x-data-schema","*,Building{Name,Park{Name}}");
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            String resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            result = JSONObject.parse(resultString);
            code=response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("result",result);
        map.put("code",code);
        return map;
    }
   /* public static String doPostOfMultipartFormData(String url, MultipartFile file, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            if (file != null) {
                multipartEntityBuilder.addBinaryBody("file", file.getInputStream());
            }
            if (param != null) {
                for (String key : param.keySet()) {
                    multipartEntityBuilder.addTextBody(key, param.get(key));
                }
            }
            httpPost.setEntity(multipartEntityBuilder.build());
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
            }
        }
        return resultString;
    }*/
}