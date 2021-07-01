package com.ltyl.medicalrule.gatewayimpl.database;

import cn.org.atool.generator.FileGenerator;
import cn.org.atool.generator.annotation.Table;
import cn.org.atool.generator.annotation.Tables;
import org.junit.Test;

public class MedicalRuleTest {

    public static final String url = "jdbc:mysql://localhost:3306/medical_rule_engine?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";

    public static final String username = "medical_rule";

    public static final String password = "medical_rule_engine";

    @Test
    public void generate() throws Exception {
        FileGenerator.build(Empty.class);
    }

    @Tables(
            // 设置数据库连接信息
            url = url, username = username, password = password,

            entitySuffix = "DO",

            // 设置entity类生成src目录, 相对于 user.dir
            srcDir = "src/main/java",
            // 设置entity类的package值
            basePack = "com.ltyl.medicalrule.gatewayimpl.database",
            // 设置dao接口和实现的src目录, 相对于 user.dir
            daoDir = "src/main/java",
            // 设置哪些表要生成Entity文件
            tables = {@Table(value = {"medical_rule"})}
    )
    static class Empty {
    }
}
