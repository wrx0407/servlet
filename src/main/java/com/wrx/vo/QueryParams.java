package com.wrx.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * 分页查询参数
 * </p>
 *
 * @author 王荣幸
 * @since 2023/6/26
 */
@Getter
@Setter
@ToString
public class QueryParams {
    /**
     * 用户名
     */
    private String username;
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

    private Date beginTime;
    private Date endTime;
}
