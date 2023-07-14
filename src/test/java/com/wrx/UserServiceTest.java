package com.wrx;

import com.wrx.core.Page;
import com.wrx.entity.User;
import com.wrx.service.IUserService;

import static org.junit.Assert.*;

import com.wrx.vo.QueryParams;
import com.wrx.vo.ResetPwdVO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 王荣幸
 * @since 2023/6/26
 */
public class UserServiceTest {
    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);
    private final IUserService userService = AppFactory.getUserService();

    @Test
    public void login() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("1234");
        assertNotNull(userService.login(user));
    }

    @Test
    public void getUserById() {
        assertNotNull(userService.getUserById(1));
    }

    @Test
    public void getUserList() {
        QueryParams queryParams = new QueryParams();
        queryParams.setUsername("C");
        queryParams.setPhone("13");
        queryParams.setEndTime(new Date());
        final Page<User> userPage = userService.getUserList(1, 3, queryParams);
        assertTrue(userPage.getTotal() > 0);
        log.info("{}", userPage);
    }

    @Test
    public void save() {
        User user = new User();
        user.setUsername("测试新增用户");
        user.setPassword("123456");
        user.setSex("0");
        user.setAge(18);
        user.setPhone("18123123123");
        user.setCreateBy(1);
        assertTrue(userService.save(user) > 0);
    }

    @Test
    public void updateById() {
        User user = new User();
        user.setId(204);
        user.setUsername("测试修改用户");
        user.setSex("2");
        user.setAge(30);
        user.setPhone("123456");
        assertTrue(userService.updateById(user) > 0);
    }

    @Test
    public void resetPwd() {
        ResetPwdVO resetPwdVO = new ResetPwdVO();
        resetPwdVO.setId(5);
        resetPwdVO.setPassword("8aHHjjVUbJ");
        resetPwdVO.setNewPwd("1234");
        assertTrue(userService.resetPwd(resetPwdVO) > 0);
    }

    @Test
    public void deleteById() {
        assertTrue(userService.deleteById(203) > 0);
    }
}
