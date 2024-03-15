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
public class ProductTest implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String udiCode;

    private String yiBaoCode;

}
