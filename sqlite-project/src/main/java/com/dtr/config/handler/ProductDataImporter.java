package com.dtr.config.handler;


import com.dtr.bean.Product;
import org.apache.commons.lang3.time.StopWatch;

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
public class ProductDataImporter {
    // 数据库连接信息
    private static final String DB_URL = "jdbc:sqlite:D:/tool/SQLite/udi.db";

    // 接口获取产品数据的方法
    public List<Product> getProductDataFromAPI() {
        // 在这里实现从接口获取产品数据的逻辑
        // 返回一个包含产品对象的列表
        List<Product> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(Product.builder().udiCode("69"+i).yiBaoCode("C03"+i).build());
        }
        return list;
    }

    public Product getProduct(int i){
        return Product.builder().udiCode("69"+i).yiBaoCode("C03"+i).build();
    }

    // 将产品数据保存到SQLite数据库
    public void saveProductDataToSQLite(List<Product> productList) {
        int batchSize = 1000;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO product( udi_code, yi_bao_one_code, yi_bao_two_code, yi_bao_code, yi_bao_code_prefix, " +
                    "company_name, brand_name, registry_no, registry_name, registry_start_time, registry_end_time, registry, " +
                    "product_code, product_factory_code, infynova_code, product_name, product_type, specification, model, material, aseptic_packaging, before_sterilize, sterilization_method, " +
                    "yj_foreign_id, yb_foreign_id, source_name, create_time, update_time, version)" +
                    " VALUES " +
                    "(?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                conn.setAutoCommit(false);

                for (int i = 0; i < 3000000; i++) {
                    //
                    pstmt.setString(1, "69389544990"+i);
                    pstmt.setString(2, "C033803107000"+i);
                    pstmt.setString(3, "C03380310700000"+i);
                    pstmt.setString(4, "C0338031070000000"+i);
                    pstmt.setString(5, "C03");

                    //
                    pstmt.setString(6, "深圳市无境创新有限公司");
                    pstmt.setString(7, "无境创新");
                    pstmt.setString(8, "国械注准20240000001");
                    pstmt.setString(9, "人工踝关节假体");
                    pstmt.setString(10, "2000-01-01");
                    pstmt.setString(11, "2099-01-01");
                    pstmt.setString(12, "深圳市无境创新有限公司");

                    //
                    pstmt.setString(13, "infynova-DEMO-001"+i);
                    pstmt.setString(14, "0116001");
                    pstmt.setString(15, "infynova-DEMO-001"+i);
                    pstmt.setString(16, "人工踝关节假体-跟距骨假体QH型26×26×23");
                    pstmt.setString(17, "耗材");
                    pstmt.setString(18, "QH型 26×26×23");
                    pstmt.setString(19, "QH型 26×26×23");
                    pstmt.setString(20, "不区分");
                    pstmt.setString(21, "否");
                    pstmt.setString(22, "是");
                    pstmt.setString(23, "按照说明书要求进行灭菌");

                    //
                    pstmt.setString(24, null);
                    pstmt.setString(25, null);
                    pstmt.setString(26, null);
                    pstmt.setString(27, "2024-03-15 12:00:00");
                    pstmt.setString(28, "2024-03-15 12:00:00");
                    pstmt.setString(29, "1");

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

    public static void main(String[] args) {
        ProductDataImporter importer = new ProductDataImporter();
        //List<Product> productList = importer.getProductDataFromAPI();
        List<Product> productList = new ArrayList<>();
        /*for (int i = 0; i < 10000; i++) {
            productList.add(importer.getProduct(i));
        }*/
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        importer.saveProductDataToSQLite(productList);
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTime();
        double seconds = totalTimeMillis / 1000.0; // 转换为秒
        System.out.println("Total time: " + totalTimeMillis + " ms");
        System.out.println("Seconds: " + seconds);
    }
}

