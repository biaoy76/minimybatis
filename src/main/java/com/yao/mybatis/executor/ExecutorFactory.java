package com.yao.mybatis.executor;

import com.yao.mybatis.config.Configuration;

/**
 * @author biaoy
 * @version 1.0
 * @date 2019/3/9 10:45
 * @description Executor工厂
 */
public class ExecutorFactory {

    public static Executor get(ExecutorType type, Configuration configuration) {
        if (ExecutorType.SIMPLE.equals(type)) {
            return new SimpleExecutor(configuration);
        }
        if (ExecutorType.CASHING.equals(type)) {
            return new CachingExecutor(configuration);
        }
        throw new RuntimeException("类型错误");
    }

    public enum ExecutorType {
        /**
         * 简单sql执行器
         */
        SIMPLE,

        /**
         * 带缓存功能的sql执行器
         */
        CASHING;
    }
}
