package com.yourssu.blog.support.common;

import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.InitializingBean;
import java.util.List;
import com.google.common.base.CaseFormat;
import java.util.stream.Collectors;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseCleaner implements InitializingBean {

    @PersistenceContext
    private EntityManager entityManager;

    private List<String> tableNames;

    @Override
    public void afterPropertiesSet() {
        tableNames = entityManager.getMetamodel().getEntities().stream()
                .filter(e -> e.getJavaType().getAnnotation(Entity.class) != null)
                .map(this::extractTableName)
                .map(tableName -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, tableName))
                .collect(Collectors.toList());
    }

    private String extractTableName(EntityType<?> e) {
        final var tableAnnotation = e.getJavaType().getAnnotation(Table.class);
        if (tableAnnotation != null) {
            return tableAnnotation.name();
        }
        return e.getName();
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

        for (String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            entityManager.createNativeQuery(
                    "ALTER TABLE " + tableName + " ALTER COLUMN " + tableName + "_id RESTART WITH 1").executeUpdate();
        }
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}