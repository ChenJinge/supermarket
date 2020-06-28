package com.supermarket.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JDBCUtil {

    private static String url;
    private static String driverClass;
    private static String userName;
    private static String password;

    /**
     * 初始化驱动程序
     * 静态代码块中（只加载一次）
     */
    static {
        try {
            Properties properties = new Properties();

            /**
             * 使用类路径的读取方式
             *  / : 斜杠表示classpath的根目录
             *     在java项目下，classpath的根目录从bin目录开始
             *     在web项目下，classpath的根目录从WEB-INF/classes目录开始
             */
            InputStream inputStream = JDBCUtil.class.getResourceAsStream("/db.properties");

            properties.load(inputStream);
            url = properties.getProperty("url");
            driverClass = properties.getProperty("driverClass");
            userName = properties.getProperty("user");
            password = properties.getProperty("password");
            Class.forName(driverClass);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 打开数据库驱动连接
     */
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * 清理环境，关闭连接(顺序:后打开的先关闭)
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null)
            try {
                resultSet.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new RuntimeException(e1);
            }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public static String humpToLine2(String str) {
        Pattern humpPattern = Pattern.compile("[A-Z]");
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 将数据库字段转换成驼峰
     * @param str
     * @return
     */
    public static String toCamel(String str){
         String[] strs = str.split("_");
         StringBuffer sb = new StringBuffer(strs[0]);
        for (int i = 1; i < strs.length; i++) {
            sb.append(strs[i].substring(0,1).toUpperCase()).append(strs[i].substring(1));
        }
        return sb.toString();
    }

    public static void main(String[] args) {

//        Connection connection = JDBCUtil.getConnection();
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            statement = connection.createStatement();
//            String sql = "SELECT * FROM vip";
//            resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("vipID");
//                System.out.println("ID: " + id);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtil.close(connection, statement, resultSet);
//        }

        String param = "user_type_name";
        String re = toCamel(param);
    }
}
