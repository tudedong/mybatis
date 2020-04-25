package com.tdd.config;

import com.tdd.pojo.Configuration;
import com.tdd.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @author tudedong
 * @description 解析*Mapper.xml
 * @date 2020-04-21 22:22:47
 */
public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {

        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();

        String namespace = rootElement.attributeValue("namespace");

        List<Element> list = rootElement.selectNodes("//select|//insert|//update|//delete");
        if (list != null) {
            for (Element element : list) {
                String id = element.attributeValue("id");
                String resultType = element.attributeValue("resultType");
                String paramterType = element.attributeValue("paramterType");
                String sqlText = element.getTextTrim();
                MappedStatement mappedStatement = new MappedStatement();
                //设置sql类型
                mappedStatement.setSqlType(element.getQName().getName().toUpperCase());
                mappedStatement.setId(id);
                mappedStatement.setResultType(resultType);
                mappedStatement.setParamterType(paramterType);
                mappedStatement.setSql(sqlText);
                String key = namespace + "." + id;
                configuration.getMappedStatementMap().put(key, mappedStatement);
            }
        }
    }
}
