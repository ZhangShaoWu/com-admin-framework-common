package com.admin.framework.common.entity;

import lombok.Data;

/**
 * @author zsw
 * @date 2020-05-09 00:57
 */
@Data
public class CodeGeneratorEntity {
    private String moduleName;
    private String parent;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private String[] tableNames;
    private String templatePath = "/templates/mapper.xml.ftl";
    private String projectPath = System.getProperty("user.dir");
    private String author;
}
