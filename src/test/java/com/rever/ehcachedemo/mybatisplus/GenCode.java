package com.rever.ehcachedemo.mybatisplus;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Description: 数据库代码生成
 * Author: gaoyakang
 * Version: 1.0
 * Create Date Time: 2018-12-27 09:03
 * Update Date Time:
 *
 * @see
 */
@Ignore
public class GenCode {

    private Generate generate = new Generate("jdbc:mysql://localhost:3306/cacheTest", "root", "", "gaoyakang", "D://codeGen");

    @Test
    public void gen() throws Exception {
        generate.generateAll("com.sino.ehcachedemo.server");
    }

    @Test
    public void genByTable() throws Exception {
        generate.generateByTables("com.rever.ehcachedemo", "user");
    }


    @Test
    public void genByTable2() throws Exception {
        generate.generateByTables("com.sino.event.server.persistence.mybatis", "notify_group", "notify_user");
    }

    @Test
    public void genByTable3() throws Exception {
        generate.generateByTables("com.sino.event.server.persistence.mybatis", "collect_task", "collect_field");
    }

    @Test
    public void genByTable4() throws Exception {
        generate.generateByTables("com.sino.notice.server.persistence", "collect_task", "collect_field");
    }
}
