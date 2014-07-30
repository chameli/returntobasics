package com.chameli.rtb.service.junit.dao;

import com.chameli.rtb.service.junit.dao.GuiceJpaLiquibaseManager.Config;
import com.chameli.rtb.service.junit.dao.GuiceJpaLiquibaseManager.DdlGeneration;

public class ConfigHelper {

    public static boolean isLiquibased(Config config) {
        return DdlGeneration.LIQUIBASE.equals(config.ddlGeneration());
    }

}
