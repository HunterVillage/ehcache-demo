package com.rever.ehcachedemo.config;

import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.incrementer.H2KeyGenerator;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @Description: MybatisPlus 的配置
 * @Author: gaoyakang
 * @Version: 1.0
 * @Create Date Time: 2018-08-06 09:37
 * @Update Date Time:
 *
 * @see
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 实体类包名
     */
    private final String ENTITY_PACKAGE = "com.rever.ehcachedemo.entity";
    /**
     * mapper.xml的匹配位置
     */
    private final String MAPPER_LOCATIONS = "classpath:/com/rever/ehcachedemo/mapper/xml/*Mapper.xml";

    /**
     * mapper包
     */
    private final String MAPPER_SCANNER = "com.rever.ehcachedemo.mapper";

    @Bean("mybatisSqlSession")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource,
                                               ResourcePatternResolver resourcePatternResolver) throws Exception {

        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage(ENTITY_PACKAGE);
        sqlSessionFactory.setMapperLocations(resourcePatternResolver.getResources(MAPPER_LOCATIONS));
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{
                this.paginationInterceptor(),
                this.performanceInterceptor(),
                new OptimisticLockerInterceptor()
        });
        sqlSessionFactory.setGlobalConfig(this.globalConfiguration());
        return sqlSessionFactory.getObject();
    }




    /**
     * mapper包扫描器相当于顶部的：
     * {@code @MapperScan("com.rever.ehcachedemo.mapper*")}
     * 这里可以扩展，比如使用配置文件来配置扫描Mapper的路径
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage(MAPPER_SCANNER);
        return scannerConfigurer;
    }

    /**
     * 全局配置
     * @return
     */
    private GlobalConfiguration globalConfiguration(){
        GlobalConfiguration globalConfiguration = new GlobalConfiguration(new LogicSqlInjector());
        globalConfiguration.setIdType(3);
        globalConfiguration.setFieldStrategy(2);
        globalConfiguration.setDbColumnUnderline(true);
        // 主键生成策略
        globalConfiguration.setKeyGenerator(new H2KeyGenerator());
        // 逻辑删除配置
        globalConfiguration.setLogicDeleteValue("1");
        globalConfiguration.setLogicNotDeleteValue("0");
        // sql注入器
        globalConfiguration.setSqlInjector(new LogicSqlInjector());
        return globalConfiguration;
    }

    /**
     * 分页
     * @return
     */
    private PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 开启 PageHelper 的支持
        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }

    /**
     * mapper.mybatis-plus SQL执行效率插件【生产环境可以关闭】
     */
    private PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }


}
