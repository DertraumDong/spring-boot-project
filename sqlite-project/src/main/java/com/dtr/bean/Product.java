package com.dtr.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author liudong
 * 2024/3/15 14:18
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String udiCode;

    private String yiBaoOneCode;

    private String yiBaoTwoCode;

    private String yiBaoCode;

    private String yiBaoCodePrefix;

    private String companyName;

    private String brandName;

    private String registryNo;

    private String registryName;

    private LocalDate registryStartTime;

    private LocalDate registryEndTime;

    private String registry;

    private String productCode;

    private String productFactoryCode;

    private String infynovaCode;

    private String productName;

    private String productType;

    private String specification;

    private String model;

    private String material;

    private Boolean asepticPackaging;

    private Boolean beforeSterilize;

    private String sterilizationMethod;

    private String yjForeignId;

    private String ybForeignId;

    private String sourceName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Version
    @TableField(fill = FieldFill.INSERT_UPDATE, update="%s+1")
    private String version;
}
