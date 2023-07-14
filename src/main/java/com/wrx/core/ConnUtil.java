package com.wrx.core;

import com.wrx.AppFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * 数据库连接工具类
 * </p>
 *
 * @author 王荣幸
 * @since 2023/6/25
 */
public class ConnUtil {
    private static final Logger log = LoggerFactory.getLogger(ConnUtil.class);
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private ConnUtil() {
    }

    /**
     * 获取连接
     *
     * @return 线程级别的数据库连接
     */
    public static Connection getConn() {
        // 根据当前线程id获取连接
        Connection conn = threadLocal.get();
        if (conn == null) {
            try {
                // 获取连接
                conn = AppFactory.getDataSource().getConnection();
                // 将当前线程id和连接存起来
                threadLocal.set(conn);
            } catch (SQLException e) {
                log.error("获取数据库连接异常", e);
            }
        }
        return conn;
    }

    /**
     * 开启手动提交事务
     */
    public static void openHandCommit() {
        try {
            ConnUtil.getConn().setAutoCommit(false);
        } catch (SQLException e) {
            log.error("开启手动提交事务异常", e);
        }
    }

    /**
     * 事务提交
     */
    public static void commit() {
        Connection conn = getConn();
        try {
            // 若开启了手动提交事务，则提交
            if (!conn.getAutoCommit()) {
                conn.commit();
                ConnUtil.close(conn);// 关闭连接
            }
        } catch (SQLException e) {
            log.error("事务提交异常", e);
        }
    }

    /**
     * 事务回滚
     */
    public static void rollback() {
        Connection conn = getConn();
        try {
            // 若开启了手动提交事务，则回滚
            if (!conn.getAutoCommit()) {
                conn.rollback();
                ConnUtil.close(conn);// 关闭连接
            }
        } catch (SQLException e) {
            log.error("事务回滚异常", e);
        }
    }

    /**
     * 释放（归还）资源
     */
    public static void close(Statement stat, Connection conn) {
        ConnUtil.close(null, stat, conn);
    }

    /**
     * 释放（归还）资源
     */
    public static void close(ResultSet rs, Statement stat, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
            // 若未开启手动提交事务，则关闭连接
            if (conn != null && conn.getAutoCommit()) {
                ConnUtil.close(conn);
            }
        } catch (SQLException e) {
            log.error("释放数据库资源异常", e);
        }
    }

    /**
     * 关闭连接
     */
    private static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            log.error("归还数据库连接异常", e);
        }
        threadLocal.remove();// 删除 ThreadLocal 存储的值，防止内存泄漏
    }

}
