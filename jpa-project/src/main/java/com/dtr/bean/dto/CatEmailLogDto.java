package com.dtr.bean.dto;

import com.dtr.base.dto.BaseQueryDTO;
import lombok.Data;

/**
 * Created on 2021/4/11.
 *
 * @author LiuDong
 */
@Data
public class CatEmailLogDto extends BaseQueryDTO {
    private Integer id;
    private String emailContext;
}
