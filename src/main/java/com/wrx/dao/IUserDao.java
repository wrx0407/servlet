package com.wrx.dao;

import com.wrx.entity.User;
import com.wrx.vo.QueryParams;

import java.util.List;

/**
 * <p>
 * 用户持久层接口
 * </p>
 *
 * @author 王荣幸
 * @since 2023/6/26
 */
public interface IUserDao {
    /**
     * 查询单个用户
     *
     * @param user 查询条件
     * @return 查询结果
     */
    User getUser(User user);


    /**
     * 查询记录总条数
     *
     * @param queryParams 查询条件
     * @return 总条数
     */
    int getUserTotal(QueryParams queryParams);

    /**
     * 根据条件分页查询用户列表
     *
     * @param pageNum     当前页
     * @param pageSize    查询条数
     * @param queryParams 查询条件
     * @return 对象集合
     */
    List<User> getUserList(Integer pageNum, Integer pageSize, QueryParams queryParams);

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
     * 删除用户
     *
     * @param id 用户ID
     * @return 影响的行数
     */
    int deleteById(Integer id);
}
