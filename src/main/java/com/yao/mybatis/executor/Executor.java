package com.yao.mybatis.executor;

import com.yao.mybatis.config.MapperRegistry;

/**
 * @author biaoy
 * @version 1.0
 * @date 2019/3/6 20:00
 * @description
 */
public interface Executor {
    <T> T query(MapperRegistry.MapperData statement, Object params);
}
