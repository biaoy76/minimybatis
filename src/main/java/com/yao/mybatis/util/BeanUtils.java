package com.yao.mybatis.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author biaoy
 * @version 1.0
 * @date 2019/3/6 21:19
 * @description
 */
public class BeanUtils {
    public static <T> T map2bean(Map<String, Object> map, Class<T> clz) {
        T obj = null;
        try {
            //创建JavaBean对象
            obj = clz.newInstance();
            //获取指定类的BeanInfo对象
            BeanInfo beanInfo = Introspector.getBeanInfo(clz, Object.class);
            //获取所有的属性描述器
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                Object value = map.get(pd.getName());
                Method setter = pd.getWriteMethod();
                setter.invoke(obj, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
