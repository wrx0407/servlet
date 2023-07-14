package com.wrx.dao.impl;

import com.wrx.core.JdbcUtil;
import com.wrx.dao.IUserDao;
import com.wrx.entity.User;
import com.wrx.vo.QueryParams;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户持久层实现类
 * </p>
 *
 * @author 王荣幸
 * @since 2023/6/26
 */
public class UserDaoImpl implements IUserDao {
    /**
     * 查询单个用户
     *
     * @param user 查询条件
     * @return 查询结果
     */
    @Override
    public User getUser(User user) {
        StringBuilder sql = new StringBuilder("select id, username, sex, age, phone, create_time from user where 1=1 ");
        List<Object> list = new ArrayList<>();
        if (user.getId() != null) {
            sql.append("and id = ? ");
            list.add(user.getId());
        }
        if (user.getUsername() != null && !user.getUsername().equals("")) {
            sql.append("and username = ? ");
            list.add(user.getUsername());
        }
        if (user.getPassword() != null && !user.getPassword().equals("")) {
            sql.append("and password = ? ");
            list.add(user.getPassword());
        }
        return JdbcUtil.queryOne(User.class, sql.toString(), list);
    }

    /**
     * 封装条件查询 SQL
     *
     * @param sql         初始 SQL
     * @param queryParams 查询条件
     * @return 预编译 SQL 参数列表
     */
    private List<Object> setUserSql(StringBuilder sql, QueryParams queryParams) {
        List<Object> list = new ArrayList<>();
        if (queryParams.getUsername() != null && !queryParams.getUsername().equals("")) {
            sql.append("and username like concat('%', ? , '%') ");
            list.add(queryParams.getUsername());
        }
        if (queryParams.getSex() != null && !queryParams.getSex().equals("")) {
            sql.append("and sex = ? ");
            list.add(queryParams.getSex());
        }
        if (queryParams.getAge() != null) {
            sql.append("and age = ? ");
            list.add(queryParams.getAge());
        }
        if (queryParams.getPhone() != null && !queryParams.getPhone().equals("")) {
            sql.append("and phone like concat('%', ? , '%') ");
            list.add(queryParams.getPhone());
        }
        if (queryParams.getBeginTime() != null) {
            sql.append("and date_format(create_time, '%y%m%d') >= date_format( ?, '%y%m%d') ");
            list.add(queryParams.getBeginTime());
        }
        if (queryParams.getEndTime() != null) {
            sql.append("and date_format(create_time, '%y%m%d') <= date_format( ?, '%y%m%d') ");
            list.add(queryParams.getEndTime());
        }
        return list;
    }

    /**
     * 查询记录总条数
     *
     * @param queryParams 查询条件
     * @return 总条数
     */
    @Override
    public int getUserTotal(QueryParams queryParams) {
        StringBuilder sql = new StringBuilder("select count(id) from user where 1=1 ");
        final List<Object> list = this.setUserSql(sql, queryParams);
        return JdbcUtil.count(sql.toString(), list);
    }

    /**
     * 根据条件分页查询用户列表
     *
     * @param pageNum     当前页
     * @param pageSize    查询条数
     * @param queryParams 查询条件
     * @return 对象集合
     */
    @Override
    public List<User> getUserList(Integer pageNum, Integer pageSize, QueryParams queryParams) {
        StringBuilder sql = new StringBuilder("select id, username, sex, age, phone,create_time from user where 1=1 ");
        List<Object> list = this.setUserSql(sql, queryParams);
        /*
         * 分页
         */
        sql.append("limit ?, ? ");
        list.add((pageNum - 1) * pageSize);
        list.add(pageSize);
        return JdbcUtil.queryList(User.class, sql.toString(), list);
    }

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return 影响的行数
     */
    @Override
    public int save(User user) {
        String sql = "insert into user (username, password, sex, age, phone, create_by, create_time) values ( ?, ?, ?, ?, ?, ?, NOW())";
        List<Object> list = new ArrayList<>();
        list.add(user.getUsername());
        list.add(user.getPassword());
        list.add(user.getSex());
        list.add(user.getAge());
        list.add(user.getPhone());
        list.add(user.getCreateBy());
        return JdbcUtil.exeUpdate(sql, list);
    }

    /**
     * 根据ID修改用户
     *
     * @param user 需要修改的信息及ID
     * @return 影响的行数
     */
    @Override
    public int updateById(User user) {
        StringBuilder sql = new StringBuilder("update user set ");
        List<Object> list = new ArrayList<>();
        if (user.getUsername() != null && !user.getUsername().equals("")) {
            sql.append("username = ?, ");
            list.add(user.getUsername());
        }
        if (user.getPassword() != null && !user.getPassword().equals("")) {
            sql.append("password = ?, ");
            list.add(user.getPassword());
        }
        if (user.getSex() != null && !user.getSex().equals("")) {
            sql.append("sex = ?, ");
            list.add(user.getSex());
        }
        if (user.getAge() != null) {
            sql.append("age = ?, ");
            list.add(user.getAge());
        }
        if (user.getPhone() != null && !user.getPhone().equals("")) {
            sql.append("phone = ?, ");
            list.add(user.getPhone());
        }
        /*
         * 没有修改条件则返回
         */
        if (list.size() == 0 || user.getId() == null) {
            return 0;
        }
        /*
         * 去掉逗号最后一个逗号
         */
        sql.delete(sql.length() - 2, sql.length() - 1)
                .append("where id = ? ");
        list.add(user.getId());
        return JdbcUtil.exeUpdate(sql.toString(), list);
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 影响的行数
     */
    @Override
    public int deleteById(Integer id) {
        List<Integer> list = new ArrayList<>();
        list.add(id);
        return JdbcUtil.exeUpdate("delete from user where id = ? ", list);
    }
}
