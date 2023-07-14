package com.wrx.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 简化请求响应对象
 * </p>
 *
 * @author 王荣幸
 * @since 2023/6/25
 */
public class ModelAndView {
    private static final Logger log = LoggerFactory.getLogger(ModelAndView.class);
    private final HttpServletRequest request;
    private final HttpServletResponse response;


    public ModelAndView(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        this.request = request;
        this.response = response;
        this.request.setCharacterEncoding("UTF-8");
    }

    /**
     * 获取 单个参数
     *
     * @param name  参数名
     * @param clazz 参数类型Class
     * @return 结果
     */
    public <E> E getParam(String name, Class<E> clazz) {
        final String value = request.getParameter(name);
        if (value == null || value.equals("")) {
            return null;
        }
        if (clazz == Date.class) {
            return (E) this.getDate(value); // 返回日期对象
        }
        Constructor<E> con;
        E param = null;
        try {
            con = clazz.getConstructor(String.class);
            param = con.newInstance(value); // 创建对象
        } catch (ReflectiveOperationException e) {
            log.error("反射创建对象异常", e);
        }
        return param;
    }

    /**
     * 获取 参数对象
     *
     * @param clazz 参数类型Class
     * @return 结果对象
     */
    public <E> E getObject(Class<E> clazz) {
        E object = null;
        try {
            Constructor<E> con = clazz.getConstructor();// 获取无参构造
            object = con.newInstance(); // 创建对象
        } catch (ReflectiveOperationException e) {
            log.error("反射创建对象异常", e);
        }
        Field[] fields = clazz.getDeclaredFields(); // 获取属性数组
        for (Field field : fields) {
            Object value = this.getValue(field); // 获取请求参数
            String methodName = this.getSetName(field.getName());
            Method method;
            try {
                method = clazz.getMethod(methodName, field.getType());// 获取对应 set 方法
                method.invoke(object, value); // 执行 set 方法
            } catch (ReflectiveOperationException ex) {
                log.error("反射 set 方法赋值异常", ex);
            }
        }
        return object;
    }

    /**
     * 将对象放入请求作用域
     *
     * @param name   名字
     * @param object 对象
     * @return ModelAndView
     */
    public ModelAndView setModel(String name, Object object) {
        request.setAttribute(name, object);
        return this;
    }

    /**
     * 从请求作用域中获取数据
     *
     * @param name 名字
     * @return 对象
     */
    public Object getModel(String name) {
        return request.getAttribute(name);
    }

    /**
     * 转发 JSP
     *
     * @param name 去掉前缀和后缀的 路径+文件名
     */
    public void JSP(String name) throws ServletException, IOException {
        this.dispatcher("/WEB-INF/jsp/" + name + ".jsp");
    }

    /**
     * 重定向
     *
     * @param path 路径
     */
    public void redirect(String path) throws IOException {
        response.sendRedirect(request.getContextPath() + path);
    }

    /**
     * 请求转发
     *
     * @param path 路径
     */
    public void dispatcher(String path) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }


    /**
     * 根据反射获取的字段获取请求参数
     *
     * @param field Field
     * @return 获取的结果
     */
    private Object getValue(Field field) {
        Class<?> clazz = field.getType();
        String parameter = request.getParameter(field.getName());
        if (parameter == null || parameter.equals("")) {
            return null;
        }
        Object object = null;
        if (clazz == Date.class) {
            return this.getDate(parameter); // 返回日期对象
        }
        try {
            Constructor<?> con = clazz.getConstructor(parameter.getClass());
            object = con.newInstance(parameter); // 创建对象
        } catch (ReflectiveOperationException e) {
            log.error("反射创建对象异常", e);
        }
        return object;
    }

    /**
     * 获取日期对象
     *
     * @param parameter 日期字符串
     * @return Date
     */
    private Date getDate(String parameter) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 指定日期格式
        try {
            return sdf.parse(parameter); // 创建日期对象
        } catch (ParseException e) {
            log.error("字符串转日期异常", e);
            return null;
        }
    }

    /**
     * 获取 set 方法名
     *
     * @param name 字段名
     * @return set 方法名
     */
    private String getSetName(String name) {
        if (name == null || name.length() == 0) {
            return null;
        }
        char var1 = name.charAt(0);
        char var2 = (char) (var1 - 32);
        return "set" + var2 + name.substring(1);
    }
}
