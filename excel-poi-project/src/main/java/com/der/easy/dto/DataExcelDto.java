package com.der.easy.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liudong
 * 2023/6/19 14:55
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataExcelDto {
    @ExcelProperty("A")
    private String dataA;
    @ExcelProperty("B")
    private String dataB;
}
