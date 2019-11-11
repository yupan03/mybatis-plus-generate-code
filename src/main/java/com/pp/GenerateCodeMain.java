package com.pp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.DbType;
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

public class GenerateCodeMain {
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        GlobalConfig gc = new GlobalConfig();

        gc.setOutputDir(Config.projectPath + "/src/main/java");
        gc.setFileOverride(true);
        gc.setOpen(false);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(false);
        gc.setFileOverride(Config.fileOverride);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        DataSourceConfig dsc = new DataSourceConfig();

        dsc.setUrl(Config.url);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(Config.username);
        dsc.setPassword(Config.password);
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        PackageConfig pc = new PackageConfig();

        pc.setParent(Config.packageName);

        mpg.setPackageInfo(pc);

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                // 自定义配置，在模版中cfg.superColums 获取
                // 这里解决子类会生成父类属性的问题，在模版里会用到该配置
                map.put("superColums", this.getConfig().getStrategyConfig().getSuperEntityColumns());
                this.setMap(map);
            }
        };

        mpg.setCfg(cfg);

        StrategyConfig strategy = new StrategyConfig();

        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(Config.includeTables);
        strategy.setExclude(Config.excludeTables);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 不生成如下类型模板
        templateConfig.setXml(null);

        mpg.setTemplate(templateConfig);

        // 如果模板引擎是 velocity
        String mapperXmlPath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        focList.add(new FileOutConfig(mapperXmlPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义xml 文件名和生成路径
                return Config.projectPath + Config.xmlPath + "/" + tableInfo.getEntityName() + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        mpg.setStrategy(strategy);

        mpg.execute();
    }
}