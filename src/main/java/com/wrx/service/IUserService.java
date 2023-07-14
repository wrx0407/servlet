package com.wrx.service;

import com.wrx.core.Page;
import com.wrx.entity.User;
import com.wrx.vo.QueryParams;
import com.wrx.vo.ResetPwdVO;

/**
 * <p>
 * 用户业务层接口
 * </p>
 *
 * @author 王荣幸
 * @since 2023/6/26
 */
public interface IUserService {

    /**
     * 用户登陆
     *
     * @param user 封装的用户名、密码
     * @return 查询到底用户信息
     */
    User login(User user);

    /**
     * 根据 ID 查询用户
     *
     * @param id 用户 ID
     * @return 用户信息
     */
    User getUserById(Integer id);

    /**
     * 根据条件分页查询用户列表
     *
     * @param pageNum     当前页
     * @param pageSize    查询条数
     * @param queryParams 查询条件
     * @return 分页对象
     */
    Page<User> getUserList(Integer pageNum, Integer pageSize, QueryParams queryParams);

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return 影响的行数
     */
    int save(User user);

    /**
     * 根据ID修改用户
     *
     * @param user 需要修改的信息及ID
     * @return 影响的行数
     */
    int updateById(User user);

    /**
     * 重置密码
     *
     * @param pwdVO 重置密码视图对象
     * @return 影响的行数
     */
    int resetPwd(ResetPwdVO pwdVO);

    /**
     * 根据ID删除用户
     *
     * @param id 用户ID
     * @return 影响的行数
     */
    int deleteById(Integer id);
}
