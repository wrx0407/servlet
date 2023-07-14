package com.wrx.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * JDBC 操作工具类
 * </p>
 *
 * @author 王荣幸
 * @since 2023/6/25
 */
public class JdbcUtil {
    private static final Logger log = LoggerFactory.getLogger(JdbcUtil.class);

    private JdbcUtil() {
    }

    /**
     * 通用 DML 执行方法
     *
     * @param sql    SQL语句
     * @param params 参数集合
     * @return 影响的条数
     */
    public static int exeUpdate(String sql, List<?> params) {
        log.debug("sql ->: {}", sql);
        log.debug("params ->: {}", params);
        Connection conn = ConnUtil.getConn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            // 执行预处理
            JdbcUtil.prepare(ps, params);
            // 执行更新
            final int update = ps.executeUpdate();
            log.debug("影响的行数 ->: {}", update);
            return update;
        } catch (SQLException e) {
            log.error("通用 DML 执行方法异常", e);
            return 0;
        } finally {
            ConnUtil.close(ps, conn);
        }
    }

    /**
     * 查询单条数据
     *
     * @param clazz  Class
     * @param sql    SQL语句
     * @param params 参数集合
     * @return 查询结果
     */
    public static <T> T queryOne(Class<T> clazz, String sql, List<?> params) {
        log.debug("\n   sql ->: {}", sql);
        log.debug("\n   params ->: {}", params);
        Connection conn = ConnUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        T obj = null;
        try {
            ps = conn.prepareStatement(sql);
            // 执行预处理
            JdbcUtil.prepare(ps, params);
            // 执行查询
            rs = ps.executeQuery();
            if (rs.next()) {
                obj = JdbcUtil.toObject(clazz, rs);
            }
        } catch (SQLException e) {
            log.error("查询单条数据异常", e);
        } finally {
            ConnUtil.close(rs, ps, conn);
        }
        log.debug("\n   查询结果 ->: {}", obj);
        return obj;
    }

    /**
     * 查询多条数据
     *
     * @param clazz  Class
     * @param sql    SQL语句
     * @param params 参数集合
     * @return 结果集合
     */
    public static <T> List<T> queryList(Class<T> clazz, String sql, List<?> params) {
        log.debug("\n   sql ->: {}", sql);
        log.debug("\n   params ->: {}", params);
        Connection conn = ConnUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            // 执行预处理
            JdbcUtil.prepare(ps, params);
            // 执行查询
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(
                        JdbcUtil.toObject(clazz, rs));
            }
        } catch (SQLException e) {
            log.error("查询多条数据异常", e);
        } finally {
            ConnUtil.close(rs, ps, conn);
        }
        if (log.isDebugEnabled()) {
            log.debug("   结果条数 ->: [{}]", list.size());
            for (T t : list) {
                System.out.println("   =>: " + t);
            }
        }
        return list;
    }

    /**
     * 查询记录条数
     *
     * @param sql    SQL语句
     * @param params 参数集合
     * @return 查到的条数
     */
    public static int count(String sql, List<?> params) {
        log.debug("\n   sql ->: {}", sql);
        log.debug("\n   params ->: {}", params);
        Connection conn = ConnUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            ps = conn.prepareStatement(sql);
            // 执行预处理
            JdbcUtil.prepare(ps, params);
            // 执行查询
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            log.error("查询记录条数异常", e);
        } finally {
            ConnUtil.close(rs, ps, conn);
        }
        log.debug("     查询结果 ->: {}", count);
        return count;
    }

    /**
     * 执行预处理参数
     *
     * @param ps     预编译操作对象
     * @param params 参数集合
     */
    private static void prepare(PreparedStatement ps, List<?> params) throws SQLException {
        if (params != null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
        }
    }

    /**
     * 利用反射对结果封装成对象
     *
     * @param clazz Class
     * @param rs    查询结果集
     * @return 封装好的对象
     */
    private static <T> T toObject(Class<T> clazz, ResultSet rs) {
        T obj = null;
        try {
            obj = clazz.newInstance();// 创建对象
        } catch (ReflectiveOperationException e) {
            log.error("反射创建对象异常", e);
        }
        Field[] fields = clazz.getDeclaredFields();// 获取属性数组
        for (Field field : fields) {
            Object value = null;
            try {
                value = rs.getObject(
                        JdbcUtil.getDbName(
                                field.getName()));// 获取值
            } catch (SQLException e) {
                // log.debug("获取 ResultSet 字段值异常 [{}]", e.getMessage());
            }
            field.setAccessible(true);
            try {
                /*
                 * 在MySQL数据库中，若给int类型设置了无符号，rs.getObject 收到的类型为 Long 型
                 */
                field.set(obj, value); // 给属性赋值
            } catch (IllegalAccessException e) {
                log.error("反射赋值异常", e);
            }
        }
        return obj;
    }

    /**
     * 小驼峰命名 转 下划线命名
     *
     * @param name 小驼峰命名
     * @return 下划线命名
     */
    private static String getDbName(String name) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char charAt = name.charAt(i);
            if (charAt >= 65 && charAt <= 90) {
                sb.append("_").append((char) (charAt + 32));// A --> _a
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }
}
