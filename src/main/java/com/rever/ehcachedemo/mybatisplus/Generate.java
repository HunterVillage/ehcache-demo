package com.rever.ehcachedemo.mybatisplus;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @Description: mybatis plus代码生成; 注意: 使用该工具时，请将代码写在测试中，并且使用注解 @ignore 忽视掉测试。
 * @Author: gaoyakang
 * @Version: 1.0
 * @Create Date Time: 2018-07-24 14:03
 * @Update Date Time:
 *
 * @see
 */
public final class Generate {

    /**
     * 数据库 url
     */
    private String dbUrl;

    /**
     * 数据库用户名
     */
    private String dbUsername;

    /**
     * 数据库密码
     */
    private String dbPassword;

    /**
     * 作者名
     */
    private String author;

    /**
     * 代码输出路径
     */
    private String outputDir;

    /**
     * 构造器
     * @param dbUrl 数据库 url
     * @param dbUsername 数据库用户名
     * @param dbPassword 数据库密码
     * @param author 作者名
     * @param outputDir 代码输出路径
     */
    public Generate(String dbUrl, String dbUsername, String dbPassword, String author, String outputDir) {
        this.dbUrl = dbUrl;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        this.author = author;
        this.outputDir = outputDir;
    }

    /**
     * 通过表名生成代码
     * @param packageName 包名
     * @param tableNames 表名
     */
    public void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
    }

    /**
     * 通过表名生成代码
     * @param packageName 包名
     */
    public void generateAll(String packageName) {
        generateByTables(true, packageName, null);
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername(dbUsername)
                .setPassword(dbPassword)
                .setDriverName("com.mysql.cj.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setDbColumnUnderline(true)
                .setEntityBuilderModel(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.nochange)
                .setRestControllerStyle(true);
        if(tableNames != null){
            strategyConfig.setInclude(tableNames);
        }
        config.setActiveRecord(false)
                .setAuthor(author)
                .setOutputDir(outputDir)
                .setFileOverride(true)
                .setBaseResultMap(true)
                .setIdType(IdType.UUID)
                .setEnableCache(false)
                .setBaseColumnList(true);
        if (serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }


}
