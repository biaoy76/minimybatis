package com.yao.mybatis;

import com.yao.mybatis.config.Configuration;
import com.yao.mybatis.config.MapperRegistry;
import com.yao.mybatis.executor.ExecutorFactory;
import com.yao.mybatis.session.SqlSession;
import com.yao.mybatis.test.TestMapper;

/**
 * @author biaoy
 * @version 1.0
 * @date 2019/3/6 21:14
 * @description
 */
public class Bootstrap {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setScanPath("com.yao");
        configuration.setMapperRegistry(new MapperRegistry());

        SqlSession sqlSession = new SqlSession(configuration, ExecutorFactory.get(ExecutorFactory.ExecutorType.CASHING, configuration));

        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        System.out.println(mapper.getUserById(2));

        System.out.println(mapper.getUserById(2));
        System.out.println(mapper.getUserById(2));
    }
}
