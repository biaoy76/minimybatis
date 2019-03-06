package com.yao.mybatis.executor;

/**
 * @author biaoy
 * @version 1.0
 * @date 2019/3/6 20:00
 * @description
 */
public interface Executor {
    <T> T query(String statement, Object params);
}
