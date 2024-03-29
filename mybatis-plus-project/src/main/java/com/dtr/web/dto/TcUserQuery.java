package com.dtr.web.dto;

import com.dtr.base.dto.BaseQueryDTO;
import lombok.Data;

@Data
public class TcUserQuery extends BaseQueryDTO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tc_user.id
     *
     * @mbg.generated Tue Dec 01 21:46:20 CST 2020
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tc_user.username
     *
     * @mbg.generated Tue Dec 01 21:46:20 CST 2020
     */
    private String username;
}
