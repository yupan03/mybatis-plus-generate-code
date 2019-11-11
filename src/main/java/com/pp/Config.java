package com.pp;

public class Config {
    // 数据库配置start
    public static String url = "jdbc:mysql://47.93.219.196:3306/project?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";

    public static String username = "server";

    public static String password = "server";
    // 数据库end

    public static boolean fileOverride = false;

    // 项目路径
    public static String projectPath = "C:\\Users\\73599\\Documents\\workspace\\mybatis-plus-generate-code";
    // 父包名
    public static String packageName = "com.pp";
    // xml路径
    public static String xmlPath = "/src/main/resources/mapping";
    // 要生产的表
    public static String[] includeTables = { "users" };
    // 不需要生成的表
    public static String[] excludeTables = {};
}