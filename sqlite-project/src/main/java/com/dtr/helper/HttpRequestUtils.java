package com.dtr.helper;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http 请求工具类
 */
@Slf4j
public final class HttpRequestUtils {
    //设置字符编码
    private static final String CHARSET = "UTF-8";

    private static final RestTemplate restTemplate;
    static {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // 连接超时：30秒
        requestFactory.setConnectTimeout(60*1000);
        // 读写超时：30分钟
        requestFactory.setReadTimeout(30*60*1000);
        restTemplate =new RestTemplate(requestFactory);
    }

    //释放资源，httpResponse为响应流，httpClient为请求客户端
    private static void release(CloseableHttpResponse httpResponse, CloseableHttpClient httpClient) throws IOException {
        if (httpResponse != null) {
            httpResponse.close();
        }
        if (httpClient != null) {
            httpClient.close();
        }
    }

    /**
     * 发送GET请求
     *
     * @param url        url
     * @param paramMap   Params
     * @param headersMap head
     */
    public static String get(String url, Map<String, String> paramMap, Map<String, String> headersMap) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URIBuilder uriBuilder = new URIBuilder(url);
        if (!CollectionUtils.isEmpty(paramMap)) {
            List<NameValuePair> params = new ArrayList<>();
            for (String key : paramMap.keySet()) {
                params.add(new BasicNameValuePair(key, paramMap.get(key)));
            }
            uriBuilder.setParameters(params);
        }
        HttpGet httpget = new HttpGet(uriBuilder.build());
        if (!CollectionUtils.isEmpty(headersMap)) {
            headersMap.forEach(httpget::addHeader);
        }
        CloseableHttpResponse response = httpClient.execute(httpget);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, CHARSET);
        httpget.releaseConnection();
        release(response, httpClient);
        return result;
    }


    /**
     * 发送POST请求
     *
     * @param url        url
     * @param paramMap   Params
     * @param json       body
     * @param headersMap head
     */
    public static String postJson(String url, Map<String, String> paramMap, String json, Map<String, String> headersMap) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URIBuilder uriBuilder = new URIBuilder(url);
        if (!CollectionUtils.isEmpty(paramMap)) {
            List<NameValuePair> params = new ArrayList<>();
            for (String key : paramMap.keySet()) {
                params.add(new BasicNameValuePair(key, paramMap.get(key)));
            }
            uriBuilder.setParameters(params);
        }
        if (!CollectionUtils.isEmpty(headersMap)){
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        org.springframework.http.HttpEntity<String> request = new org.springframework.http.HttpEntity<>(json, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(uriBuilder.build(), request, String.class);
        return response.getBody();
    }

    public static String portalPostJson(String url,String userName,String password, Map<String, String> paramMap, String json, Map<String, String> headersMap) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //账号密码
        headers.setBasicAuth(userName,password);

        URIBuilder uriBuilder = new URIBuilder(url);
        if (!CollectionUtils.isEmpty(paramMap)) {
            List<NameValuePair> params = new ArrayList<>();
            for (String key : paramMap.keySet()) {
                params.add(new BasicNameValuePair(key, paramMap.get(key)));
            }
            uriBuilder.setParameters(params);
        }
        for (Map.Entry<String, String> entry : headersMap.entrySet()) {
            headers.add(entry.getKey(), entry.getValue());
        }
        org.springframework.http.HttpEntity<String> request = new org.springframework.http.HttpEntity<>(json, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(uriBuilder.build(), request, String.class);
        return response.getBody();
    }


    public static void main(String[] args) throws Exception {
//        Map<String, String> header = new HashMap<>();
//        header.put("token","8882872e-cc12-4e51-8f6a-591bbf423feb");
//        header.put("timestamp","1658909039296");
//        header.put("nonce","112");
//        header.put("sign","50dcc0b9c63a56345a10add37a7f7894");
//
//
//        Map<String, String> paramMap = new HashMap<>();
//        paramMap.put("appId","");
//        String s = get("http://localhost:7401/openapi/token/test",paramMap,header);
//        System.out.println(s);


        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", "j6ZACIDd");


        Map<String, String> header = new HashMap<>();
        header.put("timestamp", "1658914126297");
        header.put("sign", "ba510f0e3ced103e6219d398a61e1a92");
        String s = postJson("http://localhost:7401/openapi/token/api-token", paramMap, "", header);
        System.out.println(s);


    }

}