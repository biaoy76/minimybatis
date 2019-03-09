package com.yao.mybatis.result;

import com.yao.mybatis.config.Configuration;
import com.yao.mybatis.config.MapperRegistry;
import com.yao.mybatis.util.BeanUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author biaoy
 * @version 1.0
 * @date 2019/3/9 13:36
 * @description ResultSet结果集处理器
 */
public class ResultSetHandler {
    private Configuration configuration;

    public ResultSetHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T handleSingleResult(ResultSet resultSet, MapperRegistry.MapperData mapperData) {
        List<Map<String, Object>> list = rsToList(resultSet);
        if (list.size() > 0) {
            Map<String, Object> tempResultMap = list.get(0);

            Class type = mapperData.getType();
            return (T) BeanUtils.map2bean(tempResultMap, type);
        }
        return null;
    }

    private List<Map<String, Object>> rsToList(ResultSet rs) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= count; i++) {
                    Object object = rs.getObject(i);
                    String columnName = underscoreToCamel(metaData.getColumnName(i));

                    //判断是否为空
                    if (object == null) {
                        map.put(columnName, null);
                        continue;
                    }

                    //处理日期
                    if (object instanceof java.sql.Timestamp) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        map.put(columnName, sdf.format(object));
                        continue;
                    }
                    map.put(metaData.getColumnName(i), object);
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 转换驼峰
     *
     * @param name
     * @return
     */
    public String underscoreToCamel(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name;
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
}
