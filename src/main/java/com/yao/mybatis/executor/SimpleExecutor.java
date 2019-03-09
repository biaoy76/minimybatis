package com.yao.mybatis.executor;

import com.yao.mybatis.config.Configuration;
import com.yao.mybatis.config.MapperRegistry;
import com.yao.mybatis.statement.StatementExecutor;

/**
 * @author biaoy
 * @version 1.0
 * @date 2019/3/6 20:54
 * @description
 */
public class SimpleExecutor implements Executor {
    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }


    public <T> T query(MapperRegistry.MapperData sql, Object params) {
        //StatementHandler->ParameterHandler->ResultSetHandler
        StatementExecutor statementExecutor = new StatementExecutor(configuration);
        return statementExecutor.query(sql, params);
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
