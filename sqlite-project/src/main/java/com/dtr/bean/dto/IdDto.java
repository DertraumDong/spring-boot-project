package com.dtr.bean.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author liudong
 * 2024/4/19 16:09
 * @version 1.0
 */
@Data
public class IdDto {
    @NotNull(message = "id不能为空")
    private Long id;
}
