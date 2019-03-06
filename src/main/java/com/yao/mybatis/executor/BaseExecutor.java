package com.yao.mybatis.executor;

import com.yao.mybatis.test.User;
import com.yao.mybatis.util.BeanUtils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author biaoy
 * @version 1.0
 * @date 2019/3/6 20:54
 * @description
 */
public class BaseExecutor implements Executor {
    public <T> T query(String sql, Object params) {
        prepare();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "root");
            String format = String.format(sql, params);
            PreparedStatement preparedStatement = connection.prepareStatement(format);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Map<String, Object>> maps = rsToList(resultSet);

            if (maps.size() >= 1) {
                Map<String, Object> stringObjectMap = maps.get(0);
                return (T)BeanUtils.map2bean(stringObjectMap, User.class);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void prepare() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<Map<String, Object>> rsToList(ResultSet rs) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= count; i++) {
                    Object object = rs.getObject(i);
                    //判断是否为空
                    if (object == null) {
                        map.put(metaData.getColumnName(i), null);
                        continue;
                    }

                    //处理日期
                    if (object instanceof java.sql.Timestamp) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        map.put(metaData.getColumnName(i), sdf.format(object));
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

}
