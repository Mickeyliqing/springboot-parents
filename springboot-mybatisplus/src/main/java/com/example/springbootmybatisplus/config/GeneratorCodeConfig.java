package com.example.springbootmybatisplus.config;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 自定义代码生成器
 **/
public class GeneratorCodeConfig {

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("李清");
        gc.setOpen(false);

        // 实体属性 Swagger2 注解
        gc.setSwagger2(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://172.16.0.27:3306/datatestui?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.example.springbootmybatisplus");
        pc.setEntity("model.auto");
        pc.setMapper("dao.mysql");
        pc.setService("service.mysql");
        pc.setServiceImpl("service.mysql.impl");

        //指定生成文件的绝对路径,项目结构不是 父-子 这种形式的，这段代码可以省略
        Map<String, String> pathInfo  = new HashMap<>();
        // 绝对路径的包路径
        String parentPath = "src\\main\\java\\com\\example\\springbootmybatisplus\\";
        // 子项目名字
        String conStr ="\\springboot-mybatisplus\\";
        String concatPath = projectPath.concat(conStr).concat(parentPath);

        String entityPath = concatPath.concat("model\\auto");
        String mapper_path = concatPath.concat("dao\\mysql");
        String service_path = concatPath.concat("service\\mysql");
        String service_impl_path = concatPath.concat("service\\mysql\\impl");
        String controller_path = concatPath.concat("controller");
        pathInfo.put("entity_path",entityPath);
        pathInfo.put("mapper_path",mapper_path);
        pathInfo.put("service_path",service_path);
        pathInfo.put("service_impl_path",service_impl_path);
        pathInfo.put("controller_path",controller_path);
        pc.setPathInfo(pathInfo);

        mpg.setPackageInfo(pc);

        // 自定义配置
 /*     InjectionConfig cfg = new InjectionConfig() {
        @Override
       public void initMap() {
        // to do nothing
         }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
         List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
        @Override
        public String outputFile(TableInfo tableInfo) {
        // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
        return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
         + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
         }
         });

        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);*/

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
       /* templateConfig.setEntity("templates/entity2.java");
          templateConfig.setService();
          templateConfig.setController();*/

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.baomidou.mybatisplus.extension.activerecord.Model");
        strategy.setEntityLombokModel(true);

        strategy.setEntityLombokModel(true);
        // 公共父类
        // strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 写于父类中的公共字段
        // strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
