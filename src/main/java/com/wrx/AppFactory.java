package com.wrx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wrx.dao.IUserDao;
import com.wrx.dao.impl.UserDaoImpl;
import com.wrx.service.IUserService;
import com.wrx.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

/**
 * <p>
 * 项目对象工厂
 * </p>
 *
 * @author 王荣幸
 * @since 2023/6/25
 */
public class AppFactory {
    private static final Logger log = LoggerFactory.getLogger(AppFactory.class);
    private static DataSource dataSource;
    private static IUserDao userDao;
    private static IUserService userService;

    private AppFactory() {
    }

    /**
     * 获取数据源
     *
     * @return c3p0数据源
     */
    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new ComboPooledDataSource();
            log.debug("创建了 c3p0 数据源对象");
        }
        return dataSource;
    }


    /**
     * 获取 UserDao 实例对象
     *
     * @return UserDao 实例
     */
    public static IUserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
            log.debug("创建了 UserDao 对象");
        }
        return userDao;
    }

    /**
     * 获取 UserService 实例对象
     *
     * @return UserService 实例
     */
    public static IUserService getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl();
            log.debug("创建了 UserService 对象");
        }
        return userService;
    }
}
