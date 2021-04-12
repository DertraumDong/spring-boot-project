package com.dtr.bean;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Created on 2021/4/11.
 *
 * @author LiuDong
 */
@Data
@Entity
public class CatEmailLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "不能为空")
    //@Column(name = "email_context", nullable = true, length = 100)
    private String emailContext;

    //@Column(name = "create_user_id", nullable = true, length = 20)
    private String createUserId;

    //@Column(name = "create_time", nullable = true)
    private Date createTime;

    //@Column(name = "updateUserId", nullable = true, length = 20)
    private String updateUserId;

    //@Column(name = "createTime", nullable = true)
    private Date updateTime;

    private Integer version;
}
