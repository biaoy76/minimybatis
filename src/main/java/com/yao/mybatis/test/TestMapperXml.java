package com.yao.mybatis.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author biaoy
 * @version 1.0
 * @date 2019/3/6 20:47
 * @description
 */
public class TestMapperXml {
    public static final String namespace = "com.yao.mybatis.test.TestMapper";

    public static Map<String, String> sqlMap = new HashMap<>();

    static {
        sqlMap.put("getUserById", "select * from sys_user where id=%d");
    }
}
