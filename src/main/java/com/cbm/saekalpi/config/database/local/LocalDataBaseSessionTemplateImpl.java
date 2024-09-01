package com.cbm.saekalpi.config.database.local;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.cbm.saekalpi.config.database.DataBaseSessionTemplate;

@Profile("local")
@Component
public class LocalDataBaseSessionTemplateImpl implements DataBaseSessionTemplate {

    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public LocalDataBaseSessionTemplateImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }
}
