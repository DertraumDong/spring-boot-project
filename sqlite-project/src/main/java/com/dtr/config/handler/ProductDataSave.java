package com.dtr.config.handler;


import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dtr.bean.Product;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import cn.hutool.core.date.LocalDateTimeUtil;
import org.springframework.util.CollectionUtils;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author liudong
 * 2024/3/15 16:40
 * @version 1.0
 */
@Slf4j
public class ProductDataSave {
    // 数据库连接信息
    private static final String DB_URL = "jdbc:sqlite:D:/tool/SQLite/udi.db";


    private static final String BASE_URL = "http://localhost:7801";
    private static final String PRODUCT_URL = "/udi/udiList/list";

    public static void main(String[] args) {
        mainMethod();
    }

    public static void mainMethod(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ProductDataSave importer = new ProductDataSave();
        importer.getProductDataFromAPI();
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTime();
        double seconds = totalTimeMillis / 1000.0; // 转换为秒
        log.info("Total time: " + totalTimeMillis + " ms");
        log.info("Seconds: " + seconds);
    }

    // 接口获取产品数据的方法
    public void getProductDataFromAPI() {
        // 在这里实现从接口获取产品数据的逻辑
        // 返回一个包含产品对象的列表
        String createOrderUrl = BASE_URL + PRODUCT_URL;
        int pageNo = 1;
        int pageSize = 1000;
        List<Product> productList = new ArrayList<>();
        while (true){
            PageVo pageVo = new PageVo();
            pageVo.setPageNo(pageNo);
            pageVo.setPageSize(pageSize);
            String requestBody = JSON.toJSONString(pageVo);
            log.info("查询udi数据库：\n {}" , requestBody);
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(createOrderUrl);

            try {
                StringEntity entity = new StringEntity(requestBody, StandardCharsets.UTF_8);
                entity.setContentType("application/json");
                httpPost.setEntity(entity);

                HttpResponse response = httpClient.execute(httpPost);
                String string = EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8);
                // 将 JSON 字符串解析为 JSONObject 对象
                JSONObject jsonObject = JSON.parseObject(string);

                // 从 JSONObject 中获取 data 字段的值
                JSONObject dataObject = jsonObject.getJSONObject("data");

                // 从 dataObject 中获取 records 字段的值
                JSONArray recordsArray = dataObject.getJSONArray("records");
                if(recordsArray.isEmpty()){
                    log.info("查询结束：page:{}",JSON.toJSONString(pageVo));
                    break;
                }
                productList = recordsArray.toJavaList(Product.class);
                log.info("循环,{},{}",pageNo,pageSize);

                saveProductDataToSQLite(productList);
            } catch (Exception e) {
                e.printStackTrace();
                log.info("循环错误中断：page:{}",JSON.toJSONString(pageVo));
                break;
            }
            pageNo++;
        }
    }

    @Data
    private static class PageVo{
        private int pageNo = 1;
        private int pageSize = 20;
    }

//    public Product getProduct(int i){
//        return Product.builder().udiCode("69"+i).yiBaoCode("C03"+i).build();
//    }

    // 将产品数据保存到SQLite数据库
    private void saveProductDataToSQLite(List<Product> productList) {
        if(CollectionUtils.isEmpty(productList)){
            log.info("数据为空不保存；");
            return ;
        }
        int batchSize = 1000;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO product(id, udi_code, yi_bao_one_code, yi_bao_two_code, yi_bao_code, yi_bao_code_prefix, " +
                    "company_name, brand_name, registry_no, registry_name, registry_start_time, registry_end_time, registry, " +
                    "product_code, product_factory_code, infynova_code, product_name, product_type, specification, model, material, aseptic_packaging, before_sterilize, sterilization_method, " +
                    "yj_foreign_id, yb_foreign_id, source_name, create_time, update_time, version)" +
                    " VALUES " +
                    " (?, ?, ?, ?, ?, ?," +
                    " ?, ?, ?, ?, ?, ?, ?," +
                    " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                    " ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                conn.setAutoCommit(false);
                for (int i = 0; i < productList.size(); i++) {
                    Product product = productList.get(i);

                    pstmt.setInt(1, Math.toIntExact(product.getId()));
                    pstmt.setString(2, product.getUdiCode());
                    pstmt.setString(3, product.getYiBaoOneCode());
                    pstmt.setString(4, product.getYiBaoTwoCode());
                    pstmt.setString(5, product.getYiBaoCode());
                    pstmt.setString(6, product.getYiBaoCodePrefix());

                    pstmt.setString(7, product.getCompanyName());
                    pstmt.setString(8, product.getBrandName());
                    pstmt.setString(9, product.getRegistryNo());
                    pstmt.setString(10, product.getRegistryName());
                    pstmt.setString(11, LocalDateTimeUtil.format(product.getRegistryStartTime(), DatePattern.NORM_DATE_FORMATTER));
                    pstmt.setString(12, LocalDateTimeUtil.format(product.getRegistryEndTime(), DatePattern.NORM_DATE_FORMATTER));
                    pstmt.setString(13, product.getRegistry());

                    pstmt.setString(14, product.getProductCode());
                    pstmt.setString(15, product.getProductFactoryCode());
                    pstmt.setString(16, product.getInfynovaCode());
                    pstmt.setString(17, product.getProductName());
                    pstmt.setString(18, product.getProductType());
                    pstmt.setString(19, product.getSpecification());
                    pstmt.setString(20, product.getModel());
                    pstmt.setString(21, product.getMaterial());
                    pstmt.setInt(22, product.getAsepticPackagingValue());
                    pstmt.setInt(23, product.getBeforeSterilizeValue());
                    pstmt.setString(24, product.getSterilizationMethod());

                    pstmt.setString(25, product.getYjForeignId());
                    pstmt.setString(26, product.getYbForeignId());
                    pstmt.setString(27, product.getSourceName());
                    pstmt.setString(28, LocalDateTimeUtil.format(product.getCreateTime(), DatePattern.NORM_DATETIME_FORMATTER));
                    pstmt.setString(29, LocalDateTimeUtil.format(product.getUpdateTime(), DatePattern.NORM_DATETIME_FORMATTER));
                    pstmt.setString(30, product.getVersion());

                    pstmt.addBatch();

                    if (i % batchSize == 0) {
                        pstmt.executeBatch();
                        conn.commit();
                    }
                }

                pstmt.executeBatch(); // 最后一次提交剩余的数据
                conn.commit();
                System.out.println("Product data inserted successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error saving product data to SQLite: " + e.getMessage());
        }
    }



}

