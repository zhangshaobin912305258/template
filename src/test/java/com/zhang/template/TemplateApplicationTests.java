package com.zhang.template;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TemplateApplicationTests {



	@Test
	public void test() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		Object userService = context.getBean("userService");
		System.out.println(userService);
	}

	/**
	 * 代码生成器
	 * @param args
	 */
	/*public static void main(String[] args) {
		//代码生成器
		AutoGenerator autoGenerator = new AutoGenerator();
		//全局配置
		GlobalConfig globalConfig = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		globalConfig.setOutputDir(projectPath + "/src/main/java");
		globalConfig.setAuthor("zhang");
		globalConfig.setOpen(false);
		globalConfig.setActiveRecord(true);
		globalConfig.setBaseResultMap(true);
		globalConfig.setBaseColumnList(true);
		//globalConfig.setFileOverride(true);

		//数据源配置
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/template?characterEncoding=utf-8&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull");
		dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
		dataSourceConfig.setUsername("root");
		dataSourceConfig.setPassword("root");

		//包配置
		PackageConfig packageConfig = new PackageConfig();
		packageConfig.setParent("com.zhang.template");

		//自定义配置
		InjectionConfig injectionConfig = new InjectionConfig() {
			@Override
			public void initMap() {

			}
		};
		String templatePath = "/templates/mapper.xml.vm";
		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});
		injectionConfig.setFileOutConfigList(focList);

		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setXml(null);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);


		autoGenerator.setGlobalConfig(globalConfig);
		autoGenerator.setDataSource(dataSourceConfig);
		autoGenerator.setPackageInfo(packageConfig);
		autoGenerator.setCfg(injectionConfig);
		autoGenerator.setTemplate(templateConfig);
		autoGenerator.setStrategy(strategy);
		autoGenerator.execute();
	}*/

}
