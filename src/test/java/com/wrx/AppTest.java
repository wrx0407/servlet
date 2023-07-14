package com.wrx;

import com.wrx.core.ConnUtil;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 *
 * </p>
 *
 * @author 王荣幸
 * @since 2023/6/25
 */
public class AppTest {
    private static final Logger log = LoggerFactory.getLogger(AppTest.class);

    /**
     * 测试数据库连接
     */
    @Test
    public void testConnection() {
        assertNotNull("数据库连接错误", ConnUtil.getConn());
    }
}
