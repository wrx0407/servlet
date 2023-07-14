package com.wrx.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * 用户实体
 * </p>
 *
 * @author 王荣幸
 * @since 2023/6/26
 */
@Getter
@Setter
@ToString
public class User {
    /**
     * 用户 ID
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别（0：未知、1：男、2：女）
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 电话号码
     */
    private String phone;

    /**
     * 创建人
     */
    private Integer createBy;
    /**
     * 创建时间
     */
    private Date createTime;
}
