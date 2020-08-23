package com.admin.framework.common.utils;

import com.admin.framework.common.entity.CodeGeneratorEntity;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zsw
 * @date 2020-05-09 01:02
 */
public class CodeGeneratorUtil {

    /**
     * 代码生成器
     * @param codeGeneratorEntity
     */
    public void gen(CodeGeneratorEntity codeGeneratorEntity){
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig globalConfig = globalConfig(codeGeneratorEntity.getProjectPath(),codeGeneratorEntity.getAuthor());
        mpg.setGlobalConfig(globalConfig);

        DataSourceConfig dataSourceConfig = dataSourceConfig(codeGeneratorEntity.getDbUrl(), codeGeneratorEntity.getDbUsername(), codeGeneratorEntity.getDbPassword());
        mpg.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(codeGeneratorEntity.getModuleName());
        pc.setParent(codeGeneratorEntity.getParent());
        mpg.setPackageInfo(pc);

        InjectionConfig injectionConfig = injectionConfig();
        List<FileOutConfig> fileOutConfigs = fileOutConfigs(pc.getModuleName(),codeGeneratorEntity.getProjectPath(),codeGeneratorEntity.getTemplatePath());
        injectionConfig.setFileOutConfigList(fileOutConfigs);
        mpg.setCfg(injectionConfig);

        TemplateConfig templateConfig = templateConfig();
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategyConfig = strategyConfig(pc.getModuleName(),codeGeneratorEntity.getTableNames());
        mpg.setStrategy(strategyConfig);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }




    /**
     * 自定义配置
     * @return
     */
    private InjectionConfig injectionConfig(){
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        return cfg;
    }

    /**
     * 模板配置
     * @return
     */
    private TemplateConfig templateConfig(){
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        return templateConfig;
    }


    /**
     * 策略配置
     * @param moduleName
     * @param tableNames
     * @return
     */
    private StrategyConfig strategyConfig(String moduleName,String... tableNames){
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setInclude(tableNames);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(moduleName + "_");
        return strategy;
    }

    /**
     * 文件输出配置
     * @param moduleName
     * @return
     */
    private List<FileOutConfig> fileOutConfigs(String moduleName,String projectPath,String templatePath){
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + moduleName + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        return focList;
    }

    /**
     * 数据源
     * @return
     */
    private DataSourceConfig dataSourceConfig(String url,String username,String password){
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(username);
        dsc.setPassword(password);
        return dsc;
    }

    /**
     * 全局配置
     * @return
     */
    private GlobalConfig globalConfig(String projectPath,String author){
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);
        return gc;
    }

}
