package com.yao.mybatis.config;

import com.yao.mybatis.test.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author biaoy
 * @version 1.0
 * @date 2019/3/7 21:09
 * @description
 */
public class MapperRegistry {

    public static final Map<String, MapperData> methodSqlMapping = new HashMap<>();

    /**
     * 使用
     * 1.在这里配置
     * 2.JavaBean的属性顺序要和数据库表的顺序一致
     */
    public MapperRegistry() {
        methodSqlMapping.put("com.yao.mybatis.test.TestMapper.selectUserById",
                new MapperData("select * from sys_user", User.class));
    }

    public class MapperData<T> {
        private String sql ;
        private Class<T> type;

        public MapperData(String sql, Class<T> type) {
            this.sql = sql;
            this.type = type;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public Class<T> getType() {
            return type;
        }

        public void setType(Class<T> type) {
            this.type = type;
        }
    }
}
