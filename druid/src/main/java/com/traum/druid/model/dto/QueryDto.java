package com.traum.druid.model.dto;

import com.dtr.base.dto.BaseQueryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 名称：
 * 阐述：
 *
 * @author Administrator
 * @date 2022/7/11 18:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryDto {
    private String userId;
    private String tendId;
}
