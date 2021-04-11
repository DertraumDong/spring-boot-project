package com.dtr.bean.dto;

import com.dtr.base.dto.BaseQueryVO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created on 2021/4/11.
 *
 * @author LiuDong
 */
@Data
public class CatEmailLogDto extends BaseQueryVO {
    private Integer id;
    private String emailContext;
}
