package com.dtr.web;

import cn.hutool.core.util.IdUtil;
import com.dtr.helper.HttpRequestUtils;
import com.dtr.helper.MD5Util;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author liudong
 * 2024/3/25 17:07
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/wujing")
public class WuJingController {
    private static final String URL = "https://saas-test.guke.tech/api";
    private static final String OPEN_URL = "http://120.79.249.213:7401/openapi";
    private static final String TOKEN = "/integration/getToken";

    private static final String APP_ID = "j6ZACIDd";
    private static final String APP_SECRET = "9aae7756510d70fd7e420f5391950f01b7a744ed";

    @SneakyThrows
    public static void main(String[] args) {

        String finalToken = getToken();
        if(StringUtils.isBlank(finalToken)){
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        //生成sign timestamp + APP_ID + APP_SECRET 组合后再用MD5加密
        String timestamp = String.valueOf(currentTimeMillis);
        String signString = timestamp + APP_ID + APP_SECRET;
        //当前系统时间戳（毫秒）
        String signature = MD5Util.getMD5(signString);
        //         https://{infynova}/openapi/infra/customer_supplier
        String method = "/customer_supplier";
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("companyNo", "A0001");
        paramMap.put("companyName", "A0001公司");
        paramMap.put("creditCode", "9XXXXXXXXXXXXXXXXXXXXX1");
        paramMap.put("adminName", "A0001管理员");
        paramMap.put("adminMobile", "13418640001");
        paramMap.put("status", "1");
        String post = forPost(OPEN_URL, method, paramMap, null);
        System.out.println("customer_supplier: " + post);

    }

    private static String getToken(){
        String tokenUrl = URL + TOKEN;

        String finalToken = null;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建POST请求
            HttpPost httpPost = new HttpPost(tokenUrl);
            httpPost.addHeader("Content-Type", "application/json; charset=utf-8");

            // 构建请求体参数
            JSONObject requestBody = new JSONObject();
            requestBody.put("appId", APP_ID);
            requestBody.put("appSecret", APP_SECRET);
            StringEntity requestEntity = new StringEntity(requestBody.toJSONString());
            httpPost.setEntity(requestEntity);

            // 执行POST请求
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                String responseBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);

                // 打印响应结果
                System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
                System.out.println("Response Body: " + responseBody);

                // 解析JSON响应
                JSONObject jsonResponse = JSON.parseObject(responseBody);
                if (jsonResponse.getInteger("code") == 200) {
                    JSONObject data = jsonResponse.getJSONObject("data");
                    String token = data.getString("token");
                    String expireTime = data.getString("expireTime");
                    System.out.println("Token: " + token);
                    System.out.println("Expire Time: " + expireTime);
                    finalToken = token;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("msg"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalToken;
    }

    /**
     * post调用例子
     * @param method   调用业务API的url
     * @param paramMap Query参数
     * @param json     body参数
     * @return 返回
     * @throws Exception
     */
    public static String forPost(String openUrl, String method, HashMap<String, String> paramMap, String json) throws Exception {
        //headers参数
        HashMap<String, String> headersMap = new HashMap<>();
        //获取的token
        String token = getToken();
        //当前系统时间戳搓，单位：毫秒
        String timestamp = String.valueOf(System.currentTimeMillis());
        //随便生成一段随机字符串就行
        String nonce = IdUtil.simpleUUID().substring(0, 6);
        //token
        headersMap.put("token", token);
        //时间戳,毫秒
        headersMap.put("timestamp", timestamp);
        //随机字符串
        headersMap.put("nonce", nonce);
        // 请求参数 + key + token + timestamp + nonce   生成sign
        String signString = concatSignString(paramMap) + APP_SECRET + token + timestamp + nonce;
        String sign = MD5Util.getMD5(signString);
        headersMap.put("sign", sign);
        //返回结果
        return HttpRequestUtils.postJson(openUrl + method, paramMap, json, headersMap);
    }

    /**
     * 根据参数名称的ASCII码表的顺序排序
     * @param map query参数
     * @return string
     */
    public static String concatSignString(Map<String, String> map) {
        Map<String, String> paramMap = new HashMap<>();
        map.forEach((key, value) -> paramMap.put(key, value));
        // 按照key升续排序，然后拼接参数
        Set<String> keySet = paramMap.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (paramMap.get(k).trim().length() > 0) {
                // 参数值为空，则不参与签名
                sb.append(k).append("=").append(paramMap.get(k).trim()).append("&");
            }
        }
        return sb.toString();
    }

}
