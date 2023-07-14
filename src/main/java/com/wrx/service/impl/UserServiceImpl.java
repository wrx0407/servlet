package com.wrx.service.impl;

import com.wrx.AppFactory;
import com.wrx.core.Page;
import com.wrx.dao.IUserDao;
import com.wrx.entity.User;
import com.wrx.service.IUserService;
import com.wrx.vo.QueryParams;
import com.wrx.vo.ResetPwdVO;

import java.util.List;

/**
 * <p>
 * 用户业务层实现类
 * </p>
 *
 * @author 王荣幸
 * @since 2023/6/26
 */
public class UserServiceImpl implements IUserService {
    private final IUserDao userDao = AppFactory.getUserDao();

    /**
     * 用户登陆
     *
     * @param user 封装的用户名、密码
     * @return 查询到底用户信息
     */
    @Override
    public User login(User user) {
        return userDao.getUser(user);
    }

    /**
     * 根据 ID 查询用户
     *
     * @param id 用户 ID
     * @return 用户信息
     */
    @Override
    public User getUserById(Integer id) {
        User user = new User();
        user.setId(id);
        return userDao.getUser(user);
    }

    /**
     * 根据条件分页查询用户列表
     *
     * @param pageNum     当前页
     * @param pageSize    查询条数
     * @param queryParams 查询条件
     * @return 分页对象
     */
    @Override
    public Page<User> getUserList(Integer pageNum, Integer pageSize, QueryParams queryParams) {
        int userTotal = userDao.getUserTotal(queryParams);
        Page<User> page = new Page<>(pageNum, pageSize, userTotal);
        List<User> userList = userDao.getUserList(page.getPageNum(), page.getPageSize(), queryParams);
        page.setResult(userList);
        return page;
    }

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return 影响的行数
     */
    @Override
    public int save(User user) {
        return userDao.save(user);
    }

    /**
     * 根据ID修改用户
     *
     * @param user 需要修改的信息及ID
     * @return 影响的行数
     */
    @Override
    public int updateById(User user) {
        return userDao.updateById(user);
    }

    /**
     * 重置密码
     *
     * @param pwdVO 重置密码视图对象
     * @return 影响的行数
     */
    @Override
    public int resetPwd(ResetPwdVO pwdVO) {
        User user = new User();
        user.setId(pwdVO.getId());
        user.setPassword(pwdVO.getPassword());
        if (userDao.getUser(user) == null) {
            return -1;
        }
        user.setPassword(pwdVO.getNewPwd());
        return userDao.updateById(user);
    }

    /**
     * 根据ID删除用户
     *
     * @param id 用户ID
     * @return 影响的行数
     */
    @Override
    public int deleteById(Integer id) {
        return userDao.deleteById(id);
    }
}
