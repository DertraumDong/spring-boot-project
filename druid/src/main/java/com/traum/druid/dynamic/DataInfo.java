package com.traum.druid.dynamic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 名称：
 * 阐述：
 *
 * @author Administrator
 * @date 2022/7/11 18:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataInfo {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
