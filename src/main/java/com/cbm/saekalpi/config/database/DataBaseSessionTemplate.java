package com.cbm.saekalpi.config.database;

import org.mybatis.spring.SqlSessionTemplate;

public interface DataBaseSessionTemplate {
    SqlSessionTemplate getSqlSessionTemplate();
}
