package com.zhang.template.util;

public class CodeGenerator {

    /*
    public static void main(String[] args) { // 代码生成器
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        //外层包名
        String packageName = "com.zhang.template";
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //gc.setFileOverride(false);
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(true);
        gc.setAuthor("zhang"); //生成文件的作者名称
        gc.setOpen(false);
        gc.setSwagger2(false);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://47.108.59.160:3306/template?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver"); //设置数据库驱动,因为我的数据库版本是mysql5.7,所以使用该驱动
        dsc.setUsername("root"); //数据库名称
        dsc.setPassword("123456"); //数据库密码
        mpg.setDataSource(dsc);
        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName("");
        pc.setParent(packageName);
        mpg.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setEntityLombokModel(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityBuilderModel(true);
        strategy.setInclude("article", "navigation", "label");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
    */
}
