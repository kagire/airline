package com.kagire;

import com.kagire.annotations.InjectSqlHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {InjectSqlExampleClass.class, InjectSqlHandler.class})
public class InjectSqlAnnotationTest {

    @Autowired
    InjectSqlExampleClass injectSqlExampleClass;

    @Test
    public void testInjectSqlAnnotation(){
        Assertions.assertEquals("SELECT * FROM departments ORDER BY id", injectSqlExampleClass.getFIND_ALL_SQL());
    }

    @Test
    public void testInjectSqlExampleClass(){
        InjectSqlExampleClass injectSqlExampleClass1 = new InjectSqlExampleClass();
        assert injectSqlExampleClass1.getFIND_ALL_SQL() == null;
    }
}
