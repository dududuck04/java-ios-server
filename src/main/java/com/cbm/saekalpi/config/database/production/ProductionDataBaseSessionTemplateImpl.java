package com.cbm.saekalpi.config.database.production;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.cbm.saekalpi.config.database.DataBaseSessionTemplate;

@Profile("production")
@Component
public class ProductionDataBaseSessionTemplateImpl implements DataBaseSessionTemplate {

    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public ProductionDataBaseSessionTemplateImpl(@Qualifier("productionSqlSessionTemplate") SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }
}
