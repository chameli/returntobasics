package com.chameli.rtb.fw.guice;

import com.chameli.rtb.fw.guice.GuiceJpaLiquibaseManager.Config;
import com.chameli.rtb.fw.guice.GuiceJpaLiquibaseManager.DdlGeneration;

public class ConfigHelper {

    public static boolean isLiquibased(Config config) {
        return DdlGeneration.LIQUIBASE.equals(config.ddlGeneration());
    }

}
