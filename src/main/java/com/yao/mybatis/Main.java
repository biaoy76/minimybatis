package com.yao.mybatis;

import com.yao.mybatis.executor.BaseExecutor;
import com.yao.mybatis.executor.Executor;
import com.yao.mybatis.session.SqlSession;
import com.yao.mybatis.test.TestMapper;

/**
 * @author biaoy
 * @version 1.0
 * @date 2019/3/6 21:14
 * @description
 */
public class Main {
    public static void main(String[] args) {
        Executor executor = new BaseExecutor();
        SqlSession sqlSession = new SqlSession(null, executor);

        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        System.out.println(mapper.getUserById(2));
    }
}
