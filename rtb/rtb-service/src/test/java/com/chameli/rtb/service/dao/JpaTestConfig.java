package com.chameli.rtb.service.dao;

import com.chameli.rtb.service.junit.dao.GuiceJpaLiquibaseManager;
import com.chameli.rtb.service.junit.dao.GuiceJpaLiquibaseManager.DdlGeneration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@GuiceJpaLiquibaseManager.Config(modules = GuiceModule.class, persistenceUnitName = "persistenceUnit-hsqldb", ddlGeneration = DdlGeneration.LIQUIBASE, sqlExplorer = false)
public @interface JpaTestConfig {

}
