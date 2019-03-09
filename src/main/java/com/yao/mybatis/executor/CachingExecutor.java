package com.yao.mybatis.executor;

import com.yao.mybatis.config.Configuration;
import com.yao.mybatis.config.MapperRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author biaoy
 * @version 2.0
 * @date 2019/3/9 16:10
 * @description 带缓存功能的Executor执行器
 */
public class CachingExecutor implements Executor{
    /**
     * 委托delegate实现
     */
    private Executor delegate;

    /**
     * 本地一级缓存
     */
    private Map<String, Object> localCache = new HashMap<>();

    private Configuration configuration;

    public CachingExecutor(Configuration configuration) {
        this.configuration = configuration;
        this.delegate = new SimpleExecutor(configuration);
    }

    @Override
    public <T> T query(MapperRegistry.MapperData statement, Object params) {
        Object o = localCache.get(statement.getSql());
        if (o != null) {
            System.out.println("命中缓存");
            return (T) o;
        }

        Object result = this.delegate.query(statement, params);
        localCache.put(statement.getSql(), result);
        return (T) result;
    }
}
