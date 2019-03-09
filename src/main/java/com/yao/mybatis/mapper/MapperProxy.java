package com.yao.mybatis.mapper;

import com.yao.mybatis.config.MapperRegistry;
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
        MapperRegistry mapperRegistry = sqlSession.getConfiguration().getMapperRegistry();
        MapperRegistry.MapperData mapperData = mapperRegistry.methodSqlMapping.get(method.getDeclaringClass().getName() + "." + method.getName());

        if (mapperData != null) {
            return this.sqlSession.selectOne(mapperData, String.valueOf(args[0]));
        }

        return method.invoke(proxy, args);
    }
}
