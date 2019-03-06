package com.yao.mybatis.mapper;

import com.yao.mybatis.session.SqlSession;
import com.yao.mybatis.test.TestMapperXml;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author biaoy
 * @version 1.0
 * @date 2019/3/6 20:07
 * @description
 */
public class MapperProxy<T> implements InvocationHandler {
    private final SqlSession sqlSession;
    private Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> clazz) {
        this.sqlSession = sqlSession;
        this.mapperInterface = clazz;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //硬编码，先匹配命名空间，找到xml文件，此处使用java类模拟
        if (method.getDeclaringClass().getName().equals(TestMapperXml.namespace)) {
            String sql = TestMapperXml.sqlMap.get(method.getName());

            return this.sqlSession.selectOne(sql, args[0]);
        }
        return null;
    }
}
