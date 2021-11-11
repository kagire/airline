package com.kagire;

import com.kagire.annotations.InjectSql;
import org.springframework.stereotype.Component;

@Component
public class InjectSqlExampleClass {

    @InjectSql("classpath:findAll.sql")
    private final String FIND_ALL_SQL = null;

    public String getFIND_ALL_SQL() {
        return FIND_ALL_SQL;
    }
}
