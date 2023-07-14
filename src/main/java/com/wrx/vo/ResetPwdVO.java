package com.wrx.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 接收 重置密码
 * </p>
 *
 * @author 王荣幸
 * @since 2023/6/10
 */
@Getter
@Setter
@ToString
public class ResetPwdVO {
    private Integer id;

    private String password;

    private String newPwd;
}
