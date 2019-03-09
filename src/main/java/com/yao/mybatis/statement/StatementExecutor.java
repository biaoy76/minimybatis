package com.yao.mybatis.statement;

import com.yao.mybatis.config.Configuration;
import com.yao.mybatis.config.MapperRegistry;
import com.yao.mybatis.result.ResultSetHandler;

import java.sql.*;

/**
 * @author biaoy
 * @version 1.0
 * @date 2019/3/9 10:28
 * @description 语句执行器
 */
public class StatementExecutor {

    private Configuration configuration;

    public StatementExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T query(MapperRegistry.MapperData sql, Object params) {
        Connection connection = null;
        try {
            connection = getConnection();
            String format = String.format(sql.getSql(), params);
            PreparedStatement preparedStatement = connection.prepareStatement(format);
            ResultSet resultSet = preparedStatement.executeQuery();

            //结果处理器
            ResultSetHandler resultSetHandler = new ResultSetHandler(configuration);
            T t =  resultSetHandler.handleSingleResult(resultSet, sql);

            preparedStatement.close();
            resultSet.close();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private Connection getConnection() throws Exception{
        loadDriver();
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/gms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",
                "root");
    }

    private void loadDriver() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("加载成功");
    }
}
