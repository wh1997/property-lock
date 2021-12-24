package com.tianjian.property.utils;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Date on 2020\1\13 0013  10:08
 * @description http请求工具类
 */
public class HttpUtils {

    public static String DEFAULT_ECODING = "UTF-8";

//    public static String DEFAULT_CONTENT_TYPE = "application/x-www-form-urlencoded";

    public static String DEFAULT_CONTENT_TYPE = "application/json; charset=utf-8";

    /**
     * java原生get请求
     * @param url 请求地址
     * @return  请求结果
     */
    public static String doGet(String url){
        return doGet(url, DEFAULT_ECODING, 15000, 60000);
    }

    /**
     * java原生get请求
     * @param url   请求地址
     * @param encoding  编码格式
     * @param connectTimeout    连接主机超时时间
     * @param readTimeout   读取远程返回超时时间
     * @return  请求结果
     */
    public static String doGet(String url,String  encoding,int connectTimeout, int readTimeout ){
        // 创建http连接
        HttpURLConnection connection = null;
        // 输入流
        InputStream inputStream = null;
        // 读取缓存
        BufferedReader bufferedReader = null;
        // 返回结果字符串
        StringBuffer result=new StringBuffer();
        try {
            // 创建远程url连接对象
            URL getUrl = new URL(url);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) getUrl.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(connectTimeout);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(readTimeout);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
           // System.out.println(connection.getResponseCode());
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                // 提取输入流
                inputStream = connection.getInputStream();
                if(null != inputStream){
                    //请求返回值不为空
                    // 封装输入流inputStream，并指定字符集,一般为UTF-8
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, encoding));
                    String temp = null;
                    while(null != (temp = bufferedReader.readLine())){
                        //行数据不为空
                        result.append(temp);
                        result.append("\r\n");
                    }
                }else {
                    return String.valueOf(responseCode);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 关闭远程连接
            connection.disconnect();
        }
        return result.toString();
    }
    /**
     * java原生get请求
     * @param url   请求地址
     * @return  请求结果
     */
    public static Integer doGetExists(String url){
        // 创建http连接
        HttpURLConnection connection = null;
        // 输入流
        InputStream inputStream = null;
        // 读取缓存
        BufferedReader bufferedReader = null;
        // 返回结果字符串
        StringBuffer result=new StringBuffer();
        try {
            // 创建远程url连接对象
            URL getUrl = new URL(url);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) getUrl.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
           // System.out.println(connection.getResponseCode());
            int responseCode = connection.getResponseCode();
            return responseCode;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 关闭远程连接
            connection.disconnect();
        }
        return null;
    }

    public static String doPost(String url, String param){
        return doPost(url, param, DEFAULT_ECODING, 15000, 60000, true,
                true, DEFAULT_CONTENT_TYPE ,null,null);
    }

//    public static String doPost(String url, String param, String contentType){
//        return doPost(url, param, DEFAULT_ECODING, 15000, 60000, true,
//                true, contentType ,null,null);
//    }

    public static String doPost(String url, String param, Map<String, String> propertyMap){
        return doPost(url, param, DEFAULT_ECODING, 15000, 60000, true,
                true, DEFAULT_CONTENT_TYPE ,null, propertyMap);
    }
    /**
     * java原生post请求
     * @param url  请求地址
     * @param param 请求参数
     * @param encoding  编码格式
     * @param connectTimeout 连接主机超时毫秒数
     * @param readTimeout  读取返回值超时毫秒数
     * @param doOutput  是否向远程写数据
     * @param doInput 向远程读数据
     * @param contentType 入参格式
     * @param authorization
     * @return
     */
    public static String doPost(String url, String param, String encoding, int connectTimeout, int readTimeout,
                                boolean doOutput, boolean doInput, String contentType, String authorization, Map<String, String> propertyMap){
        // 创建http连接
        HttpURLConnection connection = null;
        // 输入流
        InputStream inputStream = null;
        // 输出流
        OutputStream outputStream = null;
        // 读取缓存
        BufferedReader bufferedReader = null;
        // 结果
        StringBuffer result = new StringBuffer();

        try {
            // 创建远程url连接对象
            URL postUrl = new URL(url);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) postUrl.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(connectTimeout);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(readTimeout);
            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(doOutput);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(doInput);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            if(null != contentType && !contentType.equals("")){
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            }

            if(null != propertyMap && propertyMap.size() > 0){
                for (String key : propertyMap.keySet()) {
                    connection.setRequestProperty(key, propertyMap.get(key));
                }
            }

            // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
            if(null != authorization && !authorization.equals("")){
                connection.setRequestProperty("Authorization", authorization);
            }
            // 入参处理
            if(null != param && !param.equals("")){
                // 通过连接对象获取一个输出流
                outputStream = connection.getOutputStream();
                // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
                outputStream.write(param.getBytes());
            }
            //开启连接,可省略
            //connection.connect();
            /*   System.out.println(connection.getErrorStream());*/

            if(connection.getResponseCode() == 200){
                // 获取输入流
                inputStream = connection.getInputStream();
                if(null != inputStream){
                    //请求返回不为空
                    // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, encoding));
                    String temp = null;
                    if(null != (temp = bufferedReader.readLine())){
                        result.append(temp);
                        result.append("\r\n");
                    }
                }else{
                    return null;
                }
            }
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if(null != bufferedReader){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            connection.disconnect();
        }
        return result.toString();
    }

    /**
     * httpClient的get请求
     * @param url 请求地址
     * @return
     */
    public static String doGetByHttpClient(String url){
        return doGetByHttpClient(url, DEFAULT_ECODING, 15000, 60000, 3,
                true);
    }

    /**
     * httpClient的get请求
     * @param url 请求地址
     * @param encoding  编码格式
     * @param connectionTimeout 连接服务器超时时间
     * @param timeout 读取数据超时时间
     * @param retryCount 重连次数
     * @param requestSentRetryEnabled 是否失败重连
     * @return
     */
    public static String doGetByHttpClient(String url, String encoding, int connectionTimeout, int timeout, int retryCount,
                                           boolean requestSentRetryEnabled){
        // 输入流
        InputStream inputStream = null;
        // 读取缓存
        BufferedReader bufferedReader = null;
        // 结果集
        StringBuffer result=new StringBuffer();
        // 创建httpClient实例
        HttpClient httpClient = new HttpClient();
        // 设置http连接主机服务超时时间：15000毫秒
        // 先获取连接管理器对象，再获取参数对象,再进行参数的赋值
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
        // 创建一个Get方法实例对象
        GetMethod getMethod = new GetMethod(url);
        // 设置get请求超时为60000毫秒
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeout);
        // 设置请求重试机制，默认重试次数：3次，参数设置为true，重试机制可用，false相反
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(
                retryCount, requestSentRetryEnabled));
        try {
            // 执行Get方法
            int statusCode = httpClient.executeMethod(getMethod);
            // 判断返回码
            if (statusCode != HttpStatus.SC_OK) {
                // 如果状态码返回的不是ok,说明失败了,打印错误信息
                System.err.println("Method faild: " + getMethod.getStatusLine());
            } else {
                // 通过getMethod实例，获取远程的一个输入流
                inputStream = getMethod.getResponseBodyAsStream();
                // 包装输入流
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, encoding));
                // 读取封装的输入流
                String temp = null;
                while (null != (temp = bufferedReader.readLine())) {
                    result.append(temp).append("\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 释放连接
            getMethod.releaseConnection();
        }
        return result.toString();
    }

    public static String doPostByHttpClient(String url, Map<String , Object> param){
        return doPostByHttpClient(url, param, DEFAULT_ECODING, 15000, 60000);
    }

    /**
     * httpClient的post请求
     * @param url 请求地址
     * @param param 请求入参
     * @param encoding 编码格式
     * @param connectionTimeout 连接超时时间
     * @param timeout 读取超时时间
     * @return
     */
    public static String doPostByHttpClient(String url, Map<String , Object> param, String encoding,
                                           int connectionTimeout, int timeout){
        // 获取输入流
        InputStream inputStream = null;
        // 读取缓存
        BufferedReader bufferedReader = null;
        // 结果集
        StringBuffer result = new StringBuffer();
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
//        httpClient.
        // 创建post请求方法实例对象
        PostMethod postMethod = new PostMethod(url);
        // 设置post请求超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeout);
        postMethod.addRequestHeader("Content-type", DEFAULT_CONTENT_TYPE);

        NameValuePair[] nvp = null;
        // 判断参数map集合paramMap是否为空
        if(null != param && param.size() > 0){
            // 创建键值参数对象数组，大小为参数的个数
            nvp = new NameValuePair[param.size()];
            // 循环遍历参数集合map
            Set<Map.Entry<String, Object>> entrySet = param.entrySet();
            // 获取迭代器
            Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();

            int index = 0;
            while (iterator.hasNext()) {
                Map.Entry<String, Object> mapEntry = iterator.next();
                // 从mapEntry中获取key和value创建键值对象存放到数组中
                try {
                    nvp[index] = new NameValuePair(mapEntry.getKey(),
                            new String(mapEntry.getValue().toString().getBytes(encoding), encoding));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                index++;
            }
        }
        // 判断nvp数组是否为空
        if (null != nvp && nvp.length > 0) {
            // 将参数存放到requestBody对象中
            postMethod.setRequestBody(nvp);
        }
        // 执行POST方法
        try {
            int statusCode = httpClient.executeMethod(postMethod);
            // 判断是否成功
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method faild: " + postMethod.getStatusLine());
            }
            // 获取远程返回的数据
            inputStream = postMethod.getResponseBodyAsStream();
            // 封装输入流
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, encoding));

            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                result.append(temp).append("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 释放连接
            postMethod.releaseConnection();
        }
        return result.toString();
    }

    /**
     * 基于httpClient4的get请求
     * @param url 请求地址
     * @return
     */
    public static String doGetByHttpClient4(String url){
        return doGetByHttpClient4(url, null, 35000, 35000, 60000);
    }

    /**
     * 基于httpClient4的get请求
     * @param url 请求地址
     * @param authorization 鉴权信息
     * @param connectTimeout 连接超时时间
     * @param requestTimeout 请求超时时间
     * @param socketTimeout 读取数据超时时间
     * @return
     */
    public static String doGetByHttpClient4(String url, String authorization, int connectTimeout, int requestTimeout,
                                            int socketTimeout){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";

        try {
            // 通过址默认配置创建一个httpClient实例
            httpClient = HttpClients.createDefault();
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);
            // 设置请求头信息，鉴权(Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0)
            if(null != authorization && !authorization.equals("")){
                httpGet.setHeader("Authorization", authorization);
            }
            // 设置配置请求参数
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 基于httpClient4的post请求
     * @param url 请求地址
     * @param param 请求入参
     * @return
     */
    public static String doPostByClient4(String url,Map<String, Object> param){
        return doPostByClient4(url, param, DEFAULT_ECODING, DEFAULT_CONTENT_TYPE, 35000,
                35000, 6000);
    }

    /**
     * 基于httpClient4的post请求
     * @param url 请求地址
     * @param param 请求参数
     * @param encoding 编码格式
     * @param contentType 请求格式
     * @param connectTimeout 连接主机超时时间
     * @param requestTimeout 连接请求超时时间
     * @param socketTimeout 读取数据连接超时时间
     * @return
     */
    public static String doPostByClient4(String url,Map<String, Object> param, String encoding, String contentType,
                                         int connectTimeout, int requestTimeout, int socketTimeout){

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(requestTimeout)// 设置连接请求超时时间
                .setSocketTimeout(socketTimeout)// 设置读取数据连接超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        if(null != contentType && !contentType.equals("")){
            httpPost.addHeader("Content-Type", contentType);
        }
        // 封装post请求参数
        if (null != param && param.size() > 0) {
            List<BasicNameValuePair> nvps = new ArrayList<>();
            // 通过map集成entrySet方法获取entity
            Set<Map.Entry<String, Object>> entrySet = param.entrySet();
            // 循环遍历，获取迭代器
            Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> mapEntry = iterator.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(),
                        null != mapEntry.getValue() ? mapEntry.getValue().toString() : null));
            }
            // 为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
