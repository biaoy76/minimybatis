package com.yao.mybatis.session;

import com.yao.mybatis.config.Configuration;
import com.yao.mybatis.config.MapperRegistry;
import com.yao.mybatis.executor.Executor;
import com.yao.mybatis.mapper.MapperProxy;

import java.lang.reflect.Proxy;

/**
 * @author biaoy
 * @version 1.0
 * @date 2019/3/6 20:00
 * @description
 */
public class SqlSession {
    private Configuration configuration;
    private Executor executor;

    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    /**
     * 获取mapper，通过动态代理生成代理类
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new MapperProxy(this, clazz));
    }

    /**
     * 查询一条记录
     * @param <T>
     * @param statement
     * @param params
     * @return
     */
    public <T> T selectOne(MapperRegistry.MapperData statement, Object params) {
        return this.executor.query(statement, params);
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
