package com.dtr.sp.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

public class TcUser {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tc_user.id
     *
     * @mbg.generated Tue Dec 01 21:46:20 CST 2020
     */
    @ApiModelProperty(value = "ID")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tc_user.username
     *
     * @mbg.generated Tue Dec 01 21:46:20 CST 2020
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_user.id
     *
     * @return the value of tc_user.id
     *
     * @mbg.generated Tue Dec 01 21:46:20 CST 2020
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_user.id
     *
     * @param id the value for tc_user.id
     *
     * @mbg.generated Tue Dec 01 21:46:20 CST 2020
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_user.username
     *
     * @return the value of tc_user.username
     *
     * @mbg.generated Tue Dec 01 21:46:20 CST 2020
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_user.username
     *
     * @param username the value for tc_user.username
     *
     * @mbg.generated Tue Dec 01 21:46:20 CST 2020
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
}