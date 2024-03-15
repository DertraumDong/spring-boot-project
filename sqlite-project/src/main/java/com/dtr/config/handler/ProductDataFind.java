package com.dtr.config.handler;


import com.dtr.bean.Product;

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
public class ProductDataFind {
    // 数据库连接信息
    private static final String DB_URL = "jdbc:sqlite:D:/tool/SQLite/udi.db";


    // 将产品数据保存到SQLite数据库
    public void findProductDataToSQLite(String udiCode) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "select * from product_test where udi_code = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, udiCode);
                pstmt.executeUpdate();

                System.out.println("Product data select successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error select product data to SQLite: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ProductDataFind importer = new ProductDataFind();
        String udiCode = "测试一下6912";
        importer.findProductDataToSQLite(udiCode);
    }
}

