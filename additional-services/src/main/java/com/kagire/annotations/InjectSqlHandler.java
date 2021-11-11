package com.kagire.annotations;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;

@Component
public class InjectSqlHandler implements DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeforeDestruction(Object o, String s) throws BeansException {
    }

    @Override
    public boolean requiresDestruction(Object bean) {
        return DestructionAwareBeanPostProcessor.super.requiresDestruction(bean);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return DestructionAwareBeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        performSqlInjection(bean);
        return DestructionAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    public void performSqlInjection(Object bean){
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectSql.class))
            {
                field.setAccessible(true);
                StringBuilder sqlValue = new StringBuilder();
                try {
                    for(String value : Files.readAllLines(ResourceUtils.getFile(field.getAnnotation(InjectSql.class).value()).toPath(), Charset.defaultCharset())){
                        sqlValue.append(value);
                    }
                    field.set(bean, sqlValue.toString());
                } catch (IllegalAccessException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}