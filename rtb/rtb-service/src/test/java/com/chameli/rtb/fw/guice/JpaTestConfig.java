package com.chameli.rtb.fw.guice;

import com.chameli.rtb.fw.guice.GuiceJpaLiquibaseManager.DdlGeneration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@GuiceJpaLiquibaseManager.Config(modules = GuiceModule.class, persistenceUnitName = "persistenceUnit-hsqldb", ddlGeneration = DdlGeneration.LIQUIBASE)
public @interface JpaTestConfig {

}
