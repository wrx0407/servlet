package com.wrx;

import com.wrx.dao.IUserDao;

import static org.junit.Assert.*;

import com.wrx.entity.User;
import com.wrx.vo.QueryParams;
import org.junit.Test;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 王荣幸
 * @since 2023/6/26
 */
public class UserDaoTest {
    private final IUserDao userDao = AppFactory.getUserDao();

    @Test
    public void getUser() {
        User user = new User();
        //user.setId(1);
        user.setUsername("admin");
        user.setPassword("1234");
        assertNotNull(userDao.getUser(user));
    }

    @Test
    public void getUserTotal() {
        QueryParams queryParams = new QueryParams();
        queryParams.setUsername("C");
        queryParams.setPhone("13");
        queryParams.setEndTime(new Date());
        assertTrue(userDao.getUserTotal(queryParams) > 0);
    }

    @Test
    public void getUserList() {
        QueryParams queryParams = new QueryParams();
        queryParams.setUsername("C");
        queryParams.setPhone("13");
        queryParams.setEndTime(new Date());
        assertTrue(userDao.getUserList(1, 3, queryParams).size() > 0);
    }

    @Test
    public void save() {
        User user = new User();
        user.setUsername("测试用户");
        user.setPassword("123456");
        user.setSex("0");
        user.setAge(18);
        user.setPhone("18123123123");
        user.setCreateBy(1);
        assertTrue(userDao.save(user) > 0);
    }

    @Test
    public void updateById() {
        User user = new User();
        user.setId(203);
        user.setUsername("测试修改用户");
        user.setSex("2");
        user.setAge(30);
        user.setPhone("123456");
        assertTrue(userDao.updateById(user) > 0);
    }

    @Test
    public void deleteById() {
        assertTrue(userDao.deleteById(202) > 0);
    }
}
